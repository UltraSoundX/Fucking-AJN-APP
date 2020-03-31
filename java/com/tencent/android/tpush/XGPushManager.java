package com.tencent.android.tpush;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.android.tpush.c.g;
import com.tencent.android.tpush.cloudctr.CloudControlManager;
import com.tencent.android.tpush.cloudctr.CloudControlManager.ICloudControlDispatcher;
import com.tencent.android.tpush.cloudctr.CloudControlManager.ICloudControlDispatcher.DownloadStatus;
import com.tencent.android.tpush.cloudctr.CloudControlManager.ICloudControlDispatcher.ErrorResponse;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.ReturnCode;
import com.tencent.android.tpush.common.k;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.d.d;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.h;
import com.tencent.android.tpush.service.e.i;
import com.tencent.android.tpush.service.f;
import com.tencent.android.tpush.stat.XGPatchMonitor;
import com.tencent.mid.sotrage.StorageInterface;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGPushManager {
    public static final String ENABLE_SERVICE_SUFFIX = ".enableService";
    public static final int OPERATION_FAIL = 1;
    public static final int OPERATION_REQ_REGISTER = 100;
    public static final int OPERATION_REQ_UNREGISTER = 101;
    public static final int OPERATION_SUCCESS = 0;
    /* access modifiers changed from: private */
    public static final String a = XGPushManager.class.getSimpleName();
    private static boolean b = false;
    private static Context c = null;
    private static XGPushNotifactionCallback d = null;
    private static Long e = Long.valueOf(0);
    public static int enableService = -1;
    /* access modifiers changed from: private */
    public static Map<b, c> f = new ConcurrentHashMap();
    public static Map<String, Long> lastSuccessRegisterMap = new HashMap();

    /* compiled from: ProGuard */
    private static class a implements Runnable {
        /* access modifiers changed from: private */
        public Context a;
        private Intent b;
        private XGIOperateCallback c;
        private int d;
        private int e = 0;

        public a(XGIOperateCallback xGIOperateCallback, Context context, Intent intent, int i, int i2) {
            this.c = xGIOperateCallback;
            this.a = context;
            this.b = intent;
            this.d = i;
            this.e = i2;
        }

        public void run() {
            try {
                this.b.removeExtra("storage");
                if (this.d != 1) {
                    if (this.d == 0) {
                        switch (this.b.getIntExtra("operation", -1)) {
                            case 100:
                                XGPushManager.c(this.a, this.b, this.c);
                                break;
                            case 101:
                                XGPushManager.d(this.a, this.b, this.c);
                                break;
                        }
                    }
                } else {
                    final String absolutePath = this.a.getDir("dex", 0).getAbsolutePath();
                    com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                        public void run() {
                            CloudControlManager.a().a(a.this.a, "XG", absolutePath, new ICloudControlDispatcher() {
                                public void a(String str) {
                                    h.b(a.this.a, a.this.a.getPackageName() + "_ccConfig", str);
                                }

                                public void a(String str, String str2, String str3, DownloadStatus downloadStatus, ErrorResponse errorResponse) {
                                    if (downloadStatus == DownloadStatus.Success) {
                                        Log.i("test", "Download file Success" + str + " to " + str2);
                                        h.b(a.this.a, a.this.a.getPackageName() + "_dexPath", new File(str2, str).getAbsolutePath());
                                        return;
                                    }
                                    Log.i("test", "Download file error" + str + " to " + str2);
                                    com.tencent.android.tpush.b.a.c(XGPushManager.a, "Download file error" + str + " to " + str2);
                                }
                            });
                            String str = "TryDyLoad";
                            CloudControlManager.a().a(a.this.a, "TryDyLoad", null, new ICloudControlDispatcher() {
                                public void a(String str) {
                                    h.b(a.this.a, "TryDyLoad_ccConfig", str);
                                }

                                public void a(String str, String str2, String str3, DownloadStatus downloadStatus, ErrorResponse errorResponse) {
                                    if (downloadStatus == DownloadStatus.Success) {
                                        try {
                                            File file = new File(str2, str);
                                            Class cls = Class.forName("com.tencent.a.a.a");
                                            com.tencent.android.tpush.cloudctr.b.a.a(file, new File(((String) cls.getMethod("getTryDyLoadBundlePath", new Class[0]).invoke(cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{a.this.a}), new Object[0])) + "/" + str));
                                            com.tencent.android.tpush.b.a.c("TryDyLoad", "下载文件成功，path" + file);
                                        } catch (Exception e) {
                                            com.tencent.android.tpush.b.a.c("TryDyLoad", "拷贝文件失败，path" + e.getMessage());
                                        }
                                    } else {
                                        com.tencent.android.tpush.b.a.c("TryDyLoad", "下载文件失败" + new File(str2, str).getAbsolutePath());
                                    }
                                }
                            });
                        }
                    });
                    String stringExtra = this.b.getStringExtra("data");
                    switch (this.b.getIntExtra("operation", -1)) {
                        case 0:
                            String stringExtra2 = this.b.getStringExtra("otherPushToken");
                            Long valueOf = Long.valueOf(this.b.getLongExtra("otherPushType", -1));
                            long longValue = valueOf.longValue() << 4;
                            if (valueOf.longValue() <= 0) {
                                stringExtra2 = stringExtra;
                            } else if (!l.c(stringExtra2) && !l.c(stringExtra.toString())) {
                                h.b(this.a, stringExtra2, stringExtra.toString());
                            }
                            this.c.onSuccess(stringExtra2, this.b.getIntExtra("flag", -1));
                            RegisterEntity registerEntity = new RegisterEntity();
                            if (this.e == 0) {
                                com.tencent.android.tpush.common.h.b(this.a, ".firstregister", 0);
                                registerEntity.state = 0;
                            } else {
                                registerEntity.state = 1;
                            }
                            registerEntity.accessId = this.b.getLongExtra("accId", 0);
                            registerEntity.packageName = this.a.getPackageName();
                            registerEntity.token = stringExtra2;
                            registerEntity.timestamp = System.currentTimeMillis() / 1000;
                            registerEntity.xgSDKVersion = 4.03f;
                            registerEntity.appVersion = l.f(this.a);
                            CacheManager.setCurrentAppRegisterEntity(this.a, registerEntity);
                            if (!l.c(registerEntity.packageName)) {
                                XGPushManager.lastSuccessRegisterMap.put(registerEntity.packageName, Long.valueOf(System.currentTimeMillis() / 1000));
                            }
                            if ((XGPushConfig.isUsedOtherPush(this.a) && d.a(this.a).g()) || (XGPushConfig.isUsedFcmPush(this.a) && k.a(this.a).c())) {
                                com.tencent.android.tpush.d.b.a(this.a);
                                break;
                            }
                        case 1:
                            this.c.onFail(stringExtra, this.b.getIntExtra("code", -1), this.b.getStringExtra(NotificationCompat.CATEGORY_MESSAGE));
                            break;
                    }
                }
                com.tencent.android.tpush.common.a.a(this.a);
                f.a(this.a).a();
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d(XGPushManager.a, "OperateRunnable", th);
            }
        }
    }

    /* compiled from: ProGuard */
    static class b extends BroadcastReceiver {
        Context a = null;
        Intent b = null;
        XGIOperateCallback c = null;
        int d = 0;

        public b(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
            this.a = context;
            this.b = intent;
            this.c = xGIOperateCallback;
            this.d = intent.getIntExtra("opType", 0);
        }

        public void onReceive(Context context, Intent intent) {
            try {
                com.tencent.android.tpush.common.c.a().b().removeCallbacks((c) XGPushManager.f.remove(this));
                switch (this.d) {
                    case 0:
                        XGPushManager.c(this.a, this.b, this.c);
                        break;
                    case 1:
                        XGPushManager.d(this.a, this.b, this.c);
                        break;
                    default:
                        com.tencent.android.tpush.b.a.i(XGPushManager.a, "RegisterStartReceiver error optype:" + this.d);
                        break;
                }
                l.a(this.a, (BroadcastReceiver) this);
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(XGPushManager.a, "RegisterStartReceiver error", e);
            }
        }
    }

    /* compiled from: ProGuard */
    static class c implements Runnable {
        Context a = null;
        Intent b = null;
        XGIOperateCallback c = null;
        int d = 0;

        public c(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
            this.a = context;
            this.b = intent;
            this.c = xGIOperateCallback;
            this.d = intent.getIntExtra("opType", 0);
        }

        public void run() {
            try {
                switch (this.d) {
                    case 0:
                        XGPushManager.c(this.a, this.b, this.c);
                        break;
                    case 1:
                        XGPushManager.d(this.a, this.b, this.c);
                        break;
                    default:
                        com.tencent.android.tpush.b.a.i(XGPushManager.a, "TimeoutRunnable error optype:" + this.d);
                        break;
                }
                for (b a2 : XGPushManager.f.keySet()) {
                    l.a(this.a, (BroadcastReceiver) a2);
                }
                XGPushManager.f.clear();
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d(XGPushManager.a, " RegisterTimeoutRunnable run error", e);
            }
        }
    }

    private XGPushManager() {
    }

    public static Context getContext() {
        return c;
    }

    public static void setContext(Context context) {
        if (c == null && context != null) {
            c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
    }

    public static void startPushService(Context context) {
        if (context != null) {
            setContext(context);
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.f(a, context.getPackageName() + "call start Push Service");
            }
            l.g(context);
            if (l.c(context) == 0) {
                l.e(context);
            }
        }
    }

    static void a(Context context) {
        if (context != null) {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.f(a, context.getPackageName() + " call stop Push Service");
            }
            Intent intent = new Intent();
            intent.setClass(StubApp.getOrigApplicationContext(context.getApplicationContext()), XGPushServiceV4.class);
            context.stopService(intent);
        }
    }

    public static int getServiceStatus(Context context) {
        if (context != null) {
            return l.c(context);
        }
        return 0;
    }

    public static void registerPush(Context context) {
        registerPush(context, (XGIOperateCallback) new XGIOperateCallback() {
            public void onSuccess(Object obj, int i) {
                com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG register push success with token : " + obj);
            }

            public void onFail(Object obj, int i, String str) {
                com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG register push failed with token : " + obj + ", errCode : " + i + " , msg : " + str);
            }
        });
    }

    public static void registerPush(Context context, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        a(context, null, null, -1, null, xGIOperateCallback, -1, null, null, null, null, 0);
    }

    public static void registerPush(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        registerPush(context, str, "0", 0, (String) null, xGIOperateCallback);
    }

    public static void registerPush(Context context, String str, int i, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        a(context, str, null, -1, null, xGIOperateCallback, -1, null, null, null, null, i);
    }

    public static void registerPush(Context context, String str, String str2, String str3, String str4, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        a(context, str, null, -1, null, xGIOperateCallback, -1, null, str2, str3, str4, 0);
    }

    public static void registerPush(Context context, String str, String str2, String str3, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        }
        a(context, null, null, -1, null, xGIOperateCallback, -1, null, str, str2, str3, 0);
    }

    public static void registerPush(Context context, String str) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
        } else {
            registerPush(context, str, new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG register push success with token = " + obj);
                }

                public void onFail(Object obj, int i, String str) {
                    com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG register push failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
                }
            });
        }
    }

    public static void bindAccount(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        registerPush(context, str, "0", 32, (String) null, xGIOperateCallback);
    }

    public static void bindAccount(Context context, String str, int i, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        registerPush(context, str, "0", 32, null, xGIOperateCallback, i);
    }

    public static void bindAccount(Context context, final String str, int i) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
        } else {
            bindAccount(context, str, i, new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG binderAccount  " + str + " success with token = " + obj);
                }

                public void onFail(Object obj, int i, String str) {
                    com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG binderAccount " + str + " failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
                }
            });
        }
    }

    public static void bindAccount(Context context, final String str) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
        } else {
            bindAccount(context, str, (XGIOperateCallback) new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG binderAccount  " + str + " success with token = " + obj);
                }

                public void onFail(Object obj, int i, String str) {
                    com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG binderAccount " + str + " failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
                }
            });
        }
    }

    public static void appendAccount(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        registerPush(context, str, "0", 48, (String) null, xGIOperateCallback);
    }

    public static void appendAccount(Context context, String str, int i, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        registerPush(context, str, "0", 48, null, xGIOperateCallback, i);
    }

    public static void appendAccount(Context context, final String str, int i) {
        appendAccount(context, str, i, new XGIOperateCallback() {
            public void onSuccess(Object obj, int i) {
                com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG appendAccount " + str + " success with token = " + obj);
            }

            public void onFail(Object obj, int i, String str) {
                com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG appendAccount failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
            }
        });
    }

    public static void appendAccount(Context context, final String str) {
        appendAccount(context, str, (XGIOperateCallback) new XGIOperateCallback() {
            public void onSuccess(Object obj, int i) {
                com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG appendAccount " + str + " success with token = " + obj);
            }

            public void onFail(Object obj, int i, String str) {
                com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG appendAccount failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
            }
        });
    }

    public static void delAccount(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        com.tencent.android.tpush.b.a.f(Constants.LogTag, "Action delAccout " + str);
        registerPush(context, str, "0", 16, (String) null, xGIOperateCallback);
    }

    public static void delAccount(Context context, String str, int i, XGIOperateCallback xGIOperateCallback) {
        if (context == null || str == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, "the parameter context or account of registerPush is invalid.");
            return;
        }
        com.tencent.android.tpush.b.a.f(Constants.LogTag, "Action delAccout " + str);
        registerPush(context, str, "0", 16, null, xGIOperateCallback, i);
    }

    public static void delAccount(Context context, final String str) {
        delAccount(context, str, new XGIOperateCallback() {
            public void onSuccess(Object obj, int i) {
                com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG deleteAccout " + str + " success  with token = " + obj);
            }

            public void onFail(Object obj, int i, String str) {
                com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG deleteAccout " + str + " failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
            }
        });
    }

    public static void delAllAccount(Context context) {
        delAllAccount(context, new XGIOperateCallback() {
            public void onSuccess(Object obj, int i) {
                com.tencent.android.tpush.b.a.f(XGPushManager.a, "XG deleteAllAccout  success with token = " + obj);
            }

            public void onFail(Object obj, int i, String str) {
                com.tencent.android.tpush.b.a.j(XGPushManager.a, "XG deleteAllAccout failed with token = " + obj + ", errCode =  " + i + ", msg = " + str);
            }
        });
    }

    public static void delAllAccount(Context context, XGIOperateCallback xGIOperateCallback) {
        delAccount(context, "*", xGIOperateCallback);
    }

    public static void registerPush(Context context, String str, String str2, int i, String str3, XGIOperateCallback xGIOperateCallback) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        } else if (context == null || l.c(str) || l.c(str2) || i < 0) {
            xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The context, account, ticket or ticketType is(are) invalid!");
        } else {
            a(context, str, str2, i, str3, xGIOperateCallback, -1, null, null, null, null, 0);
        }
    }

    public static void registerPush(Context context, String str, String str2, int i, String str3, XGIOperateCallback xGIOperateCallback, int i2) {
        if (xGIOperateCallback == null) {
            throw new IllegalArgumentException("The callback parameter can not be null!");
        } else if (context == null || l.c(str) || l.c(str2) || i < 0) {
            xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The context, account, ticket or ticketType is(are) invalid!");
        } else {
            a(context, str, str2, i, str3, xGIOperateCallback, -1, null, null, null, null, i2);
        }
    }

    public static void unregisterPush(Context context) {
        if (context == null) {
            com.tencent.android.tpush.b.a.i(Constants.LogTag, "the context of unregisterPush is null");
        } else {
            unregisterPush(context, new XGIOperateCallback() {
                public void onSuccess(Object obj, int i) {
                    com.tencent.android.tpush.b.a.f(XGPushManager.a, "UnRegisterPush push succeed with token = " + obj + " flag = " + i);
                }

                public void onFail(Object obj, int i, String str) {
                    com.tencent.android.tpush.b.a.j(XGPushManager.a, "UnRegisterPush push failed with token = " + obj + " , errCode = " + i + " , msg = " + str);
                }
            });
        }
    }

    public static void unregisterPush(Context context, String str, String str2, String str3, XGIOperateCallback xGIOperateCallback) {
        a(context, xGIOperateCallback, XGPushConfig.getAccessId(context), XGPushConfig.getAccessKey(context), str, str2, str3);
    }

    public static void unregisterPush(Context context, XGIOperateCallback xGIOperateCallback) {
        a(context, xGIOperateCallback, XGPushConfig.getAccessId(context), XGPushConfig.getAccessKey(context), null, null, null);
    }

    static void a(Context context, XGIOperateCallback xGIOperateCallback, long j, String str, String str2, String str3, String str4) {
        if (context != null) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            final XGIOperateCallback xGIOperateCallback2 = xGIOperateCallback;
            final long j2 = j;
            final String str5 = str;
            com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        int a2 = l.a(origApplicationContext);
                        if (a2 == 0) {
                            long j = j2 <= 0 ? XGPushConfig.getAccessId(origApplicationContext) : j2;
                            String str = l.c(str5) ? XGPushConfig.getAccessKey(origApplicationContext) : str5;
                            String token = XGPushConfig.getToken(origApplicationContext);
                            if ((j <= 0 || l.c(str) || l.c(token)) && xGIOperateCallback2 != null) {
                                xGIOperateCallback2.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The accessId, accessKey or token is invalid! accessId=" + j + ",accessKey=" + str + ",token=" + token);
                                throw new IllegalArgumentException("accessId, accessKey or token is invalid.");
                            }
                            Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER.V4");
                            intent.putExtra("accId", Rijndael.encrypt("" + j));
                            intent.putExtra("accKey", Rijndael.encrypt(str));
                            intent.putExtra(Constants.FLAG_TOKEN, Rijndael.encrypt(token));
                            intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(origApplicationContext.getPackageName()));
                            intent.putExtra("operation", 101);
                            intent.putExtra("opType", 1);
                            boolean b2 = k.a(origApplicationContext).b();
                            if (l.c(origApplicationContext) != 1 || b2) {
                                XGPushManager.a(origApplicationContext, intent, xGIOperateCallback2, b2);
                            } else {
                                XGPushManager.d(origApplicationContext, intent, xGIOperateCallback2);
                            }
                        } else if (xGIOperateCallback2 != null) {
                            xGIOperateCallback2.onFail(null, a2, "XINGE SDK config error");
                        }
                    } catch (Throwable th) {
                        com.tencent.android.tpush.b.a.e(Constants.LogTag, "unregisterPush", th);
                    }
                }
            });
        } else if (xGIOperateCallback != null) {
            xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The context parameter can not be null!");
        } else {
            throw new IllegalArgumentException("The context parameter can not be null!");
        }
    }

    public static void setTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "Action -> setTag with tag = " + str);
        }
        a(context, str, 1, -1, str);
    }

    static String a(Set<String> set, String str) {
        if (set == null) {
            com.tencent.android.tpush.b.a.j(Constants.LogTag, str + " -> the parameter tags is null.");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator it = set.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                break;
            }
            String replaceAll = ((String) it.next()).replaceAll(" ", "");
            if (replaceAll.length() > 40) {
                com.tencent.android.tpush.b.a.h(a, str + " -> the tag:" + replaceAll + " length is more than 40, discard it");
                i = i2;
            } else {
                if (i2 >= 1000) {
                    if (!XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.h(a, str + " -> tags size is more than 1000, discard some tags");
                        break;
                    }
                    com.tencent.android.tpush.b.a.h(a, str + " -> tags size is " + (i2 + 1) + ", so discard tag:" + replaceAll);
                } else {
                    if (i2 != 0) {
                        sb.append(" ");
                    }
                    sb.append(replaceAll);
                }
                i = i2 + 1;
            }
        }
        return sb.toString();
    }

    public static void setTags(Context context, String str, Set<String> set) {
        if (context == null || set == null || set.isEmpty()) {
            com.tencent.android.tpush.b.a.j(a, "the parameter context or tags of setTags is invalid.");
            return;
        }
        String a2 = a(set, "setTags");
        if (a2 == null) {
            com.tencent.android.tpush.b.a.j(a, "setTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "Action -> setTags with all tags = " + a2);
        }
        a(context, a2, 6, -1, str);
    }

    public static void addTags(Context context, String str, Set<String> set) {
        if (context == null || set == null || set.isEmpty()) {
            com.tencent.android.tpush.b.a.j(a, "the parameter context or tags of addTags is invalid.");
            return;
        }
        String a2 = a(set, "addTags");
        if (a2 == null) {
            com.tencent.android.tpush.b.a.j(a, "addTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "addTags -> setTags with all tags = " + a2);
        }
        a(context, a2, 5, -1, str);
    }

    public static void setKeyValueTag(Context context, String str, String str2) {
        if (context == null || str == null || str.trim().length() == 0 || str2 == null || str2.trim().length() == 0) {
            com.tencent.android.tpush.b.a.j(a, "setKeyValueTag context or tagKey or tagValue invalid.");
            return;
        }
        String str3 = str + "::::" + str2;
        com.tencent.android.tpush.b.a.f(a, "Action -> setKeyValueTag with tag = " + str3);
        a(context, str3, 3, -1, str3);
    }

    public static void sendCommReport2Service(Context context, String str, String str2) {
        long accessId = XGPushConfig.getAccessId(context);
        Intent intent = new Intent("com.tencent.android.tpush.action.COMM_REPORT.V4");
        intent.putExtra("type", 1);
        intent.putExtra("accessId", Rijndael.encrypt("" + accessId));
        intent.putExtra(MessageKey.MSG_ID, 1000);
        intent.putExtra("broadcastId", 0);
        intent.putExtra("msgTimestamp", System.currentTimeMillis() / 1000);
        intent.putExtra("clientTimestamp", System.currentTimeMillis() / 1000);
        intent.putExtra("pkgName", context.getPackageName());
        intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, Rijndael.encrypt(str));
        intent.putExtra("ext", Rijndael.encrypt(str2));
        context.sendBroadcast(intent);
    }

    public static void deleteKeyValueTag(Context context, String str, String str2) {
        if (context == null || str == null || str.trim().length() == 0) {
            com.tencent.android.tpush.b.a.j(a, "deleteKeyValueTag context or tagKey invalid.");
            return;
        }
        String str3 = str + "::::" + str2;
        com.tencent.android.tpush.b.a.f(a, "Action -> deleteKeyValueTag with tag = " + str3);
        a(context, str3, 4, -1, str3);
    }

    public static void deleteTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "Action -> deleteTag with tag = " + str);
        }
        if (context == null || str == null || str.trim().length() == 0) {
            com.tencent.android.tpush.b.a.j(a, "context is null or tagName invalid.");
        } else {
            a(context, str, 2, -1, str);
        }
    }

    public static void deleteTags(Context context, String str, Set<String> set) {
        if (context == null || set == null || set.isEmpty()) {
            com.tencent.android.tpush.b.a.j(a, "the parameter context or tags of deleteTags is invalid.");
            return;
        }
        String a2 = a(set, "deleteTags");
        if (a2 == null) {
            com.tencent.android.tpush.b.a.j(a, "deleteTags -> getTagsFromSet return null!!!");
            return;
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "deleteTags -> setTags with all tags = " + a2);
        }
        a(context, a2, 7, -1, str);
    }

    public static void cleanTags(Context context, String str) {
        if (context == null) {
            com.tencent.android.tpush.b.a.j(a, "the parameter context of cleanTags is invalid");
            return;
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(a, "Action -> cleanTags");
        }
        a(context, "*", 8, -1, str);
    }

    static void a(Context context, String str, int i, long j, String str2) {
        if (context == null) {
            throw new IllegalArgumentException("The context parameter can not be null!");
        } else if (l.a(context) <= 0) {
            if (str == null) {
                throw new IllegalArgumentException("The tagName parameter can not be null!");
            }
            if (j <= 0) {
                j = XGPushConfig.getAccessId(context);
            }
            if (j < 0) {
                throw new IllegalArgumentException("The accessId not set!");
            }
            Intent intent = new Intent("com.tencent.android.tpush.action.TAG.V4");
            intent.putExtra("accId", j);
            intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(context.getPackageName()));
            intent.putExtra(Constants.FLAG_TAG_TYPE, i);
            intent.putExtra(Constants.FLAG_TAG_NAME, Rijndael.encrypt(str));
            intent.putExtra(Constants.FLAG_TAG_OPER_NAME, str2);
            context.sendBroadcast(intent);
        }
    }

    static XGPushClickedResult a(final Activity activity) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(Constants.LogTag, ">>> onActivityStarted activity=" + activity);
        }
        if (activity == null || activity.getIntent() == null) {
            return null;
        }
        final Intent intent = activity.getIntent();
        String stringExtra = intent.getStringExtra(Constants.TAG_TPUSH_MESSAGE);
        if (stringExtra == null || !stringExtra.equalsIgnoreCase("true") || l.a(StubApp.getOrigApplicationContext(activity.getApplicationContext())) > 0) {
            return null;
        }
        XGPushClickedResult xGPushClickedResult = new XGPushClickedResult();
        xGPushClickedResult.parseIntent(intent);
        intent.removeExtra(Constants.TAG_TPUSH_MESSAGE);
        com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
            public void run() {
                XGPushManager.a((Context) activity, intent);
                XGPushManager.c(activity, intent);
            }
        });
        return xGPushClickedResult;
    }

    public static boolean isNotificationOpened(Context context) {
        return com.tencent.android.tpush.service.e.d.a(context);
    }

    public static XGPushClickedResult onActivityStarted(Activity activity) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.f(Constants.PushMessageLogTag, ">>> onActivityStarted " + activity);
        }
        if (activity == null || activity.getIntent() == null || !l.h(activity)) {
            return null;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            try {
                if (intent.hasExtra(Constants.TAG_TPUSH_NOTIFICATION)) {
                    Serializable serializableExtra = intent.getSerializableExtra(Constants.TAG_TPUSH_NOTIFICATION);
                    intent.removeExtra(Constants.TAG_TPUSH_NOTIFICATION);
                    if (serializableExtra != null && (serializableExtra instanceof XGPushClickedResult)) {
                        XGPushClickedResult xGPushClickedResult = (XGPushClickedResult) serializableExtra;
                        xGPushClickedResult.parseIntent(intent);
                        return xGPushClickedResult;
                    }
                }
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(a, "onActivityStarted", e2);
            }
        }
        return null;
    }

    public static long addLocalNotification(Context context, XGLocalMessage xGLocalMessage) {
        return a(context, xGLocalMessage, -1);
    }

    static long a(Context context, XGLocalMessage xGLocalMessage, long j) {
        if (context == null || xGLocalMessage == null) {
            try {
                com.tencent.android.tpush.b.a.i(a, "addLocalNotification context == null or msg == null");
                return -1;
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d(Constants.LogTag, "addLocalNotification ", th);
                return 0;
            }
        } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            return -1;
        } else {
            if (j <= 0) {
                j = XGPushConfig.getAccessId(context);
            }
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder(64);
            sb.append(j).append(xGLocalMessage.getMsgId()).append(context.getPackageName()).append(TextUtils.isEmpty(xGLocalMessage.getTitle()) ? "" : xGLocalMessage.getTitle()).append(TextUtils.isEmpty(xGLocalMessage.getContent()) ? "" : xGLocalMessage.getContent());
            String custom_content = xGLocalMessage.getCustom_content();
            if (TextUtils.isEmpty(custom_content) || new JSONObject(custom_content).length() == 0) {
                custom_content = "";
            }
            sb.append(custom_content);
            if (xGLocalMessage.getType() == 1) {
                sb.append(TextUtils.isEmpty(xGLocalMessage.getUrl()) ? "" : xGLocalMessage.getUrl()).append(TextUtils.isEmpty(xGLocalMessage.getIntent()) ? "" : xGLocalMessage.getIntent()).append(TextUtils.isEmpty(xGLocalMessage.getActivity()) ? "" : xGLocalMessage.getActivity());
            }
            String sb2 = sb.toString();
            String str = Constants.LOCAL_MESSAGE_FLAG + com.tencent.android.tpush.encrypt.a.a(sb2);
            long expirationTimeMs = xGLocalMessage.getExpirationTimeMs();
            com.tencent.android.tpush.common.h.b(context, str, expirationTimeMs);
            com.tencent.android.tpush.b.a.e(Constants.LogTag, sb2 + ",tag:" + str + ",exp:" + expirationTimeMs);
            TpnsPushMsg tpnsPushMsg = new TpnsPushMsg();
            tpnsPushMsg.msgId = xGLocalMessage.getMsgId();
            tpnsPushMsg.accessId = j;
            tpnsPushMsg.appPkgName = context.getPackageName();
            tpnsPushMsg.busiMsgId = xGLocalMessage.getBusiMsgId();
            tpnsPushMsg.timestamp = currentTimeMillis / 1000;
            tpnsPushMsg.serverTime = -currentTimeMillis;
            tpnsPushMsg.ttl = xGLocalMessage.getTtl();
            tpnsPushMsg.type = (long) xGLocalMessage.getType();
            tpnsPushMsg.multiPkg = 0;
            tpnsPushMsg.date = xGLocalMessage.getDate();
            tpnsPushMsg.content = "{\"title\":\"" + xGLocalMessage.getTitle() + "\",\"content\":\"" + a(xGLocalMessage.getContent()) + "\",\"builder_id\":" + xGLocalMessage.getBuilderId() + ",\"custom_content\":" + xGLocalMessage.getCustom_content() + ",\"ring\":" + xGLocalMessage.getRing() + ",\"vibrate\":" + xGLocalMessage.getVibrate() + ",\"lights\":" + xGLocalMessage.getLights() + ",\"n_id\":" + xGLocalMessage.getNotificationId() + ",\"ring_raw\":\"" + xGLocalMessage.getRing_raw() + "\",\"icon_type\":" + xGLocalMessage.getIcon_type() + ",\"icon_res\":\"" + xGLocalMessage.getIcon_res() + "\",\"style_id\":" + xGLocalMessage.getStyle_id() + ",\"small_icon\":\"" + xGLocalMessage.getSmall_icon() + "\",\"clearable\":1,\"accept_time\":[{\"start\":{\"hour\":\"" + xGLocalMessage.getHour() + "\",\"min\":\"" + xGLocalMessage.getMin() + "\"},\"end\":{\"hour\":\"23\",\"min\":\"59\"}}],\"action\":{\"action_type\":" + xGLocalMessage.getAction_type() + ",\"activity\":\"" + xGLocalMessage.getActivity() + "\",\"browser\":{\"url\":\"" + xGLocalMessage.getUrl() + "\"},\"intent\":\"" + xGLocalMessage.getIntent() + "\",\"package_name\":{\"packageDownloadUrl\":\"" + xGLocalMessage.getPackageDownloadUrl() + "\",\"packageName\":\"" + xGLocalMessage.getPackageName() + "\"}}}";
            com.tencent.android.tpush.service.channel.a aVar = new com.tencent.android.tpush.service.channel.a(Integer.valueOf(0), "127.0.0.1");
            Intent intent = new Intent(Constants.ACTION_INTERNAL_PUSH_MESSAGE);
            intent.setPackage(tpnsPushMsg.appPkgName);
            intent.putExtra(MessageKey.MSG_ID, tpnsPushMsg.msgId);
            intent.putExtra("content", Rijndael.encrypt(tpnsPushMsg.content));
            intent.putExtra(MessageKey.MSG_DATE, tpnsPushMsg.date);
            intent.putExtra("type", tpnsPushMsg.type);
            intent.putExtra("accId", tpnsPushMsg.accessId);
            intent.putExtra(MessageKey.MSG_BUSI_MSG_ID, tpnsPushMsg.busiMsgId);
            intent.putExtra(MessageKey.MSG_CREATE_TIMESTAMPS, tpnsPushMsg.timestamp);
            intent.putExtra(MessageKey.MSG_CREATE_MULTIPKG, tpnsPushMsg.multiPkg);
            intent.putExtra(MessageKey.MSG_SERVER_TIME, tpnsPushMsg.serverTime * 1000);
            intent.putExtra(MessageKey.MSG_TTL, tpnsPushMsg.ttl);
            intent.putExtra(MessageKey.MSG_SERVICE_ACK, true);
            intent.putExtra(MessageKey.MSG_EXTRA_HOST, i.c(aVar.d()));
            intent.putExtra(MessageKey.MSG_EXTRA_PORT, aVar.e());
            intent.putExtra(MessageKey.MSG_EXTRA_PACT, com.tencent.android.tpush.service.c.a(aVar.b()));
            intent.putExtra(MessageKey.MSG_EXTRA_PUSHTIME, currentTimeMillis);
            com.tencent.android.tpush.c.f.a(context).b(intent);
            return -currentTimeMillis;
        }
    }

    public static XGPushNotifactionCallback getNotifactionCallback() {
        return d;
    }

    public static void setNotifactionCallback(XGPushNotifactionCallback xGPushNotifactionCallback) {
        d = xGPushNotifactionCallback;
    }

    public static void clearLocalNotifications(Context context) {
        if (context == null) {
            com.tencent.android.tpush.b.a.i(a, "clearLocalNotifications  context==null.");
        } else if (l.a(context) <= 0) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                public void run() {
                    com.tencent.android.tpush.c.c.a().c(origApplicationContext);
                }
            });
        }
    }

    static void a(Context context, Intent intent) {
        com.tencent.android.tpush.c.c.a().e(context, intent.getLongExtra(MessageKey.MSG_ID, -1));
        Intent intent2 = new Intent("com.tencent.android.tpush.action.PUSH_CLICK.RESULT.V4");
        intent2.putExtras(intent);
        intent2.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
        intent2.putExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000);
        com.tencent.android.tpush.service.d.a.e(context, intent2);
        context.sendBroadcast(intent2);
    }

    public static void onMessageClicked(Context context, XGPushTextMessage xGPushTextMessage) {
        a(context, xGPushTextMessage.getSimpleIntent(), "com.tencent.android.tpush.action.PUSH_CLICK.RESULT.V4");
    }

    public static void onMessageCleared(Context context, XGPushTextMessage xGPushTextMessage) {
        a(context, xGPushTextMessage.getSimpleIntent(), "com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT.V4");
    }

    private static void a(Context context, Intent intent, String str) {
        if (context != null && intent != null && str != null) {
            Intent intent2 = new Intent(str);
            intent2.putExtras(intent);
            if ("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT.V4".equals(str)) {
                intent2.putExtra("action", NotificationAction.delete.getType());
            }
            intent2.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
            intent2.putExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000);
            com.tencent.android.tpush.service.d.a.e(context, intent2);
            context.sendBroadcast(intent2);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, Intent intent) {
        if (intent != null) {
            Intent intent2 = new Intent(Constants.ACTION_FEEDBACK);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra(Constants.FEEDBACK_TAG, 4);
            intent2.putExtra(Constants.FEEDBACK_ERROR_CODE, 0);
            intent2.putExtras(intent);
            context.sendBroadcast(intent2);
        }
    }

    public static void onActivityStoped(Activity activity) {
        if (activity == null) {
        }
    }

    public static void setPushNotificationBuilder(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context == null) {
            throw new IllegalArgumentException("context is null.");
        } else if (i < 1 || i > 4096) {
            throw new IllegalArgumentException("notificationBulderId不在范围[1, 4096].");
        } else if (xGPushNotificationBuilder != null) {
            com.tencent.android.tpush.c.b.a(context, i, xGPushNotificationBuilder);
        }
    }

    public static void setDefaultNotificationBuilder(Context context, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context != null && xGPushNotificationBuilder != null) {
            com.tencent.android.tpush.c.b.a(context, 0, xGPushNotificationBuilder);
        }
    }

    public static XGPushNotificationBuilder getDefaultNotificationBuilder(Context context) {
        XGPushNotificationBuilder notificationBuilder = getNotificationBuilder(context, 0);
        if (notificationBuilder == null) {
            com.tencent.android.tpush.c.b.a(context);
        }
        return notificationBuilder;
    }

    public static XGPushNotificationBuilder getNotificationBuilder(Context context, int i) {
        if (context != null) {
            return com.tencent.android.tpush.c.b.a(context, i);
        }
        Log.e(Constants.LogTag, "getNotificationBuilder  context == null");
        return null;
    }

    public static void cancelNotifaction(Context context, int i) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(i);
        } catch (Exception e2) {
        }
    }

    public static void cancelAllNotifaction(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancelAll();
        } catch (Exception e2) {
        }
    }

    public static void msgAck(Context context, g gVar) {
        if (context != null && gVar != null) {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.a(a, "Action -> msgAck(" + context.getPackageName() + StorageInterface.KEY_SPLITER + gVar.b() + ")");
            }
            com.tencent.android.tpush.b.a.a(3, gVar.b());
            if (gVar.b() > 0) {
                MessageId a2 = com.tencent.android.tpush.c.c.a().a(context, context.getPackageName(), gVar.b());
                if (a2 == null) {
                    com.tencent.android.tpush.b.a.h(a, "Action -> msgAck(" + context.getPackageName() + StorageInterface.KEY_SPLITER + gVar.b() + ")error, no the id: " + gVar.b());
                    return;
                }
                Intent intent = new Intent("com.tencent.android.tpush.action.MSG_ACK.V4");
                intent.putExtra(MessageKey.MSG_ID, gVar.b());
                intent.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
                intent.putExtra("MessageId", a2);
                intent.putExtra(MessageKey.MSG_CHANNEL_ID, gVar.h());
                context.sendBroadcast(intent);
            }
        }
    }

    public static String getServiceTag(Context context) {
        if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            return "xg_service_enable";
        }
        return Rijndael.encrypt(XGPushConfig.getAccessId(context) + StorageInterface.KEY_SPLITER + "xg_service_enable");
    }

    public static void enableService(final Context context, boolean z) {
        if (context != null) {
            if (!z) {
                com.tencent.android.tpush.b.a.i(Constants.LogTag, "XG is disable.");
                unregisterPush(context, new XGIOperateCallback() {
                    public void onSuccess(Object obj, int i) {
                        XGPushManager.a(context);
                    }

                    public void onFail(Object obj, int i, String str) {
                        XGPushManager.a(context);
                    }
                });
            }
            enableService = z ? 1 : 0;
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.f(a, "enableService=" + enableService);
            }
            com.tencent.android.tpush.common.g.a(context, context.getPackageName() + ENABLE_SERVICE_SUFFIX, enableService);
        }
    }

    static void a(Context context, String str, String str2, int i, String str3, XGIOperateCallback xGIOperateCallback, long j, String str4, String str5, String str6, String str7, int i2) {
        setContext(context);
        if (context == null) {
            xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The context parameter can not be null!");
            return;
        }
        if (!b) {
            com.tencent.android.tpush.service.d.a.b(context);
            b = true;
        }
        int i3 = i >> 4;
        if ((i3 <= 0 || i3 > 4) && Math.abs(System.currentTimeMillis() - e.longValue()) < ((long) 1000)) {
            xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_REGISTER_IN_PROCESS.getType(), "duplicate register request");
            return;
        }
        e = Long.valueOf(System.currentTimeMillis());
        Long l = (Long) lastSuccessRegisterMap.get(context.getPackageName());
        if (l == null || Math.abs((System.currentTimeMillis() / 1000) - l.longValue()) >= 3) {
            final Context context2 = context;
            final XGIOperateCallback xGIOperateCallback2 = xGIOperateCallback;
            final long j2 = j;
            final String str8 = str4;
            final String str9 = str;
            final int i4 = i2;
            final int i5 = i;
            final String str10 = str2;
            final String str11 = str3;
            final String str12 = str5;
            final String str13 = str7;
            final String str14 = str6;
            com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                public void run() {
                    long accessId;
                    long j2;
                    long j3 = 0;
                    try {
                        int a2 = l.a(context2);
                        if (a2 == 0) {
                            if (j2 > 0) {
                                accessId = j2;
                            } else {
                                accessId = XGPushConfig.getAccessId(context2);
                            }
                            String str = l.c(str8) ? XGPushConfig.getAccessKey(context2) : str8;
                            if (accessId <= 0 || l.c(str)) {
                                xGIOperateCallback2.onFail(null, ReturnCode.CODE_LOGIC_ILLEGAL_ARGUMENT.getType(), "The accessId or accessKey is(are) invalid!@accessId:" + accessId + ", @accessKey:" + str);
                                return;
                            }
                            if ((XGPushConfig.isUsedOtherPush(context2) && d.a(context2).g()) || (XGPushConfig.isUsedFcmPush(context2) && k.a(context2).c())) {
                                d.a(context2).b();
                                long currentTimeMillis = System.currentTimeMillis();
                                while (true) {
                                    if (System.currentTimeMillis() - currentTimeMillis >= 30000) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(200);
                                        String d2 = d.a(context2).d();
                                        if (!l.c(d2)) {
                                            com.tencent.android.tpush.b.a.e(Constants.OTHER_PUSH_TAG, "get otherToken is : " + d2);
                                            break;
                                        }
                                    } catch (InterruptedException e2) {
                                    } catch (Exception e3) {
                                        com.tencent.android.tpush.b.a.i(Constants.OTHER_PUSH_TAG, "OtherPush: call getToken Error!.");
                                    }
                                }
                            }
                            l.g(context2);
                            Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.V4");
                            intent.putExtra("accId", Rijndael.encrypt("" + accessId));
                            intent.putExtra("accChannel", XGPushConfig.getChannelId(context2));
                            intent.putExtra("accKey", Rijndael.encrypt(str));
                            if (str9 != null) {
                                intent.putExtra(Constants.FLAG_ACCOUNT, Rijndael.encrypt(str9));
                                intent.putExtra("accountType", i4);
                            }
                            if ((i5 >> 4) != 1) {
                                intent.putExtra("appVer", l.f(context2));
                                intent.putExtra(Constants.FLAG_PACK_NAME, Rijndael.encrypt(context2.getPackageName()));
                                if (com.tencent.android.tpush.common.i.a(context2) != null) {
                                    intent.putExtra("reserved", Rijndael.encrypt(com.tencent.android.tpush.common.i.a(context2).a()));
                                }
                                if (str10 != null) {
                                    intent.putExtra(Constants.FLAG_TICKET, Rijndael.encrypt(str10));
                                }
                                if (str11 != null) {
                                    intent.putExtra("qua", Rijndael.encrypt(str11));
                                }
                                intent.putExtra("operation", 100);
                                intent.putExtra("aidl", l.b(context2));
                            }
                            intent.putExtra(Constants.FLAG_TICKET_TYPE, i5);
                            intent.putExtra("currentTimeMillis", System.currentTimeMillis());
                            intent.putExtra("opType", 0);
                            if (!l.c(str12)) {
                                intent.putExtra("url", str12);
                                j3 = 4;
                            }
                            if (!l.c(str13)) {
                                intent.putExtra("otherToken", str13);
                                if (!h.a(context2, str13, "").equals(CacheManager.getToken(context2))) {
                                    j2 = 2;
                                } else {
                                    j2 = 3;
                                }
                            } else {
                                j2 = 1;
                            }
                            if (!l.c(str14)) {
                                intent.putExtra("payload", str14);
                            }
                            intent.putExtra("otherPushTokenOpType", j2);
                            intent.putExtra("otherPushType", j3);
                            com.tencent.android.tpush.b.a.c("XGPushManager", "url = " + str12 + " payload = " + str14 + " otherPushType " + j3 + " otherPushTokenOpType " + j2);
                            boolean a3 = k.a(context2).a();
                            if (l.c(context2) != 1 || a3 || !i.v(context2)) {
                                XGPushManager.a(context2, intent, xGIOperateCallback2, a3);
                            } else {
                                XGPushManager.c(context2, intent, xGIOperateCallback2);
                                com.tencent.android.tpush.service.b.b(context2);
                            }
                            if (XGPushConfig.isReportNotificationStatusEnable(context2)) {
                                i.c(context2);
                            }
                            if (XGPushConfig.isReportApplistEnable(context2)) {
                                i.b(context2);
                            }
                        } else if (xGIOperateCallback2 != null) {
                            xGIOperateCallback2.onFail(null, a2, ReturnCode.errCodeToMsg(a2));
                        }
                    } catch (Throwable th) {
                        com.tencent.android.tpush.b.a.d(XGPushManager.a, "register", th);
                    }
                }
            });
            return;
        }
        xGIOperateCallback.onFail(null, ReturnCode.CODE_LOGIC_REGISTER_IN_PROCESS.getType(), "duplicate register request");
    }

    static void a(Context context, Intent intent, XGIOperateCallback xGIOperateCallback, boolean z) {
        l.e(context);
        b bVar = new b(context, intent, xGIOperateCallback);
        try {
            context.registerReceiver(bVar, new IntentFilter("com.tencent.android.tpush.action.SERVICE_START.V4"));
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "Receiver not registered exception error : ", th);
        }
        c cVar = new c(context, intent, xGIOperateCallback);
        try {
            f.put(bVar, cVar);
            com.tencent.android.tpush.common.c.a().a(cVar, OkHttpUtils.DEFAULT_MILLISECONDS);
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d(Constants.LogTag, "mapTimeRunnableOfMessage error", e2);
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void c(Context context, Intent intent, final XGIOperateCallback xGIOperateCallback) {
        synchronized (XGPushManager.class) {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.f(a, "Action -> Register to xinge server");
            }
            if (xGIOperateCallback != null) {
                try {
                    context.registerReceiver(new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            if (XGPushConfig.enableDebug) {
                                com.tencent.android.tpush.b.a.f(XGPushManager.a, "Register call back to " + context.getPackageName());
                            }
                            try {
                                com.tencent.android.tpush.common.c.a().a((Runnable) new a(xGIOperateCallback, context, intent, 1, 0));
                            } catch (Exception e) {
                            }
                            l.a(context, (BroadcastReceiver) this);
                        }
                    }, new IntentFilter("com.tencent.android.tpush.action.REGISTER.RESULT.V4"));
                } catch (Throwable th) {
                }
            }
            context.sendBroadcast(intent);
            try {
                XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionReadyPatch, 0, "XGPushManager load Start", null);
                Class cls = Class.forName("com.tencent.a.a.a");
                Object invoke = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context});
                cls.getMethod("doSomething", new Class[]{String.class}).invoke(invoke, new Object[]{"XGPushManager register"});
                XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionParsePatch, 0, "XGPushManager loaded", null);
            } catch (Throwable th2) {
                com.tencent.android.tpush.b.a.c("xiangchen", th2.getMessage());
                XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), XGPatchMonitor.TypeTryDyLoad, XGPatchMonitor.ActionParsePatch, 1801, "XGPushManager load failed", null);
            }
        }
        return;
    }

    /* access modifiers changed from: private */
    public static void d(Context context, Intent intent, final XGIOperateCallback xGIOperateCallback) {
        try {
            context.registerReceiver(new BroadcastReceiver() {
                public void onReceive(final Context context, Intent intent) {
                    l.a(context, (BroadcastReceiver) this);
                    if ((k.a(context).c() && XGPushConfig.isUsedFcmPush(context)) || (XGPushConfig.isUsedOtherPush(context) && d.a(context).a())) {
                        try {
                            com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                                public void run() {
                                    try {
                                        d.a(context).c();
                                    } catch (Throwable th) {
                                    }
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                    if (xGIOperateCallback != null) {
                        try {
                            com.tencent.android.tpush.common.c.a().a((Runnable) new a(xGIOperateCallback, context, intent, 1, 1));
                        } catch (Exception e2) {
                        }
                    }
                }
            }, new IntentFilter("com.tencent.android.tpush.action.UNREGISTER.RESULT.V4"));
            context.sendBroadcast(intent);
        } catch (Throwable th) {
        }
    }

    private static String a(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case '\"':
                case '/':
                case '\\':
                    sb.append('\\').append(charAt);
                    break;
                default:
                    if (charAt > 31) {
                        sb.append(charAt);
                        break;
                    } else {
                        sb.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                        break;
                    }
            }
        }
        return sb.toString();
    }

    public static void openNotification(Context context) {
        l.k(context);
    }

    public static void openNotificationSettings(Context context) {
        l.l(context);
    }
}
