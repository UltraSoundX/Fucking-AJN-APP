package com.e23.ajn.d;

import android.content.SharedPreferences.Editor;
import com.e23.ajn.app.App;

/* compiled from: PreferenceUtils */
public class p {
    public static void a(String str, String str2) {
        Editor edit = App.getInstance().getSharedPreferences("com.e23.ajn", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static String b(String str, String str2) {
        return App.getInstance().getSharedPreferences("com.e23.ajn", 0).getString(str, str2);
    }

    public static boolean a(String str, boolean z) {
        return App.getInstance().getSharedPreferences("com.e23.ajn", 0).getBoolean(str, z);
    }

    public static void b(String str, boolean z) {
        Editor edit = App.getInstance().getSharedPreferences("com.e23.ajn", 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static long a(String str, int i) {
        return App.getInstance().getSharedPreferences("com.e23.ajn", 0).getLong(str, (long) i);
    }

    public static void a(String str, long j) {
        Editor edit = App.getInstance().getSharedPreferences("com.e23.ajn", 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static void a(String str) {
        Editor edit = App.getInstance().getSharedPreferences("com.e23.ajn", 0).edit();
        edit.remove(str);
        edit.commit();
    }
}
