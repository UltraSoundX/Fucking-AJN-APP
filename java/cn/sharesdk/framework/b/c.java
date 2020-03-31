package cn.sharesdk.framework.b;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.b;
import cn.sharesdk.framework.b.a.d;
import cn.sharesdk.framework.b.a.e;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.f;
import com.baidu.mobstat.Config;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.InternationalDomain;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: Protocols */
public class c {
    private static String a = "";
    private static MobCommunicator j;
    private e b = e.a();
    private DeviceHelper c = DeviceHelper.getInstance(MobSDK.getContext());
    private NetworkHelper d = new NetworkHelper();
    private Hashon e = new Hashon();
    private String f;
    private String g;
    private boolean h;
    private HashMap<String, String> i;

    private static synchronized MobCommunicator g() {
        MobCommunicator mobCommunicator;
        synchronized (c.class) {
            if (j == null) {
                j = new MobCommunicator(1024, "bb7addd7e33383b74e82aba9b1d274c73aea6c0c71fcc88730270f630dbe490e1d162004f74e9532f98e17004630fbea9b346de63c23e83a7dfad70dd47cebfd", "288e7c44e01569a905386e6341baabfcde63ec37d0f0835cc662c299a5d0072970808a7fa434f0a51fa581d09d5ec4350ba5d548eafbe1fd956fb3afd678c1fb6134c904668652ec5cceb5d85da337a0f2f13ea457cca74a01b3ba0f4c809ad30d382bba2562ec9b996ae44c3700731c1b914997ef826331759e4084a019a03f");
            }
            mobCommunicator = j;
        }
        return mobCommunicator;
    }

    public c() {
        try {
            this.i = (HashMap) this.b.j("buffered_server_paths");
        } catch (Throwable th) {
            this.i = new HashMap<>();
        }
        InternationalDomain domain = MobSDK.getDomain();
        if (new f().a(domain) && domain != null) {
            a = domain.getDomain();
        }
        h();
    }

