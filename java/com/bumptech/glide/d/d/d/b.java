package com.bumptech.glide.d.d.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.view.Gravity;
import com.bumptech.glide.b.a.C0036a;
import com.bumptech.glide.b.c;
import com.bumptech.glide.d.g;
import com.stub.StubApp;

/* compiled from: GifDrawable */
public class b extends com.bumptech.glide.d.d.b.b implements com.bumptech.glide.d.d.d.f.b {
    private final Paint a;
    private final Rect b;
    private final a c;
    private final com.bumptech.glide.b.a d;
    private final f e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private boolean l;

    /* compiled from: GifDrawable */
    static class a extends ConstantState {
        c a;
        byte[] b;
        Context c;
        g<Bitmap> d;
        int e;
        int f;
        C0036a g;
        com.bumptech.glide.d.b.a.c h;
        Bitmap i;

        public a(c cVar, byte[] bArr, Context context, g<Bitmap> gVar, int i2, int i3, C0036a aVar, com.bumptech.glide.d.b.a.c cVar2, Bitmap bitmap) {
            if (bitmap == null) {
                throw new NullPointerException("The first frame of the GIF must not be null");
            }
            this.a = cVar;
            this.b = bArr;
            this.h = cVar2;
            this.i = bitmap;
            this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
            this.d = gVar;
            this.e = i2;
            this.f = i3;
            this.g = aVar;
        }

        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        public Drawable newDrawable() {
            return new b(this);
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }

    public b(Context context, C0036a aVar, com.bumptech.glide.d.b.a.c cVar, g<Bitmap> gVar, int i2, int i3, c cVar2, byte[] bArr, Bitmap bitmap) {
        this(new a(cVar2, bArr, context, gVar, i2, i3, aVar, cVar, bitmap));
    }

    public b(b bVar, Bitmap bitmap, g<Bitmap> gVar) {
        this(new a(bVar.c.a, bVar.c.b, bVar.c.c, gVar, bVar.c.e, bVar.c.f, bVar.c.g, bVar.c.h, bitmap));
    }

    b(a aVar) {
        this.b = new Rect();
        this.i = true;
        this.k = -1;
        if (aVar == null) {
            throw new NullPointerException("GifState must not be null");
        }
        this.c = aVar;
        this.d = new com.bumptech.glide.b.a(aVar.g);
        this.a = new Paint();
        this.d.a(aVar.a, aVar.b);
        this.e = new f(aVar.c, this, this.d, aVar.e, aVar.f);
        this.e.a(aVar.d);
    }

    public Bitmap b() {
        return this.c.i;
    }

    public g<Bitmap> c() {
        return this.c.d;
    }

    public byte[] d() {
        return this.c.b;
    }

    public int e() {
        return this.d.c();
    }

    private void g() {
        this.j = 0;
    }

    public void start() {
        this.g = true;
        g();
        if (this.i) {
            i();
        }
    }

    public void stop() {
        this.g = false;
        j();
        if (VERSION.SDK_INT < 11) {
            h();
        }
    }

    private void h() {
        this.e.c();
        invalidateSelf();
    }

    private void i() {
        if (this.d.c() == 1) {
            invalidateSelf();
        } else if (!this.f) {
            this.f = true;
            this.e.a();
            invalidateSelf();
        }
    }

    private void j() {
        this.f = false;
        this.e.b();
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.i = z;
        if (!z) {
            j();
        } else if (this.g) {
            i();
        }
        return super.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.c.i.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.c.i.getHeight();
    }

    public boolean isRunning() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.l = true;
    }

    public void draw(Canvas canvas) {
        if (!this.h) {
            if (this.l) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.b);
                this.l = false;
            }
            Bitmap d2 = this.e.d();
            if (d2 == null) {
                d2 = this.c.i;
            }
            canvas.drawBitmap(d2, null, this.b, this.a);
        }
    }

    public void setAlpha(int i2) {
        this.a.setAlpha(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -2;
    }

    @TargetApi(11)
    public void b(int i2) {
        if (VERSION.SDK_INT < 11 || getCallback() != null) {
            invalidateSelf();
            if (i2 == this.d.c() - 1) {
                this.j++;
            }
            if (this.k != -1 && this.j >= this.k) {
                stop();
                return;
            }
            return;
        }
        stop();
        h();
    }

    public ConstantState getConstantState() {
        return this.c;
    }

    public void f() {
        this.h = true;
        this.c.h.a(this.c.i);
        this.e.c();
        this.e.b();
    }

    public boolean a() {
        return true;
    }

    public void a(int i2) {
        if (i2 <= 0 && i2 != -1 && i2 != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (i2 == 0) {
            this.k = this.d.e();
        } else {
            this.k = i2;
        }
    }
}
