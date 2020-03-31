package com.baidu.location.a;

import android.content.Context;
import android.util.Log;
import com.baidu.b.a.b;
import com.baidu.b.a.c;
import com.baidu.location.g.a;
import com.tencent.android.tpush.common.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import org.json.JSONObject;

public class j implements c {
    private static Object a = new Object();
    private static j b = null;
    private int c = 0;
    private Context d = null;
    private long e = 0;
    private String f = null;

    public static j a() {
        j jVar;
        synchronized (a) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }

    public static String b(Context context) {
        try {
            return b.a(context).b(context);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c(Context context) {
        try {
            return b.a(context).a();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(int i, String str) {
        this.c = i;
        if (this.c == 0) {
            Log.i(a.a, "LocationAuthManager Authentication AUTHENTICATE_SUCC");
        } else {
            Log.i(a.a, "LocationAuthManager Authentication Error errorcode = " + i + " , msg = " + str);
        }
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null && jSONObject.getString(Constants.FLAG_TOKEN) != null) {
                    this.f = jSONObject.getString(Constants.FLAG_TOKEN);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(Context context) {
        this.d = context;
        b.a(this.d).a(false, "lbs_locsdk", null, (c) this);
        this.e = System.currentTimeMillis();
    }

    public boolean b() {
        boolean z = true;
        if (!(this.c == 0 || this.c == 602 || this.c == 601 || this.c == -10 || this.c == -11)) {
            z = false;
        }
        if (this.d != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.e;
            if (z) {
                if (currentTimeMillis > 86400000) {
                    b.a(this.d).a(false, "lbs_locsdk", null, (c) this);
                    this.e = System.currentTimeMillis();
                }
            } else if (currentTimeMillis < 0 || currentTimeMillis > OkHttpUtils.DEFAULT_MILLISECONDS) {
                b.a(this.d).a(false, "lbs_locsdk", null, (c) this);
                this.e = System.currentTimeMillis();
            }
        }
        return z;
    }
}
