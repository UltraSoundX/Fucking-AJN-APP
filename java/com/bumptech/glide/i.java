package com.bumptech.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.d.a;
import com.bumptech.glide.d.b.b.h;
import com.bumptech.glide.d.c.a.a.C0042a;
import com.bumptech.glide.d.c.b.a.C0043a;
import com.bumptech.glide.d.c.c;
import com.bumptech.glide.d.c.l;
import com.bumptech.glide.d.c.m;
import com.bumptech.glide.d.d.a.e;
import com.bumptech.glide.d.d.a.g;
import com.bumptech.glide.d.d.a.j;
import com.bumptech.glide.d.d.a.n;
import com.bumptech.glide.d.d.a.p;
import com.bumptech.glide.d.d.f.d;
import com.bumptech.glide.e.b;
import com.bumptech.glide.g.b.f;
import com.stub.StubApp;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/* compiled from: Glide */
public class i {
    private static volatile i a;
    private final c b;
    private final com.bumptech.glide.d.b.c c;
    private final com.bumptech.glide.d.b.a.c d;
    private final h e;
    private final a f;
    private final f g = new f();
    private final d h = new d();
    private final com.bumptech.glide.f.c i;
    private final e j;
    private final com.bumptech.glide.d.d.e.f k;
    private final com.bumptech.glide.d.d.a.i l;
    private final com.bumptech.glide.d.d.e.f m;
    private final Handler n;
    private final com.bumptech.glide.d.b.d.a o;

