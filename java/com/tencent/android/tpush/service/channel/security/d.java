package com.tencent.android.tpush.service.channel.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: ProGuard */
public class d {
    private static RSAPublicKey a = null;

    public static void a(String str) {
        try {
            a = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(a.a(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("公钥数据为空");
        }
    }

    public static String a(byte[] bArr) {
        if (a == null) {
            a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDyMrH3s73WgYu7MnBDurisRILqXwj1enRsuO7lPZCrPIxRd1RpTrv0xoWzKSyl2gwhY+l6/csBqs/Ako70II7wFWP3ugyKroHaWgvPw9M090xowDqBhQjcEfWKMd8A/cimVAlO/1p7kQDH0eTvZvOsv7sLmfTsMe8PkT2t22gZWQIDAQAB");
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, a);
            byte[][] a2 = a(bArr, a.getModulus().bitLength() / 8);
            int length = a2.length;
            String str = "";
            int i = 0;
            while (i < length) {
                String str2 = str + new String(instance.doFinal(a2[i]), "UTF-8");
                i++;
                str = str2;
            }
            return str;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvalidKeyException e3) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e4) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e5) {
            throw new Exception("密文数据已损坏");
        }
    }

    public static byte[] b(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        String lowerCase = str.toLowerCase();
        int length = lowerCase.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (Integer.parseInt(lowerCase.substring(i * 2, (i * 2) + 2), 16) & 255);
        }
        return bArr;
    }

    public static byte[][] a(byte[] bArr, int i) {
        int i2;
        int length = bArr.length / i;
        int length2 = bArr.length % i;
        if (length2 != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        byte[][] bArr2 = new byte[(length + i2)][];
        for (int i3 = 0; i3 < length + i2; i3++) {
            byte[] bArr3 = new byte[i];
            if (i3 != (length + i2) - 1 || length2 == 0) {
                System.arraycopy(bArr, i3 * i, bArr3, 0, i);
            } else {
                System.arraycopy(bArr, i3 * i, bArr3, 0, length2);
            }
            bArr2[i3] = bArr3;
        }
        return bArr2;
    }
}
