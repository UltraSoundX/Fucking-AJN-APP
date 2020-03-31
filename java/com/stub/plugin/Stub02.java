package com.stub.plugin;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Stub02 extends Service {
    private Map<String, BusiItem> delegates = new HashMap();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        MyLog.log("Stub02", "onDestroy", "enter");
        try {
            for (BusiItem busiItem : this.delegates.values()) {
                ReflectionUtil.invoke(busiItem.getDelegateImpl(), ReflectionUtil.getMethod(busiItem.getDelegateClz(), "onDestroy", new Class[0]), new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        MyLog.log("Stub02", "onStartCommand", "enter");
        try {
            ReflectionUtil.invokeStatic(ReflectionUtil.getMethod(Class.forName("com.stub.stub02.ScreenReceiver"), "registerScreenActionReceiver", Context.class), this);
        } catch (Throwable th) {
        }
        if (intent != null) {
            String stringExtra = intent.getStringExtra("source");
            MyLog.log("Stub02", "onStartCommand", "source= " + stringExtra);
            BusiItem busiItem = new BusiItem();
            if (stringExtra != null) {
                if (!this.delegates.containsKey(stringExtra)) {
                    try {
                        busiItem.setDelegateClz(Class.forName(stringExtra));
                    } catch (ClassNotFoundException e) {
                    }
                    try {
                        if (busiItem.getDelegateClz() != null) {
                            try {
                                busiItem.setDelegateImpl(busiItem.getDelegateClz().newInstance());
                            } catch (IllegalAccessException | InstantiationException e2) {
                            }
                        }
                    } catch (Throwable th2) {
                    }
                } else {
                    busiItem = (BusiItem) this.delegates.get(stringExtra);
                }
                if (!(busiItem.getDelegateImpl() == null || busiItem.getDelegateClz() == null)) {
                    MyLog.log("Stub02", "onStartCommand", "call impl onStartCommand");
                    Method method = ReflectionUtil.getMethod(busiItem.getDelegateClz(), "onStartCommand", Intent.class, Integer.TYPE, Integer.TYPE, Service.class);
                    ReflectionUtil.invoke(busiItem.getDelegateImpl(), method, intent, Integer.valueOf(i), Integer.valueOf(i2), this);
                }
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
