package com.tencent.mid.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Process;
import android.webkit.WebSettings;
import com.baidu.mobstat.Config;
import com.tencent.android.tpush.common.Constants;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StatCommonHelper {
    private static String curAppMd5Signature = "";
    private static String curAppSHA1Signature = "";
    private static String curProcessName = null;
    private static Logger logger = Util.getLogger();

    public static String getPackageName(Context context) {
        if (Util.checkPermission(context, "android.permission.GET_TASKS")) {
            List runningTasks = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1);
            if (runningTasks != null && runningTasks.size() > 0) {
                return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName();
            }
        } else {
            logger.e((Object) "Could not get permission of android.permission.GET_TASKS");
        }
        return null;
    }

    public static String getCurAppVersion(Context context) {
        String str = "";
        try {
            String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 == null) {
                return "";
            }
            return str2;
        } catch (Throwable th) {
            Throwable th2 = th;
            String str3 = str;
            logger.e((Object) th2);
            return str3;
        }
    }

    public static String getAppVersion(Context context) {
        Object th;
        String str;
        String str2 = "";
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str != null) {
                try {
                    if (str.length() != 0) {
                        return str;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    logger.e(th);
                    return str;
                }
            }
            return "unknown";
        } catch (Throwable th3) {
            Object obj = th3;
            str = str2;
            th = obj;
            logger.e(th);
            return str;
        }
    }

    static String getCurProcessName(Context context) {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static boolean isStringValid(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    public static String getCurAppMd5Signature(Context context) {
        if (isStringValid(curAppMd5Signature)) {
            return curAppMd5Signature;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null) {
                logger.e((Object) "packageInfo is null ");
                return "";
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                logger.e((Object) "signatures is null");
                return "";
            }
            curAppMd5Signature = Util.md5sum(signatureArr[0].toCharsString());
            return curAppMd5Signature;
        } catch (Throwable th) {
            logger.e((Object) th);
        }
    }

    public static String getCurAppSHA1Signature(Context context) {
        if (isStringValid(curAppSHA1Signature)) {
            return curAppSHA1Signature;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null) {
                logger.e((Object) "packageInfo is null ");
                return "";
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                logger.e((Object) "signatures is null");
                return "";
            }
            byte[] digest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                String upperCase = Integer.toHexString(digest[i] & DeviceInfos.NETWORK_TYPE_UNCONNECTED).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
                if (i != length - 1) {
                    stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
                }
            }
            curAppSHA1Signature = stringBuffer.toString();
            return curAppSHA1Signature;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static long getTomorrowStartMilliseconds() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Throwable th) {
            logger.e((Object) th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    public static String getDateString(int i) {
        Calendar instance = Calendar.getInstance();
        instance.roll(6, i);
        return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
    }

    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getCurProcessName(context));
    }

    public static String getUserAgent(Context context) {
        String property;
        String str = "";
        if (VERSION.SDK_INT >= 17) {
            try {
                property = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                property = System.getProperty("http.agent");
            }
        } else {
            property = System.getProperty("http.agent");
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = property.length();
        for (int i = 0; i < length; i++) {
            char charAt = property.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                stringBuffer.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }
}
