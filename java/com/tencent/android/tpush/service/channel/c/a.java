package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ProGuard */
public class a {
    protected byte[] a;
    protected volatile int b;
    protected volatile int c;
    protected volatile int d;
    protected volatile int e;
    protected volatile boolean f;
    protected boolean g;
    protected InputStream h;
    protected boolean i;
    protected OutputStream j;
    protected boolean k;

    /* renamed from: com.tencent.android.tpush.service.channel.c.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    protected class C0069a extends InputStream {
        protected C0069a() {
        }

        public int available() {
            int a2;
            synchronized (a.this) {
                if (a.this.i) {
                    throw new IOException("InputStream has been closed, it is not ready.");
                }
                a2 = a.this.g();
            }
            return a2;
        }

        public void close() {
            synchronized (a.this) {
                a.this.i = true;
            }
        }

        public void mark(int i) {
            synchronized (a.this) {
                if (a.this.a.length - 1 > i) {
                    a.this.e = i;
                    a.this.d = a.this.b;
                }
            }
        }

        public boolean markSupported() {
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x005f, code lost:
            throw new java.io.IOException("Blocking read operation interrupted.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read() {
            /*
                r4 = this;
            L_0x0000:
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this
                monitor-enter(r1)
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.i     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0014
                java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0011 }
                java.lang.String r2 = "InputStream has been closed; cannot read from a closed InputStream."
                r0.<init>(r2)     // Catch:{ all -> 0x0011 }
                throw r0     // Catch:{ all -> 0x0011 }
            L_0x0011:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                throw r0
            L_0x0014:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r0 = r0.g()     // Catch:{ all -> 0x0011 }
                if (r0 <= 0) goto L_0x0047
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r0 = r0.a     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r2 = r2.b     // Catch:{ all -> 0x0011 }
                byte r0 = r0[r2]     // Catch:{ all -> 0x0011 }
                r0 = r0 & 255(0xff, float:3.57E-43)
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r3 = r2.b     // Catch:{ all -> 0x0011 }
                int r3 = r3 + 1
                r2.b = r3     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r2 = r2.b     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r3 = r3.a     // Catch:{ all -> 0x0011 }
                int r3 = r3.length     // Catch:{ all -> 0x0011 }
                if (r2 != r3) goto L_0x0040
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r3 = 0
                r2.b = r3     // Catch:{ all -> 0x0011 }
            L_0x0040:
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r2.i()     // Catch:{ all -> 0x0011 }
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            L_0x0046:
                return r0
            L_0x0047:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.k     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0050
                r0 = -1
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                goto L_0x0046
            L_0x0050:
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                r0 = 100
                java.lang.Thread.sleep(r0)     // Catch:{ Exception -> 0x0057 }
                goto L_0x0000
            L_0x0057:
                r0 = move-exception
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Blocking read operation interrupted."
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.a.C0069a.read():int");
        }

        public int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0081, code lost:
            throw new java.io.IOException("Blocking read operation interrupted.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read(byte[] r7, int r8, int r9) {
            /*
                r6 = this;
            L_0x0000:
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this
                monitor-enter(r1)
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.i     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0014
                java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0011 }
                java.lang.String r2 = "InputStream has been closed; cannot read from a closed InputStream."
                r0.<init>(r2)     // Catch:{ all -> 0x0011 }
                throw r0     // Catch:{ all -> 0x0011 }
            L_0x0011:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                throw r0
            L_0x0014:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r0 = r0.g()     // Catch:{ all -> 0x0011 }
                if (r0 <= 0) goto L_0x0069
                int r0 = java.lang.Math.min(r9, r0)     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r2 = r2.a     // Catch:{ all -> 0x0011 }
                int r2 = r2.length     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r3 = r3.b     // Catch:{ all -> 0x0011 }
                int r2 = r2 - r3
                int r2 = java.lang.Math.min(r0, r2)     // Catch:{ all -> 0x0011 }
                int r3 = r0 - r2
                com.tencent.android.tpush.service.channel.c.a r4 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r4 = r4.a     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r5 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r5 = r5.b     // Catch:{ all -> 0x0011 }
                java.lang.System.arraycopy(r4, r5, r7, r8, r2)     // Catch:{ all -> 0x0011 }
                if (r3 <= 0) goto L_0x0061
                com.tencent.android.tpush.service.channel.c.a r4 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r4 = r4.a     // Catch:{ all -> 0x0011 }
                r5 = 0
                int r2 = r2 + r8
                java.lang.System.arraycopy(r4, r5, r7, r2, r3)     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r2.b = r3     // Catch:{ all -> 0x0011 }
            L_0x004a:
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r2 = r2.b     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r3 = r3.a     // Catch:{ all -> 0x0011 }
                int r3 = r3.length     // Catch:{ all -> 0x0011 }
                if (r2 != r3) goto L_0x005a
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r3 = 0
                r2.b = r3     // Catch:{ all -> 0x0011 }
            L_0x005a:
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r2.i()     // Catch:{ all -> 0x0011 }
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            L_0x0060:
                return r0
            L_0x0061:
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r3 = r2.b     // Catch:{ all -> 0x0011 }
                int r3 = r3 + r0
                r2.b = r3     // Catch:{ all -> 0x0011 }
                goto L_0x004a
            L_0x0069:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.k     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0072
                r0 = -1
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                goto L_0x0060
            L_0x0072:
                monitor-exit(r1)     // Catch:{ all -> 0x0011 }
                r0 = 100
                java.lang.Thread.sleep(r0)     // Catch:{ Exception -> 0x0079 }
                goto L_0x0000
            L_0x0079:
                r0 = move-exception
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Blocking read operation interrupted."
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.a.C0069a.read(byte[], int, int):int");
        }

        public void reset() {
            synchronized (a.this) {
                if (a.this.i) {
                    throw new IOException("InputStream has been closed; cannot reset a closed InputStream.");
                }
                a.this.b = a.this.d;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0070, code lost:
            throw new java.io.IOException("Blocking read operation interrupted.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long skip(long r6) {
            /*
                r5 = this;
            L_0x0000:
                com.tencent.android.tpush.service.channel.c.a r2 = com.tencent.android.tpush.service.channel.c.a.this
                monitor-enter(r2)
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.i     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0014
                java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0011 }
                java.lang.String r1 = "InputStream has been closed; cannot skip bytes on a closed InputStream."
                r0.<init>(r1)     // Catch:{ all -> 0x0011 }
                throw r0     // Catch:{ all -> 0x0011 }
            L_0x0011:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0011 }
                throw r0
            L_0x0014:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r0 = r0.g()     // Catch:{ all -> 0x0011 }
                if (r0 <= 0) goto L_0x0057
                int r1 = (int) r6     // Catch:{ all -> 0x0011 }
                int r0 = java.lang.Math.min(r1, r0)     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r1 = r1.a     // Catch:{ all -> 0x0011 }
                int r1 = r1.length     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r3 = r3.b     // Catch:{ all -> 0x0011 }
                int r1 = r1 - r3
                int r1 = java.lang.Math.min(r0, r1)     // Catch:{ all -> 0x0011 }
                int r1 = r0 - r1
                if (r1 <= 0) goto L_0x004f
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r3.b = r1     // Catch:{ all -> 0x0011 }
            L_0x0037:
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r1 = r1.b     // Catch:{ all -> 0x0011 }
                com.tencent.android.tpush.service.channel.c.a r3 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                byte[] r3 = r3.a     // Catch:{ all -> 0x0011 }
                int r3 = r3.length     // Catch:{ all -> 0x0011 }
                if (r1 != r3) goto L_0x0047
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r3 = 0
                r1.b = r3     // Catch:{ all -> 0x0011 }
            L_0x0047:
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                r1.i()     // Catch:{ all -> 0x0011 }
                long r0 = (long) r0     // Catch:{ all -> 0x0011 }
                monitor-exit(r2)     // Catch:{ all -> 0x0011 }
            L_0x004e:
                return r0
            L_0x004f:
                com.tencent.android.tpush.service.channel.c.a r1 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                int r3 = r1.b     // Catch:{ all -> 0x0011 }
                int r3 = r3 + r0
                r1.b = r3     // Catch:{ all -> 0x0011 }
                goto L_0x0037
            L_0x0057:
                com.tencent.android.tpush.service.channel.c.a r0 = com.tencent.android.tpush.service.channel.c.a.this     // Catch:{ all -> 0x0011 }
                boolean r0 = r0.k     // Catch:{ all -> 0x0011 }
                if (r0 == 0) goto L_0x0061
                r0 = 0
                monitor-exit(r2)     // Catch:{ all -> 0x0011 }
                goto L_0x004e
            L_0x0061:
                monitor-exit(r2)     // Catch:{ all -> 0x0011 }
                r0 = 100
                java.lang.Thread.sleep(r0)     // Catch:{ Exception -> 0x0068 }
                goto L_0x0000
            L_0x0068:
                r0 = move-exception
                java.io.IOException r0 = new java.io.IOException
                java.lang.String r1 = "Blocking read operation interrupted."
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.c.a.C0069a.skip(long):long");
        }
    }

    /* compiled from: ProGuard */
    protected class b extends OutputStream {
        protected b() {
        }

