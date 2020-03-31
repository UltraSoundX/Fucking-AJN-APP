package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.app.NavUtils;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v4.view.KeyEventDispatcher.Component;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.i;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.m;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.at;
import android.support.v7.widget.ay;
import android.support.v7.widget.bb;
import android.support.v7.widget.s;
import android.support.v7.widget.y;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends c implements android.support.v7.view.menu.MenuBuilder.a, Factory2 {
    private static final boolean u = (VERSION.SDK_INT < 21);
    private static final int[] v = {16842836};
    private static boolean w = true;
    private g A;
    private boolean B = true;
    private boolean C;
    private ViewGroup D;
    private TextView E;
    private View F;
    private boolean G;
    private boolean H;
    private boolean I;
    private PanelFeatureState[] J;
    private PanelFeatureState K;
    private boolean L;
    private int M = -100;
    private boolean N;
    private e O;
    private final Runnable P = new Runnable() {
        public void run() {
            if ((AppCompatDelegateImpl.this.t & 1) != 0) {
                AppCompatDelegateImpl.this.g(0);
            }
            if ((AppCompatDelegateImpl.this.t & 4096) != 0) {
                AppCompatDelegateImpl.this.g(108);
            }
            AppCompatDelegateImpl.this.s = false;
            AppCompatDelegateImpl.this.t = 0;
        }
    };
    private boolean Q;
    private Rect R;
    private Rect S;
    private AppCompatViewInflater T;
    final Context a;
    final Window b;
    final Callback c;
    final Callback d;
    final b e;
    ActionBar f;
    MenuInflater g;
    android.support.v7.view.b h;
    ActionBarContextView i;
    PopupWindow j;
    Runnable k;
    ViewPropertyAnimatorCompat l = null;
    boolean m;
    boolean n;
    boolean o;
    boolean p;

    /* renamed from: q reason: collision with root package name */
    boolean f337q;
    boolean r;
    boolean s;
    int t;
    private CharSequence x;
    private s y;
    private b z;

    protected static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        MenuBuilder j;
        android.support.v7.view.menu.e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;

        /* renamed from: q reason: collision with root package name */
        boolean f338q = false;
        boolean r;
        Bundle s;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                /* renamed from: a */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            int a;
            boolean b;
            Bundle c;

            SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int i2) {
            this.a = i2;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            if (this.i != null || this.k.d().getCount() > 0) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            android.support.v7.view.d dVar = new android.support.v7.view.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: 0000 */
        public void a(MenuBuilder menuBuilder) {
            if (menuBuilder != this.j) {
                if (this.j != null) {
                    this.j.b((l) this.k);
                }
                this.j = menuBuilder;
                if (menuBuilder != null && this.k != null) {
                    menuBuilder.a((l) this.k);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public m a(android.support.v7.view.menu.l.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new android.support.v7.view.menu.e(this.l, R.layout.abc_list_menu_item_layout);
                this.k.a(aVar);
                this.j.a((l) this.k);
            }
            return this.k.a(this.g);
        }
    }

    private class a implements android.support.v7.app.ActionBarDrawerToggle.a {
        a() {
        }

        public void a(int i) {
            ActionBar a2 = AppCompatDelegateImpl.this.a();
            if (a2 != null) {
                a2.a(i);
            }
        }
    }

    private final class b implements android.support.v7.view.menu.l.a {
        b() {
        }

        public boolean a(MenuBuilder menuBuilder) {
            Callback m = AppCompatDelegateImpl.this.m();
            if (m != null) {
                m.onMenuOpened(108, menuBuilder);
            }
            return true;
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.b(menuBuilder);
        }
    }

    class c implements android.support.v7.view.b.a {
        private android.support.v7.view.b.a b;

        public c(android.support.v7.view.b.a aVar) {
            this.b = aVar;
        }

        public boolean a(android.support.v7.view.b bVar, Menu menu) {
            return this.b.a(bVar, menu);
        }

        public boolean b(android.support.v7.view.b bVar, Menu menu) {
            return this.b.b(bVar, menu);
        }

        public boolean a(android.support.v7.view.b bVar, MenuItem menuItem) {
            return this.b.a(bVar, menuItem);
        }

        public void a(android.support.v7.view.b bVar) {
            this.b.a(bVar);
            if (AppCompatDelegateImpl.this.j != null) {
                AppCompatDelegateImpl.this.b.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.k);
            }
            if (AppCompatDelegateImpl.this.i != null) {
                AppCompatDelegateImpl.this.r();
                AppCompatDelegateImpl.this.l = ViewCompat.animate(AppCompatDelegateImpl.this.i).alpha(0.0f);
                AppCompatDelegateImpl.this.l.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View view) {
                        AppCompatDelegateImpl.this.i.setVisibility(8);
                        if (AppCompatDelegateImpl.this.j != null) {
                            AppCompatDelegateImpl.this.j.dismiss();
                        } else if (AppCompatDelegateImpl.this.i.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.i.getParent());
                        }
                        AppCompatDelegateImpl.this.i.removeAllViews();
                        AppCompatDelegateImpl.this.l.setListener(null);
                        AppCompatDelegateImpl.this.l = null;
                    }
                });
            }
            if (AppCompatDelegateImpl.this.e != null) {
                AppCompatDelegateImpl.this.e.onSupportActionModeFinished(AppCompatDelegateImpl.this.h);
            }
            AppCompatDelegateImpl.this.h = null;
        }
    }

    class d extends i {
        d(Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder;
            if (menu instanceof MenuBuilder) {
                menuBuilder = (MenuBuilder) menu;
            } else {
                menuBuilder = null;
            }
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.c(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder == null) {
                return onPreparePanel;
            }
            menuBuilder.c(false);
            return onPreparePanel;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.e(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.d(i);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.q()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        /* access modifiers changed from: 0000 */
        public final ActionMode a(ActionMode.Callback callback) {
            android.support.v7.view.f.a aVar = new android.support.v7.view.f.a(AppCompatDelegateImpl.this.a, callback);
            android.support.v7.view.b a2 = AppCompatDelegateImpl.this.a((android.support.v7.view.b.a) aVar);
            if (a2 != null) {
                return aVar.b(a2);
            }
            return null;
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.q()) {
                switch (i) {
                    case 0:
                        return a(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState a2 = AppCompatDelegateImpl.this.a(0, true);
            if (a2 == null || a2.j == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, a2.j, i);
            }
        }
    }

    final class e {
        private h b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        e(h hVar) {
            this.b = hVar;
            this.c = hVar.a();
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            this.c = this.b.a();
            return this.c ? 2 : 1;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            boolean a2 = this.b.a();
            if (a2 != this.c) {
                this.c = a2;
                AppCompatDelegateImpl.this.j();
            }
        }

        /* access modifiers changed from: 0000 */
        public void c() {
            d();
            if (this.d == null) {
                this.d = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        e.this.b();
                    }
                };
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.a.registerReceiver(this.d, this.e);
        }

        /* access modifiers changed from: 0000 */
        public void d() {
            if (this.d != null) {
                AppCompatDelegateImpl.this.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }

    private class f extends ContentFrameLayout {
        public f(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.f(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(android.support.v7.a.a.a.b(getContext(), i));
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }
    }

    private final class g implements android.support.v7.view.menu.l.a {
        g() {
        }

        public void a(MenuBuilder menuBuilder, boolean z) {
            MenuBuilder q2 = menuBuilder.q();
            boolean z2 = q2 != menuBuilder;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                menuBuilder = q2;
            }
            PanelFeatureState a2 = appCompatDelegateImpl.a((Menu) menuBuilder);
            if (a2 == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImpl.this.a(a2.a, a2, q2);
                AppCompatDelegateImpl.this.a(a2, true);
                return;
            }
            AppCompatDelegateImpl.this.a(a2, z);
        }

        public boolean a(MenuBuilder menuBuilder) {
            if (menuBuilder == null && AppCompatDelegateImpl.this.m) {
                Callback m = AppCompatDelegateImpl.this.m();
                if (m != null && !AppCompatDelegateImpl.this.r) {
                    m.onMenuOpened(108, menuBuilder);
                }
            }
            return true;
        }
    }

    static {
        if (u && !w) {
            final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    if (a(th)) {
                        NotFoundException notFoundException = new NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }

                private boolean a(Throwable th) {
                    if (!(th instanceof NotFoundException)) {
                        return false;
                    }
                    String message = th.getMessage();
                    if (message == null) {
                        return false;
                    }
                    if (message.contains("drawable") || message.contains("Drawable")) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, b bVar) {
        this.a = context;
        this.b = window;
        this.e = bVar;
        this.c = this.b.getCallback();
        if (this.c instanceof d) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.d = new d(this.c);
        this.b.setCallback(this.d);
        at a2 = at.a(context, (AttributeSet) null, v);
        Drawable b2 = a2.b(0);
        if (b2 != null) {
            this.b.setBackgroundDrawable(b2);
        }
        a2.a();
    }

    public void a(Bundle bundle) {
        String str;
        if (this.c instanceof Activity) {
            try {
                str = NavUtils.getParentActivityName((Activity) this.c);
            } catch (IllegalArgumentException e2) {
                str = null;
            }
            if (str != null) {
                ActionBar l2 = l();
                if (l2 == null) {
                    this.Q = true;
                } else {
                    l2.c(true);
                }
            }
        }
        if (bundle != null && this.M == -100) {
            this.M = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    public void b(Bundle bundle) {
        v();
    }

    public ActionBar a() {
        u();
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public final ActionBar l() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public final Callback m() {
        return this.b.getCallback();
    }

    private void u() {
        v();
        if (this.m && this.f == null) {
            if (this.c instanceof Activity) {
                this.f = new i((Activity) this.c, this.n);
            } else if (this.c instanceof Dialog) {
                this.f = new i((Dialog) this.c);
            }
            if (this.f != null) {
                this.f.c(this.Q);
            }
        }
    }

    public void a(Toolbar toolbar) {
        if (this.c instanceof Activity) {
            ActionBar a2 = a();
            if (a2 instanceof i) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.g = null;
            if (a2 != null) {
                a2.g();
            }
            if (toolbar != null) {
                f fVar = new f(toolbar, ((Activity) this.c).getTitle(), this.d);
                this.f = fVar;
                this.b.setCallback(fVar.h());
            } else {
                this.f = null;
                this.b.setCallback(this.d);
            }
            f();
        }
    }

    /* access modifiers changed from: 0000 */
    public final Context n() {
        Context context = null;
        ActionBar a2 = a();
        if (a2 != null) {
            context = a2.b();
        }
        if (context == null) {
            return this.a;
        }
        return context;
    }

    public MenuInflater b() {
        if (this.g == null) {
            u();
            this.g = new android.support.v7.view.g(this.f != null ? this.f.b() : this.a);
        }
        return this.g;
    }

    public <T extends View> T a(int i2) {
        v();
        return this.b.findViewById(i2);
    }

    public void a(Configuration configuration) {
        if (this.m && this.C) {
            ActionBar a2 = a();
            if (a2 != null) {
                a2.a(configuration);
            }
        }
        android.support.v7.widget.f.a().a(this.a);
        j();
    }

    public void c() {
        j();
    }

    public void d() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.d(false);
        }
        if (this.O != null) {
            this.O.d();
        }
    }

    public void e() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.d(true);
        }
    }

    public void a(View view) {
        v();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }

    public void b(int i2) {
        v();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(i2, viewGroup);
        this.c.onContentChanged();
    }

    public void a(View view, LayoutParams layoutParams) {
        v();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void b(View view, LayoutParams layoutParams) {
        v();
        ((ViewGroup) this.D.findViewById(16908290)).addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void c(Bundle bundle) {
        if (this.M != -100) {
            bundle.putInt("appcompat:local_night_mode", this.M);
        }
    }

    public void g() {
        if (this.s) {
            this.b.getDecorView().removeCallbacks(this.P);
        }
        this.r = true;
        if (this.f != null) {
            this.f.g();
        }
        if (this.O != null) {
            this.O.d();
        }
    }

    private void v() {
        if (!this.C) {
            this.D = w();
            CharSequence o2 = o();
            if (!TextUtils.isEmpty(o2)) {
                if (this.y != null) {
                    this.y.setWindowTitle(o2);
                } else if (l() != null) {
                    l().a(o2);
                } else if (this.E != null) {
                    this.E.setText(o2);
                }
            }
            x();
            a(this.D);
            this.C = true;
            PanelFeatureState a2 = a(0, false);
            if (this.r) {
                return;
            }
            if (a2 == null || a2.j == null) {
                j(108);
            }
        }
    }

    private ViewGroup w() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        Context context;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
            c(1);
        } else if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
            c(108);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            c(109);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            c(10);
        }
        this.p = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        this.b.getDecorView();
        LayoutInflater from = LayoutInflater.from(this.a);
        if (this.f337q) {
            if (this.o) {
                viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
            } else {
                viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple, null);
            }
            if (VERSION.SDK_INT >= 21) {
                ViewCompat.setOnApplyWindowInsetsListener(viewGroup, new OnApplyWindowInsetsListener() {
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
                        int h = AppCompatDelegateImpl.this.h(systemWindowInsetTop);
                        if (systemWindowInsetTop != h) {
                            windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), h, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                        }
                        return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                    }
                });
                viewGroup2 = viewGroup;
            } else {
                ((y) viewGroup).setOnFitSystemWindowsListener(new android.support.v7.widget.y.a() {
                    public void a(Rect rect) {
                        rect.top = AppCompatDelegateImpl.this.h(rect.top);
                    }
                });
                viewGroup2 = viewGroup;
            }
        } else if (this.p) {
            ViewGroup viewGroup3 = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, null);
            this.n = false;
            this.m = false;
            viewGroup2 = viewGroup3;
        } else if (this.m) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                context = new android.support.v7.view.d(this.a, typedValue.resourceId);
            } else {
                context = this.a;
            }
            ViewGroup viewGroup4 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, null);
            this.y = (s) viewGroup4.findViewById(R.id.decor_content_parent);
            this.y.setWindowCallback(m());
            if (this.n) {
                this.y.a(109);
            }
            if (this.G) {
                this.y.a(2);
            }
            if (this.H) {
                this.y.a(5);
            }
            viewGroup2 = viewGroup4;
        } else {
            viewGroup2 = null;
        }
        if (viewGroup2 == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.m + ", windowActionBarOverlay: " + this.n + ", android:windowIsFloating: " + this.p + ", windowActionModeOverlay: " + this.o + ", windowNoTitle: " + this.f337q + " }");
        }
        if (this.y == null) {
            this.E = (TextView) viewGroup2.findViewById(R.id.title);
        }
        bb.b(viewGroup2);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup2.findViewById(R.id.action_bar_activity_content);
        ViewGroup viewGroup5 = (ViewGroup) this.b.findViewById(16908290);
        if (viewGroup5 != null) {
            while (viewGroup5.getChildCount() > 0) {
                View childAt = viewGroup5.getChildAt(0);
                viewGroup5.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup5.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup5 instanceof FrameLayout) {
                ((FrameLayout) viewGroup5).setForeground(null);
            }
        }
        this.b.setContentView(viewGroup2);
        contentFrameLayout.setAttachListener(new android.support.v7.widget.ContentFrameLayout.a() {
            public void a() {
            }

            public void b() {
                AppCompatDelegateImpl.this.t();
            }
        });
        return viewGroup2;
    }

    /* access modifiers changed from: 0000 */
    public void a(ViewGroup viewGroup) {
    }

    private void x() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.D.findViewById(16908290);
        View decorView = this.b.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean c(int i2) {
        int k2 = k(i2);
        if (this.f337q && k2 == 108) {
            return false;
        }
        if (this.m && k2 == 1) {
            this.m = false;
        }
        switch (k2) {
            case 1:
                y();
                this.f337q = true;
                return true;
            case 2:
                y();
                this.G = true;
                return true;
            case 5:
                y();
                this.H = true;
                return true;
            case 10:
                y();
                this.o = true;
                return true;
            case 108:
                y();
                this.m = true;
                return true;
            case 109:
                y();
                this.n = true;
                return true;
            default:
                return this.b.requestFeature(k2);
        }
    }

    public final void a(CharSequence charSequence) {
        this.x = charSequence;
        if (this.y != null) {
            this.y.setWindowTitle(charSequence);
        } else if (l() != null) {
            l().a(charSequence);
        } else if (this.E != null) {
            this.E.setText(charSequence);
        }
    }

    /* access modifiers changed from: 0000 */
    public final CharSequence o() {
        if (this.c instanceof Activity) {
            return ((Activity) this.c).getTitle();
        }
        return this.x;
    }

    /* access modifiers changed from: 0000 */
    public void d(int i2) {
        if (i2 == 108) {
            ActionBar a2 = a();
            if (a2 != null) {
                a2.e(false);
            }
        } else if (i2 == 0) {
            PanelFeatureState a3 = a(i2, true);
            if (a3.o) {
                a(a3, false);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void e(int i2) {
        if (i2 == 108) {
            ActionBar a2 = a();
            if (a2 != null) {
                a2.e(true);
            }
        }
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        Callback m2 = m();
        if (m2 != null && !this.r) {
            PanelFeatureState a2 = a((Menu) menuBuilder.q());
            if (a2 != null) {
                return m2.onMenuItemSelected(a2.a, menuItem);
            }
        }
        return false;
    }

    public void a(MenuBuilder menuBuilder) {
        a(menuBuilder, true);
    }

    public android.support.v7.view.b a(android.support.v7.view.b.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.h != null) {
            this.h.c();
        }
        c cVar = new c(aVar);
        ActionBar a2 = a();
        if (a2 != null) {
            this.h = a2.a((android.support.v7.view.b.a) cVar);
            if (!(this.h == null || this.e == null)) {
                this.e.onSupportActionModeStarted(this.h);
            }
        }
        if (this.h == null) {
            this.h = b((android.support.v7.view.b.a) cVar);
        }
        return this.h;
    }

    public void f() {
        ActionBar a2 = a();
        if (a2 == null || !a2.e()) {
            j(0);
        }
    }

    /* access modifiers changed from: 0000 */
    public android.support.v7.view.b b(android.support.v7.view.b.a aVar) {
        android.support.v7.view.b bVar;
        boolean z2;
        Context context;
        r();
        if (this.h != null) {
            this.h.c();
        }
        if (!(aVar instanceof c)) {
            aVar = new c(aVar);
        }
        if (this.e == null || this.r) {
            bVar = null;
        } else {
            try {
                bVar = this.e.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError e2) {
                bVar = null;
            }
        }
        if (bVar != null) {
            this.h = bVar;
        } else {
            if (this.i == null) {
                if (this.p) {
                    TypedValue typedValue = new TypedValue();
                    Theme theme = this.a.getTheme();
                    theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Theme newTheme = this.a.getResources().newTheme();
                        newTheme.setTo(theme);
                        newTheme.applyStyle(typedValue.resourceId, true);
                        context = new android.support.v7.view.d(this.a, 0);
                        context.getTheme().setTo(newTheme);
                    } else {
                        context = this.a;
                    }
                    this.i = new ActionBarContextView(context);
                    this.j = new PopupWindow(context, null, R.attr.actionModePopupWindowStyle);
                    PopupWindowCompat.setWindowLayoutType(this.j, 2);
                    this.j.setContentView(this.i);
                    this.j.setWidth(-1);
                    context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                    this.i.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics()));
                    this.j.setHeight(-2);
                    this.k = new Runnable() {
                        public void run() {
                            AppCompatDelegateImpl.this.j.showAtLocation(AppCompatDelegateImpl.this.i, 55, 0, 0);
                            AppCompatDelegateImpl.this.r();
                            if (AppCompatDelegateImpl.this.p()) {
                                AppCompatDelegateImpl.this.i.setAlpha(0.0f);
                                AppCompatDelegateImpl.this.l = ViewCompat.animate(AppCompatDelegateImpl.this.i).alpha(1.0f);
                                AppCompatDelegateImpl.this.l.setListener(new ViewPropertyAnimatorListenerAdapter() {
                                    public void onAnimationStart(View view) {
                                        AppCompatDelegateImpl.this.i.setVisibility(0);
                                    }

                                    public void onAnimationEnd(View view) {
                                        AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                                        AppCompatDelegateImpl.this.l.setListener(null);
                                        AppCompatDelegateImpl.this.l = null;
                                    }
                                });
                                return;
                            }
                            AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                            AppCompatDelegateImpl.this.i.setVisibility(0);
                        }
                    };
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.D.findViewById(R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(n()));
                        this.i = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.i != null) {
                r();
                this.i.c();
                Context context2 = this.i.getContext();
                ActionBarContextView actionBarContextView = this.i;
                if (this.j == null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                android.support.v7.view.e eVar = new android.support.v7.view.e(context2, actionBarContextView, aVar, z2);
                if (aVar.a((android.support.v7.view.b) eVar, eVar.b())) {
                    eVar.d();
                    this.i.a(eVar);
                    this.h = eVar;
                    if (p()) {
                        this.i.setAlpha(0.0f);
                        this.l = ViewCompat.animate(this.i).alpha(1.0f);
                        this.l.setListener(new ViewPropertyAnimatorListenerAdapter() {
                            public void onAnimationStart(View view) {
                                AppCompatDelegateImpl.this.i.setVisibility(0);
                                AppCompatDelegateImpl.this.i.sendAccessibilityEvent(32);
                                if (AppCompatDelegateImpl.this.i.getParent() instanceof View) {
                                    ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.i.getParent());
                                }
                            }

                            public void onAnimationEnd(View view) {
                                AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                                AppCompatDelegateImpl.this.l.setListener(null);
                                AppCompatDelegateImpl.this.l = null;
                            }
                        });
                    } else {
                        this.i.setAlpha(1.0f);
                        this.i.setVisibility(0);
                        this.i.sendAccessibilityEvent(32);
                        if (this.i.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) this.i.getParent());
                        }
                    }
                    if (this.j != null) {
                        this.b.getDecorView().post(this.k);
                    }
                } else {
                    this.h = null;
                }
            }
        }
        if (!(this.h == null || this.e == null)) {
            this.e.onSupportActionModeStarted(this.h);
        }
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public final boolean p() {
        return this.C && this.D != null && ViewCompat.isLaidOut(this.D);
    }

    public boolean q() {
        return this.B;
    }

    /* access modifiers changed from: 0000 */
    public void r() {
        if (this.l != null) {
            this.l.cancel();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean s() {
        if (this.h != null) {
            this.h.c();
            return true;
        }
        ActionBar a2 = a();
        if (a2 == null || !a2.f()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i2, KeyEvent keyEvent) {
        ActionBar a2 = a();
        if (a2 != null && a2.a(i2, keyEvent)) {
            return true;
        }
        if (this.K == null || !a(this.K, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.K == null) {
                PanelFeatureState a3 = a(0, true);
                b(a3, keyEvent);
                boolean a4 = a(a3, keyEvent.getKeyCode(), keyEvent, 1);
                a3.m = false;
                if (a4) {
                    return true;
                }
            }
            return false;
        } else if (this.K == null) {
            return true;
        } else {
            this.K.n = true;
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(KeyEvent keyEvent) {
        boolean z2 = true;
        if ((this.c instanceof Component) || (this.c instanceof d)) {
            View decorView = this.b.getDecorView();
            if (decorView != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
                return true;
            }
        }
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z2 = false;
        }
        return z2 ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    /* access modifiers changed from: 0000 */
    public boolean b(int i2, KeyEvent keyEvent) {
        switch (i2) {
            case 4:
                boolean z2 = this.L;
                this.L = false;
                PanelFeatureState a2 = a(0, false);
                if (a2 == null || !a2.o) {
                    if (s()) {
                        return true;
                    }
                } else if (z2) {
                    return true;
                } else {
                    a(a2, true);
                    return true;
                }
                break;
            case 82:
                e(0, keyEvent);
                return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean c(int i2, KeyEvent keyEvent) {
        boolean z2 = true;
        switch (i2) {
            case 4:
                if ((keyEvent.getFlags() & 128) == 0) {
                    z2 = false;
                }
                this.L = z2;
                break;
            case 82:
                d(0, keyEvent);
                return true;
        }
        return false;
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z2;
        boolean a2;
        if (this.T == null) {
            String string = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                this.T = new AppCompatViewInflater();
            } else {
                try {
                    this.T = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.T = new AppCompatViewInflater();
                }
            }
        }
        if (u) {
            if (!(attributeSet instanceof XmlPullParser)) {
                a2 = a((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                a2 = true;
            } else {
                a2 = false;
            }
            z2 = a2;
        } else {
            z2 = false;
        }
        return this.T.createView(view, str, context, attributeSet, z2, u, true, ay.a());
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.b.getDecorView();
        for (ViewParent viewParent2 = viewParent; viewParent2 != null; viewParent2 = viewParent2.getParent()) {
            if (viewParent2 == decorView || !(viewParent2 instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent2)) {
                return false;
            }
        }
        return true;
    }

    public void i() {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ee, code lost:
        if (r0.width == -1) goto L_0x00ad;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.app.AppCompatDelegateImpl.PanelFeatureState r11, android.view.KeyEvent r12) {
        /*
            r10 = this;
            r3 = 0
            r1 = -1
            r9 = 1
            r2 = -2
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x000c
            boolean r0 = r10.r
            if (r0 == 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            int r0 = r11.a
            if (r0 != 0) goto L_0x0025
            android.content.Context r0 = r10.a
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.screenLayout
            r0 = r0 & 15
            r4 = 4
            if (r0 != r4) goto L_0x0039
            r0 = r9
        L_0x0023:
            if (r0 != 0) goto L_0x000c
        L_0x0025:
            android.view.Window$Callback r0 = r10.m()
            if (r0 == 0) goto L_0x003b
            int r4 = r11.a
            android.support.v7.view.menu.MenuBuilder r5 = r11.j
            boolean r0 = r0.onMenuOpened(r4, r5)
            if (r0 != 0) goto L_0x003b
            r10.a(r11, r9)
            goto L_0x000c
        L_0x0039:
            r0 = r3
            goto L_0x0023
        L_0x003b:
            android.content.Context r0 = r10.a
            java.lang.String r4 = "window"
            java.lang.Object r0 = r0.getSystemService(r4)
            r8 = r0
            android.view.WindowManager r8 = (android.view.WindowManager) r8
            if (r8 == 0) goto L_0x000c
            boolean r0 = r10.b(r11, r12)
            if (r0 == 0) goto L_0x000c
            android.view.ViewGroup r0 = r11.g
            if (r0 == 0) goto L_0x0056
            boolean r0 = r11.f338q
            if (r0 == 0) goto L_0x00e0
        L_0x0056:
            android.view.ViewGroup r0 = r11.g
            if (r0 != 0) goto L_0x00ce
            boolean r0 = r10.a(r11)
            if (r0 == 0) goto L_0x000c
            android.view.ViewGroup r0 = r11.g
            if (r0 == 0) goto L_0x000c
        L_0x0064:
            boolean r0 = r10.c(r11)
            if (r0 == 0) goto L_0x000c
            boolean r0 = r11.a()
            if (r0 == 0) goto L_0x000c
            android.view.View r0 = r11.h
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            if (r0 != 0) goto L_0x00f2
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            r0.<init>(r2, r2)
            r1 = r0
        L_0x007e:
            int r0 = r11.b
            android.view.ViewGroup r4 = r11.g
            r4.setBackgroundResource(r0)
            android.view.View r0 = r11.h
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x0098
            boolean r4 = r0 instanceof android.view.ViewGroup
            if (r4 == 0) goto L_0x0098
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r4 = r11.h
            r0.removeView(r4)
        L_0x0098:
            android.view.ViewGroup r0 = r11.g
            android.view.View r4 = r11.h
            r0.addView(r4, r1)
            android.view.View r0 = r11.h
            boolean r0 = r0.hasFocus()
            if (r0 != 0) goto L_0x00ac
            android.view.View r0 = r11.h
            r0.requestFocus()
        L_0x00ac:
            r1 = r2
        L_0x00ad:
            r11.n = r3
            android.view.WindowManager$LayoutParams r0 = new android.view.WindowManager$LayoutParams
            int r3 = r11.d
            int r4 = r11.e
            r5 = 1002(0x3ea, float:1.404E-42)
            r6 = 8519680(0x820000, float:1.1938615E-38)
            r7 = -3
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            int r1 = r11.c
            r0.gravity = r1
            int r1 = r11.f
            r0.windowAnimations = r1
            android.view.ViewGroup r1 = r11.g
            r8.addView(r1, r0)
            r11.o = r9
            goto L_0x000c
        L_0x00ce:
            boolean r0 = r11.f338q
            if (r0 == 0) goto L_0x0064
            android.view.ViewGroup r0 = r11.g
            int r0 = r0.getChildCount()
            if (r0 <= 0) goto L_0x0064
            android.view.ViewGroup r0 = r11.g
            r0.removeAllViews()
            goto L_0x0064
        L_0x00e0:
            android.view.View r0 = r11.i
            if (r0 == 0) goto L_0x00f0
            android.view.View r0 = r11.i
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            if (r0 == 0) goto L_0x00f0
            int r0 = r0.width
            if (r0 == r1) goto L_0x00ad
        L_0x00f0:
            r1 = r2
            goto L_0x00ad
        L_0x00f2:
            r1 = r0
            goto L_0x007e
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImpl.a(android.support.v7.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean a(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(n());
        panelFeatureState.g = new f(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    private void a(MenuBuilder menuBuilder, boolean z2) {
        if (this.y == null || !this.y.e() || (ViewConfiguration.get(this.a).hasPermanentMenuKey() && !this.y.g())) {
            PanelFeatureState a2 = a(0, true);
            a2.f338q = true;
            a(a2, false);
            a(a2, (KeyEvent) null);
            return;
        }
        Callback m2 = m();
        if (this.y.f() && z2) {
            this.y.i();
            if (!this.r) {
                m2.onPanelClosed(108, a(0, true).j);
            }
        } else if (m2 != null && !this.r) {
            if (this.s && (this.t & 1) != 0) {
                this.b.getDecorView().removeCallbacks(this.P);
                this.P.run();
            }
            PanelFeatureState a3 = a(0, true);
            if (a3.j != null && !a3.r && m2.onPreparePanel(0, a3.i, a3.j)) {
                m2.onMenuOpened(108, a3.j);
                this.y.h();
            }
        }
    }

    private boolean b(PanelFeatureState panelFeatureState) {
        Context context;
        Context context2 = this.a;
        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.y != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context2.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context2.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context2.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            Theme theme3 = theme2;
            if (theme3 != null) {
                context = new android.support.v7.view.d(context2, 0);
                context.getTheme().setTo(theme3);
                MenuBuilder menuBuilder = new MenuBuilder(context);
                menuBuilder.a((android.support.v7.view.menu.MenuBuilder.a) this);
                panelFeatureState.a(menuBuilder);
                return true;
            }
        }
        context = context2;
        MenuBuilder menuBuilder2 = new MenuBuilder(context);
        menuBuilder2.a((android.support.v7.view.menu.MenuBuilder.a) this);
        panelFeatureState.a(menuBuilder2);
        return true;
    }

    private boolean c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.i != null) {
            panelFeatureState.h = panelFeatureState.i;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.A == null) {
                this.A = new g();
            }
            panelFeatureState.h = (View) panelFeatureState.a((android.support.v7.view.menu.l.a) this.A);
            return panelFeatureState.h != null;
        }
    }

    private boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        boolean z2;
        if (this.r) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        if (!(this.K == null || this.K == panelFeatureState)) {
            a(this.K, false);
        }
        Callback m2 = m();
        if (m2 != null) {
            panelFeatureState.i = m2.onCreatePanelView(panelFeatureState.a);
        }
        boolean z3 = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z3 && this.y != null) {
            this.y.j();
        }
        if (panelFeatureState.i == null && (!z3 || !(l() instanceof f))) {
            if (panelFeatureState.j == null || panelFeatureState.r) {
                if (panelFeatureState.j == null && (!b(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z3 && this.y != null) {
                    if (this.z == null) {
                        this.z = new b();
                    }
                    this.y.a(panelFeatureState.j, this.z);
                }
                panelFeatureState.j.h();
                if (!m2.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.a((MenuBuilder) null);
                    if (!z3 || this.y == null) {
                        return false;
                    }
                    this.y.a(null, this.z);
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.h();
            if (panelFeatureState.s != null) {
                panelFeatureState.j.d(panelFeatureState.s);
                panelFeatureState.s = null;
            }
            if (!m2.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z3 && this.y != null) {
                    this.y.a(null, this.z);
                }
                panelFeatureState.j.i();
                return false;
            }
            if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            panelFeatureState.p = z2;
            panelFeatureState.j.setQwertyMode(panelFeatureState.p);
            panelFeatureState.j.i();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.K = panelFeatureState;
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(MenuBuilder menuBuilder) {
        if (!this.I) {
            this.I = true;
            this.y.k();
            Callback m2 = m();
            if (m2 != null && !this.r) {
                m2.onPanelClosed(108, menuBuilder);
            }
            this.I = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void f(int i2) {
        a(a(i2, true), true);
    }

    /* access modifiers changed from: 0000 */
    public void a(PanelFeatureState panelFeatureState, boolean z2) {
        if (!z2 || panelFeatureState.a != 0 || this.y == null || !this.y.f()) {
            WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.o || panelFeatureState.g == null)) {
                windowManager.removeView(panelFeatureState.g);
                if (z2) {
                    a(panelFeatureState.a, panelFeatureState, null);
                }
            }
            panelFeatureState.m = false;
            panelFeatureState.n = false;
            panelFeatureState.o = false;
            panelFeatureState.h = null;
            panelFeatureState.f338q = true;
            if (this.K == panelFeatureState) {
                this.K = null;
                return;
            }
            return;
        }
        b(panelFeatureState.j);
    }

    private boolean d(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState a2 = a(i2, true);
            if (!a2.o) {
                return b(a2, keyEvent);
            }
        }
        return false;
    }

    private boolean e(int i2, KeyEvent keyEvent) {
        boolean z2;
        boolean z3 = true;
        if (this.h != null) {
            return false;
        }
        PanelFeatureState a2 = a(i2, true);
        if (i2 != 0 || this.y == null || !this.y.e() || ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
            if (a2.o || a2.n) {
                boolean z4 = a2.o;
                a(a2, true);
                z3 = z4;
            } else {
                if (a2.m) {
                    if (a2.r) {
                        a2.m = false;
                        z2 = b(a2, keyEvent);
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        a(a2, keyEvent);
                    }
                }
                z3 = false;
            }
        } else if (!this.y.f()) {
            if (!this.r && b(a2, keyEvent)) {
                z3 = this.y.h();
            }
            z3 = false;
        } else {
            z3 = this.y.i();
        }
        if (z3) {
            AudioManager audioManager = (AudioManager) this.a.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z3;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0 && i2 < this.J.length) {
                panelFeatureState = this.J[i2];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.r) {
            this.c.onPanelClosed(i2, menu);
        }
    }

    /* access modifiers changed from: 0000 */
    public PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.J;
        int i2 = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i3 = 0; i3 < i2; i3++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i3];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState a(int i2, boolean z2) {
        PanelFeatureState[] panelFeatureStateArr = this.J;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[(i2 + 1)];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.J = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    private boolean a(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        boolean z2 = false;
        if (!keyEvent.isSystem()) {
            if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && panelFeatureState.j != null) {
                z2 = panelFeatureState.j.performShortcut(i2, keyEvent, i3);
            }
            if (z2 && (i3 & 1) == 0 && this.y == null) {
                a(panelFeatureState, true);
            }
        }
        return z2;
    }

    private void j(int i2) {
        this.t |= 1 << i2;
        if (!this.s) {
            ViewCompat.postOnAnimation(this.b.getDecorView(), this.P);
            this.s = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void g(int i2) {
        PanelFeatureState a2 = a(i2, true);
        if (a2.j != null) {
            Bundle bundle = new Bundle();
            a2.j.c(bundle);
            if (bundle.size() > 0) {
                a2.s = bundle;
            }
            a2.j.h();
            a2.j.clear();
        }
        a2.r = true;
        a2.f338q = true;
        if ((i2 == 108 || i2 == 0) && this.y != null) {
            PanelFeatureState a3 = a(0, false);
            if (a3 != null) {
                a3.m = false;
                b(a3, (KeyEvent) null);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int h(int i2) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        int i3 = 0;
        if (this.i == null || !(this.i.getLayoutParams() instanceof MarginLayoutParams)) {
            z2 = false;
        } else {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
            if (this.i.isShown()) {
                if (this.R == null) {
                    this.R = new Rect();
                    this.S = new Rect();
                }
                Rect rect = this.R;
                Rect rect2 = this.S;
                rect.set(0, i2, 0, 0);
                bb.a(this.D, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    if (this.F == null) {
                        this.F = new View(this.a);
                        this.F.setBackgroundColor(this.a.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.D.addView(this.F, -1, new LayoutParams(-1, i2));
                        z4 = true;
                    } else {
                        LayoutParams layoutParams = this.F.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.F.setLayoutParams(layoutParams);
                        }
                        z4 = true;
                    }
                } else {
                    z4 = false;
                }
                if (this.F == null) {
                    z5 = false;
                }
                if (!this.o && z5) {
                    i2 = 0;
                }
                boolean z6 = z4;
                z3 = z5;
                z5 = z6;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z3 = false;
            } else {
                z5 = false;
                z3 = false;
            }
            if (z5) {
                this.i.setLayoutParams(marginLayoutParams);
            }
            z2 = z3;
        }
        if (this.F != null) {
            View view = this.F;
            if (!z2) {
                i3 = 8;
            }
            view.setVisibility(i3);
        }
        return i2;
    }

    private void y() {
        if (this.C) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int k(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 != 9) {
            return i2;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    /* access modifiers changed from: 0000 */
    public void t() {
        if (this.y != null) {
            this.y.k();
        }
        if (this.j != null) {
            this.b.getDecorView().removeCallbacks(this.k);
            if (this.j.isShowing()) {
                try {
                    this.j.dismiss();
                } catch (IllegalArgumentException e2) {
                }
            }
            this.j = null;
        }
        r();
        PanelFeatureState a2 = a(0, false);
        if (a2 != null && a2.j != null) {
            a2.j.close();
        }
    }

    public boolean j() {
        boolean z2 = false;
        int z3 = z();
        int i2 = i(z3);
        if (i2 != -1) {
            z2 = l(i2);
        }
        if (z3 == 0) {
            A();
            this.O.c();
        }
        this.N = true;
        return z2;
    }

    /* access modifiers changed from: 0000 */
    public int i(int i2) {
        switch (i2) {
            case -100:
                return -1;
            case 0:
                if (VERSION.SDK_INT >= 23 && ((UiModeManager) this.a.getSystemService(UiModeManager.class)).getNightMode() == 0) {
                    return -1;
                }
                A();
                return this.O.a();
            default:
                return i2;
        }
    }

    private int z() {
        return this.M != -100 ? this.M : k();
    }

    private boolean l(int i2) {
        Resources resources = this.a.getResources();
        Configuration configuration = resources.getConfiguration();
        int i3 = configuration.uiMode & 48;
        int i4 = i2 == 2 ? 32 : 16;
        if (i3 == i4) {
            return false;
        }
        if (B()) {
            ((Activity) this.a).recreate();
        } else {
            Configuration configuration2 = new Configuration(configuration);
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration2.uiMode = i4 | (configuration2.uiMode & -49);
            resources.updateConfiguration(configuration2, displayMetrics);
            if (VERSION.SDK_INT < 26) {
                e.a(resources);
            }
        }
        return true;
    }

    private void A() {
        if (this.O == null) {
            this.O = new e(h.a(this.a));
        }
    }

    private boolean B() {
        if (!this.N || !(this.a instanceof Activity)) {
            return false;
        }
        try {
            if ((this.a.getPackageManager().getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) == 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e2) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
            return true;
        }
    }

    public final android.support.v7.app.ActionBarDrawerToggle.a h() {
        return new a();
    }
}
