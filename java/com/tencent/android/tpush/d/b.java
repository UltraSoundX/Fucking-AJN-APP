package com.tencent.android.tpush.d;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
public class b {
    public static void a(final Context context) {
        if (context == null) {
            a.j(Constants.OTHER_PUSH_TAG, "updateToken Error: context is null");
        } else {
            c.a().a((Runnable) new Runnable() {
                public void run() {
                    b.c(context);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context) {
        String token = XGPushConfig.getToken(context);
        if (token == null) {
            a.j(Constants.OTHER_PUSH_TAG, "updateToken Error: get XG Token is null");
            return;
        }
        long accessId = XGPushConfig.getAccessId(context);
        String f = d.a(context).f();
        String d = d.a(context).d();
        a.f(Constants.OTHER_PUSH_TAG, "other push token is : " + d + " other push type: " + f);
        if (l.c(f) || l.c(d)) {
            a.h(Constants.OTHER_PUSH_TAG, "updateToken Error: get otherPushType or otherPushToken is null");
            return;
        }
        Intent intent = new Intent("com.tencent.android.tpush.action.UPDATE_OTHER_PUSH_TOKEN.V4");
        intent.putExtra("accId", Rijndael.encrypt("" + accessId));
        intent.putExtra(Constants.FLAG_TOKEN, Rijndael.encrypt(token));
        intent.putExtra("other_push_type", Rijndael.encrypt(f));
        intent.putExtra(Constants.OTHER_PUSH_TOKEN, Rijndael.encrypt(d));
        context.sendBroadcast(intent);
    }
}
