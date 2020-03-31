package com.d.a;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.FakeActivity;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: OnekeySharePage */
public class c extends FakeActivity {
    private e a;

    public c(e eVar) {
        this.a = eVar;
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return this.a.a;
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        return this.a.c;
    }

    /* access modifiers changed from: protected */
    public final ArrayList<a> c() {
        return this.a.d;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, String> d() {
        return this.a.e;
    }

    /* access modifiers changed from: protected */
    public final PlatformActionListener e() {
        return this.a.f;
    }

    /* access modifiers changed from: protected */
    public final f f() {
        return this.a.g;
    }

    /* access modifiers changed from: protected */
    public final boolean g() {
        return this.a.h;
    }

    /* access modifiers changed from: protected */
    public final void a(Platform platform) {
        this.a.b(platform);
    }

    /* access modifiers changed from: protected */
    public final ShareParams b(Platform platform) {
        if (this.a.c(platform)) {
            return this.a.d(platform);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean c(Platform platform) {
        return this.a.a(platform);
    }
}
