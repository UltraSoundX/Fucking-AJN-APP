package com.d.a.a;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import com.d.a.a;
import com.d.a.c;
import com.d.a.e;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: PlatformPage */
public abstract class i extends c {
    /* access modifiers changed from: private */
    public a a;
    /* access modifiers changed from: private */
    public Runnable b;
    private Animation c;
    private Animation d;
    private LinearLayout e;
    /* access modifiers changed from: private */
    public boolean f;

    /* access modifiers changed from: protected */
    public abstract j a(ArrayList<Object> arrayList);

    public i(e eVar) {
        super(eVar);
        this.a = (a) ResHelper.forceCast(eVar);
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(1275068416));
        i();
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        this.activity.setContentView(linearLayout);
        TextView textView = new TextView(this.activity);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                i.this.finish();
            }
        });
        linearLayout.addView(textView, layoutParams);
        this.e = new LinearLayout(this.activity);
        this.e.setOrientation(1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        this.e.setAnimation(this.c);
        linearLayout.addView(this.e, layoutParams2);
        MobViewPager mobViewPager = new MobViewPager(this.activity);
        j a2 = a(h());
        this.e.addView(mobViewPager, new LayoutParams(-1, a2.b()));
        f fVar = new f(this.activity);
        this.e.addView(fVar, new LayoutParams(-1, a2.a()));
        fVar.setScreenCount(a2.getCount());
        fVar.a(0, 0);
        a2.a(fVar);
        mobViewPager.setAdapter(a2);
    }

    /* access modifiers changed from: protected */
    public ArrayList<Object> h() {
        ArrayList<Object> arrayList = new ArrayList<>();
        Platform[] platformList = ShareSDK.getPlatformList();
        if (platformList == null) {
            platformList = new Platform[0];
        }
        HashMap d2 = d();
        if (d2 == null) {
            d2 = new HashMap();
        }
        for (Platform platform : platformList) {
            if (!d2.containsKey(platform.getName())) {
                arrayList.add(platform);
            }
        }
        ArrayList c2 = c();
        if (c2 != null && c2.size() > 0) {
            arrayList.addAll(c2);
        }
        return arrayList;
    }

    public final void d(final Platform platform) {
        this.b = new Runnable() {
            public void run() {
                boolean a2 = i.this.b();
                boolean z = platform instanceof CustomPlatform;
                boolean a3 = i.this.c(platform);
                if (a2 || z || a3) {
                    i.this.a(platform);
                    return;
                }
                ShareParams c = i.this.b(platform);
                if (c != null) {
                    ShareSDK.logDemoEvent(3, null);
                    if (i.this.f() != null) {
                        i.this.f().a(platform, c);
                    }
                    i.this.a.a(i.this.activity, platform, c);
                }
            }
        };
        finish();
    }

    public final void a(final View view, final a aVar) {
        this.b = new Runnable() {
            public void run() {
                aVar.c.onClick(view);
            }
        };
        finish();
    }

    private void i() {
        this.c = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.c.setDuration(300);
        this.d = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.d.setDuration(300);
    }

    public boolean onFinish() {
        if (this.f) {
            this.f = false;
            return false;
        }
        this.d.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (i.this.b == null) {
                    ShareSDK.logDemoEvent(2, null);
                } else {
                    i.this.b.run();
                    i.this.b = null;
                }
                i.this.f = true;
                i.this.finish();
            }
        });
        this.e.clearAnimation();
        this.e.setAnimation(this.d);
        this.e.setVisibility(8);
        return true;
    }
}
