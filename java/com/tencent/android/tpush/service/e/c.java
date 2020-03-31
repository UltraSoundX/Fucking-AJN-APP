package com.tencent.android.tpush.service.e;

import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

/* compiled from: ProGuard */
public class c {
    public static String a() {
        String str = null;
        if (b.f() != null) {
            try {
                str = TpnsSecurity.getBusinessDeviceId(b.f());
            } catch (Exception e) {
                a.d("ServiceLogTag", ">>get deviceid err", e);
            }
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            return str;
        }
        a.i(Constants.ServiceLogTag, ">>> getDeviceId() > context == null");
        return str;
    }
}
