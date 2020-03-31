package com.e23.ajn.fragment.mine.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.adapter.CommentListAdapter;
import com.e23.ajn.b.j;
import com.e23.ajn.c.a;
import com.e23.ajn.d.e;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.CommentListResponseModel;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

public class MineCommentListFragment extends BaseListFragment {
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public CommentListAdapter p;

    public static MineCommentListFragment a(int i) {
        MineCommentListFragment mineCommentListFragment = new MineCommentListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        mineCommentListFragment.setArguments(bundle);
        return mineCommentListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getInt("type");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        if (this.o == 0) {
            this.p = new CommentListAdapter(this.c, null, true, 1);
        } else {
            this.p = new CommentListAdapter(this.c, null, true, 2);
        }
    }

    public boolean h() {
        return false;
    }

    public boolean o() {
        return false;
    }

    public String i() {
        return getString(R.string.fragment_mine_tv_mycomment);
    }

    public BaseQuickAdapter j() {
        return this.p;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public String k() {
        if (this.o == 0) {
            return "http://appc.e23.cn/index.php?m=api&c=comment&a=myCommentsToMe";
        }
        return "http://appc.e23.cn/index.php?m=api&c=comment&a=myComments";
    }

    public Map<String, String> b(boolean z) {
        return null;
    }

    public Callback c(final boolean z) {
        return new a<CommentListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    MineCommentListFragment.this.f.setEnabled(false);
                    return;
                }
                MineCommentListFragment.this.p.setEnableLoadMore(false);
                MineCommentListFragment.this.f.setRefreshing(true);
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    MineCommentListFragment.this.f.setEnabled(true);
                } else {
                    MineCommentListFragment.this.f.setRefreshing(false);
                    if (MineCommentListFragment.this.p.getData().size() >= 20) {
                        MineCommentListFragment.this.p.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    MineCommentListFragment.this.p.loadMoreFail();
                    return;
                }
                MineCommentListFragment.this.p.setNewData(null);
                MineCommentListFragment.this.p.setEmptyView(MineCommentListFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(CommentListResponseModel commentListResponseModel, int i) {
                if (commentListResponseModel != null && commentListResponseModel.getCode() == 200) {
                    if (e.b(commentListResponseModel.getData())) {
                        if (z) {
                            MineCommentListFragment.this.p.addData((Collection<? extends T>) commentListResponseModel.getData());
                            MineCommentListFragment.this.p.loadMoreComplete();
                        } else {
                            MineCommentListFragment.this.p.setNewData(commentListResponseModel.getData());
                        }
                        if (commentListResponseModel.getData().size() < 20) {
                            MineCommentListFragment.this.p.loadMoreEnd(MineCommentListFragment.this.k);
                        } else {
                            MineCommentListFragment.this.n = MineCommentListFragment.this.n + 1;
                        }
                    } else if (z) {
                        MineCommentListFragment.this.p.loadMoreEnd(MineCommentListFragment.this.k);
                    } else {
                        MineCommentListFragment.this.p.setNewData(null);
                        MineCommentListFragment.this.p.setEmptyView(MineCommentListFragment.this.g);
                    }
                    if (MineCommentListFragment.this.o == 0) {
                        com.e23.ajn.a.a.c = 0;
                        com.e23.ajn.b.e.a(MineCommentListFragment.this.c).c(new j());
                    }
                } else if (z) {
                    MineCommentListFragment.this.p.loadMoreFail();
                } else {
                    MineCommentListFragment.this.p.setNewData(null);
                    MineCommentListFragment.this.p.setEmptyView(MineCommentListFragment.this.h);
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
}
