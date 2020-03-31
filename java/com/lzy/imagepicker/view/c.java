package com.lzy.imagepicker.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* compiled from: SystemBarTintManager */
public class c {
    /* access modifiers changed from: private */
    public static String a;
    private final a b;
    private boolean c;
    private boolean d;
    private boolean e;
    private View f;
    private View g;

    /* compiled from: SystemBarTintManager */
    public static class a {
        private final boolean a;
        private final boolean b;
        private final int c;
        private final int d;
        private final boolean e;
        private final int f;
        private final int g;
        private final boolean h;
        private final float i;

        private a(Activity activity, boolean z, boolean z2) {
            boolean z3 = true;
            Resources resources = activity.getResources();
            this.h = resources.getConfiguration().orientation == 1;
            this.i = a(activity);
            this.c = a(resources, "status_bar_height");
            this.d = a((Context) activity);
            this.f = b(activity);
            this.g = c(activity);
            if (this.f <= 0) {
                z3 = false;
            }
            this.e = z3;
            this.a = z;
            this.b = z2;
        }

        @TargetApi(14)
        private int a(Context context) {
            if (VERSION.SDK_INT < 14) {
                return 0;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843499, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        @TargetApi(14)
        private int b(Context context) {
            String str;
            Resources resources = context.getResources();
            if (VERSION.SDK_INT < 14 || !d(context)) {
                return 0;
            }
            if (this.h) {
                str = "navigation_bar_height";
            } else {
                str = "navigation_bar_height_landscape";
            }
            return a(resources, str);
        }

        @TargetApi(14)
        private int c(Context context) {
            Resources resources = context.getResources();
            if (VERSION.SDK_INT < 14 || !d(context)) {
                return 0;
            }
            return a(resources, "navigation_bar_width");
        }

        @TargetApi(14)
        private boolean d(Context context) {
            boolean z = true;
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier != 0) {
                boolean z2 = resources.getBoolean(identifier);
                if ("1".equals(c.a)) {
                    return false;
                }
                if ("0".equals(c.a)) {
                    return true;
                }
                return z2;
            }
            if (ViewConfiguration.get(context).hasPermanentMenuKey()) {
                z = false;
            }
            return z;
        }

        private int a(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @SuppressLint({"NewApi"})
        private float a(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            return Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
        }

        public boolean a() {
            return this.i >= 600.0f || this.h;
        }

        public int b() {
            return this.c;
        }

        public boolean c() {
            return this.e;
        }

        public int d() {
            return this.f;
        }

        public int e() {
            return this.g;
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                a = (String) declaredMethod.invoke(null, new Object[]{"qemu.hw.mainkeys"});
            } catch (Throwable th) {
                a = null;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @TargetApi(19)
    public c(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.c = obtainStyledAttributes.getBoolean(0, false);
                this.d = obtainStyledAttributes.getBoolean(1, false);
                obtainStyledAttributes.recycle();
                LayoutParams attributes = window.getAttributes();
                if ((67108864 & attributes.flags) != 0) {
                    this.c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.d = true;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        this.b = new a(activity, this.c, this.d);
        if (!this.b.c()) {
            this.d = false;
        }
        if (this.c) {
            a(activity, viewGroup);
        }
        if (this.d) {
            b(activity, viewGroup);
        }
    }

    public void a(boolean z) {
        this.e = z;
        if (this.c) {
            this.f.setVisibility(z ? 0 : 8);
        }
    }

    public void a(int i) {
        if (this.c) {
            this.f.setBackgroundResource(i);
        }
    }

    private void a(Context context, ViewGroup viewGroup) {
        this.f = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.b.b());
        layoutParams.gravity = 48;
        if (this.d && !this.b.a()) {
            layoutParams.rightMargin = this.b.e();
        }
        this.f.setLayoutParams(layoutParams);
        this.f.setBackgroundColor(-1728053248);
        this.f.setVisibility(8);
        viewGroup.addView(this.f);
    }

    private void b(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        this.g = new View(context);
        if (this.b.a()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.b.d());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.b.e(), -1);
            layoutParams.gravity = 5;
        }
        this.g.setLayoutParams(layoutParams);
        this.g.setBackgroundColor(-1728053248);
        this.g.setVisibility(8);
        viewGroup.addView(this.g);
    }
}
