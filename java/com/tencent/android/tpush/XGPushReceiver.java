package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.horse.d;
import com.tencent.android.tpush.service.b;

/* compiled from: ProGuard */
public class XGPushReceiver extends BroadcastReceiver {
    private static final String a = XGPushReceiver.class.getSimpleName();

    static {
        StubApp.interface11(4893);
    }

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null && l.h(context)) {
            String action = intent.getAction();
            if (action != null) {
                b.d(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                if (XGPushConfig.enableDebug) {
                    a.c(a, "PushReceiver received " + action + " @@ " + context.getPackageName());
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    d.a().a(intent);
                } else if (!Constants.ACTION_INTERNAL_PUSH_MESSAGE.equals(action) && !Constants.ACTION_SDK_INSTALL.equals(action)) {
                    b.a(context);
                }
            }
        }
    }
}
