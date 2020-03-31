package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

public class ChangeBounds extends Transition {
    private static final String[] a = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property<Drawable, PointF> i = new Property<Drawable, PointF>(PointF.class, "boundsOrigin") {
        private Rect a = new Rect();

        /* renamed from: a */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.a);
            this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.a);
        }

        /* renamed from: a */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.a);
            return new PointF((float) this.a.left, (float) this.a.top);
        }
    };
    private static final Property<a, PointF> j = new Property<a, PointF>(PointF.class, "topLeft") {
        /* renamed from: a */
        public void set(a aVar, PointF pointF) {
            aVar.a(pointF);
        }

        /* renamed from: a */
        public PointF get(a aVar) {
            return null;
        }
    };
    private static final Property<a, PointF> k = new Property<a, PointF>(PointF.class, "bottomRight") {
        /* renamed from: a */
        public void set(a aVar, PointF pointF) {
            aVar.b(pointF);
        }

        /* renamed from: a */
        public PointF get(a aVar) {
            return null;
        }
    };
    private static final Property<View, PointF> l = new Property<View, PointF>(PointF.class, "bottomRight") {
        /* renamed from: a */
        public void set(View view, PointF pointF) {
            ah.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }

        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<View, PointF> m = new Property<View, PointF>(PointF.class, "topLeft") {
        /* renamed from: a */
        public void set(View view, PointF pointF) {
            ah.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }

        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<View, PointF> n = new Property<View, PointF>(PointF.class, "position") {
        /* renamed from: a */
        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            ah.a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }

        /* renamed from: a */
        public PointF get(View view) {
            return null;
        }
    };
    private static m r = new m();
    private int[] o = new int[2];
    private boolean p = false;

    /* renamed from: q reason: collision with root package name */
    private boolean f332q = false;

    private static class a {
        private int a;
        private int b;
        private int c;
        private int d;
        private View e;
        private int f;
        private int g;

        a(View view) {
            this.e = view;
        }

        /* access modifiers changed from: 0000 */
        public void a(PointF pointF) {
            this.a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            this.f++;
            if (this.f == this.g) {
                a();
            }
        }

        /* access modifiers changed from: 0000 */
        public void b(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            this.g++;
            if (this.f == this.g) {
                a();
            }
        }

        private void a() {
            ah.a(this.e, this.a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }
    }

    public ChangeBounds() {
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.d);
        boolean namedBoolean = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlResourceParser) attributeSet, "resizeClip", 0, false);
        obtainStyledAttributes.recycle();
        a(namedBoolean);
    }

    public String[] a() {
        return a;
    }

    public void a(boolean z) {
        this.p = z;
    }

    private void d(v vVar) {
        View view = vVar.b;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            vVar.a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            vVar.a.put("android:changeBounds:parent", vVar.b.getParent());
            if (this.f332q) {
                vVar.b.getLocationInWindow(this.o);
                vVar.a.put("android:changeBounds:windowX", Integer.valueOf(this.o[0]));
                vVar.a.put("android:changeBounds:windowY", Integer.valueOf(this.o[1]));
            }
            if (this.p) {
                vVar.a.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
            }
        }
    }

    public void a(v vVar) {
        d(vVar);
    }

    public void b(v vVar) {
        d(vVar);
    }

    private boolean a(View view, View view2) {
        if (!this.f332q) {
            return true;
        }
        v b = b(view, true);
        if (b == null) {
            if (view == view2) {
                return true;
            }
            return false;
        } else if (view2 != b.b) {
            return false;
        } else {
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r1v31, types: [android.animation.Animator] */
    /* JADX WARNING: type inference failed for: r2v27 */
    /* JADX WARNING: type inference failed for: r2v35, types: [android.animation.Animator] */
    /* JADX WARNING: type inference failed for: r1v43, types: [android.animation.ObjectAnimator] */
    /* JADX WARNING: type inference failed for: r2v38 */
    /* JADX WARNING: type inference failed for: r1v46, types: [android.animation.ObjectAnimator] */
    /* JADX WARNING: type inference failed for: r2v41 */
    /* JADX WARNING: type inference failed for: r2v43 */
    /* JADX WARNING: type inference failed for: r1v54, types: [android.animation.ObjectAnimator] */
    /* JADX WARNING: type inference failed for: r2v46 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator a(android.view.ViewGroup r19, android.support.transition.v r20, android.support.transition.v r21) {
        /*
            r18 = this;
            if (r20 == 0) goto L_0x0004
            if (r21 != 0) goto L_0x0006
        L_0x0004:
            r2 = 0
        L_0x0005:
            return r2
        L_0x0006:
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            r0 = r21
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a
            java.lang.String r3 = "android:changeBounds:parent"
            java.lang.Object r1 = r1.get(r3)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            java.lang.String r3 = "android:changeBounds:parent"
            java.lang.Object r2 = r2.get(r3)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            if (r1 == 0) goto L_0x0022
            if (r2 != 0) goto L_0x0024
        L_0x0022:
            r2 = 0
            goto L_0x0005
        L_0x0024:
            r0 = r21
            android.view.View r3 = r0.b
            r0 = r18
            boolean r1 = r0.a(r1, r2)
            if (r1 == 0) goto L_0x01bf
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r2 = "android:changeBounds:bounds"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            r0 = r21
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a
            java.lang.String r4 = "android:changeBounds:bounds"
            java.lang.Object r2 = r2.get(r4)
            android.graphics.Rect r2 = (android.graphics.Rect) r2
            int r9 = r1.left
            int r5 = r2.left
            int r10 = r1.top
            int r6 = r2.top
            int r11 = r1.right
            int r7 = r2.right
            int r12 = r1.bottom
            int r8 = r2.bottom
            int r13 = r11 - r9
            int r14 = r12 - r10
            int r15 = r7 - r5
            int r16 = r8 - r6
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r2 = "android:changeBounds:clip"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            r0 = r21
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.a
            java.lang.String r4 = "android:changeBounds:clip"
            java.lang.Object r4 = r2.get(r4)
            android.graphics.Rect r4 = (android.graphics.Rect) r4
            r2 = 0
            if (r13 == 0) goto L_0x007d
            if (r14 != 0) goto L_0x0081
        L_0x007d:
            if (r15 == 0) goto L_0x008c
            if (r16 == 0) goto L_0x008c
        L_0x0081:
            if (r9 != r5) goto L_0x0085
            if (r10 == r6) goto L_0x0086
        L_0x0085:
            r2 = 1
        L_0x0086:
            if (r11 != r7) goto L_0x008a
            if (r12 == r8) goto L_0x008c
        L_0x008a:
            int r2 = r2 + 1
        L_0x008c:
            if (r1 == 0) goto L_0x0094
            boolean r17 = r1.equals(r4)
            if (r17 == 0) goto L_0x0098
        L_0x0094:
            if (r1 != 0) goto L_0x009a
            if (r4 == 0) goto L_0x009a
        L_0x0098:
            int r2 = r2 + 1
        L_0x009a:
            if (r2 <= 0) goto L_0x0281
            r0 = r18
            boolean r0 = r0.p
            r17 = r0
            if (r17 != 0) goto L_0x0156
            android.support.transition.ah.a(r3, r9, r10, r11, r12)
            r1 = 2
            if (r2 != r1) goto L_0x0129
            if (r13 != r15) goto L_0x00e3
            r0 = r16
            if (r14 != r0) goto L_0x00e3
            android.support.transition.PathMotion r1 = r18.l()
            float r2 = (float) r9
            float r4 = (float) r10
            float r5 = (float) r5
            float r6 = (float) r6
            android.graphics.Path r1 = r1.a(r2, r4, r5, r6)
            android.util.Property<android.view.View, android.graphics.PointF> r2 = n
            android.animation.ObjectAnimator r1 = android.support.transition.j.a(r3, r2, r1)
            r2 = r1
        L_0x00c3:
            android.view.ViewParent r1 = r3.getParent()
            boolean r1 = r1 instanceof android.view.ViewGroup
            if (r1 == 0) goto L_0x0005
            android.view.ViewParent r1 = r3.getParent()
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r3 = 1
            android.support.transition.ab.a(r1, r3)
            android.support.transition.ChangeBounds$10 r3 = new android.support.transition.ChangeBounds$10
            r0 = r18
            r3.<init>(r1)
            r0 = r18
            r0.a(r3)
            goto L_0x0005
        L_0x00e3:
            android.support.transition.ChangeBounds$a r2 = new android.support.transition.ChangeBounds$a
            r2.<init>(r3)
            android.support.transition.PathMotion r1 = r18.l()
            float r4 = (float) r9
            float r9 = (float) r10
            float r5 = (float) r5
            float r6 = (float) r6
            android.graphics.Path r1 = r1.a(r4, r9, r5, r6)
            android.util.Property<android.support.transition.ChangeBounds$a, android.graphics.PointF> r4 = j
            android.animation.ObjectAnimator r4 = android.support.transition.j.a(r2, r4, r1)
            android.support.transition.PathMotion r1 = r18.l()
            float r5 = (float) r11
            float r6 = (float) r12
            float r7 = (float) r7
            float r8 = (float) r8
            android.graphics.Path r1 = r1.a(r5, r6, r7, r8)
            android.util.Property<android.support.transition.ChangeBounds$a, android.graphics.PointF> r5 = k
            android.animation.ObjectAnimator r5 = android.support.transition.j.a(r2, r5, r1)
            android.animation.AnimatorSet r1 = new android.animation.AnimatorSet
            r1.<init>()
            r6 = 2
            android.animation.Animator[] r6 = new android.animation.Animator[r6]
            r7 = 0
            r6[r7] = r4
            r4 = 1
            r6[r4] = r5
            r1.playTogether(r6)
            android.support.transition.ChangeBounds$8 r4 = new android.support.transition.ChangeBounds$8
            r0 = r18
            r4.<init>(r2)
            r1.addListener(r4)
            r2 = r1
            goto L_0x00c3
        L_0x0129:
            if (r9 != r5) goto L_0x012d
            if (r10 == r6) goto L_0x0141
        L_0x012d:
            android.support.transition.PathMotion r1 = r18.l()
            float r2 = (float) r9
            float r4 = (float) r10
            float r5 = (float) r5
            float r6 = (float) r6
            android.graphics.Path r1 = r1.a(r2, r4, r5, r6)
            android.util.Property<android.view.View, android.graphics.PointF> r2 = m
            android.animation.ObjectAnimator r1 = android.support.transition.j.a(r3, r2, r1)
            r2 = r1
            goto L_0x00c3
        L_0x0141:
            android.support.transition.PathMotion r1 = r18.l()
            float r2 = (float) r11
            float r4 = (float) r12
            float r5 = (float) r7
            float r6 = (float) r8
            android.graphics.Path r1 = r1.a(r2, r4, r5, r6)
            android.util.Property<android.view.View, android.graphics.PointF> r2 = l
            android.animation.ObjectAnimator r1 = android.support.transition.j.a(r3, r2, r1)
            r2 = r1
            goto L_0x00c3
        L_0x0156:
            int r2 = java.lang.Math.max(r13, r15)
            r0 = r16
            int r11 = java.lang.Math.max(r14, r0)
            int r2 = r2 + r9
            int r11 = r11 + r10
            android.support.transition.ah.a(r3, r9, r10, r2, r11)
            r2 = 0
            if (r9 != r5) goto L_0x016a
            if (r10 == r6) goto L_0x028d
        L_0x016a:
            android.support.transition.PathMotion r2 = r18.l()
            float r9 = (float) r9
            float r10 = (float) r10
            float r11 = (float) r5
            float r12 = (float) r6
            android.graphics.Path r2 = r2.a(r9, r10, r11, r12)
            android.util.Property<android.view.View, android.graphics.PointF> r9 = n
            android.animation.ObjectAnimator r2 = android.support.transition.j.a(r3, r9, r2)
            r10 = r2
        L_0x017d:
            if (r1 != 0) goto L_0x028a
            android.graphics.Rect r1 = new android.graphics.Rect
            r2 = 0
            r9 = 0
            r1.<init>(r2, r9, r13, r14)
            r9 = r1
        L_0x0187:
            if (r4 != 0) goto L_0x0287
            android.graphics.Rect r1 = new android.graphics.Rect
            r2 = 0
            r11 = 0
            r0 = r16
            r1.<init>(r2, r11, r15, r0)
        L_0x0192:
            r2 = 0
            boolean r11 = r9.equals(r1)
            if (r11 != 0) goto L_0x0284
            android.support.v4.view.ViewCompat.setClipBounds(r3, r9)
            java.lang.String r2 = "clipBounds"
            android.support.transition.m r11 = r
            r12 = 2
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r13 = 0
            r12[r13] = r9
            r9 = 1
            r12[r9] = r1
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofObject(r3, r2, r11, r12)
            android.support.transition.ChangeBounds$9 r1 = new android.support.transition.ChangeBounds$9
            r2 = r18
            r1.<init>(r3, r4, r5, r6, r7, r8)
            r9.addListener(r1)
            r1 = r9
        L_0x01b8:
            android.animation.Animator r1 = android.support.transition.u.a(r10, r1)
            r2 = r1
            goto L_0x00c3
        L_0x01bf:
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r2 = "android:changeBounds:windowX"
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r2 = r1.intValue()
            r0 = r20
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r4 = "android:changeBounds:windowY"
            java.lang.Object r1 = r1.get(r4)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r4 = r1.intValue()
            r0 = r21
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r5 = "android:changeBounds:windowX"
            java.lang.Object r1 = r1.get(r5)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r5 = r1.intValue()
            r0 = r21
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.a
            java.lang.String r6 = "android:changeBounds:windowY"
            java.lang.Object r1 = r1.get(r6)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r2 != r5) goto L_0x0203
            if (r4 == r1) goto L_0x0281
        L_0x0203:
            r0 = r18
            int[] r6 = r0.o
            r0 = r19
            r0.getLocationInWindow(r6)
            int r6 = r3.getWidth()
            int r7 = r3.getHeight()
            android.graphics.Bitmap$Config r8 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r7, r8)
            android.graphics.Canvas r7 = new android.graphics.Canvas
            r7.<init>(r6)
            r3.draw(r7)
            android.graphics.drawable.BitmapDrawable r7 = new android.graphics.drawable.BitmapDrawable
            r7.<init>(r6)
            float r9 = android.support.transition.ah.c(r3)
            r6 = 0
            android.support.transition.ah.a(r3, r6)
            android.support.transition.ag r6 = android.support.transition.ah.a(r19)
            r6.a(r7)
            android.support.transition.PathMotion r6 = r18.l()
            r0 = r18
            int[] r8 = r0.o
            r10 = 0
            r8 = r8[r10]
            int r2 = r2 - r8
            float r2 = (float) r2
            r0 = r18
            int[] r8 = r0.o
            r10 = 1
            r8 = r8[r10]
            int r4 = r4 - r8
            float r4 = (float) r4
            r0 = r18
            int[] r8 = r0.o
            r10 = 0
            r8 = r8[r10]
            int r5 = r5 - r8
            float r5 = (float) r5
            r0 = r18
            int[] r8 = r0.o
            r10 = 1
            r8 = r8[r10]
            int r1 = r1 - r8
            float r1 = (float) r1
            android.graphics.Path r1 = r6.a(r2, r4, r5, r1)
            android.util.Property<android.graphics.drawable.Drawable, android.graphics.PointF> r2 = i
            android.animation.PropertyValuesHolder r1 = android.support.transition.l.a(r2, r1)
            r2 = 1
            android.animation.PropertyValuesHolder[] r2 = new android.animation.PropertyValuesHolder[r2]
            r4 = 0
            r2[r4] = r1
            android.animation.ObjectAnimator r2 = android.animation.ObjectAnimator.ofPropertyValuesHolder(r7, r2)
            android.support.transition.ChangeBounds$2 r4 = new android.support.transition.ChangeBounds$2
            r5 = r18
            r6 = r19
            r8 = r3
            r4.<init>(r6, r7, r8, r9)
            r2.addListener(r4)
            goto L_0x0005
        L_0x0281:
            r2 = 0
            goto L_0x0005
        L_0x0284:
            r1 = r2
            goto L_0x01b8
        L_0x0287:
            r1 = r4
            goto L_0x0192
        L_0x028a:
            r9 = r1
            goto L_0x0187
        L_0x028d:
            r10 = r2
            goto L_0x017d
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ChangeBounds.a(android.view.ViewGroup, android.support.transition.v, android.support.transition.v):android.animation.Animator");
    }
}
