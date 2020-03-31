package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.f;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* compiled from: MenuPopupWindow */
public class ad extends ListPopupWindow implements ac {
    private static Method a;
    private ac b;

    /* compiled from: MenuPopupWindow */
    public static class a extends w {
        final int b;
        final int c;
        private ac d;
        private MenuItem e;

        public /* bridge */ /* synthetic */ int a(int i, int i2, int i3, int i4, int i5) {
            return super.a(i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ boolean a(MotionEvent motionEvent, int i) {
            return super.a(motionEvent, i);
        }

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.b = 22;
                this.c = 21;
                return;
            }
            this.b = 21;
            this.c = 22;
        }

        public void setHoverListener(ac acVar) {
            this.d = acVar;
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.b) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i != this.c) {
                return super.onKeyDown(i, keyEvent);
            } else {
                setSelection(-1);
                ((f) getAdapter()).a().b(false);
                return true;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onHoverEvent(android.view.MotionEvent r6) {
            /*
                r5 = this;
                android.support.v7.widget.ac r0 = r5.d
                if (r0 == 0) goto L_0x0058
                android.widget.ListAdapter r0 = r5.getAdapter()
                boolean r1 = r0 instanceof android.widget.HeaderViewListAdapter
                if (r1 == 0) goto L_0x005d
                android.widget.HeaderViewListAdapter r0 = (android.widget.HeaderViewListAdapter) r0
                int r1 = r0.getHeadersCount()
                android.widget.ListAdapter r0 = r0.getWrappedAdapter()
                android.support.v7.view.menu.f r0 = (android.support.v7.view.menu.f) r0
            L_0x0018:
                r2 = 0
                int r3 = r6.getAction()
                r4 = 10
                if (r3 == r4) goto L_0x0061
                float r3 = r6.getX()
                int r3 = (int) r3
                float r4 = r6.getY()
                int r4 = (int) r4
                int r3 = r5.pointToPosition(r3, r4)
                r4 = -1
                if (r3 == r4) goto L_0x0061
                int r1 = r3 - r1
                if (r1 < 0) goto L_0x0061
                int r3 = r0.getCount()
                if (r1 >= r3) goto L_0x0061
                android.support.v7.view.menu.MenuItemImpl r1 = r0.getItem(r1)
            L_0x0040:
                android.view.MenuItem r2 = r5.e
                if (r2 == r1) goto L_0x0058
                android.support.v7.view.menu.MenuBuilder r0 = r0.a()
                if (r2 == 0) goto L_0x004f
                android.support.v7.widget.ac r3 = r5.d
                r3.a(r0, r2)
            L_0x004f:
                r5.e = r1
                if (r1 == 0) goto L_0x0058
                android.support.v7.widget.ac r2 = r5.d
                r2.b(r0, r1)
            L_0x0058:
                boolean r0 = super.onHoverEvent(r6)
                return r0
            L_0x005d:
                r1 = 0
                android.support.v7.view.menu.f r0 = (android.support.v7.view.menu.f) r0
                goto L_0x0018
            L_0x0061:
                r1 = r2
                goto L_0x0040
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ad.a.onHoverEvent(android.view.MotionEvent):boolean");
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public ad(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: 0000 */
    public w a(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }

    public void a(Object obj) {
        if (VERSION.SDK_INT >= 23) {
            this.g.setEnterTransition((Transition) obj);
        }
    }

    public void b(Object obj) {
        if (VERSION.SDK_INT >= 23) {
            this.g.setExitTransition((Transition) obj);
        }
    }

    public void a(ac acVar) {
        this.b = acVar;
    }

    public void c(boolean z) {
        if (a != null) {
            try {
                a.invoke(this.g, new Object[]{Boolean.valueOf(z)});
            } catch (Exception e) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void b(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.b != null) {
            this.b.b(menuBuilder, menuItem);
        }
    }

    public void a(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.b != null) {
            this.b.a(menuBuilder, menuItem);
        }
    }
}
