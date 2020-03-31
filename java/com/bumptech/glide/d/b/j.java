package com.bumptech.glide.d.b;

import com.bumptech.glide.d.c;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* compiled from: OriginalKey */
class j implements c {
    private final String a;
    private final c b;

    public j(String str, c cVar) {
        this.a = str;
        this.b = cVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (!this.a.equals(jVar.a)) {
            return false;
        }
        if (!this.b.equals(jVar.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.a.getBytes("UTF-8"));
        this.b.a(messageDigest);
    }
}
