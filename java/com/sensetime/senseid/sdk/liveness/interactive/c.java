package com.sensetime.senseid.sdk.liveness.interactive;

enum c {
    ACCLERATION(0),
    ROTATION_RATE(1),
    GRAVITY(2),
    MAGNETIC_FIELD(3);
    
    private int mValue;

    private c(int i) {
        this.mValue = i;
    }

    /* access modifiers changed from: 0000 */
    public final int getValue() {
        return this.mValue;
    }
}
