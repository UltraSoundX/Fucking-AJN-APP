package com.zhy.http.okhttp.request;

import java.util.Map;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetRequest extends OkHttpRequest {
    public GetRequest(String str, Object obj, Map<String, String> map, Map<String, String> map2, int i) {
        super(str, obj, map, map2, i);
    }

    /* access modifiers changed from: protected */
    public RequestBody buildRequestBody() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Request buildRequest(RequestBody requestBody) {
        return this.builder.get().build();
    }
}
