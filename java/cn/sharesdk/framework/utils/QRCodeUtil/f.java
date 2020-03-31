package cn.sharesdk.framework.utils.QRCodeUtil;

/* compiled from: ErrorCorrectionLevel */
public enum f {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final f[] e = null;
    private final int f;

    static {
        e = new f[]{M, L, H, Q};
    }

    private f(int i) {
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
