package android.support.v7.b.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/* compiled from: DrawerArrowDrawable */
public class d extends Drawable {
    private static final float b = ((float) Math.toRadians(45.0d));
    private final Paint a;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private final Path h;
    private final int i;
    private boolean j;
    private float k;
    private float l;
    private int m;

    public void a(boolean z) {
        if (this.j != z) {
            this.j = z;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        boolean z;
        float f2;
        float f3;
        Rect bounds = getBounds();
        switch (this.m) {
            case 0:
                z = false;
                break;
            case 1:
                z = true;
                break;
            case 3:
                if (DrawableCompat.getLayoutDirection(this) != 0) {
                    z = false;
                    break;
                } else {
                    z = true;
                    break;
                }
            default:
                if (DrawableCompat.getLayoutDirection(this) != 1) {
                    z = false;
                    break;
                } else {
                    z = true;
                    break;
                }
        }
        float a2 = a(this.d, (float) Math.sqrt((double) (this.c * this.c * 2.0f)), this.k);
        float a3 = a(this.d, this.e, this.k);
        float round = (float) Math.round(a(0.0f, this.l, this.k));
        float a4 = a(0.0f, b, this.k);
        if (z) {
            f2 = 0.0f;
        } else {
            f2 = -180.0f;
        }
        if (z) {
            f3 = 180.0f;
        } else {
            f3 = 0.0f;
        }
        float a5 = a(f2, f3, this.k);
        float round2 = (float) Math.round(((double) a2) * Math.cos((double) a4));
        float round3 = (float) Math.round(((double) a2) * Math.sin((double) a4));
        this.h.rewind();
        float a6 = a(this.f + this.a.getStrokeWidth(), -this.l, this.k);
        float f4 = (-a3) / 2.0f;
        this.h.moveTo(f4 + round, 0.0f);
        this.h.rLineTo(a3 - (round * 2.0f), 0.0f);
        this.h.moveTo(f4, a6);
        this.h.rLineTo(round2, round3);
        this.h.moveTo(f4, -a6);
        this.h.rLineTo(round2, -round3);
        this.h.close();
        canvas.save();
        float strokeWidth = this.a.getStrokeWidth();
        canvas.translate((float) bounds.centerX(), (strokeWidth * 1.5f) + this.f + ((float) ((((int) ((((float) bounds.height()) - (3.0f * strokeWidth)) - (this.f * 2.0f))) / 4) * 2)));
        if (this.g) {
            canvas.rotate(((float) (z ^ this.j ? -1 : 1)) * a5);
        } else if (z) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.h, this.a);
        canvas.restore();
    }

    public void setAlpha(int i2) {
        if (i2 != this.a.getAlpha()) {
            this.a.setAlpha(i2);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.i;
    }

    public int getIntrinsicWidth() {
        return this.i;
    }

    public int getOpacity() {
        return -3;
    }

    public void a(float f2) {
        if (this.k != f2) {
            this.k = f2;
            invalidateSelf();
        }
    }

    private static float a(float f2, float f3, float f4) {
        return ((f3 - f2) * f4) + f2;
    }
}
