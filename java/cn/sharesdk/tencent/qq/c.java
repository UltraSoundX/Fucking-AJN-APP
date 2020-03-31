package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.b;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.d;
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
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: QQHelper */
public class c extends e {
    private static final String[] b = {"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};
    private static c c;
    private String d;
    private String[] e;
    private String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    private String i;

    public static c a(Platform platform) {
        if (c == null) {
            c = new c(platform);
        }
        return c;
    }

    private c(Platform platform) {
        super(platform);
        b();
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(String[] strArr) {
        this.e = strArr;
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

    public void b(String str) {
        this.f = str;
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = b.a().a("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, (NetworkTimeOut) null, "/oauth2.0/me", c());
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

    public void a() {
        Subscribable create = RxMob.create(new OnSubscribe() {
            public void call(Subscriber subscriber) {
                String str = "https://graph.qq.com/oauth2.0/me";
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair("access_token", c.this.h));
                arrayList.add(new KVPair("unionid", "1"));
                NetworkTimeOut networkTimeOut = new NetworkTimeOut();
                networkTimeOut.readTimout = 10000;
                networkTimeOut.connectionTimeout = 10000;
                try {
                    c.this.g = b.a().httpPost(str, arrayList, null, null, networkTimeOut);
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    th.printStackTrace();
                    c.this.a.getDb().put("unionid", "");
                    SSDKLog.b().d("qq auth,get unionId fail", new Object[0]);
                }
            }
        });
        create.subscribeOn(Thread.NEW_THREAD);
        create.observeOn(Thread.IMMEDIATE);
        create.subscribe(new Subscriber() {
            public void onCompleted() {
                if (c.this.g != null && c.this.g.length() > 0) {
                    c.this.g = c.this.g.replace("callback( ", "");
                    c.this.g = c.this.g.replace(" );", "");
                    HashMap fromJson = new Hashon().fromJson(c.this.g);
                    if (fromJson.containsKey("unionid")) {
                        c.this.a.getDb().put("unionid", (String) fromJson.get("unionid"));
                        return;
                    }
                    c.this.a.getDb().put("unionid", "");
                }
            }

            public void onError(Throwable th) {
                c.this.a.getDb().put("unionid", "");
                SSDKLog.b().d("qq auth,get unionId fail", new Object[0]);
            }
        });
    }

    public void d(String str) {
        this.h = str;
    }

    public String getAuthorizeUrl() {
        String redirectUri;
        ShareSDK.logApiEvent("/oauth2.0/authorize", c());
        String d2 = d();
        try {
            redirectUri = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            redirectUri = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.d + "&redirect_uri=" + redirectUri + "&display=mobile&scope=" + d2;
    }

    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    public cn.sharesdk.framework.authorize.c getAuthorizeWebviewClient(g gVar) {
        return new b(gVar);
    }

    private String d() {
        String[] strArr = this.e == null ? b : this.e;
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

    public cn.sharesdk.framework.authorize.e getSSOProcessor(d dVar) {
        d dVar2 = new d(dVar);
        dVar2.a(5656);
        dVar2.a(this.d, d(), this.i);
        return dVar2;
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.h));
        arrayList.add(new KVPair("oauth_consumer_key", this.d));
        arrayList.add(new KVPair("openid", this.f));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = b.a().a("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, (NetworkTimeOut) null, "/user/get_simple_userinfo", c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    public void a(Platform platform, ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        cn.sharesdk.framework.utils.e eVar = new cn.sharesdk.framework.utils.e();
        eVar.a(this.i, "com.tencent.mobileqq.activity.JumpActivity");
        eVar.a(shareParams, platform);
        HashMap hashMap = new HashMap();
        hashMap.put("ShareParams", shareParams);
        platformActionListener.onComplete(platform, 9, hashMap);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener, boolean z, int i2, String str7, String str8, String str9, int i3) {
        if (z) {
            a(str, str2, str3, str4, str5, str6, platformActionListener);
        } else if (b()) {
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
            Intent intent = new Intent();
            intent.putExtra("title", str);
            intent.putExtra("titleUrl", str2);
            intent.putExtra("summary", str3);
            intent.putExtra("imagePath", str4);
            intent.putExtra("imageUrl", str5);
            intent.putExtra("musicUrl", str6);
            intent.putExtra("appId", this.d);
            intent.putExtra("hidden", i2);
            intent.putExtra("mini_program_appid", str7);
            intent.putExtra("mini_program_path", str8);
            intent.putExtra("mini_program_type", str9);
            intent.putExtra("share_type", i3);
            e eVar = new e();
            eVar.a(this.a, platformActionListener);
            eVar.a(this.d);
            eVar.show(MobSDK.getContext(), intent);
        } else if (platformActionListener != null) {
            platformActionListener.onError(this.a, 9, new Throwable("QQClientNotExistException"));
        }
    }

    public boolean b() {
        Throwable th;
        String str;
        try {
            PackageInfo packageInfo = MobSDK.getContext().getPackageManager().getPackageInfo(TbsConfig.APP_QQ, 0);
            str = packageInfo.versionName;
            this.i = packageInfo.packageName;
        } catch (Throwable th2) {
            try {
                PackageInfo packageInfo2 = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.minihd.qq", 0);
                str = packageInfo2.versionName;
                this.i = packageInfo2.packageName;
            } catch (Throwable th3) {
                try {
                    PackageInfo packageInfo3 = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqqi", 0);
                    str = packageInfo3.versionName;
                    this.i = packageInfo3.packageName;
                } catch (Throwable th4) {
                    try {
                        PackageInfo packageInfo4 = MobSDK.getContext().getPackageManager().getPackageInfo("com.tencent.qqlite", 0);
                        str = packageInfo4.versionName;
                        this.i = packageInfo4.packageName;
                    } catch (Throwable th5) {
                        str = null;
                        SSDKLog.b().d(th);
                    }
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) {
        String b2;
        try {
            boolean z = !TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str5);
            String str7 = !z ? "/t/add_t" : "/t/add_pic_t";
            String str8 = "https://graph.qq.com" + str7;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_consumer_key", this.d));
            arrayList.add(new KVPair("access_token", this.h));
            arrayList.add(new KVPair("openid", this.f));
            arrayList.add(new KVPair("format", "json"));
            arrayList.add(new KVPair("content", str3));
            if (z) {
                if (TextUtils.isEmpty(str4)) {
                    str4 = BitmapHelper.downloadBitmap(MobSDK.getContext(), str5);
                }
                b2 = b.a().a(str8, arrayList, new KVPair("pic", str4), str7, c());
            } else {
                b2 = b.a().b(str8, arrayList, str7, c());
            }
            if (b2 != null && b2.length() > 0 && platformActionListener != null) {
                HashMap fromJson = new Hashon().fromJson(b2);
                if (((Integer) fromJson.get("ret")).intValue() != 0) {
                    platformActionListener.onError(this.a, 9, new Exception(b2));
                } else {
                    platformActionListener.onComplete(this.a, 9, fromJson);
                }
            }
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }
}
