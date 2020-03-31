package cn.sharesdk.framework;

import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.d;
import cn.sharesdk.framework.authorize.g;

/* compiled from: PlatformHelper */
public abstract class e implements AuthorizeHelper {
    protected Platform a;
    private AuthorizeListener b;
    private SSOListener c;

    public e(Platform platform) {
        this.a = platform;
    }

    public Platform getPlatform() {
        return this.a;
    }

    public cn.sharesdk.framework.authorize.e getSSOProcessor(d dVar) {
        return null;
    }

    public int c() {
        return this.a.getPlatformId();
    }

    /* access modifiers changed from: protected */
    public void b(AuthorizeListener authorizeListener) {
        this.b = authorizeListener;
        g gVar = new g();
        gVar.a(this.b);
        gVar.a(this);
    }

    public AuthorizeListener getAuthorizeListener() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(SSOListener sSOListener) {
        this.c = sSOListener;
        d dVar = new d();
        dVar.a(sSOListener);
        dVar.a(this);
    }

    public SSOListener getSSOListener() {
        return this.c;
    }
}
