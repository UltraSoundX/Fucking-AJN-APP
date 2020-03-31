package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.ar.b;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CooperService implements ICooperService {
    private static CooperService a;
    private HeadObject b = new HeadObject();

    public static synchronized CooperService instance() {
        CooperService cooperService;
        synchronized (CooperService.class) {
            if (a == null) {
                a = new CooperService();
            }
            cooperService = a;
        }
        return cooperService;
    }

    public HeadObject getHeadObject() {
        return this.b;
    }

    public String getHost() {
        return Config.LOG_SEND_URL;
    }

    public void installHeader(Context context, JSONObject jSONObject) {
        this.b.installHeader(context, jSONObject);
    }

    public JSONObject getHeaderExt(Context context) {
        String k = av.a().k(context);
        if (TextUtils.isEmpty(k)) {
            return null;
        }
        try {
            return new JSONObject(k);
        } catch (JSONException e) {
            return null;
        }
    }

    public void setHeaderExt(Context context, ExtraInfo extraInfo) {
        String str;
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.b.setHeaderExt(jSONObject);
        av.a().g(context, jSONObject.toString());
        String str2 = "";
        if (extraInfo != null) {
            str = "Set global ExtraInfo: " + jSONObject;
        } else {
            str = "Clear global ExtraInfo";
        }
        am.c().a(str);
    }

    public JSONObject getPushId(Context context) {
        String l = av.a().l(context);
        if (TextUtils.isEmpty(l)) {
            return null;
        }
        try {
            return new JSONObject(l);
        } catch (JSONException e) {
            return null;
        }
    }

    public void setPushId(Context context, String str, String str2, String str3) {
        String str4;
        JSONObject pushId = getPushId(context);
        if (pushId == null) {
            pushId = new JSONObject();
        }
        try {
            if (!TextUtils.isEmpty(str3)) {
                pushId.put(str, str3);
            } else {
                pushId.remove(str);
            }
        } catch (Exception e) {
        }
        this.b.setPushInfo(pushId);
        av.a().h(context, pushId.toString());
        String str5 = "";
        if (str3 != null) {
            str4 = "Set platform:" + str2 + " pushId: " + str3;
        } else {
            str4 = "Clear platform:" + str2 + " pushId";
        }
        am.c().a(str4);
    }

    public void setStartType(boolean z) {
        this.b.setStartType(z);
    }

    private static String a(Context context) {
        String k = bb.k(context);
        if (!TextUtils.isEmpty(k)) {
            return k.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return k;
    }

    private static String b(Context context) {
        String j = bb.j(context);
        if (!TextUtils.isEmpty(j)) {
            return j.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return j;
    }

    private static String c(Context context) {
        String m = bb.m(context);
        if (!TextUtils.isEmpty(m)) {
            return m.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return m;
    }

    public String getMacAddress(Context context, boolean z) {
        String replace = Config.DEF_MAC_ID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        if (!z && VERSION.SDK_INT >= 23) {
            return getSecretValue(replace);
        }
        if (!TextUtils.isEmpty(this.b.s)) {
            return this.b.s;
        }
        String h = av.a().h(context);
        if (!TextUtils.isEmpty(h)) {
            this.b.s = h;
            return this.b.s;
        }
        String a2 = a(context, z);
        if (TextUtils.isEmpty(a2) || replace.equals(a2)) {
            this.b.s = "";
            return this.b.s;
        }
        this.b.s = getSecretValue(a2);
        av.a().e(context, this.b.s);
        return this.b.s;
    }

    private String a(Context context, boolean z) {
        String a2;
        if (z) {
            a2 = b(context);
        } else {
            a2 = a(context);
        }
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        return a2;
    }

    public String getMacIdForTv(Context context) {
        if (!TextUtils.isEmpty(this.b.t)) {
            return this.b.t;
        }
        String j = av.a().j(context);
        if (!TextUtils.isEmpty(j)) {
            this.b.t = j;
            return this.b.t;
        }
        String c = bb.c(1, context);
        if (!TextUtils.isEmpty(c)) {
            this.b.t = c;
            av.a().f(context, c);
            return this.b.t;
        }
        this.b.t = "";
        return this.b.t;
    }

    public String getCUID(Context context, boolean z) {
        av.a().b(context, "");
        if (this.b.f == null || "".equalsIgnoreCase(this.b.f)) {
            try {
                this.b.f = bc.a(context);
                Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.b.f);
                this.b.f = matcher.replaceAll("");
                this.b.f = getSecretValue(this.b.f);
            } catch (Exception e) {
            }
        }
        if (z) {
            return this.b.f;
        }
        try {
            String str = this.b.f;
            if (!TextUtils.isEmpty(str)) {
                return new String(b.b(1, au.a(str.getBytes())));
            }
        } catch (Exception e2) {
        }
        return null;
    }

    public int getTagValue() {
        return 1;
    }

    public String getDevicImei(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    public String getDeviceId(TelephonyManager telephonyManager, Context context) {
        if (!TextUtils.isEmpty(this.b.i)) {
            return this.b.i;
        }
        if (av.a().i(context)) {
            this.b.i = getMacIdForTv(context);
            return this.b.i;
        }
        String p = av.a().p(context);
        if (!TextUtils.isEmpty(p)) {
            this.b.i = p;
            return this.b.i;
        } else if (telephonyManager == null) {
            return this.b.i;
        } else {
            Pattern compile = Pattern.compile("\\s*|\t|\r|\n");
            try {
                String deviceId = telephonyManager.getDeviceId();
                if (deviceId != null) {
                    p = compile.matcher(deviceId).replaceAll("");
                }
            } catch (Exception e) {
            }
            if (p == null || p.equals(Config.NULL_DEVICE_ID)) {
                p = a(context);
            }
            if (bb.u(context) && (TextUtils.isEmpty(p) || p.equals(Config.NULL_DEVICE_ID))) {
                try {
                    p = c(context);
                } catch (Exception e2) {
                }
            }
            if (TextUtils.isEmpty(p) || p.equals(Config.NULL_DEVICE_ID)) {
                p = d(context);
            }
            this.b.i = p;
            this.b.i = getSecretValue(this.b.i);
            return this.b.i;
        }
    }

    private String d(Context context) {
        String e = av.a().e(context);
        if (!TextUtils.isEmpty(e) && !e.equals(Config.NULL_DEVICE_ID)) {
            return e;
        }
        String str = "hol" + (new Date().getTime() + "").hashCode() + "mes";
        av.a().a(context, str);
        return str;
    }

    public String getPlainDeviceIdForCar(Context context) {
        String optUUID = CarUUID.optUUID(context);
        if (TextUtils.isEmpty(optUUID)) {
            optUUID = d(context);
        }
        return TextUtils.isEmpty(optUUID) ? "" : optUUID;
    }

    public String getAppChannel(Context context) {
        return e(context);
    }

    private String e(Context context) {
        try {
            if (this.b.l == null || this.b.l.equals("")) {
                boolean g = av.a().g(context);
                if (g) {
                    this.b.l = av.a().f(context);
                }
                if (!g || this.b.l == null || this.b.l.equals("")) {
                    this.b.l = bb.a(context, Config.CHANNEL_META_NAME);
                }
            }
        } catch (Exception e) {
        }
        return this.b.l;
    }

    public String getAppKey(Context context) {
        if (this.b.e == null) {
            this.b.e = bb.a(context, Config.APPKEY_META_NAME);
        }
        return this.b.e;
    }

    public String getMTJSDKVersion() {
        return "3.9.7.0";
    }

    public int getAppVersionCode(Context context) {
        if (this.b.g == -1) {
            this.b.g = bb.f(context);
        }
        return this.b.g;
    }

    public String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(this.b.h)) {
            this.b.h = bb.g(context);
        }
        return this.b.h;
    }

    public void setAppVersionName(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.h = str;
        }
    }

    public String getOperator(TelephonyManager telephonyManager) {
        if (TextUtils.isEmpty(this.b.m)) {
            this.b.m = telephonyManager.getNetworkOperator();
        }
        return this.b.m;
    }

    public String getLinkedWay(Context context) {
        if (TextUtils.isEmpty(this.b.r)) {
            this.b.r = bb.q(context);
        }
        return this.b.r;
    }

    public String getOSVersion() {
        if (TextUtils.isEmpty(this.b.b)) {
            this.b.b = Integer.toString(VERSION.SDK_INT);
        }
        return this.b.b;
    }

    public String getOSSysVersion() {
        if (TextUtils.isEmpty(this.b.c)) {
            this.b.c = VERSION.RELEASE;
        }
        return this.b.c;
    }

    public String getPhoneModel() {
        if (TextUtils.isEmpty(this.b.n)) {
            this.b.n = Build.MODEL;
        }
        return this.b.n;
    }

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.b.o)) {
            this.b.o = Build.MANUFACTURER;
        }
        return this.b.o;
    }

    public boolean checkWifiLocationSetting(Context context) {
        String str = "";
        return "true".equalsIgnoreCase(bb.a(context, Config.GET_WIFI_LOCATION));
    }

    public boolean checkGPSLocationSetting(Context context) {
        String str = "";
        return "true".equals(bb.a(context, Config.GET_GPS_LOCATION));
    }

    public boolean checkCellLocationSetting(Context context) {
        String str = "";
        return "true".equalsIgnoreCase(bb.a(context, Config.GET_CELL_LOCATION));
    }

    public String getSecretValue(String str) {
        return b.c(1, str.getBytes());
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void resetHeadSign() {
        this.b.z = instance().getUUID();
    }

    public void enableDeviceMac(Context context, boolean z) {
        av.a().d(context, z);
    }

    public boolean isDeviceMacEnabled(Context context) {
        return av.a().m(context);
    }

    public void setUserId(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        av.a().i(context, str);
        this.b.setUserId(str);
        am.c().a("Set user id " + str);
    }

    public String getUserId(Context context) {
        return av.a().n(context);
    }

    public String getLastUserId(Context context) {
        return av.a().o(context);
    }

    public void setLastUserId(Context context, String str) {
        av.a().j(context, str);
    }

    public void setUserProperty(Context context, Map<String, String> map) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            try {
                av.a().l(context, "");
                this.b.setUserProperty("");
            } catch (Exception e) {
                am.c().c("[Exception] " + e.getMessage());
                e.printStackTrace();
                z = false;
            }
        } else if (map.size() > 100) {
            am.c().c("[WARNING] setUserProperty failed,map size can not over 100 !");
        } else {
            for (Entry entry : map.entrySet()) {
                JSONArray jSONArray = new JSONArray();
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (TextUtils.isEmpty(str) || str2 == null) {
                    am.c().c("[WARNING] setUserProperty failed,key or value can not null !");
                    return;
                } else if (str.length() > 256 || (!TextUtils.isEmpty(str2) && str2.length() > 256)) {
                    am.c().c("[WARNING] setUserProperty failed,key or value can not over 256 bytes !");
                    return;
                } else {
                    jSONArray.put(str2);
                    jSONArray.put("1");
                    jSONObject.put(str, jSONArray);
                }
            }
            z = true;
            if (z) {
                av.a().l(context, jSONObject.toString());
                this.b.setUserProperty(jSONObject.toString());
            }
        }
    }

    public void setZid(String str) {
    }
}
