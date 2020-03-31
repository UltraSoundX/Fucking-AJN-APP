package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.ChildCatBean;
import java.util.List;

public class CatItemAdapter extends BaseQuickAdapter<ChildCatBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c = ((int) (((double) this.c) * 0.8d));

    public CatItemAdapter(Context context, List<ChildCatBean> list) {
        super(R.layout.f173mysixin, list);
        this.a = context;
        this.c = (q.b((Activity) context) - context.getResources().getDimensionPixelSize(R.dimen.dp_96)) / 5;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, ChildCatBean childCatBean) {
        baseViewHolder.setText((int) R.id.cat_item_title, (CharSequence) childCatBean.getCname());
        i.b(this.a).a(childCatBean.getCatimg()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.cat_item_img));
        LayoutParams layoutParams = new LayoutParams(this.c, this.c);
        layoutParams.addRule(14);
        baseViewHolder.getView(R.id.cat_item_img).setLayoutParams(layoutParams);
        this.b = q.b((Activity) this.a) / getData().size();
        baseViewHolder.getView(R.id.card_view).setLayoutParams(new LayoutParams(this.b, -2));
    }
}
