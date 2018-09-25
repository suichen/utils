package com.suichen.utils.spi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class ExtensionLoader<T> {
    private static final String SPI_DIRECTORY = "META-INF/services/";
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");
    private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>();

    private final Class<?> type;

    private String cachedDefaultName;

    private Map<String, IllegalStateException> exceptions = new ConcurrentHashMap<>();

    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();
    private final ConcurrentMap<String, Holder<Object>> cachedInstances =
            new ConcurrentHashMap<>();

    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(SPI.class);
    }

    public ExtensionLoader(Class<?> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public static <T> ExtensionLoader getExtensionLoader(Class<T> type) {
        if (type == null)
            throw new IllegalArgumentException("Extension type == null");
        if (!type.isInterface())
            throw new IllegalArgumentException("Extension type("+type+") is no interface");
        if (!withExtensionAnnotation(type))
            throw new IllegalArgumentException("Extension type("+type+") is not extension, because" +
                    " Without @"+SPI.class.getSimpleName()+" Annotation!");

        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader<>(type));
            loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        }

        return loader;
    }

    private static ClassLoader findClassLoader() {
        return ExtensionLoader.class.getClassLoader();
    }

    /**
     * 返回指定名字的扩展，如果指定名字的扩展不存在，则抛异常
     * @param name
     * @return
     */
    public T getExtension(String name) {
        if (name == null || name.length() == 0)
            throw new IllegalArgumentException("Extension name == null");
        if ("true".equals(name)) {
            return getDefaultExtension();
        }

        Holder<Object> holder = cachedInstances.get(name);

        if (holder == null) {
            cachedInstances.putIfAbsent(name, new Holder<>());
            holder = cachedInstances.get(name);
        }

        Object instance = holder.getValue();
        if (instance == null) {
            synchronized (holder) {
                instance = holder.getValue();
                if (instance == null) {
                    instance = createExtension(name);
                    holder.setValue(instance);
                }
            }
        }

        return (T)instance;
    }

    private T createExtension(String name) {
        Class<?> clazz = getExtensionClasses().get(name);
        if (clazz == null) {
            throw findException(name);
        }

        try {
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                EXTENSION_INSTANCES.putIfAbsent(clazz, (T)clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            return instance;
        }catch (Throwable t) {
            throw new IllegalStateException("Extension instance(name: " + name + ", class: " +
                    type + ")  could not be instantiated: " + t.getMessage(), t);
        }
    }

    //获取扩展点class，并缓存
    private Map<String, Class<?>> getExtensionClasses() {
        Map<String, Class<?>> classes = cachedClasses.getValue();
        if (classes == null) {
            synchronized (cachedClasses) {
                classes = cachedClasses.getValue();
                if (classes == null) {
                    classes = loadExtensionClasses();
                    cachedClasses.setValue(classes);
                }
            }
        }
        return classes;
    }

    //设置接口默认的实现类名  加载文件
    private Map<String, Class<?>> loadExtensionClasses() {
        final SPI defaultAnnotation = type.getAnnotation(SPI.class);
        if (defaultAnnotation!=null) {
            String value = defaultAnnotation.value();
            if (value!=null && (value.trim().length() > 0)) {
                String[] names = NAME_SEPARATOR.split(value);
                if (names.length > 1) {
                    throw new IllegalArgumentException("more than 1 default extension name on extension "+type.getName());
                }

                if (names.length == 1) cachedDefaultName = names[0];
            }
        }

        Map<String, Class<?>> extensionClasses = new HashMap<>();
        loadFile(extensionClasses, SPI_DIRECTORY);
        return extensionClasses;
    }

    public void loadFile(Map<String, Class<?>> extensionClasses, String dir) {
        String fileName = dir+type.getName();

        try {
            Enumeration<URL> urls;
            ClassLoader classLoader = findClassLoader();
            if (classLoader != null) {
                urls = classLoader.getResources(fileName);
            }else {
                urls = ClassLoader.getSystemResources(fileName);
            }

            if (urls!=null) {
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();

                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

                        try {
                            String line = null;
                            while ((line=reader.readLine()) != null) {
                                final int ci = line.indexOf('#');
                                if (ci >= 0) line = line.substring(0, ci);
                                line = line.trim();

                                if (line.length() > 0) {
                                    try {
                                        String name = null;
                                        int i = line.indexOf('=');
                                        if (i > 0) {
                                            name = line.substring(0, i).trim();
                                            line = line.substring(i+1).trim();
                                        }

                                        if (line.length() > 0) {
                                            Class<?> clazz = Class.forName(line, true, classLoader);
                                            if (!type.isAssignableFrom(clazz)) {
                                                throw new IllegalStateException("Error when load extension class(interface: "
                                                +type+", class line: "+clazz.getName()+"), class "+clazz.getName()+" is not subtype of interface" );
                                            }

                                            extensionClasses.put(name, clazz);
                                        }
                                    }catch (Throwable t) {
                                        IllegalStateException e = new IllegalStateException("Failed to load extension class(interface: " + type + ", class line: " + line + ") in " + url + ", cause: " + t.getMessage(), t);
                                        exceptions.put(line, e);
                                    }
                                }
                            }
                        }finally {
                            reader.close();
                        }
                    }catch (Throwable ex) {

                    }
                }
            }
        }catch (Throwable e) {

        }
    }
    public T getDefaultExtension() {
        getExtensionClasses();
        if (null == cachedDefaultName || cachedDefaultName.length() == 0 || "true".equals(cachedDefaultName)) {
            return null;
        }

        return getExtension(cachedDefaultName);
    }

    private IllegalStateException findException(String name) {
        for (Map.Entry<String, IllegalStateException> entry:exceptions.entrySet()) {
            if (entry.getKey().toLowerCase().contains(name.toLowerCase())) {
                return entry.getValue();
            }
        }

        StringBuffer buf = new StringBuffer("No such extension "+type.getName()+" by name "+name);

        int i = 1;
        for (Map.Entry<String, IllegalStateException> entry : exceptions.entrySet()) {
            if (i == 1) {
                buf.append(", possible causes: ");
            }

            buf.append("\r\n(");
            buf.append(i++);
            buf.append(") ");
            buf.append(entry.getKey());
            buf.append(":\r\n");
            buf.append(entry.getValue().toString());
        }
        return new IllegalStateException(buf.toString());
    }

}
