package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* compiled from: WechatReq */
public abstract class l {
    public String d;

    public abstract int a();

    public abstract boolean a(boolean z);

    public void a(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", a());
        bundle.putString("_wxapi_basereq_transaction", this.d);
    }
}
