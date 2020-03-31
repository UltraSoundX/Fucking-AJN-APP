package com.tendyron.liveness.motion.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tendyron.liveness.R;

public class CircleTimeView extends View implements b {
    private static final int a = 7;
    private static final int b = 10;
    private static final int c = 20;
    private static final int d = Color.argb(ErrorCode.DECOUPLE_INCURUPDATE_FAIL, 74, 138, 255);
    private static final int e = Color.argb(255, 255, 255, 255);
    private final float f = getResources().getDisplayMetrics().density;
    private int g = 10;
    private int h;
    private int i;
    private float j = 7.0f;
    private float k = 20.0f;
    private String l = "";
    private Paint m;
    private Paint n;
    private RectF o = new RectF();

    public CircleTimeView(Context context) {
        super(context);
        a(null, 0);
    }

    public CircleTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public CircleTimeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(attributeSet, i2);
    }

    private void a(AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircleTimeView, i2, 0);
        a(obtainStyledAttributes);
        c();
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
    }

    private void a(TypedArray typedArray) {
        this.j = (float) ((int) (((float) typedArray.getInt(R.styleable.CircleTimeView_circle_width, 7)) * this.f));
        this.g = typedArray.getInt(R.styleable.CircleTimeView_circle_max_time, 10);
        this.k = (float) ((int) (((float) typedArray.getInt(R.styleable.CircleTimeView_circle_text_size, 20)) * this.f));
        String string = typedArray.getString(R.styleable.CircleTimeView_circle_color);
        if (string != null) {
            try {
                this.h = Color.parseColor(string);
            } catch (IllegalArgumentException e2) {
                this.h = d;
            }
        }
        String string2 = typedArray.getString(R.styleable.CircleTimeView_circle_text_color);
        if (string2 != null) {
            try {
                this.i = Color.parseColor(string2);
            } catch (IllegalArgumentException e3) {
                this.i = e;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d();
        canvas.drawCircle(((float) getWidth()) / 2.0f, (float) (getHeight() / 2), ((float) getWidth()) / 2.0f, this.n);
        Rect rect = new Rect();
        this.m.getTextBounds(this.l, 0, this.l.length(), rect);
        canvas.drawText(this.l, (((float) getWidth()) / 2.0f) - (((float) rect.width()) / 1.5f), (float) ((rect.height() / 2) + (getHeight() / 2)), this.m);
    }

    private void c() {
        this.n = new Paint();
        this.n.setAntiAlias(true);
        this.n.setColor(this.h);
        this.n.setStrokeWidth(this.j);
        this.n.setStyle(Style.FILL);
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setColor(this.i);
        this.m.setStyle(Style.FILL);
        this.m.setTextSize(this.k);
    }

    private void d() {
        this.o.top = this.j;
        this.o.left = this.j;
        this.o.bottom = ((float) getHeight()) - this.j;
        this.o.right = ((float) getWidth()) - this.j;
    }

    public void setTime(int i2) {
        this.g = i2;
    }

    public void setProgress(float f2) {
        this.l = String.valueOf(this.g - ((int) f2));
        invalidate();
    }

    public void a() {
        setVisibility(8);
    }

    public void b() {
        setVisibility(0);
    }

    public int getMaxTime() {
        return this.g;
    }
}
