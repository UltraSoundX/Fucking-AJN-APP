package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;

public class ReceiveActivity extends Activity {
    private static PlatformActionListener listener;
    private static String uriScheme;

    public static void setUriScheme(String str) {
        uriScheme = str;
    }

    public static void setPlatformActionListener(PlatformActionListener platformActionListener) {
        listener = platformActionListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            String scheme = intent.getScheme();
            finish();
            if (scheme != null && scheme.startsWith(uriScheme)) {
                Bundle urlToBundle = ResHelper.urlToBundle(intent.getDataString());
                String valueOf = String.valueOf(urlToBundle.get("result"));
                String valueOf2 = String.valueOf(urlToBundle.get("action"));
                if ("shareToQQ".equals(valueOf2) || "shareToQzone".equals(valueOf2)) {
                    if ("complete".equals(valueOf)) {
                        if (listener != null) {
                            listener.onComplete(null, 9, new Hashon().fromJson(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if ("error".equals(valueOf)) {
                        if (listener != null) {
                            listener.onError(null, 9, new Throwable(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if (listener != null) {
                        listener.onCancel(null, 9);
                    }
                }
                if (VERSION.SDK_INT <= 22) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setClass(this, MobUIShell.class);
                    intent2.setFlags(335544320);
                    startActivity(intent2);
                }
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            if (listener != null) {
                listener.onError(null, 9, th);
            }
        }
    }
}
