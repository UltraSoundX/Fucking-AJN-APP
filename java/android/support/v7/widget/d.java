package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: AppCompatBackgroundHelper */
class d {
    private final View a;
    private final f b;
    private int c = -1;
    private ar d;
    private ar e;
    private ar f;

    d(View view) {
        this.a = view;
        this.b = f.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(AttributeSet attributeSet, int i) {
        at a2 = at.a(this.a.getContext(), attributeSet, R.styleable.ViewBackgroundHelper, i, 0);
        try {
            if (a2.g(R.styleable.ViewBackgroundHelper_android_background)) {
                this.c = a2.g(R.styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList b2 = this.b.b(this.a.getContext(), this.c);
                if (b2 != null) {
                    b(b2);
                }
            }
            if (a2.g(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.a, a2.e(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (a2.g(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.a, v.a(a2.a(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            a2.a();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        this.c = i;
        b(this.b != null ? this.b.b(this.a.getContext(), i) : null);
        c();
    }

    /* access modifiers changed from: 0000 */
    public void a(Drawable drawable) {
        this.c = -1;
        b((ColorStateList) null);
        c();
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new ar();
        }
        this.e.a = colorStateList;
        this.e.d = true;
        c();
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList a() {
        if (this.e != null) {
            return this.e.a;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(Mode mode) {
        if (this.e == null) {
            this.e = new ar();
        }
        this.e.b = mode;
        this.e.c = true;
        c();
    }

    /* access modifiers changed from: 0000 */
    public Mode b() {
        if (this.e != null) {
            return this.e.b;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        Drawable background = this.a.getBackground();
        if (background == null) {
            return;
        }
        if (d() && b(background)) {
            return;
        }
        if (this.e != null) {
            f.a(background, this.e, this.a.getDrawableState());
        } else if (this.d != null) {
            f.a(background, this.d, this.a.getDrawableState());
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new ar();
            }
            this.d.a = colorStateList;
            this.d.d = true;
        } else {
            this.d = null;
        }
        c();
    }

    private boolean d() {
        int i = VERSION.SDK_INT;
        if (i > 21) {
            if (this.d != null) {
                return true;
            }
            return false;
        } else if (i != 21) {
            return false;
        } else {
            return true;
        }
    }

    private boolean b(Drawable drawable) {
        if (this.f == null) {
            this.f = new ar();
        }
        ar arVar = this.f;
        arVar.a();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.a);
        if (backgroundTintList != null) {
            arVar.d = true;
            arVar.a = backgroundTintList;
        }
        Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.a);
        if (backgroundTintMode != null) {
            arVar.c = true;
            arVar.b = backgroundTintMode;
        }
        if (!arVar.d && !arVar.c) {
            return false;
        }
        f.a(drawable, arVar, this.a.getDrawableState());
        return true;
    }
}
