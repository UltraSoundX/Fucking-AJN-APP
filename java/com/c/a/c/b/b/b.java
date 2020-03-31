package com.c.a.c.b.b;

import com.c.a.c.b.b.g.a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.util.ByteArrayBuffer;

/* compiled from: HttpMultipart */
class b {
    private static final ByteArrayBuffer a = a(d.a, ": ");
    private static final ByteArrayBuffer b = a(d.a, "\r\n");
    private static final ByteArrayBuffer c = a(d.a, "--");
    private static /* synthetic */ int[] i;
    private String d;
    private final Charset e;
    private final String f;
    private final List<a> g;
    private final c h;

    static /* synthetic */ int[] d() {
        int[] iArr = i;
        if (iArr == null) {
            iArr = new int[c.values().length];
            try {
                iArr[c.BROWSER_COMPATIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[c.STRICT.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            i = iArr;
        }
        return iArr;
    }

    private static ByteArrayBuffer a(Charset charset, String str) {
        ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encode.remaining());
        byteArrayBuffer.append(encode.array(), encode.position(), encode.remaining());
        return byteArrayBuffer;
    }

    private static void a(ByteArrayBuffer byteArrayBuffer, OutputStream outputStream) throws IOException {
        outputStream.write(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
        outputStream.flush();
    }

    private static void a(String str, Charset charset, OutputStream outputStream) throws IOException {
        a(a(charset, str), outputStream);
    }

    private static void a(String str, OutputStream outputStream) throws IOException {
        a(a(d.a, str), outputStream);
    }

    private static void a(e eVar, OutputStream outputStream) throws IOException {
        a(eVar.a(), outputStream);
        a(a, outputStream);
        a(eVar.b(), outputStream);
        a(b, outputStream);
    }

    private static void a(e eVar, Charset charset, OutputStream outputStream) throws IOException {
        a(eVar.a(), charset, outputStream);
        a(a, outputStream);
        a(eVar.b(), charset, outputStream);
        a(b, outputStream);
    }

    public b(String str, Charset charset, String str2, c cVar) {
        if (str == null) {
            throw new IllegalArgumentException("Multipart subtype may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Multipart boundary may not be null");
        } else {
            this.d = str;
            if (charset == null) {
                charset = d.a;
            }
            this.e = charset;
            this.f = str2;
            this.g = new ArrayList();
            this.h = cVar;
        }
    }

    public List<a> a() {
        return this.g;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.g.add(aVar);
        }
    }

    public String b() {
        return this.f;
    }

    private void a(c cVar, OutputStream outputStream, boolean z) throws IOException {
        a(cVar, outputStream, a.a, z);
    }

    private void a(c cVar, OutputStream outputStream, a aVar, boolean z) throws IOException {
        aVar.d = 0;
        ByteArrayBuffer a2 = a(this.e, b());
        for (a aVar2 : this.g) {
            if (!aVar.a(true)) {
                throw new InterruptedIOException("cancel");
            }
            a(c, outputStream);
            aVar.d += (long) c.length();
            a(a2, outputStream);
            aVar.d += (long) a2.length();
            a(b, outputStream);
            aVar.d += (long) b.length();
            f c2 = aVar2.c();
            switch (d()[cVar.ordinal()]) {
                case 1:
                    Iterator it = c2.iterator();
                    while (it.hasNext()) {
                        e eVar = (e) it.next();
                        a(eVar, outputStream);
                        aVar.d += (long) (a(d.a, eVar.a() + eVar.b()).length() + a.length() + b.length());
                    }
                    break;
                case 2:
                    e a3 = c2.a("Content-Disposition");
                    a(a3, this.e, outputStream);
                    aVar.d = ((long) (a(this.e, a3.a() + a3.b()).length() + a.length() + b.length())) + aVar.d;
                    if (aVar2.b().b() != null) {
                        e a4 = c2.a("Content-Type");
                        a(a4, this.e, outputStream);
                        aVar.d += (long) (a(this.e, a4.a() + a4.b()).length() + a.length() + b.length());
                        break;
                    }
                    break;
            }
            a(b, outputStream);
            aVar.d += (long) b.length();
            if (z) {
                com.c.a.c.b.b.a.b b2 = aVar2.b();
                b2.a(aVar);
                b2.a(outputStream);
            }
            a(b, outputStream);
            aVar.d += (long) b.length();
        }
        a(c, outputStream);
        aVar.d += (long) c.length();
        a(a2, outputStream);
        aVar.d += (long) a2.length();
        a(c, outputStream);
        aVar.d += (long) c.length();
        a(b, outputStream);
        aVar.d += (long) b.length();
        aVar.a(true);
    }

    public void a(OutputStream outputStream, a aVar) throws IOException {
        a(this.h, outputStream, aVar, true);
    }

    public long c() {
        long j = 0;
        for (a b2 : this.g) {
            long e2 = b2.b().e();
            if (e2 < 0) {
                return -1;
            }
            j = e2 + j;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(this.h, (OutputStream) byteArrayOutputStream, false);
            return ((long) byteArrayOutputStream.toByteArray().length) + j;
        } catch (Throwable th) {
            return -1;
        }
    }
}
