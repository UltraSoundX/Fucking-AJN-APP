package android.support.constraint.a.a;

import android.support.constraint.a.c;
import java.util.ArrayList;

/* compiled from: WidgetContainer */
public class i extends c {
    protected ArrayList<c> ac = new ArrayList<>();

    public void a() {
        this.ac.clear();
        super.a();
    }

    public void c(c cVar) {
        this.ac.add(cVar);
        if (cVar.c() != null) {
            ((i) cVar.c()).d(cVar);
        }
        cVar.a((c) this);
    }

    public void d(c cVar) {
        this.ac.remove(cVar);
        cVar.a((c) null);
    }

    public d F() {
        d dVar;
        c cVar;
        c cVar2;
        c c = c();
        if (this instanceof d) {
            dVar = (d) this;
            cVar = c;
        } else {
            dVar = null;
            cVar = c;
        }
        while (cVar != null) {
            c c2 = cVar.c();
            if (cVar instanceof d) {
                dVar = (d) cVar;
                cVar2 = c2;
            } else {
                cVar2 = c2;
            }
        }
        return dVar;
    }

    public void b(int i, int i2) {
        super.b(i, i2);
        int size = this.ac.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((c) this.ac.get(i3)).b(r(), s());
        }
    }

    public void z() {
        super.z();
        if (this.ac != null) {
            int size = this.ac.size();
            for (int i = 0; i < size; i++) {
                c cVar = (c) this.ac.get(i);
                cVar.b(n(), o());
                if (!(cVar instanceof d)) {
                    cVar.z();
                }
            }
        }
    }

    public void D() {
        z();
        if (this.ac != null) {
            int size = this.ac.size();
            for (int i = 0; i < size; i++) {
                c cVar = (c) this.ac.get(i);
                if (cVar instanceof i) {
                    ((i) cVar).D();
                }
            }
        }
    }

    public void a(c cVar) {
        super.a(cVar);
        int size = this.ac.size();
        for (int i = 0; i < size; i++) {
            ((c) this.ac.get(i)).a(cVar);
        }
    }

    public void G() {
        this.ac.clear();
    }
}
