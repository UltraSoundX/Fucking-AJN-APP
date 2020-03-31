package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.a.a.a;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.ImageView;

/* compiled from: AppCompatImageHelper */
public class h {
    private final ImageView a;
    private ar b;
    private ar c;
    private ar d;

    public h(ImageView imageView) {
        this.a = imageView;
    }

    public void a(AttributeSet attributeSet, int i) {
        at a2 = at.a(this.a.getContext(), attributeSet, R.styleable.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null) {
                int g = a2.g(R.styleable.AppCompatImageView_srcCompat, -1);
                if (g != -1) {
                    drawable = a.b(this.a.getContext(), g);
                    if (drawable != null) {
                        this.a.setImageDrawable(drawable);
                    }
                }
            }
            if (drawable != null) {
                v.b(drawable);
            }
            if (a2.g(R.styleable.AppCompatImageView_tint)) {
                ImageViewCompat.setImageTintList(this.a, a2.e(R.styleable.AppCompatImageView_tint));
            }
            if (a2.g(R.styleable.AppCompatImageView_tintMode)) {
                ImageViewCompat.setImageTintMode(this.a, v.a(a2.a(R.styleable.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    public void a(int i) {
        if (i != 0) {
            Drawable b2 = a.b(this.a.getContext(), i);
            if (b2 != null) {
                v.b(b2);
            }
            this.a.setImageDrawable(b2);
        } else {
            this.a.setImageDrawable(null);
        }
        d();
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        Drawable background = this.a.getBackground();
        if (VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new ar();
        }
        this.c.a = colorStateList;
        this.c.d = true;
        d();
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList b() {
        if (this.c != null) {
            return this.c.a;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(Mode mode) {
        if (this.c == null) {
            this.c = new ar();
        }
        this.c.b = mode;
        this.c.c = true;
        d();
    }

    /* access modifiers changed from: 0000 */
    public Mode c() {
        if (this.c != null) {
            return this.c.b;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            v.b(drawable);
        }
        if (drawable == null) {
            return;
        }
        if (e() && a(drawable)) {
            return;
        }
        if (this.c != null) {
            f.a(drawable, this.c, this.a.getDrawableState());
        } else if (this.b != null) {
            f.a(drawable, this.b, this.a.getDrawableState());
        }
    }

    private boolean e() {
        int i = VERSION.SDK_INT;
        if (i > 21) {
            if (this.b != null) {
                return true;
            }
            return false;
        } else if (i != 21) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new ar();
        }
        ar arVar = this.d;
        arVar.a();
        ColorStateList imageTintList = ImageViewCompat.getImageTintList(this.a);
        if (imageTintList != null) {
            arVar.d = true;
            arVar.a = imageTintList;
        }
        Mode imageTintMode = ImageViewCompat.getImageTintMode(this.a);
        if (imageTintMode != null) {
            arVar.c = true;
            arVar.b = imageTintMode;
        }
        if (!arVar.d && !arVar.c) {
            return false;
        }
        f.a(drawable, arVar, this.a.getDrawableState());
        return true;
    }
}
