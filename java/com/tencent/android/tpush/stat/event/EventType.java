package com.tencent.android.tpush.stat.event;

import android.support.v4.view.PointerIconCompat;

/* compiled from: ProGuard */
public enum EventType {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(PointerIconCompat.TYPE_CONTEXT_MENU),
    MONITOR_STAT(PointerIconCompat.TYPE_HAND),
    MTA_GAME_USER(PointerIconCompat.TYPE_HELP),
    NETWORK_MONITOR(PointerIconCompat.TYPE_WAIT),
    NETWORK_DETECTOR(1005),
    LBS(10001);
    
    private int v;

    private EventType(int i) {
        this.v = i;
    }

    public int GetIntValue() {
        return this.v;
    }
}
