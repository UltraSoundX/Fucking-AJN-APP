package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.b;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.l;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView extends AbsActionBarView {
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private int f348q;

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat a(int i2, long j2) {
        return super.a(i2, j2);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        at a = at.a(context, attributeSet, R.styleable.ActionMode, i2, 0);
        ViewCompat.setBackground(this, a.a(R.styleable.ActionMode_background));
        this.n = a.g(R.styleable.ActionMode_titleTextStyle, 0);
        this.o = a.g(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.e = a.f(R.styleable.ActionMode_height, 0);
        this.f348q = a.g(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        a.a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.g();
            this.d.i();
        }
    }

    public void setContentHeight(int i2) {
        this.e = i2;
    }

    public void setCustomView(View view) {
        if (this.j != null) {
            removeView(this.j);
        }
        this.j = view;
        if (!(view == null || this.k == null)) {
            removeView(this.k);
            this.k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        this.g = charSequence;
        e();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.h = charSequence;
        e();
    }

    public CharSequence getTitle() {
        return this.g;
    }

    public CharSequence getSubtitle() {
        return this.h;
    }

    private void e() {
        int i2;
        int i3 = 8;
        boolean z = true;
        if (this.k == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            this.k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.l = (TextView) this.k.findViewById(R.id.action_bar_title);
            this.m = (TextView) this.k.findViewById(R.id.action_bar_subtitle);
            if (this.n != 0) {
                this.l.setTextAppearance(getContext(), this.n);
            }
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
        }
        this.l.setText(this.g);
        this.m.setText(this.h);
        boolean z2 = !TextUtils.isEmpty(this.g);
        if (TextUtils.isEmpty(this.h)) {
            z = false;
        }
        TextView textView = this.m;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        LinearLayout linearLayout = this.k;
        if (z2 || z) {
            i3 = 0;
        }
        linearLayout.setVisibility(i3);
        if (this.k.getParent() == null) {
            addView(this.k);
        }
    }

    public void a(final b bVar) {
        if (this.i == null) {
            this.i = LayoutInflater.from(getContext()).inflate(this.f348q, this, false);
            addView(this.i);
        } else if (this.i.getParent() == null) {
            addView(this.i);
        }
        this.i.findViewById(R.id.action_mode_close_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                bVar.c();
            }
        });
        MenuBuilder menuBuilder = (MenuBuilder) bVar.b();
        if (this.d != null) {
            this.d.h();
        }
        this.d = new ActionMenuPresenter(getContext());
        this.d.b(true);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        menuBuilder.a((l) this.d, this.b);
        this.c = (ActionMenuView) this.d.a((ViewGroup) this);
        ViewCompat.setBackground(this.c, null);
        addView(this.c, layoutParams);
    }

    public void b() {
        if (this.i == null) {
            c();
        }
    }

    public void c() {
        removeAllViews();
        this.j = null;
        this.c = null;
    }

    public boolean a() {
        if (this.d != null) {
            return this.d.f();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int size;
        int i4;
        int i5;
        int i6 = 1073741824;
        int i7 = 0;
        if (MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int size2 = MeasureSpec.getSize(i2);
            if (this.e > 0) {
                size = this.e;
            } else {
                size = MeasureSpec.getSize(i3);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size2 - getPaddingLeft()) - getPaddingRight();
            int i8 = size - paddingTop;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i8, ExploreByTouchHelper.INVALID_ID);
            if (this.i != null) {
                int a = a(this.i, paddingLeft, makeMeasureSpec, 0);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.rightMargin + marginLayoutParams.leftMargin);
            }
            if (this.c != null && this.c.getParent() == this) {
                paddingLeft = a(this.c, paddingLeft, makeMeasureSpec, 0);
            }
            if (this.k != null && this.j == null) {
                if (this.p) {
                    this.k.measure(MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.k.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.k.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = a(this.k, paddingLeft, makeMeasureSpec, 0);
                }
            }
            if (this.j != null) {
                LayoutParams layoutParams = this.j.getLayoutParams();
                if (layoutParams.width != -2) {
                    i4 = 1073741824;
                } else {
                    i4 = Integer.MIN_VALUE;
                }
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i6 = Integer.MIN_VALUE;
                }
                if (layoutParams.height >= 0) {
                    i5 = Math.min(layoutParams.height, i8);
                } else {
                    i5 = i8;
                }
                this.j.measure(MeasureSpec.makeMeasureSpec(paddingLeft, i4), MeasureSpec.makeMeasureSpec(i5, i6));
            }
            if (this.e <= 0) {
                int childCount = getChildCount();
                int i9 = 0;
                while (i7 < childCount) {
                    int measuredHeight = getChildAt(i7).getMeasuredHeight() + paddingTop;
                    if (measuredHeight <= i9) {
                        measuredHeight = i9;
                    }
                    i7++;
                    i9 = measuredHeight;
                }
                setMeasuredDimension(size2, i9);
                return;
            }
            setMeasuredDimension(size2, size);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        boolean a = bb.a(this);
        int paddingLeft = a ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        if (this.i == null || this.i.getVisibility() == 8) {
            i6 = paddingLeft;
        } else {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
            int i7 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i8 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a2 = a(paddingLeft, i7, a);
            i6 = a(a(this.i, a2, paddingTop, paddingTop2, a) + a2, i8, a);
        }
        if (!(this.k == null || this.j != null || this.k.getVisibility() == 8)) {
            i6 += a(this.k, i6, paddingTop, paddingTop2, a);
        }
        if (this.j != null) {
            int a3 = a(this.j, i6, paddingTop, paddingTop2, a) + i6;
        }
        int paddingRight = a ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        if (this.c != null) {
            int a4 = a(this.c, paddingRight, paddingTop, paddingTop2, !a) + paddingRight;
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.g);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.p) {
            requestLayout();
        }
        this.p = z;
    }

    public boolean d() {
        return this.p;
    }
}
