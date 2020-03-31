package com.d.a;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import com.mob.MobApplication;
import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: OnekeyShare */
public class b {
    private HashMap<String, Object> a = new HashMap<>();

    public b() {
        this.a.put("customers", new ArrayList());
        this.a.put("hiddenPlatforms", new HashMap());
    }

    public void a(String str) {
        this.a.put("address", str);
    }

    public void b(String str) {
        this.a.put("title", str);
    }

    public void c(String str) {
        this.a.put("titleUrl", str);
    }

    public void d(String str) {
        this.a.put("text", str);
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.put("imagePath", str);
        }
    }

    public void f(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.put("imageUrl", str);
        }
    }

    public void g(String str) {
        this.a.put("url", str);
    }

    public void h(String str) {
        this.a.put("site", str);
    }

    public void i(String str) {
        this.a.put("siteUrl", str);
    }

    public void a(boolean z) {
        this.a.put("silent", Boolean.valueOf(z));
    }

    public void j(String str) {
        this.a.put("platform", str);
    }

    public void a(PlatformActionListener platformActionListener) {
        this.a.put("callback", platformActionListener);
    }

    public void a(f fVar) {
        this.a.put("customizeCallback", fVar);
    }

    public void a() {
        this.a.put("disableSSO", Boolean.valueOf(true));
    }

    public void a(Context context) {
        int i;
        boolean z;
        boolean z2 = false;
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.a);
        if (!(context instanceof MobApplication)) {
            MobSDK.init(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        ShareSDK.logDemoEvent(1, null);
        try {
            i = ResHelper.parseInt(String.valueOf(hashMap.remove("theme")));
        } catch (Throwable th) {
            i = 0;
        }
        e a2 = d.a(i).a();
        a2.a(hashMap);
        if (hashMap.containsKey("dialogMode")) {
            z = ((Boolean) hashMap.remove("dialogMode")).booleanValue();
        } else {
            z = false;
        }
        a2.a(z);
        if (hashMap.containsKey("silent")) {
            z2 = ((Boolean) hashMap.remove("silent")).booleanValue();
        }
        a2.b(z2);
        a2.a((ArrayList) hashMap.remove("customers"));
        a2.b((HashMap) hashMap.remove("hiddenPlatforms"));
        a2.a((PlatformActionListener) hashMap.remove("callback"));
        a2.a((f) hashMap.remove("customizeCallback"));
        if (hashMap.containsKey("disableSSO") && ((Boolean) hashMap.remove("disableSSO")).booleanValue()) {
            a2.a();
        }
        a2.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
    }
}
