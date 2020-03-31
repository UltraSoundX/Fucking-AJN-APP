package com.zhy.http.okhttp.request;

import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.utils.Exceptions;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public abstract class OkHttpRequest {
    protected Builder builder = new Builder();
    protected Map<String, String> headers;
    protected int id;
    protected Map<String, String> params;
    protected Object tag;
    protected String url;

    /* access modifiers changed from: protected */
    public abstract Request buildRequest(RequestBody requestBody);

    /* access modifiers changed from: protected */
    public abstract RequestBody buildRequestBody();

    protected OkHttpRequest(String str, Object obj, Map<String, String> map, Map<String, String> map2, int i) {
        this.url = str;
        this.tag = obj;
        this.params = map;
        this.headers = map2;
        this.id = i;
        if (str == null) {
            Exceptions.illegalArgument("url can not be null.", new Object[0]);
        }
        initBuilder();
    }

    private void initBuilder() {
        this.builder.url(this.url).tag(this.tag);
        appendHeaders();
    }

    /* access modifiers changed from: protected */
    public RequestBody wrapRequestBody(RequestBody requestBody, Callback callback) {
        return requestBody;
    }

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback) {
        return buildRequest(wrapRequestBody(buildRequestBody(), callback));
    }

    /* access modifiers changed from: protected */
    public void appendHeaders() {
        Headers.Builder builder2 = new Headers.Builder();
        if (this.headers != null && !this.headers.isEmpty()) {
            for (String str : this.headers.keySet()) {
                builder2.add(str, (String) this.headers.get(str));
            }
            this.builder.headers(builder2.build());
        }
    }

    public int getId() {
        return this.id;
    }
}
