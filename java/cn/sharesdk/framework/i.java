package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.loopshare.LoopShareResultListener;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.d;
import com.mob.MobSDK;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: ShareSDKCoreThread */
public class i extends d {
    private a b;
    private HashMap<String, HashMap<String, String>> c = new HashMap<>();
    private ArrayList<Platform> d = new ArrayList<>();
    private HashMap<String, Integer> e = new HashMap<>();
    private HashMap<Integer, String> f = new HashMap<>();
    private HashMap<Integer, CustomPlatform> g = new HashMap<>();
    private HashMap<Integer, HashMap<String, Object>> h = new HashMap<>();
    private HashMap<Integer, Service> i = new HashMap<>();
    private boolean j = true;
    private boolean k;

    /* compiled from: ShareSDKCoreThread */
    private enum a {
        INITIALIZING,
        READY
    }

    public void a(Activity activity) {
        h.a(activity);
    }

    public Activity a() {
        return h.b();
    }

    public void a(boolean z) {
        h.a(z);
    }

    public boolean b() {
        return h.c();
    }

    public Bitmap a(String str, int i2, int i3) {
        return h.a(str, i2, i3);
    }

    public void a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        try {
            h.a(hashMap, moblinkActionListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDKCoreThread mobLinkGetMobID " + th, new Object[0]);
        }
    }

