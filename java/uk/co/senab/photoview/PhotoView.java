package uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import uk.co.senab.photoview.d.C0087d;
import uk.co.senab.photoview.d.c;
import uk.co.senab.photoview.d.e;
import uk.co.senab.photoview.d.f;

public class PhotoView extends ImageView implements c {
    private d a;
    private ScaleType b;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ScaleType.MATRIX);
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.a == null || this.a.c() == null) {
            this.a = new d(this);
        }
        if (this.b != null) {
            setScaleType(this.b);
            this.b = null;
        }
    }

    public void setPhotoViewRotation(float f) {
        this.a.a(f);
    }

    public void setRotationTo(float f) {
        this.a.a(f);
    }

    public void setRotationBy(float f) {
        this.a.b(f);
    }

    public RectF getDisplayRect() {
        return this.a.b();
    }

    public Matrix getDisplayMatrix() {
        return this.a.l();
    }

    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    public float getMinimumScale() {
        return this.a.d();
    }

    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    public float getMediumScale() {
        return this.a.e();
    }

    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    public float getMaximumScale() {
        return this.a.f();
    }

    public float getScale() {
        return this.a.g();
    }

    public ScaleType getScaleType() {
        return this.a.h();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.a.a(z);
    }

    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    public void setMinimumScale(float f) {
        this.a.c(f);
    }

    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    public void setMediumScale(float f) {
        this.a.d(f);
    }

    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    public void setMaximumScale(float f) {
        this.a.e(f);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.a != null) {
            this.a.k();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.a != null) {
            this.a.k();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.a != null) {
            this.a.k();
        }
    }

    public void setOnMatrixChangeListener(c cVar) {
        this.a.a(cVar);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.a.a(onLongClickListener);
    }

    public void setOnPhotoTapListener(C0087d dVar) {
        this.a.a(dVar);
    }

    public C0087d getOnPhotoTapListener() {
        return this.a.i();
    }

    public void setOnViewTapListener(f fVar) {
        this.a.a(fVar);
    }

    public f getOnViewTapListener() {
        return this.a.j();
    }

    public void setScale(float f) {
        this.a.f(f);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.a != null) {
            this.a.a(scaleType);
        } else {
            this.b = scaleType;
        }
    }

    public void setZoomable(boolean z) {
        this.a.b(z);
    }

    public Bitmap getVisibleRectangleBitmap() {
        return this.a.n();
    }

    public void setZoomTransitionDuration(int i) {
        this.a.a(i);
    }

    public c getIPhotoViewImplementation() {
        return this.a;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.a.a(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(e eVar) {
        this.a.a(eVar);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.a.a();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        a();
        super.onAttachedToWindow();
    }
}
