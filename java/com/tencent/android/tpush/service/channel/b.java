package com.tencent.android.tpush.service.channel;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.ReturnCode;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.XGPushServiceV4;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a.a.C0067a;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.e.h;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.mid.sotrage.StorageInterface;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public final class b implements com.tencent.android.tpush.horse.d.b, C0067a {
    private static volatile long D = 0;
    private static volatile long E = 0;
    private static volatile long F = 0;
    /* access modifiers changed from: private */
    public static String G = "";
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;
    public static long d = 0;
    public static int e = 0;
    public static int f = 0;
    public static JSONArray g = null;
    public static JSONArray h = null;
    public static int i = 0;
    public static int j = 0;
    public static int k = 290000;
    public static int l = 180000;
    public static int m = 300000;
    public static int n = k;
    public static int o = 3600000;
    public static int p = 1800000;

    /* renamed from: q reason: collision with root package name */
    public static int f431q = l;
    protected static int r = 0;
    protected static Boolean s = null;
    private PendingIntent A;
    /* access modifiers changed from: private */
    public c B;
    /* access modifiers changed from: private */
    public volatile boolean C;
    private com.tencent.android.tpush.horse.d.a H;
    private Handler I;
    /* access modifiers changed from: private */
    public com.tencent.android.tpush.service.channel.c.a J;
    /* access modifiers changed from: private */
    public long K;
    private C0068b L;
    /* access modifiers changed from: private */
    public Handler t;
    /* access modifiers changed from: private */
    public ArrayList<c> u;
    /* access modifiers changed from: private */
    public Map<com.tencent.android.tpush.service.channel.a.a, ConcurrentHashMap<Integer, c>> v;
    /* access modifiers changed from: private */
    public Map<c, f> w;
    /* access modifiers changed from: private */
    public com.tencent.android.tpush.service.channel.a.a x;
    /* access modifiers changed from: private */
    public volatile boolean y;
    private PendingIntent z;

    /* compiled from: ProGuard */
    private class a implements Runnable {
        private com.tencent.android.tpush.service.channel.a.a b = null;
        private i c = null;

        public a(com.tencent.android.tpush.service.channel.a.a aVar, i iVar) {
            this.b = aVar;
            this.c = iVar;
        }

        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis() - b.this.B.c;
                a f = this.b.f();
                f.a(3, Long.valueOf(currentTimeMillis));
                com.tencent.android.tpush.service.channel.c.a aVar = b.this.B.f;
                if (aVar == null) {
                    com.tencent.android.tpush.b.a.i("TpnsChannel", ">> messageHandler is null");
                    b.this.J.a(b.this.B.e, this.c.l(), null, f);
                    return;
                }
                b.this.t.removeCallbacks((f) b.this.w.remove(b.this.B));
                aVar.a(b.this.B.e, this.c.l(), null, f);
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("TpnsChannel", "", th);
            }
        }
    }

    /* renamed from: com.tencent.android.tpush.service.channel.b$b reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private class C0068b extends BroadcastReceiver {
        private C0068b() {
        }

        public void onReceive(Context context, Intent intent) {
            long currentTimeMillis = System.currentTimeMillis();
            if (b.this.K == 0 || currentTimeMillis - b.this.K > 20000) {
                b.a().l();
                b.this.K = currentTimeMillis;
                return;
            }
            com.tencent.android.tpush.b.a.e("TpnsChannel", "give up heartbeatSlave ");
        }
    }

    /* compiled from: ProGuard */
    private class c implements Runnable {
        private com.tencent.android.tpush.service.channel.a.a b = null;
        private i c = null;

        public c(com.tencent.android.tpush.service.channel.a.a aVar, i iVar) {
            this.b = aVar;
            this.c = iVar;
        }

        public void run() {
            try {
                com.tencent.android.tpush.service.c.a().a(com.tencent.android.tpush.service.channel.c.b.a(this.c.h(), this.c.k()), this.b.f());
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("TpnsChannel", "run", th);
            }
        }
    }

    /* compiled from: ProGuard */
    private class d implements Runnable {
        private com.tencent.android.tpush.service.channel.a.a b = null;
        private ChannelException c = null;
        private boolean d = false;

        public d(com.tencent.android.tpush.service.channel.a.a aVar, ChannelException channelException, boolean z) {
            this.b = aVar;
            this.c = channelException;
            this.d = z;
        }

        public void run() {
            boolean z;
            if (this.b == null) {
                com.tencent.android.tpush.b.a.i("TpnsChannel", "@@RequestFailRunnable currentClient == null");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            a f = this.b.f();
            int i = this.c.errorCode;
            if (!this.d || !(i == ReturnCode.CODE_NETWORK_UNKNOWN_EXCEPTION.getType() || i == ReturnCode.CODE_NETWORK_UNEXPECTED_DATA_EXCEPTION_OCCUR.getType())) {
                z = false;
            } else {
                z = true;
            }
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) b.this.v.get(this.b);
            if (concurrentHashMap != null) {
                for (Entry value : concurrentHashMap.entrySet()) {
                    c cVar = (c) value.getValue();
                    com.tencent.android.tpush.service.channel.c.a aVar = cVar.f;
                    if (aVar != null) {
                        if (!z || cVar.a >= 5) {
                            f.a(3, Long.valueOf(currentTimeMillis - cVar.c));
                            b.this.t.removeCallbacks((f) b.this.w.remove(cVar));
                            aVar.a(cVar.e, this.c, f);
                        } else if (cVar.b()) {
                            b.this.a(cVar);
                        }
                    }
                }
                concurrentHashMap.clear();
            }
            if (!this.b.e()) {
                ArrayList arrayList = new ArrayList();
                synchronized (b.this) {
                    Iterator it = b.this.u.iterator();
                    while (it.hasNext()) {
                        c cVar2 = (c) it.next();
                        com.tencent.android.tpush.service.channel.c.a aVar2 = cVar2.f;
                        if (aVar2 != null) {
                            if (!z || cVar2.a >= 5) {
                                f.a(3, Long.valueOf(currentTimeMillis - cVar2.c));
                                b.this.t.removeCallbacks((f) b.this.w.get(cVar2));
                                aVar2.a(cVar2.e, this.c, f);
                                arrayList.add(cVar2);
                            } else {
                                cVar2.a++;
                            }
                        }
                    }
                    if (z) {
                        b.this.u.removeAll(arrayList);
                    } else {
                        b.this.u.clear();
                    }
                }
            }
            b.a = 0;
            if (b.n > b.l) {
                b.n = (b.n / 10) * 9;
            } else {
                b.n = b.l;
            }
            b.this.c();
            if (!b.this.u.isEmpty()) {
                b.this.e();
            }
            if (this.d) {
                b.this.f();
            }
        }
    }

    /* compiled from: ProGuard */
    private class e implements Runnable {
        private com.tencent.android.tpush.service.channel.a.a b = null;
        private i c = null;

        public e(com.tencent.android.tpush.service.channel.a.a aVar, i iVar) {
            this.b = aVar;
            this.c = iVar;
        }

        public void run() {
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) b.this.v.get(this.b);
            if (concurrentHashMap != null) {
                c cVar = (c) concurrentHashMap.get(Integer.valueOf(this.c.i()));
                if (cVar == null) {
                    com.tencent.android.tpush.b.a.i("TpnsChannel", ">> NetCallBackRunnable >>> 请求已被回调过，响应对应的request不存在。" + this.c);
                    try {
                        com.tencent.android.tpush.b.a.i("TpnsChannel", "onRequestSuccRunnable unhandle message type" + com.tencent.android.tpush.service.channel.c.b.a(this.c.h(), this.c.k()).getClass().getName());
                    } catch (Exception e) {
                        com.tencent.android.tpush.b.a.d("TpnsChannel", "", e);
                    }
                } else {
                    b.this.t.removeCallbacks((f) b.this.w.remove(cVar));
                    concurrentHashMap.remove(Integer.valueOf(this.c.i()));
                    com.tencent.android.tpush.service.channel.c.a aVar = cVar.f;
                    if (aVar == null) {
                        com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, ">> messageHandler is null");
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis() - cVar.c;
                    a f = this.b.f();
                    f.a(3, Long.valueOf(currentTimeMillis));
                    try {
                        aVar.a(cVar.e, this.c.l(), com.tencent.android.tpush.service.channel.c.b.a(this.c.h(), this.c.k()), f);
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d("TpnsChannel", "", e2);
                    }
                }
            }
        }
    }

    /* compiled from: ProGuard */
    private class f implements Runnable {
        private f() {
        }

        public void run() {
            a aVar;
            boolean z;
            long j;
            long j2;
            boolean z2;
            boolean z3;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                long j3 = Long.MAX_VALUE;
                long j4 = (long) com.tencent.android.tpush.service.a.a.a(com.tencent.android.tpush.service.b.f()).f;
                boolean z4 = false;
                long j5 = j4 < 15000 ? 15000 : j4;
                ChannelException channelException = new ChannelException(ReturnCode.CODE_NETWORK_TIMEOUT_WAITING_FOR_RESPONSE.getType(), "TpnsMessage wait for response timeout!");
                for (com.tencent.android.tpush.service.channel.a.a aVar2 : b.this.v.keySet()) {
                    ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) b.this.v.get(aVar2);
                    if (concurrentHashMap == null || concurrentHashMap.size() == 0) {
                        z2 = z4;
                        j2 = j3;
                    } else {
                        Iterator it = concurrentHashMap.entrySet().iterator();
                        a f = aVar2.f();
                        z2 = z4;
                        j2 = j3;
                        while (it.hasNext()) {
                            c cVar = (c) ((Entry) it.next()).getValue();
                            if (cVar != null) {
                                long j6 = currentTimeMillis - cVar.c;
                                f.a(3, Long.valueOf(j6));
                                if (j6 >= 0) {
                                    if (j6 > j5) {
                                        com.tencent.android.tpush.service.channel.c.a aVar3 = cVar.f;
                                        if (aVar3 != null) {
                                            aVar3.a(cVar.e, channelException, f);
                                            cVar.f = null;
                                        }
                                        it.remove();
                                        z3 = true;
                                    } else {
                                        if (j5 - j6 < j2) {
                                            j2 = j5 - j6;
                                            z3 = z2;
                                        }
                                        z3 = z2;
                                    }
                                }
                            } else {
                                it.remove();
                                z3 = z2;
                            }
                            z2 = z3;
                        }
                    }
                    j3 = j2;
                    z4 = z2;
                }
                ChannelException channelException2 = new ChannelException(ReturnCode.CODE_NETWORK_TIMEOUT_WAITING_TO_SEND.getType(), "TpnsMessage wait for response timeout!");
                a aVar4 = null;
                synchronized (b.this) {
                    Iterator it2 = b.this.u.iterator();
                    while (it2.hasNext()) {
                        c cVar2 = (c) it2.next();
                        if (cVar2 != null) {
                            long j7 = currentTimeMillis - cVar2.b;
                            if (j7 >= 0) {
                                if (j7 > j5) {
                                    com.tencent.android.tpush.service.channel.c.a aVar5 = cVar2.f;
                                    if (aVar5 != null) {
                                        if (aVar4 == null) {
                                            if (b.this.x != null) {
                                                aVar4 = b.this.x.f();
                                            } else {
                                                aVar4 = new a();
                                            }
                                            aVar4.a(3, Long.valueOf(j7));
                                        }
                                        aVar5.a(cVar2.e, channelException2, aVar4);
                                        cVar2.f = null;
                                    }
                                    aVar = aVar4;
                                    it2.remove();
                                    z = true;
                                    j = j3;
                                } else {
                                    if (j5 - j7 < j3) {
                                        aVar = aVar4;
                                        z = z4;
                                        j = j5 - j7;
                                    }
                                    aVar = aVar4;
                                    z = z4;
                                    j = j3;
                                }
                            }
                        } else {
                            it2.remove();
                            aVar = aVar4;
                            z = z4;
                            j = j3;
                        }
                        j3 = j;
                        z4 = z;
                        aVar4 = aVar;
                    }
                }
                if (z4) {
                    b.this.d();
                }
            } catch (Exception e) {
                com.tencent.android.tpush.b.a.d("TpnsChannel", "TimeoutRunnable.run", e);
            }
        }
    }

    /* compiled from: ProGuard */
    public static class g {
        public static final b a = new b();
    }

    public static b a() {
        return g.a;
    }

    private b() {
        this.t = null;
        this.u = new ArrayList<>();
        this.v = new ConcurrentHashMap();
        this.w = new ConcurrentHashMap();
        this.x = null;
        this.y = false;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = true;
        this.H = new com.tencent.android.tpush.horse.d.a() {
            public void a(int i, String str) {
                com.tencent.android.tpush.b.a.i("TpnsChannel", "ICreateSocketChannelCallback onFailure(" + i + StorageInterface.KEY_SPLITER + str + ")");
                synchronized (b.this) {
                    b.this.y = false;
                    if (!b.this.f()) {
                        com.tencent.android.tpush.b.a.f("TpnsChannel", "Connect to Xinge Server failed!");
                        ChannelException channelException = new ChannelException(i, str);
                        Iterator it = b.this.u.iterator();
                        while (it.hasNext()) {
                            c cVar = (c) it.next();
                            if (cVar.f != null) {
                                cVar.f.a(cVar.e, channelException, a.a());
                            } else {
                                com.tencent.android.tpush.b.a.i("TpnsChannel", cVar.toString());
                            }
                        }
                        b.this.u.clear();
                    }
                    b.a = 0;
                }
                b.f++;
                try {
                    if (b.g == null) {
                        b.g = new JSONArray();
                    }
                    if (b.g != null && b.g.length() < 10) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("errorCode", i);
                        if (com.tencent.android.tpush.service.b.f() != null) {
                            jSONObject.put("np", DeviceInfos.getNetworkType(com.tencent.android.tpush.service.b.f()));
                        }
                        b.g.put(jSONObject);
                    }
                } catch (Throwable th) {
                }
            }

            public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
                com.tencent.android.tpush.service.channel.a.a aVar;
                com.tencent.android.tpush.b.a.a("TpnsChannel", "ICreateSocketChannelCallback onSuccess(" + socketChannel + StorageInterface.KEY_SPLITER + socketChannel + ")");
                b.e++;
                synchronized (b.this) {
                    b.this.y = false;
                    b.r = 0;
                    try {
                        if (!b.G.equals(strategyItem.getServerIp())) {
                            switch (DeviceInfos.getNetworkType(com.tencent.android.tpush.service.b.f())) {
                                case 1:
                                    b.n = b.l;
                                    break;
                                case 2:
                                    b.n = b.k;
                                    break;
                                case 3:
                                    b.n = b.k;
                                    break;
                                case 4:
                                    b.n = b.k;
                                    break;
                            }
                            b.G = strategyItem.getServerIp();
                        }
                        b.a = 0;
                        b bVar = b.this;
                        if (!strategyItem.isHttp()) {
                            aVar = new com.tencent.android.tpush.service.channel.a.a(socketChannel, b.a());
                        } else if (strategyItem.isWap()) {
                            aVar = new com.tencent.android.tpush.service.channel.a.c(socketChannel, b.a(), strategyItem.getServerIp(), strategyItem.getServerPort());
                        } else {
                            aVar = new com.tencent.android.tpush.service.channel.a.b(socketChannel, b.a());
                        }
                        bVar.x = aVar;
                        b.this.a(true);
                        b.this.v.clear();
                        b.this.v.put(b.this.x, new ConcurrentHashMap());
                        b.this.C = true;
                        b.this.x.start();
                    } catch (Exception e) {
                        com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "", e);
                    }
                }
            }
        };
        this.I = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1000:
                        try {
                            b.this.e();
                            return;
                        } catch (Throwable th) {
                            com.tencent.android.tpush.b.a.i("TpnsChannel", "checkAndSetupClient expected:" + th);
                            return;
                        }
                    default:
                        com.tencent.android.tpush.b.a.i("TpnsChannel", "Unexpected: unhandled msg - " + message.what);
                        return;
                }
            }
        };
        this.J = new com.tencent.android.tpush.service.channel.c.a() {
            public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
                if (i == 0) {
                    com.tencent.android.tpush.service.d.a.a();
                    if (b.a >= 3) {
                    }
                    b.a++;
                }
                b.b++;
                com.tencent.android.tpush.b.a.c("TpnsChannel", "heartbeat success rsp = " + aVar.c() + " heartbeattimes = " + b.a);
                b.i();
            }

            public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
                b.c++;
                try {
                    com.tencent.android.tpush.b.a.i("TpnsChannel", "heartbeat failed onMessageSendFailed " + channelException.errorCode + StorageInterface.KEY_SPLITER + channelException.getMessage());
                    if (b.h == null) {
                        b.h = new JSONArray();
                    }
                    if (!(channelException == null || b.h == null || b.h.length() >= 15)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("errorCode", channelException.errorCode);
                        if (com.tencent.android.tpush.service.b.f() != null) {
                            jSONObject.put("np", DeviceInfos.getNetworkType(com.tencent.android.tpush.service.b.f()));
                        }
                        b.h.put(jSONObject);
                    }
                } catch (Throwable th) {
                    com.tencent.android.tpush.b.a.i("TpnsChannel", "Add reprot error");
                }
                b.i();
            }

            public void a(JceStruct jceStruct, a aVar) {
                com.tencent.android.tpush.b.a.i("TpnsChannel", "heartbeat failed TpnsMessage.IEventListener.onMessageDiscarded");
            }
        };
        this.K = 0;
        this.L = new C0068b();
        com.tencent.android.tpush.horse.d.a().a((com.tencent.android.tpush.horse.d.b) this);
        this.t = com.tencent.android.tpush.common.c.a().b();
    }

    public void b() {
        e();
    }

    public void c() {
        this.y = false;
        if (this.x != null) {
            this.x.c();
            this.x = null;
        }
    }

    public void d() {
        c();
        e();
    }

    public void e() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.a("TpnsChannel", "Action -> checkAndSetupClient( tpnsClient = " + this.x + ", isClientCreating = " + this.y + ")");
        }
        synchronized (this) {
            if (this.x == null && !this.y) {
                this.y = true;
                try {
                    com.tencent.android.tpush.horse.d.a().a(this.H);
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.d("TpnsChannel", "createOptimalSocketChannel error", e2);
                }
            } else if (!this.y && this.x != null && !this.x.d()) {
                com.tencent.android.tpush.b.a.i("TpnsChannel", "The socket Channel is unconnected");
                try {
                    this.x.c();
                    com.tencent.android.tpush.horse.d.a().a(this.H);
                } catch (Exception e3) {
                    com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "createOptimalSocketChannel error", e3);
                }
            }
        }
        return;
    }

    /* access modifiers changed from: protected */
    public synchronized boolean f() {
        boolean z2 = false;
        synchronized (this) {
            if (com.tencent.android.tpush.service.e.a.d(com.tencent.android.tpush.service.b.f())) {
                int j2 = l.j(com.tencent.android.tpush.service.b.f());
                if (l.i(com.tencent.android.tpush.service.b.f()) || j2 > 0) {
                    int i2 = (r + 1) * 2 * 1000;
                    r++;
                    if (r <= 3) {
                        if (i2 > l) {
                            i2 = l;
                        }
                        if (r <= 3 || j2 == 1) {
                            if (!this.I.hasMessages(1000)) {
                                if (XGPushConfig.enableDebug) {
                                    com.tencent.android.tpush.b.a.c("TpnsChannel", "onDisconnected and retry HANDLER_CHECKANDSETUP " + i2 + " retry times = " + r);
                                }
                                this.I.sendEmptyMessageDelayed(1000, (long) i2);
                            }
                            z2 = true;
                        }
                    }
                }
            }
        }
        return z2;
    }

    private void a(int i2, c cVar) {
        try {
            synchronized (this) {
                if (this.u.size() < 128) {
                    cVar.b = System.currentTimeMillis();
                    if (i2 == -1) {
                        this.u.add(cVar);
                    } else {
                        this.u.add(i2, cVar);
                    }
                } else {
                    com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, ">>FG messageInQueue is full,size:" + this.u.size());
                }
                if (this.x != null) {
                    this.x.h();
                }
                e();
            }
            f fVar = new f();
            this.w.put(cVar, fVar);
            this.t.postDelayed(fVar, (long) com.tencent.android.tpush.service.a.a.a(com.tencent.android.tpush.service.b.f()).f);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "messageInQueue", th);
        }
    }

    public void a(JceStruct jceStruct, com.tencent.android.tpush.service.channel.c.a aVar) {
        if (jceStruct != null) {
            try {
                a(-1, new c(jceStruct, aVar));
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d(Constants.ServiceLogTag, "sendMessage error ", e2);
            }
        } else {
            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "sendMessage null jceMessage");
        }
    }

    public void a(boolean z2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - E > 120000 || z2) {
            E = currentTimeMillis;
            c b2 = com.tencent.android.tpush.service.c.a().b();
            if (b2 != null) {
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> sendReconnMessage with token - " + CacheManager.getToken(com.tencent.android.tpush.service.b.f()));
                }
                if (com.tencent.android.tpush.service.b.f() != null && !"0".equals(CacheManager.getToken(com.tencent.android.tpush.service.b.f()))) {
                    a(0, b2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void k() {
        if (this.u.isEmpty()) {
            if (this.B == null) {
                this.B = new c(7, null, this.J);
            }
            if (this.B.f == null) {
                this.B = new c(7, null, this.J);
            }
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> send heartbeat ");
            }
            a(-1, this.B);
            if ((a > 0 && a % 3 == 0) || a == 2) {
                com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                    public void run() {
                        com.tencent.android.tpush.service.e.i.h(com.tencent.android.tpush.service.b.f());
                    }
                });
            }
        }
        m();
        com.tencent.android.tpush.a.b(com.tencent.android.tpush.service.b.f());
        com.tencent.android.tpush.service.f.a(com.tencent.android.tpush.service.b.f()).a();
        i();
        if (XGPushConfig.isLocationEnable(com.tencent.android.tpush.service.b.f())) {
            if (F == 0) {
                F = h.a(com.tencent.android.tpush.service.b.f(), Constants.LOC_REPORT_TIME, 0);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (F == 0 || Math.abs(currentTimeMillis - F) > ((long) p)) {
                final JSONObject reportLocationJson = CustomDeviceInfos.getReportLocationJson(com.tencent.android.tpush.service.b.f());
                com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                    public void run() {
                        com.tencent.android.tpush.service.d.a.b(com.tencent.android.tpush.service.b.f(), "location", reportLocationJson);
                    }
                });
                F = currentTimeMillis;
                h.b(com.tencent.android.tpush.service.b.f(), Constants.LOC_REPORT_TIME, currentTimeMillis);
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void l() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> send heartbeatSlave ");
        }
        i++;
        h();
        if (com.tencent.android.tpush.service.b.a().f(com.tencent.android.tpush.service.b.f())) {
            if (!com.tencent.android.tpush.service.e.a.d(com.tencent.android.tpush.service.b.f())) {
                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "network is unreachable ,give up and go on slave service");
            } else if (com.tencent.android.tpush.service.b.f() != null) {
                com.tencent.android.tpush.common.c.a().a((Runnable) new Runnable() {
                    public void run() {
                        if (XGPushConfig.isForeiginPush(com.tencent.android.tpush.service.b.f())) {
                            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "isForeiginPush network is ok , switch to main service");
                            com.tencent.android.tpush.service.b.a(com.tencent.android.tpush.service.b.f(), Constants.ACTION_SLVAE_2_MAIN, 0);
                        } else if (com.tencent.android.tpush.service.b.b.a(com.tencent.android.tpush.service.b.f()).b()) {
                            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "network is ok , switch to main service");
                            com.tencent.android.tpush.service.b.a(com.tencent.android.tpush.service.b.f(), Constants.ACTION_SLVAE_2_MAIN, 0);
                        } else {
                            com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "network is error , go on  slave service");
                        }
                    }
                });
            } else {
                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "PushServiceManager.getInstance().getContext() is null");
            }
        }
    }

    public int b(boolean z2) {
        a(true, (String) null);
        return 0;
    }

    public int a(boolean z2, String str) {
        ArrayList c2;
        int i2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - D <= 120000 && !z2) {
            return 0;
        }
        D = currentTimeMillis;
        Context f2 = com.tencent.android.tpush.service.b.f();
        if (f2 == null || com.tencent.android.tpush.service.e.i.b(f2.getPackageName())) {
            return 0;
        }
        if (str == null) {
            c2 = com.tencent.android.tpush.c.c.a().d(f2);
        } else {
            c2 = com.tencent.android.tpush.c.c.a().c(f2, str);
        }
        if (c2 == null || c2.size() <= 0) {
            return 0;
        }
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "Action -> trySendCachedMsgIntent with CachedMsgList size = " + c2.size());
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            int i3 = i2;
            if (i3 >= c2.size()) {
                break;
            }
            CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) c2.get(i3);
            try {
                String decrypt = Rijndael.decrypt(cachedMessageIntent.intent);
                if (com.tencent.android.tpush.service.e.i.b(decrypt)) {
                    arrayList.add(cachedMessageIntent);
                } else {
                    Intent parseUri = Intent.parseUri(decrypt, 1);
                    String str2 = parseUri.getPackage();
                    parseUri.getLongExtra(MessageKey.MSG_CREATE_MULTIPKG, 0);
                    if (!com.tencent.android.tpush.service.e.i.b(com.tencent.android.tpush.service.b.f(), str2, parseUri.getLongExtra("accId", 0))) {
                        arrayList.add(cachedMessageIntent);
                        com.tencent.android.tpush.b.a.i("TpnsChannel", str2 + " is uninstalled , discard the msg and report to the server");
                        com.tencent.android.tpush.service.c.a().a(str2);
                        com.tencent.android.tpush.c.c.a().b(com.tencent.android.tpush.service.b.f(), str2, new ArrayList<>());
                    } else {
                        RegisterEntity registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str2);
                        if (registerInfoByPkgName == null || registerInfoByPkgName.state <= 0) {
                            long longExtra = parseUri.getLongExtra(MessageKey.MSG_ID, 0);
                            parseUri.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
                            parseUri.getStringExtra(MessageKey.MSG_DATE);
                            if (!com.tencent.android.tpush.a.a(com.tencent.android.tpush.service.b.f(), parseUri.getPackage(), parseUri)) {
                                com.tencent.android.tpush.b.a.i(Constants.ServiceLogTag, "ProviderUtils.sendMsgByPkgName error msgId = " + longExtra + " appPkgName = " + str2);
                            } else {
                                arrayList.add(cachedMessageIntent);
                                com.tencent.android.tpush.service.c.a.a().b(com.tencent.android.tpush.service.b.f(), parseUri);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d("TpnsChannel", "", e2);
            }
            i2 = i3 + 1;
        }
        com.tencent.android.tpush.b.a.c(Constants.ServiceLogTag, "sendedList.size() = " + arrayList.size());
        if (arrayList.size() > 0) {
            com.tencent.android.tpush.c.c.a().a(f2, (List<CachedMessageIntent>) arrayList, c2);
        }
        return c2.size();
    }

    private void m() {
        try {
            if (this.z == null) {
                com.tencent.android.tpush.service.b.f().registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        b.a().k();
                    }
                }, new IntentFilter("com.tencent.android.tpush.service.channel.heartbeatIntent"));
                this.z = PendingIntent.getBroadcast(com.tencent.android.tpush.service.b.f(), 0, new Intent("com.tencent.android.tpush.service.channel.heartbeatIntent"), 134217728);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (n > m) {
                n = m;
            }
            if (XGPushConfig.isForeignWeakAlarmMode(com.tencent.android.tpush.service.b.f())) {
                com.tencent.android.tpush.b.a.f("TpnsChannel", "scheduleHeartbeat WaekAlarmMode heartbeatinterval: " + o + " ms");
                n = o;
            }
            n = h.a(com.tencent.android.tpush.service.b.f(), "com.tencent.android.xg.wx.HeartbeatIntervalMs", n);
            com.tencent.android.tpush.service.d.a().a(0, currentTimeMillis + ((long) n), this.z);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("TpnsChannel", "scheduleHeartbeat error", th);
        }
    }

    public void g() {
        try {
            if (this.A == null && this.L != null) {
                com.tencent.android.tpush.service.b.f().unregisterReceiver(this.L);
            }
        } catch (Throwable th) {
        }
    }

    public void h() {
        try {
            if (this.A == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                intentFilter.addAction("com.tencent.android.tpush.service.channel.heartbeatIntent.pullup");
                com.tencent.android.tpush.service.b.f().registerReceiver(this.L, intentFilter);
                this.A = PendingIntent.getBroadcast(com.tencent.android.tpush.service.b.f(), 0, new Intent("com.tencent.android.tpush.service.channel.heartbeatIntent.pullup"), 134217728);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (f431q > m) {
                f431q = m;
            }
            if (XGPushConfig.isForeignWeakAlarmMode(com.tencent.android.tpush.service.b.f())) {
                com.tencent.android.tpush.b.a.f("TpnsChannel", "schedulePullUpHeartbeat WaekAlarmMode heartbeatinterval: " + o + " ms");
                f431q = o;
            }
            f431q = h.a(com.tencent.android.tpush.service.b.f(), "com.tencent.android.xg.wx.HeartbeatIntervalMs", f431q);
            long j2 = currentTimeMillis + ((long) f431q);
            b(true);
            com.tencent.android.tpush.service.d.a().a(0, j2, this.A);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("TpnsChannel", "scheduleHeartbeat error", th);
        }
    }

    public synchronized ArrayList<com.tencent.android.tpush.service.channel.b.h> a(com.tencent.android.tpush.service.channel.a.a aVar, int i2) {
        ArrayList<com.tencent.android.tpush.service.channel.b.h> arrayList;
        if (i2 < 1) {
            i2 = 1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.v.get(aVar);
        arrayList = new ArrayList<>(i2);
        if (!this.u.isEmpty()) {
            Iterator it = this.u.iterator();
            c cVar = (c) it.next();
            com.tencent.android.tpush.service.channel.b.h hVar = new com.tencent.android.tpush.service.channel.b.h(cVar.c());
            cVar.a(hVar);
            arrayList.add(hVar);
            cVar.c = currentTimeMillis;
            if (!cVar.a()) {
                concurrentHashMap.put(Integer.valueOf(cVar.d()), cVar);
            }
            it.remove();
            int i3 = i2 - 1;
            boolean z2 = cVar.e instanceof TpnsReconnectReq;
            while (it.hasNext()) {
                final c cVar2 = (c) it.next();
                if (!z2 || (!(cVar2.e instanceof TpnsReconnectReq) && !(cVar2.e instanceof TpnsPushVerifyReq))) {
                    int i4 = i3 - 1;
                    if (i3 > 0) {
                        com.tencent.android.tpush.service.channel.b.h hVar2 = new com.tencent.android.tpush.service.channel.b.h(cVar2.c());
                        cVar2.a(hVar2);
                        arrayList.add(hVar2);
                        cVar2.c = currentTimeMillis;
                        if (!cVar2.a()) {
                            concurrentHashMap.put(Integer.valueOf(cVar2.d()), cVar2);
                        }
                        it.remove();
                    }
                    i3 = i4;
                } else {
                    if (cVar2.f != null) {
                        this.t.post(new Runnable() {
                            public void run() {
                                cVar2.f.a(cVar2.e, new a());
                            }
                        });
                    }
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public void a(com.tencent.android.tpush.service.channel.a.a aVar, ChannelException channelException) {
        com.tencent.android.tpush.b.a.i("TpnsChannel", "clientExceptionOccurs(isHttpClient : " + (aVar instanceof com.tencent.android.tpush.service.channel.a.b) + StorageInterface.KEY_SPLITER + channelException + ")");
        f++;
        this.t.post(new d(aVar, channelException, true));
        try {
            if (g == null) {
                g = new JSONArray();
            }
            if (g != null && g.length() < 10) {
                JSONObject jSONObject = new JSONObject();
                if (channelException != null) {
                    jSONObject.put("errorCode", channelException.errorCode);
                }
                if (com.tencent.android.tpush.service.b.f() != null) {
                    jSONObject.put("np", DeviceInfos.getNetworkType(com.tencent.android.tpush.service.b.f()));
                }
                g.put(jSONObject);
            }
        } catch (Throwable th) {
        }
        i();
    }

    public void a(com.tencent.android.tpush.service.channel.a.a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.i("TpnsChannel", "Action -> clientDidCancelled " + aVar);
        }
        this.t.post(new d(aVar, new ChannelException(ReturnCode.CODE_NETWORK_CHANNEL_CANCELLED.getType(), "TpnsClient is cancelled!"), false));
    }

    public void b(com.tencent.android.tpush.service.channel.a.a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.i("TpnsChannel", "Action -> clientDidRetired " + aVar);
        }
        this.t.post(new d(aVar, new ChannelException(ReturnCode.CODE_NETWORK_TIMEOUT_EXCEPTION_OCCUR.getType(), "TpnsMessage timeout!"), false));
    }

    public void a(com.tencent.android.tpush.service.channel.a.a aVar, i iVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> clientDidSendPacket packet : " + iVar.m());
        }
        c cVar = (c) ((ConcurrentHashMap) this.v.get(aVar)).get(Integer.valueOf(iVar.i()));
        if (cVar != null) {
            cVar.c = System.currentTimeMillis();
        } else {
            com.tencent.android.tpush.b.a.i("TpnsChannel", ">> message(" + iVar.i() + ") not in the sentQueue!");
        }
    }

    public synchronized void b(com.tencent.android.tpush.service.channel.a.a aVar, i iVar) {
        a().b(false);
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> clientDidReceivePacket packet : " + iVar.m());
        }
        switch (iVar.j()) {
            case 1:
            case 10:
                if (iVar.e()) {
                    com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> clientDidReceivePacket RequestSuccRunnable NEV1 : " + iVar.m());
                    this.t.post(new e(aVar, iVar));
                } else {
                    com.tencent.android.tpush.b.a.c("TpnsChannel", "Action -> clientDidReceivePacket PushMessageRunnable NEV1 : " + iVar.m());
                    this.t.post(new c(aVar, iVar));
                }
                m();
                break;
            case 20:
                this.t.post(new a(aVar, iVar));
                break;
            default:
                com.tencent.android.tpush.b.a.i("TpnsChannel", "Action -> clientDidReceivePacket unkonwn protocol : " + iVar.m());
                m();
                break;
        }
    }

    public static void i() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (d == 0) {
                d = currentTimeMillis;
            } else if (currentTimeMillis - d < 30000) {
                return;
            }
            if (com.tencent.android.tpush.service.b.f() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("srv_stime", XGPushServiceV4.a);
                jSONObject.put("srv_etime", System.currentTimeMillis());
                jSONObject.put("srv_startTime", XGPushServiceV4.b);
                if (XGPushServiceV4.c != null) {
                    jSONObject.put("srv_freason", XGPushServiceV4.c);
                }
                jSONObject.put("hb_suc", b);
                jSONObject.put("hb_failed", c);
                if (h != null) {
                    jSONObject.put("hb_freason", h);
                }
                jSONObject.put("con_suc", e);
                jSONObject.put("con_failed", f);
                if (g != null) {
                    jSONObject.put("con_freason", g);
                }
                h.b(com.tencent.android.tpush.service.b.f(), "service_state", jSONObject.toString());
                d = currentTimeMillis;
                com.tencent.android.tpush.b.a.e("TpnsChannel", "Service bi state " + jSONObject.toString());
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("TpnsChannel", "saveBIReportJson ", th);
        }
    }

    /* access modifiers changed from: private */
    public void a(c cVar) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = false;
        try {
            if (cVar.d == 4) {
                Iterator it = this.u.iterator();
                while (it.hasNext()) {
                    if (((c) it.next()).d == 4) {
                        z4 = true;
                    } else {
                        z4 = z5;
                    }
                    z5 = z4;
                }
                if (!z5) {
                    this.u.add(cVar);
                }
            } else if (cVar.d == 15) {
                Iterator it2 = this.u.iterator();
                while (it2.hasNext()) {
                    if (((c) it2.next()).d() == cVar.d()) {
                        z3 = true;
                    } else {
                        z3 = z5;
                    }
                    z5 = z3;
                }
                if (!z5) {
                    this.u.add(cVar);
                }
            } else if (cVar.d == 5) {
                Iterator it3 = this.u.iterator();
                while (it3.hasNext()) {
                    if (((c) it3.next()).d == 5) {
                        z2 = true;
                    } else {
                        z2 = z5;
                    }
                    z5 = z2;
                }
                if (!z5) {
                    this.u.add(cVar);
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("TpnsChannel", "addTpnsMessages", th);
        }
    }
}
