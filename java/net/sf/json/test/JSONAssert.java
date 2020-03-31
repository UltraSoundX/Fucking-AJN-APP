package net.sf.json.test;

import java.util.Iterator;
import junit.framework.Assert;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.util.JSONUtils;

public class JSONAssert extends Assert {
    public static void assertEquals(JSON json, JSON json2) {
        assertEquals((String) null, json, json2);
    }

    public static void assertEquals(JSONArray jSONArray, JSONArray jSONArray2) {
        assertEquals((String) null, jSONArray, jSONArray2);
    }

    public static void assertEquals(JSONArray jSONArray, String str) {
        assertEquals((String) null, jSONArray, str);
    }

    public static void assertEquals(JSONFunction jSONFunction, String str) {
        assertEquals((String) null, jSONFunction, str);
    }

    public static void assertEquals(JSONNull jSONNull, String str) {
        assertEquals((String) null, jSONNull, str);
    }

    public static void assertEquals(JSONObject jSONObject, JSONObject jSONObject2) {
        assertEquals((String) null, jSONObject, jSONObject2);
    }

    public static void assertEquals(JSONObject jSONObject, String str) {
        assertEquals((String) null, jSONObject, str);
    }

    public static void assertEquals(String str, JSON json, JSON json2) {
        String stringBuffer;
        if (str == null) {
            stringBuffer = "";
        } else {
            stringBuffer = new StringBuffer().append(str).append(": ").toString();
        }
        if (json == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected was null").toString());
        }
        if (json2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual was null").toString());
        }
        if (json != json2 && !json.equals(json2)) {
            if (json instanceof JSONArray) {
                if (json2 instanceof JSONArray) {
                    assertEquals(stringBuffer, (JSONArray) json, (JSONArray) json2);
                } else {
                    Assert.fail(new StringBuffer().append(stringBuffer).append("actual is not a JSONArray").toString());
                }
            } else if (json instanceof JSONObject) {
                if (json2 instanceof JSONObject) {
                    assertEquals(stringBuffer, (JSONObject) json, (JSONObject) json2);
                } else {
                    Assert.fail(new StringBuffer().append(stringBuffer).append("actual is not a JSONObject").toString());
                }
            } else if ((json instanceof JSONNull) && !(json2 instanceof JSONNull)) {
                Assert.fail(new StringBuffer().append(stringBuffer).append("actual is not a JSONNull").toString());
            }
        }
    }

