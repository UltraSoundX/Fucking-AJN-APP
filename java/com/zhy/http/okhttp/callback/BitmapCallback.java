package com.zhy.http.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import okhttp3.Response;

public abstract class BitmapCallback extends Callback<Bitmap> {
    public Bitmap parseNetworkResponse(Response response, int i) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }
}
