package com.tencent.android.tpush.cloudctr.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.cloudctr.b.c;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.stat.XGPatchMonitor;
import java.util.Date;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    private c a = new c("cloud control network thread");
    /* access modifiers changed from: private */
    public com.tencent.android.tpush.cloudctr.a.a b;

    public a(com.tencent.android.tpush.cloudctr.a.a aVar) {
        this.b = aVar;
    }

    public void a(Context context, String str, long j) {
        final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        final String str2 = str;
        final long j2 = j;
        this.a.a(new Runnable() {
            public void run() {
                try {
                    long accessId = XGPushConfig.getAccessId(origApplicationContext);
                    String accessKey = XGPushConfig.getAccessKey(origApplicationContext);
                    if (accessId > 0 && !l.c(accessKey)) {
                        Date a2 = a.this.b.a(origApplicationContext, str2, accessId);
                        if (a2 == null || System.currentTimeMillis() - a2.getTime() >= 259200000) {
                            Intent intent = new Intent("com.tencent.android.tpush.action.REGISTER.V4");
                            intent.putExtra("accId", Rijndael.encrypt("" + accessId));
                            intent.putExtra("accChannel", XGPushConfig.getChannelId(origApplicationContext));
                            intent.putExtra("accKey", Rijndael.encrypt(accessKey));
                            intent.putExtra("currentTimeMillis", System.currentTimeMillis());
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("ccver", 1);
                                jSONObject.put("cccheck", 1);
                                jSONObject.put("cccfgver", j2);
                                jSONObject.put("ccbuscode", str2);
                            } catch (Exception e) {
                            }
                            intent.putExtra("reserved", Rijndael.encrypt(jSONObject.toString()));
                            XGPatchMonitor.onConfigAction(origApplicationContext, accessId, str2, XGPatchMonitor.ActionRequestConfig, 0, null, null);
                            origApplicationContext.sendBroadcast(intent);
                            Log.d("cloud control", "send broadcast");
                            a.this.b.a(origApplicationContext, str2, accessId, new Date());
                            return;
                        }
                        com.tencent.android.tpush.b.a.c("cloud control", "未达到云控注册时间间隔，忽略这次注册");
                    }
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.i("cloud control", e2.toString());
                }
            }
        });
    }
}
