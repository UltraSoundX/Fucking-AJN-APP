package com.b.a.d;

import com.b.a.b.e;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* compiled from: JsonReader */
public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        e.a = new e() {
            public void a(a aVar) throws IOException {
                if (aVar instanceof com.b.a.b.a.e) {
                    ((com.b.a.b.a.e) aVar).o();
                    return;
                }
                int i = aVar.a;
                if (i == 0) {
                    i = aVar.r();
                }
                if (i == 13) {
                    aVar.a = 9;
                } else if (i == 12) {
                    aVar.a = 8;
                } else if (i == 14) {
                    aVar.a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + aVar.f() + aVar.x());
                }
            }
        };
    }

    public a(Reader reader) {
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + x());
    }

    public void b() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 4) {
            this.n--;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + x());
    }

    public void c() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 1) {
            a(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + x());
    }

    public void d() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + x());
    }

    public boolean e() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        return (i2 == 2 || i2 == 4) ? false : true;
    }

    public b f() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        switch (i2) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    public int r() throws IOException {
        int i2 = this.m[this.n - 1];
        if (i2 == 1) {
            this.m[this.n - 1] = 2;
        } else if (i2 == 2) {
            switch (b(true)) {
                case 44:
                    break;
                case 59:
                    v();
                    break;
                case 93:
                    this.a = 4;
                    return 4;
                default:
                    throw b("Unterminated array");
            }
        } else if (i2 == 3 || i2 == 5) {
            this.m[this.n - 1] = 4;
            if (i2 == 5) {
                switch (b(true)) {
                    case 44:
                        break;
                    case 59:
                        v();
                        break;
                    case ErrorCode.DOWNLOAD_THROWABLE /*125*/:
                        this.a = 2;
                        return 2;
                    default:
                        throw b("Unterminated object");
                }
            }
            int b2 = b(true);
            switch (b2) {
                case 34:
                    this.a = 13;
                    return 13;
                case 39:
                    v();
                    this.a = 12;
                    return 12;
                case ErrorCode.DOWNLOAD_THROWABLE /*125*/:
                    if (i2 != 5) {
                        this.a = 2;
                        return 2;
                    }
                    throw b("Expected name");
                default:
                    v();
                    this.f--;
                    if (a((char) b2)) {
                        this.a = 14;
                        return 14;
                    }
                    throw b("Expected name");
            }
        } else if (i2 == 4) {
            this.m[this.n - 1] = 5;
            switch (b(true)) {
                case 58:
                    break;
                case 61:
                    v();
                    if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                        this.f++;
                        break;
                    }
                default:
                    throw b("Expected ':'");
            }
        } else if (i2 == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i2 == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            v();
            this.f--;
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (b(true)) {
            case 34:
                this.a = 9;
                return 9;
            case 39:
                v();
                this.a = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.a = 3;
                return 3;
            case 93:
                if (i2 == 1) {
                    this.a = 4;
                    return 4;
                }
                break;
            case ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /*123*/:
                this.a = 1;
                return 1;
            default:
                this.f--;
                int o2 = o();
                if (o2 != 0) {
                    return o2;
                }
                int s = s();
                if (s != 0) {
                    return s;
                }
                if (!a(this.e[this.f])) {
                    throw b("Expected value");
                }
                v();
                this.a = 10;
                return 10;
        }
        if (i2 == 1 || i2 == 2) {
            v();
            this.f--;
            this.a = 7;
            return 7;
        }
        throw b("Unexpected value");
    }

    private int o() throws IOException {
        String str;
        String str2;
        int i2;
        char c2 = this.e[this.f];
        if (c2 == 't' || c2 == 'T') {
            str = "true";
            str2 = "TRUE";
            i2 = 5;
        } else if (c2 == 'f' || c2 == 'F') {
            str = "false";
            str2 = "FALSE";
            i2 = 6;
        } else if (c2 != 'n' && c2 != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i2 = 7;
        }
        int length = str.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f + i3 >= this.g && !b(i3 + 1)) {
                return 0;
            }
            char c3 = this.e[this.f + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i2;
        return i2;
    }

    private int s() throws IOException {
        char c2;
        char c3;
        boolean z;
        boolean z2;
        char[] cArr = this.e;
        int i2 = this.f;
        long j2 = 0;
        boolean z3 = false;
        boolean z4 = true;
        char c4 = 0;
        int i3 = 0;
        int i4 = this.g;
        int i5 = i2;
        while (true) {
            if (i5 + i3 == i4) {
                if (i3 == cArr.length) {
                    return 0;
                }
                if (b(i3 + 1)) {
                    i5 = this.f;
                    i4 = this.g;
                }
            }
            c2 = cArr[i5 + i3];
            switch (c2) {
                case '+':
                    if (c4 != 5) {
                        return 0;
                    }
                    c3 = 6;
                    z = z4;
                    z2 = z3;
                    continue;
                case '-':
                    if (c4 == 0) {
                        c3 = 1;
                        boolean z5 = z4;
                        z2 = true;
                        z = z5;
                        continue;
                    } else if (c4 == 5) {
                        c3 = 6;
                        z = z4;
                        z2 = z3;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (c4 != 2) {
                        return 0;
                    }
                    c3 = 3;
                    z = z4;
                    z2 = z3;
                    continue;
                case 'E':
                case 'e':
                    if (c4 != 2 && c4 != 4) {
                        return 0;
                    }
                    c3 = 5;
                    z = z4;
                    z2 = z3;
                    continue;
                default:
                    if (c2 >= '0' && c2 <= '9') {
                        if (c4 != 1 && c4 != 0) {
                            if (c4 != 2) {
                                if (c4 != 3) {
                                    if (c4 != 5 && c4 != 6) {
                                        c3 = c4;
                                        z = z4;
                                        z2 = z3;
                                        break;
                                    } else {
                                        c3 = 7;
                                        z = z4;
                                        z2 = z3;
                                        break;
                                    }
                                } else {
                                    c3 = 4;
                                    z = z4;
                                    z2 = z3;
                                    break;
                                }
                            } else if (j2 != 0) {
                                long j3 = (10 * j2) - ((long) (c2 - '0'));
                                boolean z6 = (j2 > -922337203685477580L || (j2 == -922337203685477580L && j3 < j2)) & z4;
                                z2 = z3;
                                j2 = j3;
                                char c5 = c4;
                                z = z6;
                                c3 = c5;
                                break;
                            } else {
                                return 0;
                            }
                        } else {
                            j2 = (long) (-(c2 - '0'));
                            c3 = 2;
                            z = z4;
                            z2 = z3;
                            continue;
                        }
                    } else {
                        break;
                    }
                    break;
            }
            i3++;
            z3 = z2;
            z4 = z;
            c4 = c3;
        }
        if (a(c2)) {
            return 0;
        }
        if (c4 == 2 && z4 && (j2 != Long.MIN_VALUE || z3)) {
            if (!z3) {
                j2 = -j2;
            }
            this.j = j2;
            this.f += i3;
            this.a = 15;
            return 15;
        } else if (c4 != 2 && c4 != 4 && c4 != 7) {
            return 0;
        } else {
            this.k = i3;
            this.a = 16;
            return 16;
        }
    }

    private boolean a(char c2) throws IOException {
        switch (c2) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /*123*/:
            case ErrorCode.DOWNLOAD_THROWABLE /*125*/:
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                v();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() throws IOException {
        String b2;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 14) {
            b2 = t();
        } else if (i2 == 12) {
            b2 = b('\'');
        } else if (i2 == 13) {
            b2 = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + x());
        }
        this.a = 0;
        this.o[this.n - 1] = b2;
        return b2;
    }

    public String h() throws IOException {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 10) {
            str = t();
        } else if (i2 == 8) {
            str = b('\'');
        } else if (i2 == 9) {
            str = b('\"');
        } else if (i2 == 11) {
            str = this.l;
            this.l = null;
        } else if (i2 == 15) {
            str = Long.toString(this.j);
        } else if (i2 == 16) {
            str = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + x());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    public boolean i() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 5) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + f() + x());
        }
    }

    public void j() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + x());
    }

    public double k() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9) {
            this.l = b(i2 == 8 ? '\'' : '\"');
        } else if (i2 == 10) {
            this.l = t();
        } else if (i2 != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        }
        throw new d("JSON forbids NaN and infinities: " + parseDouble + x());
    }

    public long l() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = t();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException e2) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j2 = (long) parseDouble;
        if (((double) j2) != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.l + x());
        }
        this.l = null;
        this.a = 0;
        int[] iArr3 = this.p;
        int i5 = this.n - 1;
        iArr3[i5] = iArr3[i5] + 1;
        return j2;
    }

    private String b(char c2) throws IOException {
        char[] cArr = this.e;
        StringBuilder sb = new StringBuilder();
        do {
            int i2 = this.f;
            int i3 = this.g;
            int i4 = i2;
            while (i4 < i3) {
                int i5 = i4 + 1;
                char c3 = cArr[i4];
                if (c3 == c2) {
                    this.f = i5;
                    sb.append(cArr, i2, (i5 - i2) - 1);
                    return sb.toString();
                }
                if (c3 == '\\') {
                    this.f = i5;
                    sb.append(cArr, i2, (i5 - i2) - 1);
                    sb.append(y());
                    i2 = this.f;
                    i3 = this.g;
                    i5 = i2;
                } else if (c3 == 10) {
                    this.h++;
                    this.i = i5;
                }
                i4 = i5;
            }
            sb.append(cArr, i2, i4 - i2);
            this.f = i4;
        } while (b(1));
        throw b("Unterminated string");
    }

    private String t() throws IOException {
        String sb;
        StringBuilder sb2 = null;
        int i2 = 0;
        while (true) {
            if (this.f + i2 < this.g) {
                switch (this.e[this.f + i2]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /*123*/:
                    case ErrorCode.DOWNLOAD_THROWABLE /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        v();
                        break;
                    default:
                        i2++;
                        continue;
                }
            } else if (i2 >= this.e.length) {
                if (sb2 == null) {
                    sb2 = new StringBuilder();
                }
                sb2.append(this.e, this.f, i2);
                this.f = i2 + this.f;
                if (!b(1)) {
                    i2 = 0;
                } else {
                    i2 = 0;
                }
            } else if (b(i2 + 1)) {
            }
        }
        if (sb2 == null) {
            sb = new String(this.e, this.f, i2);
        } else {
            sb2.append(this.e, this.f, i2);
            sb = sb2.toString();
        }
        this.f = i2 + this.f;
        return sb;
    }

    private void c(char c2) throws IOException {
        char[] cArr = this.e;
        do {
            int i2 = this.f;
            int i3 = this.g;
            int i4 = i2;
            while (i4 < i3) {
                int i5 = i4 + 1;
                char c3 = cArr[i4];
                if (c3 == c2) {
                    this.f = i5;
                    return;
                }
                if (c3 == '\\') {
                    this.f = i5;
                    y();
                    i5 = this.f;
                    i3 = this.g;
                } else if (c3 == 10) {
                    this.h++;
                    this.i = i5;
                }
                i4 = i5;
            }
            this.f = i4;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void u() throws IOException {
        do {
            int i2 = 0;
            while (this.f + i2 < this.g) {
                switch (this.e[this.f + i2]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED /*123*/:
                    case ErrorCode.DOWNLOAD_THROWABLE /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        v();
                        break;
                    default:
                        i2++;
                }
                this.f = i2 + this.f;
                return;
            }
            this.f = i2 + this.f;
        } while (b(1));
    }

    public int m() throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            int i3 = (int) this.j;
            if (this.j != ((long) i3)) {
                throw new NumberFormatException("Expected an int but was " + this.j + x());
            }
            this.a = 0;
            int[] iArr = this.p;
            int i4 = this.n - 1;
            iArr[i4] = iArr[i4] + 1;
            return i3;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = t();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i5 = this.n - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException e2) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        int i6 = (int) parseDouble;
        if (((double) i6) != parseDouble) {
            throw new NumberFormatException("Expected an int but was " + this.l + x());
        }
        this.l = null;
        this.a = 0;
        int[] iArr3 = this.p;
        int i7 = this.n - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public void close() throws IOException {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() throws IOException {
        int i2 = 0;
        do {
            int i3 = this.a;
            if (i3 == 0) {
                i3 = r();
            }
            if (i3 == 3) {
                a(1);
                i2++;
            } else if (i3 == 1) {
                a(3);
                i2++;
            } else if (i3 == 4) {
                this.n--;
                i2--;
            } else if (i3 == 2) {
                this.n--;
                i2--;
            } else if (i3 == 14 || i3 == 10) {
                u();
            } else if (i3 == 8 || i3 == 12) {
                c('\'');
            } else if (i3 == 9 || i3 == 13) {
                c('\"');
            } else if (i3 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i2 != 0);
        int[] iArr = this.p;
        int i4 = this.n - 1;
        iArr[i4] = iArr[i4] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i2) {
        if (this.n == this.m.length) {
            int[] iArr = new int[(this.n * 2)];
            int[] iArr2 = new int[(this.n * 2)];
            String[] strArr = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, iArr, 0, this.n);
            System.arraycopy(this.p, 0, iArr2, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr;
            this.p = iArr2;
            this.o = strArr;
        }
        int[] iArr3 = this.m;
        int i3 = this.n;
        this.n = i3 + 1;
        iArr3[i3] = i2;
    }

    private boolean b(int i2) throws IOException {
        char[] cArr = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(cArr, this.f, cArr, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(cArr, this.g, cArr.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g = read + this.g;
            if (this.h == 0 && this.i == 0 && this.g > 0 && cArr[0] == 65279) {
                this.f++;
                this.i++;
                i2++;
            }
        } while (this.g < i2);
        return true;
    }

    private int b(boolean z) throws IOException {
        char[] cArr = this.e;
        int i2 = this.f;
        int i3 = this.g;
        while (true) {
            if (i2 == i3) {
                this.f = i2;
                if (b(1)) {
                    i2 = this.f;
                    i3 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + x());
                }
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == 10) {
                this.h++;
                this.i = i4;
                i2 = i4;
            } else if (c2 == ' ' || c2 == 13) {
                i2 = i4;
            } else if (c2 == 9) {
                i2 = i4;
            } else if (c2 == '/') {
                this.f = i4;
                if (i4 == i3) {
                    this.f--;
                    boolean b2 = b(2);
                    this.f++;
                    if (!b2) {
                        return c2;
                    }
                }
                v();
                switch (cArr[this.f]) {
                    case '*':
                        this.f++;
                        if (a("*/")) {
                            i2 = this.f + 2;
                            i3 = this.g;
                            break;
                        } else {
                            throw b("Unterminated comment");
                        }
                    case '/':
                        this.f++;
                        w();
                        i2 = this.f;
                        i3 = this.g;
                        break;
                    default:
                        return c2;
                }
            } else if (c2 == '#') {
                this.f = i4;
                v();
                w();
                i2 = this.f;
                i3 = this.g;
            } else {
                this.f = i4;
                return c2;
            }
        }
    }

    private void v() throws IOException {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void w() throws IOException {
        char c2;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i2 = this.f;
                this.f = i2 + 1;
                c2 = cArr[i2];
                if (c2 == 10) {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c2 != 13);
    }

    private boolean a(String str) throws IOException {
        while (true) {
            if (this.f + str.length() > this.g && !b(str.length())) {
                return false;
            }
            if (this.e[this.f] == 10) {
                this.h++;
                this.i = this.f + 1;
            } else {
                int i2 = 0;
                while (i2 < str.length()) {
                    if (this.e[this.f + i2] == str.charAt(i2)) {
                        i2++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + x();
    }

    /* access modifiers changed from: private */
    public String x() {
        return " at line " + (this.h + 1) + " column " + ((this.f - this.i) + 1) + " path " + p();
    }

    public String p() {
        StringBuilder append = new StringBuilder().append('$');
        int i2 = this.n;
        for (int i3 = 0; i3 < i2; i3++) {
            switch (this.m[i3]) {
                case 1:
                case 2:
                    append.append('[').append(this.p[i3]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    append.append('.');
                    if (this.o[i3] == null) {
                        break;
                    } else {
                        append.append(this.o[i3]);
                        break;
                    }
            }
        }
        return append.toString();
    }

    private char y() throws IOException {
        int i2;
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i3 = this.f;
            this.f = i3 + 1;
            char c2 = cArr[i3];
            switch (c2) {
                case 10:
                    this.h++;
                    this.i = this.f;
                    return c2;
                case '\"':
                case '\'':
                case '/':
                case '\\':
                    return c2;
                case 'b':
                    return 8;
                case 'f':
                    return 12;
                case 'n':
                    return 10;
                case 'r':
                    return 13;
                case 't':
                    return 9;
                case 'u':
                    if (this.f + 4 <= this.g || b(4)) {
                        int i4 = this.f;
                        int i5 = i4 + 4;
                        int i6 = i4;
                        char c3 = 0;
                        for (int i7 = i6; i7 < i5; i7++) {
                            char c4 = this.e[i7];
                            char c5 = (char) (c3 << 4);
                            if (c4 >= '0' && c4 <= '9') {
                                i2 = c4 - '0';
                            } else if (c4 >= 'a' && c4 <= 'f') {
                                i2 = (c4 - 'a') + 10;
                            } else if (c4 < 'A' || c4 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                            } else {
                                i2 = (c4 - 'A') + 10;
                            }
                            c3 = (char) (c5 + i2);
                        }
                        this.f += 4;
                        return c3;
                    }
                    throw b("Unterminated escape sequence");
                default:
                    throw b("Invalid escape sequence");
            }
        } else {
            throw b("Unterminated escape sequence");
        }
    }

    private IOException b(String str) throws IOException {
        throw new d(str + x());
    }

    private void z() throws IOException {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i2 = 0;
            while (i2 < b.length) {
                if (this.e[this.f + i2] == b[i2]) {
                    i2++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }
}
