package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SessionAnalysis {
    private boolean a = false;
    private Map<String, a> b = new HashMap();
    private a c = new a();
    private a d = new a();
    private boolean e = false;
    private long f = 0;
    private Session g = new Session();
    private int h = 0;
    private int i = 0;
    private long j = 0;
    private boolean k = true;
    private LaunchInfo l;
    private LaunchInfo m;
    public Callback mCallback;

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    static class a {
        String a;
        long b;
        boolean c = false;

        public a() {
        }

        public a(String str) {
            this.a = str;
        }
    }

    public SessionAnalysis() {
    }

    public SessionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    public void setSessionTimeOut(int i2) {
        if (i2 < 1) {
            i2 = 30;
            am.c().b("[WARNING] SessionTimeout should be between 1 and 600. Default value[30] is used");
        } else if (i2 > 600) {
            am.c().b("[WARNING] SessionTimeout should be between 1 and 600. Default value[600] is used");
            i2 = 600;
        }
        this.h = i2 * 1000;
    }

    public int getSessionTimeOut() {
        if (this.h <= 0) {
            this.h = Config.SESSION_PERIOD;
        }
        return this.h;
    }

    public long getSessionStartTime() {
        return this.g.getStartTime();
    }

    public JSONObject getPageSessionHead() {
        return this.g.getPageSessionHead();
    }

    public int getSessionSize() {
        return this.i;
    }

    public void doSendLogCheck(Context context, long j2) {
        if (this.f > 0 && j2 - this.f > ((long) getSessionTimeOut())) {
            a(context, -1, false, false, 0);
        }
    }

    public void setAutoSend(boolean z) {
        this.k = z;
    }

    public void autoTrackLaunchInfo(LaunchInfo launchInfo, boolean z) {
        if (z) {
            this.l = launchInfo;
        } else {
            this.m = launchInfo;
        }
    }

    public void autoTrackSessionStartTime(Context context, long j2) {
        if (context != null) {
            this.g.setTrackStartTime(j2);
            this.j = j2;
        }
    }

    public void autoTrackSessionEndTime(Context context, long j2) {
        if (context != null) {
            this.g.setTrackEndTime(j2);
            a(context);
        }
    }

    public void onSessionStart(Context context, long j2, boolean z) {
        if (!this.a) {
            DataCore.instance().init(context);
            int i2 = 0;
            if (this.l != null) {
                i2 = this.l.getLaunchType(context);
            }
            a(context, j2, z, true, i2);
            this.a = true;
        }
    }

    public void onPageStart(Context context, String str, int i2, long j2) {
        onSessionStart(context, j2, false);
        if (!TextUtils.isEmpty(str)) {
            a a2 = a(str);
            if (a2 != null) {
                if (a2.c) {
                    am.c().c("[WARNING] 遗漏StatService.onPageEnd(), 请检查邻近页面埋点: " + str);
                }
                if (!this.e) {
                    a(context, this.f, j2, i2, 3);
                    this.e = true;
                }
                a2.c = true;
                a2.b = j2;
            }
        }
    }

    public void onPageEnd(Context context, String str, String str2, String str3, long j2, ExtraInfo extraInfo, boolean z) {
        this.e = false;
        if (!TextUtils.isEmpty(str)) {
            a a2 = a(str);
            if (a2 == null) {
                return;
            }
            if (!a2.c) {
                am.c().c("[WARNING] 遗漏StatService.onPageStart(), 请检查邻近页面埋点: " + str);
                return;
            }
            a(context, a2.a, str, a2.b, j2, str2, "", str3, false, extraInfo, z);
            b(str);
            this.f = j2;
        }
    }

    public void onPageStartAct(Context context, String str, long j2, boolean z) {
        onSessionStart(context, j2, false);
        if (!TextUtils.isEmpty(str)) {
            a aVar = z ? this.d : this.c;
            if (aVar.c && !z) {
                am.c().c("[WARNING] 遗漏StatService.onPause(Activity), 请检查邻近页面埋点: " + str);
            }
            if (!this.e) {
                a(context, this.f, j2, 1, 1);
                this.e = true;
            }
            aVar.c = true;
            aVar.a = str;
            aVar.b = j2;
        }
    }

    public void onPageEndAct(Context context, String str, String str2, String str3, long j2, boolean z, ExtraInfo extraInfo) {
        a aVar;
        this.e = false;
        if (z) {
            aVar = this.d;
        } else {
            aVar = this.c;
        }
        if (aVar.c) {
            a(context, aVar.a, str, aVar.b, j2, str2, str3, str, z, extraInfo, false);
            aVar.c = false;
            this.f = j2;
        } else if (!z) {
            am.c().c("[WARNING] 遗漏StatService.onResume(Activity), 请检查邻近页面埋点: " + str);
        }
    }

    public void onPageStartFrag(Context context, String str, long j2) {
        onSessionStart(context, j2, false);
        if (!TextUtils.isEmpty(str)) {
            a a2 = a(str);
            if (a2.c) {
                am.c().c("[WARNING] 遗漏StatService.onPause(Fragment), 请检查邻近页面埋点: " + str);
            }
            a(context, this.f, j2, 2, 2);
            a2.c = true;
            a2.a = str;
            a2.b = j2;
        }
    }

    public void onPageEndFrag(Context context, String str, String str2, String str3, long j2) {
        if (!TextUtils.isEmpty(str)) {
            a a2 = a(str);
            if (a2 == null) {
                return;
            }
            if (!a2.c) {
                am.c().c("[WARNING] 遗漏StatService.onResume(Fragment), 请检查邻近页面埋点: " + str);
                return;
            }
            a(context, a2.a, str, a2.b, j2, str2, str3, null, false, null, false);
            b(str);
            this.f = j2;
        }
    }

    private void a(Context context, long j2, long j3, int i2, int i3) {
        int i4;
        if (j3 - j2 > ((long) getSessionTimeOut())) {
            if (j2 > 0) {
                if (2 == i3) {
                    this.g.setEndTime(j2);
                }
                if (this.m != null) {
                    i4 = this.m.getLaunchType(context);
                } else {
                    i4 = 0;
                }
                a(context, j3, false, false, i4);
            }
            this.g.setTrackStartTime(this.j);
            this.g.setInvokeType(i2);
        }
    }

    private void a(Context context, String str, String str2, long j2, long j3, String str3, String str4, String str5, boolean z, ExtraInfo extraInfo, boolean z2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.equals(str2)) {
            this.g.addPageView(new a(str3, str4, str5, j3 - j2, j2, z, extraInfo, z2));
            this.g.setEndTime(j3);
            a(context);
        }
    }

    private a a(String str) {
        if (!this.b.containsKey(str)) {
            this.b.put(str, new a(str));
        }
        return (a) this.b.get(str);
    }

    private void a(Context context, long j2, boolean z, boolean z2, int i2) {
        if (this.g.hasEnd()) {
            DataCore.instance().putSession(this.g);
            DataCore.instance().flush(context);
            ah.a(this.g.getPageSessionHead());
            this.g.setEndTime(0);
        }
        boolean z3 = j2 > 0;
        long startTime = z3 ? j2 : this.g.getStartTime();
        if (z3) {
            this.g.reset();
            this.g.setStartTime(j2);
            if (0 != 0) {
                this.g.setLaunchInfo(null);
            }
        }
        DataCore.instance().saveLogData(context, z3, z, startTime, z2, null);
        if (this.mCallback != null) {
            this.mCallback.onCallback(DataCore.instance().getLogData());
        }
        if (z3 || this.k) {
            LogSender.instance().onSend(context);
        }
        clearLastSessionCache(context);
    }

    private void a(Context context) {
        if (this.g.hasStart()) {
            String jSONObject = this.g.constructJSONObject().toString();
            this.i = jSONObject.getBytes().length;
            at.a(context, bb.s(context) + Config.LAST_SESSION_FILE_NAME, jSONObject, false);
        }
    }

    public void clearLastSessionCache(Context context) {
        if (context != null) {
            at.a(context, bb.s(context) + Config.LAST_SESSION_FILE_NAME, new JSONObject().toString(), false);
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str) && this.b.containsKey(str)) {
            this.b.remove(str);
        }
    }

    public boolean isSessionStart() {
        return this.g.getStartTime() > 0;
    }
}
