package net.sf.json.processors;

import net.sf.json.JsonConfig;

public class JsDateJsonValueProcessor implements JsonValueProcessor {
    private JsonBeanProcessor processor = new JsDateJsonBeanProcessor();

    public Object processArrayValue(Object obj, JsonConfig jsonConfig) {
        return process(obj, jsonConfig);
    }

    public Object processObjectValue(String str, Object obj, JsonConfig jsonConfig) {
        return process(obj, jsonConfig);
    }

    private Object process(Object obj, JsonConfig jsonConfig) {
        return this.processor.processBean(obj, jsonConfig);
    }
}
