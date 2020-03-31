package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.mid.core.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

public class HeadObject {
    JSONObject A;
    JSONObject B;
    String C;
    int D;
    String E = "";
    String F;
    boolean a = false;
    String b;
    String c;
    String d = "0";
    String e = null;
    String f = null;
    int g = -1;
    String h;
    String i;
    int j;
    int k;
    String l = null;
    String m;
    String n;
    String o;
    String p;

    /* renamed from: q reason: collision with root package name */
    String f386q;
    String r;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    public synchronized void installHeader(Context context, JSONObject jSONObject) {
        a(context);
        if (jSONObject.length() <= 10) {
            updateHeader(context, jSONObject);
        }
    }

    private synchronized void a(Context context) {
        if (!this.a) {
            at.e(context, Constants.PERMISSION_READ_PHONE_STATE);
            at.e(context, Constants.PERMISSION_INTERNET);
            at.e(context, Constants.PERMISSION_ACCESS_NETWORK_STATE);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.b = CooperService.instance().getOSVersion();
            this.c = CooperService.instance().getOSSysVersion();
            this.n = CooperService.instance().getPhoneModel();
            this.o = CooperService.instance().getManufacturer();
            this.z = CooperService.instance().getUUID();
            this.A = CooperService.instance().getHeaderExt(context);
            this.B = CooperService.instance().getPushId(context);
            this.i = CooperService.instance().getDeviceId(telephonyManager, context);
            this.d = av.a().i(context) ? "1" : "0";
            if (bb.u(context)) {
                this.d = "2";
            }
            this.d += "-0";
            try {
                this.s = CooperService.instance().getMacAddress(context, CooperService.instance().isDeviceMacEnabled(context));
            } catch (Exception e2) {
            }
            try {
                this.u = bb.f(1, context);
            } catch (Exception e3) {
            }
            try {
                this.v = bb.a(context, 1);
            } catch (Exception e4) {
            }
            this.f = CooperService.instance().getCUID(context, true);
            try {
                this.m = CooperService.instance().getOperator(telephonyManager);
            } catch (Exception e5) {
            }
            try {
                this.j = bb.c(context);
                this.k = bb.d(context);
                if (context.getResources().getConfiguration().orientation == 2) {
                    this.j ^= this.k;
                    this.k = this.j ^ this.k;
                    this.j ^= this.k;
                }
            } catch (Exception e6) {
            }
            this.l = CooperService.instance().getAppChannel(context);
            this.e = CooperService.instance().getAppKey(context);
            try {
                this.g = CooperService.instance().getAppVersionCode(context);
                this.h = CooperService.instance().getAppVersionName(context);
            } catch (Exception e7) {
            }
            try {
                if (CooperService.instance().checkCellLocationSetting(context)) {
                    this.p = bb.h(context);
                } else {
                    this.p = "0_0_0";
                }
            } catch (Exception e8) {
            }
            try {
                if (CooperService.instance().checkGPSLocationSetting(context)) {
                    this.f386q = bb.i(context);
                } else {
                    this.f386q = "";
                }
            } catch (Exception e9) {
            }
            try {
                this.r = CooperService.instance().getLinkedWay(context);
            } catch (Exception e10) {
            }
            this.w = bb.b();
            this.x = Build.BOARD;
            this.y = Build.BRAND;
            this.C = CooperService.instance().getUserId(context);
            this.a = true;
            this.E = av.a().q(context);
        }
    }

