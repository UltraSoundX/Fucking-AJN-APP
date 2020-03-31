package com.b.a.b.a;

import com.b.a.d.a;
import com.b.a.d.b;
import com.b.a.g;
import com.b.a.j;
import com.b.a.l;
import com.b.a.m;
import com.b.a.o;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: JsonTreeReader */
public final class e extends a {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d = new Object[32];
    private int e = 0;
    private String[] f = new String[32];
    private int[] g = new int[32];

    public e(j jVar) {
        super(b);
        a((Object) jVar);
    }

    public void a() throws IOException {
        a(b.BEGIN_ARRAY);
        a((Object) ((g) s()).iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() throws IOException {
        a(b.END_ARRAY);
        t();
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void c() throws IOException {
        a(b.BEGIN_OBJECT);
        a((Object) ((m) s()).o().iterator());
    }

    public void d() throws IOException {
        a(b.END_OBJECT);
        t();
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean e() throws IOException {
        b f2 = f();
        return (f2 == b.END_OBJECT || f2 == b.END_ARRAY) ? false : true;
    }

    public b f() throws IOException {
        if (this.e == 0) {
            return b.END_DOCUMENT;
        }
        Object s = s();
        if (s instanceof Iterator) {
            boolean z = this.d[this.e - 2] instanceof m;
            Iterator it = (Iterator) s;
            if (!it.hasNext()) {
                return z ? b.END_OBJECT : b.END_ARRAY;
            }
            if (z) {
                return b.NAME;
            }
            a(it.next());
            return f();
        } else if (s instanceof m) {
            return b.BEGIN_OBJECT;
        } else {
            if (s instanceof g) {
                return b.BEGIN_ARRAY;
            }
            if (s instanceof o) {
                o oVar = (o) s;
                if (oVar.q()) {
                    return b.STRING;
                }
                if (oVar.o()) {
                    return b.BOOLEAN;
                }
                if (oVar.p()) {
                    return b.NUMBER;
                }
                throw new AssertionError();
            } else if (s instanceof l) {
                return b.NULL;
            } else {
                if (s == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object s() {
        return this.d[this.e - 1];
    }

    private Object t() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        this.d[this.e] = null;
        return obj;
    }

    private void a(b bVar) throws IOException {
        if (f() != bVar) {
            throw new IllegalStateException("Expected " + bVar + " but was " + f() + u());
        }
    }

    public String g() throws IOException {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) s()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public String h() throws IOException {
        b f2 = f();
        if (f2 == b.STRING || f2 == b.NUMBER) {
            String b2 = ((o) t()).b();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b2;
        }
        throw new IllegalStateException("Expected " + b.STRING + " but was " + f2 + u());
    }

    public boolean i() throws IOException {
        a(b.BOOLEAN);
        boolean f2 = ((o) t()).f();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f2;
    }

    public void j() throws IOException {
        a(b.NULL);
        t();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double k() throws IOException {
        b f2 = f();
        if (f2 == b.NUMBER || f2 == b.STRING) {
            double c2 = ((o) s()).c();
            if (q() || (!Double.isNaN(c2) && !Double.isInfinite(c2))) {
                t();
                if (this.e > 0) {
                    int[] iArr = this.g;
                    int i = this.e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c2;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c2);
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f2 + u());
    }

    public long l() throws IOException {
        b f2 = f();
        if (f2 == b.NUMBER || f2 == b.STRING) {
            long d2 = ((o) s()).d();
            t();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d2;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f2 + u());
    }

    public int m() throws IOException {
        b f2 = f();
        if (f2 == b.NUMBER || f2 == b.STRING) {
            int e2 = ((o) s()).e();
            t();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e2;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f2 + u());
    }

    public void close() throws IOException {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public void n() throws IOException {
        if (f() == b.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            t();
            this.f[this.e - 1] = "null";
        }
        int[] iArr = this.g;
        int i = this.e - 1;
        iArr[i] = iArr[i] + 1;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() throws IOException {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) s()).next();
        a(entry.getValue());
        a((Object) new o((String) entry.getKey()));
    }

    private void a(Object obj) {
        if (this.e == this.d.length) {
            Object[] objArr = new Object[(this.e * 2)];
            int[] iArr = new int[(this.e * 2)];
            String[] strArr = new String[(this.e * 2)];
            System.arraycopy(this.d, 0, objArr, 0, this.e);
            System.arraycopy(this.g, 0, iArr, 0, this.e);
            System.arraycopy(this.f, 0, strArr, 0, this.e);
            this.d = objArr;
            this.g = iArr;
            this.f = strArr;
        }
        Object[] objArr2 = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr2[i] = obj;
    }

    public String p() {
        StringBuilder append = new StringBuilder().append('$');
        int i = 0;
        while (i < this.e) {
            if (this.d[i] instanceof g) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    append.append('[').append(this.g[i]).append(']');
                }
            } else if (this.d[i] instanceof m) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    append.append('.');
                    if (this.f[i] != null) {
                        append.append(this.f[i]);
                    }
                }
            }
            i++;
        }
        return append.toString();
    }

    private String u() {
        return " at path " + p();
    }
}
