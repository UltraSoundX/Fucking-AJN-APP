package net.sf.json.processors;

import net.sf.json.JsonConfig;

public interface JsonValueProcessor {
    Object processArrayValue(Object obj, JsonConfig jsonConfig);

    Object processObjectValue(String str, Object obj, JsonConfig jsonConfig);
}
