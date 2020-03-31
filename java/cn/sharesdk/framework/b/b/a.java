package cn.sharesdk.framework.b.b;

import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

/* compiled from: ApiEvent */
public class a extends c {
    private static int c;
    private static long d;
    public int a;
    public String b;

    /* access modifiers changed from: protected */
    public String a() {
        return "[API]";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|').append(this.a);
        sb.append('|').append(this.b);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int b() {
        return ReaderCallback.GET_BAR_ANIMATING;
    }

    /* access modifiers changed from: protected */
    public int c() {
        return 50;
    }

    /* access modifiers changed from: protected */
    public long d() {
        return (long) c;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return d;
    }

    /* access modifiers changed from: protected */
    public void f() {
        c++;
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        d = j;
    }
}