    private void h() {
        String str = this.c.getPackageName() + "/" + this.c.getAppVersionName();
        this.f = str + " " + "ShareSDK/3.6.8" + " " + ("Android/" + this.c.getOSVersionInt());
        this.g = b(MobSDK.checkRequestUrl("api.share.mob.com"));
        this.h = true;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            SSDKLog.b().d("duid === " + str, new Object[0]);
            this.f += " " + str;
        }
    }

    public String b(String str) {
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            int indexOf = str.indexOf("://");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(0, indexOf + 3));
            stringBuffer.append(a + ".");
            stringBuffer.append(str.substring(indexOf + 3, str.length()));
            str = stringBuffer.toString();
            SSDKLog.b().d("DomainUrl = " + str, new Object[0]);
            return str;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return str;
        }
    }

    public void c(String str) {
        this.g = str;
    }

    public void a(HashMap<String, String> hashMap) {
        this.i = hashMap;
        this.b.a("buffered_server_paths", (Object) this.i);
    }

    private String i() {
        return this.g + "/conn";
    }

    public HashMap<String, Object> a() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", a.a()));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = Config.SESSION_PERIOD;
        networkTimeOut.connectionTimeout = Config.SESSION_PERIOD;
        String httpPost = this.d.httpPost(i(), arrayList, null, arrayList2, networkTimeOut);
        SSDKLog.b().i(" isConnectToServer response == %s", httpPost);
        return this.e.fromJson(httpPost);
    }

    private String j() {
        if (this.i == null || !this.i.containsKey("/date")) {
            return this.g + "/date";
        }
        return ((String) this.i.get("/date")) + "/date";
    }

    public long b() throws Throwable {
        if (!this.b.k()) {
            return 0;
        }
        String str = "{}";
        try {
            str = this.d.httpGet(j(), null, null, null);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
        HashMap fromJson = this.e.fromJson(str);
        if (!fromJson.containsKey("timestamp")) {
            return this.b.b();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(fromJson.get("timestamp")));
            this.b.a("service_time", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (Throwable th2) {
            SSDKLog.b().d(th2);
            return this.b.b();
        }
    }

    private String k() {
        return this.g + "/conf5";
    }

    public HashMap<String, Object> c() throws Throwable {
        String appkey = MobSDK.getAppkey();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", appkey));
        arrayList.add(new KVPair(Config.DEVICE_PART, this.c.getDeviceKey()));
        arrayList.add(new KVPair("plat", String.valueOf(this.c.getPlatformCode())));
        arrayList.add(new KVPair("apppkg", this.c.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.c.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(ShareSDK.SDK_VERSION_CODE)));
        arrayList.add(new KVPair("networktype", this.c.getDetailNetworkTypeForStatic()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", a.a()));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        String httpPost = this.d.httpPost(k(), arrayList, null, arrayList2, networkTimeOut);
        try {
            HashMap fromJson = new Hashon().fromJson(httpPost);
            if (fromJson.containsKey("error")) {
                if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    if (TextUtils.isEmpty(appkey)) {
                        b.a().b();
                    } else {
                        cn.sharesdk.framework.a.a = true;
                    }
                }
                SSDKLog.b().i(" get server config response == %s", httpPost);
                return this.e.fromJson(httpPost);
            }
            if (!TextUtils.isEmpty(appkey)) {
                cn.sharesdk.framework.a.b = appkey;
            }
            SSDKLog.b().i(" get server config response == %s", httpPost);
            return this.e.fromJson(httpPost);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    private String l() {
        return b(MobSDK.checkRequestUrl("up.mob.com/upload/image"));
    }

    public HashMap<String, Object> d(String str) throws Throwable {
        KVPair kVPair = new KVPair("file", str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("User-Identity", a.a()));
        String httpPost = this.d.httpPost(l(), null, kVPair, arrayList, (NetworkTimeOut) null);
        SSDKLog.b().i("upload file response == %s", httpPost);
        return this.e.fromJson(httpPost);
    }

    private String m() {
        if (this.i == null || !this.i.containsKey("/log4")) {
            return this.g + "/log4";
        }
        return ((String) this.i.get("/log4")) + "/log4";
    }

    public boolean a(String str, boolean z) {
        try {
            if (!MobSDK.isMob()) {
                return true;
            }
            if ("none".equals(this.c.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair(Config.MODEL, str));
            arrayList.add(new KVPair("t", z ? "1" : "0"));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Identity", a.a()));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = Config.SESSION_PERIOD;
            networkTimeOut.connectionTimeout = Config.SESSION_PERIOD;
            String httpPost = this.d.httpPost(m(), arrayList, null, arrayList2, networkTimeOut);
            SSDKLog.b().i("> Upload All Log  resp: %s", httpPost);
            if (TextUtils.isEmpty(httpPost) || ((Integer) this.e.fromJson(httpPost).get(NotificationCompat.CATEGORY_STATUS)).intValue() == 200) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return false;
        }
    }

    private String n() {
        return b(MobSDK.checkRequestUrl("l.mob.com/url/shareSdkEncryptMapping.do"));
    }

    public HashMap<String, Object> a(String str, ArrayList<String> arrayList, int i2, String str2) throws Throwable {
        if (!this.h) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(SettingsContentProvider.KEY, MobSDK.getAppkey()));
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList2.add(new KVPair("urls", ((String) arrayList.get(i3)).toString()));
        }
        arrayList2.add(new KVPair("deviceid", this.c.getDeviceKey()));
        arrayList2.add(new KVPair("snsplat", String.valueOf(i2)));
        String f2 = f(str2);
        if (TextUtils.isEmpty(f2)) {
            return null;
        }
        arrayList2.add(new KVPair(Config.MODEL, f2));
        new ArrayList().add(new KVPair("User-Identity", a.a()));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = ReaderCallback.GET_BAR_ANIMATING;
        networkTimeOut.connectionTimeout = ReaderCallback.GET_BAR_ANIMATING;
        HashMap hashMap = new HashMap();
        hashMap.put(SettingsContentProvider.KEY, MobSDK.getAppkey());
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            arrayList3.add(URLEncoder.encode((String) arrayList.get(i4), "UTF-8"));
        }
        hashMap.put("urls", arrayList3);
        hashMap.put("deviceid", this.c.getDeviceKey());
        hashMap.put("snsplat", Integer.valueOf(i2));
        if (TextUtils.isEmpty(f2)) {
            return null;
        }
        hashMap.put(Config.MODEL, f2);
        HashMap<String, Object> hashMap2 = (HashMap) g().requestSynchronized(hashMap, n(), false);
        SSDKLog.b().i("> SERVER_SHORT_LINK_URL  resp: %s", hashMap2);
        if (hashMap2.size() == 0) {
            this.h = false;
            return null;
        } else if (hashMap2.get("data") == null) {
            return null;
        } else {
            return hashMap2;
        }
    }

    private String f(String str) throws Throwable {
        boolean c2 = this.b.c();
        boolean d2 = this.b.d();
        StringBuilder sb = new StringBuilder();
        sb.append(Data.urlEncode(this.c.getPackageName(), "utf-8")).append("|");
        sb.append(Data.urlEncode(this.c.getAppVersionName(), "utf-8")).append("|");
        sb.append(Data.urlEncode(String.valueOf(ShareSDK.SDK_VERSION_CODE), "utf-8")).append("|");
        sb.append(Data.urlEncode(String.valueOf(this.c.getPlatformCode()), "utf-8")).append("|");
        sb.append(Data.urlEncode(this.c.getDetailNetworkTypeForStatic(), "utf-8")).append("|");
        if (c2) {
            sb.append(Data.urlEncode(String.valueOf(this.c.getOSVersionInt()), "utf-8")).append("|");
            sb.append(Data.urlEncode(this.c.getScreenSize(), "utf-8")).append("|");
            sb.append(Data.urlEncode(this.c.getManufacturer(), "utf-8")).append("|");
            sb.append(Data.urlEncode(this.c.getModel(), "utf-8")).append("|");
            sb.append(Data.urlEncode(this.c.getCarrier(), "utf-8")).append("|");
        } else {
            sb.append("|||||");
        }
        if (d2) {
            sb.append(str);
        } else {
            sb.append(str.split("\\|")[0]);
            sb.append("|||||");
        }
        String sb2 = sb.toString();
        SSDKLog.b().i("shorLinkMsg ===>>>>", sb2);
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.c.getDeviceKey(), MobSDK.getAppkey()})), sb2), 2);
    }

    private String o() {
        if (this.i == null || !this.i.containsKey("/snsconf")) {
            return this.g + "/snsconf";
        }
        return ((String) this.i.get("/snsconf")) + "/snsconf";
    }

    public HashMap<String, Object> d() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", MobSDK.getAppkey()));
        arrayList.add(new KVPair(Config.DEVICE_PART, this.c.getDeviceKey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Identity", a.a()));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        return this.e.fromJson(this.d.httpPost(o(), arrayList, null, arrayList2, networkTimeOut));
    }

    public void a(cn.sharesdk.framework.b.b.c cVar) throws Throwable {
        d.a(cVar.toString(), cVar.e);
    }

    public ArrayList<cn.sharesdk.framework.b.a.c> e() throws Throwable {
        ArrayList<cn.sharesdk.framework.b.a.c> a2 = d.a();
        if (a2 == null) {
            return new ArrayList<>();
        }
        return a2;
    }

    public void a(ArrayList<String> arrayList) throws Throwable {
        d.a(arrayList);
    }

    public HashMap<String, Object> f() throws Throwable {
        return this.e.fromJson(this.b.i());
    }

    public void b(HashMap<String, Object> hashMap) throws Throwable {
        this.b.g(this.e.fromHashMap(hashMap));
    }

    public HashMap<String, Object> e(String str) throws Throwable {
        return this.e.fromJson(new String(Data.AES128Decode(Data.rawMD5(MobSDK.getAppkey() + Config.TRACE_TODAY_VISIT_SPLIT + this.c.getDeviceKey()), Base64.decode(str, 2)), "UTF-8").trim());
    }
}
