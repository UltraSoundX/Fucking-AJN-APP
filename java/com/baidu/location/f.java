package com.baidu.location;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.baidu.location.f.a;
import com.baidu.location.g.j;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;

public class f extends Service {
    public static String d = "repll.jar";
    public static Context e = null;
    public static boolean f = false;
    public static boolean g = false;
    e a = null;
    e b = null;
    e c = null;

    static {
        StubApp.interface11(2655);
    }

    public static float a() {
        return 7.62f;
    }

    private boolean a(File file) {
        boolean z = false;
        try {
            File file2 = new File(j.i() + "/grtcfrsa.dat");
            if (file2.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                randomAccessFile.seek(200);
                if (randomAccessFile.readBoolean() && randomAccessFile.readBoolean()) {
                    int readInt = randomAccessFile.readInt();
                    if (readInt != 0) {
                        byte[] bArr = new byte[readInt];
                        randomAccessFile.read(bArr, 0, readInt);
                        String str = new String(bArr);
                        String a2 = j.a(file, "SHA-256");
                        if (!(str == null || a2 == null || !j.b(a2, str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiP7BS5IjEOzrKGR9/Ww9oSDhdX1ir26VOsYjT1T6tk2XumRpkHRwZbrucDcNnvSB4QsqiEJnvTSRi7YMbh2H9sLMkcvHlMV5jAErNvnuskWfcvf7T2mq7EUZI/Hf4oVZhHV0hQJRFVdTcjWI6q2uaaKM3VMh+roDesiE7CR2biQIDAQAB"))) {
                            z = true;
                        }
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception e2) {
        }
        return z;
    }

    public static String b() {
        return "app.jar";
    }

    public static Context c() {
        return e;
    }

    public IBinder onBind(Intent intent) {
        return this.c.onBind(intent);
    }

    @SuppressLint({"NewApi"})
    public void onCreate() {
        e = StubApp.getOrigApplicationContext(getApplicationContext());
        System.currentTimeMillis();
        this.b = new a();
        try {
            File file = new File(j.i() + File.separator + d);
            File file2 = new File(j.i() + File.separator + "app.jar");
            if (file.exists()) {
                if (file2.exists()) {
                    file2.delete();
                }
                file.renameTo(file2);
            }
            if (file2.exists() && a(new File(j.i() + File.separator + "app.jar"))) {
                this.a = (e) new DexClassLoader(j.i() + File.separator + "app.jar", j.i(), null, getClassLoader()).loadClass("com.baidu.serverLoc.LocationService").newInstance();
            }
        } catch (Exception e2) {
            this.a = null;
        }
        if (this.a == null || this.a.a() < this.b.a()) {
            this.c = this.b;
            this.a = null;
        } else {
            this.c = this.a;
            this.b = null;
        }
        f = true;
        this.c.a((Context) this);
    }

    public void onDestroy() {
        f = false;
        this.c.onDestroy();
        if (g) {
            stopForeground(true);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                int intExtra = intent.getIntExtra("command", 0);
                if (intExtra == 1) {
                    startForeground(intent.getIntExtra(Config.FEED_LIST_ITEM_CUSTOM_ID, 0), (Notification) intent.getParcelableExtra("notification"));
                    g = true;
                } else if (intExtra == 2) {
                    stopForeground(intent.getBooleanExtra("removenotify", true));
                    g = false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return this.c.onStartCommand(intent, i, i2);
    }

    public void onTaskRemoved(Intent intent) {
        this.c.onTaskRemoved(intent);
    }

    public boolean onUnbind(Intent intent) {
        return this.c.a(intent);
    }
}
