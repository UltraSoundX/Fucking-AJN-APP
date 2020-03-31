package com.tencent.android.tpush.stat;

import android.content.Context;
import com.stub.StubApp;
import com.tencent.android.tpush.stat.a.b;
import com.tencent.android.tpush.stat.a.c;
import com.tencent.mid.sotrage.StorageInterface;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class d {
    private static com.tencent.android.tpush.stat.a.d c = c.b();
    private static volatile d d = null;
    private static Context e = null;
    DefaultHttpClient a = null;
    StringBuilder b = new StringBuilder(4096);
    private long f = 0;

    private d(Context context) {
        try {
            e = StubApp.getOrigApplicationContext(context.getApplicationContext());
            this.f = System.currentTimeMillis() / 1000;
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.a = new DefaultHttpClient(basicHttpParams);
            this.a.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
                public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                    long keepAliveDuration = d.super.getKeepAliveDuration(httpResponse, httpContext);
                    if (keepAliveDuration == -1) {
                        return 30000;
                    }
                    return keepAliveDuration;
                }
            });
        } catch (Throwable th) {
            c.b(th);
        }
    }

    static void a(Context context) {
        e = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    static Context a() {
        return e;
    }

    public static d b(Context context) {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d(context);
                }
            }
        }
        return d;
    }

    private void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull("cfg")) {
                b.a(e, jSONObject.getJSONObject("cfg"));
            }
            if (!jSONObject.isNull("ncts")) {
                int i = jSONObject.getInt("ncts");
                int currentTimeMillis = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                if (b.b()) {
                    c.b((Object) "server time:" + i + ", diff time:" + currentTimeMillis);
                }
                c.e(e);
                c.a(e, currentTimeMillis);
            }
        } catch (Throwable th) {
            c.d(th);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(List<?> list, c cVar) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Throwable th = null;
            try {
                this.b.delete(0, this.b.length());
                this.b.append("[");
                String str = "rc4";
                for (int i = 0; i < size; i++) {
                    this.b.append(list.get(i).toString());
                    if (i != size - 1) {
                        this.b.append(StorageInterface.KEY_SPLITER);
                    }
                }
                this.b.append("]");
                String sb = this.b.toString();
                int length = sb.length();
                String str2 = b.d() + "/?index=" + this.f;
                this.f++;
                if (b.b()) {
                    c.b((Object) "[" + str2 + "]Send request(eventsize:" + size + StorageInterface.KEY_SPLITER + length + "bytes), content:" + sb);
                }
                HttpPost httpPost = new HttpPost(str2);
                httpPost.addHeader("Accept-Encoding", "gzip");
                httpPost.setHeader("Connection", "Keep-Alive");
                httpPost.removeHeaders("Cache-Control");
                HttpHost b2 = c.b(e);
                httpPost.addHeader("Content-Encoding", str);
                if (b2 == null) {
                    this.a.getParams().removeParameter("http.route.default-proxy");
                } else {
                    if (b.b()) {
                        c.h("proxy:" + b2.toHostString());
                    }
                    httpPost.addHeader("X-Content-Encoding", str);
                    this.a.getParams().setParameter("http.route.default-proxy", b2);
                    httpPost.addHeader("X-Online-Host", b.d);
                    httpPost.addHeader("Accept", "*/*");
                    httpPost.addHeader("Content-Type", "json");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
                byte[] bytes = sb.getBytes("UTF-8");
                int length2 = bytes.length;
                if (length > 512) {
                    httpPost.removeHeaders("Content-Encoding");
                    String str3 = str + ",gzip";
                    httpPost.addHeader("Content-Encoding", str3);
                    if (b2 != null) {
                        httpPost.removeHeaders("X-Content-Encoding");
                        httpPost.addHeader("X-Content-Encoding", str3);
                    }
                    byteArrayOutputStream.write(new byte[4]);
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bytes);
                    gZIPOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
                    ByteBuffer.wrap(bytes, 0, 4).putInt(length2);
                    if (b.b()) {
                        c.h("before Gzip:" + length2 + " bytes, after Gzip:" + bytes.length + " bytes");
                    }
                }
                httpPost.setEntity(new ByteArrayEntity(b.a(bytes)));
                HttpResponse execute = this.a.execute(httpPost);
                HttpEntity entity = execute.getEntity();
                int statusCode = execute.getStatusLine().getStatusCode();
                long contentLength = entity.getContentLength();
                if (b.b()) {
                    c.b((Object) "http recv response status code:" + statusCode + ", content length:" + contentLength);
                }
                if (contentLength <= 0) {
                    c.f("Server response no data.");
                    if (cVar != null) {
                        cVar.b();
                    }
                    EntityUtils.toString(entity);
                    return;
                }
                if (contentLength > 0) {
                    InputStream content = entity.getContent();
                    DataInputStream dataInputStream = new DataInputStream(content);
                    byte[] bArr = new byte[((int) entity.getContentLength())];
                    dataInputStream.readFully(bArr);
                    content.close();
                    dataInputStream.close();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null) {
                        if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                            bArr = b.b(c.a(bArr));
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                            bArr = c.a(b.b(bArr));
                        } else if (firstHeader.getValue().equalsIgnoreCase("gzip")) {
                            bArr = c.a(bArr);
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                            bArr = b.b(bArr);
                        }
                    }
                    String str4 = new String(bArr, "UTF-8");
                    if (b.b()) {
                        c.b((Object) "http get response data:" + str4);
                    }
                    JSONObject jSONObject = new JSONObject(str4);
                    if (statusCode == 200) {
                        a(jSONObject);
                        if (cVar != null) {
                            if (jSONObject.optInt("ret") == 0) {
                                cVar.a();
                            } else {
                                c.e("response error data.");
                                cVar.b();
                            }
                        }
                    } else {
                        c.e("Server response error code:" + statusCode + ", error:" + new String(bArr, "UTF-8"));
                        if (cVar != null) {
                            cVar.b();
                        }
                    }
                    content.close();
                } else {
                    EntityUtils.toString(entity);
                }
                byteArrayOutputStream.close();
                if (th != null) {
                    c.a(th);
                    if (cVar != null) {
                        try {
                            cVar.b();
                        } catch (Throwable th2) {
                            c.b(th2);
                        }
                    }
                    if (th instanceof OutOfMemoryError) {
                        System.gc();
                        this.b = null;
                        this.b = new StringBuilder(2048);
                        return;
                    }
                    if ((th instanceof UnknownHostException) || (th instanceof SocketTimeoutException)) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(List<?> list, c cVar) {
        a(list, cVar);
    }

    public void a(com.tencent.android.tpush.stat.event.b bVar, c cVar) {
        b(Arrays.asList(new String[]{bVar.d()}), cVar);
    }
}
