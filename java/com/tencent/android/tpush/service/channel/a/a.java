package com.tencent.android.tpush.service.channel.a;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.ReturnCode;
import com.tencent.android.tpush.service.channel.b.d;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

/* compiled from: ProGuard */
public class a extends Thread {
    protected C0067a a;
    public SocketChannel b = null;
    protected Selector c = null;
    protected TpnsSecurity d = new TpnsSecurity();
    protected d e = null;
    protected e f = null;
    protected String g = "";
    protected int h = 0;
    protected int i = 0;
    protected long j = Long.MAX_VALUE;
    protected com.tencent.android.tpush.service.channel.a k = null;
    private volatile boolean l = false;

    /* renamed from: com.tencent.android.tpush.service.channel.a.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public interface C0067a {
        ArrayList<h> a(a aVar, int i);

        void a(a aVar);

        void a(a aVar, i iVar);

        void a(a aVar, ChannelException channelException);

        void b(a aVar);

        void b(a aVar, i iVar);
    }

    public a(SocketChannel socketChannel, C0067a aVar) {
        super("TpnsClient");
        if (socketChannel.socket().isConnected()) {
            this.g = socketChannel.socket().getInetAddress() == null ? "" : socketChannel.socket().getInetAddress().getHostAddress();
            this.h = socketChannel.socket().getPort();
            this.i = 0;
            com.tencent.android.tpush.b.a.f("TpnsClient", "Connect to Xinge Server succeed!");
        } else {
            com.tencent.android.tpush.b.a.i("TpnsClient", "TpnsClient -> the socketChannel is not connected");
        }
        this.b = socketChannel;
        this.a = aVar;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        if (this.e == null) {
            this.e = new g();
            ((g) this.e).a(this.d);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        if (this.f == null) {
            ArrayList a2 = this.a.a(this, 1);
            if (!a2.isEmpty()) {
                this.f = (e) a2.get(0);
            }
            if (this.f != null) {
                ((h) this.f).a(this.d);
            }
        }
        return this.f != null;
    }

    public void a(a aVar, d dVar) {
        this.a.b(aVar, (i) dVar);
    }

    public void a(a aVar, e eVar) {
        if ((((h) eVar).h() & 127) != 7) {
            this.a.a(aVar, (i) eVar);
        }
    }

    public void run() {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.b.a.e("TpnsClient", "TpnsClient is running and ready for send and recevie msg.");
        }
        try {
            this.c = Selector.open();
            this.b.configureBlocking(false);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(24576);
            com.tencent.android.tpush.service.channel.c.a aVar = new com.tencent.android.tpush.service.channel.c.a(24576, false);
            byte[] bArr = new byte[24576];
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(4096);
            com.tencent.android.tpush.service.channel.c.a aVar2 = new com.tencent.android.tpush.service.channel.c.a(-1, false);
            byte[] bArr2 = new byte[4096];
            allocateDirect2.flip();
            long j2 = 0;
            while (true) {
                if (this.l) {
                    break;
                }
                this.b.register(this.c, 1);
                if (b() || allocateDirect2.remaining() > 0 || aVar2.c() > 0) {
                    this.b.register(this.c, 4);
                }
                if (g() && this.e == null && this.f == null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">> retired!!!");
                    break;
                }
                this.c.select(j2);
                j2 = 0;
                if (this.f != null) {
                    long a2 = this.f.a();
                    if (a2 <= 0) {
                        throw new TimeoutException("发送超时");
                    }
                    if (a2 >= 0) {
                        a2 = 0;
                    }
                    j2 = a2;
                }
                if (this.e != null) {
                    long a3 = this.e.a();
                    if (a3 <= 0) {
                        throw new TimeoutException("接收超时");
                    }
                    if (a3 >= j2) {
                        a3 = j2;
                    }
                    j2 = a3;
                }
                Iterator it = this.c.selectedKeys().iterator();
                while (true) {
                    if (it.hasNext()) {
                        SelectionKey selectionKey = (SelectionKey) it.next();
                        if (selectionKey.isReadable()) {
                            allocateDirect.clear();
                            allocateDirect.limit(aVar.d());
                            int read = this.b.read(allocateDirect.slice());
                            if (read == -1) {
                                throw new IOException("socket channel read return -1");
                            }
                            allocateDirect.get(bArr, 0, read);
                            aVar.a().write(bArr, 0, read);
                            a(aVar.b());
                        }
                        if (selectionKey.isWritable()) {
                            a(aVar2.a());
                            if (aVar2.c() > 0) {
                                allocateDirect2.compact();
                                allocateDirect2.put(bArr2, 0, aVar2.b().read(bArr2, 0, allocateDirect2.remaining() < aVar2.c() ? allocateDirect2.remaining() : aVar2.c()));
                                allocateDirect2.flip();
                                this.b.write(allocateDirect2);
                            }
                        }
                        it.remove();
                    }
                }
            }
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e2);
                }
                try {
                    this.b.close();
                } catch (Exception e3) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e3);
                }
            }
            if (0 != 0) {
                com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + null);
                this.a.a(this, (ChannelException) null);
                return;
            } else if (this.l) {
                com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                this.a.a(this);
                return;
            } else {
                com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                this.a.b(this);
                return;
            }
        } catch (IOException e4) {
            com.tencent.android.tpush.b.a.d("TpnsClient", "<<< Run <<< socketChannel IOException", e4);
            ChannelException channelException = new ChannelException(ReturnCode.CODE_NETWORK_IOEXCEPTION_OCCUR.getType(), "TpnsClient发生IO异常，链路可能被关闭", e4);
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e5) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e5);
                }
                try {
                    this.b.close();
                } catch (Exception e6) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e6);
                }
                if (channelException != null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + channelException);
                    this.a.a(this, channelException);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        } catch (InnerException e7) {
            com.tencent.android.tpush.b.a.d("TpnsClient", "<<< Run <<< socketChannel InnerException", e7);
            ChannelException channelException2 = new ChannelException(ReturnCode.CODE_NETWORK_INNER_EXCEPTION_OCCUR.getType(), "TpnsClient发生内部异常", e7);
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e8) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e8);
                }
                try {
                    this.b.close();
                } catch (Exception e9) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e9);
                }
                if (channelException2 != null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + channelException2);
                    this.a.a(this, channelException2);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        } catch (UnexpectedDataException e10) {
            com.tencent.android.tpush.b.a.d("TpnsClient", "<<< Run <<< socketChannel UnexpectedDataException", e10);
            ChannelException channelException3 = new ChannelException(ReturnCode.CODE_NETWORK_UNEXPECTED_DATA_EXCEPTION_OCCUR.getType(), "TpnsClient发生非预期数据异常", e10);
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e11) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e11);
                }
                try {
                    this.b.close();
                } catch (Exception e12) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e12);
                }
                if (channelException3 != null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + channelException3);
                    this.a.a(this, channelException3);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        } catch (TimeoutException e13) {
            com.tencent.android.tpush.b.a.d("TpnsClient", "<<< Run <<< socketChannel TimeoutException", e13);
            ChannelException channelException4 = new ChannelException(ReturnCode.CODE_NETWORK_TIMEOUT_EXCEPTION_OCCUR.getType(), "TpnsClient发生超时异常", e13);
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e14) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e14);
                }
                try {
                    this.b.close();
                } catch (Exception e15) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e15);
                }
                if (channelException4 != null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + channelException4);
                    this.a.a(this, channelException4);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        } catch (Exception e16) {
            com.tencent.android.tpush.b.a.d("TpnsClient", "<<< Run <<< socketChannel Exception", e16);
            ChannelException channelException5 = new ChannelException(ReturnCode.CODE_NETWORK_UNKNOWN_EXCEPTION.getType(), "TpnsClient发生未知异常" + e16 + " errorcode " + ReturnCode.CODE_NETWORK_UNKNOWN_EXCEPTION.getType(), e16);
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e17) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e17);
                }
                try {
                    this.b.close();
                } catch (Exception e18) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e18);
                }
                if (channelException5 != null) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + channelException5);
                    this.a.a(this, channelException5);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        } finally {
            synchronized (this) {
                try {
                    this.c.close();
                } catch (Exception e19) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> selector.close() " + e19);
                }
                try {
                    this.b.close();
                } catch (Exception e20) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", ">>> Run >>> socketChannel.close(): " + e20);
                }
                if (0 != 0) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "delegate.clientExceptionOccurs <<< Run <<< exit!!! cause: " + null);
                    this.a.a(this, (ChannelException) null);
                } else if (this.l) {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! cancelled! ");
                    this.a.a(this);
                } else {
                    com.tencent.android.tpush.b.a.i("TpnsClient", "<<< Run <<< exit!!! Retired! ");
                    this.a.b(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public int a(InputStream inputStream) {
        int i2 = 0;
        while (true) {
            if (inputStream.available() <= 0) {
                break;
            }
            a();
            if (this.e != null) {
                i2 += this.e.a(inputStream);
                if (!this.e.b()) {
                    com.tencent.android.tpush.b.a.i(Constants.TcpRecvPackLogTag, ">> recvHandle not success");
                    break;
                }
                a(this, this.e);
                this.e = null;
            }
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    public int a(OutputStream outputStream) {
        int i2 = 0;
        if (!g()) {
            b();
        }
        if (this.f != null) {
            i2 = this.f.a(outputStream);
            if (this.f.b()) {
                a(this, this.f);
                this.f = null;
            }
            if (b()) {
                h();
            }
        }
        return i2;
    }

    public synchronized void start() {
        super.start();
    }

    public synchronized void c() {
        this.l = true;
        h();
    }

    public synchronized boolean d() {
        boolean z;
        if (this.b != null) {
            z = this.b.isConnected();
        } else {
            z = false;
        }
        return z;
    }

    public boolean e() {
        return this.i == 1;
    }

    public com.tencent.android.tpush.service.channel.a f() {
        boolean z = true;
        if (this.k == null) {
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(0);
            objArr[1] = this.g;
            objArr[2] = Integer.valueOf(1);
            objArr[3] = Integer.valueOf(this.h);
            objArr[4] = Integer.valueOf(2);
            if (this.i != 1) {
                z = false;
            }
            objArr[5] = Boolean.valueOf(z);
            this.k = new com.tencent.android.tpush.service.channel.a(objArr);
        }
        return this.k;
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return System.currentTimeMillis() > this.j;
    }

    public void h() {
        try {
            if (this.c != null && this.c.isOpen()) {
                this.c.wakeup();
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("TpnsClient", ">>selector wakeup err", th);
        }
    }

    public String toString() {
        return new StringBuffer(getClass().getSimpleName()).append("(ip:").append(this.g).append(",port:").append(this.h).append(",protocol:").append(this.i == 1 ? "http" : "tcp").append(")").toString();
    }
}
