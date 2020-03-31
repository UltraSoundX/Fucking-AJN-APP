package com.c.a.c.b.a;

import com.c.a.c.b.c.b;
import com.c.a.e.c;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.AbstractHttpEntity;

/* compiled from: BodyParamsEntity */
public class a extends AbstractHttpEntity implements Cloneable {
    protected byte[] a;
    private boolean b;
    private String c;
    private List<NameValuePair> d;

    public a() {
        this(null);
    }

    public a(String str) {
        this.b = true;
        this.c = "UTF-8";
        if (str != null) {
            this.c = str;
        }
        setContentType("application/x-www-form-urlencoded");
        this.d = new ArrayList();
    }

    public a(List<NameValuePair> list, String str) {
        this.b = true;
        this.c = "UTF-8";
        if (str != null) {
            this.c = str;
        }
        setContentType("application/x-www-form-urlencoded");
        this.d = list;
        a();
    }

    private void a() {
        if (this.b) {
            try {
                this.a = b.a(this.d, this.c).getBytes(this.c);
            } catch (UnsupportedEncodingException e) {
                c.a(e.getMessage(), e);
            }
            this.b = false;
        }
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        a();
        return (long) this.a.length;
    }

    public InputStream getContent() throws IOException {
        a();
        return new ByteArrayInputStream(this.a);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        a();
        outputStream.write(this.a);
        outputStream.flush();
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
