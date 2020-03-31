package com.e23.ajn.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.e23.ajn.base.BaseSupportActivity;
import com.e23.ajn.d.p;
import com.e23.ajn.model.AdImageInfo;
import com.stub.StubApp;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import java.lang.ref.WeakReference;
import okhttp3.Call;

public class SplashActivity extends BaseSupportActivity {
    /* access modifiers changed from: private */
    public static boolean g = false;
    public long TOUCH_TIME = 0;
    /* access modifiers changed from: private */
    public ImageView b;
    /* access modifiers changed from: private */
    public ImageView c;
    private ImageView d;
    private Thread e;
    /* access modifiers changed from: private */
    public final a f = new a(this);
    /* access modifiers changed from: private */
    public boolean h = false;
    private CountDownTimer i = new CountDownTimer(4000, 1000) {
        public void onTick(long j) {
            if (j / 1000 == 2) {
                SplashActivity.this.c.setImageDrawable(ResourcesCompat.getDrawable(SplashActivity.this.getResources(), R.mipmap.clock2, null));
            }
            if (j / 1000 == 1) {
                SplashActivity.this.c.setImageDrawable(ResourcesCompat.getDrawable(SplashActivity.this.getResources(), R.mipmap.clock3, null));
            }
        }

        public void onFinish() {
            if (!SplashActivity.this.h) {
                SplashActivity.this.d();
            }
        }
    };

    private static class a extends Handler {
        private final WeakReference<SplashActivity> a;

        public a(SplashActivity splashActivity) {
            this.a = new WeakReference<>(splashActivity);
        }

        public void handleMessage(Message message) {
            Activity activity = (Activity) this.a.get();
            if (activity != null && message.what == 3000 && !SplashActivity.g) {
                Intent intent = new Intent();
                intent.setClass(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static {
        StubApp.interface11(3500);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: private */
    public void a(AdImageInfo adImageInfo) {
        this.b.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashActivity.this.h = true;
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        });
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        this.f.removeCallbacksAndMessages(null);
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
        super.onDestroy();
    }

    private void b() {
        this.e = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = new Message();
                    message.what = 3000;
                    SplashActivity.this.f.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.e.start();
    }

    private void c() {
        ((GetBuilder) ((GetBuilder) OkHttpUtils.get().url("http://appc.e23.cn/ajnapi/adv/app_adv_android.html")).tag(this)).build().execute(new com.e23.ajn.c.a<AdImageInfo>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(AdImageInfo adImageInfo, int i) {
                if (adImageInfo != null && !TextUtils.isEmpty(adImageInfo.getImgurl())) {
                    SplashActivity.g = true;
                    String imgurl = adImageInfo.getImgurl();
                    if (imgurl.contains(".gif")) {
                        i.a((FragmentActivity) SplashActivity.this).a(imgurl).i().b(b.SOURCE).a(SplashActivity.this.b);
                    } else {
                        i.a((FragmentActivity) SplashActivity.this).a(imgurl).a(SplashActivity.this.b);
                    }
                    if (adImageInfo.getIsRed() == 1) {
                        p.b("is_red", true);
                    } else {
                        p.b("is_red", false);
                    }
                    com.jaeger.library.a.a((Activity) SplashActivity.this, (View) null);
                    if (!TextUtils.isEmpty(adImageInfo.getLinkurl())) {
                        SplashActivity.this.a(adImageInfo);
                    }
                    SplashActivity.this.e();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c.setVisibility(0);
        this.i.start();
    }

    public void onBackPressedSupport() {
        if (System.currentTimeMillis() - this.TOUCH_TIME < 2000) {
            ActivityCompat.finishAfterTransition(this);
            return;
        }
        this.TOUCH_TIME = System.currentTimeMillis();
        Toast.makeText(this, R.string.press_again_exit, 0).show();
    }
}
