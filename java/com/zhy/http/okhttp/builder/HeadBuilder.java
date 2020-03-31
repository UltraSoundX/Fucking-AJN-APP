package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.OkHttpUtils.METHOD;
import com.zhy.http.okhttp.request.OtherRequest;
import com.zhy.http.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder {
    public RequestCall build() {
        return new OtherRequest(null, null, METHOD.HEAD, this.url, this.tag, this.params, this.headers, this.id).build();
    }
}
