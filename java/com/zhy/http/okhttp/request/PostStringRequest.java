package com.zhy.http.okhttp.request;

import com.zhy.http.okhttp.utils.Exceptions;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostStringRequest extends OkHttpRequest {
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    private String content;
    private MediaType mediaType;

    public PostStringRequest(String str, Object obj, Map<String, String> map, Map<String, String> map2, String str2, MediaType mediaType2, int i) {
        super(str, obj, map, map2, i);
        this.content = str2;
        this.mediaType = mediaType2;
        if (this.content == null) {
            Exceptions.illegalArgument("the content can not be null !", new Object[0]);
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }
    }

    /* access modifiers changed from: protected */
    public RequestBody buildRequestBody() {
        return RequestBody.create(this.mediaType, this.content);
    }

    /* access modifiers changed from: protected */
    public Request buildRequest(RequestBody requestBody) {
        return this.builder.post(requestBody).build();
    }
}
