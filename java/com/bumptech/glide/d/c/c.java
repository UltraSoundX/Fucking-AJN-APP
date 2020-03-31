package com.bumptech.glide.d.c;

import android.content.Context;
import com.stub.StubApp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: GenericLoaderFactory */
public class c {
    private static final l c = new l() {
        public com.bumptech.glide.d.a.c a(Object obj, int i, int i2) {
            throw new NoSuchMethodError("This should never be called!");
        }

        public String toString() {
            return "NULL_MODEL_LOADER";
        }
    };
    private final Map<Class, Map<Class, m>> a = new HashMap();
    private final Map<Class, Map<Class, l>> b = new HashMap();
    private final Context d;

    public c(Context context) {
        this.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public synchronized <T, Y> m<T, Y> a(Class<T> cls, Class<Y> cls2, m<T, Y> mVar) {
        m<T, Y> mVar2;
        this.b.clear();
        Map map = (Map) this.a.get(cls);
        if (map == null) {
            map = new HashMap();
            this.a.put(cls, map);
        }
        mVar2 = (m) map.put(cls2, mVar);
        if (mVar2 != null) {
            Iterator it = this.a.values().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((Map) it.next()).containsValue(mVar2)) {
                        mVar2 = null;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return mVar2;
    }

    public synchronized <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2) {
        l<T, Y> c2;
        c2 = c(cls, cls2);
        if (c2 == null) {
            m d2 = d(cls, cls2);
            if (d2 != null) {
                c2 = d2.a(this.d, this);
                a(cls, cls2, c2);
            } else {
                b(cls, cls2);
            }
        } else if (c.equals(c2)) {
            c2 = null;
        }
        return c2;
    }

    private <T, Y> void b(Class<T> cls, Class<Y> cls2) {
        a(cls, cls2, c);
    }

    private <T, Y> void a(Class<T> cls, Class<Y> cls2, l<T, Y> lVar) {
        Map map = (Map) this.b.get(cls);
        if (map == null) {
            map = new HashMap();
            this.b.put(cls, map);
        }
        map.put(cls2, lVar);
    }

    private <T, Y> l<T, Y> c(Class<T> cls, Class<Y> cls2) {
        Map map = (Map) this.b.get(cls);
        if (map != null) {
            return (l) map.get(cls2);
        }
        return null;
    }

    private <T, Y> m<T, Y> d(Class<T> cls, Class<Y> cls2) {
        m<T, Y> mVar;
        m<T, Y> mVar2;
        Map map = (Map) this.a.get(cls);
        if (map != null) {
            mVar = (m) map.get(cls2);
        } else {
            mVar = null;
        }
        if (mVar2 != null) {
            return mVar2;
        }
        Iterator it = this.a.keySet().iterator();
        while (true) {
            m<T, Y> mVar3 = mVar2;
            if (!it.hasNext()) {
                return mVar3;
            }
            Class cls3 = (Class) it.next();
            if (cls3.isAssignableFrom(cls)) {
                Map map2 = (Map) this.a.get(cls3);
                if (map2 != null) {
                    mVar2 = (m) map2.get(cls2);
                    if (mVar2 != null) {
                        return mVar2;
                    }
                }
            }
            mVar2 = mVar3;
        }
    }
}
