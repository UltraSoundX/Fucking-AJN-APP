package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import com.e23.ajn.model.DistrictBean;
import java.util.List;

public class DistrictAdapter extends BaseQuickAdapter<DistrictBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c;
    private int d;
    private int e;

    public DistrictAdapter(Context context, List<DistrictBean> list) {
        super(R.layout.f180nbgd_item, list);
        this.a = context;
        this.b = q.b((Activity) context);
        this.c = (this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_32)) / 4;
        this.d = (this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_80)) / 4;
        this.e = context.getResources().getDimensionPixelSize(R.dimen.dp_16) - (this.c - this.d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, DistrictBean districtBean) {
        LayoutParams layoutParams = new LayoutParams(this.d, this.d);
        if (baseViewHolder.getLayoutPosition() % 4 == 0) {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(R.dimen.dp_16), 0, 0);
            layoutParams.gravity = 3;
        } else if (baseViewHolder.getLayoutPosition() % 4 == 1) {
            layoutParams.setMargins(this.e, this.a.getResources().getDimensionPixelSize(R.dimen.dp_16), 0, 0);
            layoutParams.gravity = 3;
        } else if (baseViewHolder.getLayoutPosition() % 4 == 2) {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(R.dimen.dp_16), this.e, 0);
            layoutParams.gravity = 5;
        } else {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(R.dimen.dp_16), 0, 0);
            layoutParams.gravity = 5;
        }
        baseViewHolder.getView(R.id.district_item_img).setLayoutParams(layoutParams);
        i.b(this.a).a(districtBean.getCatimgn()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.district_item_img));
    }
}
