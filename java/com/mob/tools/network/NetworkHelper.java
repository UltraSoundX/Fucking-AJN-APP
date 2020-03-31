package com.mob.tools.network;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.tools.MobLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.zhy.http.okhttp.OkHttpUtils.METHOD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class NetworkHelper {
    public static int connectionTimeout;
    private static boolean followRedirects = true;
    public static int readTimout;
    protected boolean instanceFollowRedirects = followRedirects;

    public static class NetworkTimeOut {
        public int connectionTimeout;
        public int readTimout;
    }

    public static final class SimpleX509TrustManager implements X509TrustManager {
        private X509TrustManager standardTrustManager;

        public SimpleX509TrustManager(KeyStore keyStore) {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init(keyStore);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers == null || trustManagers.length == 0) {
                    throw new NoSuchAlgorithmException("no trust manager found.");
                }
                this.standardTrustManager = (X509TrustManager) trustManagers[0];
            } catch (Exception e) {
                MobLog.getInstance().d("failed to initialize the standard trust manager: " + e.getMessage(), new Object[0]);
                this.standardTrustManager = null;
            }
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (x509CertificateArr == null) {
                throw new IllegalArgumentException("there were no certificates.");
            } else if (x509CertificateArr.length == 1) {
                x509CertificateArr[0].checkValidity();
            } else if (this.standardTrustManager != null) {
                this.standardTrustManager.checkServerTrusted(x509CertificateArr, str);
            } else {
                throw new CertificateException("there were one more certificates but no trust manager found.");
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public String httpGet(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().i("httpGet: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair kVPair = (KVPair) it.next();
                connection.setRequestProperty(kVPair.name, (String) kVPair.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            connection.disconnect();
            String sb2 = sb.toString();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
            if (sb3.length() > 0) {
                sb3.append(10);
            }
            sb3.append(readLine2);
        }
        bufferedReader2.close();
        connection.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb3.toString());
        hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public void download(String str, final OutputStream outputStream, NetworkTimeOut networkTimeOut) throws Throwable {
        final byte[] bArr = new byte[1024];
        rawGet(str, (RawNetworkCallback) new RawNetworkCallback() {
            public void onResponse(InputStream inputStream) throws Throwable {
                int read = inputStream.read(bArr);
                while (read != -1) {
                    outputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr);
                }
            }
        }, networkTimeOut);
        outputStream.flush();
    }

    public String downloadCache(Context context, String str, String str2, boolean z, NetworkTimeOut networkTimeOut) throws Throwable {
        return downloadCache(context, str, str2, z, networkTimeOut, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0201 A[SYNTHETIC, Splitter:B:72:0x0201] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0231 A[Catch:{ Throwable -> 0x02bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0252 A[Catch:{ Throwable -> 0x02bd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String downloadCache(android.content.Context r21, java.lang.String r22, java.lang.String r23, boolean r24, com.mob.tools.network.NetworkHelper.NetworkTimeOut r25, com.mob.tools.network.FileDownloadListener r26) throws java.lang.Throwable {
        /*
            r20 = this;
            long r12 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "downloading: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r22
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r4.i(r5, r6)
            if (r24 == 0) goto L_0x0076
            r0 = r21
            r1 = r23
            java.lang.String r4 = com.mob.tools.utils.ResHelper.getCachePath(r0, r1)
            java.lang.String r5 = com.mob.tools.utils.Data.MD5(r22)
            java.io.File r10 = new java.io.File
            r10.<init>(r4, r5)
            if (r24 == 0) goto L_0x0076
            boolean r4 = r10.exists()
            if (r4 == 0) goto L_0x0076
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "use time: "
            java.lang.StringBuilder r5 = r5.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r12
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r4.i(r5, r6)
            if (r26 == 0) goto L_0x0071
            r5 = 100
            long r6 = r10.length()
            long r8 = r10.length()
            r4 = r26
            r4.onProgress(r5, r6, r8)
        L_0x0071:
            java.lang.String r4 = r10.getAbsolutePath()
        L_0x0075:
            return r4
        L_0x0076:
            r0 = r20
            r1 = r22
            r2 = r25
            java.net.HttpURLConnection r11 = r0.getConnection(r1, r2)
            r0 = r20
            boolean r4 = r0.instanceFollowRedirects
            r11.setInstanceFollowRedirects(r4)
            r11.connect()
            int r5 = r11.getResponseCode()
            r4 = 200(0xc8, float:2.8E-43)
            if (r5 != r4) goto L_0x02c8
            r5 = 0
            java.util.Map r6 = r11.getHeaderFields()
            if (r6 == 0) goto L_0x00f4
            java.lang.String r4 = "Content-Disposition"
            java.lang.Object r4 = r6.get(r4)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x00f4
            int r7 = r4.size()
            if (r7 <= 0) goto L_0x00f4
            r7 = 0
            java.lang.Object r4 = r4.get(r7)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r7 = ";"
            java.lang.String[] r7 = r4.split(r7)
            int r8 = r7.length
            r4 = 0
            r19 = r4
            r4 = r5
            r5 = r19
        L_0x00bd:
            if (r5 >= r8) goto L_0x00f5
            r9 = r7[r5]
            java.lang.String r10 = r9.trim()
            java.lang.String r14 = "filename"
            boolean r10 = r10.startsWith(r14)
            if (r10 == 0) goto L_0x00f1
            java.lang.String r4 = "="
            java.lang.String[] r4 = r9.split(r4)
            r9 = 1
            r4 = r4[r9]
            java.lang.String r9 = "\""
            boolean r9 = r4.startsWith(r9)
            if (r9 == 0) goto L_0x00f1
            java.lang.String r9 = "\""
            boolean r9 = r4.endsWith(r9)
            if (r9 == 0) goto L_0x00f1
            r9 = 1
            int r10 = r4.length()
            int r10 = r10 + -1
            java.lang.String r4 = r4.substring(r9, r10)
        L_0x00f1:
            int r5 = r5 + 1
            goto L_0x00bd
        L_0x00f4:
            r4 = r5
        L_0x00f5:
            if (r4 != 0) goto L_0x014b
            java.lang.String r5 = com.mob.tools.utils.Data.MD5(r22)
            if (r6 == 0) goto L_0x0326
            java.lang.String r4 = "Content-Type"
            java.lang.Object r4 = r6.get(r4)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L_0x0326
            int r6 = r4.size()
            if (r6 <= 0) goto L_0x0326
            r6 = 0
            java.lang.Object r4 = r4.get(r6)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L_0x019c
            java.lang.String r4 = ""
        L_0x0118:
            java.lang.String r6 = "image/"
            boolean r6 = r4.startsWith(r6)
            if (r6 == 0) goto L_0x01a2
            java.lang.String r6 = "image/"
            int r6 = r6.length()
            java.lang.String r4 = r4.substring(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r6 = "."
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "jpeg"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0143
            java.lang.String r4 = "jpg"
        L_0x0143:
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r4 = r4.toString()
        L_0x014b:
            r0 = r21
            r1 = r23
            java.lang.String r5 = com.mob.tools.utils.ResHelper.getCachePath(r0, r1)
            java.io.File r14 = new java.io.File
            r14.<init>(r5, r4)
            if (r24 == 0) goto L_0x01e5
            boolean r4 = r14.exists()
            if (r4 == 0) goto L_0x01e5
            r11.disconnect()
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "use time: "
            java.lang.StringBuilder r5 = r5.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r12
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r4.i(r5, r6)
            if (r26 == 0) goto L_0x0196
            r5 = 100
            long r6 = r14.length()
            long r8 = r14.length()
            r4 = r26
            r4.onProgress(r5, r6, r8)
        L_0x0196:
            java.lang.String r4 = r14.getAbsolutePath()
            goto L_0x0075
        L_0x019c:
            java.lang.String r4 = r4.trim()
            goto L_0x0118
        L_0x01a2:
            r4 = 47
            r0 = r22
            int r6 = r0.lastIndexOf(r4)
            r4 = 0
            if (r6 <= 0) goto L_0x01b5
            int r4 = r6 + 1
            r0 = r22
            java.lang.String r4 = r0.substring(r4)
        L_0x01b5:
            if (r4 == 0) goto L_0x0326
            int r6 = r4.length()
            if (r6 <= 0) goto L_0x0326
            r6 = 46
            int r6 = r4.lastIndexOf(r6)
            if (r6 <= 0) goto L_0x0326
            int r7 = r4.length()
            int r7 = r7 - r6
            r8 = 10
            if (r7 >= r8) goto L_0x0326
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r4 = r4.substring(r6)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r4 = r4.toString()
            goto L_0x014b
        L_0x01e5:
            java.io.File r4 = r14.getParentFile()
            boolean r4 = r4.exists()
            if (r4 != 0) goto L_0x01f6
            java.io.File r4 = r14.getParentFile()
            r4.mkdirs()
        L_0x01f6:
            boolean r4 = r14.exists()
            if (r4 == 0) goto L_0x01ff
            r14.delete()
        L_0x01ff:
            if (r26 == 0) goto L_0x0213
            boolean r4 = r26.isCanceled()     // Catch:{ Throwable -> 0x02bd }
            if (r4 == 0) goto L_0x0213
            boolean r4 = r14.exists()     // Catch:{ Throwable -> 0x02bd }
            if (r4 == 0) goto L_0x0210
            r14.delete()     // Catch:{ Throwable -> 0x02bd }
        L_0x0210:
            r4 = 0
            goto L_0x0075
        L_0x0213:
            java.io.InputStream r15 = r11.getInputStream()     // Catch:{ Throwable -> 0x02bd }
            int r16 = r11.getContentLength()     // Catch:{ Throwable -> 0x02bd }
            java.io.FileOutputStream r17 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x02bd }
            r0 = r17
            r0.<init>(r14)     // Catch:{ Throwable -> 0x02bd }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r4]     // Catch:{ Throwable -> 0x02bd }
            r18 = r0
            r5 = 0
            r0 = r18
            int r4 = r15.read(r0)     // Catch:{ Throwable -> 0x02bd }
        L_0x022f:
            if (r4 <= 0) goto L_0x0250
            r6 = 0
            r0 = r17
            r1 = r18
            r0.write(r1, r6, r4)     // Catch:{ Throwable -> 0x02bd }
            int r10 = r5 + r4
            if (r26 == 0) goto L_0x0272
            if (r16 > 0) goto L_0x026d
            r5 = 100
        L_0x0241:
            long r6 = (long) r10     // Catch:{ Throwable -> 0x02bd }
            r0 = r16
            long r8 = (long) r0     // Catch:{ Throwable -> 0x02bd }
            r4 = r26
            r4.onProgress(r5, r6, r8)     // Catch:{ Throwable -> 0x02bd }
            boolean r4 = r26.isCanceled()     // Catch:{ Throwable -> 0x02bd }
            if (r4 == 0) goto L_0x0272
        L_0x0250:
            if (r26 == 0) goto L_0x0289
            boolean r4 = r26.isCanceled()     // Catch:{ Throwable -> 0x02bd }
            if (r4 == 0) goto L_0x027a
            boolean r4 = r14.exists()     // Catch:{ Throwable -> 0x02bd }
            if (r4 == 0) goto L_0x0261
            r14.delete()     // Catch:{ Throwable -> 0x02bd }
        L_0x0261:
            r17.flush()     // Catch:{ Throwable -> 0x02bd }
            r15.close()     // Catch:{ Throwable -> 0x02bd }
            r17.close()     // Catch:{ Throwable -> 0x02bd }
            r4 = 0
            goto L_0x0075
        L_0x026d:
            int r4 = r10 * 100
            int r5 = r4 / r16
            goto L_0x0241
        L_0x0272:
            r0 = r18
            int r4 = r15.read(r0)     // Catch:{ Throwable -> 0x02bd }
            r5 = r10
            goto L_0x022f
        L_0x027a:
            r5 = 100
            long r6 = r14.length()     // Catch:{ Throwable -> 0x02bd }
            long r8 = r14.length()     // Catch:{ Throwable -> 0x02bd }
            r4 = r26
            r4.onProgress(r5, r6, r8)     // Catch:{ Throwable -> 0x02bd }
        L_0x0289:
            r17.flush()     // Catch:{ Throwable -> 0x02bd }
            r15.close()     // Catch:{ Throwable -> 0x02bd }
            r17.close()     // Catch:{ Throwable -> 0x02bd }
            r11.disconnect()
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "use time: "
            java.lang.StringBuilder r5 = r5.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r12
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r4.i(r5, r6)
            java.lang.String r4 = r14.getAbsolutePath()
            goto L_0x0075
        L_0x02bd:
            r4 = move-exception
            boolean r5 = r14.exists()
            if (r5 == 0) goto L_0x02c7
            r14.delete()
        L_0x02c7:
            throw r4
        L_0x02c8:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.io.InputStreamReader r4 = new java.io.InputStreamReader
            java.io.InputStream r7 = r11.getErrorStream()
            java.lang.String r8 = "utf-8"
            java.nio.charset.Charset r8 = java.nio.charset.Charset.forName(r8)
            r4.<init>(r7, r8)
            java.io.BufferedReader r7 = new java.io.BufferedReader
            r7.<init>(r4)
            java.lang.String r4 = r7.readLine()
        L_0x02e5:
            if (r4 == 0) goto L_0x02fa
            int r8 = r6.length()
            if (r8 <= 0) goto L_0x02f2
            r8 = 10
            r6.append(r8)
        L_0x02f2:
            r6.append(r4)
            java.lang.String r4 = r7.readLine()
            goto L_0x02e5
        L_0x02fa:
            r7.close()
            r11.disconnect()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r7 = "error"
            java.lang.String r6 = r6.toString()
            r4.put(r7, r6)
            java.lang.String r6 = "status"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4.put(r6, r5)
            java.lang.Throwable r5 = new java.lang.Throwable
            com.mob.tools.utils.Hashon r6 = new com.mob.tools.utils.Hashon
            r6.<init>()
            java.lang.String r4 = r6.fromHashMap(r4)
            r5.<init>(r4)
            throw r5
        L_0x0326:
            r4 = r5
            goto L_0x014b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.downloadCache(android.content.Context, java.lang.String, java.lang.String, boolean, com.mob.tools.network.NetworkHelper$NetworkTimeOut, com.mob.tools.network.FileDownloadListener):java.lang.String");
    }

    public void rawGet(String str, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, null, rawNetworkCallback, networkTimeOut);
    }

    public void rawGet(String str, ArrayList<KVPair<String>> arrayList, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().i("rawGet: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair kVPair = (KVPair) it.next();
                connection.setRequestProperty(kVPair.name, (String) kVPair.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            if (rawNetworkCallback != null) {
                rawNetworkCallback.onResponse(connection.getInputStream());
            }
            connection.disconnect();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        connection.disconnect();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb.toString());
        hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public void rawGet(String str, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, null, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008f, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0092, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawGet(java.lang.String r8, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r9, com.mob.tools.network.HttpResponseCallback r10, com.mob.tools.network.NetworkHelper.NetworkTimeOut r11) throws java.lang.Throwable {
        /*
            r7 = this;
            r6 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "rawGet: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r6]
            r0.i(r1, r4)
            java.net.HttpURLConnection r1 = r7.getConnection(r8, r11)
            if (r9 == 0) goto L_0x0041
            java.util.Iterator r4 = r9.iterator()
        L_0x002b:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0041
            java.lang.Object r0 = r4.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r5 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r1.setRequestProperty(r5, r0)
            goto L_0x002b
        L_0x0041:
            boolean r0 = r7.instanceFollowRedirects
            r1.setInstanceFollowRedirects(r0)
            r1.connect()
            int r0 = r1.getResponseCode()
            r4 = 301(0x12d, float:4.22E-43)
            if (r0 != r4) goto L_0x007e
            java.lang.String r0 = "Location"
            java.lang.String r0 = r1.getHeaderField(r0)
            r1 = 0
            r7.rawGet(r0, r1, r10, r11)
        L_0x005b:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r6]
            r0.i(r1, r2)
            return
        L_0x007e:
            if (r10 == 0) goto L_0x0093
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x008c }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x008c }
            r10.onResponse(r0)     // Catch:{ Throwable -> 0x008c }
            r1.disconnect()
            goto L_0x005b
        L_0x008c:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x008e }
        L_0x008e:
            r0 = move-exception
            r1.disconnect()
            throw r0
        L_0x0093:
            r1.disconnect()
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawGet(java.lang.String, java.util.ArrayList, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public String jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        jsonPost(str, arrayList, arrayList2, networkTimeOut, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode == 201) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("res", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        });
        if (hashMap.containsKey("res")) {
            return (String) hashMap.get("res");
        }
        return null;
    }

    public void jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, HttpResponseCallback httpResponseCallback) throws Throwable {
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            hashMap.put(kVPair.name, kVPair.value);
        }
        jsonPost(str, hashMap, arrayList2, networkTimeOut, httpResponseCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00bf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c0, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c3, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void jsonPost(java.lang.String r9, java.util.HashMap<java.lang.String, java.lang.Object> r10, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r11, com.mob.tools.network.NetworkHelper.NetworkTimeOut r12, com.mob.tools.network.HttpResponseCallback r13) throws java.lang.Throwable {
        /*
            r8 = this;
            r7 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "jsonPost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r0.i(r1, r4)
            java.net.HttpURLConnection r1 = r8.getConnection(r9, r12)
            r0 = 1
            r1.setDoOutput(r0)
            r1.setChunkedStreamingMode(r7)
            java.lang.String r0 = "content-type"
            java.lang.String r4 = "application/json"
            r1.setRequestProperty(r0, r4)
            if (r11 == 0) goto L_0x004f
            java.util.Iterator r4 = r11.iterator()
        L_0x0039:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x004f
            java.lang.Object r0 = r4.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r5 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r1.setRequestProperty(r5, r0)
            goto L_0x0039
        L_0x004f:
            com.mob.tools.network.StringPart r0 = new com.mob.tools.network.StringPart
            r0.<init>()
            if (r10 == 0) goto L_0x0062
            com.mob.tools.utils.Hashon r4 = new com.mob.tools.utils.Hashon
            r4.<init>()
            java.lang.String r4 = r4.fromHashMap(r10)
            r0.append(r4)
        L_0x0062:
            boolean r4 = r8.instanceFollowRedirects
            r1.setInstanceFollowRedirects(r4)
            r1.connect()
            java.io.OutputStream r4 = r1.getOutputStream()
            java.io.InputStream r5 = r0.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r5.read(r6)
        L_0x007a:
            if (r0 <= 0) goto L_0x0084
            r4.write(r6, r7, r0)
            int r0 = r5.read(r6)
            goto L_0x007a
        L_0x0084:
            r4.flush()
            r5.close()
            r4.close()
            if (r13 == 0) goto L_0x00c4
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00bd }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00bd }
            r13.onResponse(r0)     // Catch:{ Throwable -> 0x00bd }
            r1.disconnect()
        L_0x009a:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r0.i(r1, r2)
            return
        L_0x00bd:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r0 = move-exception
            r1.disconnect()
            throw r0
        L_0x00c4:
            r1.disconnect()
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.jsonPost(java.lang.String, java.util.HashMap, java.util.ArrayList, com.mob.tools.network.NetworkHelper$NetworkTimeOut, com.mob.tools.network.HttpResponseCallback):void");
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPost(str, arrayList, kVPair, arrayList2, 0, networkTimeOut);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        ArrayList arrayList3 = new ArrayList();
        if (!(kVPair == null || kVPair.value == null || !new File((String) kVPair.value).exists())) {
            arrayList3.add(kVPair);
        }
        return httpPostFiles(str, arrayList, arrayList3, arrayList2, i, networkTimeOut);
    }

    public String httpPostFiles(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPostFiles(str, arrayList, arrayList2, arrayList3, 0, networkTimeOut);
    }

    public String httpPostFiles(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, arrayList2, arrayList3, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public String httpPostFilesChecked(String str, ArrayList<KVPair<String>> arrayList, byte[] bArr, ArrayList<KVPair<String>> arrayList2, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, bArr, arrayList2, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, i, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    hashMap.put("resp", sb.toString());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append(10);
                    }
                    sb2.append(readLine2);
                }
                bufferedReader2.close();
                HashMap hashMap = new HashMap();
                hashMap.put("error", sb2.toString());
                hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
                throw new Throwable(new Hashon().fromHashMap(hashMap));
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    public void httpPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        httpPost(str, arrayList, arrayList2, arrayList3, 0, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cc, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cf, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r11, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r12, int r13, com.mob.tools.network.HttpResponseCallback r14, com.mob.tools.network.NetworkHelper.NetworkTimeOut r15) throws java.lang.Throwable {
        /*
            r8 = this;
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "httpPost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r0.i(r1, r4)
            java.net.HttpURLConnection r4 = r8.getConnection(r9, r15)
            r0 = 1
            r4.setDoOutput(r0)
            java.lang.String r0 = "Connection"
            java.lang.String r1 = "Keep-Alive"
            r4.setRequestProperty(r0, r1)
            if (r11 == 0) goto L_0x005e
            int r0 = r11.size()
            if (r0 <= 0) goto L_0x005e
            com.mob.tools.network.HTTPPart r0 = r8.getFilePostHTTPPart(r4, r9, r10, r11)
            if (r13 < 0) goto L_0x006a
            r4.setChunkedStreamingMode(r13)
            r1 = r0
        L_0x0042:
            if (r12 == 0) goto L_0x006c
            java.util.Iterator r5 = r12.iterator()
        L_0x0048:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x006c
            java.lang.Object r0 = r5.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r6 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r4.setRequestProperty(r6, r0)
            goto L_0x0048
        L_0x005e:
            com.mob.tools.network.HTTPPart r0 = r8.getTextPostHTTPPart(r4, r9, r10)
            long r6 = r0.length()
            int r1 = (int) r6
            r4.setFixedLengthStreamingMode(r1)
        L_0x006a:
            r1 = r0
            goto L_0x0042
        L_0x006c:
            boolean r0 = r8.instanceFollowRedirects
            r4.setInstanceFollowRedirects(r0)
            r4.connect()
            java.io.OutputStream r5 = r4.getOutputStream()
            java.io.InputStream r1 = r1.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r1.read(r6)
        L_0x0084:
            if (r0 <= 0) goto L_0x008f
            r7 = 0
            r5.write(r6, r7, r0)
            int r0 = r1.read(r6)
            goto L_0x0084
        L_0x008f:
            r5.flush()
            r1.close()
            r5.close()
            if (r14 == 0) goto L_0x00d0
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00c9 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00c9 }
            r14.onResponse(r0)     // Catch:{ Throwable -> 0x00c9 }
            r4.disconnect()
        L_0x00a5:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r0.i(r1, r2)
            return
        L_0x00c9:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00cb }
        L_0x00cb:
            r0 = move-exception
            r4.disconnect()
            throw r0
        L_0x00d0:
            r4.disconnect()
            goto L_0x00a5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c9, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00cc, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, byte[] r11, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r12, int r13, com.mob.tools.network.HttpResponseCallback r14, com.mob.tools.network.NetworkHelper.NetworkTimeOut r15) throws java.lang.Throwable {
        /*
            r8 = this;
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "httpPost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r0.i(r1, r4)
            java.net.HttpURLConnection r4 = r8.getConnection(r9, r15)
            r0 = 1
            r4.setDoOutput(r0)
            java.lang.String r0 = "Connection"
            java.lang.String r1 = "Keep-Alive"
            r4.setRequestProperty(r0, r1)
            if (r11 == 0) goto L_0x005b
            int r0 = r11.length
            if (r0 <= 0) goto L_0x005b
            com.mob.tools.network.HTTPPart r0 = r8.getDataPostHttpPart(r4, r9, r11)
            if (r13 < 0) goto L_0x0067
            r4.setChunkedStreamingMode(r13)
            r1 = r0
        L_0x003f:
            if (r12 == 0) goto L_0x0069
            java.util.Iterator r5 = r12.iterator()
        L_0x0045:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0069
            java.lang.Object r0 = r5.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r6 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r4.setRequestProperty(r6, r0)
            goto L_0x0045
        L_0x005b:
            com.mob.tools.network.HTTPPart r0 = r8.getTextPostHTTPPart(r4, r9, r10)
            long r6 = r0.length()
            int r1 = (int) r6
            r4.setFixedLengthStreamingMode(r1)
        L_0x0067:
            r1 = r0
            goto L_0x003f
        L_0x0069:
            boolean r0 = r8.instanceFollowRedirects
            r4.setInstanceFollowRedirects(r0)
            r4.connect()
            java.io.OutputStream r5 = r4.getOutputStream()
            java.io.InputStream r1 = r1.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r1.read(r6)
        L_0x0081:
            if (r0 <= 0) goto L_0x008c
            r7 = 0
            r5.write(r6, r7, r0)
            int r0 = r1.read(r6)
            goto L_0x0081
        L_0x008c:
            r5.flush()
            r1.close()
            r5.close()
            if (r14 == 0) goto L_0x00cd
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00c6 }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00c6 }
            r14.onResponse(r0)     // Catch:{ Throwable -> 0x00c6 }
            r4.disconnect()
        L_0x00a2:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r0.i(r1, r2)
            return
        L_0x00c6:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r0 = move-exception
            r4.disconnect()
            throw r0
        L_0x00cd:
            r4.disconnect()
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, byte[], java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b3, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b6, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPost(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, int r11, com.mob.tools.network.HttpResponseCallback r12, com.mob.tools.network.NetworkHelper.NetworkTimeOut r13) throws java.lang.Throwable {
        /*
            r8 = this;
            r7 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "httpPost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r0.i(r1, r4)
            java.net.HttpURLConnection r1 = r8.getConnection(r9, r13)
            r0 = 1
            r1.setDoOutput(r0)
            java.lang.String r0 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r1.setRequestProperty(r0, r4)
            if (r10 == 0) goto L_0x004c
            java.util.Iterator r4 = r10.iterator()
        L_0x0036:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x004c
            java.lang.Object r0 = r4.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r5 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r1.setRequestProperty(r5, r0)
            goto L_0x0036
        L_0x004c:
            com.mob.tools.network.StringPart r0 = new com.mob.tools.network.StringPart
            r0.<init>()
            r4 = 0
            r0.append(r4)
            boolean r4 = r8.instanceFollowRedirects
            r1.setInstanceFollowRedirects(r4)
            r1.connect()
            java.io.OutputStream r4 = r1.getOutputStream()
            java.io.InputStream r5 = r0.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r5.read(r6)
        L_0x006d:
            if (r0 <= 0) goto L_0x0077
            r4.write(r6, r7, r0)
            int r0 = r5.read(r6)
            goto L_0x006d
        L_0x0077:
            r4.flush()
            r5.close()
            r4.close()
            if (r12 == 0) goto L_0x00b7
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00b0 }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00b0 }
            r12.onResponse(r0)     // Catch:{ Throwable -> 0x00b0 }
            r1.disconnect()
        L_0x008d:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r0.i(r1, r2)
            return
        L_0x00b0:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            r1.disconnect()
            throw r0
        L_0x00b7:
            r1.disconnect()
            goto L_0x008d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPost(java.lang.String, java.util.ArrayList, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    private HTTPPart getDataPostHttpPart(HttpURLConnection httpURLConnection, String str, byte[] bArr) throws Throwable {
        ByteArrayPart byteArrayPart = new ByteArrayPart();
        byteArrayPart.append(bArr);
        return byteArrayPart;
    }

    private HTTPPart getFilePostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + uuid);
        MultiPart multiPart = new MultiPart();
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair kVPair = (KVPair) it.next();
                stringPart.append("--").append(uuid).append("\r\n");
                stringPart.append("Content-Disposition: form-data; name=\"").append(kVPair.name).append("\"\r\n\r\n");
                stringPart.append((String) kVPair.value).append("\r\n");
            }
        }
        multiPart.append(stringPart);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            KVPair kVPair2 = (KVPair) it2.next();
            StringPart stringPart2 = new StringPart();
            File file = new File((String) kVPair2.value);
            stringPart2.append("--").append(uuid).append("\r\n");
            stringPart2.append("Content-Disposition: form-data; name=\"").append(kVPair2.name).append("\"; filename=\"").append(file.getName()).append("\"\r\n");
            String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor((String) kVPair2.value);
            if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                if (((String) kVPair2.value).toLowerCase().endsWith("jpg") || ((String) kVPair2.value).toLowerCase().endsWith("jpeg")) {
                    contentTypeFor = "image/jpeg";
                } else if (((String) kVPair2.value).toLowerCase().endsWith("png")) {
                    contentTypeFor = "image/png";
                } else if (((String) kVPair2.value).toLowerCase().endsWith("gif")) {
                    contentTypeFor = "image/gif";
                } else {
                    FileInputStream fileInputStream = new FileInputStream((String) kVPair2.value);
                    contentTypeFor = URLConnection.guessContentTypeFromStream(fileInputStream);
                    fileInputStream.close();
                    if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                        contentTypeFor = "application/octet-stream";
                    }
                }
            }
            stringPart2.append("Content-Type: ").append(contentTypeFor).append("\r\n\r\n");
            multiPart.append(stringPart2);
            FilePart filePart = new FilePart();
            filePart.setFile((String) kVPair2.value);
            multiPart.append(filePart);
            StringPart stringPart3 = new StringPart();
            stringPart3.append("\r\n");
            multiPart.append(stringPart3);
        }
        StringPart stringPart4 = new StringPart();
        stringPart4.append("--").append(uuid).append("--\r\n");
        multiPart.append(stringPart4);
        return multiPart;
    }

    private HTTPPart getTextPostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            stringPart.append(kvPairsToUrl(arrayList));
        }
        return stringPart;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b2, code lost:
        if (r4 != null) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b7, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ba, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawPost(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, com.mob.tools.network.HTTPPart r11, com.mob.tools.network.RawNetworkCallback r12, com.mob.tools.network.NetworkHelper.NetworkTimeOut r13) throws java.lang.Throwable {
        /*
            r8 = this;
            r7 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "rawpost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r0.i(r1, r4)
            java.net.HttpURLConnection r1 = r8.getConnection(r9, r13)
            r0 = 1
            r1.setDoOutput(r0)
            r1.setChunkedStreamingMode(r7)
            if (r10 == 0) goto L_0x0048
            java.util.Iterator r4 = r10.iterator()
        L_0x0032:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0048
            java.lang.Object r0 = r4.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r5 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r1.setRequestProperty(r5, r0)
            goto L_0x0032
        L_0x0048:
            boolean r0 = r8.instanceFollowRedirects
            r1.setInstanceFollowRedirects(r0)
            r1.connect()
            java.io.OutputStream r4 = r1.getOutputStream()
            java.io.InputStream r5 = r11.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r5.read(r6)
        L_0x0060:
            if (r0 <= 0) goto L_0x006a
            r4.write(r6, r7, r0)
            int r0 = r5.read(r6)
            goto L_0x0060
        L_0x006a:
            r4.flush()
            r5.close()
            r4.close()
            int r4 = r1.getResponseCode()
            r0 = 200(0xc8, float:2.8E-43)
            if (r4 != r0) goto L_0x00bf
            if (r12 == 0) goto L_0x00bb
            java.io.InputStream r4 = r1.getInputStream()
            r12.onResponse(r4)     // Catch:{ Throwable -> 0x00af }
            if (r4 == 0) goto L_0x0089
            r4.close()     // Catch:{ Throwable -> 0x011d }
        L_0x0089:
            r1.disconnect()
        L_0x008c:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r0.i(r1, r2)
            return
        L_0x00af:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00b1 }
        L_0x00b1:
            r0 = move-exception
            if (r4 == 0) goto L_0x00b7
            r4.close()     // Catch:{ Throwable -> 0x0120 }
        L_0x00b7:
            r1.disconnect()
            throw r0
        L_0x00bb:
            r1.disconnect()
            goto L_0x008c
        L_0x00bf:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            java.io.InputStream r3 = r1.getErrorStream()
            java.lang.String r5 = "utf-8"
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)
            r0.<init>(r3, r5)
            java.io.BufferedReader r3 = new java.io.BufferedReader
            r3.<init>(r0)
            java.lang.String r0 = r3.readLine()
        L_0x00dc:
            if (r0 == 0) goto L_0x00f1
            int r5 = r2.length()
            if (r5 <= 0) goto L_0x00e9
            r5 = 10
            r2.append(r5)
        L_0x00e9:
            r2.append(r0)
            java.lang.String r0 = r3.readLine()
            goto L_0x00dc
        L_0x00f1:
            r3.close()
            r1.disconnect()
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "error"
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            java.lang.String r1 = "status"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r0.put(r1, r2)
            java.lang.Throwable r1 = new java.lang.Throwable
            com.mob.tools.utils.Hashon r2 = new com.mob.tools.utils.Hashon
            r2.<init>()
            java.lang.String r0 = r2.fromHashMap(r0)
            r1.<init>(r0)
            throw r1
        L_0x011d:
            r0 = move-exception
            goto L_0x0089
        L_0x0120:
            r2 = move-exception
            goto L_0x00b7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawPost(java.lang.String, java.util.ArrayList, com.mob.tools.network.HTTPPart, com.mob.tools.network.RawNetworkCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawPost(str, arrayList, hTTPPart, 0, httpResponseCallback, networkTimeOut);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a8, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ab, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rawPost(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, com.mob.tools.network.HTTPPart r11, int r12, com.mob.tools.network.HttpResponseCallback r13, com.mob.tools.network.NetworkHelper.NetworkTimeOut r14) throws java.lang.Throwable {
        /*
            r8 = this;
            r7 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "rawpost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r0.i(r1, r4)
            java.net.HttpURLConnection r1 = r8.getConnection(r9, r14)
            r0 = 1
            r1.setDoOutput(r0)
            if (r12 < 0) goto L_0x002e
            r1.setChunkedStreamingMode(r7)
        L_0x002e:
            if (r10 == 0) goto L_0x004a
            java.util.Iterator r4 = r10.iterator()
        L_0x0034:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x004a
            java.lang.Object r0 = r4.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r5 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r1.setRequestProperty(r5, r0)
            goto L_0x0034
        L_0x004a:
            boolean r0 = r8.instanceFollowRedirects
            r1.setInstanceFollowRedirects(r0)
            r1.connect()
            java.io.OutputStream r4 = r1.getOutputStream()
            java.io.InputStream r5 = r11.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r5.read(r6)
        L_0x0062:
            if (r0 <= 0) goto L_0x006c
            r4.write(r6, r7, r0)
            int r0 = r5.read(r6)
            goto L_0x0062
        L_0x006c:
            r4.flush()
            r5.close()
            r4.close()
            if (r13 == 0) goto L_0x00ac
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00a5 }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00a5 }
            r13.onResponse(r0)     // Catch:{ Throwable -> 0x00a5 }
            r1.disconnect()
        L_0x0082:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r0.i(r1, r2)
            return
        L_0x00a5:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00a7 }
        L_0x00a7:
            r0 = move-exception
            r1.disconnect()
            throw r0
        L_0x00ac:
            r1.disconnect()
            goto L_0x0082
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.rawPost(java.lang.String, java.util.ArrayList, com.mob.tools.network.HTTPPart, int, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ce, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d1, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getHttpPostResponse(java.lang.String r9, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r10, com.mob.tools.network.KVPair<java.lang.String> r11, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r12, com.mob.tools.network.HttpResponseCallback r13, com.mob.tools.network.NetworkHelper.NetworkTimeOut r14) throws java.lang.Throwable {
        /*
            r8 = this;
            r7 = 0
            long r2 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "httpPost: "
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.StringBuilder r1 = r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r0.i(r1, r4)
            java.net.HttpURLConnection r4 = r8.getConnection(r9, r14)
            r0 = 1
            r4.setDoOutput(r0)
            r4.setChunkedStreamingMode(r7)
            if (r11 == 0) goto L_0x006a
            T r0 = r11.value
            if (r0 == 0) goto L_0x006a
            java.io.File r1 = new java.io.File
            T r0 = r11.value
            java.lang.String r0 = (java.lang.String) r0
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 == 0) goto L_0x006a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r0.add(r11)
            com.mob.tools.network.HTTPPart r0 = r8.getFilePostHTTPPart(r4, r9, r10, r0)
            r1 = r0
        L_0x004e:
            if (r12 == 0) goto L_0x0070
            java.util.Iterator r5 = r12.iterator()
        L_0x0054:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0070
            java.lang.Object r0 = r5.next()
            com.mob.tools.network.KVPair r0 = (com.mob.tools.network.KVPair) r0
            java.lang.String r6 = r0.name
            T r0 = r0.value
            java.lang.String r0 = (java.lang.String) r0
            r4.setRequestProperty(r6, r0)
            goto L_0x0054
        L_0x006a:
            com.mob.tools.network.HTTPPart r0 = r8.getTextPostHTTPPart(r4, r9, r10)
            r1 = r0
            goto L_0x004e
        L_0x0070:
            boolean r0 = r8.instanceFollowRedirects
            r4.setInstanceFollowRedirects(r0)
            r4.connect()
            java.io.OutputStream r5 = r4.getOutputStream()
            java.io.InputStream r1 = r1.toInputStream()
            r0 = 65536(0x10000, float:9.18355E-41)
            byte[] r6 = new byte[r0]
            int r0 = r1.read(r6)
        L_0x0088:
            if (r0 <= 0) goto L_0x0092
            r5.write(r6, r7, r0)
            int r0 = r1.read(r6)
            goto L_0x0088
        L_0x0092:
            r5.flush()
            r1.close()
            r5.close()
            if (r13 == 0) goto L_0x00d2
            com.mob.tools.network.HttpConnectionImpl23 r0 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00cb }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00cb }
            r13.onResponse(r0)     // Catch:{ Throwable -> 0x00cb }
            r4.disconnect()
        L_0x00a8:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "use time: "
            java.lang.StringBuilder r1 = r1.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r2 = r4 - r2
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r0.i(r1, r2)
            return
        L_0x00cb:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00cd }
        L_0x00cd:
            r0 = move-exception
            r4.disconnect()
            throw r0
        L_0x00d2:
            r4.disconnect()
            goto L_0x00a8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.getHttpPostResponse(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, java.util.ArrayList, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPut(str, arrayList, kVPair, arrayList2, networkTimeOut, null);
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, OnReadListener onReadListener) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().i("httpPut: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestMethod(METHOD.PUT);
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair kVPair2 = (KVPair) it.next();
                connection.setRequestProperty(kVPair2.name, (String) kVPair2.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        OutputStream outputStream = connection.getOutputStream();
        FilePart filePart = new FilePart();
        if (onReadListener != null) {
            filePart.setOnReadListener(onReadListener);
        }
        filePart.setFile((String) kVPair.value);
        InputStream inputStream = filePart.toInputStream();
        byte[] bArr = new byte[65536];
        for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
            outputStream.write(bArr, 0, read);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200 || responseCode == 201) {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            connection.disconnect();
            String sb2 = sb.toString();
            MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8")));
        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
            if (sb3.length() > 0) {
                sb3.append(10);
            }
            sb3.append(readLine2);
        }
        bufferedReader2.close();
        HashMap hashMap = new HashMap();
        hashMap.put("error", sb3.toString());
        hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(responseCode));
        throw new Throwable(new Hashon().fromHashMap(hashMap));
    }

    public ArrayList<KVPair<String[]>> httpHead(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().i("httpHead: " + str, new Object[0]);
        if (arrayList != null) {
            String kvPairsToUrl = kvPairsToUrl(arrayList);
            if (kvPairsToUrl.length() > 0) {
                str = str + "?" + kvPairsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setRequestMethod(METHOD.HEAD);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair kVPair2 = (KVPair) it.next();
                connection.setRequestProperty(kVPair2.name, (String) kVPair2.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        Map headerFields = connection.getHeaderFields();
        ArrayList<KVPair<String[]>> arrayList3 = new ArrayList<>();
        if (headerFields != null) {
            for (Entry entry : headerFields.entrySet()) {
                List list = (List) entry.getValue();
                if (list == null) {
                    arrayList3.add(new KVPair((String) entry.getKey(), new String[0]));
                } else {
                    String[] strArr = new String[list.size()];
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= strArr.length) {
                            break;
                        }
                        strArr[i2] = (String) list.get(i2);
                        i = i2 + 1;
                    }
                    arrayList3.add(new KVPair((String) entry.getKey(), strArr));
                }
            }
        }
        connection.disconnect();
        MobLog.getInstance().i("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        return arrayList3;
    }

    public void httpPatch(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, long j, ArrayList<KVPair<String>> arrayList2, OnReadListener onReadListener, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        if (VERSION.SDK_INT >= 23) {
            httpPatchImpl23(str, arrayList, kVPair, j, arrayList2, onReadListener, httpResponseCallback, networkTimeOut);
        } else {
            httpPatchImpl(str, arrayList, kVPair, j, arrayList2, onReadListener, httpResponseCallback, networkTimeOut);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x026d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x026e, code lost:
        com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, "shutdown", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0276, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void httpPatchImpl(java.lang.String r15, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r16, com.mob.tools.network.KVPair<java.lang.String> r17, long r18, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r20, com.mob.tools.network.OnReadListener r21, com.mob.tools.network.HttpResponseCallback r22, com.mob.tools.network.NetworkHelper.NetworkTimeOut r23) throws java.lang.Throwable {
        /*
            r14 = this;
            long r4 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "httpPatch: "
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r3 = r3.append(r15)
            java.lang.String r3 = r3.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r2.i(r3, r6)
            java.lang.String r2 = "org.apache.http.entity.InputStreamEntity"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.params.BasicHttpParams"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.params.HttpConnectionParams"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.HttpVersion"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.params.HttpProtocolParams"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.conn.scheme.SchemeRegistry"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.conn.scheme.PlainSocketFactory"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.conn.scheme.Scheme"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            java.lang.String r2 = "org.apache.http.impl.client.DefaultHttpClient"
            com.mob.tools.utils.ReflectHelper.importClass(r2)
            if (r16 == 0) goto L_0x0078
            r0 = r16
            java.lang.String r2 = r14.kvPairsToUrl(r0)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0078
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r3 = r3.append(r15)
            java.lang.String r6 = "?"
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r15 = r2.toString()
        L_0x0078:
            java.lang.Object r6 = r14.getHttpPatch(r15)
            if (r6 == 0) goto L_0x00a3
            if (r20 == 0) goto L_0x00a3
            java.util.Iterator r3 = r20.iterator()
        L_0x0084:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x00a3
            java.lang.Object r2 = r3.next()
            com.mob.tools.network.KVPair r2 = (com.mob.tools.network.KVPair) r2
            java.lang.String r7 = "setHeader"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            java.lang.String r10 = r2.name
            r8[r9] = r10
            r9 = 1
            T r2 = r2.value
            r8[r9] = r2
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r7, r8)
            goto L_0x0084
        L_0x00a3:
            com.mob.tools.network.FilePart r3 = new com.mob.tools.network.FilePart
            r3.<init>()
            r0 = r21
            r3.setOnReadListener(r0)
            r0 = r17
            T r2 = r0.value
            java.lang.String r2 = (java.lang.String) r2
            r3.setFile(r2)
            r0 = r18
            r3.setOffset(r0)
            java.io.InputStream r2 = r3.toInputStream()
            long r8 = r3.length()
            long r8 = r8 - r18
            java.lang.String r3 = "InputStreamEntity"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r10 = 0
            r7[r10] = r2
            r2 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r7[r2] = r8
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.newInstance(r3, r7)
            java.lang.String r3 = "setContentEncoding"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r8 = 0
            java.lang.String r9 = "application/offset+octet-stream"
            r7[r8] = r9
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r3, r7)
            java.lang.String r3 = "setEntity"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r8 = 0
            r7[r8] = r2
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r3, r7)
            java.lang.String r2 = "BasicHttpParams"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Object r7 = com.mob.tools.utils.ReflectHelper.newInstance(r2, r3)
            if (r23 != 0) goto L_0x0254
            int r2 = connectionTimeout
            r3 = r2
        L_0x00fe:
            if (r3 <= 0) goto L_0x0114
            java.lang.String r2 = "HttpConnectionParams"
            java.lang.String r8 = "setConnectionTimeout"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r7
            r10 = 1
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)
            r9[r10] = r11
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r2, r8, r9)
        L_0x0114:
            if (r23 != 0) goto L_0x025b
            int r2 = readTimout
        L_0x0118:
            if (r2 <= 0) goto L_0x012e
            java.lang.String r2 = "HttpConnectionParams"
            java.lang.String r8 = "setSoTimeout"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r7
            r10 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r9[r10] = r3
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r2, r8, r9)
        L_0x012e:
            java.lang.String r2 = "setParams"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r8 = 0
            r3[r8] = r7
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r2, r3)
            java.lang.String r2 = "https://"
            boolean r2 = r15.startsWith(r2)
            if (r2 == 0) goto L_0x0261
            java.lang.String r2 = java.security.KeyStore.getDefaultType()
            java.security.KeyStore r2 = java.security.KeyStore.getInstance(r2)
            r3 = 0
            r7 = 0
            r2.load(r3, r7)
            com.mob.tools.network.SSLSocketFactoryEx r3 = new com.mob.tools.network.SSLSocketFactoryEx
            r3.<init>(r2)
            org.apache.http.conn.ssl.X509HostnameVerifier r2 = org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER
            r3.setHostnameVerifier(r2)
            java.lang.String r2 = "BasicHttpParams"
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.newInstance(r2, r7)
            java.lang.String r7 = "HttpVersion"
            java.lang.String r8 = "HTTP_1_1"
            java.lang.Object r7 = com.mob.tools.utils.ReflectHelper.getStaticField(r7, r8)
            java.lang.String r8 = "HttpProtocolParams"
            java.lang.String r9 = "setVersion"
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r11 = 0
            r10[r11] = r2
            r11 = 1
            r10[r11] = r7
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r8, r9, r10)
            java.lang.String r7 = "HttpProtocolParams"
            java.lang.String r8 = "setContentCharset"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r2
            r10 = 1
            java.lang.String r11 = "UTF-8"
            r9[r10] = r11
            com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r7, r8, r9)
            java.lang.String r7 = "SchemeRegistry"
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            java.lang.Object r7 = com.mob.tools.utils.ReflectHelper.newInstance(r7, r8)
            java.lang.String r8 = "PlainSocketFactory"
            java.lang.String r9 = "getSocketFactory"
            r10 = 0
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.Object r8 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r8, r9, r10)
            java.lang.String r9 = "Scheme"
            r10 = 3
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r11 = 0
            java.lang.String r12 = "http"
            r10[r11] = r12
            r11 = 1
            r10[r11] = r8
            r8 = 2
            r11 = 80
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10[r8] = r11
            java.lang.Object r8 = com.mob.tools.utils.ReflectHelper.newInstance(r9, r10)
            java.lang.String r9 = "Scheme"
            r10 = 3
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r11 = 0
            java.lang.String r12 = "https"
            r10[r11] = r12
            r11 = 1
            r10[r11] = r3
            r3 = 2
            r11 = 443(0x1bb, float:6.21E-43)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r10[r3] = r11
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.newInstance(r9, r10)
            java.lang.String r9 = "register"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r11 = 0
            r10[r11] = r8
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r7, r9, r10)
            java.lang.String r8 = "register"
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r3
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r7, r8, r9)
            java.lang.String r3 = "ThreadSafeClientConnManager"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            r8[r9] = r2
            r9 = 1
            r8[r9] = r7
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.newInstance(r3, r8)
            java.lang.String r7 = "DefaultHttpClient"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            r8[r9] = r3
            r3 = 1
            r8[r3] = r2
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.newInstance(r7, r8)
        L_0x0207:
            java.lang.String r3 = "execute"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r8 = 0
            r7[r8] = r6
            java.lang.Object r3 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r3, r7)
            java.lang.String r6 = "getConnectionManager"
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r2, r6, r7)
            if (r22 == 0) goto L_0x0277
            com.mob.tools.network.HttpConnectionImpl r2 = new com.mob.tools.network.HttpConnectionImpl     // Catch:{ Throwable -> 0x026b }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x026b }
            r0 = r22
            r0.onResponse(r2)     // Catch:{ Throwable -> 0x026b }
            java.lang.String r2 = "shutdown"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r2, r3)
        L_0x0230:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "use time: "
            java.lang.StringBuilder r3 = r3.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r4 = r6 - r4
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r2.i(r3, r4)
            return
        L_0x0254:
            r0 = r23
            int r2 = r0.connectionTimeout
            r3 = r2
            goto L_0x00fe
        L_0x025b:
            r0 = r23
            int r2 = r0.readTimout
            goto L_0x0118
        L_0x0261:
            java.lang.String r2 = "DefaultHttpClient"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Object r2 = com.mob.tools.utils.ReflectHelper.newInstance(r2, r3)
            goto L_0x0207
        L_0x026b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x026d }
        L_0x026d:
            r2 = move-exception
            java.lang.String r3 = "shutdown"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r3, r4)
            throw r2
        L_0x0277:
            java.lang.String r2 = "shutdown"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r6, r2, r3)
            goto L_0x0230
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPatchImpl(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, long, java.util.ArrayList, com.mob.tools.network.OnReadListener, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    private Object getHttpPatch(String str) throws Throwable {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ef, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00f0, code lost:
        r3.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f3, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpPatchImpl23(java.lang.String r11, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r12, com.mob.tools.network.KVPair<java.lang.String> r13, long r14, java.util.ArrayList<com.mob.tools.network.KVPair<java.lang.String>> r16, com.mob.tools.network.OnReadListener r17, com.mob.tools.network.HttpResponseCallback r18, com.mob.tools.network.NetworkHelper.NetworkTimeOut r19) throws java.lang.Throwable {
        /*
            r10 = this;
            long r4 = java.lang.System.currentTimeMillis()
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "httpPatch: "
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r3 = r3.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r2.i(r3, r6)
            if (r12 == 0) goto L_0x0044
            java.lang.String r2 = r10.kvPairsToUrl(r12)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0044
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r6 = "?"
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r11 = r2.toString()
        L_0x0044:
            r0 = r19
            java.net.HttpURLConnection r3 = r10.getConnection(r11, r0)
            r2 = 1
            r3.setDoOutput(r2)
            r2 = 0
            r3.setChunkedStreamingMode(r2)
            java.lang.String r2 = "PATCH"
            r3.setRequestMethod(r2)
            java.lang.String r2 = "Content-Type"
            java.lang.String r6 = "application/offset+octet-stream"
            r3.setRequestProperty(r2, r6)
            if (r16 == 0) goto L_0x007a
            java.util.Iterator r6 = r16.iterator()
        L_0x0064:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L_0x007a
            java.lang.Object r2 = r6.next()
            com.mob.tools.network.KVPair r2 = (com.mob.tools.network.KVPair) r2
            java.lang.String r7 = r2.name
            T r2 = r2.value
            java.lang.String r2 = (java.lang.String) r2
            r3.setRequestProperty(r7, r2)
            goto L_0x0064
        L_0x007a:
            boolean r2 = r10.instanceFollowRedirects
            r3.setInstanceFollowRedirects(r2)
            r3.connect()
            java.io.OutputStream r6 = r3.getOutputStream()
            com.mob.tools.network.FilePart r7 = new com.mob.tools.network.FilePart
            r7.<init>()
            r0 = r17
            r7.setOnReadListener(r0)
            T r2 = r13.value
            java.lang.String r2 = (java.lang.String) r2
            r7.setFile(r2)
            r7.setOffset(r14)
            java.io.InputStream r7 = r7.toInputStream()
            r2 = 65536(0x10000, float:9.18355E-41)
            byte[] r8 = new byte[r2]
            int r2 = r7.read(r8)
        L_0x00a6:
            if (r2 <= 0) goto L_0x00b1
            r9 = 0
            r6.write(r8, r9, r2)
            int r2 = r7.read(r8)
            goto L_0x00a6
        L_0x00b1:
            r6.flush()
            r7.close()
            r6.close()
            if (r18 == 0) goto L_0x00f4
            com.mob.tools.network.HttpConnectionImpl23 r2 = new com.mob.tools.network.HttpConnectionImpl23     // Catch:{ Throwable -> 0x00ed }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ed }
            r0 = r18
            r0.onResponse(r2)     // Catch:{ Throwable -> 0x00ed }
            r3.disconnect()
        L_0x00c9:
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "use time: "
            java.lang.StringBuilder r3 = r3.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r4 = r6 - r4
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r2.i(r3, r4)
            return
        L_0x00ed:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            r2 = move-exception
            r3.disconnect()
            throw r2
        L_0x00f4:
            r3.disconnect()
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.network.NetworkHelper.httpPatchImpl23(java.lang.String, java.util.ArrayList, com.mob.tools.network.KVPair, long, java.util.ArrayList, com.mob.tools.network.OnReadListener, com.mob.tools.network.HttpResponseCallback, com.mob.tools.network.NetworkHelper$NetworkTimeOut):void");
    }

    private String kvPairsToUrl(ArrayList<KVPair<String>> arrayList) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            String urlEncode = Data.urlEncode(kVPair.name, "utf-8");
            String str = kVPair.value != null ? Data.urlEncode((String) kVPair.value, "utf-8") : "";
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(urlEncode).append('=').append(str);
        }
        return sb.toString();
    }

    private HttpURLConnection getConnection(String str, NetworkTimeOut networkTimeOut) throws Throwable {
        Object obj;
        String str2;
        boolean z;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        String str3 = "methodTokens";
        try {
            obj = ReflectHelper.getInstanceField(httpURLConnection, str3);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj == null) {
            String str4 = "PERMITTED_USER_METHODS";
            try {
                obj = ReflectHelper.getStaticField("HttpURLConnection", str4);
                str2 = str4;
                z = true;
            } catch (Throwable th2) {
                str2 = str4;
                z = true;
            }
        } else {
            str2 = str3;
            z = false;
        }
        if (obj != null) {
            String[] strArr = (String[]) obj;
            String[] strArr2 = new String[(strArr.length + 1)];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            strArr2[strArr.length] = METHOD.PATCH;
            if (z) {
                ReflectHelper.setStaticField("HttpURLConnection", str2, strArr2);
            } else {
                ReflectHelper.setInstanceField(httpURLConnection, str2, strArr2);
            }
        }
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            X509HostnameVerifier x509HostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new SimpleX509TrustManager(null)}, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(x509HostnameVerifier);
        }
        int i = networkTimeOut == null ? connectionTimeout : networkTimeOut.connectionTimeout;
        if (i > 0) {
            httpURLConnection.setConnectTimeout(i);
        }
        int i2 = networkTimeOut == null ? readTimout : networkTimeOut.readTimout;
        if (i2 > 0) {
            httpURLConnection.setReadTimeout(i2);
        }
        return httpURLConnection;
    }

    public boolean getInstanceFollowRedirects() {
        return this.instanceFollowRedirects;
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.instanceFollowRedirects = z;
    }

    public static boolean getFollowRedirects() {
        return followRedirects;
    }

    public static void setFollowRedirects(boolean z) {
        followRedirects = z;
    }

    public static String checkHttpRequestUrl(String str) {
        try {
            if (TextUtils.isEmpty(str) || VERSION.SDK_INT < 23) {
                return str;
            }
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod(ReflectHelper.importClass("android.security.NetworkSecurityPolicy"), "getInstance", new Object[0]);
            if (((Boolean) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "isCleartextTrafficPermitted", new Object[0])).booleanValue()) {
                return str;
            }
            String trim = str.trim();
            if (!trim.startsWith("http://")) {
                return trim;
            }
            Uri parse = Uri.parse(trim.trim());
            if (parse == null) {
                return trim;
            }
            String scheme = parse.getScheme();
            if (scheme == null || !scheme.equals("http")) {
                return trim;
            }
            String host = parse.getHost();
            String path = parse.getPath();
            if (host != null) {
                int port = parse.getPort();
                String str2 = host + ((port <= 0 || port == 80) ? "" : Config.TRACE_TODAY_VISIT_SPLIT + port);
                if (VERSION.SDK_INT >= 24) {
                    if (((Boolean) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "isCleartextTrafficPermitted", str2)).booleanValue()) {
                        return trim;
                    }
                }
                host = str2;
            }
            return "https://" + host + (path == null ? "" : path);
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return str;
        }
    }
}
