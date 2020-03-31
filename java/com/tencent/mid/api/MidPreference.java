package com.tencent.mid.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.stub.StubApp;

public class MidPreference {
    private static MidPreference instance = null;
    private String TAG = "__QQ_MID_STR__";
    private Context context = null;
    private SharedPreferences preference = null;

    public SharedPreferences getSharedPreferences() {
        return this.preference;
    }

    private MidPreference(Context context2) {
        this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
        this.preference = this.context.getSharedPreferences(this.context.getPackageName() + ".mid.world.ro", 0);
    }

    public void writeMid(String str) {
        if (str == null || !str.equals(readMid())) {
            this.preference.edit().putString(this.TAG, str).commit();
        }
    }

    public String readMid() {
        return this.preference.getString(this.TAG, null);
    }

    public static MidPreference getInstance(Context context2) {
        if (instance == null) {
            synchronized (MidPreference.class) {
                if (instance == null) {
                    instance = new MidPreference(context2);
                }
            }
        }
        return instance;
    }
}
