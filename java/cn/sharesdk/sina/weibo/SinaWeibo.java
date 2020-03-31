package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.smtt.export.external.TbsCoreSettings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SinaWeibo extends Platform {
    public static final String NAME = SinaWeibo.class.getSimpleName();
    private String a;
    private String b;
    private String c;
    private boolean d;

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.a = getDevinfo(TbsCoreSettings.TBS_SETTINGS_APP_KEY);
        this.b = getDevinfo("AppSecret");
        this.c = getDevinfo("RedirectUrl");
        this.d = !"false".equals(getDevinfo("ShareByAppClient"));
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public int getPlatformId() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_key", TbsCoreSettings.TBS_SETTINGS_APP_KEY);
        this.b = getNetworkDevinfo("app_secret", "AppSecret");
        this.c = getNetworkDevinfo("redirect_uri", "RedirectUrl");
    }

    public boolean isClientValid() {
        return h.a((Platform) this).b();
    }

    private boolean c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        h a2 = h.a((Platform) this);
        a2.a(this.a, this.b);
        a2.a(this.c);
        return a2.a();
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        h a2 = h.a((Platform) this);
        a2.c(this.db.getToken());
        a2.a(this.a, this.b);
        a2.a(this.c);
        a2.d();
        if (i == 9 || isAuthValid() || c()) {
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        final h a2 = h.a((Platform) this);
        a2.a(this.a, this.b);
        a2.a(this.c);
        a2.a(strArr);
        a2.a((AuthorizeListener) new AuthorizeListener() {
            public void onComplete(Bundle bundle) {
                long j;
                String string = bundle.getString(Config.CUSTOM_USER_ID);
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                String string4 = bundle.getString("refresh_token");
                if (bundle.containsKey("username")) {
                    SinaWeibo.this.db.put("nickname", bundle.getString("userName"));
                }
                SinaWeibo.this.db.put("remind_in", bundle.getString("remind_in"));
                SinaWeibo.this.db.putToken(string2);
                try {
                    j = ResHelper.parseLong(string3);
                } catch (Throwable th) {
                    j = 0;
                }
                SinaWeibo.this.db.putExpiresIn(j);
                SinaWeibo.this.db.put("refresh_token", string4);
                SinaWeibo.this.db.putUserId(string);
                a2.c(string2);
                SinaWeibo.this.afterRegister(1, null);
            }

            public void onError(Throwable th) {
                if (SinaWeibo.this.listener != null) {
                    SinaWeibo.this.listener.onError(SinaWeibo.this, 1, th);
                }
            }

            public void onCancel() {
                if (SinaWeibo.this.listener != null) {
                    SinaWeibo.this.listener.onCancel(SinaWeibo.this, 1);
                }
            }
        }, isSSODisable());
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
        try {
            HashMap e = h.a((Platform) this).e(str);
            if (e == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable());
                }
            } else if (!e.containsKey("error_code") || ((Integer) e.get("error_code")).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, 6, e);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 6, new Throwable(new Hashon().fromHashMap(e)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, 6, th);
        }
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap a2 = h.a((Platform) this).a(i, i2, str);
                if (a2 == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 7, new Throwable());
                    }
                } else if (!a2.containsKey("error_code") || ((Integer) a2.get("error_code")).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 7, a2);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable(new Hashon().fromHashMap(a2)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 7, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    /* access modifiers changed from: protected */
    public void userInfor(String str) {
        boolean z = true;
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
            z2 = true;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        } else {
            z = z2;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap d2 = h.a((Platform) this).d(str);
                if (d2 == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!d2.containsKey("error_code") || ((Integer) d2.get("error_code")).intValue() == 0) {
                    if (z) {
                        this.db.putUserId(String.valueOf(d2.get(Config.FEED_LIST_ITEM_CUSTOM_ID)));
                        this.db.put("nickname", String.valueOf(d2.get("screen_name")));
                        this.db.put(MessageKey.MSG_ICON, String.valueOf(d2.get("avatar_hd")));
                        if (String.valueOf(d2.get("verified")).equals("true")) {
                            this.db.put("secretType", "1");
                        } else {
                            this.db.put("secretType", "0");
                        }
                        this.db.put("secret", String.valueOf(d2.get("verified_reason")));
                        String valueOf = String.valueOf(d2.get("gender"));
                        if (valueOf.equals(Config.MODEL)) {
                            this.db.put("gender", "0");
                        } else if (valueOf.equals("f")) {
                            this.db.put("gender", "1");
                        } else {
                            this.db.put("gender", "2");
                        }
                        this.db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(d2.get("profile_url")));
                        this.db.put("resume", String.valueOf(d2.get("description")));
                        this.db.put("followerCount", String.valueOf(d2.get("followers_count")));
                        this.db.put("favouriteCount", String.valueOf(d2.get("friends_count")));
                        this.db.put("shareCount", String.valueOf(d2.get("statuses_count")));
                        this.db.put("snsregat", String.valueOf(ResHelper.dateToLong(String.valueOf(d2.get("created_at")))));
                    }
                    if (this.listener != null) {
                        this.listener.onComplete(this, 8, d2);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(d2)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 8, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap b2 = h.a((Platform) this).b(i, i2, str);
                if (b2 == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 2, new Throwable());
                    }
                } else if (!b2.containsKey("error_code") || ((Integer) b2.get("error_code")).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 2, b2);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(b2)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 2, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a2 = h.a((Platform) this).a(str, str2, hashMap, hashMap2);
            if (a2 == null || a2.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable());
                }
            } else if (!a2.containsKey("error_code") || ((Integer) a2.get("error_code")).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, i, a2);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a2)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, i, th);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c0, code lost:
        r9.listener.onError(r9, 9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00da, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00db, code lost:
        r9.listener.onError(r9, 9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0017, B:29:0x00d4] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doShare(cn.sharesdk.framework.Platform.ShareParams r10) {
        /*
            r9 = this;
            r8 = 9
            cn.sharesdk.sina.weibo.h r7 = cn.sharesdk.sina.weibo.h.a(r9)
            java.lang.String r0 = r9.a
            java.lang.String r1 = r9.b
            r7.a(r0, r1)
            boolean r0 = r9.d
            if (r0 == 0) goto L_0x00d4
            boolean r0 = r7.b()
            if (r0 == 0) goto L_0x00d4
            java.lang.String r1 = r10.getLcSummary()     // Catch:{ Throwable -> 0x00bf }
            org.json.JSONObject r2 = r10.getLcImage()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r3 = r10.getLcObjectType()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r4 = r10.getLcDisplayName()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r5 = r10.getLcCreateAt()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r6 = r10.getLcUrl()     // Catch:{ Throwable -> 0x00bf }
            cn.sharesdk.framework.b.a.e r0 = cn.sharesdk.framework.b.a.e.a()     // Catch:{ Throwable -> 0x00bf }
            boolean r0 = r0.h()     // Catch:{ Throwable -> 0x00bf }
            if (r0 == 0) goto L_0x00c6
            if (r1 == 0) goto L_0x00c6
            if (r2 == 0) goto L_0x00c6
            if (r3 == 0) goto L_0x00c6
            if (r4 == 0) goto L_0x00c6
            if (r5 == 0) goto L_0x00c6
            if (r6 == 0) goto L_0x00c6
            cn.sharesdk.sina.weibo.b r0 = cn.sharesdk.sina.weibo.b.a()     // Catch:{ Throwable -> 0x00bf }
            java.util.HashMap r0 = r0.a(r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00bf }
            if (r0 == 0) goto L_0x0081
            java.lang.String r1 = "error"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x00bf }
            if (r1 == 0) goto L_0x0081
            cn.sharesdk.framework.PlatformActionListener r1 = r9.listener     // Catch:{ Throwable -> 0x00bf }
            if (r1 == 0) goto L_0x0081
            java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00bf }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bf }
            r2.<init>()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r3 = "error: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r3 = "error"
            java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x00bf }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00bf }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x00bf }
            cn.sharesdk.framework.PlatformActionListener r0 = r9.listener     // Catch:{ Throwable -> 0x00bf }
            r2 = 9
            r0.onError(r9, r2, r1)     // Catch:{ Throwable -> 0x00bf }
        L_0x0080:
            return
        L_0x0081:
            int r1 = r0.size()     // Catch:{ Throwable -> 0x00bf }
            if (r1 <= 0) goto L_0x0080
            java.lang.String r1 = "url"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x00bf }
            if (r1 == 0) goto L_0x0080
            java.lang.String r1 = "url"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r1 = r10.getText()     // Catch:{ Throwable -> 0x00bf }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bf }
            r2.<init>()     // Catch:{ Throwable -> 0x00bf }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Throwable -> 0x00bf }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x00bf }
            r10.setText(r0)     // Catch:{ Throwable -> 0x00bf }
            cn.sharesdk.framework.PlatformActionListener r0 = r9.listener     // Catch:{ Throwable -> 0x00bf }
            r7.a(r10, r0)     // Catch:{ Throwable -> 0x00bf }
            cn.sharesdk.sina.weibo.c r0 = cn.sharesdk.sina.weibo.c.a()     // Catch:{ Throwable -> 0x00bf }
            r1 = 2
            r0.a(r1)     // Catch:{ Throwable -> 0x00bf }
            goto L_0x0080
        L_0x00bf:
            r0 = move-exception
            cn.sharesdk.framework.PlatformActionListener r1 = r9.listener
            r1.onError(r9, r8, r0)
            goto L_0x0080
        L_0x00c6:
            cn.sharesdk.framework.PlatformActionListener r0 = r9.listener     // Catch:{ Throwable -> 0x00bf }
            r7.a(r10, r0)     // Catch:{ Throwable -> 0x00bf }
            cn.sharesdk.sina.weibo.c r0 = cn.sharesdk.sina.weibo.c.a()     // Catch:{ Throwable -> 0x00bf }
            r1 = 3
            r0.a(r1)     // Catch:{ Throwable -> 0x00bf }
            goto L_0x0080
        L_0x00d4:
            cn.sharesdk.framework.PlatformActionListener r0 = r9.listener     // Catch:{ Throwable -> 0x00da }
            r7.b(r10, r0)     // Catch:{ Throwable -> 0x00da }
            goto L_0x0080
        L_0x00da:
            r0 = move-exception
            cn.sharesdk.framework.PlatformActionListener r1 = r9.listener
            r1.onError(r9, r8, r0)
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.SinaWeibo.doShare(cn.sharesdk.framework.Platform$ShareParams):void");
    }

    /* access modifiers changed from: protected */
    public a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        if (hashMap != null) {
            aVar.a = String.valueOf(hashMap.get(Config.FEED_LIST_ITEM_CUSTOM_ID));
            aVar.d.add(String.valueOf(hashMap.get("original_pic")));
            aVar.g = hashMap;
        }
        return aVar;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap b2 = h.a((Platform) this).b(i, i2, str);
            if (b2 == null || b2.containsKey("error_code")) {
                return null;
            }
            b2.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(2, b2);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap c2 = h.a((Platform) this).c(i, i2, str);
            if (c2 == null || c2.containsKey("error_code")) {
                return null;
            }
            c2.put("page_count", Integer.valueOf(i));
            c2.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(10, c2);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get("nickname");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            HashMap d2 = h.a((Platform) this).d(i, i2, str);
            if (d2 == null || d2.containsKey("error_code")) {
                return null;
            }
            d2.put("current_cursor", Integer.valueOf(i2));
            return filterFriendshipInfo(11, d2);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        String str;
        HashMap hashMap2 = new HashMap();
        switch (i) {
            case 2:
                hashMap2.put("type", "FOLLOWING");
                break;
            case 10:
                hashMap2.put("type", "FRIENDS");
                break;
            case 11:
                hashMap2.put("type", "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_number")));
        if (parseInt2 == 0) {
            return null;
        }
        Object obj = hashMap.get("users");
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get(Config.FEED_LIST_ITEM_CUSTOM_ID)));
                hashMap4.put("nickname", String.valueOf(hashMap3.get("screen_name")));
                hashMap4.put(MessageKey.MSG_ICON, String.valueOf(hashMap3.get("avatar_hd")));
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", "1");
                } else {
                    hashMap4.put("secretType", "0");
                }
                hashMap4.put("secret", String.valueOf(hashMap3.get("verified_reason")));
                String valueOf = String.valueOf(hashMap3.get("gender"));
                if (valueOf.equals(Config.MODEL)) {
                    hashMap4.put("gender", "0");
                } else if (valueOf.equals("f")) {
                    hashMap4.put("gender", "1");
                } else {
                    hashMap4.put("gender", "2");
                }
                hashMap4.put("snsUserUrl", "http://weibo.com/" + String.valueOf(hashMap3.get("profile_url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get("description")));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(ResHelper.dateToLong(String.valueOf(hashMap3.get("created_at")))));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        if (10 == i) {
            hashMap2.put("nextCursor", ((Integer) hashMap.get("page_count")).intValue() * (parseInt + 1) >= parseInt2 ? parseInt + "_true" : (parseInt + 1) + "_false");
        } else {
            int size = arrayList.size() + parseInt;
            if (size >= parseInt2) {
                str = parseInt2 + "_true";
            } else {
                str = size + "_false";
            }
            hashMap2.put("nextCursor", str);
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
