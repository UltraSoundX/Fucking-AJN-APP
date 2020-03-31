package me.yokeyword.fragmentation;

/* compiled from: Fragmentation */
public class b {
    static volatile b a;
    private boolean b;
    private int c = 2;
    private me.yokeyword.fragmentation.helper.a d;

    /* compiled from: Fragmentation */
    public static class a {
        /* access modifiers changed from: private */
        public boolean a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public me.yokeyword.fragmentation.helper.a c;

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a a(me.yokeyword.fragmentation.helper.a aVar) {
            this.c = aVar;
            return this;
        }

        public b a() {
            b bVar;
            synchronized (b.class) {
                if (b.a != null) {
                    throw new RuntimeException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
                }
                b.a = new b(this);
                bVar = b.a;
            }
            return bVar;
        }
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(new a());
                }
            }
        }
        return a;
    }

    b(a aVar) {
        this.b = aVar.a;
        if (this.b) {
            this.c = aVar.b;
        } else {
            this.c = 0;
        }
        this.d = aVar.c;
    }

    public me.yokeyword.fragmentation.helper.a b() {
        return this.d;
    }

    public int c() {
        return this.c;
    }

    public static a d() {
        return new a();
    }
}
