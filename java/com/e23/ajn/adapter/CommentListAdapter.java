package com.e23.ajn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.i;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.d.f;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.views.CircularImage;
import java.util.List;

public class CommentListAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    /* access modifiers changed from: private */
    public Context a;
    private boolean b;
    private int c;

    public CommentListAdapter(Context context, List<CommentBean> list, boolean z, int i) {
        super(R.layout.f178myvisit_item, list);
        this.a = context;
        this.c = i;
        this.b = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(BaseViewHolder baseViewHolder, final CommentBean commentBean) {
        baseViewHolder.setText((int) R.id.comment_user_name, (CharSequence) commentBean.getUsername());
        baseViewHolder.setText((int) R.id.comment_content, (CharSequence) f.a(this.a, commentBean.getContent()));
        baseViewHolder.setText((int) R.id.comment_content_time, (CharSequence) commentBean.getCreat_at());
        baseViewHolder.setText((int) R.id.comment_zan, (CharSequence) commentBean.getZan());
        baseViewHolder.getView(R.id.comment_zan).setVisibility(8);
        if (this.c == 1) {
            baseViewHolder.getView(R.id.comment_content_reply_me).setVisibility(0);
        } else {
            baseViewHolder.getView(R.id.comment_content_reply_me).setVisibility(8);
        }
        i.b(this.a).a(commentBean.getAvatar()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CircularImage) baseViewHolder.getView(R.id.comment_header));
        if (this.b) {
            i.b(this.a).a(commentBean.getThumb()).b(b.ALL).d((int) R.mipmap.f276plimg_s).c((int) R.mipmap.f276plimg_s).a((ImageView) baseViewHolder.getView(R.id.content_img));
            baseViewHolder.setText((int) R.id.content_title, (CharSequence) commentBean.getTitle());
            baseViewHolder.addOnClickListener(R.id.comment_news_content);
            baseViewHolder.getView(R.id.comment_news_content).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(CommentListAdapter.this.a, SwipeBackCommonActivity.class);
                    intent.putExtra(SwipeBackCommonActivity.TAG, SwipeBackCommonActivity.NEWS_DETAIL_FROM_MINE);
                    intent.putExtra("newsid", commentBean.getNewsid());
                    CommentListAdapter.this.a.startActivity(intent);
                }
            });
            return;
        }
        baseViewHolder.getView(R.id.contentlayout).setVisibility(8);
    }
}
