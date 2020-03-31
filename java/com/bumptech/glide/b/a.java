package com.bumptech.glide.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: GifDecoder */
public class a {
    private static final String a = a.class.getSimpleName();
    private static final Config b = Config.ARGB_8888;
    private int[] c;
    private ByteBuffer d;
    private final byte[] e = new byte[256];
    private short[] f;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private int[] j;
    private int k;
    private byte[] l;
    private c m;
    private C0036a n;
    private Bitmap o;
    private boolean p;

    /* renamed from: q reason: collision with root package name */
    private int f388q;

    /* renamed from: com.bumptech.glide.b.a$a reason: collision with other inner class name */
    /* compiled from: GifDecoder */
    public interface C0036a {
        Bitmap a(int i, int i2, Config config);

        void a(Bitmap bitmap);
    }

    public a(C0036a aVar) {
        this.n = aVar;
        this.m = new c();
    }

    public void a() {
        this.k = (this.k + 1) % this.m.c;
    }

    public int a(int i2) {
        if (i2 < 0 || i2 >= this.m.c) {
            return -1;
        }
        return ((b) this.m.e.get(i2)).i;
    }

    public int b() {
        if (this.m.c <= 0 || this.k < 0) {
            return -1;
        }
        return a(this.k);
    }

    public int c() {
        return this.m.c;
    }

    public int d() {
        return this.k;
    }

    public int e() {
        return this.m.m;
    }