    public static i a(Context context) {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                    List<com.bumptech.glide.e.a> a2 = new b(origApplicationContext).a();
                    j jVar = new j(origApplicationContext);
                    for (com.bumptech.glide.e.a a3 : a2) {
                        a3.a(origApplicationContext, jVar);
                    }
                    a = jVar.a();
                    for (com.bumptech.glide.e.a a4 : a2) {
                        a4.a(origApplicationContext, a);
                    }
                }
            }
        }
        return a;
    }

    i(com.bumptech.glide.d.b.c cVar, h hVar, com.bumptech.glide.d.b.a.c cVar2, Context context, a aVar) {
        this.c = cVar;
        this.d = cVar2;
        this.e = hVar;
        this.f = aVar;
        this.b = new c(context);
        this.n = new Handler(Looper.getMainLooper());
        this.o = new com.bumptech.glide.d.b.d.a(hVar, cVar2, aVar);
        this.i = new com.bumptech.glide.f.c();
        p pVar = new p(cVar2, aVar);
        this.i.a(InputStream.class, Bitmap.class, pVar);
        g gVar = new g(cVar2, aVar);
        this.i.a(ParcelFileDescriptor.class, Bitmap.class, gVar);
        n nVar = new n(pVar, gVar);
        this.i.a(com.bumptech.glide.d.c.g.class, Bitmap.class, nVar);
        com.bumptech.glide.d.d.d.c cVar3 = new com.bumptech.glide.d.d.d.c(context, cVar2);
        this.i.a(InputStream.class, com.bumptech.glide.d.d.d.b.class, cVar3);
        this.i.a(com.bumptech.glide.d.c.g.class, com.bumptech.glide.d.d.e.a.class, new com.bumptech.glide.d.d.e.g(nVar, cVar3, cVar2));
        this.i.a(InputStream.class, File.class, new com.bumptech.glide.d.d.c.d());
        a(File.class, ParcelFileDescriptor.class, (m<T, Y>) new C0042a<T,Y>());
        a(File.class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.c.a<T,Y>());
        a(Integer.TYPE, ParcelFileDescriptor.class, (m<T, Y>) new com.bumptech.glide.d.c.a.c.a<T,Y>());
        a(Integer.TYPE, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.e.a<T,Y>());
        a(Integer.class, ParcelFileDescriptor.class, (m<T, Y>) new com.bumptech.glide.d.c.a.c.a<T,Y>());
        a(Integer.class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.e.a<T,Y>());
        a(String.class, ParcelFileDescriptor.class, (m<T, Y>) new com.bumptech.glide.d.c.a.d.a<T,Y>());
        a(String.class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.f.a<T,Y>());
        a(Uri.class, ParcelFileDescriptor.class, (m<T, Y>) new com.bumptech.glide.d.c.a.e.a<T,Y>());
        a(Uri.class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.g.a<T,Y>());
        a(URL.class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.h.a<T,Y>());
        a(com.bumptech.glide.d.c.d.class, InputStream.class, (m<T, Y>) new C0043a<T,Y>());
        a(byte[].class, InputStream.class, (m<T, Y>) new com.bumptech.glide.d.c.b.b.a<T,Y>());
        this.h.a(Bitmap.class, j.class, new com.bumptech.glide.d.d.f.b(context.getResources(), cVar2));
        this.h.a(com.bumptech.glide.d.d.e.a.class, com.bumptech.glide.d.d.b.b.class, new com.bumptech.glide.d.d.f.a(new com.bumptech.glide.d.d.f.b(context.getResources(), cVar2)));
        this.j = new e(cVar2);
        this.k = new com.bumptech.glide.d.d.e.f(cVar2, (com.bumptech.glide.d.g<Bitmap>) this.j);
        this.l = new com.bumptech.glide.d.d.a.i(cVar2);
        this.m = new com.bumptech.glide.d.d.e.f(cVar2, (com.bumptech.glide.d.g<Bitmap>) this.l);
    }

    public com.bumptech.glide.d.b.a.c a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public <Z, R> com.bumptech.glide.d.d.f.c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        return this.h.a(cls, cls2);
    }

    /* access modifiers changed from: 0000 */
    public <T, Z> com.bumptech.glide.f.b<T, Z> b(Class<T> cls, Class<Z> cls2) {
        return this.i.a(cls, cls2);
    }

    /* access modifiers changed from: 0000 */
    public <R> com.bumptech.glide.g.b.j<R> a(ImageView imageView, Class<R> cls) {
        return this.g.a(imageView, cls);
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.d.b.c b() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public e c() {
        return this.j;
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.d.d.a.i d() {
        return this.l;
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.d.d.e.f e() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public com.bumptech.glide.d.d.e.f f() {
        return this.m;
    }

    /* access modifiers changed from: 0000 */
    public a g() {
        return this.f;
    }

    private c j() {
        return this.b;
    }

    public void h() {
        com.bumptech.glide.i.h.a();
        this.e.a();
        this.d.a();
    }

    public void a(int i2) {
        com.bumptech.glide.i.h.a();
        this.e.a(i2);
        this.d.a(i2);
    }

    public void i() {
        com.bumptech.glide.i.h.b();
        b().a();
    }

    public static void a(com.bumptech.glide.g.b.j<?> jVar) {
        com.bumptech.glide.i.h.a();
        com.bumptech.glide.g.b c2 = jVar.c();
        if (c2 != null) {
            c2.d();
            jVar.a((com.bumptech.glide.g.b) null);
        }
    }

    public <T, Y> void a(Class<T> cls, Class<Y> cls2, m<T, Y> mVar) {
        m a2 = this.b.a(cls, cls2, mVar);
        if (a2 != null) {
            a2.a();
        }
    }

    public static <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        if (cls != null) {
            return a(context).j().a(cls, cls2);
        }
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Unable to load null model, setting placeholder only");
        }
        return null;
    }

    public static <T> l<T, InputStream> a(Class<T> cls, Context context) {
        return a(cls, InputStream.class, context);
    }

    public static <T> l<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a(cls, ParcelFileDescriptor.class, context);
    }

    public static l b(Context context) {
        return com.bumptech.glide.manager.j.a().a(context);
    }

    public static l a(Activity activity) {
        return com.bumptech.glide.manager.j.a().a(activity);
    }

    public static l a(FragmentActivity fragmentActivity) {
        return com.bumptech.glide.manager.j.a().a(fragmentActivity);
    }
}
