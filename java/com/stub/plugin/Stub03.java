package com.stub.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Stub03 extends BroadcastReceiver {
    private Map<String, BusiItem> delegates = new HashMap();

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("source");
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
                    } catch (Throwable th) {
                        return;
                    }
                } else {
                    busiItem = (BusiItem) this.delegates.get(stringExtra);
                }
                if (busiItem.getDelegateImpl() != null && busiItem.getDelegateClz() != null) {
                    Method method = ReflectionUtil.getMethod(busiItem.getDelegateClz(), "onReceive", Context.class, Intent.class);
                    ReflectionUtil.invoke(busiItem.getDelegateImpl(), method, context, intent);
                }
            }
        }
    }
}
