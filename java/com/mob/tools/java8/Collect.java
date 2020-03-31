package com.mob.tools.java8;

public interface Collect<T, R> extends Function {

    public interface CollectByte<N> extends Collect<N, Byte> {
    }

    public interface CollectDouble<N> extends Collect<N, Double> {
    }

    public interface CollectFloat<N> extends Collect<N, Float> {
    }

    public interface CollectInt<N> extends Collect<N, Integer> {
    }

    public interface CollectLong<N> extends Collect<N, Long> {
    }

    public interface CollectShort<N> extends Collect<N, Short> {
    }

    public interface MapCollect<K, V, U, R> {
        MapFlow<U, R> collect(K k, V v);
    }

    Flow<R> collect(T t);
}
