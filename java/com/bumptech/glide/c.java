package com.bumptech.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.d.c.g;
import com.bumptech.glide.d.d.b.b;
import com.bumptech.glide.d.d.e.a;
import com.bumptech.glide.d.e;
import com.bumptech.glide.f.f;
import com.bumptech.glide.g.a.d;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.manager.l;

/* compiled from: DrawableRequestBuilder */
public class c<ModelType> extends e<ModelType, g, a, b> {
    c(Context context, Class<ModelType> cls, f<ModelType, g, a, b> fVar, i iVar, l lVar, com.bumptech.glide.manager.g gVar) {
        super(context, cls, fVar, b.class, iVar, lVar, gVar);
        c();
    }

    /* renamed from: a */
    public c<ModelType> b(e<g, a> eVar) {
        super.b(eVar);
        return this;
    }

    public c<ModelType> a() {
        return b((com.bumptech.glide.d.g<a>[]) new com.bumptech.glide.d.g[]{this.c.e()});
    }

    public c<ModelType> b() {
        return b((com.bumptech.glide.d.g<a>[]) new com.bumptech.glide.d.g[]{this.c.f()});
    }

    /* renamed from: a */
    public c<ModelType> b(com.bumptech.glide.d.g<a>... gVarArr) {
        super.b((com.bumptech.glide.d.g<ResourceType>[]) gVarArr);
        return this;
    }

    public final c<ModelType> c() {
        super.a((d<TranscodeType>) new com.bumptech.glide.g.a.a<TranscodeType>());
        return this;
    }

    /* renamed from: a */
    public c<ModelType> d(int i) {
        super.d(i);
        return this;
    }

    /* renamed from: b */
    public c<ModelType> c(int i) {
        super.c(i);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(com.bumptech.glide.d.b.b bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(boolean z) {
        super.b(z);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(com.bumptech.glide.d.b<g> bVar) {
        super.b(bVar);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(com.bumptech.glide.d.c cVar) {
        super.b(cVar);
        return this;
    }

    /* renamed from: a */
    public c<ModelType> b(ModelType modeltype) {
        super.b(modeltype);
        return this;
    }

    /* renamed from: g */
    public c<ModelType> f() {
        return (c) super.clone();
    }

    public j<b> a(ImageView imageView) {
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
