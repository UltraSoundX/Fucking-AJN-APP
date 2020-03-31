package com.tencent.android.tpush.service.c;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.baidu.mobstat.Config;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.NotificationAction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.e;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.PushClickEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.rpc.a.C0062a;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.d;
import com.tencent.android.tpush.service.e.f;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    public static long a = 306000;
    private static a b = new a();
    private static final byte[] c = new byte[0];
    private static long d = 0;
    /* access modifiers changed from: private */
    public static volatile boolean e = false;
    /* access modifiers changed from: private */
    public static volatile boolean f = false;
    /* access modifiers changed from: private */
    public static volatile boolean g = false;
    /* access modifiers changed from: private */
    public static volatile boolean h = false;
    private static ConcurrentHashMap<Long, Map<String, List<Long>>> i = null;
    private PendingIntent j = null;

    /* renamed from: com.tencent.android.tpush.service.c.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private class C0066a implements Runnable {
        int a;
        Context b;

        public C0066a(Context context, int i) {
            this.b = context;
            this.a = i;
        }

        public void run() {
            switch (this.a) {
                case 1:
                    a.this.c(this.b, (MessageId) null);
                    return;
                case 2:
                    a.this.a(this.b, Long.valueOf(-1));
                    return;
                case 4:
                    a.this.b(this.b, (MessageId) null);
                    return;
                default:
                    com.tencent.android.tpush.b.a.i("SrvMessageManager", "unknown report type");
                    return;
            }
        }
    }

    private a() {
    }

    public static a a() {
        c();
        return b;
    }

    /* access modifiers changed from: private */
    public void a(final Context context, Long l) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList b2 = b(context);
        if (e) {
            com.tencent.android.tpush.b.a.c("SrvMessageManager", ">> msg ack is uploading , this time will give up! MessageId = " + l);
            return;
        }
        final ArrayList c2 = c(context, (List<MessageId>) b2);
        if (c2 == null || c2.size() <= 0) {
            com.tencent.android.tpush.b.a.c("SrvMessageManager", "Null report list with msgId " + l);
        } else {
            e = true;
            d = currentTimeMillis;
        }
        com.tencent.android.tpush.b.a.a(5, (List<TpnsPushClientReport>) c2);
        c.a().a(c2, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
            public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                a.e = false;
                if (i == 0) {
                    com.tencent.android.tpush.b.a.a(6, (List<TpnsPushClientReport>) c2);
                    ArrayList<TpnsPushClientReport> arrayList = ((TpnsPushVerifyReq) jceStruct).msgReportList;
                    com.tencent.android.tpush.b.a.a(7, (List<TpnsPushClientReport>) arrayList);
                    com.tencent.android.tpush.service.d.a.b(c2);
                    if (arrayList == null || arrayList.size() == 0) {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestAck ack failed with null tReq.msgReportList rsp = " + aVar.c());
                    }
                    a.this.d(b.f(), (List<TpnsPushClientReport>) arrayList);
                    com.tencent.android.tpush.common.c.a().a(2);
                    com.tencent.android.tpush.common.c.a().a(new C0066a(context, 2), 2, 3000);
                    return;
                }
                com.tencent.android.tpush.b.a.i("SrvMessageManager", ">> msg ack onMessageSendFailed  responseCode=" + i);
            }

            public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                a.e = false;
                com.tencent.android.tpush.b.a.a(8, (List<TpnsPushClientReport>) c2);
            }

            public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                a.e = false;
                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageDiscarded ");
            }
        });
    }

    private static String a(Context context, long j2) {
        String a2 = g.a(context, "tpush_channelId_" + j2, true);
        if (a2 != null && a2.length() > 5500) {
            a2 = a2.substring(0, Config.MAX_CACHE_JSON_CAPACIT_EXCEPTION);
        }
        return a2 != null ? a2 : "";
    }

    private static boolean a(Context context, long j2, Long l) {
        if (a(context, j2).contains(l + "$")) {
            return true;
        }
        return false;
    }

    private static void b(Context context, long j2, Long l) {
        if (j2 > 0 && l.longValue() > 0) {
            g.a(context, "tpush_channelId_" + j2, l + "$" + a(context, j2), true);
        }
    }

    public synchronized void a(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            try {
                long longExtra = intent.getLongExtra(MessageKey.MSG_ID, -1);
                String stringExtra = intent.getStringExtra(Constants.FLAG_PACK_NAME);
                MessageId messageId = (MessageId) intent.getSerializableExtra("MessageId");
                com.tencent.android.tpush.b.a.a(4, longExtra);
                if (messageId != null) {
                    com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "verify " + stringExtra + longExtra);
                    com.tencent.android.tpush.c.c.a().c(context, stringExtra, longExtra);
                    c(context, stringExtra, messageId);
                    a(context, stringExtra, longExtra, 1);
                    a(context, Long.valueOf(longExtra));
                }
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void b(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                MessageId messageId = new MessageId();
                messageId.id = intent.getLongExtra(MessageKey.MSG_ID, -1);
                if (messageId.id < 0) {
                    com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "@@ msgSendSDKAck: Not add LocalMsg");
                } else {
                    messageId.accessId = intent.getLongExtra("accId", -1);
                    messageId.host = intent.getLongExtra(MessageKey.MSG_EXTRA_HOST, -1);
                    messageId.port = intent.getIntExtra(MessageKey.MSG_EXTRA_PORT, -1);
                    messageId.pact = intent.getByteExtra(MessageKey.MSG_EXTRA_PACT, -1);
                    messageId.apn = DeviceInfos.getNetworkType(b.f());
                    messageId.isp = i.k(b.f());
                    messageId.pushTime = intent.getLongExtra(MessageKey.MSG_EXTRA_PUSHTIME, -1);
                    messageId.serviceHost = b.f().getPackageName();
                    messageId.receivedTime = currentTimeMillis;
                    messageId.pkgName = intent.getPackage();
                    messageId.busiMsgId = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1);
                    messageId.timestamp = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1);
                    messageId.msgType = intent.getLongExtra("type", -1);
                    messageId.multiPkg = intent.getLongExtra(MessageKey.MSG_CREATE_MULTIPKG, -1);
                    messageId.date = intent.getStringExtra(MessageKey.MSG_DATE);
                    messageId.channelId = intent.getLongExtra(MessageKey.MSG_CHANNEL_ID, -1);
                    String stringExtra = intent.getStringExtra(MessageKey.MSG_GROUP_KEYS);
                    if (!i.b(stringExtra)) {
                        messageId.groupKeys = stringExtra;
                    }
                    String stringExtra2 = intent.getStringExtra(MessageKey.MSG_STAT_TAG);
                    if (!i.b(stringExtra2)) {
                        messageId.statTag = stringExtra2;
                    }
                    b(context, "all", messageId);
                    b(context, messageId);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(final Context context, final MessageId messageId) {
        Long valueOf;
        ArrayList c2 = c(context, "all");
        if (g) {
            String str = "SrvMessageManager";
            StringBuilder append = new StringBuilder().append("requestSendSDKAck ack is uploading , this time will give up!  msgId =  ");
            if (messageId == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(messageId.id);
            }
            com.tencent.android.tpush.b.a.c(str, append.append(valueOf).toString());
        } else {
            ArrayList b2 = b(context, (List<MessageId>) c2);
            if (b2 == null || b2.size() == 0) {
                com.tencent.android.tpush.b.a.c("SrvMessageManager", "requestSendSDKAck with null list , give up this time");
            } else {
                com.tencent.android.tpush.b.a.c("SrvMessageManager", "requestSendSDKAck with list size = " + b2.size());
                g = true;
                c.a().a(b2, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                        Long l = null;
                        if (i == 0) {
                            try {
                                if (jceStruct instanceof TpnsPushVerifyReq) {
                                    TpnsPushVerifyReq tpnsPushVerifyReq = (TpnsPushVerifyReq) jceStruct;
                                    if (tpnsPushVerifyReq.msgReportList == null || tpnsPushVerifyReq.msgReportList.size() == 0) {
                                        String str = "SrvMessageManager";
                                        StringBuilder append = new StringBuilder().append("requestSendSDKAck ack failed with null tReq.msgReportList rsp = ").append(aVar.c()).append(" msgId ");
                                        if (messageId != null) {
                                            l = Long.valueOf(messageId.id);
                                        }
                                        com.tencent.android.tpush.b.a.i(str, append.append(l).toString());
                                    }
                                    Iterator it = tpnsPushVerifyReq.msgReportList.iterator();
                                    while (it.hasNext()) {
                                        com.tencent.android.tpush.b.a.c("SrvMessageManager", "requestSendSDKAck ack succeed with size = " + tpnsPushVerifyReq.msgReportList.size() + " msgid = " + ((TpnsPushClientReport) it.next()).msgId);
                                    }
                                    a.this.c(context, tpnsPushVerifyReq.msgReportList);
                                } else {
                                    com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestServiceAck -> Invalid ack callback");
                                }
                                com.tencent.android.tpush.common.c.a().a(1);
                                com.tencent.android.tpush.common.c.a().a(new C0066a(context, 1), 1, 3000);
                            } catch (Throwable th) {
                                com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestServiceAck -> Invalid ack callback");
                            } finally {
                                a.g = false;
                            }
                        } else {
                            a.g = false;
                            String str2 = "SrvMessageManager";
                            StringBuilder append2 = new StringBuilder().append(">> requestServiceAck ack onMessageSendFailed responseCode= ").append(i).append(" msgId = ");
                            if (messageId != null) {
                                l = Long.valueOf(messageId.id);
                            }
                            com.tencent.android.tpush.b.a.i(str2, append2.append(l).toString());
                        }
                    }

                    public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestServiceAck ack onMessageSendFailed  responseCode= " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                        a.g = false;
                    }

                    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                        Long valueOf;
                        String str = "SrvMessageManager";
                        StringBuilder append = new StringBuilder().append("requestServiceAck ack onMessageDiscarded msgId = ");
                        if (messageId == null) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(messageId.id);
                        }
                        com.tencent.android.tpush.b.a.i(str, append.append(valueOf).toString());
                        a.g = false;
                    }
                });
            }
        }
    }

    public synchronized void c(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            String stringExtra = intent.getStringExtra(Constants.FLAG_PACK_NAME);
            long longExtra = intent.getLongExtra(MessageKey.MSG_ID, -1);
            if (longExtra <= 0) {
                com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "@@ msgClick: Not add LocalMsg");
            } else {
                Context context2 = context;
                a(context2, stringExtra, new PushClickEntity(longExtra, intent.getLongExtra("accId", -1), intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1), intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1), stringExtra, (long) intent.getIntExtra(Constants.PUSH_CHANNEL, 1), intent.getLongExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000), intent.getIntExtra("action", NotificationAction.clicked.getType())));
                d(context, intent);
            }
        }
    }

    public void a(Context context, TpnsPushMsg tpnsPushMsg, long j2, com.tencent.android.tpush.service.channel.a aVar) {
        if (tpnsPushMsg.msgId <= 0) {
            com.tencent.android.tpush.b.a.a(Constants.ServiceLogTag, "@@ msgServiceAck: Not add LocalMsg");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MessageId messageId = new MessageId();
        messageId.id = tpnsPushMsg.msgId;
        messageId.accessId = tpnsPushMsg.accessId;
        messageId.host = i.c(aVar.d());
        messageId.port = aVar.e();
        messageId.pact = c.a(aVar.b());
        messageId.apn = DeviceInfos.getNetworkType(b.f());
        messageId.isp = i.k(b.f());
        messageId.pushTime = j2;
        messageId.serviceHost = b.f().getPackageName();
        messageId.receivedTime = currentTimeMillis;
        messageId.pkgName = tpnsPushMsg.appPkgName;
        messageId.busiMsgId = tpnsPushMsg.busiMsgId;
        messageId.timestamp = tpnsPushMsg.timestamp;
        messageId.msgType = tpnsPushMsg.type;
        messageId.multiPkg = tpnsPushMsg.multiPkg;
        messageId.date = tpnsPushMsg.date;
        messageId.channelId = tpnsPushMsg.channelId;
        String str = tpnsPushMsg.groupKey;
        if (!i.b(str)) {
            messageId.groupKeys = str;
        }
        String str2 = tpnsPushMsg.statTag;
        if (!i.b(str2)) {
            messageId.statTag = str2;
        }
        a(context, tpnsPushMsg.appPkgName, messageId);
        c(context, messageId);
    }

    /* access modifiers changed from: private */
    public synchronized void c(final Context context, final MessageId messageId) {
        Long valueOf;
        ArrayList a2 = a(context, messageId);
        if (f) {
            String str = "SrvMessageManager";
            StringBuilder append = new StringBuilder().append("requestServiceAck ack is uploading , this time will give up!  msgId =  ");
            if (messageId == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(messageId.id);
            }
            com.tencent.android.tpush.b.a.c(str, append.append(valueOf).toString());
        } else {
            ArrayList a3 = a(context, (List<MessageId>) a2);
            if (a3 == null || a3.size() == 0) {
                com.tencent.android.tpush.b.a.c("SrvMessageManager", "requestServiceAck with null list , give up this time");
            } else {
                f = true;
                c.a().a(a3, (com.tencent.android.tpush.service.channel.c.a) new com.tencent.android.tpush.service.channel.c.a() {
                    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                        Long l = null;
                        if (i == 0) {
                            try {
                                if (jceStruct instanceof TpnsPushVerifyReq) {
                                    TpnsPushVerifyReq tpnsPushVerifyReq = (TpnsPushVerifyReq) jceStruct;
                                    com.tencent.android.tpush.b.a.a(1, (List<TpnsPushClientReport>) tpnsPushVerifyReq.msgReportList);
                                    if (tpnsPushVerifyReq.msgReportList == null || tpnsPushVerifyReq.msgReportList.size() == 0) {
                                        String str = "SrvMessageManager";
                                        StringBuilder append = new StringBuilder().append("ServiceAcking ack failed with null tReq.msgReportList rsp = ").append(aVar.c()).append(" msgId ");
                                        if (messageId != null) {
                                            l = Long.valueOf(messageId.id);
                                        }
                                        com.tencent.android.tpush.b.a.i(str, append.append(l).toString());
                                    }
                                    a.this.a(context, tpnsPushVerifyReq.msgReportList);
                                } else {
                                    com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestServiceAck -> Invalid ack callback");
                                }
                                com.tencent.android.tpush.common.c.a().a(1);
                                com.tencent.android.tpush.common.c.a().a(new C0066a(context, 1), 1, 3000);
                            } catch (Throwable th) {
                                com.tencent.android.tpush.b.a.i("SrvMessageManager", "requestServiceAck -> Invalid ack callback");
                            } finally {
                                a.f = false;
                            }
                        } else {
                            a.f = false;
                            String str2 = "SrvMessageManager";
                            StringBuilder append2 = new StringBuilder().append(">> ServiceAcking ack onMessageSendFailed responseCode= ").append(i).append(" msgId = ");
                            if (messageId != null) {
                                l = Long.valueOf(messageId.id);
                            }
                            com.tencent.android.tpush.b.a.i(str2, append2.append(l).toString());
                        }
                    }

                    public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", "ServiceAcking ack onMessageSendFailed  responseCode= " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                        a.f = false;
                    }

                    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                        Long valueOf;
                        String str = "SrvMessageManager";
                        StringBuilder append = new StringBuilder().append("ServiceAcking ack onMessageDiscarded msgId = ");
                        if (messageId == null) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(messageId.id);
                        }
                        com.tencent.android.tpush.b.a.i(str, append.append(valueOf).toString());
                        a.f = false;
                    }
                });
            }
        }
    }

    public void a(Context context, ArrayList<TpnsPushClientReport> arrayList) {
        ArrayList arrayList2;
        boolean z;
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                if (arrayList.size() > 0) {
                    try {
                        ArrayList b2 = b(context, "all");
                        if (b2 != null && b2.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = b2.iterator();
                            while (it.hasNext()) {
                                MessageId messageId = (MessageId) it.next();
                                ArrayList arrayList3 = (ArrayList) hashMap.get(messageId.pkgName);
                                if (arrayList3 == null) {
                                    ArrayList arrayList4 = new ArrayList();
                                    hashMap.put(messageId.pkgName, arrayList4);
                                    arrayList2 = arrayList4;
                                } else {
                                    arrayList2 = arrayList3;
                                }
                                int i2 = 0;
                                while (true) {
                                    int i3 = i2;
                                    if (i3 >= arrayList.size()) {
                                        z = true;
                                        break;
                                    }
                                    if (messageId.id == ((TpnsPushClientReport) arrayList.get(i3)).msgId) {
                                        arrayList.remove(i3);
                                        z = false;
                                        break;
                                    }
                                    i2 = i3 + 1;
                                }
                                if (z) {
                                    arrayList2.add(messageId);
                                    hashMap.put(messageId.pkgName, arrayList2);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                b(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d("SrvMessageManager", "+++ clear msg id exception", e2);
                    }
                }
            }
            com.tencent.android.tpush.b.a.i("SrvMessageManager", "deleteServiceMsgIdBatch with null context or null list");
        }
    }

    public ArrayList<TpnsPushClientReport> a(Context context, List<MessageId> list) {
        ArrayList<TpnsPushClientReport> arrayList = null;
        if (list != null && list.size() > 0) {
            ArrayList<TpnsPushClientReport> arrayList2 = new ArrayList<>();
            for (MessageId messageId : list) {
                TpnsPushClientReport tpnsPushClientReport = new TpnsPushClientReport();
                tpnsPushClientReport.accessId = messageId.accessId;
                tpnsPushClientReport.msgId = messageId.id;
                tpnsPushClientReport.apn = messageId.apn;
                tpnsPushClientReport.isp = messageId.isp;
                tpnsPushClientReport.locip = messageId.host;
                tpnsPushClientReport.locport = messageId.port;
                tpnsPushClientReport.pack = messageId.pact;
                tpnsPushClientReport.timeUs = messageId.pushTime;
                tpnsPushClientReport.qua = CacheManager.getQua(context, tpnsPushClientReport.accessId);
                tpnsPushClientReport.serviceHost = messageId.serviceHost;
                tpnsPushClientReport.confirmMs = System.currentTimeMillis() - messageId.receivedTime;
                tpnsPushClientReport.broadcastId = messageId.busiMsgId;
                tpnsPushClientReport.timestamp = messageId.timestamp;
                tpnsPushClientReport.type = messageId.msgType;
                tpnsPushClientReport.ackType = 1;
                tpnsPushClientReport.receiveTime = messageId.receivedTime / 1000;
                tpnsPushClientReport.channelId = messageId.channelId;
                tpnsPushClientReport.groupKey = messageId.groupKeys;
                tpnsPushClientReport.statTag = messageId.statTag;
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c("SrvMessageManager", "Ack to server : @msgId=" + tpnsPushClientReport.msgId + " @accId=" + tpnsPushClientReport.accessId + " @timeUs=" + tpnsPushClientReport.timeUs + " @confirmMs=" + tpnsPushClientReport.confirmMs + " @recTime=" + messageId.receivedTime + " @msgType=" + messageId.msgType + " @broadcastId=" + tpnsPushClientReport.broadcastId);
                }
                arrayList2.add(tpnsPushClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public ArrayList<TpnsPushClientReport> b(Context context, List<MessageId> list) {
        ArrayList<TpnsPushClientReport> arrayList = null;
        if (list != null && list.size() > 0) {
            ArrayList<TpnsPushClientReport> arrayList2 = new ArrayList<>();
            for (MessageId messageId : list) {
                TpnsPushClientReport tpnsPushClientReport = new TpnsPushClientReport();
                tpnsPushClientReport.accessId = messageId.accessId;
                tpnsPushClientReport.msgId = messageId.id;
                tpnsPushClientReport.apn = messageId.apn;
                tpnsPushClientReport.isp = messageId.isp;
                tpnsPushClientReport.locip = messageId.host;
                tpnsPushClientReport.locport = messageId.port;
                tpnsPushClientReport.pack = messageId.pact;
                tpnsPushClientReport.timeUs = messageId.pushTime;
                tpnsPushClientReport.qua = CacheManager.getQua(context, tpnsPushClientReport.accessId);
                tpnsPushClientReport.serviceHost = messageId.serviceHost;
                tpnsPushClientReport.confirmMs = System.currentTimeMillis() - messageId.receivedTime;
                tpnsPushClientReport.broadcastId = messageId.busiMsgId;
                tpnsPushClientReport.timestamp = messageId.timestamp;
                tpnsPushClientReport.type = messageId.msgType;
                tpnsPushClientReport.ackType = 4;
                tpnsPushClientReport.receiveTime = messageId.receivedTime / 1000;
                tpnsPushClientReport.channelId = messageId.channelId;
                tpnsPushClientReport.groupKey = messageId.groupKeys;
                tpnsPushClientReport.statTag = messageId.statTag;
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c("SrvMessageManager", "Send sdk Ack to server : @msgId=" + tpnsPushClientReport.msgId + " @accId=" + tpnsPushClientReport.accessId + " @timeUs=" + tpnsPushClientReport.timeUs + " @confirmMs=" + tpnsPushClientReport.confirmMs + " @recTime=" + messageId.receivedTime + " @msgType=" + messageId.msgType + " @broadcastId=" + tpnsPushClientReport.broadcastId);
                }
                arrayList2.add(tpnsPushClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public ArrayList<MessageId> a(Context context, MessageId messageId) {
        ArrayList<MessageId> arrayList;
        synchronized (c) {
            arrayList = null;
            if (context != null) {
                boolean z = false;
                List<String> registerInfos = CacheManager.getRegisterInfos(context);
                if (registerInfos != null && registerInfos.size() > 0) {
                    ArrayList<MessageId> arrayList2 = new ArrayList<>();
                    for (String str : registerInfos) {
                        ArrayList b2 = b(context, str);
                        if (messageId == null || str.equals(messageId.pkgName)) {
                            z = true;
                        }
                        if (b2 != null && b2.size() > 0) {
                            arrayList2.addAll(b2);
                        }
                    }
                    arrayList = arrayList2;
                }
                if (!z) {
                    try {
                        ArrayList b3 = b(context, messageId.pkgName);
                        if (b3 != null && b3.size() > 0) {
                            arrayList.retainAll(b3);
                            if (arrayList.size() > 0) {
                                arrayList.removeAll(arrayList);
                                arrayList.addAll(b3);
                            } else {
                                arrayList.addAll(b3);
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
                b(context, "all", arrayList);
            }
        }
        return arrayList;
    }

    public void a(Context context, String str, MessageId messageId) {
        synchronized (c) {
            if (context != null) {
                if (!i.b(str) && messageId != null) {
                    ArrayList b2 = b(context, str);
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < b2.size(); i2++) {
                        MessageId messageId2 = (MessageId) b2.get(i2);
                        if (messageId2.id == messageId.id) {
                            arrayList.add(messageId2);
                        }
                    }
                    b2.removeAll(arrayList);
                    b2.add(messageId);
                    b(context, str, b2);
                }
            }
        }
    }

    public void b(Context context, String str, MessageId messageId) {
        synchronized (c) {
            if (context != null) {
                if (!i.b(str) && messageId != null) {
                    ArrayList c2 = c(context, str);
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < c2.size(); i2++) {
                        MessageId messageId2 = (MessageId) c2.get(i2);
                        if (messageId2.id == messageId.id) {
                            arrayList.add(messageId2);
                        }
                    }
                    c2.removeAll(arrayList);
                    c2.add(messageId);
                    a(context, str, c2);
                }
            }
        }
    }

    public void a(Context context, String str, ArrayList<MessageId> arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.send.sdk", arrayList);
            }
        }
    }

    public void b(Context context, String str, ArrayList<MessageId> arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.service", arrayList);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.tencent.android.tpush.data.MessageId> b(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x001b
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ".tpns.msg.id.service"
            java.lang.Object r0 = r2.a(r3, r4, r0)
            if (r0 == 0) goto L_0x001b
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001a:
            return r0
        L_0x001b:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.c.a.b(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.tencent.android.tpush.data.MessageId> c(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x001b
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ".tpns.msg.id.send.sdk"
            java.lang.Object r0 = r2.a(r3, r4, r0)
            if (r0 == 0) goto L_0x001b
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001a:
            return r0
        L_0x001b:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.c.a.c(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public void b(Context context, ArrayList<TpnsClickClientReport> arrayList) {
        ArrayList arrayList2;
        boolean z;
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                if (arrayList.size() > 0) {
                    try {
                        ArrayList c2 = c(context);
                        if (c2 != null && c2.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = c2.iterator();
                            while (it.hasNext()) {
                                PushClickEntity pushClickEntity = (PushClickEntity) it.next();
                                ArrayList arrayList3 = (ArrayList) hashMap.get(pushClickEntity.pkgName);
                                if (arrayList3 == null) {
                                    ArrayList arrayList4 = new ArrayList();
                                    hashMap.put(pushClickEntity.pkgName, arrayList4);
                                    arrayList2 = arrayList4;
                                } else {
                                    arrayList2 = arrayList3;
                                }
                                int i2 = 0;
                                while (true) {
                                    int i3 = i2;
                                    if (i3 >= arrayList.size()) {
                                        z = true;
                                        break;
                                    }
                                    if (pushClickEntity.msgId == ((TpnsClickClientReport) arrayList.get(i3)).msgId) {
                                        arrayList.remove(i3);
                                        z = false;
                                        break;
                                    }
                                    i2 = i3 + 1;
                                }
                                if (z) {
                                    arrayList2.add(pushClickEntity);
                                    hashMap.put(pushClickEntity.pkgName, arrayList2);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                c(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d("SrvMessageManager", "+++ clear msg id exception", e2);
                    }
                }
            }
        }
    }

    public ArrayList<TpnsClickClientReport> a(Context context) {
        ArrayList<TpnsClickClientReport> arrayList = null;
        ArrayList<PushClickEntity> c2 = c(context);
        if (c2 != null && c2.size() > 0) {
            ArrayList<TpnsClickClientReport> arrayList2 = new ArrayList<>();
            for (PushClickEntity pushClickEntity : c2) {
                TpnsClickClientReport tpnsClickClientReport = new TpnsClickClientReport();
                tpnsClickClientReport.accessId = pushClickEntity.accessId;
                tpnsClickClientReport.msgId = pushClickEntity.msgId;
                tpnsClickClientReport.broadcastId = pushClickEntity.broadcastId;
                tpnsClickClientReport.timestamp = pushClickEntity.timestamp;
                tpnsClickClientReport.type = pushClickEntity.type;
                tpnsClickClientReport.clickTime = pushClickEntity.clickTime;
                tpnsClickClientReport.action = (long) pushClickEntity.action;
                arrayList2.add(tpnsClickClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public void d(final Context context, final Intent intent) {
        if (!h) {
            final ArrayList a2 = a(context);
            if (a2 == null || a2.size() <= 0) {
                h = false;
                return;
            }
            h = true;
            c.a().b(a2, new com.tencent.android.tpush.service.channel.c.a() {
                public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.service.d.a.c(a2);
                    if (i == 0) {
                        a.this.b(b.f(), a2);
                        com.tencent.android.tpush.common.c.a().a(new Runnable() {
                            public void run() {
                                a.this.d(context, intent);
                            }
                        }, OkHttpUtils.DEFAULT_MILLISECONDS);
                    } else {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", ">> msg ckicled ack failed responseCode=" + i);
                    }
                    a.h = false;
                }

                public void a(JceStruct jceStruct, ChannelException channelException, com.tencent.android.tpush.service.channel.a aVar) {
                    com.tencent.android.tpush.b.a.i("SrvMessageManager", "### msg ack onMessageSendFailed  responseCode=" + channelException.errorCode);
                    a.h = false;
                }

                public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.a aVar) {
                    a.h = false;
                }
            });
        }
    }

    public ArrayList<MessageId> b(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String e2 : registerInfos) {
            ArrayList e3 = e(context, e2);
            if (e3 != null && e3.size() > 0) {
                arrayList.addAll(e3);
            }
        }
        return arrayList;
    }

    public ArrayList<PushClickEntity> c(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String d2 : registerInfos) {
            ArrayList d3 = d(context, d2);
            if (d3 != null && d3.size() > 0) {
                arrayList.addAll(d3);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.tencent.android.tpush.data.PushClickEntity> d(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x001b
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ".tpns.msg.id.clicked"
            java.lang.Object r0 = r2.a(r3, r4, r0)
            if (r0 == 0) goto L_0x001b
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001a:
            return r0
        L_0x001b:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.c.a.d(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public void a(Context context, String str, PushClickEntity pushClickEntity) {
        synchronized (c) {
            if (context != null) {
                if (!i.b(str) && pushClickEntity != null) {
                    ArrayList d2 = d(context, str);
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < d2.size(); i2++) {
                        PushClickEntity pushClickEntity2 = (PushClickEntity) d2.get(i2);
                        if (pushClickEntity2.msgId == pushClickEntity.msgId) {
                            arrayList.add(pushClickEntity2);
                        }
                    }
                    d2.removeAll(arrayList);
                    d2.add(pushClickEntity);
                    c(context, str, d2);
                }
            }
        }
    }

    public void c(Context context, String str, ArrayList<PushClickEntity> arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.clicked", arrayList);
            }
        }
    }

    public ArrayList<TpnsPushClientReport> c(Context context, List<MessageId> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            ArrayList<TpnsPushClientReport> arrayList2 = new ArrayList<>();
            for (MessageId messageId : list) {
                TpnsPushClientReport tpnsPushClientReport = new TpnsPushClientReport();
                tpnsPushClientReport.accessId = messageId.accessId;
                tpnsPushClientReport.msgId = messageId.id;
                tpnsPushClientReport.apn = messageId.apn;
                tpnsPushClientReport.isp = messageId.isp;
                tpnsPushClientReport.locip = messageId.host;
                tpnsPushClientReport.locport = messageId.port;
                tpnsPushClientReport.pack = messageId.pact;
                tpnsPushClientReport.timeUs = messageId.pushTime;
                tpnsPushClientReport.qua = CacheManager.getQua(context, tpnsPushClientReport.accessId);
                tpnsPushClientReport.serviceHost = messageId.serviceHost;
                tpnsPushClientReport.confirmMs = System.currentTimeMillis() - messageId.receivedTime;
                tpnsPushClientReport.broadcastId = messageId.busiMsgId;
                tpnsPushClientReport.timestamp = messageId.timestamp;
                tpnsPushClientReport.type = messageId.msgType;
                tpnsPushClientReport.receiveTime = messageId.receivedTime / 1000;
                tpnsPushClientReport.channelId = messageId.channelId;
                tpnsPushClientReport.statTag = messageId.statTag;
                tpnsPushClientReport.groupKey = messageId.groupKeys;
                arrayList2.add(tpnsPushClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private ArrayList<MessageId> e(Context context, String str) {
        ArrayList<MessageId> arrayList;
        synchronized (c) {
            arrayList = null;
            if (context != null) {
                if (!i.b(str)) {
                    ArrayList a2 = a(context, str);
                    if (a2 != null && a2.size() > 0) {
                        ArrayList<MessageId> arrayList2 = new ArrayList<>();
                        Iterator it = a2.iterator();
                        while (it.hasNext()) {
                            MessageId messageId = (MessageId) it.next();
                            if (messageId.isMsgAcked()) {
                                arrayList2.add(messageId);
                            }
                        }
                        arrayList = arrayList2;
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.tencent.android.tpush.data.MessageId> a(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            r1 = 0
            if (r3 == 0) goto L_0x001b
            boolean r0 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ".tpns.msg.id"
            java.lang.Object r0 = r2.a(r3, r4, r0)
            if (r0 == 0) goto L_0x001b
            java.util.ArrayList r0 = (java.util.ArrayList) r0
        L_0x0013:
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001a:
            return r0
        L_0x001b:
            r0 = r1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.c.a.a(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public void d(Context context, String str, ArrayList<MessageId> arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id", arrayList);
            }
        }
    }

    public void a(Context context, String str, long j2, short s) {
        boolean z;
        synchronized (c) {
            boolean z2 = false;
            if (context != null && j2 > 0) {
                ArrayList a2 = a(context, str);
                if (a2 == null || a2.size() <= 0) {
                    com.tencent.android.tpush.b.a.a(12, j2);
                } else {
                    Iterator it = a2.iterator();
                    while (it.hasNext()) {
                        MessageId messageId = (MessageId) it.next();
                        if (messageId.id == j2) {
                            messageId.isAck = s;
                            z = true;
                        } else {
                            z = z2;
                        }
                        z2 = z;
                    }
                    if (z2) {
                        d(context, str, a2);
                    } else {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", "updateMsgIdFlag Failed with no equal MessageId = " + j2 + " pkgName = " + str);
                        com.tencent.android.tpush.b.a.a(11, j2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(Context context, ArrayList<TpnsPushClientReport> arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                if (arrayList.size() > 0) {
                    try {
                        ArrayList c2 = c(context, "all");
                        ArrayList arrayList2 = new ArrayList();
                        if (c2 != null && c2.size() > 0) {
                            Iterator it = c2.iterator();
                            while (it.hasNext()) {
                                MessageId messageId = (MessageId) it.next();
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    if (messageId.id == ((TpnsPushClientReport) arrayList.get(i2)).msgId) {
                                        arrayList2.add(messageId);
                                    }
                                }
                            }
                        }
                        c2.removeAll(arrayList2);
                        a(context, "all", c2);
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d("SrvMessageManager", "deleteMsgIdBatch", e2);
                    }
                }
            }
        }
    }

    public void d(Context context, List<TpnsPushClientReport> list) {
        ArrayList arrayList;
        boolean z;
        synchronized (c) {
            if (!(context == null || list == null)) {
                if (list.size() > 0) {
                    try {
                        ArrayList b2 = b(context);
                        if (b2 != null && b2.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = b2.iterator();
                            while (it.hasNext()) {
                                MessageId messageId = (MessageId) it.next();
                                ArrayList arrayList2 = (ArrayList) hashMap.get(messageId.pkgName);
                                if (arrayList2 == null) {
                                    ArrayList arrayList3 = new ArrayList();
                                    hashMap.put(messageId.pkgName, arrayList3);
                                    arrayList = arrayList3;
                                } else {
                                    arrayList = arrayList2;
                                }
                                int i2 = 0;
                                while (true) {
                                    int i3 = i2;
                                    if (i3 >= list.size()) {
                                        z = true;
                                        break;
                                    }
                                    if (messageId.id == ((TpnsPushClientReport) list.get(i3)).msgId) {
                                        z = false;
                                        break;
                                    }
                                    i2 = i3 + 1;
                                }
                                if (z) {
                                    arrayList.add(messageId);
                                    hashMap.put(messageId.pkgName, arrayList);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                d(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d("SrvMessageManager", "deleteMsgIdBatch", e2);
                    }
                }
            }
        }
    }

    public void c(Context context, String str, MessageId messageId) {
        ArrayList arrayList;
        synchronized (c) {
            if (context != null) {
                if (!i.b(str) && messageId != null) {
                    ArrayList a2 = a(context, str);
                    if (a2 != null) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= a2.size()) {
                                arrayList = a2;
                                break;
                            } else if (((MessageId) a2.get(i2)).id == messageId.id) {
                                a2.remove(i2);
                                arrayList = a2;
                                break;
                            } else {
                                i2++;
                            }
                        }
                    } else {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(messageId);
                    d(context, str, arrayList);
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v36, types: [java.lang.String[], java.io.Serializable] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v36, types: [java.lang.String[], java.io.Serializable]
  assigns: [java.lang.String[]]
  uses: [?[OBJECT, ARRAY][], java.io.Serializable]
  mth insns count: 374
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg r19, long r20, com.tencent.android.tpush.service.channel.a r22) {
        /*
            r18 = this;
            android.content.Intent r5 = new android.content.Intent
            java.lang.String r2 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE"
            r5.<init>(r2)
            r0 = r19
            java.lang.String r2 = r0.appPkgName
            r5.setPackage(r2)
            java.lang.String r2 = "msgId"
            r0 = r19
            long r6 = r0.msgId
            r5.putExtra(r2, r6)
            java.lang.String r2 = "title"
            r0 = r19
            java.lang.String r3 = r0.title
            java.lang.String r3 = com.tencent.android.tpush.encrypt.Rijndael.encrypt(r3)
            r5.putExtra(r2, r3)
            java.lang.String r2 = "content"
            r0 = r19
            java.lang.String r3 = r0.content
            java.lang.String r3 = com.tencent.android.tpush.encrypt.Rijndael.encrypt(r3)
            r5.putExtra(r2, r3)
            java.lang.String r2 = "date"
            r0 = r19
            java.lang.String r3 = r0.date
            r5.putExtra(r2, r3)
            java.lang.String r2 = "type"
            r0 = r19
            long r6 = r0.type
            r5.putExtra(r2, r6)
            java.lang.String r2 = "accId"
            r0 = r19
            long r6 = r0.accessId
            r5.putExtra(r2, r6)
            java.lang.String r2 = "busiMsgId"
            r0 = r19
            long r6 = r0.busiMsgId
            r5.putExtra(r2, r6)
            java.lang.String r2 = "timestamps"
            r0 = r19
            long r6 = r0.timestamp
            r5.putExtra(r2, r6)
            java.lang.String r2 = "multiPkg"
            r0 = r19
            long r6 = r0.multiPkg
            r5.putExtra(r2, r6)
            java.lang.String r2 = "server_time"
            r0 = r19
            long r6 = r0.serverTime
            r5.putExtra(r2, r6)
            java.lang.String r2 = "ttl"
            r0 = r19
            int r3 = r0.ttl
            r5.putExtra(r2, r3)
            r0 = r19
            long r6 = r0.channelId
            java.lang.String r2 = "svrAck"
            r3 = 1
            r5.putExtra(r2, r3)
            java.lang.String r2 = "svrPkgName"
            java.lang.String r3 = com.tencent.android.tpush.service.b.g()
            r5.putExtra(r2, r3)
            r0 = r19
            int r4 = r0.ttl
            r0 = r19
            long r8 = r0.serverTime
            r0 = r19
            long r10 = r0.timestamp
            long r12 = java.lang.System.currentTimeMillis()
            r2 = 259200000(0xf731400, double:1.280618154E-315)
            if (r4 <= 0) goto L_0x0153
            long r2 = (long) r4
            r14 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r14
        L_0x00a5:
            r14 = 0
            int r4 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x0163
            r14 = 0
            int r4 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x0163
            long r10 = r8 - r10
            r14 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 * r14
            long r10 = r10 + r12
            long r2 = r2 + r10
        L_0x00b8:
            java.lang.String r4 = "time_gap"
            r10 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 * r10
            long r8 = r12 - r8
            r5.putExtra(r4, r8)
            java.lang.String r4 = "expire_time"
            r5.putExtra(r4, r2)
            java.lang.String r2 = "enKeySet"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0166 }
            r4 = 0
            java.lang.String r8 = "title"
            r3[r4] = r8     // Catch:{ Exception -> 0x0166 }
            r4 = 1
            java.lang.String r8 = "content"
            r3[r4] = r8     // Catch:{ Exception -> 0x0166 }
            java.lang.String r3 = com.tencent.android.tpush.common.e.a(r3)     // Catch:{ Exception -> 0x0166 }
            r5.putExtra(r2, r3)     // Catch:{ Exception -> 0x0166 }
        L_0x00dd:
            java.lang.String r2 = "extra_host"
            java.lang.String r3 = r22.d()
            long r8 = com.tencent.android.tpush.service.e.i.c(r3)
            r5.putExtra(r2, r8)
            java.lang.String r2 = "extra_port"
            int r3 = r22.e()
            r5.putExtra(r2, r3)
            java.lang.String r2 = "extra_pact"
            boolean r3 = r22.b()
            byte r3 = com.tencent.android.tpush.service.c.a(r3)
            r5.putExtra(r2, r3)
            java.lang.String r2 = "extra_push_time"
            r0 = r20
            r5.putExtra(r2, r0)
            r0 = r19
            long r8 = r0.multiPkg
            r0 = r19
            long r10 = r0.accessId
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r12 = 0
            int r2 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x02d2
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            r0 = r19
            long r8 = r0.msgId
            java.lang.Long r4 = java.lang.Long.valueOf(r8)
            boolean r2 = a(r2, r6, r4)
            if (r2 == 0) goto L_0x0170
            java.lang.String r2 = "SrvMessageManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "give up sended msg id "
            java.lang.StringBuilder r3 = r3.append(r4)
            r0 = r19
            long r4 = r0.msgId
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = "  channel "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.android.tpush.b.a.i(r2, r3)
        L_0x0152:
            return
        L_0x0153:
            r0 = r19
            long r14 = r0.msgId
            r16 = 0
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 <= 0) goto L_0x00a5
            if (r4 != 0) goto L_0x00a5
            r2 = 30000(0x7530, double:1.4822E-319)
            goto L_0x00a5
        L_0x0163:
            long r2 = r2 + r12
            goto L_0x00b8
        L_0x0166:
            r2 = move-exception
            java.lang.String r3 = "SrvMessageManager"
            java.lang.String r4 = "distribute2SDK"
            com.tencent.android.tpush.b.a.d(r3, r4, r2)
            goto L_0x00dd
        L_0x0170:
            java.lang.String r2 = "channel_id"
            r0 = r19
            long r8 = r0.channelId
            r5.putExtra(r2, r8)
            java.lang.String r2 = "group_keys"
            r0 = r19
            java.lang.String r4 = r0.groupKey
            r5.putExtra(r2, r4)
            r0 = r19
            java.lang.String r2 = r0.groupKey
            boolean r2 = com.tencent.android.tpush.common.l.c(r2)
            if (r2 == 0) goto L_0x01ed
            java.lang.String r2 = "SrvMessageManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "push msg by channelId "
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.e(r2, r4)
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            boolean r2 = com.tencent.android.tpush.service.e.i.a(r2, r4)
            if (r2 == 0) goto L_0x01cf
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            java.lang.String r2 = r2.getPackageName()
            r3.add(r2)
        L_0x01bd:
            if (r3 == 0) goto L_0x01c5
            boolean r2 = r3.isEmpty()
            if (r2 == 0) goto L_0x02df
        L_0x01c5:
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            r0 = r18
            r0.b(r2, r5)
            goto L_0x0152
        L_0x01cf:
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            java.util.List r3 = com.tencent.android.tpush.service.e.i.b(r2, r3)
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            java.util.List r2 = com.tencent.android.tpush.service.e.i.a(r2, r3)
            if (r2 == 0) goto L_0x0434
            int r4 = r2.size()
            if (r4 <= 0) goto L_0x0434
        L_0x01eb:
            r3 = r2
            goto L_0x01bd
        L_0x01ed:
            java.lang.String r2 = "SrvMessageManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "push msg by groupkey "
            java.lang.StringBuilder r4 = r4.append(r8)
            r0 = r19
            java.lang.String r8 = r0.groupKey
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r8 = "  channelId "
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.e(r2, r4)
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, java.util.Map<java.lang.String, java.util.List<java.lang.Long>>> r2 = i
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            java.lang.Object r2 = r2.get(r4)
            java.util.Map r2 = (java.util.Map) r2
            if (r2 == 0) goto L_0x02c9
            boolean r4 = r2.isEmpty()
            if (r4 != 0) goto L_0x02c9
            r0 = r19
            java.lang.String r4 = r0.groupKey
            java.lang.Object r2 = r2.get(r4)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r4 = "SrvMessageManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "push msg by groupkey accessids "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.tencent.android.tpush.b.a.e(r4, r8)
            android.content.Context r4 = com.tencent.android.tpush.service.b.f()
            java.util.List r4 = com.tencent.android.tpush.service.e.i.b(r4, r2)
            java.lang.String r2 = "SrvMessageManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "push msg by groupkey pkgsGroups "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r4)
            java.lang.String r8 = r8.toString()
            com.tencent.android.tpush.b.a.e(r2, r8)
            if (r4 == 0) goto L_0x0431
            boolean r2 = r4.isEmpty()
            if (r2 != 0) goto L_0x0431
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            java.util.List r2 = com.tencent.android.tpush.service.e.i.b(r2, r3)
            java.lang.String r3 = "SrvMessageManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "push msg by groupkey channelPakgs "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.tencent.android.tpush.b.a.e(r3, r8)
            r2.retainAll(r4)
            android.content.Context r3 = com.tencent.android.tpush.service.b.f()
            java.lang.String r3 = r3.getPackageName()
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x02b7
            r2.clear()
            android.content.Context r3 = com.tencent.android.tpush.service.b.f()
            java.lang.String r3 = r3.getPackageName()
            r2.add(r3)
        L_0x02b4:
            r3 = r2
            goto L_0x01bd
        L_0x02b7:
            android.content.Context r3 = com.tencent.android.tpush.service.b.f()
            java.util.List r3 = com.tencent.android.tpush.service.e.i.a(r3, r2)
            if (r3 == 0) goto L_0x02b4
            int r4 = r3.size()
            if (r4 <= 0) goto L_0x02b4
            r2 = r3
            goto L_0x02b4
        L_0x02c9:
            java.lang.String r2 = "SrvMessageManager"
            java.lang.String r4 = "the groupkey is null"
            com.tencent.android.tpush.b.a.e(r2, r4)
            goto L_0x01bd
        L_0x02d2:
            r12 = 0
            int r2 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x034e
            java.lang.String r2 = r5.getPackage()
            r3.add(r2)
        L_0x02df:
            java.lang.String r2 = "SrvMessageManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "distribute2SDK pkgs "
            java.lang.StringBuilder r4 = r4.append(r8)
            int r8 = r3.size()
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r8 = " pkgs "
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.StringBuilder r4 = r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.e(r2, r4)
            r2 = 0
            r4 = r2
        L_0x0307:
            int r2 = r3.size()
            if (r4 >= r2) goto L_0x0152
            java.lang.Object r2 = r3.get(r4)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r8 = "SrvMessageManager"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a2 }
            r9.<init>()     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r12 = "distribute2SDK pkgs msgid "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            r0 = r19
            long r12 = r0.msgId     // Catch:{ Exception -> 0x03a2 }
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r12 = "  pkg "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            r0 = r19
            java.lang.String r12 = r0.appPkgName     // Catch:{ Exception -> 0x03a2 }
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.b.a.e(r8, r9)     // Catch:{ Exception -> 0x03a2 }
            boolean r8 = com.tencent.android.tpush.service.e.i.b(r2)     // Catch:{ Exception -> 0x03a2 }
            if (r8 == 0) goto L_0x035c
            java.lang.String r2 = "SrvMessageManager"
            java.lang.String r8 = ">> msg.appPkgName is null!"
            com.tencent.android.tpush.b.a.c(r2, r8)     // Catch:{ Exception -> 0x03a2 }
        L_0x034a:
            int r2 = r4 + 1
            r4 = r2
            goto L_0x0307
        L_0x034e:
            java.lang.String r2 = com.tencent.android.tpush.service.cache.CacheManager.findValidPackageByAccessid(r10)
            boolean r4 = com.tencent.android.tpush.service.e.i.b(r2)
            if (r4 != 0) goto L_0x02df
            r3.add(r2)
            goto L_0x02df
        L_0x035c:
            android.content.Context r8 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.a.a r8 = com.tencent.android.tpush.a.a.a(r8)     // Catch:{ Exception -> 0x03a2 }
            r8.a(r2)     // Catch:{ Exception -> 0x03a2 }
            android.content.Context r8 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            boolean r8 = com.tencent.android.tpush.service.e.i.a(r8, r2, r10)     // Catch:{ Exception -> 0x03a2 }
            if (r8 != 0) goto L_0x03ab
            java.lang.String r8 = "SrvMessageManager"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a2 }
            r9.<init>()     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r12 = "dispatchMessageOnTime appPkgName "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            java.lang.StringBuilder r9 = r9.append(r2)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r12 = " is not installed."
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.b.a.e(r8, r9)     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.service.c r8 = com.tencent.android.tpush.service.c.a()     // Catch:{ Exception -> 0x03a2 }
            r8.a(r2)     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.c.c r8 = com.tencent.android.tpush.c.c.a()     // Catch:{ Exception -> 0x03a2 }
            android.content.Context r9 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            r8.d(r9, r2)     // Catch:{ Exception -> 0x03a2 }
            goto L_0x034a
        L_0x03a2:
            r2 = move-exception
            java.lang.String r8 = "SrvMessageManager"
            java.lang.String r9 = "dispatchMessageOnTime"
            com.tencent.android.tpush.b.a.d(r8, r9, r2)
            goto L_0x034a
        L_0x03ab:
            com.tencent.android.tpush.data.RegisterEntity r8 = com.tencent.android.tpush.service.cache.CacheManager.getRegisterInfoByPkgName(r2)     // Catch:{ Exception -> 0x03a2 }
            if (r8 != 0) goto L_0x03d7
            java.lang.String r9 = "SrvMessageManager"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x03a2 }
            r12.<init>()     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r13 = "RegInfo is null "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ Exception -> 0x03a2 }
            java.lang.StringBuilder r12 = r12.append(r2)     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x03a2 }
            com.tencent.android.tpush.b.a.a(r9, r12)     // Catch:{ Exception -> 0x03a2 }
            r12 = 0
            int r9 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r9 <= 0) goto L_0x03e8
            int r9 = r3.size()     // Catch:{ Exception -> 0x03a2 }
            int r9 = r9 + -1
            if (r4 != r9) goto L_0x034a
        L_0x03d7:
            int r8 = r8.state     // Catch:{ Exception -> 0x03a2 }
            if (r8 <= 0) goto L_0x03f3
            com.tencent.android.tpush.c.c r8 = com.tencent.android.tpush.c.c.a()     // Catch:{ Exception -> 0x03a2 }
            android.content.Context r9 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            r8.d(r9, r2)     // Catch:{ Exception -> 0x03a2 }
            goto L_0x034a
        L_0x03e8:
            r0 = r19
            java.lang.String r8 = r0.date     // Catch:{ Exception -> 0x03a2 }
            r0 = r18
            r0.a(r8, r5, r2)     // Catch:{ Exception -> 0x03a2 }
            goto L_0x034a
        L_0x03f3:
            r5.setPackage(r2)     // Catch:{ Exception -> 0x03a2 }
            r18.e()     // Catch:{ Exception -> 0x03a2 }
            android.content.Context r8 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            java.lang.String r9 = r5.getPackage()     // Catch:{ Exception -> 0x03a2 }
            boolean r8 = com.tencent.android.tpush.a.a(r8, r9, r5)     // Catch:{ Exception -> 0x03a2 }
            if (r8 != 0) goto L_0x0427
            r0 = r19
            java.lang.String r8 = r0.date     // Catch:{ Exception -> 0x03a2 }
            r0 = r18
            r0.a(r8, r5, r2)     // Catch:{ Exception -> 0x03a2 }
        L_0x0410:
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x034a
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            r0 = r19
            long r8 = r0.msgId     // Catch:{ Exception -> 0x03a2 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Exception -> 0x03a2 }
            b(r2, r6, r8)     // Catch:{ Exception -> 0x03a2 }
            goto L_0x0152
        L_0x0427:
            android.content.Context r2 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x03a2 }
            r0 = r18
            r0.b(r2, r5)     // Catch:{ Exception -> 0x03a2 }
            goto L_0x0410
        L_0x0431:
            r2 = r3
            goto L_0x02b4
        L_0x0434:
            r2 = r3
            goto L_0x01eb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.c.a.a(com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg, long, com.tencent.android.tpush.service.channel.a):void");
    }

    public void a(String str, Intent intent, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        if (i.b(str) || (!i.b(str) && simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) == 0)) {
            if (i.a(intent)) {
                List e2 = i.e(b.f(), str2 + Constants.RPC_SUFFIX);
                if (e2 == null || e2.size() < 1) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, ">> send message intent:" + intent);
                    }
                    com.tencent.android.tpush.c.c.a().a(b.f(), str2, intent);
                    return;
                }
                com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, ">> send rpc message intent:" + intent);
                a(intent);
            }
        } else if (!i.b(str) && simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) < 0) {
            List e3 = i.e(b.f(), str2 + Constants.RPC_SUFFIX);
            if (e3 == null || e3.size() < 1) {
                b.f().sendBroadcast(intent);
            } else {
                a(intent);
            }
        }
    }

    public void a(final Intent intent) {
        com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
            /* access modifiers changed from: private */
            public com.tencent.android.tpush.rpc.a c;
            /* access modifiers changed from: private */
            public com.tencent.android.tpush.rpc.c d = new com.tencent.android.tpush.rpc.c();
            /* access modifiers changed from: private */
            public ServiceConnection e = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        AnonymousClass5.this.c = C0062a.a(iBinder);
                        if (AnonymousClass5.this.c != null) {
                            AnonymousClass5.this.c.a(intent.toURI(), AnonymousClass5.this.d);
                        }
                    } catch (Throwable th) {
                        com.tencent.android.tpush.b.a.d("SrvMessageManager", "SendBroadcastByRPC", th);
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    AnonymousClass5.this.e = null;
                    AnonymousClass5.this.c = null;
                    AnonymousClass5.this.d = null;
                }
            };

            public void run() {
                try {
                    intent.setAction(intent.getPackage() + Constants.RPC_SUFFIX);
                    this.d.a(this.e);
                    if (!b.f().bindService(intent, this.e, 1)) {
                        com.tencent.android.tpush.b.a.i("SrvMessageManager", "Failed Send AIDL" + intent + " failed  msgid = " + intent.getLongExtra(MessageKey.MSG_ID, -1));
                        com.tencent.android.tpush.c.c.a().a(b.f(), intent.getPackage(), intent);
                        return;
                    }
                    com.tencent.android.tpush.b.a.c("SrvMessageManager", "Succeed Send AIDL" + intent + " success  msgid = " + intent.getLongExtra(MessageKey.MSG_ID, -1));
                    a.this.b(b.f(), intent);
                } catch (Throwable th) {
                    com.tencent.android.tpush.b.a.d("SrvMessageManager", "SendBroadcastByRPC -> bindService", th);
                    com.tencent.android.tpush.c.c.a().a(b.f(), intent.getPackage(), intent);
                }
            }
        });
    }

    public void a(ArrayList<TpnsPushMsg> arrayList, long j2, com.tencent.android.tpush.service.channel.a aVar) {
        b(arrayList, j2, aVar);
    }

    public void b(ArrayList<TpnsPushMsg> arrayList, long j2, com.tencent.android.tpush.service.channel.a aVar) {
        if (!(b.f() == null || arrayList == null || arrayList.size() <= 0)) {
            com.tencent.android.tpush.b.a.b(0, (List<TpnsPushMsg>) arrayList);
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= arrayList.size()) {
                    break;
                }
                com.tencent.android.tpush.b.a.a("SrvMessageManager", "receive msg from service msgId = " + ((TpnsPushMsg) arrayList.get(i3)).msgId + " pkg = " + ((TpnsPushMsg) arrayList.get(i3)).appPkgName + " size = " + arrayList.size());
                i2 = i3 + 1;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                final TpnsPushMsg tpnsPushMsg = (TpnsPushMsg) it.next();
                com.tencent.android.tpush.b.a.c("SrvMessageManager", "distributeFromServer : accid=" + tpnsPushMsg.accessId + " ,channelId=" + tpnsPushMsg.channelId + ",busiId=" + tpnsPushMsg.busiMsgId + ",pkg=" + tpnsPushMsg.appPkgName + ",msgId=" + tpnsPushMsg.msgId + ",type=" + tpnsPushMsg.type + ",ts=" + tpnsPushMsg.timestamp + ",multi=" + tpnsPushMsg.multiPkg + ",date=" + tpnsPushMsg.date + ",serverTime=" + tpnsPushMsg.serverTime + ",ttl=" + tpnsPushMsg.ttl + ", size = " + arrayList.size());
                a(b.f(), tpnsPushMsg, j2, aVar);
                if (tpnsPushMsg.type == 3) {
                    try {
                        if (tpnsPushMsg.getContent() != null) {
                            a(tpnsPushMsg.getContent());
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                    }
                } else if (i.b(tpnsPushMsg.appPkgName) && tpnsPushMsg.multiPkg == 0 && tpnsPushMsg.channelId <= 0) {
                    com.tencent.android.tpush.b.a.c("SrvMessageManager", ">> messageDistribute, msg.appPkgName is null!");
                } else if (tpnsPushMsg.accessId == XGPushConfig.getAccessId(b.f()) || System.currentTimeMillis() - XGPushServiceV4.a >= OkHttpUtils.DEFAULT_MILLISECONDS) {
                    a(tpnsPushMsg, j2, aVar);
                } else {
                    long nextInt = (long) (new Random().nextInt(ReaderCallback.GET_BAR_ANIMATING) + ReaderCallback.GET_BAR_ANIMATING);
                    com.tencent.android.tpush.b.a.c("SrvMessageManager", ">> messageDistribute, delay " + nextInt + "ms send to app, msgid:" + tpnsPushMsg.msgId + ", accessid:" + tpnsPushMsg.accessId);
                    final long j3 = j2;
                    final com.tencent.android.tpush.service.channel.a aVar2 = aVar;
                    com.tencent.android.tpush.common.c.a().a(new Runnable() {
                        public void run() {
                            a.this.a(tpnsPushMsg, j3, aVar2);
                        }
                    }, nextInt);
                }
            }
        }
        com.tencent.android.tpush.service.d.a.a(arrayList);
    }

    private synchronized void a(String str) {
        boolean z = false;
        synchronized (this) {
            if (!l.c(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str).getJSONObject("custom_content");
                    JSONArray jSONArray = jSONObject.getJSONArray("config");
                    int i2 = jSONObject.getInt(Config.INPUT_DEF_VERSION);
                    if (jSONArray != null && jSONArray.length() > 0) {
                        int i3 = 0;
                        while (i3 < jSONArray.length()) {
                            boolean a2 = a(jSONArray.getJSONObject(i3));
                            if (!a2) {
                                a2 = z;
                            }
                            i3++;
                            z = a2;
                        }
                    }
                    if (z) {
                        JSONArray b2 = b();
                        h.b(b.f(), "GroupKeysConfigVersion", i2);
                        h.b(b.f(), "ChannelGroupKeysConfig", b2.toString());
                    }
                } catch (Throwable th) {
                    com.tencent.android.tpush.b.a.d("SrvMessageManager", "handleGroupKeysConfig", th);
                }
            }
        }
        return;
    }

    private static JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : i.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            System.out.println("Key = " + entry.getKey());
            Map map = (Map) entry.getValue();
            JSONArray jSONArray2 = new JSONArray();
            for (Entry entry2 : map.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put((String) entry2.getKey(), new JSONArray((Collection) entry2.getValue()));
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put(((Long) entry.getKey()).toString(), jSONArray2);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private static synchronized void c() {
        synchronized (a.class) {
            try {
                if (i == null) {
                    i = new ConcurrentHashMap<>();
                    String a2 = h.a(b.f(), "ChannelGroupKeysConfig", (String) null);
                    if (!l.c(a2)) {
                        JSONArray jSONArray = new JSONArray(a2);
                        if (jSONArray != null && jSONArray.length() > 0) {
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                Iterator keys = jSONObject.keys();
                                while (keys.hasNext()) {
                                    String str = (String) keys.next();
                                    Long valueOf = Long.valueOf(str);
                                    JSONArray jSONArray2 = jSONObject.getJSONArray(str);
                                    if (jSONArray2 != null && jSONArray2.length() > 0) {
                                        HashMap hashMap = new HashMap();
                                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                                            Iterator keys2 = jSONObject2.keys();
                                            while (keys2.hasNext()) {
                                                String str2 = (String) keys2.next();
                                                hashMap.put(str2, a(jSONObject2.getJSONArray(str2)));
                                            }
                                        }
                                        i.put(valueOf, hashMap);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("SrvMessageManager", "initChanellGroupKey", th);
            }
        }
        return;
    }

    private synchronized boolean a(JSONObject jSONObject) {
        boolean z = false;
        synchronized (this) {
            Long valueOf = Long.valueOf(jSONObject.getLong("channelId"));
            if (valueOf.longValue() > 0) {
                JSONArray jSONArray = jSONObject.getJSONArray("infos");
                if (jSONArray != null && jSONArray.length() > 0) {
                    if (!i.containsKey(valueOf)) {
                        i.put(valueOf, new HashMap());
                    }
                    boolean z2 = false;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        Map map = (Map) i.get(valueOf);
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        String string = jSONObject2.getString("groupKey");
                        int i3 = jSONObject2.getInt(Config.OPERATOR);
                        JSONArray jSONArray2 = jSONObject2.getJSONArray("apps");
                        if (jSONArray2 != null && jSONArray2.length() > 0) {
                            if (!map.containsKey(string)) {
                                map.put(string, new ArrayList());
                            }
                            List list = (List) map.get(string);
                            List a2 = a(jSONArray2);
                            if (i3 == 1) {
                                list = a2;
                                z2 = true;
                            } else if (i3 == 2) {
                                a2.remove(list);
                                list.addAll(a2);
                                z2 = true;
                            } else if (i3 == 3) {
                                list.removeAll(a2);
                                z2 = true;
                            } else {
                                com.tencent.android.tpush.b.a.i("SrvMessageManager", "error op type " + i3);
                            }
                            map.put(string, list);
                        }
                        i.put(valueOf, map);
                    }
                    z = z2;
                }
            }
        }
        return z;
    }

    private static List<Long> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(Long.valueOf(jSONArray.getLong(i2)));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public synchronized void d() {
        if (com.tencent.android.tpush.service.channel.b.a().b(true) > 0) {
            e();
        }
    }

    private void e() {
        if (this.j == null) {
            b.f().registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    a.a().d();
                }
            }, new IntentFilter("com.tencent.android.tpush.service.channel.cacheMsgBeatIntent"));
            this.j = PendingIntent.getBroadcast(b.f(), 0, new Intent("com.tencent.android.tpush.service.channel.cacheMsgBeatIntent"), 134217728);
        }
        if (XGPushConfig.isForeignWeakAlarmMode(b.f())) {
            com.tencent.android.tpush.b.a.f("SrvMessageManager", "scheduleCacheMsgBeat WaekAlarmMode heartbeatinterval: " + com.tencent.android.tpush.service.channel.b.o + " ms");
            a = (long) com.tencent.android.tpush.service.channel.b.o;
        }
        d.a().a(0, System.currentTimeMillis() + a, this.j);
    }

    private void a(Context context, String str, String str2, ArrayList<?> arrayList) {
        try {
            if (arrayList.size() > 50) {
                arrayList.subList(0, 10).clear();
            }
            f.a(context, str + str2, Rijndael.encrypt(e.a((Serializable) arrayList)));
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("SrvMessageManager", "putSettings", e2);
        }
    }

    private Object a(Context context, String str, String str2) {
        try {
            return e.a(Rijndael.decrypt(f.a(context, str + str2)));
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("SrvMessageManager", "getSettings", e2);
            return null;
        }
    }
}
