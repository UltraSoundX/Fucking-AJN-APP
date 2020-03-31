package com.tencent.mid.core;

import com.baidu.mobstat.Config;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.RSAHelper;
import com.tencent.mid.util.Util;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpConnectClient {
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private String addr;
    private DefaultHttpClient client;
    private Map<String, String> httpParamsMap;
    private Logger logger;
    private HttpHost proxy;
    private int timeout;

    public HttpResponseResult httpPost(String str, byte[] bArr, String str2, int i) throws Exception {
        String httpFullUrl = getHttpFullUrl(str);
        this.logger.i("[" + httpFullUrl + "]Send request(" + bArr.length + "bytes):" + bArr);
        HttpPost httpPost = new HttpPost(httpFullUrl);
        httpPost.setHeader("Connection", "Keep-Alive");
        httpPost.removeHeaders("Cache-Control");
        httpPost.removeHeaders("User-Agent");
        if (this.proxy != null) {
            httpPost.addHeader("X-Online-Host", this.addr);
            httpPost.addHeader("Accept", "*/*");
            httpPost.addHeader("Content-Type", "json");
        } else {
            this.client.getParams().removeParameter("http.route.default-proxy");
        }
        if (this.proxy == null) {
            httpPost.addHeader(HEADER_CONTENT_ENCODING, str2);
        } else {
            httpPost.addHeader("X-Content-Encoding", str2);
        }
        httpPost.setEntity(new ByteArrayEntity(bArr));
        HttpResponse execute = this.client.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        int statusCode = execute.getStatusLine().getStatusCode();
        this.logger.i("recv response status code:" + statusCode + ", content length:" + entity.getContentLength());
        byte[] byteArray = EntityUtils.toByteArray(entity);
        String str3 = "";
        Header firstHeader = execute.getFirstHeader(HEADER_CONTENT_ENCODING);
        if (firstHeader != null) {
            if (firstHeader.getValue().toUpperCase().contains("AES")) {
                str3 = new String(HttpManager.getInstance(HttpManager.getApplicationContext()).getAesHelper(i).decrypt(byteArray), "UTF-8");
            }
            if (firstHeader.getValue().toUpperCase().contains("RSA")) {
                str3 = RSAHelper.decrypt(byteArray);
            }
            if (firstHeader.getValue().toUpperCase().contains("IDENTITY")) {
                str3 = new String(byteArray, "UTF-8");
            }
        }
        this.logger.i("recv response status code:" + statusCode + ", content :" + str3);
        return new HttpResponseResult(statusCode, str3);
    }

    public HttpConnectClient(String str, Map<String, String> map) {
        this.proxy = null;
        this.client = null;
        this.addr = null;
        this.httpParamsMap = null;
        this.logger = null;
        this.timeout = Config.SESSION_PERIOD;
        this.logger = Util.getLogger();
        this.proxy = Util.getHttpProxy();
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.timeout);
        HttpConnectionParams.setSoTimeout(basicHttpParams, this.timeout);
        this.client = new DefaultHttpClient(basicHttpParams);
        this.logger.i("proxy==" + (this.proxy == null ? "null" : this.proxy.getHostName()));
        if (this.proxy != null) {
            this.client.getParams().setParameter("http.route.default-proxy", this.proxy);
        }
        if (this.proxy != null && this.proxy.getHostName().equals("10.0.0.200")) {
            this.client.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ctwap@mycdma.cn", "vnet.mobi"));
        }
        java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(Level.FINEST);
        java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
        this.client.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                long keepAliveDuration = HttpConnectClient.super.getKeepAliveDuration(httpResponse, httpContext);
                if (keepAliveDuration == -1) {
                    return 20000;
                }
                return keepAliveDuration;
            }
        });
        this.addr = str;
        this.httpParamsMap = map;
    }

    public void shutdown() {
        if (this.client != null) {
            this.client.getConnectionManager().shutdown();
            this.client = null;
            this.addr = null;
            this.httpParamsMap = null;
            this.proxy = null;
        }
    }

    private String getHttpParams() {
        StringBuilder sb = new StringBuilder();
        if (!(this.httpParamsMap == null || this.httpParamsMap.size() == 0)) {
            int i = 0;
            for (Entry entry : this.httpParamsMap.entrySet()) {
                int i2 = i + 1;
                sb.append(i == 0 ? "?" : "&");
                sb.append((String) entry.getKey());
                sb.append("=");
                sb.append((String) entry.getValue());
                i = i2;
            }
        }
        return sb.toString();
    }

    public String getHttpFullUrl(String str) {
        return this.addr + str + getHttpParams();
    }

    public HttpHost getProxy() {
        return this.proxy;
    }

    public void setProxy(HttpHost httpHost) {
        this.proxy = httpHost;
    }

    public DefaultHttpClient getClient() {
        return this.client;
    }

    public void setClient(DefaultHttpClient defaultHttpClient) {
        this.client = defaultHttpClient;
    }

    public Map<String, String> getHttpParamsMap() {
        return this.httpParamsMap;
    }

    public void setHttpParamsMap(Map<String, String> map) {
        this.httpParamsMap = map;
    }
}
