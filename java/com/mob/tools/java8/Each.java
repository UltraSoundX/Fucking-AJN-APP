package com.mob.tools.java8;

public interface Each<T> extends Function {

    public interface MapEach<K, V> {
        void each(K k, V v);
    }

    void each(T t);
}