    public synchronized void updateHeader(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2;
        int i2 = 0;
        synchronized (this) {
            try {
                jSONObject.put(Config.OS, "Android" == null ? "" : "Android");
                jSONObject.put(Config.STAT_SDK_TYPE, 0);
                jSONObject.put("s", this.b == null ? "" : this.b);
                jSONObject.put("sv", this.c == null ? "" : this.c);
                jSONObject.put(Config.APP_KEY, this.e == null ? "" : this.e);
                jSONObject.put(Config.PLATFORM_TYPE, this.d == null ? "0" : this.d);
                jSONObject.put("i", "");
                jSONObject.put("v", "3.9.7.0");
                jSONObject.put(Config.STAT_SDK_CHANNEL, 0);
                jSONObject.put(Config.APP_VERSION_CODE, this.g);
                jSONObject.put("n", this.h == null ? "" : this.h);
                jSONObject.put("d", "");
                jSONObject.put(Config.DEVICE_MAC_ID, this.s == null ? "" : this.s);
                jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, this.u == null ? "" : this.u);
                jSONObject.put(Config.DEVICE_ID_SEC, this.i == null ? "" : this.i);
                jSONObject.put(Config.CUID_SEC, this.f == null ? "" : this.f);
                jSONObject.put(Config.SDK_TAG, 1);
                jSONObject.put(Config.DEVICE_WIDTH, this.j);
                jSONObject.put("h", this.k);
                jSONObject.put(Config.DEVICE_NAME, this.v == null ? "" : this.v);
                jSONObject.put("c", this.l == null ? "" : this.l);
                jSONObject.put(Config.OPERATOR, this.m == null ? "" : this.m);
                jSONObject.put(Config.MODEL, this.n == null ? "" : this.n);
                jSONObject.put(Config.MANUFACTURER, this.o == null ? "" : this.o);
                jSONObject.put(Config.CELL_LOCATION, this.p == null ? "" : this.p);
                jSONObject.put(Config.GPS_LOCATION, this.f386q == null ? "" : this.f386q);
                jSONObject.put("l", this.r == null ? "" : this.r);
                jSONObject.put("t", System.currentTimeMillis());
                jSONObject.put(Config.PACKAGE_NAME, bb.h(1, context));
                jSONObject.put(Config.ROM, this.w == null ? "" : this.w);
                jSONObject.put(Config.DEVICE_BOARD, this.x == null ? "" : this.x);
                jSONObject.put(Config.DEVICE_BRAND, this.y == null ? "" : this.y);
                jSONObject.put(Config.TEST_DEVICE_ID, bb.b(context));
                String str = Config.TARGET_SDK_VERSION;
                if (!(context == null || context.getApplicationInfo() == null)) {
                    i2 = context.getApplicationInfo().targetSdkVersion;
                }
                jSONObject.put(str, i2);
                jSONObject.put(Config.USER_PROPERTY, this.E);
                if (!TextUtils.isEmpty(this.C)) {
                    if (!TextUtils.isEmpty(this.E)) {
                        jSONObject2 = new JSONObject(this.E);
                    } else {
                        jSONObject2 = new JSONObject();
                    }
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(this.C);
                    jSONArray.put("1");
                    jSONObject2.put("uid_", jSONArray);
                    jSONObject.put(Config.USER_PROPERTY, jSONObject2.toString());
                }
                jSONObject.put(Config.UID_CHANGE, "");
                jSONObject.put("at", "0");
                String s2 = bb.s(context);
                jSONObject.put(Config.PROCESS_LABEL, s2);
                String str2 = null;
                if (!TextUtils.isEmpty(s2)) {
                    str2 = bb.t(context);
                }
                String str3 = Config.PROCESS_CLASS;
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(str3, str2);
                jSONObject.put("sign", this.z == null ? "" : this.z);
                if (this.A == null || this.A.length() == 0) {
                    jSONObject.remove("ext");
                } else {
                    jSONObject.put("ext", this.A);
                }
                if (this.B == null) {
                    this.B = new JSONObject();
                }
                jSONObject.put(Config.PUSH, this.B);
                jSONObject.put(Config.CUSTOM_USER_ID, this.C);
                jSONObject.put(Config.START_TYPE, String.valueOf(this.D));
            } catch (Exception e2) {
            }
        }
    }

    public void setHeaderExt(JSONObject jSONObject) {
        this.A = jSONObject;
    }

    public void setPushInfo(JSONObject jSONObject) {
        this.B = jSONObject;
    }

    public void setUserId(String str) {
        this.C = str;
    }

    public void setUserProperty(String str) {
        this.E = str;
    }

    public void setZid(String str) {
        this.F = str;
    }

    public void setStartType(boolean z2) {
        if (z2) {
            this.D = 1;
        } else {
            this.D = 0;
        }
    }
}
