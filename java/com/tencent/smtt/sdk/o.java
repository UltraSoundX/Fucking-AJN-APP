package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;
import com.tencent.tbs.video.interfaces.a;

/* compiled from: TbsVideoPlayer */
class o {
    private static o e = null;
    q a = null;
    Context b;
    a c;
    IUserStateChangedListener d;

    public static synchronized o a(Context context) {
        o oVar;
        synchronized (o.class) {
            if (e == null) {
                e = new o(context);
            }
            oVar = e;
        }
        return oVar;
    }

    private o(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.a = new q(this.b);
    }

    public boolean a(String str, Bundle bundle, a aVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("videoUrl", str);
        }
        if (aVar != null) {
            this.a.a();
            if (!this.a.b()) {
                return false;
            }
            this.c = aVar;
            this.d = new IUserStateChangedListener() {
                public void onUserStateChanged() {
                    o.this.a.c();
                }
            };
            this.c.a(this.d);
            bundle.putInt("callMode", 3);
        } else {
            bundle.putInt("callMode", 1);
        }
        q qVar = this.a;
        if (aVar == null) {
            this = null;
        }
        qVar.a(bundle, (Object) this);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(Activity activity, int i) {
        this.a.a(activity, i);
    }

    public boolean a() {
        this.a.a();
        return this.a.b();
    }

    public void a(int i, int i2, Intent intent) {
        if (this.c != null) {
            this.c.a(i, i2, intent);
        }
    }
}
