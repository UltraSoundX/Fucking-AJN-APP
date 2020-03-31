package com.qq.taf.jce;

import com.tencent.mid.sotrage.StorageInterface;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class JceDisplayer {
    private int _level = 0;
    private StringBuilder sb;

    private void ps(String str) {
        for (int i = 0; i < this._level; i++) {
            this.sb.append(9);
        }
        if (str != null) {
            this.sb.append(str).append(": ");
        }
    }

    public JceDisplayer(StringBuilder sb2, int i) {
        this.sb = sb2;
        this._level = i;
    }

    public JceDisplayer(StringBuilder sb2) {
        this.sb = sb2;
    }

    public JceDisplayer display(boolean z, String str) {
        ps(str);
        this.sb.append(z ? 'T' : 'F').append(10);
        return this;
    }

    public JceDisplayer displaySimple(boolean z, boolean z2) {
        this.sb.append(z ? 'T' : 'F');
        if (z2) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(byte b, String str) {
        ps(str);
        this.sb.append(b).append(10);
        return this;
    }

    public JceDisplayer displaySimple(byte b, boolean z) {
        this.sb.append(b);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(char c, String str) {
        ps(str);
        this.sb.append(c).append(10);
        return this;
    }

    public JceDisplayer displaySimple(char c, boolean z) {
        this.sb.append(c);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(short s, String str) {
        ps(str);
        this.sb.append(s).append(10);
        return this;
    }

    public JceDisplayer displaySimple(short s, boolean z) {
        this.sb.append(s);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(int i, String str) {
        ps(str);
        this.sb.append(i).append(10);
        return this;
    }

    public JceDisplayer displaySimple(int i, boolean z) {
        this.sb.append(i);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(long j, String str) {
        ps(str);
        this.sb.append(j).append(10);
        return this;
    }

    public JceDisplayer displaySimple(long j, boolean z) {
        this.sb.append(j);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(float f, String str) {
        ps(str);
        this.sb.append(f).append(10);
        return this;
    }

    public JceDisplayer displaySimple(float f, boolean z) {
        this.sb.append(f);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(double d, String str) {
        ps(str);
        this.sb.append(d).append(10);
        return this;
    }

    public JceDisplayer displaySimple(double d, boolean z) {
        this.sb.append(d);
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(String str, String str2) {
        ps(str2);
        if (str == null) {
            this.sb.append("null").append(10);
        } else {
            this.sb.append(str).append(10);
        }
        return this;
    }

    public JceDisplayer displaySimple(String str, boolean z) {
        if (str == null) {
            this.sb.append("null");
        } else {
            this.sb.append(str);
        }
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(byte[] bArr, String str) {
        ps(str);
        if (bArr == null) {
            this.sb.append("null").append(10);
        } else if (bArr.length == 0) {
            this.sb.append(bArr.length).append(", []").append(10);
        } else {
            this.sb.append(bArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (byte display : bArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(byte[] bArr, boolean z) {
        if (bArr != null && bArr.length != 0) {
            this.sb.append(HexUtil.bytes2HexStr(bArr));
            if (z) {
                this.sb.append("|");
            }
        } else if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(char[] cArr, String str) {
        ps(str);
        if (cArr == null) {
            this.sb.append("null").append(10);
        } else if (cArr.length == 0) {
            this.sb.append(cArr.length).append(", []").append(10);
        } else {
            this.sb.append(cArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (char display : cArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(char[] cArr, boolean z) {
        if (cArr != null && cArr.length != 0) {
            this.sb.append(new String(cArr));
            if (z) {
                this.sb.append("|");
            }
        } else if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public JceDisplayer display(short[] sArr, String str) {
        ps(str);
        if (sArr == null) {
            this.sb.append("null").append(10);
        } else if (sArr.length == 0) {
            this.sb.append(sArr.length).append(", []").append(10);
        } else {
            this.sb.append(sArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (short display : sArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(short[] sArr, boolean z) {
        if (sArr == null || sArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < sArr.length; i++) {
                short s = sArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(s, false);
            }
            this.sb.append("]");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public JceDisplayer display(int[] iArr, String str) {
        ps(str);
        if (iArr == null) {
            this.sb.append("null").append(10);
        } else if (iArr.length == 0) {
            this.sb.append(iArr.length).append(", []").append(10);
        } else {
            this.sb.append(iArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int display : iArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(int[] iArr, boolean z) {
        if (iArr == null || iArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(i2, false);
            }
            this.sb.append("]");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public JceDisplayer display(long[] jArr, String str) {
        ps(str);
        if (jArr == null) {
            this.sb.append("null").append(10);
        } else if (jArr.length == 0) {
            this.sb.append(jArr.length).append(", []").append(10);
        } else {
            this.sb.append(jArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (long display : jArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(long[] jArr, boolean z) {
        if (jArr == null || jArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < jArr.length; i++) {
                long j = jArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(j, false);
            }
            this.sb.append("]");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public JceDisplayer display(float[] fArr, String str) {
        ps(str);
        if (fArr == null) {
            this.sb.append("null").append(10);
        } else if (fArr.length == 0) {
            this.sb.append(fArr.length).append(", []").append(10);
        } else {
            this.sb.append(fArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (float display : fArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(float[] fArr, boolean z) {
        if (fArr == null || fArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < fArr.length; i++) {
                float f = fArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(f, false);
            }
            this.sb.append("]");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public JceDisplayer display(double[] dArr, String str) {
        ps(str);
        if (dArr == null) {
            this.sb.append("null").append(10);
        } else if (dArr.length == 0) {
            this.sb.append(dArr.length).append(", []").append(10);
        } else {
            this.sb.append(dArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (double display : dArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public JceDisplayer displaySimple(double[] dArr, boolean z) {
        if (dArr == null || dArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(d, false);
            }
            this.sb.append("[");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public <K, V> JceDisplayer display(Map<K, V> map, String str) {
        ps(str);
        if (map == null) {
            this.sb.append("null").append(10);
        } else if (map.isEmpty()) {
            this.sb.append(map.size()).append(", {}").append(10);
        } else {
            this.sb.append(map.size()).append(", {").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            JceDisplayer jceDisplayer2 = new JceDisplayer(this.sb, this._level + 2);
            for (Entry entry : map.entrySet()) {
                jceDisplayer.display('(', (String) null);
                jceDisplayer2.display((T) entry.getKey(), (String) null);
                jceDisplayer2.display((T) entry.getValue(), (String) null);
                jceDisplayer.display(')', (String) null);
            }
            display('}', (String) null);
        }
        return this;
    }

    public <K, V> JceDisplayer displaySimple(Map<K, V> map, boolean z) {
        if (map == null || map.isEmpty()) {
            this.sb.append("{}");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("{");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 2);
            boolean z2 = true;
            for (Entry entry : map.entrySet()) {
                if (!z2) {
                    this.sb.append(StorageInterface.KEY_SPLITER);
                }
                jceDisplayer.displaySimple((T) entry.getKey(), true);
                jceDisplayer.displaySimple((T) entry.getValue(), false);
                z2 = false;
            }
            this.sb.append("}");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public <T> JceDisplayer display(T[] tArr, String str) {
        ps(str);
        if (tArr == null) {
            this.sb.append("null").append(10);
        } else if (tArr.length == 0) {
            this.sb.append(tArr.length).append(", []").append(10);
        } else {
            this.sb.append(tArr.length).append(", [").append(10);
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (T display : tArr) {
                jceDisplayer.display(display, (String) null);
            }
            display(']', (String) null);
        }
        return this;
    }

    public <T> JceDisplayer displaySimple(T[] tArr, boolean z) {
        if (tArr == null || tArr.length == 0) {
            this.sb.append("[]");
            if (z) {
                this.sb.append("|");
            }
        } else {
            this.sb.append("[");
            JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
            for (int i = 0; i < tArr.length; i++) {
                T t = tArr[i];
                if (i != 0) {
                    this.sb.append("|");
                }
                jceDisplayer.displaySimple(t, false);
            }
            this.sb.append("]");
            if (z) {
                this.sb.append("|");
            }
        }
        return this;
    }

    public <T> JceDisplayer display(Collection<T> collection, String str) {
        if (collection != null) {
            return display((T[]) collection.toArray(), str);
        }
        ps(str);
        this.sb.append("null").append(9);
        return this;
    }

    public <T> JceDisplayer displaySimple(Collection<T> collection, boolean z) {
        if (collection != null) {
            return displaySimple((T[]) collection.toArray(), z);
        }
        this.sb.append("[]");
        if (!z) {
            return this;
        }
        this.sb.append("|");
        return this;
    }

    public <T> JceDisplayer display(T t, String str) {
        if (t == null) {
            this.sb.append("null").append(10);
        } else if (t instanceof Byte) {
            display(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            display(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            display(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            display(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            display(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            display(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            display(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            display((String) t, str);
        } else if (t instanceof Map) {
            display((Map) t, str);
        } else if (t instanceof List) {
            display((Collection<T>) (List) t, str);
        } else if (t instanceof JceStruct) {
            display((JceStruct) t, str);
        } else if (t instanceof byte[]) {
            display((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            display((T) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            display((short[]) t, str);
        } else if (t instanceof int[]) {
            display((int[]) t, str);
        } else if (t instanceof long[]) {
            display((long[]) t, str);
        } else if (t instanceof float[]) {
            display((float[]) t, str);
        } else if (t instanceof double[]) {
            display((double[]) t, str);
        } else if (t.getClass().isArray()) {
            display((T[]) (Object[]) t, str);
        } else {
            throw new JceEncodeException("write object error: unsupport type.");
        }
        return this;
    }

    public <T> JceDisplayer displaySimple(T t, boolean z) {
        if (t == null) {
            this.sb.append("null").append(10);
        } else if (t instanceof Byte) {
            displaySimple(((Byte) t).byteValue(), z);
        } else if (t instanceof Boolean) {
            displaySimple(((Boolean) t).booleanValue(), z);
        } else if (t instanceof Short) {
            displaySimple(((Short) t).shortValue(), z);
        } else if (t instanceof Integer) {
            displaySimple(((Integer) t).intValue(), z);
        } else if (t instanceof Long) {
            displaySimple(((Long) t).longValue(), z);
        } else if (t instanceof Float) {
            displaySimple(((Float) t).floatValue(), z);
        } else if (t instanceof Double) {
            displaySimple(((Double) t).doubleValue(), z);
        } else if (t instanceof String) {
            displaySimple((String) t, z);
        } else if (t instanceof Map) {
            displaySimple((Map) t, z);
        } else if (t instanceof List) {
            displaySimple((Collection<T>) (List) t, z);
        } else if (t instanceof JceStruct) {
            displaySimple((JceStruct) t, z);
        } else if (t instanceof byte[]) {
            displaySimple((byte[]) t, z);
        } else if (t instanceof boolean[]) {
            displaySimple((T) (boolean[]) t, z);
        } else if (t instanceof short[]) {
            displaySimple((short[]) t, z);
        } else if (t instanceof int[]) {
            displaySimple((int[]) t, z);
        } else if (t instanceof long[]) {
            displaySimple((long[]) t, z);
        } else if (t instanceof float[]) {
            displaySimple((float[]) t, z);
        } else if (t instanceof double[]) {
            displaySimple((double[]) t, z);
        } else if (t.getClass().isArray()) {
            displaySimple((T[]) (Object[]) t, z);
        } else {
            throw new JceEncodeException("write object error: unsupport type.");
        }
        return this;
    }

    public JceDisplayer display(JceStruct jceStruct, String str) {
        display('{', str);
        if (jceStruct == null) {
            this.sb.append(9).append("null");
        } else {
            jceStruct.display(this.sb, this._level + 1);
        }
        display('}', (String) null);
        return this;
    }

    public JceDisplayer displaySimple(JceStruct jceStruct, boolean z) {
        this.sb.append("{");
        if (jceStruct == null) {
            this.sb.append(9).append("null");
        } else {
            jceStruct.displaySimple(this.sb, this._level + 1);
        }
        this.sb.append("}");
        if (z) {
            this.sb.append("|");
        }
        return this;
    }

    public static void main(String[] strArr) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(1.2d);
        System.out.println(sb2.toString());
    }
}
