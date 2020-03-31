package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.design.R;
import android.support.design.a.a;
import android.support.design.a.b;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IndicatorViewController */
final class j {
    private final Context a;
    private final TextInputLayout b;
    private LinearLayout c;
    private int d;
    private FrameLayout e;
    private int f;
    /* access modifiers changed from: private */
    public Animator g;
    private final float h = ((float) this.a.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y));
    /* access modifiers changed from: private */
    public int i;
    private int j;
    private CharSequence k;
    private boolean l;
    /* access modifiers changed from: private */
    public TextView m;
    private int n;
    private CharSequence o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private TextView f329q;
    private int r;
    private Typeface s;

    public j(TextInputLayout textInputLayout) {
        this.a = textInputLayout.getContext();
        this.b = textInputLayout;
    }

    /* access modifiers changed from: 0000 */
    public void a(CharSequence charSequence) {
        c();
        this.o = charSequence;
        this.f329q.setText(charSequence);
        if (this.i != 2) {
            this.j = 2;
        }
        a(this.i, this.j, a(this.f329q, charSequence));
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        c();
        if (this.i == 2) {
            this.j = 0;
        }
        a(this.i, this.j, a(this.f329q, (CharSequence) null));
    }

    /* access modifiers changed from: 0000 */
    public void b(CharSequence charSequence) {
        c();
        this.k = charSequence;
        this.m.setText(charSequence);
        if (this.i != 1) {
            this.j = 1;
        }
        a(this.i, this.j, a(this.m, charSequence));
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.k = null;
        c();
        if (this.i == 1) {
            if (!this.p || TextUtils.isEmpty(this.o)) {
                this.j = 0;
            } else {
                this.j = 2;
            }
        }
        a(this.i, this.j, a(this.m, (CharSequence) null));
    }

    private boolean a(TextView textView, CharSequence charSequence) {
        return ViewCompat.isLaidOut(this.b) && this.b.isEnabled() && (this.j != this.i || textView == null || !TextUtils.equals(textView.getText(), charSequence));
    }

    private void a(int i2, int i3, boolean z) {
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.g = animatorSet;
            ArrayList arrayList = new ArrayList();
            a(arrayList, this.p, this.f329q, 2, i2, i3);
            a(arrayList, this.l, this.m, 1, i2, i3);
            b.a(animatorSet, arrayList);
            final TextView d2 = d(i2);
            final TextView d3 = d(i3);
            final int i4 = i3;
            final int i5 = i2;
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    j.this.i = i4;
                    j.this.g = null;
                    if (d2 != null) {
                        d2.setVisibility(4);
                        if (i5 == 1 && j.this.m != null) {
                            j.this.m.setText(null);
                        }
                    }
                }

                public void onAnimationStart(Animator animator) {
                    if (d3 != null) {
                        d3.setVisibility(0);
                    }
                }
            });
            animatorSet.start();
        } else {
            a(i2, i3);
        }
        this.b.c();
        this.b.a(z);
        this.b.d();
    }

    private void a(int i2, int i3) {
        if (i2 != i3) {
            if (i3 != 0) {
                TextView d2 = d(i3);
                if (d2 != null) {
                    d2.setVisibility(0);
                    d2.setAlpha(1.0f);
                }
            }
            if (i2 != 0) {
                TextView d3 = d(i2);
                if (d3 != null) {
                    d3.setVisibility(4);
                    if (i2 == 1) {
                        d3.setText(null);
                    }
                }
            }
            this.i = i3;
        }
    }

    private void a(List<Animator> list, boolean z, TextView textView, int i2, int i3, int i4) {
        if (textView != null && z) {
            if (i2 == i4 || i2 == i3) {
                list.add(a(textView, i4 == i2));
                if (i4 == i2) {
                    list.add(a(textView));
                }
            }
        }
    }

    private ObjectAnimator a(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, new float[]{z ? 1.0f : 0.0f});
        ofFloat.setDuration(167);
        ofFloat.setInterpolator(a.a);
        return ofFloat;
    }

    private ObjectAnimator a(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, new float[]{-this.h, 0.0f});
        ofFloat.setDuration(217);
        ofFloat.setInterpolator(a.d);
        return ofFloat;
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (this.g != null) {
            this.g.cancel();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2) {
        return i2 == 0 || i2 == 1;
    }

    private TextView d(int i2) {
        switch (i2) {
            case 1:
                return this.m;
            case 2:
                return this.f329q;
            default:
                return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        if (m()) {
            ViewCompat.setPaddingRelative(this.c, ViewCompat.getPaddingStart(this.b.getEditText()), 0, ViewCompat.getPaddingEnd(this.b.getEditText()), 0);
        }
    }

    private boolean m() {
        return (this.c == null || this.b.getEditText() == null) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public void a(TextView textView, int i2) {
        if (this.c == null && this.e == null) {
            this.c = new LinearLayout(this.a);
            this.c.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            this.e = new FrameLayout(this.a);
            this.c.addView(this.e, -1, new LayoutParams(-2, -2));
            this.c.addView(new Space(this.a), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.b.getEditText() != null) {
                d();
            }
        }
        if (a(i2)) {
            this.e.setVisibility(0);
            this.e.addView(textView);
            this.f++;
        } else {
            this.c.addView(textView, i2);
        }
        this.c.setVisibility(0);
        this.d++;
    }

    /* access modifiers changed from: 0000 */
    public void b(TextView textView, int i2) {
        if (this.c != null) {
            if (!a(i2) || this.e == null) {
                this.c.removeView(textView);
            } else {
                this.f--;
                a((ViewGroup) this.e, this.f);
                this.e.removeView(textView);
            }
            this.d--;
            a((ViewGroup) this.c, this.d);
        }
    }

    private void a(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        if (this.l != z) {
            c();
            if (z) {
                this.m = new AppCompatTextView(this.a);
                this.m.setId(R.id.textinput_error);
                if (this.s != null) {
                    this.m.setTypeface(this.s);
                }
                b(this.n);
                this.m.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.m, 1);
                a(this.m, 0);
            } else {
                b();
                b(this.m, 0);
                this.m = null;
                this.b.c();
                this.b.d();
            }
            this.l = z;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public boolean f() {
        return this.p;
    }

    /* access modifiers changed from: 0000 */
    public void b(boolean z) {
        if (this.p != z) {
            c();
            if (z) {
                this.f329q = new AppCompatTextView(this.a);
                this.f329q.setId(R.id.textinput_helper_text);
                if (this.s != null) {
                    this.f329q.setTypeface(this.s);
                }
                this.f329q.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.f329q, 1);
                c(this.r);
                a(this.f329q, 1);
            } else {
                a();
                b(this.f329q, 1);
                this.f329q = null;
                this.b.c();
                this.b.d();
            }
            this.p = z;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean g() {
        return e(this.j);
    }

    private boolean e(int i2) {
        if (i2 != 1 || this.m == null || TextUtils.isEmpty(this.k)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public CharSequence h() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public CharSequence i() {
        return this.o;
    }

    /* access modifiers changed from: 0000 */
    public void a(Typeface typeface) {
        if (typeface != this.s) {
            this.s = typeface;
            a(this.m, typeface);
            a(this.f329q, typeface);
        }
    }

    private void a(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    /* access modifiers changed from: 0000 */
    public int j() {
        if (this.m != null) {
            return this.m.getCurrentTextColor();
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public ColorStateList k() {
        if (this.m != null) {
            return this.m.getTextColors();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(ColorStateList colorStateList) {
        if (this.m != null) {
            this.m.setTextColor(colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        this.n = i2;
        if (this.m != null) {
            this.b.a(this.m, i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public int l() {
        if (this.f329q != null) {
            return this.f329q.getCurrentTextColor();
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public void b(ColorStateList colorStateList) {
        if (this.f329q != null) {
            this.f329q.setTextColor(colorStateList);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2) {
        this.r = i2;
        if (this.f329q != null) {
            TextViewCompat.setTextAppearance(this.f329q, i2);
        }
    }
}
