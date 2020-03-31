package com.e23.ajn.c;

import com.b.a.e;
import com.zhy.http.okhttp.callback.Callback;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Response;

/* compiled from: JsonCallBack */
public abstract class a<T> extends Callback<T> {
    public T parseNetworkResponse(Response response, int i) throws Exception {
        return new e().a(response.body().string(), a());
    }

    /* access modifiers changed from: 0000 */
    public Type a() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return type instanceof Class ? type : new com.b.a.c.a<T>() {
        }.b();
    }
}
