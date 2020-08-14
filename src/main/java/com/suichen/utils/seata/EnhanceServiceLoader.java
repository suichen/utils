package com.suichen.utils.seata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EnhanceServiceLoader {

    public static <S> List<S> loadAll(Class<S> service, Class[] argsType, Object[] args) {
        return InnerEnhanceServiceLoader.getServiceLoader(service).loadAll(argsType, args, findClassLoader());
    }

    public static <S> S load(Class<S> service, String activateName)  {
        return InnerEnhanceServiceLoader.getServiceLoader(service).loadExtension(activateName, findClassLoader(),
                null,null);
    }

    private static ClassLoader findClassLoader() {
        return EnhanceServiceLoader.class.getClassLoader();
    }
    private static class InnerEnhanceServiceLoader<S> {
        private static final Logger logger = LoggerFactory.getLogger(InnerEnhanceServiceLoader.class);
        private static final String SERVICES_DIRECTORY = "META-INF/services";
        private static final String SEATA_DIRECTORY = "META-INF/seata";

        private static final ConcurrentHashMap<Class<?>, InnerEnhanceServiceLoader<?>> SERVICE_LOADERS =
                new ConcurrentHashMap<>();

        private final Class<S> type;

        private final ConcurrentHashMap<Class<?>, ExtensionDefinition> classToDefinitionMap =
                new ConcurrentHashMap<>();

        private final Holder<List<ExtensionDefinition>> definitionHolder =
                new Holder<>();

        private final ConcurrentHashMap<ExtensionDefinition, Holder<Object>> definitionToInstanceMap =
                new ConcurrentHashMap<>();

        private final ConcurrentHashMap<String, List<ExtensionDefinition>> nameToDefinitionsMap =
                new ConcurrentHashMap<>();

        private InnerEnhanceServiceLoader(Class<S> type) {
            this.type = type;
        }


        private S load(String activateName, ClassLoader loader) {
            return loadExtension(activateName, loader, null, null);
        }

        @SuppressWarnings("rawtypes")
        private S loadExtension(String activateName, ClassLoader loader, Class[] argTypes, Object[] args) {
            if (StringUtils.isEmpty(activateName)) {
                throw new IllegalArgumentException("the name of service provider for ["+type.getName()+"] name is null");
            }

            try {
                loadAllExtensionClass(loader);
                ExtensionDefinition cachedExtensionDefinition = getCachedExtensionDefinition(activateName);
                return getExtensionInstance(cachedExtensionDefinition, loader, argTypes, args);
            }catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        private ExtensionDefinition getCachedExtensionDefinition(String activateName) {
            if (nameToDefinitionsMap.containsKey(activateName)) {
                List<ExtensionDefinition> definitions = nameToDefinitionsMap.get(activateName);
                return definitions.get(definitions.size()-1);
            }
            return null;
        }
        private static <S> InnerEnhanceServiceLoader<S> getServiceLoader(Class<S> type) {
            if (type == null) {
                throw new IllegalArgumentException("Enhanced Service type == null");
            }

            InnerEnhanceServiceLoader<S> loader = (InnerEnhanceServiceLoader<S>) SERVICE_LOADERS.get(type);
            if (loader == null) {
                SERVICE_LOADERS.put(type, new InnerEnhanceServiceLoader<>(type));
                loader = (InnerEnhanceServiceLoader<S>) SERVICE_LOADERS.get(type);
            }

            return loader;
        }

        private List<S> loadAll(Class[] argsType, Object[] args, ClassLoader loader) {
            List<S> allInstances = new ArrayList<>();
            List<Class> allClazzs = getAllExtensionClass(loader);

            if (CollectionUtils.isEmpty(allClazzs)) {
                return allInstances;
            }

            try {
                for (Class clazz:allClazzs) {
                    ExtensionDefinition definition = classToDefinitionMap.get(clazz);
                    allInstances.add(getExtensionInstance(definition, loader, argsType, args));
                }
            }catch (Exception e) {

            }

            return allInstances;
        }

        private List<Class> getAllExtensionClass(ClassLoader loader) {
            return loadAllExtensionClass(loader);
        }

        private List<Class> loadAllExtensionClass(ClassLoader loader) {
            List<Class> result;
            List<ExtensionDefinition> definitions = definitionHolder.get();
            if (definitions == null) {
                synchronized (definitionHolder) {
                    definitions = definitionHolder.get();
                    if (definitions == null) {
                        definitions = findAllExtensionDefinition(loader);
                        definitionHolder.set(definitions);
                    }
                }
            }

            return definitions.stream().map(def->def.getServiceClass())
                    .collect(Collectors.toList());
        }

        private List<ExtensionDefinition> findAllExtensionDefinition(ClassLoader loader) {
            List<ExtensionDefinition> extensionDefinitions = new ArrayList<>();
            try {
                loadFile(SERVICES_DIRECTORY, loader, extensionDefinitions);
                loadFile(SEATA_DIRECTORY, loader, extensionDefinitions);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

            return extensionDefinitions;
        }

        private void loadFile(String dir, ClassLoader loader,
                              List<ExtensionDefinition> extensions) throws IOException {

            String fileName = dir + type.getName();
            Enumeration<URL> urls;
            if (loader!=null) {
                urls = loader.getResources(fileName);
            }else {
                urls = ClassLoader.getSystemResources(fileName);
            }

            if (urls!=null) {
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"))) {
                        String line = null;
                        while ((line = reader.readLine())!= null) {
                            final int ci = line.indexOf('#');
                            if (ci > 0) {
                                line = line.substring(0, ci);
                            }
                            line = line.trim();
                            if (line.length() > 0) {
                                try {
                                    Class<?> clazz = Class.forName(line, true, loader);
                                    ExtensionDefinition extensionDefinition =
                                            getUnloadedExtensionDefinition(clazz);
                                    if (extensionDefinition == null) {
                                        logger.info("The same extension {} has already been loaded, skipped", line);
                                        continue;
                                    }
                                    extensions.add(extensionDefinition);
                                }catch (LinkageError | ClassNotFoundException e) {
                                    logger.warn("Load [{}] class fail. {}", line, e.getMessage());
                                }
                            }
                        }
                    }catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                }
            }
        }

        private ExtensionDefinition getUnloadedExtensionDefinition(Class<?> clazz) {
            String serviceName = null;
            Integer priority = 0;
            Scope scope = Scope.SINGLETON;

            LoadLevel loadLevel = clazz.getAnnotation(LoadLevel.class);
            if (loadLevel!=null) {
                serviceName = loadLevel.name();
                priority = loadLevel.order();
                scope = loadLevel.scope();
            }

            if (!classToDefinitionMap.contains(clazz)) {
                ExtensionDefinition result = new ExtensionDefinition(serviceName, clazz, priority, scope);
                classToDefinitionMap.put(clazz, result);
                if (serviceName!=null) {
                    nameToDefinitionsMap.computeIfAbsent(serviceName, e->new ArrayList<>()).add(result);
                }
                return result;
            }
            return null;
        }

        private S getExtensionInstance(ExtensionDefinition definition, ClassLoader loader, Class[] argTypes,
                                       Object[] args) {

            if (definition == null) {
                throw new RuntimeException("not find service provider for:"+type.getName());
            }

            if (Scope.SINGLETON.equals(definition.getScope())) {
                Holder<Object> holder = definitionToInstanceMap.get(definition);
                if (holder == null) {
                    definitionToInstanceMap.put(definition, new Holder<>());
                    holder = definitionToInstanceMap.get(definition);
                }

                Object instacne = holder.get();

                if (instacne == null) {
                    synchronized (holder) {
                        instacne = holder.get();
                        if (instacne == null) {
                            instacne = createNewExtension(definition, loader, argTypes, args);
                            holder.set(instacne);
                        }
                    }
                }

                return (S) instacne;
            }else {
                return createNewExtension(definition, loader, argTypes, args);
            }

        }

        private S createNewExtension(ExtensionDefinition definition, ClassLoader loader,
                                     Class[] argTypes, Object[] args) {
            Class<?> clazz = definition.getServiceClass();
            try {
                S newInatance = initInstance(clazz, argTypes, args);
                return newInatance;
            }catch (Throwable t) {
                throw new IllegalStateException("Extension instance(definition:"+definition+", Class:"+type
                +") could not be instantiated. "+t.getMessage(), t);
            }
        }

        private S initInstance(Class implClazz, Class[] argTypes, Object[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
            S s = null;
            if (argTypes!=null && args!=null) {
                Constructor<S> constructor = implClazz.getDeclaredConstructor(argTypes);
                s = type.cast(constructor.newInstance(args));
            }else {
                s = type.cast(implClazz.newInstance());
            }

            /*
            if (s instanceof Initialize) {

            }
            */
            return s;
        }
    }

    private static class Holder<T> {
        private volatile T value;

        private void set(T value) {
            this.value = value;
        }

        private T get() {
            return value;
        }
    }
}
