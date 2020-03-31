package com.tencent.android.tpush.service.channel.c;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import com.stub.StubApp;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.mid.core.Constants;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class d {
    private static volatile d c = null;
    private int a = 10;
    private int b = 0;
    private Context d = null;
    private boolean e = false;

    public boolean a(String str, String str2) {
        if (this.e) {
            try {
                return System.putString(this.d.getContentResolver(), str, str2);
            } catch (Throwable th) {
                int i = this.b;
                this.b = i + 1;
                if (i < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public String a(String str) {
        try {
            return System.getString(this.d.getContentResolver(), str);
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean a(String str, int i) {
        if (this.e) {
            try {
                return System.putInt(this.d.getContentResolver(), str, i);
            } catch (Throwable th) {
                int i2 = this.b;
                this.b = i2 + 1;
                if (i2 < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean a(String str, long j) {
        if (this.e) {
            try {
                return System.putLong(this.d.getContentResolver(), str, j);
            } catch (Throwable th) {
                int i = this.b;
                this.b = i + 1;
                if (i < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean a(String str, float f) {
        if (this.e) {
            try {
                return System.putFloat(this.d.getContentResolver(), str, f);
            } catch (Throwable th) {
                int i = this.b;
                this.b = i + 1;
                if (i < this.a) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    private d(Context context) {
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
        try {
            this.e = f.a(this.d, Constants.PERMISSION_WRITE_SETTINGS);
            if (this.e && VERSION.SDK_INT >= 23) {
                Method declaredMethod = System.class.getDeclaredMethod("canWrite", new Class[]{Context.class});
                declaredMethod.setAccessible(true);
                this.e = ((Boolean) declaredMethod.invoke(null, new Object[]{this.d})).booleanValue();
            }
        } catch (Throwable th) {
            int i = this.b;
            this.b = i + 1;
            if (i < this.a) {
                th.printStackTrace();
            }
        }
    }

    public static d a(Context context) {
        if (c == null) {
            synchronized (d.class) {
                if (c == null) {
                    c = new d(context);
                }
            }
        }
        return c;
    }
}
