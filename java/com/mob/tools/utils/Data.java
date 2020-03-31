package com.mob.tools.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobLog;
import com.mob.tools.network.BufferedByteArrayOutputStream;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;

public class Data {
    private static final String CHAT_SET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static byte[] SHA1(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return SHA1(str.getBytes("utf-8"));
    }

    public static byte[] SHA1(byte[] bArr) throws Throwable {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(bArr);
        return instance.digest();
    }

    public static byte[] SHA1(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[1024];
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            int read = inputStream.read(bArr);
            while (read != -1) {
                instance.update(bArr, 0, read);
                read = inputStream.read(bArr);
            }
            return instance.digest();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static byte[] SHA1(File file) {
        byte[] bArr = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bArr = SHA1((InputStream) fileInputStream);
            fileInputStream.close();
            return bArr;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return bArr;
        }
    }

    public static byte[] AES128Encode(String str, String str2) throws Throwable {
        if (str == null || str2 == null) {
            return null;
        }
        byte[] bytes = str.getBytes("UTF-8");
        byte[] bArr = new byte[16];
        System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 16));
        byte[] bytes2 = str2.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        StringBuilder sb = new StringBuilder();
        sb.append("AES");
        sb.append("/EC");
        sb.append("B/PKCS7P");
        sb.append("adding");
        Cipher cipher = getCipher(sb.toString(), "BC");
        cipher.init(1, secretKeySpec);
        byte[] bArr2 = new byte[cipher.getOutputSize(bytes2.length)];
        cipher.doFinal(bArr2, cipher.update(bytes2, 0, bytes2.length, bArr2, 0));
        return bArr2;
    }

    private static Cipher getCipher(String str, String str2) throws Throwable {
        Cipher cipher = null;
        if (!TextUtils.isEmpty(str2)) {
            try {
                Provider provider = Security.getProvider(str2);
                if (provider != null) {
                    cipher = Cipher.getInstance(str, provider);
                }
            } catch (Throwable th) {
            }
        }
        if (cipher == null) {
            return Cipher.getInstance(str, str2);
        }
        return cipher;
    }

    public static byte[] AES128Encode(byte[] bArr, String str) throws Throwable {
        if (bArr == null || str == null) {
            return null;
        }
        return AES128Encode(bArr, str.getBytes("UTF-8"));
    }

    public static byte[] AES128Encode(byte[] bArr, byte[] bArr2) throws Throwable {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        StringBuilder sb = new StringBuilder();
        sb.append("AES");
        sb.append("/EC");
        sb.append("B/PKCS7P");
        sb.append("adding");
        Cipher cipher = getCipher(sb.toString(), "BC");
        cipher.init(1, secretKeySpec);
        byte[] bArr3 = new byte[cipher.getOutputSize(bArr2.length)];
        cipher.doFinal(bArr3, cipher.update(bArr2, 0, bArr2.length, bArr3, 0));
        return bArr3;
    }

    public static String AES128Decode(String str, byte[] bArr) throws Throwable {
        if (str == null || bArr == null) {
            return null;
        }
        return new String(AES128Decode(str.getBytes("UTF-8"), bArr), "UTF-8");
    }

    public static byte[] AES128Decode(byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, "AES");
        StringBuilder sb = new StringBuilder();
        sb.append("AES");
        sb.append("/EC");
        sb.append("B/NoP");
        sb.append("adding");
        Cipher cipher = getCipher(sb.toString(), "BC");
        cipher.init(2, secretKeySpec);
        byte[] bArr4 = new byte[cipher.getOutputSize(bArr2.length)];
        int update = cipher.update(bArr2, 0, bArr2.length, bArr4, 0);
        int doFinal = cipher.doFinal(bArr4, update) + update;
        return bArr4;
    }

    public static void AES128Decode(String str, InputStream inputStream, OutputStream outputStream) throws Throwable {
        if (str != null) {
            AES128Decode(str.getBytes("UTF-8"), inputStream, outputStream);
        }
    }

    public static void AES128Decode(byte[] bArr, InputStream inputStream, OutputStream outputStream) throws Throwable {
        if (bArr != null && inputStream != null && outputStream != null) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, 16));
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            StringBuilder sb = new StringBuilder();
            sb.append("AES");
            sb.append("/EC");
            sb.append("B/PKCS7P");
            sb.append("adding");
            Cipher cipher = getCipher(sb.toString(), "BC");
            cipher.init(2, secretKeySpec);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] bArr3 = new byte[1024];
            for (int read = cipherInputStream.read(bArr3); read != -1; read = cipherInputStream.read(bArr3)) {
                outputStream.write(bArr3, 0, read);
            }
            outputStream.flush();
        }
    }

    public static String byteToHex(byte[] bArr) {
        return byteToHex(bArr, 0, bArr.length);
    }

    public static String byteToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr == null) {
            return stringBuffer.toString();
        }
        while (i < i2) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i])}));
            i++;
        }
        return stringBuffer.toString();
    }

    public static String base62(long j) {
        String str = j == 0 ? "0" : "";
        while (j > 0) {
            int i = (int) (j % 62);
            j /= 62;
            str = CHAT_SET.charAt(i) + str;
        }
        return str;
    }

    public static String MD5(String str) {
        if (str == null) {
            return null;
        }
        byte[] rawMD5 = rawMD5(str);
        if (rawMD5 != null) {
            return toHex(rawMD5);
        }
        return null;
    }

    public static String MD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return MD5(bArr, 0, bArr.length);
    }

    public static String MD5(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        byte[] rawMD5 = rawMD5(bArr, i, i2);
        if (rawMD5 != null) {
            return toHex(rawMD5);
        }
        return null;
    }

    public static String MD5(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] rawMD5 = rawMD5((InputStream) fileInputStream);
            fileInputStream.close();
            if (rawMD5 != null) {
                return toHex(rawMD5);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static byte[] rawMD5(String str) {
        byte[] bArr = null;
        if (str == null) {
            return bArr;
        }
        try {
            return rawMD5(str.getBytes("utf-8"));
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return bArr;
        }
    }

    public static byte[] rawMD5(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return rawMD5(bArr, 0, bArr.length);
    }

    public static byte[] rawMD5(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (bArr == null) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            bArr2 = rawMD5((InputStream) byteArrayInputStream);
            byteArrayInputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            bArr2 = null;
        }
        return bArr2;
    }

    public static byte[] rawMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[1024];
            MessageDigest instance = MessageDigest.getInstance("MD5");
            int read = inputStream.read(bArr);
            while (read != -1) {
                instance.update(bArr, 0, read);
                read = inputStream.read(bArr);
            }
            return instance.digest();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static String Base64AES(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        try {
            String encodeToString = Base64.encodeToString(AES128Encode(str2, str), 0);
            if (TextUtils.isEmpty(encodeToString) || !encodeToString.contains("\n")) {
                return encodeToString;
            }
            return encodeToString.replace("\n", "");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static String urlEncode(String str, String str2) throws Throwable {
        String encode = URLEncoder.encode(str, str2);
        return TextUtils.isEmpty(encode) ? encode : encode.replace("+", "%20");
    }

    public static String urlEncode(String str) {
        try {
            return urlEncode(str, "utf-8");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public static String CRC32(byte[] bArr) throws Throwable {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        long value = crc32.getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 56))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 48))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 40))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 32))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 24))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 16))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) (value >>> 8))) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        sb.append(String.format("%02x", new Object[]{Integer.valueOf(((byte) ((int) value)) & DeviceInfos.NETWORK_TYPE_UNCONNECTED)}));
        while (sb.charAt(0) == '0') {
            sb = sb.deleteCharAt(0);
        }
        return sb.toString().toLowerCase();
    }

    public static byte[] rawRSAEncode(byte[] bArr, byte[] bArr2, int i) throws Throwable {
        int i2 = (i / 8) - 11;
        RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
        Cipher instance = Cipher.getInstance("RSA/None/PKCS1Padding");
        instance.init(1, rSAPublicKey);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i3 = 0; bArr.length - i3 > 0; i3 += i2) {
            byte[] doFinal = instance.doFinal(bArr, i3, Math.min(bArr.length - i3, i2));
            byteArrayOutputStream.write(doFinal, 0, doFinal.length);
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] rawRSADecode(byte[] bArr, byte[] bArr2, int i) throws Throwable {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr2));
        Cipher instance = Cipher.getInstance("RSA/None/PKCS1Padding");
        instance.init(2, rSAPrivateKey);
        int i2 = i / 8;
        BufferedByteArrayOutputStream bufferedByteArrayOutputStream = new BufferedByteArrayOutputStream();
        for (int i3 = 0; bArr.length - i3 > 0; i3 += i2) {
            byte[] doFinal = instance.doFinal(bArr, i3, Math.min(bArr.length - i3, i2));
            bufferedByteArrayOutputStream.write(doFinal, 0, doFinal.length);
        }
        bufferedByteArrayOutputStream.close();
        return bufferedByteArrayOutputStream.toByteArray();
    }

    private static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte valueOf : bArr) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
        }
        return stringBuffer.toString();
    }

    private static byte[] hexToBytes(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }
}
