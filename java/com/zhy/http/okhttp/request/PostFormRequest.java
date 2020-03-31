package com.zhy.http.okhttp.request;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder.FileInput;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.CountingRequestBody.Listener;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import net.sf.json.util.JSONUtils;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostFormRequest extends OkHttpRequest {
    private List<FileInput> files;

    public PostFormRequest(String str, Object obj, Map<String, String> map, Map<String, String> map2, List<FileInput> list, int i) {
        super(str, obj, map, map2, i);
        this.files = list;
    }

    /* access modifiers changed from: protected */
    public RequestBody buildRequestBody() {
        if (this.files == null || this.files.isEmpty()) {
            Builder builder = new Builder();
            addParams(builder);
            return builder.build();
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        addParams(type);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.files.size()) {
                return type.build();
            }
            FileInput fileInput = (FileInput) this.files.get(i2);
            type.addFormDataPart(fileInput.key, fileInput.filename, RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file));
            i = i2 + 1;
        }
    }

    /* access modifiers changed from: protected */
    public RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return callback == null ? requestBody : new CountingRequestBody(requestBody, new Listener() {
            public void onRequestProgress(long j, long j2) {
                final long j3 = j;
                final long j4 = j2;
                OkHttpUtils.getInstance().getDelivery().execute(new Runnable() {
                    public void run() {
                        callback.inProgress((((float) j3) * 1.0f) / ((float) j4), j4, PostFormRequest.this.id);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public Request buildRequest(RequestBody requestBody) {
        return this.builder.post(requestBody).build();
    }

    private String guessMimeType(String str) {
        String str2;
        try {
            str2 = URLConnection.getFileNameMap().getContentTypeFor(URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str2 = null;
        }
        if (str2 == null) {
            return "application/octet-stream";
        }
        return str2;
    }

    private void addParams(MultipartBody.Builder builder) {
        if (this.params != null && !this.params.isEmpty()) {
            for (String str : this.params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + str + JSONUtils.DOUBLE_QUOTE), RequestBody.create((MediaType) null, (String) this.params.get(str)));
            }
        }
    }

    private void addParams(Builder builder) {
        if (this.params != null) {
            for (String str : this.params.keySet()) {
                builder.add(str, (String) this.params.get(str));
            }
        }
    }
}
