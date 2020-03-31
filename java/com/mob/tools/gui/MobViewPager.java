package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class MobViewPager extends ViewGroup {
    private static final int DECELERATION = 10;
    private static final int SNAP_VELOCITY = 500;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private ViewPagerAdapter adapter;
    private View currentPage;
    private int currentScreen;
    private int flingVelocity;
    private float lastMotionX;
    private float lastMotionY;
    private int maximumVelocity;
    private View nextPage;
    private int pageWidth;
    private View previousPage;
    private int screenCount;
    private Scroller scroller;
    private boolean skipScreen;
    private int touchSlop;
    private int touchState;
    private VelocityTracker velocityTracker;

    public MobViewPager(Context context) {
        this(context, null);
    }

    public MobViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MobViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.scroller = new Scroller(context, new Interpolator() {
            public float getInterpolation(float f) {
                return (2.0f - f) * f;
            }
        });
        this.touchState = 0;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void setAdapter(ViewPagerAdapter viewPagerAdapter) {
        if (this.adapter != null) {
            this.adapter.setMobViewPager(null);
        }
        this.adapter = viewPagerAdapter;
        if (this.adapter != null) {
            this.adapter.setMobViewPager(this);
        }
        if (viewPagerAdapter == null) {
            this.currentScreen = 0;
            removeAllViews();
            return;
        }
        this.screenCount = viewPagerAdapter.getCount();
        if (this.screenCount <= 0) {
            this.currentScreen = 0;
            removeAllViews();
        } else if (this.screenCount <= this.currentScreen) {
            scrollToScreenOnUIThread(this.screenCount - 1, true);
        } else {
            removeAllViews();
            if (this.currentScreen > 0) {
                this.previousPage = viewPagerAdapter.getView(this.currentScreen - 1, this.previousPage, this);
                addView(this.previousPage);
            }
            this.currentPage = viewPagerAdapter.getView(this.currentScreen, this.currentPage, this);
            addView(this.currentPage);
            if (this.currentScreen < this.screenCount - 1) {
                this.nextPage = viewPagerAdapter.getView(this.currentScreen + 1, this.nextPage, this);
                addView(this.nextPage);
            }
        }
    }

    public int getCurrentScreen() {
        return this.currentScreen;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.adapter != null && this.screenCount > 0) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                getChildAt(i3).measure(makeMeasureSpec, makeMeasureSpec2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.adapter != null && this.screenCount > 0) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int i7 = this.currentScreen * i5;
            if (this.currentScreen > 0) {
                this.previousPage.layout(i7 - i5, 0, i7, i6);
            }
            this.currentPage.layout(i7, 0, i7 + i5, i6);
            if (this.currentScreen < this.screenCount - 1) {
                this.nextPage.layout(i7 + i5, 0, i5 + i7 + i5, i6);
            }
            if (this.pageWidth != getWidth()) {
                int i8 = this.pageWidth;
                this.pageWidth = getWidth();
                if (i8 != 0) {
                    adjustScroller();
                }
            }
        }
    }

    private void adjustScroller() {
        this.skipScreen = true;
        if (this.currentPage != null && getFocusedChild() == this.currentPage) {
            this.currentPage.clearFocus();
        }
        int width = (this.currentScreen * getWidth()) - getScrollX();
        this.scroller.abortAnimation();
        if (width != 0) {
            this.scroller.startScroll(getScrollX(), 0, width, 0, 0);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.adapter != null && this.screenCount > 0) {
            long drawingTime = getDrawingTime();
            if (this.currentScreen > 0) {
                drawChild(canvas, this.previousPage, drawingTime);
            }
            drawChild(canvas, this.currentPage, drawingTime);
            if (this.currentScreen < this.screenCount - 1) {
                drawChild(canvas, this.nextPage, drawingTime);
            }
        }
    }

    public void computeScroll() {
        if (this.adapter != null && this.screenCount > 0) {
            if (this.scroller.computeScrollOffset()) {
                scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
                postInvalidate();
            } else {
                int i = this.currentScreen;
                int currX = this.scroller.getCurrX();
                int width = getWidth();
                int i2 = currX / width;
                if (currX % width > width / 2) {
                    i2++;
                }
                this.currentScreen = Math.max(0, Math.min(i2, this.screenCount - 1));
                if (i != this.currentScreen) {
                    onScreenChange(i);
                }
            }
            if (this.adapter != null) {
                this.adapter.onScreenChanging(((float) getScrollX()) / ((float) getWidth()));
            }
        }
    }

    private void onScreenChange(int i) {
        if (this.adapter != null) {
            if (this.skipScreen && Math.abs(i - this.currentScreen) > 2) {
                removeAllViews();
                if (this.currentScreen > 0) {
                    this.previousPage = this.adapter.getView(this.currentScreen - 1, this.previousPage, this);
                    addView(this.previousPage);
                }
                this.currentPage = this.adapter.getView(this.currentScreen, this.currentPage, this);
                addView(this.currentPage);
                if (this.currentScreen < this.screenCount - 1) {
                    this.nextPage = this.adapter.getView(this.currentScreen + 1, this.nextPage, this);
                    addView(this.nextPage);
                }
            } else if (this.currentScreen > i) {
                for (int i2 = 0; i2 < this.currentScreen - i; i2++) {
                    int i3 = i + i2 + 1;
                    View view = this.previousPage;
                    this.previousPage = this.currentPage;
                    this.currentPage = this.nextPage;
                    if (getChildCount() >= 3) {
                        removeViewAt(0);
                    }
                    if (i3 < this.screenCount - 1) {
                        this.nextPage = this.adapter.getView(i3 + 1, view, this);
                        addView(this.nextPage);
                    } else {
                        this.nextPage = view;
                    }
                }
            } else {
                for (int i4 = 0; i4 < i - this.currentScreen; i4++) {
                    int i5 = (i - i4) - 1;
                    View view2 = this.nextPage;
                    this.nextPage = this.currentPage;
                    this.currentPage = this.previousPage;
                    if (getChildCount() >= 3) {
                        removeViewAt(2);
                    }
                    if (i5 > 0) {
                        this.previousPage = this.adapter.getView(i5 - 1, view2, this);
                        addView(this.previousPage, 0);
                    } else {
                        this.previousPage = view2;
                    }
                }
            }
            this.adapter.onScreenChange(this.currentScreen, i);
        }
    }

    public void scrollLeft(boolean z) {
        if (this.currentScreen > 0) {
            scrollToScreen(this.currentScreen - 1, z);
        }
    }

    public void scrollRight(boolean z) {
        if (this.currentScreen < this.screenCount - 1) {
            scrollToScreen(this.currentScreen + 1, z);
        }
    }

    public void scrollToScreen(final int i, final boolean z) {
        post(new Runnable() {
            public void run() {
                MobViewPager.this.scrollToScreenOnUIThread(i, z);
            }
        });
    }

    @Deprecated
    public void scrollToScreen(int i, boolean z, boolean z2) {
        scrollToScreen(i, z);
    }

    /* access modifiers changed from: private */
    public void scrollToScreenOnUIThread(int i, boolean z) {
        int i2;
        this.skipScreen = z;
        if (this.currentPage != null && getFocusedChild() == this.currentPage) {
            this.currentPage.clearFocus();
        }
        int width = (getWidth() * i) - getScrollX();
        this.scroller.abortAnimation();
        if (width != 0) {
            if (!z) {
                int abs = Math.abs(width) / 2;
                if (this.flingVelocity != 0) {
                    int abs2 = Math.abs(this.flingVelocity);
                    i2 = (int) (((((double) abs2) - Math.sqrt((double) ((abs2 * abs2) - (Math.abs(width) * 20)))) * 1000.0d) / 10.0d);
                } else {
                    i2 = 0;
                }
                if (i2 == 0 || i2 > abs) {
                    i2 = abs;
                }
            } else {
                i2 = 0;
            }
            this.scroller.startScroll(getScrollX(), 0, width, 0, i2);
        }
        invalidate();
    }

    public boolean dispatchUnhandledMove(View view, int i) {
        if (this.adapter == null) {
            return super.dispatchUnhandledMove(view, i);
        }
        if (i == 17) {
            if (this.currentScreen > 0) {
                scrollToScreenOnUIThread(this.currentScreen - 1, false);
                return true;
            }
        } else if (i == 66 && this.currentScreen < this.screenCount - 1) {
            scrollToScreenOnUIThread(this.currentScreen + 1, false);
            return true;
        }
        return super.dispatchUnhandledMove(view, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.touchState != 0) {
            return true;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        switch (action) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.lastMotionX = x;
                this.lastMotionY = y;
                this.touchState = this.scroller.isFinished() ? 0 : 1;
                break;
            case 1:
            case 3:
                if (this.velocityTracker != null) {
                    this.velocityTracker.recycle();
                    this.velocityTracker = null;
                }
                this.touchState = 0;
                break;
            case 2:
                handleInterceptMove(motionEvent);
                break;
        }
        if (this.touchState == 0) {
            return false;
        }
        return true;
    }

    private void handleInterceptMove(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        int abs = (int) Math.abs(x - this.lastMotionX);
        if (((int) Math.abs(motionEvent.getY() - this.lastMotionY)) < abs && abs > this.touchSlop) {
            this.touchState = 1;
            this.lastMotionX = x;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.adapter == null) {
            return false;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                if (this.touchState != 0) {
                    if (!this.scroller.isFinished()) {
                        this.scroller.abortAnimation();
                    }
                    this.lastMotionX = x;
                    break;
                }
                break;
            case 1:
                if (this.touchState == 1) {
                    this.velocityTracker.computeCurrentVelocity(1000, (float) this.maximumVelocity);
                    this.flingVelocity = (int) this.velocityTracker.getXVelocity();
                    if (this.flingVelocity > 500 && this.currentScreen > 0) {
                        scrollToScreenOnUIThread(this.currentScreen - 1, false);
                    } else if (this.flingVelocity >= -500 || this.currentScreen >= this.screenCount - 1) {
                        int width = getWidth();
                        scrollToScreenOnUIThread((getScrollX() + (width / 2)) / width, false);
                    } else {
                        scrollToScreenOnUIThread(this.currentScreen + 1, false);
                    }
                    if (this.velocityTracker != null) {
                        this.velocityTracker.recycle();
                        this.velocityTracker = null;
                    }
                }
                this.touchState = 0;
                break;
            case 2:
                if (this.touchState != 1) {
                    if (onInterceptTouchEvent(motionEvent) && this.touchState == 1) {
                        handleScrollMove(motionEvent);
                        break;
                    }
                } else {
                    handleScrollMove(motionEvent);
                    break;
                }
            case 3:
                this.touchState = 0;
                break;
        }
        return true;
    }

    private void handleScrollMove(MotionEvent motionEvent) {
        if (this.adapter != null) {
            float x = motionEvent.getX();
            int i = (int) (this.lastMotionX - x);
            this.lastMotionX = x;
            if (i < 0) {
                if (getScrollX() > 0) {
                    scrollBy(Math.max(-getScrollX(), i), 0);
                }
            } else if (i > 0 && getChildCount() != 0) {
                int right = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth();
                if (right > 0) {
                    scrollBy(Math.min(right, i), 0);
                }
            }
        }
    }
}
