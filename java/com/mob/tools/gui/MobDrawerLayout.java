package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.mob.tools.utils.ResHelper;

public class MobDrawerLayout extends ViewGroup {
    private static final int SNAP_VELOCITY = 500;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private FrameLayout bodyContainer;
    private FrameLayout drawerContainer;
    private double drawerWidth;
    private float lastMotionX;
    private float lastMotionY;
    private OnDrawerStateChangeListener listener;
    private boolean lockScroll;
    private int maximumVelocity;
    private boolean opened;
    private Paint paint;
    private Scroller scroller;
    private int touchSlop;
    private int touchState;
    private DrawerType type;
    private VelocityTracker velocityTracker;

    public enum DrawerType {
        LEFT_COVER,
        RIGHT_COVER,
        LEFT_BOTTOM,
        RIGHT_BOTTOM,
        LEFT_PUSH,
        RIGHT_PUSH
    }

    public interface OnDrawerStateChangeListener {
        void onClosing(MobDrawerLayout mobDrawerLayout, int i);

        void onOpening(MobDrawerLayout mobDrawerLayout, int i);
    }

    public MobDrawerLayout(Context context) {
        super(context);
        init(context);
    }

    public MobDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MobDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.scroller = SmoothScroller.DEFAULT.getScroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.type = DrawerType.LEFT_COVER;
        this.drawerWidth = 0.8d;
        this.touchState = 0;
        this.paint = new Paint();
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
            }
        };
        this.bodyContainer = new FrameLayout(context);
        this.bodyContainer.setOnClickListener(r0);
        this.drawerContainer = new FrameLayout(context);
        this.drawerContainer.setOnClickListener(r0);
        addView(this.bodyContainer);
        addView(this.drawerContainer);
    }

    public void setDrawerType(DrawerType drawerType) {
        if (drawerType == null) {
            drawerType = DrawerType.LEFT_COVER;
        }
        if (this.type != drawerType) {
            this.type = drawerType;
            switch (drawerType) {
                case LEFT_COVER:
                case RIGHT_COVER:
                    this.drawerContainer.bringToFront();
                    break;
                default:
                    this.bodyContainer.bringToFront();
                    break;
            }
            postInvalidate();
        }
    }

    public void setDrawerWidth(double d) {
        double d2;
        double d3 = 1.0d;
        if (d < 0.0d) {
            d2 = 0.800000011920929d;
        } else {
            d2 = d;
        }
        if (d2 <= 1.0d) {
            d3 = d2;
        }
        if (this.drawerWidth != d3) {
            this.drawerWidth = d3;
            postInvalidate();
        }
    }

    public void open(boolean z) {
        switchDrawer(true, z);
    }

    public void open() {
        open(false);
    }

    public void close(boolean z) {
        switchDrawer(false, z);
    }

    public void close() {
        close(false);
    }

    public boolean isOpened() {
        return this.opened;
    }

    public void setOnDrawerStateChangeListener(OnDrawerStateChangeListener onDrawerStateChangeListener) {
        this.listener = onDrawerStateChangeListener;
    }

    public void setBody(View view) {
        if (!ResHelper.isEqual(this.bodyContainer.getChildCount() == 0 ? null : this.bodyContainer.getChildAt(0), view)) {
            this.bodyContainer.removeAllViews();
            this.bodyContainer.addView(view);
        }
    }

    public void setDrawer(View view) {
        if (!ResHelper.isEqual(this.drawerContainer.getChildCount() == 0 ? null : this.drawerContainer.getChildAt(0), view)) {
            this.drawerContainer.removeAllViews();
            this.drawerContainer.addView(view);
        }
    }

    public void setLockScroll(boolean z) {
        this.lockScroll = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.bodyContainer.measure(makeMeasureSpec, makeMeasureSpec2);
        this.drawerContainer.measure(MeasureSpec.makeMeasureSpec((int) (((double) measuredWidth) * this.drawerWidth), 1073741824), makeMeasureSpec2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = (int) (((double) i5) * this.drawerWidth);
        if (isOpened()) {
            switch (this.type) {
                case LEFT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case RIGHT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                case LEFT_BOTTOM:
                    this.bodyContainer.layout(i7, 0, i5 + i7, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case LEFT_PUSH:
                    this.bodyContainer.layout(i7, 0, i5 + i7, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case RIGHT_BOTTOM:
                    this.bodyContainer.layout(-i7, 0, i5 - i7, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                case RIGHT_PUSH:
                    this.bodyContainer.layout(-i7, 0, i5 - i7, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                default:
                    return;
            }
        } else {
            switch (this.type) {
                case LEFT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(-i7, 0, 0, i6);
                    return;
                case RIGHT_COVER:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5, 0, i7 + i5, i6);
                    return;
                case LEFT_BOTTOM:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(0, 0, i7, i6);
                    return;
                case LEFT_PUSH:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(-i7, 0, 0, i6);
                    return;
                case RIGHT_BOTTOM:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5 - i7, 0, i5, i6);
                    return;
                case RIGHT_PUSH:
                    this.bodyContainer.layout(0, 0, i5, i6);
                    this.drawerContainer.layout(i5, 0, i7 + i5, i6);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        long drawingTime = getDrawingTime();
        switch (this.type) {
            case LEFT_COVER:
            case RIGHT_COVER:
            case RIGHT_PUSH:
                frameLayout = this.bodyContainer;
                frameLayout2 = this.drawerContainer;
                break;
            default:
                frameLayout = this.drawerContainer;
                frameLayout2 = this.bodyContainer;
                break;
        }
        drawChild(canvas, frameLayout, drawingTime);
        drawChild(canvas, frameLayout2, drawingTime);
        drawShadow(canvas);
    }

    private void drawShadow(Canvas canvas) {
        switch (this.type) {
            case LEFT_COVER:
                int right = this.drawerContainer.getRight();
                if (right > 0) {
                    this.paint.setShader(new LinearGradient((float) right, 0.0f, (float) (right + 25), 0.0f, ExploreByTouchHelper.INVALID_ID, 0, TileMode.CLAMP));
                    canvas.drawRect((float) right, 0.0f, (float) (right + 25), (float) getHeight(), this.paint);
                    return;
                }
                return;
            case RIGHT_COVER:
                int left = this.drawerContainer.getLeft();
                if (left < getWidth()) {
                    this.paint.setShader(new LinearGradient((float) (left - 25), 0.0f, (float) left, 0.0f, 0, ExploreByTouchHelper.INVALID_ID, TileMode.CLAMP));
                    canvas.drawRect((float) (left - 25), 0.0f, (float) left, (float) getHeight(), this.paint);
                    return;
                }
                return;
            case RIGHT_BOTTOM:
            case RIGHT_PUSH:
                int right2 = this.bodyContainer.getRight();
                if (right2 < getWidth()) {
                    this.paint.setShader(new LinearGradient((float) right2, 0.0f, (float) (right2 + 25), 0.0f, ExploreByTouchHelper.INVALID_ID, 0, TileMode.CLAMP));
                    canvas.drawRect((float) right2, 0.0f, (float) (right2 + 25), (float) getHeight(), this.paint);
                    return;
                }
                return;
            default:
                int left2 = this.bodyContainer.getLeft();
                if (left2 > 0) {
                    this.paint.setShader(new LinearGradient((float) (left2 - 25), 0.0f, (float) left2, 0.0f, 0, ExploreByTouchHelper.INVALID_ID, TileMode.CLAMP));
                    canvas.drawRect((float) (left2 - 25), 0.0f, (float) left2, (float) getHeight(), this.paint);
                    return;
                }
                return;
        }
    }

    private void switchDrawer(boolean z, boolean z2) {
        int i;
        int i2;
        double d = 0.0d;
        this.bodyContainer.clearFocus();
        this.drawerContainer.clearFocus();
        switch (this.type) {
            case LEFT_COVER:
            case LEFT_PUSH:
                int left = this.drawerContainer.getLeft();
                if (!z) {
                    d = ((double) (-getWidth())) * this.drawerWidth;
                }
                i = left;
                i2 = (int) d;
                break;
            case RIGHT_COVER:
            case RIGHT_PUSH:
                int width = getWidth();
                int left2 = this.drawerContainer.getLeft();
                if (z) {
                    d = ((double) width) * this.drawerWidth;
                }
                i = left2;
                i2 = width - ((int) d);
                break;
            case LEFT_BOTTOM:
                int left3 = this.bodyContainer.getLeft();
                if (z) {
                    d = ((double) getWidth()) * this.drawerWidth;
                }
                i = left3;
                i2 = (int) d;
                break;
            case RIGHT_BOTTOM:
                int left4 = this.bodyContainer.getLeft();
                if (z) {
                    d = ((double) (-getWidth())) * this.drawerWidth;
                }
                i = left4;
                i2 = (int) d;
                break;
            default:
                i2 = 0;
                i = 0;
                break;
        }
        this.scroller.abortAnimation();
        if (i != i2) {
            this.scroller.startScroll(i, 0, i2 - i, 0, z2 ? 0 : 100);
        }
        invalidate();
    }

    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            switch (this.type) {
                case LEFT_COVER:
                case RIGHT_COVER:
                    int currX = this.scroller.getCurrX();
                    this.drawerContainer.layout(currX, 0, ((int) (((double) getWidth()) * this.drawerWidth)) + currX, getHeight());
                    break;
                case LEFT_BOTTOM:
                case RIGHT_BOTTOM:
                    int currX2 = this.scroller.getCurrX();
                    this.bodyContainer.layout(currX2, 0, getWidth() + currX2, getHeight());
                    break;
                case LEFT_PUSH:
                    int width = getWidth();
                    int currX3 = this.scroller.getCurrX();
                    int i = ((int) (((double) width) * this.drawerWidth)) + currX3;
                    int i2 = width + i;
                    this.drawerContainer.layout(currX3, 0, i, getHeight());
                    this.bodyContainer.layout(i, 0, i2, getHeight());
                    break;
                case RIGHT_PUSH:
                    int width2 = getWidth();
                    int currX4 = this.scroller.getCurrX();
                    int i3 = currX4 - width2;
                    int i4 = ((int) (((double) width2) * this.drawerWidth)) + currX4;
                    this.bodyContainer.layout(i3, 0, currX4, getHeight());
                    this.drawerContainer.layout(currX4, 0, i4, getHeight());
                    break;
            }
            postInvalidate();
            if (this.listener != null && this.scroller.getFinalX() != this.scroller.getStartX()) {
                int currX5 = ((this.scroller.getCurrX() - this.scroller.getStartX()) * 100) / (this.scroller.getFinalX() - this.scroller.getStartX());
                if (this.opened) {
                    this.listener.onClosing(this, currX5);
                } else {
                    this.listener.onOpening(this, currX5);
                }
            }
        } else if (isClose()) {
            this.opened = false;
        } else {
            this.opened = true;
        }
    }

    private boolean isClose() {
        switch (this.type) {
            case LEFT_COVER:
                if (this.drawerContainer.getRight() != 0) {
                    return false;
                }
                return true;
            case RIGHT_COVER:
                if (this.drawerContainer.getLeft() != getWidth()) {
                    return false;
                }
                return true;
            case LEFT_BOTTOM:
            case LEFT_PUSH:
                if (this.bodyContainer.getLeft() != 0) {
                    return false;
                }
                return true;
            case RIGHT_BOTTOM:
            case RIGHT_PUSH:
                if (this.bodyContainer.getRight() != getWidth()) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                if (this.touchState != 0) {
                    if (!this.scroller.isFinished()) {
                        this.scroller.abortAnimation();
                    }
                    this.lastMotionX = motionEvent.getX();
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.touchState == 1 && this.velocityTracker != null) {
                    this.velocityTracker.computeCurrentVelocity(1000, (float) this.maximumVelocity);
                    computeDrag((int) this.velocityTracker.getXVelocity());
                    this.velocityTracker.recycle();
                    this.velocityTracker = null;
                }
                this.touchState = 0;
                break;
            case 2:
                if (this.touchState == 1 || (onInterceptTouchEvent(motionEvent) && this.touchState == 1)) {
                    float x = motionEvent.getX();
                    int i = (int) (this.lastMotionX - x);
                    if (i < 0) {
                        dragToRight(-i);
                    } else if (i > 0) {
                        dragToLeft(i);
                    }
                    this.lastMotionX = x;
                    break;
                }
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.lockScroll) {
            return false;
        }
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
                this.lastMotionX = motionEvent.getX();
                this.lastMotionY = motionEvent.getY();
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
                float x = motionEvent.getX();
                int abs = (int) Math.abs(x - this.lastMotionX);
                if (((int) Math.abs(motionEvent.getY() - this.lastMotionY)) < abs && abs > this.touchSlop) {
                    this.touchState = 1;
                    this.lastMotionX = x;
                    break;
                }
        }
        if (this.touchState == 0) {
            return false;
        }
        return true;
    }

    private void dragToRight(int i) {
        switch (this.type) {
            case LEFT_COVER:
                int left = this.drawerContainer.getLeft();
                if (left < 0) {
                    int i2 = left + i;
                    if (i2 > 0) {
                        i2 = 0;
                    }
                    this.drawerContainer.layout(i2, 0, ((int) (((double) getWidth()) * this.drawerWidth)) + i2, getHeight());
                    return;
                }
                return;
            case RIGHT_COVER:
                int left2 = this.drawerContainer.getLeft();
                int width = getWidth();
                if (left2 < width) {
                    int i3 = left2 + i;
                    if (i3 <= width) {
                        width = i3;
                    }
                    this.drawerContainer.layout(width, 0, ((int) (((double) getWidth()) * this.drawerWidth)) + width, getHeight());
                    return;
                }
                return;
            case LEFT_BOTTOM:
                int left3 = this.bodyContainer.getLeft();
                int width2 = (int) (((double) getWidth()) * this.drawerWidth);
                if (left3 < width2) {
                    int i4 = left3 + i;
                    if (i4 <= width2) {
                        width2 = i4;
                    }
                    this.bodyContainer.layout(width2, 0, getWidth() + width2, getHeight());
                    return;
                }
                return;
            case LEFT_PUSH:
                int left4 = this.drawerContainer.getLeft();
                if (left4 < 0) {
                    int i5 = left4 + i;
                    if (i5 > 0) {
                        i5 = 0;
                    }
                    int width3 = ((int) (((double) getWidth()) * this.drawerWidth)) + i5;
                    int width4 = getWidth() + width3;
                    this.drawerContainer.layout(i5, 0, width3, getHeight());
                    this.bodyContainer.layout(width3, 0, width4, getHeight());
                    return;
                }
                return;
            case RIGHT_BOTTOM:
                int left5 = this.bodyContainer.getLeft();
                if (left5 < 0) {
                    int i6 = left5 + i;
                    if (i6 > 0) {
                        i6 = 0;
                    }
                    this.bodyContainer.layout(i6, 0, getWidth() + i6, getHeight());
                    return;
                }
                return;
            case RIGHT_PUSH:
                int left6 = this.bodyContainer.getLeft();
                if (left6 < 0) {
                    int i7 = left6 + i;
                    if (i7 > 0) {
                        i7 = 0;
                    }
                    int width5 = getWidth() + i7;
                    int width6 = ((int) (((double) getWidth()) * this.drawerWidth)) + width5;
                    this.bodyContainer.layout(i7, 0, width5, getHeight());
                    this.drawerContainer.layout(width5, 0, width6, getHeight());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void dragToLeft(int i) {
        switch (this.type) {
            case LEFT_COVER:
                int right = this.drawerContainer.getRight();
                if (right > 0) {
                    int i2 = right - i;
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    this.drawerContainer.layout(i2 - ((int) (((double) getWidth()) * this.drawerWidth)), 0, i2, getHeight());
                    return;
                }
                return;
            case RIGHT_COVER:
                int right2 = this.drawerContainer.getRight();
                int width = getWidth();
                if (right2 > width) {
                    int i3 = right2 - i;
                    if (i3 >= width) {
                        width = i3;
                    }
                    this.drawerContainer.layout(width - ((int) (((double) getWidth()) * this.drawerWidth)), 0, width, getHeight());
                    return;
                }
                return;
            case LEFT_BOTTOM:
                int left = this.bodyContainer.getLeft();
                if (left > 0) {
                    int i4 = left - i;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    this.bodyContainer.layout(i4, 0, getWidth() + i4, getHeight());
                    return;
                }
                return;
            case LEFT_PUSH:
                int right3 = this.drawerContainer.getRight();
                if (right3 > 0) {
                    int i5 = right3 - i;
                    if (i5 < 0) {
                        i5 = 0;
                    }
                    int width2 = getWidth() + i5;
                    this.drawerContainer.layout(i5 - ((int) (((double) getWidth()) * this.drawerWidth)), 0, i5, getHeight());
                    this.bodyContainer.layout(i5, 0, width2, getHeight());
                    return;
                }
                return;
            case RIGHT_BOTTOM:
                int left2 = this.bodyContainer.getLeft();
                int i6 = (int) (((double) (-getWidth())) * this.drawerWidth);
                if (left2 > i6) {
                    int i7 = left2 - i;
                    if (i7 >= i6) {
                        i6 = i7;
                    }
                    this.bodyContainer.layout(i6, 0, getWidth() + i6, getHeight());
                    return;
                }
                return;
            case RIGHT_PUSH:
                int right4 = this.drawerContainer.getRight();
                int width3 = getWidth();
                if (right4 > width3) {
                    int i8 = right4 - i;
                    if (i8 >= width3) {
                        width3 = i8;
                    }
                    int width4 = width3 - ((int) (((double) getWidth()) * this.drawerWidth));
                    int width5 = width4 - getWidth();
                    this.drawerContainer.layout(width4, 0, width3, getHeight());
                    this.bodyContainer.layout(width5, 0, width4, getHeight());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void computeDrag(int i) {
        if (i >= 500) {
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_BOTTOM:
                case LEFT_PUSH:
                    open();
                    return;
                case RIGHT_COVER:
                case RIGHT_BOTTOM:
                case RIGHT_PUSH:
                    close();
                    return;
                default:
                    return;
            }
        } else if (i <= -500) {
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_BOTTOM:
                case LEFT_PUSH:
                    close();
                    return;
                case RIGHT_COVER:
                case RIGHT_BOTTOM:
                case RIGHT_PUSH:
                    open();
                    return;
                default:
                    return;
            }
        } else {
            int i2 = 0;
            switch (this.type) {
                case LEFT_COVER:
                case LEFT_PUSH:
                    i2 = this.drawerContainer.getRight();
                    break;
                case RIGHT_COVER:
                case RIGHT_PUSH:
                    i2 = getWidth() - this.drawerContainer.getLeft();
                    break;
                case LEFT_BOTTOM:
                    i2 = this.bodyContainer.getLeft();
                    break;
                case RIGHT_BOTTOM:
                    i2 = -this.bodyContainer.getLeft();
                    break;
            }
            if (i2 >= ((int) (((double) getWidth()) * this.drawerWidth)) / 2) {
                open();
            } else {
                close();
            }
        }
    }
}
