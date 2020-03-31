package cn.sharesdk.framework.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.QRCodeUtil.WriterException;
import cn.sharesdk.framework.utils.QRCodeUtil.a;
import cn.sharesdk.framework.utils.QRCodeUtil.c;
import cn.sharesdk.framework.utils.QRCodeUtil.e;
import cn.sharesdk.framework.utils.QRCodeUtil.j;
import java.util.Hashtable;

/* compiled from: UrlToQRCode */
public class i {
    private static volatile i a = null;

    public static i a() {
        synchronized (i.class) {
            if (a == null) {
                synchronized (i.class) {
                    if (a == null) {
                        a = new i();
                    }
                }
            }
        }
        return a;
    }

    public static Bitmap a(String str, int i, int i2, String str2, String str3, String str4, int i3, int i4) {
        c cVar;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i < 0 || i2 < 0) {
            return null;
        }
        try {
            Hashtable hashtable = new Hashtable();
            if (!TextUtils.isEmpty(str2)) {
                hashtable.put(e.CHARACTER_SET, str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                hashtable.put(e.ERROR_CORRECTION, str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                e eVar = e.AZTEC_LAYERS;
                hashtable.put(e.MARGIN, str4);
            }
            try {
                cVar = new j().encode(str, a.QR_CODE, i, i2, hashtable);
            } catch (WriterException e) {
                e.printStackTrace();
                cVar = null;
            }
            int[] iArr = new int[(i * i2)];
            for (int i5 = 0; i5 < i2; i5++) {
                for (int i6 = 0; i6 < i; i6++) {
                    if (cVar.a(i6, i5)) {
                        iArr[(i5 * i) + i6] = i3;
                    } else {
                        iArr[(i5 * i) + i6] = i4;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
            return createBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
