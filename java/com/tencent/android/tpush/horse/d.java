package com.tencent.android.tpush.horse;

import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Handler;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.ReturnCode;
import com.tencent.android.tpush.horse.a.C0061a;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.e.i;
import java.nio.channels.SocketChannel;
import java.util.Timer;

/* compiled from: ProGuard */
public class d {
    static long a = 1;
    static long b = 0;
    public static int c = -1;
    private static long j = 0;
    private static long k = 0;
    /* access modifiers changed from: private */
    public static int m;
    /* access modifiers changed from: private */
    public final Object d;
    /* access modifiers changed from: private */
    public volatile int e;
    /* access modifiers changed from: private */
    public volatile boolean f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public a h;
    private b i;
    private Timer l;
    private Handler n;
    private C0061a o;
    /* access modifiers changed from: private */
    public C0061a p;

    /* compiled from: ProGuard */
    public interface a {
        void a(int i, String str);

        void a(SocketChannel socketChannel, StrategyItem strategyItem);
    }

    /* compiled from: ProGuard */
    public interface b {
    }

    /* compiled from: ProGuard */
    public static class c {
        public static d a = new d();
    }

    public void a(b bVar) {
        this.i = bVar;
    }

    public static d a() {
        return c.a;
    }

    private d() {
        this.d = new Object();
        this.e = 0;
        this.f = false;
        this.l = new Timer();
        this.n = null;
        this.o = new C0061a() {
            public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
                d.m = 0;
                if (g.i().b()) {
                    d.this.e = 1;
                }
                synchronized (d.this.d) {
                    if (d.this.e == 1) {
                        try {
                            d.this.d.wait();
                        } catch (Exception e) {
                            com.tencent.android.tpush.b.a.d(Constants.HorseLogTag, "lock.wait", e);
                        }
                    }
                }
                if (!socketChannel.isConnected() || g.i().c()) {
                    if (!socketChannel.isConnected() && !g.i().c()) {
                        d.this.a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create channel fail httpChannelCallback !");
                    }
                } else if (d.this.h == null) {
                    com.tencent.android.tpush.b.a.i(Constants.HorseLogTag, ">> mcreateSocket channelCallback is null ");
                } else if (strategyItem.isRedirected()) {
                    try {
                        socketChannel.close();
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d(Constants.HorseLogTag, "socketChannel.close()", e2);
                    }
                } else {
                    d.this.h.a(socketChannel, strategyItem);
                }
            }

            public void a(StrategyItem strategyItem) {
                if (!g.i().b() && !c.i().b() && d.this.e == 0) {
                    d.this.e = 2;
                    if (d.this.h != null) {
                        d.this.h.a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create http channel fail!");
                    }
                }
            }
        };
        this.p = new C0061a() {
            public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
                d.m = 0;
                if (socketChannel == null || strategyItem == null) {
                    d.this.a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create channel fail!");
                    return;
                }
                if (!socketChannel.isConnected()) {
                    d.this.a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create channel fail!");
                } else if (d.this.h != null) {
                    if (!strategyItem.isRedirected() || d.this.f) {
                        d.this.h.a(socketChannel, strategyItem);
                    } else {
                        try {
                            socketChannel.close();
                        } catch (Exception e) {
                            com.tencent.android.tpush.b.a.d(Constants.HorseLogTag, "socketChannel.close()", e);
                        }
                    }
                }
                if (d.this.f) {
                    d.this.f = false;
                }
                synchronized (d.this.d) {
                    d.this.e = 2;
                    d.this.d.notify();
                }
            }

