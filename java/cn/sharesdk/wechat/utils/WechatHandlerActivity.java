package cn.sharesdk.wechat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;

public class WechatHandlerActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setTheme(16973839);
        super.onCreate(bundle);
        try {
            k.a().a(this);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            k.a().a(this);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
        finish();
    }

    public void onGetMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    public void onShowMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }
}
