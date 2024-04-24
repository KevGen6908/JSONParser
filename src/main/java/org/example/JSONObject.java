package org.example;

import org.example.JavaCCParser.JSONParser;
import org.example.JavaCCParser.ParseException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class JSONObject extends LinkedHashMap<String, Object>{
    public static Object parseObject(String jsonString) throws ParseException {
        Parser parser = new Parser(jsonString);
        return parser.parse();
    }

    public static Map<String, Object> parseToMap(String data) throws ParseException{
        Parser parser = new Parser(data);
        return parser.object();
    }

    public static <T> T parseValueToClass (LinkedHashMap<String, Object> hashMap, Class<T> clazz) throws Exception{
        var object = clazz.newInstance();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()){
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            if(JSONUtil.isPrimitiveBoxingOrString(field.getType())){
                field.set(object, fieldValue);
            } else if (fieldValue instanceof LinkedHashMap) {
                field.set(object, parseValueToClass((LinkedHashMap<String, Object>) fieldValue, field.getType()));
            } else if (field.getType().isArray()) {
                Class<?> componentType = field.getType().getComponentType();
                Object array = parseArray((ArrayList<?>) fieldValue, componentType);
                field.set(object, array);
            }else if (fieldValue instanceof ArrayList<?> list){
                Class<?> componentType = (Class<?>) ((java.lang.reflect.ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]; // извините меня за такой высер
                ArrayList<Object> arrayList = parserArrayList(list, componentType);
                field.set(object, arrayList);
            }
        }
        return object;
    }

    private static ArrayList<Object> parserArrayList(ArrayList<?> list, Class<?> componentType) throws Exception {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object value : list){
            if(value instanceof LinkedHashMap) {
                arrayList.add(parseValueToClass((LinkedHashMap<String, Object>) value, componentType));
            }else {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    private static Object parseArray(ArrayList<?> list, Class<?> componentType) throws Exception{
        int size = list.size();
        Object array;
        if (componentType.isPrimitive()) {
            if (componentType == int.class) {
                int[] intArray = new int[size];
                for (int i = 0; i < size; i++) {
                    intArray[i] = (int) list.get(i);
                }
                array = intArray;
            } else if (componentType == boolean.class) {
                boolean[] booleanArray = new boolean[size];
                for (int i = 0; i < size; i++) {
                    booleanArray[i] = (boolean) list.get(i);
                }
                array = booleanArray;
            } else if (componentType == byte.class) {
                byte[] byteArray = new byte[size];
                for (int i = 0; i < size; i++) {
                    byteArray[i] = (byte) list.get(i);
                }
                array = byteArray;
            } else if (componentType == char.class) {
                char[] charArray = new char[size];
                for (int i = 0; i < size; i++) {
                    charArray[i] = (char) list.get(i);
                }
                array = charArray;
            } else if (componentType == short.class) {
                short[] shortArray = new short[size];
                for (int i = 0; i < size; i++) {
                    shortArray[i] = (short) list.get(i);
                }
                array = shortArray;
            } else if (componentType == long.class) {
                long[] longArray = new long[size];
                for (int i = 0; i < size; i++) {
                    longArray[i] = (long) list.get(i);
                }
                array = longArray;
            } else if (componentType == float.class) {
                float[] floatArray = new float[size];
                for (int i = 0; i < size; i++) {
                    floatArray[i] = (float) list.get(i);
                }
                array = floatArray;
            } else if (componentType == double.class) {
                double[] doubleArray = new double[size];
                for (int i = 0; i < size; i++) {
                    doubleArray[i] = (double) list.get(i);
                }
                array = doubleArray;
            } else {
                throw new IllegalArgumentException("Unsupported primitive component type: " + componentType);
            }
        } else {
            array = Array.newInstance(componentType, size);
            for (int i = 0; i < size; i++) {
                Object val = list.get(i);
                if (val instanceof LinkedHashMap) {
                    Array.set(array, i, parseValueToClass((LinkedHashMap<String, Object>) val, componentType));
                } else {
                    Array.set(array, i, val);
                }
            }
        }
        return array;
    }

    public static <T> T parserToClass(String text, Class<T> Clazz) throws Exception{
        if(!JSONUtil.isConvertibleToJson(Clazz)){
            throw new IllegalArgumentException("Can't parse into class: " + Clazz.getName());
        }
        Map<String, Object> parsedJson = parseToMap(text);

        Field[] ClazzFields = Clazz.getDeclaredFields();
        if (parsedJson.size() != ClazzFields.length){
            throw new IllegalArgumentException("JSON file and class have different key set sizes: " + Clazz.getName());
        }

        T instance = parseValueToClass((LinkedHashMap<String, Object>) parsedJson, Clazz);
        return instance;
    }
    public String geString(String key){
        Object val = get(key);
        return val == null ? null : val.toString();
    }

    public JSONObject getJSONObject(String key){
        Object val = get(key);
        if(val instanceof JSONObject){
            return (JSONObject) val;
        }
        return null;
    }

    public JSONArray getJSONArray(String key){
        Object val = get(key);
        if(val instanceof JSONArray){
            return (JSONArray) val;
        }
        return null;
    }

    public Integer getInt(String key){
        Object val = get(key);
        return val == null ? 0 : TypeCast.castToInteger(val);
    }

    public boolean getBoolean(String key){
        Object val = get(key);
        return val == null ? false : TypeCast.castToBoolean(val);
    }
}
