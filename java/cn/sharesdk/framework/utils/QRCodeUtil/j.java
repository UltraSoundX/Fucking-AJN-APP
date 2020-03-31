package cn.sharesdk.framework.utils.QRCodeUtil;

import cn.sharesdk.framework.utils.QRCodeUtil.a.b;
import cn.sharesdk.framework.utils.QRCodeUtil.a.c;
import cn.sharesdk.framework.utils.QRCodeUtil.a.f;
import java.util.Map;

/* compiled from: QRCodeWriter */
public final class j implements Writer {
    public c encode(String str, a aVar, int i, int i2) throws WriterException {
        return encode(str, aVar, i, i2, null);
    }

    public c encode(String str, a aVar, int i, int i2, Map<e, ?> map) throws WriterException {
        f fVar;
        int i3;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (aVar != a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + aVar);
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        } else {
            f fVar2 = f.L;
            if (map != null) {
                if (map.containsKey(e.ERROR_CORRECTION)) {
                    fVar2 = f.valueOf(map.get(e.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(e.MARGIN)) {
                    fVar = fVar2;
                    i3 = Integer.parseInt(map.get(e.MARGIN).toString());
                    return a(c.a(str, fVar, map), i, i2, i3);
                }
            }
            fVar = fVar2;
            i3 = 4;
            return a(c.a(str, fVar, map), i, i2, i3);
        }
    }

    private static c a(f fVar, int i, int i2, int i3) {
        b a = fVar.a();
        if (a == null) {
            throw new IllegalStateException();
        }
        int b = a.b();
        int a2 = a.a();
        int i4 = (i3 * 2) + b;
        int i5 = (i3 * 2) + a2;
        int max = Math.max(i, i4);
        int max2 = Math.max(i2, i5);
        int min = Math.min(max / i4, max2 / i5);
        int i6 = (max - (b * min)) / 2;
        int i7 = (max2 - (a2 * min)) / 2;
        c cVar = new c(max, max2);
        int i8 = i7;
        for (int i9 = 0; i9 < a2; i9++) {
            int i10 = 0;
            int i11 = i6;
            while (i10 < b) {
                if (a.a(i10, i9) == 1) {
                    cVar.a(i11, i8, min, min);
                }
                i10++;
                i11 += min;
            }
            i8 += min;
        }
        return cVar;
    }
}
