package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.ResHelper;
import java.io.File;

/* compiled from: Locks */
public class d {
    public static final Object a = new Object();

    public static synchronized File a(String str) {
        File cacheRootFile;
        synchronized (d.class) {
            cacheRootFile = ResHelper.getCacheRootFile(MobSDK.getContext(), str);
        }
        return cacheRootFile;
    }

    public static boolean a(File file, LockAction lockAction) {
        return a(file, true, lockAction);
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.endsWith("comm/locks/.globalLock")) {
            return "comm/locks/.globalLock";
        }
        if (str.endsWith("comm/locks/.dhlock")) {
            return "comm/locks/.dhlock";
        }
        if (str.endsWith("comm/locks/.mrlock")) {
            return "comm/locks/.mrlock";
        }
        if (str.endsWith("comm/locks/.rc_lock")) {
            return "comm/locks/.rc_lock";
        }
        if (str.endsWith("comm/locks/.artc_lock")) {
            return "comm/locks/.artc_lock";
        }
        if (str.endsWith("comm/locks/.lesd_lock")) {
            return "comm/locks/.lesd_lock";
        }
        if (str.endsWith("comm/locks/.dic_lock")) {
            return "comm/locks/.dic_lock";
        }
        if (str.endsWith("comm/locks/.pkgs_lock")) {
            return "comm/locks/.pkgs_lock";
        }
        if (str.endsWith("comm/locks/.pkg_lock")) {
            return "comm/locks/.pkg_lock";
        }
        if (str.endsWith("comm/locks/.ss_lock")) {
            return "comm/locks/.ss_lock";
        }
        if (str.endsWith("comm/locks/.im_lock")) {
            return "comm/locks/.im_lock";
        }
        if (str.endsWith("comm/locks/.mph_lock")) {
            return "comm/locks/.mph_lock";
        }
        if (str.endsWith("comm/locks/.gm_lock")) {
            return "comm/locks/.gm_lock";
        }
        if (str.endsWith("comm/locks/.cz_lock")) {
            return "comm/locks/.cz_lock";
        }
        if (str.endsWith("comm/locks/.du_lock")) {
            return "comm/locks/.du_lock";
        }
        if (str.endsWith("comm/locks/.bs_lock")) {
            return "comm/locks/.bs_lock";
        }
        if (str.endsWith("comm/locks/.dy_lock")) {
            return "comm/locks/.dy_lock";
        }
        if (str.endsWith("comm/locks/.at_lock")) {
            return "comm/locks/.at_lock";
        }
        return str;
    }

    public static boolean a(File file, boolean z, LockAction lockAction) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            String absolutePath = file.getAbsolutePath();
            synchronized (b(absolutePath)) {
                FileLocker fileLocker = new FileLocker();
                fileLocker.setLockFile(absolutePath);
                if (!fileLocker.lock(z)) {
                    return false;
                }
                if (!lockAction.run(fileLocker)) {
                    fileLocker.release();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return true;
    }
}
