package com.ccb.crypto.tp.tool;

public class TpSafeUtils {
    protected static native String ad(String str, byte[] bArr, String str2);

    protected static native String ad2(String str, String str2, byte[] bArr, String str3);

    protected static native String ae(String str, byte[] bArr, String str2);

    protected static native String hm(String str, String str2);

    protected static native String hs(String str);

    protected static native int sc(String str, String str2);

    protected static native String te(String str, byte[] bArr, String str2);

    protected static native String te2(String str, String str2, byte[] bArr, String str3);

    static {
        try {
            System.loadLibrary("Esafeexptp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
