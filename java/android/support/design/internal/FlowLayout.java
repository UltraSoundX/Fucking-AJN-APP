package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.R;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class FlowLayout extends ViewGroup {
    private int a;
    private int b;
    private boolean c;

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FlowLayout, 0, 0);
        this.a = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_lineSpacing, 0);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_itemSpacing, 0);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public int getLineSpacing() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void setLineSpacing(int i) {
        this.a = i;
    }

    /* access modifiers changed from: protected */
    public int getItemSpacing() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void setItemSpacing(int i) {
        this.b = i;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return this.c;
    }

    public void setSingleLine(boolean z) {
        this.c = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        int i5 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i5 - getPaddingRight();
        int i6 = 0;
        int i7 = paddingTop;
        int i8 = paddingLeft;
        int i9 = paddingTop;
        int i10 = 0;
        while (true) {
            int i11 = i6;
            if (i11 < getChildCount()) {
                View childAt = getChildAt(i11);
                if (childAt.getVisibility() != 8) {
                    measureChild(childAt, i, i2);
                    LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                        int i12 = 0 + marginLayoutParams.leftMargin;
                        i3 = marginLayoutParams.rightMargin + 0;
                        i4 = i12;
                    } else {
                        i3 = 0;
                        i4 = 0;
                    }
                    if (i8 + i4 + childAt.getMeasuredWidth() > paddingRight && !b()) {
                        i8 = getPaddingLeft();
                        i7 = this.a + i9;
                    }
                    int measuredWidth = i8 + i4 + childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight() + i7;
                    if (measuredWidth > i10) {
                        i10 = measuredWidth;
                    }
                    i8 += i3 + i4 + childAt.getMeasuredWidth() + this.b;
                    i9 = measuredHeight;
                }
                i6 = i11 + 1;
            } else {
                setMeasuredDimension(a(size, mode, i10), a(size2, mode2, i9));
                return;
            }
        }
    }

    private static int a(int i, int i2, int i3) {
        switch (i2) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                return Math.min(i3, i);
            case 1073741824:
                return i;
            default:
                return i3;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (getChildCount() != 0) {
            boolean z2 = ViewCompat.getLayoutDirection(this) == 1;
            int paddingLeft = z2 ? getPaddingRight() : getPaddingLeft();
            int paddingRight = z2 ? getPaddingLeft() : getPaddingRight();
            int paddingTop = getPaddingTop();
            int i7 = (i3 - i) - paddingRight;
            int i8 = 0;
            int i9 = paddingTop;
            int i10 = paddingTop;
            int i11 = paddingLeft;
            while (true) {
                int i12 = i8;
                if (i12 < getChildCount()) {
                    View childAt = getChildAt(i12);
                    if (childAt.getVisibility() != 8) {
                        LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof MarginLayoutParams) {
                            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                            i6 = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
                            i5 = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
                        } else {
                            i5 = 0;
                            i6 = 0;
                        }
                        int measuredWidth = i11 + i6 + childAt.getMeasuredWidth();
                        if (!this.c && measuredWidth > i7) {
                            i10 = this.a + i9;
                            i11 = paddingLeft;
                        }
                        int measuredWidth2 = childAt.getMeasuredWidth() + i11 + i6;
                        i9 = childAt.getMeasuredHeight() + i10;
                        if (z2) {
                            childAt.layout(i7 - measuredWidth2, i10, (i7 - i11) - i6, i9);
                        } else {
                            childAt.layout(i11 + i6, i10, measuredWidth2, i9);
                        }
                        i11 += i5 + i6 + childAt.getMeasuredWidth() + this.b;
                    }
                    i8 = i12 + 1;
                } else {
                    return;
                }
            }
        }
    }
}
