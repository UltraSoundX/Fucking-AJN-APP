package com.e23.ajn.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.u;
import com.e23.ajn.model.CollectionResponseModel.DataBean;

public class CollectionListAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {
    private Context a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, DataBean dataBean) {
        i.b(this.a).a(dataBean.getThumb()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.collection_item_img));
        baseViewHolder.setText((int) R.id.collection_item_title, (CharSequence) dataBean.getTitle());
        baseViewHolder.setText((int) R.id.collection_item_time, (CharSequence) u.b(dataBean.getCelltime()));
        if (baseViewHolder.getLayoutPosition() == 0) {
            baseViewHolder.getView(R.id.collection_item_line).setVisibility(8);
        } else {
            baseViewHolder.getView(R.id.collection_item_line).setVisibility(0);
        }
    }
}
