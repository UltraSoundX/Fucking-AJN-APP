package com.b.a.b;

import com.b.a.d.c;
import com.b.a.d.d;
import com.b.a.k;
import com.b.a.l;
import com.b.a.n;
import com.b.a.r;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* compiled from: Streams */
public final class j {

    /* compiled from: Streams */
    private static final class a extends Writer {
        private final Appendable a;
        private final C0019a b = new C0019a();

        /* renamed from: com.b.a.b.j$a$a reason: collision with other inner class name */
        /* compiled from: Streams */
        static class C0019a implements CharSequence {
            char[] a;

            C0019a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.b.a = cArr;
            this.a.append(this.b, i, i + i2);
        }

        public void write(int i) throws IOException {
            this.a.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static com.b.a.j a(com.b.a.d.a aVar) throws n {
        boolean z = true;
        try {
            aVar.f();
            z = false;
            return (com.b.a.j) com.b.a.b.a.n.X.b(aVar);
        } catch (EOFException e) {
            if (z) {
                return l.a;
            }
            throw new r((Throwable) e);
        } catch (d e2) {
            throw new r((Throwable) e2);
        } catch (IOException e3) {
            throw new k((Throwable) e3);
        } catch (NumberFormatException e4) {
            throw new r((Throwable) e4);
        }
    }

    public static void a(com.b.a.j jVar, c cVar) throws IOException {
        com.b.a.b.a.n.X.a(cVar, jVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
