package cn.sharesdk.framework.b.b;

import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

/* compiled from: ExitEvent */
public class e extends c {
    private static int b;
    private static long c;
    public long a;

    /* access modifiers changed from: protected */
    public String a() {
        return "[EXT]";
    }

    /* access modifiers changed from: protected */
    public int b() {
        return ReaderCallback.GET_BAR_ANIMATING;
    }

    /* access modifiers changed from: protected */
    public int c() {
        return 5;
    }

    public boolean g() {
        cn.sharesdk.framework.b.a.e a2 = cn.sharesdk.framework.b.a.e.a();
        b = a2.i("insertExitEventCount");
        c = a2.h("lastInsertExitEventTime");
        return super.g();
    }

    public void h() {
        super.h();
        cn.sharesdk.framework.b.a.e a2 = cn.sharesdk.framework.b.a.e.a();
        a2.a("lastInsertExitEventTime", Long.valueOf(c));
        a2.a("insertExitEventCount", b);
    }

    /* access modifiers changed from: protected */
    public long d() {
        return (long) b;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return c;
    }

    /* access modifiers changed from: protected */
    public void f() {
        b++;
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        c = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        if (!TextUtils.isEmpty(this.l)) {
            sb.append(this.l);
        }
        sb.append('|').append(Math.round(((float) this.a) / 1000.0f));
        return sb.toString();
    }
}
