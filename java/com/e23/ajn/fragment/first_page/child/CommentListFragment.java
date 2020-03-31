package com.e23.ajn.fragment.first_page.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.adapter.CommentListAdapter;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.CommentListResponseModel;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class CommentListFragment extends BaseListFragment {
    private String o;
    private String p;

    /* renamed from: q reason: collision with root package name */
    private int f401q;
    /* access modifiers changed from: private */
    public CommentListAdapter r;
    private View s;
    private TextView t;

    public static CommentListFragment a(String str, String str2, int i) {
        CommentListFragment commentListFragment = new CommentListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("news_id", str);
        bundle.putString("cat_id", str2);
        bundle.putInt("all_count", i);
        commentListFragment.setArguments(bundle);
        return commentListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("news_id");
            this.p = getArguments().getString("cat_id");
            this.f401q = getArguments().getInt("all_count");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.r = new CommentListAdapter(this.c, null, true, 0);
    }

    public boolean h() {
        return true;
    }

    public String i() {
        return getString(R.string.comment);
    }

    public BaseQuickAdapter j() {
        return this.r;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=comment&a=getcomment_pub";
    }

    public Map<String, String> b(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("catid", this.p);
        hashMap.put("newsid", this.o);
        return hashMap;
    }

    public Callback c(final boolean z) {
        return new a<CommentListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    CommentListFragment.this.f.setEnabled(false);
                } else {
                    CommentListFragment.this.r.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    CommentListFragment.this.f.setEnabled(true);
                } else {
                    CommentListFragment.this.f.setRefreshing(false);
                    if (CommentListFragment.this.r.getData().size() >= 20) {
                        CommentListFragment.this.r.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    CommentListFragment.this.r.loadMoreFail();
                    return;
                }
                CommentListFragment.this.r.setNewData(null);
                CommentListFragment.this.r.setEmptyView(CommentListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(CommentListResponseModel commentListResponseModel, int i) {
                if (commentListResponseModel == null || commentListResponseModel.getCode() != 200) {
                    if (z) {
                        CommentListFragment.this.r.loadMoreFail();
                        return;
                    }
                    CommentListFragment.this.r.setNewData(null);
                    CommentListFragment.this.r.setEmptyView(CommentListFragment.this.h);
                } else if (e.b(commentListResponseModel.getData())) {
                    if (z) {
                        CommentListFragment.this.r.addData((Collection<? extends T>) commentListResponseModel.getData());
                        CommentListFragment.this.r.loadMoreComplete();
                    } else {
                        CommentListFragment.this.s();
                        CommentListFragment.this.r.setNewData(commentListResponseModel.getData());
                    }
                    if (commentListResponseModel.getData().size() < 20) {
                        CommentListFragment.this.r.loadMoreEnd(CommentListFragment.this.k);
                    } else {
                        CommentListFragment.this.n = CommentListFragment.this.n + 1;
                    }
                } else if (z) {
                    CommentListFragment.this.r.loadMoreEnd(CommentListFragment.this.k);
                } else {
                    CommentListFragment.this.r.setNewData(null);
                    CommentListFragment.this.r.setEmptyView(CommentListFragment.this.g);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.s == null) {
            this.s = this.c.getLayoutInflater().inflate(R.layout.f177myvisit, (ViewGroup) this.e.getParent(), false);
            this.t = (TextView) this.s.findViewById(R.id.comment_head_count);
        } else {
            ViewParent parent = this.s.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.s);
            }
        }
        this.r.addHeaderView(this.s);
        this.t.setText("全部评论（" + this.f401q + "条）");
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

    public boolean o() {
        return false;
    }
}
