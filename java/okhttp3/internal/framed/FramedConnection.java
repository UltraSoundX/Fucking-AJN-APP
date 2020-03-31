package okhttp3.internal.framed;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.framed.FrameReader.Handler;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class FramedConnection implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    /* access modifiers changed from: private */
    public static final ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp FramedConnection", true));
    long bytesLeftInWriteWindow;
    final boolean client;
    /* access modifiers changed from: private */
    public final Set<Integer> currentPushRequests;
    final FrameWriter frameWriter;
    /* access modifiers changed from: private */
    public final String hostname;
    private long idleStartTimeNs;
    /* access modifiers changed from: private */
    public int lastGoodStreamId;
    /* access modifiers changed from: private */
    public final Listener listener;
    private int nextPingId;
    /* access modifiers changed from: private */
    public int nextStreamId;
    Settings okHttpSettings;
    final Settings peerSettings;
    private Map<Integer, Ping> pings;
    final Protocol protocol;
    private final ExecutorService pushExecutor;
    /* access modifiers changed from: private */
    public final PushObserver pushObserver;
    final Reader readerRunnable;
    /* access modifiers changed from: private */
    public boolean receivedInitialPeerSettings;
    /* access modifiers changed from: private */
    public boolean shutdown;
    final Socket socket;
    /* access modifiers changed from: private */
    public final Map<Integer, FramedStream> streams;
    long unacknowledgedBytesRead;
    final Variant variant;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean client;
        /* access modifiers changed from: private */
        public String hostname;
        /* access modifiers changed from: private */
        public Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        /* access modifiers changed from: private */
        public Protocol protocol = Protocol.SPDY_3;
        /* access modifiers changed from: private */
        public PushObserver pushObserver = PushObserver.CANCEL;
        /* access modifiers changed from: private */
        public BufferedSink sink;
        /* access modifiers changed from: private */
        public Socket socket;
        /* access modifiers changed from: private */
        public BufferedSource source;

        public Builder(boolean z) throws IOException {
            this.client = z;
        }

        public Builder socket(Socket socket2) throws IOException {
            return socket(socket2, ((InetSocketAddress) socket2.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket2)), Okio.buffer(Okio.sink(socket2)));
        }

        public Builder socket(Socket socket2, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.socket = socket2;
            this.hostname = str;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }

        public Builder listener(Listener listener2) {
            this.listener = listener2;
            return this;
        }

        public Builder protocol(Protocol protocol2) {
            this.protocol = protocol2;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver2) {
            this.pushObserver = pushObserver2;
            return this;
        }

        public FramedConnection build() throws IOException {
            return new FramedConnection(this);
        }
    }

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() {
            public void onStream(FramedStream framedStream) throws IOException {
                framedStream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public abstract void onStream(FramedStream framedStream) throws IOException;

        public void onSettings(FramedConnection framedConnection) {
        }
    }

    class Reader extends NamedRunnable implements Handler {
        final FrameReader frameReader;

        private Reader(FrameReader frameReader2) {
            super("OkHttp %s", FramedConnection.this.hostname);
            this.frameReader = frameReader2;
        }

        /* access modifiers changed from: protected */
        public void execute() {
            ErrorCode errorCode;
            Throwable th;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            try {
                if (!FramedConnection.this.client) {
                    this.frameReader.readConnectionPreface();
                }
                do {
                } while (this.frameReader.nextFrame(this));
                try {
                    FramedConnection.this.close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
                Util.closeQuietly((Closeable) this.frameReader);
            } catch (IOException e2) {
                errorCode = ErrorCode.PROTOCOL_ERROR;
                try {
                    FramedConnection.this.close(errorCode, ErrorCode.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                Util.closeQuietly((Closeable) this.frameReader);
            } catch (Throwable th2) {
                th = th2;
                FramedConnection.this.close(errorCode, errorCode3);
                Util.closeQuietly((Closeable) this.frameReader);
                throw th;
            }
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (FramedConnection.this.pushedStream(i)) {
                FramedConnection.this.pushDataLater(i, bufferedSource, i2, z);
                return;
            }
            FramedStream stream = FramedConnection.this.getStream(i);
            if (stream == null) {
                FramedConnection.this.writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                bufferedSource.skip((long) i2);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0092, code lost:
            if (r14.failIfStreamPresent() == false) goto L_0x00a0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0094, code lost:
            r0.closeLater(okhttp3.internal.framed.ErrorCode.PROTOCOL_ERROR);
            r8.this$0.removeStream(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a0, code lost:
            r0.receiveHeaders(r13, r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
            if (r10 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a5, code lost:
            r0.receiveFin();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r9, boolean r10, int r11, int r12, java.util.List<okhttp3.internal.framed.Header> r13, okhttp3.internal.framed.HeadersMode r14) {
            /*
                r8 = this;
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this
                boolean r0 = r0.pushedStream(r11)
                if (r0 == 0) goto L_0x000e
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this
                r0.pushHeadersLater(r11, r13, r10)
            L_0x000d:
                return
            L_0x000e:
                okhttp3.internal.framed.FramedConnection r6 = okhttp3.internal.framed.FramedConnection.this
                monitor-enter(r6)
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                boolean r0 = r0.shutdown     // Catch:{ all -> 0x001b }
                if (r0 == 0) goto L_0x001e
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                goto L_0x000d
            L_0x001b:
                r0 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                throw r0
            L_0x001e:
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.FramedStream r0 = r0.getStream(r11)     // Catch:{ all -> 0x001b }
                if (r0 != 0) goto L_0x008d
                boolean r0 = r14.failIfStreamAbsent()     // Catch:{ all -> 0x001b }
                if (r0 == 0) goto L_0x0035
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.ErrorCode r1 = okhttp3.internal.framed.ErrorCode.INVALID_STREAM     // Catch:{ all -> 0x001b }
                r0.writeSynResetLater(r11, r1)     // Catch:{ all -> 0x001b }
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                goto L_0x000d
            L_0x0035:
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                int r0 = r0.lastGoodStreamId     // Catch:{ all -> 0x001b }
                if (r11 > r0) goto L_0x003f
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                goto L_0x000d
            L_0x003f:
                int r0 = r11 % 2
                okhttp3.internal.framed.FramedConnection r1 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                int r1 = r1.nextStreamId     // Catch:{ all -> 0x001b }
                int r1 = r1 % 2
                if (r0 != r1) goto L_0x004d
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                goto L_0x000d
            L_0x004d:
                okhttp3.internal.framed.FramedStream r0 = new okhttp3.internal.framed.FramedStream     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.FramedConnection r2 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                r1 = r11
                r3 = r9
                r4 = r10
                r5 = r13
                r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.FramedConnection r1 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                r1.lastGoodStreamId = r11     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.FramedConnection r1 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                java.util.Map r1 = r1.streams     // Catch:{ all -> 0x001b }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x001b }
                r1.put(r2, r0)     // Catch:{ all -> 0x001b }
                java.util.concurrent.ExecutorService r1 = okhttp3.internal.framed.FramedConnection.executor     // Catch:{ all -> 0x001b }
                okhttp3.internal.framed.FramedConnection$Reader$1 r2 = new okhttp3.internal.framed.FramedConnection$Reader$1     // Catch:{ all -> 0x001b }
                java.lang.String r3 = "OkHttp %s stream %d"
                r4 = 2
                java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x001b }
                r5 = 0
                okhttp3.internal.framed.FramedConnection r7 = okhttp3.internal.framed.FramedConnection.this     // Catch:{ all -> 0x001b }
                java.lang.String r7 = r7.hostname     // Catch:{ all -> 0x001b }
                r4[r5] = r7     // Catch:{ all -> 0x001b }
                r5 = 1
                java.lang.Integer r7 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x001b }
                r4[r5] = r7     // Catch:{ all -> 0x001b }
                r2.<init>(r3, r4, r0)     // Catch:{ all -> 0x001b }
                r1.execute(r2)     // Catch:{ all -> 0x001b }
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                goto L_0x000d
            L_0x008d:
                monitor-exit(r6)     // Catch:{ all -> 0x001b }
                boolean r1 = r14.failIfStreamPresent()
                if (r1 == 0) goto L_0x00a0
                okhttp3.internal.framed.ErrorCode r1 = okhttp3.internal.framed.ErrorCode.PROTOCOL_ERROR
                r0.closeLater(r1)
                okhttp3.internal.framed.FramedConnection r0 = okhttp3.internal.framed.FramedConnection.this
                r0.removeStream(r11)
                goto L_0x000d
            L_0x00a0:
                r0.receiveHeaders(r13, r14)
                if (r10 == 0) goto L_0x000d
                r0.receiveFin()
                goto L_0x000d
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.framed.FramedConnection.Reader.headers(boolean, boolean, int, int, java.util.List, okhttp3.internal.framed.HeadersMode):void");
        }

        public void rstStream(int i, ErrorCode errorCode) {
            if (FramedConnection.this.pushedStream(i)) {
                FramedConnection.this.pushResetLater(i, errorCode);
                return;
            }
            FramedStream removeStream = FramedConnection.this.removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        public void settings(boolean z, Settings settings) {
            FramedStream[] framedStreamArr;
            long j;
            synchronized (FramedConnection.this) {
                int initialWindowSize = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                if (z) {
                    FramedConnection.this.peerSettings.clear();
                }
                FramedConnection.this.peerSettings.merge(settings);
                if (FramedConnection.this.getProtocol() == Protocol.HTTP_2) {
                    ackSettingsLater(settings);
                }
                int initialWindowSize2 = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    framedStreamArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (initialWindowSize2 - initialWindowSize);
                    if (!FramedConnection.this.receivedInitialPeerSettings) {
                        FramedConnection.this.addBytesToWriteWindow(j2);
                        FramedConnection.this.receivedInitialPeerSettings = true;
                    }
                    if (!FramedConnection.this.streams.isEmpty()) {
                        j = j2;
                        framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                    } else {
                        j = j2;
                        framedStreamArr = null;
                    }
                }
                FramedConnection.executor.execute(new NamedRunnable("OkHttp %s settings", FramedConnection.this.hostname) {
                    public void execute() {
                        FramedConnection.this.listener.onSettings(FramedConnection.this);
                    }
                });
            }
            if (framedStreamArr != null && j != 0) {
                for (FramedStream framedStream : framedStreamArr) {
                    synchronized (framedStream) {
                        framedStream.addBytesToWriteWindow(j);
                    }
                }
            }
        }

        private void ackSettingsLater(final Settings settings) {
            FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{FramedConnection.this.hostname}) {
                public void execute() {
                    try {
                        FramedConnection.this.frameWriter.ackSettings(settings);
                    } catch (IOException e) {
                    }
                }
            });
        }

        public void ackSettings() {
        }

        public void ping(boolean z, int i, int i2) {
            if (z) {
                Ping access$2400 = FramedConnection.this.removePing(i);
                if (access$2400 != null) {
                    access$2400.receive();
                    return;
                }
                return;
            }
            FramedConnection.this.writePingLater(true, i, i2, null);
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            FramedStream[] framedStreamArr;
            if (byteString.size() > 0) {
            }
            synchronized (FramedConnection.this) {
                framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                FramedConnection.this.shutdown = true;
            }
            for (FramedStream framedStream : framedStreamArr) {
                if (framedStream.getId() > i && framedStream.isLocallyInitiated()) {
                    framedStream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    FramedConnection.this.removeStream(framedStream.getId());
                }
            }
        }

        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (FramedConnection.this) {
                    FramedConnection.this.bytesLeftInWriteWindow += j;
                    FramedConnection.this.notifyAll();
                }
                return;
            }
            FramedStream stream = FramedConnection.this.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        public void pushPromise(int i, int i2, List<Header> list) {
            FramedConnection.this.pushRequestLater(i2, list);
        }

        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }
    }

    static {
        boolean z;
        if (!FramedConnection.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private FramedConnection(Builder builder) throws IOException {
        int i = 2;
        this.streams = new HashMap();
        this.idleStartTimeNs = System.nanoTime();
        this.unacknowledgedBytesRead = 0;
        this.okHttpSettings = new Settings();
        this.peerSettings = new Settings();
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet();
        this.protocol = builder.protocol;
        this.pushObserver = builder.pushObserver;
        this.client = builder.client;
        this.listener = builder.listener;
        this.nextStreamId = builder.client ? 1 : 2;
        if (builder.client && this.protocol == Protocol.HTTP_2) {
            this.nextStreamId += 2;
        }
        if (builder.client) {
            i = 1;
        }
        this.nextPingId = i;
        if (builder.client) {
            this.okHttpSettings.set(7, 0, 16777216);
        }
        this.hostname = builder.hostname;
        if (this.protocol == Protocol.HTTP_2) {
            this.variant = new Http2();
            this.pushExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", this.hostname), true));
            this.peerSettings.set(7, 0, 65535);
            this.peerSettings.set(5, 0, 16384);
        } else if (this.protocol == Protocol.SPDY_3) {
            this.variant = new Spdy3();
            this.pushExecutor = null;
        } else {
            throw new AssertionError(this.protocol);
        }
        this.bytesLeftInWriteWindow = (long) this.peerSettings.getInitialWindowSize(65536);
        this.socket = builder.socket;
        this.frameWriter = this.variant.newWriter(builder.sink, this.client);
        this.readerRunnable = new Reader(this.variant.newReader(builder.source, this.client));
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    /* access modifiers changed from: 0000 */
    public synchronized FramedStream getStream(int i) {
        return (FramedStream) this.streams.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: 0000 */
    public synchronized FramedStream removeStream(int i) {
        FramedStream framedStream;
        framedStream = (FramedStream) this.streams.remove(Integer.valueOf(i));
        if (framedStream != null && this.streams.isEmpty()) {
            setIdle(true);
        }
        notifyAll();
        return framedStream;
    }

    private synchronized void setIdle(boolean z) {
        this.idleStartTimeNs = z ? System.nanoTime() : Long.MAX_VALUE;
    }

    public synchronized boolean isIdle() {
        return this.idleStartTimeNs != Long.MAX_VALUE;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public synchronized long getIdleStartTimeNs() {
        return this.idleStartTimeNs;
    }

    public FramedStream pushStream(int i, List<Header> list, boolean z) throws IOException {
        if (this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        } else if (this.protocol == Protocol.HTTP_2) {
            return newStream(i, list, z, false);
        } else {
            throw new IllegalStateException("protocol != HTTP_2");
        }
    }

    public FramedStream newStream(List<Header> list, boolean z, boolean z2) throws IOException {
        return newStream(0, list, z, z2);
    }

    private FramedStream newStream(int i, List<Header> list, boolean z, boolean z2) throws IOException {
        int i2;
        FramedStream framedStream;
        boolean z3;
        boolean z4 = !z;
        boolean z5 = !z2;
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new IOException("shutdown");
                }
                i2 = this.nextStreamId;
                this.nextStreamId += 2;
                framedStream = new FramedStream(i2, this, z4, z5, list);
                z3 = !z || this.bytesLeftInWriteWindow == 0 || framedStream.bytesLeftInWriteWindow == 0;
                if (framedStream.isOpen()) {
                    this.streams.put(Integer.valueOf(i2), framedStream);
                    setIdle(false);
                }
            }
            if (i == 0) {
                this.frameWriter.synStream(z4, z5, i2, i, list);
            } else if (this.client) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.frameWriter.pushPromise(i, i2, list);
            }
        }
        if (z3) {
            this.frameWriter.flush();
        }
        return framedStream;
    }

    /* access modifiers changed from: 0000 */
    public void writeSynReply(int i, boolean z, List<Header> list) throws IOException {
        this.frameWriter.synReply(z, i, list);
    }

    public void writeData(int i, boolean z, Buffer buffer, long j) throws IOException {
        int min;
        boolean z2;
        if (j == 0) {
            this.frameWriter.data(z, i, buffer, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.bytesLeftInWriteWindow <= 0) {
                    try {
                        if (!this.streams.containsKey(Integer.valueOf(i))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.bytesLeftInWriteWindow), this.frameWriter.maxDataLength());
                this.bytesLeftInWriteWindow -= (long) min;
            }
            j -= (long) min;
            FrameWriter frameWriter2 = this.frameWriter;
            if (!z || j != 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            frameWriter2.data(z2, i, buffer, min);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void writeSynResetLater(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        executor.submit(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) {
            public void execute() {
                try {
                    FramedConnection.this.writeSynReset(i2, errorCode2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.frameWriter.rstStream(i, errorCode);
    }

    /* access modifiers changed from: 0000 */
    public void writeWindowUpdateLater(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) {
            public void execute() {
                try {
                    FramedConnection.this.frameWriter.windowUpdate(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    public Ping ping() throws IOException {
        int i;
        Ping ping = new Ping();
        synchronized (this) {
            if (this.shutdown) {
                throw new IOException("shutdown");
            }
            i = this.nextPingId;
            this.nextPingId += 2;
            if (this.pings == null) {
                this.pings = new HashMap();
            }
            this.pings.put(Integer.valueOf(i), ping);
        }
        writePing(false, i, 1330343787, ping);
        return ping;
    }

    /* access modifiers changed from: private */
    public void writePingLater(boolean z, int i, int i2, Ping ping) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final Ping ping2 = ping;
        executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.hostname, Integer.valueOf(i), Integer.valueOf(i2)}) {
            public void execute() {
                try {
                    FramedConnection.this.writePing(z2, i3, i4, ping2);
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void writePing(boolean z, int i, int i2, Ping ping) throws IOException {
        synchronized (this.frameWriter) {
            if (ping != null) {
                ping.send();
            }
            this.frameWriter.ping(z, i, i2);
        }
    }

    /* access modifiers changed from: private */
    public synchronized Ping removePing(int i) {
        return this.pings != null ? (Ping) this.pings.remove(Integer.valueOf(i)) : null;
    }

    public void flush() throws IOException {
        this.frameWriter.flush();
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.shutdown = true;
                    int i = this.lastGoodStreamId;
                    this.frameWriter.goAway(i, errorCode, Util.EMPTY_BYTE_ARRAY);
                }
            }
        }
    }

    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* access modifiers changed from: private */
    public void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        IOException iOException;
        FramedStream[] framedStreamArr;
        Ping[] pingArr;
        if ($assertionsDisabled || !Thread.holdsLock(this)) {
            try {
                shutdown(errorCode);
                iOException = null;
            } catch (IOException e) {
                iOException = e;
            }
            synchronized (this) {
                if (!this.streams.isEmpty()) {
                    FramedStream[] framedStreamArr2 = (FramedStream[]) this.streams.values().toArray(new FramedStream[this.streams.size()]);
                    this.streams.clear();
                    setIdle(false);
                    framedStreamArr = framedStreamArr2;
                } else {
                    framedStreamArr = null;
                }
                if (this.pings != null) {
                    Ping[] pingArr2 = (Ping[]) this.pings.values().toArray(new Ping[this.pings.size()]);
                    this.pings = null;
                    pingArr = pingArr2;
                } else {
                    pingArr = null;
                }
            }
            if (framedStreamArr != null) {
                IOException iOException2 = iOException;
                for (FramedStream close : framedStreamArr) {
                    try {
                        close.close(errorCode2);
                    } catch (IOException e2) {
                        if (iOException2 != null) {
                            iOException2 = e2;
                        }
                    }
                }
                iOException = iOException2;
            }
            if (pingArr != null) {
                for (Ping cancel : pingArr) {
                    cancel.cancel();
                }
            }
            try {
                this.frameWriter.close();
                e = iOException;
            } catch (IOException e3) {
                e = e3;
                if (iOException != null) {
                    e = iOException;
                }
            }
            try {
                this.socket.close();
            } catch (IOException e4) {
                e = e4;
            }
            if (e != null) {
                throw e;
            }
            return;
        }
        throw new AssertionError();
    }

    public void start() throws IOException {
        start(true);
    }

    /* access modifiers changed from: 0000 */
    public void start(boolean z) throws IOException {
        if (z) {
            this.frameWriter.connectionPreface();
            this.frameWriter.settings(this.okHttpSettings);
            int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
            if (initialWindowSize != 65536) {
                this.frameWriter.windowUpdate(0, (long) (initialWindowSize - 65536));
            }
        }
        new Thread(this.readerRunnable).start();
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new IOException("shutdown");
                }
                this.okHttpSettings.merge(settings);
                this.frameWriter.settings(settings);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean pushedStream(int i) {
        return this.protocol == Protocol.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    /* access modifiers changed from: private */
    public void pushRequestLater(int i, List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i))) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i));
            final int i2 = i;
            final List<Header> list2 = list;
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
                public void execute() {
                    if (FramedConnection.this.pushObserver.onRequest(i2, list2)) {
                        try {
                            FramedConnection.this.frameWriter.rstStream(i2, ErrorCode.CANCEL);
                            synchronized (FramedConnection.this) {
                                FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void pushHeadersLater(int i, List<Header> list, boolean z) {
        final int i2 = i;
        final List<Header> list2 = list;
        final boolean z2 = z;
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
            public void execute() {
                boolean onHeaders = FramedConnection.this.pushObserver.onHeaders(i2, list2, z2);
                if (onHeaders) {
                    try {
                        FramedConnection.this.frameWriter.rstStream(i2, ErrorCode.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (onHeaders || z2) {
                    synchronized (FramedConnection.this) {
                        FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushDataLater(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
        final Buffer buffer = new Buffer();
        bufferedSource.require((long) i2);
        bufferedSource.read(buffer, (long) i2);
        if (buffer.size() != ((long) i2)) {
            throw new IOException(buffer.size() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
            public void execute() {
                try {
                    boolean onData = FramedConnection.this.pushObserver.onData(i3, buffer, i4, z2);
                    if (onData) {
                        FramedConnection.this.frameWriter.rstStream(i3, ErrorCode.CANCEL);
                    }
                    if (onData || z2) {
                        synchronized (FramedConnection.this) {
                            FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushResetLater(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
            public void execute() {
                FramedConnection.this.pushObserver.onReset(i2, errorCode2);
                synchronized (FramedConnection.this) {
                    FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}
