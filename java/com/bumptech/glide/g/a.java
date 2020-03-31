package com.bumptech.glide.g;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.d.b.c.C0041c;
import com.bumptech.glide.d.b.k;
import com.bumptech.glide.d.c;
import com.bumptech.glide.d.g;
import com.bumptech.glide.f.f;
import com.bumptech.glide.g.a.d;
import com.bumptech.glide.g.b.h;
import com.bumptech.glide.g.b.j;
import com.stub.StubApp;
import java.util.Queue;
import net.sf.json.util.JSONUtils;

/* compiled from: GenericRequest */
public final class a<A, T, Z, R> implements b, h, e {
    private static final Queue<a<?, ?, ?, ?>> a = com.bumptech.glide.i.h.a(0);
    private k<?> A;
    private C0041c B;
    private long C;
    private C0044a D;
    private final String b = String.valueOf(hashCode());
    private c c;
    private Drawable d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private g<Z> i;
    private f<A, T, Z, R> j;
    private c k;
    private A l;
    private Class<R> m;
    private boolean n;
    private com.bumptech.glide.k o;
    private j<R> p;

    /* renamed from: q reason: collision with root package name */
    private d<? super A, R> f393q;
    private float r;
    private com.bumptech.glide.d.b.c s;
    private d<R> t;
    private int u;
    private int v;
    private b w;
    private Drawable x;
    private Drawable y;
    private boolean z;

    /* renamed from: com.bumptech.glide.g.a$a reason: collision with other inner class name */
    /* compiled from: GenericRequest */
    private enum C0044a {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <A, T, Z, R> a<A, T, Z, R> a(f<A, T, Z, R> fVar, A a2, c cVar, Context context, com.bumptech.glide.k kVar, j<R> jVar, float f2, Drawable drawable, int i2, Drawable drawable2, int i3, Drawable drawable3, int i4, d<? super A, R> dVar, c cVar2, com.bumptech.glide.d.b.c cVar3, g<Z> gVar, Class<R> cls, boolean z2, d<R> dVar2, int i5, int i6, b bVar) {
        a<A, T, Z, R> aVar = (a) a.poll();
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.b(fVar, a2, cVar, context, kVar, jVar, f2, drawable, i2, drawable2, i3, drawable3, i4, dVar, cVar2, cVar3, gVar, cls, z2, dVar2, i5, i6, bVar);
        return aVar;
    }

    private a() {
    }

    public void a() {
        this.j = null;
        this.l = null;
        this.h = null;
        this.p = null;
        this.x = null;
        this.y = null;
        this.d = null;
        this.f393q = null;
        this.k = null;
        this.i = null;
        this.t = null;
        this.z = false;
        this.B = null;
        a.offer(this);
    }

