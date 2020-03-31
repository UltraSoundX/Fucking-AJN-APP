package com.baidu.location.indoor.mapversion.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.location.Jni;
import com.baidu.location.indoor.mapversion.IndoorJni;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

public class a {
    /* access modifiers changed from: private */
    public c a;
    /* access modifiers changed from: private */
    public d b;
    /* access modifiers changed from: private */
    public ExecutorService c;
    /* access modifiers changed from: private */
    public File d;
    /* access modifiers changed from: private */
    public boolean e;

    /* renamed from: com.baidu.location.indoor.mapversion.a.a$a reason: collision with other inner class name */
    private class C0033a implements Runnable {
        public d a;
        public String b;

        private C0033a() {
            this.b = null;
        }

        /* synthetic */ C0033a(a aVar, b bVar) {
            this();
        }

        public void run() {
            if (this.b == null || this.a == null) {
                a.this.e = false;
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", System.currentTimeMillis());
                jSONObject.put("manufacturer", Build.MANUFACTURER);
                jSONObject.put("product", Build.PRODUCT);
                jSONObject.put("brand", Build.BRAND);
                jSONObject.put("model", Build.MODEL);
                jSONObject.put("gravity", String.format(Locale.CHINESE, "%.5f,%.5f,%.5f", new Object[]{Float.valueOf(this.a.e[0]), Float.valueOf(this.a.e[1]), Float.valueOf(this.a.e[2])}));
                jSONObject.put("fov", this.a.d);
                jSONObject.put("bid", this.a.g);
                String str = "";
                if (com.baidu.location.g.b.a().b != null) {
                    str = com.baidu.location.g.b.a().b;
                }
                jSONObject.put("cu", str);
                IndoorJni.a(this.a.c, a.this.d);
                File file = new File(a.this.d, "compress.jpg");
                String a2 = a.this.c(file.getAbsolutePath());
                if (a2 != null) {
                    jSONObject.put("image", a2);
                    file.delete();
                    a.this.b(this.a.c);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b).openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.getOutputStream().write(jSONObject.toString().getBytes());
                    httpURLConnection.disconnect();
                    a.this.e = false;
                }
            } catch (Exception e) {
            } catch (Error e2) {
            } finally {
                a.this.e = false;
            }
        }
    }

    private class b extends com.baidu.location.g.e {
        public d a;
        private boolean c;

        b() {
            this.c = false;
            this.c = false;
        }

        private void b() {
            if (a.this.a != null) {
                e eVar = new e();
                if (a.this.b != null) {
                    eVar.i = a.this.b.b();
                }
                eVar.a = "param not enough";
                a.this.a.a(false, eVar);
            }
        }

        private String c() {
            float[] d = this.a.e;
            if (d == null || this.a.c == null) {
                return null;
            }
            this.a.b = BitmapFactory.decodeFile(this.a.c);
            if (this.a.b == null) {
                return null;
            }
            String a2 = IndoorJni.a(a.this.d, this.a.b, this.a.d, d);
            if (a2 == null) {
                return null;
            }
            try {
                if (!this.a.b.isRecycled()) {
                    this.a.b.recycle();
                }
            } catch (Error e) {
                e.printStackTrace();
            }
            String a3 = a.this.c(a2);
            if (a3 == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(com.baidu.location.g.b.a().a(true));
            sb.append("&bid=").append(this.a.g);
            sb.append("&data_type=vps");
            sb.append("&coor=gcj02");
            if (this.a.f != null && !this.a.f.equalsIgnoreCase("")) {
                sb.append("&code=").append(this.a.f);
            }
            sb.append("&img=").append(a3);
            return sb.toString();
        }

        public void a() {
            String c2 = c();
            if (c2 == null) {
                b();
                this.c = true;
                return;
            }
            String f = Jni.f(c2);
            this.k = new HashMap();
            this.k.put("vps", f + "|tp=4");
        }

        public void a(boolean z) {
            e eVar;
            if (!this.c) {
                if (!z || this.j == null) {
                    eVar = null;
                } else {
                    eVar = new e();
                    if (a.this.b != null) {
                        eVar.i = a.this.b.b();
                    }
                    try {
                        z = eVar.a(new JSONObject(this.j));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (a.this.a != null) {
                    a.this.a.a(z, eVar);
                }
                if (eVar == null) {
                    return;
                }
                if (eVar.j <= 0 || a.this.e || z) {
                    a.this.b(this.a.c);
                } else if (!TextUtils.isEmpty(eVar.k)) {
                    C0033a aVar = new C0033a(a.this, null);
                    aVar.b = eVar.k;
                    aVar.a = this.a;
                    a.this.e = true;
                    a.this.c.execute(aVar);
                }
            }
        }
    }

    public interface c {
        void a(boolean z, e eVar);
    }

    public static class d {
        /* access modifiers changed from: private */
        public boolean a = false;
        /* access modifiers changed from: private */
        public Bitmap b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public double d;
        /* access modifiers changed from: private */
        public float[] e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public String g;

        public d a(double d2) {
            this.d = d2;
            return this;
        }

        public d a(String str) {
            this.c = str;
            return this;
        }

        public d a(boolean z) {
            this.a = z;
            return this;
        }

        public d a(float[] fArr) {
            this.e = (float[]) fArr.clone();
            return this;
        }

        public d b(String str) {
            this.g = str;
            return this;
        }

        public d c(String str) {
            this.f = str;
            return this;
        }
    }

    public static class e {
        public String a;
        public String b;
        public String c;
        public String d;
        public double e;
        public double f;
        public double g;
        public String h;
        public String i;
        public int j = 0;
        public String k;

        public boolean a(JSONObject jSONObject) {
            this.b = jSONObject.optString("bldg");
            this.c = jSONObject.optString("floor");
            this.d = jSONObject.optString("bldgid");
            String optString = jSONObject.optString("indoor");
            JSONObject optJSONObject = jSONObject.optJSONObject("extra");
            if (optJSONObject != null) {
                this.j = optJSONObject.optInt("rpfg");
                this.k = optJSONObject.optString("rpurl");
                if (this.k != null) {
                    this.k = new String(Base64.decode(this.k, 0));
                }
            }
            if (optString == null || optString.equalsIgnoreCase("0")) {
                this.a = "定位失败";
                return false;
            }
            String optString2 = jSONObject.optString("radius");
            if (optString2 == null) {
                this.a = "定位失败";
                return false;
            }
            this.g = Double.valueOf(optString2).doubleValue();
            JSONObject optJSONObject2 = jSONObject.optJSONObject(Config.EVENT_HEAT_POINT);
            if (optJSONObject2 == null) {
                this.a = "定位失败";
                return false;
            }
            String optString3 = optJSONObject2.optString(Config.EVENT_HEAT_X);
            String optString4 = optJSONObject2.optString("y");
            if (!optString3.equals("-1.000000") || !optString4.equals("-1.000000")) {
                this.a = "";
                this.f = Double.valueOf(optString3).doubleValue();
                this.e = Double.valueOf(optString4).doubleValue();
                if (optJSONObject != null) {
                    this.h = optJSONObject.optString("code");
                }
                return true;
            }
            this.a = "定位失败";
            return false;
        }
    }

    private void a(String str) {
        e eVar = new e();
        eVar.a = str;
        if (this.b != null) {
            eVar.i = this.b.b();
        }
        if (this.a != null) {
            this.a.a(false, eVar);
        }
    }

    private boolean a(String str, String str2) {
        if (this.b == null || !this.b.a()) {
            return false;
        }
        c cVar = null;
        if (this.b != null) {
            cVar = this.b.a(str, str2);
        }
        if (cVar == null) {
            return false;
        }
        e eVar = new e();
        eVar.b = cVar.a();
        eVar.d = cVar.b();
        eVar.c = cVar.d();
        eVar.f = cVar.e();
        eVar.e = cVar.f();
        eVar.h = str2;
        if (this.b != null) {
            eVar.i = this.b.b();
        }
        if (this.a != null) {
            this.a.a(true, eVar);
        }
        return true;
    }

    private void b(d dVar) {
        b bVar = new b();
        bVar.a = dVar;
        bVar.b("https://loc.map.baidu.com/ios_indoorloc");
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public String c(String str) {
        String str2 = null;
        if (str == null) {
            return str2;
        }
        try {
            File file = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[((int) file.length())];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public void a(d dVar) {
        if (dVar.g == null) {
            a("no bid");
            return;
        }
        this.b.a(dVar.g);
        if (dVar.f == null || dVar.a || !a(dVar.g, dVar.f)) {
            b(dVar);
        }
    }
}
