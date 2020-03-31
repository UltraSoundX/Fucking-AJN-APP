package com.d.a.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* compiled from: IndicatorView */
public class f extends View {
    private int a;
    private int b;

    public f(Context context) {
        super(context);
    }

    public void setScreenCount(int i) {
        this.a = i;
    }

    public void a(int i, int i2) {
        if (i != this.b) {
            this.b = i;
            postInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.a <= 1) {
            setVisibility(8);
            return;
        }
        float height = (float) getHeight();
        float f = (6.0f * height) / 52.0f;
        float f2 = (14.0f * height) / 52.0f;
        float width = (((float) getWidth()) - (((f * 2.0f) * ((float) this.a)) + (((float) (this.a - 1)) * f2))) / 2.0f;
        float f3 = height / 2.0f;
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        for (int i = 0; i < this.a; i++) {
            if (i == this.b) {
                paint.setColor(-10653280);
            } else {
                paint.setColor(-5262921);
            }
            canvas.drawCircle((((f * 2.0f) + f2) * ((float) i)) + width, f3, f, paint);
        }
    }
}
