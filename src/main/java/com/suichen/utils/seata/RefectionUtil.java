package com.suichen.utils.seata;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class RefectionUtil {
    public static final int MAX_NEST_DEPTH = 20;

    public static Class<?> getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
    }

    public static Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException{
        Class<?> cl = target.getClass();

        int i = 0;

        while ((i++) < MAX_NEST_DEPTH && cl != null) {
            try {
                Field field = cl.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(target);
            }catch (Exception e) {
                cl = cl.getSuperclass();
            }
        }

        throw new NoSuchFieldException("class:"+target.getClass()+", field:"+fieldName);
    }


    public static void modifyStaticFinalField(Class cla, String modifyFieldName, Object newValue)
    throws NoSuchFieldException, IllegalAccessException{
        Field field = cla.getDeclaredField(modifyFieldName);
        field.setAccessible(true);

        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(cla, newValue);
    }
}