    public void a(LoopShareResultListener loopShareResultListener) {
        try {
            h.a(loopShareResultListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDKCoreThrad prepareLoopShare " + th, new Object[0]);
        }
    }

    public HashMap<String, Object> c() {
        return h.d();
    }

    public void d() {
        this.b = a.INITIALIZING;
        SSDKLog.a();
        EventRecorder.prepare();
        j();
        super.d();
    }

    private void j() {
        XmlPullParser newPullParser;
        InputStream inputStream;
        synchronized (this.c) {
            this.c.clear();
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                newPullParser = newInstance.newPullParser();
                inputStream = null;
                inputStream = MobSDK.getContext().getAssets().open("ShareSDK.xml");
            } catch (Throwable th) {
                SSDKLog.b().d(th);
            }
            newPullParser.setInput(inputStream, "utf-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    String name = newPullParser.getName();
                    HashMap hashMap = new HashMap();
                    int attributeCount = newPullParser.getAttributeCount();
                    for (int i2 = 0; i2 < attributeCount; i2++) {
                        hashMap.put(newPullParser.getAttributeName(i2), newPullParser.getAttributeValue(i2).trim());
                    }
                    this.c.put(name, hashMap);
                }
            }
            inputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        synchronized (this.i) {
            synchronized (this.d) {
                try {
                    String checkRecord = EventRecorder.checkRecord(ShareSDK.SDK_TAG);
                    if (!TextUtils.isEmpty(checkRecord)) {
                        cn.sharesdk.framework.b.a.a().a(null);
                        SSDKLog.b().w("EventRecorder checkRecord result ==" + checkRecord);
                        i();
                    }
                    EventRecorder.clear();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
                try {
                    this.d.clear();
                    ArrayList a2 = h.a();
                    if (a2 != null) {
                        this.d.addAll(a2);
                    }
                    Iterator it = this.d.iterator();
                    while (it.hasNext()) {
                        Platform platform = (Platform) it.next();
                        this.f.put(Integer.valueOf(platform.getPlatformId()), platform.getName());
                        this.e.put(platform.getName(), Integer.valueOf(platform.getPlatformId()));
                    }
                    h.a(this.a);
                    this.b = a.READY;
                    new Thread() {
                        public void run() {
                            i.this.h();
                        }
                    }.start();
                    this.b = a.READY;
                    this.d.notify();
                    this.i.notify();
                } catch (Throwable th2) {
                    this.b = a.READY;
                    this.d.notify();
                    this.i.notify();
                    throw th2;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(Message message) {
    }

    public void a(Class<? extends Service> cls) {
        synchronized (this.i) {
            if (!this.i.containsKey(Integer.valueOf(cls.hashCode()))) {
                try {
                    Service service = (Service) cls.newInstance();
                    this.i.put(Integer.valueOf(cls.hashCode()), service);
                    service.onBind();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
                return;
            }
            return;
        }
    }

    public void b(Class<? extends Service> cls) {
        synchronized (this.i) {
            int hashCode = cls.hashCode();
            if (this.i.containsKey(Integer.valueOf(hashCode))) {
                ((Service) this.i.get(Integer.valueOf(hashCode))).onUnbind();
                this.i.remove(Integer.valueOf(hashCode));
            }
        }
    }

    public <T extends Service> T c(Class<T> cls) {
        T t;
        synchronized (this.i) {
            if (this.b == a.INITIALIZING) {
                try {
                    this.i.wait();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
            try {
                t = (Service) cls.cast(this.i.get(Integer.valueOf(cls.hashCode())));
            } catch (Throwable th2) {
                SSDKLog.b().w(th2);
                t = null;
            }
        }
        return t;
    }

    public void d(Class<? extends CustomPlatform> cls) {
        synchronized (this.g) {
            if (!this.g.containsKey(Integer.valueOf(cls.hashCode()))) {
                try {
                    CustomPlatform customPlatform = (CustomPlatform) cls.newInstance();
                    this.g.put(Integer.valueOf(cls.hashCode()), customPlatform);
                    if (customPlatform != null && customPlatform.b()) {
                        this.f.put(Integer.valueOf(customPlatform.getPlatformId()), customPlatform.getName());
                        this.e.put(customPlatform.getName(), Integer.valueOf(customPlatform.getPlatformId()));
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
                return;
            }
            return;
        }
    }

    public void e(Class<? extends CustomPlatform> cls) {
        int hashCode = cls.hashCode();
        synchronized (this.g) {
            this.g.remove(Integer.valueOf(hashCode));
        }
    }

    public Platform a(String str) {
        if (str == null) {
            return null;
        }
        Platform[] e2 = e();
        if (e2 == null) {
            return null;
        }
        for (Platform platform : e2) {
            if (str.equals(platform.getName())) {
                return platform;
            }
        }
        return null;
    }

    public Platform[] e() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.d) {
            if (this.b == a.INITIALIZING) {
                try {
                    this.d.wait();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            Platform platform = (Platform) it.next();
            if (platform != null && platform.b()) {
                platform.a();
                arrayList.add(platform);
            }
        }
        h.a(arrayList);
        for (Entry value : this.g.entrySet()) {
            Platform platform2 = (Platform) value.getValue();
            if (platform2 != null && platform2.b()) {
                arrayList.add(platform2);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        Platform[] platformArr = new Platform[arrayList.size()];
        for (int i2 = 0; i2 < platformArr.length; i2++) {
            platformArr[i2] = (Platform) arrayList.get(i2);
        }
        SSDKLog.b().i("sort list use time: %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return platformArr;
    }

    public void a(int i2) {
        NetworkHelper.connectionTimeout = i2;
    }

    public void b(int i2) {
        NetworkHelper.readTimout = i2;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public boolean f() {
        return this.k;
    }

    public void a(int i2, Platform platform) {
        h.a(i2, platform);
    }

    public void a(String str, int i2) {
        h.a(str, i2);
    }

    public void a(String str, HashMap<String, Object> hashMap) {
        HashMap hashMap2;
        synchronized (this.c) {
            HashMap hashMap3 = (HashMap) this.c.get(str);
            if (hashMap3 == null) {
                hashMap2 = new HashMap();
            } else {
                hashMap2 = hashMap3;
            }
            synchronized (hashMap2) {
                for (Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        hashMap2.put(str2, String.valueOf(value));
                    }
                }
            }
            this.c.put(str, hashMap2);
        }
        synchronized (this.d) {
            if (this.b == a.INITIALIZING) {
                try {
                    this.d.wait();
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            Platform platform = (Platform) it.next();
            if (platform != null && platform.getName().equals(str)) {
                platform.a();
                return;
            }
        }
    }

    public String c(int i2) {
        String str;
        synchronized (this.d) {
            synchronized (this.g) {
                str = (String) this.f.get(Integer.valueOf(i2));
            }
        }
        return str;
    }

    public int b(String str) {
        int i2;
        synchronized (this.d) {
            synchronized (this.g) {
                if (this.e.containsKey(str)) {
                    i2 = ((Integer) this.e.get(str)).intValue();
                } else {
                    i2 = 0;
                }
            }
        }
        return i2;
    }

    public void a(String str, String str2) {
        synchronized (this.c) {
            this.c.put(str2, (HashMap) this.c.get(str));
        }
    }

    public void a(int i2, int i3) {
        synchronized (this.h) {
            this.h.put(Integer.valueOf(i3), (HashMap) this.h.get(Integer.valueOf(i2)));
        }
    }

    public String b(String str, String str2) {
        String str3;
        synchronized (this.c) {
            HashMap hashMap = (HashMap) this.c.get(str);
            if (hashMap == null) {
                str3 = null;
            } else {
                str3 = (String) hashMap.get(str2);
            }
        }
        return str3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(int r5, java.lang.String r6) {
        /*
            r4 = this;
            r1 = 0
            java.util.HashMap<java.lang.Integer, java.util.HashMap<java.lang.String, java.lang.Object>> r2 = r4.h
            monitor-enter(r2)
            java.util.HashMap<java.lang.Integer, java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r4.h     // Catch:{ all -> 0x001e }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x001e }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x001e }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ all -> 0x001e }
            if (r0 != 0) goto L_0x0015
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            r0 = r1
        L_0x0014:
            return r0
        L_0x0015:
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x001e }
            if (r0 != 0) goto L_0x0021
            r0 = r1
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            goto L_0x0014
        L_0x001e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            throw r0
        L_0x0021:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x001e }
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.i.a(int, java.lang.String):java.lang.String");
    }

    public boolean g() {
        boolean z;
        synchronized (this.h) {
            if (this.h == null || this.h.size() <= 0) {
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    public boolean h() {
        boolean z = false;
        if (a.READY != this.b) {
            SSDKLog.b().d("Statistics module unopened", new Object[0]);
        } else {
            final cn.sharesdk.framework.b.a a2 = cn.sharesdk.framework.b.a.a();
            HashMap a3 = a(a2, a2.e());
            if (a3 != null && a3.size() > 0) {
                z = a(a3);
            }
            if (z) {
                new Thread() {
                    public void run() {
                        try {
                            HashMap f = a2.f();
                            HashMap a2 = i.this.a(a2, f);
                            if (a2 != null && a2.size() > 0 && i.this.a(a2)) {
                                a2.a(f);
                            }
                        } catch (Throwable th) {
                            SSDKLog.b().w(th);
                        }
                    }
                }.start();
            } else {
                try {
                    HashMap f2 = a2.f();
                    HashMap a4 = a(a2, f2);
                    if (a4 != null && a4.size() > 0) {
                        z = a(a4);
                        if (z) {
                            a2.a(f2);
                        }
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public HashMap<String, Object> a(cn.sharesdk.framework.b.a aVar, HashMap<String, Object> hashMap) {
        try {
            if (hashMap.containsKey("error")) {
                SSDKLog.b().i("ShareSDK parse sns config ==>>", new Hashon().fromHashMap(hashMap));
                return null;
            } else if (!hashMap.containsKey("res")) {
                SSDKLog.b().d("ShareSDK platform config result ==>>", "SNS configuration is empty");
                return null;
            } else {
                String str = (String) hashMap.get("res");
                if (str == null) {
                    return null;
                }
                return aVar.c(str);
            }
        } catch (Throwable th) {
            SSDKLog.b().w(th);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean a(HashMap<String, Object> hashMap) {
        boolean z;
        synchronized (this.h) {
            HashMap<Integer, HashMap<String, Object>> a2 = h.a(hashMap);
            if (a2 == null || a2.size() <= 0) {
                z = false;
            } else {
                this.h.clear();
                this.h = a2;
                z = true;
            }
        }
        return z;
    }

    public String a(String str, boolean z, int i2, String str2) {
        return a.READY != this.b ? str : cn.sharesdk.framework.b.a.a().a(str, i2, z, str2);
    }

    public String c(String str) {
        if (a.READY != this.b) {
            return null;
        }
        return cn.sharesdk.framework.b.a.a().b(str);
    }

    public String a(Bitmap bitmap) {
        if (a.READY != this.b) {
            return null;
        }
        return cn.sharesdk.framework.b.a.a().a(bitmap);
    }

    public void i() {
        try {
            ResHelper.clearCache(MobSDK.getContext());
        } catch (Throwable th) {
            SSDKLog.b().w(th);
        }
    }
}
