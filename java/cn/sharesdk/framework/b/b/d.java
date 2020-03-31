package cn.sharesdk.framework.b.b;

import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

/* compiled from: DemoEvent */
public class d extends c {
    private static int d;
    private static long m;
    public String a;
    public int b;
    public String c = "";

    /* access modifiers changed from: protected */
    public String a() {
        return "[EVT]";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|').append(this.a);
        sb.append('|').append(this.b);
        sb.append('|').append(this.c);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int b() {
        return ReaderCallback.GET_BAR_ANIMATING;
    }

    /* access modifiers changed from: protected */
    public int c() {
        return 30;
    }

    /* access modifiers changed from: protected */
    public long d() {
        return (long) d;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return m;
    }

    /* access modifiers changed from: protected */
    public void f() {
        d++;
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        m = j;
    }
}