            public void a(StrategyItem strategyItem) {
                if (d.this.f) {
                    d.this.f = false;
                    d.this.b();
                } else if (!g.i().b()) {
                    if (d.this.e == 0 && !c.i().b()) {
                        d.this.e = 2;
                        if (d.this.h != null) {
                            d.this.a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create channel fail!");
                        }
                    }
                    if (d.this.e == 1) {
                        synchronized (d.this.d) {
                            d.this.e = 2;
                            d.this.d.notify();
                        }
                    }
                } else {
                    com.tencent.android.tpush.b.a.e(Constants.HorseLogTag, ">> tcp has remain");
                }
            }
        };
        this.n = com.tencent.android.tpush.common.c.a().b();
    }

    public synchronized void a(a aVar) {
        this.e = 0;
        this.h = aVar;
        this.n.post(new Runnable() {
            /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0054=Splitter:B:17:0x0054, B:54:0x0135=Splitter:B:54:0x0135} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    r4 = 1
                    monitor-enter(r6)
                    boolean r0 = com.tencent.android.tpush.XGPushConfig.enableDebug     // Catch:{ all -> 0x0092 }
                    if (r0 == 0) goto L_0x000d
                    java.lang.String r0 = "XGHorse"
                    java.lang.String r1 = "Action ->  createOptimalSocketChannel run"
                    com.tencent.android.tpush.b.a.c(r0, r1)     // Catch:{ all -> 0x0092 }
                L_0x000d:
                    android.content.Context r0 = com.tencent.android.tpush.XGPushManager.getContext()     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.service.a.a r0 = com.tencent.android.tpush.service.a.a.a(r0)     // Catch:{ all -> 0x0092 }
                    int r0 = r0.D     // Catch:{ all -> 0x0092 }
                    if (r0 == r4) goto L_0x0023
                    android.content.Context r0 = com.tencent.android.tpush.service.b.f()     // Catch:{ all -> 0x0092 }
                    boolean r0 = com.tencent.android.tpush.XGPushConfig.isForeiginPush(r0)     // Catch:{ all -> 0x0092 }
                    if (r0 == 0) goto L_0x0095
                L_0x0023:
                    java.lang.String r0 = "XGHorse"
                    java.lang.String r1 = "Action ->  createOptimalSocketChannel run"
                    com.tencent.android.tpush.b.a.c(r0, r1)     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.e r0 = new com.tencent.android.tpush.horse.e     // Catch:{ Throwable -> 0x0070 }
                    r0.<init>()     // Catch:{ Throwable -> 0x0070 }
                    r1 = 0
                    r0.a(r1)     // Catch:{ Throwable -> 0x0070 }
                    java.nio.channels.SocketChannel r1 = r0.a()     // Catch:{ Throwable -> 0x0070 }
                    boolean r1 = r1.isConnected()     // Catch:{ Throwable -> 0x0070 }
                    if (r1 == 0) goto L_0x0056
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.d$a r1 = r1.h     // Catch:{ Throwable -> 0x0070 }
                    if (r1 == 0) goto L_0x0054
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.d$a r1 = r1.h     // Catch:{ Throwable -> 0x0070 }
                    java.nio.channels.SocketChannel r2 = r0.a()     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.data.StrategyItem r0 = r0.a     // Catch:{ Throwable -> 0x0070 }
                    r1.a(r2, r0)     // Catch:{ Throwable -> 0x0070 }
                L_0x0054:
                    monitor-exit(r6)     // Catch:{ all -> 0x0092 }
                L_0x0055:
                    return
                L_0x0056:
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.d$a r0 = r0.h     // Catch:{ Throwable -> 0x0070 }
                    if (r0 == 0) goto L_0x0054
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.horse.d$a r0 = r0.h     // Catch:{ Throwable -> 0x0070 }
                    com.tencent.android.tpush.common.ReturnCode r1 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED     // Catch:{ Throwable -> 0x0070 }
                    int r1 = r1.getType()     // Catch:{ Throwable -> 0x0070 }
                    java.lang.String r2 = "create tcp channel fail!"
                    r0.a(r1, r2)     // Catch:{ Throwable -> 0x0070 }
                    goto L_0x0054
                L_0x0070:
                    r0 = move-exception
                    java.lang.String r1 = "XGHorse"
                    java.lang.String r2 = "createOptimalSocketChannel error"
                    com.tencent.android.tpush.b.a.d(r1, r2, r0)     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d$a r0 = r0.h     // Catch:{ all -> 0x0092 }
                    if (r0 == 0) goto L_0x0054
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d$a r0 = r0.h     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.common.ReturnCode r1 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED     // Catch:{ all -> 0x0092 }
                    int r1 = r1.getType()     // Catch:{ all -> 0x0092 }
                    java.lang.String r2 = "create tcp channel fail!"
                    r0.a(r1, r2)     // Catch:{ all -> 0x0092 }
                    goto L_0x0054
                L_0x0092:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch:{ all -> 0x0092 }
                    throw r0
                L_0x0095:
                    com.tencent.android.tpush.horse.g r0 = com.tencent.android.tpush.horse.g.i()     // Catch:{ all -> 0x0092 }
                    boolean r0 = r0.b()     // Catch:{ all -> 0x0092 }
                    if (r0 != 0) goto L_0x01ac
                    com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()     // Catch:{ all -> 0x0092 }
                    boolean r0 = r0.b()     // Catch:{ all -> 0x0092 }
                    if (r0 != 0) goto L_0x01ac
                    java.lang.String r0 = ""
                    android.content.Context r1 = com.tencent.android.tpush.service.b.f()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r0 = com.tencent.android.tpush.service.e.i.l(r1)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    android.content.Context r1 = com.tencent.android.tpush.service.b.f()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.data.OptStrategyList r1 = com.tencent.android.tpush.service.cache.CacheManager.getOptStrategyList(r1, r0)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.data.StrategyItem r2 = r1.getOptStrategyItem()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    int r3 = r2.getProtocolType()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r3 == r4) goto L_0x00d1
                    if (r2 == 0) goto L_0x00d1
                    long r4 = r1.getTimestamp()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    boolean r1 = com.tencent.android.tpush.horse.b.a(r4)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r1 == 0) goto L_0x00d9
                L_0x00d1:
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.a(r0)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    monitor-exit(r6)     // Catch:{ all -> 0x0092 }
                    goto L_0x0055
                L_0x00d9:
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    long r4 = java.lang.System.currentTimeMillis()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.g = r4     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    int r1 = r2.getProtocolType()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r1 != 0) goto L_0x0138
                    boolean r1 = com.tencent.android.tpush.XGPushConfig.enableDebug     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r1 == 0) goto L_0x0108
                    java.lang.String r1 = "XGHorse"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r3.<init>()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r4 = "Using the optStrategyItem"
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r4 = r2.toString()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r3 = r3.toString()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.b.a.c(r1, r3)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                L_0x0108:
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r3 = 1
                    r1.f = r3     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.<init>()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r3 = 0
                    r2.setRedirect(r3)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.add(r2)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.g r2 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.d r3 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.a$a r3 = r3.p     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r2.a(r3)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.g r2 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r2.a(r1)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.g r1 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.g()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                L_0x0135:
                    monitor-exit(r6)     // Catch:{ all -> 0x0092 }
                    goto L_0x0055
                L_0x0138:
                    boolean r1 = com.tencent.android.tpush.XGPushConfig.enableDebug     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r1 == 0) goto L_0x0158
                    java.lang.String r1 = "XGHorse"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r3.<init>()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r4 = "Using Http chanel http:"
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r4 = r2.toString()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.lang.String r3 = r3.toString()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.b.a.c(r1, r3)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                L_0x0158:
                    com.tencent.android.tpush.horse.e r1 = new com.tencent.android.tpush.horse.e     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.<init>()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r1.a(r2)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.nio.channels.SocketChannel r3 = r1.a()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    boolean r3 = r3.isConnected()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r3 == 0) goto L_0x0135
                    com.tencent.android.tpush.horse.d r3 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.d$a r3 = r3.h     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    if (r3 == 0) goto L_0x0135
                    com.tencent.android.tpush.horse.d r3 = com.tencent.android.tpush.horse.d.this     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    com.tencent.android.tpush.horse.d$a r3 = r3.h     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    java.nio.channels.SocketChannel r1 = r1.a()     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    r3.a(r1, r2)     // Catch:{ NullReturnException -> 0x0182, HorseIgnoreException -> 0x0190, Exception -> 0x019e }
                    monitor-exit(r6)     // Catch:{ all -> 0x0092 }
                    goto L_0x0055
                L_0x0182:
                    r1 = move-exception
                    java.lang.String r1 = "XGHorse"
                    java.lang.String r2 = "no network Strategy, begin horse running"
                    com.tencent.android.tpush.b.a.c(r1, r2)     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d r1 = com.tencent.android.tpush.horse.d.this     // Catch:{ all -> 0x0092 }
                    r1.a(r0)     // Catch:{ all -> 0x0092 }
                    goto L_0x0135
                L_0x0190:
                    r0 = move-exception
                    java.lang.String r0 = "XGHorse"
                    java.lang.String r1 = "create OptimalSocket  Channel error"
                    com.tencent.android.tpush.b.a.c(r0, r1)     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ all -> 0x0092 }
                    r0.b()     // Catch:{ all -> 0x0092 }
                    goto L_0x0135
                L_0x019e:
                    r0 = move-exception
                    java.lang.String r1 = "XGHorse"
                    java.lang.String r2 = "createOptimalSocketChannel error"
                    com.tencent.android.tpush.b.a.d(r1, r2, r0)     // Catch:{ all -> 0x0092 }
                    com.tencent.android.tpush.horse.d r0 = com.tencent.android.tpush.horse.d.this     // Catch:{ all -> 0x0092 }
                    r0.b()     // Catch:{ all -> 0x0092 }
                    goto L_0x0135
                L_0x01ac:
                    java.lang.String r0 = "XGHorse"
                    java.lang.String r1 = ">> horse task running"
                    com.tencent.android.tpush.b.a.c(r0, r1)     // Catch:{ all -> 0x0092 }
                    goto L_0x0135
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.d.AnonymousClass1.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x02b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8) {
        /*
            r7 = this;
            r2 = 0
            if (r8 == 0) goto L_0x001d
            com.tencent.android.tpush.stat.a.d r0 = com.tencent.android.tpush.stat.a.c.b()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "startHorseTask key:"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.d(r1)
        L_0x001d:
            android.content.Context r0 = com.tencent.android.tpush.service.b.f()
            boolean r0 = com.tencent.android.tpush.service.e.a.d(r0)
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = "OptimalLinkSelector"
            java.lang.String r1 = "Network can't reachable"
            com.tencent.android.tpush.b.a.i(r0, r1)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_UNREACHABLE
            int r0 = r0.getType()
            java.lang.String r1 = "network can't reachable!"
            r7.a(r0, r1)
        L_0x0039:
            return
        L_0x003a:
            com.tencent.android.tpush.horse.g r0 = com.tencent.android.tpush.horse.g.i()
            boolean r0 = r0.b()
            if (r0 != 0) goto L_0x004e
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x0056
        L_0x004e:
            java.lang.String r0 = "OptimalLinkSelector"
            java.lang.String r1 = "Horse task running"
            com.tencent.android.tpush.b.a.a(r0, r1)
            goto L_0x0039
        L_0x0056:
            boolean r0 = com.tencent.android.tpush.XGPushConfig.enableDebug
            if (r0 == 0) goto L_0x0072
            java.lang.String r0 = "OptimalLinkSelector"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Action -> startHorseTask with key = "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.tencent.android.tpush.b.a.c(r0, r1)
        L_0x0072:
            android.content.Context r0 = com.tencent.android.tpush.service.b.f()
            com.tencent.android.tpush.service.cache.CacheManager.removeOptStrategyList(r0, r8)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0163 }
            r0.<init>()     // Catch:{ Exception -> 0x0163 }
            java.lang.String r1 = ""
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            com.tencent.android.tpush.common.MobileType r1 = com.tencent.android.tpush.common.MobileType.CHINAMOBILE     // Catch:{ Exception -> 0x0163 }
            byte r1 = r1.getType()     // Catch:{ Exception -> 0x0163 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0163 }
            boolean r0 = r8.equals(r0)     // Catch:{ Exception -> 0x0163 }
            if (r0 != 0) goto L_0x00d6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0163 }
            r0.<init>()     // Catch:{ Exception -> 0x0163 }
            java.lang.String r1 = ""
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            com.tencent.android.tpush.common.MobileType r1 = com.tencent.android.tpush.common.MobileType.TELCOM     // Catch:{ Exception -> 0x0163 }
            byte r1 = r1.getType()     // Catch:{ Exception -> 0x0163 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0163 }
            boolean r0 = r8.equals(r0)     // Catch:{ Exception -> 0x0163 }
            if (r0 != 0) goto L_0x00d6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0163 }
            r0.<init>()     // Catch:{ Exception -> 0x0163 }
            java.lang.String r1 = ""
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            com.tencent.android.tpush.common.MobileType r1 = com.tencent.android.tpush.common.MobileType.UNICOM     // Catch:{ Exception -> 0x0163 }
            byte r1 = r1.getType()     // Catch:{ Exception -> 0x0163 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x0163 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0163 }
            boolean r0 = r8.equals(r0)     // Catch:{ Exception -> 0x0163 }
            if (r0 == 0) goto L_0x015d
        L_0x00d6:
            android.content.Context r0 = com.tencent.android.tpush.service.b.f()     // Catch:{ Exception -> 0x0163 }
            java.util.ArrayList r0 = com.tencent.android.tpush.service.cache.CacheManager.getServerItems(r0, r8)     // Catch:{ Exception -> 0x0163 }
        L_0x00de:
            if (r0 != 0) goto L_0x00e5
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x00e5:
            java.util.ArrayList r1 = com.tencent.android.tpush.horse.DefaultServer.b()
            r0.addAll(r1)
            android.content.Context r1 = com.tencent.android.tpush.service.b.f()
            int r1 = com.tencent.android.tpush.horse.Tools.getChannelType(r1)
            java.lang.String r3 = "OptimalLinkSelector"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Tools.getChannelType = "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.c(r3, r4)
            switch(r1) {
                case 2: goto L_0x01a6;
                case 3: goto L_0x021e;
                case 4: goto L_0x02b5;
                default: goto L_0x010f;
            }
        L_0x010f:
            java.lang.String r1 = "OptimalLinkSelector"
            java.lang.String r2 = "XINGE Use TcpTask"
            com.tencent.android.tpush.b.a.c(r1, r2)     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            java.util.List r0 = com.tencent.android.tpush.horse.f.a(r0, r8)     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            com.tencent.android.tpush.horse.g r1 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            com.tencent.android.tpush.horse.a$a r2 = r7.p     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            r1.a(r2)     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            com.tencent.android.tpush.horse.g r1 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            r1.a(r0)     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            com.tencent.android.tpush.horse.g r0 = com.tencent.android.tpush.horse.g.i()     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            r0.g()     // Catch:{ NullReturnException -> 0x0133, Exception -> 0x0350 }
            goto L_0x0039
        L_0x0133:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> Can not get strategyItems(create tcp channel fail!) >> "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create tcp channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x015d:
            java.util.ArrayList r0 = com.tencent.android.tpush.horse.DefaultServer.a(r8)     // Catch:{ Exception -> 0x0163 }
            goto L_0x00de
        L_0x0163:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ">> Can not get local serverItems : "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            java.util.ArrayList r0 = com.tencent.android.tpush.horse.DefaultServer.a(r8)     // Catch:{ Exception -> 0x0186 }
            goto L_0x00de
        L_0x0186:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ">> Can not get default serverItems : "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            r0 = r2
            goto L_0x00de
        L_0x01a6:
            java.lang.String r1 = "OptimalLinkSelector"
            java.lang.String r2 = "XINGE Use HTTP TASK"
            com.tencent.android.tpush.b.a.c(r1, r2)     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            java.util.List r0 = com.tencent.android.tpush.horse.f.b(r0, r8)     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            com.tencent.android.tpush.horse.c r1 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            com.tencent.android.tpush.horse.a$a r2 = r7.o     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            r1.a(r2)     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            com.tencent.android.tpush.horse.c r1 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            r1.a(r0)     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            r0.g()     // Catch:{ NullReturnException -> 0x01ca, Exception -> 0x01f4 }
            goto L_0x0039
        L_0x01ca:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> Can not get strategyItems(create http channel fail!)>>"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create http channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x01f4:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> (create http channel fail!) >> "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create http channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x021e:
            java.lang.String r1 = "OptimalLinkSelector"
            java.lang.String r2 = "XINGE Use HTTP_WAP"
            com.tencent.android.tpush.b.a.c(r1, r2)     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            java.util.List r0 = com.tencent.android.tpush.horse.f.b(r0, r8)     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            r1.<init>()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
        L_0x0232:
            boolean r0 = r2.hasNext()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            if (r0 == 0) goto L_0x0272
            java.lang.Object r0 = r2.next()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            com.tencent.android.tpush.horse.data.StrategyItem r0 = (com.tencent.android.tpush.horse.data.StrategyItem) r0     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            boolean r3 = r0.isWap()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            if (r3 == 0) goto L_0x0232
            r1.add(r0)     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            goto L_0x0232
        L_0x0248:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> Can not get strategyItems(create wap channel fail!)>>"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create wap channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x0272:
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            com.tencent.android.tpush.horse.a$a r2 = r7.o     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            r0.a(r2)     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            r0.a(r1)     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            r0.g()     // Catch:{ NullReturnException -> 0x0248, Exception -> 0x028b }
            goto L_0x0039
        L_0x028b:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> (create wap channel fail!) >> "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create wap channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x02b5:
            java.lang.String r1 = "OptimalLinkSelector"
            java.lang.String r3 = "XINGE Use TCP_OR_HTTP"
            com.tencent.android.tpush.b.a.c(r1, r3)     // Catch:{ NullReturnException -> 0x02f7, Exception -> 0x0323 }
            java.util.List r1 = com.tencent.android.tpush.horse.f.a(r0, r8)     // Catch:{ NullReturnException -> 0x02f7, Exception -> 0x0323 }
            java.util.List r0 = com.tencent.android.tpush.horse.f.b(r0, r8)     // Catch:{ NullReturnException -> 0x037f, Exception -> 0x037a }
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x02c7:
            com.tencent.android.tpush.horse.g r2 = com.tencent.android.tpush.horse.g.i()
            com.tencent.android.tpush.horse.a$a r3 = r7.p
            r2.a(r3)
            com.tencent.android.tpush.horse.g r2 = com.tencent.android.tpush.horse.g.i()
            r2.a(r0)
            com.tencent.android.tpush.horse.g r0 = com.tencent.android.tpush.horse.g.i()
            r0.g()
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()
            com.tencent.android.tpush.horse.a$a r2 = r7.o
            r0.a(r2)
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()
            r0.a(r1)
            com.tencent.android.tpush.horse.c r0 = com.tencent.android.tpush.horse.c.i()
            r0.g()
            goto L_0x0039
        L_0x02f7:
            r0 = move-exception
            r1 = r0
            r0 = r2
        L_0x02fa:
            java.lang.String r3 = "XGHorse"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ">> Can not get strategyItems(create default channel fail!)>>"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.android.tpush.b.a.i(r3, r1)
            com.tencent.android.tpush.common.ReturnCode r1 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r1 = r1.getType()
            java.lang.String r3 = "create default channel fail!"
            r7.a(r1, r3)
            r1 = r2
            goto L_0x02c7
        L_0x0323:
            r0 = move-exception
            r1 = r0
            r0 = r2
        L_0x0326:
            java.lang.String r3 = "XGHorse"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ">> (create default channel fail!) >> "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.String r1 = r1.toString()
            com.tencent.android.tpush.b.a.i(r3, r1)
            com.tencent.android.tpush.common.ReturnCode r1 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r1 = r1.getType()
            java.lang.String r3 = "create default channel fail!"
            r7.a(r1, r3)
            r1 = r2
            goto L_0x02c7
        L_0x0350:
            r0 = move-exception
            java.lang.String r1 = "XGHorse"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ">> (create tcp channel fail!) >> "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            com.tencent.android.tpush.b.a.i(r1, r0)
            com.tencent.android.tpush.common.ReturnCode r0 = com.tencent.android.tpush.common.ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED
            int r0 = r0.getType()
            java.lang.String r1 = "create tcp channel fail!"
            r7.a(r0, r1)
            goto L_0x0039
        L_0x037a:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
            goto L_0x0326
        L_0x037f:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
            goto L_0x02fa
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.d.a(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str) {
        if (this.h != null) {
            this.h.a(i2, str);
        }
    }

    public synchronized void a(Intent intent) {
        try {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo != null) {
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c("OptimalLinkSelector", "Connection state changed to - " + networkInfo.toString());
                }
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                int type = networkInfo.getType();
                if (booleanExtra) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.c("OptimalLinkSelector", "DisConnected with network type " + networkInfo.getTypeName());
                    }
                    com.tencent.android.tpush.service.b.c(com.tencent.android.tpush.service.b.f());
                } else if (State.CONNECTED == networkInfo.getState()) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.c("OptimalLinkSelector", "Connected with network type " + networkInfo.getTypeName());
                    }
                    c = type;
                    com.tencent.android.tpush.service.b.a(com.tencent.android.tpush.service.b.f(), 2000);
                } else if (State.DISCONNECTED == networkInfo.getState()) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.b.a.c("OptimalLinkSelector", "NetworkInfo.State.DISCONNECTED with network type = " + networkInfo.getTypeName());
                    }
                    if (c == -1 || c == type) {
                        com.tencent.android.tpush.service.b.c(com.tencent.android.tpush.service.b.f());
                    }
                } else if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c("OptimalLinkSelector", "other network state - " + networkInfo.getState() + ". Do nothing.");
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.a("OptimalLinkSelector", "onNetworkChanged", th);
        }
        return;
    }

    public void b() {
        m++;
        if (m < com.tencent.android.tpush.service.a.a.a(com.tencent.android.tpush.service.b.f()).t) {
            a().a(i.l(com.tencent.android.tpush.service.b.f()));
        } else {
            a(ReturnCode.CODE_NETWORK_CREATE_OPTIOMAL_SC_FAILED.getType(), "create socket err");
        }
    }
}
