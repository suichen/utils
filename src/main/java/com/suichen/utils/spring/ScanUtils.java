package com.suichen.utils.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScanUtils {
    private static List<String> listClassName = new ArrayList<>();
    private static List<String> componentList = new ArrayList<>();
    private static Map<String, String> interfaceAndImplMap = new ConcurrentHashMap<>();

    public static <A extends Annotation> void addNewAnnotation(Class<A> annotationClass, Class<?> aClass) throws ClassNotFoundException {

    }
    public static void resolveComponent(String className) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);

    }

    public static List<String> getComponentList(String packageName) {
        List<String> clazzNameList = getClassName(packageName);
        makeInterfaceAndImplMap(clazzNameList);

        for (String className:clazzNameList) {
            /*try {
                //TODO
            }ca*/
        }

        return clazzNameList;
    }

    /**
     * 扫描指定包下所有类，进行加载
     *
     * 扫描指定包下所有有两种方式：
     *  1. spring 中@Component注解的扫描方式
     *  2. spring boot 加载 spring.factories 文件中类的方式
     * @param packageName
     * @return
     */
    public static List<String> getClassName(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String newPackageName = packageName.replace(".", "/");

        try {
            Enumeration<URL> urls = classLoader.getResources(newPackageName);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                File packageFile = new File(url.getPath());
                File[] files = packageFile.listFiles();

                if (files == null) break;

                for (File file:files) {
                    if(file.getName().endsWith(".class")) {
                        String templeName = packageName.replace("/", ".")
                                                + "."+file.getName();
                        String clazzName = templeName.substring(0, templeName.lastIndexOf("."));
                        listClassName.add(clazzName);
                    } else {
                        String nextPackageName = newPackageName + "." +file.getName();
                        getClassName(nextPackageName);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listClassName;
    }


    private static Map<String, String> makeInterfaceAndImplMap(List<String> clazzNameList) {
        Class<?> aClass = null;

        List<String> interfaceNameList = new ArrayList<>();
        List<String> interfaceExist = new ArrayList<>();

        for (String className:clazzNameList) {
            try {
                aClass = Class.forName(className);
            }catch (Exception e) {
                e.printStackTrace();
            }

            if (aClass.isInterface()) {
                interfaceNameList.add(aClass.getName());
            }
        }


        for (String className:listClassName) {
            Class<?> bClass = null;

            try {
                bClass = Class.forName(className);
            }catch (Exception e) {
                e.printStackTrace();
            }

            Class<?>[] interfaces = bClass.getInterfaces();

            if (interfaces!=null && interfaces.length != 0) {
                for (String interfaceName:interfaceNameList) {
                    for (Class<?> interfaceClass:interfaces) {
                        if (interfaceClass.getName().equals(interfaceName)) {
                            interfaceAndImplMap.put(interfaceName, bClass.getName());
                            interfaceExist.add(interfaceName);
                        }
                    }
                }
            }
        }

        interfaceNameList.removeAll(interfaceExist);
        if (interfaceNameList.size() > 0) {
            for (String proxyInterface:interfaceNameList) {
                interfaceAndImplMap.put(proxyInterface, "proxy");
            }
        }

        return null;
    }

}
