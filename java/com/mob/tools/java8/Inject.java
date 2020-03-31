package com.mob.tools.java8;

public interface Inject<T, R> extends Function {

    public interface MapInject<K, V, R> {
        R inject(K k, V v, R r);
    }

    R inject(T t, R r);
}
