package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Keep
public final class ImageUtil {
    private ImageUtil() {
    }

    public static void bgrToArgb(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = ((bArr[(i4 * 3) + 2] << 16) & 16711680) | -16777216 | ((bArr[(i4 * 3) + 1] << 8) & 65280) | (bArr[i4 * 3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
        }
    }

    public static Bitmap bgrToBitmap(byte[] bArr, int i, int i2) {
        int[] iArr = new int[(i * i2)];
        bgrToArgb(iArr, bArr, i, i2);
        return Bitmap.createBitmap(iArr, i, i2, Config.ARGB_8888);
    }

    public static byte[] bgrToGrayscale(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= 0 || i <= 0 || i2 <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[(i * i2)];
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            byte b = bArr[(i3 * 3) + 2];
            bArr2[i3] = (byte) ((int) ((((double) bArr[(i3 * 3) + 1]) * 0.587d) + (0.2989d * ((double) b)) + (((double) bArr[i3 * 3]) * 0.114d)));
        }
        return bArr2;
    }

    public static byte[] bitmapToJpeg(Bitmap bitmap, int i) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] cropRgba(int[] iArr, int i, int i2, Rect rect) {
        if (rect == null) {
            return iArr;
        }
        int i3 = rect.right - rect.left;
        int i4 = rect.bottom - rect.top;
        if (iArr == null || iArr.length <= 0 || i <= 0 || i2 <= 0 || rect.left < 0 || rect.top < 0 || rect.right < 0 || rect.bottom < 0 || rect.left >= i || rect.top >= i2 || rect.right > i || rect.bottom > i2 || i3 > i || i3 < 0 || i4 > i2 || i4 < 0) {
            return iArr;
        }
        int i5 = 0;
        int[] iArr2 = new int[(i3 * i4)];
        for (int i6 = rect.top; i6 < rect.bottom; i6++) {
            int i7 = rect.left;
            while (i7 < rect.right) {
                iArr2[i5] = iArr[(i * i6) + i7];
                i7++;
                i5++;
            }
        }
        return iArr2;
    }

    public static byte[] getJpegData(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getPixelsBgr(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        byte[] bArr = new byte[((array.length / 4) * 3)];
        for (int i = 0; i < array.length / 4; i++) {
            bArr[i * 3] = array[(i * 4) + 2];
            bArr[(i * 3) + 1] = array[(i * 4) + 1];
            bArr[(i * 3) + 2] = array[i * 4];
        }
        return bArr;
    }

    public static Bitmap nv21ToBitmap(byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 70, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        new Options().inSampleSize = 1;
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static int[] nv21ToRgba(byte[] bArr, int i, int i2) {
        int[] iArr = new int[(i * i2)];
        Bitmap nv21ToBitmap = nv21ToBitmap(bArr, i, i2);
        nv21ToBitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        nv21ToBitmap.recycle();
        return iArr;
    }

    public static byte[] rgbaToBgr(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[(i * i2 * 3)];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            bArr[i3 * 3] = (byte) Color.blue(iArr[i3]);
            bArr[(i3 * 3) + 1] = (byte) Color.green(iArr[i3]);
            bArr[(i3 * 3) + 2] = (byte) Color.red(iArr[i3]);
        }
        return bArr;
    }

    public static byte[] rgbaToJpeg(int[] iArr, int i, int i2, int i3) {
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Config.ARGB_8888);
        byte[] bitmapToJpeg = bitmapToJpeg(createBitmap, i3);
        createBitmap.recycle();
        return bitmapToJpeg;
    }

    public static byte[] rotateNV21Degree90(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[bArr.length];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i5;
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                bArr2[i6] = bArr[(i * i7) + i4];
                i6++;
            }
            i4++;
            i5 = i6;
        }
        for (int i8 = 0; i8 < i; i8 += 2) {
            for (int i9 = (i2 / 2) - 1; i9 >= 0; i9--) {
                bArr2[i5] = bArr[(i * i9) + i3 + i8];
                bArr2[i5 + 1] = bArr[(i * i9) + i3 + i8 + 1];
                i5 += 2;
            }
        }
        return bArr2;
    }

    public static byte[] rotateYuv420Degree180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[bArr.length];
        int i4 = 0;
        for (int i5 = i2 - 1; i5 >= 0; i5--) {
            for (int i6 = i - 1; i6 >= 0; i6--) {
                bArr2[i4] = bArr[(i * i5) + i6];
                i4++;
            }
        }
        int i7 = i % 2 == 0 ? i - 2 : i - 1;
        int i8 = i4;
        for (int i9 = (i2 / 2) - 1; i9 >= 0; i9--) {
            while (i7 >= 0) {
                bArr2[i8] = bArr[(i * i9) + i3 + i7];
                bArr2[i8 + 1] = bArr[(i * i9) + i3 + i7 + 1];
                i8 += 2;
                i7 -= 2;
            }
        }
        return bArr2;
    }

    public static byte[] rotateYuv420Degree270(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[bArr.length];
        int i4 = 0;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            for (int i6 = 0; i6 > i2; i6++) {
                bArr2[i4] = bArr[(i * i6) + i5];
                i4++;
            }
        }
        int i7 = i % 2 == 0 ? i - 2 : i - 1;
        int i8 = i4;
        while (i7 >= 0) {
            int i9 = i8;
            for (int i10 = 0; i10 < i2 / 2; i10++) {
                bArr2[i9] = bArr[(i * i10) + i3 + i7];
                bArr2[i9 + 1] = bArr[(i * i10) + i3 + i7 + 1];
                i9 += 2;
            }
            i7 -= 2;
            i8 = i9;
        }
        return bArr2;
    }

    public static void saveBitmapToFile(Bitmap bitmap, String str) {
        if (bitmap != null && !TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists() || !file.getParentFile().mkdirs() || file.createNewFile()) {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(CompressFormat.JPEG, 100, bufferedOutputStream);
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveNV21ToFile(byte[] bArr, Size size, String str) {
        Bitmap nv21ToBitmap = nv21ToBitmap(bArr, size.getWidth(), size.getHeight());
        saveBitmapToFile(nv21ToBitmap, str);
        nv21ToBitmap.recycle();
    }

    public static Bitmap scaledBitmapToWidth(Bitmap bitmap, int i) {
        int i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            int i3 = (height * i) / width;
            i2 = i;
            i = i3;
        } else {
            i2 = (width * i) / height;
        }
        return Bitmap.createScaledBitmap(bitmap, i2, i, true);
    }
}
