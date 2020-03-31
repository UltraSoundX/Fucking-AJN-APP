package net.sf.json.groovy;

import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.MissingMethodException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JsonGroovyBuilder extends GroovyObjectSupport {
    private static final String JSON = "json";
    private JSON current;
    private Map properties = new HashMap();
    private Stack stack = new Stack();

    public Object getProperty(String str) {
        if (this.stack.isEmpty()) {
            return _getProperty(str);
        }
        Object peek = this.stack.peek();
        if (!(peek instanceof JSONObject)) {
            return _getProperty(str);
        }
        JSONObject jSONObject = (JSONObject) peek;
        if (jSONObject.containsKey(str)) {
            return jSONObject.get(str);
        }
        return _getProperty(str);
    }

    public Object invokeMethod(String str, Object obj) {
        JSON json;
        int i = 0;
        if (JSON.equals(str) && this.stack.isEmpty()) {
            return createObject(str, obj);
        }
        Object[] objArr = (Object[]) obj;
        if (objArr.length == 0) {
            throw new MissingMethodException(str, getClass(), objArr);
        }
        if (objArr.length > 1) {
            this.stack.push(new JSONArray());
            while (true) {
                int i2 = i;
                if (i2 >= objArr.length) {
                    break;
                }
                if (objArr[i2] instanceof Closure) {
                    append(str, createObject((Closure) objArr[i2]));
                } else if (objArr[i2] instanceof Map) {
                    append(str, createObject((Map) objArr[i2]));
                } else if (objArr[i2] instanceof List) {
                    append(str, createArray((List) objArr[i2]));
                } else {
                    _append(str, objArr[i2], (JSON) this.stack.peek());
                }
                i = i2 + 1;
            }
            this.stack.pop();
            json = null;
        } else if (objArr[0] instanceof Closure) {
            json = createObject((Closure) objArr[0]);
        } else if (objArr[0] instanceof Map) {
            json = createObject((Map) objArr[0]);
        } else if (objArr[0] instanceof List) {
            json = createArray((List) objArr[0]);
        } else {
            json = null;
        }
        if (this.stack.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate(str, (Object) this.current);
            this.current = jSONObject;
        } else if (((JSON) this.stack.peek()) instanceof JSONObject) {
            if (this.current != null) {
                json = this.current;
            }
            append(str, json);
        }
        return this.current;
    }

    public void setProperty(String str, Object obj) {
        if (obj instanceof GString) {
            obj = obj.toString();
            try {
                obj = JSONSerializer.toJSON(obj);
            } catch (JSONException e) {
            }
        } else if (obj instanceof Closure) {
            obj = createObject((Closure) obj);
        } else if (obj instanceof Map) {
            obj = createObject((Map) obj);
        } else if (obj instanceof List) {
            obj = createArray((List) obj);
        }
        append(str, obj);
    }

    private Object _getProperty(String str) {
        if (this.properties.containsKey(str)) {
            return this.properties.get(str);
        }
        return JsonGroovyBuilder.super.getProperty(str);
    }

    private void append(String str, Object obj) {
        if (!this.stack.isEmpty()) {
            this.current = (JSON) this.stack.peek();
            _append(str, obj, this.current);
            return;
        }
        this.properties.put(str, obj);
    }

    private void _append(String str, Object obj, JSON json) {
        if (json instanceof JSONObject) {
            ((JSONObject) json).accumulate(str, obj);
        } else if (json instanceof JSONArray) {
            ((JSONArray) json).element(obj);
        }
    }

    private JSON createArray(List list) {
        JSONArray jSONArray = new JSONArray();
        this.stack.push(jSONArray);
        for (Object next : list) {
            if (next instanceof Closure) {
                next = createObject((Closure) next);
            } else if (next instanceof Map) {
                next = createObject((Map) next);
            } else if (next instanceof List) {
                next = createArray((List) next);
            }
            jSONArray.element(next);
        }
        this.stack.pop();
        return jSONArray;
    }

    private JSON createObject(Closure closure) {
        JSONObject jSONObject = new JSONObject();
        this.stack.push(jSONObject);
        closure.setDelegate(this);
        closure.call();
        this.stack.pop();
        return jSONObject;
    }

    private JSON createObject(Map map) {
        JSONObject jSONObject = new JSONObject();
        this.stack.push(jSONObject);
        for (Entry entry : map.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Closure) {
                value = createObject((Closure) value);
            } else if (value instanceof Map) {
                value = createObject((Map) value);
            } else if (value instanceof List) {
                value = createArray((List) value);
            }
            jSONObject.element(valueOf, value);
        }
        this.stack.pop();
        return jSONObject;
    }

    /* JADX WARNING: type inference failed for: r0v24, types: [java.lang.Throwable, net.sf.json.JSONException] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v24, types: [java.lang.Throwable, net.sf.json.JSONException]
  assigns: [net.sf.json.JSONException]
  uses: [java.lang.Throwable]
  mth insns count: 71
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private net.sf.json.JSON createObject(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            int r1 = r6.length
            if (r1 != 0) goto L_0x0012
            groovy.lang.MissingMethodException r0 = new groovy.lang.MissingMethodException
            java.lang.Class r1 = r4.getClass()
            r0.<init>(r5, r1, r6)
            throw r0
        L_0x0012:
            int r1 = r6.length
            r2 = 1
            if (r1 != r2) goto L_0x004b
            r1 = r6[r0]
            boolean r1 = r1 instanceof groovy.lang.Closure
            if (r1 == 0) goto L_0x0025
            r0 = r6[r0]
            groovy.lang.Closure r0 = (groovy.lang.Closure) r0
            net.sf.json.JSON r0 = r4.createObject(r0)
        L_0x0024:
            return r0
        L_0x0025:
            r1 = r6[r0]
            boolean r1 = r1 instanceof java.util.Map
            if (r1 == 0) goto L_0x0034
            r0 = r6[r0]
            java.util.Map r0 = (java.util.Map) r0
            net.sf.json.JSON r0 = r4.createObject(r0)
            goto L_0x0024
        L_0x0034:
            r1 = r6[r0]
            boolean r1 = r1 instanceof java.util.List
            if (r1 == 0) goto L_0x0043
            r0 = r6[r0]
            java.util.List r0 = (java.util.List) r0
            net.sf.json.JSON r0 = r4.createArray(r0)
            goto L_0x0024
        L_0x0043:
            net.sf.json.JSONException r0 = new net.sf.json.JSONException
            java.lang.String r1 = "Unsupported type"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            net.sf.json.JSONArray r2 = new net.sf.json.JSONArray
            r2.<init>()
            java.util.Stack r1 = r4.stack
            r1.push(r2)
            r1 = r0
        L_0x0056:
            int r0 = r6.length
            if (r1 >= r0) goto L_0x00a0
            r0 = r6[r1]
            boolean r0 = r0 instanceof groovy.lang.Closure
            if (r0 == 0) goto L_0x006e
            r0 = r6[r1]
            groovy.lang.Closure r0 = (groovy.lang.Closure) r0
            net.sf.json.JSON r0 = r4.createObject(r0)
            r4.append(r5, r0)
        L_0x006a:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0056
        L_0x006e:
            r0 = r6[r1]
            boolean r0 = r0 instanceof java.util.Map
            if (r0 == 0) goto L_0x0080
            r0 = r6[r1]
            java.util.Map r0 = (java.util.Map) r0
            net.sf.json.JSON r0 = r4.createObject(r0)
            r4.append(r5, r0)
            goto L_0x006a
        L_0x0080:
            r0 = r6[r1]
            boolean r0 = r0 instanceof java.util.List
            if (r0 == 0) goto L_0x0092
            r0 = r6[r1]
            java.util.List r0 = (java.util.List) r0
            net.sf.json.JSON r0 = r4.createArray(r0)
            r4.append(r5, r0)
            goto L_0x006a
        L_0x0092:
            r3 = r6[r1]
            java.util.Stack r0 = r4.stack
            java.lang.Object r0 = r0.peek()
            net.sf.json.JSON r0 = (net.sf.json.JSON) r0
            r4._append(r5, r3, r0)
            goto L_0x006a
        L_0x00a0:
            java.util.Stack r0 = r4.stack
            r0.pop()
            r0 = r2
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.groovy.JsonGroovyBuilder.createObject(java.lang.String, java.lang.Object):net.sf.json.JSON");
    }
}
