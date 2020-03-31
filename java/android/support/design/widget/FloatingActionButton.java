package android.support.design.widget;

import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.R;
import android.support.design.d.c;
import android.support.design.stateful.ExtendableSavedState;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.TintableImageSourceView;
import android.support.v7.widget.f;
import android.support.v7.widget.h;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import java.util.List;

@android.support.design.widget.CoordinatorLayout.b(a = Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton implements android.support.design.d.a, TintableBackgroundView, TintableImageSourceView {
    boolean a;
    final Rect b;
    private ColorStateList c;
    private Mode d;
    private ColorStateList e;
    private Mode f;
    private int g;
    private ColorStateList h;
    private int i;
    private int j;
    /* access modifiers changed from: private */
    public int k;
    private int l;
    private final Rect m;
    private final h n;
    private final c o;
    private h p;

    protected static class BaseBehavior<T extends FloatingActionButton> extends android.support.design.widget.CoordinatorLayout.Behavior<T> {
        private Rect a;
        private a b;
        private boolean c;

        public BaseBehavior() {
            this.c = true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton_Behavior_Layout);
            this.c = obtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            obtainStyledAttributes.recycle();
        }

        public void a(d dVar) {
            if (dVar.h == 0) {
                dVar.h = 80;
            }
        }

        /* renamed from: a */
        public boolean b(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                a(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
            } else if (a(view)) {
                b(view, floatingActionButton);
            }
            return false;
        }

        private static boolean a(View view) {
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof d) {
                return ((d) layoutParams).b() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean a(View view, FloatingActionButton floatingActionButton) {
            d dVar = (d) floatingActionButton.getLayoutParams();
            if (!this.c) {
                return false;
            }
            if (dVar.a() != view.getId()) {
                return false;
            }
            if (floatingActionButton.getUserSetVisibility() != 0) {
                return false;
            }
            return true;
        }

        private boolean a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!a((View) appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.a == null) {
                this.a = new Rect();
            }
            Rect rect = this.a;
            f.b(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.b(this.b, false);
            } else {
                floatingActionButton.a(this.b, false);
            }
            return true;
        }

        private boolean b(View view, FloatingActionButton floatingActionButton) {
            if (!a(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < ((d) floatingActionButton.getLayoutParams()).topMargin + (floatingActionButton.getHeight() / 2)) {
                floatingActionButton.b(this.b, false);
            } else {
                floatingActionButton.a(this.b, false);
            }
            return true;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            List c2 = coordinatorLayout.c((View) floatingActionButton);
            int size = c2.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) c2.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (a(view) && b(view, floatingActionButton)) {
                        break;
                    }
                } else if (a(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.a((View) floatingActionButton, i);
            a(coordinatorLayout, floatingActionButton);
            return true;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.b;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        private void a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            int i;
            int i2 = 0;
            Rect rect = floatingActionButton.b;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0) {
                d dVar = (d) floatingActionButton.getLayoutParams();
                if (floatingActionButton.getRight() >= coordinatorLayout.getWidth() - dVar.rightMargin) {
                    i = rect.right;
                } else if (floatingActionButton.getLeft() <= dVar.leftMargin) {
                    i = -rect.left;
                } else {
                    i = 0;
                }
                if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - dVar.bottomMargin) {
                    i2 = rect.bottom;
                } else if (floatingActionButton.getTop() <= dVar.topMargin) {
                    i2 = -rect.top;
                }
                if (i2 != 0) {
                    ViewCompat.offsetTopAndBottom(floatingActionButton, i2);
                }
                if (i != 0) {
                    ViewCompat.offsetLeftAndRight(floatingActionButton, i);
                }
            }
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public /* bridge */ /* synthetic */ void a(d dVar) {
            super.a(dVar);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i) {
            return super.a(coordinatorLayout, floatingActionButton, i);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.a(coordinatorLayout, floatingActionButton, rect);
        }

        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.b(coordinatorLayout, floatingActionButton, view);
        }

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class a {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    private class b implements m {
        b() {
        }

        public float a() {
            return ((float) FloatingActionButton.this.getSizeDimension()) / 2.0f;
        }

        public void a(int i, int i2, int i3, int i4) {
            FloatingActionButton.this.b.set(i, i2, i3, i4);
            FloatingActionButton.this.setPadding(FloatingActionButton.this.k + i, FloatingActionButton.this.k + i2, FloatingActionButton.this.k + i3, FloatingActionButton.this.k + i4);
        }

        public void a(Drawable drawable) {
            FloatingActionButton.super.setBackgroundDrawable(drawable);
        }

        public boolean b() {
            return FloatingActionButton.this.a;
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.floatingActionButtonStyle);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = new Rect();
        this.m = new Rect();
        TypedArray a2 = android.support.design.internal.c.a(context, attributeSet, R.styleable.FloatingActionButton, i2, R.style.Widget_Design_FloatingActionButton, new int[0]);
        this.c = android.support.design.e.a.a(context, a2, R.styleable.FloatingActionButton_backgroundTint);
        this.d = android.support.design.internal.d.a(a2.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.h = android.support.design.e.a.a(context, a2, R.styleable.FloatingActionButton_rippleColor);
        this.i = a2.getInt(R.styleable.FloatingActionButton_fabSize, -1);
        this.j = a2.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
        this.g = a2.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        float dimension = a2.getDimension(R.styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = a2.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = a2.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.a = a2.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
        this.l = a2.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0);
        android.support.design.a.h a3 = android.support.design.a.h.a(context, a2, R.styleable.FloatingActionButton_showMotionSpec);
        android.support.design.a.h a4 = android.support.design.a.h.a(context, a2, R.styleable.FloatingActionButton_hideMotionSpec);
        a2.recycle();
        this.n = new h(this);
        this.n.a(attributeSet, i2);
        this.o = new c(this);
        getImpl().a(this.c, this.d, this.h, this.g);
        getImpl().a(dimension);
        getImpl().b(dimension2);
        getImpl().c(dimension3);
        getImpl().a(this.l);
        getImpl().a(a3);
        getImpl().b(a4);
        setScaleType(ScaleType.MATRIX);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int sizeDimension = getSizeDimension();
        this.k = (sizeDimension - this.l) / 2;
        getImpl().j();
        int min = Math.min(a(sizeDimension, i2), a(sizeDimension, i3));
        setMeasuredDimension(this.b.left + min + this.b.right, min + this.b.top + this.b.bottom);
    }

    @Deprecated
    public int getRippleColor() {
        if (this.h != null) {
            return this.h.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.h;
    }

    public void setRippleColor(int i2) {
        setRippleColor(ColorStateList.valueOf(i2));
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.h != colorStateList) {
            this.h = colorStateList;
            getImpl().b(this.h);
        }
    }

    public ColorStateList getBackgroundTintList() {
        return this.c;
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.c != colorStateList) {
            this.c = colorStateList;
            getImpl().a(colorStateList);
        }
    }

    public Mode getBackgroundTintMode() {
        return this.d;
    }

    public void setBackgroundTintMode(Mode mode) {
        if (this.d != mode) {
            this.d = mode;
            getImpl().a(mode);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        setBackgroundTintMode(mode);
    }

    public Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.e != colorStateList) {
            this.e = colorStateList;
            c();
        }
    }

    public ColorStateList getSupportImageTintList() {
        return this.e;
    }

    public void setSupportImageTintMode(Mode mode) {
        if (this.f != mode) {
            this.f = mode;
            c();
        }
    }

    public Mode getSupportImageTintMode() {
        return this.f;
    }

    private void c() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            if (this.e == null) {
                DrawableCompat.clearColorFilter(drawable);
                return;
            }
            int colorForState = this.e.getColorForState(getDrawableState(), 0);
            Mode mode = this.f;
            if (mode == null) {
                mode = Mode.SRC_IN;
            }
            drawable.mutate().setColorFilter(f.a(colorForState, mode));
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundResource(int i2) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setBackgroundColor(int i2) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    public void setImageResource(int i2) {
        this.n.a(i2);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        getImpl().d();
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar, boolean z) {
        getImpl().b(a(aVar), z);
    }

    public void a(AnimatorListener animatorListener) {
        getImpl().a(animatorListener);
    }

    public void b(AnimatorListener animatorListener) {
        getImpl().b(animatorListener);
    }

    /* access modifiers changed from: 0000 */
    public void b(a aVar, boolean z) {
        getImpl().a(a(aVar), z);
    }

    public void c(AnimatorListener animatorListener) {
        getImpl().c(animatorListener);
    }

    public void d(AnimatorListener animatorListener) {
        getImpl().d(animatorListener);
    }

    public boolean a() {
        return this.o.a();
    }

    public void setExpandedComponentIdHint(int i2) {
        this.o.a(i2);
    }

    public int getExpandedComponentIdHint() {
        return this.o.c();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.a != z) {
            this.a = z;
            getImpl().i();
        }
    }

    public boolean getUseCompatPadding() {
        return this.a;
    }

    public void setSize(int i2) {
        this.j = 0;
        if (i2 != this.i) {
            this.i = i2;
            requestLayout();
        }
    }

    public int getSize() {
        return this.i;
    }

    private d a(final a aVar) {
        if (aVar == null) {
            return null;
        }
        return new d() {
            public void a() {
                aVar.a(FloatingActionButton.this);
            }

            public void b() {
                aVar.b(FloatingActionButton.this);
            }
        };
    }

    public boolean b() {
        return getImpl().r();
    }

    public void setCustomSize(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        this.j = i2;
    }

    public int getCustomSize() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public int getSizeDimension() {
        return a(this.i);
    }

    private int a(int i2) {
        if (this.j != 0) {
            return this.j;
        }
        Resources resources = getResources();
        switch (i2) {
            case -1:
                if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470) {
                    return a(1);
                }
                return a(0);
            case 1:
                return resources.getDimensionPixelSize(R.dimen.design_fab_size_mini);
            default:
                return resources.getDimensionPixelSize(R.dimen.design_fab_size_normal);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().k();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().l();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().a(getDrawableState());
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().g();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(super.onSaveInstanceState());
        extendableSavedState.a.put("expandableWidgetHelper", this.o.b());
        return extendableSavedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.getSuperState());
        this.o.a((Bundle) extendableSavedState.a.get("expandableWidgetHelper"));
    }

    @Deprecated
    public boolean a(Rect rect) {
        if (!ViewCompat.isLaidOut(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        c(rect);
        return true;
    }

    public void b(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        c(rect);
    }

    private void c(Rect rect) {
        rect.left += this.b.left;
        rect.top += this.b.top;
        rect.right -= this.b.right;
        rect.bottom -= this.b.bottom;
    }

    public Drawable getContentBackground() {
        return getImpl().h();
    }

    private static int a(int i2, int i3) {
        int mode = MeasureSpec.getMode(i3);
        int size = MeasureSpec.getSize(i3);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                return Math.min(i2, size);
            case 0:
                return i2;
            case 1073741824:
                return size;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || !a(this.m) || this.m.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public float getCompatElevation() {
        return getImpl().a();
    }

    public void setCompatElevation(float f2) {
        getImpl().a(f2);
    }

    public void setCompatElevationResource(int i2) {
        setCompatElevation(getResources().getDimension(i2));
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().b();
    }

    public void setCompatHoveredFocusedTranslationZ(float f2) {
        getImpl().b(f2);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i2) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i2));
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().c();
    }

    public void setCompatPressedTranslationZ(float f2) {
        getImpl().c(f2);
    }

    public void setCompatPressedTranslationZResource(int i2) {
        setCompatPressedTranslationZ(getResources().getDimension(i2));
    }

    public android.support.design.a.h getShowMotionSpec() {
        return getImpl().e();
    }

    public void setShowMotionSpec(android.support.design.a.h hVar) {
        getImpl().a(hVar);
    }

    public void setShowMotionSpecResource(int i2) {
        setShowMotionSpec(android.support.design.a.h.a(getContext(), i2));
    }

    public android.support.design.a.h getHideMotionSpec() {
        return getImpl().f();
    }

    public void setHideMotionSpec(android.support.design.a.h hVar) {
        getImpl().b(hVar);
    }

    public void setHideMotionSpecResource(int i2) {
        setHideMotionSpec(android.support.design.a.h.a(getContext(), i2));
    }

    private h getImpl() {
        if (this.p == null) {
            this.p = d();
        }
        return this.p;
    }

    private h d() {
        if (VERSION.SDK_INT >= 21) {
            return new i(this, new b());
        }
        return new h(this, new b());
    }
}
