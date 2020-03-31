package cn.sharesdk.framework.utils.QRCodeUtil.a;

import cn.sharesdk.framework.utils.QRCodeUtil.WriterException;
import cn.sharesdk.framework.utils.QRCodeUtil.b;
import cn.sharesdk.framework.utils.QRCodeUtil.d;
import cn.sharesdk.framework.utils.QRCodeUtil.e;
import cn.sharesdk.framework.utils.QRCodeUtil.f;
import cn.sharesdk.framework.utils.QRCodeUtil.g;
import cn.sharesdk.framework.utils.QRCodeUtil.i;
import cn.sharesdk.framework.utils.QRCodeUtil.k;
import cn.sharesdk.framework.utils.QRCodeUtil.l;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: Encoder */
public final class c {
    private static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private static int a(b bVar) {
        return d.a(bVar) + d.b(bVar) + d.c(bVar) + d.d(bVar);
    }

    public static f a(String str, f fVar, Map<e, ?> map) throws WriterException {
        l lVar;
        boolean z = true;
        String str2 = "ISO-8859-1";
        boolean z2 = map != null && map.containsKey(e.CHARACTER_SET);
        if (z2) {
            str2 = map.get(e.CHARACTER_SET).toString();
        }
        i a2 = a(str, str2);
        b bVar = new b();
        if (a2 == i.BYTE && z2) {
            d a3 = d.a(str2);
            if (a3 != null) {
                a(a3, bVar);
            }
        }
        if (map == null || !map.containsKey(e.GS1_FORMAT)) {
            z = false;
        }
        if (z && Boolean.valueOf(map.get(e.GS1_FORMAT).toString()).booleanValue()) {
            a(i.FNC1_FIRST_POSITION, bVar);
        }
        a(a2, bVar);
        b bVar2 = new b();
        a(str, a2, bVar2, str2);
        if (map == null || !map.containsKey(e.QR_VERSION)) {
            lVar = a(fVar, a2, bVar, bVar2);
        } else {
            lVar = l.a(Integer.parseInt(map.get(e.QR_VERSION).toString()));
            if (!a(a(a2, bVar, bVar2, lVar), lVar, fVar)) {
                throw new WriterException("Data too big for requested version");
            }
        }
        b bVar3 = new b();
        bVar3.a(bVar);
        a(a2 == i.BYTE ? bVar2.b() : str.length(), lVar, a2, bVar3);
        bVar3.a(bVar2);
        l.b a4 = lVar.a(fVar);
        int b = lVar.b() - a4.c();
        a(b, bVar3);
        b a5 = a(bVar3, lVar.b(), b, a4.b());
        f fVar2 = new f();
        fVar2.a(fVar);
        fVar2.a(a2);
        fVar2.a(lVar);
        int c = lVar.c();
        b bVar4 = new b(c, c);
        int a6 = a(a5, fVar, lVar, bVar4);
        fVar2.a(a6);
        e.a(a5, fVar, lVar, a6, bVar4);
        fVar2.a(bVar4);
        return fVar2;
    }

    private static l a(f fVar, i iVar, b bVar, b bVar2) throws WriterException {
        return a(a(iVar, bVar, bVar2, a(a(iVar, bVar, bVar2, l.a(1)), fVar)), fVar);
    }

    private static int a(i iVar, b bVar, b bVar2, l lVar) {
        return bVar.a() + iVar.a(lVar) + bVar2.a();
    }

    static int a(int i) {
        if (i < a.length) {
            return a[i];
        }
        return -1;
    }