        public void close() {
            synchronized (a.this) {
                if (!a.this.k) {
                    flush();
                }
                a.this.k = true;
            }
        }

        public void flush() {
            if (a.this.k) {
                throw new IOException("OutputStream has been closed; cannot flush a closed OutputStream.");
            } else if (a.this.i) {
                throw new IOException("Buffer closed by inputStream; cannot flush.");
            }
        }

        public void write(byte[] bArr) {
            write(bArr, 0, bArr.length);
        }

        public void write(byte[] bArr, int i, int i2) {
            while (i2 > 0) {
                synchronized (a.this) {
                    if (a.this.k) {
                        throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
                    } else if (a.this.i) {
                        throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
                    } else {
                        int c = a.this.f();
                        while (a.this.f && c < i2) {
                            a.this.e();
                            c = a.this.f();
                        }
                        if (a.this.g || c >= i2) {
                            int min = Math.min(i2, c);
                            int min2 = Math.min(min, a.this.a.length - a.this.c);
                            int min3 = Math.min(min - min2, (a.this.a.length - a.this.d) - 1);
                            int i3 = min2 + min3;
                            if (min2 > 0) {
                                System.arraycopy(bArr, i, a.this.a, a.this.c, min2);
                            }
                            if (min3 > 0) {
                                System.arraycopy(bArr, min2 + i, a.this.a, 0, min3);
                                a.this.c = min3;
                            } else {
                                a.this.c += i3;
                            }
                            if (a.this.c == a.this.a.length) {
                                a.this.c = 0;
                            }
                            i += i3;
                            i2 -= i3;
                        } else {
                            throw new IORefusedException("CircularByteBuffer is full; cannot write " + i2 + " bytes");
                        }
                    }
                }
                if (i2 > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        throw new IOException("Waiting for available space in buffer interrupted.");
                    }
                }
            }
        }

