package com.baidu.mobstat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import java.util.List;

public enum t {
    SERVICE(1) {
        public void a(Context context) {
            if (t.d(context) && u.a(context).b(context)) {
                try {
                    Intent intent = new Intent(context, Class.forName("com.baidu.bottom.service.BottomService"));
                    intent.putExtra("SDK_PRODUCT_LY", "MS");
                    context.startService(intent);
                } catch (Throwable th) {
                    al.c().b(th);
                }
            }
        }
    },
    NO_SERVICE(2) {
        public void a(Context context) {
            if (t.d(context)) {
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                a a = u.a(context);
                ac acVar = new ac();
                acVar.a = false;
                acVar.b = "M";
                acVar.c = false;
                a.a(origApplicationContext, acVar.a());
            }
        }
    },
    RECEIVER(3) {
        public void a(Context context) {
            if (t.d(context)) {
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                a a = u.a(context);
                ac acVar = new ac();
                acVar.a = false;
                acVar.b = "R";
                acVar.c = false;
                a.a(origApplicationContext, acVar.a());
            }
        }
    },
    ERISED(4) {
        public void a(Context context) {
            if (t.d(context)) {
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                a a = u.a(context);
                ac acVar = new ac();
                acVar.a = false;
                acVar.b = "E";
                acVar.c = false;
                a.a(origApplicationContext, acVar.a());
            }
        }
    };
    
    private int e;

    public abstract void a(Context context);

    private t(int i) {
        this.e = i;
    }

    public String toString() {
        return String.valueOf(this.e);
    }

    public static t a(int i) {
        t[] values;
        for (t tVar : values()) {
            if (tVar.e == i) {
                return tVar;
            }
        }
        return NO_SERVICE;
    }

    public static boolean b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        if (activityManager != null) {
            try {
                List runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                int i = 0;
                while (runningServices != null && i < runningServices.size()) {
                    if ("com.baidu.bottom.service.BottomService".equals(((RunningServiceInfo) runningServices.get(i)).service.getClassName())) {
                        return true;
                    }
                    i++;
                }
            } catch (Exception e2) {
                al.c().a((Throwable) e2);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean d(Context context) {
        return at.e(context, com.tencent.mid.core.Constants.PERMISSION_WRITE_EXTERNAL_STORAGE);
    }
}
