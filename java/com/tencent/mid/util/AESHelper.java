package com.tencent.mid.util;

import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESHelper {
    private static final String CipherMode = "AES/CFB/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    public static final List<Integer> TWO_POWER_LIST = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(16), Integer.valueOf(32), Integer.valueOf(64), Integer.valueOf(128), Integer.valueOf(256), Integer.valueOf(512), Integer.valueOf(1024), Integer.valueOf(2048)}));
    private IvParameterSpec iv = null;
    private SecretKey key = null;

    public static String createSecretKeyString() {
        SecretKey createSecretKey = createSecretKey();
        if (createSecretKey != null) {
            return byte2hex(createSecretKey.getEncoded());
        }
        return null;
    }

    public static SecretKey createSecretKey() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(KEY_ALGORITHM);
            instance.init(128);
            return new SecretKeySpec(instance.generateKey().getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public byte[] getKeyBytes() {
        if (this.key != null) {
            return this.key.getEncoded();
        }
        return null;
    }

    public String getKeyString() {
        if (this.key != null) {
            return byte2hex(this.key.getEncoded());
        }
        return null;
    }

    public byte[] getIvBytes() {
        if (this.iv != null) {
            return this.iv.getIV();
        }
        return null;
    }

    public String getIvString() {
        if (this.iv != null) {
            return byte2hex(this.iv.getIV());
        }
        return null;
    }

    public static String createIvParameterString() {
        IvParameterSpec createIvParameterSpec = createIvParameterSpec();
        if (createIvParameterSpec != null) {
            return byte2hex(createIvParameterSpec.getIV());
        }
        return null;
    }

    public static IvParameterSpec createIvParameterSpec() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return new IvParameterSpec(bArr);
    }

    public void initKeyAndIV() {
        this.key = createSecretKey();
        this.iv = createIvParameterSpec();
    }

    private int searchTwoPowerLength(int i, int i2) {
        if (i < i2) {
            i = i2;
        }
        if (TWO_POWER_LIST.contains(Integer.valueOf(i))) {
            return i;
        }
        for (Integer num : TWO_POWER_LIST) {
            if (num.intValue() > i) {
                return num.intValue();
            }
        }
        return i;
    }

    private byte[] fillBytes(String str, int i) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        if (length >= i) {
            return bytes;
        }
        byte[] bArr = new byte[i];
        Arrays.fill(bArr, 0);
        System.arraycopy(bytes, 0, bArr, 0, length);
        return bArr;
    }

    public void setKeyAndIV(String str, String str2) {
        int searchTwoPowerLength = searchTwoPowerLength(str.length(), str2.length());
        byte[] fillBytes = fillBytes(str, searchTwoPowerLength);
        byte[] fillBytes2 = fillBytes(str2, searchTwoPowerLength);
        this.key = new SecretKeySpec(fillBytes, KEY_ALGORITHM);
        this.iv = new IvParameterSpec(fillBytes2);
    }

    public byte[] encrypt(byte[] bArr) throws Exception {
        if (this.key == null) {
            throw new Exception("密钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance(CipherMode);
            instance.init(1, this.key, this.iv);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    public String encrypt(String str) throws Exception {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byte2hex(encrypt(bArr));
    }

    public byte[] decrypt(byte[] bArr) throws Exception {
        if (this.key == null) {
            throw new Exception("密钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance(CipherMode);
            instance.init(2, this.key, this.iv);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    public String decrypt(String str) throws Exception {
        byte[] bArr;
        try {
            bArr = hex2byte(str);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        byte[] decrypt = decrypt(bArr);
        if (decrypt == null) {
            return null;
        }
        try {
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            return null;
        }
    }

    public static String byte2hex(byte[] bArr) {
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

    public static byte[] hex2byte(String str) {
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
}
