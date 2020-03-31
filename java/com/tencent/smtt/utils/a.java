package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import java.io.File;
import java.util.regex.Pattern;

/* compiled from: ApkUtil */
public class a {
    public static boolean a(Context context, File file, long j, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (j > 0 && j != file.length()) {
            return false;
        }
        try {
            if (i != a(context, file)) {
                return false;
            }
            if ("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(b.a(context, true, file))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A[SYNTHETIC, Splitter:B:13:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006a A[SYNTHETIC, Splitter:B:31:0x006a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r11) {
        /*
            r1 = 0
            r10 = 16
            r0 = 0
            char[] r4 = new char[r10]
            r4 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102} // fill-array
            r2 = 32
            char[] r5 = new char[r2]
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r2)     // Catch:{ Exception -> 0x0075, all -> 0x0066 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0075, all -> 0x0066 }
            r2.<init>(r11)     // Catch:{ Exception -> 0x0075, all -> 0x0066 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ Exception -> 0x0028 }
        L_0x001c:
            int r7 = r2.read(r6)     // Catch:{ Exception -> 0x0028 }
            r8 = -1
            if (r7 == r8) goto L_0x0033
            r8 = 0
            r3.update(r6, r8, r7)     // Catch:{ Exception -> 0x0028 }
            goto L_0x001c
        L_0x0028:
            r0 = move-exception
        L_0x0029:
            r0.printStackTrace()     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0031
            r2.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0031:
            r0 = r1
        L_0x0032:
            return r0
        L_0x0033:
            byte[] r6 = r3.digest()     // Catch:{ Exception -> 0x0028 }
            r3 = r0
        L_0x0038:
            if (r0 >= r10) goto L_0x0051
            byte r7 = r6[r0]     // Catch:{ Exception -> 0x0028 }
            int r8 = r3 + 1
            int r9 = r7 >>> 4
            r9 = r9 & 15
            char r9 = r4[r9]     // Catch:{ Exception -> 0x0028 }
            r5[r3] = r9     // Catch:{ Exception -> 0x0028 }
            int r3 = r8 + 1
            r7 = r7 & 15
            char r7 = r4[r7]     // Catch:{ Exception -> 0x0028 }
            r5[r8] = r7     // Catch:{ Exception -> 0x0028 }
            int r0 = r0 + 1
            goto L_0x0038
        L_0x0051:
            java.lang.String r0 = new java.lang.String     // Catch:{ Exception -> 0x0028 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x0032
            r2.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0032
        L_0x005c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0032
        L_0x0061:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0031
        L_0x0066:
            r0 = move-exception
            r2 = r1
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ IOException -> 0x006e }
        L_0x006d:
            throw r0
        L_0x006e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x006d
        L_0x0073:
            r0 = move-exception
            goto L_0x0068
        L_0x0075:
            r0 = move-exception
            r2 = r1
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.a(java.io.File):java.lang.String");
    }

    public static int a(Context context, File file) {
        try {
            boolean z = VERSION.SDK_INT >= 20 ? !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME) : false;
            return a(context, file, z);
        } catch (Exception e) {
            TbsLog.i("ApkUtil", "getApkVersion Failed");
            return 0;
        }
    }

    public static final String a(boolean z) {
        if (z) {
            return "x5.decouple.backup";
        }
        return "x5.backup";
    }

    private static int a(boolean z, File file) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                File[] listFiles = parentFile.listFiles();
                Pattern compile = Pattern.compile(a(z) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        return Integer.parseInt(file2.getName().substring(file2.getName().lastIndexOf(".") + 1));
                    }
                }
            }
        } catch (Exception e) {
        }
        return -1;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r5, java.io.File r6, boolean r7) {
        /*
            r2 = 1
            r0 = 0
            if (r6 == 0) goto L_0x007f
            boolean r1 = r6.exists()     // Catch:{ Throwable -> 0x007b }
            if (r1 == 0) goto L_0x007f
            java.lang.String r1 = r6.getName()     // Catch:{ Throwable -> 0x007b }
            java.lang.String r3 = "tbs.org"
            boolean r1 = r1.contains(r3)     // Catch:{ Throwable -> 0x007b }
            java.lang.String r3 = r6.getName()     // Catch:{ Throwable -> 0x007b }
            java.lang.String r4 = "x5.tbs.decouple"
            boolean r3 = r3.contains(r4)     // Catch:{ Throwable -> 0x007b }
            if (r1 != 0) goto L_0x0022
            if (r3 == 0) goto L_0x0030
        L_0x0022:
            int r1 = a(r3, r6)     // Catch:{ Throwable -> 0x007b }
            if (r1 <= 0) goto L_0x002a
            r0 = r1
        L_0x0029:
            return r0
        L_0x002a:
            boolean r1 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)     // Catch:{ Throwable -> 0x007b }
            if (r1 == 0) goto L_0x0066
        L_0x0030:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x007b }
            r3 = 23
            if (r1 == r3) goto L_0x003c
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x007b }
            r3 = 25
            if (r1 != r3) goto L_0x009f
        L_0x003c:
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch:{ Throwable -> 0x007b }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Throwable -> 0x007b }
            java.lang.String r3 = "mi"
            boolean r1 = r1.contains(r3)     // Catch:{ Throwable -> 0x007b }
            if (r1 == 0) goto L_0x009f
            r1 = r2
        L_0x004b:
            com.tencent.smtt.sdk.TbsPVConfig.releaseInstance()     // Catch:{ Throwable -> 0x007b }
            com.tencent.smtt.sdk.TbsPVConfig r3 = com.tencent.smtt.sdk.TbsPVConfig.getInstance(r5)     // Catch:{ Throwable -> 0x007b }
            int r3 = r3.getReadApk()     // Catch:{ Throwable -> 0x007b }
            if (r3 != r2) goto L_0x0077
            r1 = r0
            r7 = r0
        L_0x005a:
            if (r7 != 0) goto L_0x005e
            if (r1 == 0) goto L_0x007f
        L_0x005e:
            int r1 = b(r6)     // Catch:{ Throwable -> 0x007b }
            if (r1 <= 0) goto L_0x007f
            r0 = r1
            goto L_0x0029
        L_0x0066:
            java.lang.String r1 = r6.getAbsolutePath()     // Catch:{ Throwable -> 0x007b }
            android.content.pm.ApplicationInfo r3 = r5.getApplicationInfo()     // Catch:{ Throwable -> 0x007b }
            java.lang.String r3 = r3.packageName     // Catch:{ Throwable -> 0x007b }
            boolean r1 = r1.contains(r3)     // Catch:{ Throwable -> 0x007b }
            if (r1 != 0) goto L_0x0030
            goto L_0x0029
        L_0x0077:
            r2 = 2
            if (r3 != r2) goto L_0x005a
            goto L_0x0029
        L_0x007b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x007f:
            if (r6 == 0) goto L_0x0029
            boolean r1 = r6.exists()
            if (r1 == 0) goto L_0x0029
            android.content.pm.PackageManager r1 = r5.getPackageManager()     // Catch:{ Throwable -> 0x0099 }
            java.lang.String r2 = r6.getAbsolutePath()     // Catch:{ Throwable -> 0x0099 }
            r3 = 1
            android.content.pm.PackageInfo r1 = r1.getPackageArchiveInfo(r2, r3)     // Catch:{ Throwable -> 0x0099 }
            if (r1 == 0) goto L_0x0029
            int r0 = r1.versionCode     // Catch:{ Throwable -> 0x0099 }
            goto L_0x0029
        L_0x0099:
            r0 = move-exception
            r0.printStackTrace()
            r0 = -1
            goto L_0x0029
        L_0x009f:
            r1 = r0
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.a(android.content.Context, java.io.File, boolean):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        r0 = java.lang.Integer.parseInt(r0[1].trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r1 == null) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r3 == null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r3.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0065 A[SYNTHETIC, Splitter:B:42:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x006a A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0075 A[SYNTHETIC, Splitter:B:50:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x007a A[Catch:{ Exception -> 0x007e }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x004c=Splitter:B:22:0x004c, B:54:0x007d=Splitter:B:54:0x007d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.io.File r6) {
        /*
            r2 = 0
            java.lang.Class<com.tencent.smtt.utils.a> r4 = com.tencent.smtt.utils.a.class
            monitor-enter(r4)
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x005e, all -> 0x0070 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x005e, all -> 0x0070 }
            java.lang.String r0 = "assets/webkit/tbs.conf"
            java.util.jar.JarEntry r0 = r3.getJarEntry(r0)     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
            java.io.InputStream r0 = r3.getInputStream(r0)     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0088, all -> 0x0080 }
        L_0x001d:
            java.lang.String r0 = r1.readLine()     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            if (r0 == 0) goto L_0x004e
            java.lang.String r2 = "tbs_core_version"
            boolean r2 = r0.contains(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            if (r2 == 0) goto L_0x001d
            java.lang.String r2 = "="
            java.lang.String[] r0 = r0.split(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            if (r0 == 0) goto L_0x001d
            int r2 = r0.length     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            r5 = 2
            if (r2 != r5) goto L_0x001d
            r2 = 1
            r0 = r0[r2]     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x008c, all -> 0x0083 }
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ Exception -> 0x0091 }
        L_0x0047:
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ Exception -> 0x0091 }
        L_0x004c:
            monitor-exit(r4)     // Catch:{ all -> 0x005b }
        L_0x004d:
            return r0
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ Exception -> 0x008f }
        L_0x0053:
            if (r3 == 0) goto L_0x0058
            r3.close()     // Catch:{ Exception -> 0x008f }
        L_0x0058:
            r0 = -1
            monitor-exit(r4)     // Catch:{ all -> 0x005b }
            goto L_0x004d
        L_0x005b:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005b }
            throw r0
        L_0x005e:
            r0 = move-exception
            r1 = r2
        L_0x0060:
            r0.printStackTrace()     // Catch:{ all -> 0x0085 }
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ Exception -> 0x006e }
        L_0x0068:
            if (r2 == 0) goto L_0x0058
            r2.close()     // Catch:{ Exception -> 0x006e }
            goto L_0x0058
        L_0x006e:
            r0 = move-exception
            goto L_0x0058
        L_0x0070:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0073:
            if (r1 == 0) goto L_0x0078
            r1.close()     // Catch:{ Exception -> 0x007e }
        L_0x0078:
            if (r3 == 0) goto L_0x007d
            r3.close()     // Catch:{ Exception -> 0x007e }
        L_0x007d:
            throw r0     // Catch:{ all -> 0x005b }
        L_0x007e:
            r1 = move-exception
            goto L_0x007d
        L_0x0080:
            r0 = move-exception
            r1 = r2
            goto L_0x0073
        L_0x0083:
            r0 = move-exception
            goto L_0x0073
        L_0x0085:
            r0 = move-exception
            r3 = r2
            goto L_0x0073
        L_0x0088:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0060
        L_0x008c:
            r0 = move-exception
            r2 = r3
            goto L_0x0060
        L_0x008f:
            r0 = move-exception
            goto L_0x0058
        L_0x0091:
            r1 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.b(java.io.File):int");
    }
}
