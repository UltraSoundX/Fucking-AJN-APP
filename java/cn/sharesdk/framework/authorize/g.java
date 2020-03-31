package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.ResizeLayout.OnResizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;

/* compiled from: WebAuthorizeActivity */
public class g extends a implements Callback, OnResizeListener {
    protected AuthorizeListener b;
    private AuthorizeAdapter c;
    private RegisterView d;
    private WebView e;

    /* compiled from: WebAuthorizeActivity */
    private static class a implements Interpolator {
        private float[] a;

        private a() {
            this.a = new float[]{0.0f, 0.02692683f, 0.053847015f, 0.080753915f, 0.10764089f, 0.13450131f, 0.16132854f, 0.18811597f, 0.21485697f, 0.24154496f, 0.26817337f, 0.2947356f, 0.3212251f, 0.34763536f, 0.37395984f, 0.40019205f, 0.42632553f, 0.4523538f, 0.47827047f, 0.50406915f, 0.52974343f, 0.555287f, 0.5806936f, 0.60595685f, 0.6310707f, 0.65602875f, 0.68082494f, 0.70545316f, 0.72990733f, 0.75418144f, 0.7782694f, 0.8021654f, 0.8258634f, 0.8493577f, 0.8726424f, 0.89571184f, 0.9185602f, 0.94118196f, 0.9635715f, 0.9857233f, 1.0076319f, 1.0292919f, 1.0506978f, 1.0718446f, 1.0927268f, 1.1133395f, 1.1336775f, 1.1537358f, 1.1735094f, 1.1929934f, 1.1893399f, 1.1728106f, 1.1565471f, 1.1405534f, 1.1248333f, 1.1093911f, 1.0942302f, 1.0793544f, 1.0647675f, 1.050473f, 1.0364745f, 1.0227754f, 1.0093791f, 0.99628896f, 0.9835081f, 0.9710398f, 0.958887f, 0.9470527f, 0.93553996f, 0.9243516f, 0.91349024f, 0.90295863f, 0.90482706f, 0.9114033f, 0.91775465f, 0.9238795f, 0.9297765f, 0.93544406f, 0.9408808f, 0.94608533f, 0.95105654f, 0.955793f, 0.9602937f, 0.9645574f, 0.96858317f, 0.9723699f, 0.97591674f, 0.97922283f, 0.9822872f, 0.9851093f, 0.98768836f, 0.9900237f, 0.9921147f, 0.993961f, 0.99556196f, 0.9969173f, 0.9980267f, 0.99888986f, 0.99950653f, 0.9998766f, 1.0f};
        }

