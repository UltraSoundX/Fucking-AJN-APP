package com.bumptech.glide;

import android.graphics.Bitmap;
import com.bumptech.glide.d.c;
import com.bumptech.glide.d.d.a.d;
import com.bumptech.glide.d.d.d.b;
import com.bumptech.glide.d.e;
import com.bumptech.glide.f.f;
import com.bumptech.glide.g.a.a;
import java.io.InputStream;

/* compiled from: GifRequestBuilder */
public class g<ModelType> extends e<ModelType, InputStream, b, b> {
    g(f<ModelType, InputStream, b, b> fVar, Class<b> cls, e<ModelType, ?, ?, ?> eVar) {
        super(fVar, cls, eVar);
    }

    /* renamed from: a */
    public g<ModelType> b(e<InputStream, b> eVar) {
        super.b(eVar);
        return this;
    }

    public g<ModelType> a() {
        return a(this.c.c());
    }

    public g<ModelType> b() {
        return a(this.c.d());
    }

    public g<ModelType> a(d... dVarArr) {
        return b((com.bumptech.glide.d.g<b>[]) c((com.bumptech.glide.d.g<Bitmap>[]) dVarArr));
    }

    private com.bumptech.glide.d.d.d.e[] c(com.bumptech.glide.d.g<Bitmap>[] gVarArr) {
        com.bumptech.glide.d.d.d.e[] eVarArr = new com.bumptech.glide.d.d.d.e[gVarArr.length];
        for (int i = 0; i < gVarArr.length; i++) {
            eVarArr[i] = new com.bumptech.glide.d.d.d.e(gVarArr[i], this.c.a());
        }
        return eVarArr;
    }

    /* renamed from: a */
    public g<ModelType> b(com.bumptech.glide.d.g<b>... gVarArr) {
        super.b((com.bumptech.glide.d.g<ResourceType>[]) gVarArr);
        return this;
    }

    public g<ModelType> c() {
        super.a((com.bumptech.glide.g.a.d<TranscodeType>) new a<TranscodeType>());
        return this;
    }

    /* renamed from: a */
    public g<ModelType> d(int i) {
        super.d(i);
        return this;
    }

    /* renamed from: b */
    public g<ModelType> c(int i) {
        super.c(i);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(boolean z) {
        super.b(z);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(com.bumptech.glide.d.b.b bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(com.bumptech.glide.d.b<InputStream> bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(c cVar) {
        super.b(cVar);
        return this;
    }

    /* renamed from: a */
    public g<ModelType> b(ModelType modeltype) {
        super.b(modeltype);
        return this;
    }

    /* renamed from: g */
    public g<ModelType> f() {
        return (g) super.clone();
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        b();
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        a();
    }
}
