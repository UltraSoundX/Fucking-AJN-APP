package me.yokeyword.fragmentation.b;

import android.os.Handler;
import android.os.Looper;
import java.util.LinkedList;
import java.util.Queue;
import me.yokeyword.fragmentation.d;
import me.yokeyword.fragmentation.g;

/* compiled from: ActionQueue */
public class b {
    /* access modifiers changed from: private */
    public Queue<a> a = new LinkedList();
    private Handler b;

    public b(Handler handler) {
        this.b = handler;
    }

    public void a(final a aVar) {
        if (!d(aVar)) {
            if (aVar.i == 4 && this.a.isEmpty() && Thread.currentThread() == Looper.getMainLooper().getThread()) {
                aVar.a();
            } else {
                this.b.post(new Runnable() {
                    public void run() {
                        b.this.b(aVar);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(a aVar) {
        this.a.add(aVar);
        if (this.a.size() == 1) {
            a();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!this.a.isEmpty()) {
            a aVar = (a) this.a.peek();
            aVar.a();
            c(aVar);
        }
    }

    private void c(a aVar) {
        if (aVar.i == 1) {
            d c = g.c(aVar.h);
            if (c != null) {
                aVar.j = c.b().o();
            } else {
                return;
            }
        }
        this.b.postDelayed(new Runnable() {
            public void run() {
                b.this.a.poll();
                b.this.a();
            }
        }, aVar.j);
    }

    private boolean d(a aVar) {
        if (aVar.i == 3) {
            a aVar2 = (a) this.a.peek();
            if (aVar2 != null && aVar2.i == 1) {
                return true;
            }
        }
        return false;
    }
}
