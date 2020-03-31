package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.stub.StubApp;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BDStatCore {
    public static final int INVOKE_ACT = 1;
    public static final int INVOKE_API = 0;
    public static final int INVOKE_CUSTOME = 3;
    public static final int INVOKE_FRAG = 2;
    private static BDStatCore a;
    private Handler b;
    /* access modifiers changed from: private */
    public volatile boolean c = false;
    /* access modifiers changed from: private */
    public SessionAnalysis d;
    /* access modifiers changed from: private */
    public EventAnalysis e;
    private Runnable f;
    /* access modifiers changed from: private */
    public long g = 0;
    /* access modifiers changed from: private */
    public volatile boolean h = false;
    private Handler i;

    public static BDStatCore instance() {
        if (a == null) {
            synchronized (BDStatCore.class) {
                if (a == null) {
                    a = new BDStatCore();
                }
            }
        }
        return a;
    }

    private BDStatCore() {
        HandlerThread handlerThread = new HandlerThread("BDStatCore", 10);
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.d = new SessionAnalysis();
        this.e = new EventAnalysis();
        HandlerThread handlerThread2 = new HandlerThread("dataAnalyzeThread");
        handlerThread2.start();
        handlerThread2.setPriority(10);
        this.i = new Handler(handlerThread2.getLooper());
    }

    private void a(Context context) {
    }

    public void init(final Context context) {
        a(context);
        if (!this.c) {
            ActivityLifeTask.registerActivityLifeCallback(context);
            this.b.post(new Runnable() {
                public void run() {
                    if (!BDStatCore.this.c) {
                        PrefOperate.loadMetaDataConfig(context);
                        BDStatCore.this.c = true;
                    }
                }
            });
        }
    }

    public void setAutoSendLog(Context context, boolean z) {
        if (context != null) {
            init(context);
            b(context);
            this.d.setAutoSend(z);
        }
    }

    public void setSessionTimeOut(int i2) {
        this.d.setSessionTimeOut(i2);
    }

    public JSONObject getPageSessionHead() {
        return this.d.getPageSessionHead();
    }

    public long getSessionStartTime() {
        return this.d.getSessionStartTime();
    }

    public int getSessionSize() {
        return this.d.getSessionSize();
    }

    public void onSessionStart(Context context, boolean z) {
        if (context != null) {
            init(context);
            b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final Context context2 = context;
            final boolean z2 = z;
            this.b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.d.onSessionStart(context2, currentTimeMillis, z2);
                }
            });
        }
    }

    public void autoTrackLaunchInfo(Context context, final LaunchInfo launchInfo, final boolean z) {
        if (launchInfo != null) {
            if (z) {
                this.d.autoTrackLaunchInfo(launchInfo, z);
                return;
            }
            init(context);
            this.b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.d.autoTrackLaunchInfo(launchInfo, z);
                }
            });
        }
    }

    public void autoTrackSessionStartTime(final Context context) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            this.b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.d.autoTrackSessionStartTime(context, currentTimeMillis);
                }
            });
        }
    }

    public void autoTrackSessionEndTime(final Context context) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            this.b.post(new Runnable() {
                public void run() {
                    BDStatCore.this.d.autoTrackSessionEndTime(context, currentTimeMillis);
                }
            });
        }
    }

    public void doSendLogCheck(final Context context) {
        if (context != null) {
            int sessionTimeOut = this.d.getSessionTimeOut();
            this.f = new Runnable() {
                public void run() {
                    BDStatCore.this.d.doSendLogCheck(context, System.currentTimeMillis());
                }
            };
            this.b.postDelayed(this.f, (long) sessionTimeOut);
        }
    }

    public void cancelSendLogCheck() {
        Runnable runnable = this.f;
        if (runnable != null) {
            this.b.removeCallbacks(runnable);
        }
        this.f = null;
    }

    public void onPageStart(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            b(context);
            final int a2 = a();
            final long currentTimeMillis = System.currentTimeMillis();
            final String str2 = str;
            final Context context2 = context;
            this.b.post(new Runnable() {
                public void run() {
                    am.c().a("Start page view " + str2);
                    BDStatCore.this.d.onPageStart(context2, str2, a2, currentTimeMillis);
                }
            });
        }
    }

    public void onPageEnd(Context context, String str, ExtraInfo extraInfo) {
        onPageEnd(context, str, extraInfo, false);
    }

    public void onPageEnd(Context context, String str, ExtraInfo extraInfo, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            final String b2 = b();
            final long currentTimeMillis = System.currentTimeMillis();
            final String str2 = str;
            final Context context2 = context;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z2 = z;
            this.b.post(new Runnable() {
                public void run() {
                    am.c().a("End page view " + str2);
                    BDStatCore.this.d.onPageEnd(context2, str2, str2, b2, currentTimeMillis, extraInfo2, z2);
                }
            });
        }
    }

    public void onResume(Activity activity, final boolean z) {
        if (activity != null) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
            if (origApplicationContext != null) {
                init(origApplicationContext);
                b(origApplicationContext);
                final WeakReference weakReference = new WeakReference(activity);
                this.b.post(new Runnable() {
                    public void run() {
                        Activity activity = (Activity) weakReference.get();
                        if (activity != null) {
                            Class cls = activity.getClass();
                            if (cls != null) {
                                String name = activity.getClass().getName();
                                long currentTimeMillis = System.currentTimeMillis();
                                if (!z) {
                                    am.c().a("Start page view " + cls.getSimpleName());
                                }
                                BDStatCore.this.d.onPageStartAct(origApplicationContext, name, currentTimeMillis, z);
                            }
                        }
                    }
                });
            }
        }
    }

    public void onResume(Fragment fragment) {
        if (fragment != null) {
            FragmentActivity activity = fragment.getActivity();
            if (activity != null) {
                final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
                if (origApplicationContext != null) {
                    init(origApplicationContext);
                    b(origApplicationContext);
                    final WeakReference weakReference = new WeakReference(fragment);
                    this.b.post(new Runnable() {
                        public void run() {
                            Fragment fragment = (Fragment) weakReference.get();
                            if (fragment != null) {
                                Class cls = fragment.getClass();
                                if (cls != null) {
                                    String name = fragment.getClass().getName();
                                    long currentTimeMillis = System.currentTimeMillis();
                                    am.c().a("Start page view " + cls.getSimpleName());
                                    BDStatCore.this.d.onPageStartFrag(origApplicationContext, name, currentTimeMillis);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void onResume(android.app.Fragment fragment) {
        if (fragment != null) {
            Activity activity = fragment.getActivity();
            if (activity != null) {
                final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
                if (origApplicationContext != null) {
                    init(origApplicationContext);
                    b(origApplicationContext);
                    final WeakReference weakReference = new WeakReference(fragment);
                    this.b.post(new Runnable() {
                        public void run() {
                            android.app.Fragment fragment = (android.app.Fragment) weakReference.get();
                            if (fragment != null) {
                                Class cls = fragment.getClass();
                                if (cls != null) {
                                    String name = fragment.getClass().getName();
                                    long currentTimeMillis = System.currentTimeMillis();
                                    am.c().a("Start page view " + cls.getSimpleName());
                                    BDStatCore.this.d.onPageStartFrag(origApplicationContext, name, currentTimeMillis);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void onPause(Activity activity, boolean z, ExtraInfo extraInfo) {
        if (activity != null) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
            if (origApplicationContext != null) {
                init(origApplicationContext);
                final WeakReference weakReference = new WeakReference(activity);
                final boolean z2 = z;
                final ExtraInfo extraInfo2 = extraInfo;
                this.b.post(new Runnable() {
                    public void run() {
                        Activity activity = (Activity) weakReference.get();
                        if (activity != null) {
                            Class cls = activity.getClass();
                            if (cls != null) {
                                String name = activity.getClass().getName();
                                String simpleName = activity.getClass().getSimpleName();
                                CharSequence title = activity.getTitle();
                                String charSequence = title == null ? "" : title.toString();
                                if (!z2) {
                                    am.c().a("End page view " + cls.getSimpleName());
                                }
                                BDStatCore.this.d.onPageEndAct(origApplicationContext, name, simpleName, charSequence, System.currentTimeMillis(), z2, extraInfo2);
                            }
                        }
                    }
                });
            }
        }
    }

    public void onPause(Fragment fragment) {
        if (fragment != null) {
            final FragmentActivity activity = fragment.getActivity();
            if (activity != null) {
                final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
                if (origApplicationContext != null) {
                    init(origApplicationContext);
                    final WeakReference weakReference = new WeakReference(fragment);
                    final Fragment fragment2 = fragment;
                    this.b.post(new Runnable() {
                        public void run() {
                            Fragment fragment = (Fragment) weakReference.get();
                            if (fragment != null && fragment.getClass() != null) {
                                String name = fragment2.getClass().getName();
                                String simpleName = fragment2.getClass().getSimpleName();
                                CharSequence title = activity.getTitle();
                                String charSequence = title == null ? "" : title.toString();
                                am.c().a("End page view " + simpleName);
                                BDStatCore.this.d.onPageEndFrag(origApplicationContext, name, simpleName, charSequence, System.currentTimeMillis());
                            }
                        }
                    });
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void onPause(android.app.Fragment fragment) {
        if (fragment != null) {
            final Activity activity = fragment.getActivity();
            if (activity != null) {
                final Context origApplicationContext = StubApp.getOrigApplicationContext(activity.getApplicationContext());
                if (origApplicationContext != null) {
                    init(origApplicationContext);
                    final WeakReference weakReference = new WeakReference(fragment);
                    final android.app.Fragment fragment2 = fragment;
                    this.b.post(new Runnable() {
                        public void run() {
                            android.app.Fragment fragment = (android.app.Fragment) weakReference.get();
                            if (fragment != null && fragment.getClass() != null) {
                                String name = fragment2.getClass().getName();
                                String simpleName = fragment2.getClass().getSimpleName();
                                CharSequence title = activity.getTitle();
                                String charSequence = title == null ? "" : title.toString();
                                am.c().a("End page view " + simpleName);
                                BDStatCore.this.d.onPageEndFrag(origApplicationContext, name, simpleName, charSequence, System.currentTimeMillis());
                            }
                        }
                    });
                }
            }
        }
    }

    private int a() {
        Class cls;
        Class cls2;
        Class cls3;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (ClassNotFoundException e2) {
            cls = null;
        }
        try {
            cls2 = Class.forName("android.support.v4.app.Fragment");
        } catch (ClassNotFoundException e3) {
            cls2 = null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i2 = 0;
        while (stackTrace != null && i2 < stackTrace.length) {
            String className = stackTrace[i2].getClassName();
            if (!TextUtils.isEmpty(className)) {
                if ("onResume".equals(stackTrace[i2].getMethodName())) {
                    try {
                        cls3 = Class.forName(className);
                    } catch (Throwable th) {
                        cls3 = null;
                    }
                    if (cls3 == null) {
                        continue;
                    } else if (Activity.class.isAssignableFrom(cls3)) {
                        return 1;
                    } else {
                        if (cls != null && cls.isAssignableFrom(cls3)) {
                            return 2;
                        }
                        if (cls2 != null && cls2.isAssignableFrom(cls3)) {
                            return 2;
                        }
                    }
                } else {
                    continue;
                }
            }
            i2++;
        }
        return 3;
    }

    private String b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement className : stackTrace) {
            String className2 = className.getClassName();
            if (!TextUtils.isEmpty(className2)) {
                Class cls = null;
                try {
                    cls = Class.forName(className2);
                } catch (Throwable th) {
                }
                if (cls != null && Activity.class.isAssignableFrom(cls)) {
                    return cls.getName();
                }
            }
        }
        return "";
    }

    public void onEvent(Context context, String str, String str2, int i2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEvent(context, str, str2, i2, extraInfo, map, z, false);
    }

    public void onEvent(Context context, String str, String str2, int i2, ExtraInfo extraInfo, Map<String, String> map, boolean z, boolean z2) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z3 = z;
            final String str4 = str;
            final int i3 = i2;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z4 = z2;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.d.onSessionStart(context2, currentTimeMillis, z3);
                    am.c().a("Put event" + BDStatCore.this.a(str4, str, i3, 0, map2, extraInfo2));
                    BDStatCore.this.e.onEvent(context2, BDStatCore.this.d.getSessionStartTime(), str4, str, i3, currentTimeMillis, extraInfo2, map2, z4);
                }
            });
        }
    }

    public void onEvent(Context context, String str, String str2, int i2, String str3, String str4, int i3, boolean z) {
        if (context != null) {
            init(context);
            b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str5 = str2;
            final Context context2 = context;
            final String str6 = str;
            final int i4 = i2;
            final String str7 = str3;
            final String str8 = str4;
            final int i5 = i3;
            final boolean z2 = z;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str5;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.d.onSessionStart(context2, currentTimeMillis, false);
                    am.c().a("Put event" + BDStatCore.this.a(str6, str, i4, 0, null, null));
                    BDStatCore.this.e.onEvent(context2, BDStatCore.this.d.getSessionStartTime(), str6, str, i4, currentTimeMillis, str7, str8, i5, z2);
                }
            });
        }
    }

    public void onEvent(Context context, String str, String str2, int i2, long j, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map) {
        onEvent(context, str, str2, i2, j, jSONArray, jSONArray2, str3, str4, str5, map, false);
    }

    public void onEvent(Context context, String str, String str2, int i2, long j, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map, boolean z) {
        if (context != null) {
            init(context);
            b(context);
            final String str6 = str2;
            final Context context2 = context;
            final long j2 = j;
            final String str7 = str;
            final int i3 = i2;
            final Map<String, String> map2 = map;
            final JSONArray jSONArray3 = jSONArray;
            final JSONArray jSONArray4 = jSONArray2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            final boolean z2 = z;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str6;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.d.onSessionStart(context2, j2, false);
                    am.c().a("Put event" + BDStatCore.this.a(str7, str, i3, 0, map2, null));
                    BDStatCore.this.e.onEvent(context2, BDStatCore.this.d.getSessionStartTime(), str7, str, i3, j2, jSONArray3, jSONArray4, str8, str9, str10, map2, z2);
                }
            });
        }
    }

    public void onEventStart(Context context, String str, String str2, boolean z) {
        if (context != null) {
            init(context);
            b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z2 = z;
            final String str4 = str;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.d.onSessionStart(context2, currentTimeMillis, z2);
                    am.c().a("Start event" + BDStatCore.this.a(str4, str, 1, -1, null, null));
                    BDStatCore.this.e.onEventStart(context2, str4, str, currentTimeMillis);
                }
            });
        }
    }

    public void onEventEnd(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        onEventEnd(context, str, str2, extraInfo, map, false);
    }

    public void onEventEnd(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        if (context != null) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final String str4 = str;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final Context context2 = context;
            final boolean z2 = z;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    long sessionStartTime = BDStatCore.this.d.getSessionStartTime();
                    am.c().a("End event" + BDStatCore.this.a(str4, str, 1, -1, map2, extraInfo2));
                    BDStatCore.this.e.onEventEnd(context2, sessionStartTime, str4, str, currentTimeMillis, extraInfo2, map2, z2);
                }
            });
        }
    }

    public void onEventDuration(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEventDuration(context, str, str2, j, extraInfo, map, z, false);
    }

    public void onEventDuration(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map, boolean z, boolean z2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            init(context);
            b(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final String str3 = str2;
            final Context context2 = context;
            final boolean z3 = z;
            final String str4 = str;
            final long j2 = j;
            final Map<String, String> map2 = map;
            final ExtraInfo extraInfo2 = extraInfo;
            final boolean z4 = z2;
            this.b.post(new Runnable() {
                public void run() {
                    String str = str3;
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    BDStatCore.this.d.onSessionStart(context2, currentTimeMillis, z3);
                    am.c().a("Put event" + BDStatCore.this.a(str4, str, 1, j2, map2, extraInfo2));
                    BDStatCore.this.e.onEventDuration(context2, BDStatCore.this.d.getSessionStartTime(), str4, str, currentTimeMillis, j2, extraInfo2, map2, z4);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String a(String str, String str2, int i2, long j, Map<String, String> map, ExtraInfo extraInfo) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        StringBuilder sb = new StringBuilder();
        if (map == null || map.size() == 0) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(map.toString());
            } catch (Exception e2) {
                jSONObject = null;
            }
        }
        if (extraInfo != null) {
            jSONObject2 = extraInfo.dumpToJson();
        }
        sb.append(" eventId " + str + ", with eventLabel " + str2 + ", with acc " + i2);
        if (j > 0) {
            sb.append(", with duration " + j);
        }
        if (!(jSONObject == null || jSONObject.length() == 0)) {
            sb.append(", with attributes " + jSONObject);
        }
        if (!(jSONObject2 == null || jSONObject2.length() == 0)) {
            sb.append(", with extraInfo " + jSONObject2);
        }
        return sb.toString();
    }

    public void onStat(final Context context, final String str) {
        if (!this.d.isSessionStart()) {
            this.b.post(new Runnable() {
                public void run() {
                    LogSender.instance().sendEmptyLogData(context, str);
                }
            });
        }
    }

    public void onErised(Context context, String str, String str2, String str3) {
        if (!this.d.isSessionStart()) {
            init(context);
            final long currentTimeMillis = System.currentTimeMillis();
            final Context context2 = context;
            final String str4 = str2;
            final String str5 = str3;
            this.b.post(new Runnable() {
                public void run() {
                    DataCore.instance().init(context2);
                    BDStatCore.this.e.onEvent(context2, currentTimeMillis, str4, str5, 1, currentTimeMillis, null, null, false);
                    DataCore.instance().saveLogData(context2, true, false, currentTimeMillis, false);
                    if (currentTimeMillis - BDStatCore.this.g > 30000 && bb.p(context2)) {
                        LogSender.instance().onSend(context2);
                        BDStatCore.this.g = currentTimeMillis;
                    }
                }
            });
        }
    }

    private void b(final Context context) {
        if (!this.h && context != null && this.i != null) {
            this.i.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (!t.b(context)) {
                            t.a(2).a(context);
                        }
                    } catch (Throwable th) {
                    }
                    BDStatCore.this.h = false;
                }
            }, Config.BPLUS_DELAY_TIME);
            this.h = true;
        }
    }
}
