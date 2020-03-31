package com.sensetime.senseid.sdk.liveness.interactive;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Keep;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.PixelFormat;
import com.sensetime.senseid.sdk.liveness.interactive.common.type.Size;
import com.sensetime.senseid.sdk.liveness.interactive.type.BoundInfo;

@Keep
public final class InteractiveLivenessApi {
    private static f sService;

    private InteractiveLivenessApi() {
    }

    public static void cancel() {
        if (sService != null) {
            sService.cancel();
        }
    }

    public static String getLibraryVersion() {
        if (sService == null) {
            return null;
        }
        return sService.getLibraryVersion();
    }

    public static String getVersion() {
        return "3.7.3";
    }

    public static void init(Context context, String str, String str2, String str3, String str4, String str5, OnLivenessListener onLivenessListener) {
        if (sService == null) {
            sService = new f();
        }
        sService.start();
        sService.initialized(context, str, str2, str3, str4, str5, onLivenessListener);
    }

    public static void inputData(byte[] bArr, PixelFormat pixelFormat, Size size, Rect rect, boolean z, int i, BoundInfo boundInfo) {
        if (sService != null) {
            sService.inputData(bArr, pixelFormat, size, rect, z, i, boundInfo);
        }
    }

    public static void release() {
        if (sService != null) {
            sService.release();
            sService = null;
        }
    }

    public static void setBrowOcclusion(boolean z) {
        if (sService != null) {
            sService.setBrowOcclusion(z);
        }
    }

    public static void setDetectTimeout(long j) {
        if (sService != null) {
            sService.setDetectTimeout(j);
        }
    }

    public static void setThreshold(float f) {
        if (sService != null) {
            sService.setThreshold(f);
        }
    }

    public static void start(int[] iArr, int i) {
        if (sService != null) {
            sService.setMotionList(iArr, i);
        }
    }
}
