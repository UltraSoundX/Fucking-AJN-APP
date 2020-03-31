package com.jg.ids.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import com.jg.ids.g;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.security.MessageDigest;

public final class d extends g {
    private String b = "";
    private Context c = null;

    public d(Context context) {
        super(context);
        this.c = context;
    }

    /* access modifiers changed from: protected */
    public final Intent a() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        return intent;
    }

    /* access modifiers changed from: protected */
    public final void a(IBinder iBinder) {
        try {
            a a = b.a(iBinder);
            String a2 = a(this.c, a, "AUID");
            String a3 = a(this.c, a, "OUID");
            String a4 = a(this.c, a, "DUID");
            a(a2);
            b(a4);
            c(a3);
            b();
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public final boolean d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (VERSION.SDK_INT >= 28) {
                if (packageInfo == null || packageInfo.getLongVersionCode() < 1) {
                    return false;
                }
                return true;
            } else if (packageInfo == null || packageInfo.versionCode <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    private String a(Context context, a aVar, String str) {
        if (context == null || aVar == null || !d(context)) {
            return "";
        }
        if (TextUtils.isEmpty(this.b)) {
            try {
                Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
                if (signatureArr != null && signatureArr.length > 0) {
                    try {
                        byte[] digest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
                        StringBuilder sb = new StringBuilder();
                        for (byte b2 : digest) {
                            sb.append(Integer.toHexString((b2 & DeviceInfos.NETWORK_TYPE_UNCONNECTED) | DeviceInfos.NETWORK_TYPE_UNCONNECTED).substring(1, 3));
                        }
                        this.b = sb.toString();
                    } catch (Throwable th) {
                        return "";
                    }
                }
            } catch (Throwable th2) {
                return "";
            }
        }
        return aVar.a(context.getPackageName(), this.b, str);
    }
}
