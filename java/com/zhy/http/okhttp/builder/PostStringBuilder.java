package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.request.PostStringRequest;
import com.zhy.http.okhttp.request.RequestCall;
import okhttp3.MediaType;

public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder> {
    private String content;
    private MediaType mediaType;

    public PostStringBuilder content(String str) {
        this.content = str;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType2) {
        this.mediaType = mediaType2;
        return this;
    }

    public RequestCall build() {
        return new PostStringRequest(this.url, this.tag, this.params, this.headers, this.content, this.mediaType, this.id).build();
    }
}
