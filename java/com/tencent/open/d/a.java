package com.tencent.open.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.open.a.f;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import net.sf.json.util.JSONUtils;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {

    /* renamed from: com.tencent.open.d.a$a reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static class C0080a extends Exception {
        public C0080a(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    public static class b extends Exception {
        public b(String str) {
            super(str);
        }
    }

    /* compiled from: ProGuard */
    public static class c {
        public final String a;
        public final int b;

        private c(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0102, code lost:
        r12 = -4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x014b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x014c, code lost:
        r13 = r4;
        r13.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r12 = java.lang.Integer.parseInt(r13.getMessage().replace("http status code error:", ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x016c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x016d, code lost:
        r4.printStackTrace();
        r12 = -9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0173, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0174, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0177, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0178, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0179, code lost:
        r13 = r4;
        r13.printStackTrace();
        com.tencent.open.b.g.a().a(r5, r6, 0, 0, -3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0189, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x018a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x018b, code lost:
        r13 = r4;
        r13.printStackTrace();
        com.tencent.open.b.g.a().a(r5, r6, 0, 0, a(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x019e, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01b1, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01b2, code lost:
        r15 = r8;
        r16 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01b7, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01b8, code lost:
        r15 = r8;
        r16 = r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x014b A[ExcHandler: a (r4v12 'e' com.tencent.open.d.a$a A[CUSTOM_DECLARE]), Splitter:B:8:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0173 A[ExcHandler: b (r4v11 'e' com.tencent.open.d.a$b A[CUSTOM_DECLARE]), Splitter:B:8:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0178 A[ExcHandler: MalformedURLException (r4v9 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:8:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x018a A[ExcHandler: IOException (r4v7 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:8:0x00e2] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01bd A[LOOP:0: B:7:0x00da->B:61:0x01bd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0143 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject a(com.tencent.connect.b.b r20, android.content.Context r21, java.lang.String r22, android.os.Bundle r23, java.lang.String r24) throws java.io.IOException, org.json.JSONException, com.tencent.open.d.a.b, com.tencent.open.d.a.C0080a {
        /*
            java.lang.String r4 = "openSDK_LOG.HttpUtils"
            java.lang.String r5 = "OpenApi request"
            com.tencent.open.a.f.c(r4, r5)
            java.lang.String r4 = r22.toLowerCase()
            java.lang.String r5 = "http"
            boolean r4 = r4.startsWith(r5)
            if (r4 != 0) goto L_0x01c1
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.tencent.open.d.f r5 = com.tencent.open.d.f.a()
            java.lang.String r6 = "https://openmobile.qq.com/"
            r0 = r21
            java.lang.String r5 = r5.a(r0, r6)
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r22
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            com.tencent.open.d.f r6 = com.tencent.open.d.f.a()
            java.lang.String r7 = "https://openmobile.qq.com/"
            r0 = r21
            java.lang.String r6 = r6.a(r0, r7)
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r22
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
        L_0x0051:
            r0 = r21
            r1 = r20
            r2 = r22
            a(r0, r1, r2)
            r10 = 0
            long r8 = android.os.SystemClock.elapsedRealtime()
            r7 = 0
            java.lang.String r6 = r20.b()
            r0 = r21
            com.tencent.open.d.e r6 = com.tencent.open.d.e.a(r0, r6)
            java.lang.String r11 = "Common_HttpRetryCount"
            int r6 = r6.a(r11)
            java.lang.String r11 = "OpenConfig_test"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "config 1:Common_HttpRetryCount            config_value:"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r6)
            java.lang.String r13 = "   appid:"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = r20.b()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = "     url:"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.StringBuilder r12 = r12.append(r5)
            java.lang.String r12 = r12.toString()
            com.tencent.open.a.f.a(r11, r12)
            if (r6 != 0) goto L_0x00ff
            r6 = 3
            r13 = r6
        L_0x00a4:
            java.lang.String r6 = "OpenConfig_test"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "config 1:Common_HttpRetryCount            result_value:"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r13)
            java.lang.String r12 = "   appid:"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = r20.b()
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "     url:"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r5)
            java.lang.String r11 = r11.toString()
            com.tencent.open.a.f.a(r6, r11)
            r18 = r7
            r6 = r8
            r8 = r18
            r9 = r10
        L_0x00da:
            int r14 = r8 + 1
            r0 = r21
            r1 = r24
            r2 = r23
            com.tencent.open.d.j$a r10 = a(r0, r4, r1, r2)     // Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
            java.lang.String r8 = r10.a     // Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
            org.json.JSONObject r15 = com.tencent.open.d.j.c(r8)     // Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
            java.lang.String r8 = "ret"
            int r12 = r15.getInt(r8)     // Catch:{ JSONException -> 0x0101, ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a }
        L_0x00f2:
            long r8 = r10.b     // Catch:{ ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
            long r10 = r10.c     // Catch:{ ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, a -> 0x014b, b -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
            r13 = r15
        L_0x00f7:
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            return r13
        L_0x00ff:
            r13 = r6
            goto L_0x00a4
        L_0x0101:
            r8 = move-exception
            r12 = -4
            goto L_0x00f2
        L_0x0104:
            r8 = move-exception
            r16 = r15
            r15 = r8
        L_0x0108:
            r15.printStackTrace()
            r12 = -7
            r8 = 0
            r10 = 0
            if (r14 >= r13) goto L_0x0122
            long r6 = android.os.SystemClock.elapsedRealtime()
            r18 = r8
            r8 = r16
            r16 = r18
        L_0x011c:
            if (r14 < r13) goto L_0x01bd
            r13 = r8
            r8 = r16
            goto L_0x00f7
        L_0x0122:
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r15
        L_0x012a:
            r8 = move-exception
            r16 = r15
            r15 = r8
        L_0x012e:
            r15.printStackTrace()
            r12 = -8
            r8 = 0
            r10 = 0
            if (r14 >= r13) goto L_0x0143
            long r6 = android.os.SystemClock.elapsedRealtime()
            r18 = r8
            r8 = r16
            r16 = r18
            goto L_0x011c
        L_0x0143:
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r15
        L_0x014b:
            r4 = move-exception
            r13 = r4
            r13.printStackTrace()
            java.lang.String r4 = r13.getMessage()
            java.lang.String r8 = "http status code error:"
            java.lang.String r9 = ""
            java.lang.String r4 = r4.replace(r8, r9)     // Catch:{ Exception -> 0x016c }
            int r12 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x016c }
        L_0x0160:
            r8 = 0
            r10 = 0
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r13
        L_0x016c:
            r4 = move-exception
            r4.printStackTrace()
            r12 = -9
            goto L_0x0160
        L_0x0173:
            r4 = move-exception
            r4.printStackTrace()
            throw r4
        L_0x0178:
            r4 = move-exception
            r13 = r4
            r13.printStackTrace()
            r12 = -3
            r8 = 0
            r10 = 0
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r13
        L_0x018a:
            r4 = move-exception
            r13 = r4
            r13.printStackTrace()
            int r12 = a(r13)
            r8 = 0
            r10 = 0
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r13
        L_0x019f:
            r4 = move-exception
            r13 = r4
            r13.printStackTrace()
            r12 = -4
            r8 = 0
            r10 = 0
            com.tencent.open.b.g r4 = com.tencent.open.b.g.a()
            r4.a(r5, r6, r8, r10, r12)
            throw r13
        L_0x01b1:
            r8 = move-exception
            r15 = r8
            r16 = r9
            goto L_0x012e
        L_0x01b7:
            r8 = move-exception
            r15 = r8
            r16 = r9
            goto L_0x0108
        L_0x01bd:
            r9 = r8
            r8 = r14
            goto L_0x00da
        L_0x01c1:
            r5 = r22
            r4 = r22
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.d.a.a(com.tencent.connect.b.b, android.content.Context, java.lang.String, android.os.Bundle, java.lang.String):org.json.JSONObject");
    }

    public static void a(com.tencent.connect.b.b bVar, Context context, String str, Bundle bundle, String str2, com.tencent.tauth.a aVar) {
        f.c("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        final com.tencent.connect.b.b bVar2 = bVar;
        final Context context2 = context;
        final String str3 = str;
        final Bundle bundle2 = bundle;
        final String str4 = str2;
        final com.tencent.tauth.a aVar2 = aVar;
        new Thread() {
            public void run() {
                try {
                    JSONObject a2 = a.a(bVar2, context2, str3, bundle2, str4);
                    if (aVar2 != null) {
                        aVar2.a(a2);
                        f.c("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                } catch (MalformedURLException e2) {
                    if (aVar2 != null) {
                        aVar2.a(e2);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", e2);
                    }
                } catch (ConnectTimeoutException e3) {
                    if (aVar2 != null) {
                        aVar2.a(e3);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", e3);
                    }
                } catch (SocketTimeoutException e4) {
                    if (aVar2 != null) {
                        aVar2.a(e4);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", e4);
                    }
                } catch (b e5) {
                    if (aVar2 != null) {
                        aVar2.a(e5);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", e5);
                    }
                } catch (C0080a e6) {
                    if (aVar2 != null) {
                        aVar2.a(e6);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", e6);
                    }
                } catch (IOException e7) {
                    if (aVar2 != null) {
                        aVar2.a(e7);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", e7);
                    }
                } catch (JSONException e8) {
                    if (aVar2 != null) {
                        aVar2.a(e8);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", e8);
                    }
                } catch (Exception e9) {
                    if (aVar2 != null) {
                        aVar2.a(e9);
                        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", e9);
                    }
                }
            }
        }.start();
    }

    private static void a(Context context, com.tencent.connect.b.b bVar, String str) {
        if (str.indexOf("add_share") > -1 || str.indexOf("upload_pic") > -1 || str.indexOf("add_topic") > -1 || str.indexOf("set_user_face") > -1 || str.indexOf("add_t") > -1 || str.indexOf("add_pic_t") > -1 || str.indexOf("add_pic_url") > -1 || str.indexOf("add_video") > -1) {
            com.tencent.connect.a.a.a(context, bVar, "requireApi", str);
        }
    }

    public static int a(IOException iOException) {
        if (iOException instanceof CharConversionException) {
            return -20;
        }
        if (iOException instanceof MalformedInputException) {
            return -21;
        }
        if (iOException instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException instanceof HttpResponseException) {
            return -23;
        }
        if (iOException instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException instanceof ConnectionClosedException) {
            return -25;
        }
        if (iOException instanceof EOFException) {
            return -26;
        }
        if (iOException instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException instanceof HttpRetryException) {
            return -29;
        }
        if (iOException instanceof ConnectTimeoutException) {
            return -7;
        }
        if (iOException instanceof SocketTimeoutException) {
            return -8;
        }
        if (iOException instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException instanceof MalformedChunkCodingException) {
            return -31;
        }
        if (iOException instanceof MalformedURLException) {
            return -3;
        }
        if (iOException instanceof NoHttpResponseException) {
            return -32;
        }
        if (iOException instanceof InvalidClassException) {
            return -33;
        }
        if (iOException instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException instanceof NotActiveException) {
            return -35;
        }
        if (iOException instanceof NotSerializableException) {
            return -36;
        }
        if (iOException instanceof OptionalDataException) {
            return -37;
        }
        if (iOException instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException instanceof ProtocolException) {
            return -40;
        }
        if (iOException instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException instanceof SSLKeyException) {
            return -42;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException instanceof BindException) {
            return -45;
        }
        if (iOException instanceof ConnectException) {
            return -46;
        }
        if (iOException instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException instanceof SyncFailedException) {
            return -49;
        }
        if (iOException instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException instanceof UnknownHostException) {
            return -51;
        }
        if (iOException instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException instanceof UnsupportedEncodingException) {
            return -53;
        }
        if (iOException instanceof ZipException) {
            return -54;
        }
        return -2;
    }

    public static com.tencent.open.d.j.a a(Context context, String str, String str2, Bundle bundle) throws MalformedURLException, IOException, b, C0080a {
        Bundle bundle2;
        HttpUriRequest httpUriRequest;
        int i;
        String str3;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    throw new b("network unavailable");
                }
            }
        }
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        String str4 = "";
        String string = bundle2.getString("appid_for_getting_config");
        bundle2.remove("appid_for_getting_config");
        HttpClient a = a(context, string, str);
        if (str2.equals("GET")) {
            String a2 = a(bundle2);
            int length = 0 + a2.length();
            f.a("openSDK_LOG.HttpUtils", "-->openUrl2 before url =" + str);
            if (str.indexOf("?") == -1) {
                str3 = str + "?";
            } else {
                str3 = str + "&";
            }
            f.c("openSDK_LOG.HttpUtils", "-->openUrl2 encodedParam =" + a2 + " -- url = " + str3);
            HttpUriRequest httpGet = new HttpGet(str3 + a2);
            httpGet.addHeader("Accept-Encoding", "gzip");
            int i2 = length;
            httpUriRequest = httpGet;
            i = i2;
        } else if (str2.equals("POST")) {
            HttpUriRequest httpPost = new HttpPost(str);
            httpPost.addHeader("Accept-Encoding", "gzip");
            Bundle bundle3 = new Bundle();
            for (String str5 : bundle2.keySet()) {
                Object obj = bundle2.get(str5);
                if (obj instanceof byte[]) {
                    bundle3.putByteArray(str5, (byte[]) obj);
                }
            }
            if (!bundle2.containsKey("method")) {
                bundle2.putString("method", str2);
            }
            httpPost.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            httpPost.setHeader("Connection", "Keep-Alive");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(j.h("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
            byteArrayOutputStream.write(j.h(a(bundle2, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
            if (!bundle3.isEmpty()) {
                int size = bundle3.size();
                byteArrayOutputStream.write(j.h("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                int i3 = -1;
                for (String str6 : bundle3.keySet()) {
                    i3++;
                    byteArrayOutputStream.write(j.h("Content-Disposition: form-data; name=\"" + str6 + "\"; filename=\"" + str6 + JSONUtils.DOUBLE_QUOTE + "\r\n"));
                    byteArrayOutputStream.write(j.h("Content-Type: content/unknown\r\n\r\n"));
                    byte[] byteArray = bundle3.getByteArray(str6);
                    if (byteArray != null) {
                        byteArrayOutputStream.write(byteArray);
                    }
                    if (i3 < size - 1) {
                        byteArrayOutputStream.write(j.h("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                    }
                }
            }
            byteArrayOutputStream.write(j.h("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            i = byteArray2.length + 0;
            byteArrayOutputStream.close();
            httpPost.setEntity(new ByteArrayEntity(byteArray2));
            httpUriRequest = httpPost;
        } else {
            httpUriRequest = null;
            i = 0;
        }
        HttpResponse execute = a.execute(httpUriRequest);
        int statusCode = execute.getStatusLine().getStatusCode();
        f.c("openSDK_LOG.HttpUtils", "-->openUrl2 response cdoe =" + statusCode);
        if (statusCode == 200) {
            return new com.tencent.open.d.j.a(a(execute), i);
        }
        throw new C0080a("http status code error:" + statusCode);
    }

    private static String a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream inputStream;
        String str = "";
        InputStream content = httpResponse.getEntity().getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) {
            inputStream = content;
        } else {
            inputStream = new GZIPInputStream(content);
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                inputStream.close();
                return str2;
            }
        }
    }

    public static HttpClient a(Context context, String str, String str2) {
        e eVar;
        int i;
        int i2 = 0;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        try {
            if (VERSION.SDK_INT >= 23) {
                SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
                socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                schemeRegistry.register(new Scheme("https", socketFactory, 443));
            } else {
                schemeRegistry.register(new Scheme("https", new i(), 443));
            }
        } catch (Exception e) {
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        if (context != null) {
            eVar = e.a(context, str);
        } else {
            eVar = null;
        }
        if (eVar != null) {
            i = eVar.a("Common_HttpConnectionTimeout");
            i2 = eVar.a("Common_SocketConnectionTimeout");
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 15000;
        }
        if (i2 == 0) {
            i2 = Config.SESSION_PERIOD;
        }
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i2);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUserAgent(basicHttpParams, "AndroidSDK_" + VERSION.SDK + "_" + Build.DEVICE + "_" + VERSION.RELEASE);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        c a = a(context);
        if (a != null) {
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(a.a, a.b));
        }
        return defaultHttpClient;
    }

    public static String a(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if ((obj instanceof String) || (obj instanceof String[])) {
                if (obj instanceof String[]) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append("&");
                    }
                    sb.append(URLEncoder.encode(str) + "=");
                    String[] stringArray = bundle.getStringArray(str);
                    if (stringArray != null) {
                        for (int i = 0; i < stringArray.length; i++) {
                            if (i == 0) {
                                sb.append(URLEncoder.encode(stringArray[i]));
                            } else {
                                sb.append(URLEncoder.encode(StorageInterface.KEY_SPLITER + stringArray[i]));
                            }
                        }
                    }
                } else {
                    if (z) {
                        z = false;
                    } else {
                        sb.append("&");
                    }
                    sb.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
                }
                z = z;
            }
        }
        return sb.toString();
    }

    public static String a(Bundle bundle, String str) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = bundle.size();
        int i = -1;
        for (String str2 : bundle.keySet()) {
            int i2 = i + 1;
            Object obj = bundle.get(str2);
            if (!(obj instanceof String)) {
                i = i2;
            } else {
                sb.append("Content-Disposition: form-data; name=\"" + str2 + JSONUtils.DOUBLE_QUOTE + "\r\n" + "\r\n" + ((String) obj));
                if (i2 < size - 1) {
                    sb.append("\r\n--" + str + "\r\n");
                }
                i = i2;
            }
        }
        return sb.toString();
    }

    public static c a(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            String c2 = c(context);
            int b2 = b(context);
            if (!TextUtils.isEmpty(c2) && b2 >= 0) {
                return new c(c2, b2);
            }
        }
        return null;
    }

    private static int b(Context context) {
        int i = -1;
        if (VERSION.SDK_INT >= 11) {
            String property = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return i;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                return i;
            }
        } else if (context == null) {
            return Proxy.getDefaultPort();
        } else {
            int port = Proxy.getPort(context);
            if (port < 0) {
                return Proxy.getDefaultPort();
            }
            return port;
        }
    }

    private static String c(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (context == null) {
            return Proxy.getDefaultHost();
        }
        String host = Proxy.getHost(context);
        if (TextUtils.isEmpty(host)) {
            return Proxy.getDefaultHost();
        }
        return host;
    }
}
