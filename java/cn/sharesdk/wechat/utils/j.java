package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* compiled from: WechatHandler */
public class j {
    private Platform a;
    private ShareParams b;
    private PlatformActionListener c;
    private AuthorizeListener d;
    private g e;

    public j(Platform platform) {
        this.a = platform;
    }

    public void a(AuthorizeListener authorizeListener) {
        this.d = authorizeListener;
    }

    public void a(ShareParams shareParams, PlatformActionListener platformActionListener) {
        this.b = shareParams;
        this.c = platformActionListener;
    }

    public void a(g gVar) {
        this.e = gVar;
    }

    public void a(WechatResp wechatResp) {
        switch (wechatResp.f) {
            case -4:
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(wechatResp.f));
                hashMap.put("errStr", wechatResp.g);
                hashMap.put("transaction", wechatResp.h);
                Throwable th = new Throwable(new Hashon().fromHashMap(hashMap));
                switch (wechatResp.a()) {
                    case 1:
                        if (this.d != null) {
                            this.d.onError(th);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case -3:
                HashMap hashMap2 = new HashMap();
                hashMap2.put("errCode", Integer.valueOf(wechatResp.f));
                hashMap2.put("errStr", wechatResp.g);
                hashMap2.put("transaction", wechatResp.h);
                Throwable th2 = new Throwable(new Hashon().fromHashMap(hashMap2));
                switch (wechatResp.a()) {
                    case 1:
                        if (this.d != null) {
                            this.d.onError(th2);
                            return;
                        }
                        return;
                    case 2:
                        if (this.c != null) {
                            this.c.onError(this.a, 9, th2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case -2:
                switch (wechatResp.a()) {
                    case 1:
                        if (this.d != null) {
                            this.d.onCancel();
                            return;
                        }
                        return;
                    case 2:
                        if (this.c != null) {
                            this.c.onCancel(this.a, 9);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            case 0:
                switch (wechatResp.a()) {
                    case 1:
                        if (this.d != null) {
                            Bundle bundle = new Bundle();
                            wechatResp.b(bundle);
                            this.e.a(bundle, this.d);
                            return;
                        }
                        return;
                    case 2:
                        if (this.c != null) {
                            HashMap hashMap3 = new HashMap();
                            hashMap3.put("ShareParams", this.b);
                            this.c.onComplete(this.a, 9, hashMap3);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                HashMap hashMap4 = new HashMap();
                hashMap4.put("req", wechatResp.getClass().getSimpleName());
                hashMap4.put("errCode", Integer.valueOf(wechatResp.f));
                hashMap4.put("errStr", wechatResp.g);
                hashMap4.put("transaction", wechatResp.h);
                Throwable th3 = new Throwable(new Hashon().fromHashMap(hashMap4));
                if (this.c != null) {
                    this.c.onError(this.a, 9, th3);
                }
                if (this.d != null) {
                    this.d.onError(th3);
                    return;
                }
                return;
        }
    }

    public ShareParams a() {
        return this.b;
    }

    public Platform b() {
        return this.a;
    }

    public PlatformActionListener c() {
        return this.c;
    }
}
