package com.tencent.android.tpush.horse;

import com.baidu.mobstat.Config;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.exception.HorseIgnoreException;
import com.tencent.android.tpush.service.e.i;
import com.tencent.mid.sotrage.StorageInterface;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;

/* compiled from: ProGuard */
public class e {
    private static int f = 0;
    public StrategyItem a;
    private SocketChannel b;
    private ArrayBlockingQueue<a> c = new ArrayBlockingQueue<>(1);
    private long d;
    private long e;

    /* compiled from: ProGuard */
    public interface a {
        void a(StrategyItem strategyItem);

        void a(StrategyItem strategyItem, StrategyItem strategyItem2);

        void b(StrategyItem strategyItem);
    }

    public void a(StrategyItem strategyItem) {
        String strategyItem2;
        this.d = System.currentTimeMillis();
        this.a = strategyItem;
        if (XGPush4Msdk.isDebugServerInfoStrategyItem(b.f())) {
            try {
                String debugServerInfo = XGPush4Msdk.getDebugServerInfo(b.f());
                if (!i.b(debugServerInfo)) {
                    String[] split = debugServerInfo.split(StorageInterface.KEY_SPLITER);
                    if (split.length == 2 && split[0].length() > 4) {
                        this.a = new StrategyItem(split[0], Integer.valueOf(split[1]).intValue(), "", 80, 0, 0);
                        com.tencent.android.tpush.b.a.c("SocketClient", "use test StrategyItem:" + this.a.getServerIp() + Config.TRACE_TODAY_VISIT_SPLIT + this.a.getServerPort());
                    }
                }
            } catch (Exception e2) {
                com.tencent.android.tpush.b.a.d("SocketClient", " XGPush4Msdk.getDebugServerInfo", e2);
            }
        } else if (XGPushConfig.isForeiginPush(XGPushManager.getContext())) {
            String c2 = com.tencent.android.tpush.service.b.b.c();
            if (com.tencent.android.tpush.service.b.b.b(c2)) {
                this.a = new StrategyItem(c2, DefaultServer.a(), null, DefaultServer.a(), 0, 0);
                com.tencent.android.tpush.b.a.c("SocketClient", "use foreigin StrategyItem:" + this.a.getServerIp() + Config.TRACE_TODAY_VISIT_SPLIT + this.a.getServerPort());
            }
        } else if (com.tencent.android.tpush.service.a.a.a(XGPushManager.getContext()).D == 1 && f <= 3) {
            try {
                String a2 = com.tencent.android.tpush.service.b.b.a(XGPushManager.getContext()).a();
                if (com.tencent.android.tpush.service.b.b.b(a2)) {
                    this.a = new StrategyItem(a2, DefaultServer.a(), "", 80, 0, 0);
                    com.tencent.android.tpush.b.a.c("SocketClient", "use httpdns StrategyItem:" + this.a.getServerIp() + Config.TRACE_TODAY_VISIT_SPLIT + this.a.getServerPort());
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("SocketClient", "HttpDNS error", th);
                f++;
            }
        }
        try {
            com.tencent.android.tpush.b.a.d("SocketClient", "connect to " + this.a.getServerIp() + Config.TRACE_TODAY_VISIT_SPLIT + this.a.getServerPort());
            this.b = SocketChannel.open();
            this.b.configureBlocking(true);
            this.b.socket().connect(b(this.a), b.b());
            this.b.socket().setSoTimeout(b.c());
        } catch (Exception e3) {
            Exception exc = e3;
            if (com.tencent.android.tpush.service.a.a.a(XGPushManager.getContext()).D == 1) {
                f++;
            }
            com.tencent.android.tpush.b.a.d("SocketClient", "socket connect error", exc);
            d();
            if (strategyItem == null) {
                strategyItem2 = "null";
            } else {
                strategyItem2 = strategyItem.toString();
            }
            throw new HorseIgnoreException(strategyItem2, exc);
        }
    }

    private InetSocketAddress b(StrategyItem strategyItem) {
        if (strategyItem.getProtocolType() != 1 || !strategyItem.isWap()) {
            return new InetSocketAddress(strategyItem.getServerIp(), strategyItem.getServerPort());
        }
        return new InetSocketAddress(strategyItem.getProxyIp(), strategyItem.getProxyPort());
    }

    public SocketChannel a() {
        return this.b;
    }

    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13, types: [java.io.OutputStream, java.io.ByteArrayOutputStream, java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v7
  assigns: []
  uses: []
  mth insns count: 111
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
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.qq.taf.jce.JceStruct r7) {
        /*
            r6 = this;
            r2 = 10
            com.qq.taf.jce.JceOutputStream r0 = new com.qq.taf.jce.JceOutputStream
            r0.<init>()
            java.lang.String r1 = "UTF-8"
            r0.setServerEncoding(r1)
            r7.writeTo(r0)
            com.tencent.android.tpush.service.channel.b.h r3 = new com.tencent.android.tpush.service.channel.b.h
            r1 = 1
            r3.<init>(r1)
            r3.b(r2)
            r3.a(r2)
            java.nio.ByteBuffer r0 = r0.getByteBuffer()
            byte[] r0 = r0.array()
            r3.a(r0)
            r2 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ InnerException -> 0x011d, IOException -> 0x011a, UnexpectedDataException -> 0x00f0, Exception -> 0x0102, all -> 0x0112 }
            r1.<init>()     // Catch:{ InnerException -> 0x011d, IOException -> 0x011a, UnexpectedDataException -> 0x00f0, Exception -> 0x0102, all -> 0x0112 }
            com.tencent.android.tpush.horse.data.StrategyItem r0 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            int r0 = r0.getProtocolType()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            if (r0 != 0) goto L_0x0054
        L_0x0034:
            boolean r0 = r3.b()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            if (r0 != 0) goto L_0x00dc
            r3.a(r1)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            goto L_0x0034
        L_0x003e:
            r0 = move-exception
        L_0x003f:
            java.lang.String r2 = "SocketClient"
            java.lang.String r3 = "SocketClient -> send "
            com.tencent.android.tpush.b.a.d(r2, r3, r0)     // Catch:{ all -> 0x004f }
            r6.d()     // Catch:{ all -> 0x004f }
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r2 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException     // Catch:{ all -> 0x004f }
            r2.<init>(r0)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
        L_0x0050:
            com.tencent.android.tpush.common.b.a(r1)
            throw r0
        L_0x0054:
            com.tencent.android.tpush.service.channel.b.b r0 = new com.tencent.android.tpush.service.channel.b.b     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r2 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r2 = r2.getServerIp()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r4.<init>()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = "http://"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r5 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = r5.getServerIp()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = ":"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r5 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            int r5 = r5.getServerPort()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r4 = r4.toString()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r0.<init>(r2, r4)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r2 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            boolean r2 = r2.isWap()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            if (r2 == 0) goto L_0x00be
            java.lang.String r2 = "X-Online-Host"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r4.<init>()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r5 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = r5.getServerIp()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r5 = ":"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.horse.data.StrategyItem r5 = r6.a     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            int r5 = r5.getServerPort()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.lang.String r4 = r4.toString()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r0.a(r2, r4)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
        L_0x00be:
            r0.a(r3)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
        L_0x00c1:
            boolean r2 = r0.b()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            if (r2 != 0) goto L_0x00dc
            r0.a(r1)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            goto L_0x00c1
        L_0x00cb:
            r0 = move-exception
        L_0x00cc:
            java.lang.String r2 = "SocketClient"
            java.lang.String r3 = "SocketClient -> send "
            com.tencent.android.tpush.b.a.d(r2, r3, r0)     // Catch:{ all -> 0x004f }
            r6.d()     // Catch:{ all -> 0x004f }
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r2 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException     // Catch:{ all -> 0x004f }
            r2.<init>(r0)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ all -> 0x004f }
        L_0x00dc:
            java.nio.channels.SocketChannel r0 = r6.b     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.net.Socket r0 = r0.socket()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r1.writeTo(r0)     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            r1.flush()     // Catch:{ InnerException -> 0x003e, IOException -> 0x00cb, UnexpectedDataException -> 0x0118, Exception -> 0x0116 }
            com.tencent.android.tpush.common.b.a(r1)
        L_0x00ef:
            return
        L_0x00f0:
            r0 = move-exception
            r1 = r2
        L_0x00f2:
            java.lang.String r2 = "SocketClient"
            java.lang.String r3 = "SocketClient -> send "
            com.tencent.android.tpush.b.a.d(r2, r3, r0)     // Catch:{ all -> 0x004f }
            r6.d()     // Catch:{ all -> 0x004f }
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r2 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException     // Catch:{ all -> 0x004f }
            r2.<init>(r0)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ all -> 0x004f }
        L_0x0102:
            r0 = move-exception
            r1 = r2
        L_0x0104:
            java.lang.String r2 = "SocketClient"
            java.lang.String r3 = "SocketClient -> send "
            com.tencent.android.tpush.b.a.d(r2, r3, r0)     // Catch:{ all -> 0x004f }
            r6.d()     // Catch:{ all -> 0x004f }
            com.tencent.android.tpush.common.b.a(r1)
            goto L_0x00ef
        L_0x0112:
            r0 = move-exception
            r1 = r2
            goto L_0x0050
        L_0x0116:
            r0 = move-exception
            goto L_0x0104
        L_0x0118:
            r0 = move-exception
            goto L_0x00f2
        L_0x011a:
            r0 = move-exception
            r1 = r2
            goto L_0x00cc
        L_0x011d:
            r0 = move-exception
            r1 = r2
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.e.a(com.qq.taf.jce.JceStruct):void");
    }

    private void d() {
        try {
            a aVar = (a) this.c.remove();
            if (aVar != null) {
                aVar.b(this.a);
            }
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("SocketClient", "notifyFail", e2);
        }
        this.e = System.currentTimeMillis();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x014a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r8 = this;
            r1 = 0
            r0 = 0
            com.tencent.android.tpush.horse.data.StrategyItem r2 = r8.a
            if (r2 != 0) goto L_0x0011
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r0 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            java.lang.String r1 = "Recv() fail,because mStrategyItem is null"
            r0.<init>(r1)
            throw r0
        L_0x0011:
            com.tencent.android.tpush.horse.data.StrategyItem r2 = r8.a
            if (r2 == 0) goto L_0x00a7
            com.tencent.android.tpush.horse.data.StrategyItem r2 = r8.a
            int r2 = r2.getProtocolType()
            if (r2 != 0) goto L_0x00a7
            com.tencent.android.tpush.service.channel.b.g r2 = new com.tencent.android.tpush.service.channel.b.g
            r2.<init>()
            java.nio.channels.SocketChannel r3 = r8.b     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            java.net.Socket r3 = r3.socket()     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            r5.<init>(r4)     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
        L_0x0035:
            boolean r6 = r2.b()     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            if (r6 != 0) goto L_0x0056
            int r6 = r4.length     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            int r6 = r6 - r0
            int r0 = r3.read(r4, r0, r6)     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            r2.a(r5)     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
            goto L_0x0035
        L_0x0045:
            r0 = move-exception
            java.lang.String r1 = "SocketClient"
            java.lang.String r2 = "SocketClient -> recv "
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x0056:
            byte[] r0 = r2.k()     // Catch:{ UnexpectedDataException -> 0x0045, IOException -> 0x0067, InnerException -> 0x0078, IndexOutOfBoundsException -> 0x0089, Exception -> 0x009a }
        L_0x005a:
            if (r0 != 0) goto L_0x014a
            java.lang.String r0 = "XGService"
            java.lang.String r1 = ">> dataBuffer is null"
            com.tencent.android.tpush.b.a.i(r0, r1)
            r8.d()
        L_0x0066:
            return
        L_0x0067:
            r0 = move-exception
            java.lang.String r1 = "SocketClient"
            java.lang.String r2 = "SocketClient -> recv "
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x0078:
            r0 = move-exception
            java.lang.String r1 = "SocketClient"
            java.lang.String r2 = "SocketClient -> recv "
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x0089:
            r0 = move-exception
            java.lang.String r1 = "SocketClient"
            java.lang.String r2 = "SocketClient -> recv "
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x009a:
            r0 = move-exception
            java.lang.String r2 = "SocketClient"
            java.lang.String r3 = "SocketClient -> recv "
            com.tencent.android.tpush.b.a.d(r2, r3, r0)
            r8.d()
        L_0x00a5:
            r0 = r1
            goto L_0x005a
        L_0x00a7:
            com.tencent.android.tpush.service.channel.b.a r2 = new com.tencent.android.tpush.service.channel.b.a
            r2.<init>()
            java.nio.channels.SocketChannel r3 = r8.b     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            java.net.Socket r3 = r3.socket()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            r5.<init>(r4)     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
        L_0x00bf:
            boolean r6 = r2.b()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            if (r6 != 0) goto L_0x00e1
            int r6 = r4.length     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            int r6 = r6 - r0
            int r6 = r3.read(r4, r0, r6)     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            int r0 = r0 + r6
            r2.a(r5)     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            goto L_0x00bf
        L_0x00d0:
            r0 = move-exception
            java.lang.String r1 = "XGService"
            java.lang.String r2 = ""
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x00e1:
            if (r2 == 0) goto L_0x00fe
            java.util.ArrayList<com.tencent.android.tpush.service.channel.b.g> r0 = r2.i     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            if (r0 == 0) goto L_0x00fe
            java.util.ArrayList<com.tencent.android.tpush.service.channel.b.g> r0 = r2.i     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            int r0 = r0.size()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            if (r0 <= 0) goto L_0x00fe
            java.util.ArrayList<com.tencent.android.tpush.service.channel.b.g> r0 = r2.i     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            r2 = 0
            java.lang.Object r0 = r0.get(r2)     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            com.tencent.android.tpush.service.channel.b.g r0 = (com.tencent.android.tpush.service.channel.b.g) r0     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            byte[] r0 = r0.k()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            goto L_0x005a
        L_0x00fe:
            java.lang.String r0 = "XGService"
            java.lang.String r2 = ">> packet is null or packet.recvPackets is null"
            com.tencent.android.tpush.b.a.i(r0, r2)     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            r8.d()     // Catch:{ UnexpectedDataException -> 0x00d0, IOException -> 0x010a, InnerException -> 0x011b, IndexOutOfBoundsException -> 0x012c, Exception -> 0x013d }
            goto L_0x0066
        L_0x010a:
            r0 = move-exception
            java.lang.String r1 = "XGService"
            java.lang.String r2 = ""
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x011b:
            r0 = move-exception
            java.lang.String r1 = "XGService"
            java.lang.String r2 = ""
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x012c:
            r0 = move-exception
            java.lang.String r1 = "XGService"
            java.lang.String r2 = ""
            com.tencent.android.tpush.b.a.d(r1, r2, r0)
            r8.d()
            com.tencent.android.tpush.service.channel.exception.HorseIgnoreException r1 = new com.tencent.android.tpush.service.channel.exception.HorseIgnoreException
            r1.<init>(r0)
            throw r1
        L_0x013d:
            r0 = move-exception
            java.lang.String r2 = "XGService"
            java.lang.String r3 = ""
            com.tencent.android.tpush.b.a.d(r2, r3, r0)
            r8.d()
            goto L_0x00a5
        L_0x014a:
            com.qq.taf.jce.JceInputStream r2 = new com.qq.taf.jce.JceInputStream
            r2.<init>(r0)
            java.lang.String r0 = "UTF-8"
            r2.setServerEncoding(r0)
            com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp r3 = new com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp
            r3.<init>()
            r3.readFrom(r2)
            java.util.concurrent.ArrayBlockingQueue<com.tencent.android.tpush.horse.e$a> r0 = r8.c     // Catch:{ Exception -> 0x01a3 }
            java.lang.Object r0 = r0.remove()     // Catch:{ Exception -> 0x01a3 }
            com.tencent.android.tpush.horse.e$a r0 = (com.tencent.android.tpush.horse.e.a) r0     // Catch:{ Exception -> 0x01a3 }
            r7 = r0
        L_0x0165:
            if (r7 == 0) goto L_0x019b
            long r0 = r3.ip
            java.lang.String r1 = com.tencent.android.tpush.service.e.i.a(r0)
            int r2 = r3.port
            com.tencent.android.tpush.horse.data.StrategyItem r0 = new com.tencent.android.tpush.horse.data.StrategyItem
            com.tencent.android.tpush.horse.data.StrategyItem r3 = r8.a
            java.lang.String r3 = r3.getProxyIp()
            com.tencent.android.tpush.horse.data.StrategyItem r4 = r8.a
            int r4 = r4.getProxyPort()
            com.tencent.android.tpush.horse.data.StrategyItem r5 = r8.a
            int r5 = r5.getProtocolType()
            com.tencent.android.tpush.horse.data.StrategyItem r6 = r8.a
            int r6 = r6.getRedirect()
            r0.<init>(r1, r2, r3, r4, r5, r6)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0194
            if (r2 != 0) goto L_0x01ad
        L_0x0194:
            if (r7 == 0) goto L_0x019b
            com.tencent.android.tpush.horse.data.StrategyItem r0 = r8.a
            r7.a(r0)
        L_0x019b:
            long r0 = java.lang.System.currentTimeMillis()
            r8.e = r0
            goto L_0x0066
        L_0x01a3:
            r0 = move-exception
            java.lang.String r2 = "XGService"
            java.lang.String r4 = "callBacks.remove()"
            com.tencent.android.tpush.b.a.d(r2, r4, r0)
            r7 = r1
            goto L_0x0165
        L_0x01ad:
            r1 = 1
            r0.setRedirect(r1)
            if (r7 == 0) goto L_0x019b
            com.tencent.android.tpush.horse.data.StrategyItem r1 = r8.a
            r7.a(r1, r0)
            goto L_0x019b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.horse.e.b():void");
    }

    public void c() {
        try {
            this.b.close();
            this.c.clear();
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("SocketClient", "mSocketChannel.close()", e2);
        }
    }

    public void a(a aVar) {
        try {
            this.c.add(aVar);
        } catch (Exception e2) {
            com.tencent.android.tpush.b.a.d("SocketClient", "register", e2);
        }
    }
}
