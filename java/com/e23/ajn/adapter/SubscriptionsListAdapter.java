package com.e23.ajn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.c.a;
import com.e23.ajn.d.p;
import com.e23.ajn.model.Result;
import com.e23.ajn.model.SubscriptionsResponse.DyBean;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;

public class SubscriptionsListAdapter extends BaseQuickAdapter<DyBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public Context a;

    public SubscriptionsListAdapter(Context context, List<DyBean> list) {
        super(R.layout.f113notification_template_custom_big, list);
        this.a = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(final BaseViewHolder baseViewHolder, final DyBean dyBean) {
        i.b(this.a).a(dyBean.getHeadimg()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.subscription_head_img));
        baseViewHolder.setText((int) R.id.subscription_tv_name, (CharSequence) dyBean.getUsername());
        baseViewHolder.setText((int) R.id.subscription_tv_des, (CharSequence) dyBean.getIntro());
        if (dyBean.getDingyue().equals("1")) {
            baseViewHolder.setText((int) R.id.subscription_tv_act, (CharSequence) "已订阅");
            baseViewHolder.setBackgroundRes(R.id.subscription_tv_act, R.drawable.subscription_list_act_ed_bg);
        } else {
            baseViewHolder.setText((int) R.id.subscription_tv_act, (CharSequence) "+ 订阅");
            baseViewHolder.setBackgroundRes(R.id.subscription_tv_act, R.drawable.subscription_list_act_default_bg);
        }
        baseViewHolder.getView(R.id.subscription_tv_act).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!p.a("is_logined", false)) {
                    Intent intent = new Intent(SubscriptionsListAdapter.this.a, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, 21);
                    SubscriptionsListAdapter.this.a.startActivity(intent);
                    return;
                }
                SubscriptionsListAdapter.this.a(dyBean.getUserid(), dyBean.getDingyue().equals("1") ? "del" : "add", baseViewHolder.getLayoutPosition());
            }
        });
        baseViewHolder.getView(R.id.card_view).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SubscriptionsListAdapter.this.a, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 10);
                intent.putExtra(SwipeBackCommonActivity.URL, dyBean.getUsername());
                SubscriptionsListAdapter.this.a.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, final String str2, final int i) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=subscribe&a=modDingYue")).params(com.e23.ajn.c.b.a(null)).addParams("act", str2).addParams("dyid", str).tag(this)).build().execute(new a<Result>() {
            public void onError(Call call, Exception exc, int i) {
                exc.printStackTrace();
            }

            /* renamed from: a */
            public void onResponse(Result result, int i) {
                if (result != null && result.getCode() == 200) {
                    if (str2.equals("add")) {
                        ((DyBean) SubscriptionsListAdapter.this.getData().get(i)).setDingyue("1");
                    } else {
                        ((DyBean) SubscriptionsListAdapter.this.getData().get(i)).setDingyue("0");
                    }
                    SubscriptionsListAdapter.this.notifyDataSetChanged();
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(SubscriptionsListAdapter.this.a);
            }
        });
    }
}
