package cn.sharesdk.sina.weibo.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.ColorStateList;
import android.util.StateSet;
import com.mob.tools.utils.Data;

/* compiled from: Utils */
public class a {
    public static ColorStateList a(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[]{16842908}, StateSet.WILD_CARD}, new int[]{i2, i2, i2, i});
    }

    public static String a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature byteArray : packageInfo.signatures) {
                byte[] byteArray2 = byteArray.toByteArray();
                if (byteArray2 != null) {
                    return Data.MD5(byteArray2);
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r7v3, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r11) {
        /*
            r6 = 64
            r5 = 1
            r1 = 0
            java.lang.String r0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
            char[] r8 = r0.toCharArray()
            int r0 = r11.length
            int r0 = r0 + 2
            int r0 = r0 / 3
            int r0 = r0 * 4
            byte[] r9 = new byte[r0]
            r0 = r1
            r2 = r1
        L_0x0015:
            int r3 = r11.length
            if (r2 >= r3) goto L_0x0072
            byte r3 = r11[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            int r4 = r2 + 1
            int r7 = r11.length
            if (r4 >= r7) goto L_0x0076
            int r4 = r2 + 1
            byte r4 = r11[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r3 = r3 | r4
            r4 = r5
        L_0x002b:
            int r3 = r3 << 8
            int r7 = r2 + 2
            int r10 = r11.length
            if (r7 >= r10) goto L_0x0073
            int r7 = r2 + 2
            byte r7 = r11[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r3 = r3 | r7
            r7 = r3
            r3 = r5
        L_0x003b:
            int r10 = r0 + 3
            if (r3 == 0) goto L_0x006e
            r3 = r7 & 63
        L_0x0041:
            char r3 = r8[r3]
            byte r3 = (byte) r3
            r9[r10] = r3
            int r7 = r7 >> 6
            int r10 = r0 + 2
            if (r4 == 0) goto L_0x0070
            r3 = r7 & 63
        L_0x004e:
            char r3 = r8[r3]
            byte r3 = (byte) r3
            r9[r10] = r3
            int r3 = r7 >> 6
            int r4 = r0 + 1
            r7 = r3 & 63
            char r7 = r8[r7]
            byte r7 = (byte) r7
            r9[r4] = r7
            int r3 = r3 >> 6
            int r4 = r0 + 0
            r3 = r3 & 63
            char r3 = r8[r3]
            byte r3 = (byte) r3
            r9[r4] = r3
            int r2 = r2 + 3
            int r0 = r0 + 4
            goto L_0x0015
        L_0x006e:
            r3 = r6
            goto L_0x0041
        L_0x0070:
            r3 = r6
            goto L_0x004e
        L_0x0072:
            return r9
        L_0x0073:
            r7 = r3
            r3 = r1
            goto L_0x003b
        L_0x0076:
            r4 = r1
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.sdk.a.a(byte[]):byte[]");
    }
}
