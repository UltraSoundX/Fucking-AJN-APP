package com.b.a.c;

import com.b.a.b.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* compiled from: TypeToken */
public class a<T> {
    final Class<? super T> b;
    final Type c;
    final int d;

    protected a() {
        this.c = a(getClass());
        this.b = b.e(this.c);
        this.d = this.c.hashCode();
    }

    a(Type type) {
        this.c = b.d((Type) com.b.a.b.a.a(type));
        this.b = b.e(this.c);
        this.d = this.c.hashCode();
    }

    static Type a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return b.d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public final Class<? super T> a() {
        return this.b;
    }

    public final Type b() {
        return this.c;
    }

    public final int hashCode() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && b.a(this.c, ((a) obj).c);
    }

    public final String toString() {
        return b.f(this.c);
    }

    public static a<?> a(Type type) {
        return new a<>(type);
    }

    public static <T> a<T> b(Class<T> cls) {
        return new a<>(cls);
    }
}
