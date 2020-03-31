package com.zhy.http.okhttp.log;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "OkHttpUtils";
    private boolean showResponse;
    private String tag;

    public LoggerInterceptor(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = TAG;
        }
        this.showResponse = z;
        this.tag = str;
    }

    public LoggerInterceptor(String str) {
        this(str, false);
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        return logForResponse(chain.proceed(request));
    }

    private Response logForResponse(Response response) {
        try {
            Log.e(this.tag, "========response'log=======");
            Response build = response.newBuilder().build();
            Log.e(this.tag, "url : " + build.request().url());
            Log.e(this.tag, "code : " + build.code());
            Log.e(this.tag, "protocol : " + build.protocol());
            if (!TextUtils.isEmpty(build.message())) {
                Log.e(this.tag, "message : " + build.message());
            }
            if (this.showResponse) {
                ResponseBody body = build.body();
                if (body != null) {
                    MediaType contentType = body.contentType();
                    if (contentType != null) {
                        Log.e(this.tag, "responseBody's contentType : " + contentType.toString());
                        if (isText(contentType)) {
                            String string = body.string();
                            Log.e(this.tag, "responseBody's content : " + string);
                            return response.newBuilder().body(ResponseBody.create(contentType, string)).build();
                        }
                        Log.e(this.tag, "responseBody's content :  maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            Log.e(this.tag, "========response'log=======end");
            return response;
        } catch (Exception e) {
            return response;
        }
    }

    private void logForRequest(Request request) {
        try {
            String httpUrl = request.url().toString();
            Headers headers = request.headers();
            Log.e(this.tag, "========request'log=======");
            Log.e(this.tag, "method : " + request.method());
            Log.e(this.tag, "url : " + httpUrl);
            if (headers != null && headers.size() > 0) {
                Log.e(this.tag, "headers : " + headers.toString());
            }
            RequestBody body = request.body();
            if (body != null) {
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    Log.e(this.tag, "requestBody's contentType : " + contentType.toString());
                    if (isText(contentType)) {
                        Log.e(this.tag, "requestBody's content : " + bodyToString(request));
                    } else {
                        Log.e(this.tag, "requestBody's content :  maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            Log.e(this.tag, "========request'log=======end");
        } catch (Exception e) {
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() == null || (!mediaType.subtype().equals("json") && !mediaType.subtype().equals("xml") && !mediaType.subtype().equals("html") && !mediaType.subtype().equals("webviewhtml"))) {
            return false;
        }
        return true;
    }

    private String bodyToString(Request request) {
        try {
            Request build = request.newBuilder().build();
            Buffer buffer = new Buffer();
            build.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            return "something error when show requestBody.";
        }
    }
}
