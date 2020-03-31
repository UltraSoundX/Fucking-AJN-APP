package com.tencent.android.tpush;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.net.Uri;
import android.os.Bundle;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.stat.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ProGuard */
public class XGPushActivity extends Activity {
    static ActivityLifecycleCallbacks a = null;
    static List<String> b = null;
    static String c = "";
    static long d = 0;
    static long e = 0;

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private boolean a(Intent intent) {
        if (intent == null || !intent.hasExtra(MessageKey.MSG_PORTECT_TAG)) {
            return false;
        }
        String stringExtra = intent.getStringExtra(MessageKey.MSG_PORTECT_TAG);
        if (l.c(stringExtra)) {
            return false;
        }
        try {
            Long valueOf = Long.valueOf(Rijndael.decrypt(stringExtra));
            if (valueOf.longValue() <= 0 || System.currentTimeMillis() < valueOf.longValue()) {
                return false;
            }
            return true;
        } catch (NumberFormatException e2) {
            return false;
        }
    }

    static {
        StubApp.interface11(4857);
    }

    static void a(Application application) {
        if (a == null) {
            a = new ActivityLifecycleCallbacks() {
                public void onActivityStopped(Activity activity) {
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityResumed(Activity activity) {
                    if (activity != null && activity.getComponentName().getClassName() != null && StubApp.getOrigApplicationContext(activity.getApplicationContext()) != null) {
                        e.b(StubApp.getOrigApplicationContext(activity.getApplicationContext()), activity.getComponentName().getClassName(), XGPushConfig.getAccessId(StubApp.getOrigApplicationContext(activity.getApplicationContext())));
                    }
                }

                public void onActivityPaused(Activity activity) {
                    if (activity != null && activity.getComponentName().getClassName() != null && StubApp.getOrigApplicationContext(activity.getApplicationContext()) != null) {
                        if (activity.getComponentName().getClassName().equals(XGPushActivity.c)) {
                            e.c(StubApp.getOrigApplicationContext(activity.getApplicationContext()), activity.getComponentName().getClassName(), XGPushConfig.getAccessId(StubApp.getOrigApplicationContext(activity.getApplicationContext())));
                        } else {
                            e.a(StubApp.getOrigApplicationContext(activity.getApplicationContext()), activity.getComponentName().getClassName(), XGPushConfig.getAccessId(StubApp.getOrigApplicationContext(activity.getApplicationContext())), XGPushActivity.d, XGPushActivity.e);
                        }
                    }
                }

                public void onActivityDestroyed(Activity activity) {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                }
            };
            if (application != null) {
                try {
                    ((Application) StubApp.getOrigApplicationContext(application.getApplicationContext())).registerActivityLifecycleCallbacks(a);
                } catch (Exception e2) {
                }
            }
        }
    }

    public static void addActivityNames(String str) {
        if (!l.c(str)) {
            if (b == null) {
                b = new ArrayList();
            }
            if (!b.contains(str)) {
                b.add(str);
            }
        }
    }

    public static boolean isMonitorActivityNames(String str) {
        if (b != null && !l.c(str) && b.contains(str)) {
            return true;
        }
        return false;
    }

    private void b(Intent intent) {
        String str = intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME) != null ? intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME) : "";
        if (XGPushConfig.enableDebug) {
            a.e(Constants.PushMessageLogTag, "activity intent =" + intent + "activity = " + str + "intent.getFlags()" + intent.getFlags());
        }
        if (intent != null) {
            d = intent.getLongExtra(MessageKey.MSG_ID, 0);
            e = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, 0);
            c = str;
        }
        Intent intent2 = new Intent();
        intent2.addFlags(intent.getFlags());
        intent2.addFlags(536870912);
        intent2.setClassName(StubApp.getOrigApplicationContext(getApplicationContext()), str);
        intent.putExtra(Constants.TAG_TPUSH_MESSAGE, "true");
        intent2.putExtras(intent);
        intent2.putExtra(Constants.TAG_TPUSH_NOTIFICATION, XGPushManager.a((Activity) this));
        try {
            a(getApplication());
            startActivity(intent2);
        } catch (ActivityNotFoundException e2) {
        }
        finish();
    }

    private void c(Intent intent) {
        e(intent);
        ResolveInfo a2 = a(intent.getStringExtra(Constants.FLAG_PACKAGE_NAME));
        if (a2 != null) {
            String str = a2.activityInfo.name;
            String str2 = a2.activityInfo.packageName;
            Intent intent2 = new Intent();
            intent2.putExtras(intent);
            intent2.setComponent(new ComponentName(str2, str));
            a(0, intent2);
            return;
        }
        a(1, intent);
    }

    private ResolveInfo a(String str) {
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN", null);
            intent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            Collections.sort(queryIntentActivities, new DisplayNameComparator(packageManager));
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                String str2 = resolveInfo.activityInfo.name;
                if (resolveInfo.activityInfo.packageName.equals(str)) {
                    return resolveInfo;
                }
            }
        } catch (Throwable th) {
            a.d(Constants.LogTag, "查找主Activity出错", th);
        }
        return null;
    }

    private void a(int i, final Intent intent) {
        if (i == 0) {
            Builder builder = new Builder(this);
            builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    intent.putExtra("action", NotificationAction.open_cancel.getType());
                    XGPushActivity.this.e(intent);
                    XGPushActivity.this.finish();
                }
            }).setTitle("提示").setMessage("是否确定打开此应用？").setPositiveButton("打开", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("action", NotificationAction.open.getType());
                    XGPushActivity.this.e(intent);
                    try {
                        XGPushActivity.this.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                    }
                    XGPushActivity.this.finish();
                }
            }).setNegativeButton("取消", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("action", NotificationAction.open_cancel.getType());
                    XGPushActivity.this.e(intent);
                    dialogInterface.dismiss();
                    XGPushActivity.this.finish();
                }
            });
            builder.create().show();
        } else if (i == 1) {
            Builder builder2 = new Builder(this);
            builder2.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    intent.putExtra("action", NotificationAction.download_cancel.getType());
                    XGPushActivity.this.e(intent);
                    XGPushActivity.this.finish();
                }
            }).setTitle("提示").setMessage("本地未发现此应用，建议去下载！").setPositiveButton("下载", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("action", NotificationAction.download.getType());
                    XGPushActivity.this.e(intent);
                    Intent intent = new Intent(XGPushActivity.this, XGDownloadService.class);
                    intent.putExtras(intent);
                    intent.putExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL, intent.getStringExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL));
                    XGPushActivity.this.startService(intent);
                    XGPushActivity.this.finish();
                }
            }).setNegativeButton("取消", new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.putExtra("action", NotificationAction.download_cancel.getType());
                    XGPushActivity.this.e(intent);
                    dialogInterface.dismiss();
                    XGPushActivity.this.finish();
                }
            });
            builder2.create().show();
        }
    }

    private void b(int i, final Intent intent) {
        if (i == 0) {
            final String stringExtra = intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME);
            if (intent.getIntExtra(Constants.FLAG_ACTION_CONFIRM, 0) == 1) {
                new Builder(this).setTitle("提示").setCancelable(false).setMessage("是否打开网站:" + stringExtra + "?").setPositiveButton("确认", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XGPushActivity.this.a(stringExtra, intent);
                        XGPushActivity.this.finish();
                    }
                }).setNegativeButton("取消", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XGPushActivity.this.e(intent);
                        dialogInterface.cancel();
                        XGPushActivity.this.finish();
                    }
                }).show();
            } else {
                a(stringExtra, intent);
            }
        } else if (i != 1) {
        } else {
            if (intent.getIntExtra(Constants.FLAG_ACTION_CONFIRM, 0) == 1) {
                new Builder(this).setTitle("提示").setCancelable(false).setMessage("继续打开Intent?").setPositiveButton("确认", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XGPushActivity.this.d(intent);
                        XGPushActivity.this.finish();
                    }
                }).setNegativeButton("取消", new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XGPushActivity.this.e(intent);
                        dialogInterface.cancel();
                        XGPushActivity.this.finish();
                    }
                }).show();
            } else {
                d(intent);
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(Intent intent) {
        try {
            Intent intent2 = new Intent();
            int intExtra = intent.getIntExtra(Constants.FLAG_ACTION_TYPE, NotificationAction.intent.getType());
            if (intExtra == NotificationAction.intent.getType()) {
                Uri parse = Uri.parse(intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME));
                intent2.setAction("android.intent.action.VIEW");
                intent2.setData(parse);
            } else if (intExtra == NotificationAction.intent_with_action.getType()) {
                intent2.setAction(intent.getStringExtra(Constants.FLAG_ACTIVITY_NAME));
                intent2.setPackage(getPackageName());
                intent2.setFlags(268435456);
            }
            if (intent2.resolveActivity(getPackageManager()) != null) {
                e(intent);
                startActivity(intent2);
            }
            finish();
        } catch (Throwable th) {
            a.d(Constants.LogTag, "openIntent error.", th);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Intent intent) {
        try {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent2.setFlags(268435456);
            e(intent);
            startActivity(intent2);
        } catch (Throwable th) {
            a.d(Constants.LogTag, "openUrl error.", th);
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void e(Intent intent) {
        XGPushManager.a(StubApp.getOrigApplicationContext(getApplicationContext()), intent);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }
}
