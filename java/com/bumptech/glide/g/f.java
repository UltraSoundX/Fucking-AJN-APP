package com.bumptech.glide.g;

/* compiled from: ThumbnailRequestCoordinator */
public class f implements b, c {
    private b a;
    private b b;
    private c c;

    public f() {
        this(null);
    }

    public f(c cVar) {
        this.c = cVar;
    }

    public void a(b bVar, b bVar2) {
        this.a = bVar;
        this.b = bVar2;
    }

    public boolean a(b bVar) {
        return j() && (bVar.equals(this.a) || !this.a.h());
    }

    private boolean j() {
        return this.c == null || this.c.a(this);
    }

    public boolean b(b bVar) {
        return k() && bVar.equals(this.a) && !c();
    }

    private boolean k() {
        return this.c == null || this.c.b(this);
    }

    public boolean c() {
        return l() || h();
    }

    public void c(b bVar) {
        if (!bVar.equals(this.b)) {
            if (this.c != null) {
                this.c.c(this);
            }
            if (!this.b.g()) {
                this.b.d();
            }
        }
    }

    private boolean l() {
        return this.c != null && this.c.c();
    }

    public void b() {
        if (!this.b.f()) {
            this.b.b();
        }
        if (!this.a.f()) {
            this.a.b();
        }
    }

    public void e() {
        this.a.e();
        this.b.e();
    }

    public void d() {
        this.b.d();
        this.a.d();
    }

    public boolean f() {
        return this.a.f();
    }

    public boolean g() {
        return this.a.g() || this.b.g();
    }

    public boolean h() {
        return this.a.h() || this.b.h();
    }

    public boolean i() {
        return this.a.i();
    }

    public void a() {
        this.a.a();
        this.b.a();
    }
}
