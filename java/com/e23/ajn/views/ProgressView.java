package com.e23.ajn.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {
    int a;
    Paint b;
    Paint c;
    int d;
    int e;
    boolean f;
    int g;
    int h;
    ValueAnimator i;

    public ProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = -49023;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = false;
        this.g = 0;
        this.h = 0;
        a();
    }

    private void a() {
        setLayerType(1, null);
        this.b = new Paint();
        this.b.setColor(this.a);
        this.c = new Paint();
        this.c.setColor(this.a);
        this.c.setMaskFilter(new BlurMaskFilter(10.0f, Blur.SOLID));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.g = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        this.h = getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d <= 100 && this.f) {
            this.f = false;
            setAlpha(1.0f);
        }
        canvas.drawRect(0.0f, 0.0f, (float) (((double) this.g) * (((double) this.d) / 100.0d)), (float) this.h, this.b);
        canvas.drawCircle(((float) (((double) this.g) * (((double) this.d) / 100.0d))) - ((float) (this.h / 2)), (float) (this.h / 2), (float) this.h, this.c);
        if (this.d >= 100) {
            b();
        }
    }

    private void b() {
        postDelayed(new Runnable() {
            public void run() {
                ViewCompat.animate(ProgressView.this).alpha(0.0f);
                ProgressView.this.f = true;
                ProgressView.this.d = 0;
            }
        }, 100);
    }

    public int getDefaultColor() {
        return this.a;
    }

    public void setDefaultColor(int i2) {
        this.a = i2;
    }

    public void setProgress(int i2) {
        this.e = i2;
        if (this.i != null && this.i.isRunning()) {
            this.i.cancel();
        }
        this.i = ValueAnimator.ofInt(new int[]{this.d, this.e});
        this.i.setDuration(300);
        this.i.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProgressView.this.d = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ProgressView.this.invalidate();
            }
        });
        this.i.start();
    }
}
