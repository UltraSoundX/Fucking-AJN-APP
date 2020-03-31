package cn.sharesdk.wechat.moments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import cn.sharesdk.wechat.utils.g;
import cn.sharesdk.wechat.utils.j;
import cn.sharesdk.wechat.utils.k;
import java.util.HashMap;

public class WechatMoments extends Platform {
    public static final String NAME = WechatMoments.class.getSimpleName();
    private String a;
    private String b;
    private boolean c;

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.a = getDevinfo("AppId");
        this.b = getDevinfo("AppSecret");
        this.c = "true".equals(getDevinfo("BypassApproval"));
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("Wechat", "AppId");
            this.c = "true".equals(getDevinfo("Wechat", "BypassApproval"));
            if (this.a == null || this.a.length() <= 0) {
                this.a = getDevinfo("WechatFavorite", "AppId");
                if (this.a != null && this.a.length() > 0) {
                    copyDevinfo("WechatFavorite", NAME);
                    this.a = getDevinfo("AppId");
                    SSDKLog.b().d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyDevinfo("Wechat", NAME);
            this.a = getDevinfo("AppId");
            this.c = "true".equals(getDevinfo("BypassApproval"));
            SSDKLog.b().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    public int getPlatformId() {
        return 23;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_id", "AppId");
        this.b = getNetworkDevinfo("app_secret", "AppSecret");
        if (this.a == null || this.a.length() <= 0) {
            this.a = getNetworkDevinfo(22, "app_id", "AppId");
            if (this.a == null || this.a.length() <= 0) {
                this.a = getNetworkDevinfo(37, "app_id", "AppId");
                if (this.a != null && this.a.length() > 0) {
                    copyNetworkDevinfo(23, 23);
                    this.a = getNetworkDevinfo("app_id", "AppId");
                    SSDKLog.b().d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            } else {
                copyNetworkDevinfo(22, 23);
                this.a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.b().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
        if (this.b == null || this.b.length() <= 0) {
            this.b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            if (this.b == null || this.b.length() <= 0) {
                this.b = getNetworkDevinfo(37, "app_secret", "AppSecret");
                if (this.b != null && this.b.length() > 0) {
                    copyNetworkDevinfo(23, 23);
                    this.b = getNetworkDevinfo("app_secret", "AppSecret");
                    SSDKLog.b().d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyNetworkDevinfo(22, 23);
            this.b = getNetworkDevinfo("app_secret", "AppSecret");
            SSDKLog.b().d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            k a2 = k.a();
            a2.c(this.a);
            if (!a2.c()) {
                if (this.listener != null) {
                    this.listener.onError(this, 1, new WechatClientNotExistException());
                }
            } else if (a2.d()) {
                g gVar = new g(this, 23);
                gVar.a(this.a, this.b);
                j jVar = new j(this);
                jVar.a(gVar);
                jVar.a((AuthorizeListener) new AuthorizeListener() {
                    public void onError(Throwable th) {
                        if (WechatMoments.this.listener != null) {
                            WechatMoments.this.listener.onError(WechatMoments.this, 1, th);
                        }
                    }

                    public void onComplete(Bundle bundle) {
                        WechatMoments.this.afterRegister(1, null);
                    }

                    public void onCancel() {
                        if (WechatMoments.this.listener != null) {
                            WechatMoments.this.listener.onCancel(WechatMoments.this, 1);
                        }
                    }
                });
                try {
                    a2.a(jVar);
                } catch (Throwable th) {
                    if (this.listener != null) {
                        this.listener.onError(this, 1, th);
                    }
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }

    private boolean c() {
        if (TextUtils.isEmpty(getDb().get("refresh_token"))) {
            return false;
        }
        g gVar = new g(this, 23);
        gVar.a(this.a, this.b);
        return gVar.a();
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        k a2 = k.a();
        a2.c(this.a);
        if (!a2.c()) {
            if (this.listener == null) {
                return false;
            }
            this.listener.onError(this, i, new WechatClientNotExistException());
            return false;
        } else if (!a2.d()) {
            if (this.listener == null) {
                return false;
            }
            this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
            return false;
        } else if (i == 9 || isAuthValid() || c()) {
            return true;
        } else {
            innerAuthorize(i, obj);
            return false;
        }
    }

    public boolean isClientValid() {
        k a2 = k.a();
        a2.c(this.a);
        if (!a2.c() || !a2.d()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void doShare(ShareParams shareParams) {
        shareParams.set("scene", Integer.valueOf(1));
        k a2 = k.a();
        a2.c(this.a);
        j jVar = new j(this);
        if (this.c) {
            try {
                a2.a(jVar, shareParams, this.listener);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
            }
        } else {
            jVar.a(shareParams, this.listener);
            try {
                a2.b(jVar);
                if (this.listener != null && a2.b()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ShareParams", shareParams);
                    if (this.listener != null) {
                        this.listener.onComplete(this, 9, hashMap);
                    }
                }
            } catch (Throwable th2) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    /* access modifiers changed from: protected */
    public void userInfor(String str) {
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            g gVar = new g(this, 23);
            gVar.a(this.a, this.b);
            try {
                gVar.a(this.listener);
            } catch (Throwable th) {
                SSDKLog.b().d(th);
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    /* access modifiers changed from: protected */
    public a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        String text = shareParams.getText();
        aVar.b = text;
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        Bitmap imageData = shareParams.getImageData();
        if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        } else if (imagePath != null) {
            aVar.e.add(imagePath);
        } else if (imageData != null) {
            aVar.f.add(imageData);
        }
        String url = shareParams.getUrl();
        if (url != null) {
            aVar.c.add(url);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", url);
        hashMap2.put("extInfo", null);
        hashMap2.put("content", text);
        hashMap2.put("image", aVar.d);
        hashMap2.put("musicFileUrl", url);
        aVar.g = hashMap2;
        return aVar;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return !this.c;
    }
}
