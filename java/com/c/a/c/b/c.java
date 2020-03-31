package com.c.a.c.b;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;

/* compiled from: RetryHandler */
public class c implements HttpRequestRetryHandler {
    private static HashSet<Class<?>> a = new HashSet<>();
    private static HashSet<Class<?>> b = new HashSet<>();
    private final int c;

    static {
        a.add(NoHttpResponseException.class);
        a.add(UnknownHostException.class);
        a.add(SocketException.class);
        b.add(InterruptedIOException.class);
        b.add(SSLHandshakeException.class);
    }

    public c(int i) {
        this.c = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean retryRequest(java.io.IOException r6, int r7, org.apache.http.protocol.HttpContext r8) {
        /*
            r5 = this;
            r2 = 1
            r1 = 0
            if (r6 == 0) goto L_0x0006
            if (r8 != 0) goto L_0x0007
        L_0x0006:
            return r1
        L_0x0007:
            java.lang.String r0 = "http.request_sent"
            java.lang.Object r0 = r8.getAttribute(r0)
            if (r0 != 0) goto L_0x0037
            r0 = r1
        L_0x0010:
            int r3 = r5.c
            if (r7 <= r3) goto L_0x003e
            r2 = r1
        L_0x0015:
            if (r2 == 0) goto L_0x0079
            java.lang.String r0 = "http.request"
            java.lang.Object r0 = r8.getAttribute(r0)     // Catch:{ Throwable -> 0x0072 }
            if (r0 == 0) goto L_0x006c
            boolean r3 = r0 instanceof org.apache.http.client.methods.HttpRequestBase     // Catch:{ Throwable -> 0x0072 }
            if (r3 == 0) goto L_0x005b
            org.apache.http.client.methods.HttpRequestBase r0 = (org.apache.http.client.methods.HttpRequestBase) r0     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r2 = "GET"
            java.lang.String r0 = r0.getMethod()     // Catch:{ Throwable -> 0x0072 }
            boolean r1 = r2.equals(r0)     // Catch:{ Throwable -> 0x0072 }
        L_0x002f:
            if (r1 == 0) goto L_0x0006
            r2 = 500(0x1f4, double:2.47E-321)
            android.os.SystemClock.sleep(r2)
            goto L_0x0006
        L_0x0037:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L_0x0010
        L_0x003e:
            java.util.HashSet<java.lang.Class<?>> r3 = b
            java.lang.Class r4 = r6.getClass()
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x004c
            r2 = r1
            goto L_0x0015
        L_0x004c:
            java.util.HashSet<java.lang.Class<?>> r3 = a
            java.lang.Class r4 = r6.getClass()
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0015
            if (r0 != 0) goto L_0x0015
            goto L_0x0015
        L_0x005b:
            boolean r3 = r0 instanceof org.apache.http.impl.client.RequestWrapper     // Catch:{ Throwable -> 0x0072 }
            if (r3 == 0) goto L_0x0079
            org.apache.http.impl.client.RequestWrapper r0 = (org.apache.http.impl.client.RequestWrapper) r0     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r2 = "GET"
            java.lang.String r0 = r0.getMethod()     // Catch:{ Throwable -> 0x0072 }
            boolean r1 = r2.equals(r0)     // Catch:{ Throwable -> 0x0072 }
            goto L_0x002f
        L_0x006c:
            java.lang.String r0 = "retry error, curr request is null"
            com.c.a.e.c.b(r0)     // Catch:{ Throwable -> 0x0072 }
            goto L_0x002f
        L_0x0072:
            r0 = move-exception
            java.lang.String r2 = "retry error"
            com.c.a.e.c.a(r2, r0)
            goto L_0x002f
        L_0x0079:
            r1 = r2
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.a.c.b.c.retryRequest(java.io.IOException, int, org.apache.http.protocol.HttpContext):boolean");
    }
}
