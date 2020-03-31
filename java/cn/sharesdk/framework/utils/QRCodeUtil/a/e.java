package cn.sharesdk.framework.utils.QRCodeUtil.a;

import cn.sharesdk.framework.utils.QRCodeUtil.WriterException;
import cn.sharesdk.framework.utils.QRCodeUtil.b;
import cn.sharesdk.framework.utils.QRCodeUtil.f;
import cn.sharesdk.framework.utils.QRCodeUtil.l;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: MatrixUtil */
final class e {
    private static final int[][] a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, -1}, new int[]{6, 30, 54, 78, 102, ErrorCode.PV_UPLOAD_ERROR, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, ErrorCode.NEEDDOWNLOAD_3, -1}, new int[]{6, 34, 62, 90, 118, ErrorCode.NEEDDOWNLOAD_7, -1}, new int[]{6, 30, 54, 78, 102, ErrorCode.PV_UPLOAD_ERROR, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, ErrorCode.STARTDOWNLOAD_3}, new int[]{6, 26, 54, 82, 110, 138, ErrorCode.STARTDOWNLOAD_7}, new int[]{6, 30, 58, 86, 114, ErrorCode.NEEDDOWNLOAD_3, ErrorCode.NEEDDOWNLOAD_TRUE}};
    private static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    static void a(b bVar) {
        bVar.a(-1);
    }

    static void a(b bVar, f fVar, l lVar, int i, b bVar2) throws WriterException {
        a(bVar2);
        a(lVar, bVar2);
        a(fVar, i, bVar2);
        b(lVar, bVar2);
        a(bVar, i, bVar2);
    }

    static void a(l lVar, b bVar) throws WriterException {
        d(bVar);
        c(bVar);
        c(lVar, bVar);
        b(bVar);
    }

    static void a(f fVar, int i, b bVar) throws WriterException {
        b bVar2 = new b();
        a(fVar, i, bVar2);
        for (int i2 = 0; i2 < bVar2.a(); i2++) {
            boolean a2 = bVar2.a((bVar2.a() - 1) - i2);
            int[] iArr = d[i2];
            bVar.a(iArr[0], iArr[1], a2);
            if (i2 < 8) {
                bVar.a((bVar.b() - i2) - 1, 8, a2);
            } else {
                bVar.a(8, (bVar.a() - 7) + (i2 - 8), a2);
            }
        }
    }

    static void b(l lVar, b bVar) throws WriterException {
        if (lVar.a() >= 7) {
            b bVar2 = new b();
            a(lVar, bVar2);
            int i = 17;
            int i2 = 0;
            while (i2 < 6) {
                int i3 = i;
                for (int i4 = 0; i4 < 3; i4++) {
                    boolean a2 = bVar2.a(i3);
                    i3--;
                    bVar.a(i2, (bVar.a() - 11) + i4, a2);
                    bVar.a((bVar.a() - 11) + i4, i2, a2);
                }
                i2++;
                i = i3;
            }
        }
    }

    static void a(b bVar, int i, b bVar2) throws WriterException {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        int b2 = bVar2.b() - 1;
        int a2 = bVar2.a() - 1;
        int i6 = -1;
        int i7 = 0;
        while (b2 > 0) {
            if (b2 == 6) {
                i3 = a2;
                i2 = b2 - 1;
                i4 = i7;
            } else {
                i3 = a2;
                i2 = b2;
                i4 = i7;
            }
            while (i3 >= 0 && i3 < bVar2.a()) {
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i2 - i8;
                    if (b((int) bVar2.a(i9, i3))) {
                        if (i4 < bVar.a()) {
                            boolean a3 = bVar.a(i4);
                            i5 = i4 + 1;
                            z = a3;
                        } else {
                            i5 = i4;
                            z = false;
                        }
                        if (i != -1 && d.a(i, i9, i3)) {
                            if (!z) {
                                z = true;
                            } else {
                                z = false;
                            }
                        }
                        bVar2.a(i9, i3, z);
                        i4 = i5;
                    }
                }
                i3 += i6;
            }
            i6 = -i6;
            b2 = i2 - 2;
            i7 = i4;
            a2 = i3 + i6;
        }
        if (i7 != bVar.a()) {
            throw new WriterException("Not all bits consumed: " + i7 + '/' + bVar.a());
        }
    }

    static int a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    static int a(int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int a2 = a(i2);
        int i3 = i << (a2 - 1);
        while (a(i3) >= a2) {
            i3 ^= i2 << (a(i3) - a2);
        }
        return i3;
    }

    static void a(f fVar, int i, b bVar) throws WriterException {
        if (!f.b(i)) {
            throw new WriterException("Invalid mask pattern");
        }
        int a2 = (fVar.a() << 3) | i;
        bVar.a(a2, 5);
        bVar.a(a(a2, 1335), 10);
        b bVar2 = new b();
        bVar2.a(21522, 15);
        bVar.b(bVar2);
        if (bVar.a() != 15) {
            throw new WriterException("should not happen but we got: " + bVar.a());
        }
    }

    static void a(l lVar, b bVar) throws WriterException {
        bVar.a(lVar.a(), 6);
        bVar.a(a(lVar.a(), 7973), 12);
        if (bVar.a() != 18) {
            throw new WriterException("should not happen but we got: " + bVar.a());
        }
    }

    private static boolean b(int i) {
        return i == -1;
    }

    private static void b(b bVar) {
        for (int i = 8; i < bVar.b() - 8; i++) {
            int i2 = (i + 1) % 2;
            if (b((int) bVar.a(i, 6))) {
                bVar.a(i, 6, i2);
            }
            if (b((int) bVar.a(6, i))) {
                bVar.a(6, i, i2);
            }
        }
    }

    private static void c(b bVar) throws WriterException {
        if (bVar.a(8, bVar.a() - 8) == 0) {
            throw new WriterException();
        }
        bVar.a(8, bVar.a() - 8, 1);
    }

    private static void a(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            if (!b((int) bVar.a(i + i3, i2))) {
                throw new WriterException();
            }
            bVar.a(i + i3, i2, 0);
        }
    }

    private static void b(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            if (!b((int) bVar.a(i, i2 + i3))) {
                throw new WriterException();
            }
            bVar.a(i, i2 + i3, 0);
        }
    }

    private static void c(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                bVar.a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    private static void d(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                bVar.a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    private static void d(b bVar) throws WriterException {
        int length = a[0].length;
        d(0, 0, bVar);
        d(bVar.b() - length, 0, bVar);
        d(0, bVar.b() - length, bVar);
        a(0, 7, bVar);
        a(bVar.b() - 8, 7, bVar);
        a(0, bVar.b() - 8, bVar);
        b(7, 0, bVar);
        b((bVar.a() - 7) - 1, 0, bVar);
        b(7, bVar.a() - 7, bVar);
    }

    private static void c(l lVar, b bVar) {
        if (lVar.a() >= 2) {
            int[] iArr = c[lVar.a() - 1];
            for (int i : iArr) {
                if (i >= 0) {
                    for (int i2 : iArr) {
                        if (i2 >= 0 && b((int) bVar.a(i2, i))) {
                            c(i2 - 2, i - 2, bVar);
                        }
                    }
                }
            }
        }
    }
}
