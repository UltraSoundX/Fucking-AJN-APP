package com.e23.ajn.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import me.yokeyword.fragmentation.a;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.c;
import me.yokeyword.fragmentation.d;
import me.yokeyword.fragmentation.e;
import me.yokeyword.fragmentation.g;

public class BaseSupportActivity extends AppCompatActivity implements c {
    final e a = new e(this);

    public e getSupportDelegate() {
        return this.a;
    }

    public a extraTransaction() {
        return this.a.a();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a.a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.a.b(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        this.a.h();
        super.onDestroy();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.a.a(motionEvent) || super.dispatchTouchEvent(motionEvent);
    }

    public final void onBackPressed() {
        this.a.f();
    }

    public void onBackPressedSupport() {
        this.a.g();
    }

    public FragmentAnimator getFragmentAnimator() {
        return this.a.c();
    }

    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.a.a(fragmentAnimator);
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return this.a.d();
    }

    public void post(Runnable runnable) {
        this.a.a(runnable);
    }

    public void loadRootFragment(int i, d dVar) {
        this.a.a(i, dVar);
    }

    public void loadRootFragment(int i, d dVar, boolean z, boolean z2) {
        this.a.a(i, dVar, z, z2);
    }

    public void loadMultipleRootFragment(int i, int i2, d... dVarArr) {
        this.a.a(i, i2, dVarArr);
    }

    public void showHideFragment(d dVar) {
        this.a.a(dVar);
    }

    public void showHideFragment(d dVar, d dVar2) {
        this.a.a(dVar, dVar2);
    }

    public void start(d dVar) {
        this.a.b(dVar);
    }

    public void start(d dVar, int i) {
        this.a.a(dVar, i);
    }

    public void startForResult(d dVar, int i) {
        this.a.b(dVar, i);
    }

    public void startWithPop(d dVar) {
        this.a.c(dVar);
    }

    public void startWithPopTo(d dVar, Class<?> cls, boolean z) {
        this.a.a(dVar, cls, z);
    }

    public void replaceFragment(d dVar, boolean z) {
        this.a.a(dVar, z);
    }

    public void pop() {
        this.a.i();
    }

    public void popTo(Class<?> cls, boolean z) {
        this.a.a(cls, z);
    }

    public void popTo(Class<?> cls, boolean z, Runnable runnable) {
        this.a.a(cls, z, runnable);
    }

    public void popTo(Class<?> cls, boolean z, Runnable runnable, int i) {
        this.a.a(cls, z, runnable, i);
    }

    public void setDefaultFragmentBackground(int i) {
        this.a.a(i);
    }

    public d getTopFragment() {
        return g.a(getSupportFragmentManager());
    }

    public <T extends d> T findFragment(Class<T> cls) {
        return g.a(getSupportFragmentManager(), cls);
    }
}