        public void write(int i) {
            boolean z = false;
            while (!z) {
                synchronized (a.this) {
                    if (a.this.k) {
                        throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
                    } else if (a.this.i) {
                        throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
                    } else {
                        int c = a.this.f();
                        while (a.this.f && c < 1) {
                            a.this.e();
                            c = a.this.f();
                        }
                        if (!a.this.g && c < 1) {
                            throw new IORefusedException("CircularByteBuffer is full; cannot write 1 byte");
                        } else if (c > 0) {
                            a.this.a[a.this.c] = (byte) (i & 255);
                            a.this.c++;
                            if (a.this.c == a.this.a.length) {
                                a.this.c = 0;
                            }
                            z = true;
                        }
                    }
                }
                if (!z) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        throw new IOException("Waiting for available space in buffer interrupted.");
                    }
                }
            }
        }
    }

    public OutputStream a() {
        return this.j;
    }

    public InputStream b() {
        return this.h;
    }

    public int c() {
        int g2;
        synchronized (this) {
            g2 = g();
        }
        return g2;
    }

    public int d() {
        int f2;
        synchronized (this) {
            f2 = f();
        }
        return f2;
    }

    /* access modifiers changed from: private */
    public void e() {
        byte[] bArr = new byte[(this.a.length * 2)];
        int h2 = h();
        int g2 = g();
        if (this.d <= this.c) {
            System.arraycopy(this.a, this.d, bArr, 0, this.c - this.d);
        } else {
            int length = this.a.length - this.d;
            System.arraycopy(this.a, this.d, bArr, 0, length);
            System.arraycopy(this.a, 0, bArr, length, this.c);
        }
        this.a = bArr;
        this.d = 0;
        this.b = h2;
        this.c = h2 + g2;
    }

    /* access modifiers changed from: private */
    public int f() {
        if (this.c < this.d) {
            return (this.d - this.c) - 1;
        }
        return (this.a.length - 1) - (this.c - this.d);
    }

    /* access modifiers changed from: private */
    public int g() {
        if (this.b <= this.c) {
            return this.c - this.b;
        }
        return this.a.length - (this.b - this.c);
    }

    private int h() {
        if (this.d <= this.b) {
            return this.b - this.d;
        }
        return this.a.length - (this.d - this.b);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (h() >= this.e) {
            this.d = this.b;
            this.e = 0;
        }
    }

    public a() {
        this(4096, true);
    }

    public a(int i2, boolean z) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = false;
        this.g = true;
        this.h = new C0069a();
        this.i = false;
        this.j = new b();
        this.k = false;
        if (i2 == -1) {
            this.a = new byte[4096];
            this.f = true;
        } else {
            this.a = new byte[i2];
            this.f = false;
        }
        this.g = z;
    }
}
