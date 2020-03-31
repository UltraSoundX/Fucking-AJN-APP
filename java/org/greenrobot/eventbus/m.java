package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.a.b;

/* compiled from: SubscriberMethodFinder */
class m {
    private static final Map<Class<?>, List<l>> a = new ConcurrentHashMap();
    private static final a[] e = new a[4];
    private List<b> b;
    private final boolean c;
    private final boolean d;

    /* compiled from: SubscriberMethodFinder */
    static class a {
        final List<l> a = new ArrayList();
        final Map<Class, Object> b = new HashMap();
        final Map<String, Class> c = new HashMap();
        final StringBuilder d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        org.greenrobot.eventbus.a.a h;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(Method method, Class<?> cls) {
            Object put = this.b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (!b((Method) put, cls)) {
                    throw new IllegalStateException();
                }
                this.b.put(cls, this);
            }
            return b(method, cls);
        }

        private boolean b(Method method, Class<?> cls) {
            this.d.setLength(0);
            this.d.append(method.getName());
            this.d.append('>').append(cls.getName());
            String sb = this.d.toString();
            Class declaringClass = method.getDeclaringClass();
            Class cls2 = (Class) this.c.put(sb, declaringClass);
            if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.c.put(sb, cls2);
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            if (this.g) {
                this.f = null;
                return;
            }
            this.f = this.f.getSuperclass();
            String name = this.f.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.f = null;
            }
        }
    }

    m(List<b> list, boolean z, boolean z2) {
        this.b = list;
        this.c = z;
        this.d = z2;
    }

    /* access modifiers changed from: 0000 */
    public List<l> a(Class<?> cls) {
        List<l> list = (List) a.get(cls);
        if (list == null) {
            if (this.d) {
                list = c(cls);
            } else {
                list = b(cls);
            }
            if (list.isEmpty()) {
                throw new e("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
            }
            a.put(cls, list);
        }
        return list;
    }

    private List<l> b(Class<?> cls) {
        l[] b2;
        a a2 = a();
        a2.a(cls);
        while (a2.f != null) {
            a2.h = b(a2);
            if (a2.h != null) {
                for (l lVar : a2.h.b()) {
                    if (a2.a(lVar.a, lVar.c)) {
                        a2.a.add(lVar);
                    }
                }
            } else {
                c(a2);
            }
            a2.b();
        }
        return a(a2);
    }

    private List<l> a(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.a);
        aVar.a();
        synchronized (e) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                } else if (e[i] == null) {
                    e[i] = aVar;
                    break;
                } else {
                    i++;
                }
            }
        }
        return arrayList;
    }

    private a a() {
        synchronized (e) {
            for (int i = 0; i < 4; i++) {
                a aVar = e[i];
                if (aVar != null) {
                    e[i] = null;
                    return aVar;
                }
            }
            return new a();
        }
    }

    private org.greenrobot.eventbus.a.a b(a aVar) {
        if (!(aVar.h == null || aVar.h.c() == null)) {
            org.greenrobot.eventbus.a.a c2 = aVar.h.c();
            if (aVar.f == c2.a()) {
                return c2;
            }
        }
        if (this.b != null) {
            for (b a2 : this.b) {
                org.greenrobot.eventbus.a.a a3 = a2.a(aVar.f);
                if (a3 != null) {
                    return a3;
                }
            }
        }
        return null;
    }

    private List<l> c(Class<?> cls) {
        a a2 = a();
        a2.a(cls);
        while (a2.f != null) {
            c(a2);
            a2.b();
        }
        return a(a2);
    }

    private void c(a aVar) {
        Method[] methodArr;
        try {
            methodArr = aVar.f.getDeclaredMethods();
        } catch (Throwable th) {
            Method[] methods = aVar.f.getMethods();
            aVar.g = true;
            methodArr = methods;
        }
        for (Method method : methodArr) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    j jVar = (j) method.getAnnotation(j.class);
                    if (jVar != null) {
                        Class cls = parameterTypes[0];
                        if (aVar.a(method, cls)) {
                            aVar.a.add(new l(method, cls, jVar.a(), jVar.c(), jVar.b()));
                        }
                    }
                } else if (this.c && method.isAnnotationPresent(j.class)) {
                    throw new e("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.c && method.isAnnotationPresent(j.class)) {
                throw new e((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }
}
