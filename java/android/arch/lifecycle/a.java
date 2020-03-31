package android.arch.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ClassesInfoCache */
class a {
    static a a = new a();
    private final Map<Class, C0001a> b = new HashMap();
    private final Map<Class, Boolean> c = new HashMap();

    /* renamed from: android.arch.lifecycle.a$a reason: collision with other inner class name */
    /* compiled from: ClassesInfoCache */
    static class C0001a {
        final Map<android.arch.lifecycle.c.a, List<b>> a = new HashMap();
        final Map<b, android.arch.lifecycle.c.a> b;

        C0001a(Map<b, android.arch.lifecycle.c.a> map) {
            this.b = map;
            for (Entry entry : map.entrySet()) {
                android.arch.lifecycle.c.a aVar = (android.arch.lifecycle.c.a) entry.getValue();
                List list = (List) this.a.get(aVar);
                if (list == null) {
                    list = new ArrayList();
                    this.a.put(aVar, list);
                }
                list.add(entry.getKey());
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(e eVar, android.arch.lifecycle.c.a aVar, Object obj) {
            a((List) this.a.get(aVar), eVar, aVar, obj);
            a((List) this.a.get(android.arch.lifecycle.c.a.ON_ANY), eVar, aVar, obj);
        }

        private static void a(List<b> list, e eVar, android.arch.lifecycle.c.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((b) list.get(size)).a(eVar, aVar, obj);
                }
            }
        }
    }

    /* compiled from: ClassesInfoCache */
    static class b {
        final int a;
        final Method b;

        b(int i, Method method) {
            this.a = i;
            this.b = method;
            this.b.setAccessible(true);
        }

        /* access modifiers changed from: 0000 */
        public void a(e eVar, android.arch.lifecycle.c.a aVar, Object obj) {
            try {
                switch (this.a) {
                    case 0:
                        this.b.invoke(obj, new Object[0]);
                        return;
                    case 1:
                        this.b.invoke(obj, new Object[]{eVar});
                        return;
                    case 2:
                        this.b.invoke(obj, new Object[]{eVar, aVar});
                        return;
                    default:
                        return;
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Failed to call observer method", e.getCause());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a != bVar.a || !this.b.getName().equals(bVar.b.getName())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.a * 31) + this.b.getName().hashCode();
        }
    }

    a() {
    }

    /* access modifiers changed from: 0000 */
    public boolean a(Class cls) {
        if (this.c.containsKey(cls)) {
            return ((Boolean) this.c.get(cls)).booleanValue();
        }
        Method[] c2 = c(cls);
        for (Method annotation : c2) {
            if (((l) annotation.getAnnotation(l.class)) != null) {
                a(cls, c2);
                return true;
            }
        }
        this.c.put(cls, Boolean.valueOf(false));
        return false;
    }

    private Method[] c(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    /* access modifiers changed from: 0000 */
    public C0001a b(Class cls) {
        C0001a aVar = (C0001a) this.b.get(cls);
        if (aVar != null) {
            return aVar;
        }
        return a(cls, null);
    }

    private void a(Map<b, android.arch.lifecycle.c.a> map, b bVar, android.arch.lifecycle.c.a aVar, Class cls) {
        android.arch.lifecycle.c.a aVar2 = (android.arch.lifecycle.c.a) map.get(bVar);
        if (aVar2 != null && aVar != aVar2) {
            throw new IllegalArgumentException("Method " + bVar.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
        } else if (aVar2 == null) {
            map.put(bVar, aVar);
        }
    }

    private C0001a a(Class cls, Method[] methodArr) {
        int i;
        boolean z;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null) {
            C0001a b2 = b(superclass);
            if (b2 != null) {
                hashMap.putAll(b2.b);
            }
        }
        for (Class b3 : cls.getInterfaces()) {
            for (Entry entry : b(b3).b.entrySet()) {
                a(hashMap, (b) entry.getKey(), (android.arch.lifecycle.c.a) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = c(cls);
        }
        int length = methodArr.length;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < length) {
            Method method = methodArr[i2];
            l lVar = (l) method.getAnnotation(l.class);
            if (lVar == null) {
                z = z2;
            } else {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else if (!parameterTypes[0].isAssignableFrom(e.class)) {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                } else {
                    i = 1;
                }
                android.arch.lifecycle.c.a a2 = lVar.a();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(android.arch.lifecycle.c.a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    } else if (a2 != android.arch.lifecycle.c.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    } else {
                        i = 2;
                    }
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                a(hashMap, new b(i, method), a2, cls);
                z = true;
            }
            i2++;
            z2 = z;
        }
        C0001a aVar = new C0001a(hashMap);
        this.b.put(cls, aVar);
        this.c.put(cls, Boolean.valueOf(z2));
        return aVar;
    }
}
