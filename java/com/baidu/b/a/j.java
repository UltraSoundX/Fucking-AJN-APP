package com.baidu.b.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.sensetime.senseid.sdk.liveness.interactive.common.util.NetworkUtil;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;

public class j {
    private Context a;
    private String b = null;
    private HashMap<String, String> c = null;
    private String d = null;

    public j(Context context) {
        this.a = context;
    }

    private String a(Context context) {
        String str = NetworkUtil.NETWORK_WIFI;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            return (extraInfo == null || (!extraInfo.trim().toLowerCase().equals("cmwap") && !extraInfo.trim().toLowerCase().equals("uniwap") && !extraInfo.trim().toLowerCase().equals("3gwap") && !extraInfo.trim().toLowerCase().equals("ctwap"))) ? str : extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap";
        } catch (Exception e) {
            if (d.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r6v3, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r6v5, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r2v6, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r6v9, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v11, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r3v27, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r3v35, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v20 */
    /* JADX WARNING: type inference failed for: r2v21 */
    /* JADX WARNING: type inference failed for: r2v22 */
    /* JADX WARNING: type inference failed for: r2v23 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11 */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* JADX WARNING: type inference failed for: r6v14 */
    /* JADX WARNING: type inference failed for: r6v15 */
    /* JADX WARNING: type inference failed for: r6v16 */
    /* JADX WARNING: type inference failed for: r6v17 */
    /* JADX WARNING: type inference failed for: r6v18 */
    /* JADX WARNING: type inference failed for: r2v24 */
    /* JADX WARNING: type inference failed for: r3v38 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v0
  assigns: []
  uses: []
  mth insns count: 256
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01e1 A[SYNTHETIC, Splitter:B:102:0x01e1] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01f6 A[SYNTHETIC, Splitter:B:113:0x01f6] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0085 A[Catch:{ all -> 0x0265 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cd A[Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d3 A[SYNTHETIC, Splitter:B:35:0x00d3] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d8 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0131 A[Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x013b A[Catch:{ all -> 0x0232 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x015f A[SYNTHETIC, Splitter:B:66:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0181 A[Catch:{ all -> 0x0230 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01a5 A[SYNTHETIC, Splitter:B:86:0x01a5] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01bd A[Catch:{ all -> 0x0230 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:96:0x01b9=Splitter:B:96:0x01b9, B:80:0x017d=Splitter:B:80:0x017d} */
    /* JADX WARNING: Unknown variable types count: 11 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(javax.net.ssl.HttpsURLConnection r13) {
        /*
            r12 = this;
            r11 = 200(0xc8, float:2.8E-43)
            r2 = 0
            r5 = -1
            r7 = 0
            r10 = -11
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "https Post start,url:"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r12.b
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.baidu.b.a.d.a(r0)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r12.c
            if (r0 != 0) goto L_0x002c
            java.lang.String r0 = "httpsPost request paramters is null."
            java.lang.String r0 = com.baidu.b.a.a.a(r0)
            r12.d = r0
        L_0x002b:
            return
        L_0x002c:
            r0 = 1
            java.io.OutputStream r6 = r13.getOutputStream()     // Catch:{ MalformedURLException -> 0x024a, IOException -> 0x017a, Exception -> 0x01b6, all -> 0x01f2 }
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r6, r4)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            r1.<init>(r3)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r12.c     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.lang.String r3 = b(r3)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            r1.write(r3)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r12.c     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.lang.String r3 = b(r3)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            com.baidu.b.a.d.a(r3)     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            r1.flush()     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            r1.close()     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            r13.connect()     // Catch:{ MalformedURLException -> 0x024e, IOException -> 0x023f, Exception -> 0x0235 }
            java.io.InputStream r1 = r13.getInputStream()     // Catch:{ IOException -> 0x0268, all -> 0x0122 }
            int r4 = r13.getResponseCode()     // Catch:{ IOException -> 0x026d, all -> 0x0258 }
            if (r11 != r4) goto L_0x027e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0271, all -> 0x025c }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0271, all -> 0x025c }
            java.lang.String r9 = "UTF-8"
            r8.<init>(r1, r9)     // Catch:{ IOException -> 0x0271, all -> 0x025c }
            r3.<init>(r8)     // Catch:{ IOException -> 0x0271, all -> 0x025c }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
            r2.<init>()     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
        L_0x0073:
            int r8 = r3.read()     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
            if (r8 == r5) goto L_0x010b
            char r8 = (char) r8     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
            r2.append(r8)     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
            goto L_0x0073
        L_0x007e:
            r0 = move-exception
            r2 = r3
            r3 = r4
        L_0x0081:
            boolean r4 = com.baidu.b.a.d.a     // Catch:{ all -> 0x0265 }
            if (r4 == 0) goto L_0x00a2
            r0.printStackTrace()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r4.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.String r8 = "httpsPost parse failed;"
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x0265 }
            java.lang.String r8 = r0.getMessage()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r4 = r4.append(r8)     // Catch:{ all -> 0x0265 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0265 }
            com.baidu.b.a.d.a(r4)     // Catch:{ all -> 0x0265 }
        L_0x00a2:
            r4 = -11
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r8.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.String r9 = "httpsPost failed,IOException:"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x0265 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r0 = r8.append(r0)     // Catch:{ all -> 0x0265 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0265 }
            java.lang.String r0 = com.baidu.b.a.a.a(r4, r0)     // Catch:{ all -> 0x0265 }
            r12.d = r0     // Catch:{ all -> 0x0265 }
            if (r1 == 0) goto L_0x00cb
            if (r2 == 0) goto L_0x00cb
            r2.close()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
            r1.close()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
        L_0x00cb:
            if (r13 == 0) goto L_0x0278
            r13.disconnect()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
            r0 = r7
        L_0x00d1:
            if (r6 == 0) goto L_0x00d6
            r6.close()     // Catch:{ IOException -> 0x0165 }
        L_0x00d6:
            if (r0 == 0) goto L_0x0203
            if (r11 == r3) goto L_0x0203
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "httpsPost failed,statusCode:"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.baidu.b.a.d.a(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "httpsPost failed,statusCode:"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = com.baidu.b.a.a.a(r10, r0)
            r12.d = r0
            goto L_0x002b
        L_0x010b:
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
            r12.d = r2     // Catch:{ IOException -> 0x007e, all -> 0x0260 }
        L_0x0111:
            if (r1 == 0) goto L_0x011b
            if (r3 == 0) goto L_0x011b
            r3.close()     // Catch:{ MalformedURLException -> 0x0253, IOException -> 0x0243, Exception -> 0x0238 }
            r1.close()     // Catch:{ MalformedURLException -> 0x0253, IOException -> 0x0243, Exception -> 0x0238 }
        L_0x011b:
            if (r13 == 0) goto L_0x027b
            r13.disconnect()     // Catch:{ MalformedURLException -> 0x0253, IOException -> 0x0243, Exception -> 0x0238 }
            r3 = r4
            goto L_0x00d1
        L_0x0122:
            r0 = move-exception
            r1 = r2
            r3 = r5
        L_0x0125:
            if (r1 == 0) goto L_0x012f
            if (r2 == 0) goto L_0x012f
            r2.close()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
            r1.close()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
        L_0x012f:
            if (r13 == 0) goto L_0x0134
            r13.disconnect()     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
        L_0x0134:
            throw r0     // Catch:{ MalformedURLException -> 0x0135, IOException -> 0x0247, Exception -> 0x023c }
        L_0x0135:
            r0 = move-exception
            r2 = r6
        L_0x0137:
            boolean r1 = com.baidu.b.a.d.a     // Catch:{ all -> 0x0232 }
            if (r1 == 0) goto L_0x013e
            r0.printStackTrace()     // Catch:{ all -> 0x0232 }
        L_0x013e:
            r1 = -11
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0232 }
            r4.<init>()     // Catch:{ all -> 0x0232 }
            java.lang.String r6 = "httpsPost failed,MalformedURLException:"
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x0232 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0232 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0232 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0232 }
            java.lang.String r0 = com.baidu.b.a.a.a(r1, r0)     // Catch:{ all -> 0x0232 }
            r12.d = r0     // Catch:{ all -> 0x0232 }
            if (r2 == 0) goto L_0x0275
            r2.close()     // Catch:{ IOException -> 0x016f }
            r0 = r7
            goto L_0x00d6
        L_0x0165:
            r1 = move-exception
            boolean r2 = com.baidu.b.a.d.a
            if (r2 == 0) goto L_0x00d6
            r1.printStackTrace()
            goto L_0x00d6
        L_0x016f:
            r0 = move-exception
            boolean r1 = com.baidu.b.a.d.a
            if (r1 == 0) goto L_0x0177
            r0.printStackTrace()
        L_0x0177:
            r0 = r7
            goto L_0x00d6
        L_0x017a:
            r0 = move-exception
            r3 = r5
            r6 = r2
        L_0x017d:
            boolean r1 = com.baidu.b.a.d.a     // Catch:{ all -> 0x0230 }
            if (r1 == 0) goto L_0x0184
            r0.printStackTrace()     // Catch:{ all -> 0x0230 }
        L_0x0184:
            r1 = -11
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0230 }
            r2.<init>()     // Catch:{ all -> 0x0230 }
            java.lang.String r4 = "httpsPost failed,IOException:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0230 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = com.baidu.b.a.a.a(r1, r0)     // Catch:{ all -> 0x0230 }
            r12.d = r0     // Catch:{ all -> 0x0230 }
            if (r6 == 0) goto L_0x0275
            r6.close()     // Catch:{ IOException -> 0x01ab }
            r0 = r7
            goto L_0x00d6
        L_0x01ab:
            r0 = move-exception
            boolean r1 = com.baidu.b.a.d.a
            if (r1 == 0) goto L_0x01b3
            r0.printStackTrace()
        L_0x01b3:
            r0 = r7
            goto L_0x00d6
        L_0x01b6:
            r0 = move-exception
            r3 = r5
            r6 = r2
        L_0x01b9:
            boolean r1 = com.baidu.b.a.d.a     // Catch:{ all -> 0x0230 }
            if (r1 == 0) goto L_0x01c0
            r0.printStackTrace()     // Catch:{ all -> 0x0230 }
        L_0x01c0:
            r1 = -11
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0230 }
            r2.<init>()     // Catch:{ all -> 0x0230 }
            java.lang.String r4 = "httpsPost failed,Exception:"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0230 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0230 }
            java.lang.String r0 = com.baidu.b.a.a.a(r1, r0)     // Catch:{ all -> 0x0230 }
            r12.d = r0     // Catch:{ all -> 0x0230 }
            if (r6 == 0) goto L_0x0275
            r6.close()     // Catch:{ IOException -> 0x01e7 }
            r0 = r7
            goto L_0x00d6
        L_0x01e7:
            r0 = move-exception
            boolean r1 = com.baidu.b.a.d.a
            if (r1 == 0) goto L_0x01ef
            r0.printStackTrace()
        L_0x01ef:
            r0 = r7
            goto L_0x00d6
        L_0x01f2:
            r0 = move-exception
            r6 = r2
        L_0x01f4:
            if (r6 == 0) goto L_0x01f9
            r6.close()     // Catch:{ IOException -> 0x01fa }
        L_0x01f9:
            throw r0
        L_0x01fa:
            r1 = move-exception
            boolean r2 = com.baidu.b.a.d.a
            if (r2 == 0) goto L_0x01f9
            r1.printStackTrace()
            goto L_0x01f9
        L_0x0203:
            java.lang.String r0 = r12.d
            if (r0 != 0) goto L_0x0216
            java.lang.String r0 = "httpsPost failed,mResult is null"
            com.baidu.b.a.d.a(r0)
            java.lang.String r0 = "httpsPost failed,internal error"
            java.lang.String r0 = com.baidu.b.a.a.a(r5, r0)
            r12.d = r0
            goto L_0x002b
        L_0x0216:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "httpsPost success end,parse result = "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r12.d
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.baidu.b.a.d.a(r0)
            goto L_0x002b
        L_0x0230:
            r0 = move-exception
            goto L_0x01f4
        L_0x0232:
            r0 = move-exception
            r6 = r2
            goto L_0x01f4
        L_0x0235:
            r0 = move-exception
            r3 = r5
            goto L_0x01b9
        L_0x0238:
            r0 = move-exception
            r3 = r4
            goto L_0x01b9
        L_0x023c:
            r0 = move-exception
            goto L_0x01b9
        L_0x023f:
            r0 = move-exception
            r3 = r5
            goto L_0x017d
        L_0x0243:
            r0 = move-exception
            r3 = r4
            goto L_0x017d
        L_0x0247:
            r0 = move-exception
            goto L_0x017d
        L_0x024a:
            r0 = move-exception
            r3 = r5
            goto L_0x0137
        L_0x024e:
            r0 = move-exception
            r3 = r5
            r2 = r6
            goto L_0x0137
        L_0x0253:
            r0 = move-exception
            r3 = r4
            r2 = r6
            goto L_0x0137
        L_0x0258:
            r0 = move-exception
            r3 = r5
            goto L_0x0125
        L_0x025c:
            r0 = move-exception
            r3 = r4
            goto L_0x0125
        L_0x0260:
            r0 = move-exception
            r2 = r3
            r3 = r4
            goto L_0x0125
        L_0x0265:
            r0 = move-exception
            goto L_0x0125
        L_0x0268:
            r0 = move-exception
            r1 = r2
            r3 = r5
            goto L_0x0081
        L_0x026d:
            r0 = move-exception
            r3 = r5
            goto L_0x0081
        L_0x0271:
            r0 = move-exception
            r3 = r4
            goto L_0x0081
        L_0x0275:
            r0 = r7
            goto L_0x00d6
        L_0x0278:
            r0 = r7
            goto L_0x00d1
        L_0x027b:
            r3 = r4
            goto L_0x00d1
        L_0x027e:
            r3 = r2
            goto L_0x0111
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.b.a.j.a(javax.net.ssl.HttpsURLConnection):void");
    }

    private static String b(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        boolean z;
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (Entry entry : hashMap.entrySet()) {
            if (z2) {
                z = false;
            } else {
                sb.append("&");
                z = z2;
            }
            sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            z2 = z;
        }
        return sb.toString();
    }

    private HttpsURLConnection b() {
        try {
            URL url = new URL(this.b);
            d.a("https URL: " + this.b);
            String a2 = a(this.a);
            if (a2 == null || a2.equals("")) {
                d.c("Current network is not available.");
                this.d = a.a(-10, "Current network is not available.");
                return null;
            }
            d.a("checkNetwork = " + a2);
            HttpsURLConnection httpsURLConnection = a2.equals("cmwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : a2.equals("ctwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setHostnameVerifier(new k(this));
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setConnectTimeout(50000);
            httpsURLConnection.setReadTimeout(50000);
            return httpsURLConnection;
        } catch (MalformedURLException e) {
            if (d.a) {
                e.printStackTrace();
                d.a(e.getMessage());
            }
            this.d = a.a(-11, "Auth server could not be parsed as a URL.");
            return null;
        } catch (Exception e2) {
            if (d.a) {
                e2.printStackTrace();
                d.a(e2.getMessage());
            }
            this.d = a.a(-11, "Init httpsurlconnection failed.");
            return null;
        }
    }

    private HashMap<String, String> c(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str : hashMap.keySet()) {
            String str2 = str.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    /* access modifiers changed from: protected */
    public String a(HashMap<String, String> hashMap) {
        this.c = c(hashMap);
        this.b = (String) this.c.get("url");
        HttpsURLConnection b2 = b();
        if (b2 == null) {
            d.c("syncConnect failed,httpsURLConnection is null");
            return this.d;
        }
        a(b2);
        return this.d;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        d.a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            d.a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (d.a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
