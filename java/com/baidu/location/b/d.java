package com.baidu.location.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import com.baidu.location.f;

public class d {
    private static d d = null;
    /* access modifiers changed from: private */
    public boolean a = false;
    /* access modifiers changed from: private */
    public String b = null;
    private a c = null;
    /* access modifiers changed from: private */
    public int e = -1;

    public class a extends BroadcastReceiver {
        public a() {
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                    d.this.a = false;
                    int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, 0);
                    int intExtra2 = intent.getIntExtra("plugged", 0);
                    int intExtra3 = intent.getIntExtra("level", -1);
                    int intExtra4 = intent.getIntExtra("scale", -1);
                    if (intExtra3 <= 0 || intExtra4 <= 0) {
                        d.this.e = -1;
                    } else {
                        d.this.e = (intExtra3 * 100) / intExtra4;
                    }
                    switch (intExtra) {
                        case 2:
                            d.this.b = "4";
                            break;
                        case 3:
                        case 4:
                            d.this.b = "3";
                            break;
                        default:
                            d.this.b = null;
                            break;
                    }
                    switch (intExtra2) {
                        case 1:
                            d.this.b = "6";
                            d.this.a = true;
                            return;
                        case 2:
                            d.this.b = "5";
                            d.this.a = true;
                            return;
                        default:
                            return;
                    }
                }
            } catch (Exception e) {
                d.this.b = null;
            }
        }
    }

    private d() {
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (d == null) {
                d = new d();
            }
            dVar = d;
        }
        return dVar;
    }

    public void b() {
        this.c = new a();
        try {
            f.c().registerReceiver(this.c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Exception e2) {
        }
    }

    public void c() {
        if (this.c != null) {
            try {
                f.c().unregisterReceiver(this.c);
            } catch (Exception e2) {
            }
        }
        this.c = null;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.a;
    }

    public int f() {
        return this.e;
    }
}
