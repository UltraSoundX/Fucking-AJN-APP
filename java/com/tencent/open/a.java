package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.stub.StubApp;
import com.tencent.open.a.f;
import com.tencent.open.b.C0079b;
import com.tencent.open.b.g;
import com.tencent.open.d.j;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a extends c {
    static final LayoutParams a = new LayoutParams(-1, -1);
    static Toast b = null;
    private static WeakReference<ProgressDialog> f;
    /* access modifiers changed from: private */
    public WeakReference<Context> e;
    private String g;
    /* access modifiers changed from: private */
    public c h;
    private com.tencent.tauth.b i;
    private FrameLayout j;
    /* access modifiers changed from: private */
    public com.tencent.open.c.a k;
    private Handler l;
    private boolean m = false;
    private com.tencent.connect.b.b n = null;

    /* renamed from: com.tencent.open.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private class C0077a extends WebViewClient {
        private C0077a() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse;
            f.a("openSDK_LOG.TDialog", "Redirect URL: " + str);
            if (str.startsWith(com.tencent.open.d.f.a().a((Context) a.this.e.get(), "auth://tauth.qq.com/"))) {
                a.this.h.onComplete(j.b(str));
                if (a.this.isShowing()) {
                    a.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://cancel")) {
                a.this.h.onCancel();
                if (a.this.isShowing()) {
                    a.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://close")) {
                if (a.this.isShowing()) {
                    a.this.dismiss();
                }
                return true;
            } else if (str.startsWith("download://") || str.endsWith(".apk")) {
                try {
                    if (str.startsWith("download://")) {
                        parse = Uri.parse(Uri.decode(str.substring("download://".length())));
                    } else {
                        parse = Uri.parse(Uri.decode(str));
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", parse);
                    intent.addFlags(268435456);
                    if (!(a.this.e == null || a.this.e.get() == null)) {
                        ((Context) a.this.e.get()).startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } else if (str.startsWith("auth://progress")) {
                return true;
            } else {
                return false;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            a.this.h.onError(new com.tencent.tauth.d(i, str, str2));
            if (!(a.this.e == null || a.this.e.get() == null)) {
                Toast.makeText((Context) a.this.e.get(), "网络连接异常或系统错误", 0).show();
            }
            a.this.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            a.this.k.setVisibility(0);
        }
    }

    /* compiled from: ProGuard */
    private class b extends C0079b {
        private b() {
        }
    }

    /* compiled from: ProGuard */
    private static class c implements com.tencent.tauth.b {
        String a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private com.tencent.tauth.b e;

        public c(Context context, String str, String str2, String str3, com.tencent.tauth.b bVar) {
            this.c = new WeakReference<>(context);
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = bVar;
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            try {
                onComplete(j.c(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
                onError(new com.tencent.tauth.d(-4, "服务器返回数据格式有误!", str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.a, false);
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        public void onError(com.tencent.tauth.d dVar) {
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, dVar.a, dVar.b != null ? dVar.b + this.a : this.a, false);
            if (this.e != null) {
                this.e.onError(dVar);
                this.e = null;
            }
        }

        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }
    }

    /* compiled from: ProGuard */
    private class d extends Handler {
        private c b;

        public d(c cVar, Looper looper) {
            super(looper);
            this.b = cVar;
        }

        public void handleMessage(Message message) {
            f.b("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + message.what);
            switch (message.what) {
                case 1:
                    this.b.a((String) message.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    if (a.this.e != null && a.this.e.get() != null) {
                        a.c((Context) a.this.e.get(), (String) message.obj);
                        return;
                    }
                    return;
                case 5:
                    if (a.this.e != null && a.this.e.get() != null) {
                        a.d((Context) a.this.e.get(), (String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public a(Context context, String str, String str2, com.tencent.tauth.b bVar, com.tencent.connect.b.b bVar2) {
        super(context, 16973840);
        this.e = new WeakReference<>(context);
        this.g = str2;
        this.h = new c(context, str, str2, bVar2.b(), bVar);
        this.l = new d(this.h, context.getMainLooper());
        this.i = bVar;
        this.n = bVar2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        a();
        b();
    }

    public void onBackPressed() {
        if (this.h != null) {
            this.h.onCancel();
        }
        super.onBackPressed();
    }

    private void a() {
        new TextView((Context) this.e.get()).setText("test");
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.k = new com.tencent.open.c.a((Context) this.e.get());
        this.k.setLayoutParams(layoutParams);
        this.j = new FrameLayout((Context) this.e.get());
        layoutParams.gravity = 17;
        this.j.setLayoutParams(layoutParams);
        this.j.addView(this.k);
        setContentView(this.j);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        f.b("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.c.a((WebView) this.k, str);
        } catch (Exception e2) {
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        this.k.setVerticalScrollBarEnabled(false);
        this.k.setHorizontalScrollBarEnabled(false);
        this.k.setWebViewClient(new C0077a());
        this.k.setWebChromeClient(this.d);
        this.k.clearFormData();
        WebSettings settings = this.k.getSettings();
        if (settings != null) {
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setCacheMode(-1);
            settings.setNeedInitialFocus(false);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);
            settings.setRenderPriority(RenderPriority.HIGH);
            settings.setJavaScriptEnabled(true);
            if (!(this.e == null || this.e.get() == null)) {
                settings.setDatabaseEnabled(true);
                settings.setDatabasePath(StubApp.getOrigApplicationContext(((Context) this.e.get()).getApplicationContext()).getDir("databases", 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            this.c.a((C0079b) new b(), "sdk_js_if");
            this.k.loadUrl(this.g);
            this.k.setLayoutParams(a);
            this.k.setVisibility(4);
            this.k.getSettings().setSavePassword(false);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, String str) {
        try {
            JSONObject c2 = j.c(str);
            int i2 = c2.getInt("type");
            String string = c2.getString(NotificationCompat.CATEGORY_MESSAGE);
            if (i2 == 0) {
                if (b == null) {
                    b = Toast.makeText(context, string, 0);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(0);
                }
                b.show();
            } else if (i2 == 1) {
                if (b == null) {
                    b = Toast.makeText(context, string, 1);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(1);
                }
                b.show();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void d(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject c2 = j.c(str);
                int i2 = c2.getInt("action");
                String string = c2.getString(NotificationCompat.CATEGORY_MESSAGE);
                if (i2 == 1) {
                    if (f == null || f.get() == null) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(string);
                        f = new WeakReference<>(progressDialog);
                        progressDialog.show();
                        return;
                    }
                    ((ProgressDialog) f.get()).setMessage(string);
                    if (!((ProgressDialog) f.get()).isShowing()) {
                        ((ProgressDialog) f.get()).show();
                    }
                } else if (i2 == 0 && f != null && f.get() != null && ((ProgressDialog) f.get()).isShowing()) {
                    ((ProgressDialog) f.get()).dismiss();
                    f = null;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
