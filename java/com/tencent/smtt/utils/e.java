package com.tencent.smtt.utils;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

/* compiled from: Elf */
public class e implements Closeable {
    static final char[] a = {127, 'E', 'L', 'F', 0};
    final char[] b = new char[16];
    boolean c;
    j[] d;
    l[] e;
    byte[] f;
    private final c g;
    private final a h;
    private final k[] i;
    private byte[] j;

    /* compiled from: Elf */
    public static abstract class a {
        short a;
        short b;
        int c;
        int d;
        short e;
        short f;
        short g;
        short h;
        short i;
        short j;

        /* access modifiers changed from: 0000 */
        public abstract long a();

        /* access modifiers changed from: 0000 */
        public abstract long b();
    }

    /* compiled from: Elf */
    static class b extends a {
        int k;
        int l;
        int m;

        b() {
        }

        /* access modifiers changed from: 0000 */
        public long a() {
            return (long) this.m;
        }

        /* access modifiers changed from: 0000 */
        public long b() {
            return (long) this.l;
        }
    }

    /* compiled from: Elf */
    static class c extends j {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        c() {
        }
    }

    /* compiled from: Elf */
    static class d extends k {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        d() {
        }

        public int a() {
            return this.d;
        }

        public long b() {
            return (long) this.c;
        }
    }

    /* renamed from: com.tencent.smtt.utils.e$e reason: collision with other inner class name */
    /* compiled from: Elf */
    static class C0081e extends l {
        int a;
        int b;

        C0081e() {
        }
    }

    /* compiled from: Elf */
    static class f extends a {
        long k;
        long l;
        long m;

        f() {
        }

        /* access modifiers changed from: 0000 */
        public long a() {
            return this.m;
        }

        /* access modifiers changed from: 0000 */
        public long b() {
            return this.l;
        }
    }

    /* compiled from: Elf */
    static class g extends j {
        long a;
        long b;
        long c;
        long d;
        long e;
        long f;

        g() {
        }
    }

    /* compiled from: Elf */
    static class h extends k {
        long a;
        long b;
        long c;
        long d;
        long e;
        long f;

        h() {
        }

        public int a() {
            return (int) this.d;
        }

        public long b() {
            return this.c;
        }
    }

    /* compiled from: Elf */
    static class i extends l {
        long a;
        long b;

        i() {
        }
    }

    /* compiled from: Elf */
    static abstract class j {
        int g;
        int h;

        j() {
        }
    }

    /* compiled from: Elf */
    public static abstract class k {
        int g;
        int h;
        int i;
        int j;

        public abstract int a();

        public abstract long b();
    }

    /* compiled from: Elf */
    static abstract class l {
        int c;
        char d;
        char e;
        short f;

        l() {
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        return this.b[0] == a[0];
    }

    /* access modifiers changed from: 0000 */
    public final char b() {
        return this.b[4];
    }

    /* access modifiers changed from: 0000 */
    public final char c() {
        return this.b[5];
    }

    public final boolean d() {
        return b() == 2;
    }

    public final boolean e() {
        return c() == 1;
    }

    public e(File file) throws IOException, UnknownFormatConversionException {
        c cVar = new c(file);
        this.g = cVar;
        cVar.a(this.b);
        if (!a()) {
            throw new UnknownFormatConversionException("Invalid elf magic: " + file);
        }
        cVar.a(e());
        boolean d2 = d();
        if (d2) {
            f fVar = new f();
            fVar.a = cVar.a();
            fVar.b = cVar.a();
            fVar.c = cVar.b();
            fVar.k = cVar.c();
            fVar.l = cVar.c();
            fVar.m = cVar.c();
            this.h = fVar;
        } else {
            b bVar = new b();
            bVar.a = cVar.a();
            bVar.b = cVar.a();
            bVar.c = cVar.b();
            bVar.k = cVar.b();
            bVar.l = cVar.b();
            bVar.m = cVar.b();
            this.h = bVar;
        }
        a aVar = this.h;
        aVar.d = cVar.b();
        aVar.e = cVar.a();
        aVar.f = cVar.a();
        aVar.g = cVar.a();
        aVar.h = cVar.a();
        aVar.i = cVar.a();
        aVar.j = cVar.a();
        this.i = new k[aVar.i];
        for (int i2 = 0; i2 < aVar.i; i2++) {
            cVar.a(aVar.a() + ((long) (aVar.h * i2)));
            if (d2) {
                h hVar = new h();
                hVar.g = cVar.b();
                hVar.h = cVar.b();
                hVar.a = cVar.c();
                hVar.b = cVar.c();
                hVar.c = cVar.c();
                hVar.d = cVar.c();
                hVar.i = cVar.b();
                hVar.j = cVar.b();
                hVar.e = cVar.c();
                hVar.f = cVar.c();
                this.i[i2] = hVar;
            } else {
                d dVar = new d();
                dVar.g = cVar.b();
                dVar.h = cVar.b();
                dVar.a = cVar.b();
                dVar.b = cVar.b();
                dVar.c = cVar.b();
                dVar.d = cVar.b();
                dVar.i = cVar.b();
                dVar.j = cVar.b();
                dVar.e = cVar.b();
                dVar.f = cVar.b();
                this.i[i2] = dVar;
            }
        }
        if (aVar.j <= -1 || aVar.j >= this.i.length) {
            throw new UnknownFormatConversionException("Invalid e_shstrndx=" + aVar.j);
        }
        k kVar = this.i[aVar.j];
        if (kVar.h == 3) {
            this.j = new byte[kVar.a()];
            cVar.a(kVar.b());
            cVar.a(this.j);
            if (this.c) {
                f();
                return;
            }
            return;
        }
        throw new UnknownFormatConversionException("Wrong string section e_shstrndx=" + aVar.j);
    }

