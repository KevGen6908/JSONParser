package org.example;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class JSONUtil {
    public static boolean isBoxingType(Class<?> clazz){
        return clazz != null && clazz.getPackage() != null && clazz.getPackage().equals(Void.class.getPackage());
    }

    public static boolean isPrimitiveBoxingOrString(Class<?> clazz){
        return clazz != null && (clazz.isPrimitive() || isBoxingType(clazz) || clazz.equals(String.class));
    }

    public static boolean isConvertibleToJson(Class<?> clazz){
        if(isPrimitiveBoxingOrString(clazz) || clazz.isArray() || List.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)){
            return true;
        }
        for(Field field : clazz.getDeclaredFields()){
            if(!isConvertibleToJson(field.getType())){
                return false;
            }
        }
        return true;
    }
}
