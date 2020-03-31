package com.e23.ajn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.SpanSizeLookup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.d.o.a;
import com.e23.ajn.d.q;
import com.e23.ajn.d.s;
import com.e23.ajn.d.u;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.NewsBean;
import com.e23.ajn.model.Seen;
import com.e23.ajn.model.ThumbZu;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.e;
import com.e23.ajn.views.k;
import com.e23.ajn.views.o;
import java.util.List;
import org.litepal.crud.DataSupport;

public class NewsItemAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public Context a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private List<Seen> h;
    private boolean i = false;
    private o j;
    private e k;

    public NewsItemAdapter(Context context, List<NewsBean> list, boolean z, boolean z2) {
        super(list);
        this.a = context;
        this.d = z;
        this.e = z2;
        addItemType(1, R.layout.f105notification_action);
        addItemType(4, R.layout.f188newslist_item_normal);
        addItemType(2, R.layout.f108notification_media_cancel_action);
        addItemType(3, R.layout.f107notification_media_action);
        addItemType(5, R.layout.f109notification_template_big_media);
        addItemType(6, R.layout.f171mypost_item);
        addItemType(7, R.layout.chatting_item_msg_text_left);
        addItemType(8, R.layout.f114select_dialog_item_material);
        this.b = q.b((Activity) context);
        this.c = (this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_32)) / 2;
        this.f = this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_20);
        this.g = ((this.b - context.getResources().getDimensionPixelSize(R.dimen.dp_16)) - context.getResources().getDimensionPixelSize(R.dimen.dp_20)) / 3;
    }

    public void a(boolean z) {
        this.i = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(final BaseViewHolder baseViewHolder, final NewsBean newsBean) {
        int i2;
        int i3;
        int i4;
        int i5;
        switch (baseViewHolder.getItemViewType()) {
            case 1:
                i.b(this.a).a(newsBean.getThumb()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.news_item_img));
                baseViewHolder.setText((int) R.id.news_item_title, (CharSequence) newsBean.getTitle());
                if (TextUtils.isEmpty(newsBean.getNewsclass())) {
                    baseViewHolder.getView(R.id.news_item_label).setVisibility(8);
                } else {
                    baseViewHolder.getView(R.id.news_item_label).setVisibility(0);
                    baseViewHolder.setText((int) R.id.news_item_label, (CharSequence) newsBean.getNewsclass());
                }
                if (this.d) {
                    if ((!TextUtils.isEmpty(newsBean.getPlsum()) || !TextUtils.isEmpty(newsBean.getDing())) && (TextUtils.isEmpty(newsBean.getNewsclass()) || (!newsBean.getNewsclass().equals("专题") && !newsBean.getNewsclass().equals("H5")))) {
                        try {
                            i4 = Integer.parseInt(newsBean.getPlsum());
                        } catch (Exception e2) {
                            i4 = 0;
                        }
                        try {
                            i5 = Integer.parseInt(newsBean.getDing());
                        } catch (Exception e3) {
                            i5 = 0;
                        }
                        if (i4 + i5 == 0) {
                            baseViewHolder.getView(R.id.news_item_num).setVisibility(8);
                        } else {
                            baseViewHolder.setText((int) R.id.news_item_num, (CharSequence) (i5 + i4) + "态度");
                            baseViewHolder.getView(R.id.news_item_num).setVisibility(0);
                        }
                    } else {
                        baseViewHolder.getView(R.id.news_item_num).setVisibility(8);
                    }
                    baseViewHolder.getView(R.id.news_item_time).setVisibility(8);
                } else {
                    baseViewHolder.getView(R.id.news_item_time).setVisibility(0);
                    baseViewHolder.getView(R.id.news_item_num).setVisibility(8);
                    baseViewHolder.setText((int) R.id.news_item_time, (CharSequence) u.c(newsBean.getInputtime()));
                }
                if (this.e) {
                    this.h = DataSupport.where("newsId = ?", newsBean.getNewsId()).find(Seen.class);
                    if (com.e23.ajn.d.e.b((List) this.h)) {
                        baseViewHolder.setTextColor(R.id.news_item_title, this.a.getResources().getColor(R.color.colorGray6));
                        return;
                    } else {
                        baseViewHolder.setTextColor(R.id.news_item_title, this.a.getResources().getColor(R.color.news_item_title_text_color));
                        return;
                    }
                } else {
                    baseViewHolder.setTextColor(R.id.news_item_title, this.a.getResources().getColor(R.color.news_item_title_text_color));
                    return;
                }
            case 2:
                if (TextUtils.isEmpty(newsBean.getTitle())) {
                    baseViewHolder.getView(R.id.layout_pics_item_title).setVisibility(8);
                } else {
                    baseViewHolder.getView(R.id.layout_pics_item_title).setVisibility(0);
                    baseViewHolder.setText((int) R.id.layout_pics_item_title, (CharSequence) newsBean.getTitle());
                }
                this.h = DataSupport.where("newsId = ?", newsBean.getNewsId()).find(Seen.class);
                if (com.e23.ajn.d.e.b((List) this.h)) {
                    baseViewHolder.setTextColor(R.id.layout_pics_item_title, this.a.getResources().getColor(R.color.colorGray6));
                } else {
                    baseViewHolder.setTextColor(R.id.layout_pics_item_title, this.a.getResources().getColor(R.color.news_item_title_text_color));
                }
                if (TextUtils.isEmpty(newsBean.getNewsclass())) {
                    baseViewHolder.getView(R.id.layout_pics_item_label).setVisibility(8);
                } else {
                    baseViewHolder.getView(R.id.layout_pics_item_label).setVisibility(0);
                    baseViewHolder.setText((int) R.id.layout_pics_item_label, (CharSequence) newsBean.getNewsclass());
                }
                if (this.d) {
                    if ((!TextUtils.isEmpty(newsBean.getPlsum()) || !TextUtils.isEmpty(newsBean.getDing())) && (TextUtils.isEmpty(newsBean.getNewsclass()) || (!newsBean.getNewsclass().equals("专题") && !newsBean.getNewsclass().equals("H5")))) {
                        try {
                            i2 = Integer.parseInt(newsBean.getPlsum());
                        } catch (Exception e4) {
                            i2 = 0;
                        }
                        try {
                            i3 = Integer.parseInt(newsBean.getDing());
                        } catch (Exception e5) {
                            i3 = 0;
                        }
                        baseViewHolder.setText((int) R.id.layout_pics_item_num, (CharSequence) (i3 + i2) + "态度");
                        baseViewHolder.getView(R.id.layout_pics_item_num).setVisibility(0);
                    } else {
                        baseViewHolder.getView(R.id.layout_pics_item_num).setVisibility(8);
                    }
                    baseViewHolder.getView(R.id.pics_item_time).setVisibility(8);
                } else {
                    baseViewHolder.getView(R.id.pics_item_time).setVisibility(0);
                    baseViewHolder.getView(R.id.layout_pics_item_num).setVisibility(8);
                    baseViewHolder.setText((int) R.id.pics_item_time, (CharSequence) u.c(newsBean.getInputtime()));
                }
                LayoutParams layoutParams = new LayoutParams(this.g, (this.g * 2) / 3);
                LayoutParams layoutParams2 = new LayoutParams(this.g, (this.g * 2) / 3);
                layoutParams2.setMargins(this.a.getResources().getDimensionPixelSize(R.dimen.dp_8), 0, this.a.getResources().getDimensionPixelSize(R.dimen.dp_8), 0);
                baseViewHolder.getView(R.id.layout_pics_item_pic1).setLayoutParams(layoutParams);
                baseViewHolder.getView(R.id.layout_pics_item_pic2).setLayoutParams(layoutParams2);
                baseViewHolder.getView(R.id.layout_pics_item_pic3).setLayoutParams(layoutParams);
                if (com.e23.ajn.d.e.a((List) newsBean.getThumbs()) && !TextUtils.isEmpty(newsBean.getZu())) {
                    newsBean.setThumbs(s.a(newsBean.getZu(), ThumbZu.class));
                }
                if (com.e23.ajn.d.e.b((List) newsBean.getThumbs())) {
                    try {
                        i.b(this.a).a(((ThumbZu) newsBean.getThumbs().get(0)).getUrl()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.layout_pics_item_pic1));
                        i.b(this.a).a(((ThumbZu) newsBean.getThumbs().get(1)).getUrl()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.layout_pics_item_pic2));
                        i.b(this.a).a(((ThumbZu) newsBean.getThumbs().get(2)).getUrl()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.layout_pics_item_pic3));
                        return;
                    } catch (Exception e6) {
                        return;
                    }
                } else {
                    return;
                }
            case 3:
                LayoutParams layoutParams3 = new LayoutParams(this.b, (this.b * 2) / 11);
                layoutParams3.setMargins(0, 0, 0, 0);
                baseViewHolder.getView(R.id.layout_one_pic_item_img).setLayoutParams(layoutParams3);
                if (!com.e23.ajn.d.e.b(newsBean.getThumb())) {
                    return;
                }
                if (newsBean.getThumb().contains(".gif")) {
                    i.b(this.a).a(newsBean.getThumb()).i().b(b.SOURCE).d((int) R.mipmap.f277plimgn).c((int) R.mipmap.f277plimgn).a((ImageView) baseViewHolder.getView(R.id.layout_one_pic_item_img));
                    return;
                } else {
                    i.b(this.a).a(newsBean.getThumb()).b(b.ALL).d((int) R.mipmap.f277plimgn).c((int) R.mipmap.f277plimgn).a((ImageView) baseViewHolder.getView(R.id.layout_one_pic_item_img));
                    return;
                }
            case 4:
                baseViewHolder.setText((int) R.id.news_big_pic_item_title, (CharSequence) newsBean.getTitle());
                this.h = DataSupport.where("newsId = ?", newsBean.getNewsId()).find(Seen.class);
                if (com.e23.ajn.d.e.b((List) this.h)) {
                    baseViewHolder.setTextColor(R.id.news_big_pic_item_title, this.a.getResources().getColor(R.color.colorGray6));
                } else {
                    baseViewHolder.setTextColor(R.id.news_big_pic_item_title, this.a.getResources().getColor(R.color.news_item_title_text_color));
                }
                LayoutParams layoutParams4 = new LayoutParams(this.f, this.f / 2);
                RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(this.f, this.f / 2);
                layoutParams5.setMargins(0, 0, 0, 0);
                layoutParams4.setMargins(0, this.a.getResources().getDimensionPixelSize(2131492889), 0, this.a.getResources().getDimensionPixelSize(2131492889));
                baseViewHolder.getView(R.id.layout_big_pic_item_img).setLayoutParams(layoutParams5);
                baseViewHolder.getView(R.id.layout_big_pic_item_img_layout).setLayoutParams(layoutParams4);
                if (newsBean.getThumb().contains(".gif")) {
                    i.b(this.a).a(newsBean.getThumb()).i().b(b.SOURCE).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a((ImageView) baseViewHolder.getView(R.id.layout_big_pic_item_img));
                } else {
                    i.b(this.a).a(newsBean.getThumb()).b(b.ALL).d((int) R.mipmap.f275plimg).c((int) R.mipmap.f275plimg).a((ImageView) baseViewHolder.getView(R.id.layout_big_pic_item_img));
                }
                if ((TextUtils.isEmpty(newsBean.getNewsclass()) || !newsBean.getNewsclass().equals("视频")) && TextUtils.isEmpty(newsBean.getVideourl())) {
                    baseViewHolder.getView(R.id.layout_big_pic_item_img_play).setVisibility(8);
                    return;
                } else {
                    baseViewHolder.getView(R.id.layout_big_pic_item_img_play).setVisibility(0);
                    return;
                }
            case 5:
                if (com.e23.ajn.d.e.a((List) newsBean.getThumbs()) && !TextUtils.isEmpty(newsBean.getZu())) {
                    newsBean.setThumbs(s.a(newsBean.getZu(), ThumbZu.class));
                }
                baseViewHolder.getView(R.id.layout_recommend_title_layout).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(NewsItemAdapter.this.a, SwipeBackCommonActivity.class);
                        intent.putExtra(SwipeBackCommonActivity.TAG, 8);
                        intent.putExtra("catid", newsBean.getCatid());
                        NewsItemAdapter.this.a.startActivity(intent);
                    }
                });
                RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.news_recommendation_list);
                if (com.e23.ajn.d.e.b((List) newsBean.getThumbs())) {
                    for (int i6 = 0; i6 < newsBean.getThumbs().size(); i6++) {
                        ((ThumbZu) newsBean.getThumbs().get(i6)).setCatId(newsBean.getUrl().split("\\|")[i6]);
                    }
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setHasFixedSize(true);
                    RecommendationItemAdapter recommendationItemAdapter = new RecommendationItemAdapter(this.a, null);
                    WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.a);
                    wrapContentLinearLayoutManager.b(0);
                    recyclerView.setLayoutManager(wrapContentLinearLayoutManager);
                    recyclerView.setAdapter(recommendationItemAdapter);
                    recommendationItemAdapter.setNewData(newsBean.getThumbs());
                    return;
                }
                return;
            case 6:
                i.b(this.a).a(newsBean.getAvatar()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CircularImage) baseViewHolder.getView(R.id.break_news_item_img_header));
                baseViewHolder.setText((int) R.id.break_news_item_tv_name, (CharSequence) newsBean.getPostername());
                baseViewHolder.setText((int) R.id.break_news_item_content, (CharSequence) newsBean.getTitle());
                baseViewHolder.setText((int) R.id.break_news_item_time, (CharSequence) u.a(newsBean.getInputtime()));
                baseViewHolder.setText((int) R.id.break_news_item_tv_reply, (CharSequence) newsBean.getPlsum());
                baseViewHolder.setText((int) R.id.break_news_item_tv_like, (CharSequence) newsBean.getDing());
                LayoutParams layoutParams6 = new LayoutParams(this.b / 2, -2);
                layoutParams6.gravity = 21;
                baseViewHolder.getView(R.id.break_news_item_act_layout).setLayoutParams(layoutParams6);
                if (newsBean.getIsPraised() == 0) {
                    baseViewHolder.setBackgroundRes(R.id.break_news_item_img_like, R.mipmap.f272nbpimg);
                } else {
                    baseViewHolder.setBackgroundRes(R.id.break_news_item_img_like, R.mipmap.f273newsloadingpic);
                }
                RecyclerView recyclerView2 = (RecyclerView) baseViewHolder.getView(R.id.break_news_item_content_pic);
                if (com.e23.ajn.d.e.a((List) newsBean.getThumbs()) && !TextUtils.isEmpty(newsBean.getZu())) {
                    newsBean.setThumbs(s.a(newsBean.getZu(), ThumbZu.class));
                }
                if (com.e23.ajn.d.e.a((List) newsBean.getThumbs())) {
                    recyclerView2.setVisibility(8);
                } else {
                    recyclerView2.setVisibility(0);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setNestedScrollingEnabled(false);
                    recyclerView2.setLayoutManager(new GridLayoutManager(this.a, 6));
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
                    recyclerView2.setAdapter(breakNewsPicAdapter);
                }
                baseViewHolder.getView(R.id.break_news_item_layout_share).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        String str;
                        String title = newsBean.getTitle();
                        String description = newsBean.getDescription();
                        String thumb = newsBean.getThumb();
                        if (TextUtils.isEmpty(thumb) && com.e23.ajn.d.e.b((List) newsBean.getThumbs())) {
                            ThumbZu thumbZu = (ThumbZu) newsBean.getThumbs().get(0);
                            if (thumbZu != null) {
                                str = thumbZu.getUrl();
                                NewsItemAdapter.this.a(title, description, str, newsBean.getUrl());
                            }
                        }
                        str = thumb;
                        NewsItemAdapter.this.a(title, description, str, newsBean.getUrl());
                    }
                });
                baseViewHolder.getView(R.id.break_news_item_layout_like).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        NewsItemAdapter.this.a(newsBean.getIsPraised() != 0, newsBean.getNewsId(), newsBean.getCatid(), baseViewHolder.getLayoutPosition());
                    }
                });
                baseViewHolder.getView(R.id.break_news_item_layout_reply).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        NewsItemAdapter.this.a(newsBean, baseViewHolder.getLayoutPosition());
                    }
                });
                final View view = baseViewHolder.getView(R.id.card_view);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(NewsItemAdapter.this.a, SwipeBackCommonActivity.class);
                        intent.putExtra(SwipeBackCommonActivity.TAG, 3);
                        intent.putExtra(SwipeBackCommonActivity.SERIALIZABLE, newsBean);
                        NewsItemAdapter.this.a.startActivity(intent);
                    }
                });
                baseViewHolder.getView(R.id.break_news_item_content_pic).setOnTouchListener(new OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return view.onTouchEvent(motionEvent);
                    }
                });
                return;
            case 7:
                baseViewHolder.setText((int) R.id.topic_content_list_item_content_title, (CharSequence) newsBean.getCatname());
                return;
            case 8:
                i.b(this.a).a(newsBean.getAvatar()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CircularImage) baseViewHolder.getView(R.id.news_item_lovejinan_plus_img_header));
                baseViewHolder.setText((int) R.id.news_item_lovejinan_plus_author, (CharSequence) newsBean.getCopyfrom());
                baseViewHolder.setText((int) R.id.news_item_lovejinan_plus_time, (CharSequence) u.b(newsBean.getInputtime()));
                i.b(this.a).a(newsBean.getThumb()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) baseViewHolder.getView(R.id.news_item_lovejinan_plus_img));
                baseViewHolder.setText((int) R.id.news_item_lovejinan_plus_title, (CharSequence) newsBean.getTitle());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        if (this.j == null) {
            this.j = new o(this.a);
        }
        if (this.j != null && !this.j.isShowing()) {
            this.j.a(str, str2, str3, str4);
            this.j.show();
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str, String str2, int i2) {
        if (z) {
            k.a(this.a, "您已赞过！");
        } else {
            a(str, str2, i2);
        }
    }

    private void a(String str, String str2, final int i2) {
        new com.e23.ajn.d.o().b(this.a, str, str2, "ding", new a() {
            public void a(boolean z, String str) {
                try {
                    ((NewsBean) NewsItemAdapter.this.getData().get(i2 - 1)).setDing((Integer.parseInt(((NewsBean) NewsItemAdapter.this.getData().get(i2 - 1)).getDing()) + 1) + "");
                    ((NewsBean) NewsItemAdapter.this.getData().get(i2 - 1)).setIsPraised(1);
                } catch (Exception e) {
                }
                NewsItemAdapter.this.notifyDataSetChanged();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(NewsBean newsBean, int i2) {
        a(new String[]{newsBean.getNewsId(), newsBean.getThumb(), newsBean.getTitle(), newsBean.getPosterid(), Integer.toString(newsBean.getNewstype()), "", "", "", ""}, i2);
    }

    private void a(String[] strArr, final int i2) {
        if (this.k == null) {
            this.k = new e(this.a, strArr, new e.a() {
                public void a(CommentBean commentBean) {
                    try {
                        ((NewsBean) NewsItemAdapter.this.getData().get(i2 - 1)).setPlsum((Integer.parseInt(((NewsBean) NewsItemAdapter.this.getData().get(i2 - 1)).getPlsum()) + 1) + "");
                    } catch (Exception e) {
                    }
                    NewsItemAdapter.this.notifyDataSetChanged();
                }
            });
        } else {
            this.k.a(strArr);
        }
        this.k.show();
    }
}
