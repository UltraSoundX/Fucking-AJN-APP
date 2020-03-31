package com.e23.ajn.d;

import android.app.Activity;
import android.util.DisplayMetrics;

/* compiled from: ScreenSize */
public class q {
    public static final DisplayMetrics a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static final int b(Activity activity) {
        return a(activity).widthPixels;
    }
}
