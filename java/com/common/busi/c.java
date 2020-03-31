package com.common.busi;

import android.content.Context;
import android.content.pm.PackageManager;
import com.stub.StubApp;

@QVMProtect
public final class c {
    private static PackageManager a = null;

    static native String a(Context context);

    public static native String a(Context context, String str);

    public static native void a(Context context, int i);

    public static native void a(Context context, long j);

    public static native void a(Thread thread);

    public static native void a(Thread thread, Throwable th, boolean z, int i);

    public static native boolean a(Context context, String str, String str2);

    public static native boolean a(String str);

    static native Boolean b(Context context, String str);

    public static native String b(Context context);

    private static native boolean b(String str);

    public static native int c(Context context);

    public static native String c(Context context, String str);

    public static native int d(Context context, String str);

    public static native long d(Context context);

    public static native boolean e(Context context, String str);

    public static native void f(Context context, String str);

    static {
        StubApp.interface11(6719);
    }
}
