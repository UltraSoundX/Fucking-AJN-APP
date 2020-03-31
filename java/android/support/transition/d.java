package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

@SuppressLint({"ViewConstructor"})
/* compiled from: GhostViewApi14 */
class d extends View implements f {
    final View a;
    ViewGroup b;
    View c;
    int d;
    Matrix e;
    private int f;
    private int g;
    private final Matrix h = new Matrix();
    private final OnPreDrawListener i = new OnPreDrawListener() {
        public boolean onPreDraw() {
            d.this.e = d.this.a.getMatrix();
            ViewCompat.postInvalidateOnAnimation(d.this);
            if (!(d.this.b == null || d.this.c == null)) {
                d.this.b.endViewTransition(d.this.c);
                ViewCompat.postInvalidateOnAnimation(d.this.b);
                d.this.b = null;
                d.this.c = null;
            }
            return true;
        }
    };

    static f a(View view, ViewGroup viewGroup) {
        d b2 = b(view);
        if (b2 == null) {
            FrameLayout a2 = a(viewGroup);
            if (a2 == null) {
                return null;
            }
            b2 = new d(view);
            a2.addView(b2);
        }
        b2.d++;
        return b2;
    }

    static void a(View view) {
        d b2 = b(view);
        if (b2 != null) {
            b2.d--;
            if (b2.d <= 0) {
                ViewParent parent = b2.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.endViewTransition(b2);
                    viewGroup.removeView(b2);
                }
            }
        }
    }

    private static FrameLayout a(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = viewGroup;
        while (!(viewGroup2 instanceof FrameLayout)) {
            ViewParent parent = viewGroup2.getParent();
            if (!(parent instanceof ViewGroup)) {
                return null;
            }
            viewGroup2 = (ViewGroup) parent;
        }
        return (FrameLayout) viewGroup2;
    }

    d(View view) {
        super(view.getContext());
        this.a = view;
        setLayerType(2, null);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(this.a, this);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr);
        this.a.getLocationOnScreen(iArr2);
        iArr2[0] = (int) (((float) iArr2[0]) - this.a.getTranslationX());
        iArr2[1] = (int) (((float) iArr2[1]) - this.a.getTranslationY());
        this.f = iArr2[0] - iArr[0];
        this.g = iArr2[1] - iArr[1];
        this.a.getViewTreeObserver().addOnPreDrawListener(this.i);
        this.a.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.a.getViewTreeObserver().removeOnPreDrawListener(this.i);
        this.a.setVisibility(0);
        a(this.a, (d) null);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.h.set(this.e);
        this.h.postTranslate((float) this.f, (float) this.g);
        canvas.setMatrix(this.h);
        this.a.draw(canvas);
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        this.a.setVisibility(i2 == 0 ? 4 : 0);
    }

    public void a(ViewGroup viewGroup, View view) {
        this.b = viewGroup;
        this.c = view;
    }

    private static void a(View view, d dVar) {
        view.setTag(R.id.ghost_view, dVar);
    }

    static d b(View view) {
        return (d) view.getTag(R.id.ghost_view);
    }
}
