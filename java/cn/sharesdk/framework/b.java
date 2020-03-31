package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.f;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.InternationalDomain;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CheckAppKeyRequestUrl */
public class b {
    private static String a = "";
    private static volatile b b = null;
    private DeviceHelper c = DeviceHelper.getInstance(MobSDK.getContext());
    private NetworkHelper d = new NetworkHelper();
    private String e;

    private b() {
        InternationalDomain domain = MobSDK.getDomain();
        if (new f().a(domain) && domain != null) {
            a = domain.getDomain();
        }
        this.e = a(MobSDK.checkRequestUrl("api.share.mob.com"));
    }

    public static b a() {
        synchronized (b.class) {
            if (b == null) {
                synchronized (b.class) {
                    if (b == null) {
                        b = new b();
                    }
                }
            }
        }
        return b;
    }

    public void b() {
        try {
            ArrayList arrayList = new ArrayList();
            String appkey = MobSDK.getAppkey();
            if (!TextUtils.isEmpty(appkey)) {
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
                HashMap fromJson = new Hashon().fromJson(this.d.httpPost(c(), arrayList, null, arrayList2, networkTimeOut));
                if (!fromJson.containsKey("error")) {
                    a.b = appkey;
                } else if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    a.a = true;
                }
            }
        } catch (Throwable th) {
            SSDKLog.b().d("updateServerConfig " + th, new Object[0]);
        }
    }

    private String c() {
        return this.e + "/conf5";
    }

    private String a(String str) {
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
}
