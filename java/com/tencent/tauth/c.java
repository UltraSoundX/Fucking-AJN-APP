package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.stub.StubApp;
import com.tencent.connect.c.a;
import com.tencent.connect.c.b;
import com.tencent.open.a.f;
import com.tencent.open.d.d;
import com.tencent.open.d.e;

/* compiled from: ProGuard */
public class c {
    private static c b;
    private final com.tencent.connect.b.c a;

    private c(String str, Context context) {
        this.a = com.tencent.connect.b.c.a(str, context);
    }

    public static synchronized c a(String str, Context context) {
        c cVar;
        synchronized (c.class) {
            d.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
            f.c("openSDK_LOG.Tencent", "createInstance()  -- start, appId = " + str);
            if (b == null) {
                b = new c(str, context);
            } else if (!str.equals(b.a())) {
                b.a(context);
                b = new c(str, context);
            }
            if (!a(context, str)) {
                cVar = null;
            } else {
                e.a(context, str);
                f.c("openSDK_LOG.Tencent", "createInstance()  -- end");
                cVar = b;
            }
        }
        return cVar;
    }

    private static boolean a(Context context, String str) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            try {
                context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
                return true;
            } catch (NameNotFoundException e) {
                f.e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.connect.common.AssistActivity\n" + ("没有在AndroidManifest.xml中检测到com.tencent.connect.common.AssistActivity,请加上com.tencent.connect.common.AssistActivity,详细信息请查看官网文档." + "\n配置示例如下: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>"));
                return false;
            }
        } catch (NameNotFoundException e2) {
            f.e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.tauth.AuthActivity" + (("没有在AndroidManifest.xml中检测到com.tencent.tauth.AuthActivity,请加上com.tencent.tauth.AuthActivity,并配置<data android:scheme=\"tencent" + str + "\" />,详细信息请查看官网文档.") + "\n配置示例如下: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + str + "\" />\n" + "</intent-filter>\n" + "</activity>"));
            return false;
        }
    }

    public void a(Context context) {
        f.c("openSDK_LOG.Tencent", "logout()");
        this.a.a().a(null, "0");
        this.a.a().a(null);
        this.a.a().b(this.a.a().b());
    }

    public void a(Activity activity, Bundle bundle, b bVar) {
        f.c("openSDK_LOG.Tencent", "shareToQQ()");
        new a(activity, this.a.a()).a(activity, bundle, bVar);
    }

    public void b(Activity activity, Bundle bundle, b bVar) {
        f.c("openSDK_LOG.Tencent", "shareToQzone()");
        new b(activity, this.a.a()).a(activity, bundle, bVar);
    }

    public String a() {
        String b2 = this.a.a().b();
        f.c("openSDK_LOG.Tencent", "getAppId() appid =" + b2);
        return b2;
    }
}
