package cn.sharesdk.sina.weibo.sdk.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.LoadingBar;
import com.mob.tools.utils.ResHelper;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.lang.reflect.Method;

/* compiled from: WeiboWebPageLayout */
public class a {
    private TextView a;
    private TextView b;
    private WebView c;
    /* access modifiers changed from: private */
    public LoadingBar d;
    private LinearLayout e;
    private TextView f;
    private Button g;
    private Context h;

    public a(Context context) {
        this.h = context;
    }

    public RelativeLayout a(int i) {
        RelativeLayout relativeLayout = new RelativeLayout(this.h);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.h);
        relativeLayout2.setId(ResHelper.getIdRes(this.h, "ssdk_sina_web_title_id"));
        relativeLayout2.setBackgroundColor(-131587);
        LayoutParams layoutParams = new LayoutParams(-1, ResHelper.dipToPx(this.h, 55));
        layoutParams.addRule(10);
        relativeLayout.addView(relativeLayout2, layoutParams);
        this.a = new TextView(this.h);
        this.a.setTextSize(1, 17.0f);
        this.a.setTextColor(cn.sharesdk.sina.weibo.sdk.a.a(-32256, 1728020992));
        int stringRes = ResHelper.getStringRes(this.h, "ssdk_sina_web_close");
        if (stringRes > 0) {
            this.a.setText(stringRes);
        }
        int dipToPx = ResHelper.dipToPx(this.h, 10);
        this.a.setPadding(dipToPx, 0, dipToPx, 0);
        this.a.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view.setBackgroundColor(-1);
                } else if (motionEvent.getAction() == 1) {
                    view.setBackgroundColor(-131587);
                }
                return false;
            }
        });
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        relativeLayout2.addView(this.a, layoutParams2);
        this.b = new TextView(this.h);
        this.b.setTextColor(-11382190);
        this.b.setTextSize(1, 18.0f);
        if (i > 0) {
            this.b.setText(i);
        }
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        relativeLayout2.addView(this.b, layoutParams3);
        this.c = new WebView(this.h);
        LayoutParams layoutParams4 = new LayoutParams(-1, -1);
        layoutParams4.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(this.c, layoutParams4);
        this.d = new LoadingBar(this.h);
        LayoutParams layoutParams5 = new LayoutParams(-1, ResHelper.dipToPx(this.h, 3));
        layoutParams5.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(this.d, layoutParams5);
        View view = new View(this.h);
        int bitmapRes = ResHelper.getBitmapRes(this.h, "ssdk_weibo_common_shadow_top");
        if (bitmapRes > 0) {
            view.setBackgroundResource(bitmapRes);
        }
        relativeLayout.addView(view, layoutParams5);
        this.e = new LinearLayout(this.h);
        this.e.setVisibility(8);
        this.e.setGravity(17);
        this.e.setOrientation(1);
        LayoutParams layoutParams6 = new LayoutParams(-2, -2);
        layoutParams6.addRule(13);
        relativeLayout.addView(this.e, layoutParams6);
        ImageView imageView = new ImageView(this.h);
        int bitmapRes2 = ResHelper.getBitmapRes(this.h, "ssdk_weibo_empty_failed");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams7.bottomMargin = ResHelper.dipToPx(this.h, 8);
        this.e.addView(imageView, layoutParams7);
        this.f = new TextView(this.h);
        int stringRes2 = ResHelper.getStringRes(this.h, "ssdk_sina_web_net_error");
        if (stringRes2 > 0) {
            this.f.setText(stringRes2);
        }
        this.f.setTextColor(-4342339);
        this.f.setTextSize(1, 14.0f);
        this.e.addView(this.f, new LinearLayout.LayoutParams(-2, -2));
        this.g = new Button(this.h);
        this.g.setTextColor(-8882056);
        this.g.setGravity(17);
        this.g.setTextSize(1, 16.0f);
        int stringRes3 = ResHelper.getStringRes(this.h, "ssdk_sina_web_refresh");
        if (stringRes3 > 0) {
            this.g.setText(stringRes3);
        }
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(ResHelper.dipToPx(this.h, ErrorCode.NEEDDOWNLOAD_3), ResHelper.dipToPx(this.h, 46));
        layoutParams8.topMargin = dipToPx;
        this.e.addView(this.g, layoutParams8);
        e();
        return relativeLayout;
    }

    private void e() {
        StringBuilder sb = new StringBuilder();
        sb.append(Build.MANUFACTURER).append("-").append(Build.MODEL);
        sb.append("_");
        sb.append(VERSION.RELEASE);
        sb.append("_");
        sb.append("weibosdk");
        sb.append("_");
        sb.append("0031405000");
        sb.append("_android");
        this.c.getSettings().setJavaScriptEnabled(true);
        this.c.getSettings().setSavePassword(false);
        this.c.getSettings().setUserAgentString(sb.toString());
        this.c.requestFocus();
        this.c.setScrollBarStyle(0);
        if (VERSION.SDK_INT > 10 && VERSION.SDK_INT < 17) {
            try {
                Method method = this.c.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.c, new Object[]{"searchBoxJavaBridge_"});
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
        }
        this.c.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                a.this.d.a(i);
                if (i == 100) {
                    a.this.d.setVisibility(4);
                } else {
                    a.this.d.setVisibility(0);
                }
            }
        });
    }

    public Button a() {
        return this.g;
    }

    public TextView b() {
        return this.a;
    }

    public WebView c() {
        return this.c;
    }

    public LinearLayout d() {
        return this.e;
    }
}
