package com.tencent.android.tpush;

import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.service.e.i;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGLocalMessage {
    private static final String a = XGLocalMessage.class.getSimpleName();
    private int A = 2592000;
    private long B = (System.currentTimeMillis() + (((long) this.A) * 1000));
    private int b = 1;
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "00";
    private String g = "00";
    private int h = 1;
    private int i = 1;
    private int j = 1;
    private int k = 0;
    private int l = 1;
    private String m = "";
    private String n = "";
    private String o = "";
    private int p = 1;

    /* renamed from: q reason: collision with root package name */
    private String f426q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "{}";
    private long w;
    private int x = 0;
    private long y = (System.currentTimeMillis() * -1);
    private long z = 0;

    public long getExpirationTimeMs() {
        return this.B;
    }

    public void setExpirationTimeMs(long j2) {
        if (j2 > System.currentTimeMillis()) {
            this.A = (int) ((j2 - System.currentTimeMillis()) / 1000);
            if (this.A < 0) {
                this.A = Integer.MAX_VALUE;
            }
            this.B = j2;
        }
    }

    public int getTtl() {
        return this.A;
    }

    public int getType() {
        return this.b;
    }

    public void setType(int i2) {
        this.b = i2;
    }

    public String getTitle() {
        return this.c;
    }

    public void setTitle(String str) {
        this.c = str;
    }

    public String getContent() {
        return this.d;
    }

    public void setContent(String str) {
        this.d = str;
    }

    public void setCustomContent(HashMap<String, Object> hashMap) {
        this.v = new JSONObject(hashMap).toString();
    }

    public String getCustom_content() {
        return this.v;
    }

    public String getHour() {
        if (this.f.length() < 1) {
            return "00";
        }
        if (this.f.length() <= 0 || this.f.length() >= 2) {
            return this.f;
        }
        return "0" + this.f;
    }

    public void setHour(String str) {
        this.f = str;
    }

    public String getMin() {
        if (this.g.length() < 1) {
            return "00";
        }
        if (this.g.length() <= 0 || this.g.length() >= 2) {
            return this.g;
        }
        return "0" + this.g;
    }

    public void setMin(String str) {
        this.g = str;
    }

    public long getBuilderId() {
        return this.w;
    }

    public void setBuilderId(long j2) {
        this.w = j2;
    }

    public String getDate() {
        if (!i.b(this.e)) {
            try {
                this.e = this.e.substring(0, 8);
                Long.parseLong(this.e);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                simpleDateFormat.setLenient(false);
                simpleDateFormat.parse(this.e);
            } catch (ParseException e2) {
                a.d(a, "XGLocalMessage.getDate()", e2);
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            } catch (Exception e3) {
                a.d(a, "XGLocalMessage.getDate()", e3);
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            }
        }
        return this.e;
    }

    public void setDate(String str) {
        this.e = str;
    }

    public void setRing(int i2) {
        this.h = i2;
    }

    public int getRing() {
        return this.h;
    }

    public void setVibrate(int i2) {
        this.i = i2;
    }

    public int getVibrate() {
        return this.i;
    }

    public void setLights(int i2) {
        this.j = i2;
    }

    public int getLights() {
        return this.j;
    }

    public void setIcon_type(int i2) {
        this.k = i2;
    }

    public int getIcon_type() {
        return this.k;
    }

    public void setStyle_id(int i2) {
        this.l = i2;
    }

    public int getStyle_id() {
        return this.l;
    }

    public void setRing_raw(String str) {
        this.m = str;
    }

    public String getRing_raw() {
        return this.m;
    }

    public void setIcon_res(String str) {
        this.n = str;
    }

    public String getIcon_res() {
        return this.n;
    }

    public void setSmall_icon(String str) {
        this.o = str;
    }

    public String getSmall_icon() {
        return this.o;
    }

    public void setAction_type(int i2) {
        this.p = i2;
    }

    public int getAction_type() {
        return this.p;
    }

    public void setActivity(String str) {
        this.f426q = str;
    }

    public String getActivity() {
        return this.f426q;
    }

    public void setUrl(String str) {
        this.r = str;
    }

    public String getUrl() {
        return this.r;
    }

    public void setIntent(String str) {
        this.s = str;
    }

    public String getIntent() {
        return this.s;
    }

    public void setPackageDownloadUrl(String str) {
        this.t = str;
    }

    public String getPackageDownloadUrl() {
        return this.t;
    }

    public void setPackageName(String str) {
        this.u = str;
    }

    public String getPackageName() {
        return this.u;
    }

    public int getNotificationId() {
        return this.x;
    }

    public void setNotificationId(int i2) {
        this.x = i2;
    }

    public long getMsgId() {
        return this.y;
    }

    public void setMsgId(long j2) {
        this.y = j2;
    }

    public long getBusiMsgId() {
        return this.z;
    }

    public void setBusiMsgId(long j2) {
        this.z = j2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("XGLocalMessage [type=").append(this.b).append(", title=").append(this.c).append(", content=").append(this.d).append(", date=").append(this.e).append(", hour=").append(this.f).append(", min=").append(this.g).append(", builderId=").append(this.w).append(", msgid=").append(this.y).append(", busiMsgId=").append(this.z).append("]");
        return sb.toString();
    }
}
