package com.tencent.android.otherPush;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.support.api.push.PushEventReceiver;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.NotificationService;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.SystemReceiver;
import com.stub.StubApp;
import com.tencent.android.otherPush.a.b;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a extends Application {
    public static int a = 0;
    private static final String b = a.class.getSimpleName();
    private static final FilenameFilter c = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".so");
        }
    };
    private static String i = "";
    private final String d;
    private final String e;
    private String f;
    private Application g;
    private Object h;

    public a() {
        String[] split = a("assets/stub_application_data.txt").split("\n");
        this.d = split[0].toString().trim();
        this.e = split[1].toString().trim();
        Log.v(b, String.format("StubApplication created. Android package is %s, real application class is %s.", new Object[]{this.e, this.d}));
    }

    private String a() {
        String str = "/data/local/tmp/incrementaldeployment/" + this.e + "/";
        String str2 = str + "resources.ap_";
        if (!new File(str2).isFile()) {
            str2 = str + "resources";
            if (!new File(str2).isDirectory()) {
                Log.v(b, "Cannot find external resources, not patching them in");
                return null;
            }
        }
        Log.v(b, "Found external resources at " + str2);
        return str2;
    }

    private List<String> a(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        String a2 = b.a(context, str + "_dexPath", "");
        if (!TextUtils.isEmpty(a2)) {
            arrayList.add(a2);
        }
        return arrayList;
    }

    private String a(String str) {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
            return str2;
        } catch (IOException e3) {
            throw new IllegalStateException(e3);
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d9, code lost:
        android.util.Log.e(b, "monkeyPatchApplication :" + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0 = java.lang.Class.forName("android.app.ActivityThread$PackageInfo");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0101, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d8 A[ExcHandler: ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException (e java.lang.Throwable), Splitter:B:1:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0101 A[ExcHandler: NoSuchFieldException (e java.lang.NoSuchFieldException), Splitter:B:1:0x0004] */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
            r11 = this;
            r2 = 0
            r1 = 0
            java.lang.String r0 = "android.app.ActivityThread"
            java.lang.Class r4 = java.lang.Class.forName(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.String r0 = "currentActivityThread"
            r3 = 0
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.reflect.Method r0 = r4.getMethod(r0, r3)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3 = 1
            r0.setAccessible(r3)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3 = 0
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r5 = r0.invoke(r3, r5)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.String r0 = "mInitialApplication"
            java.lang.reflect.Field r3 = r4.getDeclaredField(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r0 = 1
            r3.setAccessible(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r0 = r3.get(r5)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            android.app.Application r0 = (android.app.Application) r0     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r0 != r11) goto L_0x0034
            android.app.Application r0 = r11.g     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3.set(r5, r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
        L_0x0034:
            java.lang.String r0 = "mAllApplications"
            java.lang.reflect.Field r0 = r4.getDeclaredField(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3 = 1
            r0.setAccessible(r3)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3 = r1
        L_0x0045:
            int r6 = r0.size()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r3 >= r6) goto L_0x0059
            java.lang.Object r6 = r0.get(r3)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r6 != r11) goto L_0x0056
            android.app.Application r6 = r11.g     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r0.set(r3, r6)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
        L_0x0056:
            int r3 = r3 + 1
            goto L_0x0045
        L_0x0059:
            java.lang.String r0 = "android.app.LoadedApk"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x00f2, IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, InvocationTargetException -> 0x0105 }
        L_0x005f:
            java.lang.String r3 = "mApplication"
            java.lang.reflect.Field r3 = r0.getDeclaredField(r3)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r6 = 1
            r3.setAccessible(r6)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.String r6 = "mResDir"
            java.lang.reflect.Field r6 = r0.getDeclaredField(r6)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r0 = 1
            r6.setAccessible(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Class<android.app.Application> r0 = android.app.Application.class
            java.lang.String r7 = "mLoadedApk"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r7)     // Catch:{ NoSuchFieldException -> 0x0107, IllegalAccessException -> 0x00d8, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r2 = r0
        L_0x007c:
            r0 = 2
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r0 = 0
            java.lang.String r8 = "mPackages"
            r7[r0] = r8     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r0 = 1
            java.lang.String r8 = "mResourcePackages"
            r7[r0] = r8     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            int r8 = r7.length     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
        L_0x008a:
            if (r1 >= r8) goto L_0x00f1
            r0 = r7[r1]     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.reflect.Field r0 = r4.getDeclaredField(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r9 = 1
            r0.setAccessible(r9)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
        L_0x00a4:
            boolean r0 = r9.hasNext()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r0 == 0) goto L_0x00fb
            java.lang.Object r0 = r9.next()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.Object r0 = r0.get()     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r0 == 0) goto L_0x00a4
            java.lang.Object r10 = r3.get(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r10 != r11) goto L_0x00a4
            android.app.Application r10 = r11.g     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r3.set(r0, r10)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            java.lang.String r10 = r11.f     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            if (r10 == 0) goto L_0x00d0
            java.lang.String r10 = r11.f     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r6.set(r0, r10)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
        L_0x00d0:
            if (r2 == 0) goto L_0x00a4
            android.app.Application r10 = r11.g     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            r2.set(r10, r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            goto L_0x00a4
        L_0x00d8:
            r0 = move-exception
        L_0x00d9:
            java.lang.String r1 = b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "monkeyPatchApplication :"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r1, r0)
        L_0x00f1:
            return
        L_0x00f2:
            r0 = move-exception
            java.lang.String r0 = "android.app.ActivityThread$PackageInfo"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ IllegalAccessException -> 0x00d8, NoSuchFieldException -> 0x0101, NoSuchMethodException -> 0x0103, ClassNotFoundException -> 0x00ff, InvocationTargetException -> 0x0105 }
            goto L_0x005f
        L_0x00fb:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x008a
        L_0x00ff:
            r0 = move-exception
            goto L_0x00d9
        L_0x0101:
            r0 = move-exception
            goto L_0x00d9
        L_0x0103:
            r0 = move-exception
            goto L_0x00d9
        L_0x0105:
            r0 = move-exception
            goto L_0x00d9
        L_0x0107:
            r0 = move-exception
            goto L_0x007c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.otherPush.a.b():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00da, code lost:
        r1 = r2.getDeclaredField("mResourceReferences");
        r1.setAccessible(true);
        r1 = (java.util.Collection) r1.get(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0047, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0047 A[ExcHandler: ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException (e java.lang.Throwable), Splitter:B:16:0x00a5] */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
            r6 = this;
            java.lang.String r0 = r6.f
            if (r0 != 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            java.lang.Class<android.content.res.AssetManager> r0 = android.content.res.AssetManager.class
            r1 = 0
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            android.content.res.AssetManager r0 = (android.content.res.AssetManager) r0     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Class<android.content.res.AssetManager> r1 = android.content.res.AssetManager.class
            java.lang.String r2 = "addAssetPath"
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r4 = 0
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r3)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r3 = 0
            java.lang.String r4 = r6.f     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2[r3] = r4     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r1 = r1.invoke(r0, r2)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            int r1 = r1.intValue()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            if (r1 != 0) goto L_0x0061
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.String r1 = "Could not create new AssetManager"
            r0.<init>(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            throw r0     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
        L_0x0047:
            r0 = move-exception
        L_0x0048:
            java.lang.String r1 = b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "monkeyPatchExistingResources :"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r1, r0)
            goto L_0x0004
        L_0x0061:
            java.lang.Class<android.content.res.AssetManager> r1 = android.content.res.AssetManager.class
            java.lang.String r2 = "ensureStringBlocks"
            r3 = 0
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r3)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r1.invoke(r0, r2)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.String r1 = "android.app.ResourcesManager"
            java.lang.Class r2 = java.lang.Class.forName(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.String r1 = "getInstance"
            r3 = 0
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.reflect.Method r1 = r2.getDeclaredMethod(r1, r3)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r3 = 1
            r1.setAccessible(r3)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r3 = 0
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r3 = r1.invoke(r3, r4)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.String r1 = "mActiveResources"
            java.lang.reflect.Field r1 = r2.getDeclaredField(r1)     // Catch:{ NoSuchFieldException -> 0x00d9, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r4 = 1
            r1.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x00d9, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ NoSuchFieldException -> 0x00d9, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            android.util.ArrayMap r1 = (android.util.ArrayMap) r1     // Catch:{ NoSuchFieldException -> 0x00d9, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.util.Collection r1 = r1.values()     // Catch:{ NoSuchFieldException -> 0x00d9, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
        L_0x00a5:
            java.util.Iterator r2 = r1.iterator()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
        L_0x00a9:
            boolean r1 = r2.hasNext()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            if (r1 == 0) goto L_0x0004
            java.lang.Object r1 = r2.next()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r1 = r1.get()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            android.content.res.Resources r1 = (android.content.res.Resources) r1     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Class<android.content.res.Resources> r3 = android.content.res.Resources.class
            java.lang.String r4 = "mAssets"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ NoSuchFieldException -> 0x00eb, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r4 = 1
            r3.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x00eb, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r3.set(r1, r0)     // Catch:{ NoSuchFieldException -> 0x00eb, IllegalAccessException -> 0x0047, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
        L_0x00ca:
            android.content.res.Configuration r3 = r1.getConfiguration()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            android.util.DisplayMetrics r4 = r1.getDisplayMetrics()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r1.updateConfiguration(r3, r4)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            goto L_0x00a9
        L_0x00d6:
            r0 = move-exception
            goto L_0x0048
        L_0x00d9:
            r1 = move-exception
            java.lang.String r1 = "mResourceReferences"
            java.lang.reflect.Field r1 = r2.getDeclaredField(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r2 = 1
            r1.setAccessible(r2)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.util.Collection r1 = (java.util.Collection) r1     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            goto L_0x00a5
        L_0x00eb:
            r3 = move-exception
            java.lang.Class<android.content.res.Resources> r3 = android.content.res.Resources.class
            java.lang.String r4 = "mResourcesImpl"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r4 = 1
            r3.setAccessible(r4)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.Class r4 = r3.getClass()     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            java.lang.String r5 = "mAssets"
            java.lang.reflect.Field r4 = r4.getDeclaredField(r5)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r5 = 1
            r4.setAccessible(r5)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            r4.set(r3, r0)     // Catch:{ IllegalAccessException -> 0x0047, NoSuchFieldException -> 0x00d6, NoSuchMethodException -> 0x010e, ClassNotFoundException -> 0x0111, InvocationTargetException -> 0x0117, InstantiationException -> 0x0114 }
            goto L_0x00ca
        L_0x010e:
            r0 = move-exception
            goto L_0x0048
        L_0x0111:
            r0 = move-exception
            goto L_0x0048
        L_0x0114:
            r0 = move-exception
            goto L_0x0048
        L_0x0117:
            r0 = move-exception
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.otherPush.a.c():void");
    }

    private void a(File file, String str, Context context) {
        this.f = a();
        if (context != null) {
            try {
                if (StubApp.getOrigApplicationContext(context.getApplicationContext()) != null) {
                    XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), i, XGPatchMonitor.ActionReadyPatch, 0, "Patch load Start", null);
                }
            } catch (Throwable th) {
                String str2 = this.e + "_loadFailCount";
                int i2 = a + 1;
                a = i2;
                b.b(context, str2, i2);
                Log.e(b, "instantiateRealApplication :" + th);
                if (!(context == null || StubApp.getOrigApplicationContext(context.getApplicationContext()) == null)) {
                    XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), i, XGPatchMonitor.ActionParsePatch, 1803, "IncrementalClassLoader inject failed", null);
                }
            }
        }
        String b2 = b(str);
        if (!(context == null || StubApp.getOrigApplicationContext(context.getApplicationContext()) == null)) {
            XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), i, XGPatchMonitor.ActionParsePatch, 0, "IncrementalClassLoader inject", null);
        }
        com.tencent.android.otherPush.a.a.a(a.class.getClassLoader(), this.e, file, b2, a(context, this.e));
        try {
            Log.i(b, "realClassName =  " + this.d);
            this.g = (Application) Class.forName(this.d).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            String str3 = this.e + "_loadFailCount";
            int i3 = a + 1;
            a = i3;
            b.b(context, str3, i3);
            Log.e(b, "RealApplication :" + e2);
        }
    }

    private String b(String str) {
        String file;
        Map map;
        File file2 = new File("/data/local/tmp/incrementaldeployment/" + this.e + "/native");
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
        if (file4.exists() || file4.mkdirs()) {
            if (file5.exists()) {
                map = a(file5);
            } else {
                for (String add : file4.list(c)) {
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
                Log.v(b, "Deleting " + file6);
                if (file6.exists() && !file6.delete()) {
                    throw new IOException("Could not delete " + file6);
                }
            }
            for (String str6 : hashSet2) {
                Log.v(b, "Copying: " + str6);
                a(new File(file2 + "/" + str6), new File(file4 + "/" + str6));
            }
            try {
                a(file3, file5);
                return file;
            } finally {
                file5.delete();
            }
        } else {
            throw new IOException("Could not mkdir " + file4);
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r5, java.io.File r6) {
        /*
            r2 = 0
            java.lang.String r0 = b
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
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.otherPush.a.a(java.io.File, java.io.File):void");
    }

    private static Field a(Object obj, String str) {
        Class cls = obj.getClass();
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException e2) {
                cls = cls.getSuperclass();
            }
        }
        throw new IllegalStateException("Field '" + str + "' not found");
    }

    @TargetApi(19)
    private void d() {
        Log.v(b, "enableContentProviders");
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method method = cls.getMethod("currentActivityThread", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Object obj = a(invoke, "mBoundApplication").get(invoke);
            a(obj, "providers").set(obj, this.h);
            if (this.h != null) {
                Method declaredMethod = cls.getDeclaredMethod("installContentProviders", new Class[]{Context.class, List.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(invoke, new Object[]{this.g, this.h});
                this.h = null;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e(b, "enableContentProviders :" + e2);
        }
    }

    @TargetApi(19)
    private void e() {
        Log.v(b, "disableContentProviders");
        try {
            Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Object obj = a(invoke, "mBoundApplication").get(invoke);
            Field a2 = a(obj, "providers");
            this.h = a2.get(obj);
            a2.set(obj, null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e(b, "disableContentProviders :" + e2);
        }
    }

    private static String f() {
        String str = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(str)) {
            return str.trim().toLowerCase();
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        char c2;
        super.attachBaseContext(context);
        String absolutePath = context.getDir("dex", 0).getAbsolutePath();
        String f2 = f();
        if ("xiaomi".equals(f2)) {
            i = XGPatchMonitor.TypeXiaoMi;
            c2 = 1;
        } else if ("huawei".equals(f2)) {
            i = XGPatchMonitor.TypeHauwei;
            c2 = 5;
        } else if ("meizu".equals(f2)) {
            i = XGPatchMonitor.TypeMeizu;
            c2 = 2;
        } else {
            return;
        }
        String a2 = b.a(context, this.e + "_dexPath", "");
        int a3 = b.a(context, this.e + "_loadFailCount", 0);
        String a4 = b.a(context, this.e + "_ccConfig", "");
        if (!TextUtils.isEmpty(a4)) {
            try {
                if (new JSONObject(a4).optBoolean("enable", true)) {
                    b.b(context, context.getPackageName() + "_loadFailCount", 0);
                } else {
                    return;
                }
            } catch (Throwable th) {
                Log.e(b, "ccConfig paser exception :" + th);
                return;
            }
        }
        if (TextUtils.isEmpty(a2) || a3 >= 1) {
            Log.v(b, "path is null or loadFailCount > = 1");
            return;
        }
        a(context.getCacheDir(), absolutePath, context);
        switch (c2) {
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
                    break;
                } catch (Throwable th2) {
                    String str = this.e + "_loadFailCount";
                    int i2 = a + 1;
                    a = i2;
                    b.b(context, str, i2);
                    break;
                }
            case 2:
                try {
                    Class.forName(MzPushMessageReceiver.class.getName());
                    Class.forName("com.tencent.otherpush.receiver.MzReceiver");
                    Class.forName(NotificationService.class.getName());
                    Class.forName(SystemReceiver.class.getName());
                    Class.forName(PushManager.class.getName());
                    break;
                } catch (Throwable th3) {
                    String str2 = this.e + "_loadFailCount";
                    int i3 = a + 1;
                    a = i3;
                    b.b(context, str2, i3);
                    break;
                }
            case 5:
                try {
                    Class.forName(PushEventReceiver.class.getName());
                    Class.forName(HwReceiver.class.getName());
                    break;
                } catch (Throwable th4) {
                    String str3 = this.e + "_loadFailCount";
                    int i4 = a + 1;
                    a = i4;
                    b.b(context, str3, i4);
                    break;
                }
        }
        try {
            Method declaredMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", new Class[]{Context.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.g, new Object[]{context});
            e();
        } catch (Exception e2) {
            String str4 = this.e + "_loadFailCount";
            int i5 = a + 1;
            a = i5;
            b.b(context, str4, i5);
            Log.e(b, "attachBaseContext :" + e2);
        }
    }

    public void onCreate() {
        b();
        c();
        d();
        super.onCreate();
        this.g.onCreate();
    }
}
