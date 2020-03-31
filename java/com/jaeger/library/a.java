package com.jaeger.library;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: StatusBarUtil */
public class a {
    private static final int a = R.id.statusbarutil_fake_status_bar_view;
    private static final int b = R.id.statusbarutil_translucent_view;

    public static void a(Activity activity, View view) {
        a(activity, 0, view);
    }

    public static void a(Activity activity, int i, View view) {
        if (VERSION.SDK_INT >= 19) {
            c(activity);
            a(activity, i);
            if (view != null) {
                Object tag = view.getTag(-123);
                if (tag == null || !((Boolean) tag).booleanValue()) {
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + a((Context) activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                    view.setTag(-123, Boolean.valueOf(true));
                }
            }
        }
    }

    public static void b(Activity activity, int i, View view) {
        a(activity, i, view);
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
            b(activity);
        }
    }

    @TargetApi(23)
    public static void a(Activity activity) {
        a(activity, true);
        b(activity, true);
        if (VERSION.SDK_INT >= 23) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }
    }

    private static void a(Activity activity, boolean z) {
        int i = 0;
        Class cls = activity.getWindow().getClass();
        try {
            Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            if (z) {
                i = i2;
            }
            objArr[0] = Integer.valueOf(i);
            objArr[1] = Integer.valueOf(i2);
            method.invoke(window, objArr);
        } catch (Exception e) {
        }
    }

    private static void b(Activity activity, boolean z) {
        int i;
        try {
            LayoutParams attributes = activity.getWindow().getAttributes();
            Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i2 = declaredField.getInt(null);
            int i3 = declaredField2.getInt(attributes);
            if (z) {
                i = i2 | i3;
            } else {
                i = (i2 ^ -1) & i3;
            }
            declaredField2.setInt(attributes, i);
            activity.getWindow().setAttributes(attributes);
        } catch (Exception e) {
        }
    }

    @TargetApi(19)
    private static void b(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View findViewById = viewGroup.findViewById(a);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
            ((ViewGroup) ((ViewGroup) activity.findViewById(16908290)).getChildAt(0)).setPadding(0, 0, 0, 0);
        }
    }

    private static void a(Activity activity, int i) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        View findViewById = viewGroup.findViewById(b);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundColor(Color.argb(i, 0, 0, 0));
            return;
        }
        viewGroup.addView(b(activity, i));
    }

    private static void c(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (VERSION.SDK_INT >= 19) {
            activity.getWindow().setFlags(67108864, 67108864);
        }
    }

    private static View b(Activity activity, int i) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, a((Context) activity)));
        view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        view.setId(b);
        return view;
    }

    private static int a(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }
}
