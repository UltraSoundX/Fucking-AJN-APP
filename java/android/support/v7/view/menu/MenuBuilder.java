package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
    private static final int[] d = {1, 4, 5, 3, 2, 0};
    private boolean A;
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private a i;
    private ArrayList<MenuItemImpl> j;
    private ArrayList<MenuItemImpl> k;
    private boolean l;
    private ArrayList<MenuItemImpl> m;
    private ArrayList<MenuItemImpl> n;
    private boolean o;
    private int p = 0;

    /* renamed from: q reason: collision with root package name */
    private ContextMenuInfo f344q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private ArrayList<MenuItemImpl> w = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<l>> x = new CopyOnWriteArrayList<>();
    private MenuItemImpl y;
    private boolean z = false;

    public interface a {
        void a(MenuBuilder menuBuilder);

        boolean a(MenuBuilder menuBuilder, MenuItem menuItem);
    }

    public interface b {
        boolean a(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        this.e = context;
        this.f = context.getResources();
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        this.l = true;
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        this.o = true;
        e(true);
    }

    public MenuBuilder a(int i2) {
        this.p = i2;
        return this;
    }

    public void a(l lVar) {
        a(lVar, this.e);
    }

    public void a(l lVar, Context context) {
        this.x.add(new WeakReference(lVar));
        lVar.a(context, this);
        this.o = true;
    }

    public void b(l lVar) {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            l lVar2 = (l) weakReference.get();
            if (lVar2 == null || lVar2 == lVar) {
                this.x.remove(weakReference);
            }
        }
    }

    private void d(boolean z2) {
        if (!this.x.isEmpty()) {
            h();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                } else {
                    lVar.a(z2);
                }
            }
            i();
        }
    }

    private boolean a(SubMenuBuilder subMenuBuilder, l lVar) {
        boolean z2 = false;
        if (this.x.isEmpty()) {
            return false;
        }
        if (lVar != null) {
            z2 = lVar.a(subMenuBuilder);
        }
        Iterator it = this.x.iterator();
        while (true) {
            boolean z3 = z2;
            if (!it.hasNext()) {
                return z3;
            }
            WeakReference weakReference = (WeakReference) it.next();
            l lVar2 = (l) weakReference.get();
            if (lVar2 == null) {
                this.x.remove(weakReference);
            } else if (!z3) {
                z3 = lVar2.a(subMenuBuilder);
            }
            z2 = z3;
        }
    }

    private void e(Bundle bundle) {
        if (!this.x.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                } else {
                    int b2 = lVar.b();
                    if (b2 > 0) {
                        Parcelable c2 = lVar.c();
                        if (c2 != null) {
                            sparseArray.put(b2, c2);
                        }
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
    }

    private void f(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !this.x.isEmpty()) {
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                } else {
                    int b2 = lVar.b();
                    if (b2 > 0) {
                        Parcelable parcelable = (Parcelable) sparseParcelableArray.get(b2);
                        if (parcelable != null) {
                            lVar.a(parcelable);
                        }
                    }
                }
            }
        }
    }

    public void a(Bundle bundle) {
        e(bundle);
    }

    public void b(Bundle bundle) {
        f(bundle);
    }

    public void c(Bundle bundle) {
        int size = size();
        int i2 = 0;
        SparseArray sparseArray = null;
        while (i2 < size) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            SparseArray sparseArray2 = sparseArray;
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).c(bundle);
            }
            i2++;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(a(), sparseArray);
        }
    }

    public void d(Bundle bundle) {
        if (bundle != null) {
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(a());
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = getItem(i2);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).d(bundle);
                }
            }
            int i3 = bundle.getInt("android:menu:expandedactionview");
            if (i3 > 0) {
                MenuItem findItem = findItem(i3);
                if (findItem != null) {
                    findItem.expandActionView();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String a() {
        return "android:menu:actionviewstates";
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    /* access modifiers changed from: protected */
    public MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int f2 = f(i4);
        MenuItemImpl a2 = a(i2, i3, i4, f2, charSequence, this.p);
        if (this.f344q != null) {
            a2.a(this.f344q);
        }
        this.j.add(a(this.j, f2), a2);
        a(true);
        return a2;
    }

    private MenuItemImpl a(int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new MenuItemImpl(this, i2, i3, i4, i5, charSequence, i6);
    }

    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f.getString(i2));
    }

    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f.getString(i5));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, (CharSequence) this.f.getString(i2));
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) a(i2, i3, i4, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.e, this, menuItemImpl);
        menuItemImpl.a(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, (CharSequence) this.f.getString(i5));
    }

    public void setGroupDividerEnabled(boolean z2) {
        this.z = z2;
    }

    public boolean b() {
        return this.z;
    }

    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        Intent intent2;
        PackageManager packageManager = this.e.getPackageManager();
        List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int i6 = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < i6; i7++) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivityOptions.get(i7);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[resolveInfo.specificIndex];
            }
            Intent intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent4 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent4;
            }
        }
        return i6;
    }

    public void removeItem(int i2) {
        a(b(i2), true);
    }

    public void removeGroup(int i2) {
        int c2 = c(i2);
        if (c2 >= 0) {
            int size = this.j.size() - c2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || ((MenuItemImpl) this.j.get(c2)).getGroupId() != i2) {
                    a(true);
                } else {
                    a(c2, false);
                    i3 = i4;
                }
            }
            a(true);
        }
    }

    private void a(int i2, boolean z2) {
        if (i2 >= 0 && i2 < this.j.size()) {
            this.j.remove(i2);
            if (z2) {
                a(true);
            }
        }
    }

    public void clear() {
        if (this.y != null) {
            d(this.y);
        }
        this.j.clear();
        a(true);
    }

    /* access modifiers changed from: 0000 */
    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.j.size();
        h();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.getGroupId() == groupId && menuItemImpl.f() && menuItemImpl.isCheckable()) {
                menuItemImpl.b(menuItemImpl == menuItem);
            }
        }
        i();
    }

    public void setGroupCheckable(int i2, boolean z2, boolean z3) {
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i3);
            if (menuItemImpl.getGroupId() == i2) {
                menuItemImpl.a(z3);
                menuItemImpl.setCheckable(z2);
            }
        }
    }

    public void setGroupVisible(int i2, boolean z2) {
        boolean z3;
        int size = this.j.size();
        int i3 = 0;
        boolean z4 = false;
        while (i3 < size) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i3);
            if (menuItemImpl.getGroupId() != i2 || !menuItemImpl.c(z2)) {
                z3 = z4;
            } else {
                z3 = true;
            }
            i3++;
            z4 = z3;
        }
        if (z4) {
            a(true);
        }
    }

    public void setGroupEnabled(int i2, boolean z2) {
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i3);
            if (menuItemImpl.getGroupId() == i2) {
                menuItemImpl.setEnabled(z2);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((MenuItemImpl) this.j.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i3);
            if (menuItemImpl.getItemId() == i2) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu()) {
                MenuItem findItem = menuItemImpl.getSubMenu().findItem(i2);
                if (findItem != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    public int b(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (((MenuItemImpl) this.j.get(i3)).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public int c(int i2) {
        return a(i2, 0);
    }

    public int a(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        for (int i4 = i3; i4 < size; i4++) {
            if (((MenuItemImpl) this.j.get(i4)).getGroupId() == i2) {
                return i4;
            }
        }
        return -1;
    }

    public int size() {
        return this.j.size();
    }

    public MenuItem getItem(int i2) {
        return (MenuItem) this.j.get(i2);
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return a(i2, keyEvent) != null;
    }

    public void setQwertyMode(boolean z2) {
        this.g = z2;
        a(false);
    }

    private static int f(int i2) {
        int i3 = (-65536 & i2) >> 16;
        if (i3 >= 0 && i3 < d.length) {
            return (d[i3] << 16) | (65535 & i2);
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.g;
    }

    private void e(boolean z2) {
        boolean z3 = true;
        if (!z2 || this.f.getConfiguration().keyboard == 1 || !ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.e), this.e)) {
            z3 = false;
        }
        this.h = z3;
    }

    public boolean d() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public Resources e() {
        return this.f;
    }

    public Context f() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.i != null && this.i.a(menuBuilder, menuItem);
    }

    public void g() {
        if (this.i != null) {
            this.i.a(this);
        }
    }

    private static int a(ArrayList<MenuItemImpl> arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((MenuItemImpl) arrayList.get(size)).b() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        MenuItemImpl a2 = a(i2, keyEvent);
        boolean z2 = false;
        if (a2 != null) {
            z2 = a((MenuItem) a2, i3);
        }
        if ((i3 & 2) != 0) {
            b(true);
        }
        return z2;
    }

    /* access modifiers changed from: 0000 */
    public void a(List<MenuItemImpl> list, int i2, KeyEvent keyEvent) {
        boolean z2;
        boolean c2 = c();
        int modifiers = keyEvent.getModifiers();
        KeyData keyData = new KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.j.size();
            for (int i3 = 0; i3 < size; i3++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i3);
                if (menuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) menuItemImpl.getSubMenu()).a(list, i2, keyEvent);
                }
                char numericShortcut = c2 ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
                if ((modifiers & SupportMenu.SUPPORTED_MODIFIERS_MASK) == ((c2 ? menuItemImpl.getAlphabeticModifiers() : menuItemImpl.getNumericModifiers()) & SupportMenu.SUPPORTED_MODIFIERS_MASK)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 && numericShortcut != 0 && ((numericShortcut == keyData.meta[0] || numericShortcut == keyData.meta[2] || (c2 && numericShortcut == 8 && i2 == 67)) && menuItemImpl.isEnabled())) {
                    list.add(menuItemImpl);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public MenuItemImpl a(int i2, KeyEvent keyEvent) {
        char numericShortcut;
        ArrayList<MenuItemImpl> arrayList = this.w;
        arrayList.clear();
        a((List<MenuItemImpl>) arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (MenuItemImpl) arrayList.get(0);
        }
        boolean c2 = c();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i3);
            if (c2) {
                numericShortcut = menuItemImpl.getAlphabeticShortcut();
            } else {
                numericShortcut = menuItemImpl.getNumericShortcut();
            }
            if (numericShortcut == keyData.meta[0] && (metaState & 2) == 0) {
                return menuItemImpl;
            }
            if (numericShortcut == keyData.meta[2] && (metaState & 2) != 0) {
                return menuItemImpl;
            }
            if (c2 && numericShortcut == 8 && i2 == 67) {
                return menuItemImpl;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i2, int i3) {
        return a(findItem(i2), i3);
    }

    public boolean a(MenuItem menuItem, int i2) {
        return a(menuItem, (l) null, i2);
    }

    public boolean a(MenuItem menuItem, l lVar, int i2) {
        boolean z2;
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean a2 = menuItemImpl.a();
        ActionProvider supportActionProvider = menuItemImpl.getSupportActionProvider();
        if (supportActionProvider == null || !supportActionProvider.hasSubMenu()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (menuItemImpl.m()) {
            boolean expandActionView = menuItemImpl.expandActionView() | a2;
            if (!expandActionView) {
                return expandActionView;
            }
            b(true);
            return expandActionView;
        } else if (menuItemImpl.hasSubMenu() || z2) {
            if ((i2 & 4) == 0) {
                b(false);
            }
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.a(new SubMenuBuilder(f(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z2) {
                supportActionProvider.onPrepareSubMenu(subMenuBuilder);
            }
            boolean a3 = a(subMenuBuilder, lVar) | a2;
            if (a3) {
                return a3;
            }
            b(true);
            return a3;
        } else {
            if ((i2 & 1) == 0) {
                b(true);
            }
            return a2;
        }
    }

    public final void b(boolean z2) {
        if (!this.v) {
            this.v = true;
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                } else {
                    lVar.a(this, z2);
                }
            }
            this.v = false;
        }
    }

    public void close() {
        b(true);
    }

    public void a(boolean z2) {
        if (!this.r) {
            if (z2) {
                this.l = true;
                this.o = true;
            }
            d(z2);
            return;
        }
        this.s = true;
        if (z2) {
            this.t = true;
        }
    }

    public void h() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    public void i() {
        this.r = false;
        if (this.s) {
            this.s = false;
            a(this.t);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(MenuItemImpl menuItemImpl) {
        this.l = true;
        a(true);
    }

    /* access modifiers changed from: 0000 */
    public void b(MenuItemImpl menuItemImpl) {
        this.o = true;
        a(true);
    }

    public ArrayList<MenuItemImpl> j() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.isVisible()) {
                this.k.add(menuItemImpl);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }

    public void k() {
        boolean a2;
        ArrayList j2 = j();
        if (this.o) {
            Iterator it = this.x.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                    a2 = z2;
                } else {
                    a2 = lVar.a() | z2;
                }
                z2 = a2;
            }
            if (z2) {
                this.m.clear();
                this.n.clear();
                int size = j2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) j2.get(i2);
                    if (menuItemImpl.i()) {
                        this.m.add(menuItemImpl);
                    } else {
                        this.n.add(menuItemImpl);
                    }
                }
            } else {
                this.m.clear();
                this.n.clear();
                this.n.addAll(j());
            }
            this.o = false;
        }
    }

    public ArrayList<MenuItemImpl> l() {
        k();
        return this.m;
    }

    public ArrayList<MenuItemImpl> m() {
        k();
        return this.n;
    }

    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        a(false);
    }

    private void a(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources e2 = e();
        if (view != null) {
            this.c = view;
            this.a = null;
            this.b = null;
        } else {
            if (i2 > 0) {
                this.a = e2.getText(i2);
            } else if (charSequence != null) {
                this.a = charSequence;
            }
            if (i3 > 0) {
                this.b = ContextCompat.getDrawable(f(), i3);
            } else if (drawable != null) {
                this.b = drawable;
            }
            this.c = null;
        }
        a(false);
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder d(int i2) {
        a(i2, null, 0, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder e(int i2) {
        a(0, null, i2, null, null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public CharSequence n() {
        return this.a;
    }

    public Drawable o() {
        return this.b;
    }

    public View p() {
        return this.c;
    }

    public MenuBuilder q() {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public boolean r() {
        return this.u;
    }

    public boolean c(MenuItemImpl menuItemImpl) {
        boolean z2 = false;
        if (!this.x.isEmpty()) {
            h();
            Iterator it = this.x.iterator();
            while (true) {
                boolean z3 = z2;
                if (!it.hasNext()) {
                    z2 = z3;
                    break;
                }
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                    z2 = z3;
                } else {
                    z2 = lVar.a(this, menuItemImpl);
                    if (z2) {
                        break;
                    }
                }
            }
            i();
            if (z2) {
                this.y = menuItemImpl;
            }
        }
        return z2;
    }

    public boolean d(MenuItemImpl menuItemImpl) {
        boolean z2 = false;
        if (!this.x.isEmpty() && this.y == menuItemImpl) {
            h();
            Iterator it = this.x.iterator();
            while (true) {
                boolean z3 = z2;
                if (!it.hasNext()) {
                    z2 = z3;
                    break;
                }
                WeakReference weakReference = (WeakReference) it.next();
                l lVar = (l) weakReference.get();
                if (lVar == null) {
                    this.x.remove(weakReference);
                    z2 = z3;
                } else {
                    z2 = lVar.b(this, menuItemImpl);
                    if (z2) {
                        break;
                    }
                }
            }
            i();
            if (z2) {
                this.y = null;
            }
        }
        return z2;
    }

    public MenuItemImpl s() {
        return this.y;
    }

    public void c(boolean z2) {
        this.A = z2;
    }
}
