package cn.sharesdk.tencent.qzone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.g;
import cn.sharesdk.framework.e;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.RxMob;
import com.mob.tools.RxMob.OnSubscribe;
import com.mob.tools.RxMob.Subscribable;
import com.mob.tools.RxMob.Subscriber;
import com.mob.tools.RxMob.Thread;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.tauth.c;
import com.tencent.tauth.d;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: QZoneHelper */
public class b extends e {
    private static final String[] c = {"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};
    private static b d;
    com.tencent.tauth.b b = new com.tencent.tauth.b() {
        public void onCancel() {
            if (b.this.l != null) {
                b.this.l.onCancel(b.this.a, 9);
            }
        }

        public void onComplete(Object obj) {
            try {
                if (b.this.l != null) {
                    b.this.l.onComplete(b.this.a, 9, new Hashon().fromJson(obj.toString()));
                }
            } catch (Throwable th) {
                SSDKLog.b().d("qqShareListener onComplete catch " + th, new Object[0]);
            }
        }

        public void onError(d dVar) {
            try {
                if (dVar.b != null) {
                    String valueOf = String.valueOf(dVar.b);
                    if (b.this.l != null) {
                        b.this.l.onError(null, 9, new Throwable(valueOf));
                    }
                }
            } catch (Throwable th) {
                SSDKLog.b().d("qqShareListener onError catch " + th, new Object[0]);
            }
        }
    };
    private String e;
    private String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    private cn.sharesdk.framework.a.b i = cn.sharesdk.framework.a.b.a();
    private String[] j;
    private c k;
    /* access modifiers changed from: private */
    public PlatformActionListener l;

    public static b a(Platform platform) {
        if (d == null) {
            d = new b(platform);
        }
        return d;
    }

    private b(Platform platform) {
        super(platform);
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(String[] strArr) {
        this.j = strArr;
    }

    public void a(final AuthorizeListener authorizeListener, boolean z) {
        a(new SSOListener() {
            public void onFailed(Throwable th) {
                if (th != null) {
                    authorizeListener.onError(th);
                } else {
                    authorizeListener.onError(new Throwable("Unknown Throwable!"));
                }
            }

            public void onComplete(Bundle bundle) {
                authorizeListener.onComplete(bundle);
            }

            public void onCancel() {
                authorizeListener.onCancel();
            }
        });
    }

    public String getAuthorizeUrl() {
        String redirectUri;
        ShareSDK.logApiEvent("/oauth2.0/authorize", c());
        String e2 = e();
        try {
            redirectUri = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            redirectUri = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.e + "&redirect_uri=" + redirectUri + "&display=mobile&scope=" + e2;
    }

    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    public cn.sharesdk.framework.authorize.c getAuthorizeWebviewClient(g gVar) {
        return new a(gVar);
    }

    private String e() {
        String[] strArr = this.j == null ? c : this.j;
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (String str : strArr) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append(str);
            i2++;
        }
        return sb.toString();
    }

    public cn.sharesdk.framework.authorize.e getSSOProcessor(cn.sharesdk.framework.authorize.d dVar) {
        c cVar = new c(dVar);
        cVar.a(5656);
        cVar.a(this.e, e());
        return cVar;
    }

    public void a() {
        Subscribable create = RxMob.create(new OnSubscribe() {
            public void call(Subscriber subscriber) {
                String str = "https://graph.qq.com/oauth2.0/me";
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair("access_token", b.this.g));
                arrayList.add(new KVPair("unionid", "1"));
                NetworkTimeOut networkTimeOut = new NetworkTimeOut();
                networkTimeOut.readTimout = 10000;
                networkTimeOut.connectionTimeout = 10000;
                try {
                    b.this.h = cn.sharesdk.framework.a.b.a().httpPost(str, arrayList, null, null, networkTimeOut);
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    b.this.a.getDb().put("unionid", "");
                    SSDKLog.b().d("qq auth, get unionId fail", new Object[0]);
                }
            }
        });
        create.subscribeOn(Thread.NEW_THREAD);
        create.observeOn(Thread.IMMEDIATE);
        create.subscribe(new Subscriber() {
            public void onCompleted() {
                if (b.this.h != null && b.this.h.length() > 0) {
                    b.this.h = b.this.h.replace("callback( ", "");
                    b.this.h = b.this.h.replace(" );", "");
                    HashMap fromJson = new Hashon().fromJson(b.this.h);
                    if (fromJson.containsKey("unionid")) {
                        b.this.a.getDb().put("unionid", (String) fromJson.get("unionid"));
                        return;
                    }
                    b.this.a.getDb().put("unionid", "");
                }
            }

            public void onError(Throwable th) {
                b.this.a.getDb().put("unionid", "");
                SSDKLog.b().d("qq auth, get unionId fail", new Object[0]);
            }
        });
    }

    public void b(String str) {
        this.f = str;
    }

    public void c(String str) {
        this.g = str;
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("oauth_consumer_key", this.e));
        arrayList.add(new KVPair("openid", this.f));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.i.a("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, (NetworkTimeOut) null, "/user/get_simple_userinfo", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public void a(int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, PlatformActionListener platformActionListener) throws Throwable {
        String str11;
        String str12;
        if (TextUtils.isEmpty(str5)) {
            str5 = DeviceHelper.getInstance(MobSDK.getContext()).getAppName();
        }
        if (str5.length() > 20) {
            str11 = str5.substring(0, 20) + "...";
        } else {
            str11 = str5;
        }
        if (TextUtils.isEmpty(str) || str.length() <= 200) {
            str12 = str;
        } else {
            str12 = str.substring(0, 200);
        }
        if (platformActionListener != null) {
            this.l = platformActionListener;
        }
        if (i2 != 15) {
            a(i2, str12, str2, str3, str4, str11, str6, platformActionListener);
        } else if (ShareSDK.getAuthActivity() != null) {
            Activity authActivity = ShareSDK.getAuthActivity();
            this.k = c.a(this.e, (Context) authActivity);
            a(str2, "TitleUrl", platformActionListener);
            a(str7, "MiniAppId", platformActionListener);
            a(str8, "MiniAppId", platformActionListener);
            ArrayList arrayList = new ArrayList();
            try {
                if (!TextUtils.isEmpty(str10)) {
                    arrayList.add(str10);
                }
            } catch (Throwable th) {
                SSDKLog.b().d("imageUrlList error " + th, new Object[0]);
            }
            Bundle bundle = new Bundle();
            bundle.putString("title", str12);
            bundle.putString("summary", str3);
            bundle.putString("targetUrl", str2);
            bundle.putStringArrayList("imageUrl", arrayList);
            bundle.putString("mini_program_appid", str7);
            bundle.putString("mini_program_path", str8);
            bundle.putString("mini_program_type", str9);
            bundle.putInt("req_type", 7);
            if (this.k != null) {
                this.k.b(authActivity, bundle, this.b);
            }
        } else {
            platformActionListener.onError(null, 9, new Throwable("Please setActivity"));
        }
    }

    private void a(String str, String str2, PlatformActionListener platformActionListener) {
        if (TextUtils.isEmpty(str) && platformActionListener != null) {
            platformActionListener.onError(null, 9, new Throwable(str2 + "TitleUrl cannot be empty"));
        }
    }

    public void a(int i2, String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) throws Throwable {
        String str7 = "1";
        if (!TextUtils.isEmpty(str6)) {
            str7 = "4";
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            str7 = "3";
        } else if (TextUtils.isEmpty(str2)) {
            if (platformActionListener != null) {
                platformActionListener.onError(null, 9, new Throwable("The param of title or titleUrl is null !"));
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(str4)) {
            File file = new File(str4);
            if (file.exists() && str4.startsWith("/data/")) {
                String absolutePath = new File(ResHelper.getCachePath(MobSDK.getContext(), "images"), System.currentTimeMillis() + file.getName()).getAbsolutePath();
                if (ResHelper.copyFile(str4, absolutePath)) {
                    str4 = absolutePath;
                } else {
                    str4 = null;
                }
            }
        }
        if (!TextUtils.isEmpty(str3) && str3.length() > 600) {
            str3 = str3.substring(0, 600);
        }
        StringBuilder sb = new StringBuilder();
        if (str7 == "3" || str7 == "4") {
            sb.append("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        } else {
            sb.append("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("&image_url=").append(Base64.encodeToString(str4.getBytes("utf-8"), 2));
        }
        if (!TextUtils.isEmpty(str6) && str7.equals("4")) {
            ResHelper.getFileSize(str6);
            String valueOf = String.valueOf(str5);
            String f2 = f(str6);
            sb.append("&videoPath=").append(Base64.encodeToString(str6.getBytes("utf-8"), 2));
            sb.append("&videoSize=").append(Base64.encodeToString(valueOf.getBytes("utf-8"), 2));
            if (!TextUtils.isEmpty(f2)) {
                sb.append("&videoDuration=").append(Base64.encodeToString(f2.getBytes("utf-8"), 2));
            }
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append("&title=").append(Base64.encodeToString(str.getBytes("utf-8"), 2));
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("&description=").append(Base64.encodeToString(str3.getBytes("utf-8"), 2));
        }
        sb.append("&share_id=").append(this.e);
        if (!TextUtils.isEmpty(str2)) {
            sb.append("&url=").append(Base64.encodeToString(str2.getBytes("utf-8"), 2));
        }
        sb.append("&app_name=").append(Base64.encodeToString(str5.getBytes("utf-8"), 2));
        if (!TextUtils.isEmpty(str3)) {
            sb.append("&share_qq_ext_str=").append(Base64.encodeToString(str3.getBytes(), 2));
        }
        sb.append("&req_type=").append(Base64.encodeToString(str7.getBytes("utf-8"), 2));
        sb.append("&cflag=").append(Base64.encodeToString((d() ? "1" : "0").getBytes("utf-8"), 2));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(sb.toString()));
        if (MobSDK.getContext().getPackageManager().resolveActivity(intent, 1) != null) {
            d dVar = new d();
            dVar.a(sb.toString(), true);
            dVar.a(platformActionListener);
            dVar.a(this.e);
            dVar.show(MobSDK.getContext(), null);
        }
    }

    private String f(String str) {
        if (VERSION.SDK_INT <= 10) {
            return "";
        }
        try {
            Class cls = ReflectHelper.getClass("android.media.MediaMetadataRetriever");
            Object newInstance = cls.newInstance();
            cls.getMethod("setDataSource", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
            return (String) ReflectHelper.invokeInstanceMethod(newInstance, "extractMetadata", Integer.valueOf(9));
        } catch (Throwable th) {
            return "";
        }
    }

    public boolean b() {
        try {
            PackageInfo packageInfo = MobSDK.getContext().getPackageManager().getPackageInfo(TbsConfig.APP_QZONE, 0);
            if (packageInfo == null) {
                return false;
            }
            String[] split = packageInfo.versionName.split("\\.");
            int[] iArr = new int[split.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                try {
                    iArr[i2] = ResHelper.parseInt(split[i2]);
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                    iArr[i2] = 0;
                }
            }
            if (iArr.length <= 1) {
                return false;
            }
            if (iArr[0] >= 4 || iArr[1] >= 1) {
                return true;
            }
            return false;
        } catch (Throwable th2) {
            SSDKLog.b().d(th2);
            return false;
        }
    }

    public boolean d() {
        String str;
        try {
            str = MobSDK.getContext().getPackageManager().getPackageInfo(TbsConfig.APP_QQ, 0).versionName;
        } catch (Throwable th) {
            try {
                str = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.minihd.qq", 0).versionName;
            } catch (Throwable th2) {
                SSDKLog.b().d(th);
                str = null;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    public HashMap<String, Object> a(String str, String str2) throws Throwable {
        String str3 = "https://graph.qq.com/photo/upload_pic";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("oauth_consumer_key", this.e));
        arrayList.add(new KVPair("openid", this.f));
        arrayList.add(new KVPair("format", "json"));
        if (!TextUtils.isEmpty(str2)) {
            if (str2.length() > 200) {
                str2 = str2.substring(0, 199) + MobSDK.getContext().getString(ResHelper.getStringRes(MobSDK.getContext(), "ssdk_symbol_ellipsis"));
            }
            arrayList.add(new KVPair("photodesc", str2));
        }
        arrayList.add(new KVPair(NetworkUtil.NETWORK_MOBILE, "1"));
        KVPair kVPair = new KVPair("picture", str);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.i.a(str3, arrayList, kVPair, arrayList2, "/photo/upload_pic", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = this.i.a("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, (NetworkTimeOut) null, "/oauth2.0/me", c());
        if (a.startsWith("callback")) {
            while (!a.startsWith("{") && a.length() > 0) {
                a = a.substring(1);
            }
            while (!a.endsWith("}") && a.length() > 0) {
                a = a.substring(0, a.length() - 1);
            }
        }
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
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("oauth_consumer_key", this.e));
        arrayList.add(new KVPair("openid", this.f));
        arrayList.add(new KVPair("format", "json"));
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            KVPair kVPair2 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = kVPair2;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        try {
            if ("GET".equals(str2.toUpperCase())) {
                str3 = new NetworkHelper().httpGet(str, arrayList, arrayList2, null);
            } else {
                if ("POST".equals(str2.toUpperCase())) {
                    str3 = new NetworkHelper().httpPost(str, arrayList, kVPair, arrayList2, (NetworkTimeOut) null);
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

    public HashMap<String, Object> b(String str, String str2) throws Throwable {
        String b2;
        boolean z = !TextUtils.isEmpty(str);
        String str3 = z ? "/t/add_pic_t" : "/t/add_t";
        String str4 = "https://graph.qq.com" + str3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("oauth_consumer_key", this.e));
        arrayList.add(new KVPair("access_token", this.g));
        arrayList.add(new KVPair("openid", this.f));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("content", str2));
        if (z) {
            b2 = this.i.a(str4, arrayList, new KVPair("pic", str), str3, c());
        } else {
            b2 = this.i.b(str4, arrayList, str3, c());
        }
        if (b2 == null || b2.length() <= 0) {
            return null;
        }
        HashMap fromJson = new Hashon().fromJson(b2);
        if (((Integer) fromJson.get("ret")).intValue() == 0) {
            return fromJson;
        }
        throw new Throwable(b2);
    }
}
