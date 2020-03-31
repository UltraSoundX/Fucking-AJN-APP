package cn.sharesdk.framework.utils.QRCodeUtil.a;

import cn.sharesdk.framework.utils.QRCodeUtil.i;
import cn.sharesdk.framework.utils.QRCodeUtil.l;

/* compiled from: QRCode */
public final class f {
    private i a;
    private cn.sharesdk.framework.utils.QRCodeUtil.f b;
    private l c;
    private int d = -1;
    private b e;

    public b a() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.a);
        sb.append("\n ecLevel: ");
        sb.append(this.b);
        sb.append("\n version: ");
        sb.append(this.c);
        sb.append("\n maskPattern: ");
        sb.append(this.d);
        if (this.e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.e);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    public void a(i iVar) {
        this.a = iVar;
    }

    public void a(cn.sharesdk.framework.utils.QRCodeUtil.f fVar) {
        this.b = fVar;
    }

    public void a(l lVar) {
        this.c = lVar;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public static boolean b(int i) {
        return i >= 0 && i < 8;
    }
}