    public synchronized Bitmap f() {
        Bitmap bitmap;
        b bVar;
        int i2 = 0;
        synchronized (this) {
            if (this.m.c <= 0 || this.k < 0) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "unable to decode frame, frameCount=" + this.m.c + " framePointer=" + this.k);
                }
                this.f388q = 1;
            }
            if (this.f388q == 1 || this.f388q == 2) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "Unable to decode frame, status=" + this.f388q);
                }
                bitmap = null;
            } else {
                this.f388q = 0;
                b bVar2 = (b) this.m.e.get(this.k);
                int i3 = this.k - 1;
                if (i3 >= 0) {
                    bVar = (b) this.m.e.get(i3);
                } else {
                    bVar = null;
                }
                if (bVar2.k == null) {
                    this.c = this.m.a;
                } else {
                    this.c = bVar2.k;
                    if (this.m.j == bVar2.h) {
                        this.m.l = 0;
                    }
                }
                if (bVar2.f) {
                    int i4 = this.c[bVar2.h];
                    this.c[bVar2.h] = 0;
                    i2 = i4;
                }
                if (this.c == null) {
                    if (Log.isLoggable(a, 3)) {
                        Log.d(a, "No Valid Color Table");
                    }
                    this.f388q = 1;
                    bitmap = null;
                } else {
                    Bitmap a2 = a(bVar2, bVar);
                    if (bVar2.f) {
                        this.c[bVar2.h] = i2;
                    }
                    bitmap = a2;
                }
            }
        }
        return bitmap;
    }

    public void g() {
        this.m = null;
        this.l = null;
        this.i = null;
        this.j = null;
        if (this.o != null) {
            this.n.a(this.o);
        }
        this.o = null;
        this.d = null;
    }

    public void a(c cVar, byte[] bArr) {
        this.m = cVar;
        this.l = bArr;
        this.f388q = 0;
        this.k = -1;
        this.d = ByteBuffer.wrap(bArr);
        this.d.rewind();
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        this.p = false;
        Iterator it = cVar.e.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((b) it.next()).g == 3) {
                    this.p = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.i = new byte[(cVar.f * cVar.g)];
        this.j = new int[(cVar.f * cVar.g)];
    }

    private Bitmap a(b bVar, b bVar2) {
        int i2;
        int i3 = this.m.f;
        int i4 = this.m.g;
        int[] iArr = this.j;
        if (bVar2 != null && bVar2.g > 0) {
            if (bVar2.g == 2) {
                int i5 = 0;
                if (!bVar.f) {
                    i5 = this.m.l;
                }
                Arrays.fill(iArr, i5);
            } else if (bVar2.g == 3 && this.o != null) {
                this.o.getPixels(iArr, 0, i3, 0, 0, i3, i4);
            }
        }
        a(bVar);
        int i6 = 1;
        int i7 = 8;
        int i8 = 0;
        for (int i9 = 0; i9 < bVar.d; i9++) {
            if (bVar.e) {
                if (i8 >= bVar.d) {
                    i6++;
                    switch (i6) {
                        case 2:
                            i8 = 4;
                            break;
                        case 3:
                            i8 = 2;
                            i7 = 4;
                            break;
                        case 4:
                            i8 = 1;
                            i7 = 2;
                            break;
                    }
                }
                int i10 = i8;
                i8 += i7;
                i2 = i10;
            } else {
                i2 = i9;
            }
            int i11 = i2 + bVar.b;
            if (i11 < this.m.g) {
                int i12 = this.m.f * i11;
                int i13 = i12 + bVar.a;
                int i14 = bVar.c + i13;
                if (this.m.f + i12 < i14) {
                    i14 = this.m.f + i12;
                }
                int i15 = bVar.c * i9;
                int i16 = i13;
                while (i16 < i14) {
                    int i17 = i15 + 1;
                    int i18 = this.c[this.i[i15] & DeviceInfos.NETWORK_TYPE_UNCONNECTED];
                    if (i18 != 0) {
                        iArr[i16] = i18;
                    }
                    i16++;
                    i15 = i17;
                }
            }
        }
        if (this.p && (bVar.g == 0 || bVar.g == 1)) {
            if (this.o == null) {
                this.o = j();
            }
            this.o.setPixels(iArr, 0, i3, 0, 0, i3, i4);
        }
        Bitmap j2 = j();
        j2.setPixels(iArr, 0, i3, 0, 0, i3, i4);
        return j2;
    }

    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r8v15, types: [short[]] */
    /* JADX WARNING: type inference failed for: r8v16, types: [short] */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r6v26 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=null, for r8v16, types: [short] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short[], code=null, for r8v15, types: [short[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v6
  assigns: []
  uses: []
  mth insns count: 237
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.bumptech.glide.b.b r23) {
        /*
            r22 = this;
            if (r23 == 0) goto L_0x000d
            r0 = r22
            java.nio.ByteBuffer r1 = r0.d
            r0 = r23
            int r2 = r0.j
            r1.position(r2)
        L_0x000d:
            if (r23 != 0) goto L_0x0081
            r0 = r22
            com.bumptech.glide.b.c r1 = r0.m
            int r1 = r1.f
            r0 = r22
            com.bumptech.glide.b.c r2 = r0.m
            int r2 = r2.g
            int r1 = r1 * r2
        L_0x001c:
            r0 = r22
            byte[] r2 = r0.i
            if (r2 == 0) goto L_0x0029
            r0 = r22
            byte[] r2 = r0.i
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002f
        L_0x0029:
            byte[] r2 = new byte[r1]
            r0 = r22
            r0.i = r2
        L_0x002f:
            r0 = r22
            short[] r2 = r0.f
            if (r2 != 0) goto L_0x003d
            r2 = 4096(0x1000, float:5.74E-42)
            short[] r2 = new short[r2]
            r0 = r22
            r0.f = r2
        L_0x003d:
            r0 = r22
            byte[] r2 = r0.g
            if (r2 != 0) goto L_0x004b
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]
            r0 = r22
            r0.g = r2
        L_0x004b:
            r0 = r22
            byte[] r2 = r0.h
            if (r2 != 0) goto L_0x0059
            r2 = 4097(0x1001, float:5.741E-42)
            byte[] r2 = new byte[r2]
            r0 = r22
            r0.h = r2
        L_0x0059:
            int r16 = r22.h()
            r2 = 1
            int r17 = r2 << r16
            int r18 = r17 + 1
            int r4 = r17 + 2
            r10 = -1
            int r2 = r16 + 1
            r3 = 1
            int r3 = r3 << r2
            int r3 = r3 + -1
            r5 = 0
        L_0x006c:
            r0 = r17
            if (r5 >= r0) goto L_0x008b
            r0 = r22
            short[] r6 = r0.f
            r7 = 0
            r6[r5] = r7
            r0 = r22
            byte[] r6 = r0.g
            byte r7 = (byte) r5
            r6[r5] = r7
            int r5 = r5 + 1
            goto L_0x006c
        L_0x0081:
            r0 = r23
            int r1 = r0.c
            r0 = r23
            int r2 = r0.d
            int r1 = r1 * r2
            goto L_0x001c
        L_0x008b:
            r5 = 0
            r7 = 0
            r8 = r5
            r11 = r5
            r6 = r5
            r9 = r2
            r12 = r3
            r13 = r4
            r2 = r5
            r3 = r5
            r4 = r5
        L_0x0096:
            if (r7 >= r1) goto L_0x00a5
            if (r3 != 0) goto L_0x00b3
            int r3 = r22.i()
            if (r3 > 0) goto L_0x00b2
            r2 = 3
            r0 = r22
            r0.f388q = r2
        L_0x00a5:
            r2 = r4
        L_0x00a6:
            if (r2 >= r1) goto L_0x0196
            r0 = r22
            byte[] r3 = r0.i
            r4 = 0
            r3[r2] = r4
            int r2 = r2 + 1
            goto L_0x00a6
        L_0x00b2:
            r2 = 0
        L_0x00b3:
            r0 = r22
            byte[] r14 = r0.e
            byte r14 = r14[r2]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << r6
            int r5 = r5 + r14
            int r6 = r6 + 8
            int r14 = r2 + 1
            int r15 = r3 + -1
            r2 = r9
            r3 = r12
            r9 = r11
            r21 = r6
            r6 = r5
            r5 = r4
            r4 = r13
            r13 = r21
        L_0x00cd:
            if (r13 < r2) goto L_0x019f
            r11 = r6 & r3
            int r12 = r6 >> r2
            int r13 = r13 - r2
            r0 = r17
            if (r11 != r0) goto L_0x00e4
            int r2 = r16 + 1
            r3 = 1
            int r3 = r3 << r2
            int r3 = r3 + -1
            int r4 = r17 + 2
            r11 = -1
            r6 = r12
            r10 = r11
            goto L_0x00cd
        L_0x00e4:
            if (r11 <= r4) goto L_0x00f5
            r6 = 3
            r0 = r22
            r0.f388q = r6
            r11 = r9
            r6 = r13
            r9 = r2
            r13 = r4
            r2 = r14
            r4 = r5
            r5 = r12
            r12 = r3
            r3 = r15
            goto L_0x0096
        L_0x00f5:
            r0 = r18
            if (r11 != r0) goto L_0x0103
            r11 = r9
            r6 = r13
            r9 = r2
            r13 = r4
            r2 = r14
            r4 = r5
            r5 = r12
            r12 = r3
            r3 = r15
            goto L_0x0096
        L_0x0103:
            r6 = -1
            if (r10 != r6) goto L_0x0119
            r0 = r22
            byte[] r9 = r0.h
            int r6 = r8 + 1
            r0 = r22
            byte[] r10 = r0.g
            byte r10 = r10[r11]
            r9[r8] = r10
            r8 = r6
            r9 = r11
            r10 = r11
            r6 = r12
            goto L_0x00cd
        L_0x0119:
            if (r11 < r4) goto L_0x019d
            r0 = r22
            byte[] r0 = r0.h
            r19 = r0
            int r6 = r8 + 1
            byte r9 = (byte) r9
            r19[r8] = r9
            r8 = r6
            r9 = r10
        L_0x0128:
            r0 = r17
            if (r9 < r0) goto L_0x0147
            r0 = r22
            byte[] r0 = r0.h
            r19 = r0
            int r6 = r8 + 1
            r0 = r22
            byte[] r0 = r0.g
            r20 = r0
            byte r20 = r20[r9]
            r19[r8] = r20
            r0 = r22
            short[] r8 = r0.f
            short r8 = r8[r9]
            r9 = r8
            r8 = r6
            goto L_0x0128
        L_0x0147:
            r0 = r22
            byte[] r6 = r0.g
            byte r6 = r6[r9]
            r9 = r6 & 255(0xff, float:3.57E-43)
            r0 = r22
            byte[] r0 = r0.h
            r19 = r0
            int r6 = r8 + 1
            byte r0 = (byte) r9
            r20 = r0
            r19[r8] = r20
            r8 = 4096(0x1000, float:5.74E-42)
            if (r4 >= r8) goto L_0x017b
            r0 = r22
            short[] r8 = r0.f
            short r10 = (short) r10
            r8[r4] = r10
            r0 = r22
            byte[] r8 = r0.g
            byte r10 = (byte) r9
            r8[r4] = r10
            int r4 = r4 + 1
            r8 = r4 & r3
            if (r8 != 0) goto L_0x017b
            r8 = 4096(0x1000, float:5.74E-42)
            if (r4 >= r8) goto L_0x017b
            int r2 = r2 + 1
            int r3 = r3 + r4
        L_0x017b:
            r8 = r7
        L_0x017c:
            if (r6 <= 0) goto L_0x0197
            int r7 = r6 + -1
            r0 = r22
            byte[] r10 = r0.i
            int r6 = r5 + 1
            r0 = r22
            byte[] r0 = r0.h
            r19 = r0
            byte r19 = r19[r7]
            r10[r5] = r19
            int r5 = r8 + 1
            r8 = r5
            r5 = r6
            r6 = r7
            goto L_0x017c
        L_0x0196:
            return
        L_0x0197:
            r7 = r8
            r10 = r11
            r8 = r6
            r6 = r12
            goto L_0x00cd
        L_0x019d:
            r9 = r11
            goto L_0x0128
        L_0x019f:
            r11 = r9
            r12 = r3
            r3 = r15
            r9 = r2
            r2 = r14
            r21 = r13
            r13 = r4
            r4 = r5
            r5 = r6
            r6 = r21
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.a.a(com.bumptech.glide.b.b):void");
    }

    private int h() {
        boolean z = false;
        try {
            return this.d.get() & DeviceInfos.NETWORK_TYPE_UNCONNECTED;
        } catch (Exception e2) {
            this.f388q = 1;
            return z;
        }
    }

    private int i() {
        int h2 = h();
        int i2 = 0;
        if (h2 > 0) {
            while (i2 < h2) {
                int i3 = h2 - i2;
                try {
                    this.d.get(this.e, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    Log.w(a, "Error Reading Block", e2);
                    this.f388q = 1;
                }
            }
        }
        return i2;
    }

    private Bitmap j() {
        Bitmap a2 = this.n.a(this.m.f, this.m.g, b);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(this.m.f, this.m.g, b);
        }
        a(a2);
        return a2;
    }

    @TargetApi(12)
    private static void a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
