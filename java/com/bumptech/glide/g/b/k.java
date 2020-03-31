package com.bumptech.glide.g.b;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import com.bumptech.glide.g.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ViewTarget */
public abstract class k<T extends View, Z> extends a<Z> {
    private static boolean b = false;
    private static Integer c = null;
    protected final T a;
    private final a d;

    /* compiled from: ViewTarget */
    private static class a {
        private final View a;
        private final List<h> b = new ArrayList();
        private C0046a c;
        private Point d;

        /* renamed from: com.bumptech.glide.g.b.k$a$a reason: collision with other inner class name */
        /* compiled from: ViewTarget */
        private static class C0046a implements OnPreDrawListener {
            private final WeakReference<a> a;

            public C0046a(a aVar) {
                this.a = new WeakReference<>(aVar);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called listener=" + this);
                }
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    aVar.a();
                }
                return true;
            }
        }

        public a(View view) {
            this.a = view;
        }

        private void a(int i, int i2) {
            for (h a2 : this.b) {
                a2.a(i, i2);
            }
            this.b.clear();
        }

        /* access modifiers changed from: private */
        public void a() {
            if (!this.b.isEmpty()) {
                int c2 = c();
                int b2 = b();
                if (a(c2) && a(b2)) {
                    a(c2, b2);
                    ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this.c);
                    }
                    this.c = null;
                }
            }
        }

        public void a(h hVar) {
            int c2 = c();
            int b2 = b();
            if (!a(c2) || !a(b2)) {
                if (!this.b.contains(hVar)) {
                    this.b.add(hVar);
                }
                if (this.c == null) {
                    ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
                    this.c = new C0046a(this);
                    viewTreeObserver.addOnPreDrawListener(this.c);
                    return;
                }
                return;
            }
            hVar.a(c2, b2);
        }

        private int b() {
            LayoutParams layoutParams = this.a.getLayoutParams();
            if (a(this.a.getHeight())) {
                return this.a.getHeight();
            }
            if (layoutParams != null) {
                return a(layoutParams.height, true);
            }
            return 0;
        }

        private int c() {
            LayoutParams layoutParams = this.a.getLayoutParams();
            if (a(this.a.getWidth())) {
                return this.a.getWidth();
            }
            if (layoutParams != null) {
                return a(layoutParams.width, false);
            }
            return 0;
        }

        private int a(int i, boolean z) {
            if (i != -2) {
                return i;
            }
            Point d2 = d();
            return z ? d2.y : d2.x;
        }

        @TargetApi(13)
        private Point d() {
            if (this.d != null) {
                return this.d;
            }
            Display defaultDisplay = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay();
            if (VERSION.SDK_INT >= 13) {
                this.d = new Point();
                defaultDisplay.getSize(this.d);
            } else {
                this.d = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
            }
            return this.d;
        }

        private boolean a(int i) {
            return i > 0 || i == -2;
        }
    }

    public k(T t) {
        if (t == null) {
            throw new NullPointerException("View must not be null!");
        }
        this.a = t;
        this.d = new a(t);
    }

    public T a() {
        return this.a;
    }

    public void a(h hVar) {
        this.d.a(hVar);
    }

    public void a(b bVar) {
        a((Object) bVar);
    }

    public b c() {
        Object g = g();
        if (g == null) {
            return null;
        }
        if (g instanceof b) {
            return (b) g;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    private void a(Object obj) {
        if (c == null) {
            b = true;
            this.a.setTag(obj);
            return;
        }
        this.a.setTag(c.intValue(), obj);
    }

    private Object g() {
        if (c == null) {
            return this.a.getTag();
        }
        return this.a.getTag(c.intValue());
    }

    public String toString() {
        return "Target for: " + this.a;
    }
}
