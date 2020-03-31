package com.jg.ids.d;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import com.baidu.mobstat.Config;

public final class a extends com.jg.ids.a {
    private static final Uri b = Uri.parse("content://cn.nubia.identity/identity");

    public a(Context context) {
        super(context, "nubia_thread");
    }

    /* access modifiers changed from: protected */
    public final void a(Message message) {
        String str = null;
        if (message != null) {
            try {
                if (message.what == 0) {
                    int i = message.getData().getInt("type", -1);
                    String str2 = "";
                    switch (i) {
                        case 0:
                            str2 = "getOAID";
                            break;
                        case 1:
                            str2 = "getVAID";
                            str = this.a.getPackageName();
                            break;
                        case 2:
                            str2 = "getAAID";
                            str = this.a.getPackageName();
                            break;
                    }
                    a(i, a(this.a, str2, str));
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        a(2);
        a(0);
        a(1);
    }

    private void a(int i) {
        try {
            Message a = a();
            a.what = 0;
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            a.setData(bundle);
            b(a);
        } catch (Throwable th) {
        }
    }

    private static String a(Context context, String str, String str2) {
        Bundle call;
        if (context == null) {
            return "";
        }
        try {
            if (VERSION.SDK_INT >= 17) {
                ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(b);
                if (acquireContentProviderClient == null) {
                    return "";
                }
                call = acquireContentProviderClient.call(str, str2, null);
                if (VERSION.SDK_INT >= 24) {
                    acquireContentProviderClient.close();
                } else {
                    acquireContentProviderClient.release();
                }
            } else {
                call = context.getContentResolver().call(b, str, str2, null);
            }
            if (call != null && call.getInt("code", -1) == 0) {
                return call.getString(Config.FEED_LIST_ITEM_CUSTOM_ID);
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* access modifiers changed from: protected */
    public final void a(int i, String str) {
        switch (i) {
            case 0:
                a((Runnable) new b(this, str));
                return;
            case 1:
                a((Runnable) new c(this, str));
                return;
            case 2:
                a((Runnable) new d(this, str));
                return;
            default:
                return;
        }
    }
}
