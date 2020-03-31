package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import net.sf.json.util.JSONUtils;

public abstract class Transition implements Cloneable {
    private static ThreadLocal<ArrayMap<Animator, a>> A = new ThreadLocal<>();
    private static final int[] a = {2, 1, 3, 4};
    private static final PathMotion i = new PathMotion() {
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };
    private ViewGroup B = null;
    private int C = 0;
    private boolean D = false;
    private boolean E = false;
    private ArrayList<c> F = null;
    private ArrayList<Animator> G = new ArrayList<>();
    private b H;
    private ArrayMap<String, String> I;
    private PathMotion J = i;
    long b = -1;
    ArrayList<Integer> c = new ArrayList<>();
    ArrayList<View> d = new ArrayList<>();
    TransitionSet e = null;
    boolean f = false;
    ArrayList<Animator> g = new ArrayList<>();
    t h;
    private String j = getClass().getName();
    private long k = -1;
    private TimeInterpolator l = null;
    private ArrayList<String> m = null;
    private ArrayList<Class> n = null;
    private ArrayList<Integer> o = null;
    private ArrayList<View> p = null;

    /* renamed from: q reason: collision with root package name */
    private ArrayList<Class> f334q = null;
    private ArrayList<String> r = null;
    private ArrayList<Integer> s = null;
    private ArrayList<View> t = null;
    private ArrayList<Class> u = null;
    private w v = new w();
    private w w = new w();
    private int[] x = a;
    private ArrayList<v> y;
    private ArrayList<v> z;

    private static class a {
        View a;
        String b;
        v c;
        ap d;
        Transition e;

        a(View view, String str, Transition transition, ap apVar, v vVar) {
            this.a = view;
            this.b = str;
            this.c = vVar;
            this.d = apVar;
            this.e = transition;
        }
    }

    public static abstract class b {
        public abstract Rect a(Transition transition);
    }

    public interface c {
        void a(Transition transition);

        void b(Transition transition);

        void c(Transition transition);

        void d(Transition transition);
    }

    public abstract void a(v vVar);

    public abstract void b(v vVar);

