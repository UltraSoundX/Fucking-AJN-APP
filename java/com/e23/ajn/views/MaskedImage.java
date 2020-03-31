package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public abstract class MaskedImage extends AppCompatImageView {
    private static final Xfermode a = new PorterDuffXfermode(Mode.DST_IN);
    private Bitmap b;
    private Paint c;

    public abstract Bitmap a();

    public MaskedImage(Context context) {
        super(context);
    }

    public MaskedImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaskedImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            try {
                if (this.c == null) {
                    this.c = new Paint();
                    this.c.setFilterBitmap(false);
                    this.c.setXfermode(a);
                }
                int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), null, 31);
                drawable.setBounds(0, 0, getWidth(), getHeight());
                drawable.draw(canvas);
                if (this.b == null || this.b.isRecycled()) {
                    this.b = a();
                }
                canvas.drawBitmap(this.b, 0.0f, 0.0f, this.c);
                canvas.restoreToCount(saveLayer);
            } catch (Exception e) {
                new StringBuilder().append("Attempting to draw with recycled bitmap. View ID = ");
            }
        }
    }
}
