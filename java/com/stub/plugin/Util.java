package com.stub.plugin;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.WindowManager.LayoutParams;

public class Util {
    public static void setThemeWithSdkVersion(Activity activity) {
        if (VERSION.SDK_INT >= 14) {
            activity.setTheme(16973941);
        } else {
            activity.setTheme(16973835);
        }
        LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = 0.0f;
        activity.getWindow().setAttributes(attributes);
    }
}
