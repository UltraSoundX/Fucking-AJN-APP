package android.support.transition;

import android.view.View;

/* compiled from: VisibilityPropagation */
public abstract class am extends t {
    private static final String[] a = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    public void a(v vVar) {
        View view = vVar.b;
        Integer num = (Integer) vVar.a.get("android:visibility:visibility");
        if (num == null) {
            num = Integer.valueOf(view.getVisibility());
        }
        vVar.a.put("android:visibilityPropagation:visibility", num);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        iArr[0] = iArr[0] + Math.round(view.getTranslationX());
        iArr[0] = iArr[0] + (view.getWidth() / 2);
        iArr[1] = iArr[1] + Math.round(view.getTranslationY());
        iArr[1] = (view.getHeight() / 2) + iArr[1];
        vVar.a.put("android:visibilityPropagation:center", iArr);
    }

    public String[] a() {
        return a;
    }

    public int b(v vVar) {
        if (vVar == null) {
            return 8;
        }
        Integer num = (Integer) vVar.a.get("android:visibilityPropagation:visibility");
        if (num == null) {
            return 8;
        }
        return num.intValue();
    }

    public int c(v vVar) {
        return a(vVar, 0);
    }

    public int d(v vVar) {
        return a(vVar, 1);
    }

    private static int a(v vVar, int i) {
        if (vVar == null) {
            return -1;
        }
        int[] iArr = (int[]) vVar.a.get("android:visibilityPropagation:center");
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }
}
