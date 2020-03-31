package cn.sharesdk.tencent.weibo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.b;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.d;
import cn.sharesdk.framework.authorize.g;
import cn.sharesdk.framework.e;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: Weibo */
public class f extends e {
    private static f b;
    /* access modifiers changed from: private */
    public c c = new c();
    private b d = b.a();

    public static f a(Platform platform) {
        if (b == null) {
            b = new f(platform);
        }
        return b;
    }

    private f(Platform platform) {
        super(platform);
    }

    public void a(String str, String str2) {
        this.c.a = str;
        this.c.b = str2;
    }

    public void a(String str) {
        this.c.c = str;
    }

    public c getAuthorizeWebviewClient(g gVar) {
        return new d(gVar);
    }

    public cn.sharesdk.framework.authorize.e getSSOProcessor(d dVar) {
        e eVar = new e(dVar);
        eVar.a(this.c);
        return eVar;
    }

    public void a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            b(authorizeListener);
        } else {
            a(new SSOListener() {
                public void onFailed(Throwable th) {
                    SSDKLog.b().d(th);
                    f.this.b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("access_token", f.this.c.d);
                    bundle2.putString("expires_in", String.valueOf(f.this.c.h));
                    bundle2.putString("openid", String.valueOf(f.this.c.f));
                    bundle2.putString("name", String.valueOf(f.this.c.e));
                    bundle2.putString("nick", String.valueOf(f.this.c.e));
                    authorizeListener.onComplete(bundle2);
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public String getAuthorizeUrl() {
        ShareSDK.logApiEvent("/cgi-bin/oauth2/authorize", c());
        return "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=" + this.c.a + "&response_type=code&redirect_uri=" + this.c.c;
    }

    public String getRedirectUri() {
        return this.c.c;
    }

    public Bundle b(String str) throws Throwable {
        String str2 = "/cgi-bin/oauth2/access_token";
        String a = this.d.a("https://open.t.qq.com" + str2 + "?client_id=" + this.c.a + "&client_secret=" + this.c.b + "&redirect_uri=" + this.c.c + "&grant_type=authorization_code&code=" + str, null, str2, c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return ResHelper.urlToBundle("http://open.t.qq.com?" + a);
    }

    public void a(String str, String str2, String str3, String str4) {
        this.c.d = str;
        this.c.e = str2;
        this.c.f = str3;
        this.c.g = str4;
    }

    private void a(ArrayList<KVPair<String>> arrayList) {
        arrayList.add(new KVPair("oauth_consumer_key", this.c.a));
        arrayList.add(new KVPair("access_token", this.c.d));
        arrayList.add(new KVPair("openid", this.c.f));
        arrayList.add(new KVPair("clientip", "127.0.0.1"));
        arrayList.add(new KVPair("oauth_version", "2.a"));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("scope", "all"));
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        String str2 = "/api/user/info";
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (str != null) {
            str2 = "/api/user/other_info";
            arrayList.add(new KVPair("name", str));
        }
        String a = this.d.a("https://open.t.qq.com" + str2, arrayList, str2, c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> a(String str, float f, float f2) throws Throwable {
        String str2 = "https://open.t.qq.com/api/t/add";
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("content", str));
        if (!(f == 0.0f || f2 == 0.0f)) {
            arrayList.add(new KVPair("latitude", String.valueOf(f)));
            arrayList.add(new KVPair("longitude", String.valueOf(f2)));
        }
        String b2 = this.d.b(str2, arrayList, "/api/t/add", c());
        if (b2 == null || b2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(b2);
    }

    public HashMap<String, Object> a(String str, String str2, float f, float f2) throws Throwable {
        String str3 = "https://open.t.qq.com/api/t/add_pic_url";
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("content", str));
        arrayList.add(new KVPair("pic_url", str2));
        if (!(f == 0.0f || f2 == 0.0f)) {
            arrayList.add(new KVPair("longitude", String.valueOf(f2)));
            arrayList.add(new KVPair("latitude", String.valueOf(f)));
        }
        String b2 = this.d.b(str3, arrayList, "/api/t/add_pic_url", c());
        if (b2 == null || b2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(b2);
    }

    public HashMap<String, Object> b(String str, String str2, float f, float f2) throws Throwable {
        String str3 = "https://open.t.qq.com/api/t/add_pic";
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("content", str));
        KVPair kVPair = new KVPair("pic", str2);
        if (!(f == 0.0f || f2 == 0.0f)) {
            arrayList.add(new KVPair("longitude", String.valueOf(f2)));
            arrayList.add(new KVPair("latitude", String.valueOf(f)));
        }
        String a = this.d.a(str3, arrayList, kVPair, "/api/t/add_pic", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public String d(String str) throws Throwable {
        KVPair kVPair;
        String str2 = "https://open.t.qq.com/api/t/upload_pic";
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            arrayList.add(new KVPair("pic_url", str));
            arrayList.add(new KVPair("pic_type", "1"));
            kVPair = null;
        } else if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        } else {
            kVPair = new KVPair("pic", str);
            arrayList.add(new KVPair("pic_type", "2"));
        }
        String a = this.d.a(str2, arrayList, kVPair, "/api/t/upload_pic", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        HashMap fromJson = new Hashon().fromJson(a);
        if (!fromJson.containsKey("ret")) {
            return null;
        }
        if (((Integer) fromJson.get("ret")).intValue() == 0) {
            return (String) ((HashMap) fromJson.get("data")).get("imgurl");
        }
        String str3 = "tecent weibo uploadPic == " + str + " == error " + fromJson.get(NotificationCompat.CATEGORY_MESSAGE) + "(" + ((Integer) fromJson.get("errcode")).intValue() + ")";
        SSDKLog.b().d(str3, new Object[0]);
        throw new Throwable(str3);
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("name", str));
        String b2 = this.d.b("https://open.t.qq.com/api/friends/add", arrayList, "/api/friends/add", c());
        if (b2 == null || b2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(b2);
    }

    public HashMap<String, Object> a(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("reqnum", String.valueOf(i)));
        arrayList.add(new KVPair("startindex", String.valueOf(i2)));
        arrayList.add(new KVPair("name", str));
        arrayList.add(new KVPair("mode", "0"));
        String a = this.d.a("https://open.t.qq.com/api/friends/user_idollist", arrayList, "/api/friends/user_idollist", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> b(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        arrayList.add(new KVPair("reqnum", String.valueOf(i)));
        arrayList.add(new KVPair("startindex", String.valueOf(i2)));
        arrayList.add(new KVPair("name", str));
        arrayList.add(new KVPair("mode", "0"));
        String a = this.d.a("https://open.t.qq.com/api/friends/user_fanslist", arrayList, "/api/friends/user_fanslist", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        KVPair kVPair;
        String str3;
        if (str2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        a(arrayList);
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            KVPair kVPair2 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = kVPair2;
        }
        try {
            if ("GET".equals(str2.toUpperCase())) {
                str3 = new NetworkHelper().httpGet(str, arrayList, null, null);
            } else {
                if ("POST".equals(str2.toUpperCase())) {
                    str3 = new NetworkHelper().httpPost(str, arrayList, kVPair, null, (NetworkTimeOut) null);
                }
                str3 = null;
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
        if (str3 == null || str3.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str3);
    }

    public boolean a() {
        PackageManager packageManager = MobSDK.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.tencent.WBlog", 16);
            if (packageInfo == null || packageInfo.versionCode < 44 || packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("TencentAuth://weibo")), 65536).size() <= 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
