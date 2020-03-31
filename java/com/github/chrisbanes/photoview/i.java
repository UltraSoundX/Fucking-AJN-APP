package com.github.chrisbanes.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;

/* compiled from: PhotoViewAttacher */
public class i implements OnLayoutChangeListener, OnTouchListener, c {
    private static float a = 3.0f;
    private static float b = 1.75f;
    /* access modifiers changed from: private */
    public static float c = 1.0f;
    private static int d = 200;
    /* access modifiers changed from: private */
    public static int e = 1;
    /* access modifiers changed from: private */
    public h A;
    private b B;
    private int C = 2;
    private float D;
    private boolean E = true;
    private ScaleType F = ScaleType.FIT_CENTER;
    /* access modifiers changed from: private */
    public Interpolator f = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public int g = d;
    private float h = c;
    private float i = b;
    private float j = a;
    private boolean k = true;
    private boolean l = false;
    /* access modifiers changed from: private */
    public ImageView m;
    private GestureDetector n;
    private b o;
    private final Matrix p = new Matrix();

    /* renamed from: q reason: collision with root package name */
    private final Matrix f421q = new Matrix();
    /* access modifiers changed from: private */
    public final Matrix r = new Matrix();
    private final RectF s = new RectF();
    private final float[] t = new float[9];
    private d u;
    /* access modifiers changed from: private */
    public f v;
    /* access modifiers changed from: private */
    public e w;
    /* access modifiers changed from: private */
    public OnClickListener x;
    /* access modifiers changed from: private */
    public OnLongClickListener y;
    private g z;

    /* renamed from: com.github.chrisbanes.photoview.i$3 reason: invalid class name */
    /* compiled from: PhotoViewAttacher */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: PhotoViewAttacher */
    private class a implements Runnable {
        private final float b;
        private final float c;
        private final long d = System.currentTimeMillis();
        private final float e;
        private final float f;

        public a(float f2, float f3, float f4, float f5) {
            this.b = f4;
            this.c = f5;
            this.e = f2;
            this.f = f3;
        }

        public void run() {
            float a2 = a();
            i.this.a((this.e + ((this.f - this.e) * a2)) / i.this.e(), this.b, this.c);
            if (a2 < 1.0f) {
                a.a(i.this.m, this);
            }
        }

