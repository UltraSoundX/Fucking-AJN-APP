package com.tencent.android.tpush.c;

import com.tencent.android.tpush.NotificationAction;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e extends a {
    private int d = 0;
    private int e = 1;
    private int f = 1;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private String j = "";
    private int k = 1;
    private String l = "";
    private String m = "";
    private int n = 0;
    private int o = 0;
    private String p = "";

    /* renamed from: q reason: collision with root package name */
    private String f429q = "";
    private a r = new a();

    /* compiled from: ProGuard */
    public static class a {
        public int a = NotificationAction.activity.getType();
        public String b = "";
        public C0058a c = new C0058a();
        public String d = "";
        public String e = "";
        public String f = "";
        public int g = 0;
        public String h = "";
        public String i = "";
        public String j = "";

        /* renamed from: com.tencent.android.tpush.c.e$a$a reason: collision with other inner class name */
        /* compiled from: ProGuard */
        public static class C0058a {
            public int a = 0;
            public int b = 0;
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(Constants.FLAG_ACTION_TYPE)) {
                this.a = jSONObject.getInt(Constants.FLAG_ACTION_TYPE);
            }
            if (!jSONObject.isNull(Constants.FLAG_ACTIVITY_NAME)) {
                this.b = jSONObject.getString(Constants.FLAG_ACTIVITY_NAME);
            }
            if (!jSONObject.isNull("aty_attr")) {
                String optString = jSONObject.optString("aty_attr");
                if (!l.c(optString)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(optString);
                        this.c.a = jSONObject2.optInt("if");
                        this.c.b = jSONObject2.optInt("pf");
                    } catch (Exception e2) {
                        com.tencent.android.tpush.b.a.d(Constants.LogTag, "decode activityAttribute error", e2);
                    }
                }
            }
            if (!jSONObject.isNull("intent")) {
                this.d = jSONObject.getString("intent");
            }
            if (!jSONObject.isNull("browser")) {
                this.e = jSONObject.getString("browser");
                JSONObject jSONObject3 = new JSONObject(this.e);
                if (!jSONObject3.isNull("url")) {
                    this.f = jSONObject3.getString("url");
                }
                if (!jSONObject3.isNull("confirm")) {
                    this.g = jSONObject3.getInt("confirm");
                }
            }
            if (!jSONObject.isNull("package_name")) {
                this.i = jSONObject.getString("package_name");
                JSONObject jSONObject4 = new JSONObject(this.i);
                if (!jSONObject4.isNull(Constants.FLAG_PACKAGE_DOWNLOAD_URL)) {
                    this.j = jSONObject4.getString(Constants.FLAG_PACKAGE_DOWNLOAD_URL);
                }
                if (!jSONObject4.isNull(Constants.FLAG_PACKAGE_NAME)) {
                    this.h = jSONObject4.getString(Constants.FLAG_PACKAGE_NAME);
                }
                if (!jSONObject4.isNull("confirm")) {
                    this.g = jSONObject4.getInt("confirm");
                }
            }
        }
    }

    public e(String str) {
        super(str);
    }

    public int c() {
        return 1;
    }

    public int h() {
        return this.d;
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.g;
    }

    public int l() {
        return this.h;
    }

    public a m() {
        return this.r;
    }

    public int n() {
        return this.i;
    }

    public int o() {
        return this.k;
    }

    public String p() {
        return this.l;
    }

    public String q() {
        return this.j;
    }

    public String r() {
        return this.m;
    }

    public int s() {
        return this.n;
    }

    public int t() {
        return this.o;
    }

    public String u() {
        return this.p;
    }

    public String v() {
        return this.f429q;
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.d = this.a.optInt(MessageKey.MSG_BUILDER_ID);
        this.e = this.a.optInt(MessageKey.MSG_RING, 1);
        this.l = this.a.optString(MessageKey.MSG_RING_RAW);
        this.j = this.a.optString(MessageKey.MSG_ICON_RES);
        this.m = this.a.optString(MessageKey.MSG_SMALL_ICON);
        this.k = this.a.optInt(MessageKey.MSG_LIGHTS, 1);
        this.f = this.a.optInt(MessageKey.MSG_VIBRATE, 1);
        this.i = this.a.optInt(MessageKey.MSG_ICON);
        this.n = this.a.optInt(MessageKey.MSG_ICON_TYPE, 0);
        this.h = this.a.optInt(MessageKey.MSG_NOTIFY_ID);
        this.o = this.a.optInt(MessageKey.MSG_STYLE_ID, 0);
        this.p = this.a.optString(MessageKey.MSG_NOTIFACTION_ID_CHANNEL_ID);
        this.f429q = this.a.optString(MessageKey.MSG_NOTIFACTION_ID_CHANNEL_NAME);
        if (!this.a.isNull(MessageKey.MSG_CLEARABLE)) {
            this.g = this.a.optInt(MessageKey.MSG_CLEARABLE);
        } else {
            this.g = 1;
        }
        if (!this.a.isNull("action")) {
            this.r.a(this.a.getString("action"));
        }
    }
}
