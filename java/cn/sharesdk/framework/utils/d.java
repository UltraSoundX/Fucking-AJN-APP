package cn.sharesdk.framework.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.MobHandlerThread;

/* compiled from: SSDKHandlerThread */
public abstract class d implements Callback {
    protected final Handler a = MobHandlerThread.newHandler(this);

    /* access modifiers changed from: protected */
    public abstract void b(Message message);

    public void d() {
        a(0, 0, null);
    }

    public void a(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.a.sendMessage(message);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case -2:
                c(message);
                break;
            case -1:
                a(message);
                break;
            default:
                b(message);
                break;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
    }

    /* access modifiers changed from: protected */
    public void c(Message message) {
    }
}
