package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class JSONArray extends ArrayList<Object> {
    public JSONArray getArray(int index){
        Object val = get(index);
        if (val instanceof JSONArray){
            return (JSONArray) val;
        }
        return null;
    }

    public JSONObject getJSONObject(int index){
        Object val = get(index);
        if (val instanceof JSONObject){
            return (JSONObject) val;
        }
        return null;
    }

    public String getString(int index){
        Object val = get(index);
        return val == null ? null :val.toString();
    }

    public int getInt(int index){
        Object val = get(index);
        return val == null ? 0 : TypeCast.castToInteger(val);
    }

    public Boolean getBoolean(int index){
        return TypeCast.castToBoolean(get(index));
    }

    public boolean getBooleanValue(int index){
        Object val = get(index);
        return val == null ? false : TypeCast.castToBoolean(val);
    }
}
