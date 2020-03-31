package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.stub.StubApp;

/* compiled from: ProGuard */
public class e {
    private static e a = null;
    private Context b = null;
    private SharedPreferences c = null;
    private String d = "__QQ_MID_STR__";

    private e(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.c = PreferenceManager.getDefaultSharedPreferences(this.b);
    }

    public void a(String str) {
        if (str == null || !str.equals(a())) {
            this.c.edit().putString(this.d, str).commit();
        }
    }

    public String a() {
        return this.c.getString(this.d, null);
    }

    public static e a(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e(context);
                }
            }
        }
        return a;
    }
}
