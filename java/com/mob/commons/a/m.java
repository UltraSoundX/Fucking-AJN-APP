package com.mob.commons.a;

import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: PkgSClt */
public class m extends d {
    private Hashon a = new Hashon();
    private DeviceHelper b;

    m() {
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.pkgs_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.Q() > 0;
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(1);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                long Q = a.Q();
                if (Q > 0) {
                    i();
                    a(1, Q * 1000);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void i() {
        ArrayList arrayList;
        boolean z;
        boolean z2;
        boolean z3;
        ArrayList j = j();
        try {
            if (this.b == null) {
                this.b = DeviceHelper.getInstance(MobSDK.getContext());
            }
            arrayList = this.b.getSA();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            arrayList = new ArrayList();
        }
        if (!arrayList.isEmpty()) {
            boolean z4 = j == null || j.isEmpty();
            if (!z4) {
                long k = k();
                if (k == 0 || a.a() >= k) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z4) {
                    if (arrayList.size() != j.size()) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str = (String) ((HashMap) it.next()).get(Config.INPUT_DEF_PKG);
                            if (!TextUtils.isEmpty(str)) {
                                Iterator it2 = j.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        if (str.equals(((HashMap) it2.next()).get(Config.INPUT_DEF_PKG))) {
                                            z2 = true;
                                            break;
                                        }
                                    } else {
                                        z2 = false;
                                        break;
                                    }
                                }
                                if (!z2) {
                                    z4 = true;
                                    break;
                                }
                            }
                        }
                    }
                    z4 = z;
                }
            }
            if (z4) {
                a("SYSTEM_APPS", arrayList);
                a(arrayList);
                a(a.a() + (a.R() * 1000));
            }
        }
    }

    private ArrayList<HashMap<String, String>> j() {
        BufferedReader bufferedReader;
        ArrayList<HashMap<String, String>> arrayList;
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.sal");
        if (cacheRootFile != null && cacheRootFile.exists()) {
            BufferedReader bufferedReader2 = null;
            try {
                arrayList = new ArrayList<>();
                bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(cacheRootFile)), "utf-8"));
            } catch (Throwable th) {
                th = th;
                a((Closeable) bufferedReader2);
                throw th;
            }
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    HashMap fromJson = this.a.fromJson(readLine);
                    if (fromJson != null) {
                        arrayList.add(fromJson);
                    }
                }
                a((Closeable) bufferedReader);
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().d(th);
                a((Closeable) bufferedReader);
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

    private void a(String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        long a2 = a.a();
        hashMap.put("datetime", Long.valueOf(a2));
        b.a().a(a2, hashMap);
    }

    private void a(ArrayList<HashMap<String, String>> arrayList) {
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.sal"))), "utf-8");
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    outputStreamWriter.append(this.a.fromHashMap((HashMap) it.next())).append(10);
                }
                a((Closeable) outputStreamWriter);
            } catch (Throwable th) {
                th = th;
                try {
                    MobLog.getInstance().d(th);
                    a((Closeable) outputStreamWriter);
                } catch (Throwable th2) {
                    th = th2;
                    a((Closeable) outputStreamWriter);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            outputStreamWriter = null;
            a((Closeable) outputStreamWriter);
            throw th;
        }
    }

    private void a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.snulal")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private long k() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.snulal");
        if (cacheRootFile.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(cacheRootFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
        return 0;
    }
}
