package com.tencent.android.tpush.service.channel;

import com.baidu.mobstat.Config;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.c.b;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import java.util.Random;

/* compiled from: ProGuard */
public class c {
    private static int g = new Random().nextInt();
    public int a = 0;
    public long b = Long.MAX_VALUE;
    public long c = Long.MAX_VALUE;
    public short d;
    public JceStruct e = null;
    public a f;
    private int h = 0;

    /* compiled from: ProGuard */
    public interface a {
        void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar);

        void a(JceStruct jceStruct, a aVar);

        void a(JceStruct jceStruct, ChannelException channelException, a aVar);
    }

    public c(JceStruct jceStruct, a aVar) {
        this.d = b.a(jceStruct.getClass());
        this.e = jceStruct;
        this.f = aVar;
    }

    public c(short s, JceStruct jceStruct, a aVar) {
        this.d = s;
        this.e = jceStruct;
        this.f = aVar;
    }

    public void a(h hVar) {
        hVar.a(this.d);
        switch (this.d & 127) {
            case 7:
                hVar.b(20);
                return;
            default:
                try {
                    hVar.b(1);
                    JceOutputStream jceOutputStream = new JceOutputStream();
                    jceOutputStream.setServerEncoding("UTF-8");
                    this.e.writeTo(jceOutputStream);
                    hVar.a(jceOutputStream.toByteArray());
                    return;
                } catch (Throwable th) {
                    com.tencent.android.tpush.b.a.j("XINGE", "jceMessage.write Error:" + th.getLocalizedMessage());
                    if (XGPushConfig.enableDebug) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
        }
    }

    public boolean a() {
        return (this.d & 127) == 7;
    }

    public boolean b() {
        return (this.d & 127) == 4 || (this.d & 127) == 15 || (this.d & 127) == 5;
    }

    public int c() {
        int i = g + 1;
        g = i;
        this.h = i;
        return this.h;
    }

    public int d() {
        return this.h;
    }

    public String toString() {
        if (this.e == null) {
            return "null";
        }
        return this.e.getClass().getSimpleName() + Config.TRACE_TODAY_VISIT_SPLIT + this.e + ", " + this.f + " retryTimes " + this.a;
    }
}
