package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: TintContextWrapper */
public class aq extends ContextWrapper {
    private static final Object a = new Object();
    private static ArrayList<WeakReference<aq>> b;
    private final Resources c;
    private final Theme d;

    public static Context a(Context context) {
        if (!b(context)) {
            return context;
        }
        synchronized (a) {
            if (b == null) {
                b = new ArrayList<>();
            } else {
                for (int size = b.size() - 1; size >= 0; size--) {
                    WeakReference weakReference = (WeakReference) b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        b.remove(size);
                    }
                }
                for (int size2 = b.size() - 1; size2 >= 0; size2--) {
                    WeakReference weakReference2 = (WeakReference) b.get(size2);
                    aq aqVar = weakReference2 != null ? (aq) weakReference2.get() : null;
                    if (aqVar != null && aqVar.getBaseContext() == context) {
                        return aqVar;
                    }
                }
            }
            aq aqVar2 = new aq(context);
            b.add(new WeakReference(aqVar2));
            return aqVar2;
        }
    }

    private static boolean b(Context context) {
        if ((context instanceof aq) || (context.getResources() instanceof as) || (context.getResources() instanceof ay)) {
            return false;
        }
        if (VERSION.SDK_INT < 21 || ay.a()) {
            return true;
        }
        return false;
    }

    private aq(Context context) {
        super(context);
        if (ay.a()) {
            this.c = new ay(this, context.getResources());
            this.d = this.c.newTheme();
            this.d.setTo(context.getTheme());
            return;
        }
        this.c = new as(this, context.getResources());
        this.d = null;
    }

    public Theme getTheme() {
        return this.d == null ? super.getTheme() : this.d;
    }

    public void setTheme(int i) {
        if (this.d == null) {
            super.setTheme(i);
        } else {
            this.d.applyStyle(i, true);
        }
    }

    public Resources getResources() {
        return this.c;
    }

    public AssetManager getAssets() {
        return this.c.getAssets();
    }
}
