package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.i;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;

/* compiled from: ScrollbarHelper */
class ak {
    static int a(s sVar, af afVar, View view, View view2, i iVar, boolean z, boolean z2) {
        int max;
        if (iVar.y() == 0 || sVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(iVar.d(view), iVar.d(view2));
        int max2 = Math.max(iVar.d(view), iVar.d(view2));
        if (z2) {
            max = Math.max(0, (sVar.e() - max2) - 1);
        } else {
            max = Math.max(0, min);
        }
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(afVar.b(view2) - afVar.a(view))) / ((float) (Math.abs(iVar.d(view) - iVar.d(view2)) + 1)))) + ((float) (afVar.c() - afVar.a(view))));
    }

    static int a(s sVar, af afVar, View view, View view2, i iVar, boolean z) {
        if (iVar.y() == 0 || sVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(iVar.d(view) - iVar.d(view2)) + 1;
        }
        return Math.min(afVar.f(), afVar.b(view2) - afVar.a(view));
    }

    static int b(s sVar, af afVar, View view, View view2, i iVar, boolean z) {
        if (iVar.y() == 0 || sVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return sVar.e();
        }
        return (int) ((((float) (afVar.b(view2) - afVar.a(view))) / ((float) (Math.abs(iVar.d(view) - iVar.d(view2)) + 1))) * ((float) sVar.e()));
    }
}
