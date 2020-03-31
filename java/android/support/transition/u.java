package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/* compiled from: TransitionUtils */
class u {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;

    /* compiled from: TransitionUtils */
    static class a implements TypeEvaluator<Matrix> {
        final float[] a = new float[9];
        final float[] b = new float[9];
        final Matrix c = new Matrix();

        a() {
        }

        /* renamed from: a */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            matrix.getValues(this.a);
            matrix2.getValues(this.b);
            for (int i = 0; i < 9; i++) {
                this.b[i] = ((this.b[i] - this.a[i]) * f) + this.a[i];
            }
            this.c.setValues(this.b);
            return this.c;
        }
    }

    static {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (VERSION.SDK_INT >= 19) {
            z = true;
        } else {
            z = false;
        }
        a = z;
        if (VERSION.SDK_INT >= 18) {
            z2 = true;
        } else {
            z2 = false;
        }
        b = z2;
        if (VERSION.SDK_INT < 28) {
            z3 = false;
        }
        c = z3;
    }

    static View a(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
        ah.a(view, matrix);
        ah.b(viewGroup, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        matrix.mapRect(rectF);
        int round = Math.round(rectF.left);
        int round2 = Math.round(rectF.top);
        int round3 = Math.round(rectF.right);
        int round4 = Math.round(rectF.bottom);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setScaleType(ScaleType.CENTER_CROP);
        Bitmap a2 = a(view, matrix, rectF, viewGroup);
        if (a2 != null) {
            imageView.setImageBitmap(a2);
        }
        imageView.measure(MeasureSpec.makeMeasureSpec(round3 - round, 1073741824), MeasureSpec.makeMeasureSpec(round4 - round2, 1073741824));
        imageView.layout(round, round2, round3, round4);
        return imageView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap a(android.view.View r8, android.graphics.Matrix r9, android.graphics.RectF r10, android.view.ViewGroup r11) {
        /*
            r3 = 0
            r1 = 0
            boolean r0 = a
            if (r0 == 0) goto L_0x0020
            boolean r0 = r8.isAttachedToWindow()
            if (r0 != 0) goto L_0x0019
            r2 = 1
        L_0x000d:
            if (r11 != 0) goto L_0x001b
            r0 = r1
        L_0x0010:
            boolean r4 = b
            if (r4 == 0) goto L_0x00a9
            if (r2 == 0) goto L_0x00a9
            if (r0 != 0) goto L_0x0023
        L_0x0018:
            return r3
        L_0x0019:
            r2 = r1
            goto L_0x000d
        L_0x001b:
            boolean r0 = r11.isAttachedToWindow()
            goto L_0x0010
        L_0x0020:
            r0 = r1
            r2 = r1
            goto L_0x0010
        L_0x0023:
            android.view.ViewParent r0 = r8.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            int r1 = r0.indexOfChild(r8)
            android.view.ViewGroupOverlay r4 = r11.getOverlay()
            r4.add(r8)
        L_0x0034:
            float r4 = r10.width()
            int r4 = java.lang.Math.round(r4)
            float r5 = r10.height()
            int r5 = java.lang.Math.round(r5)
            if (r4 <= 0) goto L_0x0086
            if (r5 <= 0) goto L_0x0086
            r3 = 1065353216(0x3f800000, float:1.0)
            r6 = 1233125376(0x49800000, float:1048576.0)
            int r7 = r4 * r5
            float r7 = (float) r7
            float r6 = r6 / r7
            float r3 = java.lang.Math.min(r3, r6)
            float r4 = (float) r4
            float r4 = r4 * r3
            int r4 = java.lang.Math.round(r4)
            float r5 = (float) r5
            float r5 = r5 * r3
            int r5 = java.lang.Math.round(r5)
            float r6 = r10.left
            float r6 = -r6
            float r7 = r10.top
            float r7 = -r7
            r9.postTranslate(r6, r7)
            r9.postScale(r3, r3)
            boolean r3 = c
            if (r3 == 0) goto L_0x0097
            android.graphics.Picture r3 = new android.graphics.Picture
            r3.<init>()
            android.graphics.Canvas r4 = r3.beginRecording(r4, r5)
            r4.concat(r9)
            r8.draw(r4)
            r3.endRecording()
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3)
        L_0x0086:
            boolean r4 = b
            if (r4 == 0) goto L_0x0018
            if (r2 == 0) goto L_0x0018
            android.view.ViewGroupOverlay r2 = r11.getOverlay()
            r2.remove(r8)
            r0.addView(r8, r1)
            goto L_0x0018
        L_0x0097:
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r4, r5, r3)
            android.graphics.Canvas r4 = new android.graphics.Canvas
            r4.<init>(r3)
            r4.concat(r9)
            r8.draw(r4)
            goto L_0x0086
        L_0x00a9:
            r0 = r3
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.u.a(android.view.View, android.graphics.Matrix, android.graphics.RectF, android.view.ViewGroup):android.graphics.Bitmap");
    }

    static Animator a(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{animator, animator2});
        return animatorSet;
    }
}
