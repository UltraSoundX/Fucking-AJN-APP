package com.tencent.android.tpush.c;

import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.c;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.e.i;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* compiled from: ProGuard */
public class f {
    static ArrayList<Long> a;
    /* access modifiers changed from: private */
    public static final String b = f.class.getSimpleName();
    private static volatile f c = null;
    private static long e = 0;
    /* access modifiers changed from: private */
    public Context d = null;

    /* compiled from: ProGuard */
    private class a implements Runnable {
        private final String b = a.class.getSimpleName();
        private Context c;
        private Intent d;
        private XGIOperateCallback e;

        public a(Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
            this.c = context;
            this.d = intent;
            this.e = xGIOperateCallback;
        }

        private void a() {
            Intent intent = new Intent(Constants.ACTION_PUSH_MESSAGE);
            intent.setPackage(this.c.getPackageName());
            intent.putExtras(this.d);
            this.c.sendBroadcast(intent);
            String stringExtra = this.d.getStringExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME);
            if (!i.b(stringExtra)) {
                Intent intent2 = new Intent("com.tencent.android.tpush.action.ack.sdk2srv.V4");
                intent2.setPackage(stringExtra);
                intent2.putExtras(this.d);
                this.c.sendBroadcast(intent2);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:103:0x0385, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
            com.tencent.android.tpush.b.a.d(r20.b, "unknown error", r2);
            com.tencent.android.tpush.c.c.a().d(r20.c, r3.b());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x03a0, code lost:
            r2 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
            com.tencent.android.tpush.b.a.d(com.tencent.android.tpush.common.Constants.ServiceLogTag, "push msg type error", r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cc, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            com.tencent.android.tpush.b.a.d("PushMessageHandler", "", r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d5, code lost:
            r2 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            com.tencent.android.tpush.b.a.d(r20.b, "push parse error", r2);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:107:0x03a0 A[ExcHandler: IllegalArgumentException (e java.lang.IllegalArgumentException), Splitter:B:7:0x0015] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00d5 A[ExcHandler: JSONException (e org.json.JSONException), Splitter:B:15:0x0048] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00e5 A[Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r20 = this;
                r0 = r20
                com.tencent.android.tpush.c.f r11 = com.tencent.android.tpush.c.f.this
                monitor-enter(r11)
                boolean r2 = com.tencent.android.tpush.XGPushConfig.enableDebug     // Catch:{ all -> 0x00f7 }
                if (r2 == 0) goto L_0x0012
                r0 = r20
                java.lang.String r2 = r0.b     // Catch:{ all -> 0x00f7 }
                java.lang.String r3 = "Action -> handlerPushMessage"
                com.tencent.android.tpush.b.a.c(r2, r3)     // Catch:{ all -> 0x00f7 }
            L_0x0012:
                r10 = 0
                r0 = r20
                android.content.Intent r2 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r3 = "expire_time"
                r4 = 0
                long r4 = r2.getLongExtra(r3, r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r2 = 0
                int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r2 > 0) goto L_0x0069
                r0 = r20
                android.content.Intent r2 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r3 = "server_time"
                r6 = -1
                long r2 = r2.getLongExtra(r3, r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r6 = 0
                int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r6 <= 0) goto L_0x0069
                r0 = r20
                android.content.Intent r4 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "ttl"
                r6 = 0
                int r4 = r4.getIntExtra(r5, r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r4 > 0) goto L_0x03ca
                r4 = 259200(0x3f480, float:3.63217E-40)
                r5 = r4
            L_0x0048:
                java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x00cc, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00cc, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x00cc, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                int r6 = r6.length()     // Catch:{ Throwable -> 0x00cc, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                int r4 = r4.length()     // Catch:{ Throwable -> 0x00cc, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                int r4 = r6 - r4
                r6 = 3
                if (r4 != r6) goto L_0x0064
                r6 = 1000(0x3e8, double:4.94E-321)
                long r2 = r2 * r6
            L_0x0064:
                int r4 = r5 * 1000
                long r4 = (long) r4
                long r2 = r2 + r4
                r4 = r2
            L_0x0069:
                r0 = r20
                android.content.Intent r2 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.getPackage()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                long r6 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r3 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r8 = "msgId"
                r12 = -1
                long r8 = r3.getLongExtra(r8, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r3 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r12 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.c.g r3 = com.tencent.android.tpush.c.g.a(r3, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r12 = 0
                int r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
                if (r12 <= 0) goto L_0x00fa
                int r12 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                if (r12 <= 0) goto L_0x00fa
                java.lang.String r2 = "PushMessageHandler"
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r10.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = "msg is expired, currentTimeMillis="
                java.lang.StringBuilder r10 = r10.append(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r6 = r10.append(r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r7 = ", expire_time="
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = r6.append(r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = ". msgid = "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.i(r2, r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.XGPushManager.msgAck(r2, r3)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
            L_0x00cb:
                return
            L_0x00cc:
                r4 = move-exception
                java.lang.String r6 = "PushMessageHandler"
                java.lang.String r7 = ""
                com.tencent.android.tpush.b.a.d(r6, r7, r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                goto L_0x0064
            L_0x00d5:
                r2 = move-exception
                r0 = r20
                java.lang.String r3 = r0.b     // Catch:{ all -> 0x00f7 }
                java.lang.String r4 = "push parse error"
                com.tencent.android.tpush.b.a.d(r3, r4, r2)     // Catch:{ all -> 0x00f7 }
            L_0x00df:
                r0 = r20
                com.tencent.android.tpush.XGIOperateCallback r3 = r0.e     // Catch:{ all -> 0x00f7 }
                if (r3 == 0) goto L_0x00f5
                if (r2 == 0) goto L_0x03be
                r0 = r20
                com.tencent.android.tpush.XGIOperateCallback r3 = r0.e     // Catch:{ all -> 0x00f7 }
                java.lang.String r4 = ""
                r5 = -1
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00f7 }
                r3.onFail(r4, r5, r2)     // Catch:{ all -> 0x00f7 }
            L_0x00f5:
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                goto L_0x00cb
            L_0x00f7:
                r2 = move-exception
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                throw r2
            L_0x00fa:
                java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                boolean r4 = com.tencent.android.tpush.c.f.a(r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r4 != 0) goto L_0x010d
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.XGPushManager.msgAck(r2, r3)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                goto L_0x00cb
            L_0x010d:
                r4 = 2
                com.tencent.android.tpush.b.a.a(r4, r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r4 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "busiMsgId"
                r6 = 0
                long r6 = r4.getLongExtra(r5, r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r4 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "timestamps"
                r12 = 0
                long r4 = r4.getLongExtra(r5, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r12.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r13 = "@"
                java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r12 = r12.append(r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r2 = r12.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = "@"
                java.lang.StringBuilder r2 = r2.append(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r12 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r13 = "accId"
                r14 = -1
                long r12 = r12.getLongExtra(r13, r14)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r14 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r15 = "channel_id"
                r16 = -1
                long r14 = r14.getLongExtra(r15, r16)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r0 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r16 = r0
                java.util.List r16 = com.tencent.android.tpush.XGPushConfig.getAccessidList(r16)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r18 = 0
                int r17 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
                if (r17 <= 0) goto L_0x01c7
                r0 = r20
                android.content.Context r0 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r17 = r0
                long r18 = com.tencent.android.tpush.XGPushConfig.getChannelId(r17)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                int r17 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
                if (r17 == 0) goto L_0x01c7
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r4.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "PushMessageRunnable match channel failed, message droped cause channel id:"
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = " === "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r5 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                long r6 = com.tencent.android.tpush.XGPushConfig.getChannelId(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = " msgId = "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                java.lang.String r4 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.j(r4, r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.c.c r2 = com.tencent.android.tpush.c.c.a()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r4 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r2.b(r4, r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.XGPushManager.msgAck(r2, r3)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                goto L_0x00cb
            L_0x01c7:
                r18 = 0
                int r14 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
                if (r14 > 0) goto L_0x0226
                if (r16 == 0) goto L_0x0226
                int r14 = r16.size()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r14 <= 0) goto L_0x0226
                java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r16
                boolean r14 = r0.contains(r14)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r14 != 0) goto L_0x0226
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r4.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "PushMessageRunnable match accessId failed, message droped cause accessId:"
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = r4.append(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = " not in "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r16
                java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = " msgId = "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                java.lang.String r4 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.j(r4, r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.c.c r2 = com.tencent.android.tpush.c.c.a()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r4 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r2.b(r4, r8)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.XGPushManager.msgAck(r2, r3)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                goto L_0x00cb
            L_0x0226:
                r0 = r20
                android.content.Context r14 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r14 = com.tencent.android.tpush.c.c.g(r14, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                boolean r15 = r14.contains(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r15 != 0) goto L_0x03aa
                r0 = r20
                android.content.Context r15 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r16 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r16.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r17 = "tpush_msgId_"
                java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r16
                java.lang.StringBuilder r16 = r0.append(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r16 = r16.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r17.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r17
                java.lang.StringBuilder r17 = r0.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r17
                java.lang.StringBuilder r14 = r0.append(r14)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r14 = r14.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r17 = 1
                r0 = r16
                r1 = r17
                com.tencent.android.tpush.common.g.a(r15, r0, r14, r1)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r14 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r15.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r16 = "tpush_msgId_"
                java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r12 = r15.append(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r13 = 1
                java.lang.String r12 = com.tencent.android.tpush.common.g.a(r14, r12, r13)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r12 == 0) goto L_0x028f
                boolean r12 = r12.contains(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r12 != 0) goto L_0x02ac
            L_0x028f:
                r0 = r20
                java.lang.String r3 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r4.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r4 = " flag write failed"
                java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.i(r3, r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                monitor-exit(r11)     // Catch:{ all -> 0x00f7 }
                goto L_0x00cb
            L_0x02ac:
                boolean r2 = com.tencent.android.tpush.XGPushConfig.enableDebug     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r2 == 0) goto L_0x02ce
                r0 = r20
                java.lang.String r2 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r12.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r13 = "Receiver msg from server :"
                java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r13 = r3.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.f(r2, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
            L_0x02ce:
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.XGPushManager.msgAck(r2, r3)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r12 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.service.d.a.b(r2, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r2 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = "svrPkgName"
                java.lang.String r2 = r2.getStringExtra(r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r12 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r12 = r12.getPackageName()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                boolean r12 = r12.equals(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r12 != 0) goto L_0x031d
                r0 = r20
                java.lang.String r12 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r13.<init>()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r14 = "Receiver msg from other app :"
                java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.StringBuilder r2 = r13.append(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.b.a.f(r12, r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Intent r12 = r0.d     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.service.d.a.a(r2, r12)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
            L_0x031d:
                com.tencent.android.tpush.c.a r12 = r3.g()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r12 == 0) goto L_0x03af
                java.lang.String r2 = r3.f()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                boolean r2 = com.tencent.android.tpush.service.e.i.b(r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                if (r2 != 0) goto L_0x03af
                com.tencent.android.tpush.c.d r2 = new com.tencent.android.tpush.c.d     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Context r13 = r0.c     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Intent r14 = r0.d     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r2.<init>(r13, r14)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                boolean r2 = r2.a(r3, r4, r6, r8)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                if (r2 == 0) goto L_0x0375
                r20.a()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Intent r4 = r0.d     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                com.tencent.android.tpush.service.d.a.c(r2, r4)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                com.tencent.android.tpush.c.c r2 = com.tencent.android.tpush.c.c.a()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Context r4 = r0.c     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                long r6 = r3.b()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r2.c(r4, r6)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                int r2 = r12.c()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r4 = 1
                if (r2 != r4) goto L_0x0372
                r3.a()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Context r2 = r0.c     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Intent r4 = r0.d     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                com.tencent.android.tpush.service.d.a.d(r2, r4)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
            L_0x0372:
                r2 = r10
                goto L_0x00df
            L_0x0375:
                com.tencent.android.tpush.c.c r2 = com.tencent.android.tpush.c.c.a()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r0 = r20
                android.content.Context r4 = r0.c     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                long r6 = r3.b()     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                r2.d(r4, r6)     // Catch:{ Throwable -> 0x0385, JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0 }
                goto L_0x0372
            L_0x0385:
                r2 = move-exception
                r0 = r20
                java.lang.String r4 = r0.b     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                java.lang.String r5 = "unknown error"
                com.tencent.android.tpush.b.a.d(r4, r5, r2)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                com.tencent.android.tpush.c.c r4 = com.tencent.android.tpush.c.c.a()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r0 = r20
                android.content.Context r5 = r0.c     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                long r6 = r3.b()     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                r4.d(r5, r6)     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
                goto L_0x00df
            L_0x03a0:
                r2 = move-exception
                java.lang.String r3 = "XGService"
                java.lang.String r4 = "push msg type error"
                com.tencent.android.tpush.b.a.d(r3, r4, r2)     // Catch:{ all -> 0x00f7 }
                goto L_0x00df
            L_0x03aa:
                r2 = 0
                r0 = r20
                r0.e = r2     // Catch:{ JSONException -> 0x00d5, IllegalArgumentException -> 0x03a0, Throwable -> 0x03b2 }
            L_0x03af:
                r2 = r10
                goto L_0x00df
            L_0x03b2:
                r2 = move-exception
                r0 = r20
                java.lang.String r3 = r0.b     // Catch:{ all -> 0x00f7 }
                java.lang.String r4 = "unknown error"
                com.tencent.android.tpush.b.a.d(r3, r4, r2)     // Catch:{ all -> 0x00f7 }
                goto L_0x00df
            L_0x03be:
                r0 = r20
                com.tencent.android.tpush.XGIOperateCallback r2 = r0.e     // Catch:{ all -> 0x00f7 }
                java.lang.String r3 = ""
                r4 = 0
                r2.onSuccess(r3, r4)     // Catch:{ all -> 0x00f7 }
                goto L_0x00f5
            L_0x03ca:
                r5 = r4
                goto L_0x0048
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.c.f.a.run():void");
        }
    }

    public static f a(Context context) {
        if (c == null) {
            synchronized (f.class) {
                if (c == null) {
                    c = new f();
                    c.d = StubApp.getOrigApplicationContext(context.getApplicationContext());
                    b.d(c.d);
                }
            }
        }
        return c;
    }

    public void a(Intent intent) {
        c.a().a((Runnable) new a(this.d, intent, null));
    }

    protected static synchronized boolean a(Long l) {
        boolean z = false;
        synchronized (f.class) {
            try {
                if (a == null) {
                    a = new ArrayList<>();
                }
                if (!a.contains(l)) {
                    a.add(l);
                    if (a.size() > 200) {
                        a.remove(0);
                    }
                    z = true;
                }
            } catch (Throwable th) {
                com.tencent.android.tpush.b.a.d("PushMessageHandler", "addCachedmsgID", th);
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void c(final Intent intent) {
        c.a().a((Runnable) new Runnable() {
            public void run() {
                String stringExtra = intent.getStringExtra(MessageKey.MSG_DATE);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    if (i.b(stringExtra) || (!i.b(stringExtra) && simpleDateFormat.parse(stringExtra).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) == 0)) {
                        if (i.a(intent)) {
                            f.this.a(intent);
                        }
                    } else if (!i.b(stringExtra) && simpleDateFormat.parse(stringExtra).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) < 0) {
                        f.this.a(intent);
                    }
                } catch (ParseException e) {
                    com.tencent.android.tpush.b.a.j(f.b, "try to handlerPushMessage, but ParseException : " + e);
                }
            }
        });
    }

    public void b(final Intent intent) {
        c.a().a((Runnable) new Runnable() {
            public void run() {
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.c(f.b, "Action -> handleRemotePushMessage");
                }
                long longExtra = intent.getLongExtra(MessageKey.MSG_ID, 0);
                long longExtra2 = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, 0);
                long longExtra3 = intent.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
                int intExtra = intent.getIntExtra(MessageKey.MSG_TTL, 0);
                long longExtra4 = intent.getLongExtra("type", 1);
                if (!XGPushConfig.isNotificationShowEnable(f.this.d)) {
                    com.tencent.android.tpush.b.a.f(f.b, "XINGE NotificationShow is not enabe, so discard this notification, msgid:" + longExtra);
                    return;
                }
                long longExtra5 = intent.getLongExtra("accId", 0);
                String str = intent.getPackage();
                try {
                    RegisterEntity currentAppRegisterEntity = CacheManager.getCurrentAppRegisterEntity(f.this.d);
                    if (currentAppRegisterEntity != null && !i.b(currentAppRegisterEntity.packageName) && str.equals(currentAppRegisterEntity.packageName) && longExtra5 == currentAppRegisterEntity.accessId && currentAppRegisterEntity.state == 1) {
                        return;
                    }
                } catch (Throwable th) {
                    com.tencent.android.tpush.b.a.i(f.b, th.toString());
                }
                String stringExtra = intent.getStringExtra(MessageKey.MSG_DATE);
                long longExtra6 = intent.getLongExtra(MessageKey.MSG_EXTRA_PUSHTIME, 0);
                long longExtra7 = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
                long longExtra8 = intent.getLongExtra(MessageKey.MSG_CREATE_MULTIPKG, 0);
                long longExtra9 = intent.getLongExtra(MessageKey.MSG_CHANNEL_ID, -1);
                String stringExtra2 = intent.getStringExtra(MessageKey.MSG_GROUP_KEYS);
                String stringExtra3 = intent.getStringExtra(MessageKey.MSG_STAT_TAG);
                long currentTimeMillis = System.currentTimeMillis();
                MessageId messageId = new MessageId();
                messageId.id = longExtra;
                messageId.isAck = 0;
                messageId.accessId = longExtra5;
                messageId.host = intent.getLongExtra(MessageKey.MSG_EXTRA_HOST, 0);
                messageId.port = intent.getIntExtra(MessageKey.MSG_EXTRA_PORT, 0);
                messageId.pact = intent.getByteExtra(MessageKey.MSG_EXTRA_PACT, 0);
                messageId.apn = DeviceInfos.getNetworkType(f.this.d);
                messageId.isp = i.k(f.this.d);
                messageId.pushTime = longExtra6;
                messageId.serviceHost = intent.getStringExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME);
                messageId.receivedTime = currentTimeMillis;
                messageId.pkgName = str;
                messageId.busiMsgId = longExtra7;
                messageId.timestamp = longExtra2;
                messageId.msgType = longExtra4;
                messageId.multiPkg = longExtra8;
                messageId.date = stringExtra;
                messageId.channelId = longExtra9;
                if (!i.b(stringExtra2)) {
                    messageId.groupKeys = stringExtra2;
                }
                if (!i.b(stringExtra3)) {
                    messageId.statTag = stringExtra3;
                }
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.b.a.f(f.b, ">> msg from service,  @msgId=" + messageId.id + " @accId=" + messageId.accessId + " @timeUs=" + longExtra6 + " @recTime=" + messageId.receivedTime + " @msg.date=" + stringExtra + " @msg.busiMsgId=" + longExtra7 + " @msg.timestamp=" + longExtra2 + " @msg.type=" + longExtra4 + " @msg.multiPkg=" + longExtra8 + " @msg.serverTime=" + longExtra3 + " @msg.ttl=" + intExtra + " @currentTimeMillis=" + currentTimeMillis);
                }
                if (c.g(f.this.d, longExtra5).contains("@" + messageId.id + str + "@")) {
                    com.tencent.android.tpush.b.a.j(f.b, "getNotifiedMsgIds contain the msgId id, return");
                    return;
                }
                if (c.a().b(f.this.d, str, messageId.id)) {
                    com.tencent.android.tpush.b.a.j(f.b, ">> msgId:" + messageId.id + " has been acked, return");
                    return;
                }
                messageId.pkgName = str;
                if (messageId.id > 0) {
                    c.a().a(f.this.d, str, messageId);
                }
                c.a().a(f.this.d, intent);
                f.this.c(intent);
            }
        });
    }

    public void a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - e > 120000 || z) {
            e = currentTimeMillis;
            c.a().a((Runnable) new Runnable() {
                public void run() {
                    if (f.this.d != null && !i.b(f.this.d.getPackageName())) {
                        ArrayList a2 = c.a().a(f.this.d);
                        if (a2 != null && a2.size() > 0) {
                            if (XGPushConfig.enableDebug) {
                                com.tencent.android.tpush.b.a.c(f.b, "Action -> trySendCachedMsg with CachedMsgList size = " + a2.size());
                            }
                            int i = 0;
                            while (true) {
                                int i2 = i;
                                if (i2 < a2.size()) {
                                    try {
                                        f.this.c((Intent) a2.get(i2));
                                    } catch (Exception e) {
                                        com.tencent.android.tpush.b.a.d(f.b, "", e);
                                    }
                                    i = i2 + 1;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
