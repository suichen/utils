package com.suichen.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

public class BeanUtils {
    public static String[] getNullPropertyNames(Object source) {
        Set<String> nullNames = new HashSet<>();
        BeanWrapper wrapper = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();

        for (PropertyDescriptor pd:pds) {
            Object value = wrapper.getPropertyValue(pd.getName());
            if (value == null) {
                nullNames.add(pd.getName());
            }
        }

        String[] res = new String[nullNames.size()];
        return nullNames.toArray(res);
    }

    public static Field findFieldByName(Class<?> clazz, String filedName) {
        Field field = null;
        while (!clazz.getName().equals(Object.class.getName())) {
            try {
                field = clazz.getDeclaredField(filedName);
                if (field!=null) break;
            }catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }

        return field;
    }

    /**
     * 用于将一个列表转换为列表中的对象的某个属性映射到列表中的对象
     *
     * <pre>
     *      List<UserDTO> userList = userService.queryUsers();
     *      Map<Integer, userDTO> userIdToUser = BeanUtil.mapByKey("userId", userList);
     * </pre>
     *
     * @param key 属性名 唯一
     */
    public static <K,V> Map<K,V> mapByKey(String key, List<? extends Object> datas) {
        if (CollectionUtils.isEmpty(datas)) return new HashMap<>();
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key is null");
        }

        Map<K,V> resultMap = new HashMap<>();

        Class<?> clazz = datas.get(0).getClass();
        Field targetField = findFieldByName(clazz, key);

        if(targetField == null) {
            throw new RuntimeException("key("+key+") does no exist");
        }

        targetField.setAccessible(true);

        for(Object v:datas) {
            try {
                K k = (K) targetField.get(v);
                resultMap.put(k, (V) v);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return resultMap;
    }

    public static <K,V> Map<K,List<V>> mergeListByKey(String key, List<? extends Object> datas) {
        if (CollectionUtils.isEmpty(datas)) return new HashMap<>();
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key is null");
        }

        Map<K, List<V>> resultMap = new HashMap<>();
        Class<?> clazz = datas.get(0).getClass();
        Field targetField = findFieldByName(clazz, key);

        if(targetField == null) {
            throw new RuntimeException("key("+key+") does no exist");
        }

        targetField.setAccessible(true);

        for (Object o:datas) {
            try {
                Object value = targetField.get(o);
                if(value == null || StringUtils.isEmpty((String)value)) {
                    continue;
                }

                if (CollectionUtils.isEmpty(resultMap.get((K) value))) {
                    resultMap.put((K)value, new ArrayList<>());
                }

                resultMap.get((K) value).add((V) o);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return resultMap;
    }

    /**
     * 根据用户指定操作的结果对链表进行聚合
     * @param datas  原链表
     * @param operate 用户指定的操作
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<K, List<V>> mergeListByOperate(List<? extends Object> datas, String fieldName, Function<V,K> operate) {
        if (CollectionUtils.isEmpty(datas)) {
            return new HashMap<>();
        }

        Map<K, List<V>> resultMap = new HashMap<>();
        Class<?> clazz = datas.get(0).getClass();

        Field targetFiled = findFieldByName(clazz, fieldName);
        if (targetFiled == null) {
            throw new RuntimeException("fieldName("+fieldName+") does not exist");
        }

        for(Object o:datas) {
            K key = operate.apply((V) o);

            List<V> values = resultMap.get(key);

            if(CollectionUtils.isEmpty(values)) {
                values = new ArrayList<>();
                resultMap.put(key, values);
            }

            resultMap.get(key).add((V) o);
        }

        return resultMap;
    }
}
