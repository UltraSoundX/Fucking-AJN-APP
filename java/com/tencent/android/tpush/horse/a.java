package com.tencent.android.tpush.horse;

import com.baidu.mobstat.Config;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.lang.Thread.State;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public abstract class a {
    /* access modifiers changed from: private */
    public static final Object a = new Object();
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<StrategyItem> b = new LinkedBlockingQueue<>();
    private ConcurrentHashMap<Integer, b> c = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public C0061a d;
    /* access modifiers changed from: private */
    public AtomicInteger e = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public volatile boolean f = false;

    /* renamed from: com.tencent.android.tpush.horse.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public interface C0061a {
        void a(StrategyItem strategyItem);

        void a(SocketChannel socketChannel, StrategyItem strategyItem);
    }

    /* compiled from: ProGuard */
    private class b extends Thread {
        protected com.tencent.android.tpush.horse.e.a a = new com.tencent.android.tpush.horse.e.a() {
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0080, code lost:
                if (r5.getProtocolType() != 0) goto L_0x0090;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x0082, code lost:
                r4.a.b.e();
                r4.a.b.f();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0090, code lost:
                com.tencent.android.tpush.service.cache.CacheManager.addOptStrategy(r5);
                r4.a.b.a();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a2, code lost:
                if (com.tencent.android.tpush.horse.a.c(r4.a.b) == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a4, code lost:
                com.tencent.android.tpush.horse.a.c(r4.a.b).a(r4.a.a().a(), r5);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(com.tencent.android.tpush.horse.data.StrategyItem r5) {
                /*
                    r4 = this;
                    r3 = 1
                    boolean r0 = com.tencent.android.tpush.XGPushConfig.enableDebug
                    if (r0 == 0) goto L_0x0045
                    java.lang.String r0 = "BaseTask"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "Horse run onSuccess("
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.StringBuilder r1 = r1.append(r5)
                    java.lang.String r2 = ","
                    java.lang.StringBuilder r1 = r1.append(r2)
                    com.tencent.android.tpush.horse.a$b r2 = com.tencent.android.tpush.horse.a.b.this
                    int r2 = r2.d
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.String r2 = ","
                    java.lang.StringBuilder r1 = r1.append(r2)
                    com.tencent.android.tpush.horse.a$b r2 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r2 = com.tencent.android.tpush.horse.a.this
                    boolean r2 = r2.f
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.String r2 = ")"
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.tencent.android.tpush.b.a.a(r0, r1)
                L_0x0045:
                    java.lang.Object r1 = com.tencent.android.tpush.horse.a.a
                    monitor-enter(r1)
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00c8 }
                    java.util.concurrent.LinkedBlockingQueue r0 = r0.b     // Catch:{ all -> 0x00c8 }
                    r0.clear()     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00c8 }
                    boolean r0 = r0.f     // Catch:{ all -> 0x00c8 }
                    if (r0 == 0) goto L_0x0067
                    boolean r0 = r5.isRedirected()     // Catch:{ all -> 0x00c8 }
                    if (r0 != 0) goto L_0x0067
                    monitor-exit(r1)     // Catch:{ all -> 0x00c8 }
                L_0x0066:
                    return
                L_0x0067:
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00c8 }
                    r2 = 1
                    r0.f = r2     // Catch:{ all -> 0x00c8 }
                    int r0 = r5.getProtocolType()     // Catch:{ all -> 0x00c8 }
                    if (r0 != 0) goto L_0x00ba
                    int r0 = r5.getRedirect()     // Catch:{ all -> 0x00c8 }
                    if (r0 != r3) goto L_0x00ba
                L_0x007b:
                    monitor-exit(r1)     // Catch:{ all -> 0x00c8 }
                    int r0 = r5.getProtocolType()
                    if (r0 != 0) goto L_0x0090
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.e()
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.f()
                L_0x0090:
                    com.tencent.android.tpush.service.cache.CacheManager.addOptStrategy(r5)
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.a()
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    if (r0 == 0) goto L_0x0066
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    com.tencent.android.tpush.horse.a$b r1 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.e r1 = r1.a()
                    java.nio.channels.SocketChannel r1 = r1.a()
                    r0.a(r1, r5)
                    goto L_0x0066
                L_0x00ba:
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00c8 }
                    com.tencent.android.tpush.horse.a$b r2 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00c8 }
                    int r2 = r2.d     // Catch:{ all -> 0x00c8 }
                    r0.a(r2)     // Catch:{ all -> 0x00c8 }
                    goto L_0x007b
                L_0x00c8:
                    r0 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x00c8 }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.a.b.AnonymousClass1.a(com.tencent.android.tpush.horse.data.StrategyItem):void");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:13:0x007b, code lost:
                com.tencent.android.tpush.service.cache.CacheManager.addOptStrategy(r4);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0082, code lost:
                if (r4.equals(r5) == false) goto L_0x00ae;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0084, code lost:
                r3.a.b.a();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0093, code lost:
                if (com.tencent.android.tpush.horse.a.c(r3.a.b) == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0095, code lost:
                com.tencent.android.tpush.horse.a.c(r3.a.b).a(r3.a.a().a(), r4);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b2, code lost:
                if (r4.getRedirect() != 0) goto L_0x00e3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b4, code lost:
                r3.a.b.a();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bf, code lost:
                if (r5.isFormatOk() == false) goto L_0x00cc;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c1, code lost:
                com.tencent.android.tpush.horse.a.a(r3.a.b).add(r5);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cc, code lost:
                com.tencent.android.tpush.horse.a.c(r3.a.b).a(r3.a.a().a(), r4);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e3, code lost:
                r3.a.b.a();
                com.tencent.android.tpush.horse.a.c(r3.a.b).a(r3.a.a().a(), r4);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(com.tencent.android.tpush.horse.data.StrategyItem r4, com.tencent.android.tpush.horse.data.StrategyItem r5) {
                /*
                    r3 = this;
                    java.lang.String r0 = "BaseTask"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "Horse run onRedirect(org:"
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.StringBuilder r1 = r1.append(r4)
                    java.lang.String r2 = ",redirect:"
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.StringBuilder r1 = r1.append(r5)
                    java.lang.String r2 = ")"
                    java.lang.StringBuilder r1 = r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.tencent.android.tpush.b.a.a(r0, r1)
                    java.lang.Object r1 = com.tencent.android.tpush.horse.a.a
                    monitor-enter(r1)
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    java.util.concurrent.LinkedBlockingQueue r0 = r0.b     // Catch:{ all -> 0x00ab }
                    r0.clear()     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    boolean r0 = r0.f     // Catch:{ all -> 0x00ab }
                    if (r0 == 0) goto L_0x0051
                    boolean r0 = r4.isRedirected()     // Catch:{ all -> 0x00ab }
                    if (r0 != 0) goto L_0x0051
                    java.lang.String r0 = "XGHorse"
                    java.lang.String r2 = ">> hasSuccessCallback && !strategyItem.isRedirected()"
                    com.tencent.android.tpush.b.a.c(r0, r2)     // Catch:{ all -> 0x00ab }
                    monitor-exit(r1)     // Catch:{ all -> 0x00ab }
                L_0x0050:
                    return
                L_0x0051:
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    r2 = 1
                    r0.f = r2     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a$b r2 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    int r2 = r2.d     // Catch:{ all -> 0x00ab }
                    r0.a(r2)     // Catch:{ all -> 0x00ab }
                    int r0 = r4.getProtocolType()     // Catch:{ all -> 0x00ab }
                    if (r0 != 0) goto L_0x007a
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    r0.e()     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this     // Catch:{ all -> 0x00ab }
                    r0.f()     // Catch:{ all -> 0x00ab }
                L_0x007a:
                    monitor-exit(r1)     // Catch:{ all -> 0x00ab }
                    com.tencent.android.tpush.service.cache.CacheManager.addOptStrategy(r4)
                    boolean r0 = r4.equals(r5)
                    if (r0 == 0) goto L_0x00ae
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.a()
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    if (r0 == 0) goto L_0x0050
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    com.tencent.android.tpush.horse.a$b r1 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.e r1 = r1.a()
                    java.nio.channels.SocketChannel r1 = r1.a()
                    r0.a(r1, r4)
                    goto L_0x0050
                L_0x00ab:
                    r0 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x00ab }
                    throw r0
                L_0x00ae:
                    int r0 = r4.getRedirect()
                    if (r0 != 0) goto L_0x00e3
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.a()
                    boolean r0 = r5.isFormatOk()
                    if (r0 == 0) goto L_0x00cc
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    java.util.concurrent.LinkedBlockingQueue r0 = r0.b
                    r0.add(r5)
                L_0x00cc:
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    com.tencent.android.tpush.horse.a$b r1 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.e r1 = r1.a()
                    java.nio.channels.SocketChannel r1 = r1.a()
                    r0.a(r1, r4)
                    goto L_0x0050
                L_0x00e3:
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    r0.a()
                    com.tencent.android.tpush.horse.a$b r0 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.a r0 = com.tencent.android.tpush.horse.a.this
                    com.tencent.android.tpush.horse.a$a r0 = r0.d
                    com.tencent.android.tpush.horse.a$b r1 = com.tencent.android.tpush.horse.a.b.this
                    com.tencent.android.tpush.horse.e r1 = r1.a()
                    java.nio.channels.SocketChannel r1 = r1.a()
                    r0.a(r1, r4)
                    goto L_0x0050
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.a.b.AnonymousClass1.a(com.tencent.android.tpush.horse.data.StrategyItem, com.tencent.android.tpush.horse.data.StrategyItem):void");
            }

            public void b(StrategyItem strategyItem) {
                com.tencent.android.tpush.b.a.i("BaseTask", "Horse onFail(" + strategyItem + ")");
                if (strategyItem.getRedirect() != 1) {
                    a.this.e.decrementAndGet();
                    if (a.this.d != null && !a.this.b()) {
                        a.this.d.a(strategyItem);
                    }
                } else if (!a.this.f) {
                    a.this.e.decrementAndGet();
                    if (a.this.d != null && !a.this.b()) {
                        a.this.d.a(strategyItem);
                    }
                }
            }
        };
        private e c;
        /* access modifiers changed from: private */
        public int d;
        private StrategyItem e;

        public b(int i) {
            this.d = i;
        }

        public e a() {
            return this.c;
        }

        public void run() {
            while (a.this.b.size() > 0) {
                try {
                    this.e = (StrategyItem) a.this.b.remove();
                    try {
                        TpnsRedirectReq tpnsRedirectReq = new TpnsRedirectReq();
                        tpnsRedirectReq.network = DeviceInfos.getNetworkType(com.tencent.android.tpush.service.b.f());
                        tpnsRedirectReq.op = i.k(com.tencent.android.tpush.service.b.f());
                        this.c = new e();
                        this.c.a(this.a);
                        com.tencent.android.tpush.b.a.c("HorseThread", " HorseThread:" + getClass().getSimpleName() + Thread.currentThread() + "current NetworkType:" + tpnsRedirectReq.network + ",strategyItem:" + this.e);
                        this.c.a(this.e);
                        this.c.a((JceStruct) tpnsRedirectReq);
                        this.c.b();
                    } catch (Throwable th) {
                        com.tencent.android.tpush.b.a.d("HorseThread", "HorseThread error", th);
                    }
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.d("HorseThread", "Can not get strateItem from strategyItems>>", e2);
                    try {
                        Thread.sleep(Config.BPLUS_DELAY_TIME);
                    } catch (Exception e3) {
                        com.tencent.android.tpush.b.a.i(Constants.HorseLogTag, e3.toString());
                    }
                }
            }
        }
    }

    public abstract void e();

    public abstract void f();

    public void a() {
        this.e.set(0);
    }

    public boolean b() {
        return this.e.get() > 0;
    }

    public boolean c() {
        return this.f;
    }

    public LinkedBlockingQueue<StrategyItem> d() {
        return this.b;
    }

    public void g() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.c("BaseTask", "startTask() with strategyItems size = " + this.b.size());
        }
        int i = 0;
        while (i < 1) {
            try {
                if (this.c.get(Integer.valueOf(i)) == null || ((b) this.c.get(Integer.valueOf(i))).getState() == State.TERMINATED) {
                    b bVar = new b(i);
                    this.c.put(Integer.valueOf(i), bVar);
                    bVar.start();
                    i++;
                } else {
                    if (!((b) this.c.get(Integer.valueOf(i))).isAlive()) {
                        ((b) this.c.get(Integer.valueOf(i))).start();
                    }
                    i++;
                }
            } catch (Exception e2) {
                try {
                    this.c.remove(Integer.valueOf(i));
                    com.tencent.android.tpush.b.a.d(Constants.HorseLogTag, "startTask", e2);
                } catch (OutOfMemoryError e3) {
                    com.tencent.android.tpush.b.a.i("BaseTask", "startTask() Exception = " + e3);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public synchronized void a(List<StrategyItem> list) {
        if (list != null) {
            if (1 <= list.size()) {
                this.b.clear();
                this.f = false;
                a();
                for (StrategyItem strategyItem : list) {
                    if (!this.b.contains(strategyItem)) {
                        this.b.add(strategyItem);
                        this.e.incrementAndGet();
                    }
                }
            }
        }
        if (this.d != null && !b()) {
            this.d.a(null);
        }
    }

    public void a(int i) {
        try {
            if (!this.c.isEmpty()) {
                for (Integer intValue : this.c.keySet()) {
                    int intValue2 = intValue.intValue();
                    if (intValue2 != i) {
                        b bVar = (b) this.c.get(Integer.valueOf(intValue2));
                        if (!(bVar == null || bVar.a() == null)) {
                            bVar.a().c();
                        }
                    }
                }
                b bVar2 = (b) this.c.remove(Integer.valueOf(i));
                if (bVar2 != null) {
                    bVar2.interrupt();
                }
            }
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("BaseTask", "stopOtherHorse", e2);
        }
    }

    public void a(C0061a aVar) {
        this.d = aVar;
    }
}
