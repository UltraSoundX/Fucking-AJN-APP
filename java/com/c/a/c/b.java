package com.c.a.c;

import android.os.SystemClock;
import com.c.a.c.a.d;
import com.c.a.c.a.e;
import com.c.a.c.a.f;
import com.c.a.d.c;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* compiled from: HttpHandler */
public class b<T> extends c<Object, Object, Void> implements e {
    private static final a s = new a(null);
    private final AbstractHttpClient b;
    private final HttpContext c;
    private com.c.a.c.a.c d;
    private String e;
    private String f;
    private HttpRequestBase g;
    private boolean h = true;
    private d<T> i;
    private int j = 0;
    private String k = null;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private String o;
    private C0047b p = C0047b.WAITING;

    /* renamed from: q reason: collision with root package name */
    private long f394q = a.a();
    private long r;

    /* compiled from: HttpHandler */
    private static final class a implements RedirectHandler {
        private a() {
        }

        /* synthetic */ a(a aVar) {
            this();
        }

        public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
            return false;
        }

        public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
            return null;
        }
    }

    /* renamed from: com.c.a.c.b$b reason: collision with other inner class name */
    /* compiled from: HttpHandler */
    public enum C0047b {
        WAITING(0),
        STARTED(1),
        LOADING(2),
        FAILURE(3),
        CANCELLED(4),
        SUCCESS(5);
        
        private int g;

        private C0047b(int i) {
            this.g = 0;
            this.g = i;
        }
    }

    public void a(com.c.a.c.a.c cVar) {
        if (cVar != null) {
            this.d = cVar;
        }
    }

    public b(AbstractHttpClient abstractHttpClient, HttpContext httpContext, String str, d<T> dVar) {
        this.b = abstractHttpClient;
        this.c = httpContext;
        this.i = dVar;
        this.o = str;
        this.b.setRedirectHandler(s);
    }

    public void a(long j2) {
        this.f394q = j2;
    }

    private d<T> a(HttpRequestBase httpRequestBase) throws com.c.a.b.b {
        boolean retryRequest;
        IOException e2;
        long j2;
        HttpRequestRetryHandler httpRequestRetryHandler = this.b.getHttpRequestRetryHandler();
        do {
            if (this.m && this.l) {
                File file = new File(this.k);
                if (!file.isFile() || !file.exists()) {
                    j2 = 0;
                } else {
                    j2 = file.length();
                }
                if (j2 > 0) {
                    httpRequestBase.setHeader("RANGE", "bytes=" + j2 + "-");
                }
            }
            try {
                this.f = httpRequestBase.getMethod();
                if (com.c.a.a.a.b(this.f)) {
                    String a2 = com.c.a.a.a.a(this.e);
                    if (a2 != null) {
                        return new d(null, a2, true);
                    }
                }
                if (!c()) {
                    return a(this.b.execute(httpRequestBase, this.c));
                }
                return null;
            } catch (UnknownHostException e3) {
                e2 = e3;
                int i2 = this.j + 1;
                this.j = i2;
                retryRequest = httpRequestRetryHandler.retryRequest(e2, i2, this.c);
                continue;
            } catch (IOException e4) {
                e2 = e4;
                int i3 = this.j + 1;
                this.j = i3;
                retryRequest = httpRequestRetryHandler.retryRequest(e2, i3, this.c);
                continue;
            } catch (NullPointerException e5) {
                IOException iOException = new IOException(e5.getMessage());
                iOException.initCause(e5);
                int i4 = this.j + 1;
                this.j = i4;
                IOException iOException2 = iOException;
                retryRequest = httpRequestRetryHandler.retryRequest(iOException, i4, this.c);
                e2 = iOException2;
                continue;
            } catch (com.c.a.b.b e6) {
                throw e6;
            } catch (Throwable th) {
                IOException iOException3 = new IOException(th.getMessage());
                iOException3.initCause(th);
                int i5 = this.j + 1;
                this.j = i5;
                IOException iOException4 = iOException3;
                retryRequest = httpRequestRetryHandler.retryRequest(iOException3, i5, this.c);
                e2 = iOException4;
                continue;
            }
        } while (retryRequest);
        throw new com.c.a.b.b((Throwable) e2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void c(Object... objArr) {
        if (!(this.p == C0047b.CANCELLED || objArr == null || objArr.length == 0)) {
            if (objArr.length > 3) {
                this.k = String.valueOf(objArr[1]);
                this.l = this.k != null;
                this.m = objArr[2].booleanValue();
                this.n = objArr[3].booleanValue();
            }
            try {
                if (this.p != C0047b.CANCELLED) {
                    this.g = objArr[0];
                    this.e = this.g.getURI().toString();
                    if (this.i != null) {
                        this.i.a(this.e);
                    }
                    d((Progress[]) new Object[]{Integer.valueOf(1)});
                    this.r = SystemClock.uptimeMillis();
                    d a2 = a(this.g);
                    if (a2 != null) {
                        d((Progress[]) new Object[]{Integer.valueOf(4), a2});
                    }
                }
            } catch (com.c.a.b.b e2) {
                d((Progress[]) new Object[]{Integer.valueOf(3), e2, e2.getMessage()});
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void b(Object... objArr) {
        if (this.p != C0047b.CANCELLED && objArr != null && objArr.length != 0 && this.i != null) {
            switch (objArr[0].intValue()) {
                case 1:
                    this.p = C0047b.STARTED;
                    this.i.a();
                    return;
                case 2:
                    if (objArr.length == 3) {
                        this.p = C0047b.LOADING;
                        this.i.a(Long.valueOf(String.valueOf(objArr[1])).longValue(), Long.valueOf(String.valueOf(objArr[2])).longValue(), this.h);
                        return;
                    }
                    return;
                case 3:
                    if (objArr.length == 3) {
                        this.p = C0047b.FAILURE;
                        this.i.a(objArr[1], objArr[2]);
                        return;
                    }
                    return;
                case 4:
                    if (objArr.length == 2) {
                        this.p = C0047b.SUCCESS;
                        this.i.a(objArr[1]);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private d<T> a(HttpResponse httpResponse) throws com.c.a.b.b, IOException {
        Object obj;
        String str = null;
        if (httpResponse == null) {
            throw new com.c.a.b.b("response is null");
        } else if (c()) {
            return null;
        } else {
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode < 300) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    this.h = false;
                    if (this.l) {
                        this.m = this.m && com.c.a.e.d.a(httpResponse);
                        if (this.n) {
                            str = com.c.a.e.d.b(httpResponse);
                        }
                        obj = new com.c.a.c.a.b().a(entity, this, this.k, this.m, str);
                    } else {
                        obj = new f().a(entity, this, this.o);
                        if (com.c.a.a.a.b(this.f)) {
                            com.c.a.a.a.a(this.e, (String) obj, this.f394q);
                        }
                    }
                } else {
                    obj = null;
                }
                return new d<>(httpResponse, obj, false);
            } else if (statusCode == 301 || statusCode == 302) {
                if (this.d == null) {
                    this.d = new com.c.a.c.a.a();
                }
                HttpRequestBase a2 = this.d.a(httpResponse);
                if (a2 != null) {
                    return a(a2);
                }
                return null;
            } else if (statusCode == 416) {
                throw new com.c.a.b.b(statusCode, "maybe the file has downloaded completely");
            } else {
                throw new com.c.a.b.b(statusCode, statusLine.getReasonPhrase());
            }
        }
    }

    public boolean a(long j2, long j3, boolean z) {
        if (!(this.i == null || this.p == C0047b.CANCELLED)) {
            if (z) {
                d((Progress[]) new Object[]{Integer.valueOf(2), Long.valueOf(j2), Long.valueOf(j3)});
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (uptimeMillis - this.r >= ((long) this.i.b())) {
                    this.r = uptimeMillis;
                    d((Progress[]) new Object[]{Integer.valueOf(2), Long.valueOf(j2), Long.valueOf(j3)});
                }
            }
        }
        if (this.p != C0047b.CANCELLED) {
            return true;
        }
        return false;
    }
}
