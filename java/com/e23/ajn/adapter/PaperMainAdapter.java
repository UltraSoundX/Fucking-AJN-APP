package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.PaperMainResponse.DataBean;
import java.util.List;

public class PaperMainAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c;

    public PaperMainAdapter(Context context, List<DataBean> list) {
        super(R.layout.f111notification_template_big_media_narrow, list);
        this.a = context;
        this.b = (int) (((double) q.b((Activity) context)) * 0.26d);
        this.c = (int) (((double) q.b((Activity) context)) * 0.38d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, DataBean dataBean) {
        LayoutParams layoutParams = baseViewHolder.getView(R.id.bzimg).getLayoutParams();
        layoutParams.width = this.b;
        layoutParams.height = this.c;
        baseViewHolder.getView(R.id.bzimg).setLayoutParams(layoutParams);
        i.b(this.mContext).a(dataBean.getBm()).h().b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.bzimg));
        baseViewHolder.setText((int) R.id.papername, (CharSequence) dataBean.getPapername());
        baseViewHolder.setText((int) R.id.paperdate, (CharSequence) dataBean.getPaperdate());
        baseViewHolder.setText(2131820590, (CharSequence) dataBean.getTitle());
    }
}
