package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.c.a;
import com.tencent.mid.core.Constants;

/* compiled from: ConnectivityMonitorFactory */
public class d {
    public c a(Context context, a aVar) {
        if (context.checkCallingOrSelfPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE) == 0) {
            return new e(context, aVar);
        }
        return new i();
    }
}
