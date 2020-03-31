package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.v;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: DefaultItemAnimator */
public class u extends am {
    private static TimeInterpolator i;
    ArrayList<ArrayList<v>> a = new ArrayList<>();
    ArrayList<ArrayList<b>> b = new ArrayList<>();
    ArrayList<ArrayList<a>> c = new ArrayList<>();
    ArrayList<v> d = new ArrayList<>();
    ArrayList<v> e = new ArrayList<>();
    ArrayList<v> f = new ArrayList<>();
    ArrayList<v> g = new ArrayList<>();
    private ArrayList<v> j = new ArrayList<>();
    private ArrayList<v> k = new ArrayList<>();
    private ArrayList<b> l = new ArrayList<>();
    private ArrayList<a> m = new ArrayList<>();

    /* compiled from: DefaultItemAnimator */
    private static class a {
        public v a;
        public v b;
        public int c;
        public int d;
        public int e;
        public int f;

        private a(v vVar, v vVar2) {
            this.a = vVar;
            this.b = vVar2;
        }

        a(v vVar, v vVar2, int i, int i2, int i3, int i4) {
            this(vVar, vVar2);
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    /* compiled from: DefaultItemAnimator */
    private static class b {
        public v a;
        public int b;
        public int c;
        public int d;
        public int e;

        b(v vVar, int i, int i2, int i3, int i4) {
            this.a = vVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    public void a() {
        boolean z;
        boolean z2;
        boolean z3;
        long j2;
        long j3;
        boolean z4 = !this.j.isEmpty();
        if (!this.l.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        if (!this.m.isEmpty()) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!this.k.isEmpty()) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z4 || z || z3 || z2) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                u((v) it.next());
            }
            this.j.clear();
            if (z) {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.l);
                this.b.add(arrayList);
                this.l.clear();
                AnonymousClass1 r8 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            b bVar = (b) it.next();
                            u.this.b(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e);
                        }
                        arrayList.clear();
                        u.this.b.remove(arrayList);
                    }
                };
                if (z4) {
                    ViewCompat.postOnAnimationDelayed(((b) arrayList.get(0)).a.itemView, r8, g());
                } else {
                    r8.run();
                }
            }
            if (z2) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.m);
                this.c.add(arrayList2);
                this.m.clear();
                AnonymousClass2 r82 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            u.this.a((a) it.next());
                        }
                        arrayList2.clear();
                        u.this.c.remove(arrayList2);
                    }
                };
                if (z4) {
                    ViewCompat.postOnAnimationDelayed(((a) arrayList2.get(0)).a.itemView, r82, g());
                } else {
                    r82.run();
                }
            }
            if (z3) {
                final ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.k);
                this.a.add(arrayList3);
                this.k.clear();
                AnonymousClass3 r12 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            u.this.c((v) it.next());
                        }
                        arrayList3.clear();
                        u.this.a.remove(arrayList3);
                    }
                };
                if (z4 || z || z2) {
                    long j4 = z4 ? g() : 0;
                    if (z) {
                        j2 = e();
                    } else {
                        j2 = 0;
                    }
                    if (z2) {
                        j3 = h();
                    } else {
                        j3 = 0;
                    }
                    ViewCompat.postOnAnimationDelayed(((v) arrayList3.get(0)).itemView, r12, j4 + Math.max(j2, j3));
                    return;
                }
                r12.run();
            }
        }
    }

    public boolean a(v vVar) {
        v(vVar);
        this.j.add(vVar);
        return true;
    }

    private void u(final v vVar) {
        final View view = vVar.itemView;
        final ViewPropertyAnimator animate = view.animate();
        this.f.add(vVar);
        animate.setDuration(g()).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                u.this.l(vVar);
            }

            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                view.setAlpha(1.0f);
                u.this.i(vVar);
                u.this.f.remove(vVar);
                u.this.c();
            }
        }).start();
    }

    public boolean b(v vVar) {
        v(vVar);
        vVar.itemView.setAlpha(0.0f);
        this.k.add(vVar);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void c(final v vVar) {
        final View view = vVar.itemView;
        final ViewPropertyAnimator animate = view.animate();
        this.d.add(vVar);
        animate.alpha(1.0f).setDuration(f()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                u.this.n(vVar);
            }

            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                u.this.k(vVar);
                u.this.d.remove(vVar);
                u.this.c();
            }
        }).start();
    }

    public boolean a(v vVar, int i2, int i3, int i4, int i5) {
        View view = vVar.itemView;
        int translationX = i2 + ((int) vVar.itemView.getTranslationX());
        int translationY = i3 + ((int) vVar.itemView.getTranslationY());
        v(vVar);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            j(vVar);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX((float) (-i6));
        }
        if (i7 != 0) {
            view.setTranslationY((float) (-i7));
        }
        this.l.add(new b(vVar, translationX, translationY, i4, i5));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(v vVar, int i2, int i3, int i4, int i5) {
        final View view = vVar.itemView;
        final int i6 = i4 - i2;
        final int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator animate = view.animate();
        this.e.add(vVar);
        final v vVar2 = vVar;
        animate.setDuration(e()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                u.this.m(vVar2);
            }

            public void onAnimationCancel(Animator animator) {
                if (i6 != 0) {
                    view.setTranslationX(0.0f);
                }
                if (i7 != 0) {
                    view.setTranslationY(0.0f);
                }
            }

            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                u.this.j(vVar2);
                u.this.e.remove(vVar2);
                u.this.c();
            }
        }).start();
    }

    public boolean a(v vVar, v vVar2, int i2, int i3, int i4, int i5) {
        if (vVar == vVar2) {
            return a(vVar, i2, i3, i4, i5);
        }
        float translationX = vVar.itemView.getTranslationX();
        float translationY = vVar.itemView.getTranslationY();
        float alpha = vVar.itemView.getAlpha();
        v(vVar);
        int i6 = (int) (((float) (i4 - i2)) - translationX);
        int i7 = (int) (((float) (i5 - i3)) - translationY);
        vVar.itemView.setTranslationX(translationX);
        vVar.itemView.setTranslationY(translationY);
        vVar.itemView.setAlpha(alpha);
        if (vVar2 != null) {
            v(vVar2);
            vVar2.itemView.setTranslationX((float) (-i6));
            vVar2.itemView.setTranslationY((float) (-i7));
            vVar2.itemView.setAlpha(0.0f);
        }
        this.m.add(new a(vVar, vVar2, i2, i3, i4, i5));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void a(final a aVar) {
        final View view = null;
        v vVar = aVar.a;
        final View view2 = vVar == null ? null : vVar.itemView;
        v vVar2 = aVar.b;
        if (vVar2 != null) {
            view = vVar2.itemView;
        }
        if (view2 != null) {
            final ViewPropertyAnimator duration = view2.animate().setDuration(h());
            this.g.add(aVar.a);
            duration.translationX((float) (aVar.e - aVar.c));
            duration.translationY((float) (aVar.f - aVar.d));
            duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    u.this.b(aVar.a, true);
                }

                public void onAnimationEnd(Animator animator) {
                    duration.setListener(null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    u.this.a(aVar.a, true);
                    u.this.g.remove(aVar.a);
                    u.this.c();
                }
            }).start();
        }
        if (view != null) {
            final ViewPropertyAnimator animate = view.animate();
            this.g.add(aVar.b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(h()).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    u.this.b(aVar.b, false);
                }

                public void onAnimationEnd(Animator animator) {
                    animate.setListener(null);
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    u.this.a(aVar.b, false);
                    u.this.g.remove(aVar.b);
                    u.this.c();
                }
            }).start();
        }
    }

    private void a(List<a> list, v vVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            if (a(aVar, vVar) && aVar.a == null && aVar.b == null) {
                list.remove(aVar);
            }
        }
    }

    private void b(a aVar) {
        if (aVar.a != null) {
            a(aVar, aVar.a);
        }
        if (aVar.b != null) {
            a(aVar, aVar.b);
        }
    }

    private boolean a(a aVar, v vVar) {
        boolean z = false;
        if (aVar.b == vVar) {
            aVar.b = null;
        } else if (aVar.a != vVar) {
            return false;
        } else {
            aVar.a = null;
            z = true;
        }
        vVar.itemView.setAlpha(1.0f);
        vVar.itemView.setTranslationX(0.0f);
        vVar.itemView.setTranslationY(0.0f);
        a(vVar, z);
        return true;
    }

    public void d(v vVar) {
        View view = vVar.itemView;
        view.animate().cancel();
        for (int size = this.l.size() - 1; size >= 0; size--) {
            if (((b) this.l.get(size)).a == vVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                j(vVar);
                this.l.remove(size);
            }
        }
        a((List<a>) this.m, vVar);
        if (this.j.remove(vVar)) {
            view.setAlpha(1.0f);
            i(vVar);
        }
        if (this.k.remove(vVar)) {
            view.setAlpha(1.0f);
            k(vVar);
        }
        for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.c.get(size2);
            a((List<a>) arrayList, vVar);
            if (arrayList.isEmpty()) {
                this.c.remove(size2);
            }
        }
        for (int size3 = this.b.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.b.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((b) arrayList2.get(size4)).a == vVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    j(vVar);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.b.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.a.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.a.get(size5);
            if (arrayList3.remove(vVar)) {
                view.setAlpha(1.0f);
                k(vVar);
                if (arrayList3.isEmpty()) {
                    this.a.remove(size5);
                }
            }
        }
        if (this.f.remove(vVar)) {
        }
        if (this.d.remove(vVar)) {
        }
        if (this.g.remove(vVar)) {
        }
        if (this.e.remove(vVar)) {
        }
        c();
    }

    private void v(v vVar) {
        if (i == null) {
            i = new ValueAnimator().getInterpolator();
        }
        vVar.itemView.animate().setInterpolator(i);
        d(vVar);
    }

    public boolean b() {
        return !this.k.isEmpty() || !this.m.isEmpty() || !this.l.isEmpty() || !this.j.isEmpty() || !this.e.isEmpty() || !this.f.isEmpty() || !this.d.isEmpty() || !this.g.isEmpty() || !this.b.isEmpty() || !this.a.isEmpty() || !this.c.isEmpty();
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        if (!b()) {
            i();
        }
    }

    public void d() {
        for (int size = this.l.size() - 1; size >= 0; size--) {
            b bVar = (b) this.l.get(size);
            View view = bVar.a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            j(bVar.a);
            this.l.remove(size);
        }
        for (int size2 = this.j.size() - 1; size2 >= 0; size2--) {
            i((v) this.j.get(size2));
            this.j.remove(size2);
        }
        for (int size3 = this.k.size() - 1; size3 >= 0; size3--) {
            v vVar = (v) this.k.get(size3);
            vVar.itemView.setAlpha(1.0f);
            k(vVar);
            this.k.remove(size3);
        }
        for (int size4 = this.m.size() - 1; size4 >= 0; size4--) {
            b((a) this.m.get(size4));
        }
        this.m.clear();
        if (b()) {
            for (int size5 = this.b.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.b.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    b bVar2 = (b) arrayList.get(size6);
                    View view2 = bVar2.a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    j(bVar2.a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.b.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.a.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.a.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    v vVar2 = (v) arrayList2.get(size8);
                    vVar2.itemView.setAlpha(1.0f);
                    k(vVar2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.a.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.c.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.c.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    b((a) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.c.remove(arrayList3);
                    }
                }
            }
            a((List<v>) this.f);
            a((List<v>) this.e);
            a((List<v>) this.d);
            a((List<v>) this.g);
            i();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(List<v> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((v) list.get(size)).itemView.animate().cancel();
        }
    }

    public boolean a(v vVar, List<Object> list) {
        return !list.isEmpty() || super.a(vVar, list);
    }
}
