package com.c.a.c.b.a;

import com.c.a.c.a.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

/* compiled from: DecompressingEntity */
abstract class b extends HttpEntityWrapper implements d {
    private InputStream a;
    private long b;
    private long c = 0;
    private e d = null;

    /* access modifiers changed from: 0000 */
    public abstract InputStream a(InputStream inputStream) throws IOException;

    public b(HttpEntity httpEntity) {
        super(httpEntity);
        this.b = httpEntity.getContentLength();
    }

    private InputStream a() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.wrappedEntity.getContent();
            return a(inputStream);
        } catch (IOException e) {
            com.c.a.e.b.a(inputStream);
            throw e;
        }
    }

    public InputStream getContent() throws IOException {
        if (!this.wrappedEntity.isStreaming()) {
            return a();
        }
        if (this.a == null) {
            this.a = a();
        }
        return this.a;
    }

    public long getContentLength() {
        return -1;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream inputStream = null;
        try {
            InputStream content = getContent();
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        if (this.d != null) {
                            this.d.a(this.b, this.c, true);
                        }
                        com.c.a.e.b.a(content);
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.c += (long) read;
                    if (this.d != null && !this.d.a(this.b, this.c, false)) {
                        throw new InterruptedIOException("cancel");
                    }
                }
            } catch (Throwable th) {
                th = th;
                inputStream = content;
                com.c.a.e.b.a(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            com.c.a.e.b.a(inputStream);
            throw th;
        }
    }

    public void a(e eVar) {
        this.d = eVar;
    }
}
