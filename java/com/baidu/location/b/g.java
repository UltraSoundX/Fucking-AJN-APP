package com.baidu.location.b;

import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.g.e;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    private static Object a = new Object();
    private static g b = null;
    private Handler c = null;
    /* access modifiers changed from: private */
    public String d = null;
    private int e = 24;
    private a f = null;
    private long g = 0;

    private class a extends e {
        private boolean b;
        private int c;
        private JSONArray d;
        private JSONArray e;

        a() {
            this.b = false;
            this.c = 0;
            this.d = null;
            this.e = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.f();
            this.k.clear();
            this.k.put("qt", "cltrw");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("data", this.d);
                jSONObject.put("frt", this.c);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.k.put("cltr[0]", "" + Jni.e(jSONObject.toString()));
            this.k.put("cfg", Integer.valueOf(1));
            this.k.put(Config.LAUNCH_INFO, Jni.a(com.baidu.location.g.b.a().c()));
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        public void a(boolean z) {
            JSONObject jSONObject;
            boolean z2;
            if (z && this.j != null) {
                try {
                    jSONObject = new JSONObject(this.j);
                    z2 = true;
                } catch (Exception e2) {
                    jSONObject = null;
                    z2 = false;
                }
                if (z2 && jSONObject != null) {
                    try {
                        jSONObject.put("tt", System.currentTimeMillis());
                        jSONObject.put("data", this.e);
                        try {
                            File file = new File(g.this.d, "wcnf.dat");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                            bufferedWriter.write(com.baidu.a.a.a.a.b.a(jSONObject.toString().getBytes(), "UTF-8"));
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    } catch (Exception e4) {
                    }
                }
            }
            this.b = false;
        }

        public void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
            if (!this.b) {
                this.b = true;
                if (z) {
                    this.c = 1;
                } else {
                    this.c = 0;
                }
                this.d = jSONArray;
                this.e = jSONArray2;
                b(j.f());
            }
        }
    }

    private class b {
        public String a = null;
        public int b = 0;

        b(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public static g a() {
        g gVar;
        synchronized (a) {
            if (b == null) {
                b = new g();
            }
            gVar = b;
        }
        return gVar;
    }

    private Object a(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    private List<b> a(List<WifiConfiguration> list) {
        int i;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (WifiConfiguration wifiConfiguration : list) {
            String str = wifiConfiguration.SSID;
            try {
                i = ((Integer) a(wifiConfiguration, "numAssociation")).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
                i = 0;
            }
            if (i > 0 && str != null) {
                arrayList.add(new b(str, i));
            }
        }
        return arrayList;
    }

    private void a(boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (this.f == null) {
            this.f = new a();
        }
        this.f.a(z, jSONArray, jSONArray2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003f A[Catch:{ Exception -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057 A[Catch:{ Exception -> 0x00a8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r12 = this;
            java.lang.String r0 = r12.d
            if (r0 == 0) goto L_0x00ac
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r0 = r12.d     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r1 = "wcnf.dat"
            r2.<init>(r0, r1)     // Catch:{ Exception -> 0x00a8 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00a8 }
            r0 = 1
            r1 = 0
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x00a8 }
            if (r3 == 0) goto L_0x0157
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0034 }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ Exception -> 0x0034 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x0034 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r2 = ""
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0034 }
            r2.<init>()     // Catch:{ Exception -> 0x0034 }
        L_0x002a:
            java.lang.String r6 = r3.readLine()     // Catch:{ Exception -> 0x0034 }
            if (r6 == 0) goto L_0x00ad
            r2.append(r6)     // Catch:{ Exception -> 0x0034 }
            goto L_0x002a
        L_0x0034:
            r2 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
        L_0x0038:
            r0.printStackTrace()     // Catch:{ Exception -> 0x00a8 }
            r6 = r1
            r1 = r2
        L_0x003d:
            if (r1 != 0) goto L_0x0045
            int r0 = r12.e     // Catch:{ Exception -> 0x00a8 }
            int r0 = r0 * 4
            r12.e = r0     // Catch:{ Exception -> 0x00a8 }
        L_0x0045:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00a8 }
            long r0 = r0 - r4
            int r2 = r12.e     // Catch:{ Exception -> 0x00a8 }
            int r2 = r2 * 60
            int r2 = r2 * 60
            int r2 = r2 * 1000
            long r2 = (long) r2     // Catch:{ Exception -> 0x00a8 }
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ac
            r2 = 0
            r0 = 0
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()     // Catch:{ Exception -> 0x00a8 }
            java.util.List r1 = r1.d()     // Catch:{ Exception -> 0x00a8 }
            java.util.List r7 = r12.a(r1)     // Catch:{ Exception -> 0x00a8 }
            r3 = 0
            r8 = 0
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x0168
            if (r7 == 0) goto L_0x01f2
            int r1 = r7.size()     // Catch:{ Exception -> 0x00a8 }
            if (r1 <= 0) goto L_0x01f2
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Exception -> 0x00a8 }
            r2.<init>()     // Catch:{ Exception -> 0x00a8 }
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x00a8 }
            r1.<init>()     // Catch:{ Exception -> 0x00a8 }
            java.util.Iterator r3 = r7.iterator()     // Catch:{ Exception -> 0x00a8 }
        L_0x0082:
            boolean r0 = r3.hasNext()     // Catch:{ Exception -> 0x00a8 }
            if (r0 == 0) goto L_0x015e
            java.lang.Object r0 = r3.next()     // Catch:{ Exception -> 0x00a8 }
            com.baidu.location.b.g$b r0 = (com.baidu.location.b.g.b) r0     // Catch:{ Exception -> 0x00a8 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x00a8 }
            r4.<init>()     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "ssid"
            java.lang.String r6 = r0.a     // Catch:{ Exception -> 0x00a8 }
            r4.put(r5, r6)     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "num"
            int r0 = r0.b     // Catch:{ Exception -> 0x00a8 }
            r4.put(r5, r0)     // Catch:{ Exception -> 0x00a8 }
            r2.put(r4)     // Catch:{ Exception -> 0x00a8 }
            r1.put(r4)     // Catch:{ Exception -> 0x00a8 }
            goto L_0x0082
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00ac:
            return
        L_0x00ad:
            r3.close()     // Catch:{ Exception -> 0x0034 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0034 }
            if (r2 == 0) goto L_0x01fe
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0034 }
            byte[] r2 = r2.getBytes()     // Catch:{ Exception -> 0x0034 }
            byte[] r2 = com.baidu.a.a.a.a.b.a(r2)     // Catch:{ Exception -> 0x0034 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0034 }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Exception -> 0x0034 }
            r6.<init>(r3)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r2 = "ison"
            boolean r2 = r6.has(r2)     // Catch:{ Exception -> 0x0034 }
            if (r2 == 0) goto L_0x01fb
            java.lang.String r2 = "ison"
            int r2 = r6.getInt(r2)     // Catch:{ Exception -> 0x0034 }
            if (r2 != 0) goto L_0x01fb
            r0 = 0
            r2 = r0
        L_0x00da:
            java.lang.String r0 = "cfg"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x01df }
            if (r0 == 0) goto L_0x00f8
            java.lang.String r0 = "cfg"
            org.json.JSONObject r0 = r6.getJSONObject(r0)     // Catch:{ Exception -> 0x01df }
            java.lang.String r3 = "frq"
            boolean r3 = r0.has(r3)     // Catch:{ Exception -> 0x01df }
            if (r3 == 0) goto L_0x00f8
            java.lang.String r3 = "frq"
            int r0 = r0.getInt(r3)     // Catch:{ Exception -> 0x01df }
            r12.e = r0     // Catch:{ Exception -> 0x01df }
        L_0x00f8:
            java.lang.String r0 = "tt"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x01df }
            if (r0 == 0) goto L_0x0106
            java.lang.String r0 = "tt"
            long r4 = r6.getLong(r0)     // Catch:{ Exception -> 0x01df }
        L_0x0106:
            java.lang.String r0 = "data"
            boolean r0 = r6.has(r0)     // Catch:{ Exception -> 0x01df }
            if (r0 == 0) goto L_0x01f6
            java.lang.String r0 = "data"
            org.json.JSONArray r3 = r6.getJSONArray(r0)     // Catch:{ Exception -> 0x01df }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x01df }
            r0.<init>()     // Catch:{ Exception -> 0x01df }
            int r6 = r3.length()     // Catch:{ Exception -> 0x01e2 }
            r1 = 0
        L_0x011e:
            if (r1 >= r6) goto L_0x0151
            org.json.JSONObject r7 = r3.getJSONObject(r1)     // Catch:{ Exception -> 0x01e2 }
            java.lang.String r8 = "ssid"
            boolean r8 = r7.has(r8)     // Catch:{ Exception -> 0x01e2 }
            if (r8 == 0) goto L_0x014e
            java.lang.String r8 = "num"
            boolean r8 = r7.has(r8)     // Catch:{ Exception -> 0x01e2 }
            if (r8 == 0) goto L_0x014e
            com.baidu.location.b.g$b r8 = new com.baidu.location.b.g$b     // Catch:{ Exception -> 0x01e2 }
            java.lang.String r9 = "ssid"
            java.lang.String r9 = r7.getString(r9)     // Catch:{ Exception -> 0x01e2 }
            java.lang.String r10 = "num"
            int r10 = r7.getInt(r10)     // Catch:{ Exception -> 0x01e2 }
            r8.<init>(r9, r10)     // Catch:{ Exception -> 0x01e2 }
            java.lang.String r9 = "ssid"
            java.lang.String r7 = r7.getString(r9)     // Catch:{ Exception -> 0x01e2 }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x01e2 }
        L_0x014e:
            int r1 = r1 + 1
            goto L_0x011e
        L_0x0151:
            r1 = r2
            r2 = r4
        L_0x0153:
            r6 = r0
            r4 = r2
            goto L_0x003d
        L_0x0157:
            r2 = 0
            r6 = r1
            r4 = r2
            r1 = r0
            goto L_0x003d
        L_0x015e:
            r0 = 1
        L_0x015f:
            if (r2 == 0) goto L_0x00ac
            if (r1 == 0) goto L_0x00ac
            r12.a(r0, r2, r1)     // Catch:{ Exception -> 0x00a8 }
            goto L_0x00ac
        L_0x0168:
            if (r7 == 0) goto L_0x01f2
            int r1 = r7.size()     // Catch:{ Exception -> 0x00a8 }
            if (r1 <= 0) goto L_0x01f2
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Exception -> 0x00a8 }
            r4.<init>()     // Catch:{ Exception -> 0x00a8 }
            if (r6 == 0) goto L_0x01ee
            int r0 = r6.size()     // Catch:{ Exception -> 0x00a8 }
            if (r0 <= 0) goto L_0x01ee
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x00a8 }
        L_0x0181:
            boolean r0 = r7.hasNext()     // Catch:{ Exception -> 0x00a8 }
            if (r0 == 0) goto L_0x01ee
            java.lang.Object r0 = r7.next()     // Catch:{ Exception -> 0x00a8 }
            com.baidu.location.b.g$b r0 = (com.baidu.location.b.g.b) r0     // Catch:{ Exception -> 0x00a8 }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x00a8 }
            r1.<init>()     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "ssid"
            java.lang.String r8 = r0.a     // Catch:{ Exception -> 0x00a8 }
            r1.put(r5, r8)     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "num"
            int r8 = r0.b     // Catch:{ Exception -> 0x00a8 }
            r1.put(r5, r8)     // Catch:{ Exception -> 0x00a8 }
            r4.put(r1)     // Catch:{ Exception -> 0x00a8 }
            r5 = 0
            java.lang.String r1 = r0.a     // Catch:{ Exception -> 0x00a8 }
            boolean r1 = r6.containsKey(r1)     // Catch:{ Exception -> 0x00a8 }
            if (r1 == 0) goto L_0x01dd
            int r8 = r0.b     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r1 = r0.a     // Catch:{ Exception -> 0x00a8 }
            java.lang.Object r1 = r6.get(r1)     // Catch:{ Exception -> 0x00a8 }
            com.baidu.location.b.g$b r1 = (com.baidu.location.b.g.b) r1     // Catch:{ Exception -> 0x00a8 }
            int r1 = r1.b     // Catch:{ Exception -> 0x00a8 }
            if (r8 == r1) goto L_0x01ec
            r1 = 1
        L_0x01bb:
            if (r1 == 0) goto L_0x01ea
            if (r2 != 0) goto L_0x01e8
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x00a8 }
            r1.<init>()     // Catch:{ Exception -> 0x00a8 }
        L_0x01c4:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00a8 }
            r2.<init>()     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "ssid"
            java.lang.String r8 = r0.a     // Catch:{ Exception -> 0x00a8 }
            r2.put(r5, r8)     // Catch:{ Exception -> 0x00a8 }
            java.lang.String r5 = "num"
            int r0 = r0.b     // Catch:{ Exception -> 0x00a8 }
            r2.put(r5, r0)     // Catch:{ Exception -> 0x00a8 }
            r1.put(r2)     // Catch:{ Exception -> 0x00a8 }
            r0 = r1
        L_0x01db:
            r2 = r0
            goto L_0x0181
        L_0x01dd:
            r1 = 1
            goto L_0x01bb
        L_0x01df:
            r0 = move-exception
            goto L_0x0038
        L_0x01e2:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
            goto L_0x0038
        L_0x01e8:
            r1 = r2
            goto L_0x01c4
        L_0x01ea:
            r0 = r2
            goto L_0x01db
        L_0x01ec:
            r1 = r5
            goto L_0x01bb
        L_0x01ee:
            r0 = r3
            r1 = r4
            goto L_0x015f
        L_0x01f2:
            r1 = r0
            r0 = r3
            goto L_0x015f
        L_0x01f6:
            r0 = r1
            r1 = r2
            r2 = r4
            goto L_0x0153
        L_0x01fb:
            r2 = r0
            goto L_0x00da
        L_0x01fe:
            r2 = r4
            r11 = r0
            r0 = r1
            r1 = r11
            goto L_0x0153
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.g.d():void");
    }

    public void b() {
        if (this.c == null) {
            this.c = new h(this);
        }
        this.d = j.h();
    }

    public void c() {
        if (System.currentTimeMillis() - this.g > 3600000 && this.c != null) {
            this.c.sendEmptyMessage(1);
            this.g = System.currentTimeMillis();
        }
    }
}
