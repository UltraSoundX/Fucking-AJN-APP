package com.e23.ajn.d;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.e23.ajn.c.b;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.Result;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: CommentZanUtil */
public class g {

    /* compiled from: CommentZanUtil */
    public interface a {
        void a(int i);
    }

    public void a(final Context context, final int i, CommentBean commentBean, final a aVar) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=comment&a=commentZan_pub")).params(b.a(null)).addParams(Config.FEED_LIST_ITEM_CUSTOM_ID, commentBean.getId()).addParams("posterid", commentBean.getUserid()).addParams("postername", commentBean.getUsername()).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
            public void onError(Call call, Exception exc, int i) {
                exc.printStackTrace();
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
                if (result != null && result.getCode() == 200 && aVar != null) {
                    aVar.a(i);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(context);
            }
        });
    }
}
