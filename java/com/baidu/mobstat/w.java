package com.baidu.mobstat;

import android.content.Context;
import org.json.JSONObject;

class w implements a {
    private z a = z.a;
    private Object b;
    private Class<?> c;

    public w(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("proxy is null.");
        } else if (!"com.baidu.bottom.remote.BPStretegyController2".equals(obj.getClass().getName())) {
            throw new IllegalArgumentException("class isn't com.baidu.bottom.remote.BPStretegyController2");
        } else {
            this.b = obj;
            this.c = obj.getClass();
        }
    }

    public void a(Context context, JSONObject jSONObject) {
        try {
            a(new Object[]{context, jSONObject}, "startDataAnynalyze", new Class[]{Context.class, JSONObject.class});
        } catch (Exception e) {
            al.c().b((Throwable) e);
            this.a.a(context, jSONObject);
        }
    }

    public void a(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteConfig2", new Class[]{Context.class, String.class});
        } catch (Exception e) {
            al.c().b((Throwable) e);
            this.a.a(context, str);
        }
    }

    public void b(Context context, String str) {
        try {
            a(new Object[]{context, str}, "saveRemoteSign", new Class[]{Context.class, String.class});
        } catch (Exception e) {
            al.c().b((Throwable) e);
            this.a.b(context, str);
        }
    }

    public void a(Context context, long j) {
        try {
            a(new Object[]{context, Long.valueOf(j)}, "setLastUpdateTime", new Class[]{Context.class, Long.TYPE});
        } catch (Exception e) {
            al.c().b((Throwable) e);
            this.a.a(context, j);
        }
    }

    public boolean a(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "needUpdate", new Class[]{Context.class})).booleanValue();
        } catch (Exception e) {
            al.c().b((Throwable) e);
            return this.a.a(context);
        }
    }

    public boolean b(Context context) {
        try {
            return ((Boolean) a(new Object[]{context}, "canStartService", new Class[]{Context.class})).booleanValue();
        } catch (Exception e) {
            al.c().b((Throwable) e);
            return this.a.b(context);
        }
    }

    private <T> T a(Object[] objArr, String str, Class<?>[] clsArr) throws Exception {
        return this.c.getMethod(str, clsArr).invoke(this.b, objArr);
    }
}
