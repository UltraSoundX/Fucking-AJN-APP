package com.mob.tools;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public abstract class SSDKHandlerThread implements Callback {
    private static final int MSG_START = -1;
    private static final int MSG_STOP = -2;
    protected final Handler handler = MobHandlerThread.newHandler("s", (Callback) this);

    /* access modifiers changed from: protected */
    public abstract void onMessage(Message message);

    public void startThread() {
        startThread(0, 0, null);
    }

    public void startThread(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.handler.sendMessage(message);
    }

    public void stopThread() {
        stopThread(0, 0, null);
    }

    public void stopThread(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -2;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.handler.sendMessage(message);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case -2:
                onStop(message);
                break;
            case -1:
                onStart(message);
                break;
            default:
                onMessage(message);
                break;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onStart(Message message) {
    }

    /* access modifiers changed from: protected */
    public void onStop(Message message) {
    }
}
