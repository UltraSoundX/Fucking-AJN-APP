package com.c.a.c.b.b.a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StringBody */
public class e extends a {
    private final byte[] b;
    private final Charset c;

    public e(String str, String str2, Charset charset) throws UnsupportedEncodingException {
        super(str2);
        if (str == null) {
            throw new IllegalArgumentException("Text may not be null");
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        this.b = str.getBytes(charset.name());
        this.c = charset;
    }

    public e(String str) throws UnsupportedEncodingException {
        this(str, "text/plain", null);
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.b);
        byte[] bArr = new byte[4096];
        do {
            int read = byteArrayInputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return;
            }
            outputStream.write(bArr, 0, read);
            this.a.d += (long) read;
        } while (this.a.a(false));
        throw new InterruptedIOException("cancel");
    }

    public String d() {
        return "8bit";
    }

    public String c() {
        return this.c.name();
    }

    public long e() {
        return (long) this.b.length;
    }

    public String b() {
        return null;
    }
}
