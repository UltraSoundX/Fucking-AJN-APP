package com.bumptech.glide.d.c;

import android.util.Log;
import com.bumptech.glide.d.b;
import com.bumptech.glide.i.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: StreamEncoder */
public class o implements b<InputStream> {
    public boolean a(InputStream inputStream, OutputStream outputStream) {
        byte[] b = a.a().b();
        while (true) {
            try {
                int read = inputStream.read(b);
                if (read != -1) {
                    outputStream.write(b, 0, read);
                } else {
                    return true;
                }
            } catch (IOException e) {
                if (Log.isLoggable("StreamEncoder", 3)) {
                    Log.d("StreamEncoder", "Failed to encode data onto the OutputStream", e);
                }
                return false;
            } finally {
                a.a().a(b);
            }
        }
    }

    public String a() {
        return "";
    }
}
