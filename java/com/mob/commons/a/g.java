package com.mob.commons.a;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.a;
import com.mob.commons.b;
import com.mob.commons.d;
import com.mob.commons.i;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: DUClt */
public class g extends d {
    private PackageManager a;

    /* access modifiers changed from: protected */
    public boolean b_() {
        return a.W() > 0;
    }

    /* access modifiers changed from: protected */
    public File a() {
        return d.a("comm/locks/.du_lock");
    }

    /* access modifiers changed from: protected */
    public void d() {
        a(1);
        a(1, a.W() * 1000);
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                long a2 = a.a();
                long j = j();
                if (a2 >= j) {
                    i();
                    long X = a.X() * 1000;
                    a(a2 + X);
                    a(1, X);
                    return;
                }
                a(1, j - a2);
                return;
            default:
                return;
        }
    }

    private void i() {
        long j;
        try {
            if (DeviceHelper.getInstance(MobSDK.getContext()).checkPermission("android.permission.READ_EXTERNAL_STORAGE")) {
                File file = new File(Environment.getExternalStorageDirectory() + i.a(16));
                if (file == null || !file.isDirectory()) {
                    MobLog.getInstance().d("[%s] %s", "DUClt", "Can not read dir");
                    return;
                }
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length <= 0) {
                    MobLog.getInstance().d("[%s] %s", "DUClt", "No subs");
                    return;
                }
                long a2 = a.a();
                HashMap k = k();
                ArrayList arrayList = new ArrayList();
                HashMap hashMap = new HashMap();
                for (File file2 : listFiles) {
                    if (a(file2)) {
                        arrayList.add(file2.getName());
                        long a3 = a(file2.getAbsolutePath());
                        Long l = (Long) k.get(file2.getName());
                        if (l != null) {
                            j = l.longValue();
                        } else {
                            j = 0;
                        }
                        if (a3 > j) {
                            k.put(file2.getName(), Long.valueOf(a3));
                            hashMap.put(file2.getName(), Long.valueOf(a3));
                        }
                    }
                }
                this.a = null;
                ArrayList arrayList2 = new ArrayList();
                for (String str : k.keySet()) {
                    if (!arrayList.contains(str)) {
                        arrayList2.add(str);
                    }
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    k.remove((String) it.next());
                }
                a(k);
                long a4 = a.a();
                if (!hashMap.isEmpty() || !arrayList2.isEmpty()) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("scanAt", Long.valueOf(a2));
                    hashMap2.put("update", hashMap);
                    hashMap2.put("delete", arrayList2);
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("type", "APP_DIR_ACTIVE");
                    hashMap3.put("data", hashMap2);
                    hashMap3.put("datetime", Long.valueOf(a4));
                    b.a().a(a4, hashMap3);
                }
                MobLog.getInstance().d("[%s] %s", "DUClt", "total: " + arrayList.size() + ", upd: " + hashMap.size() + ", del: " + arrayList2.size() + ", duration: " + (a.a() - a2) + " ms");
                return;
            }
            MobLog.getInstance().d("[%s] %s", "DUClt", "No permission");
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    private void a(long j) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dupdcd")));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    private long j() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dupdcd");
        if (cacheRootFile.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(cacheRootFile));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            } catch (Throwable th) {
                MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
            }
        }
        return 0;
    }

    private void a(HashMap<String, Long> hashMap) {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dudcd");
        try {
            byte[] a2 = a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), hashMap);
            FileChannel channel = new FileOutputStream(cacheRootFile).getChannel();
            channel.write(ByteBuffer.wrap(a2));
            channel.force(true);
            channel.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
        }
    }

    private byte[] a(String str, HashMap<String, Long> hashMap) {
        String fromHashMap = new Hashon().fromHashMap(hashMap);
        try {
            return Data.AES128Encode(str, fromHashMap);
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
            return fromHashMap.getBytes();
        }
    }

    private HashMap<String, Long> k() {
        File cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), "comm/dbs/.dudcd");
        if (cacheRootFile.exists()) {
            try {
                FileChannel channel = new FileInputStream(cacheRootFile).getChannel();
                ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
                do {
                } while (channel.read(allocate) > 0);
                return a(DeviceHelper.getInstance(MobSDK.getContext()).getModel(), allocate.array());
            } catch (Throwable th) {
                MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
            }
        }
        return new HashMap<>();
    }

    private HashMap<String, Long> a(String str, byte[] bArr) {
        try {
            return new Hashon().fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().d(th, "[%s] %s", "DUClt", th.getMessage());
            return new HashMap<>();
        }
    }

    private boolean a(File file) {
        PackageInfo packageInfo;
        if (file != null && file.isDirectory()) {
            try {
                if (this.a == null) {
                    this.a = MobSDK.getContext().getPackageManager();
                }
                packageInfo = this.a.getPackageInfo(file.getName(), 0);
            } catch (Throwable th) {
                MobLog.getInstance().d("[%s] %s", "DUClt", "Name not found: " + th.getMessage());
                packageInfo = null;
            }
            if (packageInfo != null && !a(packageInfo)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(PackageInfo packageInfo) {
        boolean z;
        boolean z2;
        if ((packageInfo.applicationInfo.flags & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        if ((packageInfo.applicationInfo.flags & 128) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    private long a(String str) {
        long j;
        int i;
        int i2;
        long j2;
        int i3;
        int i4;
        long j3 = 0;
        File file = new File(str);
        if (file == null || !file.exists()) {
            MobLog.getInstance().d("[%s] %s", "DUClt", "Dir not exists. path: " + str);
        } else {
            j3 = file.lastModified();
            LinkedList linkedList = new LinkedList();
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (i5 < length) {
                    File file2 = listFiles[i5];
                    if (file2 != null) {
                        j2 = file2.lastModified();
                        if (j2 <= j3) {
                            j2 = j3;
                        }
                        if (file2.isDirectory()) {
                            linkedList.add(file2);
                            i3 = i6 + 1;
                            i4 = i7;
                        } else {
                            i4 = i7 + 1;
                            i3 = i6;
                        }
                    } else {
                        j2 = j3;
                        i3 = i6;
                        i4 = i7;
                    }
                    i5++;
                    i7 = i4;
                    i6 = i3;
                    j3 = j2;
                }
                while (!linkedList.isEmpty()) {
                    File[] listFiles2 = ((File) linkedList.removeFirst()).listFiles();
                    if (listFiles2 != null) {
                        int length2 = listFiles2.length;
                        int i8 = 0;
                        while (i8 < length2) {
                            File file3 = listFiles2[i8];
                            if (file3 != null) {
                                j = file3.lastModified();
                                if (j <= j3) {
                                    j = j3;
                                }
                                if (file3.isDirectory()) {
                                    linkedList.add(file3);
                                    i = i6 + 1;
                                    i2 = i7;
                                } else {
                                    i2 = i7 + 1;
                                    i = i6;
                                }
                            } else {
                                j = j3;
                                i = i6;
                                i2 = i7;
                            }
                            i8++;
                            i7 = i2;
                            i6 = i;
                            j3 = j;
                        }
                    }
                }
            }
        }
        return j3;
    }
}
