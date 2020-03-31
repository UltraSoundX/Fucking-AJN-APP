package com.baidu.location.indoor;

class e implements Runnable {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void run() {
        try {
            this.a.a(this.a.l);
        } catch (Exception e) {
        }
        if (this.a.f != null && this.a.f.isEnabled()) {
            this.a.a(false);
        }
        this.a.l.clear();
    }
}
