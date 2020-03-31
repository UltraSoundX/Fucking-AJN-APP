package com.tencent.android.tpush.encrypt;

import com.tencent.android.tpush.common.Constants;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: ProGuard */
public class a {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "md5 encrypt:" + str, e);
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "md5 encrypt:" + str, e2);
        }
        return "";
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toHexString(b & DeviceInfos.NETWORK_TYPE_UNCONNECTED));
        }
        return sb.toString();
    }
}
