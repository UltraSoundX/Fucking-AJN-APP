package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.PaperBmArticleListResponse.DataBean;
import java.util.List;

public class PaperBmArticleListAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c;

    public PaperBmArticleListAdapter(Context context, List<DataBean> list) {
        super(R.layout.f110notification_template_big_media_custom, list);
        this.a = context;
        this.b = (int) (((double) q.b((Activity) context)) * 0.26d);
        this.c = (int) (((double) q.b((Activity) context)) * 0.38d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, DataBean dataBean) {
        baseViewHolder.setText(2131820590, (CharSequence) dataBean.getTitle());
    }
}