    public Transition() {
    }

    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long namedInt = (long) TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (namedInt >= 0) {
            a(namedInt);
        }
        long namedInt2 = (long) TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (namedInt2 > 0) {
            b(namedInt2);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            a((TimeInterpolator) AnimationUtils.loadInterpolator(context, namedResourceId));
        }
        String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (namedString != null) {
            a(b(namedString));
        }
        obtainStyledAttributes.recycle();
    }

    private static int[] b(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, StorageInterface.KEY_SPLITER);
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if (Config.FEED_LIST_ITEM_CUSTOM_ID.equalsIgnoreCase(trim)) {
                iArr[i2] = 3;
            } else if ("instance".equalsIgnoreCase(trim)) {
                iArr[i2] = 1;
            } else if ("name".equalsIgnoreCase(trim)) {
                iArr[i2] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                iArr[i2] = 4;
            } else if (trim.isEmpty()) {
                int[] iArr2 = new int[(iArr.length - 1)];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                i2--;
                iArr = iArr2;
            } else {
                throw new InflateException("Unknown match type in matchOrder: '" + trim + JSONUtils.SINGLE_QUOTE);
            }
            i2++;
        }
        return iArr;
    }

    public Transition a(long j2) {
        this.b = j2;
        return this;
    }

    public long b() {
        return this.b;
    }

    public Transition b(long j2) {
        this.k = j2;
        return this;
    }

    public long c() {
        return this.k;
    }

    public Transition a(TimeInterpolator timeInterpolator) {
        this.l = timeInterpolator;
        return this;
    }

    public TimeInterpolator d() {
        return this.l;
    }

    public String[] a() {
        return null;
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        return null;
    }

    public void a(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.x = a;
            return;
        }
        int i2 = 0;
        while (i2 < iArr.length) {
            if (!a(iArr[i2])) {
                throw new IllegalArgumentException("matches contains invalid value");
            } else if (a(iArr, i2)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            } else {
                i2++;
            }
        }
        this.x = (int[]) iArr.clone();
    }

    private static boolean a(int i2) {
        return i2 >= 1 && i2 <= 4;
    }

    private static boolean a(int[] iArr, int i2) {
        int i3 = iArr[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (iArr[i4] == i3) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayMap<View, v> arrayMap, ArrayMap<View, v> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View view = (View) arrayMap.keyAt(size);
            if (view != null && b(view)) {
                v vVar = (v) arrayMap2.remove(view);
                if (!(vVar == null || vVar.b == null || !b(vVar.b))) {
                    this.y.add((v) arrayMap.removeAt(size));
                    this.z.add(vVar);
                }
            }
        }
    }

    private void a(ArrayMap<View, v> arrayMap, ArrayMap<View, v> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) longSparseArray.valueAt(i2);
            if (view != null && b(view)) {
                View view2 = (View) longSparseArray2.get(longSparseArray.keyAt(i2));
                if (view2 != null && b(view2)) {
                    v vVar = (v) arrayMap.get(view);
                    v vVar2 = (v) arrayMap2.get(view2);
                    if (!(vVar == null || vVar2 == null)) {
                        this.y.add(vVar);
                        this.z.add(vVar2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void a(ArrayMap<View, v> arrayMap, ArrayMap<View, v> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) sparseArray.valueAt(i2);
            if (view != null && b(view)) {
                View view2 = (View) sparseArray2.get(sparseArray.keyAt(i2));
                if (view2 != null && b(view2)) {
                    v vVar = (v) arrayMap.get(view);
                    v vVar2 = (v) arrayMap2.get(view2);
                    if (!(vVar == null || vVar2 == null)) {
                        this.y.add(vVar);
                        this.z.add(vVar2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void a(ArrayMap<View, v> arrayMap, ArrayMap<View, v> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        int size = arrayMap3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) arrayMap3.valueAt(i2);
            if (view != null && b(view)) {
                View view2 = (View) arrayMap4.get(arrayMap3.keyAt(i2));
                if (view2 != null && b(view2)) {
                    v vVar = (v) arrayMap.get(view);
                    v vVar2 = (v) arrayMap2.get(view2);
                    if (!(vVar == null || vVar2 == null)) {
                        this.y.add(vVar);
                        this.z.add(vVar2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void b(ArrayMap<View, v> arrayMap, ArrayMap<View, v> arrayMap2) {
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            v vVar = (v) arrayMap.valueAt(i2);
            if (b(vVar.b)) {
                this.y.add(vVar);
                this.z.add(null);
            }
        }
        for (int i3 = 0; i3 < arrayMap2.size(); i3++) {
            v vVar2 = (v) arrayMap2.valueAt(i3);
            if (b(vVar2.b)) {
                this.z.add(vVar2);
                this.y.add(null);
            }
        }
    }

    private void a(w wVar, w wVar2) {
        ArrayMap arrayMap = new ArrayMap((SimpleArrayMap) wVar.a);
        ArrayMap arrayMap2 = new ArrayMap((SimpleArrayMap) wVar2.a);
        for (int i2 : this.x) {
            switch (i2) {
                case 1:
                    a(arrayMap, arrayMap2);
                    break;
                case 2:
                    a(arrayMap, arrayMap2, wVar.d, wVar2.d);
                    break;
                case 3:
                    a(arrayMap, arrayMap2, wVar.b, wVar2.b);
                    break;
                case 4:
                    a(arrayMap, arrayMap2, wVar.c, wVar2.c);
                    break;
            }
        }
        b(arrayMap, arrayMap2);
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, w wVar, w wVar2, ArrayList<v> arrayList, ArrayList<v> arrayList2) {
        v vVar;
        v vVar2;
        View view;
        v vVar3;
        Animator animator;
        Animator animator2;
        ArrayMap r2 = r();
        long j2 = Long.MAX_VALUE;
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= size) {
                break;
            }
            v vVar4 = (v) arrayList.get(i3);
            v vVar5 = (v) arrayList2.get(i3);
            if (vVar4 == null || vVar4.c.contains(this)) {
                vVar = vVar4;
            } else {
                vVar = null;
            }
            if (vVar5 == null || vVar5.c.contains(this)) {
                vVar2 = vVar5;
            } else {
                vVar2 = null;
            }
            if (vVar != null || vVar2 != null) {
                if (vVar == null || vVar2 == null || a(vVar, vVar2)) {
                    Animator a2 = a(viewGroup, vVar, vVar2);
                    if (a2 != null) {
                        v vVar6 = null;
                        if (vVar2 != null) {
                            View view2 = vVar2.b;
                            String[] a3 = a();
                            if (view2 != null && a3 != null && a3.length > 0) {
                                v vVar7 = new v();
                                vVar7.b = view2;
                                v vVar8 = (v) wVar2.a.get(view2);
                                if (vVar8 != null) {
                                    for (int i4 = 0; i4 < a3.length; i4++) {
                                        vVar7.a.put(a3[i4], vVar8.a.get(a3[i4]));
                                    }
                                }
                                int size2 = r2.size();
                                int i5 = 0;
                                while (true) {
                                    if (i5 >= size2) {
                                        vVar6 = vVar7;
                                        animator2 = a2;
                                        break;
                                    }
                                    a aVar = (a) r2.get((Animator) r2.keyAt(i5));
                                    if (aVar.c != null && aVar.a == view2 && aVar.b.equals(q()) && aVar.c.equals(vVar7)) {
                                        animator2 = null;
                                        vVar6 = vVar7;
                                        break;
                                    }
                                    i5++;
                                }
                            } else {
                                animator2 = a2;
                            }
                            vVar3 = vVar6;
                            animator = animator2;
                            view = view2;
                        } else {
                            view = vVar.b;
                            vVar3 = null;
                            animator = a2;
                        }
                        if (animator != null) {
                            if (this.h != null) {
                                long a4 = this.h.a(viewGroup, this, vVar, vVar2);
                                sparseIntArray.put(this.G.size(), (int) a4);
                                j2 = Math.min(a4, j2);
                            }
                            r2.put(animator, new a(view, q(), this, ah.b(viewGroup), vVar3));
                            this.G.add(animator);
                        }
                    }
                }
            }
            i2 = i3 + 1;
        }
        if (j2 != 0) {
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 < sparseIntArray.size()) {
                    Animator animator3 = (Animator) this.G.get(sparseIntArray.keyAt(i7));
                    animator3.setStartDelay((((long) sparseIntArray.valueAt(i7)) - j2) + animator3.getStartDelay());
                    i6 = i7 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean b(View view) {
        int id = view.getId();
        if (this.o != null && this.o.contains(Integer.valueOf(id))) {
            return false;
        }
        if (this.p != null && this.p.contains(view)) {
            return false;
        }
        if (this.f334q != null) {
            int size = this.f334q.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((Class) this.f334q.get(i2)).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.r != null && ViewCompat.getTransitionName(view) != null && this.r.contains(ViewCompat.getTransitionName(view))) {
            return false;
        }
        if (this.c.size() == 0 && this.d.size() == 0 && ((this.n == null || this.n.isEmpty()) && (this.m == null || this.m.isEmpty()))) {
            return true;
        }
        if (this.c.contains(Integer.valueOf(id)) || this.d.contains(view)) {
            return true;
        }
        if (this.m != null && this.m.contains(ViewCompat.getTransitionName(view))) {
            return true;
        }
        if (this.n == null) {
            return false;
        }
        for (int i3 = 0; i3 < this.n.size(); i3++) {
            if (((Class) this.n.get(i3)).isInstance(view)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayMap<Animator, a> r() {
        ArrayMap<Animator, a> arrayMap = (ArrayMap) A.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, a> arrayMap2 = new ArrayMap<>();
        A.set(arrayMap2);
        return arrayMap2;
    }

    /* access modifiers changed from: protected */
    public void e() {
        j();
        ArrayMap r2 = r();
        Iterator it = this.G.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (r2.containsKey(animator)) {
                j();
                a(animator, r2);
            }
        }
        this.G.clear();
        k();
    }

    private void a(Animator animator, final ArrayMap<Animator, a> arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    Transition.this.g.add(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    arrayMap.remove(animator);
                    Transition.this.g.remove(animator);
                }
            });
            a(animator);
        }
    }

    public Transition c(View view) {
        this.d.add(view);
        return this;
    }

    public Transition d(View view) {
        this.d.remove(view);
        return this;
    }

    public List<Integer> f() {
        return this.c;
    }

    public List<View> g() {
        return this.d;
    }

    public List<String> h() {
        return this.m;
    }

    public List<Class> i() {
        return this.n;
    }

    /* access modifiers changed from: 0000 */
    public void a(ViewGroup viewGroup, boolean z2) {
        b(z2);
        if ((this.c.size() > 0 || this.d.size() > 0) && ((this.m == null || this.m.isEmpty()) && (this.n == null || this.n.isEmpty()))) {
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                View findViewById = viewGroup.findViewById(((Integer) this.c.get(i2)).intValue());
                if (findViewById != null) {
                    v vVar = new v();
                    vVar.b = findViewById;
                    if (z2) {
                        a(vVar);
                    } else {
                        b(vVar);
                    }
                    vVar.c.add(this);
                    c(vVar);
                    if (z2) {
                        a(this.v, findViewById, vVar);
                    } else {
                        a(this.w, findViewById, vVar);
                    }
                }
            }
            for (int i3 = 0; i3 < this.d.size(); i3++) {
                View view = (View) this.d.get(i3);
                v vVar2 = new v();
                vVar2.b = view;
                if (z2) {
                    a(vVar2);
                } else {
                    b(vVar2);
                }
                vVar2.c.add(this);
                c(vVar2);
                if (z2) {
                    a(this.v, view, vVar2);
                } else {
                    a(this.w, view, vVar2);
                }
            }
        } else {
            c(viewGroup, z2);
        }
        if (!z2 && this.I != null) {
            int size = this.I.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.add(this.v.d.remove((String) this.I.keyAt(i4)));
            }
            for (int i5 = 0; i5 < size; i5++) {
                View view2 = (View) arrayList.get(i5);
                if (view2 != null) {
                    this.v.d.put((String) this.I.valueAt(i5), view2);
                }
            }
        }
    }

    private static void a(w wVar, View view, v vVar) {
        wVar.a.put(view, vVar);
        int id = view.getId();
        if (id >= 0) {
            if (wVar.b.indexOfKey(id) >= 0) {
                wVar.b.put(id, null);
            } else {
                wVar.b.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (wVar.d.containsKey(transitionName)) {
                wVar.d.put(transitionName, null);
            } else {
                wVar.d.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (wVar.c.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = (View) wVar.c.get(itemIdAtPosition);
                    if (view2 != null) {
                        ViewCompat.setHasTransientState(view2, false);
                        wVar.c.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                ViewCompat.setHasTransientState(view, true);
                wVar.c.put(itemIdAtPosition, view);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(boolean z2) {
        if (z2) {
            this.v.a.clear();
            this.v.b.clear();
            this.v.c.clear();
            return;
        }
        this.w.a.clear();
        this.w.b.clear();
        this.w.c.clear();
    }

    private void c(View view, boolean z2) {
        if (view != null) {
            int id = view.getId();
            if (this.o != null && this.o.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.p == null || !this.p.contains(view)) {
                if (this.f334q != null) {
                    int size = this.f334q.size();
                    int i2 = 0;
                    while (i2 < size) {
                        if (!((Class) this.f334q.get(i2)).isInstance(view)) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    v vVar = new v();
                    vVar.b = view;
                    if (z2) {
                        a(vVar);
                    } else {
                        b(vVar);
                    }
                    vVar.c.add(this);
                    c(vVar);
                    if (z2) {
                        a(this.v, view, vVar);
                    } else {
                        a(this.w, view, vVar);
                    }
                }
                if (!(view instanceof ViewGroup)) {
                    return;
                }
                if (this.s != null && this.s.contains(Integer.valueOf(id))) {
                    return;
                }
                if (this.t == null || !this.t.contains(view)) {
                    if (this.u != null) {
                        int size2 = this.u.size();
                        int i3 = 0;
                        while (i3 < size2) {
                            if (!((Class) this.u.get(i3)).isInstance(view)) {
                                i3++;
                            } else {
                                return;
                            }
                        }
                    }
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                        c(viewGroup.getChildAt(i4), z2);
                    }
                }
            }
        }
    }

    public v a(View view, boolean z2) {
        if (this.e != null) {
            return this.e.a(view, z2);
        }
        return (v) (z2 ? this.v : this.w).a.get(view);
    }

    /* access modifiers changed from: 0000 */
    public v b(View view, boolean z2) {
        v vVar;
        if (this.e != null) {
            return this.e.b(view, z2);
        }
        ArrayList<v> arrayList = z2 ? this.y : this.z;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            }
            v vVar2 = (v) arrayList.get(i2);
            if (vVar2 == null) {
                return null;
            }
            if (vVar2.b == view) {
                break;
            }
            i2++;
        }
        if (i2 >= 0) {
            vVar = (v) (z2 ? this.z : this.y).get(i2);
        } else {
            vVar = null;
        }
        return vVar;
    }

    public void e(View view) {
        if (!this.E) {
            ArrayMap r2 = r();
            int size = r2.size();
            ap b2 = ah.b(view);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                a aVar = (a) r2.valueAt(i2);
                if (aVar.a != null && b2.equals(aVar.d)) {
                    a.a((Animator) r2.keyAt(i2));
                }
            }
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size2 = arrayList.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((c) arrayList.get(i3)).b(this);
                }
            }
            this.D = true;
        }
    }

    public void f(View view) {
        if (this.D) {
            if (!this.E) {
                ArrayMap r2 = r();
                int size = r2.size();
                ap b2 = ah.b(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    a aVar = (a) r2.valueAt(i2);
                    if (aVar.a != null && b2.equals(aVar.d)) {
                        a.b((Animator) r2.keyAt(i2));
                    }
                }
                if (this.F != null && this.F.size() > 0) {
                    ArrayList arrayList = (ArrayList) this.F.clone();
                    int size2 = arrayList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((c) arrayList.get(i3)).c(this);
                    }
                }
            }
            this.D = false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(ViewGroup viewGroup) {
        this.y = new ArrayList<>();
        this.z = new ArrayList<>();
        a(this.v, this.w);
        ArrayMap r2 = r();
        int size = r2.size();
        ap b2 = ah.b(viewGroup);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator animator = (Animator) r2.keyAt(i2);
            if (animator != null) {
                a aVar = (a) r2.get(animator);
                if (!(aVar == null || aVar.a == null || !b2.equals(aVar.d))) {
                    v vVar = aVar.c;
                    View view = aVar.a;
                    v a2 = a(view, true);
                    v b3 = b(view, true);
                    if (!(a2 == null && b3 == null) && aVar.e.a(vVar, b3)) {
                        if (animator.isRunning() || animator.isStarted()) {
                            animator.cancel();
                        } else {
                            r2.remove(animator);
                        }
                    }
                }
            }
        }
        a(viewGroup, this.v, this.w, this.y, this.z);
        e();
    }

    public boolean a(v vVar, v vVar2) {
        boolean z2;
        if (vVar == null || vVar2 == null) {
            return false;
        }
        String[] a2 = a();
        if (a2 != null) {
            int length = a2.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z2 = false;
                    break;
                } else if (a(vVar, vVar2, a2[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            return z2;
        }
        for (String a3 : vVar.a.keySet()) {
            if (a(vVar, vVar2, a3)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(v vVar, v vVar2, String str) {
        Object obj = vVar.a.get(str);
        Object obj2 = vVar2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null || !obj.equals(obj2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(Animator animator) {
        if (animator == null) {
            k();
            return;
        }
        if (b() >= 0) {
            animator.setDuration(b());
        }
        if (c() >= 0) {
            animator.setStartDelay(c());
        }
        if (d() != null) {
            animator.setInterpolator(d());
        }
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Transition.this.k();
                animator.removeListener(this);
            }
        });
        animator.start();
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (this.C == 0) {
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((c) arrayList.get(i2)).d(this);
                }
            }
            this.E = false;
        }
        this.C++;
    }

    /* access modifiers changed from: protected */
    public void k() {
        this.C--;
        if (this.C == 0) {
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((c) arrayList.get(i2)).a(this);
                }
            }
            for (int i3 = 0; i3 < this.v.c.size(); i3++) {
                View view = (View) this.v.c.valueAt(i3);
                if (view != null) {
                    ViewCompat.setHasTransientState(view, false);
                }
            }
            for (int i4 = 0; i4 < this.w.c.size(); i4++) {
                View view2 = (View) this.w.c.valueAt(i4);
                if (view2 != null) {
                    ViewCompat.setHasTransientState(view2, false);
                }
            }
            this.E = true;
        }
    }

    public Transition a(c cVar) {
        if (this.F == null) {
            this.F = new ArrayList<>();
        }
        this.F.add(cVar);
        return this;
    }

    public Transition b(c cVar) {
        if (this.F != null) {
            this.F.remove(cVar);
            if (this.F.size() == 0) {
                this.F = null;
            }
        }
        return this;
    }

    public void a(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.J = i;
        } else {
            this.J = pathMotion;
        }
    }

    public PathMotion l() {
        return this.J;
    }

    public void a(b bVar) {
        this.H = bVar;
    }

    public b m() {
        return this.H;
    }

    public Rect n() {
        if (this.H == null) {
            return null;
        }
        return this.H.a(this);
    }

    public void a(t tVar) {
        this.h = tVar;
    }

    public t o() {
        return this.h;
    }

    /* access modifiers changed from: 0000 */
    public void c(v vVar) {
        boolean z2 = false;
        if (this.h != null && !vVar.a.isEmpty()) {
            String[] a2 = this.h.a();
            if (a2 != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= a2.length) {
                        z2 = true;
                        break;
                    } else if (!vVar.a.containsKey(a2[i2])) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z2) {
                    this.h.a(vVar);
                }
            }
        }
    }

    public String toString() {
        return a("");
    }

    /* renamed from: p */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.G = new ArrayList<>();
            transition.v = new w();
            transition.w = new w();
            transition.y = null;
            transition.z = null;
            return transition;
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public String q() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public String a(String str) {
        String str2;
        String str3 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.b != -1) {
            str3 = str3 + "dur(" + this.b + ") ";
        }
        if (this.k != -1) {
            str3 = str3 + "dly(" + this.k + ") ";
        }
        if (this.l != null) {
            str3 = str3 + "interp(" + this.l + ") ";
        }
        if (this.c.size() <= 0 && this.d.size() <= 0) {
            return str3;
        }
        String str4 = str3 + "tgts(";
        if (this.c.size() > 0) {
            str2 = str4;
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                if (i2 > 0) {
                    str2 = str2 + ", ";
                }
                str2 = str2 + this.c.get(i2);
            }
        } else {
            str2 = str4;
        }
        if (this.d.size() > 0) {
            for (int i3 = 0; i3 < this.d.size(); i3++) {
                if (i3 > 0) {
                    str2 = str2 + ", ";
                }
                str2 = str2 + this.d.get(i3);
            }
        }
        return str2 + ")";
    }
}
