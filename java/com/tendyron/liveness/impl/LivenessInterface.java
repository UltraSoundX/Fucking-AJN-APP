package com.tendyron.liveness.impl;

import android.app.Activity;
import android.content.Intent;
import java.util.List;

public interface LivenessInterface {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    public static final int LIVENESS_BLINK = 0;
    public static final int LIVENESS_EASY = 1;
    public static final int LIVENESS_HARD = 3;
    public static final int LIVENESS_HEADNOD = 3;
    public static final int LIVENESS_HEADYAW = 2;
    public static final int LIVENESS_HELL = 4;
    public static final int LIVENESS_MOUTH = 1;
    public static final int LIVENESS_NORMAL = 2;
    public static final String VERSION = "v1.0.1";

    String getLivenessErrorMessage(int i);

    List<byte[]> getLivenessResultImages(Intent intent, int i);

    int[] getSequences(int i);

    String getVersion();

    void startLivenessActivityForResult(Activity activity, int i, int i2, boolean z, int[] iArr);

    void startLivenessActivityForResult(Activity activity, int i, int i2, boolean z, int[] iArr, int i3);
}
