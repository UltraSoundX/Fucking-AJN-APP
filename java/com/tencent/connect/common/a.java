package com.tencent.connect.common;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.connect.b.b;
import com.tencent.connect.b.c;
import com.tencent.open.d.a.C0080a;
import com.tencent.open.d.d;
import com.tencent.open.d.g;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class a {
    public static String e = null;
    public static String f = null;
    public static String g = null;
    public static boolean h = false;
    protected c c;
    /* access modifiers changed from: protected */
    public b d;

    /* renamed from: com.tencent.connect.common.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public class C0076a implements com.tencent.tauth.a {
        /* access modifiers changed from: private */
        public final com.tencent.tauth.b b;
        private final Handler c;

        public C0076a(com.tencent.tauth.b bVar) {
            this.b = bVar;
            this.c = new Handler(d.a().getMainLooper(), a.this) {
                public void handleMessage(Message message) {
                    if (message.what == 0) {
                        C0076a.this.b.onComplete(message.obj);
                    } else {
                        C0076a.this.b.onError(new com.tencent.tauth.d(message.what, (String) message.obj, null));
                    }
                }
            };
        }

        public void a(JSONObject jSONObject) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONObject;
            obtainMessage.what = 0;
            this.c.sendMessage(obtainMessage);
        }

        public void a(IOException iOException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = iOException.getMessage();
            obtainMessage.what = -2;
            this.c.sendMessage(obtainMessage);
        }

        public void a(MalformedURLException malformedURLException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = malformedURLException.getMessage();
            obtainMessage.what = -3;
            this.c.sendMessage(obtainMessage);
        }

        public void a(JSONException jSONException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONException.getMessage();
            obtainMessage.what = -4;
            this.c.sendMessage(obtainMessage);
        }

        public void a(ConnectTimeoutException connectTimeoutException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = connectTimeoutException.getMessage();
            obtainMessage.what = -7;
            this.c.sendMessage(obtainMessage);
        }

        public void a(SocketTimeoutException socketTimeoutException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = socketTimeoutException.getMessage();
            obtainMessage.what = -8;
            this.c.sendMessage(obtainMessage);
        }

        public void a(com.tencent.open.d.a.b bVar) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = bVar.getMessage();
            obtainMessage.what = -10;
            this.c.sendMessage(obtainMessage);
        }

        public void a(C0080a aVar) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = aVar.getMessage();
            obtainMessage.what = -9;
            this.c.sendMessage(obtainMessage);
        }

        public void a(Exception exc) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = exc.getMessage();
            obtainMessage.what = -6;
            this.c.sendMessage(obtainMessage);
        }
    }

    public a(c cVar, b bVar) {
        this.c = cVar;
        this.d = bVar;
    }

    public a(b bVar) {
        this(null, bVar);
    }

    /* access modifiers changed from: protected */
    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString("sdkv", "3.3.5.lite");
        bundle.putString("sdkp", Config.APP_VERSION_CODE);
        if (this.d != null && this.d.a()) {
            bundle.putString("access_token", this.d.c());
            bundle.putString("oauth_consumer_key", this.d.b());
            bundle.putString("openid", this.d.d());
            bundle.putString("appid_for_getting_config", this.d.b());
        }
        SharedPreferences sharedPreferences = d.a().getSharedPreferences("pfStore", 0);
        if (h) {
            bundle.putString("pf", "desktop_m_qq-" + f + "-" + "android" + "-" + e + "-" + g);
        } else {
            bundle.putString("pf", sharedPreferences.getString("pf", "openmobile_android"));
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        Bundle a = a();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            a.putString("need_version", str);
        }
        sb.append("http://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        sb.append(com.tencent.open.d.a.a(a));
        return sb.toString();
    }

    private Intent a(Activity activity, Intent intent) {
        Intent intent2 = new Intent(StubApp.getOrigApplicationContext(activity.getApplicationContext()), AssistActivity.class);
        intent2.putExtra("is_login", true);
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        return intent2;
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, int i, Intent intent, boolean z) {
        Intent intent2 = new Intent(StubApp.getOrigApplicationContext(activity.getApplicationContext()), AssistActivity.class);
        if (z) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        activity.startActivityForResult(intent2, i);
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, Intent intent, int i) {
        intent.putExtra("key_request_code", i);
        activity.startActivityForResult(a(activity, intent), i);
    }

    /* access modifiers changed from: protected */
    public boolean a(Intent intent) {
        if (intent != null) {
            return g.a(d.a(), intent);
        }
        return false;
    }
}
