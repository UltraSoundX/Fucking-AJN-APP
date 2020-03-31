package com.c.a.c.b.b;

import com.c.a.c.a.e;
import com.c.a.c.b.a.d;
import com.c.a.c.b.b.a.b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* compiled from: MultipartEntity */
public class g implements d, HttpEntity {
    private static final char[] b = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private a a;
    private final b c;
    private Header d;
    private long e;
    private volatile boolean f;
    private final String g;
    private final Charset h;
    private String i;

    /* compiled from: MultipartEntity */
    public static class a {
        public static final a a = new a();
        public e b = null;
        public long c = 0;
        public long d = 0;

        public boolean a(boolean z) {
            if (this.b != null) {
                return this.b.a(this.c, this.d, z);
            }
            return true;
        }
    }

    public void a(e eVar) {
        this.a.b = eVar;
    }

    public g(c cVar, String str, Charset charset) {
        this.a = new a();
        this.i = "form-data";
        if (str == null) {
            str = a();
        }
        this.g = str;
        if (cVar == null) {
            cVar = c.STRICT;
        }
        if (charset == null) {
            charset = d.a;
        }
        this.h = charset;
        this.c = new b(this.i, this.h, this.g, cVar);
        this.d = new BasicHeader("Content-Type", a(this.g, this.h));
        this.f = true;
    }

    public g() {
        this(c.STRICT, null, null);
    }

    /* access modifiers changed from: protected */
    public String a(String str, Charset charset) {
        StringBuilder sb = new StringBuilder();
        sb.append("multipart/" + this.i + "; boundary=");
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String a() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        for (int i2 = 0; i2 < nextInt; i2++) {
            sb.append(b[random.nextInt(b.length)]);
        }
        return sb.toString();
    }

    public void a(a aVar) {
        this.c.a(aVar);
        this.f = true;
    }

    public void a(String str, b bVar) {
        a(new a(str, bVar));
    }

    public boolean isRepeatable() {
        for (a b2 : this.c.a()) {
            if (b2.b().e() < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isChunked() {
        return !isRepeatable();
    }

    public boolean isStreaming() {
        return !isRepeatable();
    }

    public long getContentLength() {
        if (this.f) {
            this.e = this.c.c();
            this.f = false;
        }
        return this.e;
    }

    public Header getContentType() {
        return this.d;
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.a.c = getContentLength();
        this.c.a(outputStream, this.a);
    }
}
