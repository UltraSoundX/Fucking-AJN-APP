package com.mob.tools.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import com.mob.tools.MobLog;
import java.nio.ByteBuffer;
import java.util.Random;

public class UIHandler {
    private static Handler handler;

    private static final class InnerObj {
        public final Callback callback;
        public final Message msg;

        public InnerObj(Message message, Callback callback2) {
            this.msg = message;
            this.callback = callback2;
        }
    }

    private static synchronized void prepare() {
        synchronized (UIHandler.class) {
            if (handler == null) {
                reset();
                printPray();
            }
        }
    }

    private static void reset() {
        handler = new Handler(Looper.getMainLooper(), new Callback() {
            public boolean handleMessage(Message message) {
                UIHandler.handleMessage(message);
                return false;
            }
        });
    }

    private static void printPray() {
        try {
            MobLog.getInstance().d("\n" + ByteBuffer.wrap(Base64.decode(new String[]{"MAAlDCUQMAAwADAAJQwlEAAKJQwlGCUUJQAlACUAJRglFCUQAAolAjAAMAAwADAAMAAwADAAJQIACiUCMAAwADAAJQAwADAAMAAlAgAKJQIwACUsJRgwACUUJSwwACUCAAolAjAAMAAwADAAMAAwADAAJQIACiUCMAAwADAAJTQwADAAMAAlAgAKJQIwADAAMAAwADAAMAAwACUCAAolFCUAJRAwADAAMAAlDCUAJRgACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlAgAKMAAwACUCMAAwADAAJQIwADAAMAAwAABDAG8AZABlACAAaQBzACAAZgBhAHIAIABhAHcAYQB5ACAAZgByAG8AbQAgAGIAdQBnACAAdwBpAHQAaAAgAHQAaABlACAAYQBuAGkAbQBhAGwAIABwAHIAbwB0AGUAYwB0AGkAbgBnAAowADAAJQIwADAAMAAlAjAAMAAwADAAeV5RfU/dT1H/DE7jeAFl4ABCAFUARwAKMAAwACUCMAAwADAAJQIACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlFCUAJQAlACUQAAowADAAJQIwADAAMAAwADAAMAAwACUcJRAACjAAMAAlAjAAMAAwADAAMAAwADAAJRwlGAAKMAAwACUUJRAlECUMJQAlLCUQJQwlGAAKMAAwADAAJQIlJCUkMAAlAiUkJSQACjAAMAAwACUUJRglGDAAJRQlGCUY", "MAAlDCUQMAAwADAAJQwlEAAKJQwlGCUUJQAlACUAJRglFCUQAAolAjAAMAAwADAAMAAwADAAJQIACiUCMAAwADAAJQAwADAAMAAlAgAKJQIwAP8eMAAwADAA/xwwACUCAAolAjAAMAAwADAAMAAwADAAJQIACiUCMAAgJjAA/z4wACAmMAAlAgAKJQIwADAAMAAwADAAMAAwACUCAAolFCUAJRAwADAAMAAlDCUAJRgACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlAgAKMAAwACUCMAAwADAAJQIwADAAMAAwAABDAG8AZABlACAAaQBzACAAZgBhAHIAIABhAHcAYQB5ACAAZgByAG8AbQAgAGIAdQBnACAAdwBpAHQAaAAgAHQAaABlACAAYQBuAGkAbQBhAGwAIABwAHIAbwB0AGUAYwB0AGkAbgBnAAowADAAJQIwADAAMAAlAjAAMAAwADAAeV5RfU/dT1H/DE7jeAFl4ABCAFUARwAKMAAwACUCMAAwADAAJQIACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlFCUAJQAlACUQAAowADAAJQIwADAAMAAwADAAMAAwACUcJRAACjAAMAAlAjAAMAAwADAAMAAwADAAJRwlGAAKMAAwACUUJRAlECUMJQAlLCUQJQwlGAAKMAAwADAAJQIlJCUkMAAlAiUkJSQACjAAMAAwACUUJRglGDAAJRQlGCUY", "MAAlDCUQMAAwADAAJQwlEP8LMAD/CwAKJQwlGCUUJQAlACUAJRglFCUQMAD/CzAA/wsACiUCMAAwADAAMAAwADAAMAAlAgAKJQIwADAAMAAlADAAMAAwACUCMAD/C/8LMAD/CzAA/wswAP8LAAolAiWIJYgliCAVJYgliCWIJQIwAP8LAAolAjAAMAAwADAAMAAwADAAJQIwAP8LAAolAjAAMAAwACU0MAAwADAAJQIACiUCMAAwADAAMAAwADAAMAAlAjAA/wswAP8LAAolFCUAJRAwADAAMAAlDCUAJRgACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlAjAA/wswAP8LMAD/CzAA/wsACjAAMAAlAjAAMAAwACUCAAowADAAJQIwADAAMAAlAjAA/wswADAAAEMAbwBkAGUAIABpAHMAIABmAGEAcgAgAGEAdwBhAHkAIABmAHIAbwBtACAAYgB1AGcAIAB3AGkAdABoACAAdABoAGUAIABhAG4AaQBtAGEAbAAgAHAAcgBvAHQAZQBjAHQAaQBuAGcACjAAMAAlAjAAMAAwACUCMAAwADAAMAB5XlF9T91PUf8MTuN4AWXgAEIAVQBHAAowADAAJQIwADAAMAAlAjAAMAD/CwAKMAAwACUCMAAwADAAJRQlACUAJQAlEDAA/wswAP8LAAowADAAJQIwADAAMAAwADAAMAAwACUcJRAACjAAMAAlAjAAMAAwADAAMAAwADAAJRwlGAAKMAAwACUUJRAlECUMJQAlLCUQJQwlGDAA/wswAP8LMAD/CzAA/wsACjAAMAAwACUCJSQlJDAAJQIlJCUkAAowADAAMAAlFCUYJRgwACUUJRglGDAA/wswAP8LMAD/CzAA/ws="}[Math.abs(new Random().nextInt()) % 3], 2)).asCharBuffer().toString(), new Object[0]);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    /* access modifiers changed from: private */
    public static void handleMessage(Message message) {
        InnerObj innerObj = (InnerObj) message.obj;
        Message message2 = innerObj.msg;
        Callback callback = innerObj.callback;
        if (callback != null) {
            callback.handleMessage(message2);
        }
    }

    private static Message getShellMessage(Message message, Callback callback) {
        Message message2 = new Message();
        message2.obj = new InnerObj(message, callback);
        return message2;
    }

    private static Message getShellMessage(int i, Callback callback) {
        Message message = new Message();
        message.what = i;
        return getShellMessage(message, callback);
    }

    public static boolean sendMessage(Message message, Callback callback) {
        prepare();
        return handler.sendMessage(getShellMessage(message, callback));
    }

    public static boolean sendMessageDelayed(Message message, long j, Callback callback) {
        prepare();
        return handler.sendMessageDelayed(getShellMessage(message, callback), j);
    }

    public static boolean sendMessageAtTime(Message message, long j, Callback callback) {
        prepare();
        return handler.sendMessageAtTime(getShellMessage(message, callback), j);
    }

    public static boolean sendMessageAtFrontOfQueue(Message message, Callback callback) {
        prepare();
        return handler.sendMessageAtFrontOfQueue(getShellMessage(message, callback));
    }

    public static boolean sendEmptyMessage(int i, Callback callback) {
        prepare();
        return handler.sendMessage(getShellMessage(i, callback));
    }

    public static boolean sendEmptyMessageAtTime(int i, long j, Callback callback) {
        prepare();
        return handler.sendMessageAtTime(getShellMessage(i, callback), j);
    }

    public static boolean sendEmptyMessageDelayed(int i, long j, Callback callback) {
        prepare();
        return handler.sendMessageDelayed(getShellMessage(i, callback), j);
    }
}
