package com.zhy.view.flowlayout;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;

/* compiled from: TagAdapter */
public abstract class a<T> {
    private List<T> a;
    private C0084a b;
    @Deprecated
    private HashSet<Integer> c = new HashSet<>();

    /* renamed from: com.zhy.view.flowlayout.a$a reason: collision with other inner class name */
    /* compiled from: TagAdapter */
    interface C0084a {
    }

    public abstract View a(FlowLayout flowLayout, int i, T t);

    public a(List<T> list) {
        this.a = list;
    }

    /* access modifiers changed from: 0000 */
    public void a(C0084a aVar) {
        this.b = aVar;
    }

    /* access modifiers changed from: 0000 */
    @Deprecated
    public HashSet<Integer> a() {
        return this.c;
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public T a(int i) {
        return this.a.get(i);
    }

    public void a(int i, View view) {
        Log.d("zhy", "onSelected " + i);
    }

    public void b(int i, View view) {
        Log.d("zhy", "unSelected " + i);
    }

    public boolean a(int i, T t) {
        return false;
    }
}
