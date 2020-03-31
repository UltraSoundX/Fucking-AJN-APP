package com.tencent.android.tpush.c;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.android.tpush.c.e.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.mid.sotrage.StorageInterface;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class d {
    private static String f = null;
    private long a = 0;
    private long b = 0;
    private String c = "";
    private String d = null;
    private Context e = null;
    private Intent g = null;

    public d(Context context, Intent intent) {
        this.e = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.g = intent;
    }

    private boolean a() {
        if (f == null) {
            f = h.a(this.e, ".xg.push.cm.vrf", "");
        }
        if (f.contains(this.c)) {
            return true;
        }
        f = this.c + StorageInterface.KEY_SPLITER + f;
        if (f.length() > 10240) {
            f = f.substring(0, 2048);
        }
        h.b(this.e, ".xg.push.cm.vrf", f);
        return false;
    }

    public boolean a(g gVar, long j, long j2, long j3) {
        String str;
        a g2 = gVar.g();
        String stringExtra = this.g.getStringExtra("title");
        if (j3 <= 0 || stringExtra == null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(gVar.c()).append(j3).append(this.e.getPackageName()).append(TextUtils.isEmpty(g2.e()) ? "" : g2.e()).append(TextUtils.isEmpty(g2.f()) ? "" : g2.f());
            String g3 = g2.g();
            if (TextUtils.isEmpty(g3) || new JSONObject(g3).length() == 0) {
                str = "";
            } else {
                str = new JSONObject(g3).toString();
            }
            sb.append(str);
            if (g2 instanceof e) {
                a m = ((e) g2).m();
                sb.append(TextUtils.isEmpty(m.f) ? "" : m.f).append(TextUtils.isEmpty(m.d) ? "" : m.d).append(TextUtils.isEmpty(m.b) ? "" : m.b);
            }
            String sb2 = sb.toString();
            String str2 = Constants.LOCAL_MESSAGE_FLAG + com.tencent.android.tpush.encrypt.a.a(sb2);
            long a2 = h.a(this.e, str2, 0);
            h.a(this.e, str2);
            long currentTimeMillis = a2 - System.currentTimeMillis();
            com.tencent.android.tpush.b.a.c(Constants.LogTag, sb2 + ",localMsgTag:" + str2 + ",diff:" + currentTimeMillis);
            if (currentTimeMillis >= 0) {
                return true;
            }
            return false;
        }
        JSONObject jSONObject = new JSONObject(Rijndael.decrypt(stringExtra));
        com.tencent.android.tpush.b.a.e(Constants.LogTag, "title encry obj:" + jSONObject);
        this.c = com.tencent.android.tpush.service.channel.security.d.a(com.tencent.android.tpush.service.channel.security.a.a(jSONObject.getString("cipher"), 0));
        String[] split = this.c.split("_");
        this.b = Long.valueOf(split[0]).longValue();
        this.d = split[1].toUpperCase();
        this.a = Long.valueOf(split[2]).longValue();
        boolean z = true;
        if (this.b != j2) {
            z = false;
        } else if (j2 == 0) {
            z = j == this.a;
        }
        if (!z || a() || j2 != this.b || !g2.a().equalsIgnoreCase(this.d)) {
            return false;
        }
        return true;
    }
}
