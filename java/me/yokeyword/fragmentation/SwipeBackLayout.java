package me.yokeyword.fragmentation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentationMagician;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import java.lang.reflect.Field;
import java.util.List;
import me.yokeyword.fragmentation_swipeback.R;

public class SwipeBackLayout extends FrameLayout {
    /* access modifiers changed from: private */
    public float a;
    /* access modifiers changed from: private */
    public ViewDragHelper b;
    /* access modifiers changed from: private */
    public float c;
    private float d;
    /* access modifiers changed from: private */
    public FragmentActivity e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public d g;
    /* access modifiers changed from: private */
    public Fragment h;
    /* access modifiers changed from: private */
    public Drawable i;
    /* access modifiers changed from: private */
    public Drawable j;
    private Rect k;
    /* access modifiers changed from: private */
    public int l;
    private boolean m;
    /* access modifiers changed from: private */
    public int n;
    private float o;
    /* access modifiers changed from: private */
    public boolean p;

    /* renamed from: q reason: collision with root package name */
    private boolean f440q;
    /* access modifiers changed from: private */
    public int r;
    /* access modifiers changed from: private */
    public int s;
    /* access modifiers changed from: private */
    public List<b> t;
    private Context u;

    public enum a {
        MAX,
        MIN,
        MED
    }

    public interface b {
        void a(float f);

        void a(int i);

        void b(int i);
    }

    private class c extends Callback {
        private c() {
        }

        public boolean tryCaptureView(View view, int i) {
            boolean isEdgeTouched = SwipeBackLayout.this.b.isEdgeTouched(SwipeBackLayout.this.l, i);
            if (isEdgeTouched) {
                if (SwipeBackLayout.this.b.isEdgeTouched(1, i)) {
                    SwipeBackLayout.this.n = 1;
                } else if (SwipeBackLayout.this.b.isEdgeTouched(2, i)) {
                    SwipeBackLayout.this.n = 2;
                }
                if (SwipeBackLayout.this.t != null) {
                    for (b b : SwipeBackLayout.this.t) {
                        b.b(SwipeBackLayout.this.n);
                    }
                }
                if (SwipeBackLayout.this.h != null) {
                    View view2 = SwipeBackLayout.this.h.getView();
                    if (view2 != null && view2.getVisibility() != 0) {
                        view2.setVisibility(0);
                    }
                } else if (SwipeBackLayout.this.g != null) {
                    List activeFragments = FragmentationMagician.getActiveFragments(((Fragment) SwipeBackLayout.this.g).getFragmentManager());
                    if (activeFragments != null && activeFragments.size() > 1) {
                        int indexOf = activeFragments.indexOf(SwipeBackLayout.this.g) - 1;
                        while (true) {
                            if (indexOf < 0) {
                                break;
                            }
                            Fragment fragment = (Fragment) activeFragments.get(indexOf);
                            if (fragment != null && fragment.getView() != null) {
                                fragment.getView().setVisibility(0);
                                SwipeBackLayout.this.h = fragment;
                                break;
                            }
                            indexOf--;
                        }
                    }
                }
            }
            return isEdgeTouched;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if ((SwipeBackLayout.this.n & 1) != 0) {
                return Math.min(view.getWidth(), Math.max(i, 0));
            }
            if ((SwipeBackLayout.this.n & 2) != 0) {
                return Math.min(0, Math.max(i, -view.getWidth()));
            }
            return 0;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            super.onViewPositionChanged(view, i, i2, i3, i4);
            if ((SwipeBackLayout.this.n & 1) != 0) {
                SwipeBackLayout.this.c = Math.abs(((float) i) / ((float) (SwipeBackLayout.this.f.getWidth() + SwipeBackLayout.this.i.getIntrinsicWidth())));
            } else if ((SwipeBackLayout.this.n & 2) != 0) {
                SwipeBackLayout.this.c = Math.abs(((float) i) / ((float) (SwipeBackLayout.this.f.getWidth() + SwipeBackLayout.this.j.getIntrinsicWidth())));
            }
            SwipeBackLayout.this.r = i;
            SwipeBackLayout.this.s = i2;
            SwipeBackLayout.this.invalidate();
            if (SwipeBackLayout.this.t != null && SwipeBackLayout.this.b.getViewDragState() == 1 && SwipeBackLayout.this.c <= 1.0f && SwipeBackLayout.this.c > 0.0f) {
                for (b a2 : SwipeBackLayout.this.t) {
                    a2.a(SwipeBackLayout.this.c);
                }
            }
            if (SwipeBackLayout.this.c <= 1.0f) {
                return;
            }
            if (SwipeBackLayout.this.g != null) {
                if (!SwipeBackLayout.this.p && !((Fragment) SwipeBackLayout.this.g).isDetached()) {
                    SwipeBackLayout.this.d();
                    SwipeBackLayout.this.g.b().l();
                }
            } else if (!SwipeBackLayout.this.e.isFinishing()) {
                SwipeBackLayout.this.d();
                SwipeBackLayout.this.e.finish();
                SwipeBackLayout.this.e.overridePendingTransition(0, 0);
            }
        }

