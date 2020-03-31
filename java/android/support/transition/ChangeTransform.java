package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

public class ChangeTransform extends Transition {
    private static final String[] i = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    private static final Property<b, float[]> j = new Property<b, float[]>(float[].class, "nonTranslations") {
        /* renamed from: a */
        public float[] get(b bVar) {
            return null;
        }

        /* renamed from: a */
        public void set(b bVar, float[] fArr) {
            bVar.a(fArr);
        }
    };
    private static final Property<b, PointF> k = new Property<b, PointF>(PointF.class, "translations") {
        /* renamed from: a */
        public PointF get(b bVar) {
            return null;
        }

        /* renamed from: a */
        public void set(b bVar, PointF pointF) {
            bVar.a(pointF);
        }
    };
    private static final boolean l;
    boolean a = true;
    private boolean m = true;
    private Matrix n = new Matrix();

    private static class a extends q {
        private View a;
        private f b;

        a(View view, f fVar) {
            this.a = view;
            this.b = fVar;
        }

        public void a(Transition transition) {
            transition.b((android.support.transition.Transition.c) this);
            g.a(this.a);
            this.a.setTag(R.id.transition_transform, null);
            this.a.setTag(R.id.parent_matrix, null);
        }

        public void b(Transition transition) {
            this.b.setVisibility(4);
        }

        public void c(Transition transition) {
            this.b.setVisibility(0);
        }
    }

    private static class b {
        private final Matrix a = new Matrix();
        private final View b;
        private final float[] c;
        private float d;
        private float e;

        b(View view, float[] fArr) {
            this.b = view;
            this.c = (float[]) fArr.clone();
            this.d = this.c[2];
            this.e = this.c[5];
            b();
        }

        /* access modifiers changed from: 0000 */
        public void a(float[] fArr) {
            System.arraycopy(fArr, 0, this.c, 0, fArr.length);
            b();
        }

        /* access modifiers changed from: 0000 */
        public void a(PointF pointF) {
            this.d = pointF.x;
            this.e = pointF.y;
            b();
        }

        private void b() {
            this.c[2] = this.d;
            this.c[5] = this.e;
            this.a.setValues(this.c);
            ah.c(this.b, this.a);
        }

        /* access modifiers changed from: 0000 */
        public Matrix a() {
            return this.a;
        }
    }

    private static class c {
        final float a;
        final float b;
        final float c;
        final float d;
        final float e;
        final float f;
        final float g;
        final float h;

