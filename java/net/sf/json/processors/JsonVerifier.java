package net.sf.json.processors;

import java.math.BigDecimal;
import java.math.BigInteger;
import net.sf.json.JSON;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONString;

public final class JsonVerifier {
    public static boolean isValidJsonValue(Object obj) {
        if (JSONNull.getInstance().equals(obj) || (obj instanceof JSON) || (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof BigInteger) || (obj instanceof BigDecimal) || (obj instanceof JSONFunction) || (obj instanceof JSONString) || (obj instanceof String)) {
            return true;
        }
        return false;
    }
}
