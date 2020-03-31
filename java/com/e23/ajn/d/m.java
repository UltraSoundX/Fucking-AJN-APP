package com.e23.ajn.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/* compiled from: ImageUtils */
public class m {
    public static void a(final Context context) {
        new c() {
            public void a() {
                m.a(context, "ic_launcher.png", "cn_e23_love_jinan_logo.png");
            }
        }.start();
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064 A[SYNTHETIC, Splitter:B:26:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069 A[Catch:{ IOException -> 0x006d }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0087 A[SYNTHETIC, Splitter:B:43:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008c A[Catch:{ IOException -> 0x0090 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
        /*
            r2 = 0
            r0 = 0
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r1]
            android.content.res.AssetManager r1 = r7.getAssets()     // Catch:{ IOException -> 0x009e, all -> 0x0083 }
            java.io.InputStream r3 = r1.open(r8)     // Catch:{ IOException -> 0x009e, all -> 0x0083 }
            java.lang.String r1 = com.e23.ajn.d.h.g()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            if (r1 == 0) goto L_0x0072
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            r1.<init>()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.String r6 = com.e23.ajn.d.h.g()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.String r6 = java.io.File.separator     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.StringBuilder r1 = r1.append(r9)     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            boolean r1 = r5.exists()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            if (r1 == 0) goto L_0x004a
            if (r3 == 0) goto L_0x003f
            r3.close()     // Catch:{ IOException -> 0x0045 }
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r0.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0044:
            return
        L_0x0045:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0044
        L_0x004a:
            r5.createNewFile()     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x00a1, all -> 0x0095 }
        L_0x0052:
            int r0 = r3.read(r4)     // Catch:{ IOException -> 0x005d, all -> 0x0097 }
            if (r0 <= 0) goto L_0x0073
            r2 = 0
            r1.write(r4, r2, r0)     // Catch:{ IOException -> 0x005d, all -> 0x0097 }
            goto L_0x0052
        L_0x005d:
            r0 = move-exception
            r2 = r3
        L_0x005f:
            r0.printStackTrace()     // Catch:{ all -> 0x009a }
            if (r2 == 0) goto L_0x0067
            r2.close()     // Catch:{ IOException -> 0x006d }
        L_0x0067:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0044
        L_0x006d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0044
        L_0x0072:
            r1 = r2
        L_0x0073:
            if (r3 == 0) goto L_0x0078
            r3.close()     // Catch:{ IOException -> 0x007e }
        L_0x0078:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0044
        L_0x007e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0044
        L_0x0083:
            r0 = move-exception
            r3 = r2
        L_0x0085:
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch:{ IOException -> 0x0090 }
        L_0x008a:
            if (r2 == 0) goto L_0x008f
            r2.close()     // Catch:{ IOException -> 0x0090 }
        L_0x008f:
            throw r0
        L_0x0090:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x008f
        L_0x0095:
            r0 = move-exception
            goto L_0x0085
        L_0x0097:
            r0 = move-exception
            r2 = r1
            goto L_0x0085
        L_0x009a:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x0085
        L_0x009e:
            r0 = move-exception
            r1 = r2
            goto L_0x005f
        L_0x00a1:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e23.ajn.d.m.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static Bitmap a(Context context, int i) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
    }
}
