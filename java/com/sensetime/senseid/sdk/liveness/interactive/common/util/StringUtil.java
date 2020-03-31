package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.text.TextUtils;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class StringUtil {
    private StringUtil() {
    }

    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
                if (hexString.length() < 2) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sha256(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacSHA256");
            Mac instance = Mac.getInstance(secretKeySpec.getAlgorithm());
            instance.init(secretKeySpec);
            byte[] doFinal = instance.doFinal(str.getBytes());
            StringBuilder sb = new StringBuilder(doFinal.length * 2);
            Formatter formatter = new Formatter(sb);
            for (byte valueOf : doFinal) {
                formatter.format("%02x", new Object[]{Byte.valueOf(valueOf)});
            }
            return sb.toString();
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sortAscAndMerge(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] == null) {
                strArr[i] = "";
            }
        }
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
        }
        return sb.toString();
    }
}