        c(View view) {
            this.a = view.getTranslationX();
            this.b = view.getTranslationY();
            this.c = ViewCompat.getTranslationZ(view);
            this.d = view.getScaleX();
            this.e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public void a(View view) {
            ChangeTransform.a(view, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (cVar.a == this.a && cVar.b == this.b && cVar.c == this.c && cVar.d == this.d && cVar.e == this.e && cVar.f == this.f && cVar.g == this.g && cVar.h == this.h) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7 = 0;
            int floatToIntBits = (this.a != 0.0f ? Float.floatToIntBits(this.a) : 0) * 31;
            if (this.b != 0.0f) {
                i = Float.floatToIntBits(this.b);
            } else {
                i = 0;
            }
            int i8 = (i + floatToIntBits) * 31;
            if (this.c != 0.0f) {
                i2 = Float.floatToIntBits(this.c);
            } else {
                i2 = 0;
            }
            int i9 = (i2 + i8) * 31;
            if (this.d != 0.0f) {
                i3 = Float.floatToIntBits(this.d);
            } else {
                i3 = 0;
            }
            int i10 = (i3 + i9) * 31;
            if (this.e != 0.0f) {
                i4 = Float.floatToIntBits(this.e);
            } else {
                i4 = 0;
            }
            int i11 = (i4 + i10) * 31;
            if (this.f != 0.0f) {
                i5 = Float.floatToIntBits(this.f);
            } else {
                i5 = 0;
            }
            int i12 = (i5 + i11) * 31;
            if (this.g != 0.0f) {
                i6 = Float.floatToIntBits(this.g);
            } else {
                i6 = 0;
            }
            int i13 = (i6 + i12) * 31;
            if (this.h != 0.0f) {
                i7 = Float.floatToIntBits(this.h);
            }
            return i13 + i7;
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        l = z;
    }

    public ChangeTransform() {
    }

    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.g);
        this.a = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlPullParser) attributeSet, "reparentWithOverlay", 1, true);
        this.m = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlPullParser) attributeSet, "reparent", 0, true);
        obtainStyledAttributes.recycle();
    }

    public String[] a() {
        return i;
    }

    private void d(v vVar) {
        Matrix matrix;
        View view = vVar.b;
        if (view.getVisibility() != 8) {
            vVar.a.put("android:changeTransform:parent", view.getParent());
            vVar.a.put("android:changeTransform:transforms", new c(view));
            Matrix matrix2 = view.getMatrix();
            if (matrix2 == null || matrix2.isIdentity()) {
                matrix = null;
            } else {
                matrix = new Matrix(matrix2);
            }
            vVar.a.put("android:changeTransform:matrix", matrix);
            if (this.m) {
                Matrix matrix3 = new Matrix();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                ah.a((View) viewGroup, matrix3);
                matrix3.preTranslate((float) (-viewGroup.getScrollX()), (float) (-viewGroup.getScrollY()));
                vVar.a.put("android:changeTransform:parentMatrix", matrix3);
                vVar.a.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
                vVar.a.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
            }
        }
    }

    public void a(v vVar) {
        d(vVar);
        if (!l) {
            ((ViewGroup) vVar.b.getParent()).startViewTransition(vVar.b);
        }
    }

    public void b(v vVar) {
        d(vVar);
    }

    public Animator a(ViewGroup viewGroup, v vVar, v vVar2) {
        boolean z;
        if (vVar == null || vVar2 == null || !vVar.a.containsKey("android:changeTransform:parent") || !vVar2.a.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) vVar.a.get("android:changeTransform:parent");
        ViewGroup viewGroup3 = (ViewGroup) vVar2.a.get("android:changeTransform:parent");
        if (!this.m || a(viewGroup2, viewGroup3)) {
            z = false;
        } else {
            z = true;
        }
        Matrix matrix = (Matrix) vVar.a.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            vVar.a.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) vVar.a.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            vVar.a.put("android:changeTransform:parentMatrix", matrix2);
        }
        if (z) {
            b(vVar, vVar2);
        }
        ObjectAnimator a2 = a(vVar, vVar2, z);
        if (z && a2 != null && this.a) {
            b(viewGroup, vVar, vVar2);
        } else if (!l) {
            viewGroup2.endViewTransition(vVar.b);
        }
        return a2;
    }

    private ObjectAnimator a(v vVar, v vVar2, boolean z) {
        final Matrix matrix;
        Matrix matrix2 = (Matrix) vVar.a.get("android:changeTransform:matrix");
        Matrix matrix3 = (Matrix) vVar2.a.get("android:changeTransform:matrix");
        if (matrix2 == null) {
            matrix2 = i.a;
        }
        if (matrix3 == null) {
            matrix = i.a;
        } else {
            matrix = matrix3;
        }
        if (matrix2.equals(matrix)) {
            return null;
        }
        final c cVar = (c) vVar2.a.get("android:changeTransform:transforms");
        final View view = vVar2.b;
        a(view);
        float[] fArr = new float[9];
        matrix2.getValues(fArr);
        float[] fArr2 = new float[9];
        matrix.getValues(fArr2);
        final b bVar = new b(view, fArr);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bVar, new PropertyValuesHolder[]{PropertyValuesHolder.ofObject(j, new c(new float[9]), new float[][]{fArr, fArr2}), l.a(k, l().a(fArr[2], fArr[5], fArr2[2], fArr2[5]))});
        final boolean z2 = z;
        AnonymousClass3 r0 = new AnimatorListenerAdapter() {
            private boolean g;
            private Matrix h = new Matrix();

            public void onAnimationCancel(Animator animator) {
                this.g = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.g) {
                    if (!z2 || !ChangeTransform.this.a) {
                        view.setTag(R.id.transition_transform, null);
                        view.setTag(R.id.parent_matrix, null);
                    } else {
                        a(matrix);
                    }
                }
                ah.c(view, null);
                cVar.a(view);
            }

            public void onAnimationPause(Animator animator) {
                a(bVar.a());
            }

            public void onAnimationResume(Animator animator) {
                ChangeTransform.a(view);
            }

            private void a(Matrix matrix) {
                this.h.set(matrix);
                view.setTag(R.id.transition_transform, this.h);
                cVar.a(view);
            }
        };
        ofPropertyValuesHolder.addListener(r0);
        a.a(ofPropertyValuesHolder, r0);
        return ofPropertyValuesHolder;
    }

    private boolean a(ViewGroup viewGroup, ViewGroup viewGroup2) {
        if (b((View) viewGroup) && b((View) viewGroup2)) {
            v b2 = b((View) viewGroup, true);
            if (b2 == null) {
                return false;
            }
            if (viewGroup2 != b2.b) {
                return false;
            }
            return true;
        } else if (viewGroup == viewGroup2) {
            return true;
        } else {
            return false;
        }
    }

    private void b(ViewGroup viewGroup, v vVar, v vVar2) {
        View view = vVar2.b;
        Matrix matrix = new Matrix((Matrix) vVar2.a.get("android:changeTransform:parentMatrix"));
        ah.b(viewGroup, matrix);
        f a2 = g.a(view, viewGroup, matrix);
        if (a2 != null) {
            a2.a((ViewGroup) vVar.a.get("android:changeTransform:parent"), vVar.b);
            while (this.e != null) {
                this = this.e;
            }
            this.a((android.support.transition.Transition.c) new a(view, a2));
            if (l) {
                if (vVar.b != vVar2.b) {
                    ah.a(vVar.b, 0.0f);
                }
                ah.a(view, 1.0f);
            }
        }
    }

    private void b(v vVar, v vVar2) {
        Matrix matrix;
        Matrix matrix2 = (Matrix) vVar2.a.get("android:changeTransform:parentMatrix");
        vVar2.b.setTag(R.id.parent_matrix, matrix2);
        Matrix matrix3 = this.n;
        matrix3.reset();
        matrix2.invert(matrix3);
        Matrix matrix4 = (Matrix) vVar.a.get("android:changeTransform:matrix");
        if (matrix4 == null) {
            Matrix matrix5 = new Matrix();
            vVar.a.put("android:changeTransform:matrix", matrix5);
            matrix = matrix5;
        } else {
            matrix = matrix4;
        }
        matrix.postConcat((Matrix) vVar.a.get("android:changeTransform:parentMatrix"));
        matrix.postConcat(matrix3);
    }

    static void a(View view) {
        a(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    static void a(View view, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        view.setTranslationX(f);
        view.setTranslationY(f2);
        ViewCompat.setTranslationZ(view, f3);
        view.setScaleX(f4);
        view.setScaleY(f5);
        view.setRotationX(f6);
        view.setRotationY(f7);
        view.setRotation(f8);
    }
}
