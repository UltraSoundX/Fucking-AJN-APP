package com.mob.commons;

import android.net.Uri;
import android.os.Build.VERSION;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ReflectHelper;

/* compiled from: ServerConfig */
public class h {
    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith("http://")) {
            str = str.replace("http://", "");
        }
        if (str.startsWith("https://")) {
            str = str.replace("https://", "");
        }
        switch (MobSDK.getDomain()) {
            case JP:
                str2 = "jp";
                break;
            case US:
                str2 = "us";
                break;
            default:
                str2 = "";
                break;
        }
        if (TextUtils.isEmpty(str2)) {
            return b("http://" + str);
        }
        if (str.startsWith(str2 + ".")) {
            return b("http://" + str);
        }
        return b("http://" + str2 + "." + str);
    }

    public static String b(String str) {
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            boolean checkForceHttps = MobSDK.checkForceHttps();
            if (!checkForceHttps && (VERSION.SDK_INT < 23 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted())) {
                return str;
            }
            String trim = str.trim();
            if (!trim.startsWith("http://")) {
                return trim;
            }
            Uri parse = Uri.parse(trim.trim());
            if (parse == null) {
                return trim;
            }
            String scheme = parse.getScheme();
            if (scheme == null || !scheme.equals("http")) {
                return trim;
            }
            String host = parse.getHost();
            String path = parse.getPath();
            String query = parse.getQuery();
            if (host != null) {
                int port = parse.getPort();
                String str3 = host + ((port <= 0 || port == 80) ? "" : Config.TRACE_TODAY_VISIT_SPLIT + port);
                if (!checkForceHttps && VERSION.SDK_INT >= 24) {
                    if (((Boolean) ReflectHelper.invokeInstanceMethod(NetworkSecurityPolicy.getInstance(), "isCleartextTrafficPermitted", str3)).booleanValue()) {
                        return trim;
                    }
                }
                host = str3;
            }
            StringBuilder append = new StringBuilder().append("https://").append(host).append(path == null ? "" : path);
            if (query == null) {
                str2 = "";
            } else {
                str2 = "?" + query;
            }
            return append.append(str2).toString();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return str;
        }
    }
}
