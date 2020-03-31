package com.github.chrisbanes.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PhotoView extends ImageView {
    private i a;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public PhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        this.a = new i(this);
        super.setScaleType(ScaleType.MATRIX);
    }

    public i getAttacher() {
        return this.a;
    }

    public ScaleType getScaleType() {
        return this.a.f();
    }

    public Matrix getImageMatrix() {
        return this.a.h();
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.a.a(onLongClickListener);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.a.a(onClickListener);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.a != null) {
            this.a.a(scaleType);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.a != null) {
            this.a.g();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.a != null) {
            this.a.g();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.a != null) {
            this.a.g();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            this.a.g();
        }
        return frame;
    }

    public void setRotationTo(float f) {
        this.a.a(f);
    }

    public void setRotationBy(float f) {
        this.a.b(f);
    }

    public RectF getDisplayRect() {
        return this.a.a();
    }

    public float getMinimumScale() {
        return this.a.b();
    }

    public float getMediumScale() {
        return this.a.c();
    }

    public float getMaximumScale() {
        return this.a.d();
    }

    public float getScale() {
        return this.a.e();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.a.a(z);
    }

    public void setMinimumScale(float f) {
        this.a.c(f);
    }

    public void setMediumScale(float f) {
        this.a.d(f);
    }

    public void setMaximumScale(float f) {
        this.a.e(f);
    }

    public void setOnMatrixChangeListener(d dVar) {
        this.a.a(dVar);
    }

    public void setOnPhotoTapListener(f fVar) {
        this.a.a(fVar);
    }

    public void setOnOutsidePhotoTapListener(e eVar) {
        this.a.a(eVar);
    }

    public void setScale(float f) {
        this.a.f(f);
    }

    public void setZoomable(boolean z) {
        this.a.b(z);
    }

    public void setZoomTransitionDuration(int i) {
        this.a.a(i);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.a.a(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(g gVar) {
        this.a.a(gVar);
    }

    public void setOnSingleFlingListener(h hVar) {
        this.a.a(hVar);
    }
}
