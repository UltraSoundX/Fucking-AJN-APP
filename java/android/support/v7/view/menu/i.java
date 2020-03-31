package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

/* compiled from: MenuItemWrapperJB */
class i extends h {

    /* compiled from: MenuItemWrapperJB */
    class a extends a implements VisibilityListener {
        ActionProvider.VisibilityListener c;

        public a(Context context, android.view.ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        public View onCreateActionView(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        public boolean overridesItemVisibility() {
            return this.a.overridesItemVisibility();
        }

        public boolean isVisible() {
            return this.a.isVisible();
        }

        public void refreshVisibility() {
            this.a.refreshVisibility();
        }

        public void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
            this.c = visibilityListener;
            android.view.ActionProvider actionProvider = this.a;
            if (visibilityListener == null) {
                this = null;
            }
            actionProvider.setVisibilityListener(this);
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            if (this.c != null) {
                this.c.onActionProviderVisibilityChanged(z);
            }
        }
    }

    i(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    /* access modifiers changed from: 0000 */
    public a a(android.view.ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
}
