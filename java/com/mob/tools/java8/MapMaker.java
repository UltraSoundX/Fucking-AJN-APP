package com.mob.tools.java8;

import java.util.Map;

public interface MapMaker<T, K, V> {
    void toMap(T t, Map<K, V> map);
}
