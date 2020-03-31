package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* compiled from: AuthResp */
public class b extends WechatResp {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;

    public b(Bundle bundle) {
        super(bundle);
    }

    public int a() {
        return 1;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        this.a = bundle.getString("_wxapi_sendauth_resp_token");
        this.b = bundle.getString("_wxapi_sendauth_resp_state");
        this.c = bundle.getString("_wxapi_sendauth_resp_url");
        this.d = bundle.getString("_wxapi_sendauth_resp_lang");
        this.e = bundle.getString("_wxapi_sendauth_resp_country");
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        bundle.putString("_wxapi_sendauth_resp_token", this.a);
        bundle.putString("_wxapi_sendauth_resp_state", this.b);
        bundle.putString("_wxapi_sendauth_resp_url", this.c);
        bundle.putString("_wxapi_sendauth_resp_lang", this.d);
        bundle.putString("_wxapi_sendauth_resp_country", this.e);
    }
}
