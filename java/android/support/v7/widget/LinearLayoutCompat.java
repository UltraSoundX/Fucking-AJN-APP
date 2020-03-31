package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class LinearLayoutCompat extends ViewGroup {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;

    public static class LayoutParams extends MarginLayoutParams {
        public float g;
        public int h;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.h = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.h = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.h = -1;
            this.g = 0.0f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.h = -1;
        }
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = true;
        this.b = -1;
        this.c = 0;
        this.e = 8388659;
        at a2 = at.a(context, attributeSet, R.styleable.LinearLayoutCompat, i2, 0);
        int a3 = a2.a(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (a3 >= 0) {
            setOrientation(a3);
        }
        int a4 = a2.a(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (a4 >= 0) {
            setGravity(a4);
        }
        boolean a5 = a2.a(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!a5) {
            setBaselineAligned(a5);
        }
        this.g = a2.a(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.b = a2.a(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a2.a(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a2.a(R.styleable.LinearLayoutCompat_divider));
        this.n = a2.a(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.o = a2.e(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a2.a();
    }

    public void setShowDividers(int i2) {
        if (i2 != this.n) {
            requestLayout();
        }
        this.n = i2;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.n;
    }

    public Drawable getDividerDrawable() {
        return this.k;
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.k) {
            this.k = drawable;
            if (drawable != null) {
                this.l = drawable.getIntrinsicWidth();
                this.m = drawable.getIntrinsicHeight();
            } else {
                this.l = 0;
                this.m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i2) {
        this.o = i2;
    }

    public int getDividerPadding() {
        return this.o;
    }

    public int getDividerWidth() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.k != null) {
            if (this.d == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View b2 = b(i2);
            if (!(b2 == null || b2.getVisibility() == 8 || !c(i2))) {
                a(canvas, (b2.getTop() - ((LayoutParams) b2.getLayoutParams()).topMargin) - this.m);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            if (b3 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.m;
            } else {
                LayoutParams layoutParams = (LayoutParams) b3.getLayoutParams();
                bottom = layoutParams.bottomMargin + b3.getBottom();
            }
            a(canvas, bottom);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Canvas canvas) {
        int right;
        int left;
        int virtualChildCount = getVirtualChildCount();
        boolean a2 = bb.a(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View b2 = b(i2);
            if (!(b2 == null || b2.getVisibility() == 8 || !c(i2))) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (a2) {
                    left = layoutParams.rightMargin + b2.getRight();
                } else {
                    left = (b2.getLeft() - layoutParams.leftMargin) - this.l;
                }
                b(canvas, left);
            }
        }
        if (c(virtualChildCount)) {
            View b3 = b(virtualChildCount - 1);
            if (b3 != null) {
                LayoutParams layoutParams2 = (LayoutParams) b3.getLayoutParams();
                if (a2) {
                    right = (b3.getLeft() - layoutParams2.leftMargin) - this.l;
                } else {
                    right = layoutParams2.rightMargin + b3.getRight();
                }
            } else if (a2) {
                right = getPaddingLeft();
            } else {
                right = (getWidth() - getPaddingRight()) - this.l;
            }
            b(canvas, right);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Canvas canvas, int i2) {
        this.k.setBounds(getPaddingLeft() + this.o, i2, (getWidth() - getPaddingRight()) - this.o, this.m + i2);
        this.k.draw(canvas);
    }

    /* access modifiers changed from: 0000 */
    public void b(Canvas canvas, int i2) {
        this.k.setBounds(i2, getPaddingTop() + this.o, this.l + i2, (getHeight() - getPaddingBottom()) - this.o);
        this.k.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.a = z;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.h = z;
    }

    public int getBaseline() {
        int i2;
        if (this.b < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.b) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(this.b);
        int baseline = childAt.getBaseline();
        if (baseline != -1) {
            int i3 = this.c;
            if (this.d == 1) {
                int i4 = this.e & 112;
                if (i4 != 48) {
                    switch (i4) {
                        case 16:
                            i2 = i3 + (((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f) / 2);
                            break;
                        case 80:
                            i2 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f;
                            break;
                    }
                }
            }
            i2 = i3;
            return ((LayoutParams) childAt.getLayoutParams()).topMargin + i2 + baseline;
        } else if (this.b == 0) {
            return -1;
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.b;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 < 0 || i2 >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.b = i2;
    }

    /* access modifiers changed from: 0000 */
    public View b(int i2) {
        return getChildAt(i2);
    }

    /* access modifiers changed from: 0000 */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.g;
    }

    public void setWeightSum(float f2) {
        this.g = Math.max(0.0f, f2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (this.d == 1) {
            a(i2, i3);
        } else {
            b(i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i2) {
        if (i2 == 0) {
            if ((this.n & 1) != 0) {
                return true;
            }
            return false;
        } else if (i2 == getChildCount()) {
            if ((this.n & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.n & 2) == 0) {
            return false;
        } else {
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                if (getChildAt(i3).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        int i4;
        int i5;
        float f2;
        int i6;
        int i7;
        boolean z;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z2;
        boolean z3;
        int max;
        int i12;
        boolean z4;
        int i13;
        int i14;
        int i15;
        int i16;
        this.f = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        boolean z5 = true;
        float f3 = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i2);
        int mode2 = MeasureSpec.getMode(i3);
        boolean z6 = false;
        boolean z7 = false;
        int i21 = this.b;
        boolean z8 = this.h;
        int i22 = 0;
        int i23 = 0;
        while (i23 < virtualChildCount) {
            View b2 = b(i23);
            if (b2 == null) {
                this.f += d(i23);
                i14 = i22;
                z4 = z7;
                z3 = z5;
                i15 = i18;
                i13 = i17;
            } else if (b2.getVisibility() == 8) {
                i23 += a(b2, i23);
                i14 = i22;
                z4 = z7;
                z3 = z5;
                i15 = i18;
                i13 = i17;
            } else {
                if (c(i23)) {
                    this.f += this.m;
                }
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                float f4 = f3 + layoutParams.g;
                if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.g > 0.0f) {
                    int i24 = this.f;
                    this.f = Math.max(i24, layoutParams.topMargin + i24 + layoutParams.bottomMargin);
                    z7 = true;
                } else {
                    int i25 = ExploreByTouchHelper.INVALID_ID;
                    if (layoutParams.height == 0 && layoutParams.g > 0.0f) {
                        i25 = 0;
                        layoutParams.height = -2;
                    }
                    int i26 = i25;
                    a(b2, i23, i2, 0, i3, f4 == 0.0f ? this.f : 0);
                    if (i26 != Integer.MIN_VALUE) {
                        layoutParams.height = i26;
                    }
                    int measuredHeight = b2.getMeasuredHeight();
                    int i27 = this.f;
                    this.f = Math.max(i27, i27 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + b(b2));
                    if (z8) {
                        i22 = Math.max(measuredHeight, i22);
                    }
                }
                if (i21 >= 0 && i21 == i23 + 1) {
                    this.c = this.f;
                }
                if (i23 >= i21 || layoutParams.g <= 0.0f) {
                    boolean z9 = false;
                    if (mode == 1073741824 || layoutParams.width != -1) {
                        z2 = z6;
                    } else {
                        z2 = true;
                        z9 = true;
                    }
                    int i28 = layoutParams.rightMargin + layoutParams.leftMargin;
                    int measuredWidth = b2.getMeasuredWidth() + i28;
                    int max2 = Math.max(i17, measuredWidth);
                    int combineMeasuredStates = View.combineMeasuredStates(i18, b2.getMeasuredState());
                    z3 = z5 && layoutParams.width == -1;
                    if (layoutParams.g > 0.0f) {
                        if (z9) {
                            i16 = i28;
                        } else {
                            i16 = measuredWidth;
                        }
                        i12 = Math.max(i20, i16);
                        max = i19;
                    } else {
                        if (!z9) {
                            i28 = measuredWidth;
                        }
                        max = Math.max(i19, i28);
                        i12 = i20;
                    }
                    i23 += a(b2, i23);
                    z4 = z7;
                    i20 = i12;
                    i19 = max;
                    i13 = max2;
                    i14 = i22;
                    i15 = combineMeasuredStates;
                    z6 = z2;
                    f3 = f4;
                } else {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
            }
            i23++;
            i22 = i14;
            z7 = z4;
            z5 = z3;
            i18 = i15;
            i17 = i13;
        }
        if (this.f > 0 && c(virtualChildCount)) {
            this.f += this.m;
        }
        if (z8 && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.f = 0;
            int i29 = 0;
            while (i29 < virtualChildCount) {
                View b3 = b(i29);
                if (b3 == null) {
                    this.f += d(i29);
                    i11 = i29;
                } else if (b3.getVisibility() == 8) {
                    i11 = a(b3, i29) + i29;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) b3.getLayoutParams();
                    int i30 = this.f;
                    this.f = Math.max(i30, layoutParams2.bottomMargin + i30 + i22 + layoutParams2.topMargin + b(b3));
                    i11 = i29;
                }
                i29 = i11 + 1;
            }
        }
        this.f += getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumHeight()), i3, 0);
        int i31 = (16777215 & resolveSizeAndState) - this.f;
        if (z7 || (i31 != 0 && f3 > 0.0f)) {
            if (this.g > 0.0f) {
                f3 = this.g;
            }
            this.f = 0;
            int i32 = 0;
            float f5 = f3;
            boolean z10 = z5;
            int i33 = i19;
            int i34 = i18;
            int i35 = i17;
            int i36 = i31;
            while (i32 < virtualChildCount) {
                View b4 = b(i32);
                if (b4.getVisibility() == 8) {
                    i8 = i33;
                    i10 = i34;
                    i9 = i35;
                    z = z10;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) b4.getLayoutParams();
                    float f6 = layoutParams3.g;
                    if (f6 > 0.0f) {
                        int i37 = (int) ((((float) i36) * f6) / f5);
                        float f7 = f5 - f6;
                        int i38 = i36 - i37;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                        if (layoutParams3.height == 0 && mode2 == 1073741824) {
                            if (i37 <= 0) {
                                i37 = 0;
                            }
                            b4.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(i37, 1073741824));
                        } else {
                            int measuredHeight2 = i37 + b4.getMeasuredHeight();
                            if (measuredHeight2 < 0) {
                                measuredHeight2 = 0;
                            }
                            b4.measure(childMeasureSpec, MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
                        }
                        float f8 = f7;
                        i6 = i38;
                        i7 = View.combineMeasuredStates(i34, b4.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                        f2 = f8;
                    } else {
                        f2 = f5;
                        i6 = i36;
                        i7 = i34;
                    }
                    int i39 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                    int measuredWidth2 = b4.getMeasuredWidth() + i39;
                    int max3 = Math.max(i35, measuredWidth2);
                    if (!(mode != 1073741824 && layoutParams3.width == -1)) {
                        i39 = measuredWidth2;
                    }
                    int max4 = Math.max(i33, i39);
                    z = z10 && layoutParams3.width == -1;
                    int i40 = this.f;
                    this.f = Math.max(i40, layoutParams3.bottomMargin + b4.getMeasuredHeight() + i40 + layoutParams3.topMargin + b(b4));
                    i8 = max4;
                    i9 = max3;
                    float f9 = f2;
                    i10 = i7;
                    i36 = i6;
                    f5 = f9;
                }
                i32++;
                i33 = i8;
                i35 = i9;
                z10 = z;
                i34 = i10;
            }
            this.f += getPaddingTop() + getPaddingBottom();
            z5 = z10;
            i5 = i33;
            i18 = i34;
            i4 = i35;
        } else {
            int max5 = Math.max(i19, i20);
            if (z8 && mode2 != 1073741824) {
                int i41 = 0;
                while (true) {
                    int i42 = i41;
                    if (i42 >= virtualChildCount) {
                        break;
                    }
                    View b5 = b(i42);
                    if (!(b5 == null || b5.getVisibility() == 8 || ((LayoutParams) b5.getLayoutParams()).g <= 0.0f)) {
                        b5.measure(MeasureSpec.makeMeasureSpec(b5.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(i22, 1073741824));
                    }
                    i41 = i42 + 1;
                }
            }
            i5 = max5;
            i4 = i17;
        }
        if (z5 || mode == 1073741824) {
            i5 = i4;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i5 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, i18), resolveSizeAndState);
        if (z6) {
            c(virtualChildCount, i3);
        }
    }

    private void c(int i2, int i3) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i5 = layoutParams.height;
                    layoutParams.height = b2.getMeasuredHeight();
                    measureChildWithMargins(b2, makeMeasureSpec, 0, i3, 0);
                    layoutParams.height = i5;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2, int i3) {
        boolean z;
        int i4;
        int i5;
        int i6;
        float f2;
        int i7;
        int i8;
        int i9;
        boolean z2;
        int i10;
        int i11;
        float f3;
        int i12;
        int i13;
        boolean z3;
        boolean z4;
        int max;
        int i14;
        boolean z5;
        int i15;
        int i16;
        int i17;
        int i18;
        this.f = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        boolean z6 = true;
        float f4 = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = MeasureSpec.getMode(i2);
        int mode2 = MeasureSpec.getMode(i3);
        boolean z7 = false;
        boolean z8 = false;
        if (this.i == null || this.j == null) {
            this.i = new int[4];
            this.j = new int[4];
        }
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z9 = this.a;
        boolean z10 = this.h;
        if (mode == 1073741824) {
            z = true;
        } else {
            z = false;
        }
        int i23 = 0;
        int i24 = 0;
        while (i24 < virtualChildCount) {
            View b2 = b(i24);
            if (b2 == null) {
                this.f += d(i24);
                i16 = i23;
                z5 = z8;
                z4 = z6;
                i17 = i20;
                i15 = i19;
            } else if (b2.getVisibility() == 8) {
                i24 += a(b2, i24);
                i16 = i23;
                z5 = z8;
                z4 = z6;
                i17 = i20;
                i15 = i19;
            } else {
                if (c(i24)) {
                    this.f += this.l;
                }
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                float f5 = f4 + layoutParams.g;
                if (mode == 1073741824 && layoutParams.width == 0 && layoutParams.g > 0.0f) {
                    if (z) {
                        this.f += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        int i25 = this.f;
                        this.f = Math.max(i25, layoutParams.leftMargin + i25 + layoutParams.rightMargin);
                    }
                    if (z9) {
                        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                        b2.measure(makeMeasureSpec, makeMeasureSpec);
                    } else {
                        z8 = true;
                    }
                } else {
                    int i26 = ExploreByTouchHelper.INVALID_ID;
                    if (layoutParams.width == 0 && layoutParams.g > 0.0f) {
                        i26 = 0;
                        layoutParams.width = -2;
                    }
                    int i27 = i26;
                    a(b2, i24, i2, f5 == 0.0f ? this.f : 0, i3, 0);
                    if (i27 != Integer.MIN_VALUE) {
                        layoutParams.width = i27;
                    }
                    int measuredWidth = b2.getMeasuredWidth();
                    if (z) {
                        this.f += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + b(b2);
                    } else {
                        int i28 = this.f;
                        this.f = Math.max(i28, i28 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + b(b2));
                    }
                    if (z10) {
                        i23 = Math.max(measuredWidth, i23);
                    }
                }
                boolean z11 = false;
                if (mode2 == 1073741824 || layoutParams.height != -1) {
                    z3 = z7;
                } else {
                    z3 = true;
                    z11 = true;
                }
                int i29 = layoutParams.bottomMargin + layoutParams.topMargin;
                int measuredHeight = b2.getMeasuredHeight() + i29;
                int combineMeasuredStates = View.combineMeasuredStates(i20, b2.getMeasuredState());
                if (z9) {
                    int baseline = b2.getBaseline();
                    if (baseline != -1) {
                        int i30 = ((((layoutParams.h < 0 ? this.e : layoutParams.h) & 112) >> 4) & -2) >> 1;
                        iArr[i30] = Math.max(iArr[i30], baseline);
                        iArr2[i30] = Math.max(iArr2[i30], measuredHeight - baseline);
                    }
                }
                int max2 = Math.max(i19, measuredHeight);
                z4 = z6 && layoutParams.height == -1;
                if (layoutParams.g > 0.0f) {
                    if (z11) {
                        i18 = i29;
                    } else {
                        i18 = measuredHeight;
                    }
                    i14 = Math.max(i22, i18);
                    max = i21;
                } else {
                    if (!z11) {
                        i29 = measuredHeight;
                    }
                    max = Math.max(i21, i29);
                    i14 = i22;
                }
                i24 += a(b2, i24);
                z5 = z8;
                i22 = i14;
                i21 = max;
                i15 = max2;
                i16 = i23;
                i17 = combineMeasuredStates;
                z7 = z3;
                f4 = f5;
            }
            i24++;
            i23 = i16;
            z8 = z5;
            z6 = z4;
            i20 = i17;
            i19 = i15;
        }
        if (this.f > 0 && c(virtualChildCount)) {
            this.f += this.l;
        }
        if (iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1) {
            i4 = i19;
        } else {
            i4 = Math.max(i19, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (z10 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.f = 0;
            int i31 = 0;
            while (i31 < virtualChildCount) {
                View b3 = b(i31);
                if (b3 == null) {
                    this.f += d(i31);
                    i13 = i31;
                } else if (b3.getVisibility() == 8) {
                    i13 = a(b3, i31) + i31;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) b3.getLayoutParams();
                    if (z) {
                        this.f = layoutParams2.rightMargin + layoutParams2.leftMargin + i23 + b(b3) + this.f;
                        i13 = i31;
                    } else {
                        int i32 = this.f;
                        this.f = Math.max(i32, layoutParams2.rightMargin + i32 + i23 + layoutParams2.leftMargin + b(b3));
                        i13 = i31;
                    }
                }
                i31 = i13 + 1;
            }
        }
        this.f += getPaddingLeft() + getPaddingRight();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.f, getSuggestedMinimumWidth()), i2, 0);
        int i33 = (16777215 & resolveSizeAndState) - this.f;
        if (z8 || (i33 != 0 && f4 > 0.0f)) {
            if (this.g > 0.0f) {
                f4 = this.g;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.f = 0;
            int i34 = 0;
            float f6 = f4;
            boolean z12 = z6;
            int i35 = i21;
            int i36 = i20;
            int i37 = i33;
            int i38 = -1;
            while (i34 < virtualChildCount) {
                View b4 = b(i34);
                if (b4 == null) {
                    f2 = f6;
                    i7 = i37;
                    i8 = i38;
                    i9 = i35;
                    z2 = z12;
                } else if (b4.getVisibility() == 8) {
                    f2 = f6;
                    i7 = i37;
                    i8 = i38;
                    i9 = i35;
                    z2 = z12;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) b4.getLayoutParams();
                    float f7 = layoutParams3.g;
                    if (f7 > 0.0f) {
                        int i39 = (int) ((((float) i37) * f7) / f6);
                        float f8 = f6 - f7;
                        i10 = i37 - i39;
                        int childMeasureSpec = getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0 && mode == 1073741824) {
                            if (i39 <= 0) {
                                i39 = 0;
                            }
                            b4.measure(MeasureSpec.makeMeasureSpec(i39, 1073741824), childMeasureSpec);
                        } else {
                            int measuredWidth2 = i39 + b4.getMeasuredWidth();
                            if (measuredWidth2 < 0) {
                                measuredWidth2 = 0;
                            }
                            b4.measure(MeasureSpec.makeMeasureSpec(measuredWidth2, 1073741824), childMeasureSpec);
                        }
                        i11 = View.combineMeasuredStates(i36, b4.getMeasuredState() & -16777216);
                        f3 = f8;
                    } else {
                        i10 = i37;
                        i11 = i36;
                        f3 = f6;
                    }
                    if (z) {
                        this.f += b4.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + b(b4);
                    } else {
                        int i40 = this.f;
                        this.f = Math.max(i40, b4.getMeasuredWidth() + i40 + layoutParams3.leftMargin + layoutParams3.rightMargin + b(b4));
                    }
                    boolean z13 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i41 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = b4.getMeasuredHeight() + i41;
                    int max3 = Math.max(i38, measuredHeight2);
                    if (z13) {
                        i12 = i41;
                    } else {
                        i12 = measuredHeight2;
                    }
                    int max4 = Math.max(i35, i12);
                    boolean z14 = z12 && layoutParams3.height == -1;
                    if (z9) {
                        int baseline2 = b4.getBaseline();
                        if (baseline2 != -1) {
                            int i42 = ((((layoutParams3.h < 0 ? this.e : layoutParams3.h) & 112) >> 4) & -2) >> 1;
                            iArr[i42] = Math.max(iArr[i42], baseline2);
                            iArr2[i42] = Math.max(iArr2[i42], measuredHeight2 - baseline2);
                        }
                    }
                    f2 = f3;
                    i9 = max4;
                    z2 = z14;
                    i36 = i11;
                    i7 = i10;
                    i8 = max3;
                }
                i34++;
                i35 = i9;
                i38 = i8;
                z12 = z2;
                i37 = i7;
                f6 = f2;
            }
            this.f += getPaddingLeft() + getPaddingRight();
            if (!(iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1)) {
                i38 = Math.max(i38, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
            z6 = z12;
            i6 = i35;
            i20 = i36;
            i5 = i38;
        } else {
            int max5 = Math.max(i21, i22);
            if (z10 && mode != 1073741824) {
                int i43 = 0;
                while (true) {
                    int i44 = i43;
                    if (i44 >= virtualChildCount) {
                        break;
                    }
                    View b5 = b(i44);
                    if (!(b5 == null || b5.getVisibility() == 8 || ((LayoutParams) b5.getLayoutParams()).g <= 0.0f)) {
                        b5.measure(MeasureSpec.makeMeasureSpec(i23, 1073741824), MeasureSpec.makeMeasureSpec(b5.getMeasuredHeight(), 1073741824));
                    }
                    i43 = i44 + 1;
                }
            }
            i6 = max5;
            i5 = i4;
        }
        if (z6 || mode2 == 1073741824) {
            i6 = i5;
        }
        setMeasuredDimension((-16777216 & i20) | resolveSizeAndState, View.resolveSizeAndState(Math.max(i6 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, i20 << 16));
        if (z7) {
            d(virtualChildCount, i2);
        }
    }

    private void d(int i2, int i3) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View b2 = b(i4);
            if (b2.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i5 = layoutParams.width;
                    layoutParams.width = b2.getMeasuredWidth();
                    measureChildWithMargins(b2, i3, 0, makeMeasureSpec, 0);
                    layoutParams.width = i5;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int a(View view, int i2) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int d(int i2) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    /* access modifiers changed from: 0000 */
    public int a(View view) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int b(View view) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.d == 1) {
            a(i2, i3, i4, i5);
        } else {
            b(i2, i3, i4, i5);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3, int i4, int i5) {
        int paddingTop;
        int i6;
        int i7;
        int i8;
        int paddingLeft = getPaddingLeft();
        int i9 = i4 - i2;
        int paddingRight = i9 - getPaddingRight();
        int paddingRight2 = (i9 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i10 = this.e & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        switch (this.e & 112) {
            case 16:
                paddingTop = getPaddingTop() + (((i5 - i3) - this.f) / 2);
                break;
            case 80:
                paddingTop = ((getPaddingTop() + i5) - i3) - this.f;
                break;
            default:
                paddingTop = getPaddingTop();
                break;
        }
        int i11 = 0;
        int i12 = paddingTop;
        while (i11 < virtualChildCount) {
            View b2 = b(i11);
            if (b2 == null) {
                i12 += d(i11);
                i6 = i11;
            } else if (b2.getVisibility() != 8) {
                int measuredWidth = b2.getMeasuredWidth();
                int measuredHeight = b2.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                int i13 = layoutParams.h;
                if (i13 < 0) {
                    i13 = i10;
                }
                switch (GravityCompat.getAbsoluteGravity(i13, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        i7 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case 5:
                        i7 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i7 = paddingLeft + layoutParams.leftMargin;
                        break;
                }
                if (c(i11)) {
                    i8 = this.m + i12;
                } else {
                    i8 = i12;
                }
                int i14 = i8 + layoutParams.topMargin;
                a(b2, i7, i14 + a(b2), measuredWidth, measuredHeight);
                i12 = i14 + layoutParams.bottomMargin + measuredHeight + b(b2);
                i6 = a(b2, i11) + i11;
            } else {
                i6 = i11;
            }
            i11 = i6 + 1;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2, int i3, int i4, int i5) {
        int paddingLeft;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean a2 = bb.a(this);
        int paddingTop = getPaddingTop();
        int i12 = i5 - i3;
        int paddingBottom = i12 - getPaddingBottom();
        int paddingBottom2 = (i12 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i13 = this.e & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i14 = this.e & 112;
        boolean z = this.a;
        int[] iArr = this.i;
        int[] iArr2 = this.j;
        switch (GravityCompat.getAbsoluteGravity(i13, ViewCompat.getLayoutDirection(this))) {
            case 1:
                paddingLeft = getPaddingLeft() + (((i4 - i2) - this.f) / 2);
                break;
            case 5:
                paddingLeft = ((getPaddingLeft() + i4) - i2) - this.f;
                break;
            default:
                paddingLeft = getPaddingLeft();
                break;
        }
        if (a2) {
            i6 = -1;
            i7 = virtualChildCount - 1;
        } else {
            i6 = 1;
            i7 = 0;
        }
        int i15 = 0;
        while (i15 < virtualChildCount) {
            int i16 = i7 + (i6 * i15);
            View b2 = b(i16);
            if (b2 == null) {
                paddingLeft += d(i16);
                i8 = i15;
            } else if (b2.getVisibility() != 8) {
                int measuredWidth = b2.getMeasuredWidth();
                int measuredHeight = b2.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
                if (!z || layoutParams.height == -1) {
                    i9 = -1;
                } else {
                    i9 = b2.getBaseline();
                }
                int i17 = layoutParams.h;
                if (i17 < 0) {
                    i17 = i14;
                }
                switch (i17 & 112) {
                    case 16:
                        i10 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        break;
                    case 48:
                        i10 = paddingTop + layoutParams.topMargin;
                        if (i9 != -1) {
                            i10 += iArr[1] - i9;
                            break;
                        }
                        break;
                    case 80:
                        i10 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                        if (i9 != -1) {
                            i10 -= iArr2[2] - (b2.getMeasuredHeight() - i9);
                            break;
                        }
                        break;
                    default:
                        i10 = paddingTop;
                        break;
                }
                if (c(i16)) {
                    i11 = this.l + paddingLeft;
                } else {
                    i11 = paddingLeft;
                }
                int i18 = i11 + layoutParams.leftMargin;
                a(b2, i18 + a(b2), i10, measuredWidth, measuredHeight);
                paddingLeft = i18 + layoutParams.rightMargin + measuredWidth + b(b2);
                i8 = a(b2, i16) + i15;
            } else {
                i8 = i15;
            }
            i15 = i8 + 1;
        }
    }

    private void a(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i2 + i4, i3 + i5);
    }

    public void setOrientation(int i2) {
        if (this.d != i2) {
            this.d = i2;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.d;
    }

    public void setGravity(int i2) {
        int i3;
        if (this.e != i2) {
            if ((8388615 & i2) == 0) {
                i3 = 8388611 | i2;
            } else {
                i3 = i2;
            }
            if ((i3 & 112) == 0) {
                i3 |= 48;
            }
            this.e = i3;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.e;
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((this.e & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) != i3) {
            this.e = i3 | (this.e & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        if ((this.e & 112) != i3) {
            this.e = i3 | (this.e & -113);
            requestLayout();
        }
    }

    /* renamed from: b */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public LayoutParams generateDefaultLayoutParams() {
        if (this.d == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.d == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
