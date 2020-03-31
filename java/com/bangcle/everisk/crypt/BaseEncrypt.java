package com.bangcle.everisk.crypt;

public class BaseEncrypt {
    private static BaseEncrypt b = null;
    public int a = 0;

    public native byte[] decrypt_wb(byte[] bArr, int i);

    public native byte[] encrypt_wb(byte[] bArr, int i);

    private BaseEncrypt() {
    }
}