    private static i a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && a(str)) {
            return i.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (a((int) charAt) == -1) {
                return i.BYTE;
            } else {
                z = true;
            }
        }
        if (z) {
            return i.ALPHANUMERIC;
        }
        if (z2) {
            return i.NUMERIC;
        }
        return i.BYTE;
    }

    private static boolean a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                byte b = bytes[i] & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
                if ((b < 129 || b > 159) && (b < 224 || b > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int a(b bVar, f fVar, l lVar, b bVar2) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        while (i3 < 8) {
            e.a(bVar, fVar, lVar, i3, bVar2);
            int a2 = a(bVar2);
            if (a2 < i) {
                i2 = i3;
            } else {
                a2 = i;
            }
            i3++;
            i = a2;
        }
        return i2;
    }

    private static l a(int i, f fVar) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            l a2 = l.a(i2);
            if (a(i, a2, fVar)) {
                return a2;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean a(int i, l lVar, f fVar) {
        return lVar.b() - lVar.a(fVar).c() >= (i + 7) / 8;
    }

    static void a(int i, b bVar) throws WriterException {
        int i2 = i * 8;
        if (bVar.a() > i2) {
            throw new WriterException("data bits cannot fit in the QR Code" + bVar.a() + " > " + i2);
        }
        for (int i3 = 0; i3 < 4 && bVar.a() < i2; i3++) {
            bVar.a(false);
        }
        int a2 = bVar.a() & 7;
        if (a2 > 0) {
            while (a2 < 8) {
                bVar.a(false);
                a2++;
            }
        }
        int b = i - bVar.b();
        for (int i4 = 0; i4 < b; i4++) {
            bVar.a((i4 & 1) == 0 ? ErrorCode.TPATCH_INSTALL_SUCCESS : 17, 8);
        }
        if (bVar.a() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        int i11 = i7 - i9;
        int i12 = i8 - i10;
        if (i11 != i12) {
            throw new WriterException("EC bytes mismatch");
        } else if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        } else {
            if (i != (i5 * (i10 + i12)) + ((i9 + i11) * i6)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i9;
                iArr2[0] = i11;
            } else {
                iArr[0] = i10;
                iArr2[0] = i12;
            }
        }
    }

    static b a(b bVar, int i, int i2, int i3) throws WriterException {
        if (bVar.b() != i2) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList<a> arrayList = new ArrayList<>(i3);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i4 < i3) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            a(i, i2, i3, i4, iArr, iArr2);
            int i8 = iArr[0];
            byte[] bArr = new byte[i8];
            bVar.a(i7 * 8, bArr, 0, i8);
            byte[] a2 = a(bArr, iArr2[0]);
            arrayList.add(new a(bArr, a2));
            int max = Math.max(i6, i8);
            i4++;
            i5 = Math.max(i5, a2.length);
            i6 = max;
            i7 = iArr[0] + i7;
        }
        if (i2 != i7) {
            throw new WriterException("Data bytes does not match offset");
        }
        b bVar2 = new b();
        for (int i9 = 0; i9 < i6; i9++) {
            for (a a3 : arrayList) {
                byte[] a4 = a3.a();
                if (i9 < a4.length) {
                    bVar2.a(a4[i9], 8);
                }
            }
        }
        for (int i10 = 0; i10 < i5; i10++) {
            for (a b : arrayList) {
                byte[] b2 = b.b();
                if (i10 < b2.length) {
                    bVar2.a(b2[i10], 8);
                }
            }
        }
        if (i == bVar2.b()) {
            return bVar2;
        }
        throw new WriterException("Interleaving error: " + i + " and " + bVar2.b() + " differ.");
    }

    static byte[] a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
        }
        new k(g.e).a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static void a(i iVar, b bVar) {
        bVar.a(iVar.a(), 4);
    }

    static void a(int i, l lVar, i iVar, b bVar) throws WriterException {
        int a2 = iVar.a(lVar);
        if (i >= (1 << a2)) {
            throw new WriterException(i + " is bigger than " + ((1 << a2) - 1));
        }
        bVar.a(i, a2);
    }

    static void a(String str, i iVar, b bVar, String str2) throws WriterException {
        switch (iVar) {
            case NUMERIC:
                a((CharSequence) str, bVar);
                return;
            case ALPHANUMERIC:
                b(str, bVar);
                return;
            case BYTE:
                a(str, bVar, str2);
                return;
            case KANJI:
                a(str, bVar);
                return;
            default:
                throw new WriterException("Invalid mode: " + iVar);
        }
    }

    static void a(CharSequence charSequence, b bVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            if (i + 2 < length) {
                bVar.a((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i + 2) - '0'), 10);
                i += 3;
            } else if (i + 1 < length) {
                bVar.a((charAt * 10) + (charSequence.charAt(i + 1) - '0'), 7);
                i += 2;
            } else {
                bVar.a(charAt, 4);
                i++;
            }
        }
    }

    static void b(CharSequence charSequence, b bVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a2 = a((int) charSequence.charAt(i));
            if (a2 == -1) {
                throw new WriterException();
            } else if (i + 1 < length) {
                int a3 = a((int) charSequence.charAt(i + 1));
                if (a3 == -1) {
                    throw new WriterException();
                }
                bVar.a((a2 * 45) + a3, 11);
                i += 2;
            } else {
                bVar.a(a2, 6);
                i++;
            }
        }
    }

    static void a(String str, b bVar, String str2) throws WriterException {
        try {
            for (byte a2 : str.getBytes(str2)) {
                bVar.a(a2, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    static void a(String str, b bVar) throws WriterException {
        int i;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            if (bytes.length % 2 != 0) {
                throw new WriterException("Kanji byte size not even");
            }
            int length = bytes.length - 1;
            for (int i2 = 0; i2 < length; i2 += 2) {
                byte b = ((bytes[i2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 8) | (bytes[i2 + 1] & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
                if (b >= 33088 && b <= 40956) {
                    i = b - 33088;
                } else if (b < 57408 || b > 60351) {
                    i = -1;
                } else {
                    i = b - 49472;
                }
                if (i == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bVar.a((i & 255) + ((i >> 8) * 192), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException((Throwable) e);
        }
    }

    private static void a(d dVar, b bVar) {
        bVar.a(i.ECI.a(), 4);
        bVar.a(dVar.a(), 8);
    }
}
