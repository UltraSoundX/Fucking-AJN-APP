package cn.qqtheme.framework.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WheelView extends View {
    /* access modifiers changed from: private */
    public float A = 0.0f;
    /* access modifiers changed from: private */
    public int B = -1;
    /* access modifiers changed from: private */
    public int C;
    private int D;
    private int E = 7;
    private int F;
    private int G;
    private int H;
    private int I = 0;
    private float J = 0.0f;
    private long K = 0;
    private int L;
    private int M = 17;
    private int N = 0;
    private int O = 0;
    private float P;
    private boolean Q = false;
    private boolean R = true;
    /* access modifiers changed from: private */
    public c a;
    private GestureDetector b;
    /* access modifiers changed from: private */
    public d c;
    /* access modifiers changed from: private */
    public e d;
    private boolean e = true;
    private ScheduledFuture<?> f;
    private Paint g;
    private Paint h;
    private Paint i;
    private Paint j;
    /* access modifiers changed from: private */
    public List<cn.qqtheme.framework.a.a> k = new ArrayList();
    private String l;
    private int m;
    private int n;
    private int o = 0;
    private int p = 16;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public float f363q;
    private Typeface r = Typeface.DEFAULT;
    private int s = -4473925;
    private int t = -16611122;
    private a u = new a();
    private float v = 2.0f;
    private int w = -1;
    /* access modifiers changed from: private */
    public boolean x = true;
    private float y;
    private float z;

    public static class a {
        protected boolean a = true;
        protected boolean b = false;
        protected int c = -8139290;
        protected int d = -4473925;
        protected int e = 100;
        protected int f = ErrorCode.COPY_INSTALL_SUCCESS;
        protected float g = 0.1f;
        protected float h = 2.0f;

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public a b(boolean z) {
            this.b = z;
            if (z && this.c == -8139290) {
                this.c = this.d;
                this.f = 255;
            }
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public String toString() {
            return "visible=" + this.a + ",color=" + this.c + ",alpha=" + this.f + ",thick=" + this.h;
        }
    }

    private static class b extends TimerTask {
        float a = 2.14748365E9f;
        final float b;
        final WheelView c;

        b(WheelView wheelView, float f) {
            this.c = wheelView;
            this.b = f;
        }

        public final void run() {
            if (this.a == 2.14748365E9f) {
                if (Math.abs(this.b) <= 2000.0f) {
                    this.a = this.b;
                } else if (this.b > 0.0f) {
                    this.a = 2000.0f;
                } else {
                    this.a = -2000.0f;
                }
            }
            if (Math.abs(this.a) < 0.0f || Math.abs(this.a) > 20.0f) {
                int i = (int) ((this.a * 10.0f) / 1000.0f);
                this.c.A = this.c.A - ((float) i);
                if (!this.c.x) {
                    float j = this.c.f363q;
                    float f = ((float) (-this.c.B)) * j;
                    float itemCount = ((float) ((this.c.getItemCount() - 1) - this.c.B)) * j;
                    if (((double) this.c.A) - (((double) j) * 0.25d) < ((double) f)) {
                        f = this.c.A + ((float) i);
                    } else if (((double) this.c.A) + (((double) j) * 0.25d) > ((double) itemCount)) {
                        itemCount = this.c.A + ((float) i);
                    }
                    if (this.c.A <= f) {
                        this.a = 40.0f;
                        this.c.A = (float) ((int) f);
                    } else if (this.c.A >= itemCount) {
                        this.c.A = (float) ((int) itemCount);
                        this.a = -40.0f;
                    }
                }
                if (this.a < 0.0f) {
                    this.a += 20.0f;
                } else {
                    this.a -= 20.0f;
                }
                this.c.a.sendEmptyMessage(1000);
                return;
            }
            this.c.f();
            this.c.a.sendEmptyMessage(2000);
        }
    }

    private static class c extends Handler {
        final WheelView a;

        c(WheelView wheelView) {
            this.a = wheelView;
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    this.a.invalidate();
                    return;
                case 2000:
                    this.a.a(2);
                    return;
                case 3000:
                    this.a.g();
                    return;
                default:
                    return;
            }
        }
    }

    public interface d {
        void a(int i);
    }

    @Deprecated
    public interface e {
        void a(boolean z, int i, String str);
    }

    private static class f extends TimerTask {
        int a = Integer.MAX_VALUE;
        int b = 0;
        int c;
        final WheelView d;

        f(WheelView wheelView, int i) {
            this.d = wheelView;
            this.c = i;
        }

        public void run() {
            if (this.a == Integer.MAX_VALUE) {
                this.a = this.c;
            }
            this.b = (int) (((float) this.a) * 0.1f);
            if (this.b == 0) {
                if (this.a < 0) {
                    this.b = -1;
                } else {
                    this.b = 1;
                }
            }
            if (Math.abs(this.a) <= 1) {
                this.d.f();
                this.d.a.sendEmptyMessage(3000);
                return;
            }
            this.d.A = this.d.A + ((float) this.b);
            if (!this.d.x) {
                float j = this.d.f363q;
                float f = ((float) (-this.d.B)) * j;
                float itemCount = j * ((float) ((this.d.getItemCount() - 1) - this.d.B));
                if (this.d.A <= f || this.d.A >= itemCount) {
                    this.d.A = this.d.A - ((float) this.b);
                    this.d.f();
                    this.d.a.sendEmptyMessage(3000);
                    return;
                }
            }
            this.d.a.sendEmptyMessage(1000);
            this.a -= this.b;
        }
    }

    private static class g implements cn.qqtheme.framework.a.a {
        private String a;

        private g(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        float f2 = getResources().getDisplayMetrics().density;
        if (f2 < 1.0f) {
            this.P = 2.4f;
        } else if (1.0f <= f2 && f2 < 2.0f) {
            this.P = 3.6f;
        } else if (1.0f <= f2 && f2 < 2.0f) {
            this.P = 4.5f;
        } else if (2.0f <= f2 && f2 < 3.0f) {
            this.P = 6.0f;
        } else if (f2 >= 3.0f) {
            this.P = f2 * 2.5f;
        }
        a();
        a(context);
    }

    public final void setVisibleItemCount(int i2) {
        if (i2 % 2 == 0) {
            throw new IllegalArgumentException("must be odd");
        } else if (i2 != this.E) {
            this.E = i2;
        }
    }

    public final void setCycleDisable(boolean z2) {
        this.x = !z2;
    }

    public final void setOffset(int i2) {
        int i3;
        if (i2 < 1 || i2 > 5) {
            throw new IllegalArgumentException("must between 1 and 5");
        }
        int i4 = (i2 * 2) + 1;
        if (i2 % 2 == 0) {
            i3 = i4 + i2;
        } else {
            i3 = i4 + (i2 - 1);
        }
        setVisibleItemCount(i3);
    }

    public final int getSelectedIndex() {
        return this.C;
    }

    public final void setSelectedIndex(int i2) {
        if (this.k != null && !this.k.isEmpty()) {
            int size = this.k.size();
            if (i2 == 0 || (i2 > 0 && i2 < size && i2 != this.C)) {
                this.B = i2;
                this.A = 0.0f;
                this.I = 0;
                invalidate();
            }
        }
    }

    public final void setOnItemSelectListener(d dVar) {
        this.c = dVar;
    }

    @Deprecated
    public final void setOnWheelListener(e eVar) {
        this.d = eVar;
    }

    public final void setItems(List<?> list) {
        this.k.clear();
        for (Object next : list) {
            if (next instanceof cn.qqtheme.framework.a.a) {
                this.k.add((cn.qqtheme.framework.a.a) next);
            } else if ((next instanceof CharSequence) || (next instanceof Number)) {
                this.k.add(new g(next.toString()));
            } else {
                throw new IllegalArgumentException("please implements " + cn.qqtheme.framework.a.a.class.getName());
            }
        }
        d();
        invalidate();
    }

    public final void setItems(String[] strArr) {
        setItems(Arrays.asList(strArr));
    }

    public final void a(String str, boolean z2) {
        this.l = str;
        this.e = z2;
    }

    public final void setLabel(String str) {
        a(str, true);
    }

    public final void setGravity(int i2) {
        this.M = i2;
    }

    public void setTextColor(int i2) {
        this.s = i2;
        this.t = i2;
        this.g.setColor(i2);
        this.h.setColor(i2);
    }

    public final void setTypeface(Typeface typeface) {
        this.r = typeface;
        this.g.setTypeface(this.r);
        this.h.setTypeface(this.r);
    }

    public final void setTextSize(float f2) {
        if (f2 > 0.0f) {
            this.p = (int) (getContext().getResources().getDisplayMetrics().density * f2);
            this.g.setTextSize((float) this.p);
            this.h.setTextSize((float) this.p);
        }
    }

    public void setTextSkewXOffset(int i2) {
        this.o = i2;
        if (i2 != 0) {
            this.h.setTextScaleX(1.0f);
        }
    }

    public void setDividerColor(int i2) {
        this.u.a(i2);
        this.i.setColor(i2);
    }

    @Deprecated
    public void setLineConfig(a aVar) {
        setDividerConfig(aVar);
    }

    public void setDividerConfig(a aVar) {
        if (aVar == null) {
            this.u.a(false);
            this.u.b(false);
            return;
        }
        this.u = aVar;
        this.i.setColor(aVar.c);
        this.i.setStrokeWidth(aVar.h);
        this.i.setAlpha(aVar.f);
        this.j.setColor(aVar.d);
        this.j.setAlpha(aVar.e);
    }

    public final void setLineSpaceMultiplier(float f2) {
        this.v = f2;
        a();
    }

    @Deprecated
    public void setPadding(int i2) {
        setTextPadding(i2);
    }

    public void setTextPadding(int i2) {
        this.w = cn.qqtheme.framework.b.a.a(getContext(), (float) i2);
    }

    public void setUseWeight(boolean z2) {
        this.Q = z2;
    }

    public void setTextSizeAutoFit(boolean z2) {
        this.R = z2;
    }

    private void a() {
        if (this.v < 1.5f) {
            this.v = 1.5f;
        } else if (this.v > 4.0f) {
            this.v = 4.0f;
        }
    }

    private void a(Context context) {
        this.a = new c(this);
        this.b = new GestureDetector(context, new SimpleOnGestureListener() {
            public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                WheelView.this.a(f2);
                return true;
            }
        });
        this.b.setIsLongpressEnabled(false);
        b();
        c();
    }

    private void b() {
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.g.setColor(this.s);
        this.g.setTypeface(this.r);
        this.g.setTextSize((float) this.p);
        this.h = new Paint();
        this.h.setAntiAlias(true);
        this.h.setColor(this.t);
        this.h.setTextScaleX(1.0f);
        this.h.setTypeface(this.r);
        this.h.setTextSize((float) this.p);
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setColor(this.u.c);
        this.i.setStrokeWidth(this.u.h);
        this.i.setAlpha(this.u.f);
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(this.u.d);
        this.j.setAlpha(this.u.e);
        setLayerType(1, null);
    }

    private void c() {
        if (isInEditMode()) {
            setItems(new String[]{"李玉江", "男", "贵州", "穿青人"});
        }
    }

    private void d() {
        if (this.k != null) {
            e();
            int i2 = (int) (this.f363q * ((float) (this.E - 1)));
            this.F = (int) (((double) (i2 * 2)) / 3.141592653589793d);
            this.H = (int) (((double) i2) / 3.141592653589793d);
            LayoutParams layoutParams = getLayoutParams();
            if (this.Q) {
                this.G = MeasureSpec.getSize(this.L);
            } else if (layoutParams == null || layoutParams.width <= 0) {
                this.G = this.m;
                if (this.w < 0) {
                    this.w = cn.qqtheme.framework.b.a.a(getContext(), 13.0f);
                }
                this.G += this.w * 2;
                if (!TextUtils.isEmpty(this.l)) {
                    this.G += a(this.h, this.l);
                }
            } else {
                this.G = layoutParams.width;
            }
            cn.qqtheme.framework.b.b.b("measuredWidth=" + this.G + ",measuredHeight=" + this.F);
            this.y = (((float) this.F) - this.f363q) / 2.0f;
            this.z = (((float) this.F) + this.f363q) / 2.0f;
            if (this.B == -1) {
                if (this.x) {
                    this.B = (this.k.size() + 1) / 2;
                } else {
                    this.B = 0;
                }
            }
            this.D = this.B;
        }
    }

    private void e() {
        Rect rect = new Rect();
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            String a2 = a(this.k.get(i2));
            this.h.getTextBounds(a2, 0, a2.length(), rect);
            int width = rect.width();
            if (width > this.m) {
                this.m = width;
            }
            this.h.getTextBounds("测试", 0, 2, rect);
            this.n = rect.height() + 2;
        }
        this.f363q = this.v * ((float) this.n);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        f();
        if (i2 == 2 || i2 == 3) {
            this.I = (int) (((this.A % this.f363q) + this.f363q) % this.f363q);
            if (((float) this.I) > this.f363q / 2.0f) {
                this.I = (int) (this.f363q - ((float) this.I));
            } else {
                this.I = -this.I;
            }
        }
        this.f = Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new f(this, this.I), 0, 10, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        f();
        this.f = Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new b(this, f2), 0, 5, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.f != null && !this.f.isCancelled()) {
            this.f.cancel(true);
            this.f = null;
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.c != null || this.d != null) {
            postDelayed(new Runnable() {
                public void run() {
                    if (WheelView.this.c != null) {
                        WheelView.this.c.a(WheelView.this.C);
                    }
                    if (WheelView.this.d != null) {
                        WheelView.this.d.a(true, WheelView.this.C, ((cn.qqtheme.framework.a.a) WheelView.this.k.get(WheelView.this.C)).a());
                    }
                }
            }, 200);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        String str;
        if (this.k != null && this.k.size() != 0) {
            String[] strArr = new String[this.E];
            this.D = (((int) (this.A / this.f363q)) % this.k.size()) + this.B;
            if (!this.x) {
                if (this.D < 0) {
                    this.D = 0;
                }
                if (this.D > this.k.size() - 1) {
                    this.D = this.k.size() - 1;
                }
            } else {
                if (this.D < 0) {
                    this.D = this.k.size() + this.D;
                }
                if (this.D > this.k.size() - 1) {
                    this.D -= this.k.size();
                }
            }
            float f2 = this.A % this.f363q;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= this.E) {
                    break;
                }
                int i4 = this.D - ((this.E / 2) - i3);
                if (this.x) {
                    strArr[i3] = ((cn.qqtheme.framework.a.a) this.k.get(b(i4))).a();
                } else if (i4 < 0) {
                    strArr[i3] = "";
                } else if (i4 > this.k.size() - 1) {
                    strArr[i3] = "";
                } else {
                    strArr[i3] = ((cn.qqtheme.framework.a.a) this.k.get(i4)).a();
                }
                i2 = i3 + 1;
            }
            if (this.u.a) {
                float f3 = this.u.g;
                canvas.drawLine(((float) this.G) * f3, this.y, (1.0f - f3) * ((float) this.G), this.y, this.i);
                canvas.drawLine(((float) this.G) * f3, this.z, (1.0f - f3) * ((float) this.G), this.z, this.i);
            }
            if (this.u.b) {
                this.j.setColor(this.u.d);
                this.j.setAlpha(this.u.e);
                canvas.drawRect(0.0f, this.y, (float) this.G, this.z, this.j);
            }
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 < this.E) {
                    canvas.save();
                    double d2 = (double) (((this.f363q * ((float) i6)) - f2) / ((float) this.H));
                    float f4 = (float) (90.0d - ((d2 / 3.141592653589793d) * 180.0d));
                    if (f4 >= 90.0f || f4 <= -90.0f) {
                        canvas.restore();
                    } else {
                        String a2 = a((Object) strArr[i6]);
                        if (this.e || TextUtils.isEmpty(this.l) || TextUtils.isEmpty(a2)) {
                            str = a2;
                        } else {
                            str = a2 + this.l;
                        }
                        if (this.R) {
                            a(str);
                            this.M = 17;
                        } else {
                            this.M = GravityCompat.START;
                        }
                        b(str);
                        c(str);
                        float cos = (float) ((((double) this.H) - (Math.cos(d2) * ((double) this.H))) - ((Math.sin(d2) * ((double) this.n)) / 2.0d));
                        canvas.translate(0.0f, cos);
                        if (cos <= this.y && ((float) this.n) + cos >= this.y) {
                            canvas.save();
                            canvas.clipRect(0.0f, 0.0f, (float) this.G, this.y - cos);
                            canvas.scale(1.0f, ((float) Math.sin(d2)) * 0.8f);
                            canvas.drawText(str, (float) this.O, (float) this.n, this.g);
                            canvas.restore();
                            canvas.save();
                            canvas.clipRect(0.0f, this.y - cos, (float) this.G, (float) ((int) this.f363q));
                            canvas.scale(1.0f, ((float) Math.sin(d2)) * 1.0f);
                            canvas.drawText(str, (float) this.N, ((float) this.n) - this.P, this.h);
                            canvas.restore();
                        } else if (cos <= this.z && ((float) this.n) + cos >= this.z) {
                            canvas.save();
                            canvas.clipRect(0.0f, 0.0f, (float) this.G, this.z - cos);
                            canvas.scale(1.0f, ((float) Math.sin(d2)) * 1.0f);
                            canvas.drawText(str, (float) this.N, ((float) this.n) - this.P, this.h);
                            canvas.restore();
                            canvas.save();
                            canvas.clipRect(0.0f, this.z - cos, (float) this.G, (float) ((int) this.f363q));
                            canvas.scale(1.0f, ((float) Math.sin(d2)) * 0.8f);
                            canvas.drawText(str, (float) this.O, (float) this.n, this.g);
                            canvas.restore();
                        } else if (cos < this.y || cos + ((float) this.n) > this.z) {
                            canvas.save();
                            canvas.clipRect(0.0f, 0.0f, (float) this.G, this.f363q);
                            canvas.scale(1.0f, ((float) Math.sin(d2)) * 0.8f);
                            float pow = (float) Math.pow((double) (Math.abs(f4) / 90.0f), 2.2d);
                            if (this.o != 0) {
                                this.g.setTextSkewX(((float) ((f4 > 0.0f ? -1 : 1) * (this.o > 0 ? 1 : -1))) * 0.5f * pow);
                                this.g.setAlpha((int) ((1.0f - pow) * 255.0f));
                            }
                            canvas.drawText(str, ((float) this.O) + (((float) this.o) * pow), (float) this.n, this.g);
                            canvas.restore();
                        } else {
                            canvas.clipRect(0, 0, this.G, this.n);
                            float f5 = ((float) this.n) - this.P;
                            int i7 = 0;
                            Iterator it = this.k.iterator();
                            while (true) {
                                int i8 = i7;
                                if (!it.hasNext()) {
                                    break;
                                } else if (((cn.qqtheme.framework.a.a) it.next()).a().equals(a2)) {
                                    this.C = i8;
                                    break;
                                } else {
                                    i7 = i8 + 1;
                                }
                            }
                            if (this.e && !TextUtils.isEmpty(this.l)) {
                                str = str + this.l;
                            }
                            canvas.drawText(str, (float) this.N, f5, this.h);
                        }
                        canvas.restore();
                        this.h.setTextSize((float) this.p);
                    }
                    i5 = i6 + 1;
                } else {
                    return;
                }
            }
        }
    }

    private void a(String str) {
        Rect rect = new Rect();
        this.h.getTextBounds(str, 0, str.length(), rect);
        int i2 = this.p;
        for (int width = rect.width(); width > this.G; width = rect.width()) {
            i2--;
            this.h.setTextSize((float) i2);
            this.h.getTextBounds(str, 0, str.length(), rect);
        }
        this.g.setTextSize((float) i2);
    }

    private int b(int i2) {
        if (i2 < 0) {
            return b(this.k.size() + i2);
        }
        if (i2 > this.k.size() - 1) {
            return b(i2 - this.k.size());
        }
        return i2;
    }

    private String a(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof cn.qqtheme.framework.a.a) {
            return ((cn.qqtheme.framework.a.a) obj).a();
        }
        if (!(obj instanceof Integer)) {
            return obj.toString();
        }
        return String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(((Integer) obj).intValue())});
    }

    private void b(String str) {
        Rect rect = new Rect();
        this.h.getTextBounds(str, 0, str.length(), rect);
        switch (this.M) {
            case 3:
                this.N = cn.qqtheme.framework.b.a.a(getContext(), 8.0f);
                return;
            case 5:
                this.N = (this.G - rect.width()) - ((int) this.P);
                return;
            case 17:
                this.N = (int) (((double) (this.G - rect.width())) * 0.5d);
                return;
            default:
                return;
        }
    }

    private void c(String str) {
        Rect rect = new Rect();
        this.g.getTextBounds(str, 0, str.length(), rect);
        switch (this.M) {
            case 3:
                this.O = cn.qqtheme.framework.b.a.a(getContext(), 8.0f);
                return;
            case 5:
                this.O = (this.G - rect.width()) - ((int) this.P);
                return;
            case 17:
                this.O = (int) (((double) (this.G - rect.width())) * 0.5d);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.L = i2;
        d();
        setMeasuredDimension(this.G, this.F);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.b.onTouchEvent(motionEvent);
        ViewParent parent = getParent();
        switch (motionEvent.getAction()) {
            case 0:
                this.K = System.currentTimeMillis();
                f();
                this.J = motionEvent.getRawY();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            case 2:
                float rawY = this.J - motionEvent.getRawY();
                this.J = motionEvent.getRawY();
                this.A += rawY;
                if (!this.x) {
                    float f2 = this.f363q * ((float) (-this.B));
                    float size = ((float) ((this.k.size() - 1) - this.B)) * this.f363q;
                    if (((double) this.A) - (((double) this.f363q) * 0.25d) < ((double) f2)) {
                        f2 = this.A - rawY;
                    } else if (((double) this.A) + (((double) this.f363q) * 0.25d) > ((double) size)) {
                        size = this.A - rawY;
                    }
                    if (this.A >= f2) {
                        if (this.A > size) {
                            this.A = (float) ((int) size);
                            break;
                        }
                    } else {
                        this.A = (float) ((int) f2);
                        break;
                    }
                }
                break;
            default:
                if (!onTouchEvent) {
                    float f3 = ((this.A % this.f363q) + this.f363q) % this.f363q;
                    this.I = (int) ((((float) (((int) (((Math.acos((double) ((((float) this.H) - motionEvent.getY()) / ((float) this.H))) * ((double) this.H)) + ((double) (this.f363q / 2.0f))) / ((double) this.f363q))) - (this.E / 2))) * this.f363q) - f3);
                    if (System.currentTimeMillis() - this.K > 120) {
                        a(3);
                    } else {
                        a(1);
                    }
                }
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                    break;
                }
                break;
        }
        if (motionEvent.getAction() != 0) {
            invalidate();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int getItemCount() {
        if (this.k != null) {
            return this.k.size();
        }
        return 0;
    }

    private int a(Paint paint, String str) {
        int i2 = 0;
        if (str != null && str.length() > 0) {
            int length = str.length();
            float[] fArr = new float[length];
            paint.getTextWidths(str, fArr);
            int i3 = 0;
            while (i3 < length) {
                int ceil = ((int) Math.ceil((double) fArr[i3])) + i2;
                i3++;
                i2 = ceil;
            }
        }
        return i2;
    }
}
