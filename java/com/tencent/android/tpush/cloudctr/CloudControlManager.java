package com.tencent.android.tpush.cloudctr;

import android.content.Context;
import android.util.Log;
import com.stub.StubApp;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.cloudctr.a.b;
import com.tencent.android.tpush.cloudctr.b.c;
import com.tencent.android.tpush.cloudctr.network.CloudControlDownloadService;
import com.tencent.android.tpush.cloudctr.network.DownloadItem;
import com.tencent.android.tpush.cloudctr.network.a;
import com.tencent.android.tpush.stat.XGPatchMonitor;
import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public final class CloudControlManager {
    private static CloudControlManager e = new CloudControlManager();
    private c a = new c("cloud control cmd thread");
    /* access modifiers changed from: private */
    public ConcurrentMap<String, ICloudControlDispatcher> b = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public a c = new a(this.d);
    /* access modifiers changed from: private */
    public com.tencent.android.tpush.cloudctr.a.a d = new com.tencent.android.tpush.cloudctr.a.a();

    /* compiled from: ProGuard */
    public interface ICloudControlDispatcher {

        /* compiled from: ProGuard */
        public enum DownloadStatus {
            Start,
            Success,
            Error
        }

        /* compiled from: ProGuard */
        public static final class ErrorResponse {
            /* access modifiers changed from: private */
            public Response a = Response.Cancel;

            /* compiled from: ProGuard */
            enum Response {
                Retry,
                Cancel
            }

            ErrorResponse() {
            }
        }

        void a(String str);

        void a(String str, String str2, String str3, DownloadStatus downloadStatus, ErrorResponse errorResponse);
    }

    private CloudControlManager() {
    }

    public static CloudControlManager a() {
        return e;
    }

    public void a(Context context, String str, String str2, ICloudControlDispatcher iCloudControlDispatcher) {
        final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        final ICloudControlDispatcher iCloudControlDispatcher2 = iCloudControlDispatcher;
        final String str3 = str2;
        final String str4 = str;
        this.a.a(new Runnable() {
            public void run() {
                try {
                    if (iCloudControlDispatcher2 != null) {
                        String str = str3;
                        if (str3 == null) {
                            str = origApplicationContext.getFilesDir().getAbsolutePath() + File.separator + ".mta" + File.separator + "cc";
                        }
                        if (CloudControlManager.this.b.containsKey(str4)) {
                            com.tencent.android.tpush.b.a.c("CloudControlManager", "business code already registered, this might be a bug");
                            return;
                        }
                        CloudControlManager.this.b.put(str4, iCloudControlDispatcher2);
                        CloudControlManager.this.d.a(str4, str);
                        CloudControlManager.this.c.a(origApplicationContext, str4, CloudControlManager.this.d.a(origApplicationContext, str4));
                        CloudControlManager.this.b(origApplicationContext, str4);
                    }
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.i("CloudControlManager", e2.toString());
                }
            }
        });
    }

    public void a(final Context context, final DownloadItem downloadItem, final boolean z) {
        this.a.a(new Runnable() {
            public void run() {
                try {
                    String businessCode = downloadItem.getBusinessCode();
                    String fileName = downloadItem.getFileName();
                    String downloadSavedDir = downloadItem.getDownloadSavedDir();
                    ICloudControlDispatcher iCloudControlDispatcher = (ICloudControlDispatcher) CloudControlManager.this.b.get(businessCode);
                    if (iCloudControlDispatcher != null) {
                        if (downloadItem.isDownloadSucceed()) {
                            String c2 = CloudControlManager.this.d.c(context, businessCode);
                            if (c2 != null) {
                                try {
                                    JSONObject jSONObject = new JSONObject(c2);
                                    jSONObject.remove(downloadItem.getMd5());
                                    CloudControlManager.this.d.a(context, businessCode, jSONObject.toString());
                                } catch (Exception e) {
                                    com.tencent.android.tpush.b.a.i("CloudControlManager", "read download info error");
                                }
                                if (!z) {
                                    b.a(context, new File(downloadSavedDir, fileName));
                                }
                                iCloudControlDispatcher.a(fileName, downloadSavedDir, downloadItem.getMd5(), DownloadStatus.Success, null);
                                Properties properties = new Properties();
                                properties.put("fileName", fileName);
                                properties.put("downloadUrl", downloadItem.getDownloadUrl() == null ? downloadItem.getDownloadUrl() : "from cache");
                                properties.put("md5", downloadItem.getMd5());
                                XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), businessCode, XGPatchMonitor.ActionVerifyFile, 0, "finish download file", properties);
                                return;
                            }
                            return;
                        }
                        ErrorResponse errorResponse = new ErrorResponse();
                        iCloudControlDispatcher.a(fileName, null, downloadItem.getMd5(), DownloadStatus.Error, errorResponse);
                        if (errorResponse.a == Response.Retry) {
                            CloudControlDownloadService.a(context, downloadItem);
                        }
                        Properties properties2 = new Properties();
                        properties2.put("fileName", fileName);
                        properties2.put("downloadUrl", downloadItem.getDownloadUrl() == null ? downloadItem.getDownloadUrl() : "from cache");
                        properties2.put("md5", downloadItem.getMd5());
                        XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), businessCode, XGPatchMonitor.ActionVerifyFile, 1, "download file error", properties2);
                    }
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.i("CloudControlManager", e2.toString());
                }
            }
        });
    }

    public void a(Context context, final String str) {
        if (context != null) {
            final Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            this.a.a(new Runnable() {
                public void run() {
                    try {
                        Log.d("CloudControlManager", "receive config: " + str);
                        JSONObject jSONObject = new JSONObject(str);
                        String string = jSONObject.getString("ccbuscode");
                        if (string == null) {
                            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), "unknown", XGPatchMonitor.ActionParseConfig, 1, "missing business code", null);
                        } else if (!jSONObject.has("ccver")) {
                            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionParseConfig, 1, "missing protocol ver", null);
                        } else if (1 != jSONObject.getInt("ccver")) {
                            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionParseConfig, 1, "protocol ver not supported", null);
                        } else {
                            ICloudControlDispatcher iCloudControlDispatcher = (ICloudControlDispatcher) CloudControlManager.this.b.get(string);
                            if (iCloudControlDispatcher == null) {
                                return;
                            }
                            if (!jSONObject.has("cccfgver")) {
                                XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionParseConfig, 1, "missing server conf ver", null);
                                return;
                            }
                            XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionParseConfig, 0, "json format ok", null);
                            long a2 = CloudControlManager.this.d.a(origApplicationContext, string);
                            long j = jSONObject.getLong("cccfgver");
                            if (j != a2) {
                                XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionLoadConfig, 0, "start load config", null);
                                if (j < a2) {
                                    com.tencent.android.tpush.b.a.i("CloudControlManager", "服务器配置版本小于本地配置版本，如果你最近切换了 appkey，则这是正常现象");
                                }
                                CloudControlManager.this.d.b(origApplicationContext, string);
                                CloudControlManager.this.d.a(origApplicationContext, string, j, true);
                                JSONObject jSONObject2 = jSONObject.getJSONObject("cccfg");
                                if (jSONObject2.has("file")) {
                                    JSONArray jSONArray = jSONObject2.getJSONArray("file");
                                    if (jSONArray != null) {
                                        XGPatchMonitor.onConfigAction(origApplicationContext, XGPushConfig.getAccessId(origApplicationContext), string, XGPatchMonitor.ActionRequestFile, 0, jSONArray.toString(), null);
                                        JSONObject jSONObject3 = new JSONObject();
                                        int length = jSONArray.length();
                                        for (int i = 0; i < length; i++) {
                                            JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                                            String string2 = jSONObject4.getString("url_type");
                                            if (string2 != null && ((string2.equalsIgnoreCase("http") || string2.equalsIgnoreCase("https")) && jSONObject4.has("url") && jSONObject4.has("name") && jSONObject4.has("md5"))) {
                                                String string3 = jSONObject4.getString("md5");
                                                if (string3 != null) {
                                                    jSONObject4.put("download_finish", false);
                                                    jSONObject3.put(string3, jSONObject4);
                                                }
                                            }
                                        }
                                        CloudControlManager.this.d.a(origApplicationContext, string, jSONObject3.toString());
                                        CloudControlManager.this.b(origApplicationContext, string);
                                    }
                                }
                                if (jSONObject2.has("conf")) {
                                    JSONObject jSONObject5 = jSONObject2.getJSONObject("conf");
                                    if (jSONObject5 != null) {
                                        iCloudControlDispatcher.a(jSONObject5.toString());
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            CloudControlManager.this.b(origApplicationContext, string);
                        }
                    } catch (JSONException e) {
                        com.tencent.android.tpush.b.a.i("CloudControlManager", "parse json conf error");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(final Context context, final String str) {
        this.a.a(new Runnable() {
            public void run() {
                try {
                    String c2 = CloudControlManager.this.d.c(context, str);
                    if (c2 != null) {
                        Log.d("CloudControlManager", "download info: " + c2);
                        try {
                            JSONObject jSONObject = new JSONObject(c2);
                            Iterator keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                String str = (String) keys.next();
                                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                                String string = jSONObject2.getString("url");
                                String string2 = jSONObject2.getString("name");
                                String d = CloudControlManager.this.d.d(context, str);
                                if (!(string == null || string2 == null || str == null || d == null)) {
                                    String a2 = b.a(context, string2, str);
                                    if (a2 != null) {
                                        com.tencent.android.tpush.cloudctr.b.a.a(new File(a2), new File(d, string2));
                                        DownloadItem downloadItem = new DownloadItem();
                                        downloadItem.setMd5(str);
                                        downloadItem.setDownloadUrl(string);
                                        downloadItem.setDownloadSavedDir(d);
                                        downloadItem.setFileName(string2);
                                        downloadItem.setBusinessCode(str);
                                        downloadItem.setDownloadSucceed(true);
                                        CloudControlManager.this.a(context, downloadItem, true);
                                        Log.d("CloudControlManager", "use file in public cache: " + string2);
                                        return;
                                    }
                                    Properties properties = new Properties();
                                    properties.put("fileName", string2);
                                    properties.put("downloadUrl", string);
                                    properties.put("md5", str);
                                    XGPatchMonitor.onConfigAction(context, XGPushConfig.getAccessId(context), str, XGPatchMonitor.ActionDownloadFile, 0, "start download file", properties);
                                    CloudControlDownloadService.a(context, string, string2, d, str, str);
                                }
                            }
                        } catch (JSONException e) {
                            com.tencent.android.tpush.b.a.i("CloudControlManager", "get download info error");
                            CloudControlManager.this.d.b(context, str);
                        }
                    }
                } catch (Exception e2) {
                    com.tencent.android.tpush.b.a.i("CloudControlManager", e2.toString());
                }
            }
        });
    }
}
