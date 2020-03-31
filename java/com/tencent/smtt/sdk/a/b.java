package com.tencent.smtt.sdk.a;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.stub.StubApp;
import com.tencent.bigdata.dataacquisition.DeviceInfos;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsCoreLoadStat;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.h;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.l;
import com.tencent.smtt.utils.n;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONObject;

/* compiled from: HttpUtils */
public class b {
    public static byte[] a;

    static {
        a = null;
        try {
            a = "65dRa93L".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
        }
    }

    public static void a(final ThirdAppInfoNew thirdAppInfoNew, final Context context) {
        new Thread("HttpUtils") {
            public void run() {
                String str;
                String str2;
                String str3;
                JSONObject jSONObject;
                byte[] a2;
                thirdAppInfoNew.sCpu = com.tencent.smtt.utils.b.a();
                if (VERSION.SDK_INT >= 8) {
                    if (b.a == null) {
                        try {
                            b.a = "65dRa93L".getBytes("utf-8");
                        } catch (UnsupportedEncodingException e) {
                            b.a = null;
                            TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                        }
                    }
                    if (b.a == null) {
                        TbsLog.e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                        return;
                    }
                    String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsConfigKey.KEY_DESkEY_TOKEN, "");
                    String str4 = "";
                    String str5 = "";
                    if (!TextUtils.isEmpty(string)) {
                        str = string.substring(0, string.indexOf("&"));
                        str2 = string.substring(string.indexOf("&") + 1, string.length());
                    } else {
                        String str6 = str5;
                        str = str4;
                        str2 = str6;
                    }
                    boolean z = TextUtils.isEmpty(str) || str.length() != 96 || TextUtils.isEmpty(str2) || str2.length() != 24;
                    try {
                        n a3 = n.a();
                        if (z) {
                            str3 = a3.b() + h.a().b();
                        } else {
                            str3 = a3.f() + str;
                        }
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setConnectTimeout(20000);
                        if (VERSION.SDK_INT > 13) {
                            httpURLConnection.setRequestProperty("Connection", "close");
                        }
                        try {
                            jSONObject = b.c(thirdAppInfoNew, context);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            jSONObject = null;
                        }
                        if (jSONObject == null) {
                            TbsLog.e("sdkreport", "post -- jsonData is null!");
                            return;
                        }
                        try {
                            byte[] bytes = jSONObject.toString().getBytes("utf-8");
                            if (z) {
                                a2 = h.a().a(bytes);
                            } else {
                                a2 = h.a(bytes, str2);
                            }
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a2.length));
                            try {
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                outputStream.write(a2);
                                outputStream.flush();
                                if (httpURLConnection.getResponseCode() == 200) {
                                    TbsLog.i("sdkreport", "Post successful!");
                                    TbsLog.i("sdkreport", "SIGNATURE is " + jSONObject.getString("SIGNATURE"));
                                    b.b(context, b.b(httpURLConnection, str2, z));
                                    new TbsDownloadUpload(context).clearUploadCode();
                                    return;
                                }
                                TbsLog.e("sdkreport", "Post failed -- not 200 code is " + httpURLConnection.getResponseCode());
                                TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo.setErrorCode(ErrorCode.PV_UPLOAD_ERROR);
                                tbsLogInfo.setFailDetail("" + httpURLConnection.getResponseCode());
                                TbsLogReport.getInstance(context).eventReport(EventType.TYPE_DOWNLOAD, tbsLogInfo);
                            } catch (Throwable th) {
                                TbsLog.e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                                TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo2.setErrorCode(ErrorCode.PV_UPLOAD_ERROR);
                                tbsLogInfo2.setFailDetail(th);
                                TbsLogReport.getInstance(context).eventReport(EventType.TYPE_DOWNLOAD, tbsLogInfo2);
                            }
                        } catch (Throwable th2) {
                        }
                    } catch (IOException e3) {
                        TbsLog.e("sdkreport", "Post failed -- IOException:" + e3);
                    } catch (AssertionError e4) {
                        TbsLog.e("sdkreport", "Post failed -- AssertionError:" + e4);
                    } catch (NoClassDefFoundError e5) {
                        TbsLog.e("sdkreport", "Post failed -- NoClassDefFoundError:" + e5);
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public static JSONObject c(ThirdAppInfoNew thirdAppInfoNew, Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("APPNAME", thirdAppInfoNew.sAppName);
            jSONObject.put("TIME", thirdAppInfoNew.sTime);
            jSONObject.put("QUA2", thirdAppInfoNew.sQua2);
            jSONObject.put("LC", thirdAppInfoNew.sLc);
            jSONObject.put("GUID", thirdAppInfoNew.sGuid);
            jSONObject.put("IMEI", thirdAppInfoNew.sImei);
            jSONObject.put("IMSI", thirdAppInfoNew.sImsi);
            jSONObject.put("MAC", thirdAppInfoNew.sMac);
            jSONObject.put("PV", thirdAppInfoNew.iPv);
            jSONObject.put("CORETYPE", thirdAppInfoNew.iCoreType);
            jSONObject.put("APPVN", thirdAppInfoNew.sAppVersionName);
            jSONObject.put("APPMETADATA", thirdAppInfoNew.sMetaData);
            jSONObject.put("VERSION_CODE", thirdAppInfoNew.sVersionCode);
            jSONObject.put("CPU", thirdAppInfoNew.sCpu);
            if (TbsConfig.APP_WX.equals(thirdAppInfoNew.sAppName) || TbsConfig.APP_QQ.equals(thirdAppInfoNew.sAppName) || TbsConfig.APP_DEMO.equals(thirdAppInfoNew.sAppName)) {
                TbsDownloadUpload tbsDownloadUpload = new TbsDownloadUpload(context);
                tbsDownloadUpload.readTbsDownloadInfo(context);
                int needDownloadCode = tbsDownloadUpload.getNeedDownloadCode();
                int startDownloadCode = tbsDownloadUpload.getStartDownloadCode();
                int needDownloadReturn = tbsDownloadUpload.getNeedDownloadReturn();
                jSONObject.put("SIGNATURE", "" + needDownloadCode + Config.TRACE_TODAY_VISIT_SPLIT + startDownloadCode + Config.TRACE_TODAY_VISIT_SPLIT + needDownloadReturn + Config.TRACE_TODAY_VISIT_SPLIT + tbsDownloadUpload.getLocalCoreVersion());
            } else if (thirdAppInfoNew.sAppSignature == null) {
                jSONObject.put("SIGNATURE", "0");
            } else {
                jSONObject.put("SIGNATURE", thirdAppInfoNew.sAppSignature);
            }
            jSONObject.put("PROTOCOL_VERSION", 3);
            jSONObject.put("ANDROID_ID", thirdAppInfoNew.sAndroidID);
            if (TbsShareManager.isThirdPartyApp(context)) {
                jSONObject.put("HOST_COREVERSION", TbsShareManager.getHostCoreVersions(context));
            } else {
                jSONObject.put("HOST_COREVERSION", TbsDownloader.getCoreShareDecoupleCoreVersionByContext(context));
                jSONObject.put("DECOUPLE_COREVERSION", TbsDownloader.getCoreShareDecoupleCoreVersionByContext(context));
            }
            if (thirdAppInfoNew.iCoreType == 0) {
                jSONObject.put("WIFICONNECTEDTIME", thirdAppInfoNew.sWifiConnectedTime);
                jSONObject.put("CORE_EXIST", thirdAppInfoNew.localCoreVersion);
                int i = TbsCoreLoadStat.mLoadErrorCode;
                if (thirdAppInfoNew.localCoreVersion <= 0) {
                    jSONObject.put("TBS_ERROR_CODE", TbsDownloadConfig.getInstance(context).getDownloadInterruptCode());
                } else {
                    jSONObject.put("TBS_ERROR_CODE", i);
                }
                if (i == -1) {
                    TbsLog.e("sdkreport", "ATTENTION: Load errorCode missed!");
                }
            }
            TbsDownloadConfig.getInstance(context).uploadDownloadInterruptCodeIfNeeded(context);
            try {
                if (QbSdk.getTID() == null) {
                    return jSONObject;
                }
                if (thirdAppInfoNew.sAppName.equals(TbsConfig.APP_QQ)) {
                    jSONObject.put("TID", i.a().a(QbSdk.getTID()));
                    jSONObject.put("TIDTYPE", 1);
                    return jSONObject;
                } else if (!thirdAppInfoNew.sAppName.equals(TbsConfig.APP_WX)) {
                    return jSONObject;
                } else {
                    jSONObject.put("TID", QbSdk.getTID());
                    jSONObject.put("TIDTYPE", 0);
                    return jSONObject;
                }
            } catch (Exception e) {
                return jSONObject;
            }
        } catch (Exception e2) {
            TbsLog.e("sdkreport", "getPostData exception!");
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3, int i, boolean z, long j, boolean z2) {
        String str4;
        if (QbSdk.getSettings() == null || !QbSdk.getSettings().containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.getSettings().get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            String str5 = "";
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                if (TbsConfig.APP_QQ.equals(applicationInfo.packageName)) {
                    str5 = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0).versionName;
                    if (!TextUtils.isEmpty(QbSdk.getQQBuildNumber())) {
                        str5 = str5 + "." + QbSdk.getQQBuildNumber();
                    }
                }
                str4 = str5;
            } catch (Exception e) {
                Exception exc = e;
                str4 = str5;
                exc.printStackTrace();
            }
            try {
                ThirdAppInfoNew thirdAppInfoNew = new ThirdAppInfoNew();
                thirdAppInfoNew.sAppName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().packageName;
                n.a(context);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                thirdAppInfoNew.sTime = simpleDateFormat.format(Calendar.getInstance().getTime());
                thirdAppInfoNew.sVersionCode = com.tencent.smtt.utils.b.b(context);
                String a2 = com.tencent.smtt.utils.b.a(context, TbsDownloader.TBS_METADATA);
                if (!TextUtils.isEmpty(a2)) {
                    thirdAppInfoNew.sMetaData = a2;
                }
                thirdAppInfoNew.sGuid = str;
                if (z) {
                    thirdAppInfoNew.sQua2 = str2;
                    thirdAppInfoNew.bIsSandboxMode = z2;
                } else {
                    thirdAppInfoNew.sQua2 = l.a(context);
                }
                thirdAppInfoNew.sLc = str3;
                String e2 = com.tencent.smtt.utils.b.e(context);
                String c = com.tencent.smtt.utils.b.c(context);
                String d = com.tencent.smtt.utils.b.d(context);
                String f = com.tencent.smtt.utils.b.f(context);
                if (c != null && !"".equals(c)) {
                    thirdAppInfoNew.sImei = c;
                }
                if (d != null && !"".equals(d)) {
                    thirdAppInfoNew.sImsi = d;
                }
                if (!TextUtils.isEmpty(f)) {
                    thirdAppInfoNew.sAndroidID = f;
                }
                if (e2 != null && !"".equals(e2)) {
                    thirdAppInfoNew.sMac = e2;
                }
                thirdAppInfoNew.iPv = (long) i;
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    thirdAppInfoNew.iCoreType = z ? 1 : 0;
                    if (z && z2) {
                        thirdAppInfoNew.iCoreType = 3;
                    }
                } else if (z) {
                    if (TbsShareManager.getCoreFormOwn()) {
                        thirdAppInfoNew.iCoreType = 2;
                    } else {
                        thirdAppInfoNew.iCoreType = 1;
                    }
                    if (z2) {
                        thirdAppInfoNew.iCoreType = 3;
                    }
                } else {
                    thirdAppInfoNew.iCoreType = 0;
                }
                thirdAppInfoNew.sAppVersionName = str4;
                thirdAppInfoNew.sAppSignature = a(context);
                if (!z) {
                    thirdAppInfoNew.sWifiConnectedTime = j;
                    thirdAppInfoNew.localCoreVersion = QbSdk.getTbsVersion(context);
                }
                a(thirdAppInfoNew, StubApp.getOrigApplicationContext(context.getApplicationContext()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            TbsLog.i("sdkreport", "[HttpUtils.doReport] -- SET_SENDREQUEST_AND_UPLOAD is false");
        }
    }

    private static String a(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray == null) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(byteArray);
            byte[] digest = instance.digest();
            if (digest == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder("");
            if (digest == null || digest.length <= 0) {
                return null;
            }
            for (int i = 0; i < digest.length; i++) {
                String upperCase = Integer.toHexString(digest[i] & DeviceInfos.NETWORK_TYPE_UNCONNECTED).toUpperCase();
                if (i > 0) {
                    sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
                }
                if (upperCase.length() < 2) {
                    sb.append(0);
                }
                sb.append(upperCase);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0037 A[SYNTHETIC, Splitter:B:20:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A[SYNTHETIC, Splitter:B:23:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c3 A[SYNTHETIC, Splitter:B:60:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00c8 A[SYNTHETIC, Splitter:B:63:0x00c8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.net.HttpURLConnection r6, java.lang.String r7, boolean r8) {
        /*
            r2 = 0
            java.lang.String r0 = ""
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            java.lang.String r3 = r6.getContentEncoding()     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            if (r3 == 0) goto L_0x0062
            java.lang.String r4 = "gzip"
            boolean r4 = r3.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            if (r4 == 0) goto L_0x0062
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
        L_0x001a:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x00de, all -> 0x00d6 }
            r3.<init>()     // Catch:{ Exception -> 0x00de, all -> 0x00d6 }
            r1 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
        L_0x0023:
            int r2 = r4.read(r1)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            r5 = -1
            if (r2 == r5) goto L_0x007d
            r5 = 0
            r3.write(r1, r5, r2)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            goto L_0x0023
        L_0x002f:
            r1 = move-exception
            r2 = r3
            r3 = r4
        L_0x0032:
            r1.printStackTrace()     // Catch:{ all -> 0x00db }
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x003a:
            if (r3 == 0) goto L_0x003f
            r3.close()     // Catch:{ IOException -> 0x00ba }
        L_0x003f:
            java.lang.String r1 = "HttpUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getResponseFromConnection,response="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r3 = ";isUseRSA="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            return r0
        L_0x0062:
            if (r3 == 0) goto L_0x007b
            java.lang.String r4 = "deflate"
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            if (r3 == 0) goto L_0x007b
            java.util.zip.InflaterInputStream r4 = new java.util.zip.InflaterInputStream     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            java.util.zip.Inflater r3 = new java.util.zip.Inflater     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            r5 = 1
            r3.<init>(r5)     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            r4.<init>(r1, r3)     // Catch:{ Exception -> 0x0078, all -> 0x00bf }
            goto L_0x001a
        L_0x0078:
            r1 = move-exception
            r3 = r2
            goto L_0x0032
        L_0x007b:
            r4 = r1
            goto L_0x001a
        L_0x007d:
            if (r8 == 0) goto L_0x00a1
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            com.tencent.smtt.utils.h r2 = com.tencent.smtt.utils.h.a()     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            byte[] r5 = r3.toByteArray()     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            byte[] r2 = r2.c(r5)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            r0 = r1
        L_0x0091:
            if (r3 == 0) goto L_0x0096
            r3.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x0096:
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x003f
        L_0x009c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003f
        L_0x00a1:
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            byte[] r2 = r3.toByteArray()     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            byte[] r2 = com.tencent.smtt.utils.h.b(r2, r7)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x002f, all -> 0x00d8 }
            r0 = r1
            goto L_0x0091
        L_0x00b0:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0096
        L_0x00b5:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003a
        L_0x00ba:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x003f
        L_0x00bf:
            r0 = move-exception
            r4 = r2
        L_0x00c1:
            if (r2 == 0) goto L_0x00c6
            r2.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00c6:
            if (r4 == 0) goto L_0x00cb
            r4.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00cb:
            throw r0
        L_0x00cc:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00c6
        L_0x00d1:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00cb
        L_0x00d6:
            r0 = move-exception
            goto L_0x00c1
        L_0x00d8:
            r0 = move-exception
            r2 = r3
            goto L_0x00c1
        L_0x00db:
            r0 = move-exception
            r4 = r3
            goto L_0x00c1
        L_0x00de:
            r1 = move-exception
            r3 = r4
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.b.b(java.net.HttpURLConnection, java.lang.String, boolean):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str) {
        try {
            TbsPVConfig.releaseInstance();
            TbsPVConfig.getInstance(context).clear();
            if (!TextUtils.isEmpty(str)) {
                for (String split : str.split("\\|")) {
                    try {
                        String[] split2 = split.split("=");
                        if (split2.length == 2) {
                            a(context, split2[0], split2[1]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                TbsPVConfig.getInstance(context).commit();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void a(Context context, String str, String str2) {
        if (!"reset".equals(str) || !"true".equals(str2)) {
            TbsPVConfig.getInstance(context).putData(str, str2);
        } else {
            QbSdk.reset(context);
        }
    }
}
