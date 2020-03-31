package com.nanchen.compresshelper;

import android.content.Context;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* compiled from: BitmapUtil */
public class a {
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Bitmap a(android.content.Context r9, android.net.Uri r10, float r11, float r12, android.graphics.Bitmap.Config r13) {
        /*
            java.lang.String r5 = com.nanchen.compresshelper.c.b(r9, r10)
            r3 = 0
            android.graphics.BitmapFactory$Options r6 = new android.graphics.BitmapFactory$Options
            r6.<init>()
            r0 = 1
            r6.inJustDecodeBounds = r0
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeFile(r5, r6)
            if (r4 != 0) goto L_0x001f
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x009c }
            r0.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x009c }
            r1 = 0
            android.graphics.BitmapFactory.decodeStream(r0, r1, r6)     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x009c }
            r0.close()     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x009c }
        L_0x001f:
            int r1 = r6.outHeight
            int r2 = r6.outWidth
            r0 = -1
            if (r1 == r0) goto L_0x0029
            r0 = -1
            if (r2 != r0) goto L_0x00a6
        L_0x0029:
            android.media.ExifInterface r0 = new android.media.ExifInterface     // Catch:{ IOException -> 0x00a2 }
            r0.<init>(r5)     // Catch:{ IOException -> 0x00a2 }
            java.lang.String r7 = "ImageLength"
            r8 = 1
            int r1 = r0.getAttributeInt(r7, r8)     // Catch:{ IOException -> 0x00a2 }
            java.lang.String r7 = "ImageWidth"
            r8 = 1
            int r0 = r0.getAttributeInt(r7, r8)     // Catch:{ IOException -> 0x00a2 }
        L_0x003c:
            if (r0 <= 0) goto L_0x0040
            if (r1 > 0) goto L_0x004e
        L_0x0040:
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeFile(r5)
            if (r1 == 0) goto L_0x00a8
            int r0 = r1.getWidth()
            int r1 = r1.getHeight()
        L_0x004e:
            float r2 = (float) r0
            float r7 = (float) r1
            float r2 = r2 / r7
            float r7 = r11 / r12
            float r8 = (float) r1
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 > 0) goto L_0x005d
            float r8 = (float) r0
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 <= 0) goto L_0x013b
        L_0x005d:
            int r8 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r8 >= 0) goto L_0x00aa
            float r1 = (float) r1
            float r1 = r12 / r1
            float r0 = (float) r0
            float r0 = r0 * r1
            int r0 = (int) r0
            int r1 = (int) r12
            r2 = r1
            r1 = r0
        L_0x006a:
            int r0 = a(r6, r1, r2)
            r6.inSampleSize = r0
            r0 = 0
            r6.inJustDecodeBounds = r0
            r0 = 1
            r6.inPurgeable = r0
            r0 = 1
            r6.inInputShareable = r0
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r0 = new byte[r0]
            r6.inTempStorage = r0
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeFile(r5, r6)     // Catch:{ OutOfMemoryError -> 0x00c2 }
            if (r4 != 0) goto L_0x0091
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00bd }
            r0.<init>(r5)     // Catch:{ IOException -> 0x00bd }
            r7 = 0
            android.graphics.BitmapFactory.decodeStream(r0, r7, r6)     // Catch:{ IOException -> 0x00bd }
            r0.close()     // Catch:{ IOException -> 0x00bd }
        L_0x0091:
            if (r2 <= 0) goto L_0x0095
            if (r1 > 0) goto L_0x00c7
        L_0x0095:
            r0 = 0
        L_0x0096:
            return r0
        L_0x0097:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001f
        L_0x009c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001f
        L_0x00a2:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a6:
            r0 = r2
            goto L_0x003c
        L_0x00a8:
            r0 = 0
            goto L_0x0096
        L_0x00aa:
            int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b8
            float r0 = (float) r0
            float r0 = r11 / r0
            float r1 = (float) r1
            float r0 = r0 * r1
            int r1 = (int) r0
            int r0 = (int) r11
            r2 = r1
            r1 = r0
            goto L_0x006a
        L_0x00b8:
            int r1 = (int) r12
            int r0 = (int) r11
            r2 = r1
            r1 = r0
            goto L_0x006a
        L_0x00bd:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ OutOfMemoryError -> 0x00c2 }
            goto L_0x0091
        L_0x00c2:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0091
        L_0x00c7:
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r1, r2, r13)     // Catch:{ OutOfMemoryError -> 0x011c }
        L_0x00cb:
            float r1 = (float) r1
            int r3 = r6.outWidth
            float r3 = (float) r3
            float r1 = r1 / r3
            float r2 = (float) r2
            int r3 = r6.outHeight
            float r3 = (float) r3
            float r2 = r2 / r3
            android.graphics.Matrix r3 = new android.graphics.Matrix
            r3.<init>()
            r6 = 0
            r7 = 0
            r3.setScale(r1, r2, r6, r7)
            android.graphics.Canvas r1 = new android.graphics.Canvas
            r1.<init>(r0)
            r1.setMatrix(r3)
            r2 = 0
            r3 = 0
            android.graphics.Paint r6 = new android.graphics.Paint
            r7 = 2
            r6.<init>(r7)
            r1.drawBitmap(r4, r2, r3, r6)
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch:{ IOException -> 0x012b }
            r1.<init>(r5)     // Catch:{ IOException -> 0x012b }
            java.lang.String r2 = "Orientation"
            r3 = 0
            int r1 = r1.getAttributeInt(r2, r3)     // Catch:{ IOException -> 0x012b }
            android.graphics.Matrix r5 = new android.graphics.Matrix     // Catch:{ IOException -> 0x012b }
            r5.<init>()     // Catch:{ IOException -> 0x012b }
            r2 = 6
            if (r1 != r2) goto L_0x0122
            r1 = 1119092736(0x42b40000, float:90.0)
            r5.postRotate(r1)     // Catch:{ IOException -> 0x012b }
        L_0x010b:
            r1 = 0
            r2 = 0
            int r3 = r0.getWidth()     // Catch:{ IOException -> 0x012b }
            int r4 = r0.getHeight()     // Catch:{ IOException -> 0x012b }
            r6 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x012b }
            goto L_0x0096
        L_0x011c:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r3
            goto L_0x00cb
        L_0x0122:
            r2 = 3
            if (r1 != r2) goto L_0x0131
            r1 = 1127481344(0x43340000, float:180.0)
            r5.postRotate(r1)     // Catch:{ IOException -> 0x012b }
            goto L_0x010b
        L_0x012b:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0096
        L_0x0131:
            r2 = 8
            if (r1 != r2) goto L_0x010b
            r1 = 1132920832(0x43870000, float:270.0)
            r5.postRotate(r1)     // Catch:{ IOException -> 0x012b }
            goto L_0x010b
        L_0x013b:
            r2 = r1
            r1 = r0
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nanchen.compresshelper.a.a(android.content.Context, android.net.Uri, float, float, android.graphics.Bitmap$Config):android.graphics.Bitmap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031 A[SYNTHETIC, Splitter:B:15:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003b A[SYNTHETIC, Splitter:B:21:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.io.File a(android.content.Context r7, android.net.Uri r8, float r9, float r10, android.graphics.Bitmap.CompressFormat r11, android.graphics.Bitmap.Config r12, int r13, java.lang.String r14, java.lang.String r15, java.lang.String r16) {
        /*
            r6 = 0
            java.lang.String r0 = r11.name()
            java.lang.String r3 = r0.toLowerCase()
            r0 = r7
            r1 = r14
            r2 = r8
            r4 = r15
            r5 = r16
            java.lang.String r2 = a(r0, r1, r2, r3, r4, r5)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x002a, all -> 0x0037 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x002a, all -> 0x0037 }
            android.graphics.Bitmap r0 = a(r7, r8, r9, r10, r12)     // Catch:{ FileNotFoundException -> 0x0045 }
            r0.compress(r11, r13, r1)     // Catch:{ FileNotFoundException -> 0x0045 }
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ IOException -> 0x003f }
        L_0x0024:
            java.io.File r0 = new java.io.File
            r0.<init>(r2)
            return r0
        L_0x002a:
            r0 = move-exception
            r1 = r6
        L_0x002c:
            r0.printStackTrace()     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0024
            r1.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0024
        L_0x0035:
            r0 = move-exception
            goto L_0x0024
        L_0x0037:
            r0 = move-exception
            r1 = r6
        L_0x0039:
            if (r1 == 0) goto L_0x003e
            r1.close()     // Catch:{ IOException -> 0x0041 }
        L_0x003e:
            throw r0
        L_0x003f:
            r0 = move-exception
            goto L_0x0024
        L_0x0041:
            r1 = move-exception
            goto L_0x003e
        L_0x0043:
            r0 = move-exception
            goto L_0x0039
        L_0x0045:
            r0 = move-exception
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nanchen.compresshelper.a.a(android.content.Context, android.net.Uri, float, float, android.graphics.Bitmap$CompressFormat, android.graphics.Bitmap$Config, int, java.lang.String, java.lang.String, java.lang.String):java.io.File");
    }

    private static String a(Context context, String str, Uri uri, String str2, String str3, String str4) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = str3 + c.a(c.a(context, uri))[0];
        }
        return file.getAbsolutePath() + File.separator + str4 + "." + str2;
    }

    private static int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(((float) i3) / ((float) i2));
            int round = Math.round(((float) i4) / ((float) i));
            if (i5 >= round) {
                i5 = round;
            }
        }
        float f = (float) (i4 * i3);
        while (f / ((float) (i5 * i5)) > ((float) (i * i2 * 2))) {
            i5++;
        }
        return i5;
    }
}
