package android.support.v4.content.pm;

import android.content.pm.PackageInfo;
import android.os.Build.VERSION;

public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo packageInfo) {
        if (VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return (long) packageInfo.versionCode;
    }

    private PackageInfoCompat() {
    }
}
