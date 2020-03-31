package com.tendyron.liveness.motion.view;

import android.os.CountDownTimer;

/* compiled from: TimeViewContoller */
public class d {
    /* access modifiers changed from: private */
    public b a;
    private CountDownTimer b = new CountDownTimer((long) (this.d * 1000), 50) {
        public void onTick(long j) {
            d.this.c = ((float) d.this.d) - (((float) j) / 1000.0f);
            d.this.a.setProgress(d.this.c);
        }

        public void onFinish() {
            d.this.a.setProgress((float) d.this.d);
            d.this.e();
        }
    };
    /* access modifiers changed from: private */
    public float c;
    /* access modifiers changed from: private */
    public int d = this.a.getMaxTime();
    private boolean e;
    private a f;

    /* compiled from: TimeViewContoller */
    public interface a {
        void a();
    }

    public d(b bVar) {
        this.a = bVar;
    }

    public void a() {
        this.e = true;
        this.b.cancel();
    }

    public void b() {
        a(true);
    }

    public void a(boolean z) {
        if (z) {
            f();
        } else if (this.e) {
            this.e = false;
            if (this.c > ((float) this.d)) {
                e();
                return;
            }
            this.b.cancel();
            this.b.start();
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.f != null) {
            this.f.a();
        }
        if (!this.e) {
            c();
        }
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    private void f() {
        this.c = 0.0f;
        this.a.setProgress(this.c);
        d();
        this.b.cancel();
        this.b.start();
    }

    public void c() {
        this.e = true;
        this.b.cancel();
        this.a.a();
    }

    public void d() {
        this.e = false;
        this.a.b();
    }
}
