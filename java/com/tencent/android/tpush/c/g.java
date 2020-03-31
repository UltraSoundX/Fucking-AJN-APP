package com.tencent.android.tpush.c;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.cloudctr.CloudControlManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;

/* compiled from: ProGuard */
public class g {
    private long a = -1;
    private long b = -1;
    private long c = -1;
    private long d = -1;
    private String e = "";
    private long f = -1;
    private long g = -1;
    private Context h = null;
    private Intent i = null;
    private a j = null;

    private g(Context context, Intent intent) {
        this.h = context;
        this.i = intent;
    }

    public static g a(Context context, Intent intent) {
        g gVar = new g(context, intent);
        String decrypt = Rijndael.decrypt(intent.getStringExtra("content"));
        gVar.e = decrypt;
        gVar.b = intent.getLongExtra(MessageKey.MSG_ID, -1);
        gVar.c = intent.getLongExtra("accId", -1);
        gVar.d = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1);
        gVar.a = intent.getLongExtra(MessageKey.MSG_CHANNEL_ID, -1);
        gVar.f = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1);
        gVar.g = intent.getLongExtra("type", -1);
        a aVar = null;
        switch ((int) gVar.g) {
            case 1:
                aVar = new e(decrypt);
                break;
            case 2:
                aVar = new h(decrypt);
                break;
            case 3:
                c.a().b(context, decrypt);
                XGPushManager.msgAck(context, gVar);
                break;
            case 1000:
                CloudControlManager.a().a(context, decrypt);
                break;
            default:
                a.i(Constants.LogTag, "error type for message, drop it, type:" + gVar.g + ",intent:" + intent);
                XGPushManager.msgAck(context, gVar);
                break;
        }
        if (aVar != null) {
            gVar.j = aVar;
            gVar.j.b();
        }
        return gVar;
    }

    public void a() {
        if (this.j.c() == 1) {
            b.b(this.h, this);
        }
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.f;
    }

    public String f() {
        return this.e;
    }

    public a g() {
        return this.j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PushMessageManager [msgId=").append(this.b).append(", accessId=").append(this.c).append(", busiMsgId=").append(this.d).append(", content=").append(this.e).append(", timestamps=").append(this.f).append(", type=").append(this.g).append(", intent=").append(this.i).append(", messageHolder=").append(this.j).append("]");
        return sb.toString();
    }

    public long h() {
        return this.a;
    }
}
