package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import com.bumptech.glide.l;
import java.util.HashSet;

public class SupportRequestManagerFragment extends Fragment {
    private l a;
    private final a b;
    private final k c;
    private final HashSet<SupportRequestManagerFragment> d;
    private SupportRequestManagerFragment e;

    private class a implements k {
        private a() {
        }
    }

    public SupportRequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(a aVar) {
        this.c = new a();
        this.d = new HashSet<>();
        this.b = aVar;
    }

    public void a(l lVar) {
        this.a = lVar;
    }

    /* access modifiers changed from: 0000 */
    public a a() {
        return this.b;
    }

    public l b() {
        return this.a;
    }

    public k c() {
        return this.c;
    }

    private void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.add(supportRequestManagerFragment);
    }

    private void b(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.remove(supportRequestManagerFragment);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = j.a().a(getActivity().getSupportFragmentManager());
        if (this.e != this) {
            this.e.a(this);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.a != null) {
            this.a.a();
        }
    }
}
