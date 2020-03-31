package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.stub.StubApp;
import com.tencent.android.tpush.common.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExceptionAnalysis {
    private static ExceptionAnalysis a = new ExceptionAnalysis();
    private boolean b = false;
    private Context c;
    private HeadObject d = new HeadObject();
    private String e;
    public Callback mCallback;

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    public static ExceptionAnalysis getInstance() {
        return a;
    }

    private ExceptionAnalysis() {
    }

    public ExceptionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    public void openExceptionAnalysis(Context context, boolean z) {
        if (context != null) {
            this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (this.c != null && !this.b) {
            this.b = true;
            ad.a().a(this.c);
            if (!z) {
                NativeCrashHandler.init(this.c);
            }
        }
    }

    public void saveCrashInfo(Context context, Throwable th, boolean z) {
        String str;
        if (context != null) {
            this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (this.c != null) {
            String th2 = th.toString();
            String str2 = "";
            if (!TextUtils.isEmpty(th2)) {
                try {
                    String[] split = th2.split(Config.TRACE_TODAY_VISIT_SPLIT);
                    if (split.length > 1) {
                        str2 = split[0];
                    } else {
                        str2 = th2;
                    }
                } catch (Exception e2) {
                    str2 = "";
                }
            }
            if (TextUtils.isEmpty(str2)) {
                str = th2;
            } else {
                str = str2;
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            int i = !z ? th instanceof Exception ? 11 : th instanceof Error ? 12 : 13 : 0;
            saveCrashInfo(this.c, System.currentTimeMillis(), obj, str, 0, i);
        }
    }

    public void saveCrashInfo(Context context, long j, String str, String str2, int i, int i2) {
        BDStatCore.instance().autoTrackSessionEndTime(context);
        if (context != null && str != null && !str.trim().equals("")) {
            try {
                StringBuilder sb = new StringBuilder(str);
                if (!TextUtils.isEmpty(this.e)) {
                    sb.append("\n");
                    sb.append("ExtraInfo:");
                    sb.append(this.e);
                }
                String appVersionName = CooperService.instance().getAppVersionName(context);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("t", j);
                jSONObject.put("c", sb.toString());
                jSONObject.put("y", str2);
                jSONObject.put("v", appVersionName);
                jSONObject.put(Config.EXCEPTION_CRASH_TYPE, i);
                jSONObject.put("mem", a(context));
                jSONObject.put(Config.EXCEPTION_CRASH_CHANNEL, i2);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
                JSONObject jSONObject2 = new JSONObject();
                this.d.installHeader(context, jSONObject2);
                jSONObject2.put("ss", 0);
                jSONObject2.put(Config.SEQUENCE_INDEX, 0);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Config.HEADER_PART, jSONObject2);
                jSONObject3.put(Config.PRINCIPAL_PART, new JSONArray());
                jSONObject3.put(Config.EVENT_PART, new JSONArray());
                jSONObject3.put(Config.EXCEPTION_PART, jSONArray);
                jSONObject3.put(Config.TRACE_PART, a());
                if (this.mCallback != null) {
                    this.mCallback.onCallback(jSONObject3);
                }
                at.a(context, Config.PREFIX_SEND_DATA + System.currentTimeMillis(), jSONObject3.toString(), false);
                am.c().a("dump exception, exception: " + str);
            } catch (Exception e2) {
            }
        }
    }

    private JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.TRACE_APPLICATION_SESSION, 0);
        } catch (Exception e2) {
        }
        try {
            jSONObject.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception e3) {
        }
        return jSONObject;
    }

    @SuppressLint({"NewApi"})
    private JSONObject a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        if (activityManager == null) {
            return null;
        }
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            if (VERSION.SDK_INT >= 16) {
                jSONObject.put(Config.EXCEPTION_MEMORY_TOTAL, memoryInfo.totalMem);
            }
            jSONObject.put(Config.EXCEPTION_MEMORY_FREE, memoryInfo.availMem);
            jSONObject.put(Config.EXCEPTION_MEMORY_LOW, memoryInfo.lowMemory ? 1 : 0);
            return jSONObject;
        } catch (Exception e2) {
            return jSONObject;
        }
    }

    public void setCrashExtraInfo(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 256) {
                str = str.substring(0, 256);
            }
            this.e = str;
        }
    }
}
