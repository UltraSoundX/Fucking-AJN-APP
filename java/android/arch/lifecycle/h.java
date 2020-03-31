package android.arch.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Lifecycling */
public class h {
    private static Map<Class, Integer> a = new HashMap();
    private static Map<Class, List<Constructor<? extends b>>> b = new HashMap();

    static GenericLifecycleObserver a(Object obj) {
        int i = 0;
        if (obj instanceof FullLifecycleObserver) {
            return new FullLifecycleObserverAdapter((FullLifecycleObserver) obj);
        }
        if (obj instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) obj;
        }
        Class cls = obj.getClass();
        if (b(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List list = (List) b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a((Constructor) list.get(0), obj));
        }
        b[] bVarArr = new b[list.size()];
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return new CompositeGeneratedAdaptersObserver(bVarArr);
            }
            bVarArr[i2] = a((Constructor) list.get(i2), obj);
            i = i2 + 1;
        }
    }

    private static b a(Constructor<? extends b> constructor, Object obj) {
        try {
            return (b) constructor.newInstance(new Object[]{obj});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static Constructor<? extends b> a(Class<?> cls) {
        try {
            Package packageR = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String str = packageR != null ? packageR.getName() : "";
            if (!str.isEmpty()) {
                canonicalName = canonicalName.substring(str.length() + 1);
            }
            String a2 = a(canonicalName);
            if (!str.isEmpty()) {
                a2 = str + "." + a2;
            }
            Constructor<? extends b> declaredConstructor = Class.forName(a2).getDeclaredConstructor(new Class[]{cls});
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static int b(Class<?> cls) {
        if (a.containsKey(cls)) {
            return ((Integer) a.get(cls)).intValue();
        }
        int c = c(cls);
        a.put(cls, Integer.valueOf(c));
        return c;
    }

    private static int c(Class<?> cls) {
        Class[] interfaces;
        List list;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor a2 = a(cls);
        if (a2 != null) {
            b.put(cls, Collections.singletonList(a2));
            return 2;
        } else if (a.a.a(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            List list2 = null;
            if (d(superclass)) {
                if (b(superclass) == 1) {
                    return 1;
                }
                list2 = new ArrayList((Collection) b.get(superclass));
            }
            for (Class cls2 : cls.getInterfaces()) {
                if (d(cls2)) {
                    if (b(cls2) == 1) {
                        return 1;
                    }
                    if (list2 == null) {
                        list = new ArrayList();
                    } else {
                        list = list2;
                    }
                    list.addAll((Collection) b.get(cls2));
                    list2 = list;
                }
            }
            if (list2 == null) {
                return 1;
            }
            b.put(cls, list2);
            return 2;
        }
    }

    private static boolean d(Class<?> cls) {
        return cls != null && d.class.isAssignableFrom(cls);
    }

    public static String a(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }
}
