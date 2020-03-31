package android.support.v7.widget;

import android.support.v4.os.TraceCompat;
import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* compiled from: GapWorker */
final class aa implements Runnable {
    static final ThreadLocal<aa> a = new ThreadLocal<>();
    static Comparator<b> e = new Comparator<b>() {
        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            int i = -1;
            if ((bVar.d == null) != (bVar2.d == null)) {
                return bVar.d == null ? 1 : -1;
            }
            if (bVar.a != bVar2.a) {
                if (!bVar.a) {
                    i = 1;
                }
                return i;
            }
            int i2 = bVar2.b - bVar.b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = bVar.c - bVar2.c;
            if (i3 == 0) {
                return 0;
            }
            return i3;
        }
    };
    ArrayList<RecyclerView> b = new ArrayList<>();
    long c;
    long d;
    private ArrayList<b> f = new ArrayList<>();

    /* compiled from: GapWorker */
    static class a implements android.support.v7.widget.RecyclerView.i.a {
        int a;
        int b;
        int[] c;
        int d;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        /* access modifiers changed from: 0000 */
        public void a(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            i iVar = recyclerView.n;
            if (recyclerView.m != null && iVar != null && iVar.s()) {
                if (z) {
                    if (!recyclerView.f.d()) {
                        iVar.a(recyclerView.m.getItemCount(), (android.support.v7.widget.RecyclerView.i.a) this);
                    }
                } else if (!recyclerView.v()) {
                    iVar.a(this.a, this.b, recyclerView.D, (android.support.v7.widget.RecyclerView.i.a) this);
                }
                if (this.d > iVar.x) {
                    iVar.x = this.d;
                    iVar.y = z;
                    recyclerView.e.b();
                }
            }
        }

        public void b(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            } else {
                int i3 = this.d * 2;
                if (this.c == null) {
                    this.c = new int[4];
                    Arrays.fill(this.c, -1);
                } else if (i3 >= this.c.length) {
                    int[] iArr = this.c;
                    this.c = new int[(i3 * 2)];
                    System.arraycopy(iArr, 0, this.c, 0, iArr.length);
                }
                this.c[i3] = i;
                this.c[i3 + 1] = i2;
                this.d++;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(int i) {
            if (this.c == null) {
                return false;
            }
            int i2 = this.d * 2;
            for (int i3 = 0; i3 < i2; i3 += 2) {
                if (this.c[i3] == i) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            this.d = 0;
        }
    }

    /* compiled from: GapWorker */
    static class b {
        public boolean a;
        public int b;
        public int c;
        public RecyclerView d;
        public int e;

        b() {
        }

        public void a() {
            this.a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = 0;
        }
    }

    aa() {
    }

    public void a(RecyclerView recyclerView) {
        this.b.add(recyclerView);
    }

    public void b(RecyclerView recyclerView) {
        this.b.remove(recyclerView);
    }

    /* access modifiers changed from: 0000 */
    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.c == 0) {
            this.c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.C.a(i, i2);
    }

    private void a() {
        b bVar;
        boolean z;
        int i;
        int size = this.b.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            RecyclerView recyclerView = (RecyclerView) this.b.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.C.a(recyclerView, false);
                i = recyclerView.C.d + i3;
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        this.f.ensureCapacity(i3);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = (RecyclerView) this.b.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                a aVar = recyclerView2.C;
                int abs = Math.abs(aVar.a) + Math.abs(aVar.b);
                int i6 = i4;
                for (int i7 = 0; i7 < aVar.d * 2; i7 += 2) {
                    if (i6 >= this.f.size()) {
                        bVar = new b();
                        this.f.add(bVar);
                    } else {
                        bVar = (b) this.f.get(i6);
                    }
                    int i8 = aVar.c[i7 + 1];
                    if (i8 <= abs) {
                        z = true;
                    } else {
                        z = false;
                    }
                    bVar.a = z;
                    bVar.b = abs;
                    bVar.c = i8;
                    bVar.d = recyclerView2;
                    bVar.e = aVar.c[i7];
                    i6++;
                }
                i4 = i6;
            }
        }
        Collections.sort(this.f, e);
    }

    static boolean a(RecyclerView recyclerView, int i) {
        int c2 = recyclerView.g.c();
        for (int i2 = 0; i2 < c2; i2++) {
            v e2 = RecyclerView.e(recyclerView.g.d(i2));
            if (e2.mPosition == i && !e2.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private v a(RecyclerView recyclerView, int i, long j) {
        if (a(recyclerView, i)) {
            return null;
        }
        o oVar = recyclerView.e;
        try {
            recyclerView.l();
            v a2 = oVar.a(i, false, j);
            if (a2 != null) {
                if (!a2.isBound() || a2.isInvalid()) {
                    oVar.a(a2, false);
                } else {
                    oVar.a(a2.itemView);
                }
            }
            return a2;
        } finally {
            recyclerView.b(false);
        }
    }

    private void a(RecyclerView recyclerView, long j) {
        if (recyclerView != null) {
            if (recyclerView.x && recyclerView.g.c() != 0) {
                recyclerView.c();
            }
            a aVar = recyclerView.C;
            aVar.a(recyclerView, true);
            if (aVar.d != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    recyclerView.D.a(recyclerView.m);
                    for (int i = 0; i < aVar.d * 2; i += 2) {
                        a(recyclerView, aVar.c[i], j);
                    }
                } finally {
                    TraceCompat.endSection();
                }
            }
        }
    }

    private void a(b bVar, long j) {
        v a2 = a(bVar.d, bVar.e, bVar.a ? Long.MAX_VALUE : j);
        if (a2 != null && a2.mNestedRecyclerView != null && a2.isBound() && !a2.isInvalid()) {
            a((RecyclerView) a2.mNestedRecyclerView.get(), j);
        }
    }

    private void b(long j) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f.size()) {
                b bVar = (b) this.f.get(i2);
                if (bVar.d != null) {
                    a(bVar, j);
                    bVar.a();
                    i = i2 + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(long j) {
        a();
        b(j);
    }

    public void run() {
        long j;
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.b.isEmpty()) {
                int size = this.b.size();
                int i = 0;
                long j2 = 0;
                while (i < size) {
                    RecyclerView recyclerView = (RecyclerView) this.b.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j2);
                    } else {
                        j = j2;
                    }
                    i++;
                    j2 = j;
                }
                if (j2 == 0) {
                    this.c = 0;
                    TraceCompat.endSection();
                    return;
                }
                a(TimeUnit.MILLISECONDS.toNanos(j2) + this.d);
                this.c = 0;
                TraceCompat.endSection();
            }
        } finally {
            this.c = 0;
            TraceCompat.endSection();
        }
    }
}
