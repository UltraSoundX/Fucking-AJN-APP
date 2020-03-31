package com.zhy.http.okhttp.builder;

import android.net.Uri;
import android.net.Uri.Builder;
import com.zhy.http.okhttp.request.GetRequest;
import com.zhy.http.okhttp.request.RequestCall;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HasParamsable {
    public RequestCall build() {
        if (this.params != null) {
            this.url = appendParams(this.url, this.params);
        }
        return new GetRequest(this.url, this.tag, this.params, this.headers, this.id).build();
    }

    /* access modifiers changed from: protected */
    public String appendParams(String str, Map<String, String> map) {
        if (str == null || map == null || map.isEmpty()) {
            return str;
        }
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (String str2 : map.keySet()) {
            buildUpon.appendQueryParameter(str2, (String) map.get(str2));
        }
        return buildUpon.build().toString();
    }

    public GetBuilder params(Map<String, String> map) {
        this.params = map;
        return this;
    }

    public GetBuilder addParams(String str, String str2) {
        if (this.params == null) {
            this.params = new LinkedHashMap();
        }
        this.params.put(str, str2);
        return this;
    }
}