        public float getInterpolation(float f) {
            int i = 100;
            int i2 = (int) (100.0f * f);
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 <= 100) {
                i = i2;
            }
            return this.a[i];
        }
    }

    public void a(AuthorizeListener authorizeListener) {
        this.b = authorizeListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.c == null) {
            this.c = c();
            if (this.c == null) {
                this.c = new AuthorizeAdapter();
            }
        }
        this.c.setActivity(activity);
    }

    private AuthorizeAdapter c() {
        try {
            ActivityInfo activityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128);
            if (activityInfo.metaData == null || activityInfo.metaData.isEmpty()) {
                return null;
            }
            String string = activityInfo.metaData.getString("AuthorizeAdapter");
            if (string == null || string.length() <= 0) {
                string = activityInfo.metaData.getString("Adapter");
                if (string == null || string.length() <= 0) {
                    return null;
                }
            }
            Object newInstance = Class.forName(string).newInstance();
            if (!(newInstance instanceof AuthorizeAdapter)) {
                return null;
            }
            return (AuthorizeAdapter) newInstance;
        } catch (Throwable th) {
            SSDKLog.b().w(th);
            return null;
        }
    }

    public void onCreate() {
        if (this.d == null) {
            this.d = b();
            this.d.a(this);
            this.d.a(this.c.isNotitle());
            this.c.setBodyView(this.d.d());
            this.c.setWebView(this.d.b());
            TitleLayout c2 = this.d.c();
            this.c.setTitleView(c2);
            String name = this.a.getPlatform().getName();
            this.c.setPlatformName(this.a.getPlatform().getName());
            try {
                c2.getTvTitle().setText(ResHelper.getStringRes(getContext(), "ssdk_" + name.toLowerCase()));
            } catch (Throwable th) {
                SSDKLog.b().w(th);
            }
        }
        this.c.onCreate();
        if (this.c != null && !this.c.isPopUpAnimationDisable()) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(550);
            scaleAnimation.setInterpolator(new a());
            this.d.setAnimation(scaleAnimation);
        }
        disableScreenCapture();
        this.activity.setContentView(this.d);
    }

    /* access modifiers changed from: protected */
    public RegisterView b() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.a().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            SSDKLog.b().w(th);
                            AuthorizeListener authorizeListener = g.this.a.getAuthorizeListener();
                            if (authorizeListener != null) {
                                authorizeListener.onCancel();
                            }
                            g.this.finish();
                        }
                    }
                }.start();
            }
        });
        this.e = registerView.b();
        WebSettings settings = this.e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setSavePassword(false);
        this.e.setVerticalScrollBarEnabled(false);
        this.e.setHorizontalScrollBarEnabled(false);
        c authorizeWebviewClient = this.a.getAuthorizeWebviewClient(this);
        String str = "";
        if (authorizeWebviewClient != null) {
            str = authorizeWebviewClient.getClass().getSimpleName();
        }
        if ((!TextUtils.isEmpty(str) && str.equals("GooglePlusAuthorizeWebviewClient")) || ((!TextUtils.isEmpty(str) && str.contains("GooglePlus")) || str.equals("YoutubeAuthorizeWebviewClient"))) {
            this.e.getSettings().setUserAgentString(((("Mozilla/5.0 (Linux; Android 5.1; m2 note Build/LMY47D) " + "AppleWebKit/537.36 (KHTML, like Gecko) ") + "Version/4.0 ") + "Chrome/40.0.2214.127 ") + "Mobile Safari/537.36");
        }
        this.e.setWebViewClient(authorizeWebviewClient);
        new Thread() {
            public void run() {
                try {
                    Message message = new Message();
                    message.what = 2;
                    if ("none".equals(DeviceHelper.getInstance(g.this.activity).getDetailNetworkTypeForStatic())) {
                        message.arg1 = 1;
                        UIHandler.sendMessage(message, g.this);
                        return;
                    }
                    if (ShareSDK.isRemoveCookieOnAuthorize()) {
                        CookieSyncManager.createInstance(g.this.activity);
                        CookieManager.getInstance().removeAllCookie();
                    }
                    message.obj = g.this.a.getAuthorizeUrl();
                    UIHandler.sendMessage(message, g.this);
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
        return registerView;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 2:
                if (message.arg1 != 1) {
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.e.loadUrl(str);
                        break;
                    } else {
                        finish();
                        AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
                        if (authorizeListener != null) {
                            authorizeListener.onError(new Throwable("Authorize URL is empty (platform: " + this.a.getPlatform().getName() + ")"));
                            break;
                        }
                    }
                } else {
                    AuthorizeListener authorizeListener2 = this.a.getAuthorizeListener();
                    if (authorizeListener2 != null) {
                        authorizeListener2.onError(new Throwable("Network error (platform: " + this.a.getPlatform().getName() + ")"));
                        break;
                    }
                }
                break;
        }
        return false;
    }

    public void OnResize(int i, int i2, int i3, int i4) {
        if (this.c != null) {
            this.c.onResize(i, i2, i3, i4);
        }
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.c != null) {
            z = this.c.onKeyEvent(i, keyEvent);
        }
        if (!z && i == 4 && keyEvent.getAction() == 0) {
            AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
        }
        if (z) {
            return true;
        }
        return super.onKeyEvent(i, keyEvent);
    }

    public void onStart() {
        if (this.c != null) {
            this.c.onStart();
        }
    }

    public void onPause() {
        if (this.c != null) {
            this.c.onPause();
        }
    }

    public void onResume() {
        if (this.c != null) {
            this.c.onResume();
        }
    }

    public void onStop() {
        if (this.c != null) {
            this.c.onStop();
        }
    }

    public void onRestart() {
        if (this.c != null) {
            this.c.onRestart();
        }
    }

    public boolean onFinish() {
        if (this.c != null) {
            return this.c.onFinish();
        }
        if (this.e != null) {
            this.e.destroy();
            this.e.removeAllViews();
        }
        if (this.activity != null) {
            ((ViewGroup) this.activity.getWindow().getDecorView()).removeAllViews();
        }
        return super.onFinish();
    }

    public void onDestroy() {
        if (this.c != null) {
            this.c.onDestroy();
        }
        if (this.e != null) {
            this.e.setFocusable(false);
        }
    }
}
