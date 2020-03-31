package com.tencent.smtt.sdk;

import android.util.Pair;
import com.tencent.smtt.export.external.interfaces.UrlRequest;
import com.tencent.smtt.export.external.interfaces.UrlRequest.Builder;
import com.tencent.smtt.export.external.interfaces.UrlRequest.Callback;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class UrlRequestBuilderImpl extends Builder {
    private static final String a = UrlRequestBuilderImpl.class.getSimpleName();
    private final String b;
    private final Callback c;
    private final Executor d;
    private String e;
    private final ArrayList<Pair<String, String>> f = new ArrayList<>();
    private boolean g;
    private int h = 3;

    public UrlRequestBuilderImpl(String str, Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        } else if (callback == null) {
            throw new NullPointerException("Callback is required.");
        } else if (executor == null) {
            throw new NullPointerException("Executor is required.");
        } else {
            this.b = str;
            this.c = callback;
            this.d = executor;
        }
    }

    public Builder setHttpMethod(String str) {
        if (str == null) {
            throw new NullPointerException("Method is required.");
        }
        this.e = str;
        return this;
    }

    public UrlRequestBuilderImpl addHeader(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Invalid header name.");
        } else if (str2 == null) {
            throw new NullPointerException("Invalid header value.");
        } else {
            if (!"Accept-Encoding".equalsIgnoreCase(str)) {
                this.f.add(Pair.create(str, str2));
            }
            return this;
        }
    }

    public UrlRequestBuilderImpl disableCache() {
        this.g = true;
        return this;
    }

    public UrlRequestBuilderImpl setPriority(int i) {
        this.h = i;
        return this;
    }

    public UrlRequest build() throws NullPointerException {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            return null;
        }
        UrlRequest urlRequest = (UrlRequest) a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "UrlRequest_getX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class}, this.b, Integer.valueOf(this.h), this.c, this.d, Boolean.valueOf(this.g), this.e, this.f);
        if (urlRequest != null) {
            return urlRequest;
        }
        throw new NullPointerException("UrlRequest build fail");
    }
}
