package com.baidu.location.d;

import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* 'enum' access flag removed */
final class o extends b {
    o(String str, int i, String str2, String str3, String str4, int i2, int i3) {
        super(str, i, str2, str3, str4, i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public List<String> a(JSONObject jSONObject, String str, int i) {
        JSONArray jSONArray;
        Iterator keys = jSONObject.keys();
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        while (keys.hasNext()) {
            JSONArray jSONArray2 = null;
            StringBuffer stringBuffer2 = new StringBuffer();
            String str2 = (String) keys.next();
            b.b(stringBuffer, str2, str, 1);
            try {
                jSONArray = jSONObject.getJSONArray(str2);
            } catch (JSONException e) {
                jSONArray = jSONArray2;
            }
            if (jSONArray != null) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    Double d = null;
                    Double d2 = null;
                    String str3 = null;
                    String str4 = null;
                    String str5 = null;
                    Integer num = null;
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (jSONObject2.has("pid")) {
                            str3 = jSONObject2.getString("pid");
                        }
                        if (jSONObject2.has("ne")) {
                            str4 = jSONObject2.getString("ne");
                        }
                        if (jSONObject2.has("tp")) {
                            str5 = jSONObject2.getString("tp");
                        }
                        if (jSONObject2.has("rk")) {
                            num = Integer.valueOf(jSONObject2.getInt("rk"));
                        }
                        if (jSONObject2.has(Config.EVENT_HEAT_X)) {
                            d = Double.valueOf(jSONObject2.getDouble(Config.EVENT_HEAT_X));
                        }
                        if (jSONObject2.has("y")) {
                            d2 = Double.valueOf(jSONObject2.getDouble("y"));
                        }
                        if (stringBuffer2.length() > 0) {
                            stringBuffer2.append(StorageInterface.KEY_SPLITER);
                        }
                        stringBuffer2.append("(\"").append(str3).append("\",\"").append(str2).append("\",\"").append(str4).append("\",\"").append(str5).append("\",").append(d).append(StorageInterface.KEY_SPLITER).append(d2).append(StorageInterface.KEY_SPLITER).append(num).append(")");
                    } catch (JSONException e2) {
                    }
                    if (i2 % 50 == 49) {
                        arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCPOI", stringBuffer2.toString()}));
                        stringBuffer2.setLength(0);
                    }
                }
                if (stringBuffer2.length() > 0) {
                    arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCPOI", stringBuffer2.toString()}));
                }
            }
        }
        if (stringBuffer.length() > 0) {
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCUPDATE", stringBuffer}));
        }
        arrayList.add(String.format(Locale.US, "DELETE FROM RGCPOI WHERE pid NOT IN (SELECT pid FROM RGCPOI LIMIT %d);", new Object[]{Integer.valueOf(i)}));
        return arrayList;
    }
}
