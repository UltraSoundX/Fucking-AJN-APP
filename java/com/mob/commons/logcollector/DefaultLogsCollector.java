package com.mob.commons.logcollector;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import cn.sharesdk.framework.ShareSDK;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.log.LogCollector;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import java.util.HashMap;

public class DefaultLogsCollector implements LogCollector, PublicMemberKeeper {
    private static DefaultLogsCollector a;
    private final HashMap<String, Integer> b = new HashMap<>();
    private c c = c.a();
    private boolean d;

    public static synchronized DefaultLogsCollector get() {
        DefaultLogsCollector defaultLogsCollector;
        synchronized (DefaultLogsCollector.class) {
            if (a == null) {
                a = new DefaultLogsCollector();
            }
            defaultLogsCollector = a;
        }
        return defaultLogsCollector;
    }

    private DefaultLogsCollector() {
        try {
            if (MobSDK.getContext().getPackageManager().getPackageInfo("cn.sharesdk.log", 64) != null) {
                this.d = true;
            }
        } catch (Throwable th) {
            this.d = false;
        }
    }

    public void addSDK(String str, int i) {
        synchronized (this.b) {
            Integer num = (Integer) this.b.get(str);
            this.b.put(str, Integer.valueOf(i));
            if (num == null && this.c != null) {
                this.c.a(i, str);
            }
        }
    }

    public final void log(String str, int i, int i2, String str2, String str3) {
        a(i, str3);
        Integer num = (Integer) this.b.get(str);
        if (num != null) {
            if (ShareSDK.SDK_TAG.equals(str) && (!str3.contains("com.mob.") || !str3.contains("cn.sharesdk."))) {
                return;
            }
            if (i2 == 1) {
                this.c.b(num.intValue(), i2, str, str3);
            } else if (i2 == 2) {
                this.c.a(num.intValue(), i2, str, str3);
            } else if (i == 5) {
                this.c.a(num.intValue(), i2, str, str3);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final int a(int i, String str) {
        if (MobSDK.getContext() != null && this.d) {
            try {
                Intent intent = new Intent();
                intent.setPackage("cn.sharesdk.log");
                String packageName = MobSDK.getContext().getPackageName();
                intent.putExtra("package", packageName);
                intent.putExtra("priority", i);
                intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, Data.AES128Encode(packageName, str));
                MobSDK.getContext().sendBroadcast(intent);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }
        return 0;
    }
}
