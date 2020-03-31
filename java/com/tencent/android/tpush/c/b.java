package com.tencent.android.tpush.c;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.android.tpush.NotificationAction;
import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;
import com.tencent.android.tpush.XGPushNotificationBuilder;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.h;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    private static volatile BroadcastReceiver a = null;

    private static String a(int i) {
        return "TPUSH_NOTIF_BUILDID_" + String.valueOf(i);
    }

    public static synchronized XGPushNotificationBuilder a(Context context) {
        XGPushNotificationBuilder flags;
        synchronized (b.class) {
            flags = new XGBasicPushNotificationBuilder().setFlags(16);
        }
        return flags;
    }

    public static void a(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        String a2 = a(i);
        JSONObject jSONObject = new JSONObject();
        xGPushNotificationBuilder.encode(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        com.tencent.android.tpush.common.b.a(jSONObject2, xGPushNotificationBuilder.getType(), (Object) jSONObject.toString());
        h.b(context, a2, jSONObject2.toString());
    }

    public static XGPushNotificationBuilder a(Context context, int i) {
        String string;
        XGPushNotificationBuilder xGPushNotificationBuilder = null;
        if (context != null) {
            String a2 = h.a(context, a(i), (String) null);
            if (a2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a2);
                    if (jSONObject.has(XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE)) {
                        XGBasicPushNotificationBuilder xGBasicPushNotificationBuilder = new XGBasicPushNotificationBuilder();
                        try {
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            string = jSONObject.getString(XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE);
                        } catch (JSONException e) {
                            Throwable th = e;
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            e = th;
                            a.d(Constants.LogTag, "", e);
                            return xGPushNotificationBuilder;
                        }
                    } else if (jSONObject.has(XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE)) {
                        XGCustomPushNotificationBuilder xGCustomPushNotificationBuilder = new XGCustomPushNotificationBuilder();
                        try {
                            xGPushNotificationBuilder = xGCustomPushNotificationBuilder;
                            string = jSONObject.getString(XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE);
                        } catch (JSONException e2) {
                            Throwable th2 = e2;
                            xGPushNotificationBuilder = xGCustomPushNotificationBuilder;
                            e = th2;
                            a.d(Constants.LogTag, "", e);
                            return xGPushNotificationBuilder;
                        }
                    }
                    xGPushNotificationBuilder.decode(string);
                } catch (JSONException e3) {
                    e = e3;
                }
            }
        }
        return xGPushNotificationBuilder;
    }

    public static String b(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(context.getPackageName());
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                if (resolveInfo.activityInfo != null) {
                    return resolveInfo.activityInfo.name;
                }
            }
        } catch (Throwable th) {
            a.d("MessageHelper", "get Activity error", th);
        }
        return null;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent a(android.content.Context r6, com.tencent.android.tpush.c.e.a r7, boolean r8, com.tencent.android.tpush.c.g r9) {
        /*
            r0 = 0
            int r1 = r7.a
            com.tencent.android.tpush.NotificationAction r1 = com.tencent.android.tpush.NotificationAction.getNotificationAction(r1)
            int[] r2 = com.tencent.android.tpush.c.b.AnonymousClass2.a
            int r1 = r1.ordinal()
            r1 = r2[r1]
            switch(r1) {
                case 1: goto L_0x0036;
                case 2: goto L_0x00a5;
                case 3: goto L_0x00d4;
                case 4: goto L_0x00d4;
                case 5: goto L_0x012b;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.String r1 = "MessageHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unkown type"
            java.lang.StringBuilder r2 = r2.append(r3)
            int r3 = r7.a
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tencent.android.tpush.b.a.i(r1, r2)
        L_0x002c:
            if (r0 == 0) goto L_0x0035
            java.lang.String r1 = "action_confirm"
            int r2 = r7.g
            r0.putExtra(r1, r2)
        L_0x0035:
            return r0
        L_0x0036:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r0 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE"
            r2.<init>(r0)
            java.lang.String r0 = r7.b
            boolean r1 = com.tencent.android.tpush.common.l.c(r0)
            if (r1 == 0) goto L_0x0049
            java.lang.String r0 = b(r6)
        L_0x0049:
            r1 = 538968064(0x20200000, float:1.3552527E-19)
            com.tencent.android.tpush.c.e$a$a r3 = r7.c
            if (r3 == 0) goto L_0x0055
            com.tencent.android.tpush.c.e$a$a r3 = r7.c
            int r3 = r3.a
            if (r3 > 0) goto L_0x009d
        L_0x0055:
            if (r8 == 0) goto L_0x0059
            r1 = 268435456(0x10000000, float:2.5243549E-29)
        L_0x0059:
            r2.addFlags(r1)
            r1 = 67239936(0x4020000, float:1.5281427E-36)
            r2.setFlags(r1)
        L_0x0061:
            java.lang.String r1 = "activity"
            r2.putExtra(r1, r0)
            java.lang.String r0 = "msgId"
            long r4 = r9.b()
            r2.putExtra(r0, r4)
            java.lang.String r0 = "busiMsgId"
            long r4 = r9.d()
            r2.putExtra(r0, r4)
            java.lang.String r0 = "notificationActionType"
            com.tencent.android.tpush.NotificationAction r1 = com.tencent.android.tpush.NotificationAction.activity
            int r1 = r1.getType()
            r2.putExtra(r0, r1)
            java.lang.String r0 = "action_type"
            com.tencent.android.tpush.NotificationAction r1 = com.tencent.android.tpush.NotificationAction.activity
            int r1 = r1.getType()
            r2.putExtra(r0, r1)
            android.content.Context r0 = r6.getApplicationContext()
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)
            java.lang.Class<com.tencent.android.tpush.XGPushActivity> r1 = com.tencent.android.tpush.XGPushActivity.class
            r2.setClass(r0, r1)
            r0 = r2
            goto L_0x002c
        L_0x009d:
            com.tencent.android.tpush.c.e$a$a r1 = r7.c
            int r1 = r1.a
            r2.setFlags(r1)
            goto L_0x0061
        L_0x00a5:
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE"
            r0.<init>(r1)
            java.lang.String r1 = "activity"
            java.lang.String r2 = r7.f
            r0.putExtra(r1, r2)
            java.lang.String r1 = "action_type"
            int r2 = r7.a
            r0.putExtra(r1, r2)
            java.lang.String r1 = "notificationActionType"
            com.tencent.android.tpush.NotificationAction r2 = com.tencent.android.tpush.NotificationAction.url
            int r2 = r2.getType()
            r0.putExtra(r1, r2)
            android.content.Context r1 = r6.getApplicationContext()
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)
            java.lang.Class<com.tencent.android.tpush.XGPushActivity> r2 = com.tencent.android.tpush.XGPushActivity.class
            r0.setClass(r1, r2)
            goto L_0x002c
        L_0x00d4:
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE"
            r0.<init>(r1)
            java.lang.String r1 = "activity"
            java.lang.String r2 = r7.d
            r0.putExtra(r1, r2)
            java.lang.String r1 = "action_type"
            int r2 = r7.a
            r0.putExtra(r1, r2)
            int r1 = r7.a
            com.tencent.android.tpush.NotificationAction r2 = com.tencent.android.tpush.NotificationAction.intent_with_action
            int r2 = r2.getType()
            if (r1 != r2) goto L_0x011f
            java.lang.String r1 = "notificationActionType"
            com.tencent.android.tpush.NotificationAction r2 = com.tencent.android.tpush.NotificationAction.intent_with_action
            int r2 = r2.getType()
            r0.putExtra(r1, r2)
        L_0x00fe:
            java.lang.String r1 = "msgId"
            long r2 = r9.b()
            r0.putExtra(r1, r2)
            java.lang.String r1 = "busiMsgId"
            long r2 = r9.d()
            r0.putExtra(r1, r2)
            android.content.Context r1 = r6.getApplicationContext()
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)
            java.lang.Class<com.tencent.android.tpush.XGPushActivity> r2 = com.tencent.android.tpush.XGPushActivity.class
            r0.setClass(r1, r2)
            goto L_0x002c
        L_0x011f:
            java.lang.String r1 = "notificationActionType"
            com.tencent.android.tpush.NotificationAction r2 = com.tencent.android.tpush.NotificationAction.intent
            int r2 = r2.getType()
            r0.putExtra(r1, r2)
            goto L_0x00fe
        L_0x012b:
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE"
            r0.<init>(r1)
            java.lang.String r1 = r7.h
            boolean r2 = com.tencent.android.tpush.common.l.c(r1)
            if (r2 != 0) goto L_0x0035
            java.lang.String r2 = "action_type"
            int r3 = r7.a
            r0.putExtra(r2, r3)
            java.lang.String r2 = "packageDownloadUrl"
            java.lang.String r3 = r7.j
            r0.putExtra(r2, r3)
            java.lang.String r2 = "packageName"
            r0.putExtra(r2, r1)
            java.lang.String r2 = "activity"
            r0.putExtra(r2, r1)
            java.lang.String r1 = "notificationActionType"
            com.tencent.android.tpush.NotificationAction r2 = com.tencent.android.tpush.NotificationAction.action_package
            int r2 = r2.getType()
            r0.putExtra(r1, r2)
            android.content.Context r1 = r6.getApplicationContext()
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)
            java.lang.Class<com.tencent.android.tpush.XGPushActivity> r2 = com.tencent.android.tpush.XGPushActivity.class
            r0.setClass(r1, r2)
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.c.b.a(android.content.Context, com.tencent.android.tpush.c.e$a, boolean, com.tencent.android.tpush.c.g):android.content.Intent");
    }

    public static void a(Context context, g gVar) {
        int i;
        e eVar = (e) gVar.g();
        e.a m = eVar.m();
        XGPushNotificationBuilder a2 = a(context, eVar.h());
        if (a2 == null || eVar.t() == 1) {
            if (a2 == null) {
                a2 = XGPushManager.getDefaultNotificationBuilder(context);
            }
            if (a2 == null) {
                a2 = a(context);
            }
            if (eVar.k() != 0) {
                a2.setFlags(16);
            }
            if (eVar.i() != 0) {
                if (!TextUtils.isEmpty(eVar.p())) {
                    int identifier = context.getResources().getIdentifier(eVar.p(), "raw", context.getPackageName());
                    if (identifier > 0) {
                        a2.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + identifier));
                    } else {
                        a2.setDefaults(1);
                    }
                } else {
                    a2.setDefaults(1);
                }
            }
            if (eVar.j() != 0) {
                a2.setDefaults(2);
            }
            if (eVar.o() != 0) {
                a2.setFlags(1);
            }
            String r = eVar.r();
            if (r != null && !TextUtils.isEmpty(r)) {
                int identifier2 = context.getResources().getIdentifier(r, "drawable", context.getPackageName());
                if (identifier2 > 0) {
                    a2.setSmallIcon(Integer.valueOf(identifier2));
                } else {
                    int identifier3 = context.getResources().getIdentifier("notification_icon", "drawable", context.getPackageName());
                    if (identifier3 > 0) {
                        a2.setSmallIcon(Integer.valueOf(identifier3));
                    } else {
                        a2.setSmallIcon(Integer.valueOf(context.getApplicationInfo().icon));
                    }
                }
            } else if (a2.getSmallIcon() == null) {
                int identifier4 = context.getResources().getIdentifier("notification_icon", "drawable", context.getPackageName());
                if (identifier4 > 0) {
                    a2.setSmallIcon(Integer.valueOf(identifier4));
                } else {
                    a2.setSmallIcon(Integer.valueOf(context.getApplicationInfo().icon));
                }
            }
            int s = eVar.s();
            String q2 = eVar.q();
            Integer num = null;
            if (a2 instanceof XGCustomPushNotificationBuilder) {
                num = ((XGCustomPushNotificationBuilder) a2).getLayoutIconId();
            }
            if (q2 == null || TextUtils.isEmpty(q2)) {
                if (a2.getLargeIcon() == null && a2.getNotificationLargeIcon() == null) {
                    a2.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
                }
            } else if (s <= 0) {
                int identifier5 = context.getResources().getIdentifier(q2, "drawable", context.getPackageName());
                if (identifier5 > 0) {
                    a2.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), identifier5));
                    if (num != null) {
                        ((XGCustomPushNotificationBuilder) a2).setLayoutIconDrawableId(identifier5);
                    }
                } else {
                    a2.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
                }
            } else {
                a(q2, a2, context, num);
            }
        }
        if (eVar.n() > 0) {
            a2.setIcon(Integer.valueOf(eVar.n()));
        }
        if (a2.getSmallIcon() == null && a2.getLargeIcon() == null && a2.getIcon() == null) {
            int identifier6 = context.getResources().getIdentifier("notification_icon", "drawable", context.getPackageName());
            if (identifier6 > 0) {
                a2.setSmallIcon(Integer.valueOf(identifier6));
            } else {
                a2.setSmallIcon(Integer.valueOf(context.getApplicationInfo().icon));
            }
            a2.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
        }
        if (eVar.e().length() >= 20) {
            a2.setTitle(eVar.e().substring(0, 20) + "...");
        } else {
            a2.setTitle(eVar.e());
        }
        a2.setTickerText(eVar.f());
        if (!TextUtils.isEmpty(eVar.u())) {
            a2.setChannelId(eVar.u());
        }
        if (!TextUtils.isEmpty(eVar.v())) {
            a2.setChannelName(eVar.v());
        }
        boolean z = false;
        String g = eVar.g();
        if (!l.c(g) && !"{}".equalsIgnoreCase(g)) {
            z = true;
        }
        Intent a3 = a(context, m, z, gVar);
        if (a3 == null) {
            a.i("MessageHelper", "intent is null");
            return;
        }
        if (z) {
            a3.putExtra("custom_content", eVar.g());
        }
        a3.putExtra(Constants.TAG_TPUSH_MESSAGE, "true");
        a3.putExtra("title", Rijndael.encrypt(eVar.e()));
        a3.putExtra("content", Rijndael.encrypt(eVar.f()));
        if (eVar.g() != null) {
            a3.putExtra("custom_content", Rijndael.encrypt(eVar.g()));
        }
        a3.putExtra(MessageKey.MSG_ID, gVar.b());
        a3.putExtra("accId", gVar.c());
        a3.putExtra(MessageKey.MSG_BUSI_MSG_ID, gVar.d());
        a3.putExtra(MessageKey.MSG_CREATE_TIMESTAMPS, gVar.e());
        a3.putExtra(MessageKey.MSG_PORTECT_TAG, Rijndael.encrypt("" + (System.currentTimeMillis() - 1000)));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int l = eVar.l();
        if (l <= 0) {
            i = b(context, eVar.h());
        } else {
            i = l;
        }
        if (i == -1) {
            notificationManager.cancelAll();
        }
        a3.putExtra(MessageKey.NOTIFACTION_ID, i);
        int i2 = 134217728;
        if (m.c != null && m.c.b > 0) {
            i2 = m.c.b;
        }
        if (a == null) {
            a = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String stringExtra = intent.getStringExtra(Constants.FLAG_PACK_NAME);
                    a.e("MessageHelper", "onReceive: Notification has canceled! pkg name: " + stringExtra);
                    if (!l.c(stringExtra) && stringExtra.equals(context.getPackageName())) {
                        c.a().f(context, intent.getLongExtra(MessageKey.MSG_ID, -1));
                        Intent intent2 = new Intent(Constants.ACTION_FEEDBACK);
                        intent2.setPackage(stringExtra);
                        intent2.putExtras(intent);
                        intent2.putExtra(Constants.FEEDBACK_TAG, 4);
                        context.sendBroadcast(intent2);
                        Intent intent3 = new Intent("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT.V4");
                        intent3.putExtras(intent);
                        com.tencent.android.tpush.service.d.a.e(context, intent3);
                        context.sendBroadcast(intent3);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(context.getPackageName() + ".APP_PUSH_CANCELLED.RESULT");
            StubApp.getOrigApplicationContext(context.getApplicationContext()).registerReceiver(a, intentFilter);
        }
        Intent intent = new Intent(context.getPackageName() + ".APP_PUSH_CANCELLED.RESULT");
        intent.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
        intent.putExtra("action", NotificationAction.delete.getType());
        intent.putExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000);
        intent.setPackage(context.getPackageName());
        intent.putExtras(a3);
        if (VERSION.SDK_INT == 19) {
            PendingIntent.getActivity(StubApp.getOrigApplicationContext(context.getApplicationContext()), i, a3, i2).cancel();
        }
        a2.setContentIntent(PendingIntent.getActivity(StubApp.getOrigApplicationContext(context.getApplicationContext()), i, a3, i2));
        if (VERSION.SDK_INT >= 16) {
            int identifier7 = context.getResources().getIdentifier("xg_notification", "layout", context.getPackageName());
            if (identifier7 != 0) {
                int identifier8 = context.getResources().getIdentifier("xg_notification_icon", Config.FEED_LIST_ITEM_CUSTOM_ID, context.getPackageName());
                int identifier9 = context.getResources().getIdentifier("xg_notification_style_title", Config.FEED_LIST_ITEM_CUSTOM_ID, context.getPackageName());
                int identifier10 = context.getResources().getIdentifier("xg_notification_date", Config.FEED_LIST_ITEM_CUSTOM_ID, context.getPackageName());
                int identifier11 = context.getResources().getIdentifier("xg_notification_style", Config.FEED_LIST_ITEM_CUSTOM_ID, context.getPackageName());
                int identifier12 = context.getResources().getIdentifier("xg_notification_style_content", Config.FEED_LIST_ITEM_CUSTOM_ID, context.getPackageName());
                if (!(identifier8 == 0 || identifier9 == 0 || identifier10 == 0 || identifier12 == 0)) {
                    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), identifier7);
                    remoteViews.setTextViewText(identifier9, eVar.e());
                    remoteViews.setTextViewText(identifier12, eVar.f());
                    remoteViews.setTextViewText(identifier10, String.valueOf(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()))));
                    remoteViews.setImageViewResource(identifier8, context.getApplicationInfo().icon);
                    remoteViews.setViewVisibility(identifier11, 0);
                    a2.setContentView(remoteViews);
                }
            }
        }
        Notification buildNotification = a2.buildNotification(context);
        buildNotification.deleteIntent = PendingIntent.getBroadcast(StubApp.getOrigApplicationContext(context.getApplicationContext()), i, intent, i2);
        XGPushNotifactionCallback notifactionCallback = XGPushManager.getNotifactionCallback();
        if (notifactionCallback == null) {
            notificationManager.notify(i, buildNotification);
        } else {
            a.e("MessageHelper", "call notifactionCallback:" + buildNotification);
            notifactionCallback.handleNotify(new XGNotifaction(context, i, buildNotification, eVar));
        }
        Intent intent2 = new Intent(Constants.ACTION_FEEDBACK);
        intent2.putExtra(Constants.FEEDBACK_ERROR_CODE, 0);
        intent2.setPackage(context.getPackageName());
        intent2.putExtras(a3);
        intent2.putExtra(Constants.FEEDBACK_TAG, 5);
        intent2.putExtra(MessageKey.NOTIFACTION_ID, i);
        context.sendBroadcast(intent2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c5 A[SYNTHETIC, Splitter:B:39:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A[Catch:{ IOException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cf A[Catch:{ IOException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d4 A[Catch:{ IOException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d9 A[Catch:{ IOException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0129 A[SYNTHETIC, Splitter:B:71:0x0129] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x012e A[Catch:{ IOException -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0133 A[Catch:{ IOException -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0138 A[Catch:{ IOException -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x013d A[Catch:{ IOException -> 0x0145 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r10, com.tencent.android.tpush.XGPushNotificationBuilder r11, android.content.Context r12, java.lang.Integer r13) {
        /*
            r3 = 4000(0xfa0, float:5.605E-42)
            r2 = 3000(0xbb8, float:4.204E-42)
            r1 = 0
            org.apache.http.params.BasicHttpParams r0 = new org.apache.http.params.BasicHttpParams
            r0.<init>()
            org.apache.http.params.HttpConnectionParams.setSoTimeout(r0, r2)
            org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r0, r2)
            org.apache.http.impl.client.DefaultHttpClient r6 = new org.apache.http.impl.client.DefaultHttpClient
            r6.<init>(r0)
            org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r0, r3)
            org.apache.http.params.HttpConnectionParams.setSoTimeout(r0, r3)
            r2 = 4000(0xfa0, double:1.9763E-320)
            org.apache.http.conn.params.ConnManagerParams.setTimeout(r0, r2)
            r0 = 0
            r2 = 0
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x015f, all -> 0x0123 }
            r4.<init>(r10)     // Catch:{ Exception -> 0x015f, all -> 0x0123 }
            org.apache.http.client.methods.HttpGet r5 = new org.apache.http.client.methods.HttpGet     // Catch:{ Exception -> 0x015f, all -> 0x0123 }
            java.net.URI r7 = r4.toURI()     // Catch:{ Exception -> 0x015f, all -> 0x0123 }
            r5.<init>(r7)     // Catch:{ Exception -> 0x015f, all -> 0x0123 }
            java.lang.String r7 = "X-Online-Host"
            java.lang.String r4 = r4.getHost()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            r5.addHeader(r7, r4)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            org.apache.http.params.HttpParams r4 = r6.getParams()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            java.lang.String r7 = "http.socket.timeout"
            r8 = 20000(0x4e20, float:2.8026E-41)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            r4.setParameter(r7, r8)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            org.apache.http.params.HttpParams r4 = r6.getParams()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            java.lang.String r7 = "http.connection.timeout"
            r8 = 20000(0x4e20, float:2.8026E-41)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            r4.setParameter(r7, r8)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            org.apache.http.HttpResponse r4 = r6.execute(r5)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            org.apache.http.StatusLine r7 = r4.getStatusLine()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            int r7 = r7.getStatusCode()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 == r8) goto L_0x009c
            android.content.res.Resources r4 = r12.getResources()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            android.content.pm.ApplicationInfo r7 = r12.getApplicationInfo()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            int r7 = r7.icon     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeResource(r4, r7)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            r11.setLargeIcon(r4)     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            if (r1 == 0) goto L_0x007e
            r3.consumeContent()     // Catch:{ IOException -> 0x0097 }
        L_0x007e:
            if (r1 == 0) goto L_0x0083
            r0.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0088:
            if (r5 == 0) goto L_0x008d
            r5.abort()     // Catch:{ IOException -> 0x0097 }
        L_0x008d:
            if (r6 == 0) goto L_0x0096
            org.apache.http.conn.ClientConnectionManager r0 = r6.getConnectionManager()     // Catch:{ IOException -> 0x0097 }
            r0.shutdown()     // Catch:{ IOException -> 0x0097 }
        L_0x0096:
            return
        L_0x0097:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0096
        L_0x009c:
            org.apache.http.HttpEntity r2 = r4.getEntity()     // Catch:{ Exception -> 0x0165, all -> 0x014a }
            if (r2 == 0) goto L_0x017b
            java.io.InputStream r4 = r2.getContent()     // Catch:{ Exception -> 0x016b, all -> 0x014e }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0173, all -> 0x0153 }
            r3.<init>()     // Catch:{ Exception -> 0x0173, all -> 0x0153 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
        L_0x00af:
            int r1 = r4.read(r0)     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            r7 = -1
            if (r1 == r7) goto L_0x00e6
            r7 = 0
            r3.write(r0, r7, r1)     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            goto L_0x00af
        L_0x00bb:
            r0 = move-exception
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
        L_0x00c0:
            r0.printStackTrace()     // Catch:{ all -> 0x015a }
            if (r1 == 0) goto L_0x00c8
            r1.consumeContent()     // Catch:{ IOException -> 0x00e1 }
        L_0x00c8:
            if (r3 == 0) goto L_0x00cd
            r3.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00cd:
            if (r2 == 0) goto L_0x00d2
            r2.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00d2:
            if (r4 == 0) goto L_0x00d7
            r4.abort()     // Catch:{ IOException -> 0x00e1 }
        L_0x00d7:
            if (r6 == 0) goto L_0x0096
            org.apache.http.conn.ClientConnectionManager r0 = r6.getConnectionManager()     // Catch:{ IOException -> 0x00e1 }
            r0.shutdown()     // Catch:{ IOException -> 0x00e1 }
            goto L_0x0096
        L_0x00e1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0096
        L_0x00e6:
            byte[] r0 = r3.toByteArray()     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            r1 = 0
            byte[] r7 = r3.toByteArray()     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            int r7 = r7.length     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeByteArray(r0, r1, r7)     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            r11.setLargeIcon(r0)     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            if (r13 == 0) goto L_0x00fe
            com.tencent.android.tpush.XGCustomPushNotificationBuilder r11 = (com.tencent.android.tpush.XGCustomPushNotificationBuilder) r11     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
            r11.setLayoutIconDrawableBmp(r0)     // Catch:{ Exception -> 0x00bb, all -> 0x0157 }
        L_0x00fe:
            if (r2 == 0) goto L_0x0103
            r2.consumeContent()     // Catch:{ IOException -> 0x011d }
        L_0x0103:
            if (r4 == 0) goto L_0x0108
            r4.close()     // Catch:{ IOException -> 0x011d }
        L_0x0108:
            if (r3 == 0) goto L_0x010d
            r3.close()     // Catch:{ IOException -> 0x011d }
        L_0x010d:
            if (r5 == 0) goto L_0x0112
            r5.abort()     // Catch:{ IOException -> 0x011d }
        L_0x0112:
            if (r6 == 0) goto L_0x0096
            org.apache.http.conn.ClientConnectionManager r0 = r6.getConnectionManager()     // Catch:{ IOException -> 0x011d }
            r0.shutdown()     // Catch:{ IOException -> 0x011d }
            goto L_0x0096
        L_0x011d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0096
        L_0x0123:
            r0 = move-exception
            r3 = r1
            r4 = r1
            r5 = r1
        L_0x0127:
            if (r1 == 0) goto L_0x012c
            r1.consumeContent()     // Catch:{ IOException -> 0x0145 }
        L_0x012c:
            if (r4 == 0) goto L_0x0131
            r4.close()     // Catch:{ IOException -> 0x0145 }
        L_0x0131:
            if (r3 == 0) goto L_0x0136
            r3.close()     // Catch:{ IOException -> 0x0145 }
        L_0x0136:
            if (r5 == 0) goto L_0x013b
            r5.abort()     // Catch:{ IOException -> 0x0145 }
        L_0x013b:
            if (r6 == 0) goto L_0x0144
            org.apache.http.conn.ClientConnectionManager r1 = r6.getConnectionManager()     // Catch:{ IOException -> 0x0145 }
            r1.shutdown()     // Catch:{ IOException -> 0x0145 }
        L_0x0144:
            throw r0
        L_0x0145:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0144
        L_0x014a:
            r0 = move-exception
            r3 = r1
            r4 = r1
            goto L_0x0127
        L_0x014e:
            r0 = move-exception
            r3 = r1
            r4 = r1
            r1 = r2
            goto L_0x0127
        L_0x0153:
            r0 = move-exception
            r3 = r1
            r1 = r2
            goto L_0x0127
        L_0x0157:
            r0 = move-exception
            r1 = r2
            goto L_0x0127
        L_0x015a:
            r0 = move-exception
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x0127
        L_0x015f:
            r0 = move-exception
            r2 = r1
            r3 = r1
            r4 = r1
            goto L_0x00c0
        L_0x0165:
            r0 = move-exception
            r2 = r1
            r3 = r1
            r4 = r5
            goto L_0x00c0
        L_0x016b:
            r0 = move-exception
            r3 = r1
            r4 = r5
            r9 = r2
            r2 = r1
            r1 = r9
            goto L_0x00c0
        L_0x0173:
            r0 = move-exception
            r3 = r4
            r4 = r5
            r9 = r2
            r2 = r1
            r1 = r9
            goto L_0x00c0
        L_0x017b:
            r3 = r1
            r4 = r1
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.c.b.a(java.lang.String, com.tencent.android.tpush.XGPushNotificationBuilder, android.content.Context, java.lang.Integer):void");
    }

    public static void b(Context context, g gVar) {
        if (gVar.g() instanceof e) {
            if (XGPushConfig.enableDebug) {
                a.f("MessageHelper", "Action -> showNotification " + gVar.f());
            }
            e eVar = (e) gVar.g();
            if (eVar == null || eVar.m() == null) {
                a.i("MessageHelper", "showNotification holder == null || holder.getAction() == null");
            } else {
                a(context, gVar);
            }
        }
    }

    private static synchronized int b(Context context, int i) {
        Throwable th;
        int i2;
        synchronized (b.class) {
            try {
                String str = "_XINGE_NOTIF_NUMBER_" + String.valueOf(i);
                i2 = h.a(context, str, 0);
                if (i2 >= 2147483646) {
                    i2 = 0;
                }
                try {
                    h.b(context, str, i2 + 1);
                } catch (Throwable th2) {
                    th = th2;
                    a.d("MessageHelper", "", th);
                    return i2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                i2 = 0;
                th = th4;
                a.d("MessageHelper", "", th);
                return i2;
            }
        }
        return i2;
    }
}
