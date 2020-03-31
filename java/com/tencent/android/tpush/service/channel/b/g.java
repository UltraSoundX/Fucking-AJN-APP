package com.tencent.android.tpush.service.channel.b;

import com.baidu.mobstat.Config;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.service.channel.c.c;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/* compiled from: ProGuard */
public class g extends i implements d {
    protected HashMap<String, Object> a = new HashMap<>(4);
    protected int b = 0;
    protected int c = -1;

    public synchronized void d() {
        super.d();
        this.a.clear();
    }

    public int a(InputStream inputStream) {
        int i = 0;
        c();
        if (inputStream.available() != 0) {
            try {
                this.b = 0;
                while (true) {
                    if (!b()) {
                        int i2 = this.b;
                        this.b = i2 + 1;
                        if (i2 > 2) {
                            throw new InnerException("the duration of the current step is too long!");
                        }
                        switch (this.c) {
                            case -7:
                                i += h(inputStream);
                                break;
                            case -6:
                                i += g(inputStream);
                                break;
                            case -5:
                                i += f(inputStream);
                                break;
                            case -4:
                                i += e(inputStream);
                                break;
                            case -3:
                                i += d(inputStream);
                                break;
                            case -2:
                                i += c(inputStream);
                                break;
                            case -1:
                                i += b(inputStream);
                                break;
                            case 0:
                                d();
                                break;
                            default:
                                throw new InnerException("illegal step value!");
                        }
                        if (this.c == 0 || inputStream.available() != 0) {
                        }
                    }
                }
            } catch (IORefusedException e) {
                a.d("Channel.RecvPacket", "read >>> IORefusedException thrown", e);
            }
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
    public int b(InputStream inputStream) {
        this.d = c.a(inputStream);
        if (this.d != 80) {
            throw new UnexpectedDataException("soh: " + this.d + " != TPNS_SOH");
        }
        a(-2);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int c(InputStream inputStream) {
        this.k = c.a(inputStream);
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
    public int d(InputStream inputStream) {
        this.e = c.c(inputStream);
        a(-4);
        return 4;
    }

    /* access modifiers changed from: protected */
    public int e(InputStream inputStream) {
        this.f = c.b(inputStream);
        this.f -= 10;
        if (this.f > Config.FULL_TRACE_LOG_LIMIT || this.f < 0) {
            throw new UnexpectedDataException("packetLength: " + this.f);
        }
        if (this.k == 1) {
            a(-5);
        } else {
            a(-7);
        }
        return 4;
    }

    /* access modifiers changed from: protected */
    public int f(InputStream inputStream) {
        this.f--;
        this.i = c.a(inputStream);
        if (this.i != 0) {
            throw new UnexpectedDataException("negotiateSecurity: " + this.i + " != 0");
        }
        a(-6);
        return 1;
    }

    /* access modifiers changed from: protected */
    public int g(InputStream inputStream) {
        this.f -= 4;
        this.g = c.b(inputStream);
        if (this.g != this.j.getRandom()) {
            throw new UnexpectedDataException("unexpected random: " + this.g);
        }
        a(-7);
        return 4;
    }

    /* access modifiers changed from: protected */
    public int h(InputStream inputStream) {
        byte[] bArr = (byte[]) this.a.get("contentData");
        if (bArr == null) {
            if (this.f < 0) {
                throw new UnexpectedDataException("unexpected packetLength: " + this.f + " < 0");
            }
            bArr = new byte[((int) this.f)];
            this.a.put("contentData", bArr);
            this.a.put("contentDataLeftLength", Integer.valueOf(bArr.length));
        }
        byte[] bArr2 = bArr;
        int intValue = ((Integer) this.a.get("contentDataLeftLength")).intValue();
        int a2 = c.a(inputStream, bArr2, bArr2.length - intValue);
        int i = intValue - a2;
        this.a.put("contentDataLeftLength", Integer.valueOf(i));
        if (i == 0) {
            if (this.k == 1) {
                bArr2 = this.j.decryptData(bArr2);
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
            try {
                long b2 = c.b(byteArrayInputStream);
                if (this.k == 1) {
                    this.j.checkRemoteInc(b2);
                }
                this.l = c.a(byteArrayInputStream);
                this.h = c.a(byteArrayInputStream);
                this.m = c.a(byteArrayInputStream);
                if (byteArrayInputStream.available() > 0) {
                    this.n = new byte[byteArrayInputStream.available()];
                    c.a(byteArrayInputStream, this.n, 0);
                }
                a(0);
            } catch (IOException e) {
                throw new UnexpectedDataException("contentData can not be read correctly!", e);
            }
        }
        return a2;
    }
}
