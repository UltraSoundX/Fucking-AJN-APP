package android.support.constraint.a.a;

import android.support.constraint.a.a.b.C0004b;
import java.util.ArrayList;

/* compiled from: Snapshot */
public class h {
    private int a;
    private int b;
    private int c;
    private int d;
    private ArrayList<a> e = new ArrayList<>();

    /* compiled from: Snapshot */
    static class a {
        private b a;
        private b b;
        private int c;
        private C0004b d;
        private int e;

        public a(b bVar) {
            this.a = bVar;
            this.b = bVar.f();
            this.c = bVar.d();
            this.d = bVar.e();
            this.e = bVar.h();
        }

        public void a(c cVar) {
            this.a = cVar.a(this.a.c());
            if (this.a != null) {
                this.b = this.a.f();
                this.c = this.a.d();
                this.d = this.a.e();
                this.e = this.a.h();
                return;
            }
            this.b = null;
            this.c = 0;
            this.d = C0004b.STRONG;
            this.e = 0;
        }

        public void b(c cVar) {
            cVar.a(this.a.c()).a(this.b, this.c, this.d, this.e);
        }
    }

    public h(c cVar) {
        this.a = cVar.j();
        this.b = cVar.k();
        this.c = cVar.l();
        this.d = cVar.m();
        ArrayList y = cVar.y();
        int size = y.size();
        for (int i = 0; i < size; i++) {
            this.e.add(new a((b) y.get(i)));
        }
    }

    public void a(c cVar) {
        this.a = cVar.j();
        this.b = cVar.k();
        this.c = cVar.l();
        this.d = cVar.m();
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            ((a) this.e.get(i)).a(cVar);
        }
    }

    public void b(c cVar) {
        cVar.b(this.a);
        cVar.c(this.b);
        cVar.d(this.c);
        cVar.e(this.d);
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            ((a) this.e.get(i)).b(cVar);
        }
    }
}
