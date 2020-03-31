package com.e23.ajn.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.e23.ajn.R;

public class RoundImageView extends AppCompatImageView {
    private Paint a;
    private int b;
    private int c;

    public RoundImageView(Context context) {
        super(context);
        this.b = 0;
        this.c = a(4.0f);
        a();
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.c = a(4.0f);
        a(context, attributeSet, i);
        a();
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        int i2 = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView, i, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            i2 = obtainStyledAttributes.getInt(0, 0);
        }
        this.b = i2;
        this.c = obtainStyledAttributes.hasValue(1) ? obtainStyledAttributes.getDimensionPixelSize(1, this.c) : this.c;
        obtainStyledAttributes.recycle();
    }

    private void a() {
        this.a = new Paint(5);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.b == 1) {
            super.onMeasure(i, i2);
            int min = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(min, min);
            return;
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Matrix imageMatrix = getImageMatrix();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            if (imageMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
                drawable.draw(canvas);
                return;
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            if (VERSION.SDK_INT >= 16 && getCropToPadding()) {
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                canvas.clipRect(getPaddingLeft() + scrollX, getPaddingTop() + scrollY, ((scrollX + getRight()) - getLeft()) - getPaddingRight(), ((scrollY + getBottom()) - getTop()) - getPaddingBottom());
            }
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            if (this.b == 1) {
                this.a.setShader(new BitmapShader(a(drawable), TileMode.CLAMP, TileMode.CLAMP));
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) (getWidth() / 2), this.a);
            } else if (this.b == 2) {
                this.a.setShader(new BitmapShader(a(drawable), TileMode.CLAMP, TileMode.CLAMP));
                canvas.drawRoundRect(new RectF((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom())), (float) this.c, (float) this.c, this.a);
            } else {
                if (imageMatrix != null) {
                    canvas.concat(imageMatrix);
                }
                drawable.draw(canvas);
            }
            canvas.restoreToCount(saveCount);
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix imageMatrix = getImageMatrix();
        if (imageMatrix != null) {
            canvas.concat(imageMatrix);
        }
        drawable.draw(canvas);
        return createBitmap;
    }

    private int a(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
