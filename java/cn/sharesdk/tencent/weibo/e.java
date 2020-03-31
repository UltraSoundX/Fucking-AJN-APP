package cn.sharesdk.tencent.weibo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import cn.sharesdk.framework.authorize.d;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: TencentWeiboSSOProcessor */
public class e extends cn.sharesdk.framework.authorize.e {
    private c d;

    /* compiled from: TencentWeiboSSOProcessor */
    private static class a extends BroadcastReceiver {
        private e a;

        public a(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            this.a.a.finish();
            Bundle extras = intent.getExtras();
            if (extras.getBoolean("com.tencent.sso.AUTH_RESULT", false)) {
                this.a.a(extras);
            } else {
                this.a.b(extras);
            }
        }
    }

    public e(d dVar) {
        super(dVar);
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[Catch:{  }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r12 = this;
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x00f0 }
            int r0 = r12.a(r0)     // Catch:{ Throwable -> 0x00f0 }
            switch(r0) {
                case -2: goto L_0x0100;
                case -1: goto L_0x00da;
                default: goto L_0x000d;
            }
        L_0x000d:
            android.content.IntentFilter r1 = new android.content.IntentFilter     // Catch:{ Throwable -> 0x0120 }
            java.lang.String r0 = "com.tencent.sso.AUTH"
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0120 }
            java.lang.String r0 = "android.intent.category.DEFAULT"
            r1.addCategory(r0)     // Catch:{ Throwable -> 0x0120 }
            cn.sharesdk.tencent.weibo.e$a r2 = new cn.sharesdk.tencent.weibo.e$a     // Catch:{ Throwable -> 0x0120 }
            r2.<init>(r12)     // Catch:{ Throwable -> 0x0120 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x0116 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x0116 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ Throwable -> 0x0116 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x0116 }
            r0.unregisterReceiver(r2)     // Catch:{ Throwable -> 0x0116 }
        L_0x002f:
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x0120 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x0120 }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ Throwable -> 0x0120 }
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)     // Catch:{ Throwable -> 0x0120 }
            r0.registerReceiver(r2, r1)     // Catch:{ Throwable -> 0x0120 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00f0 }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r2 = r0 / r2
            java.util.Random r0 = new java.util.Random     // Catch:{ Throwable -> 0x00f0 }
            r0.<init>()     // Catch:{ Throwable -> 0x00f0 }
            int r0 = r0.nextInt()     // Catch:{ Throwable -> 0x00f0 }
            int r0 = java.lang.Math.abs(r0)     // Catch:{ Throwable -> 0x00f0 }
            long r4 = (long) r0     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r6 = r0.getPackageName()     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r7 = r12.b(r0)     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            android.content.Context r0 = r0.getContext()     // Catch:{ Throwable -> 0x00f0 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ Throwable -> 0x00f0 }
            r1 = 0
            android.content.pm.ApplicationInfo r1 = r0.getApplicationInfo(r6, r1)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.CharSequence r0 = r0.getApplicationLabel(r1)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r8 = r0.toString()     // Catch:{ Throwable -> 0x00f0 }
            byte[] r9 = r12.a(r2, r4)     // Catch:{ Throwable -> 0x00f0 }
            android.content.Intent r10 = new android.content.Intent     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "android.intent.action.VIEW"
            java.lang.String r1 = "TencentAuth://weibo"
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Throwable -> 0x00f0 }
            r10.<init>(r0, r1)     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.tencent.weibo.c r0 = r12.d     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x00f0 }
            if (r0 != 0) goto L_0x0130
            r0 = 0
        L_0x009a:
            java.lang.String r11 = "com.tencent.sso.APP_ID"
            r10.putExtra(r11, r0)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.TIMESTAMP"
            r10.putExtra(r0, r2)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.NONCE"
            r10.putExtra(r0, r4)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.SDK_VERSION"
            r1 = 1
            r10.putExtra(r0, r1)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.PACKAGE_NAME"
            r10.putExtra(r0, r6)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.ICON_MD5"
            r10.putExtra(r0, r7)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.APP_NAME"
            r10.putExtra(r0, r8)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.SIGNATURE"
            r10.putExtra(r0, r9)     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "com.tencent.sso.RESERVER"
            java.lang.String r1 = ""
            r10.putExtra(r0, r1)     // Catch:{ Throwable -> 0x00f0 }
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r10.setFlags(r0)     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            r0.startActivity(r10)     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            r0.finish()     // Catch:{ Throwable -> 0x00f0 }
        L_0x00d9:
            return
        L_0x00da:
            cn.sharesdk.framework.authorize.SSOListener r0 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            if (r0 == 0) goto L_0x00d9
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            r0.finish()     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "Weibo client version mis-match"
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00f0 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x00f0 }
            r1.onFailed(r2)     // Catch:{ Throwable -> 0x00f0 }
            goto L_0x00d9
        L_0x00f0:
            r0 = move-exception
            cn.sharesdk.framework.authorize.d r1 = r12.a
            r1.finish()
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c
            if (r1 == 0) goto L_0x00d9
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c
            r1.onFailed(r0)
            goto L_0x00d9
        L_0x0100:
            cn.sharesdk.framework.authorize.SSOListener r0 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            if (r0 == 0) goto L_0x00d9
            cn.sharesdk.framework.authorize.d r0 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            r0.finish()     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = "Weibo client install needed"
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            java.lang.Throwable r2 = new java.lang.Throwable     // Catch:{ Throwable -> 0x00f0 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x00f0 }
            r1.onFailed(r2)     // Catch:{ Throwable -> 0x00f0 }
            goto L_0x00d9
        L_0x0116:
            r0 = move-exception
            com.mob.tools.log.NLog r3 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ Throwable -> 0x0120 }
            r3.d(r0)     // Catch:{ Throwable -> 0x0120 }
            goto L_0x002f
        L_0x0120:
            r0 = move-exception
            cn.sharesdk.framework.authorize.d r1 = r12.a     // Catch:{ Throwable -> 0x00f0 }
            r1.finish()     // Catch:{ Throwable -> 0x00f0 }
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            if (r1 == 0) goto L_0x00d9
            cn.sharesdk.framework.authorize.SSOListener r1 = r12.c     // Catch:{ Throwable -> 0x00f0 }
            r1.onFailed(r0)     // Catch:{ Throwable -> 0x00f0 }
            goto L_0x00d9
        L_0x0130:
            cn.sharesdk.tencent.weibo.c r0 = r12.d     // Catch:{ Throwable -> 0x00f0 }
            java.lang.String r0 = r0.a     // Catch:{ Throwable -> 0x00f0 }
            long r0 = com.mob.tools.utils.ResHelper.parseLong(r0)     // Catch:{ Throwable -> 0x00f0 }
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.tencent.weibo.e.a():void");
    }

    private int a(Context context) throws Throwable {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.tencent.WBlog", 16);
            if (packageInfo == null || packageInfo.versionCode < 44 || packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("TencentAuth://weibo")), 65536).size() <= 0) {
                return -1;
            }
            return 0;
        } catch (NameNotFoundException e) {
            return -2;
        }
    }

    private String b(Context context) throws Throwable {
        X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(x509Certificate.getPublicKey().toString());
        stringBuffer.append(x509Certificate.getSerialNumber().toString());
        return Data.MD5(stringBuffer.toString());
    }

    private byte[] a(long j, long j2) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.d.a);
        stringBuffer.append(j);
        stringBuffer.append(j2);
        stringBuffer.append(1);
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(this.d.b.getBytes("UTF-8"), instance.getAlgorithm()));
        byte[] bytes = a.a(instance.doFinal(stringBuffer.toString().getBytes("UTF-8"))).getBytes();
        if (bytes != null) {
            return new b().a(bytes, "&-*)Wb5_U,[^!9'+".getBytes());
        }
        throw new Throwable("Generating signature failed");
    }

    public void a(int i, int i2, Intent intent) {
        super.a(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        this.d.e = bundle.getString("com.tencent.sso.WEIBO_NICK");
        a(new b().a(bundle.getByteArray("com.tencent.sso.ACCESS_TOKEN"), "&-*)Wb5_U,[^!9'+".getBytes(), 10));
    }

    private void a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            this.d.d = dataInputStream.readUTF();
            this.d.h = dataInputStream.readLong();
            this.d.i = dataInputStream.readUTF();
            this.d.f = dataInputStream.readUTF();
            this.d.j = dataInputStream.readUTF();
            this.d.k = dataInputStream.readUTF();
            if (this.c != null) {
                this.c.onComplete(null);
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th) {
                }
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                    return;
                } catch (Throwable th2) {
                    return;
                }
            } else {
                return;
            }
        } catch (Throwable th3) {
            return;
        }
        if (dataInputStream != null) {
            dataInputStream.close();
        }
    }

    /* access modifiers changed from: private */
    public void b(Bundle bundle) {
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            for (String str : bundle.keySet()) {
                hashMap.put(str, bundle.get(str));
            }
            this.c.onFailed(new Throwable(new Hashon().fromHashMap(hashMap)));
        }
    }
}
