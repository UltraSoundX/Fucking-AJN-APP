package com.bumptech.glide;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.bumptech.glide.d.b.a.c;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.d.a.d;
import com.bumptech.glide.d.d.a.f;
import com.bumptech.glide.d.d.a.h;
import com.bumptech.glide.d.d.a.q;
import com.bumptech.glide.d.e;
import com.bumptech.glide.g.b.j;
import java.io.InputStream;

/* compiled from: BitmapRequestBuilder */
public class a<ModelType, TranscodeType> extends e<ModelType, g, Bitmap, TranscodeType> {
    private final c g;
    private f h = f.a;
    private com.bumptech.glide.d.a i;
    private e<InputStream, Bitmap> j;
    private e<ParcelFileDescriptor, Bitmap> k;

    a(com.bumptech.glide.f.f<ModelType, g, Bitmap, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        super(fVar, cls, eVar);
        this.g = eVar.c.a();
        this.i = eVar.c.g();
        this.j = new q(this.g, this.i);
        this.k = new h(this.g, this.i);
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(e<g, Bitmap> eVar) {
        super.b(eVar);
        return this;
    }

    public a<ModelType, TranscodeType> a(d... dVarArr) {
        super.b((com.bumptech.glide.d.g<ResourceType>[]) dVarArr);
        return this;
    }

    public a<ModelType, TranscodeType> a() {
        return a(this.c.c());
    }

    public a<ModelType, TranscodeType> b() {
        return a(this.c.d());
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(com.bumptech.glide.d.g<Bitmap>... gVarArr) {
        super.b((com.bumptech.glide.d.g<ResourceType>[]) gVarArr);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> d(int i2) {
        super.d(i2);
        return this;
    }

    /* renamed from: b */
    public a<ModelType, TranscodeType> c(int i2) {
        super.c(i2);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(boolean z) {
        super.b(z);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(b bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(int i2, int i3) {
        super.b(i2, i3);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(com.bumptech.glide.d.b<g> bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(com.bumptech.glide.d.c cVar) {
        super.b(cVar);
        return this;
    }

    /* renamed from: a */
    public a<ModelType, TranscodeType> b(ModelType modeltype) {
        super.b(modeltype);
        return this;
    }

    /* renamed from: c */
    public a<ModelType, TranscodeType> f() {
        return (a) super.clone();
    }

    public j<TranscodeType> a(ImageView imageView) {
        return super.a(imageView);
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
