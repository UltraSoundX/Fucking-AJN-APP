package com.tencent.smtt.utils;

import android.util.Base64;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.smtt.sdk.a.a;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;

/* compiled from: PostEncryption */
public class i {
    private static final char[] a = "0123456789abcdef".toCharArray();
    private static i b;
    private String c;
    private String d;
    private String e = String.valueOf(new Random().nextInt(89999999) + 10000000);

    private i() {
        int nextInt = new Random().nextInt(89999999) + 10000000;
        this.c = this.e + String.valueOf(nextInt);
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    public void b() throws Exception {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    public String c() throws Exception {
        if (this.d == null) {
            byte[] bytes = this.c.getBytes();
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            } catch (Exception e2) {
                try {
                    b();
                    cipher = Cipher.getInstance("RSA/ECB/NoPadding");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            cipher.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
            this.d = b(cipher.doFinal(bytes));
        }
        return this.d;
    }

    public String a(String str) throws Exception {
        byte[] bytes = str.getBytes();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        } catch (Exception e2) {
            try {
                b();
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        cipher.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
        return b(cipher.doFinal(bytes));
    }

    public byte[] a(byte[] bArr) throws Exception {
        return a.a(this.e.getBytes(), bArr, 1);
    }

    private String b(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i] & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
            cArr[i * 2] = a[b2 >>> 4];
            cArr[(i * 2) + 1] = a[b2 & 15];
        }
        return new String(cArr);
    }
}
