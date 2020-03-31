package net.sf.json.processors;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.util.JSONUtils;

public class DefaultDefaultValueProcessor implements DefaultValueProcessor {
    public Object getDefaultValue(Class cls) {
        if (JSONUtils.isArray(cls)) {
            return new JSONArray();
        }
        if (JSONUtils.isNumber(cls)) {
            if (JSONUtils.isDouble(cls)) {
                return new Double(0.0d);
            }
            return new Integer(0);
        } else if (JSONUtils.isBoolean(cls)) {
            return Boolean.FALSE;
        } else {
            if (JSONUtils.isString(cls)) {
                return "";
            }
            return JSONNull.getInstance();
        }
    }
}
