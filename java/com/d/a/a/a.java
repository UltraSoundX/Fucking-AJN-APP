package com.d.a.a;

import android.content.Context;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import com.d.a.a.b.d;
import com.d.a.e;

/* compiled from: ClassicTheme */
public class a extends e {
    /* access modifiers changed from: protected */
    public void b(Context context) {
        i dVar;
        if (context.getResources().getConfiguration().orientation == 1) {
            dVar = new d(this);
        } else {
            dVar = new com.d.a.a.a.d(this);
        }
        dVar.show(context, null);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, Platform platform, ShareParams shareParams) {
        b aVar;
        if (context.getResources().getConfiguration().orientation == 1) {
            aVar = new com.d.a.a.b.a(this);
        } else {
            aVar = new com.d.a.a.a.a(this);
        }
        aVar.d(platform);
        aVar.a(shareParams);
        aVar.show(context, null);
    }
}
