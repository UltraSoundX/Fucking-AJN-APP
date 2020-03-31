package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.design.R;
import android.support.design.a.g;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: FloatingActionButtonImpl */
class h {
    static final TimeInterpolator a = android.support.design.a.a.c;
    static final int[] p = {16842919, 16842910};

    /* renamed from: q reason: collision with root package name */
    static final int[] f328q = {16843623, 16842908, 16842910};
    static final int[] r = {16842908, 16842910};
    static final int[] s = {16843623, 16842910};
    static final int[] t = {16842910};
    static final int[] u = new int[0];
    private float A;
    private ArrayList<AnimatorListener> B;
    private ArrayList<AnimatorListener> C;
    private final Rect D = new Rect();
    private final RectF E = new RectF();
    private final RectF F = new RectF();
    private final Matrix G = new Matrix();
    private OnPreDrawListener H;
    int b = 0;
    Animator c;
    android.support.design.a.h d;
    android.support.design.a.h e;
    l f;
    Drawable g;
    Drawable h;
    b i;
    Drawable j;
    float k;
    float l;
    float m;
    int n;
    float o = 1.0f;
    final VisibilityAwareImageButton v;
    final m w;
    private android.support.design.a.h x;
    private android.support.design.a.h y;
    private final o z;

    /* compiled from: FloatingActionButtonImpl */
    private class a extends f {
        a() {
            super();
        }

        /* access modifiers changed from: protected */
        public float a() {
            return 0.0f;
        }
    }

    /* compiled from: FloatingActionButtonImpl */
    private class b extends f {
        b() {
            super();
        }

        /* access modifiers changed from: protected */
        public float a() {
            return h.this.k + h.this.l;
        }
    }

    /* compiled from: FloatingActionButtonImpl */
    private class c extends f {
        c() {
            super();
        }

        /* access modifiers changed from: protected */
        public float a() {
            return h.this.k + h.this.m;
        }
    }

    /* compiled from: FloatingActionButtonImpl */
    interface d {
        void a();

        void b();
    }

    /* compiled from: FloatingActionButtonImpl */
    private class e extends f {
        e() {
            super();
        }

        /* access modifiers changed from: protected */
        public float a() {
            return h.this.k;
        }
    }

    /* compiled from: FloatingActionButtonImpl */
    private abstract class f extends AnimatorListenerAdapter implements AnimatorUpdateListener {
        private boolean a;
        private float c;
        private float d;

        /* access modifiers changed from: protected */
        public abstract float a();

