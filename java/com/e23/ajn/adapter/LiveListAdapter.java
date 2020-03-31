package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.e;
import com.e23.ajn.d.q;
import com.e23.ajn.model.LiveListResponseModel.LiveBean;
import java.util.List;

public class LiveListAdapter extends BaseQuickAdapter<LiveBean, BaseViewHolder> {
    private Context a;
    private int b;
    private int c;

    public LiveListAdapter(Context context, List<LiveBean> list) {
        super(R.layout.f185newslist_item, list);
        this.a = context;
        this.b = q.b((Activity) context);
        this.c = this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_20);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, LiveBean liveBean) {
        baseViewHolder.setText((int) R.id.live_list_item_title, (CharSequence) liveBean.getTitle());
        LayoutParams layoutParams = new LayoutParams(this.c, this.c / 2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.c, this.c / 2);
        layoutParams2.setMargins(0, 0, 0, 0);
        layoutParams.setMargins(0, this.a.getResources().getDimensionPixelSize(2131492889), 0, this.a.getResources().getDimensionPixelSize(2131492889));
        baseViewHolder.getView(R.id.live_list_item_img).setLayoutParams(layoutParams2);
        baseViewHolder.getView(R.id.live_list_item_img_layout).setLayoutParams(layoutParams);
        if (liveBean.getThumb().contains(".gif")) {
            i.b(this.a).a(liveBean.getThumb()).i().b(b.SOURCE).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a((ImageView) baseViewHolder.getView(R.id.live_list_item_img));
        } else {
            i.b(this.a).a(liveBean.getThumb()).b(b.ALL).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a((ImageView) baseViewHolder.getView(R.id.live_list_item_img));
        }
        baseViewHolder.setText((int) R.id.live_list_item_img_num, (CharSequence) liveBean.getViews());
        if (!e.b(liveBean.getStatus())) {
            return;
        }
        if (liveBean.getStatus().equals("0")) {
            baseViewHolder.setText((int) R.id.live_list_item_status, (CharSequence) "预告");
            baseViewHolder.getView(R.id.live_list_item_status).setBackgroundResource(R.drawable.f49ssdk_oks_classic_mingdao);
        } else if (liveBean.getStatus().equals("1")) {
            baseViewHolder.setText((int) R.id.live_list_item_status, (CharSequence) "直播中");
            baseViewHolder.getView(R.id.live_list_item_status).setBackgroundResource(R.drawable.f48ssdk_oks_classic_meipai);
        } else if (liveBean.getStatus().equals("2")) {
            baseViewHolder.setText((int) R.id.live_list_item_status, (CharSequence) "回顾");
            baseViewHolder.getView(R.id.live_list_item_status).setBackgroundResource(R.drawable.f47ssdk_oks_classic_linkedin);
        }
    }
}
