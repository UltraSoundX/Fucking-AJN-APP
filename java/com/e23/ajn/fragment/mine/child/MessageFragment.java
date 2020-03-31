package com.e23.ajn.fragment.mine.child;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.i;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.e23.ajn.R;
import com.e23.ajn.adapter.MessageAdapter;
import com.e23.ajn.b.e;
import com.e23.ajn.b.j;
import com.e23.ajn.fragment.BaseListFragment;
import com.e23.ajn.model.MessageBean;
import com.e23.ajn.model.MessageListResponseModel;
import com.e23.ajn.views.WrapContentLinearLayoutManager;
import com.e23.ajn.views.k;
import com.jaeger.library.a;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Collection;
import java.util.Map;
import me.yokeyword.fragmentation.d;
import okhttp3.Call;
import okhttp3.Request;

public class MessageFragment extends BaseListFragment {
    private String o;
    /* access modifiers changed from: private */
    public MessageAdapter p;

    public static MessageFragment a(String str) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userid", str);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.o = getArguments().getString("userid");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        r();
        a.b(this.c, 0, null);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private void r() {
        this.p = new MessageAdapter(this.c, null, this.o);
    }

    public boolean h() {
        return true;
    }

    public boolean o() {
        return false;
    }

    public String i() {
        return getString(R.string.fragment_mine_tv_message);
    }

    public BaseQuickAdapter j() {
        return this.p;
    }

    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        a((d) MessageListFragment.a((MessageBean) this.p.getData().get(i)));
        if (((MessageBean) this.p.getData().get(i)).getStatus().equals("1")) {
            ((MessageBean) this.p.getData().get(i)).setStatus("0");
            com.e23.ajn.a.a.b--;
            e.a(this.c).c(new j());
            this.p.notifyDataSetChanged();
        }
    }

    public String k() {
        return "http://appc.e23.cn/index.php?m=api&c=message&a=myMessage";
    }

    public Map<String, String> b(boolean z) {
        return null;
    }

    public Callback c(final boolean z) {
        return new com.e23.ajn.c.a<MessageListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                if (z) {
                    MessageFragment.this.f.setEnabled(false);
                } else {
                    MessageFragment.this.p.setEnableLoadMore(false);
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                if (z) {
                    MessageFragment.this.f.setEnabled(true);
                } else {
                    MessageFragment.this.f.setRefreshing(false);
                    if (MessageFragment.this.p.getData().size() >= 20) {
                        MessageFragment.this.p.setEnableLoadMore(true);
                    }
                }
                k.a();
            }

            public void onError(Call call, Exception exc, int i) {
                if (z) {
                    MessageFragment.this.p.loadMoreFail();
                    return;
                }
                MessageFragment.this.p.setNewData(null);
                MessageFragment.this.p.setEmptyView(MessageFragment.this.h);
            }

            /* renamed from: a */
            public void onResponse(MessageListResponseModel messageListResponseModel, int i) {
                if (messageListResponseModel == null || messageListResponseModel.getCode() != 200) {
                    if (z) {
                        MessageFragment.this.p.loadMoreFail();
                        return;
                    }
                    MessageFragment.this.p.setNewData(null);
                    MessageFragment.this.p.setEmptyView(MessageFragment.this.h);
                } else if (com.e23.ajn.d.e.b(messageListResponseModel.getData())) {
                    if (z) {
                        MessageFragment.this.p.addData((Collection<? extends T>) messageListResponseModel.getData());
                        MessageFragment.this.p.loadMoreComplete();
                    } else {
                        MessageFragment.this.p.setNewData(messageListResponseModel.getData());
                    }
                    if (messageListResponseModel.getData().size() < 20) {
                        MessageFragment.this.p.loadMoreEnd(MessageFragment.this.k);
                    } else {
                        MessageFragment.this.n = MessageFragment.this.n + 1;
                    }
                } else if (z) {
                    MessageFragment.this.p.loadMoreEnd(MessageFragment.this.k);
                } else {
                    MessageFragment.this.p.setNewData(null);
                    MessageFragment.this.p.setEmptyView(MessageFragment.this.g);
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

    public void onDestroy() {
        super.onDestroy();
    }
}
