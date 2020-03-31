package com.jg.ids;

import android.content.Context;
import android.os.Build;
import com.jg.ids.e.d;
import com.jg.ids.h.a;
import com.jg.ids.meizu.c;

public class IdsHelper {
    public static Context CONTEXT = null;
    private static final IdsHelper ourInstance = new IdsHelper();
    private k provider = null;

    public static IdsHelper getInstance() {
        return ourInstance;
    }

    private IdsHelper() {
    }

    public void init(Context context) {
        if (context != null) {
            l.a().a(context);
            CONTEXT = context;
            switch (i.a[j.a(Build.MANUFACTURER).ordinal()]) {
                case 1:
                    this.provider = new a();
                    return;
                case 2:
                    this.provider = new com.jg.ids.g.a(context, "100215079");
                    return;
                case 3:
                    this.provider = new com.jg.ids.b.a(context);
                    return;
                case 4:
                    this.provider = new d(context);
                    return;
                case 5:
                case 6:
                    this.provider = new com.jg.ids.c.d(context);
                    return;
                case 7:
                    this.provider = new com.jg.ids.a.a(context);
                    return;
                case 8:
                    this.provider = new com.jg.ids.f.d(context);
                    return;
                case 9:
                case 10:
                    this.provider = new c(context);
                    return;
                case 11:
                    this.provider = new com.jg.ids.d.a(context);
                    return;
                default:
                    return;
            }
        }
    }

    public String getOAID() {
        if (this.provider != null) {
            return this.provider.b(CONTEXT);
        }
        return "";
    }

    public String getVAID() {
        if (this.provider != null) {
            return this.provider.a(CONTEXT);
        }
        return "";
    }

    public String getAAID() {
        if (this.provider != null) {
            return this.provider.c(CONTEXT);
        }
        return "";
    }
}
