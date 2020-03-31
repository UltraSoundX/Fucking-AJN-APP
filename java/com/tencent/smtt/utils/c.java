package com.tencent.smtt.utils;

import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: DataReader */
public class c implements Closeable {
    private final RandomAccessFile a;
    private final File b;
    private final byte[] c;
    private boolean d;

    public c(String str) throws FileNotFoundException {
        this(new File(str));
    }

    public c(File file) throws FileNotFoundException {
        this.c = new byte[8];
        this.b = file;
        this.a = new RandomAccessFile(this.b, "r");
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void a(long j) throws IOException {
        this.a.seek(j);
    }

    public final int a(byte[] bArr) throws IOException {
        return this.a.read(bArr);
    }

    public final int a(char[] cArr) throws IOException {
        byte[] bArr = new byte[cArr.length];
        int read = this.a.read(bArr);
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) bArr[i];
        }
        return read;
    }

    public final short a() throws IOException {
        short readShort = this.a.readShort();
        if (!this.d) {
            return readShort;
        }
        return (short) (((readShort & 65280) >>> 8) | ((readShort & 255) << 8));
    }

    public final int b() throws IOException {
        int readInt = this.a.readInt();
        if (!this.d) {
            return readInt;
        }
        return ((readInt & -16777216) >>> 24) | ((readInt & 255) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >>> 8);
    }

    public final long c() throws IOException {
        if (!this.d) {
            return this.a.readLong();
        }
        this.a.readFully(this.c, 0, 8);
        return (((long) this.c[7]) << 56) | (((long) (this.c[6] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 48) | (((long) (this.c[5] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 40) | (((long) (this.c[4] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 32) | (((long) (this.c[3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 24) | (((long) (this.c[2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 16) | (((long) (this.c[1] & DeviceInfos.NETWORK_TYPE_UNCONNECTED)) << 8) | ((long) (this.c[0] & DeviceInfos.NETWORK_TYPE_UNCONNECTED));
    }

    public void close() {
        try {
            this.a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
