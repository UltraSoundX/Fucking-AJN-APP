package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.utils.Data;
import java.util.List;

/* compiled from: WeiboAppManager */
public class i {
    private static final Uri a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static i b;
    private static a c = null;

    /* compiled from: WeiboAppManager */
    public static class a {
        private String a = "com.sina.weibo";
        private String b = "com.sina.weibo.SSOActivity";
        private int c;

        /* access modifiers changed from: private */
        public void a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            this.c = i;
        }

        public int b() {
            return this.c;
        }

        public String toString() {
            return "WeiboInfo: PackageName = " + this.a + ", supportApi = " + this.c;
        }
    }

    private i() {
    }

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                b = new i();
            }
            iVar = b;
        }
        return iVar;
    }

    public synchronized String b() {
        if (c == null) {
            c = c();
        }
        return c.a();
    }

    private a c() {
        boolean z = true;
        a d = d();
        a e = e();
        boolean z2 = d != null;
        if (e == null) {
            z = false;
        }
        if (!z2 || !z) {
            if (z2) {
                return d;
            }
            if (z) {
                return e;
            }
            return null;
        } else if (d.b() >= e.b()) {
            return d;
        } else {
            return e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cn.sharesdk.sina.weibo.i.a d() {
        /*
            r7 = this;
            r6 = 0
            android.content.Context r0 = com.mob.MobSDK.getContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r1 = a     // Catch:{ Exception -> 0x0075, all -> 0x008b }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0075, all -> 0x008b }
            if (r1 != 0) goto L_0x001c
            if (r1 == 0) goto L_0x001a
            r1.close()
        L_0x001a:
            r0 = r6
        L_0x001b:
            return r0
        L_0x001c:
            java.lang.String r0 = "support_api"
            int r2 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r0 = "package"
            int r3 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r0 = "sso_activity"
            int r4 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0095 }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0095 }
            if (r0 == 0) goto L_0x006e
            r0 = -1
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x0095 }
            int r0 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0064 }
            r2 = r0
        L_0x003e:
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x0095 }
            if (r4 <= 0) goto L_0x0047
            r1.getString(r4)     // Catch:{ Exception -> 0x0095 }
        L_0x0047:
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0095 }
            if (r0 != 0) goto L_0x006e
            boolean r0 = a(r3)     // Catch:{ Exception -> 0x0095 }
            if (r0 == 0) goto L_0x006e
            cn.sharesdk.sina.weibo.i$a r0 = new cn.sharesdk.sina.weibo.i$a     // Catch:{ Exception -> 0x0095 }
            r0.<init>()     // Catch:{ Exception -> 0x0095 }
            r0.a(r3)     // Catch:{ Exception -> 0x0095 }
            r0.a(r2)     // Catch:{ Exception -> 0x0095 }
            if (r1 == 0) goto L_0x001b
            r1.close()
            goto L_0x001b
        L_0x0064:
            r2 = move-exception
            com.mob.tools.log.NLog r5 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Exception -> 0x0095 }
            r5.d(r2)     // Catch:{ Exception -> 0x0095 }
            r2 = r0
            goto L_0x003e
        L_0x006e:
            if (r1 == 0) goto L_0x0073
            r1.close()
        L_0x0073:
            r0 = r6
            goto L_0x001b
        L_0x0075:
            r0 = move-exception
            r1 = r6
        L_0x0077:
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ all -> 0x0093 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0093 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0093 }
            r2.e(r0, r3)     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0073
            r1.close()
            goto L_0x0073
        L_0x008b:
            r0 = move-exception
            r1 = r6
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            throw r0
        L_0x0093:
            r0 = move-exception
            goto L_0x008d
        L_0x0095:
            r0 = move-exception
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.i.d():cn.sharesdk.sina.weibo.i$a");
    }

    private a e() {
        a aVar;
        a aVar2 = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = MobSDK.getContext().getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName))) {
                    aVar = b(resolveInfo.serviceInfo.applicationInfo.packageName);
                    if (aVar != null) {
                        if (aVar2 != null) {
                            if (aVar2.b() < aVar.b()) {
                            }
                        }
                        aVar2 = aVar;
                    }
                }
                aVar = aVar2;
                aVar2 = aVar;
            }
        }
        return aVar2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6 A[SYNTHETIC, Splitter:B:40:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0 A[SYNTHETIC, Splitter:B:47:0x00e0] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x00b6=Splitter:B:37:0x00b6, B:10:0x003a=Splitter:B:10:0x003a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cn.sharesdk.sina.weibo.i.a b(java.lang.String r10) {
        /*
            r9 = this;
            r8 = -1
            r0 = 0
            r7 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 == 0) goto L_0x000a
        L_0x0009:
            return r0
        L_0x000a:
            android.content.Context r1 = com.mob.MobSDK.getContext()     // Catch:{ NameNotFoundException -> 0x00f7, Exception -> 0x00b4, all -> 0x00db }
            r2 = 2
            android.content.Context r1 = r1.createPackageContext(r10, r2)     // Catch:{ NameNotFoundException -> 0x00f7, Exception -> 0x00b4, all -> 0x00db }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r2]     // Catch:{ NameNotFoundException -> 0x00f7, Exception -> 0x00b4, all -> 0x00db }
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ NameNotFoundException -> 0x00f7, Exception -> 0x00b4, all -> 0x00db }
            java.lang.String r2 = "weibo_for_sdk.json"
            java.io.InputStream r2 = r1.open(r2)     // Catch:{ NameNotFoundException -> 0x00f7, Exception -> 0x00b4, all -> 0x00db }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
        L_0x0026:
            r4 = 0
            r5 = 4096(0x1000, float:5.74E-42)
            int r4 = r2.read(r3, r4, r5)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            if (r4 == r8) goto L_0x005d
            java.lang.String r5 = new java.lang.String     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r6 = 0
            r5.<init>(r3, r6, r4)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r1.append(r5)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            goto L_0x0026
        L_0x0039:
            r1 = move-exception
        L_0x003a:
            com.mob.tools.log.NLog r3 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00f3 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f3 }
            r3.e(r1, r4)     // Catch:{ all -> 0x00f3 }
            if (r2 == 0) goto L_0x0009
            r2.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0009
        L_0x004e:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            r2.e(r1, r3)
            goto L_0x0009
        L_0x005d:
            java.lang.String r3 = r1.toString()     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            if (r3 != 0) goto L_0x006d
            boolean r3 = a(r10)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            if (r3 != 0) goto L_0x0082
        L_0x006d:
            if (r2 == 0) goto L_0x0009
            r2.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0009
        L_0x0073:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            r2.e(r1, r3)
            goto L_0x0009
        L_0x0082:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            java.lang.String r1 = r1.toString()     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r3.<init>(r1)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            java.lang.String r1 = "support_api"
            r4 = -1
            int r3 = r3.optInt(r1, r4)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            cn.sharesdk.sina.weibo.i$a r1 = new cn.sharesdk.sina.weibo.i$a     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r1.<init>()     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r1.a(r10)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            r1.a(r3)     // Catch:{ NameNotFoundException -> 0x0039, Exception -> 0x00f5 }
            if (r2 == 0) goto L_0x00a2
            r2.close()     // Catch:{ IOException -> 0x00a5 }
        L_0x00a2:
            r0 = r1
            goto L_0x0009
        L_0x00a5:
            r0 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            r2.e(r0, r3)
            goto L_0x00a2
        L_0x00b4:
            r1 = move-exception
            r2 = r0
        L_0x00b6:
            com.mob.tools.log.NLog r3 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00f3 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f3 }
            r3.e(r1, r4)     // Catch:{ all -> 0x00f3 }
            if (r2 == 0) goto L_0x0009
            r2.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x0009
        L_0x00cb:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            r2.e(r1, r3)
            goto L_0x0009
        L_0x00db:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x00de:
            if (r2 == 0) goto L_0x00e3
            r2.close()     // Catch:{ IOException -> 0x00e4 }
        L_0x00e3:
            throw r0
        L_0x00e4:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            r2.e(r1, r3)
            goto L_0x00e3
        L_0x00f3:
            r0 = move-exception
            goto L_0x00de
        L_0x00f5:
            r1 = move-exception
            goto L_0x00b6
        L_0x00f7:
            r1 = move-exception
            r2 = r0
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.i.b(java.lang.String):cn.sharesdk.sina.weibo.i$a");
    }

    public static boolean a(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        try {
            return a(MobSDK.getContext().getPackageManager().getPackageInfo(str, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    private static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature byteArray : signatureArr) {
            if (str.equals(Data.MD5(byteArray.toByteArray()))) {
                SSDKLog.b().d("check pass", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
