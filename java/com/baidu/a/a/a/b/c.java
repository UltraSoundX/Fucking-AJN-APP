package com.baidu.a.a.a.b;

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
import com.baidu.a.a.a.a.d;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.core.Constants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private final Context b;
    private int c = 0;
    private PublicKey d;

    private static class a {
        public ApplicationInfo a;
        public int b;
        public boolean c;
        public boolean d;

        private a() {
            this.b = 0;
            this.c = false;
            this.d = false;
        }

        /* synthetic */ a(d dVar) {
            this();
        }
    }

    private static class b {
        public String a;
        public String b;
        public int c;

        private b() {
            this.c = 2;
        }

        /* synthetic */ b(d dVar) {
            this();
        }

        public static b a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("deviceid");
                String string2 = jSONObject.getString(MidEntity.TAG_IMEI);
                int i = jSONObject.getInt(MidEntity.TAG_VER);
                if (TextUtils.isEmpty(string) || string2 == null) {
                    return null;
                }
                b bVar = new b();
                bVar.a = string;
                bVar.b = string2;
                bVar.c = i;
                return bVar;
            } catch (JSONException e) {
                c.b((Throwable) e);
                return null;
            }
        }

        public String a() {
            try {
                return new JSONObject().put("deviceid", this.a).put(MidEntity.TAG_IMEI, this.b).put(MidEntity.TAG_VER, this.c).toString();
            } catch (JSONException e) {
                c.b((Throwable) e);
                return null;
            }
        }

        public String b() {
            String str = this.b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    /* renamed from: com.baidu.a.a.a.b.c$c reason: collision with other inner class name */
    static class C0020c {
        static boolean a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e) {
                c.b((Throwable) e);
                return false;
            }
        }
    }

    static {
        String str = new String(com.baidu.a.a.a.a.b.a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61}));
        a = str + new String(com.baidu.a.a.a.a.b.a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));
    }

    private c(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        a();
    }

    public static String a(Context context) {
        return c(context).b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003d A[SYNTHETIC, Splitter:B:28:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.File r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.a.a.b.c.a(java.io.File):java.lang.String");
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String str = "";
        String str2 = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
            str2 = hexString.length() == 1 ? str2 + "0" + hexString : str2 + hexString;
        }
        return str2.toLowerCase();
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
                                byte[] a2 = com.baidu.a.a.a.a.b.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(a2));
                                a aVar = new a(null);
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
                                            byte[] a3 = a(com.baidu.a.a.a.a.b.a(string2.getBytes()), this.d);
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
        Collections.sort(arrayList, new d(this));
        return arrayList;
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
            byte[] r2 = com.baidu.a.a.a.b.b.a()     // Catch:{ Exception -> 0x0025, all -> 0x0032 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.a.a.b.c.a():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        b((java.lang.Throwable) r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056 A[SYNTHETIC, Splitter:B:27:0x0056] */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r7) {
        /*
            r6 = this;
            r2 = 1
            r1 = 0
            r3 = 0
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r0 < r4) goto L_0x003b
            r0 = r1
        L_0x000a:
            android.content.Context r4 = r6.b     // Catch:{ Exception -> 0x0042, all -> 0x0053 }
            java.lang.String r5 = "libcuid.so"
            java.io.FileOutputStream r3 = r4.openFileOutput(r5, r0)     // Catch:{ Exception -> 0x0042, all -> 0x0053 }
            byte[] r4 = r7.getBytes()     // Catch:{ Exception -> 0x0062, all -> 0x0053 }
            r3.write(r4)     // Catch:{ Exception -> 0x0062, all -> 0x0053 }
            r3.flush()     // Catch:{ Exception -> 0x0062, all -> 0x0053 }
            if (r3 == 0) goto L_0x0021
            r3.close()     // Catch:{ Exception -> 0x003d }
        L_0x0021:
            if (r0 != 0) goto L_0x003a
            r0 = 436(0x1b4, float:6.11E-43)
            java.io.File r1 = new java.io.File
            android.content.Context r2 = r6.b
            java.io.File r2 = r2.getFilesDir()
            java.lang.String r3 = "libcuid.so"
            r1.<init>(r2, r3)
            java.lang.String r1 = r1.getAbsolutePath()
            boolean r2 = com.baidu.a.a.a.b.c.C0020c.a(r1, r0)
        L_0x003a:
            return r2
        L_0x003b:
            r0 = r2
            goto L_0x000a
        L_0x003d:
            r1 = move-exception
            b(r1)
            goto L_0x0021
        L_0x0042:
            r0 = move-exception
            r2 = r3
        L_0x0044:
            b(r0)     // Catch:{ all -> 0x005f }
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ Exception -> 0x004e }
        L_0x004c:
            r2 = r1
            goto L_0x003a
        L_0x004e:
            r0 = move-exception
            b(r0)
            goto L_0x004c
        L_0x0053:
            r0 = move-exception
        L_0x0054:
            if (r3 == 0) goto L_0x0059
            r3.close()     // Catch:{ Exception -> 0x005a }
        L_0x0059:
            throw r0
        L_0x005a:
            r1 = move-exception
            b(r1)
            goto L_0x0059
        L_0x005f:
            r0 = move-exception
            r3 = r2
            goto L_0x0054
        L_0x0062:
            r0 = move-exception
            r2 = r3
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.a.a.b.c.a(java.lang.String):boolean");
    }

    private boolean a(String str, String str2) {
        try {
            return System.putString(this.b.getContentResolver(), str, str2);
        } catch (Exception e2) {
            b((Throwable) e2);
            return false;
        }
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

    private static byte[] a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    private String[] a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = a(d.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    private b b() {
        boolean z;
        b bVar;
        b bVar2;
        String str;
        b bVar3;
        String str2;
        String str3 = null;
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
        b bVar4 = file.exists() ? b.a(f(a(file))) : null;
        if (bVar4 == null) {
            this.c |= 16;
            List a3 = a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (a3 != null) {
                String str4 = "files";
                File filesDir = this.b.getFilesDir();
                if (!str4.equals(filesDir.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    str2 = filesDir.getName();
                } else {
                    str2 = str4;
                }
                Iterator it = a3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    a aVar2 = (a) it.next();
                    if (!aVar2.d) {
                        File file2 = new File(new File(aVar2.a.dataDir, str2), "libcuid.so");
                        if (file2.exists()) {
                            bVar = b.a(f(a(file2)));
                            if (bVar != null) {
                                break;
                            }
                        } else {
                            bVar = bVar4;
                        }
                        bVar4 = bVar;
                    }
                }
            }
        }
        bVar = bVar4;
        if (bVar == null) {
            bVar = b.a(f(b("com.baidu.deviceid.v2")));
        }
        boolean c2 = c("android.permission.READ_EXTERNAL_STORAGE");
        if (bVar != null || !c2) {
            bVar2 = bVar;
        } else {
            this.c |= 2;
            bVar2 = e();
        }
        if (bVar2 == null) {
            this.c |= 8;
            bVar2 = d();
        }
        if (bVar2 != null || !c2) {
            str = null;
        } else {
            this.c |= 1;
            str = h("");
            bVar2 = d(str);
            z2 = true;
        }
        if (bVar2 == null) {
            this.c |= 4;
            if (!z2) {
                str = h("");
            }
            b bVar5 = new b(null);
            String b2 = b(this.b);
            bVar5.a = com.baidu.a.a.a.a.c.a((VERSION.SDK_INT < 23 ? str + b2 + UUID.randomUUID().toString() : "com.baidu" + b2).getBytes(), true);
            bVar5.b = str;
            bVar3 = bVar5;
        } else {
            bVar3 = bVar2;
        }
        File file3 = new File(this.b.getFilesDir(), "libcuid.so");
        if ((this.c & 16) != 0 || !file3.exists()) {
            String str5 = TextUtils.isEmpty(null) ? e(bVar3.a()) : null;
            a(str5);
            str3 = str5;
        }
        boolean c3 = c();
        if (c3 && ((this.c & 2) != 0 || TextUtils.isEmpty(b("com.baidu.deviceid.v2")))) {
            if (TextUtils.isEmpty(str3)) {
                str3 = e(bVar3.a());
            }
            a("com.baidu.deviceid.v2", str3);
        }
        if (c(Constants.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            File file4 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if ((this.c & 8) != 0 || !file4.exists()) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = e(bVar3.a());
                }
                g(str3);
            }
        }
        if (c3 && ((this.c & 1) != 0 || TextUtils.isEmpty(b("com.baidu.deviceid")))) {
            a("com.baidu.deviceid", bVar3.a);
        }
        if (c3 && !TextUtils.isEmpty(bVar3.b)) {
            File file5 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if ((this.c & 2) != 0 || !file5.exists()) {
                b(bVar3.b, bVar3.a);
            }
        }
        return bVar3;
    }

    public static String b(Context context) {
        String str = "";
        String string = Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private String b(String str) {
        try {
            return System.getString(this.b.getContentResolver(), str);
        } catch (Exception e2) {
            b((Throwable) e2);
            return null;
        }
    }

    private static void b(String str, String str2) {
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
                fileWriter.write(com.baidu.a.a.a.a.b.a(com.baidu.a.a.a.a.a.a(a, a, sb.toString().getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException | Exception e2) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Throwable th) {
    }

    private static b c(Context context) {
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

    private boolean c() {
        return c(Constants.PERMISSION_WRITE_SETTINGS);
    }

    private boolean c(String str) {
        return this.b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private b d() {
        String b2 = b("com.baidu.deviceid");
        String b3 = b("bd_setting_i");
        if (TextUtils.isEmpty(b3)) {
            b3 = h("");
            if (!TextUtils.isEmpty(b3)) {
            }
        }
        if (TextUtils.isEmpty(b2)) {
            b2 = b(com.baidu.a.a.a.a.c.a(("com.baidu" + b3 + b(this.b)).getBytes(), true));
        }
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        b bVar = new b(null);
        bVar.a = b2;
        bVar.b = b3;
        return bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0 A[SYNTHETIC, Splitter:B:32:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.a.a.a.b.c.b d(java.lang.String r9) {
        /*
            r8 = this;
            r2 = 0
            r3 = 0
            r4 = 1
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 >= r1) goto L_0x0014
            r5 = r4
        L_0x000a:
            if (r5 == 0) goto L_0x0016
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto L_0x0016
            r0 = r2
        L_0x0013:
            return r0
        L_0x0014:
            r5 = r3
            goto L_0x000a
        L_0x0016:
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "baidu/.cuid"
            r1.<init>(r6, r7)
            boolean r6 = r1.exists()
            if (r6 == 0) goto L_0x005a
        L_0x0029:
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r4.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
        L_0x0038:
            java.lang.String r6 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            if (r6 == 0) goto L_0x0067
            r4.append(r6)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r6 = "\r\n"
            r4.append(r6)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            goto L_0x0038
        L_0x0047:
            r1 = move-exception
            r1 = r9
        L_0x0049:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x00b6
            com.baidu.a.a.a.b.c$b r3 = new com.baidu.a.a.a.b.c$b
            r3.<init>(r2)
            r3.a = r0
            r3.b = r1
            r0 = r3
            goto L_0x0013
        L_0x005a:
            java.io.File r1 = new java.io.File
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r6 = "backups/.SystemConfig/.cuid"
            r1.<init>(r3, r6)
            r3 = r4
            goto L_0x0029
        L_0x0067:
            r1.close()     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r1 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r6 = a     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r7 = a     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r4 = r4.toString()     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            byte[] r4 = r4.getBytes()     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            byte[] r4 = com.baidu.a.a.a.a.b.a(r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            byte[] r4 = com.baidu.a.a.a.a.a.b(r6, r7, r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            java.lang.String r4 = "="
            java.lang.String[] r1 = r1.split(r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            if (r1 == 0) goto L_0x00c3
            int r4 = r1.length     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r6 = 2
            if (r4 != r6) goto L_0x00c3
            if (r5 == 0) goto L_0x00a6
            r4 = 0
            r4 = r1[r4]     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            boolean r4 = r9.equals(r4)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            if (r4 == 0) goto L_0x00a6
            r4 = 1
            r0 = r1[r4]     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r1 = r9
        L_0x009e:
            if (r3 != 0) goto L_0x0049
            b(r1, r0)     // Catch:{ FileNotFoundException -> 0x00a4, IOException -> 0x00c1, Exception -> 0x00bc }
            goto L_0x0049
        L_0x00a4:
            r3 = move-exception
            goto L_0x0049
        L_0x00a6:
            if (r5 != 0) goto L_0x00c3
            boolean r4 = android.text.TextUtils.isEmpty(r9)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            if (r4 == 0) goto L_0x00b1
            r4 = 1
            r9 = r1[r4]     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
        L_0x00b1:
            r4 = 1
            r0 = r1[r4]     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9 }
            r1 = r9
            goto L_0x009e
        L_0x00b6:
            r0 = r2
            goto L_0x0013
        L_0x00b9:
            r1 = move-exception
            r1 = r9
            goto L_0x0049
        L_0x00bc:
            r3 = move-exception
            goto L_0x0049
        L_0x00be:
            r1 = move-exception
            r1 = r9
            goto L_0x0049
        L_0x00c1:
            r3 = move-exception
            goto L_0x0049
        L_0x00c3:
            r1 = r9
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.a.a.b.c.d(java.lang.String):com.baidu.a.a.a.b.c$b");
    }

    private b e() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            String a2 = a(file);
            if (!TextUtils.isEmpty(a2)) {
                try {
                    return b.a(new String(com.baidu.a.a.a.a.a.b(a, a, com.baidu.a.a.a.a.b.a(a2.getBytes()))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return com.baidu.a.a.a.a.b.a(com.baidu.a.a.a.a.a.a(a, a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            b((Throwable) e2);
        } catch (Exception e3) {
            b((Throwable) e3);
        }
        return "";
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(com.baidu.a.a.a.a.a.b(a, a, com.baidu.a.a.a.a.b.a(str.getBytes())));
        } catch (Exception e2) {
            b((Throwable) e2);
            return "";
        }
    }

    private static void g(String str) {
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

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String h(java.lang.String r5) {
        /*
            r4 = this;
            r1 = 0
            android.content.Context r0 = r4.b     // Catch:{ Exception -> 0x001c }
            java.lang.String r2 = "phone"
            java.lang.Object r0 = r0.getSystemService(r2)     // Catch:{ Exception -> 0x001c }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x001c }
            if (r0 == 0) goto L_0x0024
            java.lang.String r0 = r0.getDeviceId()     // Catch:{ Exception -> 0x001c }
        L_0x0011:
            java.lang.String r0 = i(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0026
        L_0x001b:
            return r5
        L_0x001c:
            r0 = move-exception
            java.lang.String r2 = "DeviceId"
            java.lang.String r3 = "Read IMEI failed"
            android.util.Log.e(r2, r3, r0)
        L_0x0024:
            r0 = r1
            goto L_0x0011
        L_0x0026:
            r5 = r0
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.a.a.a.b.c.h(java.lang.String):java.lang.String");
    }

    private static String i(String str) {
        return (str == null || !str.contains(Config.TRACE_TODAY_VISIT_SPLIT)) ? str : "";
    }
}
