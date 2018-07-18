package com.suichen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackTraceUtils {
    private static Logger logger = LoggerFactory.getLogger(StackTraceUtils.class);

    private static final String REGEX = "((\\w+)\\.?)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private static StackTraceElement[] getMainThreadStackTraceElements() {
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
        Set<Thread> threads = threadMap.keySet();

        for (Thread thread:threads) {
            if (thread.getName().contains("main")) {
                return threadMap.get(thread);
            }
        }
        return null;
    }

    private static StackTraceElement getMainClassStackTraceElement() {
        StackTraceElement[] stackTraceElements = getMainThreadStackTraceElements();
        return stackTraceElements[stackTraceElements.length-1];
    }

    private static Class<?> getMainClass() throws ClassNotFoundException,NoSuchMethodException{
        String className = getMainClassStackTraceElement().getClassName();
        Class<?> clazz = Class.forName(className);
        Method method = clazz.getMethod("main", String[].class);
        return clazz;
    }

    public static String getMainClassPackage() throws ClassNotFoundException,NoSuchMethodException{
        Class<?> clazz = getMainClass();
        String mainClassPackageName = clazz.getPackage().getName();
        logger.info("Main class package path: {}", mainClassPackageName);
        return mainClassPackageName;
    }

    public static String getBasePackage(String packageName, int deep) {
        StringBuilder builder = new StringBuilder();

        Matcher matcher = PATTERN.matcher(packageName);
        int degree = 0;

        while (matcher.find()) {
            degree++;
            if (deep < 0 || degree <= deep) {
                String group = matcher.group(2);
                System.out.println(group);
                builder.append(group).append(".");
            } else {
                break;
            }
        }

        String basePackage = builder.substring(0, builder.length()-1);
        logger.info("package path is {}", basePackage);
        return basePackage;
    }
    public static String getPackagePathByMainClass(int deep) throws ClassNotFoundException,NoSuchMethodException{
        String mainClassPackageName = getMainClassPackage();
        return getBasePackage(mainClassPackageName, deep);
    }
}
