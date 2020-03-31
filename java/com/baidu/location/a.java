package com.baidu.location;

public final class a {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;

    /* renamed from: com.baidu.location.a$a reason: collision with other inner class name */
    public static class C0022a {
        /* access modifiers changed from: private */
        public String a = null;
        /* access modifiers changed from: private */
        public String b = null;
        /* access modifiers changed from: private */
        public String c = null;
        /* access modifiers changed from: private */
        public String d = null;
        /* access modifiers changed from: private */
        public String e = null;
        /* access modifiers changed from: private */
        public String f = null;
        /* access modifiers changed from: private */
        public String g = null;
        /* access modifiers changed from: private */
        public String h = null;
        /* access modifiers changed from: private */
        public String i = null;
        /* access modifiers changed from: private */
        public String j = null;

        public C0022a a(String str) {
            this.a = str;
            return this;
        }

        public a a() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.a != null) {
                stringBuffer.append(this.a);
            }
            if (this.c != null) {
                stringBuffer.append(this.c);
            }
            if (!(this.c == null || this.d == null || this.c.equals(this.d))) {
                stringBuffer.append(this.d);
            }
            if (this.f != null) {
                if (this.d == null) {
                    stringBuffer.append(this.f);
                } else if (!this.d.equals(this.f)) {
                    stringBuffer.append(this.f);
                }
            }
            if (this.g != null) {
                stringBuffer.append(this.g);
            }
            if (this.h != null) {
                stringBuffer.append(this.h);
            }
            if (stringBuffer.length() > 0) {
                this.i = stringBuffer.toString();
            }
            return new a(this);
        }

        public C0022a b(String str) {
            this.j = str;
            return this;
        }

        public C0022a c(String str) {
            this.b = str;
            return this;
        }

        public C0022a d(String str) {
            this.c = str;
            return this;
        }

        public C0022a e(String str) {
            this.d = str;
            return this;
        }

        public C0022a f(String str) {
            this.e = str;
            return this;
        }

        public C0022a g(String str) {
            this.f = str;
            return this;
        }

        public C0022a h(String str) {
            this.g = str;
            return this;
        }

        public C0022a i(String str) {
            this.h = str;
            return this;
        }
    }

    private a(C0022a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
    }
}
