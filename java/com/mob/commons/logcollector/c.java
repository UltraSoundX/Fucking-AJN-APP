package com.mob.commons.logcollector;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.LockAction;
import com.mob.commons.a;
import com.mob.commons.d;
import com.mob.commons.h;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.mid.api.MidEntity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;

/* compiled from: LogsManager */
public class c {
    private static c b;
    private static String c = h.a("api.exc.mob.com");
    protected final Handler a = MobHandlerThread.newHandler("l", (Callback) new Callback() {
        public boolean handleMessage(Message message) {
            if (!a.ac()) {
                c.this.a(message);
            }
            return false;
        }
    });
    /* access modifiers changed from: private */
    public HashMap<String, Integer> d = new HashMap<>();
    private NetworkHelper e = new NetworkHelper();
    /* access modifiers changed from: private */
    public d f;
    private File g;

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    private synchronized void b() {
        if (this.f == null) {
            this.f = d.a();
        }
    }

    public void a(int i, String str) {
        Message message = new Message();
        message.what = 100;
        message.arg1 = i;
        message.obj = str;
        this.a.sendMessage(message);
    }

    public void a(int i, int i2, String str, String str2) {
        Message message = new Message();
        message.what = 101;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = new Object[]{str, str2};
        this.a.sendMessage(message);
    }

    /* access modifiers changed from: private */
    public void b(Message message) {
        this.a.sendMessageDelayed(message, 1000);
    }

    public void b(int i, int i2, String str, String str2) {
        a(i, i2, str, str2);
        try {
            this.a.wait();
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 100:
                c(message);
                return;
            case 101:
                d(message);
                return;
            default:
                return;
        }
    }

