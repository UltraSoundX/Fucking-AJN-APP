package com.e23.ajn.d;

import android.content.Context;
import com.e23.ajn.c.a;
import com.e23.ajn.model.Result;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;

/* compiled from: AddVisitUtil */
public class b {
    public static void a(Context context, String str, String str2) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=contentc&a=addvisite")).params(com.e23.ajn.c.b.a(null)).addParams("newsid", str).addParams("catid", str2).tag(context)).build().execute(new a<Result>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
            }
        });
    }
}