    private void f() throws IOException {
        a aVar = this.h;
        c cVar = this.g;
        boolean d2 = d();
        k a2 = a(".dynsym");
        if (a2 != null) {
            cVar.a(a2.b());
            int a3 = a2.a() / (d2 ? 24 : 16);
            this.e = new l[a3];
            char[] cArr = new char[1];
            for (int i2 = 0; i2 < a3; i2++) {
                if (d2) {
                    i iVar = new i();
                    iVar.c = cVar.b();
                    cVar.a(cArr);
                    iVar.d = cArr[0];
                    cVar.a(cArr);
                    iVar.e = cArr[0];
                    iVar.a = cVar.c();
                    iVar.b = cVar.c();
                    iVar.f = cVar.a();
                    this.e[i2] = iVar;
                } else {
                    C0081e eVar = new C0081e();
                    eVar.c = cVar.b();
                    eVar.a = cVar.b();
                    eVar.b = cVar.b();
                    cVar.a(cArr);
                    eVar.d = cArr[0];
                    cVar.a(cArr);
                    eVar.e = cArr[0];
                    eVar.f = cVar.a();
                    this.e[i2] = eVar;
                }
            }
            k kVar = this.i[a2.i];
            cVar.a(kVar.b());
            this.f = new byte[kVar.a()];
            cVar.a(this.f);
        }
        this.d = new j[aVar.g];
        for (int i3 = 0; i3 < aVar.g; i3++) {
            cVar.a(aVar.b() + ((long) (aVar.f * i3)));
            if (d2) {
                g gVar = new g();
                gVar.g = cVar.b();
                gVar.h = cVar.b();
                gVar.a = cVar.c();
                gVar.b = cVar.c();
                gVar.c = cVar.c();
                gVar.d = cVar.c();
                gVar.e = cVar.c();
                gVar.f = cVar.c();
                this.d[i3] = gVar;
            } else {
                c cVar2 = new c();
                cVar2.g = cVar.b();
                cVar2.h = cVar.b();
                cVar2.a = cVar.b();
                cVar2.b = cVar.b();
                cVar2.c = cVar.b();
                cVar2.d = cVar.b();
                cVar2.e = cVar.b();
                cVar2.f = cVar.b();
                this.d[i3] = cVar2;
            }
        }
    }

    public final k a(String str) {
        k[] kVarArr;
        for (k kVar : this.i) {
            if (str.equals(a(kVar.g))) {
                return kVar;
            }
        }
        return null;
    }

    public final String a(int i2) {
        if (i2 == 0) {
            return "SHN_UNDEF";
        }
        int i3 = i2;
        while (this.j[i3] != 0) {
            i3++;
        }
        return new String(this.j, i2, i3 - i2);
    }

    public void close() {
        this.g.close();
    }

    public static boolean a(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long readInt = (long) randomAccessFile.readInt();
            randomAccessFile.close();
            if (readInt == 2135247942) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(File file) {
        if (g() && a(file)) {
            try {
                new e(file);
            } catch (IOException e2) {
                Log.e("ELF", "checkElfFile IOException: " + e2);
                return false;
            } catch (UnknownFormatConversionException e3) {
                Log.e("ELF", "checkElfFile UnknownFormatConversionException: " + e3);
            } catch (Throwable th) {
                Log.e("ELF", "checkElfFile Throwable: " + th);
            }
        }
        return true;
    }

    private static boolean g() {
        String property = System.getProperty("java.vm.version");
        return property != null && property.startsWith("2");
    }
}
