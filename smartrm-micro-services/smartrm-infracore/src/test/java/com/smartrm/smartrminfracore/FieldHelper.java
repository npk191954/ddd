package com.smartrm.smartrminfracore;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

/**
 * 设置静态属性工具类
 * 
 * @author dailj
 * @date 2022/11/30 22:37
 */
public final class FieldHelper {
    
    public static void setStaticFinalField(Class<?> clazz, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        FieldUtils.removeFinalModifier(field);
        FieldUtils.writeStaticField(field, fieldValue, true);
    }
}
