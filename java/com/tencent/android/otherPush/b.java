package com.tencent.android.otherPush;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.support.api.push.PushEventReceiver;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.NotificationService;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.SystemReceiver;
import com.stub.StubApp;
import com.tencent.android.otherPush.a.a;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.stat.XGPatchMonitor;
import com.tencent.otherpush.receiver.HwReceiver;
import com.tencent.otherpush.receiver.XmReceiver;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.xiaomi.push.service.XMJobService;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.receivers.PingReceiver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    public static int a = 0;
    private static String b;
    private static final String c = b.class.getSimpleName();
    private static String d = "";
    private static final FilenameFilter e = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".so");
        }
    };

    private static String a() {
        String str = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(str)) {
            return str.trim().toLowerCase();
        }
        return str;
    }

    public static void a(Context context) {
        int i;
        b = context.getPackageName();
        String absolutePath = context.getDir("dex", 0).getAbsolutePath();
        String a2 = a();
        if ("xiaomi".equals(a2)) {
            d = XGPatchMonitor.TypeXiaoMi;
            i = 1;
        } else if ("huawei".equals(a2)) {
            d = XGPatchMonitor.TypeHauwei;
            i = 5;
        } else if ("meizu".equals(a2)) {
            d = XGPatchMonitor.TypeMeizu;
            i = 2;
        } else {
            return;
        }
        String a3 = com.tencent.android.otherPush.a.b.a(context, b + "_dexPath", "");
        int a4 = com.tencent.android.otherPush.a.b.a(context, context.getPackageName() + "_loadFailCount", 0);
        String a5 = com.tencent.android.otherPush.a.b.a(context, b + "_ccConfig", "");
        if (!TextUtils.isEmpty(a5)) {
            try {
                if (new JSONObject(a5).optBoolean("enable", true)) {
                    com.tencent.android.otherPush.a.b.b(context, context.getPackageName() + "_loadFailCount", 0);
                } else {
                    return;
                }
            } catch (Throwable th) {
                Log.e(c, "ccConfig paser exception :" + th);
                return;
            }
        }
        if (TextUtils.isEmpty(a3) || a4 >= 1) {
            Log.v(c, "path is null or loadFailCount > = 1");
            return;
        }
        Log.v(c, "path " + a3);
        a(context.getCacheDir(), absolutePath, context);
        Log.v(c, "type = " + i);
        switch (i) {
            case 1:
                try {
                    Class.forName(PushMessageHandler.class.getName());
                    Class.forName(PushMessageReceiver.class.getName());
                    Class.forName(XmReceiver.class.getName());
                    Class.forName(NetworkStatusReceiver.class.getName());
                    Class.forName(PingReceiver.class.getName());
                    Class.forName(MiPushClient.class.getName());
                    Class.forName(XMPushService.class.getName());
                    Class.forName(XMJobService.class.getName());
                    Class.forName(MessageHandleService.class.getName());
                    return;
                } catch (Throwable th2) {
                    String str = context.getPackageName() + "_loadFailCount";
                    int i2 = a + 1;
                    a = i2;
                    com.tencent.android.otherPush.a.b.b(context, str, i2);
                    return;
                }
            case 2:
                try {
                    Class.forName(MzPushMessageReceiver.class.getName());
                    Class.forName("com.tencent.otherpush.receiver.MzReceiver");
                    Class.forName(NotificationService.class.getName());
                    Class.forName(SystemReceiver.class.getName());
                    Class.forName(PushManager.class.getName());
                    return;
                } catch (Throwable th3) {
                    String str2 = context.getPackageName() + "_loadFailCount";
                    int i3 = a + 1;
                    a = i3;
                    com.tencent.android.otherPush.a.b.b(context, str2, i3);
                    return;
                }
            case 5:
                try {
                    Class.forName(PushEventReceiver.class.getName());
                    Class.forName(HwReceiver.class.getName());
                    return;
                } catch (Throwable th4) {
                    String str3 = context.getPackageName() + "_loadFailCount";
                    int i4 = a + 1;
                    a = i4;
                    com.tencent.android.otherPush.a.b.b(context, str3, i4);
                    return;
                }
            default:
                return;
        }
    }

    private static List<String> a(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        String a2 = com.tencent.android.otherPush.a.b.a(context, str + "_dexPath", "");
        if (!TextUtils.isEmpty(a2)) {
            arrayList.add(a2);
        }
        return arrayList;
    }

    private static void a(File file, String str, Context context) {
        if (context != null) {
            try {
                if (StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
                    XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), d, XGPatchMonitor.ActionReadyPatch, 0, "Patch load Start", null);
                }
            } catch (Throwable th) {
                String str2 = context.getPackageName() + "_loadFailCount";
                int i = a + 1;
                a = i;
                com.tencent.android.otherPush.a.b.b(context, str2, i);
                Log.e(c, "instantiateRealApplication :" + th);
                if (context != null && StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
                    XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), d, XGPatchMonitor.ActionParsePatch, 1803, "IncrementalClassLoader inject failed", null);
                    return;
                }
                return;
            }
        }
        String a2 = a(str);
        if (!(context == null || StubApp.getOrigApplicationContext(context.getApplicationContext()) == null)) {
            XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), d, XGPatchMonitor.ActionParsePatch, 0, "IncrementalClassLoader inject", null);
        }
        a.a(a.class.getClassLoader(), b, file, a2, a(context, b));
    }

    private static Map<String, String> a(File file) {
        HashMap hashMap = new HashMap();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return hashMap;
                }
                String[] split = readLine.split(" ");
                hashMap.put(split[0], split[1]);
            } finally {
                bufferedReader.close();
            }
        }
    }

    private static String a(String str) {
        String file;
        Map map;
        File file2 = new File("/data/local/tmp/incrementaldeployment/" + b + "/native");
        File file3 = new File(file2, "native_manifest");
        File file4 = new File(str + "/incrementallib");
        File file5 = new File(file4, "manifest");
        String str2 = str + "/lib";
        if (!file3.exists()) {
            return str2;
        }
        Map a2 = a(file3);
        Map hashMap = new HashMap();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        if (a2.isEmpty()) {
            file = str2;
        } else {
            file = file4.toString();
        }
        if (!file4.exists() && !file4.mkdirs()) {
            Log.e(c, "Could not mkdir " + file4);
        }
        if (file5.exists()) {
            map = a(file5);
        } else {
            for (String add : file4.list(e)) {
                hashSet.add(add);
            }
            map = hashMap;
        }
        for (String str3 : map.keySet()) {
            if (!a2.containsKey(str3) || !((String) a2.get(str3)).equals(map.get(str3))) {
                hashSet.add(str3);
            }
        }
        for (String str4 : a2.keySet()) {
            if (!map.containsKey(str4) || !((String) map.get(str4)).equals(a2.get(str4))) {
                hashSet2.add(str4);
            }
        }
        if (hashSet.isEmpty() && hashSet2.isEmpty()) {
            return file;
        }
        file5.delete();
        for (String str5 : hashSet) {
            File file6 = new File(file4 + "/" + str5);
            Log.v(c, "Deleting " + file6);
            if (file6.exists() && !file6.delete()) {
                Log.e(c, "Could not delete " + file6);
            }
        }
        for (String str6 : hashSet2) {
            Log.v(c, "Copying: " + str6);
            a(new File(file2 + "/" + str6), new File(file4 + "/" + str6));
        }
        try {
            a(file3, file5);
            return file;
        } finally {
            file5.delete();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r5, java.io.File r6) {
        /*
            r2 = 0
            java.lang.String r0 = c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Copying "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r3 = " -> "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0054 }
            r3.<init>(r5)     // Catch:{ all -> 0x0054 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0057 }
            r1.<init>(r6)     // Catch:{ all -> 0x0057 }
            r0 = 1048576(0x100000, float:1.469368E-39)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x003c }
        L_0x0031:
            int r2 = r3.read(r0)     // Catch:{ all -> 0x003c }
            if (r2 <= 0) goto L_0x0049
            r4 = 0
            r1.write(r0, r4, r2)     // Catch:{ all -> 0x003c }
            goto L_0x0031
        L_0x003c:
            r0 = move-exception
            r2 = r3
        L_0x003e:
            if (r2 == 0) goto L_0x0043
            r2.close()
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()
        L_0x0048:
            throw r0
        L_0x0049:
            if (r3 == 0) goto L_0x004e
            r3.close()
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()
        L_0x0053:
            return
        L_0x0054:
            r0 = move-exception
            r1 = r2
            goto L_0x003e
        L_0x0057:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.otherPush.b.a(java.io.File, java.io.File):void");
    }
}