        private f() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.a) {
                this.c = h.this.f.a();
                this.d = a();
                this.a = true;
            }
            h.this.f.a(this.c + ((this.d - this.c) * valueAnimator.getAnimatedFraction()));
        }

        public void onAnimationEnd(Animator animator) {
            h.this.f.a(this.d);
            this.a = false;
        }
    }

    h(VisibilityAwareImageButton visibilityAwareImageButton, m mVar) {
        this.v = visibilityAwareImageButton;
        this.w = mVar;
        this.z = new o();
        this.z.a(p, a((f) new c()));
        this.z.a(f328q, a((f) new b()));
        this.z.a(r, a((f) new b()));
        this.z.a(s, a((f) new b()));
        this.z.a(t, a((f) new e()));
        this.z.a(u, a((f) new a()));
        this.A = this.v.getRotation();
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList, Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable[] drawableArr;
        this.g = DrawableCompat.wrap(p());
        DrawableCompat.setTintList(this.g, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.g, mode);
        }
        this.h = DrawableCompat.wrap(p());
        DrawableCompat.setTintList(this.h, android.support.design.f.a.a(colorStateList2));
        if (i2 > 0) {
            this.i = a(i2, colorStateList);
            drawableArr = new Drawable[]{this.i, this.g, this.h};
        } else {
            this.i = null;
            drawableArr = new Drawable[]{this.g, this.h};
        }
        this.j = new LayerDrawable(drawableArr);
        this.f = new l(this.v.getContext(), this.j, this.w.a(), this.k, this.k + this.m);
        this.f.a(false);
        this.w.a(this.f);
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        if (this.g != null) {
            DrawableCompat.setTintList(this.g, colorStateList);
        }
        if (this.i != null) {
            this.i.a(colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Mode mode) {
        if (this.g != null) {
            DrawableCompat.setTintMode(this.g, mode);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(ColorStateList colorStateList) {
        if (this.h != null) {
            DrawableCompat.setTintList(this.h, android.support.design.f.a.a(colorStateList));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(float f2) {
        if (this.k != f2) {
            this.k = f2;
            a(this.k, this.l, this.m);
        }
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public float c() {
        return this.m;
    }

    /* access modifiers changed from: 0000 */
    public final void b(float f2) {
        if (this.l != f2) {
            this.l = f2;
            a(this.k, this.l, this.m);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void c(float f2) {
        if (this.m != f2) {
            this.m = f2;
            a(this.k, this.l, this.m);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2) {
        if (this.n != i2) {
            this.n = i2;
            d();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void d() {
        d(this.o);
    }

    /* access modifiers changed from: 0000 */
    public final void d(float f2) {
        this.o = f2;
        Matrix matrix = this.G;
        a(f2, matrix);
        this.v.setImageMatrix(matrix);
    }

    private void a(float f2, Matrix matrix) {
        matrix.reset();
        Drawable drawable = this.v.getDrawable();
        if (drawable != null && this.n != 0) {
            RectF rectF = this.E;
            RectF rectF2 = this.F;
            rectF.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            rectF2.set(0.0f, 0.0f, (float) this.n, (float) this.n);
            matrix.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
            matrix.postScale(f2, f2, ((float) this.n) / 2.0f, ((float) this.n) / 2.0f);
        }
    }

    /* access modifiers changed from: 0000 */
    public final android.support.design.a.h e() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final void a(android.support.design.a.h hVar) {
        this.d = hVar;
    }

    /* access modifiers changed from: 0000 */
    public final android.support.design.a.h f() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final void b(android.support.design.a.h hVar) {
        this.e = hVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(float f2, float f3, float f4) {
        if (this.f != null) {
            this.f.a(f2, this.m + f2);
            j();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int[] iArr) {
        this.z.a(iArr);
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        this.z.a();
    }

    /* access modifiers changed from: 0000 */
    public void a(AnimatorListener animatorListener) {
        if (this.B == null) {
            this.B = new ArrayList<>();
        }
        this.B.add(animatorListener);
    }

    /* access modifiers changed from: 0000 */
    public void b(AnimatorListener animatorListener) {
        if (this.B != null) {
            this.B.remove(animatorListener);
        }
    }

    public void c(AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    public void d(AnimatorListener animatorListener) {
        if (this.C != null) {
            this.C.remove(animatorListener);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(final d dVar, final boolean z2) {
        android.support.design.a.h u2;
        if (!s()) {
            if (this.c != null) {
                this.c.cancel();
            }
            if (w()) {
                if (this.e != null) {
                    u2 = this.e;
                } else {
                    u2 = u();
                }
                AnimatorSet a2 = a(u2, 0.0f, 0.0f, 0.0f);
                a2.addListener(new AnimatorListenerAdapter() {
                    private boolean d;

                    public void onAnimationStart(Animator animator) {
                        h.this.v.a(0, z2);
                        h.this.b = 1;
                        h.this.c = animator;
                        this.d = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.d = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        h.this.b = 0;
                        h.this.c = null;
                        if (!this.d) {
                            h.this.v.a(z2 ? 8 : 4, z2);
                            if (dVar != null) {
                                dVar.b();
                            }
                        }
                    }
                });
                if (this.C != null) {
                    Iterator it = this.C.iterator();
                    while (it.hasNext()) {
                        a2.addListener((AnimatorListener) it.next());
                    }
                }
                a2.start();
                return;
            }
            this.v.a(z2 ? 8 : 4, z2);
            if (dVar != null) {
                dVar.b();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(final d dVar, final boolean z2) {
        android.support.design.a.h t2;
        if (!r()) {
            if (this.c != null) {
                this.c.cancel();
            }
            if (w()) {
                if (this.v.getVisibility() != 0) {
                    this.v.setAlpha(0.0f);
                    this.v.setScaleY(0.0f);
                    this.v.setScaleX(0.0f);
                    d(0.0f);
                }
                if (this.d != null) {
                    t2 = this.d;
                } else {
                    t2 = t();
                }
                AnimatorSet a2 = a(t2, 1.0f, 1.0f, 1.0f);
                a2.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        h.this.v.a(0, z2);
                        h.this.b = 2;
                        h.this.c = animator;
                    }

                    public void onAnimationEnd(Animator animator) {
                        h.this.b = 0;
                        h.this.c = null;
                        if (dVar != null) {
                            dVar.a();
                        }
                    }
                });
                if (this.B != null) {
                    Iterator it = this.B.iterator();
                    while (it.hasNext()) {
                        a2.addListener((AnimatorListener) it.next());
                    }
                }
                a2.start();
                return;
            }
            this.v.a(0, z2);
            this.v.setAlpha(1.0f);
            this.v.setScaleY(1.0f);
            this.v.setScaleX(1.0f);
            d(1.0f);
            if (dVar != null) {
                dVar.a();
            }
        }
    }

    private android.support.design.a.h t() {
        if (this.x == null) {
            this.x = android.support.design.a.h.a(this.v.getContext(), R.animator.design_fab_show_motion_spec);
        }
        return this.x;
    }

    private android.support.design.a.h u() {
        if (this.y == null) {
            this.y = android.support.design.a.h.a(this.v.getContext(), R.animator.design_fab_hide_motion_spec);
        }
        return this.y;
    }

    private AnimatorSet a(android.support.design.a.h hVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.v, View.ALPHA, new float[]{f2});
        hVar.b("opacity").a((Animator) ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.v, View.SCALE_X, new float[]{f3});
        hVar.b("scale").a((Animator) ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.v, View.SCALE_Y, new float[]{f3});
        hVar.b("scale").a((Animator) ofFloat3);
        arrayList.add(ofFloat3);
        a(f4, this.G);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.v, new android.support.design.a.f(), new g(), new Matrix[]{new Matrix(this.G)});
        hVar.b("iconScale").a((Animator) ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        android.support.design.a.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    /* access modifiers changed from: 0000 */
    public final Drawable h() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public void i() {
    }

    /* access modifiers changed from: 0000 */
    public final void j() {
        Rect rect = this.D;
        a(rect);
        b(rect);
        this.w.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* access modifiers changed from: 0000 */
    public void a(Rect rect) {
        this.f.getPadding(rect);
    }

    /* access modifiers changed from: 0000 */
    public void b(Rect rect) {
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        if (m()) {
            v();
            this.v.getViewTreeObserver().addOnPreDrawListener(this.H);
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        if (this.H != null) {
            this.v.getViewTreeObserver().removeOnPreDrawListener(this.H);
            this.H = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean m() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public b a(int i2, ColorStateList colorStateList) {
        Context context = this.v.getContext();
        b n2 = n();
        n2.a(ContextCompat.getColor(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_outer_color));
        n2.a((float) i2);
        n2.a(colorStateList);
        return n2;
    }

    /* access modifiers changed from: 0000 */
    public b n() {
        return new b();
    }

    /* access modifiers changed from: 0000 */
    public void o() {
        float rotation = this.v.getRotation();
        if (this.A != rotation) {
            this.A = rotation;
            x();
        }
    }

    private void v() {
        if (this.H == null) {
            this.H = new OnPreDrawListener() {
                public boolean onPreDraw() {
                    h.this.o();
                    return true;
                }
            };
        }
    }

    /* access modifiers changed from: 0000 */
    public GradientDrawable p() {
        GradientDrawable q2 = q();
        q2.setShape(1);
        q2.setColor(-1);
        return q2;
    }

    /* access modifiers changed from: 0000 */
    public GradientDrawable q() {
        return new GradientDrawable();
    }

    /* access modifiers changed from: 0000 */
    public boolean r() {
        if (this.v.getVisibility() != 0) {
            if (this.b == 2) {
                return true;
            }
            return false;
        } else if (this.b == 1) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean s() {
        if (this.v.getVisibility() == 0) {
            if (this.b == 1) {
                return true;
            }
            return false;
        } else if (this.b == 2) {
            return false;
        } else {
            return true;
        }
    }

    private ValueAnimator a(f fVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(a);
        valueAnimator.setDuration(100);
        valueAnimator.addListener(fVar);
        valueAnimator.addUpdateListener(fVar);
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        return valueAnimator;
    }

    private boolean w() {
        return ViewCompat.isLaidOut(this.v) && !this.v.isInEditMode();
    }

    private void x() {
        if (VERSION.SDK_INT == 19) {
            if (this.A % 90.0f != 0.0f) {
                if (this.v.getLayerType() != 1) {
                    this.v.setLayerType(1, null);
                }
            } else if (this.v.getLayerType() != 0) {
                this.v.setLayerType(0, null);
            }
        }
        if (this.f != null) {
            this.f.b(-this.A);
        }
        if (this.i != null) {
            this.i.b(-this.A);
        }
    }
}