    private void c(Message message) {
        try {
            int i = message.arg1;
            String str = (String) message.obj;
            b(i, str);
            a(i, str, (String[]) null);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private void c() {
        if (this.g == null) {
            this.g = new File(MobSDK.getContext().getFilesDir(), ".lock");
        }
        if (!this.g.exists()) {
            try {
                this.g.createNewFile();
            } catch (Exception e2) {
                MobLog.getInstance().w((Throwable) e2);
            }
        }
    }

    private void d(Message message) {
        try {
            int i = message.arg1;
            Object[] objArr = (Object[]) message.obj;
            String str = (String) objArr[0];
            final String str2 = (String) objArr[1];
            final int i2 = 1;
            if (message.arg2 == 0) {
                i2 = 2;
            } else if (message.arg2 == 2) {
                i2 = 1;
            }
            b();
            String g2 = this.f.g();
            if (!TextUtils.isEmpty(g2)) {
                ArrayList arrayList = (ArrayList) new Hashon().fromJson(g2).get("fakelist");
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        if (!TextUtils.isEmpty(str3) && str2.contains(str3)) {
                            return;
                        }
                    }
                }
            }
            int d2 = this.f.d();
            int e2 = this.f.e();
            int f2 = this.f.f();
            if (3 != i2 || -1 != f2) {
                if (1 != i2 || -1 != d2) {
                    if (2 != i2 || -1 != e2) {
                        final String MD5 = Data.MD5(str2);
                        c();
                        final Message message2 = message;
                        if (d.a(this.g, new LockAction() {
                            public boolean run(FileLocker fileLocker) {
                                int i;
                                try {
                                    f.a(System.currentTimeMillis() - c.this.f.b(), str2, i2, MD5);
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    if (c.this.d.containsKey(MD5)) {
                                        i = ((Integer) c.this.d.get(MD5)).intValue();
                                    } else {
                                        i = 0;
                                    }
                                    int i2 = i + 1;
                                    c.this.d.put(MD5, Integer.valueOf(i2));
                                    if (i2 < 3) {
                                        c.this.b(message2);
                                    } else {
                                        c.this.d.remove(MD5);
                                        MobLog.getInstance().w(th2);
                                    }
                                }
                                return false;
                            }
                        })) {
                            this.d.remove(MD5);
                            if (3 == i2 && 1 == f2) {
                                a(i, str, new String[]{String.valueOf(3)});
                            } else if (1 == i2 && 1 == d2) {
                                a(i, str, new String[]{String.valueOf(1)});
                            } else if (2 == i2 && 1 == e2) {
                                a(i, str, new String[]{String.valueOf(2)});
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
    }

    private String d() {
        return c + "/errconf";
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r6 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.Context r0 = com.mob.MobSDK.getContext()
            com.mob.tools.utils.DeviceHelper r0 = com.mob.tools.utils.DeviceHelper.getInstance(r0)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "key"
            java.lang.String r4 = com.mob.MobSDK.getAppkey()
            r1.<init>(r3, r4)
            r2.add(r1)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "sdk"
            r1.<init>(r3, r8)
            r2.add(r1)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "apppkg"
            java.lang.String r4 = r0.getPackageName()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r1.<init>(r3, r4)
            r2.add(r1)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "appver"
            int r4 = r0.getAppVersion()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r1.<init>(r3, r4)
            r2.add(r1)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "sdkver"
            java.lang.String r4 = java.lang.String.valueOf(r7)
            r1.<init>(r3, r4)
            r2.add(r1)
            com.mob.tools.network.KVPair r1 = new com.mob.tools.network.KVPair
            java.lang.String r3 = "plat"
            int r0 = r0.getPlatformCode()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r3, r0)
            r2.add(r1)
            com.mob.tools.network.NetworkHelper$NetworkTimeOut r5 = new com.mob.tools.network.NetworkHelper$NetworkTimeOut     // Catch:{ Throwable -> 0x01cd }
            r5.<init>()     // Catch:{ Throwable -> 0x01cd }
            r0 = 10000(0x2710, float:1.4013E-41)
            r5.readTimout = r0     // Catch:{ Throwable -> 0x01cd }
            r0 = 10000(0x2710, float:1.4013E-41)
            r5.connectionTimeout = r0     // Catch:{ Throwable -> 0x01cd }
            com.mob.tools.network.NetworkHelper r0 = r6.e     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r6.d()     // Catch:{ Throwable -> 0x01cd }
            r3 = 0
            r4 = 0
            java.lang.String r0 = r0.httpPost(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x01cd }
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = "get server config == %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01cd }
            r4 = 0
            r3[r4] = r0     // Catch:{ Throwable -> 0x01cd }
            r1.i(r2, r3)     // Catch:{ Throwable -> 0x01cd }
            com.mob.tools.utils.Hashon r1 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x01cd }
            r1.<init>()     // Catch:{ Throwable -> 0x01cd }
            java.util.HashMap r0 = r1.fromJson(r0)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = "-200"
            java.lang.String r2 = "status"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x01cd }
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x00b9
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = "error log server config response fail !!"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x01cd }
            r0.i(r1, r2)     // Catch:{ Throwable -> 0x01cd }
        L_0x00b8:
            return
        L_0x00b9:
            java.lang.String r1 = "result"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r0 == 0) goto L_0x00b8
            boolean r1 = r0 instanceof java.util.HashMap     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x00b8
            r6.b()     // Catch:{ Throwable -> 0x01cd }
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = "timestamp"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x00eb
            java.lang.String r1 = "timestamp"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x01d7 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x01d7 }
            long r2 = com.mob.tools.utils.ResHelper.parseLong(r1)     // Catch:{ Throwable -> 0x01d7 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x01d7 }
            long r2 = r4 - r2
            com.mob.commons.logcollector.d r1 = r6.f     // Catch:{ Throwable -> 0x01d7 }
            r1.a(r2)     // Catch:{ Throwable -> 0x01d7 }
        L_0x00eb:
            java.lang.String r1 = "1"
            java.lang.String r2 = "enable"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x01cd }
            boolean r1 = r1.equals(r2)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x01e1
            com.mob.commons.logcollector.d r1 = r6.f     // Catch:{ Throwable -> 0x01cd }
            r2 = 1
            r1.a(r2)     // Catch:{ Throwable -> 0x01cd }
        L_0x0103:
            java.lang.String r1 = "upconf"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x014a
            boolean r2 = r1 instanceof java.util.HashMap     // Catch:{ Throwable -> 0x01cd }
            if (r2 == 0) goto L_0x014a
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = "crash"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r3 = "sdkerr"
            java.lang.Object r3 = r1.get(r3)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r4 = "apperr"
            java.lang.Object r1 = r1.get(r4)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x01cd }
            com.mob.commons.logcollector.d r4 = r6.f     // Catch:{ Throwable -> 0x01cd }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x01cd }
            r4.a(r2)     // Catch:{ Throwable -> 0x01cd }
            com.mob.commons.logcollector.d r2 = r6.f     // Catch:{ Throwable -> 0x01cd }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Throwable -> 0x01cd }
            r2.b(r3)     // Catch:{ Throwable -> 0x01cd }
            com.mob.commons.logcollector.d r2 = r6.f     // Catch:{ Throwable -> 0x01cd }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x01cd }
            r2.c(r1)     // Catch:{ Throwable -> 0x01cd }
        L_0x014a:
            java.lang.String r1 = "requesthost"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x019f
            java.lang.String r1 = "requestport"
            boolean r1 = r0.containsKey(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x019f
            java.lang.String r1 = "requesthost"
            java.lang.Object r1 = r0.get(r1)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = "requestport"
            java.lang.Object r2 = r0.get(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Throwable -> 0x01cd }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r3 != 0) goto L_0x019f
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x01cd }
            if (r3 != 0) goto L_0x019f
            java.lang.String r3 = "80"
            boolean r3 = r2.equals(r3)     // Catch:{ Throwable -> 0x01cd }
            if (r3 == 0) goto L_0x01e9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01cd }
            r2.<init>()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r3 = "http://"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x01cd }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = com.mob.commons.h.a(r1)     // Catch:{ Throwable -> 0x01cd }
            c = r1     // Catch:{ Throwable -> 0x01cd }
        L_0x019f:
            java.lang.String r1 = "filter"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Throwable -> 0x01cd }
            if (r0 == 0) goto L_0x00b8
            boolean r1 = r0 instanceof java.util.ArrayList     // Catch:{ Throwable -> 0x01cd }
            if (r1 == 0) goto L_0x00b8
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Throwable -> 0x01cd }
            int r1 = r0.size()     // Catch:{ Throwable -> 0x01cd }
            if (r1 <= 0) goto L_0x00b8
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x01cd }
            r1.<init>()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = "fakelist"
            r1.put(r2, r0)     // Catch:{ Throwable -> 0x01cd }
            com.mob.commons.logcollector.d r0 = r6.f     // Catch:{ Throwable -> 0x01cd }
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x01cd }
            r2.<init>()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r2.fromHashMap(r1)     // Catch:{ Throwable -> 0x01cd }
            r0.a(r1)     // Catch:{ Throwable -> 0x01cd }
            goto L_0x00b8
        L_0x01cd:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.d(r0)
            goto L_0x00b8
        L_0x01d7:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ Throwable -> 0x01cd }
            r2.i(r1)     // Catch:{ Throwable -> 0x01cd }
            goto L_0x00eb
        L_0x01e1:
            com.mob.commons.logcollector.d r1 = r6.f     // Catch:{ Throwable -> 0x01cd }
            r2 = 0
            r1.a(r2)     // Catch:{ Throwable -> 0x01cd }
            goto L_0x0103
        L_0x01e9:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01cd }
            r3.<init>()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r4 = "http://"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x01cd }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r3 = ":"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r2 = r2.trim()     // Catch:{ Throwable -> 0x01cd }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x01cd }
            java.lang.String r1 = com.mob.commons.h.a(r1)     // Catch:{ Throwable -> 0x01cd }
            c = r1     // Catch:{ Throwable -> 0x01cd }
            goto L_0x019f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.logcollector.c.b(int, java.lang.String):void");
    }

    private String e() {
        return c + "/errlog";
    }

    private void a(final int i, final String str, final String[] strArr) {
        try {
            b();
            if (this.f.c()) {
                if ("none".equals(DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic())) {
                    throw new IllegalStateException("network is disconnected!");
                }
                c();
                d.a(this.g, new LockAction() {
                    public boolean run(FileLocker fileLocker) {
                        try {
                            ArrayList a2 = f.a(strArr);
                            for (int i = 0; i < a2.size(); i++) {
                                e eVar = (e) a2.get(i);
                                HashMap a3 = c.this.c(i, str);
                                a3.put("errmsg", eVar.a);
                                if (c.this.a(c.this.a(new Hashon().fromHashMap(a3)), true)) {
                                    f.a(eVar.b);
                                }
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().i(th);
                        }
                        return false;
                    }
                });
            }
        } catch (Throwable th) {
            MobLog.getInstance().i(th);
        }
    }

    /* access modifiers changed from: private */
    public HashMap<String, Object> c(int i, String str) throws Throwable {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        hashMap.put(SettingsContentProvider.KEY, MobSDK.getAppkey());
        hashMap.put("plat", Integer.valueOf(instance.getPlatformCode()));
        hashMap.put("sdk", str);
        hashMap.put("sdkver", Integer.valueOf(i));
        hashMap.put("appname", instance.getAppName());
        hashMap.put("apppkg", instance.getPackageName());
        hashMap.put("appver", String.valueOf(instance.getAppVersion()));
        hashMap.put("deviceid", instance.getDeviceKey());
        hashMap.put("model", instance.getModel());
        hashMap.put(MidEntity.TAG_MAC, instance.getMacAddress());
        hashMap.put("udid", instance.getDeviceId());
        hashMap.put("sysver", String.valueOf(instance.getOSVersionInt()));
        hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
        return hashMap;
    }

    /* access modifiers changed from: private */
    public String a(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                try {
                    break;
                } catch (Throwable th) {
                }
            }
        }
        gZIPOutputStream.flush();
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return Base64.encodeToString(byteArray, 2);
    }

    /* access modifiers changed from: private */
    public boolean a(String str, boolean z) throws Throwable {
        if (a.I()) {
            return false;
        }
        try {
            if ("none".equals(DeviceHelper.getInstance(MobSDK.getContext()).getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair(Config.MODEL, str));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            this.e.httpPost(e(), arrayList, null, null, networkTimeOut);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().i(th);
            return false;
        }
    }
}
