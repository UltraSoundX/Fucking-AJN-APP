package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.g;
import com.tencent.smtt.utils.l;
import com.tencent.smtt.utils.n;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;

public class TbsLogReport {
    private static TbsLogReport a;
    private Handler b = null;
    private Context c;
    private boolean d = false;

    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2),
        TYPE_DOWNLOAD_DECOUPLE(3),
        TYPE_INSTALL_DECOUPLE(4),
        TYPE_COOKIE_DB_SWITCH(5),
        TYPE_SDK_REPORT_INFO(6);
        
        int a;

        private EventType(int i) {
            this.a = i;
        }
    }

    public static class TbsLogInfo implements Cloneable {
        int a;
        /* access modifiers changed from: private */
        public long b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public int h;
        /* access modifiers changed from: private */
        public String i;
        /* access modifiers changed from: private */
        public int j;
        /* access modifiers changed from: private */
        public int k;
        /* access modifiers changed from: private */
        public long l;
        /* access modifiers changed from: private */
        public long m;
        /* access modifiers changed from: private */
        public int n;
        /* access modifiers changed from: private */
        public String o;
        /* access modifiers changed from: private */
        public String p;
        /* access modifiers changed from: private */

        /* renamed from: q reason: collision with root package name */
        public long f434q;

        private TbsLogInfo() {
            resetArgs();
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e2) {
                return this;
            }
        }

        public void resetArgs() {
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 2;
            this.i = "unknown";
            this.j = 0;
            this.k = 2;
            this.l = 0;
            this.m = 0;
            this.n = 1;
            this.a = 0;
            this.o = null;
            this.p = null;
            this.f434q = 0;
        }

        public void setEventTime(long j2) {
            this.b = j2;
        }

        public void setDownloadUrl(String str) {
            if (this.c == null) {
                this.c = str;
            } else {
                this.c += ";" + str;
            }
        }

        public void setResolveIp(String str) {
            this.d = str;
        }

        public void setHttpCode(int i2) {
            this.e = i2;
        }

        public void setPatchUpdateFlag(int i2) {
            this.f = i2;
        }

        public void setDownloadCancel(int i2) {
            this.g = i2;
        }

        public void setUnpkgFlag(int i2) {
            this.h = i2;
        }

        public void setApn(String str) {
            this.i = str;
        }

        public void setNetworkType(int i2) {
            this.j = i2;
        }

        public void setDownFinalFlag(int i2) {
            this.k = i2;
        }

        public int getDownFinalFlag() {
            return this.k;
        }

        public void setPkgSize(long j2) {
            this.l = j2;
        }

        public void setDownConsumeTime(long j2) {
            this.m += j2;
        }

        public void setNetworkChange(int i2) {
            this.n = i2;
        }

        public void setErrorCode(int i2) {
            if (!(i2 == 100 || i2 == 110 || i2 == 120 || i2 == 111 || i2 >= 400)) {
                TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i2, true);
            }
            if (i2 == 111) {
                TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
            }
            this.a = i2;
        }

        public void setCheckErrorDetail(String str) {
            setErrorCode(108);
            this.o = str;
        }

        public void setFailDetail(String str) {
            if (str != null) {
                if (str.length() > 1024) {
                    str = str.substring(0, 1024);
                }
                this.p = str;
            }
        }

        public void setFailDetail(Throwable th) {
            if (th == null) {
                this.p = "";
                return;
            }
            String stackTraceString = Log.getStackTraceString(th);
            if (stackTraceString.length() > 1024) {
                stackTraceString = stackTraceString.substring(0, 1024);
            }
            this.p = stackTraceString;
        }

        public void setDownloadSize(long j2) {
            this.f434q += j2;
        }
    }

    private static class a {
        private final String a;
        private final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r4v0 */
        /* JADX WARNING: type inference failed for: r4v1, types: [java.util.zip.ZipOutputStream] */
        /* JADX WARNING: type inference failed for: r2v1, types: [java.util.zip.ZipOutputStream] */
        /* JADX WARNING: type inference failed for: r4v2 */
        /* JADX WARNING: type inference failed for: r4v3 */
        /* JADX WARNING: type inference failed for: r2v2 */
        /* JADX WARNING: type inference failed for: r3v0, types: [java.io.FileInputStream] */
        /* JADX WARNING: type inference failed for: r3v1 */
        /* JADX WARNING: type inference failed for: r2v3, types: [java.io.FileInputStream] */
        /* JADX WARNING: type inference failed for: r3v2 */
        /* JADX WARNING: type inference failed for: r2v4 */
        /* JADX WARNING: type inference failed for: r2v5 */
        /* JADX WARNING: type inference failed for: r2v9 */
        /* JADX WARNING: type inference failed for: r2v10 */
        /* JADX WARNING: type inference failed for: r2v11 */
        /* JADX WARNING: type inference failed for: r4v5 */
        /* JADX WARNING: type inference failed for: r3v4 */
        /* JADX WARNING: type inference failed for: r3v5 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x004e A[SYNTHETIC, Splitter:B:22:0x004e] */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0053 A[SYNTHETIC, Splitter:B:25:0x0053] */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0062 A[SYNTHETIC, Splitter:B:30:0x0062] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0067 A[SYNTHETIC, Splitter:B:33:0x0067] */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0089 A[SYNTHETIC, Splitter:B:51:0x0089] */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x008e A[SYNTHETIC, Splitter:B:54:0x008e] */
        /* JADX WARNING: Removed duplicated region for block: B:63:0x009f A[SYNTHETIC, Splitter:B:63:0x009f] */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x00a4 A[SYNTHETIC, Splitter:B:66:0x00a4] */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x00b7 A[SYNTHETIC, Splitter:B:77:0x00b7] */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x00bc A[SYNTHETIC, Splitter:B:80:0x00bc] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0056=Splitter:B:27:0x0056, B:82:0x00bf=Splitter:B:82:0x00bf} */
        /* JADX WARNING: Unknown variable types count: 5 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r8 = this;
                r2 = 0
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00ee, all -> 0x00e3 }
                java.lang.String r0 = r8.b     // Catch:{ Exception -> 0x00ee, all -> 0x00e3 }
                r5.<init>(r0)     // Catch:{ Exception -> 0x00ee, all -> 0x00e3 }
                java.util.zip.ZipOutputStream r4 = new java.util.zip.ZipOutputStream     // Catch:{ Exception -> 0x00f1, all -> 0x00e7 }
                java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00f1, all -> 0x00e7 }
                r0.<init>(r5)     // Catch:{ Exception -> 0x00f1, all -> 0x00e7 }
                r4.<init>(r0)     // Catch:{ Exception -> 0x00f1, all -> 0x00e7 }
                r0 = 2048(0x800, float:2.87E-42)
                byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                java.lang.String r6 = r8.a     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00fc, all -> 0x00b2 }
                r3.<init>(r6)     // Catch:{ Exception -> 0x00fc, all -> 0x00b2 }
                java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0100, all -> 0x00f4 }
                r7 = 2048(0x800, float:2.87E-42)
                r1.<init>(r3, r7)     // Catch:{ Exception -> 0x0100, all -> 0x00f4 }
                java.util.zip.ZipEntry r2 = new java.util.zip.ZipEntry     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                java.lang.String r7 = "/"
                int r7 = r6.lastIndexOf(r7)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                int r7 = r7 + 1
                java.lang.String r6 = r6.substring(r7)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                r2.<init>(r6)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                r4.putNextEntry(r2)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
            L_0x0038:
                r2 = 0
                r6 = 2048(0x800, float:2.87E-42)
                int r2 = r1.read(r0, r2, r6)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                r6 = -1
                if (r2 == r6) goto L_0x006b
                r6 = 0
                r4.write(r0, r6, r2)     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                goto L_0x0038
            L_0x0047:
                r0 = move-exception
                r2 = r3
            L_0x0049:
                r0.printStackTrace()     // Catch:{ all -> 0x00f9 }
                if (r1 == 0) goto L_0x0051
                r1.close()     // Catch:{ IOException -> 0x00a8 }
            L_0x0051:
                if (r2 == 0) goto L_0x0056
                r2.close()     // Catch:{ IOException -> 0x00ad }
            L_0x0056:
                java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                java.lang.String r1 = r8.b     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                r0.<init>(r1)     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                a(r0)     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                if (r4 == 0) goto L_0x0065
                r4.close()     // Catch:{ IOException -> 0x00ca }
            L_0x0065:
                if (r5 == 0) goto L_0x006a
                r5.close()     // Catch:{ IOException -> 0x00cf }
            L_0x006a:
                return
            L_0x006b:
                r4.flush()     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                r4.closeEntry()     // Catch:{ Exception -> 0x0047, all -> 0x00f7 }
                if (r1 == 0) goto L_0x0076
                r1.close()     // Catch:{ IOException -> 0x0097 }
            L_0x0076:
                if (r3 == 0) goto L_0x0056
                r3.close()     // Catch:{ IOException -> 0x007c }
                goto L_0x0056
            L_0x007c:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x0056
            L_0x0081:
                r0 = move-exception
                r2 = r4
                r1 = r5
            L_0x0084:
                r0.printStackTrace()     // Catch:{ all -> 0x00ea }
                if (r2 == 0) goto L_0x008c
                r2.close()     // Catch:{ IOException -> 0x00d4 }
            L_0x008c:
                if (r1 == 0) goto L_0x006a
                r1.close()     // Catch:{ IOException -> 0x0092 }
                goto L_0x006a
            L_0x0092:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x006a
            L_0x0097:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x0076
            L_0x009c:
                r0 = move-exception
            L_0x009d:
                if (r4 == 0) goto L_0x00a2
                r4.close()     // Catch:{ IOException -> 0x00d9 }
            L_0x00a2:
                if (r5 == 0) goto L_0x00a7
                r5.close()     // Catch:{ IOException -> 0x00de }
            L_0x00a7:
                throw r0
            L_0x00a8:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x0051
            L_0x00ad:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x0056
            L_0x00b2:
                r0 = move-exception
                r1 = r2
                r3 = r2
            L_0x00b5:
                if (r1 == 0) goto L_0x00ba
                r1.close()     // Catch:{ IOException -> 0x00c0 }
            L_0x00ba:
                if (r3 == 0) goto L_0x00bf
                r3.close()     // Catch:{ IOException -> 0x00c5 }
            L_0x00bf:
                throw r0     // Catch:{ Exception -> 0x0081, all -> 0x009c }
            L_0x00c0:
                r1 = move-exception
                r1.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x00ba
            L_0x00c5:
                r1 = move-exception
                r1.printStackTrace()     // Catch:{ Exception -> 0x0081, all -> 0x009c }
                goto L_0x00bf
            L_0x00ca:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0065
            L_0x00cf:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x006a
            L_0x00d4:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x008c
            L_0x00d9:
                r1 = move-exception
                r1.printStackTrace()
                goto L_0x00a2
            L_0x00de:
                r1 = move-exception
                r1.printStackTrace()
                goto L_0x00a7
            L_0x00e3:
                r0 = move-exception
                r4 = r2
                r5 = r2
                goto L_0x009d
            L_0x00e7:
                r0 = move-exception
                r4 = r2
                goto L_0x009d
            L_0x00ea:
                r0 = move-exception
                r4 = r2
                r5 = r1
                goto L_0x009d
            L_0x00ee:
                r0 = move-exception
                r1 = r2
                goto L_0x0084
            L_0x00f1:
                r0 = move-exception
                r1 = r5
                goto L_0x0084
            L_0x00f4:
                r0 = move-exception
                r1 = r2
                goto L_0x00b5
            L_0x00f7:
                r0 = move-exception
                goto L_0x00b5
            L_0x00f9:
                r0 = move-exception
                r3 = r2
                goto L_0x00b5
            L_0x00fc:
                r0 = move-exception
                r1 = r2
                goto L_0x0049
            L_0x0100:
                r0 = move-exception
                r1 = r2
                r2 = r3
                goto L_0x0049
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a.a():void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x003d A[SYNTHETIC, Splitter:B:19:0x003d] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x004a A[SYNTHETIC, Splitter:B:26:0x004a] */
        /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void a(java.io.File r6) throws java.io.IOException {
            /*
                r2 = 0
                java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0036, all -> 0x0046 }
                java.lang.String r0 = "rw"
                r1.<init>(r6, r0)     // Catch:{ Exception -> 0x0036, all -> 0x0046 }
                if (r1 == 0) goto L_0x002b
                java.lang.String r0 = "00001000"
                r2 = 2
                int r0 = java.lang.Integer.parseInt(r0, r2)     // Catch:{ Exception -> 0x0055 }
                r2 = 7
                r1.seek(r2)     // Catch:{ Exception -> 0x0055 }
                int r2 = r1.read()     // Catch:{ Exception -> 0x0055 }
                r3 = r2 & r0
                if (r3 <= 0) goto L_0x002b
                r4 = 7
                r1.seek(r4)     // Catch:{ Exception -> 0x0055 }
                r0 = r0 ^ -1
                r0 = r0 & 255(0xff, float:3.57E-43)
                r0 = r0 & r2
                r1.write(r0)     // Catch:{ Exception -> 0x0055 }
            L_0x002b:
                if (r1 == 0) goto L_0x0030
                r1.close()     // Catch:{ IOException -> 0x0031 }
            L_0x0030:
                return
            L_0x0031:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0030
            L_0x0036:
                r0 = move-exception
                r1 = r2
            L_0x0038:
                r0.printStackTrace()     // Catch:{ all -> 0x0053 }
                if (r1 == 0) goto L_0x0030
                r1.close()     // Catch:{ IOException -> 0x0041 }
                goto L_0x0030
            L_0x0041:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0030
            L_0x0046:
                r0 = move-exception
                r1 = r2
            L_0x0048:
                if (r1 == 0) goto L_0x004d
                r1.close()     // Catch:{ IOException -> 0x004e }
            L_0x004d:
                throw r0
            L_0x004e:
                r1 = move-exception
                r1.printStackTrace()
                goto L_0x004d
            L_0x0053:
                r0 = move-exception
                goto L_0x0048
            L_0x0055:
                r0 = move-exception
                goto L_0x0038
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.a.a(java.io.File):void");
        }
    }

    private TbsLogReport(Context context) {
        this.c = StubApp.getOrigApplicationContext(context.getApplicationContext());
        HandlerThread handlerThread = new HandlerThread("TbsLogReportThread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 600) {
                    if (message.obj instanceof TbsLogInfo) {
                        try {
                            TbsLogInfo tbsLogInfo = (TbsLogInfo) message.obj;
                            TbsLogReport.this.a(message.arg1, tbsLogInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (message.what == 601) {
                    TbsLogReport.this.b();
                }
            }
        };
    }

    public static TbsLogReport getInstance(Context context) {
        if (a == null) {
            synchronized (TbsLogReport.class) {
                if (a == null) {
                    a = new TbsLogReport(context);
                }
            }
        }
        return a;
    }

    public TbsLogInfo tbsLogInfo() {
        return new TbsLogInfo();
    }

    public void setInstallErrorCode(int i, String str) {
        setInstallErrorCode(i, str, EventType.TYPE_INSTALL);
    }

    public void setInstallErrorCode(int i, String str, EventType eventType) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(str);
        a(i, tbsLogInfo, eventType);
    }

    private void a(int i, TbsLogInfo tbsLogInfo, EventType eventType) {
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        QbSdk.m.onInstallFinish(i);
        eventReport(eventType, tbsLogInfo);
    }

    public void setInstallErrorCode(int i, Throwable th) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(th);
        a(i, tbsLogInfo, EventType.TYPE_INSTALL);
    }

    public void setLoadErrorCode(int i, String str) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        tbsLogInfo.setFailDetail(str);
        eventReport(EventType.TYPE_LOAD, tbsLogInfo);
    }

    public void setLoadErrorCode(int i, Throwable th) {
        String str = "NULL";
        if (th != null) {
            str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
        }
        setLoadErrorCode(i, str);
    }

    public void dailyReport() {
        this.b.sendEmptyMessage(601);
    }

    public void eventReport(EventType eventType, TbsLogInfo tbsLogInfo) {
        try {
            TbsLogInfo tbsLogInfo2 = (TbsLogInfo) tbsLogInfo.clone();
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 600;
            obtainMessage.arg1 = eventType.a;
            obtainMessage.obj = tbsLogInfo2;
            this.b.sendMessage(obtainMessage);
        } catch (Throwable th) {
            TbsLog.w("upload", "[TbsLogReport.eventReport] error, message=" + th.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, TbsLogInfo tbsLogInfo) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append(a(b.c(this.c)));
        sb.append(a(l.a(this.c)));
        sb.append(a(l.a().i(this.c)));
        String str2 = Build.MODEL;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        sb.append(a(str));
        String packageName = this.c.getPackageName();
        sb.append(a(packageName));
        if (TbsConfig.APP_WX.equals(packageName)) {
            sb.append(a(b.a(this.c, TbsDownloader.TBS_METADATA)));
        } else {
            sb.append(a(b.b(this.c)));
        }
        sb.append(a(a(tbsLogInfo.b)));
        sb.append(a(tbsLogInfo.c));
        sb.append(a(tbsLogInfo.d));
        sb.append(a(tbsLogInfo.e));
        sb.append(a(tbsLogInfo.f));
        sb.append(a(tbsLogInfo.g));
        sb.append(a(tbsLogInfo.h));
        sb.append(a(tbsLogInfo.i));
        sb.append(a(tbsLogInfo.j));
        sb.append(a(tbsLogInfo.k));
        sb.append(b(tbsLogInfo.f434q));
        sb.append(b(tbsLogInfo.l));
        sb.append(b(tbsLogInfo.m));
        sb.append(a(tbsLogInfo.n));
        sb.append(a(tbsLogInfo.a));
        sb.append(a(tbsLogInfo.o));
        sb.append(a(tbsLogInfo.p));
        sb.append(a(TbsDownloadConfig.getInstance(this.c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(a(b.f(this.c)));
        sb.append(a("4.3.0.1148_43697"));
        sb.append(false);
        SharedPreferences d2 = d();
        JSONArray a2 = a();
        JSONArray jSONArray = new JSONArray();
        if (jSONArray.length() >= 5) {
            for (int i2 = 4; i2 >= 1; i2--) {
                try {
                    jSONArray.put(a2.get(jSONArray.length() - i2));
                } catch (Exception e2) {
                    TbsLog.e("upload", "JSONArray transform error!");
                }
            }
        } else {
            jSONArray = a2;
        }
        jSONArray.put(sb.toString());
        Editor edit = d2.edit();
        edit.putString("tbs_download_upload", jSONArray.toString());
        edit.commit();
        if (this.d || i != EventType.TYPE_LOAD.a) {
            b();
        }
    }

    private JSONArray a() {
        String string = d().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() <= 5) {
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            int length = jSONArray.length() - 1;
            if (length <= jSONArray.length() - 5) {
                return jSONArray;
            }
            jSONArray2.put(jSONArray.get(length));
            return jSONArray2;
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00fb A[SYNTHETIC, Splitter:B:30:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0100 A[SYNTHETIC, Splitter:B:33:0x0100] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x014e A[SYNTHETIC, Splitter:B:51:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0153 A[SYNTHETIC, Splitter:B:54:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reportTbsLog() {
        /*
            r11 = this;
            r10 = 0
            r2 = 0
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            if (r0 == 0) goto L_0x0028
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r1 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x0028
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r1 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r1 = "false"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0028
            java.lang.String r0 = "upload"
            java.lang.String r1 = "[TbsLogReport.reportTbsLog] -- SET_SENDREQUEST_AND_UPLOAD is false"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0027:
            return
        L_0x0028:
            android.content.Context r0 = r11.c
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 3
            if (r0 != r1) goto L_0x0027
            java.lang.String r3 = com.tencent.smtt.utils.TbsLog.getTbsLogFilePath()
            if (r3 == 0) goto L_0x0027
            com.tencent.smtt.utils.h r0 = com.tencent.smtt.utils.h.a()
            java.lang.String r5 = r0.b()
            android.content.Context r0 = r11.c
            java.lang.String r0 = com.tencent.smtt.utils.b.c(r0)
            android.content.Context r1 = r11.c
            java.lang.String r4 = com.tencent.smtt.utils.b.f(r1)
            byte[] r1 = r0.getBytes()
            byte[] r0 = r4.getBytes()
            com.tencent.smtt.utils.h r4 = com.tencent.smtt.utils.h.a()     // Catch:{ Exception -> 0x017e }
            byte[] r1 = r4.a(r1)     // Catch:{ Exception -> 0x017e }
            com.tencent.smtt.utils.h r4 = com.tencent.smtt.utils.h.a()     // Catch:{ Exception -> 0x017e }
            byte[] r0 = r4.a(r0)     // Catch:{ Exception -> 0x017e }
        L_0x0063:
            java.lang.String r1 = com.tencent.smtt.utils.h.b(r1)
            java.lang.String r0 = com.tencent.smtt.utils.h.b(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            android.content.Context r6 = r11.c
            com.tencent.smtt.utils.n r6 = com.tencent.smtt.utils.n.a(r6)
            java.lang.String r6 = r6.e()
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.String r4 = "&aid="
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r6 = r0.toString()
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = "application/octet-stream"
            r7.put(r0, r1)
            java.lang.String r0 = "Charset"
            java.lang.String r1 = "UTF-8"
            r7.put(r0, r1)
            java.lang.String r0 = "QUA2"
            android.content.Context r1 = r11.c
            java.lang.String r1 = com.tencent.smtt.utils.l.a(r1)
            r7.put(r0, r1)
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r1 = com.tencent.smtt.utils.f.a     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            com.tencent.smtt.sdk.TbsLogReport$a r0 = new com.tencent.smtt.sdk.TbsLogReport$a     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            r1.<init>()     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r4 = com.tencent.smtt.utils.f.a     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r4 = "/tbslog_temp.zip"
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            r0.<init>(r3, r1)     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            r0.a()     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r0 = com.tencent.smtt.utils.f.a     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.lang.String r1 = "tbslog_temp.zip"
            r3.<init>(r0, r1)     // Catch:{ Exception -> 0x0170, all -> 0x0149 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0175, all -> 0x0168 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0175, all -> 0x0168 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x017a, all -> 0x016b }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x017a, all -> 0x016b }
            r1.<init>()     // Catch:{ Exception -> 0x017a, all -> 0x016b }
        L_0x00e9:
            int r8 = r4.read(r0)     // Catch:{ Exception -> 0x00f5 }
            r9 = -1
            if (r8 == r9) goto L_0x012a
            r9 = 0
            r1.write(r0, r9, r8)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00e9
        L_0x00f5:
            r0 = move-exception
        L_0x00f6:
            r0.printStackTrace()     // Catch:{ all -> 0x016d }
            if (r4 == 0) goto L_0x00fe
            r4.close()     // Catch:{ Exception -> 0x0160 }
        L_0x00fe:
            if (r1 == 0) goto L_0x0103
            r1.close()     // Catch:{ Exception -> 0x0162 }
        L_0x0103:
            if (r3 == 0) goto L_0x0181
            r3.delete()
            r0 = r2
        L_0x0109:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r2 = "&ek="
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.sdk.TbsLogReport$2 r2 = new com.tencent.smtt.sdk.TbsLogReport$2
            r2.<init>()
            com.tencent.smtt.utils.g.a(r1, r7, r0, r2, r10)
            goto L_0x0027
        L_0x012a:
            r1.flush()     // Catch:{ Exception -> 0x00f5 }
            com.tencent.smtt.utils.h r0 = com.tencent.smtt.utils.h.a()     // Catch:{ Exception -> 0x00f5 }
            byte[] r8 = r1.toByteArray()     // Catch:{ Exception -> 0x00f5 }
            byte[] r0 = r0.a(r8)     // Catch:{ Exception -> 0x00f5 }
            if (r4 == 0) goto L_0x013e
            r4.close()     // Catch:{ Exception -> 0x015c }
        L_0x013e:
            if (r1 == 0) goto L_0x0143
            r1.close()     // Catch:{ Exception -> 0x015e }
        L_0x0143:
            if (r3 == 0) goto L_0x0109
            r3.delete()
            goto L_0x0109
        L_0x0149:
            r0 = move-exception
            r3 = r2
            r4 = r2
        L_0x014c:
            if (r4 == 0) goto L_0x0151
            r4.close()     // Catch:{ Exception -> 0x0164 }
        L_0x0151:
            if (r2 == 0) goto L_0x0156
            r2.close()     // Catch:{ Exception -> 0x0166 }
        L_0x0156:
            if (r3 == 0) goto L_0x015b
            r3.delete()
        L_0x015b:
            throw r0
        L_0x015c:
            r2 = move-exception
            goto L_0x013e
        L_0x015e:
            r1 = move-exception
            goto L_0x0143
        L_0x0160:
            r0 = move-exception
            goto L_0x00fe
        L_0x0162:
            r0 = move-exception
            goto L_0x0103
        L_0x0164:
            r1 = move-exception
            goto L_0x0151
        L_0x0166:
            r1 = move-exception
            goto L_0x0156
        L_0x0168:
            r0 = move-exception
            r4 = r2
            goto L_0x014c
        L_0x016b:
            r0 = move-exception
            goto L_0x014c
        L_0x016d:
            r0 = move-exception
            r2 = r1
            goto L_0x014c
        L_0x0170:
            r0 = move-exception
            r1 = r2
            r3 = r2
            r4 = r2
            goto L_0x00f6
        L_0x0175:
            r0 = move-exception
            r1 = r2
            r4 = r2
            goto L_0x00f6
        L_0x017a:
            r0 = move-exception
            r1 = r2
            goto L_0x00f6
        L_0x017e:
            r4 = move-exception
            goto L_0x0063
        L_0x0181:
            r0 = r2
            goto L_0x0109
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.reportTbsLog():void");
    }

    /* access modifiers changed from: private */
    public void b() {
        if (QbSdk.n == null || !QbSdk.n.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.n.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray a2 = a();
            if (a2 == null || a2.length() == 0) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] no data");
                return;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + a2);
            try {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + g.a(n.a(this.c).c(), a2.toString().getBytes("utf-8"), new com.tencent.smtt.utils.g.a() {
                    public void a(int i) {
                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
                        if (i < 300) {
                            TbsLogReport.this.c();
                        }
                    }
                }, true) + " testcase: " + -1);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            TbsLog.i("upload", "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false");
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        Editor edit = d().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    private String a(long j) {
        boolean z = false;
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception e) {
            return z;
        }
    }

    private SharedPreferences d() {
        return this.c.getSharedPreferences("tbs_download_stat", 4);
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        return sb.append(str).append("|").toString();
    }

    private String a(int i) {
        return i + "|";
    }

    private String b(long j) {
        return j + "|";
    }

    public void clear() {
        try {
            Editor edit = d().edit();
            edit.clear();
            edit.commit();
        } catch (Exception e) {
        }
    }

    public void setShouldUploadEventReport(boolean z) {
        this.d = z;
    }

    public boolean getShouldUploadEventReport() {
        return this.d;
    }
}
