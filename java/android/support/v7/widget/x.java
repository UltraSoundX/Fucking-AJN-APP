package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.m;
import android.support.v7.widget.RecyclerView.s;
import android.view.MotionEvent;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: FastScroller */
class x extends h implements l {
    private static final int[] k = {16842919};
    private static final int[] l = new int[0];
    private int A = 0;
    private final int[] B = new int[2];
    private final int[] C = new int[2];
    private final Runnable D = new Runnable() {
        public void run() {
            x.this.b((int) ErrorCode.INFO_CODE_MINIQB);
        }
    };
    private final m E = new m() {
        public void a(RecyclerView recyclerView, int i, int i2) {
            x.this.a(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    };
    final StateListDrawable a;
    final Drawable b;
    int c;
    int d;
    float e;
    int f;
    int g;
    float h;
    final ValueAnimator i = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    int j = 0;
    private final int m;
    private final int n;
    private final int o;
    private final int p;

    /* renamed from: q reason: collision with root package name */
    private final StateListDrawable f362q;
    private final Drawable r;
    private final int s;
    private final int t;
    private int u = 0;
    private int v = 0;
    private RecyclerView w;
    private boolean x = false;
    private boolean y = false;
    private int z = 0;

    /* compiled from: FastScroller */
    private class a extends AnimatorListenerAdapter {
        private boolean b = false;

        a() {
        }

        public void onAnimationEnd(Animator animator) {
            if (this.b) {
                this.b = false;
            } else if (((Float) x.this.i.getAnimatedValue()).floatValue() == 0.0f) {
                x.this.j = 0;
                x.this.a(0);
            } else {
                x.this.j = 2;
                x.this.a();
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.b = true;
        }
    }

    /* compiled from: FastScroller */
    private class b implements AnimatorUpdateListener {
        b() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            x.this.a.setAlpha(floatValue);
            x.this.b.setAlpha(floatValue);
            x.this.a();
        }
    }

    x(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i3, int i4) {
        this.a = stateListDrawable;
        this.b = drawable;
        this.f362q = stateListDrawable2;
        this.r = drawable2;
        this.o = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.p = Math.max(i2, drawable.getIntrinsicWidth());
        this.s = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.t = Math.max(i2, drawable2.getIntrinsicWidth());
        this.m = i3;
        this.n = i4;
        this.a.setAlpha(255);
        this.b.setAlpha(255);
        this.i.addListener(new a());
        this.i.addUpdateListener(new b());
        a(recyclerView);
    }

    public void a(RecyclerView recyclerView) {
        if (this.w != recyclerView) {
            if (this.w != null) {
                d();
            }
            this.w = recyclerView;
            if (this.w != null) {
                c();
            }
        }
    }

    private void c() {
        this.w.a((h) this);
        this.w.a((l) this);
        this.w.a(this.E);
    }

    private void d() {
        this.w.b((h) this);
        this.w.b((l) this);
        this.w.b(this.E);
        f();
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.w.invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2) {
        if (i2 == 2 && this.z != 2) {
            this.a.setState(k);
            f();
        }
        if (i2 == 0) {
            a();
        } else {
            b();
        }
        if (this.z == 2 && i2 != 2) {
            this.a.setState(l);
            c(1200);
        } else if (i2 == 1) {
            c(1500);
        }
        this.z = i2;
    }

    private boolean e() {
        return ViewCompat.getLayoutDirection(this.w) == 1;
    }

    public void b() {
        switch (this.j) {
            case 0:
                break;
            case 3:
                this.i.cancel();
                break;
            default:
                return;
        }
        this.j = 1;
        this.i.setFloatValues(new float[]{((Float) this.i.getAnimatedValue()).floatValue(), 1.0f});
        this.i.setDuration(500);
        this.i.setStartDelay(0);
        this.i.start();
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2) {
        switch (this.j) {
            case 1:
                this.i.cancel();
                break;
            case 2:
                break;
            default:
                return;
        }
        this.j = 3;
        this.i.setFloatValues(new float[]{((Float) this.i.getAnimatedValue()).floatValue(), 0.0f});
        this.i.setDuration((long) i2);
        this.i.start();
    }

    private void f() {
        this.w.removeCallbacks(this.D);
    }

    private void c(int i2) {
        f();
        this.w.postDelayed(this.D, (long) i2);
    }

    public void a(Canvas canvas, RecyclerView recyclerView, s sVar) {
        if (this.u != this.w.getWidth() || this.v != this.w.getHeight()) {
            this.u = this.w.getWidth();
            this.v = this.w.getHeight();
            a(0);
        } else if (this.j != 0) {
            if (this.x) {
                a(canvas);
            }
            if (this.y) {
                b(canvas);
            }
        }
    }

    private void a(Canvas canvas) {
        int i2 = this.u - this.o;
        int i3 = this.d - (this.c / 2);
        this.a.setBounds(0, 0, this.o, this.c);
        this.b.setBounds(0, 0, this.p, this.v);
        if (e()) {
            this.b.draw(canvas);
            canvas.translate((float) this.o, (float) i3);
            canvas.scale(-1.0f, 1.0f);
            this.a.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            canvas.translate((float) (-this.o), (float) (-i3));
            return;
        }
        canvas.translate((float) i2, 0.0f);
        this.b.draw(canvas);
        canvas.translate(0.0f, (float) i3);
        this.a.draw(canvas);
        canvas.translate((float) (-i2), (float) (-i3));
    }

    private void b(Canvas canvas) {
        int i2 = this.v - this.s;
        int i3 = this.g - (this.f / 2);
        this.f362q.setBounds(0, 0, this.f, this.s);
        this.r.setBounds(0, 0, this.u, this.t);
        canvas.translate(0.0f, (float) i2);
        this.r.draw(canvas);
        canvas.translate((float) i3, 0.0f);
        this.f362q.draw(canvas);
        canvas.translate((float) (-i3), (float) (-i2));
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, int i3) {
        boolean z2;
        int computeVerticalScrollRange = this.w.computeVerticalScrollRange();
        int i4 = this.v;
        this.x = computeVerticalScrollRange - i4 > 0 && this.v >= this.m;
        int computeHorizontalScrollRange = this.w.computeHorizontalScrollRange();
        int i5 = this.u;
        if (computeHorizontalScrollRange - i5 <= 0 || this.u < this.m) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.y = z2;
        if (this.x || this.y) {
            if (this.x) {
                this.d = (int) (((((float) i3) + (((float) i4) / 2.0f)) * ((float) i4)) / ((float) computeVerticalScrollRange));
                this.c = Math.min(i4, (i4 * i4) / computeVerticalScrollRange);
            }
            if (this.y) {
                this.g = (int) (((((float) i2) + (((float) i5) / 2.0f)) * ((float) i5)) / ((float) computeHorizontalScrollRange));
                this.f = Math.min(i5, (i5 * i5) / computeHorizontalScrollRange);
            }
            if (this.z == 0 || this.z == 1) {
                a(1);
            }
        } else if (this.z != 0) {
            a(0);
        }
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.z == 1) {
            boolean a2 = a(motionEvent.getX(), motionEvent.getY());
            boolean b2 = b(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0 || (!a2 && !b2)) {
                return false;
            }
            if (b2) {
                this.A = 1;
                this.h = (float) ((int) motionEvent.getX());
            } else if (a2) {
                this.A = 2;
                this.e = (float) ((int) motionEvent.getY());
            }
            a(2);
            return true;
        } else if (this.z != 2) {
            return false;
        } else {
            return true;
        }
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.z != 0) {
            if (motionEvent.getAction() == 0) {
                boolean a2 = a(motionEvent.getX(), motionEvent.getY());
                boolean b2 = b(motionEvent.getX(), motionEvent.getY());
                if (a2 || b2) {
                    if (b2) {
                        this.A = 1;
                        this.h = (float) ((int) motionEvent.getX());
                    } else if (a2) {
                        this.A = 2;
                        this.e = (float) ((int) motionEvent.getY());
                    }
                    a(2);
                }
            } else if (motionEvent.getAction() == 1 && this.z == 2) {
                this.e = 0.0f;
                this.h = 0.0f;
                a(1);
                this.A = 0;
            } else if (motionEvent.getAction() == 2 && this.z == 2) {
                b();
                if (this.A == 1) {
                    b(motionEvent.getX());
                }
                if (this.A == 2) {
                    a(motionEvent.getY());
                }
            }
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z2) {
    }

    private void a(float f2) {
        int[] g2 = g();
        float max = Math.max((float) g2[0], Math.min((float) g2[1], f2));
        if (Math.abs(((float) this.d) - max) >= 2.0f) {
            int a2 = a(this.e, max, g2, this.w.computeVerticalScrollRange(), this.w.computeVerticalScrollOffset(), this.v);
            if (a2 != 0) {
                this.w.scrollBy(0, a2);
            }
            this.e = max;
        }
    }

    private void b(float f2) {
        int[] h2 = h();
        float max = Math.max((float) h2[0], Math.min((float) h2[1], f2));
        if (Math.abs(((float) this.g) - max) >= 2.0f) {
            int a2 = a(this.h, max, h2, this.w.computeHorizontalScrollRange(), this.w.computeHorizontalScrollOffset(), this.u);
            if (a2 != 0) {
                this.w.scrollBy(a2, 0);
            }
            this.h = max;
        }
    }

    private int a(float f2, float f3, int[] iArr, int i2, int i3, int i4) {
        int i5 = iArr[1] - iArr[0];
        if (i5 == 0) {
            return 0;
        }
        int i6 = i2 - i4;
        int i7 = (int) (((f3 - f2) / ((float) i5)) * ((float) i6));
        int i8 = i3 + i7;
        if (i8 >= i6 || i8 < 0) {
            return 0;
        }
        return i7;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(float f2, float f3) {
        if (!e() ? f2 >= ((float) (this.u - this.o)) : f2 <= ((float) (this.o / 2))) {
            if (f3 >= ((float) (this.d - (this.c / 2))) && f3 <= ((float) (this.d + (this.c / 2)))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(float f2, float f3) {
        return f3 >= ((float) (this.v - this.s)) && f2 >= ((float) (this.g - (this.f / 2))) && f2 <= ((float) (this.g + (this.f / 2)));
    }

    private int[] g() {
        this.B[0] = this.n;
        this.B[1] = this.v - this.n;
        return this.B;
    }

    private int[] h() {
        this.C[0] = this.n;
        this.C[1] = this.u - this.n;
        return this.C;
    }
}
