package com.c.a;

import android.content.Context;
import android.text.TextUtils;
import com.c.a.c.a.c;
import com.c.a.c.b;
import com.c.a.d.d;
import java.io.IOException;
import java.util.concurrent.Executor;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* compiled from: HttpUtils */
public class a {
    public static final com.c.a.c.a a = new com.c.a.c.a();
    private static final d g = new d(3);
    private final DefaultHttpClient b;
    private final HttpContext c;
    private c d;
    private String e;
    private long f;

    public a() {
        this(15000, null);
    }

    public a(int i) {
        this(i, null);
    }

    public a(int i, String str) {
        this.c = new BasicHttpContext();
        this.e = "UTF-8";
        this.f = com.c.a.c.a.a();
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        if (TextUtils.isEmpty(str)) {
            str = com.c.a.e.d.a((Context) null);
        }
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", com.c.a.c.b.a.a(), 443));
        this.b = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.b.setHttpRequestRetryHandler(new com.c.a.c.b.c(3));
        this.b.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
            }
        });
        this.b.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    Header contentEncoding = entity.getContentEncoding();
                    if (contentEncoding != null) {
                        for (HeaderElement name : contentEncoding.getElements()) {
                            if (name.getName().equalsIgnoreCase("gzip")) {
                                httpResponse.setEntity(new com.c.a.c.b.a.c(httpResponse.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    public a a(CookieStore cookieStore) {
        this.c.setAttribute("http.cookie-store", cookieStore);
        return this;
    }

    public a a(SSLSocketFactory sSLSocketFactory) {
        this.b.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
        return this;
    }

    public <T> b<T> a(com.c.a.c.b.b.a aVar, String str, com.c.a.c.c cVar, com.c.a.c.a.d<T> dVar) {
        if (str != null) {
            return a(new com.c.a.c.b.b(aVar, str), cVar, dVar);
        }
        throw new IllegalArgumentException("url may not be null");
    }

    private <T> b<T> a(com.c.a.c.b.b bVar, com.c.a.c.c cVar, com.c.a.c.a.d<T> dVar) {
        b<T> bVar2 = new b<>(this.b, this.c, this.e, dVar);
        bVar2.a(this.f);
        bVar2.a(this.d);
        bVar.a(cVar, bVar2);
        if (cVar != null) {
            bVar2.a(cVar.a());
        }
        bVar2.a((Executor) g, bVar);
        return bVar2;
    }
}
