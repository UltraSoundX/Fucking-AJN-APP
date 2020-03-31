package com.github.chrisbanes.photoview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* compiled from: CustomGestureDetector */
class b {
    private int a = -1;
    private int b = 0;
    private final ScaleGestureDetector c;
    private VelocityTracker d;
    private boolean e;
    private float f;
    private float g;
    private final float h;
    private final float i;
    /* access modifiers changed from: private */
    public c j;

    b(Context context, c cVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.i = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.h = (float) viewConfiguration.getScaledTouchSlop();
        this.j = cVar;
        this.c = new ScaleGestureDetector(context, new OnScaleGestureListener() {
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                b.this.j.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        });
    }

    private float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.b);
        } catch (Exception e2) {
            return motionEvent.getX();
        }
    }

    private float c(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.b);
        } catch (Exception e2) {
            return motionEvent.getY();
        }
    }

    public boolean a() {
        return this.c.isInProgress();
    }

    public boolean b() {
        return this.e;
    }

    public boolean a(MotionEvent motionEvent) {
        try {
            this.c.onTouchEvent(motionEvent);
            return d(motionEvent);
        } catch (IllegalArgumentException e2) {
            return true;
        }
    }

    private boolean d(MotionEvent motionEvent) {
        int i2;
        int i3 = 0;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.a = motionEvent.getPointerId(0);
                this.d = VelocityTracker.obtain();
                if (this.d != null) {
                    this.d.addMovement(motionEvent);
                }
                this.f = b(motionEvent);
                this.g = c(motionEvent);
                this.e = false;
                break;
            case 1:
                this.a = -1;
                if (this.e && this.d != null) {
                    this.f = b(motionEvent);
                    this.g = c(motionEvent);
                    this.d.addMovement(motionEvent);
                    this.d.computeCurrentVelocity(1000);
                    float xVelocity = this.d.getXVelocity();
                    float yVelocity = this.d.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.i) {
                        this.j.a(this.f, this.g, -xVelocity, -yVelocity);
                    }
                }
                if (this.d != null) {
                    this.d.recycle();
                    this.d = null;
                    break;
                }
                break;
            case 2:
                float b2 = b(motionEvent);
                float c2 = c(motionEvent);
                float f2 = b2 - this.f;
                float f3 = c2 - this.g;
                if (!this.e) {
                    this.e = Math.sqrt((double) ((f2 * f2) + (f3 * f3))) >= ((double) this.h);
                }
                if (this.e) {
                    this.j.a(f2, f3);
                    this.f = b2;
                    this.g = c2;
                    if (this.d != null) {
                        this.d.addMovement(motionEvent);
                        break;
                    }
                }
                break;
            case 3:
                this.a = -1;
                if (this.d != null) {
                    this.d.recycle();
                    this.d = null;
                    break;
                }
                break;
            case 6:
                int a2 = j.a(motionEvent.getAction());
                if (motionEvent.getPointerId(a2) == this.a) {
                    if (a2 == 0) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    this.a = motionEvent.getPointerId(i2);
                    this.f = motionEvent.getX(i2);
                    this.g = motionEvent.getY(i2);
                    break;
                }
                break;
        }
        if (this.a != -1) {
            i3 = this.a;
        }
        this.b = motionEvent.findPointerIndex(i3);
        return true;
    }
}
