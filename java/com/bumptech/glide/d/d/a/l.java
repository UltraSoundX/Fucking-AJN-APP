package com.bumptech.glide.d.d.a;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: ImageHeaderParser */
public class l {
    private static final byte[] a;
    private static final int[] b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private final c c;

    /* compiled from: ImageHeaderParser */
    public enum a {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);
        
        private final boolean f;

        private a(boolean z) {
            this.f = z;
        }

        public boolean a() {
            return this.f;
        }
    }

    /* compiled from: ImageHeaderParser */
    private static class b {
        private final ByteBuffer a;

        public b(byte[] bArr) {
            this.a = ByteBuffer.wrap(bArr);
            this.a.order(ByteOrder.BIG_ENDIAN);
        }

        public void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        public int a() {
            return this.a.array().length;
        }

        public int a(int i) {
            return this.a.getInt(i);
        }

        public short b(int i) {
            return this.a.getShort(i);
        }
    }

    /* compiled from: ImageHeaderParser */
    private static class c {
        private final InputStream a;

        public c(InputStream inputStream) {
            this.a = inputStream;
        }

        public int a() throws IOException {
            return ((this.a.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip > 0) {
                    j2 -= skip;
                } else if (this.a.read() == -1) {
                    break;
                } else {
                    j2--;
                }
            }
            return j - j2;
        }

        public int a(byte[] bArr) throws IOException {
            int length = bArr.length;
            while (length > 0) {
                int read = this.a.read(bArr, bArr.length - length, length);
                if (read == -1) {
                    break;
                }
                length -= read;
            }
            return bArr.length - length;
        }

        public int c() throws IOException {
            return this.a.read();
        }
    }

    static {
        byte[] bArr = new byte[0];
        try {
            bArr = "Exif\u0000\u0000".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        a = bArr;
    }

    public l(InputStream inputStream) {
        this.c = new c(inputStream);
    }

    public boolean a() throws IOException {
        return b().a();
    }

    public a b() throws IOException {
        int a2 = this.c.a();
        if (a2 == 65496) {
            return a.JPEG;
        }
        int a3 = ((a2 << 16) & SupportMenu.CATEGORY_MASK) | (this.c.a() & 65535);
        if (a3 == -1991225785) {
            this.c.a(21);
            return this.c.c() >= 3 ? a.PNG_A : a.PNG;
        } else if ((a3 >> 8) == 4671814) {
            return a.GIF;
        } else {
            return a.UNKNOWN;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c() throws java.io.IOException {
        /*
            r7 = this;
            r2 = -1
            r1 = 0
            com.bumptech.glide.d.d.a.l$c r0 = r7.c
            int r0 = r0.a()
            boolean r0 = a(r0)
            if (r0 != 0) goto L_0x0010
            r0 = r2
        L_0x000f:
            return r0
        L_0x0010:
            byte[] r4 = r7.d()
            if (r4 == 0) goto L_0x0039
            int r0 = r4.length
            byte[] r3 = a
            int r3 = r3.length
            if (r0 <= r3) goto L_0x0039
            r3 = 1
        L_0x001d:
            if (r3 == 0) goto L_0x0040
            r0 = r1
        L_0x0020:
            byte[] r5 = a
            int r5 = r5.length
            if (r0 >= r5) goto L_0x0040
            byte r5 = r4[r0]
            byte[] r6 = a
            byte r6 = r6[r0]
            if (r5 == r6) goto L_0x003b
        L_0x002d:
            if (r1 == 0) goto L_0x003e
            com.bumptech.glide.d.d.a.l$b r0 = new com.bumptech.glide.d.d.a.l$b
            r0.<init>(r4)
            int r0 = a(r0)
            goto L_0x000f
        L_0x0039:
            r3 = r1
            goto L_0x001d
        L_0x003b:
            int r0 = r0 + 1
            goto L_0x0020
        L_0x003e:
            r0 = r2
            goto L_0x000f
        L_0x0040:
            r1 = r3
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.d.a.l.c():int");
    }

    private byte[] d() throws IOException {
        short b2;
        int a2;
        long a3;
        do {
            short b3 = this.c.b();
            if (b3 == 255) {
                b2 = this.c.b();
                if (b2 == 218) {
                    return null;
                }
                if (b2 != 217) {
                    a2 = this.c.a() - 2;
                    if (b2 != 225) {
                        a3 = this.c.a((long) a2);
                    } else {
                        byte[] bArr = new byte[a2];
                        int a4 = this.c.a(bArr);
                        if (a4 == a2) {
                            return bArr;
                        }
                        if (!Log.isLoggable("ImageHeaderParser", 3)) {
                            return null;
                        }
                        Log.d("ImageHeaderParser", "Unable to read segment data, type: " + b2 + ", length: " + a2 + ", actually read: " + a4);
                        return null;
                    }
                } else if (!Log.isLoggable("ImageHeaderParser", 3)) {
                    return null;
                } else {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                    return null;
                }
            } else if (!Log.isLoggable("ImageHeaderParser", 3)) {
                return null;
            } else {
                Log.d("ImageHeaderParser", "Unknown segmentId=" + b3);
                return null;
            }
        } while (a3 == ((long) a2));
        if (!Log.isLoggable("ImageHeaderParser", 3)) {
            return null;
        }
        Log.d("ImageHeaderParser", "Unable to skip enough data, type: " + b2 + ", wanted to skip: " + a2 + ", but actually skipped: " + a3);
        return null;
    }

    private static int a(b bVar) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b2 = bVar.b(length);
        if (b2 == 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (b2 == 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Unknown endianness = " + b2);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        bVar.a(byteOrder);
        int a2 = length + bVar.a(length + 4);
        short b3 = bVar.b(a2);
        for (int i = 0; i < b3; i++) {
            int a3 = a(a2, i);
            short b4 = bVar.b(a3);
            if (b4 == 274) {
                short b5 = bVar.b(a3 + 2);
                if (b5 >= 1 && b5 <= 12) {
                    int a4 = bVar.a(a3 + 4);
                    if (a4 >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got tagIndex=" + i + " tagType=" + b4 + " formatCode=" + b5 + " componentCount=" + a4);
                        }
                        int i2 = a4 + b[b5];
                        if (i2 <= 4) {
                            int i3 = a3 + 8;
                            if (i3 < 0 || i3 > bVar.a()) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal tagValueOffset=" + i3 + " tagType=" + b4);
                                }
                            } else if (i2 >= 0 && i3 + i2 <= bVar.a()) {
                                return bVar.b(i3);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    Log.d("ImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + b4);
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                            Log.d("ImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + b5);
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                        Log.d("ImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Got invalid format code=" + b5);
                }
            }
        }
        return -1;
    }

    private static int a(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    private static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }
}
