package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider extends ActionProvider {
    final Context a;
    String b;
    private int c;
    private final a d;

    private class a implements OnMenuItemClickListener {
        final /* synthetic */ ShareActionProvider a;

        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent b = b.a(this.a.a, this.a.b).b(menuItem.getItemId());
            if (b != null) {
                String action = b.getAction();
                if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                    this.a.a(b);
                }
                this.a.a.startActivity(b);
            }
            return true;
        }
    }

    public View onCreateActionView() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.a);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(b.a(this.a, this.b));
        }
        TypedValue typedValue = new TypedValue();
        this.a.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(android.support.v7.a.a.a.b(this.a, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    public boolean hasSubMenu() {
        return true;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        b a2 = b.a(this.a, this.b);
        PackageManager packageManager = this.a.getPackageManager();
        int a3 = a2.a();
        int min = Math.min(a3, this.c);
        for (int i = 0; i < min; i++) {
            ResolveInfo a4 = a2.a(i);
            subMenu.add(0, i, i, a4.loadLabel(packageManager)).setIcon(a4.loadIcon(packageManager)).setOnMenuItemClickListener(this.d);
        }
        if (min < a3) {
            SubMenu addSubMenu = subMenu.addSubMenu(0, min, min, this.a.getString(R.string.abc_activity_chooser_view_see_all));
            for (int i2 = 0; i2 < a3; i2++) {
                ResolveInfo a5 = a2.a(i2);
                addSubMenu.add(0, i2, i2, a5.loadLabel(packageManager)).setIcon(a5.loadIcon(packageManager)).setOnMenuItemClickListener(this.d);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Intent intent) {
        if (VERSION.SDK_INT >= 21) {
            intent.addFlags(134742016);
        } else {
            intent.addFlags(524288);
        }
    }
}
