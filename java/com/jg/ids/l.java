package com.jg.ids;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class l {
    private static final l a = new l();
    private SharedPreferences b = null;

    private l() {
    }

    public final void a(Context context) {
        if (this.b == null) {
            this.b = context.getSharedPreferences("jg_ids", 0);
        }
    }

    public static l a() {
        return a;
    }

    public final String b() {
        return this.b.getString("jg_aaid", "");
    }

    public final void a(String str) {
        Editor edit = this.b.edit();
        edit.putString("jg_aaid", str);
        edit.commit();
    }

    public final String c() {
        return this.b.getString("jg_vaid", "");
    }

    public final void b(String str) {
        Editor edit = this.b.edit();
        edit.putString("jg_vaid", str);
        edit.commit();
    }

    public final String d() {
        return this.b.getString("jg_oaid", "");
    }

    public final void c(String str) {
        Editor edit = this.b.edit();
        edit.putString("jg_oaid", str);
        edit.commit();
    }
}
