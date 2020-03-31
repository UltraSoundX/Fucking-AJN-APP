package com.baidu.location.indoor.mapversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

public class IndoorJni {
    public static boolean a;

    static {
        a = false;
        try {
            System.loadLibrary("indoor");
            a = true;
            System.err.println("load indoor lib success.");
        } catch (Throwable th) {
            System.err.println("Cannot load indoor lib");
            th.printStackTrace();
        }
    }

    public static String a(File file, Bitmap bitmap, double d, float[] fArr) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        File file2 = new File(file, "resize.jpg");
        try {
            if ((file2.exists() && !file2.delete()) || !file2.createNewFile()) {
                return null;
            }
            preprocessImage(iArr, width, height, d, (double) fArr[0], (double) fArr[1], (double) fArr[2], file2.getAbsolutePath());
            return file2.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a(String str, File file) {
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            int width = decodeFile.getWidth();
            int height = decodeFile.getHeight();
            int[] iArr = new int[(width * height)];
            decodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
            File file2 = new File(file, "compress.jpg");
            if ((file2.exists() && !file2.delete()) || !file2.createNewFile()) {
                return false;
            }
            compressImage(iArr, width, height, file2.getAbsolutePath());
            return true;
        } catch (Error | Exception e) {
            return false;
        }
    }

    public static native void compressImage(int[] iArr, int i, int i2, String str);

    public static native void initPf();

    public static native float[] pgo();

    public static native void phs(int i, float f, float f2, float f3, long j);

    public static native void preprocessImage(int[] iArr, int i, int i2, double d, double d2, double d3, double d4, String str);

    public static native void resetPf();

    public static native double[] setPfDr(double d, double d2, long j);

    public static native void setPfGeoMap(double[][] dArr, String str, int i, int i2);

    public static native void setPfGeomag(double d);

    public static native double[] setPfGps(double d, double d2, double d3, double d4, double d5, long j);

    public static native void setPfRdnt(String str, short[][] sArr, double d, double d2, int i, int i2, double d3, double d4);

    public static native double[] setPfWf(double d, double d2, double d3, long j);

    public static native void stopPdr();
}
