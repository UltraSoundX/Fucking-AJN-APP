package com.mob.tools.java8;

public interface Filter<T> extends Function {

    public interface MapFilter<K, V> {
        boolean filter(K k, V v);
    }

    boolean filter(T t);
}
