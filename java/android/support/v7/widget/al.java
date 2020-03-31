package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

/* compiled from: ScrollingTabContainerView */
public class al extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator j = new DecelerateInterpolator();
    Runnable a;
    LinearLayoutCompat b;
    int c;
    int d;
    private b e;
    private Spinner f;
    private boolean g;
    private int h;
    private int i;

    /* compiled from: ScrollingTabContainerView */
    private class a extends BaseAdapter {
        a() {
        }

        public int getCount() {
            return al.this.b.getChildCount();
        }

        public Object getItem(int i) {
            return ((c) al.this.b.getChildAt(i)).b();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return al.this.a((android.support.v7.app.ActionBar.b) getItem(i), true);
            }
            ((c) view).a((android.support.v7.app.ActionBar.b) getItem(i));
            return view;
        }
    }

    /* compiled from: ScrollingTabContainerView */
    private class b implements OnClickListener {
        b() {
        }

        public void onClick(View view) {
            boolean z;
            ((c) view).b().d();
            int childCount = al.this.b.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = al.this.b.getChildAt(i);
                if (childAt == view) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    /* compiled from: ScrollingTabContainerView */
    private class c extends LinearLayout {
        private final int[] b = {16842964};
        private android.support.v7.app.ActionBar.b c;
        private TextView d;
        private ImageView e;
        private View f;

        public c(Context context, android.support.v7.app.ActionBar.b bVar, boolean z) {
            super(context, null, R.attr.actionBarTabStyle);
            this.c = bVar;
            at a2 = at.a(context, null, this.b, R.attr.actionBarTabStyle, 0);
            if (a2.g(0)) {
                setBackgroundDrawable(a2.a(0));
            }
            a2.a();
            if (z) {
                setGravity(8388627);
            }
            a();
        }

        public void a(android.support.v7.app.ActionBar.b bVar) {
            this.c = bVar;
            a();
        }

        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.b.class.getName());
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (al.this.c > 0 && getMeasuredWidth() > al.this.c) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(al.this.c, 1073741824), i2);
            }
        }

        public void a() {
            boolean z;
            CharSequence e2;
            android.support.v7.app.ActionBar.b bVar = this.c;
            View c2 = bVar.c();
            if (c2 != null) {
                ViewParent parent = c2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(c2);
                    }
                    addView(c2);
                }
                this.f = c2;
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            if (this.f != null) {
                removeView(this.f);
                this.f = null;
            }
            Drawable a2 = bVar.a();
            CharSequence b2 = bVar.b();
            if (a2 != null) {
                if (this.e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.e = appCompatImageView;
                }
                this.e.setImageDrawable(a2);
                this.e.setVisibility(0);
            } else if (this.e != null) {
                this.e.setVisibility(8);
                this.e.setImageDrawable(null);
            }
            if (!TextUtils.isEmpty(b2)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (this.d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TruncateAt.END);
                    LayoutParams layoutParams2 = new LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.d = appCompatTextView;
                }
                this.d.setText(b2);
                this.d.setVisibility(0);
            } else if (this.d != null) {
                this.d.setVisibility(8);
                this.d.setText(null);
            }
            if (this.e != null) {
                this.e.setContentDescription(bVar.e());
            }
            if (z) {
                e2 = null;
            } else {
                e2 = bVar.e();
            }
            av.a(this, e2);
        }

        public android.support.v7.app.ActionBar.b b() {
            return this.c;
        }
    }

    public void onMeasure(int i2, int i3) {
        boolean z = true;
        int mode = MeasureSpec.getMode(i2);
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.b.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.c = -1;
        } else {
            if (childCount > 2) {
                this.c = (int) (((float) MeasureSpec.getSize(i2)) * 0.4f);
            } else {
                this.c = MeasureSpec.getSize(i2) / 2;
            }
            this.c = Math.min(this.c, this.d);
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (z2 || !this.g) {
            z = false;
        }
        if (z) {
            this.b.measure(0, makeMeasureSpec);
            if (this.b.getMeasuredWidth() > MeasureSpec.getSize(i2)) {
                b();
            } else {
                c();
            }
        } else {
            c();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i2, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (z2 && measuredWidth != measuredWidth2) {
            setTabSelected(this.i);
        }
    }

    private boolean a() {
        return this.f != null && this.f.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.g = z;
    }

    private void b() {
        if (!a()) {
            if (this.f == null) {
                this.f = d();
            }
            removeView(this.b);
            addView(this.f, new ViewGroup.LayoutParams(-2, -1));
            if (this.f.getAdapter() == null) {
                this.f.setAdapter(new a());
            }
            if (this.a != null) {
                removeCallbacks(this.a);
                this.a = null;
            }
            this.f.setSelection(this.i);
        }
    }

    private boolean c() {
        if (a()) {
            removeView(this.f);
            addView(this.b, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.f.getSelectedItemPosition());
        }
        return false;
    }

    public void setTabSelected(int i2) {
        boolean z;
        this.i = i2;
        int childCount = this.b.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.b.getChildAt(i3);
            if (i3 == i2) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                a(i2);
            }
        }
        if (this.f != null && i2 >= 0) {
            this.f.setSelection(i2);
        }
    }

    public void setContentHeight(int i2) {
        this.h = i2;
        requestLayout();
    }

    private Spinner d() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        android.support.v7.view.a a2 = android.support.v7.view.a.a(getContext());
        setContentHeight(a2.e());
        this.d = a2.g();
    }

    public void a(int i2) {
        final View childAt = this.b.getChildAt(i2);
        if (this.a != null) {
            removeCallbacks(this.a);
        }
        this.a = new Runnable() {
            public void run() {
                al.this.smoothScrollTo(childAt.getLeft() - ((al.this.getWidth() - childAt.getWidth()) / 2), 0);
                al.this.a = null;
            }
        };
        post(this.a);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    /* access modifiers changed from: 0000 */
    public c a(android.support.v7.app.ActionBar.b bVar, boolean z) {
        c cVar = new c(getContext(), bVar, z);
        if (z) {
            cVar.setBackgroundDrawable(null);
            cVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.h));
        } else {
            cVar.setFocusable(true);
            if (this.e == null) {
                this.e = new b();
            }
            cVar.setOnClickListener(this.e);
        }
        return cVar;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
        ((c) view).b().d();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
