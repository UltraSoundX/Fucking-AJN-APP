package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.mid.core.Constants;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.ZipEntry;

@SuppressLint({"NewApi"})
/* compiled from: FileUtil */
public class f {
    public static String a = null;
    public static final a b = new a() {
        public boolean a(File file, File file2) {
            return file.length() == file2.length() && file.lastModified() == file2.lastModified();
        }
    };
    private static final int c = "lib/".length();
    private static RandomAccessFile d = null;

    /* compiled from: FileUtil */
    public interface a {
        boolean a(File file, File file2);
    }

    /* compiled from: FileUtil */
    public interface b {
        boolean a(InputStream inputStream, ZipEntry zipEntry, String str) throws Exception;
    }

    public static String a(Context context, int i) {
        return a(context, context.getApplicationInfo().packageName, i, true);
    }

    public static String a(Context context, String str, int i, boolean z) {
        String str2;
        if (context == null) {
            return "";
        }
        String str3 = "";
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
            str2 = str3;
        }
        switch (i) {
            case 1:
                if (!str2.equals("")) {
                    return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
                }
                return str2;
            case 2:
                if (!str2.equals("")) {
                    return str2 + "tbs" + File.separator + "backup" + File.separator + str;
                }
                return str2;
            case 3:
                if (!str2.equals("")) {
                    return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                }
                return str2;
            case 4:
                if (str2.equals("")) {
                    return b(context, "backup");
                }
                String str4 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str4;
                }
                File file = new File(str4);
                if (file.exists() && file.canWrite()) {
                    return str4;
                }
                if (file.exists()) {
                    return b(context, "backup");
                }
                file.mkdirs();
                if (!file.canWrite()) {
                    return b(context, "backup");
                }
                return str4;
            case 5:
                if (!str2.equals("")) {
                    return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
                }
                return str2;
            case 6:
                if (a != null) {
                    return a;
                }
                a = b(context, "tbslog");
                return a;
            case 7:
                if (!str2.equals("")) {
                    return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + "core";
                }
                return str2;
            default:
                return "";
        }
    }

    private static String b(Context context, String str) {
        String str2 = "";
        if (context == null) {
            return str2;
        }
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (origApplicationContext != null) {
            context = origApplicationContext;
        }
        try {
            return context.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + context.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static boolean a(Context context) {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        if (context == null) {
            return false;
        }
        if (StubApp.getOrigApplicationContext(context.getApplicationContext()).checkSelfPermission(Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) != 0) {
            return false;
        }
        return true;
    }

    public static boolean a(File file, File file2) throws Exception {
        return a(file.getPath(), file2.getPath());
    }

    @SuppressLint({"InlinedApi"})
    public static boolean a(String str, String str2) throws Exception {
        return a(str, str2, Build.CPU_ABI, VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null, j.a("ro.product.cpu.upgradeabi", "armeabi"));
    }

    private static boolean a(String str, final String str2, String str3, String str4, String str5) throws Exception {
        return a(str, str3, str4, str5, (b) new b() {
            public boolean a(InputStream inputStream, ZipEntry zipEntry, String str) throws Exception {
                try {
                    return f.b(inputStream, zipEntry, str2, str);
                } catch (Exception e) {
                    throw new Exception("copyFileIfChanged Exception", e);
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, com.tencent.smtt.utils.f.b r14) throws java.lang.Exception {
        /*
            r2 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch:{ all -> 0x00e3 }
            r1.<init>(r10)     // Catch:{ all -> 0x00e3 }
            r3 = 0
            r2 = 0
            java.util.Enumeration r4 = r1.entries()     // Catch:{ all -> 0x00ce }
        L_0x000c:
            boolean r0 = r4.hasMoreElements()     // Catch:{ all -> 0x00ce }
            if (r0 == 0) goto L_0x00dc
            java.lang.Object r0 = r4.nextElement()     // Catch:{ all -> 0x00ce }
            java.util.zip.ZipEntry r0 = (java.util.zip.ZipEntry) r0     // Catch:{ all -> 0x00ce }
            java.lang.String r5 = r0.getName()     // Catch:{ all -> 0x00ce }
            if (r5 == 0) goto L_0x000c
            java.lang.String r6 = "../"
            boolean r6 = r5.contains(r6)     // Catch:{ all -> 0x00ce }
            if (r6 != 0) goto L_0x000c
            java.lang.String r6 = "lib/"
            boolean r6 = r5.startsWith(r6)     // Catch:{ all -> 0x00ce }
            if (r6 != 0) goto L_0x0036
            java.lang.String r6 = "assets/"
            boolean r6 = r5.startsWith(r6)     // Catch:{ all -> 0x00ce }
            if (r6 == 0) goto L_0x000c
        L_0x0036:
            r6 = 47
            int r6 = r5.lastIndexOf(r6)     // Catch:{ all -> 0x00ce }
            java.lang.String r6 = r5.substring(r6)     // Catch:{ all -> 0x00ce }
            java.lang.String r7 = ".so"
            boolean r7 = r6.endsWith(r7)     // Catch:{ all -> 0x00ce }
            if (r7 == 0) goto L_0x0065
            int r7 = c     // Catch:{ all -> 0x00ce }
            r8 = 0
            int r9 = r11.length()     // Catch:{ all -> 0x00ce }
            boolean r7 = r5.regionMatches(r7, r11, r8, r9)     // Catch:{ all -> 0x00ce }
            if (r7 == 0) goto L_0x0080
            int r7 = c     // Catch:{ all -> 0x00ce }
            int r8 = r11.length()     // Catch:{ all -> 0x00ce }
            int r7 = r7 + r8
            char r7 = r5.charAt(r7)     // Catch:{ all -> 0x00ce }
            r8 = 47
            if (r7 != r8) goto L_0x0080
            r3 = 1
        L_0x0065:
            java.io.InputStream r5 = r1.getInputStream(r0)     // Catch:{ all -> 0x00ce }
            r7 = 1
            java.lang.String r6 = r6.substring(r7)     // Catch:{ all -> 0x00d5 }
            boolean r0 = r14.a(r5, r0, r6)     // Catch:{ all -> 0x00d5 }
            if (r0 != 0) goto L_0x00c7
            r0 = 0
            if (r5 == 0) goto L_0x007a
            r5.close()     // Catch:{ all -> 0x00ce }
        L_0x007a:
            if (r1 == 0) goto L_0x007f
            r1.close()
        L_0x007f:
            return r0
        L_0x0080:
            if (r12 == 0) goto L_0x00a3
            int r7 = c     // Catch:{ all -> 0x00ce }
            r8 = 0
            int r9 = r12.length()     // Catch:{ all -> 0x00ce }
            boolean r7 = r5.regionMatches(r7, r12, r8, r9)     // Catch:{ all -> 0x00ce }
            if (r7 == 0) goto L_0x00a3
            int r7 = c     // Catch:{ all -> 0x00ce }
            int r8 = r12.length()     // Catch:{ all -> 0x00ce }
            int r7 = r7 + r8
            char r7 = r5.charAt(r7)     // Catch:{ all -> 0x00ce }
            r8 = 47
            if (r7 != r8) goto L_0x00a3
            r2 = 1
            if (r3 == 0) goto L_0x0065
            goto L_0x000c
        L_0x00a3:
            if (r13 == 0) goto L_0x000c
            int r7 = c     // Catch:{ all -> 0x00ce }
            r8 = 0
            int r9 = r13.length()     // Catch:{ all -> 0x00ce }
            boolean r7 = r5.regionMatches(r7, r13, r8, r9)     // Catch:{ all -> 0x00ce }
            if (r7 == 0) goto L_0x000c
            int r7 = c     // Catch:{ all -> 0x00ce }
            int r8 = r13.length()     // Catch:{ all -> 0x00ce }
            int r7 = r7 + r8
            char r5 = r5.charAt(r7)     // Catch:{ all -> 0x00ce }
            r7 = 47
            if (r5 != r7) goto L_0x000c
            if (r3 != 0) goto L_0x000c
            if (r2 == 0) goto L_0x0065
            goto L_0x000c
        L_0x00c7:
            if (r5 == 0) goto L_0x000c
            r5.close()     // Catch:{ all -> 0x00ce }
            goto L_0x000c
        L_0x00ce:
            r0 = move-exception
        L_0x00cf:
            if (r1 == 0) goto L_0x00d4
            r1.close()
        L_0x00d4:
            throw r0
        L_0x00d5:
            r0 = move-exception
            if (r5 == 0) goto L_0x00db
            r5.close()     // Catch:{ all -> 0x00ce }
        L_0x00db:
            throw r0     // Catch:{ all -> 0x00ce }
        L_0x00dc:
            if (r1 == 0) goto L_0x00e1
            r1.close()
        L_0x00e1:
            r0 = 1
            goto L_0x007f
        L_0x00e3:
            r0 = move-exception
            r1 = r2
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.f.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.f$b):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.io.InputStream r9, java.util.zip.ZipEntry r10, java.lang.String r11, java.lang.String r12) throws java.lang.Exception {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File
            r1.<init>(r11)
            a(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r2 = java.io.File.separator
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.io.File r8 = new java.io.File
            r8.<init>(r1)
            r3 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00b9, all -> 0x00b3 }
            r2.<init>(r8)     // Catch:{ IOException -> 0x00b9, all -> 0x00b3 }
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x003a, all -> 0x00b6 }
        L_0x002f:
            int r4 = r9.read(r3)     // Catch:{ IOException -> 0x003a, all -> 0x00b6 }
            if (r4 <= 0) goto L_0x005f
            r5 = 0
            r2.write(r3, r5, r4)     // Catch:{ IOException -> 0x003a, all -> 0x00b6 }
            goto L_0x002f
        L_0x003a:
            r0 = move-exception
            r1 = r2
        L_0x003c:
            b(r8)     // Catch:{ all -> 0x0058 }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0058 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
            r3.<init>()     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = "Couldn't write dst file "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0058 }
            java.lang.StringBuilder r3 = r3.append(r8)     // Catch:{ all -> 0x0058 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0058 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0058 }
            throw r2     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r0 = move-exception
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()
        L_0x005e:
            throw r0
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()
        L_0x0064:
            long r2 = r10.getSize()
            long r4 = r10.getTime()
            long r6 = r10.getCrc()
            boolean r2 = a(r1, r2, r4, r6)
            if (r2 == 0) goto L_0x008f
            java.lang.String r2 = "FileHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "file is different: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r1)
        L_0x008e:
            return r0
        L_0x008f:
            long r0 = r10.getTime()
            boolean r0 = r8.setLastModified(r0)
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = "FileHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Couldn't set time for dst file "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
        L_0x00b1:
            r0 = 1
            goto L_0x008e
        L_0x00b3:
            r0 = move-exception
            r1 = r3
            goto L_0x0059
        L_0x00b6:
            r0 = move-exception
            r1 = r2
            goto L_0x0059
        L_0x00b9:
            r0 = move-exception
            r1 = r3
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.f.b(java.io.InputStream, java.util.zip.ZipEntry, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r9, long r10, long r12, long r14) throws java.lang.Exception {
        /*
            r0 = 1
            r1 = 0
            java.io.File r4 = new java.io.File
            r4.<init>(r9)
            long r2 = r4.length()
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0036
            java.lang.String r1 = "FileHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "file size doesn't match: "
            java.lang.StringBuilder r2 = r2.append(r3)
            long r4 = r4.length()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r3 = " vs "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r2)
        L_0x0035:
            return r0
        L_0x0036:
            r3 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x009d }
            r2.<init>(r4)     // Catch:{ all -> 0x009d }
            java.util.zip.CRC32 r3 = new java.util.zip.CRC32     // Catch:{ all -> 0x0050 }
            r3.<init>()     // Catch:{ all -> 0x0050 }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0050 }
        L_0x0045:
            int r6 = r2.read(r5)     // Catch:{ all -> 0x0050 }
            if (r6 <= 0) goto L_0x0058
            r7 = 0
            r3.update(r5, r7, r6)     // Catch:{ all -> 0x0050 }
            goto L_0x0045
        L_0x0050:
            r0 = move-exception
            r1 = r2
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r1.close()
        L_0x0057:
            throw r0
        L_0x0058:
            long r6 = r3.getValue()     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = "FileHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0050 }
            r5.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r8 = ""
            java.lang.StringBuilder r5 = r5.append(r8)     // Catch:{ all -> 0x0050 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0050 }
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = ": crc = "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = ", zipCrc = "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ all -> 0x0050 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0050 }
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ all -> 0x0050 }
            int r3 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r3 == 0) goto L_0x0096
            if (r2 == 0) goto L_0x0035
            r2.close()
            goto L_0x0035
        L_0x0096:
            if (r2 == 0) goto L_0x009b
            r2.close()
        L_0x009b:
            r0 = r1
            goto L_0x0035
        L_0x009d:
            r0 = move-exception
            r1 = r3
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.f.a(java.lang.String, long, long, long):boolean");
    }

    public static boolean b(File file, File file2) throws Exception {
        return a(file, file2, (FileFilter) null);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) throws Exception {
        return a(file, file2, fileFilter, b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) throws Exception {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles(fileFilter);
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    private static boolean b(File file, File file2, FileFilter fileFilter, a aVar) throws Exception {
        FileChannel fileChannel;
        FileChannel fileChannel2 = null;
        if (file == null || file2 == null) {
            return false;
        }
        if (fileFilter != null && !fileFilter.accept(file)) {
            return false;
        }
        try {
            if (!file.exists() || !file.isFile()) {
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                return false;
            }
            if (file2.exists()) {
                if (aVar == null || !aVar.a(file, file2)) {
                    b(file2);
                } else {
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    return true;
                }
            }
            File parentFile = file2.getParentFile();
            if (parentFile.isFile()) {
                b(parentFile);
            }
            if (parentFile.exists() || parentFile.mkdirs()) {
                FileChannel channel = new FileInputStream(file).getChannel();
                try {
                    FileChannel channel2 = new FileOutputStream(file2).getChannel();
                    try {
                        long size = channel.size();
                        if (channel2.transferFrom(channel, 0, size) != size) {
                            b(file2);
                            if (channel != null) {
                                channel.close();
                            }
                            if (channel2 != null) {
                                channel2.close();
                            }
                            return false;
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        if (channel2 != null) {
                            channel2.close();
                        }
                        return true;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        fileChannel2 = channel;
                        fileChannel = channel2;
                        th = th2;
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    FileChannel fileChannel3 = fileChannel2;
                    fileChannel2 = channel;
                    fileChannel = fileChannel3;
                }
            } else {
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                return false;
            }
        } catch (Throwable th4) {
            th = th4;
            fileChannel = fileChannel2;
        }
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        b(file);
        return file.mkdirs();
    }

    public static void b(File file) {
        a(file, false);
    }

    public static void a(File file, boolean z) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a2 : listFiles) {
                    a(a2, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(File file, boolean z, String str) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + "except" + str + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getName().equals(str)) {
                        a(file2, z);
                    }
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static FileOutputStream d(File file) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    public static boolean b(Context context) {
        long a2 = q.a();
        boolean z = a2 >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a2);
        }
        return z;
    }

    public static String c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    static String d(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file == null) {
            return null;
        }
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static File a(Context context, boolean z, String str) {
        String c2;
        if (z) {
            c2 = d(context);
        } else {
            c2 = c(context);
        }
        if (c2 == null) {
            return null;
        }
        File file = new File(c2);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static File a(Context context, String str) {
        File file = new File(context.getFilesDir(), "tbs");
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            TbsLog.e("FileHelper", "getPermanentTbsFile -- no permission!");
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                TbsLog.e("FileHelper", "getPermanentTbsFile -- exception: " + e);
                return null;
            }
        }
        return file2;
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a2 = a(context, z, str);
        if (a2 != null) {
            try {
                return new FileOutputStream(a2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return null;
        }
        try {
            FileLock tryLock = fileOutputStream.getChannel().tryLock();
            if (tryLock.isValid()) {
                return tryLock;
            }
            return null;
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                FileChannel channel = fileLock.channel();
                if (channel != null && channel.isOpen()) {
                    fileLock.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static FileLock e(Context context) {
        boolean z;
        FileLock fileLock = null;
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #1");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable th) {
            z = true;
        }
        if (!z) {
            FileOutputStream b2 = b(context, true, "tbs_rename_lock");
            if (b2 == null) {
                TbsLog.i("FileHelper", "init -- failed to get rename fileLock#1!");
            } else {
                fileLock = a(context, b2);
                if (fileLock == null) {
                    TbsLog.i("FileHelper", "init -- failed to get rename fileLock#2!");
                } else {
                    TbsLog.i("FileHelper", "init -- get rename fileLock success!");
                }
            }
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #2 renameFileLock is " + fileLock);
        } else {
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #3");
            String str = "tbs_rename_lock";
            File a2 = a(context, str);
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #4 " + a2);
            try {
                d = new RandomAccessFile(a2.getAbsolutePath(), "r");
                fileLock = d.getChannel().tryLock(0, Long.MAX_VALUE, true);
            } catch (Throwable th2) {
                TbsLog.e("FileHelper", "getTbsCoreLoadFileLock -- exception: " + th2);
            }
            if (fileLock == null) {
                fileLock = g(context);
            }
            if (fileLock == null) {
                TbsLog.i("FileHelper", "getTbsCoreLoadFileLock -- failed: " + str);
            } else {
                TbsLog.i("FileHelper", "getTbsCoreLoadFileLock -- success: " + str);
            }
        }
        return fileLock;
    }

    private static FileLock g(Context context) {
        boolean z;
        FileLock fileLock = null;
        try {
            TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.setErrorCode(803);
            File a2 = a(context, "tbs_rename_lock");
            if (TbsDownloadConfig.getInstance(context).getTbsCoreLoadRenameFileLockWaitEnable()) {
                int i = 0;
                while (i < 20 && fileLock == null) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    d = new RandomAccessFile(a2.getAbsolutePath(), "r");
                    fileLock = d.getChannel().tryLock(0, Long.MAX_VALUE, true);
                    i++;
                }
                if (fileLock != null) {
                    tbsLogInfo.setErrorCode(802);
                } else {
                    tbsLogInfo.setErrorCode(801);
                }
                TbsLogReport.getInstance(context).eventReport(EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
                String str = "FileHelper";
                StringBuilder append = new StringBuilder().append("getTbsCoreLoadFileLock,retry num=").append(i).append("success=");
                if (fileLock == null) {
                    z = true;
                } else {
                    z = false;
                }
                TbsLog.i(str, append.append(z).toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return fileLock;
    }

    public static FileLock f(Context context) {
        FileLock fileLock = null;
        String str = "tbs_rename_lock";
        File a2 = a(context, str);
        TbsLog.i("FileHelper", "getTbsCoreRenameFileLock #1 " + a2);
        try {
            d = new RandomAccessFile(a2.getAbsolutePath(), "rw");
            fileLock = d.getChannel().tryLock(0, Long.MAX_VALUE, false);
        } catch (Throwable th) {
            TbsLog.e("FileHelper", "getTbsCoreRenameFileLock -- excpetion: " + str);
        }
        if (fileLock == null) {
            TbsLog.i("FileHelper", "getTbsCoreRenameFileLock -- failed: " + str);
        } else {
            TbsLog.i("FileHelper", "getTbsCoreRenameFileLock -- success: " + str);
        }
        return fileLock;
    }

    public static synchronized void a(Context context, FileLock fileLock) {
        synchronized (f.class) {
            TbsLog.i("FileHelper", "releaseTbsCoreRenameFileLock -- lock: " + fileLock);
            FileChannel channel = fileLock.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    fileLock.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}
