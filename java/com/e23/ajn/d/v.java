package com.e23.ajn.d;

import android.content.Context;
import android.content.Intent;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.model.NewsFoundationBean;
import com.e23.ajn.model.Seen;
import org.litepal.crud.DataSupport;

/* compiled from: ToNewsDetailUtil */
public class v {
    public static void a(Context context, NewsFoundationBean newsFoundationBean, BaseQuickAdapter baseQuickAdapter) {
        Intent intent = new Intent(context, SwipeBackCommonActivity.class);
        if (newsFoundationBean != null && newsFoundationBean.getNewstype() != 6) {
            if (newsFoundationBean.getNewstype() == 7) {
                intent.putExtra(SwipeBackCommonActivity.TAG, 9);
                intent.putExtra(SwipeBackCommonActivity.TITLE, newsFoundationBean.getCatname());
                intent.putExtra(SwipeBackCommonActivity.URL, newsFoundationBean.getCatid());
                context.startActivity(intent);
                return;
            }
            String norb = newsFoundationBean.getNorb();
            if (norb.equals(NewsFoundationBean.WAI_LIAN)) {
                Intent intent2 = new Intent(context, OutUrlActivity.class);
                intent2.putExtra("url", newsFoundationBean.getUrl());
                intent2.putExtra("title", newsFoundationBean.getTitle());
                intent2.putExtra(OutUrlActivity.ARG_THUMB, newsFoundationBean.getThumb());
                intent2.putExtra(OutUrlActivity.ARG_DESC, newsFoundationBean.getDescription());
                context.startActivity(intent2);
            } else if (norb.equals(NewsFoundationBean.NEWS)) {
                intent.putExtra(SwipeBackCommonActivity.TAG, 3);
                intent.putExtra(SwipeBackCommonActivity.SERIALIZABLE, newsFoundationBean);
                context.startActivity(intent);
            } else if (norb.equals(NewsFoundationBean.ZHUAN_TI)) {
                intent.putExtra(SwipeBackCommonActivity.TAG, 6);
                intent.putExtra(SwipeBackCommonActivity.TITLE, newsFoundationBean.getTitle());
                intent.putExtra(SwipeBackCommonActivity.URL, newsFoundationBean.getUrl());
                context.startActivity(intent);
            } else if (norb.equals(NewsFoundationBean.BBS)) {
                intent.putExtra(SwipeBackCommonActivity.TAG, 3);
                intent.putExtra(SwipeBackCommonActivity.SERIALIZABLE, newsFoundationBean);
                context.startActivity(intent);
            } else if (!norb.equals(NewsFoundationBean.MALL)) {
                if (norb.equals(NewsFoundationBean.ZHI_BO)) {
                    intent.putExtra(SwipeBackCommonActivity.TAG, 3);
                    intent.putExtra(SwipeBackCommonActivity.SERIALIZABLE, newsFoundationBean);
                    context.startActivity(intent);
                } else if (norb.equals(NewsFoundationBean.VOTE)) {
                    Intent intent3 = new Intent(context, OutUrlActivity.class);
                    intent3.putExtra("url", newsFoundationBean.getUrl());
                    intent3.putExtra("title", newsFoundationBean.getTitle());
                    intent3.putExtra(OutUrlActivity.ARG_THUMB, newsFoundationBean.getThumb());
                    intent3.putExtra(OutUrlActivity.ARG_DESC, newsFoundationBean.getDescription());
                    context.startActivity(intent3);
                } else if (norb.equals(NewsFoundationBean.ZTTJ)) {
                }
            }
            if (newsFoundationBean != null && e.b(newsFoundationBean.getNewsId())) {
                if (e.a(DataSupport.where("newsId = ?", newsFoundationBean.getNewsId()).find(Seen.class))) {
                    Seen seen = new Seen();
                    seen.setNewsId(newsFoundationBean.getNewsId());
                    seen.save();
                    if (baseQuickAdapter != null) {
                        baseQuickAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
