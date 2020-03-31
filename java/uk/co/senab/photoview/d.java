package uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.lang.ref.WeakReference;

/* compiled from: PhotoViewAttacher */
public class d implements OnTouchListener, OnGlobalLayoutListener, uk.co.senab.photoview.a.e, c {
    static final Interpolator a = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public static final boolean c = Log.isLoggable("PhotoViewAttacher", 3);
    private int A;
    private boolean B;
    private ScaleType C;
    int b;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private WeakReference<ImageView> i;
    private GestureDetector j;
    private uk.co.senab.photoview.a.d k;
    private final Matrix l;
    private final Matrix m;
    /* access modifiers changed from: private */
    public final Matrix n;
    private final RectF o;
    private final float[] p;

    /* renamed from: q reason: collision with root package name */
    private c f444q;
    private C0087d r;
    private f s;
    /* access modifiers changed from: private */
    public OnLongClickListener t;
    private e u;
    private int v;
    private int w;
    private int x;
    private int y;
    private b z;

    /* renamed from: uk.co.senab.photoview.d$2 reason: invalid class name */
    /* compiled from: PhotoViewAttacher */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.MATRIX.ordinal()] = 1;
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
                a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
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
            ImageView c2 = d.this.c();
            if (c2 != null) {
                float a2 = a();
                d.this.a((this.e + ((this.f - this.e) * a2)) / d.this.g(), this.b, this.c);
                if (a2 < 1.0f) {
                    a.a(c2, this);
                }
            }
        }

        private float a() {
            return d.a.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.d)) * 1.0f) / ((float) d.this.b)));
        }
    }

    /* compiled from: PhotoViewAttacher */
    private class b implements Runnable {
        private final uk.co.senab.photoview.c.d b;
        private int c;
        private int d;

        public b(Context context) {
            this.b = uk.co.senab.photoview.c.d.a(context);
        }

        public void a() {
            if (d.c) {
                uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "Cancel Fling");
            }
            this.b.a(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF b2 = d.this.b();
            if (b2 != null) {
                int round = Math.round(-b2.left);
                if (((float) i) < b2.width()) {
                    i5 = Math.round(b2.width() - ((float) i));
                    i6 = 0;
                } else {
                    i5 = round;
                    i6 = round;
                }
                int round2 = Math.round(-b2.top);
                if (((float) i2) < b2.height()) {
                    i7 = Math.round(b2.height() - ((float) i2));
                    i8 = 0;
                } else {
                    i7 = round2;
                    i8 = round2;
                }
                this.c = round;
                this.d = round2;
                if (d.c) {
                    uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "fling. StartX:" + round + " StartY:" + round2 + " MaxX:" + i5 + " MaxY:" + i7);
                }
                if (round != i5 || round2 != i7) {
                    this.b.a(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.b.b()) {
                ImageView c2 = d.this.c();
                if (c2 != null && this.b.a()) {
                    int c3 = this.b.c();
                    int d2 = this.b.d();
                    if (d.c) {
                        uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "fling run(). CurrentX:" + this.c + " CurrentY:" + this.d + " NewX:" + c3 + " NewY:" + d2);
                    }
                    d.this.n.postTranslate((float) (this.c - c3), (float) (this.d - d2));
                    d.this.b(d.this.m());
                    this.c = c3;
                    this.d = d2;
                    a.a(c2, this);
                }
            }
        }
    }

    /* compiled from: PhotoViewAttacher */
    public interface c {
        void a(RectF rectF);
    }

    /* renamed from: uk.co.senab.photoview.d$d reason: collision with other inner class name */
    /* compiled from: PhotoViewAttacher */
    public interface C0087d {
        void a(View view, float f, float f2);
    }

    /* compiled from: PhotoViewAttacher */
    public interface e {
        void a(float f, float f2, float f3);
    }

    /* compiled from: PhotoViewAttacher */
    public interface f {
        void a(View view, float f, float f2);
    }

    private static void b(float f2, float f3, float f4) {
        if (f2 >= f3) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f3 >= f4) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static boolean b(ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (AnonymousClass2.a[scaleType.ordinal()]) {
            case 1:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private static void b(ImageView imageView) {
        if (imageView != null && !(imageView instanceof c) && !ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ScaleType.MATRIX);
        }
    }

    public d(ImageView imageView) {
        this(imageView, true);
    }

    public d(ImageView imageView, boolean z2) {
        this.b = 200;
        this.d = 1.0f;
        this.e = 1.75f;
        this.f = 3.0f;
        this.g = true;
        this.h = false;
        this.l = new Matrix();
        this.m = new Matrix();
        this.n = new Matrix();
        this.o = new RectF();
        this.p = new float[9];
        this.A = 2;
        this.C = ScaleType.FIT_CENTER;
        this.i = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        b(imageView);
        if (!imageView.isInEditMode()) {
            this.k = uk.co.senab.photoview.a.f.a(imageView.getContext(), this);
            this.j = new GestureDetector(imageView.getContext(), new SimpleOnGestureListener() {
                public void onLongPress(MotionEvent motionEvent) {
                    if (d.this.t != null) {
                        d.this.t.onLongClick(d.this.c());
                    }
                }
            });
            this.j.setOnDoubleTapListener(new b(this));
            b(z2);
        }
    }

    public void a(OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.j.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.j.setOnDoubleTapListener(new b(this));
        }
    }

    public void a(e eVar) {
        this.u = eVar;
    }

    public void a() {
        if (this.i != null) {
            ImageView imageView = (ImageView) this.i.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener(null);
                p();
            }
            if (this.j != null) {
                this.j.setOnDoubleTapListener(null);
            }
            this.f444q = null;
            this.r = null;
            this.s = null;
            this.i = null;
        }
    }

    public RectF b() {
        s();
        return a(m());
    }

    public void a(float f2) {
        this.n.setRotate(f2 % 360.0f);
        q();
    }

    public void b(float f2) {
        this.n.postRotate(f2 % 360.0f);
        q();
    }

    public ImageView c() {
        ImageView imageView = null;
        if (this.i != null) {
            imageView = (ImageView) this.i.get();
        }
        if (imageView == null) {
            a();
            uk.co.senab.photoview.b.a.a().b("PhotoViewAttacher", "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    public float d() {
        return this.d;
    }

    public float e() {
        return this.e;
    }

    public float f() {
        return this.f;
    }

    public float g() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) a(this.n, 0), 2.0d)) + ((float) Math.pow((double) a(this.n, 3), 2.0d))));
    }

    public ScaleType h() {
        return this.C;
    }

    public void a(float f2, float f3) {
        if (!this.k.a()) {
            if (c) {
                uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", String.format("onDrag: dx: %.2f. dy: %.2f", new Object[]{Float.valueOf(f2), Float.valueOf(f3)}));
            }
            ImageView c2 = c();
            this.n.postTranslate(f2, f3);
            q();
            ViewParent parent = c2.getParent();
            if (!this.g || this.k.a() || this.h) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.A == 2 || ((this.A == 0 && f2 >= 1.0f) || (this.A == 1 && f2 <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void a(float f2, float f3, float f4, float f5) {
        if (c) {
            uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", "onFling. sX: " + f2 + " sY: " + f3 + " Vx: " + f4 + " Vy: " + f5);
        }
        ImageView c2 = c();
        this.z = new b(c2.getContext());
        this.z.a(c(c2), d(c2), (int) f4, (int) f5);
        c2.post(this.z);
    }

    public void onGlobalLayout() {
        ImageView c2 = c();
        if (c2 == null) {
            return;
        }
        if (this.B) {
            int top = c2.getTop();
            int right = c2.getRight();
            int bottom = c2.getBottom();
            int left = c2.getLeft();
            if (top != this.v || bottom != this.x || left != this.y || right != this.w) {
                a(c2.getDrawable());
                this.v = top;
                this.w = right;
                this.x = bottom;
                this.y = left;
                return;
            }
            return;
        }
        a(c2.getDrawable());
    }

    public void a(float f2, float f3, float f4) {
        if (c) {
            uk.co.senab.photoview.b.a.a().a("PhotoViewAttacher", String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[]{Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4)}));
        }
        if (g() < this.f || f2 < 1.0f) {
            if (this.u != null) {
                this.u.a(f2, f3, f4);
            }
            this.n.postScale(f2, f2, f3, f4);
            q();
        }
    }

    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
            r8 = this;
            r6 = 0
            r7 = 1
            boolean r0 = r8.B
            if (r0 == 0) goto L_0x009f
            r0 = r9
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            boolean r0 = a(r0)
            if (r0 == 0) goto L_0x009f
            android.view.ViewParent r0 = r9.getParent()
            int r1 = r10.getAction()
            switch(r1) {
                case 0: goto L_0x005c;
                case 1: goto L_0x0072;
                case 2: goto L_0x001a;
                case 3: goto L_0x0072;
                default: goto L_0x001a;
            }
        L_0x001a:
            r0 = r6
        L_0x001b:
            uk.co.senab.photoview.a.d r1 = r8.k
            if (r1 == 0) goto L_0x004e
            uk.co.senab.photoview.a.d r0 = r8.k
            boolean r1 = r0.a()
            uk.co.senab.photoview.a.d r0 = r8.k
            boolean r3 = r0.b()
            uk.co.senab.photoview.a.d r0 = r8.k
            boolean r0 = r0.c(r10)
            if (r1 != 0) goto L_0x009b
            uk.co.senab.photoview.a.d r1 = r8.k
            boolean r1 = r1.a()
            if (r1 != 0) goto L_0x009b
            r2 = r7
        L_0x003c:
            if (r3 != 0) goto L_0x009d
            uk.co.senab.photoview.a.d r1 = r8.k
            boolean r1 = r1.b()
            if (r1 != 0) goto L_0x009d
            r1 = r7
        L_0x0047:
            if (r2 == 0) goto L_0x004c
            if (r1 == 0) goto L_0x004c
            r6 = r7
        L_0x004c:
            r8.h = r6
        L_0x004e:
            android.view.GestureDetector r1 = r8.j
            if (r1 == 0) goto L_0x005b
            android.view.GestureDetector r1 = r8.j
            boolean r1 = r1.onTouchEvent(r10)
            if (r1 == 0) goto L_0x005b
            r0 = r7
        L_0x005b:
            return r0
        L_0x005c:
            if (r0 == 0) goto L_0x0066
            r0.requestDisallowInterceptTouchEvent(r7)
        L_0x0061:
            r8.p()
            r0 = r6
            goto L_0x001b
        L_0x0066:
            uk.co.senab.photoview.b.b r0 = uk.co.senab.photoview.b.a.a()
            java.lang.String r1 = "PhotoViewAttacher"
            java.lang.String r2 = "onTouch getParent() returned null"
            r0.b(r1, r2)
            goto L_0x0061
        L_0x0072:
            float r0 = r8.g()
            float r1 = r8.d
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x001a
            android.graphics.RectF r1 = r8.b()
            if (r1 == 0) goto L_0x001a
            uk.co.senab.photoview.d$a r0 = new uk.co.senab.photoview.d$a
            float r2 = r8.g()
            float r3 = r8.d
            float r4 = r1.centerX()
            float r5 = r1.centerY()
            r1 = r8
            r0.<init>(r2, r3, r4, r5)
            r9.post(r0)
            r0 = r7
            goto L_0x001b
        L_0x009b:
            r2 = r6
            goto L_0x003c
        L_0x009d:
            r1 = r6
            goto L_0x0047
        L_0x009f:
            r0 = r6
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.co.senab.photoview.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void a(boolean z2) {
        this.g = z2;
    }

    public void c(float f2) {
        b(f2, this.e, this.f);
        this.d = f2;
    }

    public void d(float f2) {
        b(this.d, f2, this.f);
        this.e = f2;
    }

    public void e(float f2) {
        b(this.d, this.e, f2);
        this.f = f2;
    }

    public void a(OnLongClickListener onLongClickListener) {
        this.t = onLongClickListener;
    }

    public void a(c cVar) {
        this.f444q = cVar;
    }

    public void a(C0087d dVar) {
        this.r = dVar;
    }

    public C0087d i() {
        return this.r;
    }

    public void a(f fVar) {
        this.s = fVar;
    }

    public f j() {
        return this.s;
    }

    public void f(float f2) {
        a(f2, false);
    }

    public void a(float f2, boolean z2) {
        ImageView c2 = c();
        if (c2 != null) {
            a(f2, (float) (c2.getRight() / 2), (float) (c2.getBottom() / 2), z2);
        }
    }

    public void a(float f2, float f3, float f4, boolean z2) {
        ImageView c2 = c();
        if (c2 == null) {
            return;
        }
        if (f2 < this.d || f2 > this.f) {
            uk.co.senab.photoview.b.a.a().b("PhotoViewAttacher", "Scale must be within the range of minScale and maxScale");
        } else if (z2) {
            c2.post(new a(g(), f2, f3, f4));
        } else {
            this.n.setScale(f2, f2, f3, f4);
            q();
        }
    }

    public void a(ScaleType scaleType) {
        if (b(scaleType) && scaleType != this.C) {
            this.C = scaleType;
            k();
        }
    }

    public void b(boolean z2) {
        this.B = z2;
        k();
    }

    public void k() {
        ImageView c2 = c();
        if (c2 == null) {
            return;
        }
        if (this.B) {
            b(c2);
            a(c2.getDrawable());
            return;
        }
        t();
    }

    public Matrix l() {
        return new Matrix(m());
    }

    public Matrix m() {
        this.m.set(this.l);
        this.m.postConcat(this.n);
        return this.m;
    }

    private void p() {
        if (this.z != null) {
            this.z.a();
            this.z = null;
        }
    }

    private void q() {
        if (s()) {
            b(m());
        }
    }

    private void r() {
        ImageView c2 = c();
        if (c2 != null && !(c2 instanceof c) && !ScaleType.MATRIX.equals(c2.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean s() {
        float f2;
        float f3 = 0.0f;
        ImageView c2 = c();
        if (c2 == null) {
            return false;
        }
        RectF a2 = a(m());
        if (a2 == null) {
            return false;
        }
        float height = a2.height();
        float width = a2.width();
        int d2 = d(c2);
        if (height <= ((float) d2)) {
            switch (AnonymousClass2.a[this.C.ordinal()]) {
                case 2:
                    f2 = -a2.top;
                    break;
                case 3:
                    f2 = (((float) d2) - height) - a2.top;
                    break;
                default:
                    f2 = ((((float) d2) - height) / 2.0f) - a2.top;
                    break;
            }
        } else if (a2.top > 0.0f) {
            f2 = -a2.top;
        } else if (a2.bottom < ((float) d2)) {
            f2 = ((float) d2) - a2.bottom;
        } else {
            f2 = 0.0f;
        }
        int c3 = c(c2);
        if (width <= ((float) c3)) {
            switch (AnonymousClass2.a[this.C.ordinal()]) {
                case 2:
                    f3 = -a2.left;
                    break;
                case 3:
                    f3 = (((float) c3) - width) - a2.left;
                    break;
                default:
                    f3 = ((((float) c3) - width) / 2.0f) - a2.left;
                    break;
            }
            this.A = 2;
        } else if (a2.left > 0.0f) {
            this.A = 0;
            f3 = -a2.left;
        } else if (a2.right < ((float) c3)) {
            f3 = ((float) c3) - a2.right;
            this.A = 1;
        } else {
            this.A = -1;
        }
        this.n.postTranslate(f3, f2);
        return true;
    }

    private RectF a(Matrix matrix) {
        ImageView c2 = c();
        if (c2 != null) {
            Drawable drawable = c2.getDrawable();
            if (drawable != null) {
                this.o.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
                matrix.mapRect(this.o);
                return this.o;
            }
        }
        return null;
    }

    public Bitmap n() {
        ImageView c2 = c();
        if (c2 == null) {
            return null;
        }
        return c2.getDrawingCache();
    }

    public void a(int i2) {
        if (i2 < 0) {
            i2 = 200;
        }
        this.b = i2;
    }

    private float a(Matrix matrix, int i2) {
        matrix.getValues(this.p);
        return this.p[i2];
    }

    private void t() {
        this.n.reset();
        b(m());
        s();
    }

    /* access modifiers changed from: private */
    public void b(Matrix matrix) {
        ImageView c2 = c();
        if (c2 != null) {
            r();
            c2.setImageMatrix(matrix);
            if (this.f444q != null) {
                RectF a2 = a(matrix);
                if (a2 != null) {
                    this.f444q.a(a2);
                }
            }
        }
    }

    private void a(Drawable drawable) {
        ImageView c2 = c();
        if (c2 != null && drawable != null) {
            float c3 = (float) c(c2);
            float d2 = (float) d(c2);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.l.reset();
            float f2 = c3 / ((float) intrinsicWidth);
            float f3 = d2 / ((float) intrinsicHeight);
            if (this.C != ScaleType.CENTER) {
                if (this.C != ScaleType.CENTER_CROP) {
                    if (this.C != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, c3, d2);
                        switch (AnonymousClass2.a[this.C.ordinal()]) {
                            case 2:
                                this.l.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.l.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.l.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.l.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                        }
                    } else {
                        float min = Math.min(1.0f, Math.min(f2, f3));
                        this.l.postScale(min, min);
                        this.l.postTranslate((c3 - (((float) intrinsicWidth) * min)) / 2.0f, (d2 - (((float) intrinsicHeight) * min)) / 2.0f);
                    }
                } else {
                    float max = Math.max(f2, f3);
                    this.l.postScale(max, max);
                    this.l.postTranslate((c3 - (((float) intrinsicWidth) * max)) / 2.0f, (d2 - (((float) intrinsicHeight) * max)) / 2.0f);
                }
            } else {
                this.l.postTranslate((c3 - ((float) intrinsicWidth)) / 2.0f, (d2 - ((float) intrinsicHeight)) / 2.0f);
            }
            t();
        }
    }

    private int c(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int d(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }
}
