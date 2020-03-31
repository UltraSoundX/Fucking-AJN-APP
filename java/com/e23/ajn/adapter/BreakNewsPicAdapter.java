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
import com.e23.ajn.model.ThumbZu;
import java.util.ArrayList;
import java.util.List;

public class BreakNewsPicAdapter extends BaseQuickAdapter<ThumbZu, BaseViewHolder> {
    private Context a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private ArrayList<String> f = new ArrayList<>();

    public BreakNewsPicAdapter(Context context, List<ThumbZu> list, boolean z) {
        super(R.layout.f172myposts, list);
        this.a = context;
        this.b = z;
        this.e = q.b((Activity) context) - context.getResources().getDimensionPixelSize(R.dimen.dp_140);
        if (list.size() == 1) {
            this.c = 6;
            this.d = ((this.e - context.getResources().getDimensionPixelSize(2131492889)) * 3) / 4;
        } else if (list.size() == 2 || list.size() == 4) {
            this.c = 3;
            this.d = (this.e - context.getResources().getDimensionPixelSize(2131492889)) / 2;
        } else {
            this.c = 2;
            this.d = (this.e - context.getResources().getDimensionPixelSize(R.dimen.dp_20)) / 3;
        }
        for (ThumbZu url : list) {
            this.f.add(url.getUrl());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, ThumbZu thumbZu) {
        i.b(this.a).a(thumbZu.getUrl()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.break_news_item_pic_item));
        LayoutParams layoutParams = new LayoutParams(this.d, this.d);
        if (this.c == 6) {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(2131492889), 0, 0);
            layoutParams.gravity = 3;
        } else if (this.c == 3) {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(2131492889), 0, 0);
            if (baseViewHolder.getLayoutPosition() % 2 == 0) {
                layoutParams.gravity = 3;
            } else {
                layoutParams.gravity = 5;
            }
        } else {
            layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(2131492889), 0, 0);
            if (baseViewHolder.getLayoutPosition() % 3 == 0) {
                layoutParams.gravity = 3;
            } else if (baseViewHolder.getLayoutPosition() % 3 == 1) {
                layoutParams.gravity = 17;
            } else if (baseViewHolder.getLayoutPosition() % 3 == 2) {
                layoutParams.gravity = 5;
            }
        }
        baseViewHolder.getView(R.id.break_news_item_pic_item).setLayoutParams(layoutParams);
        if (this.b) {
        }
    }
}
