package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.libwebp;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;
import java.util.Map;

/* compiled from: TbsWizard */
class r {
    private Context a = null;
    private Context b = null;
    private String c = null;
    private String[] d = null;
    private DexLoader e = null;
    private String f = "TbsDexOpt";
    private String g = null;

    public r(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        boolean z;
        boolean z2;
        boolean z3;
        TbsLog.i("TbsWizard", "construction start...");
        if (context == null || ((context2 == null && TbsShareManager.getHostCorePathAppDefined() == null) || TextUtils.isEmpty(str) || strArr == null || strArr.length == 0)) {
            throw new Exception("TbsWizard paramter error:-1callerContext:" + context + "hostcontext" + context2 + "isEmpty" + TextUtils.isEmpty(str) + "dexfileList" + strArr);
        }
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (StubApp.getOrigApplicationContext(context2.getApplicationContext()) != null) {
            this.b = StubApp.getOrigApplicationContext(context2.getApplicationContext());
        } else {
            this.b = context2;
        }
        this.c = str;
        this.d = strArr;
        this.f = str2;
        for (int i = 0; i < this.d.length; i++) {
            TbsLog.i("TbsWizard", "#2 mDexFileList[" + i + "]: " + this.d[i]);
        }
        TbsLog.i("TbsWizard", "new DexLoader #2 libraryPath is " + str3 + " mCallerAppContext is " + this.a + " dexOutPutDir is " + str2);
        this.e = new DexLoader(str3, this.a, this.d, str2, QbSdk.n);
        System.currentTimeMillis();
        a(context);
        libwebp.loadWepLibraryIfNeed(context2, this.c);
        if ("com.nd.android.pandahome2".equals(this.a.getApplicationInfo().packageName)) {
            this.e.invokeStaticMethod("com.tencent.tbs.common.beacon.X5CoreBeaconUploader", "getInstance", new Class[]{Context.class}, this.a);
        }
        if (QbSdk.n != null) {
            try {
                z = TbsPVConfig.getInstance(this.a).getTbsCoreSandboxModeEnable();
            } catch (Throwable th) {
                z = false;
            }
            try {
                if ("true".equals(String.valueOf(QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX)))) {
                    z2 = true;
                    Map<String, Object> map = QbSdk.n;
                    String str4 = TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX;
                    if (z || !z2) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    map.put(str4, Boolean.valueOf(z3));
                    this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.n);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            z2 = false;
            Map<String, Object> map2 = QbSdk.n;
            String str42 = TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX;
            if (z) {
            }
            z3 = false;
            map2.put(str42, Boolean.valueOf(z3));
            this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.n);
        }
        int b2 = b(context);
        if (b2 < 0) {
            throw new Exception("TbsWizard init error: " + b2 + "; msg: " + this.g);
        }
        TbsLog.i("TbsWizard", "construction end...");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r4) {
        /*
            r3 = this;
            r1 = 0
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            if (r0 == 0) goto L_0x001b
            java.lang.String r2 = "check_tbs_validity"
            java.lang.Object r0 = r0.get(r2)
            boolean r2 = r0 instanceof java.lang.Boolean
            if (r2 == 0) goto L_0x001b
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
        L_0x0015:
            if (r0 == 0) goto L_0x001a
            com.tencent.smtt.utils.m.b(r4)
        L_0x001a:
            return
        L_0x001b:
            r0 = r1
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.r.a(android.content.Context):void");
    }

    public void a(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        this.a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (StubApp.getOrigApplicationContext(this.b.getApplicationContext()) != null) {
            this.b = StubApp.getOrigApplicationContext(this.b.getApplicationContext());
        }
        this.c = str;
        this.d = strArr;
        this.f = str2;
        libwebp.loadWepLibraryIfNeed(context2, this.c);
        if (QbSdk.n != null) {
            this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.n);
        }
        int b2 = b(context);
        if (b2 < 0) {
            throw new Exception("continueInit init error: " + b2 + "; msg: " + this.g);
        }
    }

    private int b(Context context) {
        Object invokeStaticMethod;
        int i;
        if (this.b != null || TbsShareManager.getHostCorePathAppDefined() == null) {
            TbsLog.i("TbsWizard", "initTesRuntimeEnvironment callerContext is " + context + " mHostContext is " + this.b + " mDexLoader is " + this.e + " mtbsInstallLocation is " + this.c + " mDexOptPath is " + this.f);
            invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class}, context, this.b, this.e, this.c, this.f, TbsConfig.TBS_SDK_VERSIONNAME, Integer.valueOf(43697), QbSdk.a());
        } else {
            invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class, String.class}, context, this.b, this.e, this.c, this.f, TbsConfig.TBS_SDK_VERSIONNAME, Integer.valueOf(43697), QbSdk.a(), TbsShareManager.getHostCorePathAppDefined());
        }
        if (invokeStaticMethod == null) {
            c();
            d();
            invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class}, context, this.b, this.e, this.c, this.f);
        }
        if (invokeStaticMethod == null) {
            i = -3;
        } else if (invokeStaticMethod instanceof Integer) {
            i = ((Integer) invokeStaticMethod).intValue();
        } else if (invokeStaticMethod instanceof Throwable) {
            TbsCoreLoadStat.getInstance().a(this.a, ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, (Throwable) invokeStaticMethod);
            i = -5;
        } else {
            i = -4;
        }
        if (i < 0) {
            Object invokeStaticMethod2 = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
            if (invokeStaticMethod2 instanceof Throwable) {
                Throwable th = (Throwable) invokeStaticMethod2;
                this.g = "#" + th.getMessage() + "; cause: " + th.getCause() + "; th: " + th;
            }
            if (invokeStaticMethod2 instanceof String) {
                this.g = (String) invokeStaticMethod2;
            }
        } else {
            this.g = null;
        }
        return i;
    }

    private void c() {
        this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "setTesSdkVersionName", new Class[]{String.class}, TbsConfig.TBS_SDK_VERSIONNAME);
    }

    private void d() {
        this.e.setStaticField("com.tencent.tbs.tbsshell.TBSShell", "VERSION", Integer.valueOf(43697));
    }

    public String a() {
        String str = null;
        Object invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMethod", new Class[]{Boolean.TYPE, String.class, String.class, Class[].class, Object[].class}, Boolean.valueOf(true), "com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        if (invokeStaticMethod == null) {
            invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        }
        if (invokeStaticMethod != null) {
            str = String.valueOf(invokeStaticMethod) + " ReaderPackName=" + TbsReaderView.gReaderPackName + " ReaderPackVersion=" + TbsReaderView.gReaderPackVersion;
        }
        return str == null ? "X5 core get nothing..." : str;
    }

    public boolean a(Context context, String str, String str2, Bundle bundle) {
        Object invokeStaticMethod = this.e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "installLocalQbApk", new Class[]{Context.class, String.class, String.class, Bundle.class}, context, str, str2, bundle);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    public DexLoader b() {
        return this.e;
    }
}
