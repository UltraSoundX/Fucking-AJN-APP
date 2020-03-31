package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.request.PostFileRequest;
import com.zhy.http.okhttp.request.RequestCall;
import java.io.File;
import okhttp3.MediaType;

public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder> {
    private File file;
    private MediaType mediaType;

    public OkHttpRequestBuilder file(File file2) {
        this.file = file2;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType2) {
        this.mediaType = mediaType2;
        return this;
    }

    public RequestCall build() {
        return new PostFileRequest(this.url, this.tag, this.params, this.headers, this.file, this.mediaType, this.id).build();
    }
}
