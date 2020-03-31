package cn.qqtheme.framework.b;

import android.content.Context;

/* compiled from: ConvertUtils */
public class a {
    public static int a(Context context, float f) {
        int i = (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
        b.a(f + " dp == " + i + " px");
        return i;
    }
}
