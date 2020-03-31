package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.tencent.android.tpush.common.b;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGCustomPushNotificationBuilder extends XGPushNotificationBuilder {
    private Integer A = null;
    private Integer B = null;
    private Integer C = null;
    private Integer D = null;
    private Integer E = null;
    private Bitmap F = null;
    private Integer y = null;
    private Integer z = null;

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        b.a(jSONObject, "layoutId", (Object) this.y);
        b.a(jSONObject, "layoutIconId", (Object) this.z);
        b.a(jSONObject, "layoutTitleId", (Object) this.A);
        b.a(jSONObject, "layoutTextId", (Object) this.B);
        b.a(jSONObject, "layoutIconDrawableId", (Object) this.C);
        b.a(jSONObject, "statusBarIconDrawableId", (Object) this.D);
        b.a(jSONObject, "layoutTimeId", (Object) this.E);
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject) {
        this.y = (Integer) b.b(jSONObject, "layoutId", null);
        this.z = (Integer) b.b(jSONObject, "layoutIconId", null);
        this.A = (Integer) b.b(jSONObject, "layoutTitleId", null);
        this.B = (Integer) b.b(jSONObject, "layoutTextId", null);
        this.C = (Integer) b.b(jSONObject, "layoutIconDrawableId", null);
        this.D = (Integer) b.b(jSONObject, "statusBarIconDrawableId", null);
        this.E = (Integer) b.b(jSONObject, "layoutTimeId", null);
    }

    public Notification buildNotification(Context context) {
        if (this.y == null) {
            return a(context);
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), this.y.intValue());
        if (this.A != null) {
            remoteViews.setTextViewText(this.A.intValue(), getTitle(context));
        }
        if (this.B != null) {
            remoteViews.setTextViewText(this.B.intValue(), this.f427q);
        }
        if (this.z != null) {
            remoteViews.setImageViewResource(this.z.intValue(), this.C.intValue());
        }
        if (this.F != null) {
            remoteViews.setImageViewBitmap(this.z.intValue(), this.F);
        }
        if (this.D != null) {
            remoteViews.setTextViewText(this.D.intValue(), getTitle(context));
        }
        if (this.E != null) {
            remoteViews.setTextViewText(this.E.intValue(), String.valueOf(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()))));
        }
        this.e = remoteViews;
        return a(context);
    }

    public int getLayoutId() {
        return this.y.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutId(int i) {
        this.y = Integer.valueOf(i);
        return this;
    }

    public Integer getLayoutIconId() {
        return this.z;
    }

    public XGCustomPushNotificationBuilder setLayoutIconId(int i) {
        this.z = Integer.valueOf(i);
        return this;
    }

    public int getLayoutTitleId() {
        return this.A.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutTitleId(int i) {
        this.A = Integer.valueOf(i);
        return this;
    }

    public int getLayoutTextId() {
        return this.B.intValue();
    }

    public int getLayoutTimeId() {
        return this.E.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutTextId(int i) {
        this.B = Integer.valueOf(i);
        return this;
    }

    public XGCustomPushNotificationBuilder setLayoutTimeId(int i) {
        this.E = Integer.valueOf(i);
        return this;
    }

    public int getLayoutIconDrawableId() {
        return this.C.intValue();
    }

    public XGCustomPushNotificationBuilder setLayoutIconDrawableId(int i) {
        this.C = Integer.valueOf(i);
        return this;
    }

    public XGCustomPushNotificationBuilder setLayoutIconDrawableBmp(Bitmap bitmap) {
        this.F = bitmap;
        return this;
    }

    public String getType() {
        return XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE;
    }
}
