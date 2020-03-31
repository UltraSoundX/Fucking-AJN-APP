package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.d.q;
import com.e23.ajn.model.ThumbZu;
import java.util.List;

public class RecommendationItemAdapter extends BaseQuickAdapter<ThumbZu, BaseViewHolder> {
    /* access modifiers changed from: private */
    public Context a;
    private int b;
    private int c;

    public RecommendationItemAdapter(Context context, List<ThumbZu> list) {
        super(R.layout.f200wangqiliulan, list);
        this.a = context;
        this.c = ((q.b((Activity) context) - context.getResources().getDimensionPixelSize(R.dimen.dp_20)) / 2) - context.getResources().getDimensionPixelSize(R.dimen.dp_20);
        this.b = this.c - context.getResources().getDimensionPixelSize(2131492889);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, final ThumbZu thumbZu) {
        LayoutParams layoutParams = new LayoutParams(this.b, (this.b * 3) / 4);
        LayoutParams layoutParams2 = new LayoutParams(this.b, -2);
        layoutParams.gravity = 1;
        layoutParams2.gravity = 1;
        LayoutParams layoutParams3 = new LayoutParams(this.c, -2);
        baseViewHolder.setText((int) R.id.layout_recommend_item_pic_name, (CharSequence) thumbZu.getAlt());
        baseViewHolder.getView(R.id.layout_recommend_item_pic_layout).setLayoutParams(layoutParams3);
        baseViewHolder.getView(R.id.layout_recommend_item_pic_name).setLayoutParams(layoutParams2);
        baseViewHolder.getView(R.id.layout_recommend_item_pic).setLayoutParams(layoutParams);
        i.b(this.a).a(thumbZu.getUrl()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.layout_recommend_item_pic));
        baseViewHolder.getView(R.id.layout_recommend_item_pic_layout).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationItemAdapter.this.a, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 6);
                intent.putExtra(SwipeBackCommonActivity.TITLE, thumbZu.getAlt());
                intent.putExtra(SwipeBackCommonActivity.URL, thumbZu.getCatId());
                RecommendationItemAdapter.this.a.startActivity(intent);
            }
        });
    }
}
