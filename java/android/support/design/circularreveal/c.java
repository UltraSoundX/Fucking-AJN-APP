package android.support.design.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.support.design.widget.k;
import android.util.Property;

/* compiled from: CircularRevealWidget */
public interface c extends a {

    /* compiled from: CircularRevealWidget */
    public static class a implements TypeEvaluator<d> {
        public static final TypeEvaluator<d> a = new a();
        private final d b = new d();

        /* renamed from: a */
        public d evaluate(float f, d dVar, d dVar2) {
            this.b.a(k.a(dVar.a, dVar2.a, f), k.a(dVar.b, dVar2.b, f), k.a(dVar.c, dVar2.c, f));
            return this.b;
        }
    }

    /* compiled from: CircularRevealWidget */
    public static class b extends Property<c, d> {
        public static final Property<c, d> a = new b("circularReveal");

        private b(String str) {
            super(d.class, str);
        }

        /* renamed from: a */
        public d get(c cVar) {
            return cVar.getRevealInfo();
        }

        /* renamed from: a */
        public void set(c cVar, d dVar) {
            cVar.setRevealInfo(dVar);
        }
    }

    /* renamed from: android.support.design.circularreveal.c$c reason: collision with other inner class name */
    /* compiled from: CircularRevealWidget */
    public static class C0005c extends Property<c, Integer> {
        public static final Property<c, Integer> a = new C0005c("circularRevealScrimColor");

        private C0005c(String str) {
            super(Integer.class, str);
        }

        /* renamed from: a */
        public Integer get(c cVar) {
            return Integer.valueOf(cVar.getCircularRevealScrimColor());
        }

        /* renamed from: a */
        public void set(c cVar, Integer num) {
            cVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    /* compiled from: CircularRevealWidget */
    public static class d {
        public float a;
        public float b;
        public float c;

        private d() {
        }

        public d(float f, float f2, float f3) {
            this.a = f;
            this.b = f2;
            this.c = f3;
        }

        public d(d dVar) {
            this(dVar.a, dVar.b, dVar.c);
        }

        public void a(float f, float f2, float f3) {
            this.a = f;
            this.b = f2;
            this.c = f3;
        }

        public void a(d dVar) {
            a(dVar.a, dVar.b, dVar.c);
        }

        public boolean a() {
            return this.c == Float.MAX_VALUE;
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    d getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(d dVar);
}
