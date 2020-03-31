package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.internal.c;
import android.support.design.shape.MaterialShapeDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.a;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class BottomAppBar extends Toolbar implements a {
    AnimatorListenerAdapter a;
    private final int f;
    /* access modifiers changed from: private */
    public final MaterialShapeDrawable g;
    /* access modifiers changed from: private */
    public final a h;
    /* access modifiers changed from: private */
    public Animator i;
    /* access modifiers changed from: private */
    public Animator j;
    /* access modifiers changed from: private */
    public Animator k;
    /* access modifiers changed from: private */
    public int l;
    private boolean m;
    /* access modifiers changed from: private */
    public boolean n;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        private final Rect a = new Rect();

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        private boolean a(FloatingActionButton floatingActionButton, BottomAppBar bottomAppBar) {
            ((d) floatingActionButton.getLayoutParams()).d = 17;
            bottomAppBar.a(floatingActionButton);
            return true;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, int i) {
            FloatingActionButton e = bottomAppBar.m();
            if (e != null) {
                a(e, bottomAppBar);
                e.b(this.a);
                bottomAppBar.setFabDiameter(this.a.height());
            }
            if (!bottomAppBar.p()) {
                bottomAppBar.q();
            }
            coordinatorLayout.a((View) bottomAppBar, i);
            return super.a(coordinatorLayout, bottomAppBar, i);
        }

        public boolean a(CoordinatorLayout coordinatorLayout, BottomAppBar bottomAppBar, View view, View view2, int i, int i2) {
            return bottomAppBar.getHideOnScroll() && super.a(coordinatorLayout, bottomAppBar, view, view2, i, i2);
        }

        /* access modifiers changed from: protected */
        public void a(BottomAppBar bottomAppBar) {
            super.a(bottomAppBar);
            FloatingActionButton e = bottomAppBar.m();
            if (e != null) {
                e.clearAnimation();
                e.animate().translationY(bottomAppBar.getFabTranslationY()).setInterpolator(android.support.design.a.a.d).setDuration(225);
            }
        }

        /* access modifiers changed from: protected */
        public void b(BottomAppBar bottomAppBar) {
            super.b(bottomAppBar);
            FloatingActionButton e = bottomAppBar.m();
            if (e != null) {
                e.a(this.a);
                float measuredHeight = (float) (e.getMeasuredHeight() - this.a.height());
                e.clearAnimation();
                e.animate().translationY(((float) (-e.getPaddingBottom())) + measuredHeight).setInterpolator(android.support.design.a.a.c).setDuration(175);
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;
        boolean b;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b ? 1 : 0);
        }
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomAppBarStyle);
    }

    public BottomAppBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.n = true;
        this.a = new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                BottomAppBar.this.a(BottomAppBar.this.n);
                BottomAppBar.this.a(BottomAppBar.this.l, BottomAppBar.this.n);
            }
        };
        TypedArray a2 = c.a(context, attributeSet, R.styleable.BottomAppBar, i2, R.style.Widget_MaterialComponents_BottomAppBar, new int[0]);
        ColorStateList a3 = android.support.design.e.a.a(context, a2, R.styleable.BottomAppBar_backgroundTint);
        float dimensionPixelOffset = (float) a2.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = (float) a2.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = (float) a2.getDimensionPixelOffset(R.styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.l = a2.getInt(R.styleable.BottomAppBar_fabAlignmentMode, 0);
        this.m = a2.getBoolean(R.styleable.BottomAppBar_hideOnScroll, false);
        a2.recycle();
        this.f = getResources().getDimensionPixelOffset(R.dimen.mtrl_bottomappbar_fabOffsetEndMode);
        this.h = new a(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        android.support.design.shape.d dVar = new android.support.design.shape.d();
        dVar.a(this.h);
        this.g = new MaterialShapeDrawable(dVar);
        this.g.a(true);
        this.g.a(Style.FILL);
        DrawableCompat.setTintList(this.g, a3);
        ViewCompat.setBackground(this, this.g);
    }

    public int getFabAlignmentMode() {
        return this.l;
    }

    public void setFabAlignmentMode(int i2) {
        a(i2);
        a(i2, this.n);
        this.l = i2;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        DrawableCompat.setTintList(this.g, colorStateList);
    }

    public ColorStateList getBackgroundTint() {
        return this.g.a();
    }

    public float getFabCradleMargin() {
        return this.h.d();
    }

    public void setFabCradleMargin(float f2) {
        if (f2 != getFabCradleMargin()) {
            this.h.d(f2);
            this.g.invalidateSelf();
        }
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.h.e();
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        if (f2 != getFabCradleRoundedCornerRadius()) {
            this.h.e(f2);
            this.g.invalidateSelf();
        }
    }

    public float getCradleVerticalOffset() {
        return this.h.b();
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 != getCradleVerticalOffset()) {
            this.h.b(f2);
            this.g.invalidateSelf();
        }
    }

    public boolean getHideOnScroll() {
        return this.m;
    }

    public void setHideOnScroll(boolean z) {
        this.m = z;
    }

    /* access modifiers changed from: 0000 */
    public void setFabDiameter(int i2) {
        if (((float) i2) != this.h.c()) {
            this.h.c((float) i2);
            this.g.invalidateSelf();
        }
    }

    private void a(int i2) {
        if (this.l != i2 && ViewCompat.isLaidOut(this)) {
            if (this.j != null) {
                this.j.cancel();
            }
            ArrayList arrayList = new ArrayList();
            a(i2, (List<Animator>) arrayList);
            b(i2, (List<Animator>) arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.j = animatorSet;
            this.j.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    BottomAppBar.this.j = null;
                }
            });
            this.j.start();
        }
    }

    private void a(int i2, List<Animator> list) {
        if (this.n) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.h.a(), (float) b(i2)});
            ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BottomAppBar.this.h.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    BottomAppBar.this.g.invalidateSelf();
                }
            });
            ofFloat.setDuration(300);
            list.add(ofFloat);
        }
    }

    /* access modifiers changed from: private */
    public FloatingActionButton m() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        for (View view : ((CoordinatorLayout) getParent()).d((View) this)) {
            if (view instanceof FloatingActionButton) {
                return (FloatingActionButton) view;
            }
        }
        return null;
    }

    private boolean n() {
        FloatingActionButton m2 = m();
        return m2 != null && m2.b();
    }

    private void b(int i2, List<Animator> list) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(m(), "translationX", new float[]{(float) b(i2)});
        ofFloat.setDuration(300);
        list.add(ofFloat);
    }

    /* access modifiers changed from: private */
    public void a(int i2, boolean z) {
        int i3 = 0;
        if (ViewCompat.isLaidOut(this)) {
            if (this.k != null) {
                this.k.cancel();
            }
            ArrayList arrayList = new ArrayList();
            if (!n()) {
                z = false;
            } else {
                i3 = i2;
            }
            a(i3, z, (List<Animator>) arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.k = animatorSet;
            this.k.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    BottomAppBar.this.k = null;
                }
            });
            this.k.start();
        }
    }

    private void a(final int i2, final boolean z, List<Animator> list) {
        final ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{1.0f});
            if ((this.n || (z && n())) && (this.l == 1 || i2 == 1)) {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", new float[]{0.0f});
                ofFloat2.addListener(new AnimatorListenerAdapter() {
                    public boolean a;

                    public void onAnimationCancel(Animator animator) {
                        this.a = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (!this.a) {
                            BottomAppBar.this.a(actionMenuView, i2, z);
                        }
                    }
                });
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(150);
                animatorSet.playSequentially(new Animator[]{ofFloat2, ofFloat});
                list.add(animatorSet);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                list.add(ofFloat);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (ViewCompat.isLaidOut(this)) {
            if (this.i != null) {
                this.i.cancel();
            }
            ArrayList arrayList = new ArrayList();
            a(z && n(), (List<Animator>) arrayList);
            b(z, (List<Animator>) arrayList);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            this.i = animatorSet;
            this.i.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    BottomAppBar.this.i = null;
                }
            });
            this.i.start();
        }
    }

    private void a(boolean z, List<Animator> list) {
        if (z) {
            this.h.a(getFabTranslationX());
        }
        float[] fArr = new float[2];
        fArr[0] = this.g.b();
        fArr[1] = z ? 1.0f : 0.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BottomAppBar.this.g.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        ofFloat.setDuration(300);
        list.add(ofFloat);
    }

    private void b(boolean z, List<Animator> list) {
        FloatingActionButton m2 = m();
        if (m2 != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(m2, "translationY", new float[]{b(z)});
            ofFloat.setDuration(300);
            list.add(ofFloat);
        }
    }

    private float b(boolean z) {
        FloatingActionButton m2 = m();
        if (m2 == null) {
            return 0.0f;
        }
        Rect rect = new Rect();
        m2.a(rect);
        float height = (float) rect.height();
        if (height == 0.0f) {
            height = (float) m2.getMeasuredHeight();
        }
        float height2 = (float) (m2.getHeight() - rect.bottom);
        float f2 = (height / 2.0f) + (-getCradleVerticalOffset()) + height2;
        float height3 = ((float) (m2.getHeight() - rect.height())) - ((float) m2.getPaddingBottom());
        float f3 = (float) (-getMeasuredHeight());
        if (!z) {
            f2 = height3;
        }
        return f2 + f3;
    }

    /* access modifiers changed from: private */
    public float getFabTranslationY() {
        return b(this.n);
    }

    private int b(int i2) {
        boolean z;
        int i3 = 1;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            z = true;
        } else {
            z = false;
        }
        if (i2 != 1) {
            return 0;
        }
        int measuredWidth = (getMeasuredWidth() / 2) - this.f;
        if (z) {
            i3 = -1;
        }
        return i3 * measuredWidth;
    }

    private float getFabTranslationX() {
        return (float) b(this.l);
    }

    private ActionMenuView getActionMenuView() {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= getChildCount()) {
                return null;
            }
            View childAt = getChildAt(i3);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
            i2 = i3 + 1;
        }
    }

    /* access modifiers changed from: private */
    public void a(ActionMenuView actionMenuView, int i2, boolean z) {
        boolean z2;
        boolean z3;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            if (!(childAt.getLayoutParams() instanceof LayoutParams) || (((LayoutParams) childAt.getLayoutParams()).a & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) != 8388611) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                i3 = Math.max(i3, z2 ? childAt.getLeft() : childAt.getRight());
            }
        }
        actionMenuView.setTranslationX((i2 != 1 || !z) ? 0.0f : (float) (i3 - (z2 ? actionMenuView.getRight() : actionMenuView.getLeft())));
    }

    private void o() {
        if (this.i != null) {
            this.i.cancel();
        }
        if (this.k != null) {
            this.k.cancel();
        }
        if (this.j != null) {
            this.j.cancel();
        }
    }

    /* access modifiers changed from: private */
    public boolean p() {
        return (this.i != null && this.i.isRunning()) || (this.k != null && this.k.isRunning()) || (this.j != null && this.j.isRunning());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        o();
        q();
    }

    /* access modifiers changed from: private */
    public void q() {
        this.h.a(getFabTranslationX());
        FloatingActionButton m2 = m();
        this.g.a((!this.n || !n()) ? 0.0f : 1.0f);
        if (m2 != null) {
            m2.setTranslationY(getFabTranslationY());
            m2.setTranslationX(getFabTranslationX());
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            actionMenuView.setAlpha(1.0f);
            if (!n()) {
                a(actionMenuView, 0, false);
            } else {
                a(actionMenuView, this.l, this.n);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(FloatingActionButton floatingActionButton) {
        b(floatingActionButton);
        floatingActionButton.c((AnimatorListener) this.a);
        floatingActionButton.a((AnimatorListener) this.a);
    }

    private void b(FloatingActionButton floatingActionButton) {
        floatingActionButton.d(this.a);
        floatingActionButton.b((AnimatorListener) this.a);
    }

    public void setTitle(CharSequence charSequence) {
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public android.support.design.widget.CoordinatorLayout.Behavior<BottomAppBar> getBehavior() {
        return new Behavior();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.l;
        savedState.b = this.n;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.l = savedState.a;
        this.n = savedState.b;
    }
}
