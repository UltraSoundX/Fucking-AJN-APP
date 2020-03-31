package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.view.b;
import android.support.v7.view.b.a;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* compiled from: AppCompatDelegate */
public abstract class c {
    private static int a = -1;

    public abstract ActionBar a();

    public abstract b a(a aVar);

    public abstract <T extends View> T a(int i);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(Toolbar toolbar);

    public abstract void a(View view);

    public abstract void a(View view, LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, LayoutParams layoutParams);

    public abstract void c();

    public abstract void c(Bundle bundle);

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract ActionBarDrawerToggle.a h();

    public abstract void i();

    public abstract boolean j();

    public static c a(Activity activity, b bVar) {
        return new AppCompatDelegateImpl(activity, activity.getWindow(), bVar);
    }

    public static c a(Dialog dialog, b bVar) {
        return new AppCompatDelegateImpl(dialog.getContext(), dialog.getWindow(), bVar);
    }

    c() {
    }

    public static int k() {
        return a;
    }
}
