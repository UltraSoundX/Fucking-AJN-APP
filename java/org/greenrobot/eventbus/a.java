package org.greenrobot.eventbus;

/* compiled from: AsyncPoster */
class a implements Runnable {
    private final i a = new i();
    private final c b;

    a(c cVar) {
        this.b = cVar;
    }

    public void a(n nVar, Object obj) {
        this.a.a(h.a(nVar, obj));
        this.b.b().execute(this);
    }

    public void run() {
        h a2 = this.a.a();
        if (a2 == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a2);
    }
}
