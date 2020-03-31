package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.d.e;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.d.u;
import com.e23.ajn.model.MinePostResponse.DataBean.NewsBean;
import com.e23.ajn.model.ThumbZu;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.o;
import java.util.List;

public class MinePostsListAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {
    private Context a;
    private int b;
    private o c;

    public MinePostsListAdapter(Context context, List<NewsBean> list) {
        super(R.layout.f171mypost_item, list);
        this.a = context;
        this.b = q.b((Activity) context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, final NewsBean newsBean) {
        i.b(this.a).a(p.b("user_avatar", "")).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) (CircularImage) baseViewHolder.getView(R.id.break_news_item_img_header));
        baseViewHolder.setText((int) R.id.break_news_item_tv_name, (CharSequence) newsBean.getPostername());
        baseViewHolder.setText((int) R.id.break_news_item_content, (CharSequence) newsBean.getTitle());
        baseViewHolder.setText((int) R.id.break_news_item_time, (CharSequence) u.c(newsBean.getInputtime()));
        baseViewHolder.setText((int) R.id.break_news_item_tv_reply, (CharSequence) newsBean.getPlsum());
        baseViewHolder.setText((int) R.id.break_news_item_tv_like, (CharSequence) newsBean.getDing());
        LayoutParams layoutParams = new LayoutParams(this.b / 2, -2);
        layoutParams.gravity = 21;
        baseViewHolder.getView(R.id.break_news_item_act_layout).setLayoutParams(layoutParams);
        baseViewHolder.setBackgroundRes(R.id.break_news_item_img_like, R.mipmap.f272nbpimg);
        RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.break_news_item_content_pic);
        if (e.a((List) newsBean.getThumbs())) {
            recyclerView.setVisibility(8);
        } else {
            recyclerView.setVisibility(0);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new GridLayoutManager(this.a, 6));
            BreakNewsPicAdapter breakNewsPicAdapter = new BreakNewsPicAdapter(this.a, newsBean.getThumbs(), true);
            breakNewsPicAdapter.setSpanSizeLookup(new SpanSizeLookup() {
                public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                    if (newsBean.getThumbs().size() == 1) {
                        return 6;
                    }
                    if (newsBean.getThumbs().size() == 2 || newsBean.getThumbs().size() == 4) {
                        return 3;
                    }
                    return 2;
                }
            });
            recyclerView.setAdapter(breakNewsPicAdapter);
        }
        baseViewHolder.getView(R.id.break_news_item_layout_share).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str;
                String title = newsBean.getTitle();
                String description = newsBean.getDescription();
                String thumb = newsBean.getThumb();
                if (TextUtils.isEmpty(thumb) && e.b((List) newsBean.getThumbs())) {
                    ThumbZu thumbZu = (ThumbZu) newsBean.getThumbs().get(0);
                    if (thumbZu != null) {
                        str = thumbZu.getUrl();
                        MinePostsListAdapter.this.a(title, description, str, newsBean.getUrl());
                    }
                }
                str = thumb;
                MinePostsListAdapter.this.a(title, description, str, newsBean.getUrl());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        if (this.c == null) {
            this.c = new o(this.a);
        }
        if (this.c != null && !this.c.isShowing()) {
            this.c.a(str, str2, str3, str4);
            this.c.show();
        }
    }
}
