package cn.sharesdk.framework.utils.QRCodeUtil.a;

/* compiled from: MaskUtil */
final class d {
    static int a(b bVar) {
        return a(bVar, true) + a(bVar, false);
    }

    static int b(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a - 1; i2++) {
            byte[] bArr = c[i2];
            for (int i3 = 0; i3 < b - 1; i3++) {
                byte b2 = bArr[i3];
                if (b2 == bArr[i3 + 1] && b2 == c[i2 + 1][i3] && b2 == c[i2 + 1][i3 + 1]) {
                    i++;
                }
            }
        }
        return i * 3;
    }

    static int c(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            for (int i3 = 0; i3 < b; i3++) {
                byte[] bArr = c[i2];
                if (i3 + 6 < b && bArr[i3] == 1 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 1 && bArr[i3 + 3] == 1 && bArr[i3 + 4] == 1 && bArr[i3 + 5] == 0 && bArr[i3 + 6] == 1 && (a(bArr, i3 - 4, i3) || a(bArr, i3 + 7, i3 + 11))) {
                    i++;
                }
                if (i2 + 6 < a && c[i2][i3] == 1 && c[i2 + 1][i3] == 0 && c[i2 + 2][i3] == 1 && c[i2 + 3][i3] == 1 && c[i2 + 4][i3] == 1 && c[i2 + 5][i3] == 0 && c[i2 + 6][i3] == 1 && (a(c, i3, i2 - 4, i2) || a(c, i3, i2 + 7, i2 + 11))) {
                    i++;
                }
            }
        }
        return i * 40;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    static int d(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            byte[] bArr = c[i2];
            for (int i3 = 0; i3 < b; i3++) {
                if (bArr[i3] == 1) {
                    i++;
                }
            }
        }
        int a2 = bVar.a() * bVar.b();
        return ((Math.abs((i * 2) - a2) * 10) / a2) * 10;
    }

    static boolean a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 % 3) + (i5 & 1);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 % 3) + (i6 & 1)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + i);
        }
        if (i4 == 0) {
            return true;
        }
        return false;
    }

    private static int a(b bVar, boolean z) {
        int i;
        byte b;
        int b2 = z ? bVar.a() : bVar.b();
        int a = z ? bVar.b() : bVar.a();
        byte[][] c = bVar.c();
        int i2 = 0;
        int i3 = 0;
        while (i2 < b2) {
            byte b3 = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < a) {
                byte b4 = z ? c[i2][i4] : c[i4][i2];
                if (b4 == b3) {
                    b = b3;
                    i = i5 + 1;
                } else {
                    if (i5 >= 5) {
                        i3 += (i5 - 5) + 3;
                    }
                    i = 1;
                    b = b4;
                }
                i4++;
                i5 = i;
                b3 = b;
                i3 = i3;
            }
            if (i5 >= 5) {
                i3 += (i5 - 5) + 3;
            }
            i2++;
        }
        return i3;
    }
}
