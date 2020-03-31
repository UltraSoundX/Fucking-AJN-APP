package com.baidu.mobstat;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.baidu.mobstat.ar.a;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class s {
    private static String a = (VERSION.SDK_INT < 9 ? "http://openrcv.baidu.com/1010/bplus.gif" : "https://openrcv.baidu.com/1010/bplus.gif");
    private static s b;
    private Handler c;

    private s() {
        HandlerThread handlerThread = new HandlerThread("LogSender");
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
    }

    public static s a() {
        if (b == null) {
            synchronized (s.class) {
                if (b == null) {
                    b = new s();
                }
            }
        }
        return b;
    }

    public void a(final Context context, final String str) {
        al.c().a("data = " + str);
        if (str != null && !"".equals(str)) {
            this.c.post(new Runnable() {
                public void run() {
                    try {
                        s.this.a(str);
                        if (context != null) {
                            s.this.a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                        }
                    } catch (Throwable th) {
                        al.c().b(th);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        at.a("backups/system" + File.separator + "__send_log_data_" + System.currentTimeMillis(), str, false);
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        if ("mounted".equals(at.a())) {
            File file = new File(Environment.getExternalStorageDirectory(), "backups/system");
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    try {
                        Arrays.sort(listFiles, new Comparator<File>() {
                            /* renamed from: a */
                            public int compare(File file, File file2) {
                                return (int) (file2.lastModified() - file.lastModified());
                            }
                        });
                    } catch (Exception e) {
                        al.c().b((Throwable) e);
                    }
                    int i = 0;
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            String name = file2.getName();
                            if (!TextUtils.isEmpty(name) && name.startsWith("__send_log_data_")) {
                                String str = "backups/system" + File.separator + name;
                                String b2 = at.b(str);
                                if (b(context, b2)) {
                                    at.c(str);
                                    i = 0;
                                } else {
                                    a(b2, str);
                                    i++;
                                    if (i >= 5) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(String str, String str2) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception e) {
                jSONObject = null;
            }
            JSONObject a2 = h.a(jSONObject);
            if (a2 != null) {
                h.b(a2);
                at.a(str2, jSONObject.toString(), false);
            }
        }
    }

    private boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!bb.c().booleanValue()) {
            return true;
        }
        try {
            a(context, a, str);
            return true;
        } catch (Exception e) {
            al.c().c((Throwable) e);
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
    private String a(Context context, String str, String str2) throws IOException {
        boolean z;
        byte[] bytes;
        if (!str.startsWith("https:")) {
            z = true;
        } else {
            z = false;
        }
        HttpURLConnection d = at.d(context, str);
        d.setDoOutput(true);
        d.setInstanceFollowRedirects(false);
        d.setUseCaches(false);
        d.setRequestProperty("Content-Encoding", "gzip");
        try {
            JSONObject jSONObject = new JSONObject(str2).getJSONArray("payload").getJSONObject(0).getJSONObject(Config.HEADER_PART);
            d.setRequestProperty("Content-Type", "gzip");
            d.setRequestProperty("mtj_appversion", jSONObject.getString("n"));
            d.setRequestProperty("mtj_os", "Android");
            d.setRequestProperty("mtj_pn", jSONObject.getString(Config.PACKAGE_NAME));
            d.setRequestProperty("mtj_tg", "1");
            d.setRequestProperty("mtj_ii", jSONObject.getString(Config.CUID_SEC));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        d.connect();
        try {
            OutputStream outputStream = d.getOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
            gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
            gZIPOutputStream.write(new byte[]{0, 0, 0, 1});
            gZIPOutputStream.write(new byte[]{0, 0, 3, -14});
            gZIPOutputStream.write(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            gZIPOutputStream.write(new byte[]{0, 2});
            if (z) {
                gZIPOutputStream.write(new byte[]{0, 1});
            } else {
                gZIPOutputStream.write(new byte[]{0, 0});
            }
            gZIPOutputStream.write(new byte[]{72, 77, 48, 49});
            if (z) {
                byte[] a2 = a.a();
                byte[] a3 = ba.a(false, aw.a(), a2);
                gZIPOutputStream.write(a((long) a3.length, 4));
                gZIPOutputStream.write(a3);
                bytes = a.a(a2, new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, str2.getBytes("utf-8"));
                gZIPOutputStream.write(a((long) bytes.length, 2));
            } else {
                bytes = str2.getBytes("utf-8");
            }
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            outputStream.close();
            int responseCode = d.getResponseCode();
            int contentLength = d.getContentLength();
            al.c().c("code: " + responseCode + "; len: " + contentLength);
            if (responseCode == 200 && contentLength == 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(d.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        String sb2 = sb.toString();
                        d.disconnect();
                        return sb2;
                    }
                    sb.append(readLine);
                }
            } else {
                throw new IOException("Response code = " + d.getResponseCode());
            }
        } catch (Exception e2) {
            al.c().b((Throwable) e2);
            d.disconnect();
            return "";
        } catch (Throwable th) {
            d.disconnect();
            throw th;
        }
    }

    private static byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[(i - i2) - 1] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return bArr;
    }
}
