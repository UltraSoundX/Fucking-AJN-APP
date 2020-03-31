package com.e23.ajn.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.e23.ajn.R;

public class CirleImageView extends AppCompatImageView {
    private int a = 0;
    private Context b;
    private int c = -1;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;

    public CirleImageView(Context context) {
        super(context);
        this.b = context;
    }

    public CirleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        setCustomAttributes(attributeSet);
    }

    public CirleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        setCustomAttributes(attributeSet);
    }

    private void setCustomAttributes(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(attributeSet, R.styleable.roundedimageview);
        this.a = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.d = obtainStyledAttributes.getColor(2, this.c);
        this.e = obtainStyledAttributes.getColor(1, this.c);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        Drawable drawable = getDrawable();
        if (drawable != null && getWidth() != 0 && getHeight() != 0) {
            measure(0, 0);
            if (drawable.getClass() != NinePatchDrawable.class) {
                Bitmap copy = ((BitmapDrawable) drawable).getBitmap().copy(Config.ARGB_8888, true);
                if (this.f == 0) {
                    this.f = getWidth();
                }
                if (this.g == 0) {
                    this.g = getHeight();
                }
                if (this.e != this.c && this.d != this.c) {
                    i = ((this.f < this.g ? this.f : this.g) / 2) - (this.a * 2);
                    a(canvas, (this.a / 2) + i, this.e);
                    a(canvas, this.a + i + (this.a / 2), this.d);
                } else if (this.e != this.c && this.d == this.c) {
                    i = ((this.f < this.g ? this.f : this.g) / 2) - this.a;
                    a(canvas, (this.a / 2) + i, this.e);
                } else if (this.e != this.c || this.d == this.c) {
                    i = (this.f < this.g ? this.f : this.g) / 2;
                } else {
                    i = ((this.f < this.g ? this.f : this.g) / 2) - this.a;
                    a(canvas, (this.a / 2) + i, this.d);
                }
                canvas.drawBitmap(a(copy, i), (float) ((this.f / 2) - i), (float) ((this.g / 2) - i), null);
            }
        }
    }

    public Bitmap a(Bitmap bitmap, int i) {
        int i2 = i * 2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height > width) {
            bitmap = Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width);
        } else if (height < width) {
            bitmap = Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height);
        }
        if (!(bitmap.getWidth() == i2 && bitmap.getHeight() == i2)) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i2, i2, true);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    private void a(Canvas canvas, int i, int i2) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(i2);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth((float) this.a);
        canvas.drawCircle((float) (this.f / 2), (float) (this.g / 2), (float) i, paint);
    }
}
