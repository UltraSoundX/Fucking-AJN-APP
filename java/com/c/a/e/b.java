package com.c.a.e;

import java.io.Closeable;

/* compiled from: IOUtils */
public class b {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }
}
