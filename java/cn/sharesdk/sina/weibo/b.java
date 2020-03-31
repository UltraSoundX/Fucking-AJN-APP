package cn.sharesdk.sina.weibo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import java.security.MessageDigest;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: SinaLinkCard */
public class b {
    private static volatile b a = null;
    private MobCommunicator b;
    private String c = MobSDK.getAppkey();
    private String d = MobSDK.getContext().getPackageName();
    private String e = a(this.d);

    private synchronized MobCommunicator b() {
        if (this.b == null) {
            this.b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
        }
        return this.b;
    }

    public static b a() {
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
        }
        return a;
    }

    public HashMap<String, Object> a(String str, JSONObject jSONObject, String str2, String str3, String str4, String str5) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("appkey", this.c);
        hashMap.put("plat", Integer.valueOf(1));
        hashMap.put("androidPk", this.d);
        hashMap.put("androidPkSign", this.e);
        hashMap.put("summary", str);
        hashMap.put("image", jSONObject);
        hashMap.put("object_type", str2);
        hashMap.put("display_name", str3);
        hashMap.put("create_at", str4);
        hashMap.put("url", str5);
        try {
            return (HashMap) b().requestSynchronized(hashMap, "http://lks.share.mob.com/share/genShareInfo", false);
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message != null && message.endsWith("}")) {
                HashMap fromJson = new Hashon().fromJson(message);
                if (fromJson.containsKey("error")) {
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put("error", String.valueOf(fromJson.get("error")));
                    return hashMap2;
                }
            }
            return null;
        }
    }

    public static String a(String str) {
        Signature[] b2 = b(str);
        if (b2 == null || b2.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Signature byteArray : b2) {
            stringBuffer.append(a(byteArray.toByteArray()));
        }
        return stringBuffer.toString();
    }

    private static Signature[] b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            PackageInfo packageInfo = MobSDK.getContext().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                return packageInfo.signatures;
            }
            return null;
        } catch (NameNotFoundException e2) {
            SSDKLog.b().d(e2);
            return null;
        }
    }

    public static final String a(byte[] bArr) {
        byte[] digest;
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            char[] cArr2 = new char[(r4 * 2)];
            int i = 0;
            for (byte b2 : instance.digest()) {
                cArr2[i] = cArr[(b2 >>> 4) & 15];
                int i2 = i + 1;
                cArr2[i2] = cArr[b2 & 15];
                i = i2 + 1;
            }
            return new String(cArr2);
        } catch (Exception e2) {
            SSDKLog.b().d(e2);
            return null;
        }
    }
}
