package com.tencent.android.tpush.service.d;

import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.tencent.android.tpush.NotificationAction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.stat.StatReportStrategy;
import com.tencent.android.tpush.stat.b;
import com.tencent.android.tpush.stat.e;
import com.tencent.android.tpush.stat.event.c;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import java.util.ArrayList;
import java.util.Properties;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    private static Context a = null;

    public static void a(Context context) {
        b.b(true);
        b.a(StatReportStrategy.INSTANT);
        e.b(context);
        e.e(context);
        a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public static void a() {
        e.a(a, -1);
    }

    public static void a(ArrayList<TpnsPushMsg> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            try {
                e.a(a, arrayList);
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("XgStat", "reportSrvAck", th);
            }
        }
    }

    public static void a(Intent intent) {
        if (intent != null) {
            try {
                long longExtra = intent.getLongExtra("type", 0);
                long longExtra2 = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
                long longExtra3 = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
                long longExtra4 = intent.getLongExtra(MessageKey.MSG_ID, 0);
                long longExtra5 = intent.getLongExtra("accId", 0);
                Properties properties = new Properties();
                properties.setProperty("type", "" + longExtra);
                properties.setProperty(MessageKey.MSG_BUSI_MSG_ID, "" + longExtra2);
                properties.setProperty(MessageKey.MSG_ID, "" + longExtra4);
                e.a(a, "SdkAck", properties, longExtra5, longExtra3);
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("XgStat", "reportSDKAck", th);
            }
        }
    }

    public static void a(Context context, String str, JSONObject jSONObject) {
        try {
            a(context, (com.tencent.android.tpush.stat.event.b) new com.tencent.android.tpush.stat.event.a(context, str, jSONObject, "Axg" + XGPushConfig.getAccessId(context), true));
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("XgStat", "reportXGStat ", th);
        }
    }

    public static void b(Context context, String str, JSONObject jSONObject) {
        try {
            a(context, (com.tencent.android.tpush.stat.event.b) new c(context, str, jSONObject, "Axg" + XGPushConfig.getAccessId(context), true));
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("XgStat", "reportXGLBS ", th);
        }
    }

    public static void a(Context context, com.tencent.android.tpush.stat.event.b bVar) {
        e.a(context, bVar);
    }

    public static void b(ArrayList<TpnsPushClientReport> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            com.tencent.android.tpush.b.a.i("XgStat", "ServiceStat reportAck 15 with null list ");
            return;
        }
        try {
            e.b(a, arrayList);
        } catch (Throwable th) {
            com.tencent.android.tpush.b.a.d("XgStat", "reportAck", th);
        }
    }

    public static void c(ArrayList<TpnsClickClientReport> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            try {
                e.c(a, arrayList);
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("XgStat", "reportNotifactionClickedOrClear", th);
            }
        }
    }

    private static void a(Context context, Intent intent, String str) {
        if (intent != null && !l.c(str)) {
            try {
                long longExtra = intent.getLongExtra(MessageKey.MSG_ID, 0);
                if (longExtra >= 0) {
                    long longExtra2 = intent.getLongExtra("type", 1);
                    long longExtra3 = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
                    long longExtra4 = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
                    long longExtra5 = intent.getLongExtra("accId", 0);
                    Properties properties = new Properties();
                    properties.setProperty("type", "" + longExtra2);
                    properties.setProperty(MessageKey.MSG_BUSI_MSG_ID, "" + longExtra3);
                    properties.setProperty(MessageKey.MSG_ID, "" + longExtra);
                    if (str.equals("Action")) {
                        properties.put("action", "" + intent.getIntExtra("action", NotificationAction.clicked.getType()));
                    }
                    e.a(StubApp.getOrigApplicationContext(context.getApplicationContext()), str, properties, longExtra5, longExtra4);
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("XgStat", "reportSDKAck", th);
            }
        }
    }

    public static void a(Context context, Intent intent) {
        a(context, intent, "OtherPull");
    }

    public static void b(Context context, Intent intent) {
        a(context, intent, "SdkAck");
    }

    public static void c(Context context, Intent intent) {
        a(context, intent, "Verify");
    }

    public static void d(Context context, Intent intent) {
        a(context, intent, "SHOW");
    }

    public static void e(Context context, Intent intent) {
        a(context, intent, "Action");
    }

    public static void b(Context context) {
        boolean isImportImplClass = CustomDeviceInfos.isImportImplClass();
        Properties properties = new Properties();
        properties.setProperty("value", "" + isImportImplClass);
        e.a(a, "IsCustomDataVersion", properties, XGPushConfig.getAccessId(a));
    }
}
