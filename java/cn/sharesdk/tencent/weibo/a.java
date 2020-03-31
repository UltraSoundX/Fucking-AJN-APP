package cn.sharesdk.tencent.weibo;

/* compiled from: Base64 */
public class a {
    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i += 3) {
            stringBuffer.append(a(bArr, i));
        }
        return stringBuffer.toString();
    }

    protected static char[] a(byte[] bArr, int i) {
        int i2;
        int length = (bArr.length - i) - 1;
        if (length >= 2) {
            i2 = 2;
        } else {
            i2 = length;
        }
        int i3 = 0;
        for (int i4 = 0; i4 <= i2; i4++) {
            byte b = bArr[i + i4];
            i3 += (b < 0 ? b + 256 : b) << ((2 - i4) * 8);
        }
        char[] cArr = new char[4];
        for (int i5 = 0; i5 < 4; i5++) {
            cArr[i5] = a((i3 >>> ((3 - i5) * 6)) & 63);
        }
        if (length < 1) {
            cArr[2] = '=';
        }
        if (length < 2) {
            cArr[3] = '=';
        }
        return cArr;
    }

    protected static char a(int i) {
        if (i >= 0 && i <= 25) {
            return (char) (i + 65);
        }
        if (i >= 26 && i <= 51) {
            return (char) ((i - 26) + 97);
        }
        if (i >= 52 && i <= 61) {
            return (char) ((i - 52) + 48);
        }
        if (i == 62) {
            return '+';
        }
        if (i == 63) {
            return '/';
        }
        return '?';
    }
}
