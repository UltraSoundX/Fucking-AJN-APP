package com.bumptech.glide.d.b;

import com.bumptech.glide.d.b;
import com.bumptech.glide.d.c;
import com.bumptech.glide.d.e;
import com.bumptech.glide.d.g;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* compiled from: EngineKey */
class f implements c {
    private final String a;
    private final int b;
    private final int c;
    private final e d;
    private final e e;
    private final g f;
    private final com.bumptech.glide.d.f g;
    private final com.bumptech.glide.d.d.f.c h;
    private final b i;
    private final c j;
    private String k;
    private int l;
    private c m;

    public f(String str, c cVar, int i2, int i3, e eVar, e eVar2, g gVar, com.bumptech.glide.d.f fVar, com.bumptech.glide.d.d.f.c cVar2, b bVar) {
        this.a = str;
        this.j = cVar;
        this.b = i2;
        this.c = i3;
        this.d = eVar;
        this.e = eVar2;
        this.f = gVar;
        this.g = fVar;
        this.h = cVar2;
        this.i = bVar;
    }

    public c a() {
        if (this.m == null) {
            this.m = new j(this.a, this.j);
        }
        return this.m;
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (!this.a.equals(fVar.a) || !this.j.equals(fVar.j) || this.c != fVar.c || this.b != fVar.b) {
            return false;
        }
        if ((this.f == null) ^ (fVar.f == null)) {
            return false;
        }
        if (this.f != null && !this.f.a().equals(fVar.f.a())) {
            return false;
        }
        if (this.e == null) {
            z = true;
        } else {
            z = false;
        }
        if (z ^ (fVar.e == null)) {
            return false;
        }
        if (this.e != null && !this.e.a().equals(fVar.e.a())) {
            return false;
        }
        if (this.d == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 ^ (fVar.d == null)) {
            return false;
        }
        if (this.d != null && !this.d.a().equals(fVar.d.a())) {
            return false;
        }
        if (this.g == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 ^ (fVar.g == null)) {
            return false;
        }
        if (this.g != null && !this.g.a().equals(fVar.g.a())) {
            return false;
        }
        if (this.h == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 ^ (fVar.h == null)) {
            return false;
        }
        if (this.h != null && !this.h.a().equals(fVar.h.a())) {
            return false;
        }
        if (this.i == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 ^ (fVar.i == null)) {
            return false;
        }
        if (this.i == null || this.i.a().equals(fVar.i.a())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (this.l == 0) {
            this.l = this.a.hashCode();
            this.l = (this.l * 31) + this.j.hashCode();
            this.l = (this.l * 31) + this.b;
            this.l = (this.l * 31) + this.c;
            this.l = (this.d != null ? this.d.a().hashCode() : 0) + (this.l * 31);
            int i7 = this.l * 31;
            if (this.e != null) {
                i2 = this.e.a().hashCode();
            } else {
                i2 = 0;
            }
            this.l = i2 + i7;
            int i8 = this.l * 31;
            if (this.f != null) {
                i3 = this.f.a().hashCode();
            } else {
                i3 = 0;
            }
            this.l = i3 + i8;
            int i9 = this.l * 31;
            if (this.g != null) {
                i4 = this.g.a().hashCode();
            } else {
                i4 = 0;
            }
            this.l = i4 + i9;
            int i10 = this.l * 31;
            if (this.h != null) {
                i5 = this.h.a().hashCode();
            } else {
                i5 = 0;
            }
            this.l = i5 + i10;
            int i11 = this.l * 31;
            if (this.i != null) {
                i6 = this.i.a().hashCode();
            }
            this.l = i11 + i6;
        }
        return this.l;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (this.k == null) {
            StringBuilder append = new StringBuilder().append("EngineKey{").append(this.a).append('+').append(this.j).append("+[").append(this.b).append('x').append(this.c).append("]+").append('\'');
            if (this.d != null) {
                str = this.d.a();
            } else {
                str = "";
            }
            StringBuilder append2 = append.append(str).append('\'').append('+').append('\'');
            if (this.e != null) {
                str2 = this.e.a();
            } else {
                str2 = "";
            }
            StringBuilder append3 = append2.append(str2).append('\'').append('+').append('\'');
            if (this.f != null) {
                str3 = this.f.a();
            } else {
                str3 = "";
            }
            StringBuilder append4 = append3.append(str3).append('\'').append('+').append('\'');
            if (this.g != null) {
                str4 = this.g.a();
            } else {
                str4 = "";
            }
            StringBuilder append5 = append4.append(str4).append('\'').append('+').append('\'');
            if (this.h != null) {
                str5 = this.h.a();
            } else {
                str5 = "";
            }
            StringBuilder append6 = append5.append(str5).append('\'').append('+').append('\'');
            if (this.i != null) {
                str6 = this.i.a();
            } else {
                str6 = "";
            }
            this.k = append6.append(str6).append('\'').append('}').toString();
        }
        return this.k;
    }

    public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        byte[] array = ByteBuffer.allocate(8).putInt(this.b).putInt(this.c).array();
        this.j.a(messageDigest);
        messageDigest.update(this.a.getBytes("UTF-8"));
        messageDigest.update(array);
        messageDigest.update((this.d != null ? this.d.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.e != null ? this.e.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.f != null ? this.f.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.g != null ? this.g.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.i != null ? this.i.a() : "").getBytes("UTF-8"));
    }
}
