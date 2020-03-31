package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View.MeasureSpec;
import android.widget.ImageView.ScaleType;

public class ZoomableImageView extends AppCompatImageView {
    /* access modifiers changed from: private */
    public float a = 3.0f;
    private float b = 1.0f;
    /* access modifiers changed from: private */
    public c c;
    private Matrix d;
    private float[] e = new float[9];
    private PointF f = new PointF();
    private float g = 1.0f;
    private int h;
    private int i;
    private float j;
    private float k;
    private ScaleGestureDetector l;
    private GestureDetector m;
    private float n;

    private class a extends SimpleOnGestureListener {
        private a() {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (ZoomableImageView.this.g()) {
                ZoomableImageView.this.a();
            } else {
                ZoomableImageView.this.a(motionEvent.getX(), motionEvent.getY(), ZoomableImageView.this.a);
            }
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            ZoomableImageView.this.performClick();
            return true;
        }
    }

    private class b extends SimpleOnScaleGestureListener {
        private b() {
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ZoomableImageView.this.c = c.ZOOM;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomableImageView.this.a(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), scaleGestureDetector.getScaleFactor());
            return true;
        }
    }

    private enum c {
        INIT,
        DRAG,
        ZOOM
    }

    public ZoomableImageView(Context context) {
        super(context);
        setUp(context);
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setUp(context);
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setUp(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.h = MeasureSpec.getSize(i2);
        this.i = MeasureSpec.getSize(i3);
        if (e()) {
            a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.l.onTouchEvent(motionEvent);
        this.m.onTouchEvent(motionEvent);
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        switch (motionEvent.getAction()) {
            case 0:
                this.f.set(pointF);
                this.c = c.DRAG;
                b();
                break;
            case 1:
                this.c = c.INIT;
                c();
                break;
            case 2:
                if (this.c == c.DRAG) {
                    if (a(pointF)) {
                        b();
                        this.f.set(pointF);
                        break;
                    } else {
                        c();
                        return false;
                    }
                }
                break;
            case 6:
                this.c = c.INIT;
                break;
        }
        setImageMatrix(this.d);
        invalidate();
        return true;
    }

    private void b() {
        getParent().requestDisallowInterceptTouchEvent(true);
    }

    private void c() {
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    private void setUp(Context context) {
        super.setClickable(false);
        this.d = new Matrix();
        this.c = c.INIT;
        this.l = new ScaleGestureDetector(context, new b());
        this.m = new GestureDetector(context, new a());
        setScaleType(ScaleType.MATRIX);
    }

    public void a() {
        float scaleForDrawable = getScaleForDrawable();
        this.d.setScale(scaleForDrawable, scaleForDrawable);
        float intrinsicHeight = (((float) this.i) - (((float) getDrawable().getIntrinsicHeight()) * scaleForDrawable)) / 2.0f;
        float intrinsicWidth = (((float) this.h) - (scaleForDrawable * ((float) getDrawable().getIntrinsicWidth()))) / 2.0f;
        this.d.postTranslate(intrinsicWidth, intrinsicHeight);
        this.j = ((float) this.h) - (intrinsicWidth * 2.0f);
        this.k = ((float) this.i) - (intrinsicHeight * 2.0f);
        setImageMatrix(this.d);
        this.g = 1.0f;
        this.c = c.INIT;
    }

    public float getMaxScale() {
        return this.a;
    }

    public void setMaxScale(float f2) {
        this.a = f2;
    }

    public float getMinScale() {
        return this.b;
    }

    public void setMinScale(float f2) {
        this.b = f2;
    }

    private boolean a(PointF pointF) {
        if (b(pointF)) {
            return false;
        }
        float c2 = c(pointF.x - this.f.x, (float) this.h, this.j * this.g);
        float c3 = c(pointF.y - this.f.y, (float) this.i, this.k * this.g);
        this.n = c2;
        this.d.postTranslate(c2, c3);
        d();
        return true;
    }

    private boolean b(PointF pointF) {
        Matrix matrix = new Matrix();
        matrix.set(this.d);
        float c2 = c(pointF.x - this.f.x, (float) this.h, this.j * this.g);
        matrix.postTranslate(c2, c(pointF.y - this.f.y, (float) this.i, this.k * this.g));
        matrix.getValues(this.e);
        return b(this.e[2], (float) this.h, this.j * this.g) + c2 == 0.0f;
    }

    /* access modifiers changed from: private */
    public void a(float f2, float f3, float f4) {
        float f5 = this.g;
        float f6 = f5 * f4;
        if (f6 > this.a) {
            this.g = this.a;
            f4 = this.a / f5;
        } else if (f6 < this.b) {
            this.g = this.b;
            f4 = this.b / f5;
        } else {
            this.g = f6;
        }
        if (f()) {
            this.d.postScale(f4, f4, ((float) this.h) / 2.0f, ((float) this.i) / 2.0f);
        } else {
            this.d.postScale(f4, f4, f2, f3);
        }
        d();
    }

    private void d() {
        this.d.getValues(this.e);
        float f2 = this.e[2];
        float f3 = this.e[5];
        float b2 = b(f2, (float) this.h, this.j * this.g);
        float b3 = b(f3, (float) this.i, this.k * this.g);
        if (this.n + b2 == 0.0f) {
            c();
        } else {
            b();
        }
        this.d.postTranslate(b2, b3);
    }

    private float b(float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 <= f3) {
            f6 = f3 - f4;
            f5 = 0.0f;
        } else {
            f5 = f3 - f4;
            f6 = 0.0f;
        }
        if (f2 < f5) {
            return f5 - f2;
        }
        if (f2 > f6) {
            return f6 - f2;
        }
        return 0.0f;
    }

    private float c(float f2, float f3, float f4) {
        if (f4 <= f3) {
            return 0.0f;
        }
        return f2;
    }

    private float getScaleForDrawable() {
        return Math.min(((float) this.h) / ((float) getDrawable().getIntrinsicWidth()), ((float) this.i) / ((float) getDrawable().getIntrinsicHeight()));
    }

    private boolean e() {
        return (getDrawable() == null || getDrawable().getIntrinsicWidth() == 0 || getDrawable().getIntrinsicHeight() == 0) ? false : true;
    }

    private boolean f() {
        return this.j * this.g <= ((float) this.h) || this.k * this.g <= ((float) this.i);
    }

    /* access modifiers changed from: private */
    public boolean g() {
        return this.g != 1.0f;
    }
}
