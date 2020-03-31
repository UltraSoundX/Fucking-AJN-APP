package com.mob.tools.java8;

public interface Map<T, R> extends Function {

    public interface MapByte<N> extends Map<N, Byte> {
    }

    public interface MapDouble<N> extends Map<N, Double> {
    }

    public interface MapFloat<N> extends Map<N, Float> {
    }

    public interface MapInt<N> extends Map<N, Integer> {
    }

    public interface MapLong<N> extends Map<N, Long> {
    }

    public interface MapMap<K, V, R> {
        R map(K k, V v);
    }

    public interface MapShort<N> extends Map<N, Short> {
    }

    R map(T t);
}
