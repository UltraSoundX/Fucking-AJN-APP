package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class TbsPVConfig extends TbsBaseConfig {
    private static TbsPVConfig b;
    public SharedPreferences mPreferences;

    public interface TbsPVConfigKey {
        public static final String KEY_DISABLED_CORE_VERSION = "disabled_core_version";
        public static final String KEY_EMERGENT_CORE_VERSION = "emergent_core_version";
        public static final String KEY_ENABLE_NO_SHARE_GRAY = "enable_no_share_gray";
        public static final String KEY_GET_LOCALCOREVERSION_MORETIMES = "get_localcoreversion_moretimes";
        public static final String KEY_IS_DISABLE_HOST_BACKUP_CORE = "disable_host_backup";
        public static final String KEY_READ_APK = "read_apk";
        public static final String KEY_TBS_CORE_SANDBOX_MODE_ENABLE = "tbs_core_sandbox_mode_enable";
    }

    private TbsPVConfig() {
    }

    public String getConfigFileName() {
        return "tbs_pv_config";
    }

    public static synchronized TbsPVConfig getInstance(Context context) {
        TbsPVConfig tbsPVConfig;
        synchronized (TbsPVConfig.class) {
            if (b == null) {
                b = new TbsPVConfig();
                b.init(context);
            }
            tbsPVConfig = b;
        }
        return tbsPVConfig;
    }

    public static synchronized void releaseInstance() {
        synchronized (TbsPVConfig.class) {
            b = null;
        }
    }

    public synchronized int getLocalCoreVersionMoreTimes() {
        int i;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_GET_LOCALCOREVERSION_MORETIMES);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i = 0;
        return i;
    }

    public synchronized int getEmergentCoreVersion() {
        int i;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_EMERGENT_CORE_VERSION);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i = 0;
        return i;
    }

    public synchronized int getReadApk() {
        int i;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_READ_APK);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i = 0;
        return i;
    }

    public synchronized int getDisabledCoreVersion() {
        int i;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_DISABLED_CORE_VERSION);
            if (!TextUtils.isEmpty(str)) {
                i = Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i = 0;
        return i;
    }

    public synchronized boolean isEnableNoCoreGray() {
        boolean z;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_ENABLE_NO_SHARE_GRAY);
            if (!TextUtils.isEmpty(str) && str.equals("true")) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized boolean getTbsCoreSandboxModeEnable() {
        boolean z;
        try {
            if ("true".equals((String) this.a.get(TbsPVConfigKey.KEY_TBS_CORE_SANDBOX_MODE_ENABLE))) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized boolean isDisableHostBackupCore() {
        boolean z;
        try {
            String str = (String) this.a.get(TbsPVConfigKey.KEY_IS_DISABLE_HOST_BACKUP_CORE);
            if (!TextUtils.isEmpty(str) && str.equals("true")) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized void putData(String str, String str2) {
        this.a.put(str, str2);
    }
}
