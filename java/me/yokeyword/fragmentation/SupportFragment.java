package me.yokeyword.fragmentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class SupportFragment extends Fragment implements d {
    final f b = new f(this);
    /* access modifiers changed from: protected */
    public FragmentActivity c;

    public f b() {
        return this.b;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b.a(activity);
        this.c = this.b.n();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b.a(bundle);
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return this.b.a(i, z, i2);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.b.c(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.b.b(bundle);
    }

    public void onResume() {
        super.onResume();
        this.b.a();
    }

    public void onPause() {
        super.onPause();
        this.b.b();
    }

    public void onDestroyView() {
        this.b.c();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.b.d();
        super.onDestroy();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.b.a(z);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.b.b(z);
    }

    public void a(Bundle bundle) {
        this.b.d(bundle);
    }

    public void b(Bundle bundle) {
        this.b.e(bundle);
    }

    public void c() {
        this.b.e();
    }

    public void d() {
        this.b.f();
    }

    public final boolean e() {
        return this.b.g();
    }

    public FragmentAnimator f() {
        return this.b.h();
    }

    public boolean a() {
        return this.b.j();
    }

    public void a(int i, int i2, Bundle bundle) {
        this.b.a(i, i2, bundle);
    }

    public void c(Bundle bundle) {
        this.b.f(bundle);
    }

    public void a(d dVar) {
        this.b.a(dVar);
    }
}
