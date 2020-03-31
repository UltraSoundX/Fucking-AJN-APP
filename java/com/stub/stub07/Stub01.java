package com.stub.stub07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public class Stub01 extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static String JG_REPORT_REASON_CONN_CHANGE = "5";
    private static final StubMessage MESSAGE = new StubMessage();
    public static final int NETWORK_STATUS_MOBILE = 2;
    public static final int NETWORK_STATUS_NOT_CONNECTED = 0;
    public static final int NETWORK_STATUS_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;
    public static final int TYPE_WIFI = 1;
    private static final Handler handler = new Handler();
    private static boolean isFirst = true;
    private static Stub01 mInstance = new Stub01();
    private static final int mJC = 16;
    private int lastStatus = 0;

    private static class StubMessage implements Runnable {
        private StubMessage() {
        }

        public void run() {
            Stub01.mark1(Stub01.JG_REPORT_REASON_CONN_CHANGE);
        }
    }

    public static native void mark1(String str);

    public static Stub01 getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        mInstance = new Stub01();
        return mInstance;
    }

    private static int getConnectivityStatus(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0) {
                return 2;
            }
        }
        return 0;
    }

    private static int getConnectivityStatusString(Context context) {
        int connectivityStatus = getConnectivityStatus(context);
        if (connectivityStatus == 1) {
            return 1;
        }
        if (connectivityStatus == 2 || connectivityStatus == 0) {
            return 2;
        }
        if (connectivityStatus == 0) {
            return 0;
        }
        return 0;
    }

    public void handleException(Thread thread, Throwable th, boolean z, int i) {
        try {
            Class cls = Class.forName("com.qihoo.bugreport.CrashReport");
            Class cls2 = Class.forName("com.qihoo.bugreport.javacrash.ExceptionHandleReporter");
            Object invoke = cls.getDeclaredMethod("getExceptionHandlerInstance", new Class[0]).invoke(null, new Object[0]);
            if (invoke != null) {
                cls2.getDeclaredMethod("uncaughtException", new Class[]{Thread.class, Throwable.class, Boolean.TYPE, Integer.TYPE}).invoke(invoke, new Object[]{thread, th, Boolean.valueOf(z), Integer.valueOf(i)});
            }
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (isFirst) {
            isFirst = false;
            return;
        }
        try {
            int connectivityStatusString = getConnectivityStatusString(context);
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                if (this.lastStatus != connectivityStatusString) {
                    if (connectivityStatusString == 0) {
                        handler.postDelayed(MESSAGE, 500);
                    } else if (connectivityStatusString == 2) {
                        handler.postDelayed(MESSAGE, 500);
                    } else if (connectivityStatusString == 1) {
                        handler.postDelayed(MESSAGE, 500);
                    }
                }
                this.lastStatus = connectivityStatusString;
            }
        } catch (Throwable th) {
            handleException(Thread.currentThread(), th, true, 16);
        }
    }
}
