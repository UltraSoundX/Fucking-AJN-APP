package com.c.a.c.b.b.a;

import com.c.a.e.b;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* compiled from: FileBody */
public class d extends a {
    private final File b;
    private final String c;
    private final String d;

    public d(File file, String str, String str2, String str3) {
        super(str2);
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        this.b = file;
        if (str != null) {
            this.c = str;
        } else {
            this.c = file.getName();
        }
        this.d = str3;
    }

    public d(File file, String str) {
        this(file, null, str, null);
    }

    public void a(OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(this.b));
            try {
                byte[] bArr = new byte[4096];
                do {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        b.a(bufferedInputStream);
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.a.d += (long) read;
                } while (this.a.a(false));
                throw new InterruptedIOException("cancel");
            } catch (Throwable th) {
                th = th;
                b.a(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
            b.a(bufferedInputStream);
            throw th;
        }
    }

    public String d() {
        return "binary";
    }

    public String c() {
        return this.d;
    }

    public long e() {
        return this.b.length();
    }

    public String b() {
        return this.c;
    }
}
