package cn.sharesdk.sina.weibo;

import android.support.v4.view.PointerIconCompat;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import java.util.HashMap;

/* compiled from: SinaLinkCardLog */
public class c {
    private static volatile c a = null;
    private MobCommunicator b;
    /* access modifiers changed from: private */
    public String c = MobSDK.getAppkey();

    /* access modifiers changed from: private */
    public synchronized MobCommunicator b() {
        if (this.b == null) {
            this.b = new MobCommunicator(PointerIconCompat.TYPE_GRABBING, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
        }
        return this.b;
    }

    public static c a() {
        synchronized (c.class) {
            if (a == null) {
                synchronized (c.class) {
                    if (a == null) {
                        a = new c();
                    }
                }
            }
        }
        return a;
    }

    public void a(final int i) {
        new Thread() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("appkey", c.this.c);
                hashMap.put("plat", Integer.valueOf(1));
                hashMap.put("action", Integer.valueOf(i));
                hashMap.put("shareTime", Long.valueOf(c.this.c()));
                try {
                    HashMap hashMap2 = (HashMap) c.this.b().requestSynchronized(hashMap, "http://lks.share.mob.com/share/shareLog", false);
                } catch (Throwable th) {
                    SSDKLog.b().d("LinkCard log up error===> " + th.getMessage(), new Object[0]);
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public long c() {
        return System.currentTimeMillis();
    }
}
