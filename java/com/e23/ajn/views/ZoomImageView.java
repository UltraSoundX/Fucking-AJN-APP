package com.e23.ajn.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;

public class ZoomImageView extends AppCompatImageView implements OnTouchListener, OnGlobalLayoutListener {
    /* access modifiers changed from: private */
    public float a;
    /* access modifiers changed from: private */
    public float b;
    /* access modifiers changed from: private */
    public float c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public c e;
    private final Matrix f;
    private final Matrix g;
    /* access modifiers changed from: private */
    public final Matrix h;
    private final RectF i;
    private final float[] j;
    /* access modifiers changed from: private */
    public d k;
    /* access modifiers changed from: private */
    public e l;
    /* access modifiers changed from: private */
    public OnLongClickListener m;
    private int n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f417q;
    /* access modifiers changed from: private */
    public b r;
    /* access modifiers changed from: private */
    public int s;
    private boolean t;
    private ScaleType u;

    /* renamed from: com.e23.ajn.views.ZoomImageView$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.FIT_START.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class a implements Runnable {
        private final float b;
        private final float c;
        private final float d;
        private final float e;

        public a(float f, float f2, float f3, float f4) {
            this.d = f2;
            this.b = f3;
            this.c = f4;
            if (f < f2) {
                this.e = 1.07f;
            } else {
                this.e = 0.93f;
            }
        }

        public void run() {
            ZoomImageView.this.h.postScale(this.e, this.e, this.b, this.c);
            ZoomImageView.this.b();
            float scale = ZoomImageView.this.getScale();
            if ((this.e <= 1.0f || scale >= this.d) && (this.e >= 1.0f || this.d >= scale)) {
                float f = this.d / scale;
                ZoomImageView.this.h.postScale(f, f, this.b, this.c);
                ZoomImageView.this.b();
                return;
            }
            ZoomImageView.this.a((View) ZoomImageView.this, (Runnable) this);
        }
    }

    private class b implements Runnable {
        private final f b;
        private int c;
        private int d;

        public b(Context context) {
            this.b = new f(context);
        }

        public void a() {
            this.b.a(true);
        }

        public void a(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = ZoomImageView.this.getDisplayRect();
            if (displayRect != null) {
                int round = Math.round(-displayRect.left);
                if (((float) i) < displayRect.width()) {
                    i5 = Math.round(displayRect.width() - ((float) i));
                    i6 = 0;
                } else {
                    i5 = round;
                    i6 = round;
                }
                int round2 = Math.round(-displayRect.top);
                if (((float) i2) < displayRect.height()) {
                    i7 = Math.round(displayRect.height() - ((float) i2));
                    i8 = 0;
                } else {
                    i7 = round2;
                    i8 = round2;
                }
                this.c = round;
                this.d = round2;
                if (round != i5 || round2 != i7) {
                    this.b.a(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            if (this.b.a()) {
                int b2 = this.b.b();
                int c2 = this.b.c();
                ZoomImageView.this.h.postTranslate((float) (this.c - b2), (float) (this.d - c2));
                ZoomImageView.this.setImageMatrix(ZoomImageView.this.getDisplayMatrix());
                this.c = b2;
                this.d = c2;
                ZoomImageView.this.a((View) ZoomImageView.this, (Runnable) this);
            }
        }
    }

    private class c extends SimpleOnGestureListener implements OnScaleGestureListener {
        private final ScaleGestureDetector b;
        private final GestureDetector c;
        private VelocityTracker d;
        private boolean e;
        private float f;
        private float g;
        private float h;
        private final float i;
        private final float j;

        public c(Context context) {
            this.b = new ScaleGestureDetector(context, this);
            this.c = new GestureDetector(context, this);
            this.c.setOnDoubleTapListener(this);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.j = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.i = (float) viewConfiguration.getScaledTouchSlop();
        }

        public boolean a() {
            return this.b.isInProgress();
        }

        public boolean a(MotionEvent motionEvent) {
            if (!this.c.onTouchEvent(motionEvent)) {
                this.b.onTouchEvent(motionEvent);
                int pointerCount = motionEvent.getPointerCount();
                float f2 = 0.0f;
                float f3 = 0.0f;
                for (int i2 = 0; i2 < pointerCount; i2++) {
                    f3 += motionEvent.getX(i2);
                    f2 += motionEvent.getY(i2);
                }
                float f4 = f3 / ((float) pointerCount);
                float f5 = f2 / ((float) pointerCount);
                if (((float) pointerCount) != this.h) {
                    this.e = false;
                    if (this.d != null) {
                        this.d.clear();
                    }
                    this.f = f4;
                    this.g = f5;
                }
                this.h = (float) pointerCount;
                switch (motionEvent.getAction()) {
                    case 0:
                        if (this.d == null) {
                            this.d = VelocityTracker.obtain();
                        } else {
                            this.d.clear();
                        }
                        this.d.addMovement(motionEvent);
                        this.f = f4;
                        this.g = f5;
                        this.e = false;
                        break;
                    case 1:
                        if (this.e) {
                            this.f = f4;
                            this.g = f5;
                            if (this.d != null) {
                                this.d.addMovement(motionEvent);
                                this.d.computeCurrentVelocity(1000);
                                float xVelocity = this.d.getXVelocity();
                                float yVelocity = this.d.getYVelocity();
                                if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.j && ZoomImageView.this.getDrawable() != null) {
                                    ZoomImageView.this.r = new b(ZoomImageView.this.getContext());
                                    ZoomImageView.this.r.a(ZoomImageView.this.getWidth(), ZoomImageView.this.getHeight(), (int) (-xVelocity), (int) (-yVelocity));
                                    ZoomImageView.this.post(ZoomImageView.this.r);
                                    break;
                                }
                            }
                        }
                        break;
                    case 2:
                        float f6 = f4 - this.f;
                        float f7 = f5 - this.g;
                        if (!this.e) {
                            this.e = Math.sqrt((double) ((f6 * f6) + (f7 * f7))) >= ((double) this.i);
                        }
                        if (this.e) {
                            if (ZoomImageView.this.getDrawable() != null) {
                                ZoomImageView.this.h.postTranslate(f6, f7);
                                ZoomImageView.this.b();
                                if (ZoomImageView.this.d && !ZoomImageView.this.e.a() && ((ZoomImageView.this.s == 2 || ((ZoomImageView.this.s == 0 && f6 >= 1.0f) || (ZoomImageView.this.s == 1 && f6 <= -1.0f))) && ZoomImageView.this.getParent() != null)) {
                                    ZoomImageView.this.getParent().requestDisallowInterceptTouchEvent(false);
                                }
                            }
                            this.f = f4;
                            this.g = f5;
                            if (this.d != null) {
                                this.d.addMovement(motionEvent);
                                break;
                            }
                        }
                        break;
                    case 3:
                        this.h = 0.0f;
                        if (this.d != null) {
                            this.d.recycle();
                            this.d = null;
                            break;
                        }
                        break;
                }
            }
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scale = ZoomImageView.this.getScale();
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (ZoomImageView.this.getDrawable() != null && ((scale < ZoomImageView.this.c || scaleFactor <= 1.0f) && (((double) scale) > 0.75d || scaleFactor >= 1.0f))) {
                ZoomImageView.this.h.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                ZoomImageView.this.b();
            }
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            try {
                float scale = ZoomImageView.this.getScale();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (scale < ZoomImageView.this.b) {
                    ZoomImageView.this.post(new a(scale, ZoomImageView.this.b, x, y));
                } else if (scale < ZoomImageView.this.b || scale >= ZoomImageView.this.c) {
                    ZoomImageView.this.post(new a(scale, ZoomImageView.this.a, x, y));
                } else {
                    ZoomImageView.this.post(new a(scale, ZoomImageView.this.c, x, y));
                }
            } catch (Exception e2) {
            }
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (ZoomImageView.this.k != null) {
                RectF displayRect = ZoomImageView.this.getDisplayRect();
                if (displayRect != null) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (displayRect.contains(x, y)) {
                        ZoomImageView.this.k.a(ZoomImageView.this, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                        return true;
                    }
                }
            }
            if (ZoomImageView.this.l != null) {
                ZoomImageView.this.l.a(ZoomImageView.this, motionEvent.getX(), motionEvent.getY());
            }
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (ZoomImageView.this.m != null) {
                ZoomImageView.this.m.onLongClick(ZoomImageView.this);
            }
        }
    }

    public interface d {
        void a(View view, float f, float f2);
    }

    public interface e {
        void a(View view, float f, float f2);
    }

    @TargetApi(9)
    private class f {
        private boolean b;
        private Object c;

        public f(Context context) {
            if (VERSION.SDK_INT < 9) {
                this.b = true;
                this.c = new Scroller(context);
                return;
            }
            this.b = false;
            this.c = new OverScroller(context);
        }

        public boolean a() {
            if (this.b) {
                return ((Scroller) this.c).computeScrollOffset();
            }
            return ((OverScroller) this.c).computeScrollOffset();
        }

        public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            if (this.b) {
                ((Scroller) this.c).fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                ((OverScroller) this.c).fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        }

        public void a(boolean z) {
            if (this.b) {
                ((Scroller) this.c).forceFinished(z);
            } else {
                ((OverScroller) this.c).forceFinished(z);
            }
        }

        public int b() {
            return this.b ? ((Scroller) this.c).getCurrX() : ((OverScroller) this.c).getCurrX();
        }

        public int c() {
            return this.b ? ((Scroller) this.c).getCurrY() : ((OverScroller) this.c).getCurrY();
        }
    }

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 1.0f;
        this.b = 1.75f;
        this.c = 3.0f;
        this.d = true;
        this.f = new Matrix();
        this.g = new Matrix();
        this.h = new Matrix();
        this.i = new RectF();
        this.j = new float[9];
        this.s = 2;
        this.u = ScaleType.FIT_CENTER;
        super.setScaleType(ScaleType.MATRIX);
        setOnTouchListener(this);
        this.e = new c(context);
        setIsZoomEnabled(true);
    }

    public final RectF getDisplayRect() {
        c();
        return a(getDisplayMatrix());
    }

    public float getMinScale() {
        return this.a;
    }

    public void setMinScale(float f2) {
        a(f2, this.b, this.c);
        this.a = f2;
    }

    public float getMidScale() {
        return this.b;
    }

    public void setMidScale(float f2) {
        a(this.a, f2, this.c);
        this.b = f2;
    }

    public float getMaxScale() {
        return this.c;
    }

    public void setMaxScale(float f2) {
        a(this.a, this.b, f2);
        this.c = f2;
    }

    public final float getScale() {
        this.h.getValues(this.j);
        return this.j[0];
    }

    public final ScaleType getScaleType() {
        return this.u;
    }

    public final void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.MATRIX) {
            throw new IllegalArgumentException(scaleType.name() + " is not supported in ZoomImageView");
        } else if (scaleType != this.u) {
            this.u = scaleType;
            a();
        }
    }

    public final void setIsZoomEnabled(boolean z) {
        this.t = z;
        a();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.d = z;
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        a();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        a();
    }

    public void setImageResource(int i2) {
        super.setImageResource(i2);
        a();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        a();
    }

    public final void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.m = onLongClickListener;
    }

    public final void setOnPhotoTapListener(d dVar) {
        this.k = dVar;
    }

    public final void setOnViewTapListener(e eVar) {
        this.l = eVar;
    }

    public final void onGlobalLayout() {
        if (this.t) {
            int top = getTop();
            int right = getRight();
            int bottom = getBottom();
            int left = getLeft();
            if (top != this.n || bottom != this.p || left != this.f417q || right != this.o) {
                a(getDrawable());
                this.n = top;
                this.o = right;
                this.p = bottom;
                this.f417q = left;
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (!this.t) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (view.getParent() != null) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (this.r != null) {
                    this.r.a();
                    this.r = null;
                    break;
                }
                break;
            case 1:
            case 3:
                if (getScale() < this.a) {
                    RectF displayRect = getDisplayRect();
                    if (displayRect != null) {
                        view.post(new a(getScale(), this.a, displayRect.centerX(), displayRect.centerY()));
                        z = true;
                        break;
                    }
                }
                break;
        }
        if (this.e == null || !this.e.a(motionEvent)) {
            return z;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public Matrix getDisplayMatrix() {
        this.g.set(this.f);
        this.g.postConcat(this.h);
        return this.g;
    }

    private final void a() {
        if (this.t) {
            super.setScaleType(ScaleType.MATRIX);
            a(getDrawable());
            return;
        }
        d();
    }

    /* access modifiers changed from: private */
    public void b() {
        c();
        setImageMatrix(getDisplayMatrix());
    }

