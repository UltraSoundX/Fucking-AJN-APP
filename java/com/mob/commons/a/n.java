package com.mob.commons.a;

import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.commons.i;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ResHelper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: RtClt */
public class n extends d {
    private static final String a = (VERSION.SDK_INT >= 16 ? "^u\\d+_a\\d+" : "^app_\\d+");
    private PackageManager b;
    private Process c = null;
    private OutputStream d = null;
    private String e = null;
    private long f = 0;
    private boolean g = true;

    /* access modifiers changed from: protected */
    public File a() {
        if (VERSION.SDK_INT > 24) {
            return null;
        }
        return d.a("comm/locks/.rc_lock");
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.c();
    }

    /* access modifiers changed from: protected */
    public void d() {
        b(0);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        j();
        a(0, (long) (a.d() * 1000));
    }

    private void i() throws Throwable {
        if (this.d == null || TextUtils.isEmpty(this.e)) {
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.plst");
            if (cacheRootFile != null) {
                if (!cacheRootFile.exists()) {
                    cacheRootFile.createNewFile();
                }
                this.e = cacheRootFile.getAbsolutePath();
                this.f = k();
                this.g = true;
                this.d = null;
                this.c = Runtime.getRuntime().exec(i.a(0));
                this.d = this.c.getOutputStream();
            }
        }
    }

