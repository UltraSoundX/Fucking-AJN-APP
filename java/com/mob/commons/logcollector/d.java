package com.mob.commons.logcollector;

import com.mob.MobSDK;
import com.mob.tools.utils.SharePrefrenceHelper;

/* compiled from: LogsSharePrefrence */
public class d {
    private static d a;
    private SharePrefrenceHelper b = new SharePrefrenceHelper(MobSDK.getContext());

    private d() {
        this.b.open("mob_sdk_exception", 1);
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public void a(long j) {
        this.b.putLong("service_time", Long.valueOf(j));
    }

    public long b() {
        return this.b.getLong("service_time");
    }

    public void a(boolean z) {
        this.b.putInt("is_upload_err_log", Integer.valueOf(z ? 0 : 1));
    }

    public boolean c() {
        return this.b.getInt("is_upload_err_log") == 0;
    }

    public void a(int i) {
        this.b.putInt("is_upload_crash", Integer.valueOf(i));
    }

    public int d() {
        return this.b.getInt("is_upload_crash");
    }

    public void b(int i) {
        this.b.putInt("is_upload_sdkerr", Integer.valueOf(i));
    }

    public int e() {
        return this.b.getInt("is_upload_sdkerr");
    }

    public void c(int i) {
        this.b.putInt("is_upload_apperr", Integer.valueOf(i));
    }

    public int f() {
        return this.b.getInt("is_upload_apperr");
    }

    public String g() {
        return this.b.getString("err_log_filter");
    }

    public void a(String str) {
        this.b.putString("err_log_filter", str);
    }
}
