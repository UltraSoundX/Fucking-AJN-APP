package com.tencent.connect;

import android.content.Context;
import com.tencent.connect.b.b;
import com.tencent.connect.common.a.C0076a;
import com.tencent.open.d.d;

/* compiled from: ProGuard */
public class a extends com.tencent.connect.common.a {
    public a(Context context, b bVar) {
        super(bVar);
    }

    public void a(com.tencent.tauth.b bVar) {
        com.tencent.open.d.a.a(this.d, d.a(), "user/get_simple_userinfo", a(), "GET", new C0076a(bVar));
    }
}
