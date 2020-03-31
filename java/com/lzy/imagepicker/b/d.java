package com.lzy.imagepicker.b;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/* compiled from: Utils */
public class d {
    public static int a(Context context) {
        char c = 65535;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
    }

    public static int a(Activity activity) {
        int i = 3;
        int i2 = activity.getResources().getDisplayMetrics().widthPixels;
        int i3 = i2 / activity.getResources().getDisplayMetrics().densityDpi;
        if (i3 >= 3) {
            i = i3;
        }
        return (i2 - (((int) (2.0f * activity.getResources().getDisplayMetrics().density)) * (i - 1))) / i;
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static DisplayMetrics b(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static boolean b(Context context) {
        if (VERSION.SDK_INT >= 17) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics2);
            return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
        } else if (VERSION.SDK_INT < 14) {
            return true;
        } else {
            boolean hasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
            if (hasPermanentMenuKey || deviceHasKey) {
                return false;
            }
            return true;
        }
    }

    public static int c(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