    private void c() {
        float f2;
        float f3 = 0.0f;
        RectF a2 = a(getDisplayMatrix());
        if (a2 != null) {
            float height = a2.height();
            float width = a2.width();
            int height2 = getHeight();
            if (height <= ((float) height2)) {
                switch (AnonymousClass1.a[this.u.ordinal()]) {
                    case 1:
                        f2 = -a2.top;
                        break;
                    case 2:
                        f2 = (((float) height2) - height) - a2.top;
                        break;
                    default:
                        f2 = ((((float) height2) - height) / 2.0f) - a2.top;
                        break;
                }
            } else if (a2.top > 0.0f) {
                f2 = -a2.top;
            } else if (a2.bottom < ((float) height2)) {
                f2 = ((float) height2) - a2.bottom;
            } else {
                f2 = 0.0f;
            }
            int width2 = getWidth();
            if (width <= ((float) width2)) {
                switch (AnonymousClass1.a[this.u.ordinal()]) {
                    case 1:
                        f3 = -a2.left;
                        break;
                    case 2:
                        f3 = (((float) width2) - width) - a2.left;
                        break;
                    default:
                        f3 = ((((float) width2) - width) / 2.0f) - a2.left;
                        break;
                }
                this.s = 2;
            } else if (a2.left > 0.0f) {
                this.s = 0;
                f3 = -a2.left;
            } else if (a2.right < ((float) width2)) {
                f3 = ((float) width2) - a2.right;
                this.s = 1;
            } else {
                this.s = -1;
            }
            this.h.postTranslate(f3, f2);
        }
    }

