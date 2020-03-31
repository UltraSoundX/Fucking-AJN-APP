package com.mob.tools.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapCollections {

    public static class MapBulder<K, V> {
        private HashMap<K, V> map;

        MapBulder() {
            new HashMap();
        }

        public MapItemBuilder<K, V> set(K k) {
            this.map.put(k, null);
            return new MapItemBuilder<>(this, k);
        }

        /* access modifiers changed from: 0000 */
        public MapBulder<K, V> putItem(K k, V v) {
            this.map.put(k, v);
            return this;
        }

        public Map<K, V> build() {
            return this.map;
        }
    }

    public static class MapItemBuilder<K, V> {
        private MapBulder<K, V> builder;
        private K key;

        MapItemBuilder(MapBulder<K, V> mapBulder, K k) {
            this.builder = mapBulder;
            this.key = k;
        }

        public void withValue(V v) {
            this.builder.putItem(this.key, v);
        }
    }

    public static <T> List<T> listOf(T... tArr) {
        return Arrays.asList(tArr);
    }

    public static List<Byte> listOf(byte... bArr) {
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return listOf((T[]) bArr2);
    }

    public static List<Short> listOf(short... sArr) {
        Short[] shArr = new Short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return listOf((T[]) shArr);
    }

    public static List<Integer> listOf(int... iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return listOf((T[]) numArr);
    }

    public static List<Long> listOf(long... jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return listOf((T[]) lArr);
    }

    public static List<Float> listOf(float... fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return listOf((T[]) fArr2);
    }

    public static List<Double> listOf(double... dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return listOf((T[]) dArr2);
    }

    public static List<Character> listOf(char... cArr) {
        Character[] chArr = new Character[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return listOf((T[]) chArr);
    }

    public static List<Boolean> listOf(boolean... zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return listOf((T[]) boolArr);
    }

    public static <T> Set<T> setOf(T... tArr) {
        return new HashSet(listOf(tArr));
    }

    public static Set<Byte> setOf(byte... bArr) {
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return setOf((T[]) bArr2);
    }

    public static Set<Short> setOf(short... sArr) {
        Short[] shArr = new Short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return setOf((T[]) shArr);
    }

    public static Set<Integer> setOf(int... iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return setOf((T[]) numArr);
    }

    public static Set<Long> setOf(long... jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return setOf((T[]) lArr);
    }

    public static Set<Float> setOf(float... fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return setOf((T[]) fArr2);
    }

    public static Set<Double> setOf(double... dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return setOf((T[]) dArr2);
    }

    public static Set<Character> setOf(char... cArr) {
        Character[] chArr = new Character[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return setOf((T[]) chArr);
    }

    public static Set<Boolean> setOf(boolean... zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return setOf((T[]) boolArr);
    }

    public static <K, V> Map<K, V> mapOf(K k, V v) {
        return mapOfRaw(MapItem.of(k, v));
    }

    private static <K, V> Map<K, V> mapOfRaw(MapItem<K, V>... mapItemArr) {
        HashMap hashMap = new HashMap();
        for (MapItem<K, V> mapItem : mapItemArr) {
            hashMap.put(mapItem.key, mapItem.value);
        }
        return hashMap;
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5), MapItem.of(k6, v6));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5), MapItem.of(k6, v6), MapItem.of(k7, v7));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5), MapItem.of(k6, v6), MapItem.of(k7, v7), MapItem.of(k8, v8));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5), MapItem.of(k6, v6), MapItem.of(k7, v7), MapItem.of(k8, v8), MapItem.of(k9, v9));
    }

    public static <K, V> Map<K, V> mapOf(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
        return mapOfRaw(MapItem.of(k, v), MapItem.of(k2, v2), MapItem.of(k3, v3), MapItem.of(k4, v4), MapItem.of(k5, v5), MapItem.of(k6, v6), MapItem.of(k7, v7), MapItem.of(k8, v8), MapItem.of(k9, v9), MapItem.of(k10, v10));
    }

    public static <K, V> MapBulder<K, V> buildMap() {
        return new MapBulder<>();
    }
}
