package cn.sharesdk.tencent.qzone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;

public class ReceiveActivity extends Activity {
    private static String a;
    private static PlatformActionListener b;

    public static void a(String str) {
        a = str;
    }

    public static void a(PlatformActionListener platformActionListener) {
        b = platformActionListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            String scheme = intent.getScheme();
            if (scheme != null && scheme.startsWith(a)) {
                finish();
                Bundle urlToBundle = ResHelper.urlToBundle(intent.getDataString());
                String valueOf = String.valueOf(urlToBundle.get("result"));
                String valueOf2 = String.valueOf(urlToBundle.get("action"));
                if ("shareToQQ".equals(valueOf2) || "shareToQzone".equals(valueOf2)) {
                    if ("complete".equals(valueOf)) {
                        if (b != null) {
                            b.onComplete(null, 9, new Hashon().fromJson(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if ("error".equals(valueOf)) {
                        if (b != null) {
                            b.onError(null, 9, new Throwable(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if (b != null) {
                        b.onCancel(null, 9);
                    }
                }
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setClass(this, MobUIShell.class);
                intent2.setFlags(335544320);
                startActivity(intent2);
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            if (b != null) {
                b.onError(null, 9, th);
            }
        }
    }
}