    public static void assertEquals(String str, JSONArray jSONArray) {
        assertEquals((String) null, str, jSONArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x023b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void assertEquals(java.lang.String r8, net.sf.json.JSONArray r9, net.sf.json.JSONArray r10) {
        /*
            if (r8 != 0) goto L_0x003e
            java.lang.String r0 = ""
            r3 = r0
        L_0x0005:
            if (r9 != 0) goto L_0x001d
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.StringBuffer r0 = r0.append(r3)
            java.lang.String r1 = "expected array was null"
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            junit.framework.Assert.fail(r0)
        L_0x001d:
            if (r10 != 0) goto L_0x0035
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.StringBuffer r0 = r0.append(r3)
            java.lang.String r1 = "actual array was null"
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            junit.framework.Assert.fail(r0)
        L_0x0035:
            if (r9 == r10) goto L_0x003d
            boolean r0 = r9.equals(r10)
            if (r0 == 0) goto L_0x0053
        L_0x003d:
            return
        L_0x003e:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.StringBuffer r0 = r0.append(r8)
            java.lang.String r1 = ": "
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3 = r0
            goto L_0x0005
        L_0x0053:
            int r0 = r10.size()
            int r1 = r9.size()
            if (r0 == r1) goto L_0x0089
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.StringBuffer r0 = r0.append(r3)
            java.lang.String r1 = "arrays sizes differed, expected.length()="
            java.lang.StringBuffer r0 = r0.append(r1)
            int r1 = r9.size()
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r1 = " actual.length()="
            java.lang.StringBuffer r0 = r0.append(r1)
            int r1 = r10.size()
            java.lang.StringBuffer r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            junit.framework.Assert.fail(r0)
        L_0x0089:
            int r4 = r9.size()
            r0 = 0
            r2 = r0
        L_0x008f:
            if (r2 >= r4) goto L_0x003d
            java.lang.Object r0 = r9.get(r2)
            java.lang.Object r1 = r10.get(r2)
            net.sf.json.JSONNull r5 = net.sf.json.JSONNull.getInstance()
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x00fe
            net.sf.json.JSONNull r5 = net.sf.json.JSONNull.getInstance()
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x00b1
        L_0x00ad:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x008f
        L_0x00b1:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            junit.framework.Assert.fail(r5)
        L_0x00d1:
            boolean r5 = r0 instanceof net.sf.json.JSONArray
            if (r5 == 0) goto L_0x0129
            boolean r5 = r1 instanceof net.sf.json.JSONArray
            if (r5 == 0) goto L_0x0129
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            net.sf.json.JSONArray r1 = (net.sf.json.JSONArray) r1
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element "
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = ";"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x00fe:
            net.sf.json.JSONNull r5 = net.sf.json.JSONNull.getInstance()
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x00d1
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            junit.framework.Assert.fail(r5)
            goto L_0x00d1
        L_0x0129:
            boolean r5 = r0 instanceof java.lang.String
            if (r5 == 0) goto L_0x0157
            boolean r5 = r1 instanceof net.sf.json.JSONFunction
            if (r5 == 0) goto L_0x0157
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = (java.lang.String) r0
            net.sf.json.JSONFunction r1 = (net.sf.json.JSONFunction) r1
            assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x0157:
            boolean r5 = r0 instanceof net.sf.json.JSONFunction
            if (r5 == 0) goto L_0x0185
            boolean r5 = r1 instanceof java.lang.String
            if (r5 == 0) goto L_0x0185
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.json.JSONFunction r0 = (net.sf.json.JSONFunction) r0
            java.lang.String r1 = (java.lang.String) r1
            assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x0185:
            boolean r5 = r0 instanceof net.sf.json.JSONObject
            if (r5 == 0) goto L_0x01b3
            boolean r5 = r1 instanceof net.sf.json.JSONObject
            if (r5 == 0) goto L_0x01b3
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.json.JSONObject r0 = (net.sf.json.JSONObject) r0
            net.sf.json.JSONObject r1 = (net.sf.json.JSONObject) r1
            assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x01b3:
            boolean r5 = r0 instanceof net.sf.json.JSONArray
            if (r5 == 0) goto L_0x01e1
            boolean r5 = r1 instanceof net.sf.json.JSONArray
            if (r5 == 0) goto L_0x01e1
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.json.JSONArray r0 = (net.sf.json.JSONArray) r0
            net.sf.json.JSONArray r1 = (net.sf.json.JSONArray) r1
            assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x01e1:
            boolean r5 = r0 instanceof net.sf.json.JSONFunction
            if (r5 == 0) goto L_0x020f
            boolean r5 = r1 instanceof net.sf.json.JSONFunction
            if (r5 == 0) goto L_0x020f
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.json.JSONFunction r0 = (net.sf.json.JSONFunction) r0
            net.sf.json.JSONFunction r1 = (net.sf.json.JSONFunction) r1
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x020f:
            boolean r5 = r0 instanceof java.lang.String
            if (r5 == 0) goto L_0x023b
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = java.lang.String.valueOf(r1)
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x023b:
            boolean r5 = r1 instanceof java.lang.String
            if (r5 == 0) goto L_0x0267
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = (java.lang.String) r1
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x0267:
            net.sf.ezmorph.MorpherRegistry r5 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Class r6 = r0.getClass()
            net.sf.ezmorph.Morpher r5 = r5.getMorpherFor(r6)
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Class r7 = r1.getClass()
            net.sf.ezmorph.Morpher r6 = r6.getMorpherFor(r7)
            if (r5 == 0) goto L_0x02b5
            net.sf.ezmorph.object.IdentityObjectMorpher r7 = net.sf.ezmorph.object.IdentityObjectMorpher.getInstance()
            if (r5 == r7) goto L_0x02b5
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Class r7 = r0.getClass()
            java.lang.Object r1 = r6.morph(r7, r1)
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x02b5:
            if (r6 == 0) goto L_0x02eb
            net.sf.ezmorph.object.IdentityObjectMorpher r5 = net.sf.ezmorph.object.IdentityObjectMorpher.getInstance()
            if (r6 == r5) goto L_0x02eb
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            net.sf.ezmorph.MorpherRegistry r6 = net.sf.json.util.JSONUtils.getMorpherRegistry()
            java.lang.Class r7 = r0.getClass()
            java.lang.Object r0 = r6.morph(r7, r0)
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        L_0x02eb:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.lang.StringBuffer r5 = r5.append(r3)
            java.lang.String r6 = "arrays first differed at element ["
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.StringBuffer r5 = r5.append(r2)
            java.lang.String r6 = "];"
            java.lang.StringBuffer r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            junit.framework.Assert.assertEquals(r5, r0, r1)
            goto L_0x00ad
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.json.test.JSONAssert.assertEquals(java.lang.String, net.sf.json.JSONArray, net.sf.json.JSONArray):void");
    }

    public static void assertEquals(String str, JSONArray jSONArray, String str2) {
        try {
            assertEquals(str, jSONArray, JSONArray.fromObject(str2));
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(str == null ? "" : new StringBuffer().append(str).append(": ").toString()).append("actual is not a JSONArray").toString());
        }
    }

    public static void assertEquals(String str, JSONFunction jSONFunction) {
        assertEquals((String) null, str, jSONFunction);
    }

    public static void assertEquals(String str, JSONFunction jSONFunction, String str2) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (jSONFunction == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected function was null").toString());
        }
        if (str2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual string was null").toString());
        }
        try {
            Assert.assertEquals(stringBuffer, jSONFunction, JSONFunction.parse(str2));
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(stringBuffer).append(JSONUtils.SINGLE_QUOTE).append(str2).append("' is not a function").toString());
        }
    }

    public static void assertEquals(String str, JSONNull jSONNull) {
        assertEquals((String) null, str, jSONNull);
    }

    public static void assertEquals(String str, JSONNull jSONNull, String str2) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (str2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual string was null").toString());
        } else if (jSONNull == null) {
            Assert.assertEquals(stringBuffer, "null", str2);
        } else {
            Assert.assertEquals(stringBuffer, jSONNull.toString(), str2);
        }
    }

    public static void assertEquals(String str, JSONObject jSONObject) {
        assertEquals((String) null, str, jSONObject);
    }

    public static void assertEquals(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        String stringBuffer;
        if (str == null) {
            stringBuffer = "";
        } else {
            stringBuffer = new StringBuffer().append(str).append(": ").toString();
        }
        if (jSONObject == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected object was null").toString());
        }
        if (jSONObject2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual object was null").toString());
        }
        if (jSONObject != jSONObject2) {
            if (jSONObject.isNullObject()) {
                if (!jSONObject2.isNullObject()) {
                    Assert.fail(new StringBuffer().append(stringBuffer).append("actual is not a null JSONObject").toString());
                } else {
                    return;
                }
            } else if (jSONObject2.isNullObject()) {
                Assert.fail(new StringBuffer().append(stringBuffer).append("actual is a null JSONObject").toString());
            }
            Assert.assertEquals(new StringBuffer().append(stringBuffer).append("names sizes differed, expected.names().length()=").append(jSONObject.names().size()).append(" actual.names().length()=").append(jSONObject2.names().size()).toString(), jSONObject.names().size(), jSONObject2.names().size());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object opt = jSONObject.opt(str2);
                Object opt2 = jSONObject2.opt(str2);
                if (JSONNull.getInstance().equals(opt)) {
                    if (!JSONNull.getInstance().equals(opt2)) {
                        Assert.fail(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString());
                    }
                } else if (JSONNull.getInstance().equals(opt2)) {
                    Assert.fail(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString());
                }
                if ((opt instanceof String) && (opt2 instanceof JSONFunction)) {
                    assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (String) opt, (JSONFunction) opt2);
                } else if ((opt instanceof JSONFunction) && (opt2 instanceof String)) {
                    assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (JSONFunction) opt, (String) opt2);
                } else if ((opt instanceof JSONObject) && (opt2 instanceof JSONObject)) {
                    assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (JSONObject) opt, (JSONObject) opt2);
                } else if ((opt instanceof JSONArray) && (opt2 instanceof JSONArray)) {
                    assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (JSONArray) opt, (JSONArray) opt2);
                } else if ((opt instanceof JSONFunction) && (opt2 instanceof JSONFunction)) {
                    Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (JSONFunction) opt, (JSONFunction) opt2);
                } else if (opt instanceof String) {
                    Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), (String) opt, String.valueOf(opt2));
                } else if (opt2 instanceof String) {
                    Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), String.valueOf(opt), (String) opt2);
                } else {
                    IdentityObjectMorpher morpherFor = JSONUtils.getMorpherRegistry().getMorpherFor(opt.getClass());
                    IdentityObjectMorpher morpherFor2 = JSONUtils.getMorpherRegistry().getMorpherFor(opt2.getClass());
                    if (morpherFor != null && morpherFor != IdentityObjectMorpher.getInstance()) {
                        Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), opt, JSONUtils.getMorpherRegistry().morph(opt.getClass(), opt2));
                    } else if (morpherFor2 == null || morpherFor2 == IdentityObjectMorpher.getInstance()) {
                        Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), opt, opt2);
                    } else {
                        Assert.assertEquals(new StringBuffer().append(stringBuffer).append("objects differed at key [").append(str2).append("];").toString(), JSONUtils.getMorpherRegistry().morph(opt.getClass(), opt), opt2);
                    }
                }
            }
        }
    }

    public static void assertEquals(String str, JSONObject jSONObject, String str2) {
        try {
            assertEquals(str, jSONObject, JSONObject.fromObject(str2));
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(str == null ? "" : new StringBuffer().append(str).append(": ").toString()).append("actual is not a JSONObject").toString());
        }
    }

    public static void assertEquals(String str, String str2, JSONArray jSONArray) {
        try {
            assertEquals(str, JSONArray.fromObject(str2), jSONArray);
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(str == null ? "" : new StringBuffer().append(str).append(": ").toString()).append("expected is not a JSONArray").toString());
        }
    }

    public static void assertEquals(String str, String str2, JSONFunction jSONFunction) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (str2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected string was null").toString());
        }
        if (jSONFunction == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual function was null").toString());
        }
        try {
            Assert.assertEquals(stringBuffer, JSONFunction.parse(str2), jSONFunction);
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(stringBuffer).append(JSONUtils.SINGLE_QUOTE).append(str2).append("' is not a function").toString());
        }
    }

    public static void assertEquals(String str, String str2, JSONNull jSONNull) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (str2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected was null").toString());
        } else if (jSONNull == null) {
            Assert.assertEquals(stringBuffer, str2, "null");
        } else {
            Assert.assertEquals(stringBuffer, str2, jSONNull.toString());
        }
    }

    public static void assertEquals(String str, String str2, JSONObject jSONObject) {
        try {
            assertEquals(str, JSONObject.fromObject(str2), jSONObject);
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(str == null ? "" : new StringBuffer().append(str).append(": ").toString()).append("expected is not a JSONObject").toString());
        }
    }

    public static void assertJsonEquals(String str, String str2) {
        assertJsonEquals(null, str, str2);
    }

    public static void assertJsonEquals(String str, String str2, String str3) {
        String stringBuffer;
        JSON json;
        JSON json2 = null;
        if (str == null) {
            stringBuffer = "";
        } else {
            stringBuffer = new StringBuffer().append(str).append(": ").toString();
        }
        if (str2 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected was null").toString());
        }
        if (str3 == null) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual was null").toString());
        }
        try {
            json = JSONSerializer.toJSON(str2);
        } catch (JSONException e) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("expected is not a valid JSON string").toString());
            json = json2;
        }
        try {
            json2 = JSONSerializer.toJSON(str3);
        } catch (JSONException e2) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("actual is not a valid JSON string").toString());
        }
        assertEquals(stringBuffer, json, json2);
    }

    public static void assertNotNull(JSON json) {
        assertNotNull(null, json);
    }

    public static void assertNotNull(String str, JSON json) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (json instanceof JSONObject) {
            Assert.assertFalse(new StringBuffer().append(stringBuffer).append("Object is null").toString(), ((JSONObject) json).isNullObject());
        } else if (JSONNull.getInstance().equals(json)) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("Object is null").toString());
        }
    }

    public static void assertNull(JSON json) {
        assertNull(null, json);
    }

    public static void assertNull(String str, JSON json) {
        String stringBuffer = str == null ? "" : new StringBuffer().append(str).append(": ").toString();
        if (json instanceof JSONObject) {
            Assert.assertTrue(new StringBuffer().append(stringBuffer).append("Object is not null").toString(), ((JSONObject) json).isNullObject());
        } else if (!JSONNull.getInstance().equals(json)) {
            Assert.fail(new StringBuffer().append(stringBuffer).append("Object is not null").toString());
        }
    }
}
