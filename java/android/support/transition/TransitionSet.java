package android.support.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.transition.Transition.b;
import android.support.transition.Transition.c;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet extends Transition {
    int a;
    boolean i = false;
    private ArrayList<Transition> j = new ArrayList<>();
    private boolean k = true;
    private int l = 0;

    static class a extends q {
        TransitionSet a;

        a(TransitionSet transitionSet) {
            this.a = transitionSet;
        }

        public void d(Transition transition) {
            if (!this.a.i) {
                this.a.j();
                this.a.i = true;
            }
        }

        public void a(Transition transition) {
            this.a.a--;
            if (this.a.a == 0) {
                this.a.i = false;
                this.a.k();
            }
            transition.b((c) this);
        }
    }

    public TransitionSet() {
    }

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.i);
        a(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }

    public TransitionSet a(int i2) {
        switch (i2) {
            case 0:
                this.k = true;
                break;
            case 1:
                this.k = false;
                break;
            default:
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
        }
        return this;
    }

    public TransitionSet a(Transition transition) {
        this.j.add(transition);
        transition.e = this;
        if (this.b >= 0) {
            transition.a(this.b);
        }
        if ((this.l & 1) != 0) {
            transition.a(d());
        }
        if ((this.l & 2) != 0) {
            transition.a(o());
        }
        if ((this.l & 4) != 0) {
            transition.a(l());
        }
        if ((this.l & 8) != 0) {
            transition.a(m());
        }
        return this;
    }

    public int r() {
        return this.j.size();
    }

    public Transition b(int i2) {
        if (i2 < 0 || i2 >= this.j.size()) {
            return null;
        }
        return (Transition) this.j.get(i2);
    }

    /* renamed from: c */
    public TransitionSet a(long j2) {
        super.a(j2);
        if (this.b >= 0) {
            int size = this.j.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Transition) this.j.get(i2)).a(j2);
            }
        }
        return this;
    }

    /* renamed from: d */
    public TransitionSet b(long j2) {
        return (TransitionSet) super.b(j2);
    }

    /* renamed from: b */
    public TransitionSet a(TimeInterpolator timeInterpolator) {
        this.l |= 1;
        if (this.j != null) {
            int size = this.j.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((Transition) this.j.get(i2)).a(timeInterpolator);
            }
        }
        return (TransitionSet) super.a(timeInterpolator);
    }

    /* renamed from: a */
    public TransitionSet c(View view) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.j.size()) {
                return (TransitionSet) super.c(view);
            }
            ((Transition) this.j.get(i3)).c(view);
            i2 = i3 + 1;
        }
    }

    /* renamed from: c */
    public TransitionSet a(c cVar) {
        return (TransitionSet) super.a(cVar);
    }

    /* renamed from: g */
    public TransitionSet d(View view) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.j.size()) {
                return (TransitionSet) super.d(view);
            }
            ((Transition) this.j.get(i3)).d(view);
            i2 = i3 + 1;
        }
    }

    /* renamed from: d */
    public TransitionSet b(c cVar) {
        return (TransitionSet) super.b(cVar);
    }

    public void a(PathMotion pathMotion) {
        super.a(pathMotion);
        this.l |= 4;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.j.size()) {
                ((Transition) this.j.get(i3)).a(pathMotion);
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    private void s() {
        a aVar = new a(this);
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).a((c) aVar);
        }
        this.a = this.j.size();
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, w wVar, w wVar2, ArrayList<v> arrayList, ArrayList<v> arrayList2) {
        long c = c();
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            Transition transition = (Transition) this.j.get(i2);
            if (c > 0 && (this.k || i2 == 0)) {
                long c2 = transition.c();
                if (c2 > 0) {
                    transition.b(c2 + c);
                } else {
                    transition.b(c);
                }
            }
            transition.a(viewGroup, wVar, wVar2, arrayList, arrayList2);
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (this.j.isEmpty()) {
            j();
            k();
            return;
        }
        s();
        if (!this.k) {
            int i2 = 1;
            while (true) {
                int i3 = i2;
                if (i3 >= this.j.size()) {
                    break;
                }
                final Transition transition = (Transition) this.j.get(i3);
                ((Transition) this.j.get(i3 - 1)).a((c) new q() {
                    public void a(Transition transition) {
                        transition.e();
                        transition.b((c) this);
                    }
                });
                i2 = i3 + 1;
            }
            Transition transition2 = (Transition) this.j.get(0);
            if (transition2 != null) {
                transition2.e();
                return;
            }
            return;
        }
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).e();
        }
    }

    public void a(v vVar) {
        if (b(vVar.b)) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.b(vVar.b)) {
                    transition.a(vVar);
                    vVar.c.add(transition);
                }
            }
        }
    }

    public void b(v vVar) {
        if (b(vVar.b)) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                Transition transition = (Transition) it.next();
                if (transition.b(vVar.b)) {
                    transition.b(vVar);
                    vVar.c.add(transition);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(v vVar) {
        super.c(vVar);
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.j.get(i2)).c(vVar);
        }
    }

    public void e(View view) {
        super.e(view);
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.j.get(i2)).e(view);
        }
    }

    public void f(View view) {
        super.f(view);
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.j.get(i2)).f(view);
        }
    }

    public void a(t tVar) {
        super.a(tVar);
        this.l |= 2;
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.j.get(i2)).a(tVar);
        }
    }

    public void a(b bVar) {
        super.a(bVar);
        this.l |= 8;
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Transition) this.j.get(i2)).a(bVar);
        }
    }

    /* access modifiers changed from: 0000 */
    public String a(String str) {
        String a2 = super.a(str);
        int i2 = 0;
        while (i2 < this.j.size()) {
            String str2 = a2 + "\n" + ((Transition) this.j.get(i2)).a(str + "  ");
            i2++;
            a2 = str2;
        }
        return a2;
    }

    /* renamed from: p */
    public Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.j = new ArrayList<>();
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            transitionSet.a(((Transition) this.j.get(i2)).clone());
        }
        return transitionSet;
    }
}
