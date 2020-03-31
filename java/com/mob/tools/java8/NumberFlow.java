package com.mob.tools.java8;

import com.mob.tools.java8.Closure.IClosure1V;
import com.mob.tools.java8.Collect.CollectByte;
import com.mob.tools.java8.Collect.CollectDouble;
import com.mob.tools.java8.Collect.CollectFloat;
import com.mob.tools.java8.Collect.CollectInt;
import com.mob.tools.java8.Collect.CollectLong;
import com.mob.tools.java8.Collect.CollectShort;
import com.mob.tools.java8.Map.MapByte;
import com.mob.tools.java8.Map.MapDouble;
import com.mob.tools.java8.Map.MapFloat;
import com.mob.tools.java8.Map.MapInt;
import com.mob.tools.java8.Map.MapLong;
import com.mob.tools.java8.Map.MapShort;
import com.mob.tools.java8.NumberFlow;
import java.lang.Number;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class NumberFlow<N extends Number, NF extends NumberFlow<N, NF>> {
    private Flow<N> flow;
    private NF self;

    public static class ByteFlow extends NumberFlow<Byte, ByteFlow> {
        ByteFlow(Flow<Byte> flow) {
            super(flow);
        }

        public Byte[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Byte[0];
            }
            return (Byte[]) list.toArray(new Byte[list.size()]);
        }

        public Byte sum() {
            return (Byte) inject(Byte.valueOf(0), new Inject<Byte, Byte>() {
                public Byte inject(Byte b, Byte b2) {
                    return Byte.valueOf((byte) (b.byteValue() + b2.byteValue()));
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Byte, Double>() {
                public Double inject(Byte b, Double d) {
                    return Double.valueOf(((double) b.byteValue()) + d.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public static class DoubleFlow extends NumberFlow<Double, DoubleFlow> {
        DoubleFlow(Flow<Double> flow) {
            super(flow);
        }

        public Double[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Double[0];
            }
            return (Double[]) list.toArray(new Double[list.size()]);
        }

        public Double sum() {
            return (Double) inject(Double.valueOf(0.0d), new Inject<Double, Double>() {
                public Double inject(Double d, Double d2) {
                    return Double.valueOf(d.doubleValue() + d2.doubleValue());
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Double, Double>() {
                public Double inject(Double d, Double d2) {
                    return Double.valueOf(d.doubleValue() + d2.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public static class FloatFlow extends NumberFlow<Float, FloatFlow> {
        FloatFlow(Flow<Float> flow) {
            super(flow);
        }

        public Float[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Float[0];
            }
            return (Float[]) list.toArray(new Float[list.size()]);
        }

        public Float sum() {
            return (Float) inject(Float.valueOf(0.0f), new Inject<Float, Float>() {
                public Float inject(Float f, Float f2) {
                    return Float.valueOf(f.floatValue() + f2.floatValue());
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Float, Double>() {
                public Double inject(Float f, Double d) {
                    return Double.valueOf(((double) f.floatValue()) + d.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public static class IntFlow extends NumberFlow<Integer, IntFlow> {
        IntFlow(Flow<Integer> flow) {
            super(flow);
        }

        public Integer[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Integer[0];
            }
            return (Integer[]) list.toArray(new Integer[list.size()]);
        }

        public Integer sum() {
            return (Integer) inject(Integer.valueOf(0), new Inject<Integer, Integer>() {
                public Integer inject(Integer num, Integer num2) {
                    return Integer.valueOf(num.intValue() + num2.intValue());
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Integer, Double>() {
                public Double inject(Integer num, Double d) {
                    return Double.valueOf(((double) num.intValue()) + d.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public static class LongFlow extends NumberFlow<Long, LongFlow> {
        LongFlow(Flow<Long> flow) {
            super(flow);
        }

        public Long[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Long[0];
            }
            return (Long[]) list.toArray(new Long[list.size()]);
        }

        public Long sum() {
            return (Long) inject(Long.valueOf(0), new Inject<Long, Long>() {
                public Long inject(Long l, Long l2) {
                    return Long.valueOf(l.longValue() + l2.longValue());
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Long, Double>() {
                public Double inject(Long l, Double d) {
                    return Double.valueOf(((double) l.longValue()) + d.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public static class ShortFlow extends NumberFlow<Short, ShortFlow> {
        ShortFlow(Flow<Short> flow) {
            super(flow);
        }

        public Short[] toArray() {
            List list = toList();
            if (list.isEmpty()) {
                return new Short[0];
            }
            return (Short[]) list.toArray(new Short[list.size()]);
        }

        public Short sum() {
            return (Short) inject(Short.valueOf(0), new Inject<Short, Short>() {
                public Short inject(Short sh, Short sh2) {
                    return Short.valueOf((short) (sh.shortValue() + sh2.shortValue()));
                }
            });
        }

        public Optional<Double> average() {
            List list = toList();
            if (list.isEmpty()) {
                return new Optional<>(null);
            }
            return new Optional<>(Double.valueOf(((Double) inject(Double.valueOf(0.0d), new Inject<Short, Double>() {
                public Double inject(Short sh, Double d) {
                    return Double.valueOf(((double) sh.shortValue()) + d.doubleValue());
                }
            })).doubleValue() / ((double) list.size())));
        }
    }

    public abstract Optional<Double> average();

    public abstract N sum();

    public abstract N[] toArray();

    private NumberFlow(Flow<N> flow2) {
        this.flow = flow2;
        this.self = this;
    }

    public NF filter(Filter<N> filter) {
        this.flow = this.flow.filter(filter);
        return this.self;
    }

    public ByteFlow map(MapByte<N> mapByte) {
        return new ByteFlow(this.flow.map(mapByte));
    }

    public ByteFlow mapByte(MapByte<N> mapByte) {
        return map(mapByte);
    }

    public ShortFlow map(MapShort<N> mapShort) {
        return new ShortFlow(this.flow.map(mapShort));
    }

    public ShortFlow mapShort(MapShort<N> mapShort) {
        return map(mapShort);
    }

    public IntFlow map(MapInt<N> mapInt) {
        return new IntFlow(this.flow.map(mapInt));
    }

    public IntFlow mapInt(MapInt<N> mapInt) {
        return map(mapInt);
    }

    public LongFlow map(MapLong<N> mapLong) {
        return new LongFlow(this.flow.map(mapLong));
    }

    public LongFlow mapLong(MapLong<N> mapLong) {
        return map(mapLong);
    }

    public FloatFlow map(MapFloat<N> mapFloat) {
        return new FloatFlow(this.flow.map(mapFloat));
    }

    public FloatFlow mapFloat(MapFloat<N> mapFloat) {
        return map(mapFloat);
    }

    public DoubleFlow map(MapDouble<N> mapDouble) {
        return new DoubleFlow(this.flow.map(mapDouble));
    }

    public DoubleFlow mapDouble(MapDouble<N> mapDouble) {
        return map(mapDouble);
    }

    public <R> Flow<R> rawMap(Map<N, R> map) {
        return this.flow.map(map);
    }

    public ByteFlow collect(CollectByte<N> collectByte) {
        return new ByteFlow(this.flow.collect(collectByte));
    }

    public ByteFlow collectByte(CollectByte<N> collectByte) {
        return collect(collectByte);
    }

    public ShortFlow collect(CollectShort<N> collectShort) {
        return new ShortFlow(this.flow.collect(collectShort));
    }

    public ShortFlow collectByte(CollectShort<N> collectShort) {
        return collect(collectShort);
    }

    public IntFlow collect(CollectInt<N> collectInt) {
        return new IntFlow(this.flow.collect(collectInt));
    }

    public IntFlow collectByte(CollectInt<N> collectInt) {
        return collect(collectInt);
    }

    public LongFlow collect(CollectLong<N> collectLong) {
        return new LongFlow(this.flow.collect(collectLong));
    }

    public LongFlow collectByte(CollectLong<N> collectLong) {
        return collect(collectLong);
    }

    public FloatFlow collect(CollectFloat<N> collectFloat) {
        return new FloatFlow(this.flow.collect(collectFloat));
    }

    public FloatFlow collectByte(CollectFloat<N> collectFloat) {
        return collect(collectFloat);
    }

    public DoubleFlow collect(CollectDouble<N> collectDouble) {
        return new DoubleFlow(this.flow.collect(collectDouble));
    }

    public DoubleFlow collectByte(CollectDouble<N> collectDouble) {
        return collect(collectDouble);
    }

    public <R> Flow<R> rawCollect(Collect<N, R> collect) {
        return this.flow.collect(collect);
    }

    public NF peek(Peek<N> peek) {
        this.flow = this.flow.peek(peek);
        return this.self;
    }

    public NF limit(int i) {
        this.flow = this.flow.limit(i);
        return this.self;
    }

    public NF skip(int i) {
        this.flow = this.flow.skip(i);
        return this.self;
    }

    public NF distinct() {
        this.flow = this.flow.distinct();
        return this.self;
    }

    public Flow<List<N>> chunk(int i) {
        return this.flow.chunk(i);
    }

    public Flow<N> chunkCollect(int i) {
        return this.flow.chunkCollect(i);
    }

    public NF sort(Comparator<N> comparator) {
        this.flow = this.flow.sort(comparator);
        return this.self;
    }

    public NF sort() {
        return sort(null);
    }

    public NF jumble() {
        this.flow = this.flow.jumble();
        return this.self;
    }

    public void each(Each<N> each) {
        this.flow.each(each);
    }

    public Optional<N> first() {
        return this.flow.first();
    }

    public Optional<N> min() {
        return this.flow.sort().first();
    }

    public Optional<N> last() {
        return this.flow.last();
    }

    public Optional<N> max() {
        return this.flow.sort().last();
    }

    public boolean any() {
        return this.flow.any();
    }

    public Set<N> toSet() {
        return this.flow.toSet();
    }

    public List<N> toList() {
        return this.flow.toList();
    }

    public int count() {
        return this.flow.count();
    }

    public <R> R inject(R r, Inject<N, R> inject) {
        return this.flow.inject(r, inject);
    }

    public <R> R inject(Inject<N, R> inject) {
        return inject(null, inject);
    }

    public <K, V> Map<K, V> toMap(MapMaker<N, K, V> mapMaker) {
        return this.flow.toMap(mapMaker);
    }

    public void cowork(int i, IClosure1V<N> iClosure1V) throws Throwable {
        this.flow.cowork(i, iClosure1V);
    }

    public void cowork(IClosure1V<N> iClosure1V) throws Throwable {
        this.flow.cowork(iClosure1V);
    }
}
