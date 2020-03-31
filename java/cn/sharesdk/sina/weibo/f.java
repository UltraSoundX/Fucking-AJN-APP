package cn.sharesdk.sina.weibo;

import android.net.Uri;
import android.os.Bundle;
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
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: WebAuthPage */
public class f extends FakeActivity implements OnClickListener {
    /* access modifiers changed from: private */
    public AuthorizeListener a;
    private String b;
    /* access modifiers changed from: private */
    public String c;
    private String d;
    /* access modifiers changed from: private */
    public int e;
    private a f;
    /* access modifiers changed from: private */
    public LinearLayout g;
    private TextView h;
    /* access modifiers changed from: private */
    public WebView i;
    private Button j;

    public void a(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public void a(AuthorizeListener authorizeListener) {
        this.a = authorizeListener;
    }

    public void onCreate() {
        this.f = new a(getContext());
        this.activity.setContentView(this.f.a(ResHelper.getStringRes(getContext(), "ssdk_sina_web_login_title")));
        a();
    }

    private void a() {
        this.h = this.f.b();
        this.i = this.f.c();
        this.g = this.f.d();
        this.j = this.f.a();
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
        if (ShareSDK.isRemoveCookieOnAuthorize()) {
            CookieSyncManager.createInstance(this.activity);
            CookieManager.getInstance().removeAllCookie();
        }
        this.i.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                if (f.this.e == -1) {
                    f.this.g.setVisibility(0);
                    f.this.i.setVisibility(8);
                }
                f.this.e = 0;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                String url = webView.getUrl();
                if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(str2)) {
                    Uri parse = Uri.parse(url);
                    Uri parse2 = Uri.parse(str2);
                    if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
                        f.this.e = -1;
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.startsWith(f.this.c)) {
                    return false;
                }
                if (f.this.a != null) {
                    f.this.a(str);
                }
                return true;
            }
        });
        c();
    }

    public void onClick(View view) {
        if (view == this.h && this.a != null) {
            this.a.onCancel();
            finish();
        } else if (view == this.j) {
            this.g.setVisibility(8);
            this.i.setVisibility(0);
            c();
        }
    }

    /* access modifiers changed from: private */
    public String b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.b));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("redirect_uri", this.c));
        arrayList.add(new KVPair("packagename", this.activity.getPackageName()));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("luicode", "10000360"));
        if (this.d != null && !TextUtils.isEmpty(this.d)) {
            arrayList.add(new KVPair("trans_token", this.d));
            arrayList.add(new KVPair("trans_access_token", this.d));
        }
        arrayList.add(new KVPair("key_hash", cn.sharesdk.sina.weibo.sdk.a.a(getContext(), this.activity.getPackageName())));
        arrayList.add(new KVPair(Config.INPUT_DEF_VERSION, "0041005000"));
        arrayList.add(new KVPair("scope", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write"));
        arrayList.add(new KVPair("display", NetworkUtil.NETWORK_MOBILE));
        return "https://open.weibo.cn/oauth2/authorize?" + ResHelper.encodeUrl(arrayList);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        if (urlToBundle.containsKey("access_token")) {
            this.a.onComplete(urlToBundle);
        } else if (urlToBundle.containsKey("error")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("error_uri:", urlToBundle.containsKey("error_uri") ? urlToBundle.getString("error_uri") : "");
                jSONObject.put("error:", urlToBundle.containsKey("error") ? urlToBundle.getString("error") : "");
                jSONObject.put("error_description:", urlToBundle.containsKey("error_description") ? urlToBundle.getString("error_description") : "");
                jSONObject.put("error_code:", urlToBundle.containsKey("error_code") ? urlToBundle.getString("error_code") : "");
                this.a.onError(new Throwable(jSONObject.toString()));
            } catch (Throwable th) {
                SSDKLog.b().e(th);
                this.a.onError(th);
            }
        }
        finish();
    }

    public boolean onFinish() {
        if (this.a != null) {
            this.a = null;
        }
        return super.onFinish();
    }

    private String c() {
        Subscribable create = RxMob.create(new OnSubscribe<String>() {
            public void call(Subscriber<String> subscriber) {
                f.this.i.loadUrl(f.this.b());
                subscriber.onCompleted();
            }
        });
        create.subscribeOn(Thread.UI_THREAD);
        create.observeOn(Thread.UI_THREAD);
        create.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                super.onCompleted();
            }

            public void onError(Throwable th) {
                SSDKLog.b().d(th);
            }
        });
        return null;
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getAction() != 0) {
            return false;
        }
        if (this.a != null) {
            this.a.onCancel();
        }
        finish();
        return true;
    }

    public void onDestroy() {
        if (this.i != null) {
            this.i.setFocusable(false);
        }
    }
}
