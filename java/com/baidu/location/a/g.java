package com.baidu.location.a;

import com.baidu.location.g.i;
import com.baidu.location.g.j;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class g {
    private static g a = null;
    private static String b = "Temp_in.dat";
    private static File c = new File(i.a, b);
    private static StringBuffer d = null;
    private static boolean e = true;
    private static int f = 0;
    private static int g = 0;
    private static long h = 0;
    private static long i = 0;
    private static long j = 0;
    private static double k = 0.0d;
    private static double l = 0.0d;
    private static int m = 0;
    private static int n = 0;
    private static int o = 0;

    public static String a() {
        if (c == null) {
            return null;
        }
        if (!c.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (!a(readInt, readInt2, readInt3)) {
                randomAccessFile.close();
                c();
                return null;
            } else if (readInt2 == 0 || readInt2 == readInt3) {
                randomAccessFile.close();
                return null;
            } else {
                long j2 = 0 + ((long) (((readInt2 - 1) * 1024) + 12));
                randomAccessFile.seek(j2);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j2 + 4);
                for (int i2 = 0; i2 < readInt4; i2++) {
                    bArr[i2] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                int i3 = readInt < j.ae ? readInt2 + 1 : readInt2 == j.ae ? 1 : readInt2 + 1;
                randomAccessFile.seek(4);
                randomAccessFile.writeInt(i3);
                randomAccessFile.close();
                return str;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    private static boolean a(int i2, int i3, int i4) {
        if (i2 < 0 || i2 > j.ae) {
            return false;
        }
        if (i3 < 0 || i3 > i2 + 1) {
            return false;
        }
        return i4 >= 1 && i4 <= i2 + 1 && i4 <= j.ae;
    }

    private static void b() {
        e = true;
        d = null;
        f = 0;
        g = 0;
        h = 0;
        i = 0;
        j = 0;
        k = 0.0d;
        l = 0.0d;
        m = 0;
        n = 0;
        o = 0;
    }

    private static boolean c() {
        if (c.exists()) {
            c.delete();
        }
        if (!c.getParentFile().exists()) {
            c.getParentFile().mkdirs();
        }
        try {
            c.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(c, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            b();
            return c.exists();
        } catch (IOException e2) {
            return false;
        }
    }
}
