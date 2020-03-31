package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.ChildCatBean;
import java.util.List;

public class InteractionCatItemAdapter extends BaseQuickAdapter<ChildCatBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c = ((int) (((double) this.c) * 0.8d));

    public InteractionCatItemAdapter(Context context, List<ChildCatBean> list) {
        super(R.layout.f183newsimglist, list);
        this.a = context;
        this.c = (q.b((Activity) context) - context.getResources().getDimensionPixelSize(R.dimen.dp_96)) / 5;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, ChildCatBean childCatBean) {
        baseViewHolder.setText((int) R.id.cat_item_title, (CharSequence) childCatBean.getCname());
        if (baseViewHolder.getLayoutPosition() == 2) {
            baseViewHolder.setTextColor(R.id.cat_item_title, Color.parseColor("#1e519c"));
        } else {
            baseViewHolder.setTextColor(R.id.cat_item_title, Color.parseColor("#666666"));
        }
        i.b(this.a).a(childCatBean.getCatimg()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.cat_item_img));
        baseViewHolder.getView(R.id.cat_item_img).setLayoutParams(new LayoutParams(this.c, this.c));
        this.b = q.b((Activity) this.a) / getData().size();
        baseViewHolder.getView(R.id.card_view).setLayoutParams(new LayoutParams(this.b, -2));
    }
}
