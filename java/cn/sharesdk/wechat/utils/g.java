package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.a.b;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: WXAuthHelper */
public class g {
    /* access modifiers changed from: private */
    public String a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public b c = b.a();
    /* access modifiers changed from: private */
    public Platform d;
    /* access modifiers changed from: private */
    public int e;

    public g(Platform platform, int i) {
        this.d = platform;
        this.e = i;
    }

    public void a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public void a(Bundle bundle, AuthorizeListener authorizeListener) {
        String string = bundle.getString("_wxapi_sendauth_resp_url");
        if (!TextUtils.isEmpty(string)) {
            int indexOf = string.indexOf("://oauth?");
            if (indexOf >= 0) {
                string = string.substring(indexOf + 1);
            }
            try {
                a(ResHelper.urlToBundle(string).getString("code"), authorizeListener);
            } catch (Throwable th) {
                SSDKLog.b().d(th);
                if (authorizeListener != null) {
                    authorizeListener.onError(th);
                }
            }
        } else if (authorizeListener != null) {
            authorizeListener.onError(null);
        }
    }

    private void a(final String str, final AuthorizeListener authorizeListener) throws Throwable {
        SSDKLog.b().d("getAuthorizeToken ==>> " + str, new Object[0]);
        new Thread() {
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x006b }
                    r0.<init>()     // Catch:{ Throwable -> 0x006b }
                    com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r2 = "appid"
                    cn.sharesdk.wechat.utils.g r3 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r3 = r3.a     // Catch:{ Throwable -> 0x006b }
                    r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x006b }
                    r0.add(r1)     // Catch:{ Throwable -> 0x006b }
                    com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r2 = "secret"
                    cn.sharesdk.wechat.utils.g r3 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r3 = r3.b     // Catch:{ Throwable -> 0x006b }
                    r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x006b }
                    r0.add(r1)     // Catch:{ Throwable -> 0x006b }
                    com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r2 = "code"
                    java.lang.String r3 = r4     // Catch:{ Throwable -> 0x006b }
                    r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x006b }
                    r0.add(r1)     // Catch:{ Throwable -> 0x006b }
                    com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r2 = "grant_type"
                    java.lang.String r3 = "authorization_code"
                    r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x006b }
                    r0.add(r1)     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r1 = "https://api.weixin.qq.com/sns/oauth2/access_token"
                    cn.sharesdk.wechat.utils.g r2 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x0064 }
                    cn.sharesdk.framework.a.b r2 = r2.c     // Catch:{ Throwable -> 0x0064 }
                    java.lang.String r3 = "/sns/oauth2/access_token"
                    cn.sharesdk.wechat.utils.g r4 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x0064 }
                    int r4 = r4.e     // Catch:{ Throwable -> 0x0064 }
                    java.lang.String r0 = r2.a(r1, r0, r3, r4)     // Catch:{ Throwable -> 0x0064 }
                    boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x006b }
                    if (r1 == 0) goto L_0x0074
                    cn.sharesdk.framework.authorize.AuthorizeListener r0 = r5     // Catch:{ Throwable -> 0x006b }
                    java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ Throwable -> 0x006b }
                    java.lang.String r2 = "Authorize token is empty"
                    r1.<init>(r2)     // Catch:{ Throwable -> 0x006b }
                    r0.onError(r1)     // Catch:{ Throwable -> 0x006b }
                L_0x0063:
                    return
                L_0x0064:
                    r0 = move-exception
                    cn.sharesdk.framework.authorize.AuthorizeListener r1 = r5     // Catch:{ Throwable -> 0x006b }
                    r1.onError(r0)     // Catch:{ Throwable -> 0x006b }
                    goto L_0x0063
                L_0x006b:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
                    r1.d(r0)
                    goto L_0x0063
                L_0x0074:
                    java.lang.String r1 = "errcode"
                    boolean r1 = r0.contains(r1)     // Catch:{ Throwable -> 0x006b }
                    if (r1 == 0) goto L_0x008b
                    cn.sharesdk.framework.authorize.AuthorizeListener r1 = r5     // Catch:{ Throwable -> 0x006b }
                    if (r1 == 0) goto L_0x0063
                    cn.sharesdk.framework.authorize.AuthorizeListener r1 = r5     // Catch:{ Throwable -> 0x006b }
                    java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x006b }
                    r2.<init>(r0)     // Catch:{ Throwable -> 0x006b }
                    r1.onError(r2)     // Catch:{ Throwable -> 0x006b }
                    goto L_0x0063
                L_0x008b:
                    cn.sharesdk.wechat.utils.g r1 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x006b }
                    r1.a(r0)     // Catch:{ Throwable -> 0x006b }
                    cn.sharesdk.framework.authorize.AuthorizeListener r0 = r5     // Catch:{ Throwable -> 0x006b }
                    r1 = 0
                    r0.onComplete(r1)     // Catch:{ Throwable -> 0x006b }
                    goto L_0x0063
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.wechat.utils.g.AnonymousClass1.run():void");
            }
        }.start();
    }

    public boolean a() {
        String str = this.d.getDb().get("refresh_token");
        if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appid", this.a));
        arrayList.add(new KVPair("refresh_token", str));
        arrayList.add(new KVPair("grant_type", "refresh_token"));
        try {
            String a2 = this.c.a("https://api.weixin.qq.com/sns/oauth2/refresh_token", arrayList, "/sns/oauth2/refresh_token", this.e);
            if (TextUtils.isEmpty(a2) || a2.contains("errcode")) {
                return false;
            }
            a(a2);
            return true;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return false;
        }
    }

    public void a(final PlatformActionListener platformActionListener) throws Throwable {
        new Thread() {
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r12 = this;
                    r1 = 2
                    java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00d1 }
                    r0.<init>()     // Catch:{ Throwable -> 0x00d1 }
                    com.mob.tools.network.KVPair r2 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = "access_token"
                    cn.sharesdk.wechat.utils.g r4 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r4 = r4.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r4 = r4.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r4 = r4.getToken()     // Catch:{ Throwable -> 0x00d1 }
                    r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x00d1 }
                    r0.add(r2)     // Catch:{ Throwable -> 0x00d1 }
                    com.mob.tools.network.KVPair r2 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = "openid"
                    cn.sharesdk.wechat.utils.g r4 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r4 = r4.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r4 = r4.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r5 = "openid"
                    java.lang.String r4 = r4.get(r5)     // Catch:{ Throwable -> 0x00d1 }
                    r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x00d1 }
                    r0.add(r2)     // Catch:{ Throwable -> 0x00d1 }
                    com.mob.tools.network.KVPair r2 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = "lang"
                    java.lang.String r4 = "zh_CN"
                    r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x00d1 }
                    r0.add(r2)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r2 = "https://api.weixin.qq.com/sns/userinfo"
                    cn.sharesdk.wechat.utils.g r3 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.a.b r3 = r3.c     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r4 = "/sns/userinfo"
                    cn.sharesdk.wechat.utils.g r5 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    int r5 = r5.e     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r0 = r3.a(r2, r0, r4, r5)     // Catch:{ Throwable -> 0x00d1 }
                    boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00d1 }
                    if (r2 == 0) goto L_0x0075
                    cn.sharesdk.framework.PlatformActionListener r0 = r2     // Catch:{ Throwable -> 0x00d1 }
                    if (r0 == 0) goto L_0x0074
                    cn.sharesdk.framework.PlatformActionListener r0 = r2     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r1 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r1 = r1.d     // Catch:{ Throwable -> 0x00d1 }
                    r2 = 8
                    java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00d1 }
                    r3.<init>()     // Catch:{ Throwable -> 0x00d1 }
                    r0.onError(r1, r2, r3)     // Catch:{ Throwable -> 0x00d1 }
                L_0x0074:
                    return
                L_0x0075:
                    com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00d1 }
                    r3.<init>()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r4 = "getUserInfo ==>>"
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00d1 }
                    r4 = 0
                    java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00d1 }
                    r2.d(r3, r4)     // Catch:{ Throwable -> 0x00d1 }
                    com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x00d1 }
                    r2.<init>()     // Catch:{ Throwable -> 0x00d1 }
                    java.util.HashMap r2 = r2.fromJson(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r0 = "errcode"
                    boolean r0 = r2.containsKey(r0)     // Catch:{ Throwable -> 0x00d1 }
                    if (r0 == 0) goto L_0x00da
                    java.lang.String r0 = "errcode"
                    java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x00d1 }
                    int r0 = r0.intValue()     // Catch:{ Throwable -> 0x00d1 }
                    if (r0 == 0) goto L_0x00da
                    cn.sharesdk.framework.PlatformActionListener r0 = r2     // Catch:{ Throwable -> 0x00d1 }
                    if (r0 == 0) goto L_0x0074
                    com.mob.tools.utils.Hashon r0 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x00d1 }
                    r0.<init>()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r0 = r0.fromHashMap(r2)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformActionListener r1 = r2     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r2 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r2 = r2.d     // Catch:{ Throwable -> 0x00d1 }
                    r3 = 8
                    java.lang.Throwable r4 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00d1 }
                    r4.<init>(r0)     // Catch:{ Throwable -> 0x00d1 }
                    r1.onError(r2, r3, r4)     // Catch:{ Throwable -> 0x00d1 }
                    goto L_0x0074
                L_0x00d1:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
                    r1.d(r0)
                    goto L_0x0074
                L_0x00da:
                    java.lang.String r0 = "openid"
                    java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r0 = "nickname"
                    java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r0 = "sex"
                    java.lang.Object r0 = r2.get(r0)     // Catch:{ Throwable -> 0x01ee }
                    java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x01ee }
                    int r0 = com.mob.tools.utils.ResHelper.parseInt(r0)     // Catch:{ Throwable -> 0x01ee }
                L_0x00fc:
                    java.lang.String r5 = "province"
                    java.lang.Object r5 = r2.get(r5)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r6 = "city"
                    java.lang.Object r6 = r2.get(r6)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r7 = "country"
                    java.lang.Object r7 = r2.get(r7)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r8 = "headimgurl"
                    java.lang.Object r8 = r2.get(r8)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r9 = "unionid"
                    java.lang.Object r9 = r2.get(r9)     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r10 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r10 = r10.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r10 = r10.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r11 = "nickname"
                    r10.put(r11, r4)     // Catch:{ Throwable -> 0x00d1 }
                    r4 = 1
                    if (r0 != r4) goto L_0x01f9
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "gender"
                    java.lang.String r4 = "0"
                    r0.put(r1, r4)     // Catch:{ Throwable -> 0x00d1 }
                L_0x0151:
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    r0.putUserId(r3)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "icon"
                    r0.put(r1, r8)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "province"
                    r0.put(r1, r5)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "city"
                    r0.put(r1, r6)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "country"
                    r0.put(r1, r7)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "openid"
                    r0.put(r1, r3)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "unionid"
                    r0.put(r1, r9)     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "userTags"
                    java.lang.String r0 = r0.get(r1)     // Catch:{ Throwable -> 0x00d1 }
                    if (r0 == 0) goto L_0x01df
                    java.lang.String r0 = "userTags"
                    cn.sharesdk.wechat.utils.g r1 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r1 = r1.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r1 = r1.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r3 = "userTags"
                    java.lang.String r1 = r1.get(r3)     // Catch:{ Throwable -> 0x00d1 }
                    r2.put(r0, r1)     // Catch:{ Throwable -> 0x00d1 }
                L_0x01df:
                    cn.sharesdk.framework.PlatformActionListener r0 = r2     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.wechat.utils.g r1 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r1 = r1.d     // Catch:{ Throwable -> 0x00d1 }
                    r3 = 8
                    r0.onComplete(r1, r3, r2)     // Catch:{ Throwable -> 0x00d1 }
                    goto L_0x0074
                L_0x01ee:
                    r0 = move-exception
                    com.mob.tools.log.NLog r5 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x00d1 }
                    r5.d(r0)     // Catch:{ Throwable -> 0x00d1 }
                    r0 = r1
                    goto L_0x00fc
                L_0x01f9:
                    if (r0 != r1) goto L_0x020e
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "gender"
                    java.lang.String r4 = "1"
                    r0.put(r1, r4)     // Catch:{ Throwable -> 0x00d1 }
                    goto L_0x0151
                L_0x020e:
                    cn.sharesdk.wechat.utils.g r0 = cn.sharesdk.wechat.utils.g.this     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.Platform r0 = r0.d     // Catch:{ Throwable -> 0x00d1 }
                    cn.sharesdk.framework.PlatformDb r0 = r0.getDb()     // Catch:{ Throwable -> 0x00d1 }
                    java.lang.String r1 = "gender"
                    java.lang.String r4 = "2"
                    r0.put(r1, r4)     // Catch:{ Throwable -> 0x00d1 }
                    goto L_0x0151
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.wechat.utils.g.AnonymousClass2.run():void");
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        SSDKLog.b().d("wechat getAuthorizeToken ==>>" + str, new Object[0]);
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get("access_token"));
        String valueOf2 = String.valueOf(fromJson.get("refresh_token"));
        String valueOf3 = String.valueOf(fromJson.get("expires_in"));
        this.d.getDb().put("openid", String.valueOf(fromJson.get("openid")));
        this.d.getDb().putExpiresIn(Long.valueOf(valueOf3).longValue());
        this.d.getDb().putToken(valueOf);
        this.d.getDb().put("refresh_token", valueOf2);
    }
}