        public int getViewHorizontalDragRange(View view) {
            if (SwipeBackLayout.this.g != null) {
                return 1;
            }
            if (!(SwipeBackLayout.this.e instanceof me.yokeyword.fragmentation_swipeback.a.a) || !((me.yokeyword.fragmentation_swipeback.a.a) SwipeBackLayout.this.e).swipeBackPriority()) {
                return 0;
            }
            return 1;
        }

        public void onViewReleased(View view, float f, float f2) {
            int width = view.getWidth();
            int i = (SwipeBackLayout.this.n & 1) != 0 ? (f > 0.0f || (f == 0.0f && SwipeBackLayout.this.c > SwipeBackLayout.this.a)) ? width + SwipeBackLayout.this.i.getIntrinsicWidth() + 10 : 0 : (SwipeBackLayout.this.n & 2) != 0 ? (f < 0.0f || (f == 0.0f && SwipeBackLayout.this.c > SwipeBackLayout.this.a)) ? -(width + SwipeBackLayout.this.j.getIntrinsicWidth() + 10) : 0 : 0;
            SwipeBackLayout.this.b.settleCapturedViewAt(i, 0);
            SwipeBackLayout.this.invalidate();
        }

        public void onViewDragStateChanged(int i) {
            super.onViewDragStateChanged(i);
            if (SwipeBackLayout.this.t != null) {
                for (b a2 : SwipeBackLayout.this.t) {
                    a2.a(i);
                }
            }
        }

        public void onEdgeTouched(int i, int i2) {
            super.onEdgeTouched(i, i2);
            if ((SwipeBackLayout.this.l & i) != 0) {
                SwipeBackLayout.this.n = i;
            }
        }
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeBackLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 0.4f;
        this.k = new Rect();
        this.m = true;
        this.o = 0.33f;
        this.u = context;
        c();
    }

    private void c() {
        this.b = ViewDragHelper.create(this, new c());
        a(R.drawable.shadow_left, 1);
        setEdgeOrientation(1);
    }

    public ViewDragHelper getViewDragHelper() {
        return this.b;
    }

    public void setScrollThresHold(float f2) {
        if (f2 >= 1.0f || f2 <= 0.0f) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        this.a = f2;
    }

    public void setParallaxOffset(float f2) {
        this.o = f2;
    }

    public void setEdgeOrientation(int i2) {
        this.l = i2;
        this.b.setEdgeTrackingEnabled(i2);
        if (i2 == 2 || i2 == 3) {
            a(R.drawable.shadow_right, 2);
        }
    }

    public void a(Drawable drawable, int i2) {
        if ((i2 & 1) != 0) {
            this.i = drawable;
        } else if ((i2 & 2) != 0) {
            this.j = drawable;
        }
        invalidate();
    }

    public void a(int i2, int i3) {
        a(getResources().getDrawable(i2), i3);
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        boolean z = view == this.f;
        boolean drawChild = super.drawChild(canvas, view, j2);
        if (z && this.d > 0.0f && this.b.getViewDragState() != 0) {
            a(canvas, view);
            b(canvas, view);
        }
        return drawChild;
    }

