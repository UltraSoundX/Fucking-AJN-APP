package com.bumptech.glide.d.c.b;

import android.content.Context;
import com.bumptech.glide.d.a.f;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.d;
import com.bumptech.glide.d.c.k;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.InputStream;

/* compiled from: HttpUrlGlideUrlLoader */
public class a implements l<d, InputStream> {
    private final k<d, d> a;

    /* renamed from: com.bumptech.glide.d.c.b.a$a reason: collision with other inner class name */
    /* compiled from: HttpUrlGlideUrlLoader */
    public static class C0043a implements m<d, InputStream> {
        private final k<d, d> a = new k<>(ErrorCode.INFO_CODE_MINIQB);

        public l<d, InputStream> a(Context context, c cVar) {
            return new a(this.a);
        }

        public void a() {
        }
    }

    public a() {
        this(null);
    }

    public a(k<d, d> kVar) {
        this.a = kVar;
    }

    public com.bumptech.glide.d.a.c<InputStream> a(d dVar, int i, int i2) {
        if (this.a != null) {
            d dVar2 = (d) this.a.a(dVar, 0, 0);
            if (dVar2 == null) {
                this.a.a(dVar, 0, 0, dVar);
            } else {
                dVar = dVar2;
            }
        }
        return new f(dVar);
    }
}
