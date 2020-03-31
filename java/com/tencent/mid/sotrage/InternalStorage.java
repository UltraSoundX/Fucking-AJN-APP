package com.tencent.mid.sotrage;

import android.content.Context;
import android.os.Environment;
import com.tencent.mid.core.Constants;
import com.tencent.mid.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class InternalStorage extends StorageInterface {
    public InternalStorage(Context context, int i) {
        super(context, i);
    }

    public int getType() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public boolean checkPermission() {
        try {
            if (!Util.checkPermission(this.context, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) || !"mounted".equals(Environment.getExternalStorageState())) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            logger.i("checkPermission " + th);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public String read() {
        String str;
        String str2 = null;
        synchronized (this) {
            logger.i("read mid from InternalStorage  version code = 4.07");
            File file = new File(Environment.getExternalStorageDirectory(), getMidFilePath());
            if (file != null) {
                try {
                    Iterator it = FileHelper.readEntireFileLines(file).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str = null;
                            break;
                        }
                        String[] split = ((String) it.next()).split(StorageInterface.KEY_SPLITER);
                        if (split.length == 2 && split[0].equals(getStorageKey())) {
                            logger.i("read mid from InternalStorage:" + split[1]);
                            str = split[1];
                            break;
                        }
                    }
                    str2 = str;
                } catch (IOException e) {
                }
            }
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0072 A[SYNTHETIC, Splitter:B:18:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007b A[SYNTHETIC, Splitter:B:23:0x007b] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x006c=Splitter:B:12:0x006c, B:25:0x007e=Splitter:B:25:0x007e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.tencent.mid.util.Logger r0 = logger     // Catch:{ all -> 0x007f }
            java.lang.String r1 = "write mid to InternalStorage"
            r0.i(r1)     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            r0.<init>()     // Catch:{ all -> 0x007f }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x007f }
            java.lang.String r1 = "/"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x007f }
            java.lang.String r1 = r4.getMidDir()     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x007f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x007f }
            com.tencent.mid.sotrage.FileHelper.createDir(r0)     // Catch:{ all -> 0x007f }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x007f }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x007f }
            java.lang.String r2 = r4.getMidFilePath()     // Catch:{ all -> 0x007f }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x006c
            r1 = 0
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ IOException -> 0x006e, all -> 0x0078 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x006e, all -> 0x0078 }
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x006e, all -> 0x0078 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x006e, all -> 0x0078 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            r1.<init>()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r2 = r4.getStorageKey()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r2 = ","
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            r0.write(r1)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r1 = "\n"
            r0.write(r1)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            if (r0 == 0) goto L_0x006c
            r0.close()     // Catch:{ Exception -> 0x0082 }
        L_0x006c:
            monitor-exit(r4)     // Catch:{ all -> 0x007f }
            return
        L_0x006e:
            r0 = move-exception
            r0 = r1
        L_0x0070:
            if (r0 == 0) goto L_0x006c
            r0.close()     // Catch:{ Exception -> 0x0076 }
            goto L_0x006c
        L_0x0076:
            r0 = move-exception
            goto L_0x006c
        L_0x0078:
            r0 = move-exception
        L_0x0079:
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ Exception -> 0x0084 }
        L_0x007e:
            throw r0     // Catch:{ all -> 0x007f }
        L_0x007f:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x007f }
            throw r0
        L_0x0082:
            r0 = move-exception
            goto L_0x006c
        L_0x0084:
            r1 = move-exception
            goto L_0x007e
        L_0x0086:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0079
        L_0x008b:
            r1 = move-exception
            goto L_0x0070
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.sotrage.InternalStorage.write(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d A[SYNTHETIC, Splitter:B:19:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0056 A[SYNTHETIC, Splitter:B:24:0x0056] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0047=Splitter:B:13:0x0047, B:26:0x0059=Splitter:B:26:0x0059} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005a }
            r0.<init>()     // Catch:{ all -> 0x005a }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x005a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x005a }
            java.lang.String r1 = "/"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x005a }
            java.lang.String r1 = r4.getMidDir()     // Catch:{ all -> 0x005a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x005a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005a }
            com.tencent.mid.sotrage.FileHelper.createDir(r0)     // Catch:{ all -> 0x005a }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x005a }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x005a }
            java.lang.String r2 = r4.getMidFilePath()     // Catch:{ all -> 0x005a }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0047
            r1 = 0
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ IOException -> 0x0049, all -> 0x0053 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0049, all -> 0x0053 }
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0049, all -> 0x0053 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0049, all -> 0x0053 }
            java.lang.String r1 = ""
            r0.write(r1)     // Catch:{ IOException -> 0x0066, all -> 0x0061 }
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ Exception -> 0x005d }
        L_0x0047:
            monitor-exit(r4)     // Catch:{ all -> 0x005a }
            return
        L_0x0049:
            r0 = move-exception
            r0 = r1
        L_0x004b:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0047
        L_0x0051:
            r0 = move-exception
            goto L_0x0047
        L_0x0053:
            r0 = move-exception
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ Exception -> 0x005f }
        L_0x0059:
            throw r0     // Catch:{ all -> 0x005a }
        L_0x005a:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005a }
            throw r0
        L_0x005d:
            r0 = move-exception
            goto L_0x0047
        L_0x005f:
            r1 = move-exception
            goto L_0x0059
        L_0x0061:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0054
        L_0x0066:
            r1 = move-exception
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.sotrage.InternalStorage.clear():void");
    }

    /* access modifiers changed from: protected */
    public CheckEntity readCheckEntityIner() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void writeCheckEntityIner(CheckEntity checkEntity) {
    }
}
