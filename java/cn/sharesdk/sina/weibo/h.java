package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.b;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.d;
import cn.sharesdk.framework.authorize.g;
import cn.sharesdk.framework.e;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.a;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: Weibo */
public class h extends e {
    private static h b;
    private String c;
    private String d;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    private String[] g = {"follow_app_official_microblog"};
    private b h = b.a();

    public static synchronized h a(Platform platform) {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h(platform);
            }
            hVar = b;
        }
        return hVar;
    }

    private h(Platform platform) {
        super(platform);
    }

    public void a(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.g = strArr;
        }
    }

    public c getAuthorizeWebviewClient(g gVar) {
        return new d(gVar);
    }

    public cn.sharesdk.framework.authorize.e getSSOProcessor(d dVar) {
        e eVar = new e(dVar);
        eVar.a(32973);
        eVar.a(this.c, this.e, this.g);
        return eVar;
    }

    public void a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            a(authorizeListener);
        } else {
            a(new SSOListener() {
                public void onFailed(Throwable th) {
                    SSDKLog.b().d(th);
                    h.this.a(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    c.a().a(1);
                    try {
                        ResHelper.parseLong(bundle.getString("expires_in"));
                        authorizeListener.onComplete(bundle);
                    } catch (Throwable th) {
                        onFailed(th);
                    }
                }

                public void onCancel() {
                    c.a().a(1);
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public String getAuthorizeUrl() {
        return "";
    }

    public String getRedirectUri() {
        return TextUtils.isEmpty(this.e) ? "https://api.weibo.com/oauth2/default.html" : this.e;
    }

    public String b(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.c));
        arrayList.add(new KVPair("client_secret", this.d));
        arrayList.add(new KVPair("redirect_uri", this.e));
        arrayList.add(new KVPair("grant_type", "authorization_code"));
        arrayList.add(new KVPair("code", str));
        String b2 = this.h.b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", c());
        ShareSDK.logApiEvent("/oauth2/access_token", c());
        return b2;
    }

    public boolean a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.c));
        arrayList.add(new KVPair("client_secret", this.d));
        arrayList.add(new KVPair("redirect_uri", this.e));
        arrayList.add(new KVPair("grant_type", "refresh_token"));
        arrayList.add(new KVPair("refresh_token", this.a.getDb().get("refresh_token")));
        try {
            String b2 = this.h.b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", c());
            if (TextUtils.isEmpty(b2) || b2.contains("error") || b2.contains("error_code")) {
                return false;
            }
            HashMap fromJson = new Hashon().fromJson(b2);
            String valueOf = String.valueOf(fromJson.get(Config.CUSTOM_USER_ID));
            String valueOf2 = String.valueOf(fromJson.get("expires_in"));
            this.f = String.valueOf(fromJson.get("access_token"));
            String valueOf3 = String.valueOf(fromJson.get("refresh_token"));
            String valueOf4 = String.valueOf(fromJson.get("remind_in"));
            this.a.getDb().putUserId(valueOf);
            this.a.getDb().putExpiresIn(Long.valueOf(valueOf2).longValue());
            this.a.getDb().putToken(this.f);
            this.a.getDb().put("refresh_token", valueOf3);
            this.a.getDb().put("remind_in", valueOf4);
            return true;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return false;
        }
    }

    public void c(String str) {
        this.f = str;
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.h.a("https://api.weibo.com/2/users/show.json", arrayList, "/2/users/show.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public boolean b() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.sina.weibo");
        intent.setType("image/*");
        ResolveInfo resolveActivity = MobSDK.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setPackage("com.sina.weibog3");
            intent2.setType("image/*");
            resolveActivity = MobSDK.getContext().getPackageManager().resolveActivity(intent2, 0);
        }
        if (resolveActivity != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void a(AuthorizeListener authorizeListener) {
        f fVar = new f();
        fVar.a(this.c, this.e, this.f);
        fVar.a(authorizeListener);
        fVar.show(MobSDK.getContext(), null);
    }

    public void a(final ShareParams shareParams, final PlatformActionListener platformActionListener) {
        if (shareParams.getImageData() == null && TextUtils.isEmpty(shareParams.getImagePath()) && !TextUtils.isEmpty(shareParams.getImageUrl())) {
            try {
                File file = new File(BitmapHelper.downloadBitmap(MobSDK.getContext(), shareParams.getImageUrl()));
                if (file.exists()) {
                    shareParams.setImagePath(file.getAbsolutePath());
                }
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
        }
        if (shareParams.getImageArray() != null && shareParams.getImageArray().length > 0) {
            try {
                List<String> asList = Arrays.asList(shareParams.getImageArray());
                String[] strArr = new String[asList.size()];
                int i = 0;
                for (String str : asList) {
                    if (str.startsWith("http")) {
                        str = BitmapHelper.downloadBitmap(MobSDK.getContext(), str);
                    }
                    File file2 = new File(str);
                    if (file2.exists() && str.startsWith("/data/")) {
                        File file3 = new File(ResHelper.getCachePath(MobSDK.getContext(), "images"), System.currentTimeMillis() + file2.getName());
                        String absolutePath = file3.getAbsolutePath();
                        file3.createNewFile();
                        if (ResHelper.copyFile(str, absolutePath)) {
                            str = file3.getAbsolutePath();
                        }
                    }
                    strArr[i] = str;
                    i++;
                }
                shareParams.setImageArray(strArr);
            } catch (Throwable th2) {
                SSDKLog.b().d(th2);
            }
        }
        String text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(getPlatform().getShortLintk(text, false));
        }
        AnonymousClass2 r0 = new AuthorizeListener() {
            public void onError(Throwable th) {
                if (platformActionListener != null) {
                    platformActionListener.onError(h.this.a, 9, th);
                }
            }

            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(h.this.a, 9, hashMap);
                }
            }

            public void onCancel() {
                if (platformActionListener != null) {
                    platformActionListener.onCancel(h.this.a, 9);
                }
            }
        };
        a aVar = new a();
        aVar.a(this.c);
        aVar.a(shareParams);
        aVar.a((AuthorizeListener) r0);
        aVar.show(MobSDK.getContext(), null);
    }

    public void b(final ShareParams shareParams, final PlatformActionListener platformActionListener) {
        String str = TextUtils.isEmpty(shareParams.getUrl()) ? shareParams.getText() : shareParams.getText() + " " + shareParams.getUrl();
        if (!TextUtils.isEmpty(str)) {
            shareParams.setText(this.a.getShortLintk(str, false));
        } else {
            int stringRes = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_weibo_upload_content");
            if (stringRes > 0) {
                shareParams.setText(MobSDK.getContext().getResources().getString(stringRes));
            }
        }
        AnonymousClass3 r0 = new AuthorizeListener() {
            public void onError(Throwable th) {
                if (platformActionListener != null) {
                    platformActionListener.onError(h.this.a, 9, th);
                }
            }

            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(h.this.a, 9, hashMap);
                }
                if (bundle != null) {
                    String string = bundle.getString(Config.CUSTOM_USER_ID);
                    String string2 = bundle.getString("access_token");
                    String string3 = bundle.getString("expire_in");
                    if (!TextUtils.isEmpty(string2)) {
                        h.this.f = string2;
                        h.this.a.getDb().putToken(h.this.f);
                    }
                    h.this.a.getDb().putUserId(string);
                    try {
                        h.this.a.getDb().putExpiresIn(ResHelper.parseLong(string3));
                    } catch (Throwable th) {
                    }
                }
            }

            public void onCancel() {
                if (platformActionListener != null) {
                    platformActionListener.onCancel(h.this.a, 9);
                }
            }
        };
        g gVar = new g();
        gVar.a(this.c, this.f);
        gVar.a(shareParams);
        gVar.a((AuthorizeListener) r0);
        gVar.show(MobSDK.getContext(), null);
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        arrayList.add(new KVPair("access_token", this.f));
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String b2 = this.h.b("https://api.weibo.com/2/friendships/create.json", arrayList, "/2/friendships/create.json", c());
        if (b2 != null) {
            return new Hashon().fromJson(b2);
        }
        return null;
    }

    public HashMap<String, Object> a(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(Config.TRACE_VISIT_RECENT_COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/statuses/user_timeline.json", arrayList, "/2/statuses/user_timeline.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> b(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(Config.TRACE_VISIT_RECENT_COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/friends.json", arrayList, "/2/friendships/friends.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> c(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(Config.TRACE_VISIT_RECENT_COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/friends/bilateral.json", arrayList, "/2/friendships/friends/bilateral.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    public HashMap<String, Object> d(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
        boolean z = true;
        try {
            ResHelper.parseLong(str);
        } catch (Throwable th) {
            z = false;
        }
        if (z) {
            arrayList.add(new KVPair(Config.CUSTOM_USER_ID, str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair(Config.TRACE_VISIT_RECENT_COUNT, String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.h.a("https://api.weibo.com/2/friendships/followers.json", arrayList, "/2/friendships/followers.json", c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
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
        arrayList.add(new KVPair("source", this.c));
        if (this.f != null) {
            arrayList.add(new KVPair("access_token", this.f));
        }
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

    public boolean d() {
        Intent intent = new Intent();
        intent.setAction("com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER");
        String packageName = MobSDK.getContext().getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", this.c);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", a.a(MobSDK.getContext(), packageName));
        SSDKLog.b().d("intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
        MobSDK.getContext().sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
        return true;
    }
}
