package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.p;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow implements p {
    private static Method a;
    private static Method b;
    private static Method h;
    private Drawable A;
    private OnItemClickListener B;
    private OnItemSelectedListener C;
    private final d D;
    private final c E;
    private final a F;
    private Runnable G;
    private final Rect H;
    private Rect I;
    private boolean J;
    w c;
    int d;
    final e e;
    final Handler f;
    PopupWindow g;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private boolean f352q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private View w;
    private int x;
    private DataSetObserver y;
    private View z;

    private class a implements Runnable {
        a() {
        }

        public void run() {
            ListPopupWindow.this.m();
        }
    }

    private class b extends DataSetObserver {
        b() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.f()) {
                ListPopupWindow.this.d();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.e();
        }
    }

    private class c implements OnScrollListener {
        c() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.n() && ListPopupWindow.this.g.getContentView() != null) {
                ListPopupWindow.this.f.removeCallbacks(ListPopupWindow.this.e);
                ListPopupWindow.this.e.run();
            }
        }
    }

    private class d implements OnTouchListener {
        d() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.g != null && ListPopupWindow.this.g.isShowing() && x >= 0 && x < ListPopupWindow.this.g.getWidth() && y >= 0 && y < ListPopupWindow.this.g.getHeight()) {
                ListPopupWindow.this.f.postDelayed(ListPopupWindow.this.e, 250);
            } else if (action == 1) {
                ListPopupWindow.this.f.removeCallbacks(ListPopupWindow.this.e);
            }
            return false;
        }
    }

    private class e implements Runnable {
        e() {
        }

        public void run() {
            if (ListPopupWindow.this.c != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.c) && ListPopupWindow.this.c.getCount() > ListPopupWindow.this.c.getChildCount() && ListPopupWindow.this.c.getChildCount() <= ListPopupWindow.this.d) {
                ListPopupWindow.this.g.setInputMethodMode(2);
                ListPopupWindow.this.d();
            }
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e2) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException e3) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            h = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException e4) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.k = -2;
        this.l = -2;
        this.o = PointerIconCompat.TYPE_HAND;
        this.f352q = true;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.d = Integer.MAX_VALUE;
        this.x = 0;
        this.e = new e();
        this.D = new d();
        this.E = new c();
        this.F = new a();
        this.H = new Rect();
        this.i = context;
        this.f = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i2, i3);
        this.m = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.n != 0) {
            this.p = true;
        }
        obtainStyledAttributes.recycle();
        this.g = new AppCompatPopupWindow(context, attributeSet, i2, i3);
        this.g.setInputMethodMode(1);
    }

    public void a(ListAdapter listAdapter) {
        if (this.y == null) {
            this.y = new b();
        } else if (this.j != null) {
            this.j.unregisterDataSetObserver(this.y);
        }
        this.j = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.y);
        }
        if (this.c != null) {
            this.c.setAdapter(this.j);
        }
    }

    public void a(int i2) {
        this.x = i2;
    }

    public void a(boolean z2) {
        this.J = z2;
        this.g.setFocusable(z2);
    }

    public boolean c() {
        return this.J;
    }

    public Drawable h() {
        return this.g.getBackground();
    }

    public void a(Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
    }

    public void b(int i2) {
        this.g.setAnimationStyle(i2);
    }

    public View i() {
        return this.z;
    }

    public void b(View view) {
        this.z = view;
    }

    public int j() {
        return this.m;
    }

    public void c(int i2) {
        this.m = i2;
    }

    public int k() {
        if (!this.p) {
            return 0;
        }
        return this.n;
    }

    public void d(int i2) {
        this.n = i2;
        this.p = true;
    }

    public void a(Rect rect) {
        this.I = rect;
    }

    public void e(int i2) {
        this.t = i2;
    }

    public int l() {
        return this.l;
    }

    public void f(int i2) {
        this.l = i2;
    }

    public void g(int i2) {
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            this.l = this.H.left + this.H.right + i2;
            return;
        }
        f(i2);
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.B = onItemClickListener;
    }

    public void d() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z2 = true;
        boolean z3 = false;
        int i7 = -1;
        int b2 = b();
        boolean n2 = n();
        PopupWindowCompat.setWindowLayoutType(this.g, this.o);
        if (!this.g.isShowing()) {
            if (this.l == -1) {
                i2 = -1;
            } else if (this.l == -2) {
                i2 = i().getWidth();
            } else {
                i2 = this.l;
            }
            if (this.k == -1) {
                b2 = -1;
            } else if (this.k != -2) {
                b2 = this.k;
            }
            this.g.setWidth(i2);
            this.g.setHeight(b2);
            c(true);
            PopupWindow popupWindow = this.g;
            if (this.v || this.u) {
                z2 = false;
            }
            popupWindow.setOutsideTouchable(z2);
            this.g.setTouchInterceptor(this.D);
            if (this.s) {
                PopupWindowCompat.setOverlapAnchor(this.g, this.r);
            }
            if (h != null) {
                try {
                    h.invoke(this.g, new Object[]{this.I});
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
            PopupWindowCompat.showAsDropDown(this.g, i(), this.m, this.n, this.t);
            this.c.setSelection(-1);
            if (!this.J || this.c.isInTouchMode()) {
                m();
            }
            if (!this.J) {
                this.f.post(this.F);
            }
        } else if (ViewCompat.isAttachedToWindow(i())) {
            if (this.l == -1) {
                i3 = -1;
            } else if (this.l == -2) {
                i3 = i().getWidth();
            } else {
                i3 = this.l;
            }
            if (this.k == -1) {
                if (!n2) {
                    b2 = -1;
                }
                if (n2) {
                    PopupWindow popupWindow2 = this.g;
                    if (this.l == -1) {
                        i6 = -1;
                    } else {
                        i6 = 0;
                    }
                    popupWindow2.setWidth(i6);
                    this.g.setHeight(0);
                    i4 = b2;
                } else {
                    PopupWindow popupWindow3 = this.g;
                    if (this.l == -1) {
                        i5 = -1;
                    } else {
                        i5 = 0;
                    }
                    popupWindow3.setWidth(i5);
                    this.g.setHeight(-1);
                    i4 = b2;
                }
            } else if (this.k == -2) {
                i4 = b2;
            } else {
                i4 = this.k;
            }
            PopupWindow popupWindow4 = this.g;
            if (!this.v && !this.u) {
                z3 = true;
            }
            popupWindow4.setOutsideTouchable(z3);
            PopupWindow popupWindow5 = this.g;
            View i8 = i();
            int i9 = this.m;
            int i10 = this.n;
            if (i3 < 0) {
                i3 = -1;
            }
            if (i4 >= 0) {
                i7 = i4;
            }
            popupWindow5.update(i8, i9, i10, i3, i7);
        }
    }

    public void e() {
        this.g.dismiss();
        a();
        this.g.setContentView(null);
        this.c = null;
        this.f.removeCallbacks(this.e);
    }

    public void a(OnDismissListener onDismissListener) {
        this.g.setOnDismissListener(onDismissListener);
    }

    private void a() {
        if (this.w != null) {
            ViewParent parent = this.w.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.w);
            }
        }
    }

    public void h(int i2) {
        this.g.setInputMethodMode(i2);
    }

    public void i(int i2) {
        w wVar = this.c;
        if (f() && wVar != null) {
            wVar.setListSelectionHidden(false);
            wVar.setSelection(i2);
            if (wVar.getChoiceMode() != 0) {
                wVar.setItemChecked(i2, true);
            }
        }
    }

    public void m() {
        w wVar = this.c;
        if (wVar != null) {
            wVar.setListSelectionHidden(true);
            wVar.requestLayout();
        }
    }

    public boolean f() {
        return this.g.isShowing();
    }

    public boolean n() {
        return this.g.getInputMethodMode() == 2;
    }

    public ListView g() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public w a(Context context, boolean z2) {
        return new w(context, z2);
    }

    private int b() {
        int i2;
        int i3;
        int makeMeasureSpec;
        View view;
        int i4;
        int i5;
        int i6;
        boolean z2 = true;
        if (this.c == null) {
            Context context = this.i;
            this.G = new Runnable() {
                public void run() {
                    View i = ListPopupWindow.this.i();
                    if (i != null && i.getWindowToken() != null) {
                        ListPopupWindow.this.d();
                    }
                }
            };
            this.c = a(context, !this.J);
            if (this.A != null) {
                this.c.setSelector(this.A);
            }
            this.c.setAdapter(this.j);
            this.c.setOnItemClickListener(this.B);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    if (i != -1) {
                        w wVar = ListPopupWindow.this.c;
                        if (wVar != null) {
                            wVar.setListSelectionHidden(false);
                        }
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.c.setOnScrollListener(this.E);
            if (this.C != null) {
                this.c.setOnItemSelectedListener(this.C);
            }
            View view2 = this.c;
            View view3 = this.w;
            if (view3 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LayoutParams layoutParams = new LayoutParams(-1, 0, 1.0f);
                switch (this.x) {
                    case 0:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams);
                        break;
                    case 1:
                        linearLayout.addView(view2, layoutParams);
                        linearLayout.addView(view3);
                        break;
                    default:
                        Log.e("ListPopupWindow", "Invalid hint position " + this.x);
                        break;
                }
                if (this.l >= 0) {
                    i6 = this.l;
                    i5 = Integer.MIN_VALUE;
                } else {
                    i5 = 0;
                    i6 = 0;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(i6, i5), 0);
                LayoutParams layoutParams2 = (LayoutParams) view3.getLayoutParams();
                i4 = layoutParams2.bottomMargin + view3.getMeasuredHeight() + layoutParams2.topMargin;
                view = linearLayout;
            } else {
                view = view2;
                i4 = 0;
            }
            this.g.setContentView(view);
            i2 = i4;
        } else {
            ViewGroup viewGroup = (ViewGroup) this.g.getContentView();
            View view4 = this.w;
            if (view4 != null) {
                LayoutParams layoutParams3 = (LayoutParams) view4.getLayoutParams();
                i2 = layoutParams3.bottomMargin + view4.getMeasuredHeight() + layoutParams3.topMargin;
            } else {
                i2 = 0;
            }
        }
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            int i7 = this.H.top + this.H.bottom;
            if (!this.p) {
                this.n = -this.H.top;
                i3 = i7;
            } else {
                i3 = i7;
            }
        } else {
            this.H.setEmpty();
            i3 = 0;
        }
        if (this.g.getInputMethodMode() != 2) {
            z2 = false;
        }
        int a2 = a(i(), this.n, z2);
        if (this.u || this.k == -1) {
            return a2 + i3;
        }
        switch (this.l) {
            case -2:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), ExploreByTouchHelper.INVALID_ID);
                break;
            case -1:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), 1073741824);
                break;
            default:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.l, 1073741824);
                break;
        }
        int a3 = this.c.a(makeMeasureSpec, 0, -1, a2 - i2, -1);
        if (a3 > 0) {
            i2 += this.c.getPaddingTop() + this.c.getPaddingBottom() + i3;
        }
        return a3 + i2;
    }

    public void b(boolean z2) {
        this.s = true;
        this.r = z2;
    }

    private void c(boolean z2) {
        if (a != null) {
            try {
                a.invoke(this.g, new Object[]{Boolean.valueOf(z2)});
            } catch (Exception e2) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int a(View view, int i2, boolean z2) {
        if (b != null) {
            try {
                return ((Integer) b.invoke(this.g, new Object[]{view, Integer.valueOf(i2), Boolean.valueOf(z2)})).intValue();
            } catch (Exception e2) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.g.getMaxAvailableHeight(view, i2);
    }
}
