package com.tencent.android.tpush.encrypt;

import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.e.i;

/* compiled from: ProGuard */
public class Rijndael {
    public static String encrypt(String str) {
        if (!i.b(str)) {
            String oiSymmetryEncrypt2 = TpnsSecurity.oiSymmetryEncrypt2(str);
            int i = 0;
            while (i < 3) {
                if (!"failed".equals(oiSymmetryEncrypt2)) {
                    return oiSymmetryEncrypt2;
                }
                i++;
                oiSymmetryEncrypt2 = TpnsSecurity.oiSymmetryEncrypt2(str);
            }
        }
        return "";
    }

    public static String decrypt(String str) {
        if (!i.b(str)) {
            String oiSymmetryDecrypt2 = TpnsSecurity.oiSymmetryDecrypt2(str);
            int i = 0;
            while (i < 3) {
                if (!"failed".equals(oiSymmetryDecrypt2)) {
                    return oiSymmetryDecrypt2;
                }
                i++;
                oiSymmetryDecrypt2 = TpnsSecurity.oiSymmetryDecrypt2(str);
            }
        }
        return "";
    }
}
