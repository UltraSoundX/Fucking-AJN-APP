package com.d.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/* compiled from: RotateImageView */
public class k extends ImageView {
    private float a;

    public k(Context context) {
        super(context);
    }

    public void setRotation(float f) {
        this.a = f;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.rotate(this.a, (float) (getWidth() / 2), (float) (getHeight() / 2));
        super.onDraw(canvas);
    }
}
