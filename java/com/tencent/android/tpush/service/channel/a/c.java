package com.tencent.android.tpush.service.channel.a;

import com.baidu.mobstat.Config;
import com.tencent.android.tpush.service.channel.a.a.C0067a;
import com.tencent.android.tpush.service.channel.b.b;
import java.nio.channels.SocketChannel;

/* compiled from: ProGuard */
public class c extends b {
    public c(SocketChannel socketChannel, C0067a aVar, String str, int i) {
        String str2 = "http://" + str + (i == 80 ? "" : Config.TRACE_TODAY_VISIT_SPLIT + i) + "/";
        super(socketChannel, aVar, str, i, str2);
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return super.a();
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        if (this.f == null && super.b()) {
            ((b) this.f).a("X-Online-Host", this.m);
        }
        return this.f != null;
    }
}
