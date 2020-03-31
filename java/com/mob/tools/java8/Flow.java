package com.mob.tools.java8;

import com.mob.tools.java8.Closure.IClosure;
import com.mob.tools.java8.Closure.IClosure1V;
import com.mob.tools.java8.FlowIterator.ArrayIterator;
import com.mob.tools.java8.FlowIterator.BoolArrayIterator;
import com.mob.tools.java8.FlowIterator.BufferedReaderIterator;
import com.mob.tools.java8.FlowIterator.ByteArrayIterator;
import com.mob.tools.java8.FlowIterator.CharArrayIterator;
import com.mob.tools.java8.FlowIterator.DoubleArrayIterator;
import com.mob.tools.java8.FlowIterator.DoubleRangeIterator;
import com.mob.tools.java8.FlowIterator.EnumerationIterator;
import com.mob.tools.java8.FlowIterator.FileReaderIterator;
import com.mob.tools.java8.FlowIterator.FloatArrayIterator;
import com.mob.tools.java8.FlowIterator.FlowIteratorWrapper;
import com.mob.tools.java8.FlowIterator.IntArrayIterator;
import com.mob.tools.java8.FlowIterator.IntRangeIterator;
import com.mob.tools.java8.FlowIterator.IterableIterator;
import com.mob.tools.java8.FlowIterator.LongArrayIterator;
import com.mob.tools.java8.FlowIterator.MapIterator;
import com.mob.tools.java8.FlowIterator.ShortArrayIterator;
import com.mob.tools.java8.NumberFlow.ByteFlow;
import com.mob.tools.java8.NumberFlow.DoubleFlow;
import com.mob.tools.java8.NumberFlow.FloatFlow;
import com.mob.tools.java8.NumberFlow.IntFlow;
import com.mob.tools.java8.NumberFlow.LongFlow;
import com.mob.tools.java8.NumberFlow.ShortFlow;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Flow<T> {
    protected LinkedList<Function> functions;
    protected FlowIterator<?> iterator;

    public static <T> Flow<T> of(FlowIterator<T> flowIterator) {
        return new Flow<>(flowIterator);
    }

    public static <T> Flow<T> of(Iterable<T> iterable) {
        return of((FlowIterator<T>) new IterableIterator<T>(iterable));
    }

    public static <T> Flow<T> of(Enumeration<T> enumeration) {
        return of((FlowIterator<T>) new EnumerationIterator<T>(enumeration));
    }

    public static <K, V> MapFlow<K, V> of(Map<K, V> map) {
        return new MapFlow<>(of((FlowIterator<T>) new MapIterator<T>(map)));
    }

    public static <T> Flow<T> of(T... tArr) {
        return of((FlowIterator<T>) new ArrayIterator<T>(tArr));
    }

    public static ByteFlow of(Byte... bArr) {
        return new ByteFlow(of((FlowIterator<T>) new ArrayIterator<T>(bArr)));
    }

    public static ByteFlow of(byte... bArr) {
        return new ByteFlow(of((FlowIterator<T>) new ByteArrayIterator<T>(bArr)));
    }

    public static ShortFlow of(Short... shArr) {
        return new ShortFlow(of((FlowIterator<T>) new ArrayIterator<T>(shArr)));
    }

    public static ShortFlow of(short... sArr) {
        return new ShortFlow(of((FlowIterator<T>) new ShortArrayIterator<T>(sArr)));
    }

    public static IntFlow of(Integer... numArr) {
        return new IntFlow(of((FlowIterator<T>) new ArrayIterator<T>(numArr)));
    }

    public static IntFlow of(int... iArr) {
        return new IntFlow(of((FlowIterator<T>) new IntArrayIterator<T>(iArr)));
    }

    public static LongFlow of(Long... lArr) {
        return new LongFlow(of((FlowIterator<T>) new ArrayIterator<T>(lArr)));
    }

    public static LongFlow of(long... jArr) {
        return new LongFlow(of((FlowIterator<T>) new LongArrayIterator<T>(jArr)));
    }

    public static FloatFlow of(Float... fArr) {
        return new FloatFlow(of((FlowIterator<T>) new ArrayIterator<T>(fArr)));
    }

    public static FloatFlow of(float... fArr) {
        return new FloatFlow(of((FlowIterator<T>) new FloatArrayIterator<T>(fArr)));
    }

    public static DoubleFlow of(Double... dArr) {
        return new DoubleFlow(of((FlowIterator<T>) new ArrayIterator<T>(dArr)));
    }

    public static DoubleFlow of(double... dArr) {
        return new DoubleFlow(of((FlowIterator<T>) new DoubleArrayIterator<T>(dArr)));
    }

    public static Flow<Character> of(char... cArr) {
        return of((FlowIterator<T>) new CharArrayIterator<T>(cArr));
    }

    public static Flow<Boolean> of(boolean... zArr) {
        return of((FlowIterator<T>) new BoolArrayIterator<T>(zArr));
    }

    public static IntFlow range(int i, int i2, int i3) {
        return new IntFlow(of((FlowIterator<T>) new IntRangeIterator<T>(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3))));
    }

    public static IntFlow range(int i, int i2) {
        return range(i, i2, 1);
    }

    public static DoubleFlow range(double d, double d2, double d3) {
        return new DoubleFlow(of((FlowIterator<T>) new DoubleRangeIterator<T>(Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3))));
    }

    public static DoubleFlow range(double d, double d2) {
        return range(d, d2, 1.0d);
    }

    public static Flow<String> of(BufferedReader bufferedReader) {
        return of((FlowIterator<T>) new BufferedReaderIterator<T>(bufferedReader));
    }

    public static Flow<String> of(File file) {
        return of(file, "utf-8");
    }

    public static Flow<String> of(File file, String str) {
        return of(file, Charset.forName(str));
    }

    public static Flow<String> of(File file, Charset charset) {
        return of((FlowIterator<T>) new FileReaderIterator<T>(file, charset));
    }

    public static <T> Flow<T> concat(final Flow<? extends T>... flowArr) {
        return new Flow<T>() {
            /* access modifiers changed from: protected */
            public void evaluate() {
                Flow[] flowArr;
                for (Flow flow : flowArr) {
                    flow.functions.addAll(this.functions);
                    flow.evaluate();
                }
            }
        };
    }

    private Flow() {
        this.functions = new LinkedList<>();
    }

    private Flow(FlowIterator<T> flowIterator) {
        this.iterator = flowIterator;
        this.functions = new LinkedList<>();
    }

    private Flow(Flow<?> flow) {
        this.iterator = flow.iterator;
        this.functions = flow.functions;
    }

    private <R> Flow<R> nextFlow(Function function) {
        this.functions.add(function);
        return new Flow<>(this);
    }

    public Flow<T> filter(Filter<T> filter) {
        return nextFlow(filter);
    }

    public <R> Flow<R> map(Map<T, R> map) {
        return nextFlow(map);
    }

    public <R> Flow<R> collect(Collect<T, R> collect) {
        return nextFlow(collect);
    }

    public Flow<T> peek(Peek<T> peek) {
        return nextFlow(peek);
    }

    public Flow<T> limit(int i) {
        return i < 0 ? this : nextFlow(new Limit(i));
    }

    public Flow<T> skip(int i) {
        return i < 0 ? this : nextFlow(new Skip(i));
    }

    public Flow<T> distinct() {
        return nextFlow(new Filter<T>() {
            private HashSet<T> output = new HashSet<>();

            public boolean filter(T t) {
                if (this.output.contains(t)) {
                    return false;
                }
                this.output.add(t);
                return true;
            }
        });
    }

    public Flow<List<T>> chunk(int i) {
        return nextFlow(new Chunk(i));
    }

    public Flow<T> chunkCollect(int i) {
        return chunk(i).collect(new Collect<List<T>, T>() {
            public Flow<T> collect(List<T> list) {
                return Flow.of((Iterable<T>) list);
            }
        });
    }

    public Flow<T> sort(final Comparator<T> comparator) {
        Flow<T> flow = new Flow<>();
        flow.iterator = new FlowIteratorWrapper<T>() {
            /* access modifiers changed from: protected */
            public FlowIterator<T> initIterator() {
                List list = this.toList();
                Collections.sort(list, comparator);
                final Iterator it = list.iterator();
                return new FlowIterator<T>() {
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public T next() {
                        return it.next();
                    }

                    public void finish() {
                    }
                };
            }
        };
        return flow;
    }

    public Flow<T> sort() {
        return sort(null);
    }

    public Flow<T> jumble() {
        Flow<T> flow = new Flow<>();
        flow.iterator = new FlowIteratorWrapper<T>() {
            /* access modifiers changed from: protected */
            public FlowIterator<T> initIterator() {
                List list = this.toList();
                Random random = new Random();
                LinkedList linkedList = new LinkedList();
                while (list.size() > 0) {
                    linkedList.add(list.remove(random.nextInt(list.size())));
                }
                final Iterator it = linkedList.iterator();
                return new FlowIterator<T>() {
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public T next() {
                        return it.next();
                    }

                    public void finish() {
                    }
                };
            }
        };
        return flow;
    }

    public void each(Each<T> each) {
        nextFlow(each);
        evaluate();
    }

    public Optional<T> first() {
        Limit limit = new Limit(1);
        nextFlow(limit);
        evaluate();
        LinkedList output = limit.output();
        return new Optional<>(output.isEmpty() ? null : output.getFirst());
    }

    public Optional<T> last() {
        Limit limit = new Limit();
        nextFlow(limit);
        evaluate();
        LinkedList output = limit.output();
        return new Optional<>(output.isEmpty() ? null : output.getLast());
    }

    public boolean any() {
        Limit limit = new Limit(1);
        nextFlow(limit);
        evaluate();
        if (!limit.output().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean any(Filter<T> filter) {
        return filter(filter).any();
    }

    public void cowork(int i, IClosure1V<T> iClosure1V) throws Throwable {
        final List list = toList();
        final int size = list == null ? 0 : list.size();
        CoworkerThread.produceWork(new IClosure<T>() {
            private int index;

            public T call() throws Throwable {
                T t = this.index < size ? list.get(this.index) : null;
                this.index++;
                return t;
            }
        }).consumeWork(iClosure1V).threadCount(i).start();
    }

    public void cowork(IClosure1V<T> iClosure1V) throws Throwable {
        cowork(5, iClosure1V);
    }

    /* access modifiers changed from: protected */
    public void evaluate() {
        evaluate(this.iterator, 0);
    }

    private void evaluate(FlowIterator<Object> flowIterator, int i) {
        Object obj;
        boolean z;
        while (flowIterator.hasNext()) {
            boolean z2 = false;
            Object next = flowIterator.next();
            int size = this.functions.size();
            int i2 = i;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                Function function = (Function) this.functions.get(i2);
                if (function instanceof Filter) {
                    if (!((Filter) function).filter(next)) {
                        continue;
                        break;
                    }
                    obj = next;
                } else if (function instanceof Map) {
                    obj = ((Map) function).map(next);
                } else if (function instanceof Collect) {
                    evaluate(((Collect) function).collect(next).iterator, i2 + 1);
                    continue;
                    break;
                } else if (function instanceof Each) {
                    ((Each) function).each(next);
                    obj = next;
                } else if (function instanceof Limit) {
                    if (((Limit) function).test(next) > 0) {
                        z = true;
                    } else {
                        z = z2;
                    }
                    z2 = z;
                    obj = next;
                } else if (function instanceof Skip) {
                    if (((Skip) function).test(next) < 2) {
                        continue;
                        break;
                    }
                    obj = next;
                } else if (function instanceof Chunk) {
                    Chunk chunk = (Chunk) function;
                    if (chunk.test(next) < 1 && flowIterator.hasNext()) {
                        break;
                    }
                    Object output = chunk.output();
                    chunk.reset();
                    obj = output;
                } else {
                    if (function instanceof Peek) {
                        ((Peek) function).peek(next);
                    }
                    obj = next;
                }
                i2++;
                next = obj;
            }
            if (z2) {
                break;
            }
        }
        flowIterator.finish();
    }

    public Set<T> toSet() {
        final HashSet hashSet = new HashSet();
        each(new Each<T>() {
            public void each(T t) {
                hashSet.add(t);
            }
        });
        return hashSet;
    }

    public List<T> toList() {
        final LinkedList linkedList = new LinkedList();
        each(new Each<T>() {
            public void each(T t) {
                linkedList.add(t);
            }
        });
        return linkedList;
    }

    public Object[] toArray() {
        return toList().toArray();
    }

    public T[] toArray(T[] tArr) {
        return toList().toArray(tArr);
    }

    public int count() {
        final int[] iArr = new int[1];
        each(new Each<T>() {
            public void each(T t) {
                int[] iArr = iArr;
                iArr[0] = iArr[0] + 1;
            }
        });
        return iArr[0];
    }

    public <R> R inject(R r, final Inject<T, R> inject) {
        final R[] rArr = {r};
        each(new Each<T>() {
            public void each(T t) {
                rArr[0] = inject.inject(t, rArr[0]);
            }
        });
        return rArr[0];
    }

    public <R> R inject(Inject<T, R> inject) {
        return inject(null, inject);
    }

    public <K, V> Map<K, V> toMap(final MapMaker<T, K, V> mapMaker) {
        final HashMap hashMap = new HashMap();
        each(new Each<T>() {
            public void each(T t) {
                mapMaker.toMap(t, hashMap);
            }
        });
        return hashMap;
    }
}
