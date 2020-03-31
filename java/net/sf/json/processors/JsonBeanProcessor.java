package net.sf.json.processors;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public interface JsonBeanProcessor {
    JSONObject processBean(Object obj, JsonConfig jsonConfig);
}
