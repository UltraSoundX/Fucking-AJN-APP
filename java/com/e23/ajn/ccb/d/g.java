package com.e23.ajn.ccb.d;

import android.content.Context;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.c.a.a;
import com.c.a.c.a.d;
import com.c.a.c.b.b;
import com.c.a.c.c;
import com.e23.ajn.ccb.entity.BaseReq;
import com.e23.ajn.ccb.entity.SecurityReqBody;
import java.io.File;
import java.util.Map;
import org.apache.http.client.CookieStore;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: HttpXutils */
public class g {
    private static g f;
    a a = new a(60000);
    Context b;
    String c;
    BaseReq d;
    d e;

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (f == null) {
                f = new g();
            }
            gVar = f;
        }
        return gVar;
    }

    private g() {
        Log.i("Polling", "xutils init.");
        this.a.a(SSLSocketFactory.getSocketFactory());
    }

    public void a(Context context, String str, BaseReq baseReq, final SecurityReqBody securityReqBody, d dVar) {
        this.b = context;
        this.d = baseReq;
        this.c = str;
        this.e = dVar;
        new Runnable() {
            public void run() {
                Log.i("Polling", "xutils post." + g.this.c);
                c a2 = g.this.a(g.this.b, g.this.d, securityReqBody);
                Log.i("Polling", "xutils POST." + a2.toString());
                g.this.a.a(b.a.POST, g.this.c, a2, g.this.e);
            }
        }.run();
    }

    public void a(CookieStore cookieStore, String str, final File file, BaseReq baseReq, d dVar) {
        if (file != null) {
            this.a.a(cookieStore);
            this.d = baseReq;
            this.c = str;
            this.e = dVar;
            new Runnable() {
                public void run() {
                    c a2 = g.this.a(null, g.this.d, null);
                    a2.a("file", file, "image/JPEG");
                    Log.i("Polling", "xutils post." + g.this.c);
                    Log.i("Polling", "xutils POST." + a2.toString());
                    g.this.a.a(b.a.POST, g.this.c, a2, g.this.e);
                }
            }.run();
        }
    }

    /* access modifiers changed from: private */
    public c a(Context context, BaseReq baseReq, SecurityReqBody securityReqBody) {
        c cVar = new c();
        if (!(securityReqBody == null || context == null)) {
            String a2 = h.a(securityReqBody);
            Log.i("Polling", "securityReqBody xutils post." + (a2.contains("base64_Ecrp_Txn_Inf") ? a2.substring(0, a2.indexOf("base64_Ecrp_Txn_Inf")) : a2));
            baseReq.ccbParam = e.a(context, a2);
        }
        Map a3 = d.a(baseReq);
        for (String str : a3.keySet()) {
            Log.i("Polling", "params xutils post." + str + Config.TRACE_TODAY_VISIT_SPLIT + ((String) a3.get(str)));
            cVar.a(str, (String) a3.get(str));
        }
        return cVar;
    }
}
