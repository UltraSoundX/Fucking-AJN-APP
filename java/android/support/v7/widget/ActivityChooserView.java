package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.p;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class ActivityChooserView extends ViewGroup {
    final a a;
    final FrameLayout b;
    final FrameLayout c;
    ActionProvider d;
    final DataSetObserver e;
    OnDismissListener f;
    boolean g;
    int h;
    private final b i;
    private final View j;
    private final Drawable k;
    private final ImageView l;
    private final ImageView m;
    private final int n;
    private final OnGlobalLayoutListener o;
    private ListPopupWindow p;

    /* renamed from: q reason: collision with root package name */
    private boolean f351q;
    private int r;

    public static class InnerLayout extends LinearLayout {
        private static final int[] a = {16842964};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            at a2 = at.a(context, attributeSet, a);
            setBackgroundDrawable(a2.a(0));
            a2.a();
        }
    }

    private class a extends BaseAdapter {
        private b b;
        private int c = 4;
        private boolean d;
        private boolean e;
        private boolean f;

        a() {
        }

        public void a(b bVar) {
            b e2 = ActivityChooserView.this.a.e();
            if (e2 != null && ActivityChooserView.this.isShown()) {
                e2.unregisterObserver(ActivityChooserView.this.e);
            }
            this.b = bVar;
            if (bVar != null && ActivityChooserView.this.isShown()) {
                bVar.registerObserver(ActivityChooserView.this.e);
            }
            notifyDataSetChanged();
        }

        public int getItemViewType(int i) {
            if (!this.f || i != getCount() - 1) {
                return 0;
            }
            return 1;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int getCount() {
            int a2 = this.b.a();
            if (!this.d && this.b.b() != null) {
                a2--;
            }
            int min = Math.min(a2, this.c);
            if (this.f) {
                return min + 1;
            }
            return min;
        }

        public Object getItem(int i) {
            switch (getItemViewType(i)) {
                case 0:
                    if (!this.d && this.b.b() != null) {
                        i++;
                    }
                    return this.b.a(i);
                case 1:
                    return null;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    if (view == null || view.getId() != R.id.list_item) {
                        view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    }
                    PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
                    ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
                    ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
                    ((TextView) view.findViewById(R.id.title)).setText(resolveInfo.loadLabel(packageManager));
                    if (!this.d || i != 0 || !this.e) {
                        view.setActivated(false);
                        return view;
                    }
                    view.setActivated(true);
                    return view;
                case 1:
                    if (view != null && view.getId() == 1) {
                        return view;
                    }
                    View inflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    inflate.setId(1);
                    ((TextView) inflate.findViewById(R.id.title)).setText(ActivityChooserView.this.getContext().getString(R.string.abc_activity_chooser_view_see_all));
                    return inflate;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public int a() {
            int i = this.c;
            this.c = Integer.MAX_VALUE;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int i2 = 0;
            for (int i3 = 0; i3 < count; i3++) {
                view = getView(i3, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i2 = Math.max(i2, view.getMeasuredWidth());
            }
            this.c = i;
            return i2;
        }

        public void a(int i) {
            if (this.c != i) {
                this.c = i;
                notifyDataSetChanged();
            }
        }

        public ResolveInfo b() {
            return this.b.b();
        }

        public void a(boolean z) {
            if (this.f != z) {
                this.f = z;
                notifyDataSetChanged();
            }
        }

        public int c() {
            return this.b.a();
        }

        public int d() {
            return this.b.c();
        }

        public b e() {
            return this.b;
        }

        public void a(boolean z, boolean z2) {
            if (this.d != z || this.e != z2) {
                this.d = z;
                this.e = z2;
                notifyDataSetChanged();
            }
        }

        public boolean f() {
            return this.d;
        }
    }

    private class b implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
        b() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (((a) adapterView.getAdapter()).getItemViewType(i)) {
                case 0:
                    ActivityChooserView.this.b();
                    if (!ActivityChooserView.this.g) {
                        if (!ActivityChooserView.this.a.f()) {
                            i++;
                        }
                        Intent b = ActivityChooserView.this.a.e().b(i);
                        if (b != null) {
                            b.addFlags(524288);
                            ActivityChooserView.this.getContext().startActivity(b);
                            return;
                        }
                        return;
                    } else if (i > 0) {
                        ActivityChooserView.this.a.e().c(i);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    ActivityChooserView.this.a(Integer.MAX_VALUE);
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void onClick(View view) {
            if (view == ActivityChooserView.this.c) {
                ActivityChooserView.this.b();
                Intent b = ActivityChooserView.this.a.e().b(ActivityChooserView.this.a.e().a(ActivityChooserView.this.a.b()));
                if (b != null) {
                    b.addFlags(524288);
                    ActivityChooserView.this.getContext().startActivity(b);
                }
            } else if (view == ActivityChooserView.this.b) {
                ActivityChooserView.this.g = false;
                ActivityChooserView.this.a(ActivityChooserView.this.h);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public boolean onLongClick(View view) {
            if (view == ActivityChooserView.this.c) {
                if (ActivityChooserView.this.a.getCount() > 0) {
                    ActivityChooserView.this.g = true;
                    ActivityChooserView.this.a(ActivityChooserView.this.h);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }

        public void onDismiss() {
            a();
            if (ActivityChooserView.this.d != null) {
                ActivityChooserView.this.d.subUiVisibilityChanged(false);
            }
        }

        private void a() {
            if (ActivityChooserView.this.f != null) {
                ActivityChooserView.this.f.onDismiss();
            }
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.e = new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.a.notifyDataSetChanged();
            }

            public void onInvalidated() {
                super.onInvalidated();
                ActivityChooserView.this.a.notifyDataSetInvalidated();
            }
        };
        this.o = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!ActivityChooserView.this.c()) {
                    return;
                }
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().e();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().d();
                if (ActivityChooserView.this.d != null) {
                    ActivityChooserView.this.d.subUiVisibilityChanged(true);
                }
            }
        };
        this.h = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActivityChooserView, i2, 0);
        this.h = obtainStyledAttributes.getInt(R.styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, this, true);
        this.i = new b();
        this.j = findViewById(R.id.activity_chooser_view_content);
        this.k = this.j.getBackground();
        this.c = (FrameLayout) findViewById(R.id.default_activity_button);
        this.c.setOnClickListener(this.i);
        this.c.setOnLongClickListener(this.i);
        this.m = (ImageView) this.c.findViewById(R.id.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.expand_activities_button);
        frameLayout.setOnClickListener(this.i);
        frameLayout.setAccessibilityDelegate(new AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCanOpenPopup(true);
            }
        });
        frameLayout.setOnTouchListener(new z(frameLayout) {
            public p a() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            /* access modifiers changed from: protected */
            public boolean b() {
                ActivityChooserView.this.a();
                return true;
            }

            /* access modifiers changed from: protected */
            public boolean c() {
                ActivityChooserView.this.b();
                return true;
            }
        });
        this.b = frameLayout;
        this.l = (ImageView) frameLayout.findViewById(R.id.image);
        this.l.setImageDrawable(drawable);
        this.a = new a();
        this.a.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.d();
            }
        });
        Resources resources = context.getResources();
        this.n = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    }

    public void setActivityChooserModel(b bVar) {
        this.a.a(bVar);
        if (c()) {
            b();
            a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.l.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.l.setContentDescription(getContext().getString(i2));
    }

    public void setProvider(ActionProvider actionProvider) {
        this.d = actionProvider;
    }

    public boolean a() {
        if (c() || !this.f351q) {
            return false;
        }
        this.g = false;
        a(this.h);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        int i3;
        if (this.a.e() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.o);
        boolean z = this.c.getVisibility() == 0;
        int c2 = this.a.c();
        if (z) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i2 == Integer.MAX_VALUE || c2 <= i3 + i2) {
            this.a.a(false);
            this.a.a(i2);
        } else {
            this.a.a(true);
            this.a.a(i2 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (!listPopupWindow.f()) {
            if (this.g || !z) {
                this.a.a(true, z);
            } else {
                this.a.a(false, false);
            }
            listPopupWindow.g(Math.min(this.a.a(), this.n));
            listPopupWindow.d();
            if (this.d != null) {
                this.d.subUiVisibilityChanged(true);
            }
            listPopupWindow.g().setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
            listPopupWindow.g().setSelector(new ColorDrawable(0));
        }
    }

    public boolean b() {
        if (c()) {
            getListPopupWindow().e();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.o);
            }
        }
        return true;
    }

    public boolean c() {
        return getListPopupWindow().f();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b e2 = this.a.e();
        if (e2 != null) {
            e2.registerObserver(this.e);
        }
        this.f351q = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b e2 = this.a.e();
        if (e2 != null) {
            e2.unregisterObserver(this.e);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.o);
        }
        if (c()) {
            b();
        }
        this.f351q = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        View view = this.j;
        if (this.c.getVisibility() != 0) {
            i3 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i3), 1073741824);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.j.layout(0, 0, i4 - i2, i5 - i3);
        if (!c()) {
            b();
        }
    }

    public b getDataModel() {
        return this.a.e();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public void setInitialActivityCount(int i2) {
        this.h = i2;
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.r = i2;
    }

    /* access modifiers changed from: 0000 */
    public ListPopupWindow getListPopupWindow() {
        if (this.p == null) {
            this.p = new ListPopupWindow(getContext());
            this.p.a((ListAdapter) this.a);
            this.p.b((View) this);
            this.p.a(true);
            this.p.a((OnItemClickListener) this.i);
            this.p.a((OnDismissListener) this.i);
        }
        return this.p;
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        if (this.a.getCount() > 0) {
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
        }
        int c2 = this.a.c();
        int d2 = this.a.d();
        if (c2 == 1 || (c2 > 1 && d2 > 0)) {
            this.c.setVisibility(0);
            ResolveInfo b2 = this.a.b();
            PackageManager packageManager = getContext().getPackageManager();
            this.m.setImageDrawable(b2.loadIcon(packageManager));
            if (this.r != 0) {
                CharSequence loadLabel = b2.loadLabel(packageManager);
                this.c.setContentDescription(getContext().getString(this.r, new Object[]{loadLabel}));
            }
        } else {
            this.c.setVisibility(8);
        }
        if (this.c.getVisibility() == 0) {
            this.j.setBackgroundDrawable(this.k);
        } else {
            this.j.setBackgroundDrawable(null);
        }
    }
}
