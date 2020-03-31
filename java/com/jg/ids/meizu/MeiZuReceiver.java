package com.jg.ids.meizu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.ArrayList;

public class MeiZuReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                int intExtra = intent.getIntExtra("openIdNotifyFlag", 0);
                if (intExtra == 1) {
                    if (!TextUtils.equals(intent.getStringExtra("openIdPackage"), context.getPackageName())) {
                        return;
                    }
                } else if (intExtra == 2) {
                    ArrayList stringArrayListExtra = intent.getStringArrayListExtra("openIdPackageList");
                    if (stringArrayListExtra == null || !stringArrayListExtra.contains(context.getPackageName())) {
                        return;
                    }
                }
                String stringExtra = intent.getStringExtra("openIdType");
                if ("aaid".equals(stringExtra)) {
                    a.a().a(2);
                } else if ("vaid".equals(stringExtra)) {
                    a.a().a(1);
                } else if ("oaid".equals(stringExtra)) {
                    a.a().a(0);
                }
            } catch (Throwable th) {
            }
        }
    }
}
