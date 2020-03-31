package com.e23.ajn.d;

import android.content.Context;
import com.e23.ajn.c.b;
import com.e23.ajn.model.Result;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: NewsUtil */
public class o {
    public a a;

    /* compiled from: NewsUtil */
    public interface a {
        void a(boolean z, String str);
    }

    public void a(final Context context, String str, String str2, final String str3, final a aVar) {
        this.a = aVar;
        String str4 = "";
        if (str3.equals("addtocell")) {
            str4 = "http://appc.e23.cn/index.php?m=api&c=contentc&a=addtocell";
        }
        if (str3.equals("cancelcell")) {
            str4 = "http://appc.e23.cn/index.php?m=api&c=contentc&a=cancelcell";
        }
        if (!str4.equals("")) {
            ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url(str4)).params(b.a(null)).addParams("newsid", str).addParams("catid", str2).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
                public void onError(Call call, Exception exc, int i) {
                }

                /* renamed from: a */
                public void onResponse(Result result, int i) {
                    if (result != null && result.getCode() == 200) {
                        if (str3.equals("addtocell")) {
                            k.a(context, "收藏成功");
                            if (aVar != null) {
                                aVar.a(true, str3);
                                return;
                            }
                            return;
                        }
                        k.a(context, "取消收藏");
                        if (aVar != null) {
                            aVar.a(false, str3);
                        }
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

    public void b(final Context context, String str, String str2, final String str3, final a aVar) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=dingcai")).params(b.a(null)).addParams("newsid", str).addParams("catid", str2).addParams("action", str3).tag(this)).build().execute(new com.e23.ajn.c.a<Result>() {
            public void onError(Call call, Exception exc, int i) {
                exc.printStackTrace();
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
                if (result != null && result.getCode() == 200 && aVar != null) {
                    aVar.a(true, str3);
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
