package com.sensetime.senseid.sdk.liveness.interactive;

enum d {
    DEVICE(0),
    OS(1),
    SDK_VERSION(2),
    SYS_VERSION(3),
    IS_ROOT(4),
    IDFA(5),
    CONTROL_SEQ(6),
    CUSTOMER_ID(7);
    
    private int mValue;

    private d(int i) {
        this.mValue = -1;
        this.mValue = i;
    }

    /* access modifiers changed from: 0000 */
    public final int getKeyValue() {
        return this.mValue;
    }
}
