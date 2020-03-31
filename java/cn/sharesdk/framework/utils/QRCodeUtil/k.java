package cn.sharesdk.framework.utils.QRCodeUtil;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ReedSolomonEncoder */
public final class k {
    private final g a;
    private final List<h> b = new ArrayList();

    public k(g gVar) {
        this.a = gVar;
        this.b.add(new h(gVar, new int[]{1}));
    }

    private h a(int i) {
        if (i >= this.b.size()) {
            h hVar = (h) this.b.get(this.b.size() - 1);
            h hVar2 = hVar;
            for (int size = this.b.size(); size <= i; size++) {
                hVar2 = hVar2.b(new h(this.a, new int[]{1, this.a.a((size - 1) + this.a.b())}));
                this.b.add(hVar2);
            }
        }
        return (h) this.b.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        h a2 = a(i);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] a3 = new h(this.a, iArr2).a(i, 1).c(a2)[1].a();
        int length2 = i - a3.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(a3, 0, iArr, length + length2, a3.length);
    }
}