    private void a(Canvas canvas, View view) {
        Rect rect = this.k;
        view.getHitRect(rect);
        if ((this.n & 1) != 0) {
            this.i.setBounds(rect.left - this.i.getIntrinsicWidth(), rect.top, rect.left, rect.bottom);
            this.i.setAlpha((int) (this.d * 255.0f));
            this.i.draw(canvas);
        } else if ((this.n & 2) != 0) {
            this.j.setBounds(rect.right, rect.top, rect.right + this.j.getIntrinsicWidth(), rect.bottom);
            this.j.setAlpha((int) (this.d * 255.0f));
            this.j.draw(canvas);
        }
    }

    private void b(Canvas canvas, View view) {
        int i2 = ((int) (153.0f * this.d)) << 24;
        if ((this.n & 1) != 0) {
            canvas.clipRect(0, 0, view.getLeft(), getHeight());
        } else if ((this.n & 2) != 0) {
            canvas.clipRect(view.getRight(), 0, getRight(), getHeight());
        }
        canvas.drawColor(i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.f440q = true;
        if (this.f != null) {
            this.f.layout(this.r, this.s, this.r + this.f.getMeasuredWidth(), this.s + this.f.getMeasuredHeight());
        }
        this.f440q = false;
    }

    public void requestLayout() {
        if (!this.f440q) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        float f2 = 0.0f;
        this.d = 1.0f - this.c;
        if (this.d >= 0.0f) {
            if (this.b.continueSettling(true)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            if (this.h != null && this.h.getView() != null) {
                if (this.p) {
                    this.h.getView().setX(0.0f);
                } else if (this.b.getCapturedView() != null) {
                    int left = (int) (((float) (this.b.getCapturedView().getLeft() - getWidth())) * this.o * this.d);
                    View view = this.h.getView();
                    if (left <= 0) {
                        f2 = (float) left;
                    }
                    view.setX(f2);
                }
            }
        }
    }

    public void a() {
        this.p = true;
    }

    public void a(d dVar, View view) {
        this.g = dVar;
        this.f = view;
    }

    public void b() {
        if (this.h != null && this.h.getView() != null) {
            this.h.getView().setVisibility(8);
        }
    }

    public void a(FragmentActivity fragmentActivity) {
        this.e = fragmentActivity;
        TypedArray obtainStyledAttributes = fragmentActivity.getTheme().obtainStyledAttributes(new int[]{16842836});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        ViewGroup viewGroup = (ViewGroup) fragmentActivity.getWindow().getDecorView();
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(0);
        viewGroup2.setBackgroundResource(resourceId);
        viewGroup.removeView(viewGroup2);
        addView(viewGroup2);
        setContentView(viewGroup2);
        viewGroup.addView(this);
    }

    public void b(d dVar, View view) {
        addView(view);
        a(dVar, view);
    }

    private void setContentView(View view) {
        this.f = view;
    }

    public void setEnableGesture(boolean z) {
        this.m = z;
    }

    public void setEdgeLevel(a aVar) {
        a(-1, aVar);
    }

    public void setEdgeLevel(int i2) {
        a(i2, (a) null);
    }

    private void a(int i2, a aVar) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.u.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            Field declaredField = this.b.getClass().getDeclaredField("mEdgeSize");
            declaredField.setAccessible(true);
            if (i2 >= 0) {
                declaredField.setInt(this.b, i2);
            } else if (aVar == a.MAX) {
                declaredField.setInt(this.b, displayMetrics.widthPixels);
            } else if (aVar == a.MED) {
                declaredField.setInt(this.b, displayMetrics.widthPixels / 2);
            } else {
                declaredField.setInt(this.b, (int) ((displayMetrics.density * 20.0f) + 0.5f));
            }
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.t != null) {
            for (b a2 : this.t) {
                a2.a(3);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.m) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        try {
            return this.b.shouldInterceptTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.m) {
            return super.onTouchEvent(motionEvent);
        }
        try {
            this.b.processTouchEvent(motionEvent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
