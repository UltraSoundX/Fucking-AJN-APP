package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.service.channel.c.c;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/* compiled from: ProGuard */
public class h extends i implements e {
    protected HashMap<String, Object> a;
    protected int b;
    protected int c;

    public h(int i) {
        this.a = new HashMap<>(4);
        this.b = 0;
        this.c = -1;
        this.d = 80;
        this.e = i;
    }

    public synchronized void d() {
        super.d();
        this.a.clear();
    }

    public int a(OutputStream outputStream) {
        int i;
        IORefusedException e;
        c();
        try {
            this.b = 0;
            i = 0;
            while (!b()) {
                try {
                    int i2 = this.b;
                    this.b = i2 + 1;
                    if (i2 > 2) {
                        throw new InnerException("the duration of the current step is too long!");
                    }
                    switch (this.c) {
                        case -5:
                            i += f(outputStream);
                            break;
                        case -4:
                            i += e(outputStream);
                            break;
                        case -3:
                            i += d(outputStream);
                            break;
                        case -2:
                            i += c(outputStream);
                            break;
                        case -1:
                            i += b(outputStream);
                            break;
                        case 0:
                            d();
                            break;
                        default:
                            throw new InnerException("illegal step value!");
                    }
                } catch (IORefusedException e2) {
                    e = e2;
                    a.d("Channel.SendPacket", "write >>> IORefusedException thrown", e);
                    return i;
                }
            }
        } catch (IORefusedException e3) {
            IORefusedException iORefusedException = e3;
            i = 0;
            e = iORefusedException;
            a.d("Channel.SendPacket", "write >>> IORefusedException thrown", e);
            return i;
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        if (this.c != i) {
            this.b = 0;
        }
        this.c = i;
    }

    /* access modifiers changed from: protected */
    public int b(OutputStream outputStream) {
        c.a(outputStream, (int) this.d);
        a(-2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int c(OutputStream outputStream) {
        c.a(outputStream, (int) this.k);
        switch (this.k) {
            case 1:
            case 10:
                a(-3);
                break;
            case 20:
                a(0);
                break;
            default:
                throw new UnexpectedDataException("protocol: " + this.k);
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public int d(OutputStream outputStream) {
        c.b(outputStream, this.e);
        a(-5);
        return 4;
    }

    /* access modifiers changed from: protected */
    public int e(OutputStream outputStream) {
        c.a(outputStream, this.f);
        a(-5);
        return 4;
    }

    /* access modifiers changed from: protected */
    public int f(OutputStream outputStream) {
        byte[] bArr = (byte[]) this.a.get("packetData");
        if (bArr == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                if (this.k == 10) {
                    h(byteArrayOutputStream);
                } else {
                    g(byteArrayOutputStream);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                this.f = (long) (byteArray.length + 10);
                this.a.put("packetData", byteArray);
                this.a.put("packetDataLeftLength", Integer.valueOf(byteArray.length));
                a(-4);
                return 0;
            } catch (IOException e) {
                throw new UnexpectedDataException("packetData can not be write correctly!", e);
            }
        } else {
            int intValue = ((Integer) this.a.get("packetDataLeftLength")).intValue();
            if (intValue == 0) {
                a(0);
                return 0;
            }
            int a2 = c.a(outputStream, bArr);
            this.a.put("packetDataLeftLength", Integer.valueOf(intValue - a2));
            return a2;
        }
    }

    private void g(OutputStream outputStream) {
        this.i = 0;
        if (this.j.needsUpdate()) {
            this.i = 1;
            this.j.update();
        }
        c.a(outputStream, (int) this.i);
        this.g = this.j.getRandom();
        c.a(outputStream, this.g);
        if (this.i != 0) {
            c.a(outputStream, this.j.getEncKey());
        }
        h(outputStream);
    }

    private void h(OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        c.a((OutputStream) byteArrayOutputStream, this.k != 1 ? 0 : this.j.getInc());
        c.a((OutputStream) byteArrayOutputStream, (int) this.l);
        c.a((OutputStream) byteArrayOutputStream, (int) this.h);
        c.a((OutputStream) byteArrayOutputStream, (int) this.m);
        byteArrayOutputStream.write(this.n);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (this.k == 1) {
            byteArray = this.j.encryptData(byteArray);
        }
        c.a(outputStream, byteArray);
    }
}
