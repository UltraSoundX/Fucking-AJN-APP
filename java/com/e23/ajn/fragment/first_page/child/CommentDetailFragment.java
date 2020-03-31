package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.d.b.b;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.adapter.CommentListAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.f;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.CommentListResponseModel;
import com.e23.ajn.views.CircularImage;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.e;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class CommentDetailFragment extends BaseListFragment {
    OnClickListener o = new OnClickListener() {
        public void onClick(View view) {
            CommentDetailFragment.this.a(new String[]{CommentDetailFragment.this.p.getNewsid(), CommentDetailFragment.this.p.getThumb(), CommentDetailFragment.this.p.getTitle(), CommentDetailFragment.this.p.getWzposterid(), CommentDetailFragment.this.p.getNewstype(), CommentDetailFragment.this.p.getId(), CommentDetailFragment.this.p.getUserid(), CommentDetailFragment.this.p.getUsername(), f.d(CommentDetailFragment.this.p.getContent())});
        }
    };
    /* access modifiers changed from: private */
    public CommentBean p;

    /* renamed from: q reason: collision with root package name */
    private View f400q;
    private e r;
    /* access modifiers changed from: private */
    public CommentListAdapter s;

    public static CommentDetailFragment a(CommentBean commentBean) {
        CommentDetailFragment commentDetailFragment = new CommentDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("comment", commentBean);
        commentDetailFragment.setArguments(bundle);
        return commentDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.p = (CommentBean) getArguments().getSerializable("comment");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.s = new CommentListAdapter(this.c, null, false, 0);
    }

    public boolean h() {
        return true;
    }

    public boolean o() {
        return false;
    }

    public String i() {
        return this.p.getReplysum() + "回复";
    }

    public BaseQuickAdapter j() {
        return this.s;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=comment&a=getCommentToComment_pub";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("plid", this.p.getId());
        return hashMap;
    }

    public Callback c(final boolean z) {
        return new a<CommentListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    CommentDetailFragment.this.f.setEnabled(false);
                } else {
                    CommentDetailFragment.this.s.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    CommentDetailFragment.this.f.setEnabled(true);
                } else {
                    CommentDetailFragment.this.f.setRefreshing(false);
                    if (CommentDetailFragment.this.s.getData().size() >= 20) {
                        CommentDetailFragment.this.s.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    CommentDetailFragment.this.s.loadMoreFail();
                    return;
                }
                CommentDetailFragment.this.s.setNewData(null);
                CommentDetailFragment.this.s.setEmptyView(CommentDetailFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(CommentListResponseModel commentListResponseModel, int i) {
                if (commentListResponseModel == null || commentListResponseModel.getCode() != 200) {
                    if (z) {
                        CommentDetailFragment.this.s.loadMoreFail();
                        return;
                    }
                    CommentDetailFragment.this.s.setNewData(null);
                    CommentDetailFragment.this.s.setEmptyView(CommentDetailFragment.this.h);
                } else if (com.e23.ajn.d.e.b(commentListResponseModel.getData())) {
                    if (z) {
                        CommentDetailFragment.this.s.addData((Collection<? extends T>) commentListResponseModel.getData());
                        CommentDetailFragment.this.s.loadMoreComplete();
                    } else {
                        CommentDetailFragment.this.s();
                        CommentDetailFragment.this.s.setNewData(commentListResponseModel.getData());
                    }
                    if (commentListResponseModel.getData().size() < 20) {
                        CommentDetailFragment.this.s.loadMoreEnd(CommentDetailFragment.this.k);
                    } else {
                        CommentDetailFragment.this.n = CommentDetailFragment.this.n + 1;
                    }
                } else if (z) {
                    CommentDetailFragment.this.s.loadMoreEnd(CommentDetailFragment.this.k);
                } else {
                    CommentDetailFragment.this.s.setNewData(null);
                    CommentDetailFragment.this.s.setEmptyView(CommentDetailFragment.this.g);
                }
            }
        };
    }

    public void l() {
        onRefresh();
    }

    public boolean m() {
        return false;
    }

    public i n() {
        return new WrapContentLinearLayoutManager(this.c);
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.f400q == null) {
            this.f400q = this.c.getLayoutInflater().inflate(R.layout.f123cube_mints_base_block_menu_item, (ViewGroup) this.e.getParent(), false);
            com.bumptech.glide.i.a(this.c).a(this.p.getAvatar()).b(b.ALL).d((int) R.mipmap.f239fatieimg).c((int) R.mipmap.f239fatieimg).a((ImageView) (CircularImage) this.f400q.findViewById(R.id.comment_header));
            ((TextView) this.f400q.findViewById(R.id.comment_user_name)).setText(this.p.getUsername());
            ((TextView) this.f400q.findViewById(R.id.comment_content)).setText(f.a(this.c, this.p.getContent()));
            ((TextView) this.f400q.findViewById(R.id.comment_content_time)).setText(this.p.getCreat_at());
            ((ImageView) this.f400q.findViewById(R.id.comment_reply)).setOnClickListener(this.o);
        } else {
            ViewParent parent = this.f400q.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f400q);
            }
        }
        this.s.addHeaderView(this.f400q, 0);
    }

    /* access modifiers changed from: private */
    public void a(String[] strArr) {
        if (this.r == null) {
            this.r = new e(this.c, strArr, new e.a() {
                public void a(CommentBean commentBean) {
                    CommentDetailFragment.this.s.addData(0, commentBean);
                    CommentDetailFragment.this.s.notifyDataSetChanged();
                }
            });
        } else {
            this.r.a(strArr);
        }
        this.r.show();
    }
}
