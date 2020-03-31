package com.zhouwei.mzbanner;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Scroller;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.TbsMediaPlayer.TbsMediaPlayerListener;
import com.zhouwei.mzbanner.transformer.CoverModeTransformer;
import com.zhouwei.mzbanner.transformer.ScaleYTransformer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MZBannerView<T> extends RelativeLayout {
    /* access modifiers changed from: private */
    public CustomViewPager a;
    /* access modifiers changed from: private */
    public MZPagerAdapter b;
    /* access modifiers changed from: private */
    public List<T> c;
    /* access modifiers changed from: private */
    public boolean d = true;
    /* access modifiers changed from: private */
    public int e = 0;
    /* access modifiers changed from: private */
    public Handler f = new Handler();
    /* access modifiers changed from: private */
    public int g = 3000;
    private c h;
    private boolean i = true;
    private boolean j = true;
    private LinearLayout k;
    /* access modifiers changed from: private */
    public ArrayList<ImageView> l = new ArrayList<>();
    /* access modifiers changed from: private */
    public int[] m = {R.drawable.indicator_normal, R.drawable.indicator_selected};
    private int n = 0;
    private int o = 0;
    private int p = 0;

    /* renamed from: q reason: collision with root package name */
    private int f439q = 0;
    private int r = 0;
    private int s = 1;
    /* access modifiers changed from: private */
    public OnPageChangeListener t;
    private a u;
    private boolean v = true;
    private final Runnable w = new Runnable() {
        public void run() {
            if (MZBannerView.this.d) {
                MZBannerView.this.e = MZBannerView.this.a.getCurrentItem();
                MZBannerView.this.e = MZBannerView.this.e + 1;
                if (MZBannerView.this.e == MZBannerView.this.b.getCount() - 1) {
                    MZBannerView.this.e = 0;
                    MZBannerView.this.a.setCurrentItem(MZBannerView.this.e, false);
                    MZBannerView.this.f.postDelayed(this, (long) MZBannerView.this.g);
                    return;
                }
                MZBannerView.this.a.setCurrentItem(MZBannerView.this.e);
                MZBannerView.this.f.postDelayed(this, (long) MZBannerView.this.g);
                return;
            }
            MZBannerView.this.f.postDelayed(this, (long) MZBannerView.this.g);
        }
    };

    public static class MZPagerAdapter<T> extends PagerAdapter {
        private List<T> a;
        private com.zhouwei.mzbanner.a.a b;
        private ViewPager c;
        private boolean d;
        /* access modifiers changed from: private */
        public a e;
        private final int f = ErrorCode.INFO_CODE_MINIQB;

        public MZPagerAdapter(List<T> list, com.zhouwei.mzbanner.a.a aVar, boolean z) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            for (T add : list) {
                this.a.add(add);
            }
            this.b = aVar;
            this.d = z;
        }

        public void a(a aVar) {
            this.e = aVar;
        }

        public void a(ViewPager viewPager) {
            this.c = viewPager;
            this.c.setAdapter(this);
            this.c.getAdapter().notifyDataSetChanged();
            this.c.setCurrentItem(this.d ? a() : 0);
        }

        private int a() {
            if (b() == 0) {
                return 0;
            }
            int b2 = (b() * ErrorCode.INFO_CODE_MINIQB) / 2;
            if (b2 % b() == 0) {
                return b2;
            }
            while (b2 % b() != 0) {
                b2++;
            }
            return b2;
        }

        public int getCount() {
            return this.d ? b() * ErrorCode.INFO_CODE_MINIQB : b();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View a2 = a(i, viewGroup);
            viewGroup.addView(a2);
            return a2;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public void finishUpdate(ViewGroup viewGroup) {
            if (this.d && this.c.getCurrentItem() == getCount() - 1) {
                a(0);
            }
        }

        private void a(int i) {
            try {
                this.c.setCurrentItem(i, false);
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
        }

        private int b() {
            if (this.a == null) {
                return 0;
            }
            return this.a.size();
        }

        private View a(int i, ViewGroup viewGroup) {
            final int b2 = i % b();
            com.zhouwei.mzbanner.a.b b3 = this.b.b();
            if (b3 == null) {
                throw new RuntimeException("can not return a null holder");
            }
            View a2 = b3.a(viewGroup.getContext());
            if (this.a != null && this.a.size() > 0) {
                b3.a(viewGroup.getContext(), b2, this.a.get(b2));
            }
            a2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (MZPagerAdapter.this.e != null) {
                        MZPagerAdapter.this.e.a(view, b2);
                    }
                }
            });
            return a2;
        }
    }

    public interface a {
        void a(View view, int i);
    }

    public enum b {
        LEFT,
        CENTER,
        RIGHT
    }

    public static class c extends Scroller {
        private int a = TbsMediaPlayerListener.MEDIA_INFO_BAD_INTERLEAVING;
        private boolean b = false;

        public c(Context context) {
            super(context);
        }

        public void startScroll(int i, int i2, int i3, int i4) {
            super.startScroll(i, i2, i3, i4, this.a);
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.b ? i5 : this.a);
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void a(int i) {
            this.a = i;
        }

        public int a() {
            return this.a;
        }
    }

    public MZBannerView(Context context) {
        super(context);
        c();
    }

    public MZBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        c();
    }

    public MZBannerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
        c();
    }

    public MZBannerView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context, attributeSet);
        c();
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MZBannerView);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.MZBannerView_open_mz_mode, true);
        this.v = obtainStyledAttributes.getBoolean(R.styleable.MZBannerView_middle_page_cover, true);
        this.j = obtainStyledAttributes.getBoolean(R.styleable.MZBannerView_canLoop, true);
        this.s = obtainStyledAttributes.getInt(R.styleable.MZBannerView_indicatorAlign, b.CENTER.ordinal());
        this.n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingLeft, 0);
        this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingRight, 0);
        this.p = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingTop, 0);
        this.f439q = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MZBannerView_indicatorPaddingBottom, 0);
        obtainStyledAttributes.recycle();
    }

    private void c() {
        View inflate;
        if (this.i) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.mz_banner_effect_layout, this, true);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.mz_banner_normal_layout, this, true);
        }
        this.k = (LinearLayout) inflate.findViewById(R.id.banner_indicator_container);
        this.a = (CustomViewPager) inflate.findViewById(R.id.mzbanner_vp);
        this.a.setOffscreenPageLimit(4);
        this.r = a(30);
        f();
        e();
    }

    private void d() {
        if (!this.i) {
            return;
        }
        if (this.v) {
            this.a.setPageTransformer(true, new CoverModeTransformer(this.a));
        } else {
            this.a.setPageTransformer(false, new ScaleYTransformer());
        }
    }

    private void e() {
        if (this.s == b.LEFT.ordinal()) {
            setIndicatorAlign(b.LEFT);
        } else if (this.s == b.CENTER.ordinal()) {
            setIndicatorAlign(b.CENTER);
        } else {
            setIndicatorAlign(b.RIGHT);
        }
    }

    private void f() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.h = new c(this.a.getContext());
            declaredField.set(this.a, this.h);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
    }

    private void g() {
        this.k.removeAllViews();
        this.l.clear();
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            ImageView imageView = new ImageView(getContext());
            if (this.s == b.LEFT.ordinal()) {
                if (i2 == 0) {
                    imageView.setPadding((this.i ? this.n + this.r : this.n) + 6, 0, 6, 0);
                } else {
                    imageView.setPadding(6, 0, 6, 0);
                }
            } else if (this.s != b.RIGHT.ordinal()) {
                imageView.setPadding(6, 0, 6, 0);
            } else if (i2 == this.c.size() - 1) {
                imageView.setPadding(6, 0, (this.i ? this.r + this.o : this.o) + 6, 0);
            } else {
                imageView.setPadding(6, 0, 6, 0);
            }
            if (i2 == this.e % this.c.size()) {
                imageView.setImageResource(this.m[1]);
            } else {
                imageView.setImageResource(this.m[0]);
            }
            this.l.add(imageView);
            this.k.addView(imageView);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.j) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
            case 3:
            case 4:
                int left = this.a.getLeft();
                float rawX = motionEvent.getRawX();
                if (rawX >= ((float) left) && rawX < ((float) (a(getContext()) - left))) {
                    b();
                    break;
                }
            case 1:
                a();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public static int a(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public void a() {
        if (this.b != null && this.j) {
            b();
            this.d = true;
            this.f.postDelayed(this.w, (long) this.g);
        }
    }

    public void b() {
        this.d = false;
        this.f.removeCallbacks(this.w);
    }

    public void setCanLoop(boolean z) {
        this.j = z;
        if (!z) {
            b();
        }
    }

    public void setDelayedTime(int i2) {
        this.g = i2;
    }

    public void setBannerPageClickListener(a aVar) {
        this.u = aVar;
    }

    public void setIndicatorVisible(boolean z) {
        if (z) {
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
    }

    public ViewPager getViewPager() {
        return this.a;
    }

    public void a(List<T> list, com.zhouwei.mzbanner.a.a aVar) {
        if (list != null && aVar != null) {
            this.c = list;
            b();
            if (list.size() < 3) {
                this.i = false;
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.a.getLayoutParams();
                marginLayoutParams.setMargins(0, 0, 0, 0);
                this.a.setLayoutParams(marginLayoutParams);
                setClipChildren(true);
                this.a.setClipChildren(true);
            }
            d();
            g();
            this.b = new MZPagerAdapter(list, aVar, this.j);
            this.b.a((ViewPager) this.a);
            this.b.a(this.u);
            this.a.clearOnPageChangeListeners();
            this.a.addOnPageChangeListener(new OnPageChangeListener() {
                public void onPageScrolled(int i, float f, int i2) {
                    int size = i % MZBannerView.this.l.size();
                    if (MZBannerView.this.t != null) {
                        MZBannerView.this.t.onPageScrolled(size, f, i2);
                    }
                }

                public void onPageSelected(int i) {
                    MZBannerView.this.e = i;
                    int d = MZBannerView.this.e % MZBannerView.this.l.size();
                    for (int i2 = 0; i2 < MZBannerView.this.c.size(); i2++) {
                        if (i2 == d) {
                            ((ImageView) MZBannerView.this.l.get(i2)).setImageResource(MZBannerView.this.m[1]);
                        } else {
                            ((ImageView) MZBannerView.this.l.get(i2)).setImageResource(MZBannerView.this.m[0]);
                        }
                    }
                    if (MZBannerView.this.t != null) {
                        MZBannerView.this.t.onPageSelected(d);
                    }
                }

                public void onPageScrollStateChanged(int i) {
                    switch (i) {
                        case 1:
                            MZBannerView.this.d = false;
                            break;
                        case 2:
                            MZBannerView.this.d = true;
                            break;
                    }
                    if (MZBannerView.this.t != null) {
                        MZBannerView.this.t.onPageScrollStateChanged(i);
                    }
                }
            });
        }
    }

    public void setIndicatorAlign(b bVar) {
        this.s = bVar.ordinal();
        LayoutParams layoutParams = (LayoutParams) this.k.getLayoutParams();
        if (bVar == b.LEFT) {
            layoutParams.addRule(9);
        } else if (bVar == b.RIGHT) {
            layoutParams.addRule(11);
        } else {
            layoutParams.addRule(14);
        }
        layoutParams.setMargins(0, this.p, 0, this.f439q);
        this.k.setLayoutParams(layoutParams);
    }

    public LinearLayout getIndicatorContainer() {
        return this.k;
    }

    public void setDuration(int i2) {
        this.h.a(i2);
    }

    public void setUseDefaultDuration(boolean z) {
        this.h.a(z);
    }

    public int getDuration() {
        return this.h.a();
    }

    public static int a(int i2) {
        return (int) TypedValue.applyDimension(1, (float) i2, Resources.getSystem().getDisplayMetrics());
    }
}
