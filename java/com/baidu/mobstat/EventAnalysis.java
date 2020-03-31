package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Config.EventViewType;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventAnalysis {
    private Map<String, a> a = new HashMap();

    static class a {
        String a;
        String b;
        long c;

        private a() {
        }
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        a(context, j, str, str2, i, j2, 0, extraInfo, map, z);
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, String str3, String str4, int i2, boolean z) {
        a(context, j, str, str2, i, j2, 0, str3, str4, i2);
    }

    public void onEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        flushEvent(context, j, str, str2, i, j2, jSONArray, jSONArray2, str3, str4, str5, map, z);
    }

    public void onEventStart(Context context, String str, String str2, long j) {
        a aVar = new a();
        aVar.c = j;
        aVar.a = str;
        aVar.b = str2;
        String a2 = a(str, str2);
        if (this.a.containsKey(a2)) {
            am.c().b("[WARNING] eventId: " + str + ", with label: " + str2 + " is duplicated, older is removed");
        }
        this.a.put(a2, aVar);
    }

    /* JADX INFO: used method not loaded: com.baidu.mobstat.ak.b(java.lang.String):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x004f, code lost:
        if (r22.equals(r4.a) != false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x005b, code lost:
        if (r23.equals(r4.b) == false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005d, code lost:
        com.baidu.mobstat.am.c().b("[WARNING] eventId/label pair not match");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onEventEnd(android.content.Context r19, long r20, java.lang.String r22, java.lang.String r23, long r24, com.baidu.mobstat.ExtraInfo r26, java.util.Map<java.lang.String, java.lang.String> r27, boolean r28) {
        /*
            r18 = this;
            r0 = r18
            r1 = r22
            r2 = r23
            java.lang.String r5 = r0.a(r1, r2)
            r0 = r18
            java.util.Map<java.lang.String, com.baidu.mobstat.EventAnalysis$a> r4 = r0.a
            java.lang.Object r4 = r4.get(r5)
            com.baidu.mobstat.EventAnalysis$a r4 = (com.baidu.mobstat.EventAnalysis.a) r4
            if (r4 != 0) goto L_0x0045
            com.baidu.mobstat.am r4 = com.baidu.mobstat.am.c()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[WARNING] eventId: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r22
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = ", with label: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r23
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = " is not started or alread ended"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.b(r5)
        L_0x0044:
            return
        L_0x0045:
            if (r22 == 0) goto L_0x0051
            java.lang.String r6 = r4.a
            r0 = r22
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x005d
        L_0x0051:
            if (r23 == 0) goto L_0x0067
            java.lang.String r6 = r4.b
            r0 = r23
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x0067
        L_0x005d:
            com.baidu.mobstat.am r4 = com.baidu.mobstat.am.c()
            java.lang.String r5 = "[WARNING] eventId/label pair not match"
            r4.b(r5)
            goto L_0x0044
        L_0x0067:
            r0 = r18
            java.util.Map<java.lang.String, com.baidu.mobstat.EventAnalysis$a> r6 = r0.a
            r6.remove(r5)
            long r6 = r4.c
            long r12 = r24 - r6
            r6 = 0
            int r5 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r5 >= 0) goto L_0x0081
            com.baidu.mobstat.am r5 = com.baidu.mobstat.am.c()
            java.lang.String r6 = "[WARNING] onEventEnd must be invoked after onEventStart"
            r5.b(r6)
        L_0x0081:
            long r10 = r4.c
            r4 = r18
            r5 = r19
            r6 = r20
            r8 = r22
            r9 = r23
            r14 = r26
            r15 = r27
            r16 = r28
            r4.onEventDuration(r5, r6, r8, r9, r10, r12, r14, r15, r16)
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.EventAnalysis.onEventEnd(android.content.Context, long, java.lang.String, java.lang.String, long, com.baidu.mobstat.ExtraInfo, java.util.Map, boolean):void");
    }

    public void onEventDuration(Context context, long j, String str, String str2, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        if (j3 > 0) {
            a(context, j, str, str2, 1, j2, j3, extraInfo, map, z);
        }
    }

    private void a(Context context, long j, String str, String str2, int i, long j2, long j3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, j3, "", "", 0, 0, extraInfo, map, z));
        DataCore.instance().flush(context);
    }

    private void a(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, j3, str3, str4, i2, 1, null, null, false));
        DataCore.instance().flush(context);
    }

    public void flushEvent(Context context, long j, String str, String str2, int i, long j2, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        DataCore.instance().putEvent(context, getEvent(context, j, str, str2, i, j2, 0, "", jSONArray, jSONArray2, str3, str4, str5, EventViewType.EDIT.getValue(), 2, null, map, "", "", z));
        DataCore.instance().flush(context);
    }

    private String a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, String str4, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        return getEvent(context, j, str, str2, i, j2, j3, str3, null, null, str4, null, null, i2, i3, extraInfo, map, "", "", z);
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, JSONArray jSONArray, JSONArray jSONArray2, String str4, String str5, String str6, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, String str7, String str8, boolean z) {
        return getEvent(context, j, str, str2, i, j2, j3, str3, jSONArray, jSONArray2, str4, str5, str6, i2, i3, extraInfo, map, str7, str8, z, null, "");
    }

    public static JSONObject getEvent(Context context, long j, String str, String str2, int i, long j2, long j3, String str3, JSONArray jSONArray, JSONArray jSONArray2, String str4, String str5, String str6, int i2, int i3, ExtraInfo extraInfo, Map<String, String> map, String str7, String str8, boolean z, JSONObject jSONObject, String str9) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("ss", j);
            jSONObject2.put("i", str);
            jSONObject2.put("l", str2);
            jSONObject2.put("c", i);
            jSONObject2.put("t", j2);
            jSONObject2.put("d", j3);
            jSONObject2.put("h", str3);
            if (i3 != 3) {
                jSONObject2.put(Config.EVENT_NATIVE_VIEW_HIERARCHY, jSONArray);
                jSONObject2.put(Config.EVENT_H5_VIEW_HIERARCHY, jSONArray2);
            } else {
                jSONObject2.put(Config.EVENT_NATIVE_VIEW_HIERARCHY, str7);
                jSONObject2.put(Config.EVENT_H5_VIEW_HIERARCHY, str8);
            }
            jSONObject2.put("p", str4);
            jSONObject2.put(Config.EVENT_H5_PAGE, str5);
            jSONObject2.put(Config.EVENT_VIEW_RES_NAME, str6);
            jSONObject2.put("v", i2);
            jSONObject2.put("at", i3);
            jSONObject2.put("h5", z ? 1 : 0);
            if (!(extraInfo == null || extraInfo.dumpToJson().length() == 0)) {
                jSONObject2.put("ext", extraInfo.dumpToJson());
            }
            if (map != null) {
                JSONArray jSONArray3 = new JSONArray();
                for (Entry entry : map.entrySet()) {
                    String str10 = (String) entry.getKey();
                    String str11 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str10) && !TextUtils.isEmpty(str11) && !a(str11, 1024)) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(Config.APP_KEY, str10);
                        jSONObject3.put("v", str11);
                        jSONArray3.put(jSONObject3);
                    }
                }
                if (jSONArray3.length() != 0) {
                    jSONObject2.put(Config.EVENT_ATTR, jSONArray3);
                }
            }
            if (!(jSONObject == null || jSONObject.length() == 0)) {
                JSONArray jSONArray4 = new JSONArray();
                jSONArray4.put(jSONObject);
                jSONObject2.put(Config.EVENT_HEAT_POINT, jSONArray4);
            }
            String str12 = "sign";
            if (TextUtils.isEmpty(str9)) {
                str9 = "";
            }
            jSONObject2.put(str12, str9);
        } catch (Exception e) {
        }
        return jSONObject2;
    }

    private static boolean a(String str, int i) {
        int i2;
        if (str == null) {
            return false;
        }
        try {
            i2 = str.getBytes().length;
        } catch (Exception e) {
            i2 = 0;
        }
        if (i2 > i) {
            return true;
        }
        return false;
    }

    private static boolean b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !new JSONObject().toString().equals(str)) {
            return true;
        }
        if (TextUtils.isEmpty(str2) || new JSONArray().toString().equals(str2)) {
            return false;
        }
        return true;
    }

    public static void doEventMerge(JSONArray jSONArray, JSONObject jSONObject) {
        int i = 0;
        String str = "";
        JSONArray jSONArray2 = null;
        JSONArray jSONArray3 = null;
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        String str6 = "";
        EventViewType.EDIT.getValue();
        String str7 = "";
        String str8 = "";
        String str9 = "";
        try {
            long optLong = jSONObject.optLong("ss");
            String string = jSONObject.getString("i");
            String string2 = jSONObject.getString("l");
            long j = jSONObject.getLong("t") / 3600000;
            String optString = jSONObject.optString("s");
            int optInt = jSONObject.optInt("at");
            String optString2 = jSONObject.optString("h");
            if (optInt != 3) {
                jSONArray2 = jSONObject.optJSONArray(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                jSONArray3 = jSONObject.optJSONArray(Config.EVENT_H5_VIEW_HIERARCHY);
            } else {
                str2 = jSONObject.optString(Config.EVENT_NATIVE_VIEW_HIERARCHY);
                str3 = jSONObject.optString(Config.EVENT_H5_VIEW_HIERARCHY);
            }
            String optString3 = jSONObject.optString("p");
            String optString4 = jSONObject.optString(Config.EVENT_H5_PAGE);
            String optString5 = jSONObject.optString(Config.EVENT_VIEW_RES_NAME);
            int optInt2 = jSONObject.optInt("v");
            String optString6 = jSONObject.optString("ext");
            String optString7 = jSONObject.optString(Config.EVENT_ATTR);
            int optInt3 = jSONObject.optInt("h5");
            String optString8 = jSONObject.optString("sign");
            try {
                i = jSONObject.getInt("d");
            } catch (JSONException e) {
            }
            if (i != 0 || b(optString6, optString7)) {
                int length = jSONArray.length();
                try {
                    jSONObject.put("s", "0");
                    jSONArray.put(length, jSONObject);
                } catch (JSONException e2) {
                }
            } else {
                a(jSONArray, jSONObject, optLong, string, string2, optString, j, optString2, jSONArray2, jSONArray3, optString3, optString4, optString5, optInt2, optInt, str2, str3, optInt3, optString8);
            }
        } catch (JSONException e3) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r33.equals("") != false) goto L_0x0010;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(org.json.JSONArray r27, org.json.JSONObject r28, long r29, java.lang.String r31, java.lang.String r32, java.lang.String r33, long r34, java.lang.String r36, org.json.JSONArray r37, org.json.JSONArray r38, java.lang.String r39, java.lang.String r40, java.lang.String r41, int r42, int r43, java.lang.String r44, java.lang.String r45, int r46, java.lang.String r47) {
        /*
            int r9 = r27.length()
            if (r33 == 0) goto L_0x0010
            java.lang.String r2 = ""
            r0 = r33
            boolean r2 = r0.equals(r2)     // Catch:{ JSONException -> 0x01b5 }
            if (r2 == 0) goto L_0x0019
        L_0x0010:
            java.lang.String r2 = "s"
            java.lang.String r3 = "0|"
            r0 = r28
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x01b5 }
        L_0x0019:
            r3 = 0
            r2 = r9
        L_0x001b:
            if (r3 >= r9) goto L_0x01ba
            r0 = r27
            org.json.JSONObject r10 = r0.getJSONObject(r3)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "ss"
            long r12 = r10.optLong(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "i"
            java.lang.String r11 = r10.getString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "l"
            java.lang.String r14 = r10.getString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "t"
            long r4 = r10.getLong(r4)     // Catch:{ JSONException -> 0x01ae }
            r6 = 3600000(0x36ee80, double:1.7786363E-317)
            long r16 = r4 / r6
            r4 = 0
            java.lang.String r5 = "d"
            int r4 = r10.getInt(r5)     // Catch:{ JSONException -> 0x00b0 }
            r8 = r4
        L_0x0048:
            java.lang.String r4 = "h"
            java.lang.String r15 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "p"
            java.lang.String r18 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "p2"
            java.lang.String r19 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "rn"
            java.lang.String r20 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "v"
            int r21 = r10.optInt(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "at"
            int r22 = r10.optInt(r4)     // Catch:{ JSONException -> 0x01ae }
            r7 = 0
            r6 = 0
            java.lang.String r5 = ""
            java.lang.String r4 = ""
            r23 = 3
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x00b3
            java.lang.String r6 = "h2"
            org.json.JSONArray r7 = r10.optJSONArray(r6)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r6 = "h3"
            org.json.JSONArray r6 = r10.optJSONArray(r6)     // Catch:{ JSONException -> 0x01ae }
        L_0x0086:
            java.lang.String r23 = "ext"
            r0 = r23
            java.lang.String r23 = r10.optString(r0)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r24 = "attribute"
            r0 = r24
            java.lang.String r24 = r10.optString(r0)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r25 = "h5"
            r0 = r25
            int r25 = r10.optInt(r0)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r26 = "sign"
            r0 = r26
            java.lang.String r26 = r10.optString(r0)     // Catch:{ JSONException -> 0x01ae }
            int r16 = (r16 > r34 ? 1 : (r16 == r34 ? 0 : -1))
            if (r16 != 0) goto L_0x00ac
            if (r8 == 0) goto L_0x00c0
        L_0x00ac:
            int r3 = r3 + 1
            goto L_0x001b
        L_0x00b0:
            r5 = move-exception
            r8 = r4
            goto L_0x0048
        L_0x00b3:
            java.lang.String r4 = "h2"
            java.lang.String r5 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "h3"
            java.lang.String r4 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            goto L_0x0086
        L_0x00c0:
            boolean r8 = b(r23, r24)     // Catch:{ JSONException -> 0x01ae }
            if (r8 != 0) goto L_0x00ac
            int r8 = (r12 > r29 ? 1 : (r12 == r29 ? 0 : -1))
            if (r8 != 0) goto L_0x00ac
            r0 = r31
            boolean r8 = r11.equals(r0)     // Catch:{ JSONException -> 0x01ae }
            if (r8 == 0) goto L_0x00ac
            r0 = r32
            boolean r8 = r14.equals(r0)     // Catch:{ JSONException -> 0x01ae }
            if (r8 == 0) goto L_0x00ac
            r0 = r36
            boolean r8 = r15.equals(r0)     // Catch:{ JSONException -> 0x01ae }
            if (r8 == 0) goto L_0x00ac
            r0 = r18
            r1 = r39
            boolean r8 = r0.equals(r1)     // Catch:{ JSONException -> 0x01ae }
            if (r8 == 0) goto L_0x00ac
            r0 = r19
            r1 = r40
            boolean r8 = r0.equals(r1)     // Catch:{ JSONException -> 0x01ae }
            if (r8 == 0) goto L_0x00ac
            r0 = r37
            boolean r7 = a(r7, r0)     // Catch:{ JSONException -> 0x01ae }
            if (r7 == 0) goto L_0x00ac
            r0 = r38
            boolean r6 = a(r6, r0)     // Catch:{ JSONException -> 0x01ae }
            if (r6 == 0) goto L_0x00ac
            r0 = r20
            r1 = r41
            boolean r6 = r0.equals(r1)     // Catch:{ JSONException -> 0x01ae }
            if (r6 == 0) goto L_0x00ac
            r0 = r21
            r1 = r42
            if (r0 != r1) goto L_0x00ac
            r0 = r22
            r1 = r43
            if (r0 != r1) goto L_0x00ac
            r0 = r44
            boolean r5 = r5.equals(r0)     // Catch:{ JSONException -> 0x01ae }
            if (r5 == 0) goto L_0x00ac
            r0 = r45
            boolean r4 = r4.equals(r0)     // Catch:{ JSONException -> 0x01ae }
            if (r4 == 0) goto L_0x00ac
            r0 = r25
            r1 = r46
            if (r0 != r1) goto L_0x00ac
            r0 = r26
            r1 = r47
            boolean r4 = r0.equals(r1)     // Catch:{ JSONException -> 0x01ae }
            if (r4 == 0) goto L_0x00ac
            java.lang.String r4 = "c"
            r0 = r28
            int r4 = r0.getInt(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r5 = "c"
            int r5 = r10.getInt(r5)     // Catch:{ JSONException -> 0x01ae }
            int r7 = r4 + r5
            java.lang.String r4 = "s"
            java.lang.String r4 = r10.optString(r4)     // Catch:{ JSONException -> 0x01ae }
            if (r4 == 0) goto L_0x015c
            java.lang.String r5 = ""
            boolean r5 = r4.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x01ae }
            if (r5 == 0) goto L_0x01b8
        L_0x015c:
            java.lang.String r4 = "0|"
            r6 = r4
        L_0x015f:
            java.lang.String r4 = "t"
            r0 = r28
            long r4 = r0.getLong(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r8 = "t"
            long r12 = r10.getLong(r8)     // Catch:{ JSONException -> 0x01ae }
            long r4 = r4 - r12
            r12 = 0
            int r8 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x0176
            r4 = 0
        L_0x0176:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ae }
            r8.<init>()     // Catch:{ JSONException -> 0x01ae }
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ JSONException -> 0x01ae }
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r5 = "|"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r2 = r4.toString()     // Catch:{ JSONException -> 0x01ae }
            java.lang.String r4 = "c"
            r10.remove(r4)     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r4 = "c"
            r10.put(r4, r7)     // Catch:{ JSONException -> 0x01b1 }
            java.lang.String r4 = "s"
            r10.put(r4, r2)     // Catch:{ JSONException -> 0x01b1 }
            r0 = r28
            a(r10, r0)     // Catch:{ JSONException -> 0x01b1 }
        L_0x01a1:
            if (r3 >= r9) goto L_0x01a4
        L_0x01a3:
            return
        L_0x01a4:
            r0 = r27
            r1 = r28
            r0.put(r9, r1)     // Catch:{ JSONException -> 0x01ac }
            goto L_0x01a3
        L_0x01ac:
            r2 = move-exception
            goto L_0x01a3
        L_0x01ae:
            r4 = move-exception
            goto L_0x00ac
        L_0x01b1:
            r2 = move-exception
            r2 = r3
            goto L_0x00ac
        L_0x01b5:
            r2 = move-exception
            goto L_0x0019
        L_0x01b8:
            r6 = r4
            goto L_0x015f
        L_0x01ba:
            r3 = r2
            goto L_0x01a1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.EventAnalysis.a(org.json.JSONArray, org.json.JSONObject, long, java.lang.String, java.lang.String, java.lang.String, long, java.lang.String, org.json.JSONArray, org.json.JSONArray, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String, int, java.lang.String):void");
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject2 != null) {
            JSONArray jSONArray = new JSONArray();
            JSONArray optJSONArray = jSONObject.optJSONArray(Config.EVENT_HEAT_POINT);
            if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    try {
                        jSONArray.put(optJSONArray.getJSONObject(i));
                    } catch (Exception e) {
                    }
                }
            }
            if (jSONArray.length() < 10) {
                JSONArray optJSONArray2 = jSONObject2.optJSONArray(Config.EVENT_HEAT_POINT);
                if (!(optJSONArray2 == null || optJSONArray2.length() == 0)) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        try {
                            jSONArray.put(optJSONArray2.getJSONObject(i2));
                        } catch (Exception e2) {
                        }
                    }
                }
            }
            if (jSONArray != null && jSONArray.length() != 0) {
                try {
                    jSONObject.put(Config.EVENT_HEAT_POINT, jSONArray);
                } catch (Exception e3) {
                }
            }
        }
    }

    private static boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if (jSONArray == null || jSONArray2 == null || !jSONArray.toString().equals(jSONArray2.toString())) {
            return false;
        }
        return true;
    }
}
