package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.ar.a;
import com.stub.StubApp;
import com.tencent.android.tpush.SettingsContentProvider;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class LogSender {
    private static LogSender a = new LogSender();
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public int c = 0;
    /* access modifiers changed from: private */
    public int d = 1;
    /* access modifiers changed from: private */
    public SendStrategyEnum e = SendStrategyEnum.APP_START;
    /* access modifiers changed from: private */
    public Timer f;
    /* access modifiers changed from: private */
    public Handler g;

    public static LogSender instance() {
        return a;
    }

    private LogSender() {
        HandlerThread handlerThread = new HandlerThread("LogSenderThread");
        handlerThread.start();
        this.g = new Handler(handlerThread.getLooper());
    }

    public void setLogSenderDelayed(int i) {
        if (i >= 0 && i <= 30) {
            this.c = i;
        }
    }

    public void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (!sendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.e = sendStrategyEnum;
            av.a().a(context, this.e.ordinal());
            if (sendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
                av.a().b(context, 24);
            }
        } else if (i > 0 && i <= 24) {
            this.d = i;
            this.e = SendStrategyEnum.SET_TIME_INTERVAL;
            av.a().a(context, this.e.ordinal());
            av.a().b(context, this.d);
        }
        this.b = z;
        av.a().a(context, this.b);
    }

    public void onSend(final Context context) {
        if (context != null) {
            context = StubApp.getOrigApplicationContext(context.getApplicationContext());
        }
        if (context != null) {
            this.g.post(new Runnable() {
                public void run() {
                    if (LogSender.this.f != null) {
                        LogSender.this.f.cancel();
                        LogSender.this.f = null;
                    }
                    LogSender.this.e = SendStrategyEnum.values()[av.a().b(context)];
                    LogSender.this.d = av.a().c(context);
                    LogSender.this.b = av.a().d(context);
                    if (LogSender.this.e.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
                        LogSender.this.setSendingLogTimer(context);
                    } else if (LogSender.this.e.equals(SendStrategyEnum.ONCE_A_DAY)) {
                        LogSender.this.setSendingLogTimer(context);
                    }
                    LogSender.this.g.postDelayed(new Runnable() {
                        public void run() {
                            LogSender.this.a(context);
                        }
                    }, (long) (LogSender.this.c * 1000));
                }
            });
        }
    }

    public void setSendingLogTimer(Context context) {
        final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        long j = (long) (this.d * 3600000);
        try {
            this.f = new Timer();
            this.f.schedule(new TimerTask() {
                public void run() {
                    LogSender.this.a(origApplicationContext);
                }
            }, j, j);
        } catch (Exception e2) {
        }
    }

    public void saveLogData(Context context, String str, boolean z) {
        at.a(context, (z ? Config.PREFIX_SEND_DATA_FULL : Config.PREFIX_SEND_DATA) + System.currentTimeMillis(), str, false);
        if (z) {
            a(context, (long) Config.FULL_TRACE_LOG_LIMIT, Config.PREFIX_SEND_DATA_FULL);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004b A[SYNTHETIC, Splitter:B:24:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004f A[LOOP:0: B:1:0x0012->B:27:0x004f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x002e A[EDGE_INSN: B:36:0x002e->B:13:0x002e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r15, long r16, java.lang.String r18) {
        /*
            r14 = this;
            r8 = 0
            r0 = r18
            java.util.ArrayList r9 = r14.a(r15, r0)
            r4 = 0
            int r2 = r9.size()
            int r2 = r2 + -1
            r6 = r4
            r3 = r8
            r4 = r2
        L_0x0012:
            if (r4 < 0) goto L_0x002e
            java.lang.Object r2 = r9.get(r4)     // Catch:{ Exception -> 0x003f, all -> 0x0048 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x003f, all -> 0x0048 }
            java.io.FileInputStream r2 = r15.openFileInput(r2)     // Catch:{ Exception -> 0x003f, all -> 0x0048 }
            int r3 = r2.available()     // Catch:{ Exception -> 0x005f, all -> 0x005a }
            long r10 = (long) r3
            long r6 = r6 + r10
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ Exception -> 0x0053 }
        L_0x0029:
            r3 = r8
        L_0x002a:
            int r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x004f
        L_0x002e:
            r2 = 0
            r3 = r2
        L_0x0030:
            if (r3 > r4) goto L_0x0059
            java.lang.Object r2 = r9.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            com.baidu.mobstat.at.b(r15, r2)
            int r2 = r3 + 1
            r3 = r2
            goto L_0x0030
        L_0x003f:
            r2 = move-exception
            r2 = r3
        L_0x0041:
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ Exception -> 0x0055 }
        L_0x0046:
            r3 = r8
            goto L_0x002a
        L_0x0048:
            r2 = move-exception
        L_0x0049:
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch:{ Exception -> 0x0057 }
        L_0x004e:
            throw r2
        L_0x004f:
            int r2 = r4 + -1
            r4 = r2
            goto L_0x0012
        L_0x0053:
            r2 = move-exception
            goto L_0x0029
        L_0x0055:
            r2 = move-exception
            goto L_0x0046
        L_0x0057:
            r3 = move-exception
            goto L_0x004e
        L_0x0059:
            return
        L_0x005a:
            r3 = move-exception
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x0049
        L_0x005f:
            r3 = move-exception
            goto L_0x0041
        L_0x0061:
            r3 = r2
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.LogSender.a(android.content.Context, long, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public ArrayList<String> a(Context context, final String str) {
        Object[] objArr;
        ArrayList<String> arrayList = new ArrayList<>();
        if (context != null) {
            File filesDir = context.getFilesDir();
            if (filesDir != null && filesDir.exists()) {
                Object[] objArr2 = null;
                try {
                    objArr = filesDir.list(new FilenameFilter() {
                        public boolean accept(File file, String str) {
                            if (str.startsWith(str)) {
                                return true;
                            }
                            return false;
                        }
                    });
                } catch (Exception e2) {
                    objArr = objArr2;
                }
                if (!(objArr == null || objArr.length == 0)) {
                    try {
                        Arrays.sort(objArr, new Comparator<String>() {
                            /* renamed from: a */
                            public int compare(String str, String str2) {
                                return str.compareTo(str2);
                            }
                        });
                    } catch (Exception e3) {
                    }
                    for (Object add : objArr) {
                        arrayList.add(add);
                    }
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(final Context context) {
        if (!this.b || bb.p(context)) {
            this.g.post(new Runnable() {
                public void run() {
                    boolean z;
                    int i;
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(LogSender.this.a(context, Config.PREFIX_SEND_DATA));
                        arrayList.addAll(LogSender.this.a(context, Config.PREFIX_SEND_DATA_FULL));
                        Iterator it = arrayList.iterator();
                        int i2 = 0;
                        while (it.hasNext()) {
                            String str = (String) it.next();
                            String a2 = at.a(context, str);
                            if (TextUtils.isEmpty(a2)) {
                                at.b(context, str);
                            } else {
                                if (str.contains(Config.PREFIX_SEND_DATA_FULL)) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (LogSender.this.a(context, a2, z)) {
                                    at.b(context, str);
                                    i = 0;
                                } else {
                                    LogSender.b(context, str, a2);
                                    i = i2 + 1;
                                    if (i >= 5) {
                                        return;
                                    }
                                }
                                i2 = i;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    public void sendLogData(Context context, final String str, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            if (z) {
                b(origApplicationContext, str);
            } else {
                this.g.post(new Runnable() {
                    public void run() {
                        LogSender.this.b(origApplicationContext, str);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(Context context, String str) {
        String str2 = Config.PREFIX_SEND_DATA + System.currentTimeMillis();
        at.a(context, str2, str, false);
        if (c(context, str)) {
            at.b(context, str2);
        } else {
            b(context, str2, str);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str, String str2) {
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(str2);
        } catch (Exception e2) {
        }
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get(Config.TRACE_PART);
                jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
            } catch (Exception e3) {
            }
            at.a(context, str, jSONObject.toString(), false);
        }
    }

    public void sendEmptyLogData(Context context, final String str) {
        final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.g.post(new Runnable() {
            public void run() {
                String constructLogWithEmptyBody = DataCore.instance().constructLogWithEmptyBody(origApplicationContext, str);
                if (!TextUtils.isEmpty(constructLogWithEmptyBody)) {
                    LogSender.this.c(origApplicationContext, constructLogWithEmptyBody);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean c(Context context, String str) {
        return a(context, str, false);
    }

    /* access modifiers changed from: private */
    public boolean a(Context context, String str, boolean z) {
        boolean z2 = false;
        if (!z) {
            am.c().a("Start send log \n" + str);
        }
        if (!this.b || bb.p(context)) {
            String str2 = Config.LOG_SEND_URL;
            if (z) {
                str2 = Config.LOG_FULL_SEND_URL;
            }
            try {
                c(context, str2, str);
                z2 = true;
            } catch (Exception e2) {
                am.c().c((Throwable) e2);
            }
            if (!z) {
                am.c().a("Send log " + (z2 ? "success" : "failed"));
            }
            return z2;
        }
        am.c().a("[WARNING] wifi not available, log will be cached, next time will try to resend");
        return false;
    }

    private String c(Context context, String str, String str2) throws Exception {
        if (!str.startsWith("https://")) {
            return e(context, str, str2);
        }
        return d(context, str, str2);
    }

    private String d(Context context, String str, String str2) throws IOException {
        HttpURLConnection d2 = at.d(context, str);
        d2.setDoOutput(true);
        d2.setInstanceFollowRedirects(false);
        d2.setUseCaches(false);
        d2.setRequestProperty("Content-Type", "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONObject(Config.HEADER_PART);
            d2.setRequestProperty("mtj_appkey", jSONObject.getString(Config.APP_KEY));
            d2.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            d2.setRequestProperty("mtj_os", jSONObject.getString(Config.OS));
            d2.setRequestProperty("mtj_pn", jSONObject.getString(Config.PACKAGE_NAME));
            d2.setRequestProperty("mtj_tg", jSONObject.getString(Config.SDK_TAG));
            d2.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        d2.connect();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(d2.getOutputStream())));
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d2.getInputStream()));
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
            }
            int contentLength = d2.getContentLength();
            if (d2.getResponseCode() == 200 && contentLength == 0) {
                return sb.toString();
            }
            throw new IOException("http code = " + d2.getResponseCode() + "; contentResponse = " + sb);
        } finally {
            d2.disconnect();
        }
    }

    private String e(Context context, String str, String str2) throws Exception {
        HttpURLConnection d2 = at.d(context, str);
        d2.setDoOutput(true);
        d2.setInstanceFollowRedirects(false);
        d2.setUseCaches(false);
        d2.setRequestProperty("Content-Type", "gzip");
        byte[] a2 = a.a();
        byte[] b2 = a.b();
        d2.setRequestProperty(SettingsContentProvider.KEY, ba.a(a2));
        d2.setRequestProperty("iv", ba.a(b2));
        byte[] a3 = a.a(a2, b2, str2.getBytes("utf-8"));
        d2.connect();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(d2.getOutputStream());
            gZIPOutputStream.write(a3);
            gZIPOutputStream.flush();
            gZIPOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d2.getInputStream()));
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
            }
            int contentLength = d2.getContentLength();
            if (d2.getResponseCode() == 200 && contentLength == 0) {
                return sb.toString();
            }
            throw new IOException("http code = " + d2.getResponseCode() + "; contentResponse = " + sb);
        } finally {
            d2.disconnect();
        }
    }
}
