package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import com.bumptech.glide.l;
import java.util.HashSet;

@TargetApi(11)
public class RequestManagerFragment extends Fragment {
    private final a a;
    private final k b;
    private l c;
    private final HashSet<RequestManagerFragment> d;
    private RequestManagerFragment e;

    private class a implements k {
        private a() {
        }
    }

    public RequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(a aVar) {
        this.b = new a();
        this.d = new HashSet<>();
        this.a = aVar;
    }

    public void a(l lVar) {
        this.c = lVar;
    }

    /* access modifiers changed from: 0000 */
    public a a() {
        return this.a;
    }

    public l b() {
        return this.c;
    }

    public k c() {
        return this.b;
    }

    private void a(RequestManagerFragment requestManagerFragment) {
        this.d.add(requestManagerFragment);
    }

    private void b(RequestManagerFragment requestManagerFragment) {
        this.d.remove(requestManagerFragment);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = j.a().a(getActivity().getFragmentManager());
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
        this.a.a();
    }

    public void onStop() {
        super.onStop();
        this.a.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.c();
    }

    public void onTrimMemory(int i) {
        if (this.c != null) {
            this.c.a(i);
        }
    }

    public void onLowMemory() {
        if (this.c != null) {
            this.c.a();
        }
    }
}
