package com.tencent.smtt.sdk.b.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.BufferedInputStream;
import java.util.Map;

/* compiled from: BrowsingActivityInfo */
public class b {
    private Context a;
    private ResolveInfo b;
    private Drawable c;
    private String d;
    private String e;
    private int f = 0;

    public Drawable a(Map<String, Drawable> map) {
        Drawable a2 = a(this.a, c());
        if (a2 != null) {
            return a2;
        }
        if (this.b != null) {
            return this.b.loadIcon(this.a.getPackageManager());
        }
        return this.c;
    }

    public String a() {
        if (this.b != null) {
            return this.b.loadLabel(this.a.getPackageManager()).toString();
        }
        return this.d;
    }

    public ResolveInfo b() {
        return this.b;
    }

    b(Context context, ResolveInfo resolveInfo) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.b = resolveInfo;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    b(Context context, Drawable drawable, String str, String str2) {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.b = null;
        this.c = drawable;
        this.d = str;
        this.e = str2;
    }

    public String c() {
        if (this.b != null) {
            return this.b.activityInfo.packageName;
        }
        return this.e == null ? "" : this.e;
    }

    public void a(ResolveInfo resolveInfo) {
        this.b = resolveInfo;
    }

    public static Drawable a(Context context, String str) {
        Drawable drawable = null;
        if (TbsConfig.APP_QB.equals(str)) {
            try {
                return d.a("application_icon");
            } catch (Throwable th) {
                Log.e("error", "getApkIcon Error:" + Log.getStackTraceString(th));
                return drawable;
            }
        } else {
            PackageManager packageManager = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager();
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
                if (applicationInfo == null) {
                    return drawable;
                }
                Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
                TypedValue typedValue = new TypedValue();
                resourcesForApplication.getValue(applicationInfo.icon, typedValue, true);
                try {
                    return Drawable.createFromResourceStream(resourcesForApplication, typedValue, new BufferedInputStream(resourcesForApplication.getAssets().openNonAssetFd(typedValue.assetCookie, typedValue.string.toString()).createInputStream()), null);
                } catch (Exception e2) {
                    return drawable;
                }
            } catch (Exception e3) {
                Log.e("sdk", "e = " + e3);
                return drawable;
            }
        }
    }
}