    private void j() {
        String str;
        try {
            i();
            if (a.c()) {
                long a2 = a.a();
                this.d.write((i.a(1) + " " + this.e + " " + i.a(2) + " \"" + "======================" + "\" >> " + this.e + "\n").getBytes("ascii"));
                if (this.g) {
                    str = i.a(3) + " \"" + a2 + "_0\" >> " + this.e + "\n";
                    this.g = false;
                } else {
                    str = i.a(3) + " \"" + a2 + "_" + a.d() + "\" >> " + this.e + "\n";
                }
                this.d.write(str.getBytes("ascii"));
                this.d.flush();
                if (a2 >= this.f) {
                    this.d.write((i.a(4) + "\n").getBytes("ascii"));
                    this.d.flush();
                    this.d.close();
                    this.c.waitFor();
                    this.c.destroy();
                    if (a(this.e)) {
                        long l = l();
                        if (l > 0) {
                            this.f = l;
                        }
                        this.g = true;
                    }
                    this.c = Runtime.getRuntime().exec(i.a(0));
                    this.d = this.c.getOutputStream();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private long k() {
        long a2 = a.a();
        try {
            File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.nulplt");
            if (cacheRootFile.exists()) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(cacheRootFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            }
            cacheRootFile.createNewFile();
            l();
            return a.v() + a2;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return a.v() + a2;
        }
    }

    private long l() {
        DataOutputStream dataOutputStream;
        long a2 = a.a() + a.v();
        DataOutputStream dataOutputStream2 = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.nulplt")));
            try {
                dataOutputStream.writeLong(a2);
                a((Closeable) dataOutputStream);
                return a2;
            } catch (Throwable th) {
                th = th;
                try {
                    MobLog.getInstance().d(th);
                    a((Closeable) dataOutputStream);
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream2 = dataOutputStream;
                    a((Closeable) dataOutputStream2);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            a((Closeable) dataOutputStream2);
            throw th;
        }
    }

    private boolean a(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        a(str, arrayList, arrayList2);
        try {
            a(a((HashMap<String, String>[][]) a(a(arrayList), arrayList), arrayList2), arrayList2);
        } catch (Throwable th) {
        }
        return b(str);
    }

    private void a(String str, ArrayList<ArrayList<HashMap<String, String>>> arrayList, ArrayList<long[]> arrayList2) {
        BufferedReader bufferedReader;
        try {
            HashMap m = m();
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), "utf-8"));
            try {
                String readLine = bufferedReader.readLine();
                for (int i = 0; i < 7; i++) {
                    readLine = bufferedReader.readLine();
                }
                ArrayList arrayList3 = new ArrayList();
                while (readLine != null) {
                    if ("======================".equals(readLine)) {
                        try {
                            String[] split = bufferedReader.readLine().split("_");
                            long[] jArr = {Long.parseLong(split[0]), Long.parseLong(split[1])};
                            arrayList.add(arrayList3);
                            arrayList2.add(jArr);
                        } catch (Throwable th) {
                        }
                        arrayList3 = new ArrayList();
                        for (int i2 = 0; i2 < 7; i2++) {
                            bufferedReader.readLine();
                        }
                    } else if (readLine.length() > 0) {
                        a(readLine, m, arrayList3);
                    }
                    readLine = bufferedReader.readLine();
                }
                a((Closeable) bufferedReader);
            } catch (Throwable th2) {
                th = th2;
                try {
                    MobLog.getInstance().d(th);
                    a((Closeable) bufferedReader);
                } catch (Throwable th3) {
                    th = th3;
                    a((Closeable) bufferedReader);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            a((Closeable) bufferedReader);
            throw th;
        }
    }

    private void a(String str, HashMap<String, String[]> hashMap, ArrayList<HashMap<String, String>> arrayList) {
        String[] split = str.replaceAll(" +", " ").split(" ");
        if (split != null && split.length >= 10) {
            String str2 = split[split.length - 1];
            if (split[split.length - 2].matches(a) && !"top".equals(str2)) {
                if (hashMap == null || hashMap.isEmpty()) {
                    HashMap a2 = a(str2, split);
                    if (a2 != null) {
                        arrayList.add(a2);
                        return;
                    }
                    return;
                }
                String[] strArr = (String[]) hashMap.get(str2);
                if (strArr != null) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(Config.INPUT_DEF_PKG, str2);
                    hashMap2.put("name", strArr[0]);
                    hashMap2.put(Config.INPUT_DEF_VERSION, strArr[1]);
                    hashMap2.put("pcy", split[split.length - 3]);
                    arrayList.add(hashMap2);
                }
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.String> a(java.lang.String r7, java.lang.String[] r8) {
        /*
            r6 = this;
            r1 = 0
            r2 = 0
            r0 = 1
            android.content.pm.PackageManager r3 = r6.b     // Catch:{ Throwable -> 0x0066 }
            if (r3 != 0) goto L_0x0011
            android.content.Context r3 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0066 }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Throwable -> 0x0066 }
            r6.b = r3     // Catch:{ Throwable -> 0x0066 }
        L_0x0011:
            android.content.pm.PackageManager r3 = r6.b     // Catch:{ Throwable -> 0x0066 }
            r4 = 0
            android.content.pm.PackageInfo r4 = r3.getPackageInfo(r7, r4)     // Catch:{ Throwable -> 0x0066 }
            android.content.pm.ApplicationInfo r3 = r4.applicationInfo     // Catch:{ Throwable -> 0x0066 }
            int r3 = r3.flags     // Catch:{ Throwable -> 0x0066 }
            r3 = r3 & 1
            if (r3 != r0) goto L_0x005a
            r3 = r0
        L_0x0021:
            android.content.pm.ApplicationInfo r5 = r4.applicationInfo     // Catch:{ Throwable -> 0x0066 }
            int r5 = r5.flags     // Catch:{ Throwable -> 0x0066 }
            r5 = r5 & 128(0x80, float:1.794E-43)
            if (r5 != r0) goto L_0x005c
        L_0x0029:
            if (r3 != 0) goto L_0x006e
            if (r0 != 0) goto L_0x006e
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x0066 }
            r0.<init>()     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r2 = "pkg"
            r0.put(r2, r7)     // Catch:{ Throwable -> 0x0066 }
            android.content.pm.ApplicationInfo r2 = r4.applicationInfo     // Catch:{ Throwable -> 0x005e }
            android.content.pm.PackageManager r3 = r6.b     // Catch:{ Throwable -> 0x005e }
            java.lang.CharSequence r2 = r2.loadLabel(r3)     // Catch:{ Throwable -> 0x005e }
        L_0x003f:
            java.lang.String r3 = "name"
            if (r2 != 0) goto L_0x0061
            java.lang.String r2 = r4.packageName     // Catch:{ Throwable -> 0x0066 }
        L_0x0045:
            r0.put(r3, r2)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r2 = "version"
            java.lang.String r3 = r4.versionName     // Catch:{ Throwable -> 0x0066 }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r2 = "pcy"
            int r3 = r8.length     // Catch:{ Throwable -> 0x0066 }
            int r3 = r3 + -3
            r3 = r8[r3]     // Catch:{ Throwable -> 0x0066 }
            r0.put(r2, r3)     // Catch:{ Throwable -> 0x0066 }
        L_0x0059:
            return r0
        L_0x005a:
            r3 = r2
            goto L_0x0021
        L_0x005c:
            r0 = r2
            goto L_0x0029
        L_0x005e:
            r2 = move-exception
            r2 = r1
            goto L_0x003f
        L_0x0061:
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0066 }
            goto L_0x0045
        L_0x0066:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            r2.d(r0)
        L_0x006e:
            r0 = r1
            goto L_0x0059
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.n.a(java.lang.String, java.lang.String[]):java.util.HashMap");
    }

    private HashMap<String, String[]> m() {
        ArrayList n = n();
        HashMap<String, String[]> hashMap = new HashMap<>();
        Iterator it = n.iterator();
        while (it.hasNext()) {
            HashMap hashMap2 = (HashMap) it.next();
            hashMap.put((String) hashMap2.get(Config.INPUT_DEF_PKG), new String[]{(String) hashMap2.get("name"), (String) hashMap2.get(Config.INPUT_DEF_VERSION)});
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.String>> n() {
        /*
            r6 = this;
            r2 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x0056 }
            com.mob.tools.utils.DeviceHelper r0 = com.mob.tools.utils.DeviceHelper.getInstance(r0)     // Catch:{ Throwable -> 0x0056 }
            r1 = 0
            java.util.ArrayList r3 = r0.getIA(r1)     // Catch:{ Throwable -> 0x0056 }
        L_0x000e:
            if (r3 != 0) goto L_0x0083
            android.content.Context r0 = com.mob.MobSDK.getContext()
            java.lang.String r1 = "comm/dbs/.al"
            java.io.File r1 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r1)
            if (r1 == 0) goto L_0x0083
            boolean r0 = r1.exists()
            if (r0 == 0) goto L_0x0083
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            r0.<init>()     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.lang.String r5 = "utf-8"
            r4.<init>(r1, r5)     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x006b, all -> 0x0079 }
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x0081 }
        L_0x0041:
            if (r2 == 0) goto L_0x0060
            com.mob.tools.utils.Hashon r4 = new com.mob.tools.utils.Hashon     // Catch:{ Throwable -> 0x0081 }
            r4.<init>()     // Catch:{ Throwable -> 0x0081 }
            java.util.HashMap r2 = r4.fromJson(r2)     // Catch:{ Throwable -> 0x0081 }
            if (r2 == 0) goto L_0x0051
            r0.add(r2)     // Catch:{ Throwable -> 0x0081 }
        L_0x0051:
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x0081 }
            goto L_0x0041
        L_0x0056:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.w(r0)
            r3 = r2
            goto L_0x000e
        L_0x0060:
            r6.a(r1)
        L_0x0063:
            if (r0 != 0) goto L_0x006a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x006a:
            return r0
        L_0x006b:
            r0 = move-exception
            r1 = r2
        L_0x006d:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007e }
            r2.d(r0)     // Catch:{ all -> 0x007e }
            r6.a(r1)
            r0 = r3
            goto L_0x0063
        L_0x0079:
            r0 = move-exception
        L_0x007a:
            r6.a(r2)
            throw r0
        L_0x007e:
            r0 = move-exception
            r2 = r1
            goto L_0x007a
        L_0x0081:
            r0 = move-exception
            goto L_0x006d
        L_0x0083:
            r0 = r3
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.a.n.n():java.util.ArrayList");
    }

    private HashMap<String, Integer> a(ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) it.next()).iterator();
            int i2 = i;
            while (it2.hasNext()) {
                HashMap hashMap2 = (HashMap) it2.next();
                String str = ((String) hashMap2.get(Config.INPUT_DEF_PKG)) + Config.TRACE_TODAY_VISIT_SPLIT + ((String) hashMap2.get(Config.INPUT_DEF_VERSION));
                if (!hashMap.containsKey(str)) {
                    hashMap.put(str, Integer.valueOf(i2));
                    i2++;
                }
            }
            i = i2;
        }
        return hashMap;
    }

    private HashMap<String, String>[][] a(HashMap<String, Integer> hashMap, ArrayList<ArrayList<HashMap<String, String>>> arrayList) {
        HashMap<String, String>[][] hashMapArr = (HashMap[][]) Array.newInstance(HashMap.class, new int[]{hashMap.size(), arrayList.size()});
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList2 = (ArrayList) arrayList.get(i);
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                HashMap<String, String> hashMap2 = (HashMap) arrayList2.get(i2);
                hashMapArr[((Integer) hashMap.get(((String) hashMap2.get(Config.INPUT_DEF_PKG)) + Config.TRACE_TODAY_VISIT_SPLIT + ((String) hashMap2.get(Config.INPUT_DEF_VERSION)))).intValue()][i] = hashMap2;
            }
        }
        return hashMapArr;
    }

    private ArrayList<HashMap<String, Object>> a(HashMap<String, String>[][] hashMapArr, ArrayList<long[]> arrayList) {
        ArrayList<HashMap<String, Object>> arrayList2 = new ArrayList<>(hashMapArr.length);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= hashMapArr.length) {
                return arrayList2;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("runtimes", Long.valueOf(0));
            hashMap.put("fg", Integer.valueOf(0));
            hashMap.put("bg", Integer.valueOf(0));
            hashMap.put("empty", Integer.valueOf(0));
            arrayList2.add(hashMap);
            HashMap<String, String>[] hashMapArr2 = hashMapArr[i2];
            int length = hashMapArr2.length - 1;
            while (length >= 0) {
                if (hashMapArr2[length] != null) {
                    hashMap.put("runtimes", Long.valueOf((length == 0 ? 0 : ((long[]) arrayList.get(length))[1]) + ((Long) ResHelper.forceCast(hashMap.get("runtimes"), Long.valueOf(0))).longValue()));
                    if ("fg".equals(hashMapArr2[length].get("pcy"))) {
                        hashMap.put("fg", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("fg"), Integer.valueOf(0))).intValue() + 1));
                    } else if ("bg".equals(hashMapArr2[length].get("pcy"))) {
                        hashMap.put("bg", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("bg"), Integer.valueOf(0))).intValue() + 1));
                    } else {
                        hashMap.put("empty", Integer.valueOf(((Integer) ResHelper.forceCast(hashMap.get("empty"), Integer.valueOf(0))).intValue() + 1));
                    }
                    hashMap.put(Config.INPUT_DEF_PKG, hashMapArr2[length].get(Config.INPUT_DEF_PKG));
                    hashMap.put("name", hashMapArr2[length].get("name"));
                    hashMap.put(Config.INPUT_DEF_VERSION, hashMapArr2[length].get(Config.INPUT_DEF_VERSION));
                }
                length--;
            }
            i = i2 + 1;
        }
    }

    private void a(ArrayList<HashMap<String, Object>> arrayList, ArrayList<long[]> arrayList2) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "APP_RUNTIMES");
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(a.a()));
        hashMap.put("recordat", Long.valueOf(((long[]) arrayList2.get(0))[0]));
        long j = 0;
        for (int i = 1; i < arrayList2.size(); i++) {
            j += ((long[]) arrayList2.get(i))[1];
        }
        hashMap.put("sdk_runtime_len", Long.valueOf(j));
        hashMap.put("top_count", Integer.valueOf(arrayList2.size()));
        b.a().a(a.a(), hashMap);
    }

    private boolean b(String str) {
        try {
            File file = new File(str);
            file.delete();
            file.createNewFile();
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return false;
        }
    }
}
