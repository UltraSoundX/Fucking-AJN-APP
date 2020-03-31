package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.m;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutCompat implements android.support.v7.view.menu.MenuBuilder.b, m {
    android.support.v7.view.menu.MenuBuilder.a a;
    d b;
    private MenuBuilder c;
    private Context d;
    private int e;
    private boolean f;
    private ActionMenuPresenter g;
    private android.support.v7.view.menu.l.a h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    public static class LayoutParams extends android.support.v7.widget.LinearLayoutCompat.LayoutParams {
        @ExportedProperty
        public boolean a;
        @ExportedProperty
        public int b;
        @ExportedProperty
        public int c;
        @ExportedProperty
        public boolean d;
        @ExportedProperty
        public boolean e;
        boolean f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.a = layoutParams.a;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.a = false;
        }
    }

    public interface a {
        boolean c();

        boolean d();
    }

    private static class b implements android.support.v7.view.menu.l.a {
        b() {
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean a(MenuBuilder menuBuilder) {
            return false;
        }
    }

    private class c implements android.support.v7.view.menu.MenuBuilder.a {
        c() {
        }

        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return ActionMenuView.this.b != null && ActionMenuView.this.b.a(menuItem);
        }

        public void a(MenuBuilder menuBuilder) {
            if (ActionMenuView.this.a != null) {
                ActionMenuView.this.a.a(menuBuilder);
            }
        }
    }

    public interface d {
        boolean a(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.k = (int) (56.0f * f2);
        this.l = (int) (f2 * 4.0f);
        this.d = context;
        this.e = 0;
    }

    public void setPopupTheme(int i2) {
        if (this.e != i2) {
            this.e = i2;
            if (i2 == 0) {
                this.d = getContext();
            } else {
                this.d = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public int getPopupTheme() {
        return this.e;
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.g = actionMenuPresenter;
        this.g.a(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.g != null) {
            this.g.a(false);
            if (this.g.j()) {
                this.g.g();
                this.g.f();
            }
        }
    }

    public void setOnMenuItemClickListener(d dVar) {
        this.b = dVar;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        boolean z = this.i;
        this.i = MeasureSpec.getMode(i2) == 1073741824;
        if (z != this.i) {
            this.j = 0;
        }
        int size = MeasureSpec.getSize(i2);
        if (!(!this.i || this.c == null || size == this.j)) {
            this.j = size;
            this.c.a(true);
        }
        int childCount = getChildCount();
        if (!this.i || childCount <= 0) {
            for (int i4 = 0; i4 < childCount; i4++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            super.onMeasure(i2, i3);
            return;
        }
        c(i2, i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01dc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(int r35, int r36) {
        /*
            r34 = this;
            int r23 = android.view.View.MeasureSpec.getMode(r36)
            int r6 = android.view.View.MeasureSpec.getSize(r35)
            int r17 = android.view.View.MeasureSpec.getSize(r36)
            int r7 = r34.getPaddingLeft()
            int r8 = r34.getPaddingRight()
            int r7 = r7 + r8
            int r8 = r34.getPaddingTop()
            int r9 = r34.getPaddingBottom()
            int r19 = r8 + r9
            r8 = -2
            r0 = r36
            r1 = r19
            int r24 = getChildMeasureSpec(r0, r1, r8)
            int r25 = r6 - r7
            r0 = r34
            int r6 = r0.k
            int r9 = r25 / r6
            r0 = r34
            int r6 = r0.k
            int r6 = r25 % r6
            if (r9 != 0) goto L_0x0041
            r6 = 0
            r0 = r34
            r1 = r25
            r0.setMeasuredDimension(r1, r6)
        L_0x0040:
            return
        L_0x0041:
            r0 = r34
            int r7 = r0.k
            int r6 = r6 / r9
            int r26 = r7 + r6
            r16 = 0
            r15 = 0
            r10 = 0
            r7 = 0
            r11 = 0
            r12 = 0
            int r27 = r34.getChildCount()
            r6 = 0
            r18 = r6
        L_0x0057:
            r0 = r18
            r1 = r27
            if (r0 >= r1) goto L_0x0103
            r0 = r34
            r1 = r18
            android.view.View r8 = r0.getChildAt(r1)
            int r6 = r8.getVisibility()
            r14 = 8
            if (r6 != r14) goto L_0x007e
            r8 = r7
            r6 = r12
            r12 = r16
            r13 = r9
            r9 = r15
        L_0x0073:
            int r14 = r18 + 1
            r18 = r14
            r15 = r9
            r16 = r12
            r9 = r13
            r12 = r6
            r7 = r8
            goto L_0x0057
        L_0x007e:
            boolean r0 = r8 instanceof android.support.v7.view.menu.ActionMenuItemView
            r20 = r0
            int r14 = r7 + 1
            if (r20 == 0) goto L_0x009a
            r0 = r34
            int r6 = r0.l
            r7 = 0
            r0 = r34
            int r0 = r0.l
            r21 = r0
            r22 = 0
            r0 = r21
            r1 = r22
            r8.setPadding(r6, r7, r0, r1)
        L_0x009a:
            android.view.ViewGroup$LayoutParams r6 = r8.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            r7 = 0
            r6.f = r7
            r7 = 0
            r6.c = r7
            r7 = 0
            r6.b = r7
            r7 = 0
            r6.d = r7
            r7 = 0
            r6.leftMargin = r7
            r7 = 0
            r6.rightMargin = r7
            if (r20 == 0) goto L_0x00ff
            r7 = r8
            android.support.v7.view.menu.ActionMenuItemView r7 = (android.support.v7.view.menu.ActionMenuItemView) r7
            boolean r7 = r7.b()
            if (r7 == 0) goto L_0x00ff
            r7 = 1
        L_0x00be:
            r6.e = r7
            boolean r7 = r6.a
            if (r7 == 0) goto L_0x0101
            r7 = 1
        L_0x00c5:
            r0 = r26
            r1 = r24
            r2 = r19
            int r20 = a(r8, r0, r7, r1, r2)
            r0 = r20
            int r15 = java.lang.Math.max(r15, r0)
            boolean r7 = r6.d
            if (r7 == 0) goto L_0x031f
            int r7 = r10 + 1
        L_0x00db:
            boolean r6 = r6.a
            if (r6 == 0) goto L_0x031c
            r6 = 1
        L_0x00e0:
            int r11 = r9 - r20
            int r8 = r8.getMeasuredHeight()
            r0 = r16
            int r10 = java.lang.Math.max(r0, r8)
            r8 = 1
            r0 = r20
            if (r0 != r8) goto L_0x0310
            r8 = 1
            int r8 = r8 << r18
            long r8 = (long) r8
            long r8 = r8 | r12
            r12 = r10
            r13 = r11
            r10 = r7
            r11 = r6
            r6 = r8
            r9 = r15
            r8 = r14
            goto L_0x0073
        L_0x00ff:
            r7 = 0
            goto L_0x00be
        L_0x0101:
            r7 = r9
            goto L_0x00c5
        L_0x0103:
            if (r11 == 0) goto L_0x0140
            r6 = 2
            if (r7 != r6) goto L_0x0140
            r6 = 1
            r8 = r6
        L_0x010a:
            r18 = 0
            r20 = r12
            r19 = r9
        L_0x0110:
            if (r10 <= 0) goto L_0x030c
            if (r19 <= 0) goto L_0x030c
            r14 = 2147483647(0x7fffffff, float:NaN)
            r12 = 0
            r9 = 0
            r6 = 0
            r22 = r6
        L_0x011d:
            r0 = r22
            r1 = r27
            if (r0 >= r1) goto L_0x0161
            r0 = r34
            r1 = r22
            android.view.View r6 = r0.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            boolean r0 = r6.d
            r28 = r0
            if (r28 != 0) goto L_0x0143
            r6 = r9
            r9 = r14
        L_0x0139:
            int r14 = r22 + 1
            r22 = r14
            r14 = r9
            r9 = r6
            goto L_0x011d
        L_0x0140:
            r6 = 0
            r8 = r6
            goto L_0x010a
        L_0x0143:
            int r0 = r6.b
            r28 = r0
            r0 = r28
            if (r0 >= r14) goto L_0x0153
            int r9 = r6.b
            r12 = 1
            long r12 = r12 << r22
            r6 = 1
            goto L_0x0139
        L_0x0153:
            int r6 = r6.b
            if (r6 != r14) goto L_0x0308
            r28 = 1
            long r28 = r28 << r22
            long r12 = r12 | r28
            int r6 = r9 + 1
            r9 = r14
            goto L_0x0139
        L_0x0161:
            long r20 = r20 | r12
            r0 = r19
            if (r9 <= r0) goto L_0x01ec
            r12 = r20
        L_0x0169:
            if (r11 != 0) goto L_0x0271
            r6 = 1
            if (r7 != r6) goto L_0x0271
            r6 = 1
        L_0x016f:
            if (r19 <= 0) goto L_0x02bd
            r8 = 0
            int r8 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r8 == 0) goto L_0x02bd
            int r7 = r7 + -1
            r0 = r19
            if (r0 < r7) goto L_0x0182
            if (r6 != 0) goto L_0x0182
            r7 = 1
            if (r15 <= r7) goto L_0x02bd
        L_0x0182:
            int r7 = java.lang.Long.bitCount(r12)
            float r7 = (float) r7
            if (r6 != 0) goto L_0x0302
            r8 = 1
            long r8 = r8 & r12
            r10 = 0
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x01a6
            r6 = 0
            r0 = r34
            android.view.View r6 = r0.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            boolean r6 = r6.e
            if (r6 != 0) goto L_0x01a6
            r6 = 1056964608(0x3f000000, float:0.5)
            float r7 = r7 - r6
        L_0x01a6:
            r6 = 1
            int r8 = r27 + -1
            int r6 = r6 << r8
            long r8 = (long) r6
            long r8 = r8 & r12
            r10 = 0
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x0302
            int r6 = r27 + -1
            r0 = r34
            android.view.View r6 = r0.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            boolean r6 = r6.e
            if (r6 != 0) goto L_0x0302
            r6 = 1056964608(0x3f000000, float:0.5)
            float r6 = r7 - r6
        L_0x01c8:
            r7 = 0
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x0274
            int r7 = r19 * r26
            float r7 = (float) r7
            float r6 = r7 / r6
            int r6 = (int) r6
            r7 = r6
        L_0x01d4:
            r6 = 0
            r9 = r6
            r8 = r18
        L_0x01d8:
            r0 = r27
            if (r9 >= r0) goto L_0x02bf
            r6 = 1
            int r6 = r6 << r9
            long r10 = (long) r6
            long r10 = r10 & r12
            r14 = 0
            int r6 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r6 != 0) goto L_0x0278
            r6 = r8
        L_0x01e7:
            int r8 = r9 + 1
            r9 = r8
            r8 = r6
            goto L_0x01d8
        L_0x01ec:
            int r22 = r14 + 1
            r6 = 0
            r14 = r6
            r9 = r19
            r18 = r20
        L_0x01f4:
            r0 = r27
            if (r14 >= r0) goto L_0x0268
            r0 = r34
            android.view.View r20 = r0.getChildAt(r14)
            android.view.ViewGroup$LayoutParams r6 = r20.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            r21 = 1
            int r21 = r21 << r14
            r0 = r21
            long r0 = (long) r0
            r28 = r0
            long r28 = r28 & r12
            r30 = 0
            int r21 = (r28 > r30 ? 1 : (r28 == r30 ? 0 : -1))
            if (r21 != 0) goto L_0x0228
            int r6 = r6.b
            r0 = r22
            if (r6 != r0) goto L_0x0305
            r6 = 1
            int r6 = r6 << r14
            long r0 = (long) r6
            r20 = r0
            long r18 = r18 | r20
            r6 = r9
        L_0x0223:
            int r9 = r14 + 1
            r14 = r9
            r9 = r6
            goto L_0x01f4
        L_0x0228:
            if (r8 == 0) goto L_0x0255
            boolean r0 = r6.e
            r21 = r0
            if (r21 == 0) goto L_0x0255
            r21 = 1
            r0 = r21
            if (r9 != r0) goto L_0x0255
            r0 = r34
            int r0 = r0.l
            r21 = r0
            int r21 = r21 + r26
            r28 = 0
            r0 = r34
            int r0 = r0.l
            r29 = r0
            r30 = 0
            r0 = r20
            r1 = r21
            r2 = r28
            r3 = r29
            r4 = r30
            r0.setPadding(r1, r2, r3, r4)
        L_0x0255:
            int r0 = r6.b
            r20 = r0
            int r20 = r20 + 1
            r0 = r20
            r6.b = r0
            r20 = 1
            r0 = r20
            r6.f = r0
            int r6 = r9 + -1
            goto L_0x0223
        L_0x0268:
            r6 = 1
            r20 = r18
            r18 = r6
            r19 = r9
            goto L_0x0110
        L_0x0271:
            r6 = 0
            goto L_0x016f
        L_0x0274:
            r6 = 0
            r7 = r6
            goto L_0x01d4
        L_0x0278:
            r0 = r34
            android.view.View r10 = r0.getChildAt(r9)
            android.view.ViewGroup$LayoutParams r6 = r10.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            boolean r10 = r10 instanceof android.support.v7.view.menu.ActionMenuItemView
            if (r10 == 0) goto L_0x029b
            r6.c = r7
            r8 = 1
            r6.f = r8
            if (r9 != 0) goto L_0x0298
            boolean r8 = r6.e
            if (r8 != 0) goto L_0x0298
            int r8 = -r7
            int r8 = r8 / 2
            r6.leftMargin = r8
        L_0x0298:
            r6 = 1
            goto L_0x01e7
        L_0x029b:
            boolean r10 = r6.a
            if (r10 == 0) goto L_0x02ac
            r6.c = r7
            r8 = 1
            r6.f = r8
            int r8 = -r7
            int r8 = r8 / 2
            r6.rightMargin = r8
            r6 = 1
            goto L_0x01e7
        L_0x02ac:
            if (r9 == 0) goto L_0x02b2
            int r10 = r7 / 2
            r6.leftMargin = r10
        L_0x02b2:
            int r10 = r27 + -1
            if (r9 == r10) goto L_0x02ba
            int r10 = r7 / 2
            r6.rightMargin = r10
        L_0x02ba:
            r6 = r8
            goto L_0x01e7
        L_0x02bd:
            r8 = r18
        L_0x02bf:
            if (r8 == 0) goto L_0x02ee
            r6 = 0
            r7 = r6
        L_0x02c3:
            r0 = r27
            if (r7 >= r0) goto L_0x02ee
            r0 = r34
            android.view.View r8 = r0.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r6 = r8.getLayoutParams()
            android.support.v7.widget.ActionMenuView$LayoutParams r6 = (android.support.v7.widget.ActionMenuView.LayoutParams) r6
            boolean r9 = r6.f
            if (r9 != 0) goto L_0x02db
        L_0x02d7:
            int r6 = r7 + 1
            r7 = r6
            goto L_0x02c3
        L_0x02db:
            int r9 = r6.b
            int r9 = r9 * r26
            int r6 = r6.c
            int r6 = r6 + r9
            r9 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r9)
            r0 = r24
            r8.measure(r6, r0)
            goto L_0x02d7
        L_0x02ee:
            r6 = 1073741824(0x40000000, float:2.0)
            r0 = r23
            if (r0 == r6) goto L_0x02ff
        L_0x02f4:
            r0 = r34
            r1 = r25
            r2 = r16
            r0.setMeasuredDimension(r1, r2)
            goto L_0x0040
        L_0x02ff:
            r16 = r17
            goto L_0x02f4
        L_0x0302:
            r6 = r7
            goto L_0x01c8
        L_0x0305:
            r6 = r9
            goto L_0x0223
        L_0x0308:
            r6 = r9
            r9 = r14
            goto L_0x0139
        L_0x030c:
            r12 = r20
            goto L_0x0169
        L_0x0310:
            r8 = r14
            r9 = r15
            r32 = r12
            r12 = r10
            r13 = r11
            r11 = r6
            r10 = r7
            r6 = r32
            goto L_0x0073
        L_0x031c:
            r6 = r11
            goto L_0x00e0
        L_0x031f:
            r7 = r10
            goto L_0x00db
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuView.c(int, int):void");
    }

    static int a(View view, int i2, int i3, int i4, int i5) {
        boolean z;
        int i6;
        boolean z2 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i4) - i5, MeasureSpec.getMode(i4));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        if (actionMenuItemView == null || !actionMenuItemView.b()) {
            z = false;
        } else {
            z = true;
        }
        if (i3 <= 0 || (z && i3 < 2)) {
            i6 = 0;
        } else {
            view.measure(MeasureSpec.makeMeasureSpec(i2 * i3, ExploreByTouchHelper.INVALID_ID), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            i6 = measuredWidth / i2;
            if (measuredWidth % i2 != 0) {
                i6++;
            }
            if (z && i6 < 2) {
                i6 = 2;
            }
        }
        if (!layoutParams.a && z) {
            z2 = true;
        }
        layoutParams.d = z2;
        layoutParams.b = i6;
        view.measure(MeasureSpec.makeMeasureSpec(i6 * i2, 1073741824), makeMeasureSpec);
        return i6;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int width;
        int i11;
        if (!this.i) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i12 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i13 = 0;
        int i14 = 0;
        int paddingRight = ((i4 - i2) - getPaddingRight()) - getPaddingLeft();
        boolean z3 = false;
        boolean a2 = bb.a(this);
        int i15 = 0;
        while (i15 < childCount) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() == 8) {
                z2 = z3;
                i9 = i14;
                i8 = paddingRight;
                i10 = i13;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (a(i15)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a2) {
                        i11 = layoutParams.leftMargin + getPaddingLeft();
                        width = i11 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        i11 = width - measuredWidth;
                    }
                    int i16 = i12 - (measuredHeight / 2);
                    childAt.layout(i11, i16, width, measuredHeight + i16);
                    i8 = paddingRight - measuredWidth;
                    z2 = true;
                    i9 = i14;
                    i10 = i13;
                } else {
                    int measuredWidth2 = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    int i17 = i13 + measuredWidth2;
                    int i18 = paddingRight - measuredWidth2;
                    if (a(i15)) {
                        i17 += dividerWidth;
                    }
                    boolean z4 = z3;
                    i8 = i18;
                    i9 = i14 + 1;
                    i10 = i17;
                    z2 = z4;
                }
            }
            i15++;
            i13 = i10;
            paddingRight = i8;
            i14 = i9;
            z3 = z2;
        }
        if (childCount != 1 || z3) {
            int i19 = i14 - (z3 ? 0 : 1);
            int max = Math.max(0, i19 > 0 ? paddingRight / i19 : 0);
            if (a2) {
                int width2 = getWidth() - getPaddingRight();
                int i20 = 0;
                while (i20 < childCount) {
                    View childAt2 = getChildAt(i20);
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() == 8) {
                        i7 = width2;
                    } else if (layoutParams2.a) {
                        i7 = width2;
                    } else {
                        int i21 = width2 - layoutParams2.rightMargin;
                        int measuredWidth3 = childAt2.getMeasuredWidth();
                        int measuredHeight2 = childAt2.getMeasuredHeight();
                        int i22 = i12 - (measuredHeight2 / 2);
                        childAt2.layout(i21 - measuredWidth3, i22, i21, measuredHeight2 + i22);
                        i7 = i21 - ((layoutParams2.leftMargin + measuredWidth3) + max);
                    }
                    i20++;
                    width2 = i7;
                }
                return;
            }
            int paddingLeft = getPaddingLeft();
            int i23 = 0;
            while (i23 < childCount) {
                View childAt3 = getChildAt(i23);
                LayoutParams layoutParams3 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() == 8) {
                    i6 = paddingLeft;
                } else if (layoutParams3.a) {
                    i6 = paddingLeft;
                } else {
                    int i24 = paddingLeft + layoutParams3.leftMargin;
                    int measuredWidth4 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i25 = i12 - (measuredHeight3 / 2);
                    childAt3.layout(i24, i25, i24 + measuredWidth4, measuredHeight3 + i25);
                    i6 = layoutParams3.rightMargin + measuredWidth4 + max + i24;
                }
                i23++;
                paddingLeft = i6;
            }
            return;
        }
        View childAt4 = getChildAt(0);
        int measuredWidth5 = childAt4.getMeasuredWidth();
        int measuredHeight4 = childAt4.getMeasuredHeight();
        int i26 = ((i4 - i2) / 2) - (measuredWidth5 / 2);
        int i27 = i12 - (measuredHeight4 / 2);
        childAt4.layout(i26, i27, measuredWidth5 + i26, measuredHeight4 + i27);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.g.a(drawable);
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.g.e();
    }

    public boolean a() {
        return this.f;
    }

    public void setOverflowReserved(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public LayoutParams j() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.h = 16;
        return layoutParams;
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return j();
        }
        LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
        if (layoutParams2.h > 0) {
            return layoutParams2;
        }
        layoutParams2.h = 16;
        return layoutParams2;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public LayoutParams c() {
        LayoutParams b2 = j();
        b2.a = true;
        return b2;
    }

    public boolean a(MenuItemImpl menuItemImpl) {
        return this.c.a((MenuItem) menuItemImpl, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void a(MenuBuilder menuBuilder) {
        this.c = menuBuilder;
    }

    public Menu getMenu() {
        if (this.c == null) {
            Context context = getContext();
            this.c = new MenuBuilder(context);
            this.c.a((android.support.v7.view.menu.MenuBuilder.a) new c());
            this.g = new ActionMenuPresenter(context);
            this.g.b(true);
            this.g.a(this.h != null ? this.h : new b());
            this.c.a((l) this.g, this.d);
            this.g.a(this);
        }
        return this.c;
    }

    public void a(android.support.v7.view.menu.l.a aVar, android.support.v7.view.menu.MenuBuilder.a aVar2) {
        this.h = aVar;
        this.a = aVar2;
    }

    public MenuBuilder d() {
        return this.c;
    }

    public boolean e() {
        return this.g != null && this.g.f();
    }

    public boolean f() {
        return this.g != null && this.g.g();
    }

    public boolean g() {
        return this.g != null && this.g.j();
    }

    public boolean h() {
        return this.g != null && this.g.k();
    }

    public void i() {
        if (this.g != null) {
            this.g.h();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i2) {
        boolean z = false;
        if (i2 == 0) {
            return false;
        }
        View childAt = getChildAt(i2 - 1);
        View childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof a)) {
            z = false | ((a) childAt).d();
        }
        return (i2 <= 0 || !(childAt2 instanceof a)) ? z : ((a) childAt2).c() | z;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.g.c(z);
    }
}
