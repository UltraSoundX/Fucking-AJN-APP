package cn.sharesdk.sina.weibo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.a.a;
import com.baidu.mobstat.Config;
import com.mob.tools.FakeActivity;
import com.mob.tools.RxMob;
import com.mob.tools.RxMob.OnSubscribe;
import com.mob.tools.RxMob.Subscribable;
import com.mob.tools.RxMob.Subscriber;
import com.mob.tools.RxMob.Thread;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;

/* compiled from: WebSharePage */
public class g extends FakeActivity implements OnClickListener {
    private String a;
    private String b;
    private ShareParams c;
    /* access modifiers changed from: private */
    public AuthorizeListener d;
    private a e;
    /* access modifiers changed from: private */
    public LinearLayout f;
    private TextView g;
    /* access modifiers changed from: private */
    public WebView h;
    private Button i;
    /* access modifiers changed from: private */
    public int j;

    public void a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public void a(ShareParams shareParams) {
        this.c = shareParams;
    }

    public void a(AuthorizeListener authorizeListener) {
        this.d = authorizeListener;
    }

    public void onCreate() {
        this.e = new a(getContext());
        this.activity.setContentView(this.e.a(ResHelper.getStringRes(getContext(), "ssdk_sina_web_title")));
        a();
    }

    private void a() {
        this.g = this.e.b();
        this.h = this.e.c();
        this.f = this.e.d();
        this.i = this.e.a();
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
        if (ShareSDK.isRemoveCookieOnAuthorize()) {
            CookieSyncManager.createInstance(this.activity);
            CookieManager.getInstance().removeAllCookie();
        }
        this.h.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                if (g.this.j == -1) {
                    g.this.f.setVisibility(0);
                    g.this.h.setVisibility(8);
                }
                g.this.j = 0;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                String url = webView.getUrl();
                if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(str2)) {
                    Uri parse = Uri.parse(url);
                    Uri parse2 = Uri.parse(str2);
                    if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
                        g.this.j = -1;
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.startsWith("sinaweibo://browser/close")) {
                    return false;
                }
                if (g.this.d != null) {
                    g.this.a(str);
                }
                return true;
            }
        });
        b();
    }

    public void onClick(View view) {
        if (view == this.g) {
            if (this.d != null) {
                this.d.onCancel();
            }
            finish();
        } else if (view == this.i) {
            this.f.setVisibility(8);
            this.h.setVisibility(0);
            b();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        String string = urlToBundle.getString("code");
        String string2 = urlToBundle.getString(NotificationCompat.CATEGORY_MESSAGE);
        if (this.d != null) {
            if (TextUtils.isEmpty(string)) {
                this.d.onCancel();
            } else if ("0".equals(string)) {
                this.d.onComplete(urlToBundle);
            } else {
                this.d.onError(new Throwable(string2));
            }
        }
        finish();
    }

    /* access modifiers changed from: private */
    public String b(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("title", this.c.getText()));
        arrayList.add(new KVPair("source", this.a));
        arrayList.add(new KVPair("access_token", this.b));
        arrayList.add(new KVPair("packagename", this.activity.getPackageName()));
        arrayList.add(new KVPair("picinfo", str));
        arrayList.add(new KVPair("luicode", "10000360"));
        arrayList.add(new KVPair("key_hash", cn.sharesdk.sina.weibo.sdk.a.a(getContext(), this.activity.getPackageName())));
        arrayList.add(new KVPair("lfid", "OP_" + this.a));
        arrayList.add(new KVPair(Config.INPUT_DEF_VERSION, "0041005000"));
        return "http://service.weibo.com/share/mobilesdk.php?" + ResHelper.encodeUrl(arrayList);
    }

    private String b() {
        Subscribable create = RxMob.create(new OnSubscribe<String>() {
            public void call(Subscriber<String> subscriber) {
                subscriber.onNext(g.this.c());
            }
        });
        create.subscribeOn(Thread.NEW_THREAD);
        create.observeOn(Thread.UI_THREAD);
        create.subscribe(new Subscriber<String>() {
            public void onStart() {
                super.onStart();
            }

            /* renamed from: a */
            public void onNext(String str) {
                g.this.h.loadUrl(g.this.b(str));
            }

            public void onCompleted() {
                super.onCompleted();
            }

            public void onError(Throwable th) {
                SSDKLog.b().w(th);
                g.this.h.loadUrl(g.this.b((String) null));
            }
        });
        return null;
    }

    public boolean onFinish() {
        if (this.d != null) {
            this.d = null;
        }
        return super.onFinish();
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getAction() != 0) {
            return false;
        }
        if (this.d != null) {
            this.d.onCancel();
        }
        finish();
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A[Catch:{ Throwable -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063 A[Catch:{ Throwable -> 0x00ff }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String c() {
        /*
            r10 = this;
            r6 = 0
            cn.sharesdk.framework.Platform$ShareParams r0 = r10.c     // Catch:{ Throwable -> 0x00ff }
            android.graphics.Bitmap r0 = r0.getImageData()     // Catch:{ Throwable -> 0x00ff }
            if (r0 != 0) goto L_0x0046
            cn.sharesdk.framework.Platform$ShareParams r1 = r10.c     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r1 = r1.getImagePath()     // Catch:{ Throwable -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r1 != 0) goto L_0x0046
            cn.sharesdk.framework.Platform$ShareParams r0 = r10.c     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r0 = r0.getImagePath()     // Catch:{ Throwable -> 0x00ff }
            android.graphics.Bitmap r0 = com.mob.tools.utils.BitmapHelper.getBitmap(r0)     // Catch:{ Throwable -> 0x00ff }
        L_0x001f:
            if (r0 == 0) goto L_0x010a
            com.sina.weibo.sdk.api.ImageObject r1 = new com.sina.weibo.sdk.api.ImageObject     // Catch:{ Throwable -> 0x00ff }
            r1.<init>()     // Catch:{ Throwable -> 0x00ff }
            r1.setImageObject(r0)     // Catch:{ Throwable -> 0x00ff }
            byte[] r0 = r1.imageData     // Catch:{ Throwable -> 0x00ff }
            if (r0 == 0) goto L_0x010a
            byte[] r0 = r1.imageData     // Catch:{ Throwable -> 0x00ff }
            int r0 = r0.length     // Catch:{ Throwable -> 0x00ff }
            if (r0 <= 0) goto L_0x010a
            java.lang.String r0 = new java.lang.String     // Catch:{ Throwable -> 0x00ff }
            byte[] r1 = r1.imageData     // Catch:{ Throwable -> 0x00ff }
            byte[] r1 = cn.sharesdk.sina.weibo.sdk.a.a(r1)     // Catch:{ Throwable -> 0x00ff }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00ff }
            r1 = r0
        L_0x003e:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r0 == 0) goto L_0x0063
            r0 = r6
        L_0x0045:
            return r0
        L_0x0046:
            if (r0 != 0) goto L_0x001f
            cn.sharesdk.framework.Platform$ShareParams r1 = r10.c     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r1 = r1.getImageUrl()     // Catch:{ Throwable -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r1 != 0) goto L_0x001f
            android.content.Context r0 = r10.getContext()     // Catch:{ Throwable -> 0x00ff }
            cn.sharesdk.framework.Platform$ShareParams r1 = r10.c     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r1 = r1.getImageUrl()     // Catch:{ Throwable -> 0x00ff }
            android.graphics.Bitmap r0 = com.mob.tools.utils.BitmapHelper.getBitmap(r0, r1)     // Catch:{ Throwable -> 0x00ff }
            goto L_0x001f
        L_0x0063:
            com.mob.tools.network.NetworkHelper r0 = new com.mob.tools.network.NetworkHelper     // Catch:{ Throwable -> 0x00ff }
            r0.<init>()     // Catch:{ Throwable -> 0x00ff }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00ff }
            r2.<init>()     // Catch:{ Throwable -> 0x00ff }
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r4 = "img"
            r3.<init>(r4, r1)     // Catch:{ Throwable -> 0x00ff }
            r2.add(r3)     // Catch:{ Throwable -> 0x00ff }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00ff }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r8
            java.lang.String r1 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x00ff }
            com.mob.tools.network.KVPair r3 = new com.mob.tools.network.KVPair     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r4 = "oauth_timestamp"
            r3.<init>(r4, r1)     // Catch:{ Throwable -> 0x00ff }
            r2.add(r3)     // Catch:{ Throwable -> 0x00ff }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ff }
            r1.<init>()     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r3 = r10.b     // Catch:{ Throwable -> 0x00ff }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00ff }
            if (r3 != 0) goto L_0x009e
            java.lang.String r3 = r10.b     // Catch:{ Throwable -> 0x00ff }
            r1.append(r3)     // Catch:{ Throwable -> 0x00ff }
        L_0x009e:
            java.lang.String r3 = r10.a     // Catch:{ Throwable -> 0x00ff }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x00ff }
            if (r3 != 0) goto L_0x00ab
            java.lang.String r3 = r10.a     // Catch:{ Throwable -> 0x00ff }
            r1.append(r3)     // Catch:{ Throwable -> 0x00ff }
        L_0x00ab:
            java.lang.String r1 = "http://service.weibo.com/share/mobilesdk_uppic.php"
            r3 = 0
            r4 = 0
            r5 = 0
            java.lang.String r0 = r0.httpPost(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00ff }
            if (r1 == 0) goto L_0x00bc
            r0 = r6
            goto L_0x0045
        L_0x00bc:
            com.mob.tools.utils.Hashon r1 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x00ff }
            r1.<init>()     // Catch:{ Throwable -> 0x00ff }
            java.util.HashMap r0 = r1.fromJson(r0)     // Catch:{ Throwable -> 0x00ff }
            if (r0 == 0) goto L_0x00d7
            java.lang.String r1 = "code"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r1 == 0) goto L_0x00d7
            java.lang.String r1 = "data"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r1 != 0) goto L_0x00da
        L_0x00d7:
            r0 = r6
            goto L_0x0045
        L_0x00da:
            java.lang.String r1 = "code"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r2 = "data"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r2 = "1"
            boolean r1 = r2.equals(r1)     // Catch:{ Throwable -> 0x00ff }
            if (r1 == 0) goto L_0x00fc
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00ff }
            if (r1 == 0) goto L_0x0045
        L_0x00fc:
            r0 = r6
            goto L_0x0045
        L_0x00ff:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
            r1.d(r0)
            r0 = r6
            goto L_0x0045
        L_0x010a:
            r1 = r6
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.g.c():java.lang.String");
    }

    public void onDestroy() {
        if (this.h != null) {
            this.h.setFocusable(false);
        }
    }
}
