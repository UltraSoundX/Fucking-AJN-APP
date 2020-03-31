package com.bumptech.glide.d.b.b;

import com.bumptech.glide.d.c;
import com.bumptech.glide.i.e;
import com.bumptech.glide.i.h;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: SafeKeyGenerator */
class j {
    private final e<c, String> a = new e<>(1000);

    j() {
    }

    public String a(c cVar) {
        String str;
        synchronized (this.a) {
            str = (String) this.a.b(cVar);
        }
        if (str == null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                cVar.a(instance);
                str = h.a(instance.digest());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            synchronized (this.a) {
                this.a.b(cVar, str);
            }
        }
        return str;
    }
}
