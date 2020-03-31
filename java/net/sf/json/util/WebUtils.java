package net.sf.json.util;

import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.Iterator;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class WebUtils {
    private static final WebHijackPreventionStrategy DEFAULT_WEB_HIJACK_PREVENTION_STRATEGY = WebHijackPreventionStrategy.INFINITE_LOOP;
    private static WebHijackPreventionStrategy webHijackPreventionStrategy = DEFAULT_WEB_HIJACK_PREVENTION_STRATEGY;

    public static WebHijackPreventionStrategy getWebHijackPreventionStrategy() {
        return webHijackPreventionStrategy;
    }

    public static String protect(JSON json) {
        return protect(json, false);
    }

    public static String protect(JSON json, boolean z) {
        return webHijackPreventionStrategy.protect(!z ? json.toString(0) : toString(json));
    }

    public static void setWebHijackPreventionStrategy(WebHijackPreventionStrategy webHijackPreventionStrategy2) {
        if (webHijackPreventionStrategy2 == null) {
            webHijackPreventionStrategy2 = DEFAULT_WEB_HIJACK_PREVENTION_STRATEGY;
        }
        webHijackPreventionStrategy = webHijackPreventionStrategy2;
    }

    public static String toString(JSON json) {
        if (json instanceof JSONObject) {
            return toString((JSONObject) json);
        }
        if (json instanceof JSONArray) {
            return toString((JSONArray) json);
        }
        return toString((JSONNull) json);
    }

    private static String join(JSONArray jSONArray) {
        int size = jSONArray.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(StorageInterface.KEY_SPLITER);
            }
            stringBuffer.append(toString(jSONArray.get(i)));
        }
        return stringBuffer.toString();
    }

    private static String quote(String str) {
        if (str.indexOf(" ") > -1 || str.indexOf(Config.TRACE_TODAY_VISIT_SPLIT) > -1) {
            return JSONUtils.quote(str);
        }
        return str;
    }

    private static String toString(JSONArray jSONArray) {
        try {
            return new StringBuffer().append('[').append(join(jSONArray)).append(']').toString();
        } catch (Exception e) {
            return null;
        }
    }

    private static String toString(JSONNull jSONNull) {
        return jSONNull.toString();
    }

    private static String toString(JSONObject jSONObject) {
        if (jSONObject.isNullObject()) {
            return JSONNull.getInstance().toString();
        }
        Iterator keys = jSONObject.keys();
        StringBuffer stringBuffer = new StringBuffer("{");
        while (keys.hasNext()) {
            if (stringBuffer.length() > 1) {
                stringBuffer.append(',');
            }
            Object next = keys.next();
            stringBuffer.append(quote(next.toString()));
            stringBuffer.append(':');
            stringBuffer.append(toString(jSONObject.get(String.valueOf(next))));
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    private static String toString(Object obj) {
        if (obj instanceof JSON) {
            return toString((JSON) obj);
        }
        return JSONUtils.valueToString(obj);
    }

    private WebUtils() {
    }
}
