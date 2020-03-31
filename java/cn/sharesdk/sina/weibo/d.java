package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.g;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.ResHelper;

/* compiled from: SinaWeiboAuthorizeWebviewClient */
public class d extends c {
    private boolean a;

    public d(g gVar) {
        super(gVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!TextUtils.isEmpty(this.redirectUri) && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
            return true;
        } else if (!str.startsWith("sms:")) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            String substring = str.substring(4);
            try {
                Intent a2 = a(substring);
                a2.setPackage("com.android.mms");
                webView.getContext().startActivity(a2);
                return true;
            } catch (Throwable th) {
                if (this.listener == null) {
                    return true;
                }
                this.listener.onError(th);
                return true;
            }
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.redirectUri) && str.startsWith(this.redirectUri)) {
            webView.stopLoading();
            this.activity.finish();
            onComplete(str);
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                Intent a2 = a(substring);
                a2.setPackage("com.android.mms");
                webView.getContext().startActivity(a2);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(th);
                }
            }
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onComplete(String str) {
        if (!this.a) {
            this.a = true;
            Bundle urlToBundle = ResHelper.urlToBundle(str);
            String string = urlToBundle.getString("error");
            String string2 = urlToBundle.getString("error_code");
            if (this.listener == null) {
                return;
            }
            if (string == null && string2 == null) {
                String string3 = urlToBundle.getString("code");
                if (TextUtils.isEmpty(string3)) {
                    this.listener.onError(new Throwable("Authorize code is empty"));
                }
                a(this.activity.a().getPlatform(), string3);
            } else if (string.equals("access_denied")) {
                this.listener.onCancel();
            } else {
                int i = 0;
                try {
                    i = ResHelper.parseInt(string2);
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                }
                this.listener.onError(new Throwable(string + " (" + i + ")"));
            }
        }
    }

    private void a(final Platform platform, final String str) {
        new Thread() {
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r4 = this;
                    cn.sharesdk.framework.Platform r0 = r2     // Catch:{ Throwable -> 0x007f }
                    cn.sharesdk.sina.weibo.h r0 = cn.sharesdk.sina.weibo.h.a(r0)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r1 = r3     // Catch:{ Throwable -> 0x001f }
                    java.lang.String r0 = r0.b(r1)     // Catch:{ Throwable -> 0x001f }
                L_0x000c:
                    if (r0 != 0) goto L_0x002b
                    cn.sharesdk.sina.weibo.d r0 = cn.sharesdk.sina.weibo.d.this     // Catch:{ Throwable -> 0x007f }
                    cn.sharesdk.framework.authorize.AuthorizeListener r0 = r0.listener     // Catch:{ Throwable -> 0x007f }
                    java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "Authorize token is empty"
                    r1.<init>(r2)     // Catch:{ Throwable -> 0x007f }
                    r0.onError(r1)     // Catch:{ Throwable -> 0x007f }
                L_0x001e:
                    return
                L_0x001f:
                    r0 = move-exception
                    cn.sharesdk.sina.weibo.d r1 = cn.sharesdk.sina.weibo.d.this     // Catch:{ Throwable -> 0x007f }
                    cn.sharesdk.framework.authorize.AuthorizeListener r1 = r1.listener     // Catch:{ Throwable -> 0x007f }
                    r1.onError(r0)     // Catch:{ Throwable -> 0x007f }
                    r0 = 0
                    goto L_0x000c
                L_0x002b:
                    com.mob.tools.utils.Hashon r1 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x007f }
                    r1.<init>()     // Catch:{ Throwable -> 0x007f }
                    java.util.HashMap r0 = r1.fromJson(r0)     // Catch:{ Throwable -> 0x007f }
                    android.os.Bundle r1 = new android.os.Bundle     // Catch:{ Throwable -> 0x007f }
                    r1.<init>()     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "uid"
                    java.lang.String r3 = "uid"
                    java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x007f }
                    r1.putString(r2, r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "remind_in"
                    java.lang.String r3 = "remind_in"
                    java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x007f }
                    r1.putString(r2, r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "expires_in"
                    java.lang.String r3 = "expires_in"
                    java.lang.Object r3 = r0.get(r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x007f }
                    r1.putString(r2, r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "access_token"
                    java.lang.String r3 = "access_token"
                    java.lang.Object r0 = r0.get(r3)     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x007f }
                    r1.putString(r2, r0)     // Catch:{ Throwable -> 0x007f }
                    cn.sharesdk.sina.weibo.d r0 = cn.sharesdk.sina.weibo.d.this     // Catch:{ Throwable -> 0x007f }
                    cn.sharesdk.framework.authorize.AuthorizeListener r0 = r0.listener     // Catch:{ Throwable -> 0x007f }
                    r0.onComplete(r1)     // Catch:{ Throwable -> 0x007f }
                    goto L_0x001e
                L_0x007f:
                    r0 = move-exception
                    com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
                    r1.d(r0)
                    goto L_0x001e
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.d.AnonymousClass1.run():void");
            }
        }.start();
    }

    private Intent a(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", "");
        intent.setFlags(268435456);
        return intent;
    }
}
