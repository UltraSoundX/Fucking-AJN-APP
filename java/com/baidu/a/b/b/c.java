package com.baidu.a.b.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.a.b.a.d;
import com.stub.StubApp;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.core.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    private static final String a;
    private static b e;
    private static boolean f = true;
    /* access modifiers changed from: private */
    public final Context b;
    private int c = 0;
    private PublicKey d;

    static class a {
        public ApplicationInfo a;
        public int b;
        public boolean c;
        public boolean d;

        private a() {
            this.b = 0;
            this.c = false;
            this.d = false;
        }
    }

    static class b {
        public String a;
        public String b;
        public int c;
        public int d;

        private b() {
            this.c = 2;
            this.d = 0;
        }

        public boolean a() {
            return a(this.b);
        }

        public boolean b() {
            return a(this.d);
        }

        public static boolean a(String str) {
            return TextUtils.isEmpty(str);
        }

        public static boolean a(int i) {
            return i >= 14;
        }

        public static b b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator keys = jSONObject.keys();
                String str2 = "0";
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    if (!c.m("ZGV2aWNlaWQ=").equals(str3) && !c.m("dmVy").equals(str3)) {
                        str2 = jSONObject.optString(str3, "0");
                    }
                }
                String string = jSONObject.getString(c.m("ZGV2aWNlaWQ="));
                int i = jSONObject.getInt(c.m("dmVy"));
                int length = TextUtils.isEmpty(str2) ? 0 : str2.length();
                if (!TextUtils.isEmpty(string)) {
                    b bVar = new b();
                    bVar.a = string;
                    bVar.c = i;
                    bVar.d = length;
                    if (bVar.d >= 14) {
                        return bVar;
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "0";
                    }
                    bVar.b = str2;
                    return bVar;
                }
            } catch (JSONException e) {
                c.b((Throwable) e);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static b b(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            b bVar = new b();
            bVar.a = str;
            bVar.d = TextUtils.isEmpty(str2) ? 0 : str2.length();
            if (bVar.d < 14) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                bVar.b = str2;
            }
            return bVar;
        }

        public String c() {
            try {
                return new JSONObject().put(c.m("ZGV2aWNlaWQ="), this.a).put(c.m("aW1laQ=="), this.b).put(c.m("dmVy"), this.c).toString();
            } catch (JSONException e) {
                c.b((Throwable) e);
                return null;
            }
        }

        public String d() {
            String str = this.b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.a + "|" + str;
        }
    }

    /* renamed from: com.baidu.a.b.b.c$c reason: collision with other inner class name */
    static class C0021c {
        static boolean a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e) {
                c.b((Throwable) e);
                return false;
            } catch (Exception e2) {
                c.b((Throwable) e2);
                return false;
            }
        }
    }

    static {
        String str = new String(com.baidu.a.b.a.b.a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61}));
        a = str + new String(com.baidu.a.b.a.b.a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));
    }

    private c(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        a();
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String str = "";
        String str2 = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
            if (hexString.length() == 1) {
                str2 = str2 + "0" + hexString;
            } else {
                str2 = str2 + hexString;
            }
        }
        return str2.toLowerCase();
    }

    private String[] a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = a(d.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    private static byte[] a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029 A[SYNTHETIC, Splitter:B:14:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC, Splitter:B:20:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
            r4 = this;
            r1 = 0
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0025, all -> 0x0032 }
            byte[] r2 = com.baidu.a.b.b.b.a()     // Catch:{ Exception -> 0x0025, all -> 0x0032 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0025, all -> 0x0032 }
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch:{ Exception -> 0x0043, all -> 0x003e }
            java.security.cert.Certificate r1 = r1.generateCertificate(r0)     // Catch:{ Exception -> 0x0043, all -> 0x003e }
            java.security.PublicKey r1 = r1.getPublicKey()     // Catch:{ Exception -> 0x0043, all -> 0x003e }
            r4.d = r1     // Catch:{ Exception -> 0x0043, all -> 0x003e }
            if (r0 == 0) goto L_0x001f
            r0.close()     // Catch:{ Exception -> 0x0020 }
        L_0x001f:
            return
        L_0x0020:
            r0 = move-exception
            b(r0)
            goto L_0x001f
        L_0x0025:
            r0 = move-exception
            r0 = r1
        L_0x0027:
            if (r0 == 0) goto L_0x001f
            r0.close()     // Catch:{ Exception -> 0x002d }
            goto L_0x001f
        L_0x002d:
            r0 = move-exception
            b(r0)
            goto L_0x001f
        L_0x0032:
            r0 = move-exception
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ Exception -> 0x0039 }
        L_0x0038:
            throw r0
        L_0x0039:
            r1 = move-exception
            b(r1)
            goto L_0x0038
        L_0x003e:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0033
        L_0x0043:
            r1 = move-exception
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.b.b.c.a():void");
    }

    private List<a> a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.b.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] a2 = com.baidu.a.b.a.b.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(a2));
                                a aVar = new a();
                                aVar.b = jSONObject.getInt("priority");
                                aVar.a = resolveInfo.activityInfo.applicationInfo;
                                if (this.b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    aVar.d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (a(strArr, a(packageInfo.signatures))) {
                                            byte[] a3 = a(com.baidu.a.b.a.b.a(string2.getBytes()), this.d);
                                            if (a3 != null && Arrays.equals(a3, d.a(a2))) {
                                                aVar.c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(aVar);
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new Comparator<a>() {
            /* renamed from: a */
            public int compare(a aVar, a aVar2) {
                int i = aVar2.b - aVar.b;
                if (i != 0) {
                    return i;
                }
                if (aVar.d && aVar2.d) {
                    return 0;
                }
                if (aVar.d) {
                    return -1;
                }
                if (aVar2.d) {
                    return 1;
                }
                return i;
            }
        });
        return arrayList;
    }

    private boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String add : strArr) {
            hashSet.add(add);
        }
        HashSet hashSet2 = new HashSet();
        for (String add2 : strArr2) {
            hashSet2.add(add2);
        }
        return hashSet.equals(hashSet2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006b, code lost:
        b((java.lang.Throwable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008f, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0066 A[SYNTHETIC, Splitter:B:34:0x0066] */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean e(java.lang.String r7) {
        /*
            r6 = this;
            r2 = 1
            r1 = 0
            r3 = 0
            boolean r0 = f
            if (r0 == 0) goto L_0x004b
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r0 < r4) goto L_0x0049
            r0 = r1
        L_0x000e:
            android.content.Context r4 = r6.b     // Catch:{ Exception -> 0x0052, all -> 0x0063 }
            java.lang.String r5 = "libcuid.so"
            java.io.FileOutputStream r3 = r4.openFileOutput(r5, r0)     // Catch:{ Exception -> 0x0052, all -> 0x0063 }
            byte[] r4 = r7.getBytes()     // Catch:{ Exception -> 0x008e, all -> 0x0063 }
            r3.write(r4)     // Catch:{ Exception -> 0x008e, all -> 0x0063 }
            r3.flush()     // Catch:{ Exception -> 0x008e, all -> 0x0063 }
            if (r3 == 0) goto L_0x0025
            r3.close()     // Catch:{ Exception -> 0x004d }
        L_0x0025:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r1 < r3) goto L_0x0048
            if (r0 != 0) goto L_0x006f
            boolean r0 = f
            if (r0 == 0) goto L_0x006f
            r0 = 436(0x1b4, float:6.11E-43)
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r6.b
            java.io.File r2 = r2.getFilesDir()
            java.lang.String r3 = "libcuid.so"
            r1.<init>(r2, r3)
            java.lang.String r1 = r1.getAbsolutePath()
            boolean r2 = com.baidu.a.b.b.c.C0021c.a(r1, r0)
        L_0x0048:
            return r2
        L_0x0049:
            r0 = r2
            goto L_0x000e
        L_0x004b:
            r0 = r1
            goto L_0x000e
        L_0x004d:
            r1 = move-exception
            b(r1)
            goto L_0x0025
        L_0x0052:
            r0 = move-exception
            r2 = r3
        L_0x0054:
            b(r0)     // Catch:{ all -> 0x008b }
            if (r2 == 0) goto L_0x005c
            r2.close()     // Catch:{ Exception -> 0x005e }
        L_0x005c:
            r2 = r1
            goto L_0x0048
        L_0x005e:
            r0 = move-exception
            b(r0)
            goto L_0x005c
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            if (r3 == 0) goto L_0x0069
            r3.close()     // Catch:{ Exception -> 0x006a }
        L_0x0069:
            throw r0
        L_0x006a:
            r1 = move-exception
            b(r1)
            goto L_0x0069
        L_0x006f:
            boolean r0 = f
            if (r0 != 0) goto L_0x0048
            r0 = 432(0x1b0, float:6.05E-43)
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r6.b
            java.io.File r2 = r2.getFilesDir()
            java.lang.String r3 = "libcuid.so"
            r1.<init>(r2, r3)
            java.lang.String r1 = r1.getAbsolutePath()
            boolean r2 = com.baidu.a.b.b.c.C0021c.a(r1, r0)
            goto L_0x0048
        L_0x008b:
            r0 = move-exception
            r3 = r2
            goto L_0x0064
        L_0x008e:
            r0 = move-exception
            r2 = r3
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.b.b.c.e(java.lang.String):boolean");
    }

    /* access modifiers changed from: private */
    public String f(String str) {
        try {
            return System.getString(this.b.getContentResolver(), str);
        } catch (Exception e2) {
            b((Throwable) e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str, String str2) {
        try {
            return System.putString(this.b.getContentResolver(), str, str2);
        } catch (Exception e2) {
            b((Throwable) e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static void b(Throwable th) {
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x003d A[SYNTHETIC, Splitter:B:28:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.io.File r6) {
        /*
            r0 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x0048, all -> 0x0038 }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0048, all -> 0x0038 }
            r1 = 8192(0x2000, float:1.14794E-41)
            char[] r1 = new char[r1]     // Catch:{ Exception -> 0x001a }
            java.io.CharArrayWriter r3 = new java.io.CharArrayWriter     // Catch:{ Exception -> 0x001a }
            r3.<init>()     // Catch:{ Exception -> 0x001a }
        L_0x000f:
            int r4 = r2.read(r1)     // Catch:{ Exception -> 0x001a }
            if (r4 <= 0) goto L_0x0024
            r5 = 0
            r3.write(r1, r5, r4)     // Catch:{ Exception -> 0x001a }
            goto L_0x000f
        L_0x001a:
            r1 = move-exception
        L_0x001b:
            b(r1)     // Catch:{ all -> 0x0046 }
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0023:
            return r0
        L_0x0024:
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x001a }
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ Exception -> 0x002e }
            goto L_0x0023
        L_0x002e:
            r1 = move-exception
            b(r1)
            goto L_0x0023
        L_0x0033:
            r1 = move-exception
            b(r1)
            goto L_0x0023
        L_0x0038:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x003b:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch:{ Exception -> 0x0041 }
        L_0x0040:
            throw r0
        L_0x0041:
            r1 = move-exception
            b(r1)
            goto L_0x0040
        L_0x0046:
            r0 = move-exception
            goto L_0x003b
        L_0x0048:
            r1 = move-exception
            r2 = r0
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.b.b.c.b(java.io.File):java.lang.String");
    }

    public static String a(Context context) {
        return b(context).d();
    }

    private static b b(Context context) {
        if (e == null) {
            synchronized (b.class) {
                if (e == null) {
                    SystemClock.uptimeMillis();
                    e = new c(context).b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return e;
    }

    private static String c(Context context) {
        String str = "";
        String str2 = "";
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return string;
    }

    private b b() {
        boolean z;
        b bVar;
        b bVar2;
        b bVar3;
        String str;
        b bVar4;
        String str2;
        String str3;
        boolean z2 = false;
        List a2 = a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.b.getPackageName()), true);
        if (a2 == null || a2.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            a aVar = (a) a2.get(0);
            boolean z3 = aVar.c;
            if (!aVar.c) {
                for (int i2 = 0; i2 < 3; i2++) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
            z = z3;
        }
        File file = new File(this.b.getFilesDir(), "libcuid.so");
        if (file.exists()) {
            bVar = b.b(j(b(file)));
        } else {
            bVar = null;
        }
        if (bVar == null) {
            this.c |= 16;
            List a3 = a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (a3 != null) {
                String str4 = "files";
                File filesDir = this.b.getFilesDir();
                if (!str4.equals(filesDir.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    str3 = filesDir.getName();
                } else {
                    str3 = str4;
                }
                Iterator it = a3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    a aVar2 = (a) it.next();
                    if (!aVar2.d) {
                        File file2 = new File(new File(aVar2.a.dataDir, str3), "libcuid.so");
                        if (file2.exists()) {
                            bVar2 = b.b(j(b(file2)));
                            if (bVar2 != null) {
                                break;
                            }
                        } else {
                            bVar2 = bVar;
                        }
                        bVar = bVar2;
                    }
                }
            }
        }
        bVar2 = bVar;
        if (bVar2 == null) {
            bVar2 = b.b(j(f("com.baidu.deviceid.v2")));
        }
        boolean g = g("android.permission.READ_EXTERNAL_STORAGE");
        if (bVar2 != null || !g) {
            bVar3 = bVar2;
        } else {
            this.c |= 2;
            bVar3 = e();
        }
        if (bVar3 == null) {
            this.c |= 8;
            bVar3 = d();
        }
        if (bVar3 != null || !g) {
            str = null;
        } else {
            this.c |= 1;
            str = l("");
            bVar3 = h(str);
            z2 = true;
        }
        if (bVar3 == null) {
            this.c |= 4;
            if (!z2) {
                str = l("");
            }
            b bVar5 = new b();
            String c2 = c(this.b);
            if (VERSION.SDK_INT < 23) {
                str2 = str + c2 + UUID.randomUUID().toString();
            } else {
                str2 = "com.baidu" + c2;
            }
            bVar5.a = com.baidu.a.b.a.c.a(str2.getBytes(), true);
            bVar5.b = str;
            bVar4 = bVar5;
        } else {
            bVar4 = bVar3;
        }
        b(bVar4);
        a(bVar4);
        return bVar4;
    }

    private synchronized void a(b bVar) {
        new Thread(c(bVar)).start();
    }

    /* access modifiers changed from: private */
    public boolean b(b bVar) {
        if (bVar.b()) {
            bVar.b = "O";
            return true;
        } else if (!bVar.a()) {
            return false;
        } else {
            bVar.b = "0";
            return true;
        }
    }

    private Runnable c(final b bVar) {
        return new Runnable() {
            public void run() {
                b bVar = new b();
                bVar.b = bVar.b;
                bVar.a = bVar.a;
                File file = new File(c.this.b.getFilesDir(), "libcuid.so");
                String b2 = c.i(bVar.c());
                if (!file.exists()) {
                    c.this.e(b2);
                } else {
                    b b3 = b.b(c.j(c.b(file)));
                    if (b3 != null) {
                        if (c.this.b(b3)) {
                            c.this.e(c.i(b3.c()));
                        }
                    } else if (b3 == null) {
                        c.this.e(b2);
                    }
                }
                boolean b4 = c.this.c();
                if (b4) {
                    String b5 = c.this.f("com.baidu.deviceid.v2");
                    if (TextUtils.isEmpty(b5)) {
                        c.this.b("com.baidu.deviceid.v2", b2);
                    } else {
                        b b6 = b.b(c.j(b5));
                        if (b6 != null) {
                            if (c.this.b(b6)) {
                                c.this.b("com.baidu.deviceid.v2", c.i(b6.c()));
                            }
                        } else if (b6 == null) {
                            c.this.b("com.baidu.deviceid.v2", b2);
                        }
                    }
                }
                boolean c = c.this.g(Constants.PERMISSION_WRITE_EXTERNAL_STORAGE);
                if (c) {
                    if (!new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2").exists()) {
                        c.k(b2);
                    } else {
                        b c2 = c.this.e();
                        if (c2 != null) {
                            if (c.this.b(c2)) {
                                c.k(c.i(c2.c()));
                            }
                        } else if (c2 == null) {
                            c.k(b2);
                        }
                    }
                }
                if (b4) {
                    String b7 = c.this.f("bd_setting_i");
                    if (b.a(TextUtils.isEmpty(b7) ? 0 : b7.length())) {
                        c.this.b("bd_setting_i", "O");
                    } else if (b.a(b7)) {
                        c.this.b("bd_setting_i", "0");
                    }
                }
                if (c && new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid").exists()) {
                    b e = c.this.h(c.this.l(""));
                    if (e == null) {
                        if (e == null) {
                        }
                    } else if (c.this.b(e)) {
                        c.c(e.b, e.a);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean c() {
        return g(Constants.PERMISSION_WRITE_SETTINGS);
    }

    /* access modifiers changed from: private */
    public boolean g(String str) {
        return this.b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private b d() {
        return b.b(f("com.baidu.deviceid"), f("bd_setting_i"));
    }

    /* access modifiers changed from: private */
    public b e() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            String b2 = b(file);
            if (!TextUtils.isEmpty(b2)) {
                try {
                    return b.b(new String(com.baidu.a.b.a.a.b(a, a, com.baidu.a.b.a.b.a(b2.getBytes()))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public b h(String str) {
        String str2 = "";
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\r\n");
            }
            bufferedReader.close();
            String[] split = new String(com.baidu.a.b.a.a.b(a, a, com.baidu.a.b.a.b.a(sb.toString().getBytes()))).split("=");
            if (split != null && split.length == 2) {
                str3 = split[0];
                str2 = split[1];
            }
        } catch (FileNotFoundException | IOException | Exception e2) {
        }
        return b.b(str2, str3);
    }

    /* access modifiers changed from: private */
    public static String i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return com.baidu.a.b.a.b.a(com.baidu.a.b.a.a.a(a, a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            b((Throwable) e2);
        } catch (Exception e3) {
            b((Throwable) e3);
        }
        return "";
    }

    /* access modifiers changed from: private */
    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(com.baidu.a.b.a.a.b(a, a, com.baidu.a.b.a.b.a(str.getBytes())));
        } catch (Exception e2) {
            b((Throwable) e2);
            return "";
        }
    }

    /* access modifiers changed from: private */
    public static void k(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception e2) {
        }
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2) {
        File file;
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
            File file3 = new File(file2, ".cuid");
            try {
                if (file2.exists() && !file2.isDirectory()) {
                    Random random = new Random();
                    File parentFile = file2.getParentFile();
                    String name = file2.getName();
                    do {
                        file = new File(parentFile, name + random.nextInt() + ".tmp");
                    } while (file.exists());
                    file2.renameTo(file);
                    file.delete();
                }
                file2.mkdirs();
                FileWriter fileWriter = new FileWriter(file3, false);
                fileWriter.write(com.baidu.a.b.a.b.a(com.baidu.a.b.a.a.a(a, a, sb.toString().getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException | Exception e2) {
            }
        }
    }

    /* access modifiers changed from: private */
    public String l(String str) {
        return "0";
    }

    /* access modifiers changed from: private */
    public static String m(String str) {
        return new String(com.baidu.a.b.a.b.a(str.getBytes()));
    }
}
