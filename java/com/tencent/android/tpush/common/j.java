package com.tencent.android.tpush.common;

import android.content.ContentValues;
import android.content.Context;
import com.stub.StubApp;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.a.b;

/* compiled from: ProGuard */
public class j {
    public static volatile j a = null;
    private Context b;

    /* compiled from: ProGuard */
    public static class a {
        Context a;
        private ContentValues b;

        private a(Context context) {
            this.b = new ContentValues();
            this.a = context;
        }

        public void a() {
            try {
                b.a(this.a, SettingsContentProvider.getContentUri(this.a, SettingsContentProvider.KEY, "type"), this.b);
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("SettingsPreferences", "apply", th);
            }
        }

        public void b() {
            a();
        }

        public a a(String str, String str2) {
            this.b.put(str, str2);
            return this;
        }

        public a a(String str, long j) {
            this.b.put(str, Long.valueOf(j));
            return this;
        }

        public a a(String str, int i) {
            this.b.put(str, Integer.valueOf(i));
            return this;
        }

        public void a(String str) {
            this.b.putNull(str);
        }
    }

    public static j a(Context context) {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j(context);
                }
            }
        }
        return a;
    }

    private j(Context context) {
        this.b = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public a a() {
        return new a(this.b);
    }

    public String a(String str, String str2) {
        try {
            return SettingsContentProvider.getStringValue(b.a(this.b, SettingsContentProvider.getContentUri(this.b, str, "string"), null, null, null, null), str2);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("SettingsPreferences", "error = ", th);
            return "";
        }
    }

    public long a(String str, long j) {
        try {
            return SettingsContentProvider.getLongValue(b.a(this.b, SettingsContentProvider.getContentUri(this.b, str, SettingsContentProvider.LONG_TYPE), null, null, null, null), j);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("SettingsPreferences", "error = ", th);
            return 0;
        }
    }

    public int a(String str, int i) {
        try {
            return SettingsContentProvider.getIntValue(b.a(this.b, SettingsContentProvider.getContentUri(this.b, str, "integer"), null, null, null, null), i);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("SettingsPreferences", "error = ", th);
            return 0;
        }
    }
}
