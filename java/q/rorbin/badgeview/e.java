package q.rorbin.badgeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import q.rorbin.badgeview.a.C0086a;

/* compiled from: QBadgeView */
public class e extends View implements a {
    protected PointF A;
    protected PointF B;
    protected PointF C;
    protected PointF D;
    protected List<PointF> E;
    protected View F;
    protected int G;
    protected int H;
    protected TextPaint I;
    protected Paint J;
    protected Paint K;
    protected b L;
    protected C0086a M;
    protected ViewGroup N;
    protected int a;
    protected int b;
    protected int c;
    protected Drawable d;
    protected Bitmap e;
    protected boolean f;
    protected float g;
    protected float h;
    protected float i;
    protected int j;
    protected String k;
    protected boolean l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected int p;

    /* renamed from: q reason: collision with root package name */
    protected float f443q;
    protected float r;
    protected float s;
    protected float t;
    protected int u;
    protected boolean v;
    protected RectF w;
    protected RectF x;
    protected Path y;
    protected FontMetrics z;

    /* compiled from: QBadgeView */
    private class a extends ViewGroup {
        public a(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            }
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            int i3 = 0;
            View view = null;
            View view2 = null;
            while (i3 < getChildCount()) {
                View childAt = getChildAt(i3);
                if (childAt instanceof e) {
                    view2 = childAt;
                    childAt = view;
                }
                i3++;
                view = childAt;
            }
            if (view == null) {
                super.onMeasure(i, i2);
                return;
            }
            view.measure(i, i2);
            if (view2 != null) {
                view2.measure(MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
            }
            setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public e(Context context) {
        this(context, null);
    }

    private e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private e(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        c();
    }

    private void c() {
        setLayerType(1, null);
        this.w = new RectF();
        this.x = new RectF();
        this.y = new Path();
        this.A = new PointF();
        this.B = new PointF();
        this.C = new PointF();
        this.D = new PointF();
        this.E = new ArrayList();
        this.I = new TextPaint();
        this.I.setAntiAlias(true);
        this.I.setSubpixelText(true);
        this.I.setFakeBoldText(true);
        this.I.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        this.J = new Paint();
        this.J.setAntiAlias(true);
        this.J.setStyle(Style.FILL);
        this.K = new Paint();
        this.K.setAntiAlias(true);
        this.K.setStyle(Style.STROKE);
        this.a = -1552832;
        this.c = -1;
        this.h = (float) c.a(getContext(), 11.0f);
        this.i = (float) c.a(getContext(), 5.0f);
        this.j = 0;
        this.p = 8388661;
        this.f443q = (float) c.a(getContext(), 1.0f);
        this.r = (float) c.a(getContext(), 1.0f);
        this.t = (float) c.a(getContext(), 90.0f);
        this.o = true;
        this.f = false;
        if (VERSION.SDK_INT >= 21) {
            setTranslationZ(1000.0f);
        }
    }

    public a a(View view) {
        if (view == null) {
            throw new IllegalStateException("targetView can not be null");
        }
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("targetView must have a parent");
        }
        this.F = view;
        if (parent instanceof a) {
            ((a) parent).addView(this);
        } else {
            ViewGroup viewGroup = (ViewGroup) parent;
            int indexOfChild = viewGroup.indexOfChild(view);
            LayoutParams layoutParams = view.getLayoutParams();
            viewGroup.removeView(view);
            a aVar = new a(getContext());
            aVar.setId(view.getId());
            view.setId(-1);
            viewGroup.addView(aVar, indexOfChild, layoutParams);
            aVar.addView(view);
            aVar.addView(this);
        }
        return this;
    }

    public View getTargetView() {
        return this.F;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.N == null) {
            b(this.F);
        }
    }

    private void b(View view) {
        this.N = (ViewGroup) view.getRootView();
        if (this.N == null) {
            c(view);
        }
    }

