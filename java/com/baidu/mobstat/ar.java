package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class ar {

    public static class a {
        @SuppressLint({"TrulyRandom"})
        public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr3);
        }

        public static byte[] a() throws Exception {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            return instance.generateKey().getEncoded();
        }

        public static byte[] b() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        }

        public static String a(byte[] bArr) {
            try {
                return b(a(), b(), bArr);
            } catch (Exception e) {
                return "";
            }
        }

        public static String b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            try {
                return au.b(a(bArr, bArr2, ax.a(bArr3))) + "|" + ba.a(bArr) + "|" + ba.a(bArr2);
            } catch (Exception e) {
                return "";
            }
        }
    }

    public static class b {
        public static byte[] a(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || aw.a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(aw.a[i2].getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr);
        }

        public static byte[] b(int i, byte[] bArr) throws Exception {
            int i2 = i - 1;
            if (i2 < 0 || aw.a.length <= i2) {
                return new byte[0];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(aw.a[i2].getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr);
        }

        public static String c(int i, byte[] bArr) {
            try {
                return au.b(a(i, bArr));
            } catch (Exception e) {
                return "";
            }
        }

        public static String d(int i, byte[] bArr) {
            String c = c(i, bArr);
            return TextUtils.isEmpty(c) ? "" : c + "|" + i;
        }
    }
}
