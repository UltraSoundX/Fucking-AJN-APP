package cn.sharesdk.framework.authorize;

import android.util.Base64;
import cn.sharesdk.framework.a.b;
import cn.sharesdk.framework.b.a.e;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: SdkPlusTags */
public class f {
    private static volatile f a = null;
    private MobCommunicator b;
    private String c = MobSDK.getAppkey();
    private String d = this.e.getDeviceKey();
    private DeviceHelper e = DeviceHelper.getInstance(MobSDK.getContext());
    private b f = b.a();
    private boolean g = false;
    private HashMap<String, Object> h;

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public HashMap<String, Object> b() {
        return this.h;
    }

    private synchronized MobCommunicator e() {
        if (this.b == null) {
            this.b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
        }
        return this.b;
    }

    public static f c() {
        synchronized (f.class) {
            if (a == null) {
                synchronized (f.class) {
                    if (a == null) {
                        a = new f();
                    }
                }
            }
        }
        return a;
    }

    public void d() throws Throwable {
        e a2 = e.a();
        if (this.g && a2.g()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("Content-type", "application/json"));
            arrayList.add(new KVPair("sign", a(this.c, this.d)));
            HashMap hashMap = new HashMap();
            hashMap.put("appkey", this.c);
            hashMap.put(Constants.FLAG_DEVICE_ID, this.d);
            hashMap.put("duid", DeviceAuthorizer.authorize(new SHARESDK()));
            try {
                this.h = (HashMap) e().requestSynchronized(hashMap, "http://p.share.mob.com/tags/getTagList", false);
            } catch (Exception e2) {
            }
        }
    }

    private String a(String str, String str2) throws Throwable {
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.e.getDeviceKey(), MobSDK.getAppkey()})), str + str2), 2);
    }
}
