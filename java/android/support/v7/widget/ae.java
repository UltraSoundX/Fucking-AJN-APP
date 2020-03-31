package android.support.v7.widget;

import java.util.List;

/* compiled from: OpReorderer */
class ae {
    final a a;

    /* compiled from: OpReorderer */
    interface a {
        b a(int i, int i2, int i3, Object obj);

        void a(b bVar);
    }

    ae(a aVar) {
        this.a = aVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(List<b> list) {
        while (true) {
            int b = b(list);
            if (b != -1) {
                a(list, b, b + 1);
            } else {
                return;
            }
        }
    }

    private void a(List<b> list, int i, int i2) {
        b bVar = (b) list.get(i);
        b bVar2 = (b) list.get(i2);
        switch (bVar2.a) {
            case 1:
                c(list, i, bVar, i2, bVar2);
                return;
            case 2:
                a(list, i, bVar, i2, bVar2);
                return;
            case 4:
                b(list, i, bVar, i2, bVar2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(List<b> list, int i, b bVar, int i2, b bVar2) {
        boolean z;
        b bVar3;
        boolean z2 = false;
        if (bVar.b < bVar.d) {
            z = bVar2.b == bVar.b && bVar2.d == bVar.d - bVar.b;
        } else if (bVar2.b == bVar.d + 1 && bVar2.d == bVar.b - bVar.d) {
            z2 = true;
            z = true;
        } else {
            z = false;
            z2 = true;
        }
        if (bVar.d < bVar2.b) {
            bVar2.b--;
        } else if (bVar.d < bVar2.b + bVar2.d) {
            bVar2.d--;
            bVar.a = 2;
            bVar.d = 1;
            if (bVar2.d == 0) {
                list.remove(i2);
                this.a.a(bVar2);
                return;
            }
            return;
        }
        if (bVar.b <= bVar2.b) {
            bVar2.b++;
            bVar3 = null;
        } else if (bVar.b < bVar2.b + bVar2.d) {
            bVar3 = this.a.a(2, bVar.b + 1, (bVar2.b + bVar2.d) - bVar.b, null);
            bVar2.d = bVar.b - bVar2.b;
        } else {
            bVar3 = null;
        }
        if (z) {
            list.set(i, bVar2);
            list.remove(i2);
            this.a.a(bVar);
            return;
        }
        if (z2) {
            if (bVar3 != null) {
                if (bVar.b > bVar3.b) {
                    bVar.b -= bVar3.d;
                }
                if (bVar.d > bVar3.b) {
                    bVar.d -= bVar3.d;
                }
            }
            if (bVar.b > bVar2.b) {
                bVar.b -= bVar2.d;
            }
            if (bVar.d > bVar2.b) {
                bVar.d -= bVar2.d;
            }
        } else {
            if (bVar3 != null) {
                if (bVar.b >= bVar3.b) {
                    bVar.b -= bVar3.d;
                }
                if (bVar.d >= bVar3.b) {
                    bVar.d -= bVar3.d;
                }
            }
            if (bVar.b >= bVar2.b) {
                bVar.b -= bVar2.d;
            }
            if (bVar.d >= bVar2.b) {
                bVar.d -= bVar2.d;
            }
        }
        list.set(i, bVar2);
        if (bVar.b != bVar.d) {
            list.set(i2, bVar);
        } else {
            list.remove(i2);
        }
        if (bVar3 != null) {
            list.add(i, bVar3);
        }
    }

    private void c(List<b> list, int i, b bVar, int i2, b bVar2) {
        int i3 = 0;
        if (bVar.d < bVar2.b) {
            i3 = -1;
        }
        if (bVar.b < bVar2.b) {
            i3++;
        }
        if (bVar2.b <= bVar.b) {
            bVar.b += bVar2.d;
        }
        if (bVar2.b <= bVar.d) {
            bVar.d += bVar2.d;
        }
        bVar2.b = i3 + bVar2.b;
        list.set(i, bVar2);
        list.set(i2, bVar);
    }

    /* access modifiers changed from: 0000 */
    public void b(List<b> list, int i, b bVar, int i2, b bVar2) {
        b bVar3;
        b bVar4 = null;
        if (bVar.d < bVar2.b) {
            bVar2.b--;
            bVar3 = null;
        } else if (bVar.d < bVar2.b + bVar2.d) {
            bVar2.d--;
            bVar3 = this.a.a(4, bVar.b, 1, bVar2.c);
        } else {
            bVar3 = null;
        }
        if (bVar.b <= bVar2.b) {
            bVar2.b++;
        } else if (bVar.b < bVar2.b + bVar2.d) {
            int i3 = (bVar2.b + bVar2.d) - bVar.b;
            bVar4 = this.a.a(4, bVar.b + 1, i3, bVar2.c);
            bVar2.d -= i3;
        }
        list.set(i2, bVar);
        if (bVar2.d > 0) {
            list.set(i, bVar2);
        } else {
            list.remove(i);
            this.a.a(bVar2);
        }
        if (bVar3 != null) {
            list.add(i, bVar3);
        }
        if (bVar4 != null) {
            list.add(i, bVar4);
        }
    }

    private int b(List<b> list) {
        boolean z;
        boolean z2 = false;
        int size = list.size() - 1;
        while (size >= 0) {
            if (((b) list.get(size)).a != 8) {
                z = true;
            } else if (z2) {
                return size;
            } else {
                z = z2;
            }
            size--;
            z2 = z;
        }
        return -1;
    }
}
