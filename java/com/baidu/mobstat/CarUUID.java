package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.system.Os;
import com.tencent.mid.core.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class CarUUID {
    private static final Pattern a = Pattern.compile("(\\w{32})");

    public static String optUUID(Context context) {
        String b = b(context);
        if (b != null) {
            return b;
        }
        String c = c(context);
        if (c != null) {
            a(context, c);
            return c;
        }
        String d = d(context);
        if (d != null) {
            a(context, d);
            b(context, d);
            return d;
        }
        String a2 = a(context);
        if (a2 == null) {
            return "";
        }
        a(context, a2);
        b(context, a2);
        return a2;
    }

    private static String a(Context context) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String b(Context context) {
        return a(context.getFileStreamPath("libdueros_uuid.so"));
    }

    private static String c(Context context) {
        if (!c(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return null;
        }
        return a(new File(new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig"), ".dueros_uuid"));
    }

    private static String d(Context context) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        for (ApplicationInfo applicationInfo2 : installedApplications) {
            if (!applicationInfo.packageName.equals(applicationInfo2.packageName)) {
                String a2 = a(new File(new File(applicationInfo2.dataDir, "files"), "libdueros_uuid.so"));
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        com.baidu.mobstat.az.a(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052 A[ExcHandler: all (r0v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = 21
            r0 = 1
            r1 = 0
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x004c, all -> 0x0052 }
            if (r3 < r5) goto L_0x003f
            r3 = r1
        L_0x000a:
            java.lang.String r4 = "libdueros_uuid.so"
            java.io.FileOutputStream r2 = r6.openFileOutput(r4, r3)     // Catch:{ Exception -> 0x004c, all -> 0x0052 }
            boolean r3 = a(r2, r7)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            if (r3 == 0) goto L_0x0047
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            if (r3 < r5) goto L_0x0043
            android.content.pm.ApplicationInfo r3 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            java.lang.String r4 = "libdueros_uuid.so"
            java.io.File r4 = r6.getFileStreamPath(r4)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            java.lang.String r3 = r3.dataDir     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            r3 = 457(0x1c9, float:6.4E-43)
            boolean r3 = a(r5, r3)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            if (r3 == 0) goto L_0x0041
            r3 = 484(0x1e4, float:6.78E-43)
            boolean r3 = a(r4, r3)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            if (r3 == 0) goto L_0x0041
        L_0x003b:
            com.baidu.mobstat.az.a(r2)
        L_0x003e:
            return r0
        L_0x003f:
            r3 = r0
            goto L_0x000a
        L_0x0041:
            r0 = r1
            goto L_0x003b
        L_0x0043:
            com.baidu.mobstat.az.a(r2)
            goto L_0x003e
        L_0x0047:
            com.baidu.mobstat.az.a(r2)
        L_0x004a:
            r0 = r1
            goto L_0x003e
        L_0x004c:
            r0 = move-exception
            r0 = r2
        L_0x004e:
            com.baidu.mobstat.az.a(r0)
            goto L_0x004a
        L_0x0052:
            r0 = move-exception
            com.baidu.mobstat.az.a(r2)
            throw r0
        L_0x0057:
            r0 = move-exception
            r0 = r2
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.CarUUID.a(android.content.Context, java.lang.String):boolean");
    }

    private static boolean b(Context context, String str) {
        FileOutputStream fileOutputStream;
        boolean z = false;
        if (c(context, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(new File(new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig"), ".dueros_uuid"));
                try {
                    z = a(fileOutputStream, str);
                    az.a(fileOutputStream);
                } catch (Exception e) {
                    az.a(fileOutputStream);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream2 = fileOutputStream;
                    az.a(fileOutputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                fileOutputStream = null;
                az.a(fileOutputStream);
                return z;
            } catch (Throwable th2) {
                th = th2;
                az.a(fileOutputStream2);
                throw th;
            }
        }
        return z;
    }

    private static String a(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    String str = new String(bArr, 0, fileInputStream.read(bArr));
                    if (!a.matcher(str).matches()) {
                        str = null;
                    }
                    az.a(fileInputStream);
                    return str;
                } catch (Exception e) {
                    fileInputStream2 = fileInputStream;
                } catch (Throwable th) {
                    th = th;
                    az.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                fileInputStream2 = null;
                az.a(fileInputStream2);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                az.a(fileInputStream);
                throw th;
            }
        }
        return null;
    }

    private static boolean a(FileOutputStream fileOutputStream, String str) {
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    private static boolean a(File file, int i) {
        if (VERSION.SDK_INT < 21) {
            return true;
        }
        try {
            Os.chmod(file.getAbsolutePath(), i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean c(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
