package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.VisibilityListener;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.m.a;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;

public final class MenuItemImpl implements SupportMenuItem {
    private View A;
    private ActionProvider B;
    private OnActionExpandListener C;
    private boolean D = false;
    private ContextMenuInfo E;
    MenuBuilder a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private Intent h;
    private char i;
    private int j = 4096;
    private char k;
    private int l = 4096;
    private Drawable m;
    private int n = 0;
    private SubMenuBuilder o;
    private Runnable p;

    /* renamed from: q reason: collision with root package name */
    private OnMenuItemClickListener f345q;
    private CharSequence r;
    private CharSequence s;
    private ColorStateList t = null;
    private Mode u = null;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 16;
    private int z = 0;

    MenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.a = menuBuilder;
        this.b = i3;
        this.c = i2;
        this.d = i4;
        this.e = i5;
        this.f = charSequence;
        this.z = i6;
    }

    public boolean a() {
        if ((this.f345q != null && this.f345q.onMenuItemClick(this)) || this.a.a(this.a, (MenuItem) this)) {
            return true;
        }
        if (this.p != null) {
            this.p.run();
            return true;
        }
        if (this.h != null) {
            try {
                this.a.f().startActivity(this.h);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
            }
        }
        if (this.B == null || !this.B.onPerformDefaultAction()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.a.a(false);
        return this;
    }

    public int getGroupId() {
        return this.c;
    }

    @CapturedViewProperty
    public int getItemId() {
        return this.b;
    }

    public int getOrder() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public Intent getIntent() {
        return this.h;
    }

    public MenuItem setIntent(Intent intent) {
        this.h = intent;
        return this;
    }

    public char getAlphabeticShortcut() {
        return this.k;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.k != c2) {
            this.k = Character.toLowerCase(c2);
            this.a.a(false);
        }
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (!(this.k == c2 && this.l == i2)) {
            this.k = Character.toLowerCase(c2);
            this.l = KeyEvent.normalizeMetaState(i2);
            this.a.a(false);
        }
        return this;
    }

    public int getAlphabeticModifiers() {
        return this.l;
    }

    public char getNumericShortcut() {
        return this.i;
    }

    public int getNumericModifiers() {
        return this.j;
    }

    public MenuItem setNumericShortcut(char c2) {
        if (this.i != c2) {
            this.i = c2;
            this.a.a(false);
        }
        return this;
    }

    public MenuItem setNumericShortcut(char c2, int i2) {
        if (!(this.i == c2 && this.j == i2)) {
            this.i = c2;
            this.j = KeyEvent.normalizeMetaState(i2);
            this.a.a(false);
        }
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.i = c2;
        this.k = Character.toLowerCase(c3);
        this.a.a(false);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.i = c2;
        this.j = KeyEvent.normalizeMetaState(i2);
        this.k = Character.toLowerCase(c3);
        this.l = KeyEvent.normalizeMetaState(i3);
        this.a.a(false);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public char c() {
        return this.a.c() ? this.k : this.i;
    }

    /* access modifiers changed from: 0000 */
    public String d() {
        char c2 = c();
        if (c2 == 0) {
            return "";
        }
        Resources resources = this.a.f().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.a.f()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R.string.abc_prepend_shortcut_label));
        }
        int i2 = this.a.c() ? this.l : this.j;
        a(sb, i2, 65536, resources.getString(R.string.abc_menu_meta_shortcut_label));
        a(sb, i2, 4096, resources.getString(R.string.abc_menu_ctrl_shortcut_label));
        a(sb, i2, 2, resources.getString(R.string.abc_menu_alt_shortcut_label));
        a(sb, i2, 1, resources.getString(R.string.abc_menu_shift_shortcut_label));
        a(sb, i2, 4, resources.getString(R.string.abc_menu_sym_shortcut_label));
        a(sb, i2, 8, resources.getString(R.string.abc_menu_function_shortcut_label));
        switch (c2) {
            case 8:
                sb.append(resources.getString(R.string.abc_menu_delete_shortcut_label));
                break;
            case 10:
                sb.append(resources.getString(R.string.abc_menu_enter_shortcut_label));
                break;
            case ' ':
                sb.append(resources.getString(R.string.abc_menu_space_shortcut_label));
                break;
            default:
                sb.append(c2);
                break;
        }
        return sb.toString();
    }

    private static void a(StringBuilder sb, int i2, int i3, String str) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return this.a.d() && c() != 0;
    }

    public SubMenu getSubMenu() {
        return this.o;
    }

    public boolean hasSubMenu() {
        return this.o != null;
    }

    public void a(SubMenuBuilder subMenuBuilder) {
        this.o = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    @CapturedViewProperty
    public CharSequence getTitle() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public CharSequence a(a aVar) {
        if (aVar == null || !aVar.a()) {
            return getTitle();
        }
        return getTitleCondensed();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f = charSequence;
        this.a.a(false);
        if (this.o != null) {
            this.o.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitle(int i2) {
        return setTitle((CharSequence) this.a.f().getString(i2));
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.g != null ? this.g : this.f;
        if (VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) {
            return charSequence;
        }
        return charSequence.toString();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.g = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.f;
        }
        this.a.a(false);
        return this;
    }

    public Drawable getIcon() {
        if (this.m != null) {
            return a(this.m);
        }
        if (this.n == 0) {
            return null;
        }
        Drawable b2 = android.support.v7.a.a.a.b(this.a.f(), this.n);
        this.n = 0;
        this.m = b2;
        return a(b2);
    }

    public MenuItem setIcon(Drawable drawable) {
        this.n = 0;
        this.m = drawable;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.m = null;
        this.n = i2;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public ColorStateList getIconTintList() {
        return this.t;
    }

    public MenuItem setIconTintMode(Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public Mode getIconTintMode() {
        return this.u;
    }

    private Drawable a(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (this.v) {
                DrawableCompat.setTintList(drawable, this.t);
            }
            if (this.w) {
                DrawableCompat.setTintMode(drawable, this.u);
            }
            this.x = false;
        }
        return drawable;
    }

    public boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    public MenuItem setCheckable(boolean z2) {
        int i2 = this.y;
        this.y = (z2 ? 1 : 0) | (this.y & -2);
        if (i2 != this.y) {
            this.a.a(false);
        }
        return this;
    }

    public void a(boolean z2) {
        this.y = (z2 ? 4 : 0) | (this.y & -5);
    }

    public boolean f() {
        return (this.y & 4) != 0;
    }

    public boolean isChecked() {
        return (this.y & 2) == 2;
    }

    public MenuItem setChecked(boolean z2) {
        if ((this.y & 4) != 0) {
            this.a.a((MenuItem) this);
        } else {
            b(z2);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public void b(boolean z2) {
        int i2;
        int i3 = this.y;
        int i4 = this.y & -3;
        if (z2) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        this.y = i2 | i4;
        if (i3 != this.y) {
            this.a.a(false);
        }
    }

    public boolean isVisible() {
        if (this.B == null || !this.B.overridesItemVisibility()) {
            if ((this.y & 8) != 0) {
                return false;
            }
            return true;
        } else if ((this.y & 8) != 0 || !this.B.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean c(boolean z2) {
        int i2 = this.y;
        this.y = (z2 ? 0 : 8) | (this.y & -9);
        if (i2 != this.y) {
            return true;
        }
        return false;
    }

    public MenuItem setVisible(boolean z2) {
        if (c(z2)) {
            this.a.a(this);
        }
        return this;
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f345q = onMenuItemClickListener;
        return this;
    }

    public String toString() {
        if (this.f != null) {
            return this.f.toString();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void a(ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    public ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public void g() {
        this.a.b(this);
    }

    public boolean h() {
        return this.a.r();
    }

    public boolean i() {
        return (this.y & 32) == 32;
    }

    public boolean j() {
        return (this.z & 1) == 1;
    }

    public boolean k() {
        return (this.z & 2) == 2;
    }

    public void d(boolean z2) {
        if (z2) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    public boolean l() {
        return (this.z & 4) == 4;
    }

    public void setShowAsAction(int i2) {
        switch (i2 & 3) {
            case 0:
            case 1:
            case 2:
                this.z = i2;
                this.a.b(this);
                return;
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    /* renamed from: a */
    public SupportMenuItem setActionView(View view) {
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && this.b > 0) {
            view.setId(this.b);
        }
        this.a.b(this);
        return this;
    }

    /* renamed from: a */
    public SupportMenuItem setActionView(int i2) {
        Context f2 = this.a.f();
        setActionView(LayoutInflater.from(f2).inflate(i2, new LinearLayout(f2), false));
        return this;
    }

    public View getActionView() {
        if (this.A != null) {
            return this.A;
        }
        if (this.B == null) {
            return null;
        }
        this.A = this.B.onCreateActionView(this);
        return this.A;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public ActionProvider getSupportActionProvider() {
        return this.B;
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        if (this.B != null) {
            this.B.reset();
        }
        this.A = null;
        this.B = actionProvider;
        this.a.a(true);
        if (this.B != null) {
            this.B.setVisibilityListener(new VisibilityListener() {
                public void onActionProviderVisibilityChanged(boolean z) {
                    MenuItemImpl.this.a.a(MenuItemImpl.this);
                }
            });
        }
        return this;
    }

    /* renamed from: b */
    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public boolean expandActionView() {
        if (!m()) {
            return false;
        }
        if (this.C == null || this.C.onMenuItemActionExpand(this)) {
            return this.a.c(this);
        }
        return false;
    }

    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        if (this.C == null || this.C.onMenuItemActionCollapse(this)) {
            return this.a.d(this);
        }
        return false;
    }

    public boolean m() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && this.B != null) {
            this.A = this.B.onCreateActionView(this);
        }
        if (this.A != null) {
            return true;
        }
        return false;
    }

    public void e(boolean z2) {
        this.D = z2;
        this.a.a(false);
    }

    public boolean isActionViewExpanded() {
        return this.D;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.a.a(false);
        return this;
    }

    public CharSequence getContentDescription() {
        return this.r;
    }

    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.s = charSequence;
        this.a.a(false);
        return this;
    }

    public CharSequence getTooltipText() {
        return this.s;
    }
}
