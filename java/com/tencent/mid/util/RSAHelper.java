package com.tencent.mid.util;

import android.util.Base64;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.ByteArrayOutputStream;
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

public class RSAHelper {
    private static final String CipherMode = "RSA/NONE/PKCS1PADDING";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static RSAPublicKey key = null;

    public static void createPublicKey(String str) throws Exception {
        try {
            key = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("公钥数据为空");
        }
    }

    public static byte[] encrypt(byte[] bArr) throws Exception {
        byte[] doFinal;
        if (key == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher instance = Cipher.getInstance(CipherMode);
        instance.init(1, key);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (length - i2 > 0) {
            if (length - i2 > 117) {
                doFinal = instance.doFinal(bArr, i2, 117);
            } else {
                doFinal = instance.doFinal(bArr, i2, length - i2);
            }
            byteArrayOutputStream.write(doFinal, 0, doFinal.length);
            int i3 = i + 1;
            int i4 = i3;
            i2 = i3 * 117;
            i = i4;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    public static byte[] encrypt(String str) throws Exception {
        int i = 0;
        if (key == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance(CipherMode);
            instance.init(1, key);
            String[] splitString = splitString(str, (key.getModulus().bitLength() / 8) - 11);
            byte[] bArr = new byte[0];
            int length = splitString.length;
            while (i < length) {
                byte[] bArr2 = new byte[(bArr.length + 256)];
                System.arraycopy(instance.doFinal(splitString[i].getBytes()), 0, bArr2, bArr.length, 256);
                i++;
                bArr = bArr2;
            }
            return bArr;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e2) {
            throw new Exception("Padding错误");
        } catch (InvalidKeyException e3) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e4) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e5) {
            throw new Exception("明文数据已损坏");
        }
    }

    public static String decrypt(byte[] bArr) throws Exception {
        if (key == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance(CipherMode);
            instance.init(2, key);
            int bitLength = key.getModulus().bitLength() / 8;
            String str = "";
            if (bitLength <= 0) {
                return str;
            }
            byte[][] splitArray = splitArray(bArr, bitLength);
            int length = splitArray.length;
            int i = 0;
            while (i < length) {
                String str2 = str + new String(instance.doFinal(splitArray[i]), "UTF-8");
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
        } catch (Exception e6) {
            throw new Exception("发生未知异常");
        }
    }

    private static String byte2hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    private static byte[] hex2byte(String str) {
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

    public static String[] splitString(String str, int i) {
        int i2;
        String substring;
        int i3 = 0;
        int length = str.length() / i;
        int length2 = str.length() % i;
        if (length2 != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        String[] strArr = new String[(length + i2)];
        String str2 = "";
        while (true) {
            int i4 = i3;
            if (i4 >= length + i2) {
                return strArr;
            }
            if (i4 != (length + i2) - 1 || length2 == 0) {
                substring = str.substring(i4 * i, (i4 * i) + i);
            } else {
                substring = str.substring(i4 * i, (i4 * i) + length2);
            }
            strArr[i4] = substring;
            i3 = i4 + 1;
        }
    }

    public static byte[][] splitArray(byte[] bArr, int i) {
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
