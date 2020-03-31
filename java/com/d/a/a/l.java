package com.d.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* compiled from: XView */
public class l extends View {
    private float a;

    public l(Context context) {
        super(context);
    }

    public void setRatio(float f) {
        this.a = f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-6250336);
        float f = (float) height;
        canvas.drawRect((float) width, 0.0f, (float) getWidth(), f, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(3.0f * this.a);
        paint2.setColor(-1);
        float f2 = 8.0f * this.a;
        canvas.drawLine(((float) width) + f2, f2, ((float) getWidth()) - f2, ((float) width) - f2, paint2);
        canvas.drawLine(((float) width) + f2, ((float) width) - f2, ((float) getWidth()) - f2, f2, paint2);
    }
}
