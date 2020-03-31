package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.media.session.PlaybackStateCompat;
import com.stub.StubApp;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TbsDownloadConfig {
    public static final int CMD_ID_DOWNLOAD_FILE = 101;
    public static final int CMD_ID_FILE_UPLOAD = 100;
    public static final long DEFAULT_RETRY_INTERVAL_SEC = 86400;
    public static final int ERROR_DOWNLOAD = 2;
    public static final int ERROR_INSTALL = 5;
    public static final int ERROR_LOAD = 6;
    public static final int ERROR_NONE = 1;
    public static final int ERROR_REPORTED = 0;
    public static final int ERROR_UNZIP = 4;
    public static final int ERROR_VERIFY = 3;
    private static TbsDownloadConfig b;
    Map<String, Object> a = new HashMap();
    private Context c;
    public SharedPreferences mPreferences;

    public interface TbsConfigKey {
        public static final String KEY_APP_METADATA = "app_metadata";
        public static final String KEY_APP_VERSIONCODE = "app_versioncode";
        public static final String KEY_APP_VERSIONCODE_FOR_SWITCH = "app_versioncode_for_switch";
        public static final String KEY_APP_VERSIONNAME = "app_versionname";
        public static final String KEY_BACKUPCORE_DELFILELIST = "backupcore_delfilelist";
        public static final String KEY_COUNT_REQUEST_FAIL_IN_24HOURS = "count_request_fail_in_24hours";
        public static final String KEY_DECOUPLECOREVERSION = "tbs_decouplecoreversion";
        public static final String KEY_DESkEY_TOKEN = "tbs_deskey_token";
        public static final String KEY_DEVICE_CPUABI = "device_cpuabi";
        public static final String KEY_DOWNLOADDECOUPLECORE = "tbs_downloaddecouplecore";
        public static final String KEY_DOWNLOADURL_LIST = "tbs_downloadurl_list";
        public static final String KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES = "tbs_download_failed_max_retrytimes";
        public static final String KEY_DOWNLOAD_FAILED_RETRYTIMES = "tbs_download_failed_retrytimes";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE = "tbs_download_interrupt_code";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE_REASON = "tbs_download_interrupt_code_reason";
        public static final String KEY_DOWNLOAD_INTERRUPT_TIME = "tbs_download_interrupt_time";
        public static final String KEY_DOWNLOAD_MAXFLOW = "tbs_download_maxflow";
        public static final String KEY_DOWNLOAD_MIN_FREE_SPACE = "tbs_download_min_free_space";
        public static final String KEY_DOWNLOAD_SINGLE_TIMEOUT = "tbs_single_timeout";
        public static final String KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES = "tbs_download_success_max_retrytimes";
        public static final String KEY_DOWNLOAD_SUCCESS_RETRYTIMES = "tbs_download_success_retrytimes";
        public static final String KEY_FULL_PACKAGE = "request_full_package";
        public static final String KEY_INSTALL_INTERRUPT_CODE = "tbs_install_interrupt_code";
        public static final String KEY_IS_OVERSEA = "is_oversea";
        public static final String KEY_LAST_CHECK = "last_check";
        public static final String KEY_LAST_DOWNLOAD_DECOUPLE_CORE = "last_download_decouple_core";
        public static final String KEY_LAST_REQUEST_SUCCESS = "last_request_success";
        public static final String KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION = "last_thirdapp_sendrequest_coreversion";
        public static final String KEY_NEEDDOWNLOAD = "tbs_needdownload";
        public static final String KEY_REQUEST_FAIL = "request_fail";
        public static final String KEY_RESPONSECODE = "tbs_responsecode";
        public static final String KEY_RETRY_INTERVAL = "retry_interval";
        public static final String KEY_STOP_PRE_OAT = "tbs_stop_preoat";
        public static final String KEY_SWITCH_BACKUPCORE_ENABLE = "switch_backupcore_enable";
        public static final String KEY_TBSAPKFILESIZE = "tbs_apkfilesize";
        public static final String KEY_TBSAPK_MD5 = "tbs_apk_md5";
        public static final String KEY_TBSDOWNLOADURL = "tbs_downloadurl";
        public static final String KEY_TBSDOWNLOAD_FLOW = "tbs_downloadflow";
        public static final String KEY_TBSDOWNLOAD_STARTTIME = "tbs_downloadstarttime";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE = "tbs_core_load_rename_file_lock_enable";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE = "tbs_core_load_rename_file_lock_wait_enable";
        public static final String KEY_TBS_DOWNLOAD_V = "tbs_download_version";
        public static final String KEY_TBS_DOWNLOAD_V_TYPE = "tbs_download_version_type";
        public static final String KEY_USE_BACKUP_VERSION = "use_backup_version";
        public static final String KEY_USE_BUGLY = "tbs_use_bugly";
    }

    private TbsDownloadConfig(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_config", 4);
        this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.c == null) {
            this.c = context;
        }
    }

    public static synchronized TbsDownloadConfig getInstance(Context context) {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            if (b == null) {
                b = new TbsDownloadConfig(context);
            }
            tbsDownloadConfig = b;
        }
        return tbsDownloadConfig;
    }

    public static synchronized TbsDownloadConfig getInstance() {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            tbsDownloadConfig = b;
        }
        return tbsDownloadConfig;
    }

    public synchronized long getDownloadMaxflow() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MAXFLOW, 0);
        if (i == 0) {
            i = 20;
        }
        return ((long) (i * 1024)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public synchronized long getRetryInterval() {
        long j;
        if (TbsDownloader.getRetryIntervalInSeconds() >= 0) {
            j = TbsDownloader.getRetryIntervalInSeconds();
        } else {
            j = this.mPreferences.getLong(TbsConfigKey.KEY_RETRY_INTERVAL, DEFAULT_RETRY_INTERVAL_SEC);
        }
        return j;
    }

    public synchronized long getDownloadMinFreeSpace() {
        long j;
        int i = 0;
        synchronized (this) {
            int i2 = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MIN_FREE_SPACE, 0);
            if (i2 != 0) {
                i = i2;
            }
            j = ((long) (i * 1024)) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return j;
    }

    public synchronized int getDownloadSuccessMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 3;
        }
        return i;
    }

    public synchronized int getDownloadFailedMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 100;
        }
        return i;
    }

    public synchronized void setDownloadInterruptCode(int i) {
        try {
            Editor edit = this.mPreferences.edit();
            edit.putInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, i);
            edit.putLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, System.currentTimeMillis());
            edit.commit();
        } catch (Exception e) {
        }
    }

    public synchronized void setTbsCoreLoadRenameFileLockEnable(boolean z) {
        try {
            Editor edit = this.mPreferences.edit();
            edit.putBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE, z);
            edit.commit();
        } catch (Exception e) {
        }
    }

    public synchronized void setTbsCoreLoadRenameFileLockWaitEnable(boolean z) {
        try {
            Editor edit = this.mPreferences.edit();
            edit.putBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE, z);
            edit.commit();
        } catch (Exception e) {
        }
    }

    public synchronized boolean getTbsCoreLoadRenameFileLockEnable() {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.mPreferences.getBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE, true);
            } catch (Exception e) {
            }
        }
        return z;
    }

    public synchronized boolean getTbsCoreLoadRenameFileLockWaitEnable() {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.mPreferences.getBoolean(TbsConfigKey.KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_WAIT_ENABLE, true);
            } catch (Exception e) {
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        r1 = -95;
        r0 = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void uploadDownloadInterruptCodeIfNeeded(android.content.Context r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x0071
            java.lang.String r0 = "com.tencent.mm"
            android.content.Context r1 = r7.getApplicationContext()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            android.content.Context r1 = com.stub.StubApp.getOrigApplicationContext(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.String r1 = r1.packageName     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            if (r0 == 0) goto L_0x0071
            r1 = 1
            android.content.SharedPreferences r0 = r6.mPreferences     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.String r2 = "tbs_download_interrupt_code"
            boolean r0 = r0.contains(r2)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            if (r0 != 0) goto L_0x008a
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            android.content.Context r3 = r6.c     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            java.lang.String r4 = "shared_prefs"
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            java.lang.String r3 = "tbs_download_config"
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0073
            r0 = -97
        L_0x0040:
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0043:
            if (r0 == 0) goto L_0x0071
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r7)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.tbsLogInfo()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            r2 = 128(0x80, float:1.794E-43)
            r0.setErrorCode(r2)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            r2.<init>()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.String r3 = " "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            r0.setFailDetail(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r7)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            r1.eventReport(r2, r0)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        L_0x0071:
            monitor-exit(r6)
            return
        L_0x0073:
            android.content.SharedPreferences r0 = r6.mPreferences     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            java.lang.String r2 = "tbs_needdownload"
            boolean r0 = r0.contains(r2)     // Catch:{ Throwable -> 0x0083, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0080
            r0 = -96
            goto L_0x0040
        L_0x0080:
            r0 = -101(0xffffffffffffff9b, float:NaN)
            goto L_0x0040
        L_0x0083:
            r0 = move-exception
            r0 = -95
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0043
        L_0x008a:
            android.content.SharedPreferences r0 = r6.mPreferences     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            java.lang.String r2 = "tbs_download_interrupt_code"
            r3 = -99
            int r2 = r0.getInt(r2, r3)     // Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
            r0 = -206(0xffffffffffffff32, float:NaN)
            if (r2 > r0) goto L_0x009f
            r0 = -219(0xffffffffffffff25, float:NaN)
            if (r2 < r0) goto L_0x009f
            r0 = r1
            r1 = r2
            goto L_0x0043
        L_0x009f:
            r0 = -302(0xfffffffffffffed2, float:NaN)
            if (r2 > r0) goto L_0x00aa
            r0 = -316(0xfffffffffffffec4, float:NaN)
            if (r2 < r0) goto L_0x00aa
            r0 = r1
            r1 = r2
            goto L_0x0043
        L_0x00aa:
            r0 = -318(0xfffffffffffffec2, float:NaN)
            if (r2 > r0) goto L_0x00b5
            r0 = -322(0xfffffffffffffebe, float:NaN)
            if (r2 < r0) goto L_0x00b5
            r0 = r1
            r1 = r2
            goto L_0x0043
        L_0x00b5:
            r0 = 0
            r1 = r2
            goto L_0x0043
        L_0x00b8:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x00bb:
            r0 = move-exception
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.uploadDownloadInterruptCodeIfNeeded(android.content.Context):void");
    }

    public synchronized int getDownloadInterruptCode() {
        int i;
        int i2;
        if (!this.mPreferences.contains(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE)) {
            try {
                if (!new File(new File(this.c.getFilesDir(), "shared_prefs"), "tbs_download_config").exists()) {
                    i = -97;
                } else if (!this.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD)) {
                    i = -96;
                } else {
                    i = -101;
                }
            } catch (Throwable th) {
                i = -95;
            }
        } else {
            i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, -99);
            if (i == -119 || i == -121) {
                i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -119);
            }
            if (System.currentTimeMillis() - this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, 0) > 86400000) {
                i -= 98000;
            }
        }
        if (this.c == null || !TbsConfig.APP_QQ.equals(this.c.getApplicationInfo().packageName) || "CN".equals(Locale.getDefault().getCountry())) {
            i2 = (i * 1000) + this.mPreferences.getInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, -1);
        } else {
            i2 = -320;
        }
        return i2;
    }

    public synchronized long getDownloadSingleTimeout() {
        long j;
        j = this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_SINGLE_TIMEOUT, 0);
        if (j == 0) {
            j = 1200000;
        }
        return j;
    }

    public synchronized boolean isOverSea() {
        return this.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
    }

    public synchronized void commit() {
        try {
            Editor edit = this.mPreferences.edit();
            for (String str : this.a.keySet()) {
                Object obj = this.a.get(str);
                if (obj instanceof String) {
                    edit.putString(str, (String) obj);
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    edit.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    edit.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    edit.putFloat(str, ((Float) obj).floatValue());
                }
            }
            edit.commit();
            this.a.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void clear() {
        try {
            this.a.clear();
            Editor edit = this.mPreferences.edit();
            edit.clear();
            edit.commit();
        } catch (Exception e) {
        }
    }

    public synchronized void setInstallInterruptCode(int i) {
        Editor edit = this.mPreferences.edit();
        edit.putInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, i);
        edit.commit();
    }
}
