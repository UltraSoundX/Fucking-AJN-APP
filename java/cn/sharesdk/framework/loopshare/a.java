package cn.sharesdk.framework.loopshare;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* compiled from: MobLinkSP */
public class a {
    private SharedPreferences a;
    private Editor b = this.a.edit();

    public a(Context context, String str) {
        this.a = context.getSharedPreferences(str, 0);
    }

    public void a(String str, Object obj) {
        if (obj instanceof String) {
            this.b.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            this.b.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            this.b.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            this.b.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            this.b.putLong(str, ((Long) obj).longValue());
        } else {
            this.b.putString(str, obj.toString());
        }
        this.b.commit();
    }

    public Object b(String str, Object obj) {
        if (obj instanceof String) {
            return this.a.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(this.a.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(this.a.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(this.a.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(this.a.getLong(str, ((Long) obj).longValue()));
        }
        return this.a.getString(str, null);
    }
}
