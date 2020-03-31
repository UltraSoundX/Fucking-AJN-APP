package com.tencent.android.tpush.d.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.d.c;
import com.tencent.android.tpush.service.e.h;
import com.tencent.android.tpush.service.e.i;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* compiled from: ProGuard */
public class b extends c {
    Object a;
    Context b;
    BroadcastReceiver c = null;
    String d;

    /* compiled from: ProGuard */
    public class a implements InvocationHandler {
        public a() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method == null) {
                return null;
            }
            if (method.getName().equals("onConnected")) {
                l.a("reveiver onConnected", b.this.b);
                b.this.b();
                return method;
            } else if (method.getName().equals("onResult")) {
                l.a("reveiver onResult", b.this.b);
                if (objArr == null) {
                    return method;
                }
                try {
                    if (objArr.length <= 0) {
                        return method;
                    }
                    Object obj2 = objArr[0];
                    Class cls = Class.forName("com.huawei.hms.support.api.entity.push.TokenResp");
                    Class cls2 = Class.forName("com.huawei.hms.support.api.push.TokenResult");
                    l.a("TokenResult =  " + cls.getDeclaredMethod("getToken", new Class[0]).invoke(cls2.getDeclaredMethod("getTokenRes", new Class[0]).invoke(Class.forName("com.huawei.hms.support.api.push.TokenResult").cast(obj2), new Object[0]), new Object[0]), b.this.b);
                    return method;
                } catch (Exception e) {
                    l.a("TokenResult =  " + e.getMessage(), b.this.b);
                    return method;
                }
            } else if (!"onConnectionFailed".equals(method.getName()) || objArr == null) {
                return method;
            } else {
                try {
                    if (objArr.length <= 0) {
                        return method;
                    }
                    Class cls3 = Class.forName("com.huawei.hms.api.ConnectionResult");
                    l.a("reveiver " + method.getName() + " errorcode " + ((Integer) cls3.getDeclaredMethod("getErrorCode", new Class[0]).invoke(Class.forName("com.huawei.hms.api.ConnectionResult").cast(objArr[0]), new Object[0])).intValue(), b.this.b);
                    return method;
                } catch (Throwable th) {
                    l.a("onConnectionFailed + " + th.getMessage(), b.this.b);
                    return method;
                }
            }
        }
    }

    /* renamed from: com.tencent.android.tpush.d.a.b$b reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public class C0060b implements InvocationHandler {
        public C0060b() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method == null) {
                return null;
            }
            if (!method.getName().equals("onResult") || objArr == null) {
                return method;
            }
            try {
                if (objArr.length <= 0) {
                    return method;
                }
                Object obj2 = objArr[0];
                Class cls = Class.forName("com.huawei.hms.support.api.entity.push.TokenResp");
                Class cls2 = Class.forName("com.huawei.hms.support.api.push.TokenResult");
                l.a("TokenResult =  " + cls.getDeclaredMethod("getToken", new Class[0]).invoke(cls2.getDeclaredMethod("getTokenRes", new Class[0]).invoke(Class.forName("com.huawei.hms.support.api.push.TokenResult").cast(obj2), new Object[0]), new Object[0]), b.this.b);
                return method;
            } catch (Throwable th) {
                l.a("MyTokenHandler error TokenResult =  " + th.getMessage(), b.this.b);
                return method;
            }
        }
    }

    public void a(Context context) {
        l.a("registerPush huawei", context);
        e(context);
        try {
            this.b = context;
            Class cls = Class.forName("com.huawei.hms.api.HuaweiApiClient");
            Class cls2 = Class.forName("com.huawei.hms.api.HuaweiApiClient$Builder");
            Object[] objArr = {context};
            Constructor declaredConstructor = cls2.getDeclaredConstructor(new Class[]{Context.class});
            Class loadClass = cls2.getClassLoader().loadClass("com.huawei.hms.api.HuaweiApiClient$OnConnectionFailedListener");
            Class cls3 = Class.forName("com.huawei.hms.api.HuaweiApiClient$ConnectionCallbacks");
            a aVar = new a();
            Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls3}, aVar);
            Object newProxyInstance2 = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{loadClass}, aVar);
            Object newInstance = declaredConstructor.newInstance(objArr);
            Class cls4 = Class.forName("com.huawei.hms.support.api.push.HuaweiPush");
            Class cls5 = Class.forName("com.huawei.hms.api.Api");
            Field declaredField = cls4.getDeclaredField("PUSH_API");
            cls2.getDeclaredMethod("addApi", new Class[]{cls5}).invoke(newInstance, new Object[]{declaredField.get(cls5)});
            cls2.getDeclaredMethod("addConnectionCallbacks", new Class[]{cls3}).invoke(newInstance, new Object[]{newProxyInstance});
            cls2.getDeclaredMethod("addOnConnectionFailedListener", new Class[]{loadClass}).invoke(newInstance, new Object[]{newProxyInstance2});
            this.a = cls2.getDeclaredMethod("build", new Class[0]).invoke(newInstance, new Object[0]);
            cls.getDeclaredMethod("connect", new Class[0]).invoke(this.a, new Object[0]);
            l.a("connect to huawei", context);
        } catch (Throwable th) {
            l.a("register =  " + th.getMessage(), context);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        l.a("getTokenAsyn", this.b);
        try {
            Class cls = Class.forName("com.huawei.hms.support.api.client.ApiClient");
            if (((Boolean) cls.getDeclaredMethod("isConnected", new Class[0]).invoke(this.a, new Object[0])).booleanValue()) {
                Class cls2 = Class.forName("com.huawei.hms.support.api.push.HuaweiPush");
                Class.forName("com.huawei.hms.support.api.push.HuaweiPushApi");
                Object obj = cls2.getDeclaredField("HuaweiPushApi").get(cls2);
                Object invoke = obj.getClass().getDeclaredMethod("getToken", new Class[]{cls}).invoke(obj, new Object[]{this.a});
                Class cls3 = Class.forName("com.huawei.hms.support.api.client.PendingResult");
                Class cls4 = Class.forName("com.huawei.hms.support.api.client.ResultCallback");
                C0060b bVar = new C0060b();
                Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls4}, bVar);
                cls3.getDeclaredMethod("setResultCallback", new Class[]{cls4}).invoke(invoke, new Object[]{newProxyInstance});
                return;
            }
            com.tencent.android.tpush.b.a.i("OtherPushHuaWeiImpl", "getTokenAsyn failed with unconnected");
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("OtherPushHuaWeiImpl", "registerPush ", th);
        }
    }

    public void b(Context context) {
        try {
            Class cls = Class.forName("com.huawei.hms.support.api.client.ApiClient");
            if (((Boolean) cls.getDeclaredMethod("isConnected", new Class[0]).invoke(this.a, new Object[0])).booleanValue()) {
                Class cls2 = Class.forName("com.huawei.hms.support.api.push.HuaweiPush");
                Class.forName("com.huawei.hms.support.api.push.HuaweiPushApi");
                cls2.getDeclaredField("HuaweiPushApi").get(cls2).getClass().getDeclaredMethod("deleteToken", new Class[]{cls, String.class}).invoke(this.a, new Object[]{c(context)});
            }
        } catch (Exception e) {
            com.tencent.android.tpush.b.a.d("OtherPushHuaWeiImpl", "unregisterPush ", e);
        }
    }

    private void e(Context context) {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        try {
                            String action = intent.getAction();
                            if (!i.b(action)) {
                                if ("com.huawei.android.push.intent.REGISTRATION".equals(action)) {
                                    byte[] byteArrayExtra = intent.getByteArrayExtra("device_token");
                                    if (byteArrayExtra != null) {
                                        b.this.d = new String(byteArrayExtra, "UTF-8");
                                        if (!i.b(b.this.d)) {
                                            h.b(context, "huawei_token", b.this.d);
                                        }
                                    }
                                } else if ("com.huawei.android.push.intent.RECEIVE".equals(action)) {
                                    com.tencent.android.tpush.b.a.c("OtherPushHuaWeiImpl", "reciver action com.huawei.android.push.intent.RECEIVE");
                                } else if ("com.huawei.intent.action.PUSH_STATE".equals(action)) {
                                    com.tencent.android.tpush.b.a.c("OtherPushHuaWeiImpl", "reciver action com.huawei.intent.action.PUSH_STATEE");
                                }
                            }
                        } catch (Throwable th) {
                            com.tencent.android.tpush.b.a.d("OtherPushHuaWeiImpl", "registerHuaweiRecevier ", th);
                            l.a("receiver token error" + th.getLocalizedMessage(), b.this.b);
                        }
                    }
                }
            };
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.huawei.android.push.intent.REGISTRATION");
                intentFilter.addAction("com.huawei.android.push.intent.RECEIVE");
                intentFilter.addAction("com.huawei.intent.action.PUSH_STATE");
                context.registerReceiver(this.c, intentFilter);
            } catch (Throwable th) {
                l.a("registerReceiver error " + th.getLocalizedMessage(), this.b);
            }
        }
    }

    public String c(Context context) {
        if (!i.b(this.d)) {
            return this.d;
        }
        return h.a(context, "huawei_token", "");
    }

    public boolean d(Context context) {
        return true;
    }

    public String a() {
        return "huawei";
    }
}
