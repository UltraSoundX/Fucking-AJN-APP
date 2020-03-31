package android.support.v7.app;

import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.b.a.d;
import android.view.View;

public class ActionBarDrawerToggle implements DrawerListener {
    boolean a;
    private final a b;
    private d c;
    private boolean d;
    private final int e;
    private final int f;

    public interface a {
        void a(int i);
    }

    public void onDrawerSlide(View view, float f2) {
        if (this.d) {
            a(Math.min(1.0f, Math.max(0.0f, f2)));
        } else {
            a(0.0f);
        }
    }

    public void onDrawerOpened(View view) {
        a(1.0f);
        if (this.a) {
            a(this.f);
        }
    }

    public void onDrawerClosed(View view) {
        a(0.0f);
        if (this.a) {
            a(this.e);
        }
    }

    public void onDrawerStateChanged(int i) {
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        this.b.a(i);
    }

    private void a(float f2) {
        if (f2 == 1.0f) {
            this.c.a(true);
        } else if (f2 == 0.0f) {
            this.c.a(false);
        }
        this.c.a(f2);
    }
}
