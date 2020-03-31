package com.sensetime.senseid.sdk.liveness.interactive.type;

import android.support.annotation.Keep;

@Keep
public final class BoundInfo {
    private int mRadius = -1;
    private int mX = -1;
    private int mY = -1;

    public BoundInfo(int i, int i2, int i3) {
        this.mX = i;
        this.mY = i2;
        this.mRadius = i3;
    }

    public final int getRadius() {
        return this.mRadius;
    }

    public final int getX() {
        return this.mX;
    }

    public final int getY() {
        return this.mY;
    }

    public final String toString() {
        return "BoundInfo[X: " + this.mX + ", Y: " + this.mY + ", Radius: " + this.mRadius + "]";
    }
}
