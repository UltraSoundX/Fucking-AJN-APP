package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.sharesdk.framework.authorize.e;
import org.json.JSONObject;

/* compiled from: QQSSOProcessor */
public class d extends e {
    private String d;
    private String e;
    private String f;

    public d(cn.sharesdk.framework.authorize.d dVar) {
        super(dVar);
    }

    public void a(String str, String str2, String str3) {
        this.d = str;
        this.e = str2;
        this.f = str3;
    }

    public void a() {
        if (TextUtils.isEmpty(this.f) || this.f.equals("com.tencent.qqlite")) {
            b();
            this.a.finish();
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f, "com.tencent.open.agent.AgentActivity");
        if (this.a.getContext().getPackageManager().resolveActivity(intent, 0) == null) {
            this.a.finish();
            if (this.c != null) {
                b();
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("scope", this.e);
        bundle.putString("client_id", this.d);
        bundle.putString("pf", "openmobile_android");
        bundle.putString("need_pay", "1");
        intent.putExtra("key_params", bundle);
        intent.putExtra("key_request_code", this.b);
        intent.putExtra("key_action", "action_login");
        try {
            this.a.startActivityForResult(intent, this.b);
        } catch (Throwable th) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(th);
            }
        }
    }

    public void a(int i, int i2, Intent intent) {
        this.a.finish();
        if (i2 == 0) {
            if (this.c != null) {
                this.c.onCancel();
            }
        } else if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (extras.containsKey("key_response")) {
                String string = extras.getString("key_response");
                if (string != null && string.length() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ret", jSONObject.optInt("ret"));
                        bundle.putString("pay_token", jSONObject.optString("pay_token"));
                        bundle.putString("pf", jSONObject.optString("pf"));
                        bundle.putString("open_id", jSONObject.optString("openid"));
                        bundle.putString("expires_in", jSONObject.optString("expires_in"));
                        bundle.putString("pfkey", jSONObject.optString("pfkey"));
                        bundle.putString(NotificationCompat.CATEGORY_MESSAGE, jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE));
                        bundle.putString("access_token", jSONObject.optString("access_token"));
                        String optString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
                        if (!TextUtils.isEmpty(optString) && this.c != null) {
                            this.c.onFailed(new Throwable(optString));
                        } else if (this.c != null) {
                            this.c.onComplete(bundle);
                            this.c = null;
                        }
                    } catch (Throwable th) {
                        if (this.c != null) {
                            this.c.onFailed(th);
                        }
                    }
                } else if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (this.c != null) {
                this.c.onFailed(new Throwable("response is empty"));
            }
        } else if (this.c != null) {
            this.c.onFailed(new Throwable("response is empty"));
        }
    }

    private void b() {
        a aVar = new a();
        aVar.a(this.c);
        aVar.show(this.a.getContext(), null);
    }
}
