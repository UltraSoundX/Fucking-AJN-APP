package okhttp3;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor.Chain;
import okhttp3.Request.Builder;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Platform;
import okhttp3.internal.http.HttpEngine;
import okhttp3.internal.http.RequestException;
import okhttp3.internal.http.RouteException;
import okhttp3.internal.http.StreamAllocation;

final class RealCall implements Call {
    volatile boolean canceled;
    /* access modifiers changed from: private */
    public final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;

    class ApplicationInterceptorChain implements Chain {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;

        ApplicationInterceptorChain(int i, Request request2, boolean z) {
            this.index = i;
            this.request = request2;
            this.forWebSocket = z;
        }

        public Connection connection() {
            return null;
        }

        public Request request() {
            return this.request;
        }

        public Response proceed(Request request2) throws IOException {
            if (this.index >= RealCall.this.client.interceptors().size()) {
                return RealCall.this.getResponse(request2, this.forWebSocket);
            }
            Interceptor interceptor = (Interceptor) RealCall.this.client.interceptors().get(this.index);
            Response intercept = interceptor.intercept(new ApplicationInterceptorChain(this.index + 1, request2, this.forWebSocket));
            if (intercept != null) {
                return intercept;
            }
            throw new NullPointerException("application interceptor " + interceptor + " returned null");
        }
    }

    final class AsyncCall extends NamedRunnable {
        private final boolean forWebSocket;
        private final Callback responseCallback;

        private AsyncCall(Callback callback, boolean z) {
            super("OkHttp %s", RealCall.this.redactedUrl().toString());
            this.responseCallback = callback;
            this.forWebSocket = z;
        }

        /* access modifiers changed from: 0000 */
        public String host() {
            return RealCall.this.originalRequest.url().host();
        }

        /* access modifiers changed from: 0000 */
        public Request request() {
            return RealCall.this.originalRequest;
        }

        /* access modifiers changed from: 0000 */
        public Object tag() {
            return RealCall.this.originalRequest.tag();
        }

        /* access modifiers changed from: 0000 */
        public void cancel() {
            RealCall.this.cancel();
        }

        /* access modifiers changed from: 0000 */
        public RealCall get() {
            return RealCall.this;
        }

        /* access modifiers changed from: protected */
        public void execute() {
            boolean z = true;
            boolean z2 = false;
            try {
                Response access$100 = RealCall.this.getResponseWithInterceptorChain(this.forWebSocket);
                if (RealCall.this.canceled) {
                    try {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } catch (IOException e) {
                        e = e;
                    }
                } else {
                    this.responseCallback.onResponse(RealCall.this, access$100);
                }
                RealCall.this.client.dispatcher().finished(this);
            } catch (IOException e2) {
                e = e2;
                z = z2;
                if (z) {
                    try {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } catch (Throwable th) {
                        RealCall.this.client.dispatcher().finished(this);
                        throw th;
                    }
                } else {
                    this.responseCallback.onFailure(RealCall.this, e);
                }
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }

    protected RealCall(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient;
        this.originalRequest = request;
    }

    public Request request() {
        return this.originalRequest;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        try {
            this.client.dispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.dispatcher().finished((Call) this);
        }
    }

    /* access modifiers changed from: 0000 */
    public Object tag() {
        return this.originalRequest.tag();
    }

    public void enqueue(Callback callback) {
        enqueue(callback, false);
    }

    /* access modifiers changed from: 0000 */
    public void enqueue(Callback callback, boolean z) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.dispatcher().enqueue(new AsyncCall(callback, z));
    }

    public void cancel() {
        this.canceled = true;
        if (this.engine != null) {
            this.engine.cancel();
        }
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    /* access modifiers changed from: private */
    public String toLoggableString() {
        return (this.canceled ? "canceled call" : NotificationCompat.CATEGORY_CALL) + " to " + redactedUrl();
    }

    /* access modifiers changed from: 0000 */
    public HttpUrl redactedUrl() {
        return this.originalRequest.url().resolve("/...");
    }

    /* access modifiers changed from: private */
    public Response getResponseWithInterceptorChain(boolean z) throws IOException {
        return new ApplicationInterceptorChain(0, this.originalRequest, z).proceed(this.originalRequest);
    }

    /* access modifiers changed from: 0000 */
    public Response getResponse(Request request, boolean z) throws IOException {
        Request request2;
        boolean z2;
        RequestBody body = request.body();
        if (body != null) {
            Builder newBuilder = request.newBuilder();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                newBuilder.header("Content-Type", contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                newBuilder.header("Content-Length", Long.toString(contentLength));
                newBuilder.removeHeader("Transfer-Encoding");
            } else {
                newBuilder.header("Transfer-Encoding", "chunked");
                newBuilder.removeHeader("Content-Length");
            }
            request2 = newBuilder.build();
        } else {
            request2 = request;
        }
        this.engine = new HttpEngine(this.client, request2, false, false, z, null, null, null);
        int i = 0;
        while (!this.canceled) {
            try {
                this.engine.sendRequest();
                this.engine.readResponse();
                Response response = this.engine.getResponse();
                Request followUpRequest = this.engine.followUpRequest();
                if (followUpRequest == null) {
                    if (!z) {
                        this.engine.releaseStreamAllocation();
                    }
                    return response;
                }
                StreamAllocation close = this.engine.close();
                int i2 = i + 1;
                if (i2 > 20) {
                    close.release();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                if (!this.engine.sameConnection(followUpRequest.url())) {
                    close.release();
                    close = null;
                } else if (close.stream() != null) {
                    throw new IllegalStateException("Closing the body of " + response + " didn't close its backing stream. Bad interceptor?");
                }
                this.engine = new HttpEngine(this.client, followUpRequest, false, false, z, close, null, response);
                i = i2;
            } catch (RequestException e) {
                throw e.getCause();
            } catch (RouteException e2) {
                HttpEngine recover = this.engine.recover(e2.getLastConnectException(), true, null);
                if (recover != null) {
                    z2 = false;
                    this.engine = recover;
                } else {
                    throw e2.getLastConnectException();
                }
            } catch (IOException e3) {
                HttpEngine recover2 = this.engine.recover(e3, false, null);
                if (recover2 != null) {
                    z2 = false;
                    this.engine = recover2;
                } else {
                    throw e3;
                }
            } catch (Throwable th) {
                th = th;
            }
        }
        this.engine.releaseStreamAllocation();
        throw new IOException("Canceled");
        if (z2) {
            this.engine.close().release();
        }
        throw th;
    }
}
