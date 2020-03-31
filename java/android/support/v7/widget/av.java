package android.support.v7.widget;

import android.os.Build.VERSION;
import android.view.View;

/* compiled from: TooltipCompat */
public class av {
    public static void a(View view, CharSequence charSequence) {
        if (VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            aw.a(view, charSequence);
        }
    }
}