        private float a() {
            return i.this.f.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / ((float) i.this.g)));
        }
    }

    /* compiled from: PhotoViewAttacher */
    private class b implements Runnable {
        private final OverScroller b;
        private int c;
        private int d;

        public b(Context context) {
            this.b = new OverScroller(context);
        }

        public void a() {
            this.b.forceFinished(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF a2 = i.this.a();
            if (a2 != null) {
                int round = Math.round(-a2.left);
                if (((float) i) < a2.width()) {
                    i5 = Math.round(a2.width() - ((float) i));
                    i6 = 0;
                } else {
                    i5 = round;
                    i6 = round;
                }
                int round2 = Math.round(-a2.top);
                if (((float) i2) < a2.height()) {
                    i7 = Math.round(a2.height() - ((float) i2));
                    i8 = 0;
                } else {
                    i7 = round2;
                    i8 = round2;
                }
                this.c = round;
                this.d = round2;
                if (round != i5 || round2 != i7) {
                    this.b.fling(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.b.isFinished() && this.b.computeScrollOffset()) {
                int currX = this.b.getCurrX();
                int currY = this.b.getCurrY();
                i.this.r.postTranslate((float) (this.c - currX), (float) (this.d - currY));
                i.this.a(i.this.k());
                this.c = currX;
                this.d = currY;
                a.a(i.this.m, this);
            }
        }
    }

    public i(ImageView imageView) {
        this.m = imageView;
        imageView.setOnTouchListener(this);
        imageView.addOnLayoutChangeListener(this);
        if (!imageView.isInEditMode()) {
            this.D = 0.0f;
            this.o = new b(imageView.getContext(), this);
            this.n = new GestureDetector(imageView.getContext(), new SimpleOnGestureListener() {
                public void onLongPress(MotionEvent motionEvent) {
                    if (i.this.y != null) {
                        i.this.y.onLongClick(i.this.m);
                    }
                }

                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                    if (i.this.A == null || i.this.e() > i.c || MotionEventCompat.getPointerCount(motionEvent) > i.e || MotionEventCompat.getPointerCount(motionEvent2) > i.e) {
                        return false;
                    }
                    return i.this.A.a(motionEvent, motionEvent2, f, f2);
                }
            });
            this.n.setOnDoubleTapListener(new OnDoubleTapListener() {
                public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                    if (i.this.x != null) {
                        i.this.x.onClick(i.this.m);
                    }
                    RectF a2 = i.this.a();
                    if (a2 != null) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (a2.contains(x, y)) {
                            float width = (x - a2.left) / a2.width();
                            float height = (y - a2.top) / a2.height();
                            if (i.this.v != null) {
                                i.this.v.a(i.this.m, width, height);
                            }
                            return true;
                        } else if (i.this.w != null) {
                            i.this.w.a(i.this.m);
                        }
                    }
                    return false;
                }

                public boolean onDoubleTap(MotionEvent motionEvent) {
                    try {
                        float e = i.this.e();
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (e < i.this.c()) {
                            i.this.a(i.this.c(), x, y, true);
                        } else if (e < i.this.c() || e >= i.this.d()) {
                            i.this.a(i.this.b(), x, y, true);
                        } else {
                            i.this.a(i.this.d(), x, y, true);
                        }
                    } catch (ArrayIndexOutOfBoundsException e2) {
                    }
                    return true;
                }

                public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                    return false;
                }
            });
        }
    }

    public void a(OnDoubleTapListener onDoubleTapListener) {
        this.n.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void a(g gVar) {
        this.z = gVar;
    }

    public void a(h hVar) {
        this.A = hVar;
    }

    public RectF a() {
        n();
        return b(k());
    }

    public void a(float f2) {
        this.r.setRotate(f2 % 360.0f);
        m();
    }

    public void b(float f2) {
        this.r.postRotate(f2 % 360.0f);
        m();
    }

    public float b() {
        return this.h;
    }

    public float c() {
        return this.i;
    }

    public float d() {
        return this.j;
    }

    public float e() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) a(this.r, 0), 2.0d)) + ((float) Math.pow((double) a(this.r, 3), 2.0d))));
    }

    public ScaleType f() {
        return this.F;
    }

    public void a(float f2, float f3) {
        if (!this.o.a()) {
            this.r.postTranslate(f2, f3);
            m();
            ViewParent parent = this.m.getParent();
            if (!this.k || this.o.a() || this.l) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.C == 2 || ((this.C == 0 && f2 >= 1.0f) || (this.C == 1 && f2 <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void a(float f2, float f3, float f4, float f5) {
        this.B = new b(this.m.getContext());
        this.B.a(a(this.m), b(this.m), (int) f4, (int) f5);
        this.m.post(this.B);
    }

    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        a(this.m.getDrawable());
    }

    public void a(float f2, float f3, float f4) {
        if (e() >= this.j && f2 >= 1.0f) {
            return;
        }
        if (e() > this.h || f2 > 1.0f) {
            if (this.z != null) {
                this.z.a(f2, f3, f4);
            }
            this.r.postScale(f2, f2, f3, f4);
            m();
        }
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
            r8 = this;
            r6 = 0
            r7 = 1
            boolean r0 = r8.E
            if (r0 == 0) goto L_0x0093
            r0 = r9
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = com.github.chrisbanes.photoview.j.a(r0)
            if (r0 == 0) goto L_0x0093
            int r0 = r10.getAction()
            switch(r0) {
                case 0: goto L_0x0058;
                case 1: goto L_0x0066;
                case 2: goto L_0x0016;
                case 3: goto L_0x0066;
                default: goto L_0x0016;
            }
        L_0x0016:
            r0 = r6
        L_0x0017:
            com.github.chrisbanes.photoview.b r1 = r8.o
            if (r1 == 0) goto L_0x004a
            com.github.chrisbanes.photoview.b r0 = r8.o
            boolean r1 = r0.a()
            com.github.chrisbanes.photoview.b r0 = r8.o
            boolean r3 = r0.b()
            com.github.chrisbanes.photoview.b r0 = r8.o
            boolean r0 = r0.a(r10)
            if (r1 != 0) goto L_0x008f
            com.github.chrisbanes.photoview.b r1 = r8.o
            boolean r1 = r1.a()
            if (r1 != 0) goto L_0x008f
            r2 = r7
        L_0x0038:
            if (r3 != 0) goto L_0x0091
            com.github.chrisbanes.photoview.b r1 = r8.o
            boolean r1 = r1.b()
            if (r1 != 0) goto L_0x0091
            r1 = r7
        L_0x0043:
            if (r2 == 0) goto L_0x0048
            if (r1 == 0) goto L_0x0048
            r6 = r7
        L_0x0048:
            r8.l = r6
        L_0x004a:
            android.view.GestureDetector r1 = r8.n
            if (r1 == 0) goto L_0x0057
            android.view.GestureDetector r1 = r8.n
            boolean r1 = r1.onTouchEvent(r10)
            if (r1 == 0) goto L_0x0057
            r0 = r7
        L_0x0057:
            return r0
        L_0x0058:
            android.view.ViewParent r0 = r9.getParent()
            if (r0 == 0) goto L_0x0061
            r0.requestDisallowInterceptTouchEvent(r7)
        L_0x0061:
            r8.o()
            r0 = r6
            goto L_0x0017
        L_0x0066:
            float r0 = r8.e()
            float r1 = r8.h
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0016
            android.graphics.RectF r1 = r8.a()
            if (r1 == 0) goto L_0x0016
            com.github.chrisbanes.photoview.i$a r0 = new com.github.chrisbanes.photoview.i$a
            float r2 = r8.e()
            float r3 = r8.h
            float r4 = r1.centerX()
            float r5 = r1.centerY()
            r1 = r8
            r0.<init>(r2, r3, r4, r5)
            r9.post(r0)
            r0 = r7
            goto L_0x0017
        L_0x008f:
            r2 = r6
            goto L_0x0038
        L_0x0091:
            r1 = r6
            goto L_0x0043
        L_0x0093:
            r0 = r6
            goto L_0x0057
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.chrisbanes.photoview.i.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void a(boolean z2) {
        this.k = z2;
    }

    public void c(float f2) {
        j.a(f2, this.i, this.j);
        this.h = f2;
    }

    public void d(float f2) {
        j.a(this.h, f2, this.j);
        this.i = f2;
    }

    public void e(float f2) {
        j.a(this.h, this.i, f2);
        this.j = f2;
    }

    public void a(OnLongClickListener onLongClickListener) {
        this.y = onLongClickListener;
    }

    public void a(OnClickListener onClickListener) {
        this.x = onClickListener;
    }

    public void a(d dVar) {
        this.u = dVar;
    }

    public void a(f fVar) {
        this.v = fVar;
    }

    public void a(e eVar) {
        this.w = eVar;
    }

    public void f(float f2) {
        a(f2, false);
    }

    public void a(float f2, boolean z2) {
        a(f2, (float) (this.m.getRight() / 2), (float) (this.m.getBottom() / 2), z2);
    }

    public void a(float f2, float f3, float f4, boolean z2) {
        if (f2 < this.h || f2 > this.j) {
            throw new IllegalArgumentException("Scale must be within the range of minScale and maxScale");
        } else if (z2) {
            this.m.post(new a(e(), f2, f3, f4));
        } else {
            this.r.setScale(f2, f2, f3, f4);
            m();
        }
    }

    public void a(ScaleType scaleType) {
        if (j.a(scaleType) && scaleType != this.F) {
            this.F = scaleType;
            g();
        }
    }

    public void b(boolean z2) {
        this.E = z2;
        g();
    }

    public void g() {
        if (this.E) {
            a(this.m.getDrawable());
        } else {
            l();
        }
    }

    /* access modifiers changed from: private */
    public Matrix k() {
        this.f421q.set(this.p);
        this.f421q.postConcat(this.r);
        return this.f421q;
    }

    public Matrix h() {
        return this.f421q;
    }

    public void a(int i2) {
        this.g = i2;
    }

    private float a(Matrix matrix, int i2) {
        matrix.getValues(this.t);
        return this.t[i2];
    }

    private void l() {
        this.r.reset();
        b(this.D);
        a(k());
        n();
    }

    /* access modifiers changed from: private */
    public void a(Matrix matrix) {
        this.m.setImageMatrix(matrix);
        if (this.u != null) {
            RectF b2 = b(matrix);
            if (b2 != null) {
                this.u.a(b2);
            }
        }
    }

    private void m() {
        if (n()) {
            a(k());
        }
    }

    private RectF b(Matrix matrix) {
        Drawable drawable = this.m.getDrawable();
        if (drawable == null) {
            return null;
        }
        this.s.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.s);
        return this.s;
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            float a2 = (float) a(this.m);
            float b2 = (float) b(this.m);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.p.reset();
            float f2 = a2 / ((float) intrinsicWidth);
            float f3 = b2 / ((float) intrinsicHeight);
            if (this.F != ScaleType.CENTER) {
                if (this.F != ScaleType.CENTER_CROP) {
                    if (this.F != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, a2, b2);
                        if (((int) this.D) % 180 != 0) {
                            rectF = new RectF(0.0f, 0.0f, (float) intrinsicHeight, (float) intrinsicWidth);
                        }
                        switch (AnonymousClass3.a[this.F.ordinal()]) {
                            case 1:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 2:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.p.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                        }
                    } else {
                        float min = Math.min(1.0f, Math.min(f2, f3));
                        this.p.postScale(min, min);
                        this.p.postTranslate((a2 - (((float) intrinsicWidth) * min)) / 2.0f, (b2 - (min * ((float) intrinsicHeight))) / 2.0f);
                    }
                } else {
                    float max = Math.max(f2, f3);
                    this.p.postScale(max, max);
                    this.p.postTranslate((a2 - (((float) intrinsicWidth) * max)) / 2.0f, (b2 - (max * ((float) intrinsicHeight))) / 2.0f);
                }
            } else {
                this.p.postTranslate((a2 - ((float) intrinsicWidth)) / 2.0f, (b2 - ((float) intrinsicHeight)) / 2.0f);
            }
            l();
        }
    }

    private boolean n() {
        float f2;
        float f3 = 0.0f;
        RectF b2 = b(k());
        if (b2 == null) {
            return false;
        }
        float height = b2.height();
        float width = b2.width();
        int b3 = b(this.m);
        if (height <= ((float) b3)) {
            switch (AnonymousClass3.a[this.F.ordinal()]) {
                case 2:
                    f2 = -b2.top;
                    break;
                case 3:
                    f2 = (((float) b3) - height) - b2.top;
                    break;
                default:
                    f2 = ((((float) b3) - height) / 2.0f) - b2.top;
                    break;
            }
        } else if (b2.top > 0.0f) {
            f2 = -b2.top;
        } else if (b2.bottom < ((float) b3)) {
            f2 = ((float) b3) - b2.bottom;
        } else {
            f2 = 0.0f;
        }
        int a2 = a(this.m);
        if (width <= ((float) a2)) {
            switch (AnonymousClass3.a[this.F.ordinal()]) {
                case 2:
                    f3 = -b2.left;
                    break;
                case 3:
                    f3 = (((float) a2) - width) - b2.left;
                    break;
                default:
                    f3 = ((((float) a2) - width) / 2.0f) - b2.left;
                    break;
            }
            this.C = 2;
        } else if (b2.left > 0.0f) {
            this.C = 0;
            f3 = -b2.left;
        } else if (b2.right < ((float) a2)) {
            f3 = ((float) a2) - b2.right;
            this.C = 1;
        } else {
            this.C = -1;
        }
        this.r.postTranslate(f3, f2);
        return true;
    }

    private int a(ImageView imageView) {
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int b(ImageView imageView) {
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void o() {
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }
}
