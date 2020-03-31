package com.c.a.c.b;

import com.c.a.c.a.e;
import com.c.a.c.b.a.d;
import com.c.a.c.c;
import com.zhy.http.okhttp.OkHttpUtils.METHOD;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.CloneUtils;

/* compiled from: HttpRequest */
public class b extends HttpRequestBase implements HttpEntityEnclosingRequest {
    private HttpEntity a;
    private a b;
    private com.c.a.c.b.c.a c;
    private Charset d;

    /* compiled from: HttpRequest */
    public enum a {
        GET("GET"),
        POST("POST"),
        PUT(METHOD.PUT),
        HEAD(METHOD.HEAD),
        MOVE("MOVE"),
        COPY("COPY"),
        DELETE(METHOD.DELETE),
        OPTIONS("OPTIONS"),
        TRACE("TRACE"),
        CONNECT("CONNECT");
        
        private final String k;

        private a(String str) {
            this.k = str;
        }

        public String toString() {
            return this.k;
        }
    }

    public b(a aVar, String str) {
        this.b = aVar;
        a(str);
    }

    public b a(List<NameValuePair> list) {
        if (list != null) {
            for (NameValuePair nameValuePair : list) {
                this.c.a(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        return this;
    }

    public void a(c cVar, e eVar) {
        if (cVar != null) {
            if (this.d == null) {
                this.d = Charset.forName(cVar.b());
            }
            List<com.c.a.c.c.a> e = cVar.e();
            if (e != null) {
                for (com.c.a.c.c.a aVar : e) {
                    if (aVar.a) {
                        setHeader(aVar.b);
                    } else {
                        addHeader(aVar.b);
                    }
                }
            }
            a(cVar.d());
            HttpEntity c2 = cVar.c();
            if (c2 != null) {
                if (c2 instanceof d) {
                    ((d) c2).a(eVar);
                }
                setEntity(c2);
            }
        }
    }

    public URI getURI() {
        try {
            if (this.d == null) {
                this.d = com.c.a.e.d.a((HttpRequestBase) this);
            }
            if (this.d == null) {
                this.d = Charset.forName("UTF-8");
            }
            return this.c.a(this.d);
        } catch (URISyntaxException e) {
            com.c.a.e.c.a(e.getMessage(), e);
            return null;
        }
    }

    public void setURI(URI uri) {
        this.c = new com.c.a.c.b.c.a(uri);
    }

    public void a(String str) {
        this.c = new com.c.a.c.b.c.a(str);
    }

    public String getMethod() {
        return this.b.toString();
    }

    public HttpEntity getEntity() {
        return this.a;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.a = httpEntity;
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader("Expect");
        return firstHeader != null && "100-Continue".equalsIgnoreCase(firstHeader.getValue());
    }

    public Object clone() throws CloneNotSupportedException {
        b bVar = (b) b.super.clone();
        if (this.a != null) {
            bVar.a = (HttpEntity) CloneUtils.clone(this.a);
        }
        return bVar;
    }
}
