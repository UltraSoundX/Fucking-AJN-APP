package com.e23.ajn.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import com.baidu.mobstat.StatService;
import com.zhy.http.okhttp.OkHttpUtils;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.d;
import me.yokeyword.fragmentation.f;
import me.yokeyword.fragmentation.g;

public class BaseSupportFragment extends Fragment implements d {
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
        OkHttpUtils.getInstance().cancelTag(this);
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
        StatService.onPageStart(this.c, getClass().getName());
    }

    public void d() {
        this.b.f();
        StatService.onPageEnd(this.c, getClass().getName());
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

    public void a(int i, d dVar) {
        this.b.a(i, dVar);
    }

    public void a(int i, int i2, d... dVarArr) {
        this.b.a(i, i2, dVarArr);
    }

    public void a(d dVar, d dVar2) {
        this.b.a(dVar, dVar2);
    }

    public void a(d dVar) {
        this.b.a(dVar);
    }

    public void g() {
        this.b.k();
    }

    public void a(Class<?> cls, boolean z) {
        this.b.a(cls, z);
    }

    public <T extends d> T a(Class<T> cls) {
        return g.a(getFragmentManager(), cls);
    }

    public <T extends d> T b(Class<T> cls) {
        return g.a(getChildFragmentManager(), cls);
    }
}
