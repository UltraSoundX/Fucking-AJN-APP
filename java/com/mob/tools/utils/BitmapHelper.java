package com.mob.tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.opengl.GLES10;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import com.mob.tools.MobLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.HashMap;

public class BitmapHelper {
    private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
    private static int maxBitmapHeight;
    private static int maxBitmapWidth;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        maxBitmapWidth = max;
        maxBitmapHeight = max;
    }

    public static Bitmap getBitmapByCompressSize(String str, int i, int i2) throws Throwable {
        int i3;
        float f = 1.0f;
        int i4 = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i5 = options.outWidth;
        int i6 = options.outHeight;
        if (i <= 1 || i2 <= 1) {
            i3 = 1;
        } else {
            float min = (((float) Math.min(i5, i6)) * 1.0f) / ((float) Math.min(i, i2));
            float max = (((float) Math.max(i5, i6)) * 1.0f) / ((float) Math.max(i, i2));
            float f2 = (float) (i5 / i6);
            if (f2 > 2.0f || ((double) f2) < 0.5d) {
                while (f * 2.0f <= min) {
                    f *= 2.0f;
                }
                i3 = (int) f;
            } else {
                while (f * 2.0f <= Math.min(min, max)) {
                    f *= 2.0f;
                }
                i3 = (int) f;
            }
        }
        if (i3 >= 1) {
            i4 = i3;
        }
        while (true) {
            if (i5 / i4 > maxBitmapWidth || i6 / i4 > maxBitmapHeight) {
                i4++;
            } else {
                Options options2 = new Options();
                options2.inPreferredConfig = Config.RGB_565;
                options2.inSampleSize = i4;
                return BitmapFactory.decodeFile(str, options2);
            }
        }
    }

    public static Bitmap getBitmapByCompressQuality(String str, int i, int i2, int i3, long j) throws Throwable {
        Bitmap decodeByteArray;
        Bitmap bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
        if (i3 < 10 || i3 > 100) {
            i3 = 100;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CompressFormat bmpFormat = getBmpFormat(str);
        bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (j < 10240) {
            decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
            }
        } else {
            while (((long) byteArray.length) > j && i3 >= 11) {
                byteArrayOutputStream.reset();
                i3 -= 6;
                bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
                byteArray = byteArrayOutputStream.toByteArray();
            }
            if (i3 == 100) {
                decodeByteArray = bitmapByCompressSize;
            } else {
                decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                }
            }
        }
        return decodeByteArray;
    }

    public static String saveBitmapByCompress(String str, int i, int i2, int i3) throws Throwable {
        Bitmap bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
        if (i3 > 100) {
            i3 = 100;
        } else if (i3 < 10) {
            i3 = 10;
        }
        CompressFormat bmpFormat = getBmpFormat(str);
        String str2 = ".jpg";
        if (bmpFormat == CompressFormat.PNG) {
            str2 = ".png";
        }
        File file = new File(new File(str).getParent(), String.valueOf(System.currentTimeMillis()) + str2);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmapByCompressSize.compress(bmpFormat, i3, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static Bitmap getBitmap(String str, int i) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getBitmap(new File(str), i);
    }

    public static Bitmap getBitmap(File file, int i) throws Throwable {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        Bitmap bitmap = getBitmap((InputStream) fileInputStream, i);
        fileInputStream.close();
        return bitmap;
    }

    public static Bitmap getBitmap(InputStream inputStream, int i) {
        if (inputStream == null) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap getBitmap(String str) throws Throwable {
        return getBitmap(str, 1);
    }

    public static Bitmap getBitmap(Context context, String str) throws Throwable {
        return getBitmap(downloadBitmap(context, str));
    }

    public static String downloadBitmap(Context context, final String str) throws Throwable {
        final String cachePath = ResHelper.getCachePath(context, "images");
        File file = new File(cachePath, Data.MD5(str));
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        final HashMap hashMap = new HashMap();
        new NetworkHelper().rawGet(str, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200) {
                    String access$000 = BitmapHelper.getFileName(httpConnection, str);
                    File file = new File(cachePath, access$000);
                    if (file.exists()) {
                        hashMap.put("bitmap", file.getAbsolutePath());
                        return;
                    }
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        Bitmap bitmap = BitmapHelper.getBitmap((InputStream) new FilterInputStream(httpConnection.getInputStream()) {
                            public long skip(long j) throws IOException {
                                long j2 = 0;
                                while (j2 < j) {
                                    long skip = this.in.skip(j - j2);
                                    if (skip == 0) {
                                        break;
                                    }
                                    j2 += skip;
                                }
                                return j2;
                            }
                        }, 1);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            if (access$000.toLowerCase().endsWith(".gif") || access$000.toLowerCase().endsWith(".png")) {
                                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                            } else {
                                bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            hashMap.put("bitmap", file.getAbsolutePath());
                        }
                    } catch (Throwable th) {
                        if (file.exists()) {
                            file.delete();
                        }
                        throw th;
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    HashMap hashMap = new HashMap();
                    hashMap.put("error", sb.toString());
                    hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
                    throw new Throwable(new Hashon().fromHashMap(hashMap));
                }
            }
        }, (NetworkTimeOut) null);
        return (String) hashMap.get("bitmap");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileName(com.mob.tools.network.HttpConnection r11, java.lang.String r12) throws java.lang.Throwable {
        /*
            r2 = 0
            r10 = 1
            r3 = 0
            java.util.Map r4 = r11.getHeaderFields()
            if (r4 == 0) goto L_0x005d
            java.lang.String r0 = "Content-Disposition"
            java.lang.Object r0 = r4.get(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x005d
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x005d
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = ";"
            java.lang.String[] r5 = r0.split(r1)
            int r6 = r5.length
            r1 = r3
            r0 = r2
        L_0x0028:
            if (r1 >= r6) goto L_0x005e
            r7 = r5[r1]
            java.lang.String r8 = r7.trim()
            java.lang.String r9 = "filename"
            boolean r8 = r8.startsWith(r9)
            if (r8 == 0) goto L_0x005a
            java.lang.String r0 = "="
            java.lang.String[] r0 = r7.split(r0)
            r0 = r0[r10]
            java.lang.String r7 = "\""
            boolean r7 = r0.startsWith(r7)
            if (r7 == 0) goto L_0x005a
            java.lang.String r7 = "\""
            boolean r7 = r0.endsWith(r7)
            if (r7 == 0) goto L_0x005a
            int r7 = r0.length()
            int r7 = r7 + -1
            java.lang.String r0 = r0.substring(r10, r7)
        L_0x005a:
            int r1 = r1 + 1
            goto L_0x0028
        L_0x005d:
            r0 = r2
        L_0x005e:
            if (r0 != 0) goto L_0x00b3
            java.lang.String r1 = com.mob.tools.utils.Data.MD5(r12)
            if (r4 == 0) goto L_0x00f6
            java.lang.String r0 = "Content-Type"
            java.lang.Object r0 = r4.get(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L_0x00f6
            int r4 = r0.size()
            if (r4 <= 0) goto L_0x00f6
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x00b4
            java.lang.String r0 = ""
        L_0x0080:
            java.lang.String r3 = "image/"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x00b9
            java.lang.String r2 = "image/"
            int r2 = r2.length()
            java.lang.String r0 = r0.substring(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "jpeg"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00ab
            java.lang.String r0 = "jpg"
        L_0x00ab:
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
        L_0x00b3:
            return r0
        L_0x00b4:
            java.lang.String r0 = r0.trim()
            goto L_0x0080
        L_0x00b9:
            r0 = 47
            int r0 = r12.lastIndexOf(r0)
            if (r0 <= 0) goto L_0x00f8
            int r0 = r0 + 1
            java.lang.String r0 = r12.substring(r0)
        L_0x00c7:
            if (r0 == 0) goto L_0x00f6
            int r2 = r0.length()
            if (r2 <= 0) goto L_0x00f6
            r2 = 46
            int r2 = r0.lastIndexOf(r2)
            if (r2 <= 0) goto L_0x00f6
            int r3 = r0.length()
            int r3 = r3 - r2
            r4 = 10
            if (r3 >= r4) goto L_0x00f6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r0 = r0.substring(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            goto L_0x00b3
        L_0x00f6:
            r0 = r1
            goto L_0x00b3
        L_0x00f8:
            r0 = r2
            goto L_0x00c7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.BitmapHelper.getFileName(com.mob.tools.network.HttpConnection, java.lang.String):java.lang.String");
    }

    public static String saveViewToImage(View view) throws Throwable {
        if (view == null) {
            return null;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        return saveViewToImage(view, width, height);
    }

    public static String saveViewToImage(View view, int i, int i2) throws Throwable {
        Bitmap captureView = captureView(view, i, i2);
        if (captureView == null || captureView.isRecycled()) {
            return null;
        }
        File file = new File(ResHelper.getCachePath(view.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        captureView.compress(CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static Bitmap captureView(View view, int i, int i2) throws Throwable {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap blur(Bitmap bitmap, int i, int i2) {
        int i3 = (int) ((((float) i) / ((float) i2)) + 0.5f);
        Bitmap createBitmap = Bitmap.createBitmap((int) ((((float) bitmap.getWidth()) / ((float) i2)) + 0.5f), (int) ((((float) bitmap.getHeight()) / ((float) i2)) + 0.5f), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(1.0f / ((float) i2), 1.0f / ((float) i2));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        blur(createBitmap, i3, true);
        return createBitmap;
    }

    private static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        Bitmap copy;
        if (z) {
            copy = bitmap;
        } else {
            copy = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int[] iArr = new int[(width * height)];
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = width - 1;
        int i3 = height - 1;
        int i4 = width * height;
        int i5 = i + i + 1;
        int[] iArr2 = new int[i4];
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[Math.max(width, height)];
        int i6 = (i5 + 1) >> 1;
        int i7 = i6 * i6;
        int[] iArr6 = new int[(i7 * 256)];
        for (int i8 = 0; i8 < i7 * 256; i8++) {
            iArr6[i8] = i8 / i7;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i5, 3});
        int i9 = i + 1;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = i10;
            if (i13 >= height) {
                break;
            }
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            for (int i23 = -i; i23 <= i; i23++) {
                int i24 = iArr[Math.min(i2, Math.max(i23, 0)) + i12];
                int[] iArr8 = iArr7[i23 + i];
                iArr8[0] = (16711680 & i24) >> 16;
                iArr8[1] = (65280 & i24) >> 8;
                iArr8[2] = i24 & 255;
                int abs = i9 - Math.abs(i23);
                i21 += iArr8[0] * abs;
                i20 += iArr8[1] * abs;
                i19 += abs * iArr8[2];
                if (i23 > 0) {
                    i15 += iArr8[0];
                    i22 += iArr8[1];
                    i14 += iArr8[2];
                } else {
                    i18 += iArr8[0];
                    i17 += iArr8[1];
                    i16 += iArr8[2];
                }
            }
            int i25 = i21;
            int i26 = i20;
            int i27 = i19;
            int i28 = i12;
            int i29 = i;
            for (int i30 = 0; i30 < width; i30++) {
                iArr2[i28] = iArr6[i25];
                iArr3[i28] = iArr6[i26];
                iArr4[i28] = iArr6[i27];
                int i31 = i25 - i18;
                int i32 = i26 - i17;
                int i33 = i27 - i16;
                int[] iArr9 = iArr7[((i29 - i) + i5) % i5];
                int i34 = i18 - iArr9[0];
                int i35 = i17 - iArr9[1];
                int i36 = i16 - iArr9[2];
                if (i13 == 0) {
                    iArr5[i30] = Math.min(i30 + i + 1, i2);
                }
                int i37 = iArr[iArr5[i30] + i11];
                iArr9[0] = (16711680 & i37) >> 16;
                iArr9[1] = (65280 & i37) >> 8;
                iArr9[2] = i37 & 255;
                int i38 = i15 + iArr9[0];
                int i39 = i22 + iArr9[1];
                int i40 = i14 + iArr9[2];
                i25 = i31 + i38;
                i26 = i32 + i39;
                i27 = i33 + i40;
                i29 = (i29 + 1) % i5;
                int[] iArr10 = iArr7[i29 % i5];
                i18 = i34 + iArr10[0];
                i17 = i35 + iArr10[1];
                i16 = i36 + iArr10[2];
                i15 = i38 - iArr10[0];
                i22 = i39 - iArr10[1];
                i14 = i40 - iArr10[2];
                i28++;
            }
            i10 = i13 + 1;
            i11 += width;
            i12 = i28;
        }
        for (int i41 = 0; i41 < width; i41++) {
            int i42 = 0;
            int i43 = (-i) * width;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            int i48 = -i;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            while (i48 <= i) {
                int max = Math.max(0, i43) + i41;
                int[] iArr11 = iArr7[i48 + i];
                iArr11[0] = iArr2[max];
                iArr11[1] = iArr3[max];
                iArr11[2] = iArr4[max];
                int abs2 = i9 - Math.abs(i48);
                int i53 = (iArr2[max] * abs2) + i51;
                int i54 = (iArr3[max] * abs2) + i50;
                int i55 = (iArr4[max] * abs2) + i49;
                if (i48 > 0) {
                    i44 += iArr11[0];
                    i52 += iArr11[1];
                    i42 += iArr11[2];
                } else {
                    i47 += iArr11[0];
                    i46 += iArr11[1];
                    i45 += iArr11[2];
                }
                if (i48 < i3) {
                    i43 += width;
                }
                i48++;
                i49 = i55;
                i50 = i54;
                i51 = i53;
            }
            int i56 = i50;
            int i57 = i51;
            int i58 = i49;
            int i59 = i41;
            int i60 = i42;
            int i61 = i52;
            int i62 = i44;
            int i63 = i45;
            int i64 = i46;
            int i65 = i47;
            int i66 = i;
            for (int i67 = 0; i67 < height; i67++) {
                iArr[i59] = (-16777216 & iArr[i59]) | (iArr6[i57] << 16) | (iArr6[i56] << 8) | iArr6[i58];
                int i68 = i57 - i65;
                int i69 = i56 - i64;
                int i70 = i58 - i63;
                int[] iArr12 = iArr7[((i66 - i) + i5) % i5];
                int i71 = i65 - iArr12[0];
                int i72 = i64 - iArr12[1];
                int i73 = i63 - iArr12[2];
                if (i41 == 0) {
                    iArr5[i67] = Math.min(i67 + i9, i3) * width;
                }
                int i74 = iArr5[i67] + i41;
                iArr12[0] = iArr2[i74];
                iArr12[1] = iArr3[i74];
                iArr12[2] = iArr4[i74];
                int i75 = i62 + iArr12[0];
                int i76 = i61 + iArr12[1];
                int i77 = i60 + iArr12[2];
                i57 = i68 + i75;
                i56 = i69 + i76;
                i58 = i70 + i77;
                i66 = (i66 + 1) % i5;
                int[] iArr13 = iArr7[i66];
                i65 = i71 + iArr13[0];
                i64 = i72 + iArr13[1];
                i63 = i73 + iArr13[2];
                i62 = i75 - iArr13[0];
                i61 = i76 - iArr13[1];
                i60 = i77 - iArr13[2];
                i59 += width;
            }
        }
        copy.setPixels(iArr, 0, width, 0, 0, width, height);
        return copy;
    }

    public static Bitmap roundBitmap(Bitmap bitmap, int i, int i2, float f, float f2, float f3, float f4) throws Throwable {
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        if (width == i && height == i2) {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        } else {
            bitmap2 = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        Rect rect2 = new Rect(0, 0, i, i2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr));
        shapeDrawable.setBounds(rect2);
        shapeDrawable.draw(canvas);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return bitmap2;
    }

    public static int[] fixRect(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        } else {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        }
        return iArr3;
    }

    public static int[] fixRect_2(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        } else {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        }
        return iArr3;
    }

    public static String saveBitmap(Context context, Bitmap bitmap, CompressFormat compressFormat, int i) throws Throwable {
        String cachePath = ResHelper.getCachePath(context, "images");
        String str = ".jpg";
        if (compressFormat == CompressFormat.PNG) {
            str = ".png";
        }
        File file = new File(cachePath, String.valueOf(System.currentTimeMillis()) + str);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(compressFormat, i, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static String saveBitmap(Context context, Bitmap bitmap) throws Throwable {
        return saveBitmap(context, bitmap, CompressFormat.JPEG, 80);
    }

    public static CompressFormat getBmpFormat(byte[] bArr) {
        String mime = getMime(bArr);
        CompressFormat compressFormat = CompressFormat.JPEG;
        if (mime == null) {
            return compressFormat;
        }
        if (mime.endsWith("png") || mime.endsWith("gif")) {
            return CompressFormat.PNG;
        }
        return compressFormat;
    }

    public static CompressFormat getBmpFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith("png") || lowerCase.endsWith("gif")) {
            return CompressFormat.PNG;
        }
        if (lowerCase.endsWith("jpg") || lowerCase.endsWith("jpeg") || lowerCase.endsWith("bmp") || lowerCase.endsWith("tif")) {
            return CompressFormat.JPEG;
        }
        String mime = getMime(str);
        if (mime.endsWith("png") || mime.endsWith("gif")) {
            return CompressFormat.PNG;
        }
        return CompressFormat.JPEG;
    }

    public static String getMime(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[8];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return getMime(bArr);
        } catch (Exception e) {
            MobLog.getInstance().w((Throwable) e);
            return "";
        }
    }

    private static String getMime(byte[] bArr) {
        byte[] bArr2 = {-1, -40, -1, -31};
        if (bytesStartWith(bArr, new byte[]{-1, -40, -1, -32}) || bytesStartWith(bArr, bArr2)) {
            return "jpg";
        }
        if (bytesStartWith(bArr, new byte[]{-119, 80, 78, 71})) {
            return "png";
        }
        if (bytesStartWith(bArr, "GIF".getBytes())) {
            return "gif";
        }
        if (bytesStartWith(bArr, "BM".getBytes())) {
            return "bmp";
        }
        byte[] bArr3 = {77, 77, 42};
        if (bytesStartWith(bArr, new byte[]{73, 73, 42}) || bytesStartWith(bArr, bArr3)) {
            return "tif";
        }
        return "";
    }

    private static boolean bytesStartWith(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) throws Throwable {
        int width = (bitmap.getWidth() - i) - i3;
        int height = (bitmap.getHeight() - i2) - i4;
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, (float) (-i), (float) (-i2), new Paint());
        return createBitmap;
    }

    public static boolean isBlackBitmap(Bitmap bitmap) throws Throwable {
        boolean z;
        if (bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                z = false;
                break;
            } else if ((iArr[i] & ViewCompat.MEASURED_SIZE_MASK) != 0) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            return true;
        }
        return false;
    }

    public static int mixAlpha(int i, int i2) {
        int i3 = i >>> 24;
        return ((((255 - i3) * (i2 & 255)) + (i3 * (i & 255))) / 255) | ((((((16711680 & i) >>> 16) * i3) + (((16711680 & i2) >>> 16) * (255 - i3))) / 255) << 16) | -16777216 | ((((((65280 & i) >>> 8) * i3) + ((255 - i3) * ((65280 & i2) >>> 8))) / 255) << 8);
    }

    public static Bitmap scaleBitmapByHeight(Context context, int i, int i2) throws Throwable {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        boolean z = i2 != decodeResource.getHeight();
        Bitmap scaleBitmapByHeight = scaleBitmapByHeight(decodeResource, i2);
        if (z) {
            decodeResource.recycle();
        }
        return scaleBitmapByHeight;
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int i) throws Throwable {
        return Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * i) / bitmap.getHeight(), i, true);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j, boolean z) {
        byte[] byteArray;
        int i = 100;
        if (isEmptyBitmap(bitmap) || j <= 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (((long) byteArrayOutputStream.size()) <= j) {
            byteArray = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (((long) byteArrayOutputStream.size()) >= j) {
                byteArray = byteArrayOutputStream.toByteArray();
            } else {
                int i2 = 0;
                int i3 = 0;
                while (i3 < i) {
                    i2 = (i3 + i) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
                    int size = byteArrayOutputStream.size();
                    if (((long) size) == j) {
                        break;
                    } else if (((long) size) > j) {
                        i = i2 - 1;
                    } else {
                        i3 = i2 + 1;
                    }
                }
                if (i == i2 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(CompressFormat.JPEG, i3, byteArrayOutputStream);
                }
                byteArray = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static boolean save(Bitmap bitmap, String str, CompressFormat compressFormat) {
        return save(bitmap, FileUtils.getFileByPath(str), compressFormat, false);
    }

    public static boolean save(Bitmap bitmap, File file, CompressFormat compressFormat, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        boolean z2;
        if (isEmptyBitmap(bitmap) || !FileUtils.createFileByDeleteOldFile(file)) {
            return false;
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                z2 = bitmap.compress(compressFormat, 100, bufferedOutputStream);
                if (z) {
                    try {
                        if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            MobLog.getInstance().d(th);
                            FileUtils.closeIO(bufferedOutputStream);
                            return z2;
                        } catch (Throwable th3) {
                            th = th3;
                            FileUtils.closeIO(bufferedOutputStream);
                            throw th;
                        }
                    }
                }
                FileUtils.closeIO(bufferedOutputStream);
                return z2;
            } catch (Throwable th4) {
                th = th4;
                z2 = false;
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedOutputStream = null;
            FileUtils.closeIO(bufferedOutputStream);
            throw th;
        }
    }
}
