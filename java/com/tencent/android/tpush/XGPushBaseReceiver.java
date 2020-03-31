package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.c.g;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* compiled from: ProGuard */
public abstract class XGPushBaseReceiver extends BroadcastReceiver {
    public static final int SUCCESS = 0;
    long a = 0;

    public abstract void onDeleteTagResult(Context context, int i, String str);

    public abstract void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult);

    public abstract void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult);

    public abstract void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult);

    public abstract void onSetTagResult(Context context, int i, String str);

    public abstract void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage);

    public abstract void onUnregisterResult(Context context, int i);

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (l.a(context) <= 0) {
                    String action = intent.getAction();
                    if (Constants.ACTION_PUSH_MESSAGE.equals(action)) {
                        a(context, intent);
                    } else if (Constants.ACTION_FEEDBACK.equals(action)) {
                        b(context, intent);
                    } else {
                        a.i(Constants.PushMessageLogTag, "未知的action:" + action);
                    }
                }
            } catch (Throwable th) {
                a.d(Constants.PushMessageLogTag, "onReceive handle error.", th);
            }
        }
    }

    private void a(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(Constants.PUSH_CHANNEL, 0);
        if (intExtra == 0) {
            g a2 = g.a(context, intent);
            if (a2.g().c() == 2) {
                XGPushTextMessage xGPushTextMessage = new XGPushTextMessage();
                xGPushTextMessage.title = a2.g().e();
                xGPushTextMessage.content = a2.g().f();
                xGPushTextMessage.customContent = a2.g().g();
                xGPushTextMessage.setSimpleIntent(intent);
                onTextMessage(context, xGPushTextMessage);
                return;
            }
            return;
        }
        XGPushTextMessage xGPushTextMessage2 = new XGPushTextMessage();
        xGPushTextMessage2.pushChannel = intExtra;
        xGPushTextMessage2.content = intent.getStringExtra("content");
        xGPushTextMessage2.title = intent.getStringExtra("title");
        xGPushTextMessage2.customContent = intent.getStringExtra("custom_content");
        intent.putExtra("accId", XGPushConfig.getAccessId(context));
        c(context, intent);
        onTextMessage(context, xGPushTextMessage2);
    }

    private void b(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(Constants.FEEDBACK_TAG, -1);
        int intExtra2 = intent.getIntExtra(Constants.FEEDBACK_ERROR_CODE, -1);
        switch (intExtra) {
            case 1:
                XGPushRegisterResult xGPushRegisterResult = new XGPushRegisterResult();
                if (!intent.getBooleanExtra(Constants.FLAG_REGISTER_FROM_CLOUDCTRL, false)) {
                    if (intent.getIntExtra(Constants.PUSH_CHANNEL, 0) == 0) {
                        xGPushRegisterResult.parseIntent(intent);
                        a(context, intExtra2, xGPushRegisterResult, "SdkRegister");
                    } else {
                        xGPushRegisterResult.h = intent.getIntExtra(Constants.PUSH_CHANNEL, 0);
                        xGPushRegisterResult.g = intent.getStringExtra(Constants.OTHER_PUSH_TOKEN);
                    }
                    onRegisterResult(context, intExtra2, xGPushRegisterResult);
                    return;
                }
                return;
            case 2:
                XGPushRegisterResult xGPushRegisterResult2 = new XGPushRegisterResult();
                xGPushRegisterResult2.parseIntent(intent);
                a(context, intExtra2, xGPushRegisterResult2, "SdkUnRegister");
                onUnregisterResult(context, intExtra2);
                return;
            case 3:
                String decrypt = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TAG_NAME));
                if (!i.b(decrypt)) {
                    int intExtra3 = intent.getIntExtra(Constants.FLAG_TAG_TYPE, -1);
                    String stringExtra = intent.getStringExtra(Constants.FLAG_TAG_OPER_NAME);
                    if (intExtra3 == 1 || intExtra3 == 6 || intExtra3 == 5) {
                        onSetTagResult(context, intExtra2, stringExtra);
                        return;
                    } else if (intExtra3 == 2 || intExtra3 == 7 || intExtra3 == 8) {
                        onDeleteTagResult(context, intExtra2, stringExtra);
                        return;
                    } else {
                        a.i(Constants.PushMessageLogTag, "错误的标签处理类型：" + intExtra3 + " ,标签名：" + decrypt);
                        return;
                    }
                } else {
                    return;
                }
            case 4:
                intent.getIntExtra("action", NotificationAction.delete.getType());
                long longExtra = intent.getLongExtra("accId", 0);
                List accessidList = XGPushConfig.getAccessidList(context);
                if (accessidList == null || accessidList.size() <= 0) {
                    a.i("XGPushBaseReceiver", "accessIdList " + accessidList + " local accessid " + longExtra);
                    a.i("XGPushBaseReceiver", "give up msg");
                } else if (accessidList.contains(Long.valueOf(longExtra))) {
                    XGPushClickedResult xGPushClickedResult = new XGPushClickedResult();
                    xGPushClickedResult.parseIntent(intent);
                    onNotifactionClickedResult(context, xGPushClickedResult);
                }
                if (intent.getIntExtra(Constants.PUSH_CHANNEL, 0) != 0) {
                    XGPushClickedResult xGPushClickedResult2 = new XGPushClickedResult();
                    xGPushClickedResult2.content = intent.getStringExtra("content");
                    xGPushClickedResult2.title = intent.getStringExtra("title");
                    xGPushClickedResult2.customContent = intent.getStringExtra("custom_content");
                    xGPushClickedResult2.pushChannel = intent.getIntExtra(Constants.PUSH_CHANNEL, 0);
                    xGPushClickedResult2.actionType = intent.getIntExtra("action", 0);
                    intent.putExtra("accId", XGPushConfig.getAccessId(context));
                    Intent intent2 = new Intent("com.tencent.android.tpush.action.PUSH_CLICK.RESULT.V4");
                    intent2.putExtras(intent);
                    context.sendBroadcast(intent2);
                    onNotifactionClickedResult(context, xGPushClickedResult2);
                    return;
                }
                return;
            case 5:
                XGPushShowedResult xGPushShowedResult = new XGPushShowedResult();
                if (intent.getIntExtra(Constants.PUSH_CHANNEL, 0) == 0) {
                    xGPushShowedResult.parseIntent(intent);
                } else {
                    xGPushShowedResult.c = intent.getStringExtra("content");
                    xGPushShowedResult.b = intent.getStringExtra("title");
                    xGPushShowedResult.d = intent.getStringExtra("custom_content");
                    xGPushShowedResult.h = intent.getIntExtra(Constants.PUSH_CHANNEL, 0);
                    intent.putExtra("accId", XGPushConfig.getAccessId(context));
                    c(context, intent);
                }
                onNotifactionShowedResult(context, xGPushShowedResult);
                return;
            default:
                a.i(Constants.PushMessageLogTag, "未知的feedbackType:" + intExtra);
                return;
        }
    }

    private void c(Context context, Intent intent) {
        MessageId messageId = new MessageId();
        messageId.id = intent.getLongExtra(MessageKey.MSG_ID, 0);
        messageId.isAck = 0;
        messageId.accessId = intent.getLongExtra("accId", 0);
        messageId.host = intent.getLongExtra(MessageKey.MSG_EXTRA_HOST, 0);
        messageId.port = intent.getIntExtra(MessageKey.MSG_EXTRA_PORT, 0);
        messageId.pact = intent.getByteExtra(MessageKey.MSG_EXTRA_PACT, 0);
        messageId.apn = DeviceInfos.getNetworkType(context);
        messageId.isp = i.k(context);
        messageId.pushTime = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
        messageId.serviceHost = intent.getStringExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME);
        messageId.receivedTime = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
        messageId.pkgName = context.getPackageName();
        messageId.busiMsgId = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
        messageId.timestamp = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
        messageId.msgType = intent.getLongExtra("type", 0);
        messageId.multiPkg = intent.getLongExtra(MessageKey.MSG_CREATE_MULTIPKG, 0);
        messageId.date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        intent.putExtra("MessageId", messageId);
        Intent intent2 = new Intent("com.tencent.android.tpush.action.MSG_ACK.V4");
        intent2.putExtras(intent);
        context.sendBroadcast(intent2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066 A[Catch:{ Exception -> 0x010b }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r15, int r16, com.tencent.android.tpush.XGPushRegisterResult r17, java.lang.String r18) {
        /*
            r14 = this;
            r0 = r17
            short r2 = r0.e
            int r2 = r2 >> 4
            if (r2 <= 0) goto L_0x000c
            r3 = 4
            if (r2 > r3) goto L_0x000c
        L_0x000b:
            return
        L_0x000c:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r2 = "register_json"
            java.lang.String r4 = ""
            java.lang.String r4 = com.tencent.android.tpush.service.e.h.a(r15, r2, r4)
            boolean r2 = com.tencent.android.tpush.service.e.i.b(r4)
            if (r2 != 0) goto L_0x00f7
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x00de }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x00de }
        L_0x0024:
            java.lang.String r3 = "suc_cnt"
            r4 = 0
            int r4 = r2.optInt(r3, r4)
            java.lang.String r3 = "failed_cnt"
            r5 = 0
            int r3 = r2.optInt(r3, r5)
            if (r16 != 0) goto L_0x00fa
            int r4 = r4 + 1
            r5 = r3
            r6 = r4
        L_0x0038:
            java.lang.String r3 = "suc_cnt"
            r2.put(r3, r6)     // Catch:{ JSONException -> 0x0111 }
            java.lang.String r3 = "failed_cnt"
            r2.put(r3, r5)     // Catch:{ JSONException -> 0x0111 }
        L_0x0042:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            if (r17 == 0) goto L_0x0114
            org.json.JSONObject r3 = r17.toJson()
            r4 = r3
        L_0x004e:
            java.lang.String r3 = "errorCode"
            r0 = r16
            r4.put(r3, r0)     // Catch:{ Exception -> 0x010e }
            java.lang.String r3 = "np"
            byte r7 = com.tencent.bigdata.dataacquisition.DeviceInfos.getNetworkType(r15)     // Catch:{ Exception -> 0x010e }
            r4.put(r3, r7)     // Catch:{ Exception -> 0x010e }
        L_0x005e:
            java.lang.String r3 = "details"
            org.json.JSONArray r3 = r2.optJSONArray(r3)     // Catch:{ Exception -> 0x010b }
            if (r3 != 0) goto L_0x006b
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ Exception -> 0x010b }
            r3.<init>()     // Catch:{ Exception -> 0x010b }
        L_0x006b:
            r3.put(r4)     // Catch:{ Exception -> 0x010b }
            java.lang.String r4 = "details"
            r2.put(r4, r3)     // Catch:{ Exception -> 0x010b }
        L_0x0073:
            java.lang.String r3 = "SdkStat"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "new reprot js"
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r7 = r2.toString()
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.tencent.android.tpush.b.a.e(r3, r4)
            long r8 = java.lang.System.currentTimeMillis()
            long r10 = r14.a
            r12 = 0
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 != 0) goto L_0x00b6
            java.lang.String r3 = "register_last_report"
            r10 = 0
            long r10 = com.tencent.android.tpush.service.e.h.a(r15, r3, r10)
            r14.a = r10
            long r10 = r14.a
            r12 = 0
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 != 0) goto L_0x00b6
            r14.a = r8
            java.lang.String r3 = "register_last_report"
            long r10 = r14.a
            com.tencent.android.tpush.service.e.h.b(r15, r3, r10)
        L_0x00b6:
            int r3 = r6 + r5
            r4 = 10
            if (r3 >= r4) goto L_0x00c7
            long r4 = r14.a
            long r4 = r8 - r4
            r6 = 43200000(0x2932e00, double:2.1343636E-316)
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 < 0) goto L_0x0100
        L_0x00c7:
            r0 = r18
            com.tencent.android.tpush.service.d.a.a(r15, r0, r2)
            java.lang.String r2 = "register_json"
            java.lang.String r3 = ""
            com.tencent.android.tpush.service.e.h.b(r15, r2, r3)
            r14.a = r8
            java.lang.String r2 = "register_last_report"
            long r4 = r14.a
            com.tencent.android.tpush.service.e.h.b(r15, r2, r4)
            goto L_0x000b
        L_0x00de:
            r2 = move-exception
            java.lang.String r4 = "XGPushMessage"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "JSONObject"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r2 = r5.append(r2)
            java.lang.String r2 = r2.toString()
            com.tencent.android.tpush.b.a.i(r4, r2)
        L_0x00f7:
            r2 = r3
            goto L_0x0024
        L_0x00fa:
            int r3 = r3 + 1
            r5 = r3
            r6 = r4
            goto L_0x0038
        L_0x0100:
            java.lang.String r3 = "register_json"
            java.lang.String r2 = r2.toString()
            com.tencent.android.tpush.service.e.h.b(r15, r3, r2)
            goto L_0x000b
        L_0x010b:
            r3 = move-exception
            goto L_0x0073
        L_0x010e:
            r3 = move-exception
            goto L_0x005e
        L_0x0111:
            r3 = move-exception
            goto L_0x0042
        L_0x0114:
            r4 = r3
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.XGPushBaseReceiver.a(android.content.Context, int, com.tencent.android.tpush.XGPushRegisterResult, java.lang.String):void");
    }
}
