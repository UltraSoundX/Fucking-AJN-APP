package com.e23.ajn.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircularImage extends MaskedImage {
    public CircularImage(Context context) {
        super(context);
    }

    public CircularImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public Bitmap a() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        new Canvas(createBitmap).drawOval(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), new Paint(1));
        return createBitmap;
    }
}
