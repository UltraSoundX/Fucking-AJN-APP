package android.support.transition;

import android.support.transition.Transition.c;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TransitionManager */
public class s {
    static ArrayList<ViewGroup> a = new ArrayList<>();
    private static Transition b = new AutoTransition();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> c = new ThreadLocal<>();

    /* compiled from: TransitionManager */
    private static class a implements OnAttachStateChangeListener, OnPreDrawListener {
        Transition a;
        ViewGroup b;

        a(Transition transition, ViewGroup viewGroup) {
            this.a = transition;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            a();
            s.a.remove(this.b);
            ArrayList arrayList = (ArrayList) s.a().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).f(this.b);
                }
            }
            this.a.b(true);
        }

        public boolean onPreDraw() {
            ArrayList arrayList;
            ArrayList arrayList2;
            a();
            if (s.a.remove(this.b)) {
                final ArrayMap a2 = s.a();
                ArrayList arrayList3 = (ArrayList) a2.get(this.b);
                if (arrayList3 == null) {
                    ArrayList arrayList4 = new ArrayList();
                    a2.put(this.b, arrayList4);
                    arrayList = arrayList4;
                    arrayList2 = null;
                } else if (arrayList3.size() > 0) {
                    ArrayList arrayList5 = new ArrayList(arrayList3);
                    arrayList = arrayList3;
                    arrayList2 = arrayList5;
                } else {
                    arrayList = arrayList3;
                    arrayList2 = null;
                }
                arrayList.add(this.a);
                this.a.a((c) new q() {
                    public void a(Transition transition) {
                        ((ArrayList) a2.get(a.this.b)).remove(transition);
                    }
                });
                this.a.a(this.b, false);
                if (arrayList2 != null) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((Transition) it.next()).f(this.b);
                    }
                }
                this.a.a(this.b);
            }
            return true;
        }
    }

    static ArrayMap<ViewGroup, ArrayList<Transition>> a() {
        WeakReference weakReference = (WeakReference) c.get();
        if (weakReference != null) {
            ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap = (ArrayMap) weakReference.get();
            if (arrayMap != null) {
                return arrayMap;
            }
        }
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
        c.set(new WeakReference(arrayMap2));
        return arrayMap2;
    }

    private static void b(ViewGroup viewGroup, Transition transition) {
        if (transition != null && viewGroup != null) {
            a aVar = new a(transition, viewGroup);
            viewGroup.addOnAttachStateChangeListener(aVar);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
        }
    }

    private static void c(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) a().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).e(viewGroup);
            }
        }
        if (transition != null) {
            transition.a(viewGroup, true);
        }
        n a2 = n.a(viewGroup);
        if (a2 != null) {
            a2.a();
        }
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (!a.contains(viewGroup) && ViewCompat.isLaidOut(viewGroup)) {
            a.add(viewGroup);
            if (transition == null) {
                transition = b;
            }
            Transition p = transition.clone();
            c(viewGroup, p);
            n.a(viewGroup, null);
            b(viewGroup, p);
        }
    }
}
