package me.yokeyword.fragmentation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import me.yokeyword.fragmentation.helper.internal.b;

/* compiled from: ExtraTransaction */
public abstract class a {

    /* renamed from: me.yokeyword.fragmentation.a$a reason: collision with other inner class name */
    /* compiled from: ExtraTransaction */
    static final class C0085a<T extends d> extends a {
        private FragmentActivity a;
        private T b;
        private Fragment c;
        private h d;
        private boolean e;
        private b f = new b();

        C0085a(FragmentActivity fragmentActivity, T t, h hVar, boolean z) {
            this.a = fragmentActivity;
            this.b = t;
            this.c = (Fragment) t;
            this.d = hVar;
            this.e = z;
        }
    }
}
