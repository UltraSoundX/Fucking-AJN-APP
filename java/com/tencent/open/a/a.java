package com.tencent.open.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* compiled from: ProGuard */
public class a extends i implements Callback {
    private b a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile g e;
    private volatile g f;
    private volatile g g;
    private volatile g h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    public a(b bVar) {
        this(c.b, true, h.a, bVar);
    }

    public a(int i2, boolean z, h hVar, b bVar) {
        super(i2, z, hVar);
        this.i = false;
        a(bVar);
        this.e = new g();
        this.f = new g();
        this.g = this.e;
        this.h = this.f;
        this.d = new char[bVar.d()];
        f();
        this.j = new HandlerThread(bVar.c(), bVar.f());
        if (this.j != null) {
            this.j.start();
        }
        if (this.j.isAlive() && this.j.getLooper() != null) {
            this.k = new Handler(this.j.getLooper(), this);
        }
    }

    public void a() {
        if (this.k.hasMessages(1024)) {
            this.k.removeMessages(1024);
        }
        this.k.sendEmptyMessage(1024);
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Thread thread, long j2, String str, String str2, Throwable th) {
        a(d().a(i2, thread, j2, str, str2, th));
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.g.a(str);
        if (this.g.a() >= b().d()) {
            a();
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1024:
                e();
                break;
        }
        return true;
    }

    private void e() {
        if (Thread.currentThread() == this.j && !this.i) {
            this.i = true;
            h();
            try {
                this.h.a(f(), this.d);
            } catch (IOException e2) {
            } finally {
                this.h.b();
            }
            this.i = false;
        }
    }

    private Writer f() {
        File a2 = b().a();
        if ((a2 != null && !a2.equals(this.c)) || (this.b == null && a2 != null)) {
            this.c = a2;
            g();
            try {
                this.b = new FileWriter(this.c, true);
            } catch (IOException e2) {
                return null;
            }
        }
        return this.b;
    }

    private void g() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void h() {
        synchronized (this) {
            if (this.g == this.e) {
                this.g = this.f;
                this.h = this.e;
            } else {
                this.g = this.e;
                this.h = this.f;
            }
        }
    }

    public b b() {
        return this.a;
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
