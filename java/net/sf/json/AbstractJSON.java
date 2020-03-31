package net.sf.json;

import java.util.HashSet;
import java.util.Set;
import net.sf.json.util.JsonEventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

abstract class AbstractJSON {
    static Class class$net$sf$json$AbstractJSON;
    private static ThreadLocal cycleSet = new ThreadLocal() {
        /* access modifiers changed from: protected */
        public synchronized Object initialValue() {
            return new HashSet();
        }
    };
    private static final Log log;

    AbstractJSON() {
    }

    static {
        Class cls;
        if (class$net$sf$json$AbstractJSON == null) {
            cls = class$("net.sf.json.AbstractJSON");
            class$net$sf$json$AbstractJSON = cls;
        } else {
            cls = class$net$sf$json$AbstractJSON;
        }
        log = LogFactory.getLog(cls);
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected static boolean addInstance(Object obj) {
        return getCycleSet().add(obj);
    }

    protected static void fireArrayEndEvent(JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onArrayEnd : jsonConfig.getJsonEventListeners()) {
                try {
                    onArrayEnd.onArrayEnd();
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireArrayStartEvent(JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onArrayStart : jsonConfig.getJsonEventListeners()) {
                try {
                    onArrayStart.onArrayStart();
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireElementAddedEvent(int i, Object obj, JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onElementAdded : jsonConfig.getJsonEventListeners()) {
                try {
                    onElementAdded.onElementAdded(i, obj);
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireErrorEvent(JSONException jSONException, JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onError : jsonConfig.getJsonEventListeners()) {
                try {
                    onError.onError(jSONException);
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireObjectEndEvent(JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onObjectEnd : jsonConfig.getJsonEventListeners()) {
                try {
                    onObjectEnd.onObjectEnd();
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireObjectStartEvent(JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onObjectStart : jsonConfig.getJsonEventListeners()) {
                try {
                    onObjectStart.onObjectStart();
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void firePropertySetEvent(String str, Object obj, boolean z, JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onPropertySet : jsonConfig.getJsonEventListeners()) {
                try {
                    onPropertySet.onPropertySet(str, obj, z);
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void fireWarnEvent(String str, JsonConfig jsonConfig) {
        if (jsonConfig.isEventTriggeringEnabled()) {
            for (JsonEventListener onWarning : jsonConfig.getJsonEventListeners()) {
                try {
                    onWarning.onWarning(str);
                } catch (RuntimeException e) {
                    log.warn(e);
                }
            }
        }
    }

    protected static void removeInstance(Object obj) {
        getCycleSet().remove(obj);
    }

    private static Set getCycleSet() {
        return (Set) cycleSet.get();
    }
}
