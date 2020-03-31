package android.support.design.widget;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* compiled from: SnackbarManager */
class n {
    private static n a;
    private final Object b = new Object();
    private final Handler c = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    n.this.a((b) message.obj);
                    return true;
                default:
                    return false;
            }
        }
    });
    private b d;
    private b e;

    /* compiled from: SnackbarManager */
    interface a {
        void a();

        void a(int i);
    }

    /* compiled from: SnackbarManager */
    private static class b {
        final WeakReference<a> a;
        int b;
        boolean c;

        /* access modifiers changed from: 0000 */
        public boolean a(a aVar) {
            return aVar != null && this.a.get() == aVar;
        }
    }

    static n a() {
        if (a == null) {
            a = new n();
        }
        return a;
    }

    private n() {
    }

    public void a(a aVar, int i) {
        synchronized (this.b) {
            if (f(aVar)) {
                a(this.d, i);
            } else if (g(aVar)) {
                a(this.e, i);
            }
        }
    }

    public void a(a aVar) {
        synchronized (this.b) {
            if (f(aVar)) {
                this.d = null;
                if (this.e != null) {
                    b();
                }
            }
        }
    }

    public void b(a aVar) {
        synchronized (this.b) {
            if (f(aVar)) {
                b(this.d);
            }
        }
    }

    public void c(a aVar) {
        synchronized (this.b) {
            if (f(aVar) && !this.d.c) {
                this.d.c = true;
                this.c.removeCallbacksAndMessages(this.d);
            }
        }
    }

    public void d(a aVar) {
        synchronized (this.b) {
            if (f(aVar) && this.d.c) {
                this.d.c = false;
                b(this.d);
            }
        }
    }

    public boolean e(a aVar) {
        boolean z;
        synchronized (this.b) {
            z = f(aVar) || g(aVar);
        }
        return z;
    }

    private void b() {
        if (this.e != null) {
            this.d = this.e;
            this.e = null;
            a aVar = (a) this.d.a.get();
            if (aVar != null) {
                aVar.a();
            } else {
                this.d = null;
            }
        }
    }

    private boolean a(b bVar, int i) {
        a aVar = (a) bVar.a.get();
        if (aVar == null) {
            return false;
        }
        this.c.removeCallbacksAndMessages(bVar);
        aVar.a(i);
        return true;
    }

    private boolean f(a aVar) {
        return this.d != null && this.d.a(aVar);
    }

    private boolean g(a aVar) {
        return this.e != null && this.e.a(aVar);
    }

    private void b(b bVar) {
        if (bVar.b != -2) {
            int i = 2750;
            if (bVar.b > 0) {
                i = bVar.b;
            } else if (bVar.b == -1) {
                i = 1500;
            }
            this.c.removeCallbacksAndMessages(bVar);
            this.c.sendMessageDelayed(Message.obtain(this.c, 0, bVar), (long) i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        synchronized (this.b) {
            if (this.d == bVar || this.e == bVar) {
                a(bVar, 2);
            }
        }
    }
}
