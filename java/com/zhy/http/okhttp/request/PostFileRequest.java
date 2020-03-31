package com.zhy.http.okhttp.request;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.CountingRequestBody.Listener;
import com.zhy.http.okhttp.utils.Exceptions;
import java.io.File;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostFileRequest extends OkHttpRequest {
    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
    private File file;
    private MediaType mediaType;

    public PostFileRequest(String str, Object obj, Map<String, String> map, Map<String, String> map2, File file2, MediaType mediaType2, int i) {
        super(str, obj, map, map2, i);
        this.file = file2;
        this.mediaType = mediaType2;
        if (this.file == null) {
            Exceptions.illegalArgument("the file can not be null !", new Object[0]);
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_STREAM;
        }
    }

    /* access modifiers changed from: protected */
    public RequestBody buildRequestBody() {
        return RequestBody.create(this.mediaType, this.file);
    }

    /* access modifiers changed from: protected */
    public RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return callback == null ? requestBody : new CountingRequestBody(requestBody, new Listener() {
            public void onRequestProgress(long j, long j2) {
                final long j3 = j;
                final long j4 = j2;
                OkHttpUtils.getInstance().getDelivery().execute(new Runnable() {
                    public void run() {
                        callback.inProgress((((float) j3) * 1.0f) / ((float) j4), j4, PostFileRequest.this.id);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public Request buildRequest(RequestBody requestBody) {
        return this.builder.post(requestBody).build();
    }
}