    private void b(f<A, T, Z, R> fVar, A a2, c cVar, Context context, com.bumptech.glide.k kVar, j<R> jVar, float f2, Drawable drawable, int i2, Drawable drawable2, int i3, Drawable drawable3, int i4, d<? super A, R> dVar, c cVar2, com.bumptech.glide.d.b.c cVar3, g<Z> gVar, Class<R> cls, boolean z2, d<R> dVar2, int i5, int i6, b bVar) {
        this.j = fVar;
        this.l = a2;
        this.c = cVar;
        this.d = drawable3;
        this.e = i4;
        this.h = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.o = kVar;
        this.p = jVar;
        this.r = f2;
        this.x = drawable;
        this.f = i2;
        this.y = drawable2;
        this.g = i3;
        this.f393q = dVar;
        this.k = cVar2;
        this.s = cVar3;
        this.i = gVar;
        this.m = cls;
        this.n = z2;
        this.t = dVar2;
        this.u = i5;
        this.v = i6;
        this.w = bVar;
        this.D = C0044a.PENDING;
        if (a2 != null) {
            a("ModelLoader", fVar.e(), "try .using(ModelLoader)");
            a("Transcoder", fVar.f(), "try .as*(Class).transcode(ResourceTranscoder)");
            a("Transformation", gVar, "try .transform(UnitTransformation.get())");
            if (bVar.a()) {
                a("SourceEncoder", fVar.c(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                a("SourceDecoder", fVar.b(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (bVar.a() || bVar.b()) {
                a("CacheDecoder", fVar.a(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (bVar.b()) {
                a("Encoder", fVar.d(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
    }

    private static void a(String str, Object obj, String str2) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(" must not be null");
            if (str2 != null) {
                sb.append(", ");
                sb.append(str2);
            }
            throw new NullPointerException(sb.toString());
        }
    }

    public void b() {
        this.C = com.bumptech.glide.i.d.a();
        if (this.l == null) {
            a((Exception) null);
            return;
        }
        this.D = C0044a.WAITING_FOR_SIZE;
        if (com.bumptech.glide.i.h.a(this.u, this.v)) {
            a(this.u, this.v);
        } else {
            this.p.a((h) this);
        }
        if (!g() && !j() && o()) {
            this.p.c(m());
        }
        if (Log.isLoggable("GenericRequest", 2)) {
            a("finished run method in " + com.bumptech.glide.i.d.a(this.C));
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.D = C0044a.CANCELLED;
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    public void d() {
        com.bumptech.glide.i.h.a();
        if (this.D != C0044a.CLEARED) {
            c();
            if (this.A != null) {
                b((k) this.A);
            }
            if (o()) {
                this.p.b(m());
            }
            this.D = C0044a.CLEARED;
        }
    }

    public void e() {
        d();
        this.D = C0044a.PAUSED;
    }

    private void b(k kVar) {
        this.s.a(kVar);
        this.A = null;
    }

    public boolean f() {
        return this.D == C0044a.RUNNING || this.D == C0044a.WAITING_FOR_SIZE;
    }

    public boolean g() {
        return this.D == C0044a.COMPLETE;
    }

    public boolean h() {
        return g();
    }

    public boolean i() {
        return this.D == C0044a.CANCELLED || this.D == C0044a.CLEARED;
    }

    public boolean j() {
        return this.D == C0044a.FAILED;
    }

    private Drawable k() {
        if (this.d == null && this.e > 0) {
            this.d = this.h.getResources().getDrawable(this.e);
        }
        return this.d;
    }

    private void b(Exception exc) {
        if (o()) {
            Drawable drawable = this.l == null ? k() : null;
            if (drawable == null) {
                drawable = l();
            }
            if (drawable == null) {
                drawable = m();
            }
            this.p.a(exc, drawable);
        }
    }

    private Drawable l() {
        if (this.y == null && this.g > 0) {
            this.y = this.h.getResources().getDrawable(this.g);
        }
        return this.y;
    }

    private Drawable m() {
        if (this.x == null && this.f > 0) {
            this.x = this.h.getResources().getDrawable(this.f);
        }
        return this.x;
    }

    public void a(int i2, int i3) {
        if (Log.isLoggable("GenericRequest", 2)) {
            a("Got onSizeReady in " + com.bumptech.glide.i.d.a(this.C));
        }
        if (this.D == C0044a.WAITING_FOR_SIZE) {
            this.D = C0044a.RUNNING;
            int round = Math.round(this.r * ((float) i2));
            int round2 = Math.round(this.r * ((float) i3));
            com.bumptech.glide.d.a.c a2 = this.j.e().a(this.l, round, round2);
            if (a2 == null) {
                a(new Exception("Failed to load model: '" + this.l + JSONUtils.SINGLE_QUOTE));
                return;
            }
            com.bumptech.glide.d.d.f.c f2 = this.j.f();
            if (Log.isLoggable("GenericRequest", 2)) {
                a("finished setup for calling load in " + com.bumptech.glide.i.d.a(this.C));
            }
            this.z = true;
            this.B = this.s.a(this.c, round, round2, a2, this.j, this.i, f2, this.o, this.n, this.w, this);
            this.z = this.A != null;
            if (Log.isLoggable("GenericRequest", 2)) {
                a("finished onSizeReady in " + com.bumptech.glide.i.d.a(this.C));
            }
        }
    }

    private boolean n() {
        return this.k == null || this.k.a(this);
    }

    private boolean o() {
        return this.k == null || this.k.b(this);
    }

    private boolean p() {
        return this.k == null || !this.k.c();
    }

    private void q() {
        if (this.k != null) {
            this.k.c(this);
        }
    }

    public void a(k<?> kVar) {
        if (kVar == null) {
            a(new Exception("Expected to receive a Resource<R> with an object of " + this.m + " inside, but instead got null."));
            return;
        }
        Object b2 = kVar.b();
        if (b2 == null || !this.m.isAssignableFrom(b2.getClass())) {
            b((k) kVar);
            a(new Exception("Expected to receive an object of " + this.m + " but instead got " + (b2 != null ? b2.getClass() : "") + "{" + b2 + "}" + " inside Resource{" + kVar + "}." + (b2 != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.")));
        } else if (!n()) {
            b((k) kVar);
            this.D = C0044a.COMPLETE;
        } else {
            a(kVar, (R) b2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
        if (r6.f393q.a(r8, r6.l, r6.p, r6.z, r5) == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.bumptech.glide.d.b.k<?> r7, R r8) {
        /*
            r6 = this;
            boolean r5 = r6.p()
            com.bumptech.glide.g.a$a r0 = com.bumptech.glide.g.a.C0044a.COMPLETE
            r6.D = r0
            r6.A = r7
            com.bumptech.glide.g.d<? super A, R> r0 = r6.f393q
            if (r0 == 0) goto L_0x001d
            com.bumptech.glide.g.d<? super A, R> r0 = r6.f393q
            A r2 = r6.l
            com.bumptech.glide.g.b.j<R> r3 = r6.p
            boolean r4 = r6.z
            r1 = r8
            boolean r0 = r0.a(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x002a
        L_0x001d:
            com.bumptech.glide.g.a.d<R> r0 = r6.t
            boolean r1 = r6.z
            com.bumptech.glide.g.a.c r0 = r0.a(r1, r5)
            com.bumptech.glide.g.b.j<R> r1 = r6.p
            r1.a(r8, r0)
        L_0x002a:
            r6.q()
            java.lang.String r0 = "GenericRequest"
            r1 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r1)
            if (r0 == 0) goto L_0x0070
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Resource ready in "
            java.lang.StringBuilder r0 = r0.append(r1)
            long r2 = r6.C
            double r2 = com.bumptech.glide.i.d.a(r2)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " size: "
            java.lang.StringBuilder r0 = r0.append(r1)
            int r1 = r7.c()
            double r2 = (double) r1
            r4 = 4517110426252607488(0x3eb0000000000000, double:9.5367431640625E-7)
            double r2 = r2 * r4
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " fromCache: "
            java.lang.StringBuilder r0 = r0.append(r1)
            boolean r1 = r6.z
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.a(r0)
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.g.a.a(com.bumptech.glide.d.b.k, java.lang.Object):void");
    }

    public void a(Exception exc) {
        if (Log.isLoggable("GenericRequest", 3)) {
            Log.d("GenericRequest", "load failed", exc);
        }
        this.D = C0044a.FAILED;
        if (this.f393q == null || !this.f393q.a(exc, this.l, this.p, p())) {
            b(exc);
        }
    }

    private void a(String str) {
        Log.v("GenericRequest", str + " this: " + this.b);
    }
}
