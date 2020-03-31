package android.support.design.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShapePath */
public class c {
    public float a;
    public float b;
    public float c;
    public float d;
    private final List<C0007c> e = new ArrayList();

    /* compiled from: ShapePath */
    public static class a extends C0007c {
        private static final RectF h = new RectF();
        public float a;
        public float b;
        public float c;
        public float d;
        public float e;
        public float f;

        public a(float f2, float f3, float f4, float f5) {
            this.a = f2;
            this.b = f3;
            this.c = f4;
            this.d = f5;
        }

        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.g;
            matrix.invert(matrix2);
            path.transform(matrix2);
            h.set(this.a, this.b, this.c, this.d);
            path.arcTo(h, this.e, this.f, false);
            path.transform(matrix);
        }
    }

    /* compiled from: ShapePath */
    public static class b extends C0007c {
        /* access modifiers changed from: private */
        public float a;
        /* access modifiers changed from: private */
        public float b;

        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.g;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.a, this.b);
            path.transform(matrix);
        }
    }

    /* renamed from: android.support.design.shape.c$c reason: collision with other inner class name */
    /* compiled from: ShapePath */
    public static abstract class C0007c {
        protected final Matrix g = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    public c() {
        a(0.0f, 0.0f);
    }

    public void a(float f, float f2) {
        this.a = f;
        this.b = f2;
        this.c = f;
        this.d = f2;
        this.e.clear();
    }

    public void b(float f, float f2) {
        b bVar = new b();
        bVar.a = f;
        bVar.b = f2;
        this.e.add(bVar);
        this.c = f;
        this.d = f2;
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6) {
        a aVar = new a(f, f2, f3, f4);
        aVar.e = f5;
        aVar.f = f6;
        this.e.add(aVar);
        this.c = ((f + f3) * 0.5f) + (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians((double) (f5 + f6)))));
        this.d = ((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians((double) (f5 + f6)))));
    }

    public void a(Matrix matrix, Path path) {
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            ((C0007c) this.e.get(i)).a(matrix, path);
        }
    }
}
