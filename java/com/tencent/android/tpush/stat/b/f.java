package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.tencent.android.tpush.stat.a.c;

/* compiled from: ProGuard */
public class f extends h {
    public f(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public String c() {
        return b(f());
    }

    public String b(String str) {
        String string;
        synchronized (this) {
            if (this.a == null) {
                this.a = c.b();
            }
            this.a.b((Object) "read mid from sharedPreferences， key=" + str);
            string = PreferenceManager.getDefaultSharedPreferences(this.b).getString(str, null);
        }
        return string;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        a(f(), str);
    }

    public void a(String str, String str2) {
        synchronized (this) {
            if (this.a == null) {
                this.a = c.b();
            }
            this.a.b((Object) "write mid to sharedPreferences " + str);
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.b).edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }
}
