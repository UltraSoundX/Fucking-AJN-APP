package com.mob.tools.java8;

public interface Peek<T> extends Function {

    public interface MapPeek<K, V> {
        void peek(K k, V v);
    }

    void peek(T t);
}