    private void c(View view) {
        if (view.getParent() != null && (view.getParent() instanceof View)) {
            c((View) view.getParent());
        } else if (view instanceof ViewGroup) {
            this.N = (ViewGroup) view;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
            case 5:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (this.l && motionEvent.getPointerId(motionEvent.getActionIndex()) == 0 && x2 > this.x.left && x2 < this.x.right && y2 > this.x.top && y2 < this.x.bottom && this.k != null) {
                    i();
                    this.m = true;
                    e(1);
                    this.s = (float) c.a(getContext(), 7.0f);
                    getParent().requestDisallowInterceptTouchEvent(true);
                    b(true);
                    this.B.x = motionEvent.getRawX();
                    this.B.y = motionEvent.getRawY();
                    break;
                }
            case 1:
            case 3:
            case 6:
                if (motionEvent.getPointerId(motionEvent.getActionIndex()) == 0 && this.m) {
                    this.m = false;
                    d();
                    break;
                }
            case 2:
                if (this.m) {
                    this.B.x = motionEvent.getRawX();
                    this.B.y = motionEvent.getRawY();
                    invalidate();
                    break;
                }
                break;
        }
        if (this.m || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    private void d() {
        if (this.v) {
            a(this.B);
            e(5);
            return;
        }
        b();
        e(4);
    }

    /* access modifiers changed from: protected */
    public Bitmap a() {
        Bitmap createBitmap = Bitmap.createBitmap(((int) this.x.width()) + c.a(getContext(), 3.0f), ((int) this.x.height()) + c.a(getContext(), 3.0f), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        a(canvas, new PointF(((float) canvas.getWidth()) / 2.0f, ((float) canvas.getHeight()) / 2.0f), getBadgeCircleRadius());
        return createBitmap;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z2) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (z2) {
            this.N.addView(this, new FrameLayout.LayoutParams(-1, -1));
        } else {
            a(this.F);
        }
    }

    private void d(boolean z2) {
        int a2 = c.a(getContext(), 1.0f);
        int a3 = c.a(getContext(), 1.5f);
        switch (this.u) {
            case 1:
                a2 = c.a(getContext(), 1.0f);
                a3 = c.a(getContext(), -1.5f);
                break;
            case 2:
                a2 = c.a(getContext(), -1.0f);
                a3 = c.a(getContext(), -1.5f);
                break;
            case 3:
                a2 = c.a(getContext(), -1.0f);
                a3 = c.a(getContext(), 1.5f);
                break;
            case 4:
                a2 = c.a(getContext(), 1.0f);
                a3 = c.a(getContext(), 1.5f);
                break;
        }
        this.J.setShadowLayer(z2 ? (float) c.a(getContext(), 2.0f) : 0.0f, (float) a2, (float) a3, 855638016);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.G = i2;
        this.H = i3;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.L != null && this.L.isRunning()) {
            this.L.a(canvas);
        } else if (this.k != null) {
            e();
            float badgeCircleRadius = getBadgeCircleRadius();
            float b2 = (1.0f - (d.b(this.C, this.B) / this.t)) * this.s;
            if (!this.l || !this.m) {
                g();
                a(canvas, this.A, badgeCircleRadius);
                return;
            }
            this.u = d.a(this.B, this.C);
            d(this.o);
            boolean z2 = b2 < ((float) c.a(getContext(), 1.5f));
            this.v = z2;
            if (z2) {
                e(3);
                a(canvas, this.B, badgeCircleRadius);
                return;
            }
            e(2);
            a(canvas, b2, badgeCircleRadius);
            a(canvas, this.B, badgeCircleRadius);
        }
    }

    private void e() {
        d(this.o);
        this.J.setColor(this.a);
        this.K.setColor(this.b);
        this.K.setStrokeWidth(this.g);
        this.I.setColor(this.c);
        this.I.setTextAlign(Align.CENTER);
    }

    private void a(Canvas canvas, float f2, float f3) {
        float f4;
        float f5;
        float f6 = this.B.y - this.C.y;
        float f7 = this.B.x - this.C.x;
        this.E.clear();
        if (f7 != 0.0f) {
            double d2 = -1.0d / ((double) (f6 / f7));
            d.a(this.B, f3, Double.valueOf(d2), this.E);
            d.a(this.C, f2, Double.valueOf(d2), this.E);
        } else {
            d.a(this.B, f3, Double.valueOf(0.0d), this.E);
            d.a(this.C, f2, Double.valueOf(0.0d), this.E);
        }
        this.y.reset();
        this.y.addCircle(this.C.x, this.C.y, f2, (this.u == 1 || this.u == 2) ? Direction.CCW : Direction.CW);
        this.D.x = (this.C.x + this.B.x) / 2.0f;
        this.D.y = (this.C.y + this.B.y) / 2.0f;
        this.y.moveTo(((PointF) this.E.get(2)).x, ((PointF) this.E.get(2)).y);
        this.y.quadTo(this.D.x, this.D.y, ((PointF) this.E.get(0)).x, ((PointF) this.E.get(0)).y);
        this.y.lineTo(((PointF) this.E.get(1)).x, ((PointF) this.E.get(1)).y);
        this.y.quadTo(this.D.x, this.D.y, ((PointF) this.E.get(3)).x, ((PointF) this.E.get(3)).y);
        this.y.lineTo(((PointF) this.E.get(2)).x, ((PointF) this.E.get(2)).y);
        this.y.close();
        canvas.drawPath(this.y, this.J);
        if (this.b != 0 && this.g > 0.0f) {
            this.y.reset();
            this.y.moveTo(((PointF) this.E.get(2)).x, ((PointF) this.E.get(2)).y);
            this.y.quadTo(this.D.x, this.D.y, ((PointF) this.E.get(0)).x, ((PointF) this.E.get(0)).y);
            this.y.moveTo(((PointF) this.E.get(1)).x, ((PointF) this.E.get(1)).y);
            this.y.quadTo(this.D.x, this.D.y, ((PointF) this.E.get(3)).x, ((PointF) this.E.get(3)).y);
            if (this.u == 1 || this.u == 2) {
                f4 = this.C.y - ((PointF) this.E.get(2)).y;
                f5 = ((PointF) this.E.get(2)).x - this.C.x;
            } else {
                f4 = this.C.y - ((PointF) this.E.get(3)).y;
                f5 = ((PointF) this.E.get(3)).x - this.C.x;
            }
            float a2 = 360.0f - ((float) d.a(d.a(Math.atan((double) (f4 / f5)), this.u + -1 == 0 ? 4 : this.u - 1)));
            if (VERSION.SDK_INT >= 21) {
                this.y.addArc(this.C.x - f2, this.C.y - f2, this.C.x + f2, this.C.y + f2, a2, 180.0f);
            } else {
                this.y.addArc(new RectF(this.C.x - f2, this.C.y - f2, this.C.x + f2, this.C.y + f2), a2, 180.0f);
            }
            canvas.drawPath(this.y, this.K);
        }
    }

    private void a(Canvas canvas, PointF pointF, float f2) {
        if (pointF.x != -1000.0f || pointF.y != -1000.0f) {
            if (this.k.isEmpty() || this.k.length() == 1) {
                this.x.left = pointF.x - ((float) ((int) f2));
                this.x.top = pointF.y - ((float) ((int) f2));
                this.x.right = pointF.x + ((float) ((int) f2));
                this.x.bottom = pointF.y + ((float) ((int) f2));
                if (this.d != null) {
                    a(canvas);
                } else {
                    canvas.drawCircle(pointF.x, pointF.y, f2, this.J);
                    if (this.b != 0 && this.g > 0.0f) {
                        canvas.drawCircle(pointF.x, pointF.y, f2, this.K);
                    }
                }
            } else {
                this.x.left = pointF.x - ((this.w.width() / 2.0f) + this.i);
                this.x.top = pointF.y - ((this.w.height() / 2.0f) + (this.i * 0.5f));
                this.x.right = pointF.x + (this.w.width() / 2.0f) + this.i;
                this.x.bottom = pointF.y + (this.w.height() / 2.0f) + (this.i * 0.5f);
                float height = this.x.height() / 2.0f;
                if (this.d != null) {
                    a(canvas);
                } else {
                    canvas.drawRoundRect(this.x, height, height, this.J);
                    if (this.b != 0 && this.g > 0.0f) {
                        canvas.drawRoundRect(this.x, height, height, this.K);
                    }
                }
            }
            if (!this.k.isEmpty()) {
                canvas.drawText(this.k, pointF.x, (((this.x.bottom + this.x.top) - this.z.bottom) - this.z.top) / 2.0f, this.I);
            }
        }
    }

    private void a(Canvas canvas) {
        this.J.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        int i2 = (int) this.x.left;
        int i3 = (int) this.x.top;
        int i4 = (int) this.x.right;
        int i5 = (int) this.x.bottom;
        if (this.f) {
            int width = i2 + this.e.getWidth();
            int height = i3 + this.e.getHeight();
            canvas.saveLayer((float) i2, (float) i3, (float) width, (float) height, null, 31);
            i5 = height;
            i4 = width;
        }
        this.d.setBounds(i2, i3, i4, i5);
        this.d.draw(canvas);
        if (this.f) {
            this.J.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawBitmap(this.e, (float) i2, (float) i3, this.J);
            canvas.restore();
            this.J.setXfermode(null);
            if (this.k.isEmpty() || this.k.length() == 1) {
                canvas.drawCircle(this.x.centerX(), this.x.centerY(), this.x.width() / 2.0f, this.K);
            } else {
                canvas.drawRoundRect(this.x, this.x.height() / 2.0f, this.x.height() / 2.0f, this.K);
            }
        } else {
            canvas.drawRect(this.x, this.K);
        }
    }

    private void f() {
        if (this.k != null && this.f) {
            if (this.e != null && !this.e.isRecycled()) {
                this.e.recycle();
            }
            float badgeCircleRadius = getBadgeCircleRadius();
            if (this.k.isEmpty() || this.k.length() == 1) {
                this.e = Bitmap.createBitmap(((int) badgeCircleRadius) * 2, ((int) badgeCircleRadius) * 2, Config.ARGB_4444);
                Canvas canvas = new Canvas(this.e);
                canvas.drawCircle(((float) canvas.getWidth()) / 2.0f, ((float) canvas.getHeight()) / 2.0f, ((float) canvas.getWidth()) / 2.0f, this.J);
                return;
            }
            this.e = Bitmap.createBitmap((int) (this.w.width() + (this.i * 2.0f)), (int) (this.w.height() + this.i), Config.ARGB_4444);
            Canvas canvas2 = new Canvas(this.e);
            if (VERSION.SDK_INT >= 21) {
                canvas2.drawRoundRect(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight(), ((float) canvas2.getHeight()) / 2.0f, ((float) canvas2.getHeight()) / 2.0f, this.J);
            } else {
                canvas2.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight()), ((float) canvas2.getHeight()) / 2.0f, ((float) canvas2.getHeight()) / 2.0f, this.J);
            }
        }
    }

    private float getBadgeCircleRadius() {
        if (this.k.isEmpty()) {
            return this.i;
        }
        if (this.k.length() != 1) {
            return this.x.height() / 2.0f;
        }
        if (this.w.height() > this.w.width()) {
            return (this.w.height() / 2.0f) + (this.i * 0.5f);
        }
        return (this.w.width() / 2.0f) + (this.i * 0.5f);
    }

    private void g() {
        float width = this.w.height() > this.w.width() ? this.w.height() : this.w.width();
        switch (this.p) {
            case 17:
                this.A.x = ((float) this.G) / 2.0f;
                this.A.y = ((float) this.H) / 2.0f;
                break;
            case 49:
                this.A.x = ((float) this.G) / 2.0f;
                this.A.y = this.r + this.i + (this.w.height() / 2.0f);
                break;
            case 81:
                this.A.x = ((float) this.G) / 2.0f;
                this.A.y = ((float) this.H) - ((this.r + this.i) + (this.w.height() / 2.0f));
                break;
            case 8388627:
                this.A.x = (width / 2.0f) + this.f443q + this.i;
                this.A.y = ((float) this.H) / 2.0f;
                break;
            case 8388629:
                this.A.x = ((float) this.G) - ((width / 2.0f) + (this.f443q + this.i));
                this.A.y = ((float) this.H) / 2.0f;
                break;
            case 8388659:
                this.A.x = (width / 2.0f) + this.f443q + this.i;
                this.A.y = this.r + this.i + (this.w.height() / 2.0f);
                break;
            case 8388661:
                this.A.x = ((float) this.G) - ((width / 2.0f) + (this.f443q + this.i));
                this.A.y = this.r + this.i + (this.w.height() / 2.0f);
                break;
            case 8388691:
                this.A.x = (width / 2.0f) + this.f443q + this.i;
                this.A.y = ((float) this.H) - ((this.r + this.i) + (this.w.height() / 2.0f));
                break;
            case 8388693:
                this.A.x = ((float) this.G) - ((width / 2.0f) + (this.f443q + this.i));
                this.A.y = ((float) this.H) - ((this.r + this.i) + (this.w.height() / 2.0f));
                break;
        }
        i();
    }

    private void h() {
        this.w.left = 0.0f;
        this.w.top = 0.0f;
        if (TextUtils.isEmpty(this.k)) {
            this.w.right = 0.0f;
            this.w.bottom = 0.0f;
        } else {
            this.I.setTextSize(this.h);
            this.w.right = this.I.measureText(this.k);
            this.z = this.I.getFontMetrics();
            this.w.bottom = this.z.descent - this.z.ascent;
        }
        f();
    }

    private void i() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.C.x = this.A.x + ((float) iArr[0]);
        this.C.y = ((float) iArr[1]) + this.A.y;
    }

    /* access modifiers changed from: protected */
    public void a(PointF pointF) {
        if (this.k != null) {
            if (this.L == null || !this.L.isRunning()) {
                b(true);
                this.L = new b(a(), pointF, this);
                this.L.start();
                a(0);
            }
        }
    }

    public void b() {
        this.B.x = -1000.0f;
        this.B.y = -1000.0f;
        this.u = 4;
        b(false);
        getParent().requestDisallowInterceptTouchEvent(false);
        invalidate();
    }

    public void c(boolean z2) {
        if (!z2 || this.N == null) {
            a(0);
            return;
        }
        i();
        a(this.C);
    }

    public a a(int i2) {
        this.j = i2;
        if (this.j < 0) {
            this.k = "";
        } else if (this.j > 99) {
            this.k = this.n ? String.valueOf(this.j) : "99+";
        } else if (this.j > 0 && this.j <= 99) {
            this.k = String.valueOf(this.j);
        } else if (this.j == 0) {
            this.k = null;
        }
        h();
        invalidate();
        return this;
    }

    public int getBadgeNumber() {
        return this.j;
    }

    public String getBadgeText() {
        return this.k;
    }

    public a a(boolean z2) {
        this.n = z2;
        if (this.j > 99) {
            a(this.j);
        }
        return this;
    }

    public a b(int i2) {
        this.a = i2;
        if (this.a == 0) {
            this.I.setXfermode(null);
        } else {
            this.I.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        }
        invalidate();
        return this;
    }

    public int getBadgeBackgroundColor() {
        return this.a;
    }

    public Drawable getBadgeBackground() {
        return this.d;
    }

    public a c(int i2) {
        this.c = i2;
        invalidate();
        return this;
    }

    public int getBadgeTextColor() {
        return this.c;
    }

    public a a(float f2, boolean z2) {
        if (z2) {
            f2 = (float) c.a(getContext(), f2);
        }
        this.h = f2;
        h();
        invalidate();
        return this;
    }

    public a b(float f2, boolean z2) {
        if (z2) {
            f2 = (float) c.a(getContext(), f2);
        }
        this.i = f2;
        f();
        invalidate();
        return this;
    }

    public a d(int i2) {
        if (i2 == 8388659 || i2 == 8388661 || i2 == 8388691 || i2 == 8388693 || i2 == 17 || i2 == 49 || i2 == 81 || i2 == 8388627 || i2 == 8388629) {
            this.p = i2;
            invalidate();
            return this;
        }
        throw new IllegalStateException("only support Gravity.START | Gravity.TOP , Gravity.END | Gravity.TOP , Gravity.START | Gravity.BOTTOM , Gravity.END | Gravity.BOTTOM , Gravity.CENTER , Gravity.CENTER | Gravity.TOP , Gravity.CENTER | Gravity.BOTTOM ,Gravity.CENTER | Gravity.START , Gravity.CENTER | Gravity.END");
    }

    public int getBadgeGravity() {
        return this.p;
    }

    public a a(float f2, float f3, boolean z2) {
        if (z2) {
            f2 = (float) c.a(getContext(), f2);
        }
        this.f443q = f2;
        if (z2) {
            f3 = (float) c.a(getContext(), f3);
        }
        this.r = f3;
        invalidate();
        return this;
    }

    private void e(int i2) {
        if (this.M != null) {
            this.M.a(i2, this, this.F);
        }
    }

    public PointF getDragCenter() {
        if (!this.l || !this.m) {
            return null;
        }
        return this.B;
    }
}
