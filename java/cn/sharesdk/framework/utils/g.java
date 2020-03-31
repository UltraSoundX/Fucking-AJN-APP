package cn.sharesdk.framework.utils;

import android.content.Context;
import com.mob.tools.utils.ResHelper;
import com.stub.StubApp;

/* compiled from: SizeHelper */
public class g {
    public static float a = 1.5f;
    public static int b = 540;
    private static Context c = null;

    public static void a(Context context) {
        if (c == null || c != StubApp.getOrigApplicationContext(context.getApplicationContext())) {
            c = context;
        }
    }

    public static int a(int i) {
        return ResHelper.designToDevice(c, a, i);
    }

    public static int b(int i) {
        return ResHelper.designToDevice(c, b, i);
    }
}
