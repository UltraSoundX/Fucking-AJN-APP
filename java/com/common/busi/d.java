package com.common.busi;

import android.content.Context;
import com.stub.StubApp;
import java.io.File;
import java.net.Proxy;

@QVMProtect
public final class d {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static native String a(int i);

    public static native String a(Context context, String str);

    private static native String a(File file);

    public static native String a(String str);

    public static native String a(String str, String str2);

    public static native String a(String str, String str2, int i, int i2, int i3);

    private static native String a(byte[] bArr);

    private static native Proxy a();

    public static native void a(Context context, String str, String str2);

    private static native byte[] a(String str, String str2, int i, String str3, int i2, int i3);

    private static native byte[] a(String str, byte[] bArr);

    private static native byte[] a(byte[] bArr, String str);

    private static native String b(Context context, String str);

    private static native String b(String str);

    private static native String b(String str, String str2);

    private static native String b(byte[] bArr);

    private static native String c(String str);

    private static native byte[] c(String str, String str2);

    private static native StringBuilder[] c(Context context, String str);

    static {
        StubApp.interface11(6720);
    }
}
