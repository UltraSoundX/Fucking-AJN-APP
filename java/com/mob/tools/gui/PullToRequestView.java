package com.mob.tools.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PullToRequestView extends RelativeLayout {
    private static final int FAULT_TOLERANCE_RANGE = 10;
    private static final long MIN_REF_TIME = 1000;
    private PullToRequestAdatper adapter;
    private View bodyView;
    private float downY;
    private int footerHeight;
    private View footerView;
    private int headerHeight;
    private View headerView;
    private long pullTime;
    private boolean pullingDownLock;
    private boolean pullingUpLock;
    private int state;
    private Runnable stopAct;
    private int top;

    public PullToRequestView(Context context) {
        super(context);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.stopAct = new Runnable() {
            public void run() {
                PullToRequestView.this.reversePulling();
            }
        };
    }

    public void setAdapter(PullToRequestAdatper pullToRequestAdatper) {
        this.adapter = pullToRequestAdatper;
        removeAllViews();
        this.bodyView = (View) pullToRequestAdatper.getBodyView();
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(9);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        addView(this.bodyView, layoutParams);
        this.headerView = pullToRequestAdatper.getHeaderView();
        this.headerView.setLayoutParams(new LayoutParams(-2, -2));
        this.headerView.measure(0, 0);
        this.headerHeight = this.headerView.getMeasuredHeight();
        LayoutParams layoutParams2 = new LayoutParams(-2, this.headerHeight);
        layoutParams2.addRule(9);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        layoutParams2.topMargin = -this.headerHeight;
        addView(this.headerView, layoutParams2);
        this.footerView = pullToRequestAdatper.getFooterView();
        this.footerView.setLayoutParams(new LayoutParams(-2, -2));
        this.footerView.measure(0, 0);
        this.footerHeight = this.footerView.getMeasuredHeight();
        LayoutParams layoutParams3 = new LayoutParams(-2, this.headerHeight);
        layoutParams3.addRule(9);
        layoutParams3.addRule(11);
        layoutParams3.addRule(12);
        layoutParams3.bottomMargin = -this.headerHeight;
        addView(this.footerView, layoutParams3);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.downY = motionEvent.getY();
                break;
            case 1:
            case 3:
                switch (this.state) {
                    case -1:
                        this.top = -this.footerHeight;
                        scrollTo(0, -this.top);
                        break;
                    case 0:
                        if (this.top <= this.headerHeight) {
                            if (this.top >= (-this.footerHeight)) {
                                if (this.top != 0) {
                                    scrollTo(0, 0);
                                    if (this.adapter != null) {
                                        if (this.top > 0) {
                                            this.adapter.onPullDown(0);
                                        } else {
                                            this.adapter.onPullUp(0);
                                        }
                                    }
                                    this.top = 0;
                                    break;
                                }
                            } else {
                                this.top = -this.footerHeight;
                                scrollTo(0, -this.top);
                                if (this.adapter != null) {
                                    this.adapter.onPullUp(100);
                                }
                                performRequestNext();
                                motionEvent = getCancelEvent(motionEvent);
                                break;
                            }
                        } else {
                            this.top = this.headerHeight;
                            scrollTo(0, -this.top);
                            if (this.adapter != null) {
                                this.adapter.onPullDown(100);
                            }
                            performFresh();
                            motionEvent = getCancelEvent(motionEvent);
                            break;
                        }
                        break;
                    case 1:
                        this.top = this.headerHeight;
                        scrollTo(0, -this.top);
                        break;
                }
            case 2:
                float y = motionEvent.getY();
                switch (this.state) {
                    case -1:
                        this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                        if (this.top > 0) {
                            this.top = 0;
                        }
                        scrollTo(0, -this.top);
                        motionEvent = getCancelEvent(motionEvent);
                        break;
                    case 1:
                        this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                        if (this.top < 0) {
                            this.top = 0;
                        }
                        scrollTo(0, -this.top);
                        motionEvent = getCancelEvent(motionEvent);
                        break;
                    default:
                        if (this.top <= 0) {
                            if (this.top >= 0) {
                                if (y - this.downY <= 10.0f) {
                                    if (this.downY - y > 10.0f && canPullUp()) {
                                        this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                                        scrollTo(0, -this.top);
                                        if (!(this.adapter == null || this.footerHeight == 0)) {
                                            this.adapter.onPullUp(((-this.top) * 100) / this.footerHeight);
                                        }
                                        motionEvent = getCancelEvent(motionEvent);
                                        break;
                                    }
                                } else if (canPullDown()) {
                                    this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                                    scrollTo(0, -this.top);
                                    if (!(this.adapter == null || this.headerHeight == 0)) {
                                        this.adapter.onPullUp(((-this.top) * 100) / this.headerHeight);
                                    }
                                    motionEvent = getCancelEvent(motionEvent);
                                    break;
                                }
                            } else {
                                this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                                if (this.top > 0) {
                                    this.top = 0;
                                }
                                scrollTo(0, -this.top);
                                if (!(this.adapter == null || this.footerHeight == 0)) {
                                    this.adapter.onPullUp(((-this.top) * 100) / this.footerHeight);
                                }
                                motionEvent = getCancelEvent(motionEvent);
                                break;
                            }
                        } else {
                            this.top = (int) (((float) this.top) + ((y - this.downY) / 2.0f));
                            if (this.top < 0) {
                                this.top = 0;
                            }
                            scrollTo(0, -this.top);
                            if (!(this.adapter == null || this.headerHeight == 0)) {
                                this.adapter.onPullDown((this.top * 100) / this.headerHeight);
                            }
                            motionEvent = getCancelEvent(motionEvent);
                            break;
                        }
                        break;
                }
                this.downY = y;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private MotionEvent getCancelEvent(MotionEvent motionEvent) {
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    public void performPullingDown(boolean z) {
        this.top = this.headerHeight;
        scrollTo(0, -this.top);
        if (z) {
            performFresh();
        }
    }

    /* access modifiers changed from: protected */
    public void performFresh() {
        this.pullTime = System.currentTimeMillis();
        this.state = 1;
        if (this.adapter != null) {
            this.adapter.onRefresh();
        }
    }

    public void performPullingUp(boolean z) {
        this.top = -this.footerHeight;
        scrollTo(0, -this.top);
        if (z) {
            performRequestNext();
        }
    }

    private void performRequestNext() {
        this.pullTime = System.currentTimeMillis();
        this.state = -1;
        if (this.adapter != null) {
            this.adapter.onRequestNext();
        }
    }

    /* access modifiers changed from: private */
    public void reversePulling() {
        this.top = 0;
        scrollTo(0, 0);
        this.state = 0;
        if (this.adapter != null) {
            this.adapter.onReversed();
        }
    }

    public void stopPulling() {
        long currentTimeMillis = System.currentTimeMillis() - this.pullTime;
        if (currentTimeMillis < MIN_REF_TIME) {
            postDelayed(this.stopAct, MIN_REF_TIME - currentTimeMillis);
        } else {
            post(this.stopAct);
        }
    }

    public void lockPullingDown() {
        this.pullingDownLock = true;
    }

    public void lockPullingUp() {
        this.pullingUpLock = true;
    }

    public void releasePullingDownLock() {
        this.pullingDownLock = false;
    }

    public void releasePullingUpLock() {
        this.pullingUpLock = false;
    }

    private boolean canPullDown() {
        return !this.pullingDownLock && this.adapter.isPullDownReady() && this.state == 0;
    }

    private boolean canPullUp() {
        return !this.pullingUpLock && this.adapter.isPullUpReady() && this.state == 0;
    }
}
