package com.zhy.http.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback<T> {
    public static Callback CALLBACK_DEFAULT = new Callback() {
        public Object parseNetworkResponse(Response response, int i) throws Exception {
            return null;
        }

        public void onError(Call call, Exception exc, int i) {
        }

        public void onResponse(Object obj, int i) {
        }
    };

    public abstract void onError(Call call, Exception exc, int i);

    public abstract void onResponse(T t, int i);

    public abstract T parseNetworkResponse(Response response, int i) throws Exception;

    public void onBefore(Request request, int i) {
    }

    public void onAfter(int i) {
    }

    public void inProgress(float f, long j, int i) {
    }

    public boolean validateReponse(Response response, int i) {
        return response.isSuccessful();
    }
}
