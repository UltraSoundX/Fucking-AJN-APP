package com.c.a.c.b.a;

import com.c.a.c.a.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* compiled from: GZipDecompressingEntity */
public class c extends b {
    public /* bridge */ /* synthetic */ void a(e eVar) {
        super.a(eVar);
    }

    public /* bridge */ /* synthetic */ InputStream getContent() throws IOException {
        return super.getContent();
    }

    public /* bridge */ /* synthetic */ long getContentLength() {
        return super.getContentLength();
    }

    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }

    public c(HttpEntity httpEntity) {
        super(httpEntity);
    }

    /* access modifiers changed from: 0000 */
    public InputStream a(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }

    public Header getContentEncoding() {
        return null;
    }
}
