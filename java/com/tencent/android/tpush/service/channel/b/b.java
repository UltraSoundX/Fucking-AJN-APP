package com.tencent.android.tpush.service.channel.b;

import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.service.channel.c.c;
import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import com.tencent.android.tpush.service.channel.exception.UnexpectedDataException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: ProGuard */
public class b extends f implements e {
    protected HashMap<String, Object> a = new HashMap<>(4);
    protected int b = 0;
    protected int c = -1;
    public ArrayList<e> d = new ArrayList<>(1);
    protected String e = null;
    protected String f = null;
    protected final HashMap<String, String> g = new HashMap<>(8);

    public b(String str, String str2) {
        this.e = str;
        this.f = str2;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        if (this.c != i) {
            this.b = 0;
        }
        this.c = i;
    }

    public int a(OutputStream outputStream) {
        int i;
        IORefusedException e2;
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
                        case -1:
                            i += b(outputStream);
                            break;
                        case 0:
                            d();
                            break;
                        default:
                            throw new InnerException("illegal step value!");
                    }
                } catch (IORefusedException e3) {
                    e2 = e3;
                    a.d("Channel.HttpSendPacket", "write >>> IORefusedException thrown", e2);
                    return i;
                }
            }
        } catch (IORefusedException e4) {
            IORefusedException iORefusedException = e4;
            i = 0;
            e2 = iORefusedException;
            a.d("Channel.HttpSendPacket", "write >>> IORefusedException thrown", e2);
            return i;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int b(OutputStream outputStream) {
        String str;
        byte[] bArr = (byte[]) this.a.get("httpData");
        if (bArr == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                c(byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                a("Content-Length", String.valueOf(byteArray.length));
                String str2 = "POST " + this.f + " HTTP/1.1\r\n";
                Iterator it = this.g.entrySet().iterator();
                while (true) {
                    str = str2;
                    if (!it.hasNext()) {
                        break;
                    }
                    Entry entry = (Entry) it.next();
                    str2 = str + ((String) entry.getKey()) + ": " + ((String) entry.getValue()) + "\r\n";
                }
                byte[] bytes = (str + "\r\n").getBytes("UTF-8");
                bArr = new byte[(bytes.length + byteArray.length)];
                System.arraycopy(bytes, 0, bArr, 0, bytes.length);
                System.arraycopy(byteArray, 0, bArr, bytes.length, byteArray.length);
                this.a.put("httpData", bArr);
                this.a.put("httpDataLeftLength", Integer.valueOf(bArr.length));
            } catch (IOException e2) {
                throw new UnexpectedDataException("http content can not be write correctly!", e2);
            }
        }
        byte[] bArr2 = bArr;
        int intValue = ((Integer) this.a.get("httpDataLeftLength")).intValue();
        if (intValue == 0) {
            a(0);
            return 0;
        }
        int a2 = c.a(outputStream, bArr2);
        this.a.put("httpDataLeftLength", Integer.valueOf(intValue - a2));
        return a2;
    }

    /* access modifiers changed from: protected */
    public void c(OutputStream outputStream) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            eVar.a(this.j);
            eVar.a(outputStream);
        }
    }

    public void a(String str, String str2) {
        this.g.put(str, str2);
    }

    public void a(e eVar) {
        this.d.add(eVar);
    }
}
