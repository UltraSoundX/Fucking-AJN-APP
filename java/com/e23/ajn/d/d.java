package com.e23.ajn.d;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.e23.ajn.R;
import com.e23.ajn.app.App;
import com.e23.ajn.c.a;
import com.e23.ajn.model.CheckUpDateResponseModel;
import com.e23.ajn.model.UpdateModel;
import com.e23.ajn.views.k;
import com.e23.ajn.views.q;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: CheckUpDateUtil */
public class d {
    public static void a(final Context context, final boolean z, final boolean z2) {
        try {
            ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=check_version")).tag(context)).addParams(Config.INPUT_DEF_VERSION, App.getInstance().getCurrentVersionCode() + "").build().execute(new a<CheckUpDateResponseModel>() {
                public void onBefore(Request request, int i) {
                    super.onBefore(request, i);
                    if (z) {
                        k.a(context);
                    }
                }

                public void onError(Call call, Exception exc, int i) {
                    if (z) {
                        k.a(context, "error" + exc.getMessage());
                    }
                }

                /* renamed from: a */
                public void onResponse(CheckUpDateResponseModel checkUpDateResponseModel, int i) {
                    if (checkUpDateResponseModel == null || checkUpDateResponseModel.getCode() != 200) {
                        if (z && checkUpDateResponseModel != null && !TextUtils.isEmpty(checkUpDateResponseModel.getMsg())) {
                            k.a(context, checkUpDateResponseModel.getMsg());
                        }
                        if (z2) {
                            w.a(context.getString(R.string.check_islastversion));
                            return;
                        }
                        return;
                    }
                    d.b(context, checkUpDateResponseModel.getData());
                }

                public void onAfter(int i) {
                    super.onAfter(i);
                    if (z) {
                        k.a();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, UpdateModel updateModel) {
        q qVar = new q(context, updateModel);
        qVar.setCancelable(false);
        qVar.setCanceledOnTouchOutside(false);
        qVar.show();
    }
}
