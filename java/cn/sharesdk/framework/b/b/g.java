package cn.sharesdk.framework.b.b;

import android.text.TextUtils;
import cn.sharesdk.framework.b.a.e;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;

/* compiled from: StartEvent */
public class g extends c {
    private static int a;
    private static long b;

    /* access modifiers changed from: protected */
    public String a() {
        return "[RUN]";
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
        e a2 = e.a();
        a = a2.i("insertRunEventCount");
        b = a2.h("lastInsertRunEventTime");
        return super.g();
    }

    public void h() {
        super.h();
        e a2 = e.a();
        a2.a("lastInsertRunEventTime", Long.valueOf(b));
        a2.a("insertRunEventCount", a);
    }

    /* access modifiers changed from: protected */
    public long d() {
        return (long) a;
    }

    /* access modifiers changed from: protected */
    public long e() {
        return b;
    }

    /* access modifiers changed from: protected */
    public void f() {
        a++;
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        b = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('|');
        if (!TextUtils.isEmpty(this.l)) {
            sb.append(this.l);
        }
        return sb.toString();
    }
}
