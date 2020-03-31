package android.arch.core.a;

import java.util.concurrent.Executor;

/* compiled from: ArchTaskExecutor */
public class a extends c {
    private static volatile a a;
    private static final Executor d = new Executor() {
        public void execute(Runnable runnable) {
            a.a().b(runnable);
        }
    };
    private static final Executor e = new Executor() {
        public void execute(Runnable runnable) {
            a.a().a(runnable);
        }
    };
    private c b = this.c;
    private c c = new b();

    private a() {
    }

    public static a a() {
        if (a != null) {
            return a;
        }
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
        }
        return a;
    }

    public void a(Runnable runnable) {
        this.b.a(runnable);
    }

    public void b(Runnable runnable) {
        this.b.b(runnable);
    }

    public boolean b() {
        return this.b.b();
    }
}
