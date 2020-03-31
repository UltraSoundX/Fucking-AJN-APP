package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.request.OtherRequest;
import com.zhy.http.okhttp.request.RequestCall;
import okhttp3.RequestBody;

public class OtherRequestBuilder extends OkHttpRequestBuilder<OtherRequestBuilder> {
    private String content;
    private String method;
    private RequestBody requestBody;

    public OtherRequestBuilder(String str) {
        this.method = str;
    }

    public RequestCall build() {
        return new OtherRequest(this.requestBody, this.content, this.method, this.url, this.tag, this.params, this.headers, this.id).build();
    }

    public OtherRequestBuilder requestBody(RequestBody requestBody2) {
        this.requestBody = requestBody2;
        return this;
    }

    public OtherRequestBuilder requestBody(String str) {
        this.content = str;
        return this;
    }
}
