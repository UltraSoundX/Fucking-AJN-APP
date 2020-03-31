package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;

/* compiled from: VectorEnabledTintResources */
public class ay extends Resources {
    private static boolean a = false;
    private final WeakReference<Context> b;

    public static boolean a() {
        return b() && VERSION.SDK_INT <= 20;
    }

    public ay(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.b = new WeakReference<>(context);
    }

    public Drawable getDrawable(int i) throws NotFoundException {
        Context context = (Context) this.b.get();
        if (context != null) {
            return f.a().a(context, this, i);
        }
        return super.getDrawable(i);
    }

    /* access modifiers changed from: 0000 */
    public final Drawable a(int i) {
        return super.getDrawable(i);
    }

    public static boolean b() {
        return a;
    }
}
