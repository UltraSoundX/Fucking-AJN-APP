package com.tencent.connect.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;
import com.stub.StubApp;
import com.tencent.connect.a.a;
import com.tencent.open.a.f;
import com.tencent.open.d.d;

/* compiled from: ProGuard */
public class c {
    private a a = new a(this.b);
    private b b;

    private c(String str, Context context) {
        f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
        this.b = new b(str);
        a.c(context, this.b);
        a(context, "3.3.5.lite");
        f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
    }

    public static void a(Context context, String str) {
        Editor edit = context.getSharedPreferences("BuglySdkInfos", 0).edit();
        edit.putString("bcb3903995", str);
        edit.apply();
    }

    public static c a(String str, Context context) {
        d.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
            c cVar = new c(str, context);
            f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
            return cVar;
        } catch (NameNotFoundException e) {
            f.a("openSDK_LOG.QQAuth", "createInstance() error --end", e);
            Toast.makeText(StubApp.getOrigApplicationContext(context.getApplicationContext()), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
            return null;
        }
    }

    public b a() {
        return this.b;
    }
}
