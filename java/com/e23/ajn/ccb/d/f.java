package com.e23.ajn.ccb.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/* compiled from: FileUtils */
public class f {
    public static int a = 180000;

    public static boolean a() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public static String a(Context context) {
        return context.getCacheDir().getPath();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f A[SYNTHETIC, Splitter:B:16:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005c A[SYNTHETIC, Splitter:B:23:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r4, byte[] r5, java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = a(r4)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r1 = ".jpg"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r3 = r0.toString()
            r2 = 0
            a(r3)
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0048, all -> 0x0058 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0048, all -> 0x0058 }
            r1.write(r5)     // Catch:{ IOException -> 0x0067 }
            r1.flush()     // Catch:{ IOException -> 0x0067 }
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0042:
            return r3
        L_0x0043:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0042
        L_0x0048:
            r0 = move-exception
            r1 = r2
        L_0x004a:
            r0.printStackTrace()     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0042
        L_0x0053:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0042
        L_0x0058:
            r0 = move-exception
            r1 = r2
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x0060 }
        L_0x005f:
            throw r0
        L_0x0060:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x005f
        L_0x0065:
            r0 = move-exception
            goto L_0x005a
        L_0x0067:
            r0 = move-exception
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e23.ajn.ccb.d.f.a(android.content.Context, byte[], java.lang.String):java.lang.String");
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    public static byte[] a(Bitmap bitmap, int i) {
        int i2 = 100;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            int length = (byteArrayOutputStream.toByteArray().length / 1024) / i;
            if (i2 <= 0) {
                break;
            }
            if (length == 1) {
                i2 -= 5;
            } else {
                i2 = 200 / length;
            }
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int b(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Log.d("TAG:", "angle2=" + i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
