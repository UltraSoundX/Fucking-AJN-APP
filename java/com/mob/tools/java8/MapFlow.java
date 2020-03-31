package com.mob.tools.java8;

import com.mob.tools.java8.Closure.IClosure1V;
import com.mob.tools.java8.Closure.IClosure2V;
import com.mob.tools.java8.Collect.MapCollect;
import com.mob.tools.java8.Each.MapEach;
import com.mob.tools.java8.Filter.MapFilter;
import com.mob.tools.java8.Inject.MapInject;
import com.mob.tools.java8.Map.MapMap;
import com.mob.tools.java8.Peek.MapPeek;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapFlow<K, V> {
    Flow<MapItem<K, V>> flow;

    static class MapItem<K, V> implements Comparable<MapItem<K, V>> {
        public K key;
        public V value;

        MapItem() {
        }

        public static <K, V> MapItem<K, V> of(K k, V v) {
            MapItem<K, V> mapItem = new MapItem<>();
            mapItem.key = k;
            mapItem.value = v;
            return mapItem;
        }

        public int compareTo(MapItem<K, V> mapItem) {
            if (this.key == null) {
                if (mapItem == null || mapItem.key == null) {
                    return 0;
                }
                return -1;
            } else if (mapItem == null) {
                return 1;
            } else {
                if (mapItem.key == null) {
                    return 1;
                }
                Object[] objArr = {this.key, mapItem.key};
                Arrays.sort(objArr);
                if (!this.key.equals(objArr[0])) {
                    return 1;
                }
                Object[] objArr2 = {mapItem.key, this.key};
                Arrays.sort(objArr2);
                if (this.key.equals(objArr2[0])) {
                    return -1;
                }
                return 0;
            }
        }
    }

    MapFlow(Flow<MapItem<K, V>> flow2) {
        this.flow = flow2;
    }

    public MapFlow<K, V> filter(final MapFilter<K, V> mapFilter) {
        return new MapFlow<>(this.flow.filter(new Filter<MapItem<K, V>>() {
            public boolean filter(MapItem<K, V> mapItem) {
                return mapFilter.filter(mapItem.key, mapItem.value);
            }
        }));
    }

    public <R> MapFlow<K, R> map(final MapMap<K, V, R> mapMap) {
        return new MapFlow<>(this.flow.map(new Map<MapItem<K, V>, MapItem<K, R>>() {
            public MapItem<K, R> map(MapItem<K, V> mapItem) {
                mapItem.value = mapMap.map(mapItem.key, mapItem.value);
                return mapItem;
            }
        }));
    }

    public <U> MapFlow<U, V> mapKey(final MapMap<K, V, U> mapMap) {
        return new MapFlow<>(this.flow.map(new Map<MapItem<K, V>, MapItem<U, V>>() {
            public MapItem<U, V> map(MapItem<K, V> mapItem) {
                mapItem.key = mapMap.map(mapItem.key, mapItem.value);
                return mapItem;
            }
        }));
    }

    public <U, R> MapFlow<U, R> collect(final MapCollect<K, V, U, R> mapCollect) {
        return new MapFlow<>(this.flow.collect(new Collect<MapItem<K, V>, MapItem<U, R>>() {
            public Flow<MapItem<U, R>> collect(MapItem<K, V> mapItem) {
                return mapCollect.collect(mapItem.key, mapItem.value).flow;
            }
        }));
    }

    public MapFlow<K, V> peek(final MapPeek<K, V> mapPeek) {
        this.flow = this.flow.peek(new Peek<MapItem<K, V>>() {
            public void peek(MapItem<K, V> mapItem) {
                mapPeek.peek(mapItem.key, mapItem.value);
            }
        });
        return this;
    }

    public MapFlow<K, V> limit(int i) {
        this.flow = this.flow.limit(i);
        return this;
    }

    public MapFlow<K, V> skip(int i) {
        this.flow = this.flow.skip(i);
        return this;
    }

    public MapFlow<K, V> distinct() {
        this.flow = this.flow.distinct();
        return this;
    }

    public MapFlow<K, V> sort(final Comparator<K> comparator) {
        this.flow = this.flow.sort(new Comparator<MapItem<K, V>>() {
            public int compare(MapItem<K, V> mapItem, MapItem<K, V> mapItem2) {
                return comparator.compare(mapItem.key, mapItem2.key);
            }
        });
        return this;
    }

    public MapFlow<K, V> sort() {
        this.flow = this.flow.sort(new Comparator<MapItem<K, V>>() {
            public int compare(MapItem<K, V> mapItem, MapItem<K, V> mapItem2) {
                return mapItem.compareTo(mapItem2);
            }
        });
        return this;
    }

    public void each(final MapEach<K, V> mapEach) {
        this.flow.each(new Each<MapItem<K, V>>() {
            public void each(MapItem<K, V> mapItem) {
                mapEach.each(mapItem.key, mapItem.value);
            }
        });
    }

    public Optional<V> first() {
        V v = null;
        MapItem mapItem = (MapItem) this.flow.first().orElse(null);
        if (mapItem != null) {
            v = mapItem.value;
        }
        return new Optional<>(v);
    }

    public Optional<V> last() {
        V v = null;
        MapItem mapItem = (MapItem) this.flow.last().orElse(null);
        if (mapItem != null) {
            v = mapItem.value;
        }
        return new Optional<>(v);
    }

    public boolean any() {
        return this.flow.any();
    }

    public Map<K, V> toMap() {
        final HashMap hashMap = new HashMap();
        each(new MapEach<K, V>() {
            public void each(K k, V v) {
                hashMap.put(k, v);
            }
        });
        return hashMap;
    }

    public int count() {
        return this.flow.count();
    }

    public <R> R inject(R r, final MapInject<K, V, R> mapInject) {
        return this.flow.inject(r, new Inject<MapItem<K, V>, R>() {
            public R inject(MapItem<K, V> mapItem, R r) {
                return mapInject.inject(mapItem.key, mapItem.value, r);
            }
        });
    }

    public <R> R inject(MapInject<K, V, R> mapInject) {
        return inject(null, mapInject);
    }

    public void cowork(int i, final IClosure2V<K, V> iClosure2V) throws Throwable {
        this.flow.cowork(i, new IClosure1V<MapItem<K, V>>() {
            public void call(MapItem<K, V> mapItem) throws Throwable {
                iClosure2V.call(mapItem.key, mapItem.value);
            }
        });
    }

    public void cowork(final IClosure2V<K, V> iClosure2V) throws Throwable {
        this.flow.cowork(new IClosure1V<MapItem<K, V>>() {
            public void call(MapItem<K, V> mapItem) throws Throwable {
                iClosure2V.call(mapItem.key, mapItem.value);
            }
        });
    }
}
