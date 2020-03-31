package com.tencent.android.tpush.d.a;

import android.content.Context;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.d.c;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* compiled from: ProGuard */
public class a extends c {
    public void a(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl");
            cls.getMethod("registerPush", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "registerPush FCM Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "registerPush FCM Error, are you import otherpush package? " + e2);
        }
    }

    public void b(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl");
            cls.getMethod("unregisterPush", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "unregisterPush FCM Error, are you import otherpush package? " + e);
        }
    }

    public String c(Context context) {
        try {
            Class cls = Class.forName("com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl");
            Object invoke = cls.getMethod("getToken", new Class[]{Context.class}).invoke(cls, new Object[]{context});
            if (invoke != null) {
                return invoke.toString();
            }
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "getToken Error for InvocationTargetException: " + cause.getMessage());
            cause.printStackTrace();
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.e(Constants.OTHER_PUSH_TAG, "getToken Error", e2);
        }
        return null;
    }

    public boolean d(Context context) {
        try {
            if (context.getResources().getAssets().open("google-services.json") == null) {
                com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "isConfig : no google-services.json file");
                return false;
            } else if (com.tencent.android.tpush.d.a.a(context, "com.tencent.android.tpush.otherpush.fcm.impl.OtherPushImpl")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            com.tencent.android.tpush.b.a.j(Constants.OTHER_PUSH_TAG, "isConfig :" + e);
            return false;
        }
    }

    public String a() {
        return "fcm";
    }
}
