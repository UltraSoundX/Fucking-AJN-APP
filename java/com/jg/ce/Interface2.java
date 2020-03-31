package com.jg.ce;

import android.content.Context;
import com.stub.StubApp;

@QVMProtect
public class Interface2 {
    public static native void init();

    public static native int interface1();

    public static native int interface1(Context context);

    public static native int interface10(Context context);

    public static native int interface11(Context context);

    public static native int interface12(Context context);

    public static native int interface13(Context context);

    public static native int interface14(Context context);

    public static native int interface15(Context context);

    public static native int interface16(Context context);

    public static native int interface2();

    public static native int interface2(Context context);

    public static native int interface3();

    public static native int interface3(Context context);

    public static native int interface4();

    public static native int interface4(Context context);

    public static native int interface5();

    public static native int interface5(Context context);

    public static native int interface6();

    public static native int interface6(Context context);

    public static native int interface7(Context context);

    public static native String interface8(Context context);

    public static native void interface88(Context context, boolean z);

    public static native int interface9(Context context);

    public static native boolean interface99(Context context);

    public static native int interfacel1(Context context);

    public static native int interfacel2(Context context);

    public static native int interfacel3(Context context);

    public static native int interfacel4(Context context);

    public static native int interfacel5(Context context);

    public static native int interfaec10();

    public static native int interfaec4();

    public static native int interfaec5();

    public static native int interfaec6();

    public static native int interfaec7();

    public static native int interfaec8();

    public static native int interfaec9();

    private static native String md5(String str);

    static {
        try {
            StubApp.interface11(6723);
            System.loadLibrary("jgbEC");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
