package com.mob.tools.java8;

import android.support.v4.media.session.PlaybackStateCompat;
import com.mob.tools.java8.Closure.IClosure;
import com.mob.tools.java8.Closure.IClosureV;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public interface FlowIterator<R> {

    public static class ArrayIterator<T> implements FlowIterator<T> {
        private T[] array;
        private int index;

        ArrayIterator(T... tArr) {
            this.array = tArr;
        }

        public boolean hasNext() {
            return this.array != null && this.index < this.array.length;
        }

        public T next() {
            T t = this.array[this.index];
            this.index++;
            return t;
        }

        public void finish() {
        }
    }

    public static class BoolArrayIterator extends PrimitiveArrayIterator<Boolean> {
        private boolean[] array;

        BoolArrayIterator(boolean... zArr) {
            this.array = zArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Boolean element(int i) {
            return Boolean.valueOf(this.array[i]);
        }
    }

    public static class BufferedReaderIterator implements FlowIterator<String> {
        /* access modifiers changed from: private */
        public BufferedReader br;
        private String nextLine;

        BufferedReaderIterator(BufferedReader bufferedReader) {
            this.br = bufferedReader;
            readNext();
        }

        private void readNext() {
            this.nextLine = (String) Closure.uncheck((IClosure<R>) new IClosure<String>() {
                public String call() throws Throwable {
                    return BufferedReaderIterator.this.br.readLine();
                }
            });
        }

        public boolean hasNext() {
            return this.nextLine != null;
        }

        public String next() {
            String str = this.nextLine;
            readNext();
            return str;
        }

        public void finish() {
        }
    }

    public static class ByteArrayIterator extends PrimitiveArrayIterator<Byte> {
        private byte[] array;

        ByteArrayIterator(byte... bArr) {
            this.array = bArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Byte element(int i) {
            return Byte.valueOf(this.array[i]);
        }
    }

    public static class CharArrayIterator extends PrimitiveArrayIterator<Character> {
        private char[] array;

        CharArrayIterator(char... cArr) {
            this.array = cArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Character element(int i) {
            return Character.valueOf(this.array[i]);
        }
    }

    public static class DoubleArrayIterator extends PrimitiveArrayIterator<Double> {
        private double[] array;

        DoubleArrayIterator(double... dArr) {
            this.array = dArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Double element(int i) {
            return Double.valueOf(this.array[i]);
        }
    }

    public static class DoubleRangeIterator extends RangeIterator<Double> {
        DoubleRangeIterator(Double d, Double d2, Double d3) {
            super(d, d2, d3);
        }

        /* access modifiers changed from: protected */
        public Double increase(Double d, Double d2) {
            return Double.valueOf(d.doubleValue() + d2.doubleValue());
        }
    }

    public static class EnumerationIterator<T> implements FlowIterator<T> {
        private Enumeration<T> enumeration;

        EnumerationIterator(Enumeration<T> enumeration2) {
            this.enumeration = enumeration2;
        }

        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        public T next() {
            return this.enumeration.nextElement();
        }

        public void finish() {
        }
    }

    public static class FileReaderIterator implements FlowIterator<String> {
        /* access modifiers changed from: private */
        public BufferedReader br;
        /* access modifiers changed from: private */
        public Charset charset;
        /* access modifiers changed from: private */
        public File file;
        private BufferedReaderIterator it;

        FileReaderIterator(File file2, Charset charset2) {
            this.file = file2;
            this.charset = charset2;
        }

        public synchronized boolean hasNext() {
            if (this.br == null) {
                this.br = (BufferedReader) Closure.uncheck((IClosure<R>) new IClosure<BufferedReader>() {
                    public BufferedReader call() throws Throwable {
                        InputStreamReader inputStreamReader;
                        FileInputStream fileInputStream = new FileInputStream(FileReaderIterator.this.file);
                        if (FileReaderIterator.this.file.length() < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                            inputStreamReader = new InputStreamReader(fileInputStream, FileReaderIterator.this.charset);
                        } else {
                            inputStreamReader = new InputStreamReader(new BufferedInputStream(fileInputStream, 1048576), FileReaderIterator.this.charset);
                        }
                        return new BufferedReader(inputStreamReader);
                    }
                });
                this.it = new BufferedReaderIterator(this.br);
            }
            return this.it != null && this.it.hasNext();
        }

        public String next() {
            if (this.it == null) {
                return null;
            }
            return this.it.next();
        }

        public synchronized void finish() {
            this.it = null;
            if (this.br != null) {
                Closure.uncheck((IClosureV) new IClosureV() {
                    public void call() throws Throwable {
                        FileReaderIterator.this.br.close();
                        FileReaderIterator.this.br = null;
                    }
                });
            }
        }
    }

    public static class FloatArrayIterator extends PrimitiveArrayIterator<Float> {
        private float[] array;

        FloatArrayIterator(float... fArr) {
            this.array = fArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Float element(int i) {
            return Float.valueOf(this.array[i]);
        }
    }

    public static abstract class FlowIteratorWrapper<T> implements FlowIterator<T> {
        private FlowIterator<T> it;

        /* access modifiers changed from: protected */
        public abstract FlowIterator<T> initIterator();

        private FlowIterator<T> getIterator() {
            if (this.it == null) {
                this.it = initIterator();
            }
            return this.it;
        }

        public boolean hasNext() {
            return getIterator().hasNext();
        }

        public T next() {
            return getIterator().next();
        }

        public void finish() {
        }
    }

    public static class IntArrayIterator extends PrimitiveArrayIterator<Integer> {
        private int[] array;

        IntArrayIterator(int... iArr) {
            this.array = iArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Integer element(int i) {
            return Integer.valueOf(this.array[i]);
        }
    }

    public static class IntRangeIterator extends RangeIterator<Integer> {
        IntRangeIterator(Integer num, Integer num2, Integer num3) {
            super(num, num2, num3);
        }

        /* access modifiers changed from: protected */
        public Integer increase(Integer num, Integer num2) {
            return Integer.valueOf(num.intValue() + num2.intValue());
        }
    }

    public static class IterableIterator<T> implements FlowIterator<T> {
        private Iterator<T> it;

        IterableIterator(Iterable<T> iterable) {
            this.it = iterable.iterator();
        }

        public boolean hasNext() {
            return this.it.hasNext();
        }

        public T next() {
            return this.it.next();
        }

        public void finish() {
        }
    }

    public static class LongArrayIterator extends PrimitiveArrayIterator<Long> {
        private long[] array;

        LongArrayIterator(long... jArr) {
            this.array = jArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Long element(int i) {
            return Long.valueOf(this.array[i]);
        }
    }

    public static class MapIterator<K, V> implements FlowIterator<MapItem<K, V>> {
        private Iterator<Entry<K, V>> it;

        MapIterator(Map<K, V> map) {
            this.it = map.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.it.hasNext();
        }

        public MapItem<K, V> next() {
            MapItem<K, V> mapItem = new MapItem<>();
            Entry entry = (Entry) this.it.next();
            mapItem.key = entry.getKey();
            mapItem.value = entry.getValue();
            return mapItem;
        }

        public void finish() {
        }
    }

    public static abstract class PrimitiveArrayIterator<E> implements FlowIterator<E> {
        private int index;

        /* access modifiers changed from: protected */
        public abstract E element(int i);

        /* access modifiers changed from: protected */
        public abstract int length();

        public boolean hasNext() {
            return this.index < length();
        }

        public E next() {
            E element = element(this.index);
            this.index++;
            return element;
        }

        public void finish() {
        }
    }

    public static abstract class RangeIterator<T extends Comparable<T>> implements FlowIterator<T> {
        private T position;
        private T step;
        private T toExclude;

        /* access modifiers changed from: protected */
        public abstract T increase(T t, T t2);

        RangeIterator(T t, T t2, T t3) {
            this.toExclude = t2;
            this.step = t3;
            this.position = t;
        }

        public boolean hasNext() {
            return this.position.compareTo(this.toExclude) < 0;
        }

        public T next() {
            T t = this.position;
            this.position = increase(this.position, this.step);
            return t;
        }

        public void finish() {
        }
    }

    public static class ShortArrayIterator extends PrimitiveArrayIterator<Short> {
        private short[] array;

        ShortArrayIterator(short... sArr) {
            this.array = sArr;
        }

        /* access modifiers changed from: protected */
        public int length() {
            if (this.array == null) {
                return 0;
            }
            return this.array.length;
        }

        /* access modifiers changed from: protected */
        public Short element(int i) {
            return Short.valueOf(this.array[i]);
        }
    }

    void finish();

    boolean hasNext();

    R next();
}