    private RectF a(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        this.i.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.i);
        return this.i;
    }

    private void d() {
        this.h.reset();
        setImageMatrix(getDisplayMatrix());
        c();
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            float width = (float) getWidth();
            float height = (float) getHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.f.reset();
            float f2 = width / ((float) intrinsicWidth);
            float f3 = height / ((float) intrinsicHeight);
            if (this.u != ScaleType.CENTER) {
                if (this.u != ScaleType.CENTER_CROP) {
                    if (this.u != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
                        switch (AnonymousClass1.a[this.u.ordinal()]) {
                            case 1:
                                this.f.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 2:
                                this.f.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 3:
                                this.f.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 4:
                                this.f.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                        }
                    } else {
                        float min = Math.min(1.0f, Math.min(f2, f3));
                        this.f.postScale(min, min);
                        this.f.postTranslate((width - (((float) intrinsicWidth) * min)) / 2.0f, (height - (((float) intrinsicHeight) * min)) / 2.0f);
                    }
                } else {
                    float max = Math.max(f2, f3);
                    this.f.postScale(max, max);
                    this.f.postTranslate((width - (((float) intrinsicWidth) * max)) / 2.0f, (height - (((float) intrinsicHeight) * max)) / 2.0f);
                }
            } else {
                this.f.postTranslate((width - ((float) intrinsicWidth)) / 2.0f, (height - ((float) intrinsicHeight)) / 2.0f);
            }
            d();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    private void a(float f2, float f3, float f4) {
        if (f2 >= f3) {
            throw new IllegalArgumentException("MinZoom should be less than MidZoom");
        } else if (f3 >= f4) {
            throw new IllegalArgumentException("MidZoom should be less than MaxZoom");
        }
    }
}
