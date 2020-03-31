package com.tencent.smtt.export.external;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.stub.StubApp;
import java.io.File;
import java.util.ArrayList;

public class DexClassLoaderProviderService extends Service {
    private static final String LOGTAG = "dexloader";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOGTAG, "DexClassLoaderProviderService -- onCreate()");
        DexClassLoaderProvider.setForceLoadDexFlag(true, this);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(LOGTAG, "DexClassLoaderProviderService -- onStartCommand(" + intent + ")");
        if (intent != null) {
            try {
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("dex2oat");
                if (stringArrayListExtra != null) {
                    String str = (String) stringArrayListExtra.get(0);
                    String str2 = (String) stringArrayListExtra.get(1);
                    String str3 = (String) stringArrayListExtra.get(2);
                    String str4 = (String) stringArrayListExtra.get(3);
                    Log.d(LOGTAG, "DexClassLoaderProviderService -- onStartCommand(" + str + ")");
                    ClassLoader classLoader = getClassLoader();
                    File file = new File(str3);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    DexClassLoaderProvider.createDexClassLoader(str2, str3, str4, classLoader, StubApp.getOrigApplicationContext(getApplicationContext()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public void onDestroy() {
        Log.d(LOGTAG, "DexClassLoaderProviderService -- onDestroy()");
        System.exit(0);
    }
}
