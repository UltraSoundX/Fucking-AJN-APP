package com.mob.commons;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.a.d;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SQLiteHelper;
import com.mob.tools.utils.SQLiteHelper.SingleTableDB;
import com.tencent.mid.api.MidEntity;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

/* compiled from: DataHeap */
public class b implements Callback {
    private static final String a = h.a("l.gm.mob.com/v5/gcl");
    private static b b;
    private Handler c = MobHandlerThread.newHandler("d", (Callback) this);
    private SingleTableDB d;
    /* access modifiers changed from: private */
    public Hashon e = new Hashon();
    private Random f = new Random();
    /* access modifiers changed from: private */
    public boolean g = true;

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    private b() {
        b();
        c();
    }

    /* access modifiers changed from: private */
    public synchronized SingleTableDB b() {
        if (this.d == null) {
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dh");
            if (cacheRootFile != null) {
                if (cacheRootFile.length() > 209715200) {
                    cacheRootFile.delete();
                    cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dh");
                }
                this.d = SQLiteHelper.getDatabase(cacheRootFile.getAbsolutePath(), "DataHeap_1");
                this.d.addField("time", "text", true);
                this.d.addField("data", "text", true);
            }
        }
        return this.d;
    }

    public synchronized void a(long j, HashMap<String, Object> hashMap) {
        if (!a.ad()) {
            Message message = new Message();
            message.what = 2;
            message.obj = new Object[]{Long.valueOf(j), hashMap};
            if (hashMap != null) {
                MobLog.getInstance().d("type: " + hashMap.get("type"), new Object[0]);
            }
            this.c.sendMessage(message);
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.c.removeMessages(1);
                if (!d()) {
                    c();
                    break;
                }
                break;
            case 2:
                Object[] objArr = (Object[]) message.obj;
                long longValue = ((Long) ResHelper.forceCast(objArr[0], Long.valueOf(-1))).longValue();
                if (longValue > 0) {
                    b(longValue, (HashMap) objArr[1]);
                    c();
                    break;
                }
                break;
        }
        return false;
    }

    private void c() {
        if (!a.ad()) {
            long K = a.K();
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            if (instance != null) {
                String networkType = instance.getNetworkType();
                if (networkType == null || "none".equals(networkType)) {
                    K = 600000;
                }
            }
            this.c.sendEmptyMessageDelayed(1, K);
        }
    }

    public void a(Object... objArr) {
        try {
            c.a().a(13);
            try {
                ResHelper.deleteFileAndFolder(b(objArr));
            } catch (Throwable th) {
                c.a().a(4, th);
            }
        } catch (Throwable th2) {
            c.a().a(4, th2);
        }
    }

