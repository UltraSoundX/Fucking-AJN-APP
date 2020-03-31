package android.support.design.bottomappbar;

import android.support.design.shape.b;
import android.support.design.shape.c;

/* compiled from: BottomAppBarTopEdgeTreatment */
public class a extends b {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;

    public a(float f, float f2, float f3) {
        this.b = f;
        this.a = f2;
        this.d = f3;
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.e = 0.0f;
    }

    public void a(float f, float f2, c cVar) {
        if (this.c == 0.0f) {
            cVar.b(f, 0.0f);
            return;
        }
        float f3 = ((this.b * 2.0f) + this.c) / 2.0f;
        float f4 = f2 * this.a;
        float f5 = (f / 2.0f) + this.e;
        float f6 = (this.d * f2) + ((1.0f - f2) * f3);
        if (f6 / f3 >= 1.0f) {
            cVar.b(f, 0.0f);
            return;
        }
        float f7 = f3 + f4;
        float f8 = f6 + f4;
        float sqrt = (float) Math.sqrt((double) ((f7 * f7) - (f8 * f8)));
        float f9 = f5 - sqrt;
        float f10 = f5 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan((double) (sqrt / f8)));
        float f11 = 90.0f - degrees;
        cVar.b(f9 - f4, 0.0f);
        cVar.a(f9 - f4, 0.0f, f9 + f4, f4 * 2.0f, 270.0f, degrees);
        cVar.a(f5 - f3, (-f3) - f6, f5 + f3, f3 - f6, 180.0f - f11, (2.0f * f11) - 180.0f);
        cVar.a(f10 - f4, 0.0f, f10 + f4, f4 * 2.0f, 270.0f - degrees, degrees);
        cVar.b(f, 0.0f);
    }

    /* access modifiers changed from: 0000 */
    public void a(float f) {
        this.e = f;
    }

    /* access modifiers changed from: 0000 */
    public float a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public float b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public void b(float f) {
        this.d = f;
    }

    /* access modifiers changed from: 0000 */
    public float c() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void c(float f) {
        this.c = f;
    }

    /* access modifiers changed from: 0000 */
    public float d() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public void d(float f) {
        this.b = f;
    }

    /* access modifiers changed from: 0000 */
    public float e() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public void e(float f) {
        this.a = f;
    }
}
