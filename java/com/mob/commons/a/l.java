package com.mob.commons.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

/* compiled from: PkgClt */
public class l extends d {
    private static final String[] a = {"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"};
    private BroadcastReceiver b;
    private Hashon c;

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.pkg_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        if (a.f()) {
            i();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(2);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                n();
                return;
            case 2:
                l();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        m();
    }

    private void i() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList j = j();
        if (j == null || j.isEmpty()) {
            try {
                arrayList = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
                arrayList = new ArrayList();
            }
            a(a.u(), "APPS_ALL", arrayList);
            a(arrayList);
            a(a.a() + (a.h() * 1000));
            return;
        }
        long a2 = a.a();
        long k = k();
        if (k == 0 || a2 >= k) {
            try {
                arrayList2 = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
            } catch (Throwable th2) {
                MobLog.getInstance().w(th2);
                arrayList2 = new ArrayList();
            }
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                ArrayList a3 = a(arrayList2, j);
                a(a.u(), "APPS_ALL", a3);
                a(a3);
                a((a.h() * 1000) + a2);
                return;
            }
            return;
        }
        n();
    }

    private ArrayList<HashMap<String, String>> j() {
        BufferedReader bufferedReader;
        ArrayList<HashMap<String, String>> arrayList;
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.al");
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
                    HashMap fromJson = o().fromJson(readLine);
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

    private void a(long j, String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(a.a()));
        b.a().a(j, hashMap);
    }

    private ArrayList<HashMap<String, String>> a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        boolean z;
        if (arrayList == null || arrayList.isEmpty() || arrayList2 == null || arrayList2.isEmpty()) {
            return arrayList;
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            boolean z2 = false;
            Iterator it2 = hashMap.keySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                String str = (String) it2.next();
                if (str != null && str.contains(Config.TRACE_VISIT_FIRST)) {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                arrayList3.add(hashMap);
            } else {
                String str2 = (String) hashMap.get(Config.INPUT_DEF_PKG);
                if (!TextUtils.isEmpty(str2)) {
                    Iterator it3 = arrayList2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        HashMap hashMap2 = (HashMap) it3.next();
                        if (str2.equals(hashMap2.get(Config.INPUT_DEF_PKG))) {
                            Iterator it4 = hashMap2.keySet().iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    break;
                                }
                                String str3 = (String) it4.next();
                                if (str3 != null && str3.contains(Config.TRACE_VISIT_FIRST)) {
                                    arrayList3.add(hashMap2);
                                    z = true;
                                    break;
                                }
                            }
                        }
                    }
                    z = z2;
                    if (!z) {
                        arrayList3.add(hashMap);
                    }
                }
            }
        }
        return arrayList3;
    }

    private void a(ArrayList<HashMap<String, String>> arrayList) {
        try {
            b(arrayList);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private void b(ArrayList<HashMap<String, String>> arrayList) throws Throwable {
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.al"))), "utf-8");
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    outputStreamWriter.append(o().fromHashMap((HashMap) it.next())).append(10);
                }
                a((Closeable) outputStreamWriter);
            } catch (Throwable th) {
                th = th;
                a((Closeable) outputStreamWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStreamWriter = null;
            a((Closeable) outputStreamWriter);
            throw th;
        }
    }

    private void a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.nulal")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private long k() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.nulal");
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

    private void l() {
        if (!a.e() || !a.g()) {
            a(1);
            m();
        } else if (this.b == null) {
            this.b = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String str = null;
                    if (intent != null) {
                        str = intent.getAction();
                    }
                    if (l.this.a(str)) {
                        l.this.a(1);
                        l.this.a(1, (long) Config.BPLUS_DELAY_TIME);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            for (String addAction : a) {
                intentFilter.addAction(addAction);
            }
            intentFilter.addDataScheme("package");
            try {
                MobSDK.getContext().registerReceiver(this.b, intentFilter);
            } catch (Throwable th) {
            }
        }
        a(2, 3600000);
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        for (String equals : a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void m() {
        if (this.b != null) {
            try {
                MobSDK.getContext().unregisterReceiver(this.b);
            } catch (Throwable th) {
            }
            this.b = null;
        }
        if (this.c != null) {
            this.c = null;
        }
    }

    private void n() {
        ArrayList arrayList;
        ArrayList j = j();
        try {
            arrayList = DeviceHelper.getInstance(MobSDK.getContext()).getIA(false);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            arrayList = new ArrayList();
        }
        if (j == null || j.isEmpty()) {
            a(a.u(), "APPS_ALL", arrayList);
            a(arrayList);
            a(a.a() + (a.h() * 1000));
            return;
        }
        ArrayList b2 = b(arrayList, j);
        if (!b2.isEmpty()) {
            a(a.a(), "APPS_INCR", b2);
        }
        ArrayList b3 = b(j, arrayList);
        if (!b3.isEmpty()) {
            a(a.a(), "UNINSTALL", b3);
        }
        a(a(arrayList, j));
    }

    private ArrayList<HashMap<String, String>> b(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        boolean z;
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            String str = (String) hashMap.get(Config.INPUT_DEF_PKG);
            if (!TextUtils.isEmpty(str)) {
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (str.equals(((HashMap) it2.next()).get(Config.INPUT_DEF_PKG))) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    arrayList3.add(hashMap);
                }
            }
        }
        return arrayList3;
    }

    private Hashon o() {
        if (this.c == null) {
            this.c = new Hashon();
        }
        return this.c;
    }
}
