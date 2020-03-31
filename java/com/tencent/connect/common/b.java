package com.tencent.connect.common;

import android.content.Intent;
import com.tencent.open.a.f;
import com.tencent.open.d.g;
import com.tencent.open.d.j;
import com.tencent.tauth.d;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    private static b a = null;
    private Map<String, a> b = Collections.synchronizedMap(new HashMap());

    /* compiled from: ProGuard */
    public class a {
        public int a;
        public com.tencent.tauth.b b;

        public a(int i, com.tencent.tauth.b bVar) {
            this.a = i;
            this.b = bVar;
        }
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private b() {
        if (this.b == null) {
            this.b = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object a(int i, com.tencent.tauth.b bVar) {
        a aVar;
        String a2 = g.a(i);
        if (a2 == null) {
            f.e("openSDK_LOG.UIListenerManager", "setListener action is null! rquestCode=" + i);
            return null;
        }
        synchronized (this.b) {
            aVar = (a) this.b.put(a2, new a(i, bVar));
        }
        if (aVar == null) {
            return null;
        }
        return aVar.b;
    }

    public Object a(String str, com.tencent.tauth.b bVar) {
        a aVar;
        int a2 = g.a(str);
        if (a2 == -1) {
            f.e("openSDK_LOG.UIListenerManager", "setListnerWithAction fail, action = " + str);
            return null;
        }
        synchronized (this.b) {
            aVar = (a) this.b.put(str, new a(a2, bVar));
        }
        if (aVar == null) {
            return null;
        }
        return aVar.b;
    }

    public com.tencent.tauth.b a(String str) {
        a aVar;
        if (str == null) {
            f.e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
            return null;
        }
        synchronized (this.b) {
            aVar = (a) this.b.get(str);
            this.b.remove(str);
        }
        if (aVar == null) {
            return null;
        }
        return aVar.b;
    }

    public void a(Intent intent, com.tencent.tauth.b bVar) {
        String str;
        f.c("openSDK_LOG.UIListenerManager", "handleDataToListener");
        if (intent == null) {
            bVar.onCancel();
            return;
        }
        String stringExtra = intent.getStringExtra("key_action");
        if ("action_login".equals(stringExtra)) {
            int intExtra = intent.getIntExtra("key_error_code", 0);
            if (intExtra == 0) {
                String stringExtra2 = intent.getStringExtra("key_response");
                if (stringExtra2 != null) {
                    try {
                        bVar.onComplete(j.c(stringExtra2));
                    } catch (JSONException e) {
                        bVar.onError(new d(-4, "服务器返回数据格式有误!", stringExtra2));
                        f.a("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                    }
                } else {
                    f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                    bVar.onComplete(new JSONObject());
                }
            } else {
                f.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + intExtra + "");
                bVar.onError(new d(intExtra, intent.getStringExtra("key_error_msg"), intent.getStringExtra("key_error_detail")));
            }
        } else if ("action_share".equals(stringExtra)) {
            String stringExtra3 = intent.getStringExtra("result");
            String stringExtra4 = intent.getStringExtra("response");
            if ("cancel".equals(stringExtra3)) {
                bVar.onCancel();
            } else if ("error".equals(stringExtra3)) {
                bVar.onError(new d(-6, "unknown error", stringExtra4 + ""));
            } else if ("complete".equals(stringExtra3)) {
                try {
                    if (stringExtra4 == null) {
                        str = "{\"ret\": 0}";
                    } else {
                        str = stringExtra4;
                    }
                    bVar.onComplete(new JSONObject(str));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    bVar.onError(new d(-4, "json error", stringExtra4 + ""));
                }
            }
        }
    }
}
