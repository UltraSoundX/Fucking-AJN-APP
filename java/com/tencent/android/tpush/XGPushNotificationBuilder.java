package com.tencent.android.tpush;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.b;
import com.tencent.mid.sotrage.StorageInterface;
import net.sf.json.xml.JSONTypes;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class XGPushNotificationBuilder {
    public static final String BASIC_NOTIFICATION_BUILDER_TYPE = "basic";
    public static final String CUSTOM_NOTIFICATION_BUILDER_TYPE = "custom";
    protected String a = "xg-channle-id";
    protected String b = "message";
    protected Integer c = null;
    protected PendingIntent d = null;
    protected RemoteViews e = null;
    protected RemoteViews f = null;
    protected Integer g = null;
    protected PendingIntent h = null;
    protected Integer i = null;
    protected Integer j = null;
    protected Integer k = null;
    protected Integer l = null;
    protected Integer m = null;
    protected Integer n = null;
    protected Integer o = null;
    protected Uri p = null;

    /* renamed from: q reason: collision with root package name */
    protected CharSequence f427q = null;
    protected long[] r = null;
    protected Long s = null;
    protected Integer t = null;
    protected Bitmap u = null;
    protected Integer v = null;
    protected String w;
    protected Integer x = null;

    /* access modifiers changed from: protected */
    public abstract void a(JSONObject jSONObject);

    /* access modifiers changed from: protected */
    public abstract void b(JSONObject jSONObject);

    public abstract Notification buildNotification(Context context);

    public abstract String getType();

    public void encode(JSONObject jSONObject) {
        a(jSONObject);
        b.a(jSONObject, "audioStringType", (Object) this.c);
        b.a(jSONObject, "defaults", (Object) this.g);
        b.a(jSONObject, "flags", (Object) this.i);
        b.a(jSONObject, MessageKey.MSG_ICON, (Object) this.j);
        b.a(jSONObject, "iconLevel", (Object) this.k);
        b.a(jSONObject, "ledARGB", (Object) this.l);
        b.a(jSONObject, "ledOffMS", (Object) this.m);
        b.a(jSONObject, "ledOnMS", (Object) this.n);
        b.a(jSONObject, JSONTypes.NUMBER, (Object) this.o);
        b.a(jSONObject, "sound", (Object) this.p);
        b.a(jSONObject, "smallIcon", (Object) this.t);
        b.a(jSONObject, "notificationLargeIcon", (Object) this.v);
        if (this.r != null) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < this.r.length; i2++) {
                sb.append(String.valueOf(this.r[i2]));
                if (i2 != this.r.length - 1) {
                    sb.append(StorageInterface.KEY_SPLITER);
                }
            }
            b.a(jSONObject, MessageKey.MSG_VIBRATE, (Object) sb.toString());
        }
        b.a(jSONObject, "notificationId", (Object) this.x);
    }

    public void decode(String str) {
        JSONObject jSONObject = new JSONObject(str);
        b(jSONObject);
        this.c = (Integer) b.b(jSONObject, "audioStringType", null);
        this.g = (Integer) b.b(jSONObject, "defaults", null);
        this.i = (Integer) b.b(jSONObject, "flags", null);
        this.j = (Integer) b.b(jSONObject, MessageKey.MSG_ICON, null);
        this.k = (Integer) b.b(jSONObject, "iconLevel", null);
        this.l = (Integer) b.b(jSONObject, "ledARGB", null);
        this.m = (Integer) b.b(jSONObject, "ledOffMS", null);
        this.n = (Integer) b.b(jSONObject, "ledOnMS", null);
        this.o = (Integer) b.b(jSONObject, JSONTypes.NUMBER, null);
        String str2 = (String) b.b(jSONObject, "sound", null);
        this.t = (Integer) b.b(jSONObject, "smallIcon", null);
        this.v = (Integer) b.b(jSONObject, "notificationLargeIcon", null);
        if (str2 != null) {
            this.p = Uri.parse(str2);
        }
        String str3 = (String) b.b(jSONObject, MessageKey.MSG_VIBRATE, null);
        if (str3 != null) {
            String[] split = str3.split(StorageInterface.KEY_SPLITER);
            int length = split.length;
            this.r = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    this.r[i2] = Long.valueOf(split[i2]).longValue();
                } catch (NumberFormatException e2) {
                }
            }
        }
        this.x = (Integer) b.b(jSONObject, "notificationId", null);
    }

    public String getTitle(Context context) {
        if (this.w == null) {
            this.w = (String) StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getApplicationLabel(context.getApplicationInfo());
        }
        return this.w;
    }

    public void setTitle(String str) {
        this.w = str;
    }

    public String getChannelId() {
        return this.a;
    }

    public void setChannelId(String str) {
        this.a = str;
    }

    public String getChannelName() {
        return this.b;
    }

    public void setChannelName(String str) {
        this.b = str;
    }

    public int getApplicationIcon(Context context) {
        return context.getApplicationInfo().icon;
    }

    @SuppressLint({"NewApi"})
    public Notification getChannelNotification(Context context) {
        Builder builder = new Builder(context);
        BigTextStyle bigTextStyle = new BigTextStyle();
        if (this.t != null) {
            builder.setSmallIcon(this.t.intValue());
        }
        if (this.v != null) {
            try {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), this.v.intValue()));
            } catch (OutOfMemoryError e2) {
            }
        }
        if (this.u != null) {
            builder.setLargeIcon(this.u);
        }
        if (this.w == null) {
            this.w = getTitle(context);
        } else {
            builder.setContentTitle(this.w);
        }
        if (this.f427q == null || this.e != null) {
            builder.setContentText(this.f427q);
            builder.setTicker(this.f427q);
        } else {
            bigTextStyle.bigText(this.f427q);
            builder.setStyle(bigTextStyle);
            builder.setContentText(this.f427q);
            builder.setTicker(this.f427q);
        }
        if (VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26) {
            try {
                a.e(Constants.LogTag, "XGPushNotification create notificationChannle, channelId:" + getChannelId() + ", channelName:" + getChannelName());
                Class cls = Class.forName("android.app.NotificationChannel");
                Object newInstance = cls.getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{getChannelId(), getChannelName(), Integer.valueOf(4)});
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                notificationManager.getClass().getMethod("createNotificationChannel", new Class[]{cls}).invoke(notificationManager, new Object[]{newInstance});
                builder.getClass().getMethod("setChannelId", new Class[]{String.class}).invoke(builder, new Object[]{getChannelId()});
            } catch (Exception e3) {
                a.j(Constants.LogTag, "XGPushNotification create channel Error: " + e3.getMessage());
                e3.printStackTrace();
            }
        }
        return builder.build();
    }

    private Notification b(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        if (this.t != null) {
            builder.setSmallIcon(this.t.intValue());
        }
        if (this.v != null) {
            try {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), this.v.intValue()));
            } catch (OutOfMemoryError e2) {
            }
        }
        if (this.u != null) {
            builder.setLargeIcon(this.u);
        }
        if (this.w == null) {
            this.w = getTitle(context);
        } else {
            builder.setContentTitle(this.w);
        }
        if (this.f427q == null || this.e != null) {
            builder.setContentText(this.f427q);
            builder.setTicker(this.f427q);
        } else {
            bigTextStyle.bigText(this.f427q);
            builder.setStyle(bigTextStyle);
            builder.setContentText(this.f427q);
            builder.setTicker(this.f427q);
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public Notification a(Context context) {
        Notification b2;
        new Notification();
        if (this.x == null) {
            this.x = Integer.valueOf(0);
        }
        a.f(Constants.LogTag, "XGPushNotification Build.VERSION.SDK_INT: " + VERSION.SDK_INT + ", targetSDK:" + context.getApplicationInfo().targetSdkVersion);
        if (VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26) {
            b2 = b(context);
        } else {
            b2 = getChannelNotification(context);
        }
        if (this.c != null) {
            b2.audioStreamType = this.c.intValue();
        }
        if (this.d != null) {
            b2.contentIntent = this.d;
        }
        if (this.e != null) {
            if (Build.MANUFACTURER.trim().toLowerCase().equals("oppo")) {
                a.h(Constants.LogTag, "XGPushNotification: Oppo Rom not allow custom contentview. Not set it");
            } else {
                b2.contentView = this.e;
            }
        }
        if (this.g != null) {
            b2.defaults = this.g.intValue();
        }
        if (this.j != null) {
            b2.icon = this.j.intValue();
        }
        if (this.h != null) {
            b2.deleteIntent = this.h;
        }
        if (this.i != null) {
            b2.flags = this.i.intValue();
        } else {
            b2.flags = 16;
        }
        if (this.k != null) {
            b2.iconLevel = this.k.intValue();
        }
        if (this.l != null) {
            b2.ledARGB = this.l.intValue();
        }
        if (this.m != null) {
            b2.ledOffMS = this.m.intValue();
        }
        if (this.n != null) {
            b2.ledOnMS = this.n.intValue();
        }
        if (this.o != null) {
            b2.number = this.o.intValue();
        }
        if (this.p != null) {
            b2.sound = this.p;
        }
        if (this.r != null) {
            b2.vibrate = this.r;
        }
        if (this.s != null) {
            b2.when = this.s.longValue();
        } else {
            b2.when = System.currentTimeMillis();
        }
        return b2;
    }

    public int getAudioStringType() {
        return this.c.intValue();
    }

    public XGPushNotificationBuilder setAudioStringType(int i2) {
        this.c = Integer.valueOf(i2);
        return this;
    }

    public PendingIntent getContentIntent() {
        return this.d;
    }

    public XGPushNotificationBuilder setContentIntent(PendingIntent pendingIntent) {
        this.d = pendingIntent;
        return this;
    }

    public XGPushNotificationBuilder setContentView(RemoteViews remoteViews) {
        this.e = remoteViews;
        return this;
    }

    public XGPushNotificationBuilder setbigContentView(RemoteViews remoteViews) {
        this.f = remoteViews;
        return this;
    }

    public int getDefaults() {
        return this.g.intValue();
    }

    public XGPushNotificationBuilder setDefaults(int i2) {
        if (this.g == null) {
            this.g = Integer.valueOf(i2);
        } else {
            this.g = Integer.valueOf(this.g.intValue() | i2);
        }
        return this;
    }

    public int getFlags() {
        return this.i.intValue();
    }

    public XGPushNotificationBuilder setFlags(int i2) {
        if (this.i == null) {
            this.i = Integer.valueOf(i2);
        } else {
            this.i = Integer.valueOf(this.i.intValue() | i2);
        }
        return this;
    }

    public Integer getIcon() {
        return this.j;
    }

    public XGPushNotificationBuilder setIcon(Integer num) {
        this.j = num;
        return this;
    }

    public Integer getSmallIcon() {
        return this.t;
    }

    public XGPushNotificationBuilder setSmallIcon(Integer num) {
        this.t = num;
        return this;
    }

    public Bitmap getLargeIcon() {
        return this.u;
    }

    public XGPushNotificationBuilder setLargeIcon(Bitmap bitmap) {
        this.u = bitmap;
        return this;
    }

    public XGPushNotificationBuilder setNotificationLargeIcon(int i2) {
        this.v = Integer.valueOf(i2);
        return this;
    }

    public Integer getNotificationLargeIcon() {
        return this.v;
    }

    public int getIconLevel() {
        return this.k.intValue();
    }

    public XGPushNotificationBuilder setIconLevel(int i2) {
        this.k = Integer.valueOf(i2);
        return this;
    }

    public int getLedARGB() {
        return this.l.intValue();
    }

    public XGPushNotificationBuilder setLedARGB(int i2) {
        this.l = Integer.valueOf(i2);
        return this;
    }

    public int getLedOffMS() {
        return this.m.intValue();
    }

    public XGPushNotificationBuilder setLedOffMS(int i2) {
        this.m = Integer.valueOf(i2);
        return this;
    }

    public int getLedOnMS() {
        return this.n.intValue();
    }

    public XGPushNotificationBuilder setLedOnMS(int i2) {
        this.n = Integer.valueOf(i2);
        return this;
    }

    public int getNumber() {
        return this.o.intValue();
    }

    public XGPushNotificationBuilder setNumber(int i2) {
        this.o = Integer.valueOf(i2);
        return this;
    }

    public Uri getSound() {
        return this.p;
    }

    public XGPushNotificationBuilder setSound(Uri uri) {
        this.p = uri;
        return this;
    }

    public CharSequence getTickerText() {
        return this.f427q;
    }

    public XGPushNotificationBuilder setTickerText(CharSequence charSequence) {
        this.f427q = charSequence;
        return this;
    }

    public long[] getVibrate() {
        return this.r;
    }

    public XGPushNotificationBuilder setVibrate(long[] jArr) {
        this.r = jArr;
        return this;
    }

    public long getWhen() {
        return this.s.longValue();
    }

    public XGPushNotificationBuilder setWhen(long j2) {
        this.s = Long.valueOf(j2);
        return this;
    }
}
