package com.baidu.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.location.a.C0022a;
import com.baidu.location.g.j;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.List;

public final class BDLocation implements Parcelable {
    public static final Creator<BDLocation> CREATOR = new i();
    private int A;
    private String B;
    private int C;
    private String D;
    private int E;
    private int F;
    private int G;
    private int H;
    private String I;
    private String J;
    private String K;
    private List<Poi> L;
    private String M;
    private String N;
    private Bundle O;
    private int P;
    private int Q;
    private int a;
    private String b;
    private double c;
    private double d;
    private boolean e;
    private double f;
    private boolean g;
    private float h;
    private boolean i;
    private float j;
    private boolean k;
    private int l;
    private float m;
    private String n;
    private boolean o;
    private String p;

    /* renamed from: q reason: collision with root package name */
    private String f367q;
    private String r;
    private String s;
    private boolean t;
    private a u;
    private String v;
    private String w;
    private String x;
    private boolean y;
    private int z;

    public BDLocation() {
        this.a = 0;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.f367q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new C0022a().a();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = new Bundle();
        this.P = 0;
        this.Q = 0;
    }

    private BDLocation(Parcel parcel) {
        this.a = 0;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.f367q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new C0022a().a();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = new Bundle();
        this.P = 0;
        this.Q = 0;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readDouble();
        this.d = parcel.readDouble();
        this.f = parcel.readDouble();
        this.h = parcel.readFloat();
        this.j = parcel.readFloat();
        this.l = parcel.readInt();
        this.m = parcel.readFloat();
        this.v = parcel.readString();
        this.z = parcel.readInt();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.B = parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        String readString4 = parcel.readString();
        String readString5 = parcel.readString();
        String readString6 = parcel.readString();
        parcel.readString();
        String readString7 = parcel.readString();
        String readString8 = parcel.readString();
        this.u = new C0022a().a(readString7).c(readString8).d(readString).e(readString2).f(readString6).g(readString3).h(readString4).i(readString5).b(parcel.readString()).a();
        boolean[] zArr = new boolean[7];
        this.C = parcel.readInt();
        this.D = parcel.readString();
        this.f367q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.A = parcel.readInt();
        this.M = parcel.readString();
        this.E = parcel.readInt();
        this.F = parcel.readInt();
        this.G = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readString();
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.P = parcel.readInt();
        this.N = parcel.readString();
        this.Q = parcel.readInt();
        try {
            parcel.readBooleanArray(zArr);
            this.e = zArr[0];
            this.g = zArr[1];
            this.i = zArr[2];
            this.k = zArr[3];
            this.o = zArr[4];
            this.t = zArr[5];
            this.y = zArr[6];
        } catch (Exception e2) {
        }
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, Poi.class.getClassLoader());
        if (arrayList.size() == 0) {
            this.L = null;
        } else {
            this.L = arrayList;
        }
        this.O = parcel.readBundle();
    }

    /* synthetic */ BDLocation(Parcel parcel, i iVar) {
        this(parcel);
    }

    public BDLocation(BDLocation bDLocation) {
        int i2 = 0;
        this.a = 0;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.k = false;
        this.l = -1;
        this.m = -1.0f;
        this.n = null;
        this.o = false;
        this.p = null;
        this.f367q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = new C0022a().a();
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 1;
        this.B = null;
        this.D = "";
        this.E = -1;
        this.F = 0;
        this.G = 2;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = new Bundle();
        this.P = 0;
        this.Q = 0;
        this.a = bDLocation.a;
        this.b = bDLocation.b;
        this.c = bDLocation.c;
        this.d = bDLocation.d;
        this.e = bDLocation.e;
        this.f = bDLocation.f;
        this.g = bDLocation.g;
        this.h = bDLocation.h;
        this.i = bDLocation.i;
        this.j = bDLocation.j;
        this.k = bDLocation.k;
        this.l = bDLocation.l;
        this.m = bDLocation.m;
        this.n = bDLocation.n;
        this.o = bDLocation.o;
        this.p = bDLocation.p;
        this.t = bDLocation.t;
        this.u = new C0022a().a(bDLocation.u.a).c(bDLocation.u.b).d(bDLocation.u.c).e(bDLocation.u.d).f(bDLocation.u.e).g(bDLocation.u.f).h(bDLocation.u.g).i(bDLocation.u.h).b(bDLocation.u.j).a();
        this.v = bDLocation.v;
        this.w = bDLocation.w;
        this.x = bDLocation.x;
        this.A = bDLocation.A;
        this.z = bDLocation.z;
        this.y = bDLocation.y;
        this.B = bDLocation.B;
        this.C = bDLocation.C;
        this.D = bDLocation.D;
        this.f367q = bDLocation.f367q;
        this.r = bDLocation.r;
        this.s = bDLocation.s;
        this.E = bDLocation.E;
        this.F = bDLocation.F;
        this.G = bDLocation.F;
        this.H = bDLocation.H;
        this.I = bDLocation.I;
        this.J = bDLocation.J;
        this.K = bDLocation.K;
        this.P = bDLocation.P;
        this.N = bDLocation.N;
        if (bDLocation.L == null) {
            this.L = null;
        } else {
            ArrayList arrayList = new ArrayList();
            while (true) {
                int i3 = i2;
                if (i3 >= bDLocation.L.size()) {
                    break;
                }
                Poi poi = (Poi) bDLocation.L.get(i3);
                arrayList.add(new Poi(poi.a(), poi.c(), poi.b()));
                i2 = i3 + 1;
            }
            this.L = arrayList;
        }
        this.M = bDLocation.M;
        this.O = bDLocation.O;
        this.Q = bDLocation.Q;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:159:0x046c, code lost:
        r1 = false;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x04bc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:?, code lost:
        r1.printStackTrace();
        r1 = null;
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0526, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x014d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x014e, code lost:
        r0.printStackTrace();
        r14.a = 0;
        r14.o = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0167, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0168, code lost:
        r0.printStackTrace();
        r14.a = 0;
        r14.o = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0167 A[ExcHandler: Error (r0v45 'e' java.lang.Error A[CUSTOM_DECLARE]), Splitter:B:4:0x009d] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0142=Splitter:B:17:0x0142, B:214:0x051b=Splitter:B:214:0x051b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BDLocation(java.lang.String r15) {
        /*
            r14 = this;
            r14.<init>()
            r0 = 0
            r14.a = r0
            r0 = 0
            r14.b = r0
            r0 = 1
            r14.c = r0
            r0 = 1
            r14.d = r0
            r0 = 0
            r14.e = r0
            r0 = 1
            r14.f = r0
            r0 = 0
            r14.g = r0
            r0 = 0
            r14.h = r0
            r0 = 0
            r14.i = r0
            r0 = 0
            r14.j = r0
            r0 = 0
            r14.k = r0
            r0 = -1
            r14.l = r0
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r14.m = r0
            r0 = 0
            r14.n = r0
            r0 = 0
            r14.o = r0
            r0 = 0
            r14.p = r0
            r0 = 0
            r14.f367q = r0
            r0 = 0
            r14.r = r0
            r0 = 0
            r14.s = r0
            r0 = 0
            r14.t = r0
            com.baidu.location.a$a r0 = new com.baidu.location.a$a
            r0.<init>()
            com.baidu.location.a r0 = r0.a()
            r14.u = r0
            r0 = 0
            r14.v = r0
            r0 = 0
            r14.w = r0
            r0 = 0
            r14.x = r0
            r0 = 0
            r14.y = r0
            r0 = 0
            r14.z = r0
            r0 = 1
            r14.A = r0
            r0 = 0
            r14.B = r0
            java.lang.String r0 = ""
            r14.D = r0
            r0 = -1
            r14.E = r0
            r0 = 0
            r14.F = r0
            r0 = 2
            r14.G = r0
            r0 = 0
            r14.H = r0
            r0 = 0
            r14.I = r0
            r0 = 0
            r14.J = r0
            r0 = 0
            r14.K = r0
            r0 = 0
            r14.L = r0
            r0 = 0
            r14.M = r0
            r0 = 0
            r14.N = r0
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r14.O = r0
            r0 = 0
            r14.P = r0
            r0 = 0
            r14.Q = r0
            if (r15 == 0) goto L_0x009c
            java.lang.String r0 = ""
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x009d
        L_0x009c:
            return
        L_0x009d:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "result"
            org.json.JSONObject r1 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "error"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.e(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r3 = "time"
            java.lang.String r1 = r1.getString(r3)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r1 = 61
            if (r2 != r1) goto L_0x0173
            java.lang.String r1 = "content"
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "point"
            org.json.JSONObject r1 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "y"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r2 = java.lang.Double.parseDouble(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "x"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r2 = java.lang.Double.parseDouble(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "radius"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "s"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "d"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.c(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "n"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.f(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "h"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r1 == 0) goto L_0x012d
            java.lang.String r1 = "h"
            double r2 = r0.getDouble(r1)     // Catch:{ Exception -> 0x059b, Error -> 0x0167 }
            r14.c(r2)     // Catch:{ Exception -> 0x059b, Error -> 0x0167 }
        L_0x012d:
            java.lang.String r1 = "in_cn"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x015e, Error -> 0x0167 }
            if (r1 == 0) goto L_0x0159
            java.lang.String r1 = "in_cn"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x015e, Error -> 0x0167 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x015e, Error -> 0x0167 }
            r14.h(r0)     // Catch:{ Exception -> 0x015e, Error -> 0x0167 }
        L_0x0142:
            int r0 = r14.A     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 != 0) goto L_0x0160
            java.lang.String r0 = "wgs84"
            r14.d(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x014d:
            r0 = move-exception
            r0.printStackTrace()
            r0 = 0
            r14.a = r0
            r0 = 0
            r14.o = r0
            goto L_0x009c
        L_0x0159:
            r0 = 1
            r14.h(r0)     // Catch:{ Exception -> 0x015e, Error -> 0x0167 }
            goto L_0x0142
        L_0x015e:
            r0 = move-exception
            goto L_0x0142
        L_0x0160:
            java.lang.String r0 = "gcj02"
            r14.d(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x0167:
            r0 = move-exception
            r0.printStackTrace()
            r0 = 0
            r14.a = r0
            r0 = 0
            r14.o = r0
            goto L_0x009c
        L_0x0173:
            r1 = 161(0xa1, float:2.26E-43)
            if (r2 != r1) goto L_0x053e
            java.lang.String r1 = "content"
            org.json.JSONObject r11 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = "point"
            org.json.JSONObject r0 = r11.getJSONObject(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "y"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r2 = java.lang.Double.parseDouble(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "x"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = "radius"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = "sema"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0242
            java.lang.String r0 = "sema"
            org.json.JSONObject r1 = r11.getJSONObject(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = "aptag"
            boolean r0 = r1.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x01ce
            java.lang.String r0 = "aptag"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r2 != 0) goto L_0x020f
            r14.f367q = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x01ce:
            java.lang.String r0 = "aptagd"
            boolean r0 = r1.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0216
            java.lang.String r0 = "aptagd"
            org.json.JSONObject r0 = r1.getJSONObject(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "pois"
            org.json.JSONArray r2 = r0.getJSONArray(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r3.<init>()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r0 = 0
        L_0x01e8:
            int r4 = r2.length()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 >= r4) goto L_0x0214
            org.json.JSONObject r4 = r2.getJSONObject(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r5 = "pname"
            java.lang.String r5 = r4.getString(r5)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r6 = "pid"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r7 = "pr"
            double r8 = r4.getDouble(r7)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.Poi r4 = new com.baidu.location.Poi     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r4.<init>(r6, r5, r8)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r3.add(r4)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            int r0 = r0 + 1
            goto L_0x01e8
        L_0x020f:
            java.lang.String r0 = ""
            r14.f367q = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x01ce
        L_0x0214:
            r14.L = r3     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0216:
            java.lang.String r0 = "poiregion"
            boolean r0 = r1.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x022c
            java.lang.String r0 = "poiregion"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r2 != 0) goto L_0x022c
            r14.r = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x022c:
            java.lang.String r0 = "regular"
            boolean r0 = r1.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0242
            java.lang.String r0 = "regular"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r1 != 0) goto L_0x0242
            r14.s = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0242:
            java.lang.String r0 = "addr"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x04c6
            r9 = 0
            r8 = 0
            r7 = 0
            r6 = 0
            r5 = 0
            r4 = 0
            r3 = 0
            r2 = 0
            r0 = 0
            r10 = 0
            java.lang.String r1 = "addr"
            org.json.JSONObject r1 = r11.getJSONObject(r1)     // Catch:{ Exception -> 0x046b, Error -> 0x0167 }
            r10 = 1
            r13 = r1
            r1 = r10
            r10 = r13
        L_0x025e:
            if (r10 == 0) goto L_0x0472
            java.lang.String r9 = ""
            java.lang.String r8 = ""
            java.lang.String r7 = ""
            java.lang.String r6 = ""
            java.lang.String r5 = ""
            java.lang.String r4 = ""
            java.lang.String r3 = ""
            java.lang.String r2 = ""
            java.lang.String r0 = ""
            java.lang.String r12 = "city"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x0280
            java.lang.String r6 = "city"
            java.lang.String r6 = r10.getString(r6)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0280:
            java.lang.String r12 = "city_code"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x028e
            java.lang.String r5 = "city_code"
            java.lang.String r5 = r10.getString(r5)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x028e:
            java.lang.String r12 = "country"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x029c
            java.lang.String r9 = "country"
            java.lang.String r9 = r10.getString(r9)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x029c:
            java.lang.String r12 = "country_code"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x02aa
            java.lang.String r8 = "country_code"
            java.lang.String r8 = r10.getString(r8)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x02aa:
            java.lang.String r12 = "province"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x02b8
            java.lang.String r7 = "province"
            java.lang.String r7 = r10.getString(r7)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x02b8:
            java.lang.String r12 = "district"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x02c6
            java.lang.String r4 = "district"
            java.lang.String r4 = r10.getString(r4)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x02c6:
            java.lang.String r12 = "street"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x02d4
            java.lang.String r3 = "street"
            java.lang.String r3 = r10.getString(r3)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x02d4:
            java.lang.String r12 = "street_number"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x02e2
            java.lang.String r2 = "street_number"
            java.lang.String r2 = r10.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x02e2:
            java.lang.String r12 = "adcode"
            boolean r12 = r10.has(r12)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r12 == 0) goto L_0x059e
            java.lang.String r0 = "adcode"
            java.lang.String r0 = r10.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r13 = r1
            r1 = r0
            r0 = r13
        L_0x02f3:
            if (r0 == 0) goto L_0x0327
            com.baidu.location.a$a r0 = new com.baidu.location.a$a     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r0.<init>()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.a(r9)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.c(r8)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.d(r7)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.e(r6)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.f(r5)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.g(r4)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.h(r3)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.i(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a$a r0 = r0.b(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            com.baidu.location.a r0 = r0.a()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.u = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r0 = 1
            r14.o = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0327:
            java.lang.String r0 = "floor"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0342
            java.lang.String r0 = "floor"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.v = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = r14.v     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0342
            r0 = 0
            r14.v = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0342:
            java.lang.String r0 = "indoor"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0361
            java.lang.String r0 = "indoor"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r1 != 0) goto L_0x0361
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0361:
            java.lang.String r0 = "loctp"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x037c
            java.lang.String r0 = "loctp"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.B = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = r14.B     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x037c
            r0 = 0
            r14.B = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x037c:
            java.lang.String r0 = "bldgid"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0397
            java.lang.String r0 = "bldgid"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.w = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = r14.w     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0397
            r0 = 0
            r14.w = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0397:
            java.lang.String r0 = "bldg"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x03b2
            java.lang.String r0 = "bldg"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.x = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = r14.x     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x03b2
            r0 = 0
            r14.x = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x03b2:
            java.lang.String r0 = "ibav"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x03c9
            java.lang.String r0 = "ibav"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r1 == 0) goto L_0x04cf
            r0 = 0
            r14.z = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x03c9:
            java.lang.String r0 = "indoorflags"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x04f5
            java.lang.String r0 = "indoorflags"
            org.json.JSONObject r0 = r11.getJSONObject(r0)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            java.lang.String r1 = "area"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x03f3
            java.lang.String r1 = "area"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 != 0) goto L_0x04e8
            r1 = 2
            r14.b(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
        L_0x03f3:
            java.lang.String r1 = "support"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x040c
            java.lang.String r1 = "support"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            r14.d(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
        L_0x040c:
            java.lang.String r1 = "inbldg"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x041c
            java.lang.String r1 = "inbldg"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            r14.I = r1     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
        L_0x041c:
            java.lang.String r1 = "inbldgid"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x042c
            java.lang.String r1 = "inbldgid"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            r14.J = r1     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
        L_0x042c:
            java.lang.String r1 = "polygon"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x043d
            java.lang.String r1 = "polygon"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            r14.a(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
        L_0x043d:
            java.lang.String r1 = "ret_fields"
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            if (r1 == 0) goto L_0x04f5
            java.lang.String r1 = "ret_fields"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            java.lang.String r1 = "\\|"
            java.lang.String[] r1 = r0.split(r1)     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            int r2 = r1.length     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            r0 = 0
        L_0x0453:
            if (r0 >= r2) goto L_0x04f5
            r3 = r1[r0]     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            java.lang.String r4 = "="
            java.lang.String[] r3 = r3.split(r4)     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            r4 = 0
            r4 = r3[r4]     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            r5 = 1
            r3 = r3[r5]     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            android.os.Bundle r5 = r14.O     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            r5.putString(r4, r3)     // Catch:{ Exception -> 0x0526, Error -> 0x0167 }
            int r0 = r0 + 1
            goto L_0x0453
        L_0x046b:
            r1 = move-exception
            r1 = 0
            r13 = r1
            r1 = r10
            r10 = r13
            goto L_0x025e
        L_0x0472:
            java.lang.String r1 = "addr"
            java.lang.String r1 = r11.getString(r1)     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
            java.lang.String r10 = ","
            java.lang.String[] r1 = r1.split(r10)     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
            int r10 = r1.length     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
            if (r10 <= 0) goto L_0x0484
            r12 = 0
            r7 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x0484:
            r12 = 1
            if (r10 <= r12) goto L_0x048a
            r12 = 1
            r6 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x048a:
            r12 = 2
            if (r10 <= r12) goto L_0x0490
            r12 = 2
            r4 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x0490:
            r12 = 3
            if (r10 <= r12) goto L_0x0496
            r12 = 3
            r3 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x0496:
            r12 = 4
            if (r10 <= r12) goto L_0x049c
            r12 = 4
            r2 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x049c:
            r12 = 5
            if (r10 <= r12) goto L_0x04a2
            r12 = 5
            r5 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x04a2:
            r12 = 6
            if (r10 <= r12) goto L_0x04a8
            r12 = 6
            r9 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x04a8:
            r12 = 7
            if (r10 <= r12) goto L_0x04ae
            r12 = 7
            r8 = r1[r12]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x04ae:
            r12 = 8
            if (r10 <= r12) goto L_0x04b6
            r10 = 8
            r0 = r1[r10]     // Catch:{ Exception -> 0x04bc, Error -> 0x0167 }
        L_0x04b6:
            r1 = 1
            r13 = r1
            r1 = r0
            r0 = r13
            goto L_0x02f3
        L_0x04bc:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r1 = 0
            r13 = r1
            r1 = r0
            r0 = r13
            goto L_0x02f3
        L_0x04c6:
            r0 = 0
            r14.o = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r0 = 0
            r14.e(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x0327
        L_0x04cf:
            java.lang.String r1 = "0"
            boolean r1 = r0.equals(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r1 == 0) goto L_0x04dc
            r0 = 0
            r14.z = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x03c9
        L_0x04dc:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.z = r0     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x03c9
        L_0x04e8:
            r2 = 1
            if (r1 != r2) goto L_0x03f3
            r1 = 1
            r14.b(r1)     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            goto L_0x03f3
        L_0x04f1:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x04f5:
            java.lang.String r0 = "gpscs"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 == 0) goto L_0x052b
            java.lang.String r0 = "gpscs"
            int r0 = r11.getInt(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.k(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
        L_0x0506:
            java.lang.String r0 = "in_cn"
            boolean r0 = r11.has(r0)     // Catch:{ Exception -> 0x0535, Error -> 0x0167 }
            if (r0 == 0) goto L_0x0530
            java.lang.String r0 = "in_cn"
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x0535, Error -> 0x0167 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0535, Error -> 0x0167 }
            r14.h(r0)     // Catch:{ Exception -> 0x0535, Error -> 0x0167 }
        L_0x051b:
            int r0 = r14.A     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            if (r0 != 0) goto L_0x0537
            java.lang.String r0 = "wgs84"
            r14.d(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x0526:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x04f1, Error -> 0x0167 }
            goto L_0x04f5
        L_0x052b:
            r0 = 0
            r14.k(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x0506
        L_0x0530:
            r0 = 1
            r14.h(r0)     // Catch:{ Exception -> 0x0535, Error -> 0x0167 }
            goto L_0x051b
        L_0x0535:
            r0 = move-exception
            goto L_0x051b
        L_0x0537:
            java.lang.String r0 = "gcj02"
            r14.d(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x053e:
            r1 = 66
            if (r2 == r1) goto L_0x0546
            r1 = 68
            if (r2 != r1) goto L_0x0591
        L_0x0546:
            java.lang.String r1 = "content"
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "point"
            org.json.JSONObject r1 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "y"
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r2 = java.lang.Double.parseDouble(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r2 = "x"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            double r2 = java.lang.Double.parseDouble(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r2)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "radius"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.b(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r1 = "isCellChanged"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            r14.a(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            java.lang.String r0 = "gcj02"
            r14.d(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x0591:
            r0 = 167(0xa7, float:2.34E-43)
            if (r2 != r0) goto L_0x009c
            r0 = 2
            r14.h(r0)     // Catch:{ Exception -> 0x014d, Error -> 0x0167 }
            goto L_0x009c
        L_0x059b:
            r1 = move-exception
            goto L_0x012d
        L_0x059e:
            r13 = r1
            r1 = r0
            r0 = r13
            goto L_0x02f3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.<init>(java.lang.String):void");
    }

    private void a(Boolean bool) {
        this.t = bool.booleanValue();
    }

    public String A() {
        return this.u.g;
    }

    public String B() {
        return this.f367q;
    }

    public String C() {
        return this.v;
    }

    public String D() {
        return this.w;
    }

    public String E() {
        return this.x;
    }

    public int F() {
        return this.z;
    }

    public int G() {
        return this.P;
    }

    public String H() {
        return this.B;
    }

    public int I() {
        return this.C;
    }

    public List<Poi> a() {
        return this.L;
    }

    public void a(double d2) {
        this.c = d2;
    }

    public void a(float f2) {
        this.h = f2;
        this.g = true;
    }

    public void a(int i2) {
        this.E = i2;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.u = aVar;
            this.o = true;
        }
    }

    public void a(String str) {
        this.K = str;
    }

    public void a(String str, double[] dArr) {
        if (this.O == null) {
            this.O = new Bundle();
        }
        this.O.putDoubleArray(str, dArr);
    }

    public void a(List<Poi> list) {
        this.L = list;
    }

    public void a(boolean z2) {
        this.y = z2;
    }

    public int b() {
        return this.E;
    }

    public void b(double d2) {
        this.d = d2;
    }

    public void b(float f2) {
        this.j = f2;
        this.i = true;
    }

    public void b(int i2) {
        this.F = i2;
    }

    public void b(String str) {
        this.b = str;
        c(j.a(str));
    }

    public String c() {
        return this.I;
    }

    public void c(double d2) {
        if (d2 < 9999.0d) {
            this.f = d2;
            this.e = true;
        }
    }

    public void c(float f2) {
        this.m = f2;
    }

    public void c(int i2) {
        this.G = i2;
    }

    public void c(String str) {
        this.N = str;
    }

    public String d() {
        return this.J;
    }

    public void d(int i2) {
        this.H = i2;
    }

    public void d(String str) {
        this.n = str;
    }

    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.H;
    }

    public void e(int i2) {
        this.a = i2;
        switch (i2) {
            case 61:
                f("GPS location successful!");
                a(0);
                return;
            case 62:
                f("Location failed beacuse we can not get any loc information!");
                return;
            case 63:
            case 67:
                f("Offline location failed, please check the net (wifi/cell)!");
                return;
            case 66:
                f("Offline location successful!");
                return;
            case ErrorCode.STARTDOWNLOAD_2 /*161*/:
                f("NetWork location successful!");
                return;
            case ErrorCode.STARTDOWNLOAD_3 /*162*/:
                f("NetWork location failed because baidu location service can not decrypt the request query, please check the so file !");
                return;
            case ErrorCode.STARTDOWNLOAD_8 /*167*/:
                f("NetWork location failed because baidu location service can not caculate the location!");
                return;
            case ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_COUNTS /*505*/:
                f("NetWork location failed because baidu location service check the key is unlegal, please check the key in AndroidManifest.xml !");
                return;
            default:
                f("UnKnown!");
                return;
        }
    }

    public void e(String str) {
        this.p = str;
        if (str == null) {
            this.o = false;
        } else {
            this.o = true;
        }
    }

    public String f() {
        return this.K;
    }

    public void f(int i2) {
        this.l = i2;
    }

    public void f(String str) {
        this.M = str;
    }

    public String g() {
        return this.b;
    }

    public void g(int i2) {
        this.z = i2;
    }

    public void g(String str) {
        this.f367q = str;
    }

    public double h() {
        return this.c;
    }

    public void h(int i2) {
        this.A = i2;
    }

    public void h(String str) {
        this.v = str;
    }

    public double i() {
        return this.d;
    }

    public void i(int i2) {
        this.P = i2;
    }

    public void i(String str) {
        this.w = str;
    }

    public double j() {
        return this.f;
    }

    public void j(int i2) {
        this.C = i2;
    }

    public void j(String str) {
        this.x = str;
    }

    public float k() {
        return this.h;
    }

    public void k(int i2) {
        this.Q = i2;
    }

    public void k(String str) {
        this.B = str;
    }

    public float l() {
        return this.j;
    }

    public String l(String str) {
        return this.O.getString(str);
    }

    public String m() {
        return this.n;
    }

    public boolean n() {
        return this.e;
    }

    public int o() {
        return this.a;
    }

    public String p() {
        return this.M;
    }

    public int q() {
        this.k = true;
        return this.l;
    }

    public float r() {
        return this.m;
    }

    public boolean s() {
        return this.o;
    }

    public a t() {
        return this.u;
    }

    public String u() {
        return this.u.i;
    }

    public String v() {
        return this.u.d;
    }

    public String w() {
        return this.u.e;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeDouble(this.c);
        parcel.writeDouble(this.d);
        parcel.writeDouble(this.f);
        parcel.writeFloat(this.h);
        parcel.writeFloat(this.j);
        parcel.writeInt(this.l);
        parcel.writeFloat(this.m);
        parcel.writeString(this.v);
        parcel.writeInt(this.z);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.B);
        parcel.writeString(this.u.c);
        parcel.writeString(this.u.d);
        parcel.writeString(this.u.f);
        parcel.writeString(this.u.g);
        parcel.writeString(this.u.h);
        parcel.writeString(this.u.e);
        parcel.writeString(this.u.i);
        parcel.writeString(this.u.a);
        parcel.writeString(this.u.b);
        parcel.writeString(this.u.j);
        parcel.writeInt(this.C);
        parcel.writeString(this.D);
        parcel.writeString(this.f367q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeInt(this.A);
        parcel.writeString(this.M);
        parcel.writeInt(this.E);
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeInt(this.P);
        parcel.writeString(this.N);
        parcel.writeInt(this.Q);
        parcel.writeBooleanArray(new boolean[]{this.e, this.g, this.i, this.k, this.o, this.t, this.y});
        parcel.writeList(this.L);
        parcel.writeBundle(this.O);
    }

    public String x() {
        return this.u.a;
    }

    public String y() {
        return this.u.b;
    }

    public String z() {
        return this.u.f;
    }
}
