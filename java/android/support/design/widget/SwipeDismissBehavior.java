package android.support.design.widget;

import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    ViewDragHelper a;
    a b;
    int c = 2;
    float d = 0.5f;
    float e = 0.0f;
    float f = 0.5f;
    private boolean g;
    private float h = 0.0f;
    private boolean i;
    private final Callback j = new Callback() {
        private int b;
        private int c = -1;

        public boolean tryCaptureView(View view, int i) {
            return this.c == -1 && SwipeDismissBehavior.this.a(view);
        }

        public void onViewCaptured(View view, int i) {
            this.c = i;
            this.b = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        public void onViewDragStateChanged(int i) {
            if (SwipeDismissBehavior.this.b != null) {
                SwipeDismissBehavior.this.b.a(i);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            this.c = -1;
            int width = view.getWidth();
            boolean z = false;
            if (a(view, f)) {
                i = view.getLeft() < this.b ? this.b - width : this.b + width;
                z = true;
            } else {
                i = this.b;
            }
            if (SwipeDismissBehavior.this.a.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, new b(view, z));
            } else if (z && SwipeDismissBehavior.this.b != null) {
                SwipeDismissBehavior.this.b.a(view);
            }
        }

        private boolean a(View view, float f) {
            if (f != 0.0f) {
                boolean z = ViewCompat.getLayoutDirection(view) == 1;
                if (SwipeDismissBehavior.this.c == 2) {
                    return true;
                }
                if (SwipeDismissBehavior.this.c == 0) {
                    if (z) {
                        if (f >= 0.0f) {
                            return false;
                        }
                        return true;
                    } else if (f <= 0.0f) {
                        return false;
                    } else {
                        return true;
                    }
                } else if (SwipeDismissBehavior.this.c != 1) {
                    return false;
                } else {
                    if (z) {
                        if (f <= 0.0f) {
                            return false;
                        }
                        return true;
                    } else if (f >= 0.0f) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                if (Math.abs(view.getLeft() - this.b) < Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.d)) {
                    return false;
                }
                return true;
            }
        }

        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int width;
            int width2;
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            if (SwipeDismissBehavior.this.c == 0) {
                if (z) {
                    width = this.b - view.getWidth();
                    width2 = this.b;
                } else {
                    width = this.b;
                    width2 = this.b + view.getWidth();
                }
            } else if (SwipeDismissBehavior.this.c != 1) {
                width = this.b - view.getWidth();
                width2 = this.b + view.getWidth();
            } else if (z) {
                width = this.b;
                width2 = this.b + view.getWidth();
            } else {
                width = this.b - view.getWidth();
                width2 = this.b;
            }
            return SwipeDismissBehavior.a(width, i, width2);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = ((float) this.b) + (((float) view.getWidth()) * SwipeDismissBehavior.this.e);
            float width2 = ((float) this.b) + (((float) view.getWidth()) * SwipeDismissBehavior.this.f);
            if (((float) i) <= width) {
                view.setAlpha(1.0f);
            } else if (((float) i) >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.b(width, width2, (float) i), 1.0f));
            }
        }
    };

    public interface a {
        void a(int i);

        void a(View view);
    }

    private class b implements Runnable {
        private final View b;
        private final boolean c;

        b(View view, boolean z) {
            this.b = view;
            this.c = z;
        }

        public void run() {
            if (SwipeDismissBehavior.this.a != null && SwipeDismissBehavior.this.a.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.b, this);
            } else if (this.c && SwipeDismissBehavior.this.b != null) {
                SwipeDismissBehavior.this.b.a(this.b);
            }
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public void a(float f2) {
        this.e = a(0.0f, f2, 1.0f);
    }

    public void b(float f2) {
        this.f = a(0.0f, f2, 1.0f);
    }

    public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.g;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.g = coordinatorLayout.a((View) v, (int) motionEvent.getX(), (int) motionEvent.getY());
                z = this.g;
                break;
            case 1:
            case 3:
                this.g = false;
                break;
        }
        if (!z) {
            return false;
        }
        a((ViewGroup) coordinatorLayout);
        return this.a.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        this.a.processTouchEvent(motionEvent);
        return true;
    }

    public boolean a(View view) {
        return true;
    }

    private void a(ViewGroup viewGroup) {
        ViewDragHelper create;
        if (this.a == null) {
            if (this.i) {
                create = ViewDragHelper.create(viewGroup, this.h, this.j);
            } else {
                create = ViewDragHelper.create(viewGroup, this.j);
            }
            this.a = create;
        }
    }

    static float a(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    static int a(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    static float b(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }
}
