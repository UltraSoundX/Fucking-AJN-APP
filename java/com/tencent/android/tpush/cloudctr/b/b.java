package com.tencent.android.tpush.cloudctr.b;

import android.text.TextUtils;
import android.util.Log;
import com.tencent.android.tpush.b.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: ProGuard */
public class b {
    private static String a = "cloudcontrol Md5";

    public static boolean a(String str, File file) {
        if (TextUtils.isEmpty(str) || file == null) {
            a.i(a, "MD5 string empty or updateFile null");
            return false;
        }
        String a2 = a(file);
        if (a2 == null) {
            a.i(a, "calculatedDigest null");
            return false;
        }
        a.a(a, "Calculated digest: " + a2);
        a.a(a, "Provided digest: " + str);
        return a2.equalsIgnoreCase(str);
    }

    public static String a(File file) {
        String str = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            Log.e(a, "Exception on closing MD5 input stream", e2);
                        }
                        throw th;
                    }
                }
                str = String.format("%32s", new Object[]{new BigInteger(1, instance.digest()).toString(16)}).replace(' ', '0');
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.e(a, "Exception on closing MD5 input stream", e3);
                }
            } catch (FileNotFoundException e4) {
                Log.e(a, "Exception while getting FileInputStream", e4);
            }
        } catch (NoSuchAlgorithmException e5) {
            a.d(a, "Exception while getting digest", e5);
        }
        return str;
    }
}
