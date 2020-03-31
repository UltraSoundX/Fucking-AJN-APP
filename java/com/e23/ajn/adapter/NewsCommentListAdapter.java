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
import com.e23.ajn.d.g;
import com.e23.ajn.d.g.a;
import com.e23.ajn.d.p;
import com.e23.ajn.fragment.mine.child.MessageListFragment;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.MessageBean;
import com.e23.ajn.views.CircularImage;
import java.util.List;

public class NewsCommentListAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    a a = new a() {
        public void a(int i) {
            ((CommentBean) NewsCommentListAdapter.this.getData().get(i)).setZan((Integer.parseInt(((CommentBean) NewsCommentListAdapter.this.getData().get(i)).getZan()) + 1) + "");
            NewsCommentListAdapter.this.notifyDataSetChanged();
        }
    };
    /* access modifiers changed from: private */
    public Context b;
    private boolean c = true;
    /* access modifiers changed from: private */
    public g d;
    private int e = 0;

    public NewsCommentListAdapter(Context context, List<CommentBean> list) {
        super(R.layout.f189newspldialog, list);
        this.b = context;
    }

    public void a(int i) {
        this.e = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void convert(final BaseViewHolder baseViewHolder, final CommentBean commentBean) {
        baseViewHolder.getView(R.id.hotOrNoraml).setVisibility(8);
        if (baseViewHolder.getAdapterPosition() == 1) {
            if (commentBean.getHot().equals("1")) {
                baseViewHolder.setText((int) R.id.hotOrNoraml, (CharSequence) "热门评论");
                baseViewHolder.getView(R.id.hotOrNoraml).setVisibility(0);
            }
            if (commentBean.getHot().equals("0")) {
                baseViewHolder.setText((int) R.id.hotOrNoraml, (CharSequence) "最新评论");
                baseViewHolder.getView(R.id.hotOrNoraml).setVisibility(0);
            }
        } else if (baseViewHolder.getAdapterPosition() - 1 == this.e) {
            baseViewHolder.setText((int) R.id.hotOrNoraml, (CharSequence) "最新评论");
            baseViewHolder.getView(R.id.hotOrNoraml).setVisibility(0);
            this.c = false;
        }
        baseViewHolder.setText((int) R.id.comment_user_name, (CharSequence) commentBean.getUsername());
        baseViewHolder.setText((int) R.id.comment_content, (CharSequence) f.a(this.b, commentBean.getContent()));
        baseViewHolder.setText((int) R.id.comment_content_time, (CharSequence) commentBean.getCreat_at());
        i.b(this.b).a(commentBean.getAvatar()).b(b.ALL).d((int) R.mipmap.f278plitemding).c((int) R.mipmap.f278plitemding).a((ImageView) (CircularImage) baseViewHolder.getView(R.id.comment_header));
        if (commentBean.getReplysum().equals("0")) {
            baseViewHolder.getView(R.id.comment_plsum).setVisibility(8);
        } else {
            baseViewHolder.getView(R.id.comment_plsum).setVisibility(0);
            baseViewHolder.setText((int) R.id.comment_plsum, (CharSequence) commentBean.getReplysum() + "回复");
        }
        baseViewHolder.setText((int) R.id.comment_zan, (CharSequence) commentBean.getZan());
        baseViewHolder.addOnClickListener(R.id.comment_send_message);
        baseViewHolder.getView(R.id.comment_send_message).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!p.a("is_logined", false)) {
                    NewsCommentListAdapter.this.a();
                    return;
                }
                MessageBean messageBean = new MessageBean();
                messageBean.setMessageid("");
                messageBean.setSend_from_avatar(p.b("user_avatar", ""));
                messageBean.setSend_from_id(p.b("user_name", ""));
                messageBean.setSend_from_uid(p.b("user_name", ""));
                messageBean.setSend_to_avatar(commentBean.getAvatar());
                messageBean.setSend_to_id(commentBean.getUsername());
                messageBean.setSend_to_uid(commentBean.getUserid());
                messageBean.setIscom("0");
                ((SwipeBackCommonActivity) NewsCommentListAdapter.this.b).start(MessageListFragment.a(messageBean));
            }
        });
        baseViewHolder.addOnClickListener(R.id.comment_zan);
        baseViewHolder.getView(R.id.comment_zan).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (NewsCommentListAdapter.this.d == null) {
                    NewsCommentListAdapter.this.d = new g();
                }
                NewsCommentListAdapter.this.d.a(NewsCommentListAdapter.this.b, baseViewHolder.getLayoutPosition() - 1, commentBean, NewsCommentListAdapter.this.a);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        Intent intent = new Intent(this.b, SwipeBackCommonActivity.class);
        intent.putExtra(SwipeBackCommonActivity.TAG, 21);
        this.b.startActivity(intent);
    }
}
