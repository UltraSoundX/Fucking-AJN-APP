package com.baidu.location.d;

import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* 'enum' access flag removed */
final class l extends b {
    l(String str, int i, String str2, String str3, String str4, int i2, int i3) {
        super(str, i, str2, str3, str4, i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public List<String> a(JSONObject jSONObject, String str, int i) {
        int i2 = 0;
        Iterator keys = jSONObject.keys();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        while (true) {
            int i3 = i2;
            if (!keys.hasNext()) {
                break;
            }
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = (String) keys.next();
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str8);
                if (jSONObject2.has("cy")) {
                    str2 = jSONObject2.getString("cy");
                }
                if (jSONObject2.has("cyc")) {
                    str3 = jSONObject2.getString("cyc");
                }
                if (jSONObject2.has("prov")) {
                    str4 = jSONObject2.getString("prov");
                }
                if (jSONObject2.has("ctc")) {
                    str5 = jSONObject2.getString("ctc");
                }
                if (jSONObject2.has(Config.EXCEPTION_CRASH_TYPE)) {
                    str6 = jSONObject2.getString(Config.EXCEPTION_CRASH_TYPE);
                }
                if (jSONObject2.has("dist")) {
                    str7 = jSONObject2.getString("dist");
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(StorageInterface.KEY_SPLITER);
                }
                stringBuffer.append("(\"").append(str8).append("\",\"").append(str2).append("\",\"").append(str3).append("\",\"").append(str4).append("\",\"").append(str6).append("\",\"").append(str5).append("\",\"").append(str7).append("\",").append(System.currentTimeMillis() / 1000).append(",\"\")");
                b.b(stringBuffer2, str8, str, 0);
            } catch (JSONException e) {
            }
            if (i3 % 50 == 49 && stringBuffer.length() > 0) {
                arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCAREA", stringBuffer}));
                arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCUPDATE", stringBuffer2}));
                stringBuffer.setLength(0);
            }
            i2 = i3 + 1;
        }
        if (stringBuffer.length() > 0) {
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCAREA", stringBuffer}));
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", new Object[]{"RGCUPDATE", stringBuffer2}));
            stringBuffer.setLength(0);
        }
        arrayList.add(String.format(Locale.US, "DELETE FROM RGCAREA WHERE gridkey NOT IN (SELECT gridkey FROM RGCAREA LIMIT %d);", new Object[]{Integer.valueOf(i)}));
        return arrayList;
    }
}
