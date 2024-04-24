package org.example;

public class TypeCast {
    public static final String castToString(Object val){
        return val == null ? null : val.toString();
    }
    public static final Integer castToInteger(Object val){
        if(val == null){
            return null;
        }else if(val instanceof Number){
            return ((Number) val).intValue();
        } else if (val instanceof  String) {
            String strVal = (String) val;
            return strVal.length() == 0? null : Integer.parseInt(strVal);
        }else{
            throw new IllegalArgumentException("Can not cast to int, value " + val);
        }
    }

    public static final Boolean castToBoolean(Object val){
        if(val == null){
            return null;
        } else if (val instanceof Boolean) {
            return (Boolean) val;
        } else if (val instanceof Number) {
            return ((Number) val).intValue() == 1;
        }else {
            if(val instanceof String){
                String strVal = (String) val;
                if(strVal.length() == 0){
                    return null;
                }
                if ("true".equals(strVal)){
                    return Boolean.TRUE;
                }
                if ("false".equals(strVal)){
                    return Boolean.FALSE;
                }
                if ("1".equals(strVal)){
                    return Boolean.TRUE;
                }
                if ("0".equals(strVal)){
                    return Boolean.FALSE;
                }
            }
        }
        throw new IllegalArgumentException("Can not cast to boolean, value " + val);
    }
}
