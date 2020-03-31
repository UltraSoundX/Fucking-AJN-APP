package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.os.Environment;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.mid.core.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* compiled from: ProGuard */
public class b extends h {
    public b(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return f.a(this.b, Constants.PERMISSION_WRITE_EXTERNAL_STORAGE) && Environment.getExternalStorageState().equals("mounted");
    }

    /* access modifiers changed from: protected */
    public String c() {
        String str;
        String str2 = null;
        synchronized (this) {
            this.a.b((Object) "read mid from InternalStorage");
            File file = new File(Environment.getExternalStorageDirectory(), e());
            if (file != null) {
                try {
                    Iterator it = a.a(file).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str = null;
                            break;
                        }
                        String[] split = ((String) it.next()).split(StorageInterface.KEY_SPLITER);
                        if (split.length == 2 && split[0].equals(f())) {
                            this.a.b((Object) "read mid from InternalStorage:" + split[1]);
                            str = split[1];
                            break;
                        }
                    }
                    str2 = str;
                } catch (IOException e) {
                    this.a.d(e.toString());
                }
            }
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b A[SYNTHETIC, Splitter:B:20:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0085 A[SYNTHETIC, Splitter:B:26:0x0085] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0088=Splitter:B:28:0x0088, B:12:0x006c=Splitter:B:12:0x006c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.tencent.android.tpush.stat.a.d r0 = r4.a     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = "write mid to InternalStorage"
            r0.b(r1)     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r0.<init>()     // Catch:{ all -> 0x0089 }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = "/"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = r4.d()     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0089 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0089 }
            com.tencent.android.tpush.stat.b.a.a(r0)     // Catch:{ all -> 0x0089 }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0089 }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x0089 }
            java.lang.String r2 = r4.e()     // Catch:{ all -> 0x0089 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x006c
            r2 = 0
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ IOException -> 0x006e, all -> 0x0081 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x006e, all -> 0x0081 }
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x006e, all -> 0x0081 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x006e, all -> 0x0081 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0092 }
            r0.<init>()     // Catch:{ IOException -> 0x0092 }
            java.lang.String r2 = r4.f()     // Catch:{ IOException -> 0x0092 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ IOException -> 0x0092 }
            java.lang.String r2 = ","
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ IOException -> 0x0092 }
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ IOException -> 0x0092 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0092 }
            r1.write(r0)     // Catch:{ IOException -> 0x0092 }
            java.lang.String r0 = "\n"
            r1.write(r0)     // Catch:{ IOException -> 0x0092 }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ Exception -> 0x008c }
        L_0x006c:
            monitor-exit(r4)     // Catch:{ all -> 0x0089 }
            return
        L_0x006e:
            r0 = move-exception
            r1 = r2
        L_0x0070:
            com.tencent.android.tpush.stat.a.d r2 = r4.a     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0090 }
            r2.d(r0)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ Exception -> 0x007f }
            goto L_0x006c
        L_0x007f:
            r0 = move-exception
            goto L_0x006c
        L_0x0081:
            r0 = move-exception
            r1 = r2
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            r1.close()     // Catch:{ Exception -> 0x008e }
        L_0x0088:
            throw r0     // Catch:{ all -> 0x0089 }
        L_0x0089:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0089 }
            throw r0
        L_0x008c:
            r0 = move-exception
            goto L_0x006c
        L_0x008e:
            r1 = move-exception
            goto L_0x0088
        L_0x0090:
            r0 = move-exception
            goto L_0x0083
        L_0x0092:
            r0 = move-exception
            goto L_0x0070
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.stat.b.b.a(java.lang.String):void");
    }
}
