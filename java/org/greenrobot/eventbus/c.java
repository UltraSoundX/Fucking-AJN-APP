package org.greenrobot.eventbus;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/* compiled from: EventBus */
public class c {
    public static String a = "EventBus";
    static volatile c b;
    private static final d c = new d();
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<n>> e;
    private final Map<Object, List<Class<?>>> f;
    private final Map<Class<?>, Object> g;
    private final ThreadLocal<a> h;
    private final f i;
    private final b j;
    private final a k;
    private final m l;
    private final ExecutorService m;
    private final boolean n;
    private final boolean o;
    private final boolean p;

    /* renamed from: q reason: collision with root package name */
    private final boolean f442q;
    private final boolean r;
    private final boolean s;
    private final int t;

    /* compiled from: EventBus */
    static final class a {
        final List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        n d;
        Object e;
        boolean f;

        a() {
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public c() {
        this(c);
    }

    c(d dVar) {
        this.h = new ThreadLocal<a>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public a initialValue() {
                return new a();
            }
        };
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new ConcurrentHashMap();
        this.i = new f(this, Looper.getMainLooper(), 10);
        this.j = new b(this);
        this.k = new a(this);
        this.t = dVar.j != null ? dVar.j.size() : 0;
        this.l = new m(dVar.j, dVar.h, dVar.g);
        this.o = dVar.a;
        this.p = dVar.b;
        this.f442q = dVar.c;
        this.r = dVar.d;
        this.n = dVar.e;
        this.s = dVar.f;
        this.m = dVar.i;
    }

    public void a(Object obj) {
        List<l> a2 = this.l.a(obj.getClass());
        synchronized (this) {
            for (l a3 : a2) {
                a(obj, a3);
            }
        }
    }

    private void a(Object obj, l lVar) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class<?> cls = lVar.c;
        n nVar = new n(obj, lVar);
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.e.get(cls);
        if (copyOnWriteArrayList2 == null) {
            CopyOnWriteArrayList copyOnWriteArrayList3 = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList3);
            copyOnWriteArrayList = copyOnWriteArrayList3;
        } else if (copyOnWriteArrayList2.contains(nVar)) {
            throw new e("Subscriber " + obj.getClass() + " already registered to event " + cls);
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                break;
            } else if (i2 == size || lVar.d > ((n) copyOnWriteArrayList.get(i2)).b.d) {
                copyOnWriteArrayList.add(i2, nVar);
            } else {
                i2++;
            }
        }
        copyOnWriteArrayList.add(i2, nVar);
        List list = (List) this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (!lVar.e) {
            return;
        }
        if (this.s) {
            for (Entry entry : this.g.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    b(nVar, entry.getValue());
                }
            }
            return;
        }
        b(nVar, this.g.get(cls));
    }

    private void b(n nVar, Object obj) {
        if (obj != null) {
            a(nVar, obj, Looper.getMainLooper() == Looper.myLooper());
        }
    }

    private void a(Object obj, Class<?> cls) {
        int i2;
        int i3;
        List list = (List) this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                n nVar = (n) list.get(i4);
                if (nVar.a == obj) {
                    nVar.c = false;
                    list.remove(i4);
                    i2 = i4 - 1;
                    i3 = size - 1;
                } else {
                    i2 = i4;
                    i3 = size;
                }
                size = i3;
                i4 = i2 + 1;
            }
        }
    }

    public synchronized void b(Object obj) {
        List<Class> list = (List) this.f.get(obj);
        if (list != null) {
            for (Class a2 : list) {
                a(obj, a2);
            }
            this.f.remove(obj);
        } else {
            Log.w(a, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void c(Object obj) {
        boolean z;
        a aVar = (a) this.h.get();
        List<Object> list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            aVar.c = z;
            aVar.b = true;
            if (aVar.f) {
                throw new e("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), aVar);
                } finally {
                    aVar.b = false;
                    aVar.c = false;
                }
            }
        }
    }

    private void a(Object obj, a aVar) throws Error {
        boolean a2;
        Class<k> cls = obj.getClass();
        if (this.s) {
            List a3 = a(cls);
            boolean z = false;
            for (int i2 = 0; i2 < a3.size(); i2++) {
                z |= a(obj, aVar, (Class) a3.get(i2));
            }
            a2 = z;
        } else {
            a2 = a(obj, aVar, cls);
        }
        if (!a2) {
            if (this.p) {
                Log.d(a, "No subscribers registered for event " + cls);
            }
            if (this.r && cls != g.class && cls != k.class) {
                c(new g(this, obj));
            }
        }
    }

    private boolean a(Object obj, a aVar, Class<?> cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            n nVar = (n) it.next();
            aVar.e = obj;
            aVar.d = nVar;
            try {
                a(nVar, obj, aVar.c);
                if (aVar.f) {
                    break;
                }
            } finally {
                aVar.e = null;
                aVar.d = null;
                aVar.f = false;
            }
        }
        return true;
    }

    private void a(n nVar, Object obj, boolean z) {
        switch (nVar.b.b) {
            case POSTING:
                a(nVar, obj);
                return;
            case MAIN:
                if (z) {
                    a(nVar, obj);
                    return;
                } else {
                    this.i.a(nVar, obj);
                    return;
                }
            case BACKGROUND:
                if (z) {
                    this.j.a(nVar, obj);
                    return;
                } else {
                    a(nVar, obj);
                    return;
                }
            case ASYNC:
                this.k.a(nVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + nVar.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = (List) d.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a(list, (Class<?>[]) cls2.getInterfaces());
                }
                d.put(cls, list);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, (Class<?>[]) cls.getInterfaces());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(h hVar) {
        Object obj = hVar.a;
        n nVar = hVar.b;
        h.a(hVar);
        if (nVar.c) {
            a(nVar, obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(n nVar, Object obj) {
        try {
            nVar.b.a.invoke(nVar.a, new Object[]{obj});
        } catch (InvocationTargetException e2) {
            a(nVar, obj, e2.getCause());
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException("Unexpected exception", e3);
        }
    }

    private void a(n nVar, Object obj, Throwable th) {
        if (obj instanceof k) {
            if (this.o) {
                Log.e(a, "SubscriberExceptionEvent subscriber " + nVar.a.getClass() + " threw an exception", th);
                k kVar = (k) obj;
                Log.e(a, "Initial event " + kVar.c + " caused exception in " + kVar.d, kVar.b);
            }
        } else if (this.n) {
            throw new e("Invoking subscriber failed", th);
        } else {
            if (this.o) {
                Log.e(a, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + nVar.a.getClass(), th);
            }
            if (this.f442q) {
                c(new k(this, th, obj, nVar.a));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ExecutorService b() {
        return this.m;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.t + ", eventInheritance=" + this.s + "]";
    }
}
