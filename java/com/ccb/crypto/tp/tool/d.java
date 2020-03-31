package com.ccb.crypto.tp.tool;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.a.a.a.a;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class d implements a {
    private static boolean c = false;
    /* access modifiers changed from: private */
    public static LocationManager e;
    private static String f;
    /* access modifiers changed from: private */
    public static LocationListener g;
    /* access modifiers changed from: private */
    public static String h;
    public Context a;
    private boolean b = false;
    private a d;

    public d(Context context, String str) {
        this.a = context;
        k(str);
    }

    public String a(String str) {
        return a(str, "edd1c3ed86bb9695d4f8c9baa67c42d8fcba697cd953e79c4f35405cf7a17fcfd5044cc465f475d93023e2642bc170d3efe7bbf253a1079f56c1b1fe4cba89a9daf5c99be65807e39eaedfc794bc45a49761e9419b444a8b018d230a8a78f744f20f987a336642f4c1455603a36006c3a2d825e9b3f2240e8834b9a0e301b59d58b7d3b676828dd5ceef56f45a60db54a30e7da0a5d64b2bca99831428086a9adb30a2feea88e402a6dbd78cc8d77ac97b31023c");
    }

    public String a(String[] strArr, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(b(strArr[i2], "17d3861678effad2b5a537afb03753b15dc0028ca91bacf2ac61fdb8640fb496a870ea4f06f9a9bac5c6f3c3d20b4723ccf5b25df2d5a8f827c67f3a51445bdc06db674b228fce14d76ae4a1769065372e4c3e76c9295bc4648d020b05f16df654f20fb4e271c7cf5cc53b66150467e51ae3f7d9b2bfb88961762c33d63da8be0f8512feca58fd79b4c51a0b7f4f953390a7c548ef7a4c44d3eeca6ef83ff4e6d7e2a1ebf339ece6e2362038e04d963e6d38134d"));
        }
        return sb.toString();
    }

    public String b(String str) {
        if (!this.d.m().equals("3.0")) {
            return TpSafeUtils.te(this.d.b(), this.d.c().getBytes(), str);
        }
        return TpSafeUtils.te2(this.d.l(), this.d.b(), this.d.c().getBytes(), str);
    }

    private String a(String str, String str2) {
        return TpSafeUtils.ae(str2, null, str);
    }

    private String b(String str, String str2) {
        return TpSafeUtils.ad(str2, null, str);
    }

    public String c(String str) {
        if (!c) {
            return null;
        }
        if (!this.d.m().equals("3.0")) {
            return TpSafeUtils.te(this.d.b(), this.d.c().getBytes(), str);
        }
        return TpSafeUtils.te2(this.d.l(), this.d.b(), this.d.c().getBytes(), str);
    }

    public String d(String str) {
        if (!c) {
            return null;
        }
        if (!this.d.m().equals("3.0")) {
            return TpSafeUtils.ad(this.d.d(), null, str);
        }
        return TpSafeUtils.ad2(this.d.l(), this.d.d(), null, str);
    }

    public String e(String str) {
        if (!c) {
            return null;
        }
        return TpSafeUtils.ae("edd1c3ed86bb9695d4f8c9baa67c42d8fcba697cd953e79c4f35405cf7a17fcfd5044cc465f475d93023e2642bc170d3efe7bbf253a1079f56c1b1fe4cba89a9daf5c99be65807e39eaedfc794bc45a49761e9419b444a8b018d230a8a78f744f20f987a336642f4c1455603a36006c3a2d825e9b3f2240e8834b9a0e301b59d58b7d3b676828dd5ceef56f45a60db54a30e7da0a5d64b2bca99831428086a9adb30a2feea88e402a6dbd78cc8d77ac97b31023c", null, str);
    }

    public String f(String str) {
        if (!c) {
            return null;
        }
        return TpSafeUtils.ad("17d3861678effad2b5a537afb03753b15dc0028ca91bacf2ac61fdb8640fb496a870ea4f06f9a9bac5c6f3c3d20b4723ccf5b25df2d5a8f827c67f3a51445bdc06db674b228fce14d76ae4a1769065372e4c3e76c9295bc4648d020b05f16df654f20fb4e271c7cf5cc53b66150467e51ae3f7d9b2bfb88961762c33d63da8be0f8512feca58fd79b4c51a0b7f4f953390a7c548ef7a4c44d3eeca6ef83ff4e6d7e2a1ebf339ece6e2362038e04d963e6d38134d", null, str);
    }

    public String g(String str) {
        if (!c) {
            return null;
        }
        return TpSafeUtils.ae("139fa5134fee5ae439448a75bba075441ddd68dbed5aefd2d6e835eb305480da5bdc1264f73e7dd3a81d82deb32e59e3762e3f3e18e527a3773194c5dde1e715668bf4d103effc6a6b0f449e824db3f5f14a370729bcd5b18cd4cbb2e84c34087e9d2c15ab6c8ed24b2a84634612904af52d9f7cec98bf4a19205d764ebc3fbd7777f7feebfc20d590a52570bdec7608045ddaf9c55e45d4ec249ec27d45faffc646df81b97a7614e294fabc5fef787496cf530b", null, str);
    }

    public String h(String str) {
        if (!c) {
            return null;
        }
        return TpSafeUtils.ad("ffba45fe5c6ef437484ccd9eff887ee40703bd60dcbab0b82ec5d3481f8b979f3a72c7ef18bab60e2e05ada13957483e107af2a0dd5726d58e3ca3a8c9140400d08c56cec36fdfd99cf60ac7f5bba2773c5fbb68c9f281b4ce960d6d640388a6fbbf0e990866e3b66936cf7130fac7a07299c1a9ac4724a7dda3d032ddc9358d6f5519f6fba0329f467281e87c7c0d5a414713290f7e040e0734034ce91bb235cf4672d041cc997a39cfa0fb30bafbcfd5d18525", null, str);
    }

    public String i(String str) {
        if (!c) {
            return null;
        }
        return TpSafeUtils.hm(this.d.e(), str);
    }

    public String a() {
        return this.d.i();
    }

    public boolean b() {
        String a2;
        if (!this.d.a()) {
            String packageName = this.a.getPackageName();
            try {
                Signature[] signatureArr = this.a.getPackageManager().getPackageInfo(packageName, 64).signatures;
                if (!this.d.m().equals("3.0")) {
                    a2 = signatureArr[0].toCharsString();
                } else {
                    a2 = a(signatureArr[0].toByteArray());
                }
                if (TpSafeUtils.sc(this.d.k(), a2) != 0) {
                    c = false;
                    return false;
                } else if (!packageName.equals(this.d.h())) {
                    c = false;
                    return false;
                } else {
                    c = true;
                    return true;
                }
            } catch (NameNotFoundException e2) {
                e2.printStackTrace();
                return false;
            }
        } else if (TpSafeUtils.sc(this.d.k(), this.d.j()) == 0) {
            c = true;
            return true;
        } else {
            c = false;
            return false;
        }
    }

    private String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            byte[] digest = instance.digest();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString().toUpperCase();
    }

    public String c() {
        return this.d.f();
    }

    public String d() {
        return this.d.g();
    }

    public String e() {
        return this.d.h();
    }

    public String f() {
        File file = new File(this.a.getFilesDir().getAbsolutePath() + File.separator + "ccb_esafe_dt");
        if (!file.exists()) {
            return a(file);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String str = "";
        if (fileInputStream != null) {
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            if (stringBuffer.length() <= 0) {
                return a(file);
            }
            String ad = TpSafeUtils.ad("17d3861678effad2b5a537afb03753b15dc0028ca91bacf2ac61fdb8640fb496a870ea4f06f9a9bac5c6f3c3d20b4723ccf5b25df2d5a8f827c67f3a51445bdc06db674b228fce14d76ae4a1769065372e4c3e76c9295bc4648d020b05f16df654f20fb4e271c7cf5cc53b66150467e51ae3f7d9b2bfb88961762c33d63da8be0f8512feca58fd79b4c51a0b7f4f953390a7c548ef7a4c44d3eeca6ef83ff4e6d7e2a1ebf339ece6e2362038e04d963e6d38134d", null, stringBuffer.toString());
            if (ad == null || TextUtils.isEmpty(ad)) {
                return a(file);
            }
            return ad;
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (fileInputStream == null) {
            return null;
        }
        fileInputStream.close();
        return null;
    }

    private String a(File file) {
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        String m = m();
        String ae = TpSafeUtils.ae("edd1c3ed86bb9695d4f8c9baa67c42d8fcba697cd953e79c4f35405cf7a17fcfd5044cc465f475d93023e2642bc170d3efe7bbf253a1079f56c1b1fe4cba89a9daf5c99be65807e39eaedfc794bc45a49761e9419b444a8b018d230a8a78f744f20f987a336642f4c1455603a36006c3a2d825e9b3f2240e8834b9a0e301b59d58b7d3b676828dd5ceef56f45a60db54a30e7da0a5d64b2bca99831428086a9adb30a2feea88e402a6dbd78cc8d77ac97b31023c", null, m);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(ae.getBytes());
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return m;
    }

    private String m() {
        String deviceId = ((TelephonyManager) this.a.getSystemService("phone")).getDeviceId();
        if (deviceId == null || TextUtils.isEmpty(deviceId)) {
        }
        return TpSafeUtils.hs(deviceId);
    }

    private void k(String str) {
        this.d = new a(TpSafeUtils.ad("ffba45fe5c6ef437484ccd9eff887ee40703bd60dcbab0b82ec5d3481f8b979f3a72c7ef18bab60e2e05ada13957483e107af2a0dd5726d58e3ca3a8c9140400d08c56cec36fdfd99cf60ac7f5bba2773c5fbb68c9f281b4ce960d6d640388a6fbbf0e990866e3b66936cf7130fac7a07299c1a9ac4724a7dda3d032ddc9358d6f5519f6fba0329f467281e87c7c0d5a414713290f7e040e0734034ce91bb235cf4672d041cc997a39cfa0fb30bafbcfd5d18525", null, str));
    }

    public synchronized String g() {
        String str;
        if (!c) {
            str = null;
        } else {
            try {
                str = ((TelephonyManager) this.a.getSystemService("phone")).getDeviceId();
                if (str == null || TextUtils.isEmpty(str)) {
                    str = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                str = null;
            }
        }
        return str;
    }

    public synchronized boolean h() {
        boolean z;
        if (g != null) {
            z = true;
        } else if (ActivityCompat.checkSelfPermission(this.a, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.a, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            String str = "location";
            if (e == null) {
                e = (LocationManager) this.a.getSystemService(str);
            }
            if (f == null) {
                f = "gps";
            }
            g = new b(this);
            e.requestLocationUpdates(f, 10, 0.0f, g);
            new c(this).start();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized String i() {
        String str;
        if (h == null || TextUtils.isEmpty(h)) {
            str = null;
        } else {
            str = h;
        }
        return str;
    }
}
