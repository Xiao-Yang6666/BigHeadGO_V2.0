package com.bigheadgo.utils.mybatisPage;

import java.lang.reflect.Field;

/**
 * 反射Util
 * author: xiaoYang
 * time: 2021/11/15 18:18
 */
public class ReflectUtil {

    /**
     * 获取需要反射的类 获取Field对象
     *
     * @param obj       任何对象
     * @param fieldName 属性名
     * @return Field对象
     */
    public static Field getField(Object obj, String fieldName) {
        // 循环的目的是 如果未找到具有指定名称的字段 就循环去他父类
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }
        }
        return null;
    }

    /**
     * 获取某个参数的内容
     *
     * @param obj       实体类
     * @param fieldName 属性名
     * @return 相应的内容
     * @throws IllegalAccessException 访问权限异常
     */
    public static Object getValueByFieldName(Object obj, String fieldName) throws IllegalAccessException {
        Field field = getField(obj, fieldName);
        Object value = null;
        if (field != null) {
            // 访问检查
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 修改某个参数的内容
     *
     * @param obj       实体类
     * @param fieldName 属性名
     * @param value     修改后的值
     * @throws IllegalAccessException 访问权限异常
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value) throws IllegalAccessException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        // 访问检查
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }
}
