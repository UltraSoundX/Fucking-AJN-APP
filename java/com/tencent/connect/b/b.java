package com.tencent.connect.b;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.util.Base64;
import com.tencent.open.d.d;
import com.tencent.open.d.j;

/* compiled from: ProGuard */
public class b {
    private static SharedPreferences f;
    private String a;
    private String b;
    private String c;
    private int d = 1;
    private long e = -1;

    public b(String str) {
        this.a = str;
    }

    public boolean a() {
        return this.b != null && System.currentTimeMillis() < this.e;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public void a(String str, String str2) throws NumberFormatException {
        this.b = str;
        this.e = 0;
        if (str2 != null) {
            this.e = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
        }
    }

    public String d() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    @TargetApi(11)
    private static synchronized SharedPreferences e() {
        SharedPreferences sharedPreferences;
        synchronized (b.class) {
            if (f == null) {
                f = d.a().getSharedPreferences("token_info_file", 0);
            }
            sharedPreferences = f;
        }
        return sharedPreferences;
    }

    public void b(String str) {
        e().edit().remove(Base64.encodeToString(j.h(str), 2)).commit();
    }
}
