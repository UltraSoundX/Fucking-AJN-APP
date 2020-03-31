package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.appcompat.R;
import android.support.v7.view.c;
import android.support.v7.view.g;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.l;
import android.support.v7.widget.ActionMenuView.d;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    private final d H;
    private au I;
    private ActionMenuPresenter J;
    private a K;
    private android.support.v7.view.menu.l.a L;
    private android.support.v7.view.menu.MenuBuilder.a M;
    private boolean N;
    private final Runnable O;
    private ActionMenuView a;
    ImageButton b;
    View c;
    int d;
    b e;
    private TextView f;
    private TextView g;
    private ImageButton h;
    private ImageView i;
    private Drawable j;
    private CharSequence k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;

    /* renamed from: q reason: collision with root package name */
    private int f358q;
    private int r;
    private int s;
    private int t;
    private aj u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    public static class LayoutParams extends android.support.v7.app.ActionBar.LayoutParams {
        int b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = 0;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.b = 0;
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
            this.b = 0;
            this.b = layoutParams.b;
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super((android.view.ViewGroup.LayoutParams) marginLayoutParams);
            this.b = 0;
            a(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    public static class SavedState extends AbsSavedState {
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

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b ? 1 : 0);
        }
    }

    private class a implements l {
        MenuBuilder a;
        MenuItemImpl b;

        a() {
        }

        public void a(Context context, MenuBuilder menuBuilder) {
            if (!(this.a == null || this.b == null)) {
                this.a.d(this.b);
            }
            this.a = menuBuilder;
        }

        public void a(boolean z) {
            boolean z2 = false;
            if (this.b != null) {
                if (this.a != null) {
                    int size = this.a.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.a.getItem(i) == this.b) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!z2) {
                    b(this.a, this.b);
                }
            }
        }

        public void a(android.support.v7.view.menu.l.a aVar) {
        }

        public boolean a(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean a() {
            return false;
        }

        public boolean a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            Toolbar.this.i();
            ViewParent parent = Toolbar.this.b.getParent();
            if (parent != Toolbar.this) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(Toolbar.this.b);
                }
                Toolbar.this.addView(Toolbar.this.b);
            }
            Toolbar.this.c = menuItemImpl.getActionView();
            this.b = menuItemImpl;
            ViewParent parent2 = Toolbar.this.c.getParent();
            if (parent2 != Toolbar.this) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(Toolbar.this.c);
                }
                LayoutParams j = Toolbar.this.generateDefaultLayoutParams();
                j.a = 8388611 | (Toolbar.this.d & 112);
                j.b = 2;
                Toolbar.this.c.setLayoutParams(j);
                Toolbar.this.addView(Toolbar.this.c);
            }
            Toolbar.this.k();
            Toolbar.this.requestLayout();
            menuItemImpl.e(true);
            if (Toolbar.this.c instanceof c) {
                ((c) Toolbar.this.c).a();
            }
            return true;
        }

        public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (Toolbar.this.c instanceof c) {
                ((c) Toolbar.this.c).b();
            }
            Toolbar.this.removeView(Toolbar.this.c);
            Toolbar.this.removeView(Toolbar.this.b);
            Toolbar.this.c = null;
            Toolbar.this.l();
            this.b = null;
            Toolbar.this.requestLayout();
            menuItemImpl.e(false);
            return true;
        }

        public int b() {
            return 0;
        }

        public Parcelable c() {
            return null;
        }

        public void a(Parcelable parcelable) {
        }
    }

    public interface b {
        boolean a(MenuItem menuItem);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.H = new d() {
            public boolean a(MenuItem menuItem) {
                if (Toolbar.this.e != null) {
                    return Toolbar.this.e.a(menuItem);
                }
                return false;
            }
        };
        this.O = new Runnable() {
            public void run() {
                Toolbar.this.d();
            }
        };
        at a2 = at.a(getContext(), attributeSet, R.styleable.Toolbar, i2, 0);
        this.n = a2.g(R.styleable.Toolbar_titleTextAppearance, 0);
        this.o = a2.g(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.x = a2.c(R.styleable.Toolbar_android_gravity, this.x);
        this.d = a2.c(R.styleable.Toolbar_buttonGravity, 48);
        int d2 = a2.d(R.styleable.Toolbar_titleMargin, 0);
        if (a2.g(R.styleable.Toolbar_titleMargins)) {
            d2 = a2.d(R.styleable.Toolbar_titleMargins, d2);
        }
        this.t = d2;
        this.s = d2;
        this.r = d2;
        this.f358q = d2;
        int d3 = a2.d(R.styleable.Toolbar_titleMarginStart, -1);
        if (d3 >= 0) {
            this.f358q = d3;
        }
        int d4 = a2.d(R.styleable.Toolbar_titleMarginEnd, -1);
        if (d4 >= 0) {
            this.r = d4;
        }
        int d5 = a2.d(R.styleable.Toolbar_titleMarginTop, -1);
        if (d5 >= 0) {
            this.s = d5;
        }
        int d6 = a2.d(R.styleable.Toolbar_titleMarginBottom, -1);
        if (d6 >= 0) {
            this.t = d6;
        }
        this.p = a2.e(R.styleable.Toolbar_maxButtonHeight, -1);
        int d7 = a2.d(R.styleable.Toolbar_contentInsetStart, ExploreByTouchHelper.INVALID_ID);
        int d8 = a2.d(R.styleable.Toolbar_contentInsetEnd, ExploreByTouchHelper.INVALID_ID);
        int e2 = a2.e(R.styleable.Toolbar_contentInsetLeft, 0);
        int e3 = a2.e(R.styleable.Toolbar_contentInsetRight, 0);
        s();
        this.u.b(e2, e3);
        if (!(d7 == Integer.MIN_VALUE && d8 == Integer.MIN_VALUE)) {
            this.u.a(d7, d8);
        }
        this.v = a2.d(R.styleable.Toolbar_contentInsetStartWithNavigation, ExploreByTouchHelper.INVALID_ID);
        this.w = a2.d(R.styleable.Toolbar_contentInsetEndWithActions, ExploreByTouchHelper.INVALID_ID);
        this.j = a2.a(R.styleable.Toolbar_collapseIcon);
        this.k = a2.c(R.styleable.Toolbar_collapseContentDescription);
        CharSequence c2 = a2.c(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(c2)) {
            setTitle(c2);
        }
        CharSequence c3 = a2.c(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c3)) {
            setSubtitle(c3);
        }
        this.l = getContext();
        setPopupTheme(a2.g(R.styleable.Toolbar_popupTheme, 0));
        Drawable a3 = a2.a(R.styleable.Toolbar_navigationIcon);
        if (a3 != null) {
            setNavigationIcon(a3);
        }
        CharSequence c4 = a2.c(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c4)) {
            setNavigationContentDescription(c4);
        }
        Drawable a4 = a2.a(R.styleable.Toolbar_logo);
        if (a4 != null) {
            setLogo(a4);
        }
        CharSequence c5 = a2.c(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c5)) {
            setLogoDescription(c5);
        }
        if (a2.g(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a2.b(R.styleable.Toolbar_titleTextColor, -1));
        }
        if (a2.g(R.styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a2.b(R.styleable.Toolbar_subtitleTextColor, -1));
        }
        a2.a();
    }

    public void setPopupTheme(int i2) {
        if (this.m != i2) {
            this.m = i2;
            if (i2 == 0) {
                this.l = getContext();
            } else {
                this.l = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public int getPopupTheme() {
        return this.m;
    }

    public int getTitleMarginStart() {
        return this.f358q;
    }

    public void setTitleMarginStart(int i2) {
        this.f358q = i2;
        requestLayout();
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public void setTitleMarginTop(int i2) {
        this.s = i2;
        requestLayout();
    }

    public int getTitleMarginEnd() {
        return this.r;
    }

    public void setTitleMarginEnd(int i2) {
        this.r = i2;
        requestLayout();
    }

    public int getTitleMarginBottom() {
        return this.t;
    }

    public void setTitleMarginBottom(int i2) {
        this.t = i2;
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i2) {
        boolean z2 = true;
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i2);
        }
        s();
        aj ajVar = this.u;
        if (i2 != 1) {
            z2 = false;
        }
        ajVar.a(z2);
    }

    public void setLogo(int i2) {
        setLogo(android.support.v7.a.a.a.b(getContext(), i2));
    }

    public boolean a() {
        return getVisibility() == 0 && this.a != null && this.a.a();
    }

    public boolean b() {
        return this.a != null && this.a.g();
    }

    public boolean c() {
        return this.a != null && this.a.h();
    }

    public boolean d() {
        return this.a != null && this.a.e();
    }

    public boolean e() {
        return this.a != null && this.a.f();
    }

    public void a(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder != null || this.a != null) {
            o();
            MenuBuilder d2 = this.a.d();
            if (d2 != menuBuilder) {
                if (d2 != null) {
                    d2.b((l) this.J);
                    d2.b((l) this.K);
                }
                if (this.K == null) {
                    this.K = new a();
                }
                actionMenuPresenter.c(true);
                if (menuBuilder != null) {
                    menuBuilder.a((l) actionMenuPresenter, this.l);
                    menuBuilder.a((l) this.K, this.l);
                } else {
                    actionMenuPresenter.a(this.l, (MenuBuilder) null);
                    this.K.a(this.l, (MenuBuilder) null);
                    actionMenuPresenter.a(true);
                    this.K.a(true);
                }
                this.a.setPopupTheme(this.m);
                this.a.setPresenter(actionMenuPresenter);
                this.J = actionMenuPresenter;
            }
        }
    }

    public void f() {
        if (this.a != null) {
            this.a.i();
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m();
            if (!d(this.i)) {
                a((View) this.i, true);
            }
        } else if (this.i != null && d(this.i)) {
            removeView(this.i);
            this.F.remove(this.i);
        }
        if (this.i != null) {
            this.i.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        if (this.i != null) {
            return this.i.getDrawable();
        }
        return null;
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m();
        }
        if (this.i != null) {
            this.i.setContentDescription(charSequence);
        }
    }

    public CharSequence getLogoDescription() {
        if (this.i != null) {
            return this.i.getContentDescription();
        }
        return null;
    }

    private void m() {
        if (this.i == null) {
            this.i = new AppCompatImageView(getContext());
        }
    }

    public boolean g() {
        return (this.K == null || this.K.b == null) ? false : true;
    }

    public void h() {
        MenuItemImpl menuItemImpl = this.K == null ? null : this.K.b;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.y;
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f == null) {
                Context context = getContext();
                this.f = new AppCompatTextView(context);
                this.f.setSingleLine();
                this.f.setEllipsize(TruncateAt.END);
                if (this.n != 0) {
                    this.f.setTextAppearance(context, this.n);
                }
                if (this.A != 0) {
                    this.f.setTextColor(this.A);
                }
            }
            if (!d(this.f)) {
                a((View) this.f, true);
            }
        } else if (this.f != null && d(this.f)) {
            removeView(this.f);
            this.F.remove(this.f);
        }
        if (this.f != null) {
            this.f.setText(charSequence);
        }
        this.y = charSequence;
    }

    public CharSequence getSubtitle() {
        return this.z;
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.g == null) {
                Context context = getContext();
                this.g = new AppCompatTextView(context);
                this.g.setSingleLine();
                this.g.setEllipsize(TruncateAt.END);
                if (this.o != 0) {
                    this.g.setTextAppearance(context, this.o);
                }
                if (this.B != 0) {
                    this.g.setTextColor(this.B);
                }
            }
            if (!d(this.g)) {
                a((View) this.g, true);
            }
        } else if (this.g != null && d(this.g)) {
            removeView(this.g);
            this.F.remove(this.g);
        }
        if (this.g != null) {
            this.g.setText(charSequence);
        }
        this.z = charSequence;
    }

    public void a(Context context, int i2) {
        this.n = i2;
        if (this.f != null) {
            this.f.setTextAppearance(context, i2);
        }
    }

    public void b(Context context, int i2) {
        this.o = i2;
        if (this.g != null) {
            this.g.setTextAppearance(context, i2);
        }
    }

    public void setTitleTextColor(int i2) {
        this.A = i2;
        if (this.f != null) {
            this.f.setTextColor(i2);
        }
    }

    public void setSubtitleTextColor(int i2) {
        this.B = i2;
        if (this.g != null) {
            this.g.setTextColor(i2);
        }
    }

    public CharSequence getNavigationContentDescription() {
        if (this.h != null) {
            return this.h.getContentDescription();
        }
        return null;
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            p();
        }
        if (this.h != null) {
            this.h.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(android.support.v7.a.a.a.b(getContext(), i2));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            p();
            if (!d(this.h)) {
                a((View) this.h, true);
            }
        } else if (this.h != null && d(this.h)) {
            removeView(this.h);
            this.F.remove(this.h);
        }
        if (this.h != null) {
            this.h.setImageDrawable(drawable);
        }
    }

    public Drawable getNavigationIcon() {
        if (this.h != null) {
            return this.h.getDrawable();
        }
        return null;
    }

    public void setNavigationOnClickListener(OnClickListener onClickListener) {
        p();
        this.h.setOnClickListener(onClickListener);
    }

    public Menu getMenu() {
        n();
        return this.a.getMenu();
    }

    public void setOverflowIcon(Drawable drawable) {
        n();
        this.a.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        n();
        return this.a.getOverflowIcon();
    }

    private void n() {
        o();
        if (this.a.d() == null) {
            MenuBuilder menuBuilder = (MenuBuilder) this.a.getMenu();
            if (this.K == null) {
                this.K = new a();
            }
            this.a.setExpandedActionViewsExclusive(true);
            menuBuilder.a((l) this.K, this.l);
        }
    }

    private void o() {
        if (this.a == null) {
            this.a = new ActionMenuView(getContext());
            this.a.setPopupTheme(this.m);
            this.a.setOnMenuItemClickListener(this.H);
            this.a.a(this.L, this.M);
            LayoutParams j2 = generateDefaultLayoutParams();
            j2.a = 8388613 | (this.d & 112);
            this.a.setLayoutParams(j2);
            a((View) this.a, false);
        }
    }

    private MenuInflater getMenuInflater() {
        return new g(getContext());
    }

    public void setOnMenuItemClickListener(b bVar) {
        this.e = bVar;
    }

    public void a(int i2, int i3) {
        s();
        this.u.a(i2, i3);
    }

    public int getContentInsetStart() {
        if (this.u != null) {
            return this.u.c();
        }
        return 0;
    }

    public int getContentInsetEnd() {
        if (this.u != null) {
            return this.u.d();
        }
        return 0;
    }

    public int getContentInsetLeft() {
        if (this.u != null) {
            return this.u.a();
        }
        return 0;
    }

    public int getContentInsetRight() {
        if (this.u != null) {
            return this.u.b();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        if (this.v != Integer.MIN_VALUE) {
            return this.v;
        }
        return getContentInsetStart();
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = ExploreByTouchHelper.INVALID_ID;
        }
        if (i2 != this.v) {
            this.v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getContentInsetEndWithActions() {
        if (this.w != Integer.MIN_VALUE) {
            return this.w;
        }
        return getContentInsetEnd();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = ExploreByTouchHelper.INVALID_ID;
        }
        if (i2 != this.w) {
            this.w = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getCurrentContentInsetStart() {
        if (getNavigationIcon() != null) {
            return Math.max(getContentInsetStart(), Math.max(this.v, 0));
        }
        return getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        boolean z2;
        if (this.a != null) {
            MenuBuilder d2 = this.a.d();
            z2 = d2 != null && d2.hasVisibleItems();
        } else {
            z2 = false;
        }
        if (z2) {
            return Math.max(getContentInsetEnd(), Math.max(this.w, 0));
        }
        return getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        if (ViewCompat.getLayoutDirection(this) == 1) {
            return getCurrentContentInsetEnd();
        }
        return getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        if (ViewCompat.getLayoutDirection(this) == 1) {
            return getCurrentContentInsetStart();
        }
        return getCurrentContentInsetEnd();
    }

    private void p() {
        if (this.h == null) {
            this.h = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams j2 = generateDefaultLayoutParams();
            j2.a = 8388611 | (this.d & 112);
            this.h.setLayoutParams(j2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        if (this.b == null) {
            this.b = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.b.setImageDrawable(this.j);
            this.b.setContentDescription(this.k);
            LayoutParams j2 = generateDefaultLayoutParams();
            j2.a = 8388611 | (this.d & 112);
            j2.b = 2;
            this.b.setLayoutParams(j2);
            this.b.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Toolbar.this.h();
                }
            });
        }
    }

    private void a(View view, boolean z2) {
        LayoutParams layoutParams;
        android.view.ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams2)) {
            layoutParams = generateLayoutParams(layoutParams2);
        } else {
            layoutParams = (LayoutParams) layoutParams2;
        }
        layoutParams.b = 1;
        if (!z2 || this.c == null) {
            addView(view, layoutParams);
            return;
        }
        view.setLayoutParams(layoutParams);
        this.F.add(view);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.K == null || this.K.b == null)) {
            savedState.a = this.K.b.getItemId();
        }
        savedState.b = b();
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
        MenuBuilder menuBuilder = this.a != null ? this.a.d() : null;
        if (!(savedState.a == 0 || this.K == null || menuBuilder == null)) {
            MenuItem findItem = menuBuilder.findItem(savedState.a);
            if (findItem != null) {
                findItem.expandActionView();
            }
        }
        if (savedState.b) {
            q();
        }
    }

    private void q() {
        removeCallbacks(this.O);
        post(this.O);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.O);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    private void a(View view, int i2, int i3, int i4, int i5, int i6) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height);
        int mode = MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i6 >= 0) {
            if (mode != 0) {
                i6 = Math.min(MeasureSpec.getSize(childMeasureSpec2), i6);
            }
            childMeasureSpec2 = MeasureSpec.makeMeasureSpec(i6, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int a(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i7);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + max + i3, marginLayoutParams.width), getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private boolean r() {
        if (!this.N) {
            return false;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (a(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        char c2;
        char c3;
        int i4;
        int i5;
        int i6 = 0;
        int i7 = 0;
        int[] iArr = this.G;
        if (bb.a(this)) {
            c2 = 0;
            c3 = 1;
        } else {
            c2 = 1;
            c3 = 0;
        }
        int i8 = 0;
        if (a((View) this.h)) {
            a((View) this.h, i2, 0, i3, 0, this.p);
            i8 = this.h.getMeasuredWidth() + b((View) this.h);
            int max = Math.max(0, this.h.getMeasuredHeight() + c(this.h));
            i7 = View.combineMeasuredStates(0, this.h.getMeasuredState());
            i6 = max;
        }
        if (a((View) this.b)) {
            a((View) this.b, i2, 0, i3, 0, this.p);
            i8 = this.b.getMeasuredWidth() + b((View) this.b);
            i6 = Math.max(i6, this.b.getMeasuredHeight() + c(this.b));
            i7 = View.combineMeasuredStates(i7, this.b.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max2 = 0 + Math.max(currentContentInsetStart, i8);
        iArr[c3] = Math.max(0, currentContentInsetStart - i8);
        int i9 = 0;
        if (a((View) this.a)) {
            a((View) this.a, i2, max2, i3, 0, this.p);
            i9 = this.a.getMeasuredWidth() + b((View) this.a);
            i6 = Math.max(i6, this.a.getMeasuredHeight() + c(this.a));
            i7 = View.combineMeasuredStates(i7, this.a.getMeasuredState());
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max3 = max2 + Math.max(currentContentInsetEnd, i9);
        iArr[c2] = Math.max(0, currentContentInsetEnd - i9);
        if (a(this.c)) {
            max3 += a(this.c, i2, max3, i3, 0, iArr);
            i6 = Math.max(i6, this.c.getMeasuredHeight() + c(this.c));
            i7 = View.combineMeasuredStates(i7, this.c.getMeasuredState());
        }
        if (a((View) this.i)) {
            max3 += a((View) this.i, i2, max3, i3, 0, iArr);
            i6 = Math.max(i6, this.i.getMeasuredHeight() + c(this.i));
            i7 = View.combineMeasuredStates(i7, this.i.getMeasuredState());
        }
        int childCount = getChildCount();
        int i10 = 0;
        int i11 = i6;
        int i12 = i7;
        while (i10 < childCount) {
            View childAt = getChildAt(i10);
            if (((LayoutParams) childAt.getLayoutParams()).b != 0) {
                i4 = i12;
                i5 = i11;
            } else if (!a(childAt)) {
                i4 = i12;
                i5 = i11;
            } else {
                max3 += a(childAt, i2, max3, i3, 0, iArr);
                int max4 = Math.max(i11, childAt.getMeasuredHeight() + c(childAt));
                i4 = View.combineMeasuredStates(i12, childAt.getMeasuredState());
                i5 = max4;
            }
            i10++;
            i12 = i4;
            i11 = i5;
        }
        int i13 = 0;
        int i14 = 0;
        int i15 = this.s + this.t;
        int i16 = this.f358q + this.r;
        if (a((View) this.f)) {
            a((View) this.f, i2, max3 + i16, i3, i15, iArr);
            i13 = b((View) this.f) + this.f.getMeasuredWidth();
            i14 = this.f.getMeasuredHeight() + c(this.f);
            i12 = View.combineMeasuredStates(i12, this.f.getMeasuredState());
        }
        if (a((View) this.g)) {
            i13 = Math.max(i13, a((View) this.g, i2, max3 + i16, i3, i15 + i14, iArr));
            i14 += this.g.getMeasuredHeight() + c(this.g);
            i12 = View.combineMeasuredStates(i12, this.g.getMeasuredState());
        }
        int i17 = i13 + max3;
        int max5 = Math.max(i11, i14) + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(i17 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, -16777216 & i12);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(max5, getSuggestedMinimumHeight()), i3, i12 << 16);
        if (r()) {
            resolveSizeAndState2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean z3;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i19 = width - paddingRight;
        int[] iArr = this.G;
        iArr[1] = 0;
        iArr[0] = 0;
        int minimumHeight = ViewCompat.getMinimumHeight(this);
        int i20 = minimumHeight >= 0 ? Math.min(minimumHeight, i5 - i3) : 0;
        if (!a((View) this.h)) {
            i6 = i19;
            i7 = paddingLeft;
        } else if (z3) {
            i6 = b(this.h, i19, iArr, i20);
            i7 = paddingLeft;
        } else {
            int i21 = i19;
            i7 = a(this.h, paddingLeft, iArr, i20);
            i6 = i21;
        }
        if (a((View) this.b)) {
            if (z3) {
                i6 = b(this.b, i6, iArr, i20);
            } else {
                i7 = a(this.b, i7, iArr, i20);
            }
        }
        if (a((View) this.a)) {
            if (z3) {
                i7 = a(this.a, i7, iArr, i20);
            } else {
                i6 = b(this.a, i6, iArr, i20);
            }
        }
        int currentContentInsetLeft = getCurrentContentInsetLeft();
        int currentContentInsetRight = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft - i7);
        iArr[1] = Math.max(0, currentContentInsetRight - ((width - paddingRight) - i6));
        int max = Math.max(i7, currentContentInsetLeft);
        int min = Math.min(i6, (width - paddingRight) - currentContentInsetRight);
        if (a(this.c)) {
            if (z3) {
                min = b(this.c, min, iArr, i20);
            } else {
                max = a(this.c, max, iArr, i20);
            }
        }
        if (!a((View) this.i)) {
            i8 = min;
            i9 = max;
        } else if (z3) {
            i8 = b(this.i, min, iArr, i20);
            i9 = max;
        } else {
            i8 = min;
            i9 = a(this.i, max, iArr, i20);
        }
        boolean a2 = a((View) this.f);
        boolean a3 = a((View) this.g);
        int i22 = 0;
        if (a2) {
            LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
            i22 = 0 + layoutParams.bottomMargin + layoutParams.topMargin + this.f.getMeasuredHeight();
        }
        if (a3) {
            LayoutParams layoutParams2 = (LayoutParams) this.g.getLayoutParams();
            i10 = layoutParams2.bottomMargin + layoutParams2.topMargin + this.g.getMeasuredHeight() + i22;
        } else {
            i10 = i22;
        }
        if (a2 || a3) {
            LayoutParams layoutParams3 = (LayoutParams) (a2 ? this.f : this.g).getLayoutParams();
            LayoutParams layoutParams4 = (LayoutParams) (a3 ? this.g : this.f).getLayoutParams();
            boolean z4 = (a2 && this.f.getMeasuredWidth() > 0) || (a3 && this.g.getMeasuredWidth() > 0);
            switch (this.x & 112) {
                case 48:
                    i11 = layoutParams3.topMargin + getPaddingTop() + this.s;
                    break;
                case 80:
                    i11 = (((height - paddingBottom) - layoutParams4.bottomMargin) - this.t) - i10;
                    break;
                default:
                    int i23 = (((height - paddingTop) - paddingBottom) - i10) / 2;
                    if (i23 < layoutParams3.topMargin + this.s) {
                        i18 = layoutParams3.topMargin + this.s;
                    } else {
                        int i24 = (((height - paddingBottom) - i10) - i23) - paddingTop;
                        if (i24 < layoutParams3.bottomMargin + this.t) {
                            i18 = Math.max(0, i23 - ((layoutParams4.bottomMargin + this.t) - i24));
                        } else {
                            i18 = i23;
                        }
                    }
                    i11 = paddingTop + i18;
                    break;
            }
            if (z3) {
                int i25 = (z4 ? this.f358q : 0) - iArr[1];
                int max2 = i8 - Math.max(0, i25);
                iArr[1] = Math.max(0, -i25);
                if (a2) {
                    LayoutParams layoutParams5 = (LayoutParams) this.f.getLayoutParams();
                    int measuredWidth = max2 - this.f.getMeasuredWidth();
                    int measuredHeight = this.f.getMeasuredHeight() + i11;
                    this.f.layout(measuredWidth, i11, max2, measuredHeight);
                    i11 = measuredHeight + layoutParams5.bottomMargin;
                    i15 = measuredWidth - this.r;
                } else {
                    i15 = max2;
                }
                if (a3) {
                    LayoutParams layoutParams6 = (LayoutParams) this.g.getLayoutParams();
                    int i26 = layoutParams6.topMargin + i11;
                    int measuredHeight2 = this.g.getMeasuredHeight() + i26;
                    this.g.layout(max2 - this.g.getMeasuredWidth(), i26, max2, measuredHeight2);
                    int i27 = layoutParams6.bottomMargin + measuredHeight2;
                    i16 = max2 - this.r;
                } else {
                    i16 = max2;
                }
                if (z4) {
                    i17 = Math.min(i15, i16);
                } else {
                    i17 = max2;
                }
                i8 = i17;
            } else {
                int i28 = (z4 ? this.f358q : 0) - iArr[0];
                i9 += Math.max(0, i28);
                iArr[0] = Math.max(0, -i28);
                if (a2) {
                    LayoutParams layoutParams7 = (LayoutParams) this.f.getLayoutParams();
                    int measuredWidth2 = this.f.getMeasuredWidth() + i9;
                    int measuredHeight3 = this.f.getMeasuredHeight() + i11;
                    this.f.layout(i9, i11, measuredWidth2, measuredHeight3);
                    int i29 = layoutParams7.bottomMargin + measuredHeight3;
                    i12 = measuredWidth2 + this.r;
                    i13 = i29;
                } else {
                    i12 = i9;
                    i13 = i11;
                }
                if (a3) {
                    LayoutParams layoutParams8 = (LayoutParams) this.g.getLayoutParams();
                    int i30 = i13 + layoutParams8.topMargin;
                    int measuredWidth3 = this.g.getMeasuredWidth() + i9;
                    int measuredHeight4 = this.g.getMeasuredHeight() + i30;
                    this.g.layout(i9, i30, measuredWidth3, measuredHeight4);
                    int i31 = layoutParams8.bottomMargin + measuredHeight4;
                    i14 = this.r + measuredWidth3;
                } else {
                    i14 = i9;
                }
                if (z4) {
                    i9 = Math.max(i12, i14);
                }
            }
        }
        a((List<View>) this.E, 3);
        int size = this.E.size();
        int i32 = i9;
        for (int i33 = 0; i33 < size; i33++) {
            i32 = a((View) this.E.get(i33), i32, iArr, i20);
        }
        a((List<View>) this.E, 5);
        int size2 = this.E.size();
        for (int i34 = 0; i34 < size2; i34++) {
            i8 = b((View) this.E.get(i34), i8, iArr, i20);
        }
        a((List<View>) this.E, 1);
        int a4 = a((List<View>) this.E, iArr);
        int i35 = ((((width - paddingLeft) - paddingRight) / 2) + paddingLeft) - (a4 / 2);
        int i36 = a4 + i35;
        if (i35 < i32) {
            i35 = i32;
        } else if (i36 > i8) {
            i35 -= i36 - i8;
        }
        int size3 = this.E.size();
        int i37 = i35;
        for (int i38 = 0; i38 < size3; i38++) {
            i37 = a((View) this.E.get(i38), i37, iArr, i20);
        }
        this.E.clear();
    }

    private int a(List<View> list, int[] iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int size = list.size();
        int i4 = 0;
        int i5 = 0;
        int i6 = i3;
        int i7 = i2;
        while (i4 < size) {
            View view = (View) list.get(i4);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int i8 = layoutParams.leftMargin - i7;
            int i9 = layoutParams.rightMargin - i6;
            int max = Math.max(0, i8);
            int max2 = Math.max(0, i9);
            i7 = Math.max(0, -i8);
            i6 = Math.max(0, -i9);
            i4++;
            i5 += view.getMeasuredWidth() + max + max2;
        }
        return i5;
    }

    private int a(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = layoutParams.leftMargin - iArr[0];
        int max = Math.max(0, i4) + i2;
        iArr[0] = Math.max(0, -i4);
        int a2 = a(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a2, max + measuredWidth, view.getMeasuredHeight() + a2);
        return layoutParams.rightMargin + measuredWidth + max;
    }

    private int b(View view, int i2, int[] iArr, int i3) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = layoutParams.rightMargin - iArr[1];
        int max = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int a2 = a(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a2, max, view.getMeasuredHeight() + a2);
        return max - (layoutParams.leftMargin + measuredWidth);
    }

    private int a(View view, int i2) {
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        switch (a(layoutParams.a)) {
            case 48:
                return getPaddingTop() - i4;
            case 80:
                return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i4;
            default:
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int i5 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                if (i5 < layoutParams.topMargin) {
                    i3 = layoutParams.topMargin;
                } else {
                    int i6 = (((height - paddingBottom) - measuredHeight) - i5) - paddingTop;
                    i3 = i6 < layoutParams.bottomMargin ? Math.max(0, i5 - (layoutParams.bottomMargin - i6)) : i5;
                }
                return i3 + paddingTop;
        }
    }

    private int a(int i2) {
        int i3 = i2 & 112;
        switch (i3) {
            case 16:
            case 48:
            case 80:
                return i3;
            default:
                return this.x & 112;
        }
    }

    private void a(List<View> list, int i2) {
        boolean z2 = true;
        if (ViewCompat.getLayoutDirection(this) != 1) {
            z2 = false;
        }
        int childCount = getChildCount();
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this));
        list.clear();
        if (z2) {
            for (int i3 = childCount - 1; i3 >= 0; i3--) {
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b == 0 && a(childAt) && b(layoutParams.a) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.b == 0 && a(childAt2) && b(layoutParams2.a) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    private int b(int i2) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, layoutDirection) & 7;
        switch (absoluteGravity) {
            case 1:
            case 3:
            case 5:
                return absoluteGravity;
            default:
                return layoutDirection == 1 ? 5 : 3;
        }
    }

    private boolean a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int b(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
    }

    private int c(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof android.support.v7.app.ActionBar.LayoutParams) {
            return new LayoutParams((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public t getWrapper() {
        if (this.I == null) {
            this.I = new au(this, true);
        }
        return this.I;
    }

    /* access modifiers changed from: 0000 */
    public void k() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((LayoutParams) childAt.getLayoutParams()).b == 2 || childAt == this.a)) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() {
        for (int size = this.F.size() - 1; size >= 0; size--) {
            addView((View) this.F.get(size));
        }
        this.F.clear();
    }

    private boolean d(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    public void setCollapsible(boolean z2) {
        this.N = z2;
        requestLayout();
    }

    public void a(android.support.v7.view.menu.l.a aVar, android.support.v7.view.menu.MenuBuilder.a aVar2) {
        this.L = aVar;
        this.M = aVar2;
        if (this.a != null) {
            this.a.a(aVar, aVar2);
        }
    }

    private void s() {
        if (this.u == null) {
            this.u = new aj();
        }
    }

    /* access modifiers changed from: 0000 */
    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.J;
    }

    /* access modifiers changed from: 0000 */
    public Context getPopupContext() {
        return this.l;
    }
}
