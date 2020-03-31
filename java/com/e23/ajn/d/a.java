package com.e23.ajn.d;

import android.content.Context;
import android.widget.Toast;
import com.e23.ajn.c.b;
import com.e23.ajn.model.DataResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;

/* compiled from: AddScore */
public class a {
    public static void a(String str, final Context context) {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=addJiFen")).params(b.a(null)).addParams("act", str).build().execute(new com.e23.ajn.c.a<DataResponse>() {
            public void onError(Call call, Exception exc, int i) {
                exc.printStackTrace();
            }

            /* renamed from: a */
            public void onResponse(DataResponse dataResponse, int i) {
                if (dataResponse != null && dataResponse.getCode() == 200) {
                    Toast.makeText(context, "+" + dataResponse.getData() + "分", 0).show();
                }
            }
        });
    }
}