    private File b(Object... objArr) throws Throwable {
        int i;
        InputStream inputStream;
        String str = objArr[0];
        String str2 = objArr[1];
        String str3 = objArr[4];
        String str4 = objArr[5];
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file = new File(MobSDK.getContext().getFilesDir(), i.a(5));
            byte[] bArr = objArr[2];
            try {
                i = Integer.parseInt(String.valueOf(objArr[3]));
            } catch (Throwable th) {
                i = 0;
            }
            if (bArr == null || i <= 0 || bArr.length < i || !str.equals(Data.MD5(bArr, 0, i))) {
                File file2 = new File(file, i.a(14));
                if (!file2.exists() || !str.equals(Data.MD5(file2))) {
                    c.a().a(20);
                    file2.delete();
                    inputStream = null;
                } else {
                    inputStream = new FileInputStream(file2);
                }
            } else {
                inputStream = new ByteArrayInputStream(bArr, 0, i);
            }
            if (inputStream != null) {
                File file3 = new File(file, String.valueOf(System.currentTimeMillis()));
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                File file4 = new File(file3, file3.getName() + ".zip");
                FileOutputStream fileOutputStream = new FileOutputStream(file4);
                Data.AES128Decode(str2, inputStream, (OutputStream) fileOutputStream);
                inputStream.close();
                fileOutputStream.close();
                try {
                    DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                    if (!instance.checkADBModel(17) || !instance.checkUA()) {
                        c.a().a(14);
                        d.a(str, file4, str3, str4);
                    } else {
                        c.a().a(19);
                    }
                    try {
                        ResHelper.deleteFileAndFolder(file3);
                        return file3;
                    } catch (Throwable th2) {
                        c.a().a(4, th2);
                        return file3;
                    }
                } catch (Throwable th3) {
                    c.a().a(4, th3);
                    return file3;
                }
            }
        }
        return null;
    }

    private void b(final long j, final HashMap<String, Object> hashMap) {
        Object obj;
        if (!d.a(d.a("comm/locks/.dhlock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", String.valueOf(j));
                    DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
                    if (hashMap != null) {
                        hashMap.put("appkey", MobSDK.getAppkey());
                        hashMap.put("apppkg", instance.getPackageName());
                        hashMap.put("appver", instance.getAppVersionName());
                    }
                    contentValues.put("data", Base64.encodeToString(Data.AES128Encode(Data.rawMD5(instance.getManufacturer()), b.this.e.fromHashMap(hashMap).getBytes("utf-8")), 2));
                    SQLiteHelper.insert(b.this.b(), contentValues);
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
                return false;
            }
        })) {
            NLog instance = MobLog.getInstance();
            StringBuilder append = new StringBuilder().append("DataHeap add log error data type = ");
            if (hashMap == null) {
                obj = null;
            } else {
                obj = hashMap.get("type");
            }
            instance.e(new Throwable(append.append(obj).append(", updateTime = ").append(j).toString()));
        }
    }

    private boolean d() {
        if (a.I()) {
            return true;
        }
        DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
        if (instance == null) {
            return false;
        }
        String networkType = instance.getNetworkType();
        if (networkType == null || "none".equals(networkType)) {
            return false;
        }
        this.g = true;
        boolean a2 = d.a(d.a("comm/locks/.dhlock"), new LockAction() {
            public boolean run(FileLocker fileLocker) {
                String[][] strArr = new String[50][];
                int a2 = b.this.a(strArr);
                while (true) {
                    if (a2 <= 0) {
                        break;
                    }
                    SparseArray a3 = b.this.a(strArr, a2);
                    if (a3 != null) {
                        if (a3.size() > 0) {
                            b.this.a(a3);
                        }
                        if (a2 < strArr.length) {
                            break;
                        }
                        a2 = b.this.a(strArr);
                    } else {
                        b.this.g = false;
                        break;
                    }
                }
                return false;
            }
        });
        if (!this.g || !a2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public int a(String[][] strArr) {
        int i = 0;
        try {
            Cursor query = SQLiteHelper.query(b(), new String[]{"time", "data"}, null, null, null);
            if (query != null) {
                if (!query.moveToFirst()) {
                    query.close();
                } else {
                    long a2 = a.a();
                    while (true) {
                        int i2 = i;
                        try {
                            String[] strArr2 = {query.getString(0), query.getString(1)};
                            long j = -1;
                            try {
                                j = Long.parseLong(strArr2[0]);
                            } catch (Throwable th) {
                            }
                            if (j <= a2) {
                                strArr[i2] = strArr2;
                                i = i2 + 1;
                            } else {
                                i = i2;
                            }
                            if (i >= strArr.length || !query.moveToNext()) {
                                query.close();
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            i = i2;
                            th = th3;
                            MobLog.getInstance().w(th);
                            return i;
                        }
                    }
                    query.close();
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public SparseArray<String> a(String[][] strArr, int i) {
        try {
            SparseArray sparseArray = new SparseArray();
            HashMap hashMap = new HashMap();
            DeviceHelper instance = DeviceHelper.getInstance(MobSDK.getContext());
            hashMap.put("plat", Integer.valueOf(instance.getPlatformCode()));
            hashMap.put(Config.DEVICE_PART, instance.getDeviceKey());
            hashMap.put(MidEntity.TAG_MAC, instance.getMacAddress());
            hashMap.put("model", instance.getModel());
            hashMap.put("duid", DeviceAuthorizer.authorize(null));
            hashMap.put(MidEntity.TAG_IMEI, instance.getIMEI());
            hashMap.put("serialno", instance.getSerialno());
            hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
            ArrayList arrayList = new ArrayList();
            byte[] rawMD5 = Data.rawMD5(instance.getManufacturer());
            int i2 = 0;
            while (i2 < i) {
                String[] strArr2 = strArr[i2];
                HashMap fromJson = this.e.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(strArr2[1], 2)), "utf-8").trim());
                if (fromJson == null || fromJson.isEmpty() || a((String) ResHelper.forceCast(fromJson.get("type"), null))) {
                    sparseArray.put(i2, strArr2[0]);
                    arrayList.add(fromJson);
                    i2++;
                } else {
                    i2++;
                }
            }
            if (arrayList.isEmpty()) {
                return new SparseArray<>();
            }
            hashMap.put("datas", arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("appkey", MobSDK.getAppkey()));
            arrayList2.add(new KVPair(Config.MODEL, b(this.e.fromHashMap(hashMap))));
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = Config.SESSION_PERIOD;
            networkTimeOut.connectionTimeout = Config.SESSION_PERIOD;
            if (!"200".equals(String.valueOf(this.e.fromJson(new NetworkHelper().httpPost(e(), arrayList2, null, arrayList3, networkTimeOut)).get(NotificationCompat.CATEGORY_STATUS)))) {
                g.e((String) null);
            }
            return sparseArray;
        } catch (Throwable th) {
            g.e((String) null);
            MobLog.getInstance().w(th);
            return null;
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (i.a(19).equals(str)) {
            return a.f();
        }
        if (i.a(20).equals(str)) {
            return a.e();
        }
        if (i.a(21).equals(str)) {
            return a.g();
        }
        if (i.a(22).equals(str)) {
            return a.c();
        }
        if (i.a(23).equals(str)) {
            return a.j();
        }
        if (i.a(24).equals(str)) {
            return a.k();
        }
        if (i.a(25).equals(str)) {
            return a.m();
        }
        if (i.a(26).equals(str)) {
            return a.x();
        }
        if (i.a(27).equals(str)) {
            return a.o();
        }
        if (i.a(28).equals(str)) {
            return a.q();
        }
        if (i.a(29).equals(str)) {
            return a.w();
        }
        if (i.a(30).equals(str)) {
            if (a.A() <= 0) {
                return false;
            }
            return true;
        } else if (i.a(31).equals(str)) {
            return a.B();
        } else {
            if (i.a(32).equals(str)) {
                if (a.F() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(33).equals(str)) {
                if (a.H() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(34).equals(str)) {
                if (a.M() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(35).equals(str)) {
                if (a.O() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(36).equals(str)) {
                if (a.Q() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(37).equals(str)) {
                if (a.S() <= 0) {
                    return false;
                }
                return true;
            } else if (i.a(38).equals(str)) {
                return a.U();
            } else {
                if (i.a(39).equals(str)) {
                    if (a.W() <= 0) {
                        return false;
                    }
                    return true;
                } else if (!i.a(40).equals(str) || a.Y() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private String b(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(this.f.nextLong());
        dataOutputStream.writeLong(this.f.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream2));
        bufferedOutputStream.write(str.getBytes("utf-8"));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        byte[] AES128Encode = Data.AES128Encode(byteArray, byteArrayOutputStream2.toByteArray());
        byte[] encode = new MobRSA(1024).encode(byteArray, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream3);
        dataOutputStream2.writeInt(encode.length);
        dataOutputStream2.write(encode);
        dataOutputStream2.writeInt(AES128Encode.length);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream3.toByteArray(), 2);
    }

    /* access modifiers changed from: private */
    public int a(SparseArray<String> sparseArray) {
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append('\'').append((String) sparseArray.valueAt(i)).append('\'');
            }
            return SQLiteHelper.delete(b(), "time in (" + sb.toString() + ")", null);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String e() {
        /*
            r1 = 0
            java.lang.String r1 = com.mob.commons.g.g()     // Catch:{ Throwable -> 0x0012 }
            java.lang.String r0 = com.mob.commons.h.a(r1)     // Catch:{ Throwable -> 0x001e }
        L_0x0009:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0011
            java.lang.String r0 = a
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
        L_0x0016:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.w(r1)
            goto L_0x0009
        L_0x001e:
            r0 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.b.e():java.lang.String");
    }
}
