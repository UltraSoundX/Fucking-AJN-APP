package com.zhy.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    protected List<List<View>> a;
    protected List<Integer> b;
    protected List<Integer> c;
    private int d;
    private List<View> e;

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.e = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.d = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_tag_gravity, -1);
        obtainStyledAttributes.recycle();
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int paddingLeft;
        int paddingTop;
        int measuredHeight;
        int i3;
        int i4;
        int i5;
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int childCount = getChildCount();
        int i10 = 0;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                measuredHeight = marginLayoutParams.bottomMargin + childAt.getMeasuredHeight() + marginLayoutParams.topMargin;
                if (i8 + measuredWidth > (size - getPaddingLeft()) - getPaddingRight()) {
                    int max = Math.max(i6, i8);
                    i4 = i7 + i9;
                    i3 = measuredWidth;
                    i5 = max;
                } else {
                    int i11 = i8 + measuredWidth;
                    measuredHeight = Math.max(i9, measuredHeight);
                    i3 = i11;
                    i4 = i7;
                    i5 = i6;
                }
                if (i10 == childCount - 1) {
                    i5 = Math.max(i3, i5);
                    i4 += measuredHeight;
                }
            } else if (i10 == childCount - 1) {
                int i12 = i7 + i9;
                i5 = Math.max(i8, i6);
                int i13 = i8;
                i4 = i12;
                measuredHeight = i9;
                i3 = i13;
            } else {
                measuredHeight = i9;
                i3 = i8;
                i4 = i7;
                i5 = i6;
            }
            i10++;
            i6 = i5;
            i7 = i4;
            i8 = i3;
            i9 = measuredHeight;
        }
        if (mode == 1073741824) {
            paddingLeft = size;
        } else {
            paddingLeft = getPaddingLeft() + i6 + getPaddingRight();
        }
        if (mode2 == 1073741824) {
            paddingTop = size2;
        } else {
            paddingTop = getPaddingTop() + i7 + getPaddingBottom();
        }
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft;
        int measuredWidth;
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.e.clear();
        int width = getWidth();
        int i5 = 0;
        int i6 = 0;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth2 = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredWidth2 + i5 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin > (width - getPaddingLeft()) - getPaddingRight()) {
                    this.b.add(Integer.valueOf(i6));
                    this.a.add(this.e);
                    this.c.add(Integer.valueOf(i5));
                    i5 = 0;
                    i6 = marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
                    this.e = new ArrayList();
                }
                i5 += measuredWidth2 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                i6 = Math.max(i6, marginLayoutParams.bottomMargin + marginLayoutParams.topMargin + measuredHeight);
                this.e.add(childAt);
            }
        }
        this.b.add(Integer.valueOf(i6));
        this.c.add(Integer.valueOf(i5));
        this.a.add(this.e);
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int size = this.a.size();
        int i8 = 0;
        int i9 = paddingTop;
        int i10 = paddingLeft2;
        while (i8 < size) {
            this.e = (List) this.a.get(i8);
            int intValue = ((Integer) this.b.get(i8)).intValue();
            int intValue2 = ((Integer) this.c.get(i8)).intValue();
            switch (this.d) {
                case -1:
                    paddingLeft = getPaddingLeft();
                    break;
                case 0:
                    paddingLeft = ((width - intValue2) / 2) + getPaddingLeft();
                    break;
                case 1:
                    paddingLeft = (width - intValue2) + getPaddingLeft();
                    break;
                default:
                    paddingLeft = i10;
                    break;
            }
            int i11 = 0;
            int i12 = paddingLeft;
            while (i11 < this.e.size()) {
                View view = (View) this.e.get(i11);
                if (view.getVisibility() == 8) {
                    measuredWidth = i12;
                } else {
                    MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) view.getLayoutParams();
                    int i13 = marginLayoutParams2.leftMargin + i12;
                    int i14 = marginLayoutParams2.topMargin + i9;
                    view.layout(i13, i14, view.getMeasuredWidth() + i13, view.getMeasuredHeight() + i14);
                    measuredWidth = i12 + view.getMeasuredWidth() + marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin;
                }
                i11++;
                i12 = measuredWidth;
            }
            i8++;
            i9 += intValue;
            i10 = i12;
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new MarginLayoutParams(layoutParams);
    }
}
