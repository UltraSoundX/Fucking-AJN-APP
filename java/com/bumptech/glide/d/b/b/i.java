package com.bumptech.glide.d.b.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;

/* compiled from: MemorySizeCalculator */
public class i {
    private final int a;
    private final int b;
    private final Context c;

    /* compiled from: MemorySizeCalculator */
    private static class a implements b {
        private final DisplayMetrics a;

        public a(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        public int a() {
            return this.a.widthPixels;
        }

        public int b() {
            return this.a.heightPixels;
        }
    }

    /* compiled from: MemorySizeCalculator */
    interface b {
        int a();

        int b();
    }

    public i(Context context) {
        this(context, (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME), new a(context.getResources().getDisplayMetrics()));
    }

    i(Context context, ActivityManager activityManager, b bVar) {
        this.c = context;
        int a2 = a(activityManager);
        int a3 = bVar.a() * bVar.b() * 4;
        int i = a3 * 4;
        int i2 = a3 * 2;
        if (i2 + i <= a2) {
            this.b = i2;
            this.a = i;
        } else {
            int round = Math.round(((float) a2) / 6.0f);
            this.b = round * 2;
            this.a = round * 4;
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            Log.d("MemorySizeCalculator", "Calculated memory cache size: " + a(this.b) + " pool size: " + a(this.a) + " memory class limited? " + (i2 + i > a2) + " max size: " + a(a2) + " memoryClass: " + activityManager.getMemoryClass() + " isLowMemoryDevice: " + b(activityManager));
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    private static int a(ActivityManager activityManager) {
        return Math.round((b(activityManager) ? 0.33f : 0.4f) * ((float) (activityManager.getMemoryClass() * 1024 * 1024)));
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.c, (long) i);
    }

    @TargetApi(19)
    private static boolean b(ActivityManager activityManager) {
        if (VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return VERSION.SDK_INT < 11;
    }
}
