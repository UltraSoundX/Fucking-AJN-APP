package android.support.design.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.TintAwareDrawable;

public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable {
    private final Paint a;
    private final Matrix[] b;
    private final Matrix[] c;
    private final c[] d;
    private final Matrix e;
    private final Path f;
    private final PointF g;
    private final c h;
    private final Region i;
    private final Region j;
    private final float[] k;
    private final float[] l;
    private d m;
    private boolean n;
    private boolean o;
    private float p;

    /* renamed from: q reason: collision with root package name */
    private int f320q;
    private int r;
    private int s;
    private int t;
    private float u;
    private float v;
    private Style w;
    private PorterDuffColorFilter x;
    private Mode y;
    private ColorStateList z;

    public MaterialShapeDrawable() {
        this(null);
    }

    public MaterialShapeDrawable(d dVar) {
        this.a = new Paint();
        this.b = new Matrix[4];
        this.c = new Matrix[4];
        this.d = new c[4];
        this.e = new Matrix();
        this.f = new Path();
        this.g = new PointF();
        this.h = new c();
        this.i = new Region();
        this.j = new Region();
        this.k = new float[2];
        this.l = new float[2];
        this.m = null;
        this.n = false;
        this.o = false;
        this.p = 1.0f;
        this.f320q = -16777216;
        this.r = 5;
        this.s = 10;
        this.t = 255;
        this.u = 1.0f;
        this.v = 0.0f;
        this.w = Style.FILL_AND_STROKE;
        this.y = Mode.SRC_IN;
        this.z = null;
        this.m = dVar;
        for (int i2 = 0; i2 < 4; i2++) {
            this.b[i2] = new Matrix();
            this.c[i2] = new Matrix();
            this.d[i2] = new c();
        }
    }

    private static int a(int i2, int i3) {
        return (((i3 >>> 7) + i3) * i2) >>> 8;
    }

    public ColorStateList a() {
        return this.z;
    }

    public void setTintList(ColorStateList colorStateList) {
        this.z = colorStateList;
        c();
        invalidateSelf();
    }

    public void setTintMode(Mode mode) {
        this.y = mode;
        c();
        invalidateSelf();
    }

    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i2) {
        this.t = i2;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public Region getTransparentRegion() {
        Rect bounds = getBounds();
        this.i.set(bounds);
        b(bounds.width(), bounds.height(), this.f);
        this.j.setPath(this.f, this.i);
        this.i.op(this.j, Op.DIFFERENCE);
        return this.i;
    }

    public void a(boolean z2) {
        this.n = z2;
        invalidateSelf();
    }

    public float b() {
        return this.p;
    }

    public void a(float f2) {
        this.p = f2;
        invalidateSelf();
    }

    public void a(Style style) {
        this.w = style;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        this.a.setColorFilter(this.x);
        int alpha = this.a.getAlpha();
        this.a.setAlpha(a(alpha, this.t));
        this.a.setStrokeWidth(this.v);
        this.a.setStyle(this.w);
        if (this.r > 0 && this.n) {
            this.a.setShadowLayer((float) this.s, 0.0f, (float) this.r, this.f320q);
        }
        if (this.m != null) {
            b(canvas.getWidth(), canvas.getHeight(), this.f);
            canvas.drawPath(this.f, this.a);
        } else {
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.a);
        }
        this.a.setAlpha(alpha);
    }

    public void a(int i2, int i3, Path path) {
        path.rewind();
        if (this.m != null) {
            for (int i4 = 0; i4 < 4; i4++) {
                a(i4, i2, i3);
                b(i4, i2, i3);
            }
            for (int i5 = 0; i5 < 4; i5++) {
                a(i5, path);
                b(i5, path);
            }
            path.close();
        }
    }

    private void a(int i2, int i3, int i4) {
        a(i2, i3, i4, this.g);
        a(i2).a(c(i2, i3, i4), this.p, this.d[i2]);
        float d2 = d(((i2 - 1) + 4) % 4, i3, i4) + 1.5707964f;
        this.b[i2].reset();
        this.b[i2].setTranslate(this.g.x, this.g.y);
        this.b[i2].preRotate((float) Math.toDegrees((double) d2));
    }

    private void b(int i2, int i3, int i4) {
        this.k[0] = this.d[i2].c;
        this.k[1] = this.d[i2].d;
        this.b[i2].mapPoints(this.k);
        float d2 = d(i2, i3, i4);
        this.c[i2].reset();
        this.c[i2].setTranslate(this.k[0], this.k[1]);
        this.c[i2].preRotate((float) Math.toDegrees((double) d2));
    }

    private void a(int i2, Path path) {
        this.k[0] = this.d[i2].a;
        this.k[1] = this.d[i2].b;
        this.b[i2].mapPoints(this.k);
        if (i2 == 0) {
            path.moveTo(this.k[0], this.k[1]);
        } else {
            path.lineTo(this.k[0], this.k[1]);
        }
        this.d[i2].a(this.b[i2], path);
    }

    private void b(int i2, Path path) {
        int i3 = (i2 + 1) % 4;
        this.k[0] = this.d[i2].c;
        this.k[1] = this.d[i2].d;
        this.b[i2].mapPoints(this.k);
        this.l[0] = this.d[i3].a;
        this.l[1] = this.d[i3].b;
        this.b[i3].mapPoints(this.l);
        float hypot = (float) Math.hypot((double) (this.k[0] - this.l[0]), (double) (this.k[1] - this.l[1]));
        this.h.a(0.0f, 0.0f);
        b(i2).a(hypot, this.p, this.h);
        this.h.a(this.c[i2], path);
    }

    private a a(int i2) {
        switch (i2) {
            case 1:
                return this.m.b();
            case 2:
                return this.m.c();
            case 3:
                return this.m.d();
            default:
                return this.m.a();
        }
    }

    private b b(int i2) {
        switch (i2) {
            case 1:
                return this.m.f();
            case 2:
                return this.m.g();
            case 3:
                return this.m.h();
            default:
                return this.m.e();
        }
    }

    private void a(int i2, int i3, int i4, PointF pointF) {
        switch (i2) {
            case 1:
                pointF.set((float) i3, 0.0f);
                return;
            case 2:
                pointF.set((float) i3, (float) i4);
                return;
            case 3:
                pointF.set(0.0f, (float) i4);
                return;
            default:
                pointF.set(0.0f, 0.0f);
                return;
        }
    }

    private float c(int i2, int i3, int i4) {
        a(((i2 - 1) + 4) % 4, i3, i4, this.g);
        float f2 = this.g.x;
        float f3 = this.g.y;
        a((i2 + 1) % 4, i3, i4, this.g);
        float f4 = this.g.x;
        float f5 = this.g.y;
        a(i2, i3, i4, this.g);
        float f6 = this.g.x;
        float f7 = this.g.y;
        float f8 = f5 - f7;
        float atan2 = ((float) Math.atan2((double) (f3 - f7), (double) (f2 - f6))) - ((float) Math.atan2((double) f8, (double) (f4 - f6)));
        if (atan2 < 0.0f) {
            return (float) (((double) atan2) + 6.283185307179586d);
        }
        return atan2;
    }

    private float d(int i2, int i3, int i4) {
        int i5 = (i2 + 1) % 4;
        a(i2, i3, i4, this.g);
        float f2 = this.g.x;
        float f3 = this.g.y;
        a(i5, i3, i4, this.g);
        return (float) Math.atan2((double) (this.g.y - f3), (double) (this.g.x - f2));
    }

    private void b(int i2, int i3, Path path) {
        a(i2, i3, path);
        if (this.u != 1.0f) {
            this.e.reset();
            this.e.setScale(this.u, this.u, (float) (i2 / 2), (float) (i3 / 2));
            path.transform(this.e);
        }
    }

    private void c() {
        if (this.z == null || this.y == null) {
            this.x = null;
            return;
        }
        int colorForState = this.z.getColorForState(getState(), 0);
        this.x = new PorterDuffColorFilter(colorForState, this.y);
        if (this.o) {
            this.f320q = colorForState;
        }
    }
}
