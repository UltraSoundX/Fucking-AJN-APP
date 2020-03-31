package com.tencent.android.tpush.service.channel.security;

import com.tencent.bigdata.dataacquisition.DeviceInfos;

/* compiled from: ProGuard */
public class e {
    static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());
    private int[] b = new int[4];

    public e(byte[] bArr) {
        if (bArr == null) {
            throw new RuntimeException("Invalid key: Key was null");
        } else if (bArr.length < 16) {
            throw new RuntimeException("Invalid key: Length was less than 16 bytes");
        } else {
            int i = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = i + 1;
                int i4 = i3 + 1;
                byte b2 = (bArr[i] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) | ((bArr[i3] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 8);
                int i5 = i4 + 1;
                byte b3 = ((bArr[i4] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 16) | b2;
                i = i5 + 1;
                this.b[i2] = ((bArr[i5] & DeviceInfos.NETWORK_TYPE_UNCONNECTED) << 24) | b3;
            }
        }
    }

    public byte[] a(byte[] bArr) {
        int[] iArr = new int[((((bArr.length % 8 == 0 ? 0 : 1) + (bArr.length / 8)) * 2) + 1)];
        iArr[0] = bArr.length;
        a(bArr, iArr, 1);
        a(iArr);
        return a(iArr, 0, iArr.length * 4);
    }

    public byte[] b(byte[] bArr) {
        if (!a && bArr.length % 4 != 0) {
            throw new AssertionError();
        } else if (a || (bArr.length / 4) % 2 == 1) {
            int[] iArr = new int[(bArr.length / 4)];
            a(bArr, iArr, 0);
            b(iArr);
            return a(iArr, 1, iArr[0]);
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int[] iArr) {
        if (a || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = iArr[i + 1];
                int i5 = i3;
                int i6 = 0;
                while (true) {
                    int i7 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    int i8 = i6 - 1640531527;
                    i5 += (((i4 << 4) + this.b[0]) ^ i4) + ((i4 >>> 5) ^ i8) + this.b[1];
                    i4 = (((i5 << 4) + this.b[2]) ^ i5) + ((i5 >>> 5) ^ i8) + this.b[3] + i4;
                    i6 = i8;
                    i2 = i7;
                }
                iArr[i] = i5;
                iArr[i + 1] = i4;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void b(int[] iArr) {
        if (a || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = iArr[i + 1];
                int i5 = -957401312;
                while (true) {
                    int i6 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    i4 -= ((((i3 << 4) + this.b[2]) ^ i3) + ((i3 >>> 5) ^ i5)) + this.b[3];
                    i3 -= ((((i4 << 4) + this.b[0]) ^ i4) + ((i4 >>> 5) ^ i5)) + this.b[1];
                    i5 = 1640531527 + i5;
                    i2 = i6;
                }
                iArr[i] = i3;
                iArr[i + 1] = i4;
            }
            return;
        }
        throw new AssertionError();
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [int] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4
  assigns: []
  uses: []
  mth insns count: 32
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(byte[] r7, int[] r8, int r9) {
        /*
            r6 = this;
            r1 = 24
            r3 = 0
            boolean r0 = a
            if (r0 != 0) goto L_0x0014
            int r0 = r7.length
            int r0 = r0 / 4
            int r0 = r0 + r9
            int r2 = r8.length
            if (r0 <= r2) goto L_0x0014
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0014:
            r8[r9] = r3
            r0 = r1
            r2 = r3
        L_0x0018:
            int r4 = r7.length
            if (r2 >= r4) goto L_0x0035
            r4 = r8[r9]
            byte r5 = r7[r2]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << r0
            r4 = r4 | r5
            r8[r9] = r4
            if (r0 != 0) goto L_0x0032
            int r9 = r9 + 1
            int r0 = r8.length
            if (r9 >= r0) goto L_0x0036
            r8[r9] = r3
            r0 = r1
        L_0x002f:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0032:
            int r0 = r0 + -8
            goto L_0x002f
        L_0x0035:
            return
        L_0x0036:
            r0 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.security.e.a(byte[], int[], int):void");
    }

    /* access modifiers changed from: 0000 */
    public byte[] a(int[] iArr, int i, int i2) {
        if (a || i2 <= (iArr.length - i) * 4) {
            byte[] bArr = new byte[i2];
            int i3 = 0;
            int i4 = i;
            for (int i5 = 0; i5 < i2; i5++) {
                bArr[i5] = (byte) ((iArr[i4] >> (24 - (i3 * 8))) & 255);
                i3++;
                if (i3 == 4) {
                    i4++;
                    i3 = 0;
                }
            }
            return bArr;
        }
        throw new AssertionError();
    }
}
