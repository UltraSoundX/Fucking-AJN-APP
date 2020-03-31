package com.bumptech.glide.d.d.c;

import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: FileToStreamDecoder */
public class c<T> implements e<File, T> {
    private static final a a = new a();
    private e<InputStream, T> b;
    private final a c;

    /* compiled from: FileToStreamDecoder */
    static class a {
        a() {
        }

        public InputStream a(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }

    public c(e<InputStream, T> eVar) {
        this(eVar, a);
    }

    c(e<InputStream, T> eVar, a aVar) {
        this.b = eVar;
        this.c = aVar;
    }

    public k<T> a(File file, int i, int i2) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.c.a(file);
            k<T> a2 = this.b.a(inputStream, i, i2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return a2;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public String a() {
        return "";
    }
}
