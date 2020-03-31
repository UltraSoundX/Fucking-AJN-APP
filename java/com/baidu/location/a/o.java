package com.baidu.location.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.e.b;
import com.baidu.location.e.d;
import com.baidu.location.f;
import com.baidu.location.g.c;
import com.baidu.location.g.e;
import com.baidu.location.g.i;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class o extends e {

    /* renamed from: q reason: collision with root package name */
    private static o f371q = null;
    String a;
    String b;
    String c;
    String d;
    int e;
    Handler f;

    private o() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 1;
        this.f = null;
        this.f = new Handler();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r5, java.io.File r6) throws java.io.IOException {
        /*
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0043 }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ all -> 0x0043 }
            r0.<init>(r5)     // Catch:{ all -> 0x0043 }
            r3.<init>(r0)     // Catch:{ all -> 0x0043 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0046 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x0046 }
            r0.<init>(r6)     // Catch:{ all -> 0x0046 }
            r1.<init>(r0)     // Catch:{ all -> 0x0046 }
            r0 = 5120(0x1400, float:7.175E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0025 }
        L_0x0019:
            int r2 = r3.read(r0)     // Catch:{ all -> 0x0025 }
            r4 = -1
            if (r2 == r4) goto L_0x0032
            r4 = 0
            r1.write(r0, r4, r2)     // Catch:{ all -> 0x0025 }
            goto L_0x0019
        L_0x0025:
            r0 = move-exception
            r2 = r3
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            throw r0
        L_0x0032:
            r1.flush()     // Catch:{ all -> 0x0025 }
            r5.delete()     // Catch:{ all -> 0x0025 }
            if (r3 == 0) goto L_0x003d
            r3.close()
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()
        L_0x0042:
            return
        L_0x0043:
            r0 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x0046:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.o.a(java.io.File, java.io.File):void");
    }

    /* access modifiers changed from: private */
    public boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                String a2 = d.a(b.a().e());
                if (a2.equals(NetworkUtil.NETWORK_CLASS_3_G) || a2.equals(NetworkUtil.NETWORK_CLASS_4_G)) {
                    return true;
                }
            }
        } catch (Exception e2) {
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        File file = new File(j.i() + File.separator + "tmp");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            httpURLConnection.disconnect();
            fileOutputStream.close();
            bufferedInputStream.close();
            if (file.length() < 10240) {
                file.delete();
                return false;
            }
            file.renameTo(new File(j.i() + File.separator + str2));
            return true;
        } catch (Exception e2) {
            file.delete();
            return false;
        }
    }

    public static o b() {
        if (f371q == null) {
            f371q = new o();
        }
        return f371q;
    }

    private Handler f() {
        return this.f;
    }

    private void g() {
        try {
            File file = new File(j.i() + "/grtcfrsa.dat");
            if (!file.exists()) {
                File file2 = new File(i.a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(200);
            randomAccessFile2.writeBoolean(true);
            if (this.e == 1) {
                randomAccessFile2.writeBoolean(true);
            } else {
                randomAccessFile2.writeBoolean(false);
            }
            if (this.d != null) {
                byte[] bytes2 = this.d.getBytes();
                randomAccessFile2.writeInt(bytes2.length);
                randomAccessFile2.write(bytes2);
            } else if (Math.abs(f.a() - 7.62f) < 1.0E-8f) {
                randomAccessFile2.writeInt(0);
            }
            randomAccessFile2.close();
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.a != null) {
            new s(this).start();
        }
    }

    /* access modifiers changed from: private */
    public boolean i() {
        if (this.c != null && !new File(j.i() + File.separator + this.c).exists()) {
            return a("http://" + this.a + "/" + this.c, this.c);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.b != null) {
            File file = new File(j.i() + File.separator + this.b);
            if (!file.exists() && a("http://" + this.a + "/" + this.b, this.b)) {
                String a2 = j.a(file, "SHA-256");
                if (this.d != null && a2 != null && j.b(a2, this.d, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiP7BS5IjEOzrKGR9/Ww9oSDhdX1ir26VOsYjT1T6tk2XumRpkHRwZbrucDcNnvSB4QsqiEJnvTSRi7YMbh2H9sLMkcvHlMV5jAErNvnuskWfcvf7T2mq7EUZI/Hf4oVZhHV0hQJRFVdTcjWI6q2uaaKM3VMh+roDesiE7CR2biQIDAQAB")) {
                    File file2 = new File(j.i() + File.separator + f.d);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    try {
                        a(file, file2);
                    } catch (Exception e2) {
                        file2.delete();
                    }
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void a() {
        String str;
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.62f);
        stringBuffer.append("&fw=");
        stringBuffer.append(f.a());
        stringBuffer.append("&suit=");
        stringBuffer.append(2);
        if (com.baidu.location.g.b.a().b == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(com.baidu.location.g.b.a().a);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(com.baidu.location.g.b.a().b);
        }
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&sv=");
        String str2 = VERSION.RELEASE;
        if (str2 != null && str2.length() > 10) {
            str2 = str2.substring(0, 10);
        }
        stringBuffer.append(str2);
        try {
            if (VERSION.SDK_INT > 20) {
                String[] strArr = Build.SUPPORTED_ABIS;
                str = null;
                while (i < strArr.length) {
                    str = i == 0 ? strArr[i] + ";" : str + strArr[i] + ";";
                    i++;
                }
            } else {
                str = Build.CPU_ABI2;
            }
        } catch (Error e2) {
            str = null;
        } catch (Exception e3) {
            str = null;
        }
        if (str != null) {
            stringBuffer.append("&cpuabi=");
            stringBuffer.append(str);
        }
        stringBuffer.append("&pack=");
        stringBuffer.append(com.baidu.location.g.b.d);
        this.h = j.e() + "?&it=" + Jni.b(stringBuffer.toString());
    }

    public void a(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.j);
                if ("up".equals(jSONObject.getString("res"))) {
                    this.a = jSONObject.getString("upath");
                    if (jSONObject.has("u1")) {
                        this.b = jSONObject.getString("u1");
                    }
                    if (jSONObject.has("u2")) {
                        this.c = jSONObject.getString("u2");
                    }
                    if (jSONObject.has("u1_rsa")) {
                        this.d = jSONObject.getString("u1_rsa");
                    }
                    f().post(new r(this));
                }
                if (jSONObject.has("ison")) {
                    this.e = jSONObject.getInt("ison");
                }
                g();
            } catch (Exception e2) {
            }
        }
        c.a().a(System.currentTimeMillis());
    }

    public void c() {
        if (System.currentTimeMillis() - c.a().b() > 86400000) {
            f().postDelayed(new p(this), OkHttpUtils.DEFAULT_MILLISECONDS);
            f().postDelayed(new q(this), Config.BPLUS_DELAY_TIME);
        }
    }
}
