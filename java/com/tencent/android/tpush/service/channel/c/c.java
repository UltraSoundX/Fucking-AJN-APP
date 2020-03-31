package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ProGuard */
public class c {
    static final /* synthetic */ boolean a = (!c.class.desiredAssertionStatus());

    public static boolean a(InputStream inputStream, int i) {
        return inputStream.available() >= i;
    }

    public static short a(InputStream inputStream) {
        if (!a(inputStream, 1)) {
            throw new IORefusedException("inputstream cannot read 1 byte");
        }
        byte[] bArr = new byte[1];
        if (inputStream.read(bArr) != -1) {
            return (short) (bArr[0] & DeviceInfos.NETWORK_TYPE_UNCONNECTED);
        }
        throw new IOException("the end of stream has been reached!");
    }

    public static long b(InputStream inputStream) {
        if (!a(inputStream, 4)) {
            throw new IORefusedException("inputstream cannot read 4 byte");
        }
        byte[] bArr = new byte[4];
        if (inputStream.read(bArr) == -1) {
            throw new IOException("the end of stream has been reached!");
        }
        return (long) (((bArr[0] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 24) | (bArr[3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) | ((bArr[2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 8) | ((bArr[1] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 16));
    }

    public static int c(InputStream inputStream) {
        if (!a(inputStream, 4)) {
            throw new IORefusedException("inputstream cannot read 4 byte");
        }
        byte[] bArr = new byte[4];
        if (inputStream.read(bArr) == -1) {
            throw new IOException("the end of stream has been reached!");
        }
        return ((bArr[0] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 24) | (bArr[3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) | ((bArr[2] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 8) | ((bArr[1] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 16);
    }

    public static int a(InputStream inputStream, byte[] bArr, int i) {
        if (inputStream.available() == 0 && bArr.length - i > 0) {
            return 0;
        }
        int available = bArr.length - i < inputStream.available() ? bArr.length - i : inputStream.available();
        if (available <= 0) {
            return available;
        }
        int read = inputStream.read(bArr, i, available);
        if (read != -1) {
            return read;
        }
        throw new IOException("the end of stream has been reached!");
    }

    public static int a(OutputStream outputStream, int i) {
        if (a || (i >= 0 && ((long) i) <= 255)) {
            outputStream.write((byte) (i & 255));
            return 1;
        }
        throw new AssertionError();
    }

    public static int a(OutputStream outputStream, long j) {
        if (a || (j >= 0 && j <= 4294967295L)) {
            outputStream.write(new byte[]{(byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255))});
            return 4;
        }
        throw new AssertionError();
    }

    public static int b(OutputStream outputStream, int i) {
        outputStream.write(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
        return 4;
    }

    public static int a(OutputStream outputStream, byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            outputStream.write(bArr, i2, 1);
            i++;
        }
        return i;
    }
}
