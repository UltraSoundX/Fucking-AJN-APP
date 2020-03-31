package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.d.c;
import com.bumptech.glide.f.a;
import com.bumptech.glide.f.f;
import com.bumptech.glide.g.b.j;
import com.bumptech.glide.g.d;
import com.bumptech.glide.i.h;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.l;

/* compiled from: GenericRequestBuilder */
public class e<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final i c;
    protected final Class<TranscodeType> d;
    protected final l e;
    protected final g f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private c i;
    private boolean j;
    private int k;
    private int l;
    private d<? super ModelType, TranscodeType> m;
    private Float n;
    private e<?, ?, ?, TranscodeType> o;
    private Float p;

    /* renamed from: q reason: collision with root package name */
    private Drawable f392q;
    private Drawable r;
    private k s;
    private boolean t;
    private com.bumptech.glide.g.a.d<TranscodeType> u;
    private int v;
    private int w;
    private b x;
    private com.bumptech.glide.d.g<ResourceType> y;
    private boolean z;

    /* renamed from: com.bumptech.glide.e$1 reason: invalid class name */
    /* compiled from: GenericRequestBuilder */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    e(f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls, e<ModelType, ?, ?, ?> eVar) {
        this(eVar.b, eVar.a, fVar, cls, eVar.c, eVar.e, eVar.f);
        this.h = eVar.h;
        this.j = eVar.j;
        this.i = eVar.i;
        this.x = eVar.x;
        this.t = eVar.t;
    }

    e(Context context, Class<ModelType> cls, f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, i iVar, l lVar, g gVar) {
        a<ModelType, DataType, ResourceType, TranscodeType> aVar = null;
        this.i = com.bumptech.glide.h.a.a();
        this.p = Float.valueOf(1.0f);
        this.s = null;
        this.t = true;
        this.u = com.bumptech.glide.g.a.e.a();
        this.v = -1;
        this.w = -1;
        this.x = b.RESULT;
        this.y = com.bumptech.glide.d.d.d.b();
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = iVar;
        this.e = lVar;
        this.f = gVar;
        if (fVar != null) {
            aVar = new a<>(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.d.e<DataType, ResourceType> eVar) {
        if (this.g != null) {
            this.g.a(eVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.d.b<DataType> bVar) {
        if (this.g != null) {
            this.g.a(bVar);
        }
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        this.x = bVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.d.g<ResourceType>... gVarArr) {
        this.z = true;
        if (gVarArr.length == 1) {
            this.y = gVarArr[0];
        } else {
            this.y = new com.bumptech.glide.d.d(gVarArr);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public e<ModelType, DataType, ResourceType, TranscodeType> a(com.bumptech.glide.g.a.d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> d(int i2) {
        this.k = i2;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> c(int i2) {
        this.l = i2;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(boolean z2) {
        this.t = !z2;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(int i2, int i3) {
        if (!h.a(i2, i3)) {
            throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
        }
        this.w = i2;
        this.v = i3;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = cVar;
        return this;
    }

    public e<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modeltype) {
        this.h = modeltype;
        this.j = true;
        return this;
    }

    /* renamed from: f */
    public e<ModelType, DataType, ResourceType, TranscodeType> clone() {
        try {
            e<ModelType, DataType, ResourceType, TranscodeType> eVar = (e) super.clone();
            eVar.g = this.g != null ? this.g.clone() : null;
            return eVar;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public <Y extends j<TranscodeType>> Y a(Y y2) {
        h.a();
        if (y2 == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (!this.j) {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        } else {
            com.bumptech.glide.g.b c2 = y2.c();
            if (c2 != null) {
                c2.d();
                this.e.b(c2);
                c2.a();
            }
            com.bumptech.glide.g.b b2 = b((j<TranscodeType>) y2);
            y2.a(b2);
            this.f.a(y2);
            this.e.a(b2);
            return y2;
        }
    }

    public j<TranscodeType> a(ImageView imageView) {
        h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        if (!this.z && imageView.getScaleType() != null) {
            switch (AnonymousClass1.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    e();
                    break;
                case 2:
                case 3:
                case 4:
                    d();
                    break;
            }
        }
        return a((Y) this.c.a(imageView, this.d));
    }

    /* access modifiers changed from: 0000 */
    public void e() {
    }

    /* access modifiers changed from: 0000 */
    public void d() {
    }

    private k a() {
        if (this.s == k.LOW) {
            return k.NORMAL;
        }
        if (this.s == k.NORMAL) {
            return k.HIGH;
        }
        return k.IMMEDIATE;
    }

    private com.bumptech.glide.g.b b(j<TranscodeType> jVar) {
        if (this.s == null) {
            this.s = k.NORMAL;
        }
        return a(jVar, null);
    }

    private com.bumptech.glide.g.b a(j<TranscodeType> jVar, com.bumptech.glide.g.f fVar) {
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(com.bumptech.glide.g.a.e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (h.a(this.w, this.v) && !h.a(this.o.w, this.o.v)) {
                this.o.b(this.w, this.v);
            }
            com.bumptech.glide.g.f fVar2 = new com.bumptech.glide.g.f(fVar);
            com.bumptech.glide.g.b a2 = a(jVar, this.p.floatValue(), this.s, fVar2);
            this.A = true;
            com.bumptech.glide.g.b a3 = this.o.a(jVar, fVar2);
            this.A = false;
            fVar2.a(a2, a3);
            return fVar2;
        } else if (this.n == null) {
            return a(jVar, this.p.floatValue(), this.s, fVar);
        } else {
            com.bumptech.glide.g.f fVar3 = new com.bumptech.glide.g.f(fVar);
            fVar3.a(a(jVar, this.p.floatValue(), this.s, fVar3), a(jVar, this.n.floatValue(), a(), fVar3));
            return fVar3;
        }
    }

    private com.bumptech.glide.g.b a(j<TranscodeType> jVar, float f2, k kVar, com.bumptech.glide.g.c cVar) {
        return com.bumptech.glide.g.a.a(this.g, this.h, this.i, this.b, kVar, jVar, f2, this.f392q, this.k, this.r, this.l, this.B, this.C, this.m, cVar, this.c.b(), this.y, this.d, this.t, this.u, this.w, this.v, this.x);
    }
}
