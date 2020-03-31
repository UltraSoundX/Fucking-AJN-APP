package net.sf.json.processors;

import com.baidu.mobstat.Config;
import java.sql.Date;
import java.util.Calendar;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsDateJsonBeanProcessor implements JsonBeanProcessor {
    public JSONObject processBean(Object obj, JsonConfig jsonConfig) {
        Object obj2;
        if (obj instanceof Date) {
            obj2 = new java.util.Date(((Date) obj).getTime());
        } else {
            obj2 = obj;
        }
        if (!(obj2 instanceof java.util.Date)) {
            return new JSONObject(true);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime((java.util.Date) obj2);
        return new JSONObject().element("year", instance.get(1)).element("month", instance.get(2)).element(Config.TRACE_VISIT_RECENT_DAY, instance.get(5)).element("hours", instance.get(11)).element("minutes", instance.get(12)).element("seconds", instance.get(13)).element("milliseconds", instance.get(14));
    }
}
