package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.ReturnCode;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.f;
import com.tencent.android.tpush.service.e.h;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.StorageInterface;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    public static final String a = a.class.getSimpleName();
    private static a b = null;
    private static volatile C0065a c = null;
    private static volatile c d = null;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public ArrayList<Intent> f = new ArrayList<>();

    /* renamed from: com.tencent.android.tpush.service.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private class C0065a extends BroadcastReceiver {
        private C0065a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && context != null) {
                if (a.this.e) {
                    com.tencent.android.tpush.common.c.a().a((Runnable) new b(context, intent));
                } else if (a.this.f.size() < 10) {
                    a.this.f.add(intent);
                    com.tencent.android.tpush.b.a.f(a.a, "PackageChangesReceiver add intend to beforeInitedIntents,action:" + intent.getAction() + ", size:" + a.this.f.size());
                } else {
                    com.tencent.android.tpush.b.a.j(a.a, "Too much beforeInitedIntents. discard it");
                }
            }
        }
    }

    /* compiled from: ProGuard */
    private class b implements Runnable {
        private Context b = null;
        private Intent c = null;

        public b(Context context, Intent intent) {
            this.b = context;
            this.c = intent;
        }

        public void run() {
            try {
                String action = this.c.getAction();
                if (action != null) {
                    if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
                        a.this.a(this.b, this.c);
                    } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                        a.this.b(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.REGISTER.V4".equals(action)) {
                        a.this.c(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.UNREGISTER.V4".equals(action)) {
                        a.this.e(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.ENABLE_DEBUG.V4".equals(action)) {
                        a.this.h(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.MSG_ACK.V4".equals(action)) {
                        com.tencent.android.tpush.service.c.a.a().a(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.TAG.V4".equals(action)) {
                        a.this.d(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.PUSH_CLICK.RESULT.V4".equals(action)) {
                        com.tencent.android.tpush.service.c.a.a().c(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT.V4".equals(action)) {
                        com.tencent.android.tpush.service.c.a.a().c(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.ack.sdk2srv.V4".equals(action)) {
                        com.tencent.android.tpush.service.d.a.a(this.c);
                    } else if ("com.tencent.android.tpush.action.UPDATE_OTHER_PUSH_TOKEN.V4".equals(action)) {
                        a.this.f(this.b, this.c);
                    } else if ("com.tencent.android.tpush.action.COMM_REPORT.V4".equals(action)) {
                        a.this.g(this.b, this.c);
                    }
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d(a.a, a.a + " run error.", th);
            }
        }
    }

    /* compiled from: ProGuard */
    private class c extends BroadcastReceiver {
        private c() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && context != null) {
                if (a.this.e) {
                    com.tencent.android.tpush.common.c.a().a((Runnable) new b(context, intent));
                } else if (a.this.f.size() < 10) {
                    a.this.f.add(intent);
                    com.tencent.android.tpush.b.a.f(a.a, "TPushAppReceiver add intend to beforeInitedIntents,action:" + intent.getAction() + ", size:" + a.this.f.size());
                } else {
                    com.tencent.android.tpush.b.a.j(a.a, "Too much beforeInitedIntents. discard it");
                }
            }
        }
    }

    public void a(Context context) {
        com.tencent.android.tpush.b.a.f(a, "handleServiceInited, beforeInitedIntents size:" + this.f.size());
        this.e = true;
        try {
            Intent intent = new Intent("com.tencent.android.tpush.action.SERVICE_START.V4");
            intent.putExtra(Config.INPUT_DEF_PKG, b.f().getPackageName());
            intent.putExtra(MidEntity.TAG_VER, 4.03f);
            b.f().sendBroadcast(intent);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(a, "sendBroadcast", th);
        }
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            com.tencent.android.tpush.common.c.a().a((Runnable) new b(context, (Intent) it.next()));
        }
        this.f.clear();
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    public synchronized void b(Context context) {
        if (context != null) {
            try {
                if (c == null) {
                    c = new C0065a();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addDataScheme("package");
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                    context.registerReceiver(c, intentFilter);
                }
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(a, "registerReceiver", e2);
            }
            try {
                if (d == null) {
                    d = new c();
                    IntentFilter intentFilter2 = new IntentFilter();
                    intentFilter2.addAction("com.tencent.android.tpush.action.REGISTER.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.UNREGISTER.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.ENABLE_DEBUG.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.MSG_ACK.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.TAG.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.PUSH_CLICK.RESULT.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.ack.sdk2srv.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.reserved.act.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.UPDATE_OTHER_PUSH_TOKEN.V4");
                    intentFilter2.addAction("com.tencent.android.tpush.action.COMM_REPORT.V4");
                    context.registerReceiver(d, intentFilter2);
                }
            } catch (Exception e3) {
                com.tencent.android.tpush.b.a.d(a, "registerReceiver", e3);
            }
        }
        return;
    }

    public synchronized void c(Context context) {
        this.f.clear();
        if (context != null) {
            if (c != null) {
                l.a(context, (BroadcastReceiver) c);
                c = null;
            }
            if (d != null) {
                l.a(context, (BroadcastReceiver) d);
                d = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final Context context, Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && context != null && i.h(context, dataString.substring(8))) {
            b.a().d();
            com.tencent.android.tpush.common.c.a().a(new Runnable() {
                public void run() {
                    l.e(context);
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public void b(final Context context, Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && context != null) {
            final String substring = dataString.substring(8);
            com.tencent.android.tpush.common.c.a().a(new Runnable() {
                public void run() {
                    try {
                        context.getPackageManager().getApplicationInfo(substring, 8192);
                    } catch (NameNotFoundException e) {
                        com.tencent.android.tpush.b.a.c(a.a, "appRemoveHandler check app:" + substring + " has been removed.");
                        CacheManager.removeRegisterInfos(substring);
                        c.a().a(substring);
                    } catch (Throwable th) {
                    }
                }
            }, 30000);
        }
    }

    /* access modifiers changed from: private */
    public void d(final Context context) {
        boolean z = true;
        if (com.tencent.android.tpush.service.e.a.a(context) == 3) {
            final long currentTimeMillis = System.currentTimeMillis();
            long b2 = f.b(context, "com.tencent.android.tpush.action.next.applist.ts.V4", 0);
            if (b2 != 0 && currentTimeMillis <= b2 && Math.abs(b2 - currentTimeMillis) <= 172800000) {
                z = false;
            }
            if (z) {
                com.tencent.android.tpush.common.c.a().a(new Runnable() {
                    public void run() {
                        TpnsClientReport tpnsClientReport = new TpnsClientReport();
                        tpnsClientReport.commandId = 0;
                        tpnsClientReport.signal = i.p(context).toString();
                        TpnsClientReportReq tpnsClientReportReq = new TpnsClientReportReq();
                        tpnsClientReportReq.reportMsgs = new ArrayList<>();
                        tpnsClientReportReq.reportMsgs.add(tpnsClientReport);
                        com.tencent.android.tpush.service.channel.b.a().a((JceStruct) tpnsClientReportReq, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                            public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                                if (i == 0) {
                                    try {
                                        f.a(context, "com.tencent.android.tpush.action.next.applist.ts.V4", currentTimeMillis + 86400000);
                                    } catch (Throwable th) {
                                    }
                                }
                            }

                            public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                                com.tencent.android.tpush.b.a.i(a.a, ">>> reportReq onMessageSendFailed(" + jceStruct + ", " + channelException + ", " + aVar + ")");
                            }

                            public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                                com.tencent.android.tpush.b.a.i(a.a, ">>> reportReq onMessageDiscarded(" + jceStruct + ", " + aVar + ")");
                            }
                        });
                    }
                }, Config.BPLUS_DELAY_TIME);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(Context context, Intent intent) {
        String str;
        if (context != null && intent != null) {
            final String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            String decrypt2 = Rijndael.decrypt(intent.getStringExtra("accKey"));
            final String decrypt3 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            String decrypt4 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_ACCOUNT));
            int intExtra = intent.getIntExtra("accountType", -1);
            String decrypt5 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TICKET));
            int intExtra2 = intent.getIntExtra(Constants.FLAG_TICKET_TYPE, -1);
            String decrypt6 = Rijndael.decrypt(intent.getStringExtra("qua"));
            String stringExtra = intent.getStringExtra("appVer");
            String decrypt7 = Rijndael.decrypt(intent.getStringExtra("reserved"));
            Log.d(a, "receive intent, reserved: " + decrypt7);
            long longExtra = intent.getLongExtra("accChannel", -1);
            String stringExtra2 = intent.getStringExtra("url");
            String stringExtra3 = intent.getStringExtra("otherToken");
            String stringExtra4 = intent.getStringExtra("payload");
            long longExtra2 = intent.getLongExtra("otherPushType", -1);
            long longExtra3 = intent.getLongExtra("otherPushTokenOpType", -1);
            final boolean booleanExtra = intent.getBooleanExtra("aidl", false);
            try {
                if (!i.b(decrypt6)) {
                    CacheManager.setQua(context, Long.parseLong(decrypt), decrypt6);
                }
                String str2 = "";
                try {
                    str = TpnsSecurity.getEncryptAPKSignature(context.createPackageContext(decrypt3, 0));
                } catch (Throwable th) {
                }
                final Context context2 = context;
                c.a().a(Long.parseLong(decrypt), decrypt2, com.tencent.android.tpush.service.e.c.a(), decrypt4, decrypt5, intExtra2, str, stringExtra, decrypt7, longExtra2, stringExtra2, stringExtra4, stringExtra3, longExtra3, longExtra, intExtra, new com.tencent.android.tpush.service.channel.c.a() {
                    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                        com.tencent.android.tpush.service.channel.b.a().a(true, decrypt3);
                        if (i == 0) {
                            if (XGPushConfig.enableDebug) {
                                com.tencent.android.tpush.b.a.c(a.a, ">> Register [accId = " + decrypt + " , packName = " + decrypt3 + " , rsp = " + aVar.c() + "]");
                            }
                            a.this.a(i, (TpnsRegisterRsp) jceStruct2, (TpnsRegisterReq) jceStruct, aVar, decrypt3, booleanExtra);
                            try {
                                a.this.d(context2);
                            } catch (Throwable th) {
                                com.tencent.android.tpush.b.a.d(a.a, "handler app info failed", th);
                            }
                        } else {
                            com.tencent.android.tpush.b.a.i(a.a, ">> Register ack fail responseCode = " + i);
                            a.this.a(i, "服务器处理失败，返回错误", (TpnsRegisterReq) jceStruct, aVar, decrypt3);
                        }
                    }

                    public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                        com.tencent.android.tpush.b.a.j(a.a, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                        a.this.a(channelException.errorCode, channelException.getMessage(), (TpnsRegisterReq) jceStruct, aVar, decrypt3);
                    }

                    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                    }
                });
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.i(a, ">> register error " + e2);
                com.tencent.android.tpush.b.a.i(a, ">> register error-> " + Log.getStackTraceString(e2));
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(Context context, Intent intent) {
        if (context != null && intent != null) {
            final long longExtra = intent.getLongExtra("accId", -1);
            final String decrypt = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            final int intExtra = intent.getIntExtra(Constants.FLAG_TAG_TYPE, -1);
            final String decrypt2 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TAG_NAME));
            final String stringExtra = intent.getStringExtra(Constants.FLAG_TAG_OPER_NAME);
            c.a().a(longExtra, decrypt, intExtra, decrypt2, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    if (i == 0) {
                        com.tencent.android.tpush.b.a.f(a.a, "Set tag ack success  [accId = " + longExtra + " , tagtype = " + intExtra + " , tagName = " + decrypt2 + ", packName = " + decrypt + " , rsp = " + aVar.c() + "]");
                    } else {
                        com.tencent.android.tpush.b.a.j(a.a, "Set tag ack failed with responseCode = " + i + " , tagName = " + decrypt2);
                    }
                    a.this.a(i, decrypt2, intExtra, decrypt, stringExtra);
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    if (channelException != null) {
                        a.this.a(channelException.errorCode, decrypt2, intExtra, decrypt, stringExtra);
                    }
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.b.a.h(a.a, "Set tag onMessageDiscarded  , tagName = " + decrypt2);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e(Context context, Intent intent) {
        if (context != null && intent != null) {
            final String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            String decrypt2 = Rijndael.decrypt(intent.getStringExtra("accKey"));
            final String decrypt3 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_PACK_NAME));
            String decrypt4 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TOKEN));
            CacheManager.UnregisterInfoByPkgName(decrypt3);
            try {
                c.a().a(decrypt4, com.tencent.android.tpush.service.e.c.a(), Long.parseLong(decrypt), decrypt2, decrypt3, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                        if (i == 0) {
                            com.tencent.android.tpush.b.a.f(a.a, ">> UnRegister ack with [accId = " + decrypt + " , packName = " + decrypt3 + " , rsp = " + aVar.c() + "]");
                            a.this.a(i, (TpnsUnregisterReq) jceStruct, aVar, decrypt3);
                            return;
                        }
                        com.tencent.android.tpush.b.a.i(a.a, ">> unregeister ack failed responseCode=" + i);
                        a.this.a(i, "服务器处理失败，返回错误", (TpnsUnregisterReq) jceStruct, aVar, decrypt3);
                    }

                    public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                        com.tencent.android.tpush.b.a.i(a.a, "@@ unregister onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                        a.this.a(channelException.errorCode, channelException.getMessage(), (TpnsUnregisterReq) jceStruct, aVar, decrypt3);
                    }

                    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                    }
                });
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.i(a, ">>> unregister error " + e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void f(Context context, Intent intent) {
        if (context != null && intent != null) {
            final String decrypt = Rijndael.decrypt(intent.getStringExtra("accId"));
            final String decrypt2 = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TOKEN));
            final String decrypt3 = Rijndael.decrypt(intent.getStringExtra("other_push_type"));
            final String decrypt4 = Rijndael.decrypt(intent.getStringExtra(Constants.OTHER_PUSH_TOKEN));
            com.tencent.android.tpush.b.a.e(a, "binder other push token with accid = " + decrypt + "  token = " + decrypt2 + " otherPushType = " + decrypt3 + " otherPushToken = " + decrypt4);
            l.a("binder other push token with accid = " + decrypt + "  token = " + decrypt2 + " otherPushType = " + decrypt3 + " otherPushToken = " + decrypt4, context);
            String a2 = h.a(context, decrypt + "otherpush", "");
            if (!i.b(a2)) {
                long a3 = h.a(context, decrypt + "otherpushts", -1);
                if (!a2.equals(decrypt2 + Config.TRACE_TODAY_VISIT_SPLIT + decrypt4) || Math.abs(System.currentTimeMillis() - a3) <= 86400000) {
                    com.tencent.android.tpush.b.a.f(a, "OtherToken or Mid changed , go on binder");
                } else {
                    com.tencent.android.tpush.b.a.f(a, "Already binder other push succeed token with accid = " + decrypt + "  token = " + decrypt2 + " otherPushType = " + decrypt3 + " otherPushToken = " + decrypt4);
                    return;
                }
            }
            final Context context2 = context;
            c.a().a(Long.parseLong(decrypt), decrypt2, decrypt3, decrypt4, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    if (i == 0) {
                        l.a("bind OtherPushToken success ack with= " + decrypt + "  token = " + decrypt2 + " otherPushType = " + decrypt3 + " otherPushToken = " + decrypt4, context2);
                        com.tencent.android.tpush.b.a.f(a.a, ">> bind OtherPushToken success ack with [accId = " + decrypt + "  , rsp = " + i + "]  token = " + decrypt2 + " otherPushType = " + decrypt3 + " otherPushToken = " + decrypt4);
                        h.b(context2, decrypt + "otherpush", decrypt2 + Config.TRACE_TODAY_VISIT_SPLIT + decrypt4);
                        h.b(context2, decrypt + MidEntity.TAG_TIMESTAMPS, System.currentTimeMillis());
                        return;
                    }
                    l.a("updateOtherPushToken ack failed responseCode=" + i, context2);
                    com.tencent.android.tpush.b.a.j(a.a, ">> updateOtherPushToken ack failed responseCode=" + i);
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.b.a.j(a.a, "@@ updateOtherPushToken onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void g(Context context, Intent intent) {
        if (context != null && intent != null) {
            final String decrypt = Rijndael.decrypt(intent.getStringExtra("accessId"));
            final String stringExtra = intent.getStringExtra("pkgName");
            final Context context2 = context;
            final Intent intent2 = intent;
            c.a().a(intent, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    if (i == 0) {
                        com.tencent.android.tpush.b.a.f(a.a, ">> sendCommReportMessage ack with [accId = " + decrypt + "  , rsp = " + i + "]");
                        com.tencent.android.tpush.a.d(context2, stringExtra, intent2.toURI());
                        return;
                    }
                    com.tencent.android.tpush.b.a.j(a.a, ">> sendCommReportMessage ack failed responseCode=" + i);
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.b.a.j(a.a, "@@ sendCommReportMessage onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void h(Context context, Intent intent) {
        if (intent != null && context != null) {
            boolean booleanExtra = intent.getBooleanExtra("debugMode", false);
            XGPushConfig.enableDebug = booleanExtra;
            if (booleanExtra) {
                com.tencent.android.tpush.b.a.a(2);
            } else {
                com.tencent.android.tpush.b.a.a(3);
            }
        }
    }

    private static boolean a(TpnsRegisterReq tpnsRegisterReq) {
        if (tpnsRegisterReq == null) {
            return false;
        }
        String str = tpnsRegisterReq.reserved;
        if (str != null) {
            return str.contains("ccver");
        }
        return false;
    }

    private void a(int i, TpnsRegisterReq tpnsRegisterReq, String str, String str2, long j, String str3) {
        Intent a2 = i.a(i, str2, 1);
        a2.putExtra("accId", tpnsRegisterReq.accessId);
        if (!(tpnsRegisterReq.account == null || tpnsRegisterReq.account.length() == 0)) {
            a2.putExtra(Constants.FLAG_ACCOUNT, tpnsRegisterReq.account);
        }
        if (!(str == null || str.length() == 0)) {
            a2.putExtra(Constants.FLAG_TOKEN, str);
        }
        if (!(tpnsRegisterReq.ticket == null || tpnsRegisterReq.ticket.length() == 0)) {
            a2.putExtra(Constants.FLAG_TICKET, tpnsRegisterReq.ticket);
            a2.putExtra(Constants.FLAG_TICKET_TYPE, tpnsRegisterReq.ticketType);
        }
        if (!(tpnsRegisterReq.deviceId == null || tpnsRegisterReq.deviceId.length() == 0)) {
            a2.putExtra(Constants.FLAG_DEVICE_ID, tpnsRegisterReq.deviceId);
        }
        if (j >= 0) {
            a2.putExtra("otherPushType", j);
            if (!l.c(str3)) {
                a2.putExtra("otherPushToken", str3);
            }
        }
        if (a(tpnsRegisterReq)) {
            a2.putExtra(Constants.FLAG_REGISTER_FROM_CLOUDCTRL, true);
        }
        b.f().sendBroadcast(a2);
    }

    private void a(int i, String str) {
        b.f().sendBroadcast(i.a(i, str, 2));
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, int i2, String str2, String str3) {
        Intent a2 = i.a(i, str2, 3);
        a2.putExtra(Constants.FLAG_TAG_NAME, Rijndael.encrypt(str));
        a2.putExtra(Constants.FLAG_TAG_TYPE, i2);
        a2.putExtra(Constants.FLAG_TAG_OPER_NAME, str3);
        b.f().sendBroadcast(a2);
    }

    /* access modifiers changed from: private */
    public void a(int i, TpnsRegisterRsp tpnsRegisterRsp, TpnsRegisterReq tpnsRegisterReq, com.tencent.android.tpush.service.channel.a aVar, String str, boolean z) {
        com.tencent.android.tpush.stat.b.c.b();
        Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT.V4");
        intent.putExtra("accId", tpnsRegisterReq.accessId);
        intent.putExtra("data", tpnsRegisterRsp.token);
        intent.putExtra("flag", ReturnCode.FLAG_ONLINE.getType());
        intent.putExtra("code", i);
        intent.putExtra("operation", 0);
        intent.putExtra("otherPushType", tpnsRegisterRsp.otherPushTokenType);
        intent.putExtra("otherPushToken", tpnsRegisterRsp.otherPushToken);
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.accessId = tpnsRegisterReq.accessId;
        registerEntity.accessKey = tpnsRegisterReq.accessKey;
        registerEntity.token = tpnsRegisterRsp.token;
        registerEntity.channelId = tpnsRegisterReq.channelId;
        registerEntity.packageName = str;
        registerEntity.timestamp = System.currentTimeMillis() / 1000;
        CacheManager.addRegisterInfo(registerEntity);
        registerEntity.guid = tpnsRegisterRsp.guid;
        CacheManager.setTokenAndGuid(b.f(), tpnsRegisterRsp.token, tpnsRegisterRsp.guid);
        if (!i.b(str)) {
            intent.setPackage(str);
        }
        b.f().sendBroadcast(intent);
        a(i, tpnsRegisterReq, tpnsRegisterRsp.token, str, tpnsRegisterRsp.otherPushTokenType, tpnsRegisterRsp.otherPushToken);
        com.tencent.android.tpush.service.channel.b.a().a(false);
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("aidl", z);
            jSONObject.toString();
        } catch (JSONException e2) {
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, TpnsRegisterReq tpnsRegisterReq, com.tencent.android.tpush.service.channel.a aVar, String str2) {
        com.tencent.android.tpush.stat.b.c.b();
        Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.RESULT.V4");
        intent.putExtra("data", "");
        intent.putExtra("code", i);
        intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, str);
        intent.putExtra("flag", ReturnCode.FLAG_ONLINE.getType());
        intent.putExtra("operation", 1);
        if (!i.b(str2)) {
            intent.setPackage(str2);
        }
        b.f().sendBroadcast(intent);
        a(i, tpnsRegisterReq, tpnsRegisterReq.token, str2, -1, (String) null);
    }

    /* access modifiers changed from: private */
    public void a(int i, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar, String str) {
        Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT.V4");
        intent.putExtra("flag", ReturnCode.FLAG_ONLINE.getType());
        intent.putExtra("operation", 0);
        CacheManager.UnregisterInfoSuccessByPkgName(str);
        CacheManager.removeRegisterInfos(str);
        if (!l.c(str)) {
            intent.setPackage(str);
        }
        b.f().sendBroadcast(intent);
        a(i, str);
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar, String str2) {
        com.tencent.android.tpush.b.a.i(a, "unregisterFailHandler failed with (" + i + StorageInterface.KEY_SPLITER + str + StorageInterface.KEY_SPLITER + tpnsUnregisterReq + StorageInterface.KEY_SPLITER + aVar + StorageInterface.KEY_SPLITER + str2 + ")");
        Intent intent = new Intent("com.tencent.android.tpush.action.UNREGISTER.RESULT.V4");
        intent.putExtra("flag", ReturnCode.FLAG_ONLINE.getType());
        intent.putExtra("code", i);
        intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, str);
        intent.putExtra("operation", 1);
        if (!l.c(str2)) {
            intent.setPackage(str2);
        }
        b.f().sendBroadcast(intent);
        a(i, str2);
    }
}
