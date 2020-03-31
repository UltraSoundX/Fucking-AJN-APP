package com.sensetime.senseid.sdk.liveness.interactive.common.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.stub.StubApp;
import java.io.File;

public final class DeviceUtil {
    public static final int ROOT_NO = 2;
    public static final int ROOT_UNKNOWN = 3;
    public static final int ROOT_YES = 1;

    private DeviceUtil() {
    }

    public static Size getScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay();
        return new Size(defaultDisplay.getWidth(), defaultDisplay.getHeight());
    }

    public static int isRoot() {
        try {
            String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
            for (int i = 0; i < 5; i++) {
                if (new File(strArr[i] + "su").exists()) {
                    return 1;
                }
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }
}
