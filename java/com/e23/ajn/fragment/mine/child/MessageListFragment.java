package com.e23.ajn.fragment.mine.child;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter.UpFetchListener;
import com.e23.ajn.R;
import com.e23.ajn.adapter.MessageListAdapter;
import com.e23.ajn.base.BaseSwipeBackFragment;
import com.e23.ajn.c.b;
import com.e23.ajn.d.e;
import com.e23.ajn.d.f;
import com.e23.ajn.d.w;
import com.e23.ajn.model.BaseResponse;
import com.e23.ajn.model.MessageBean;
import com.e23.ajn.model.MessageListResponseModel;
import com.e23.ajn.model.MessageSendResponse;
import com.jaeger.library.a;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.Collection;
import okhttp3.Call;
import okhttp3.Request;

public class MessageListFragment extends BaseSwipeBackFragment {
    private Toolbar d;
    private TextView e;
    private ImageView f;
    private MessageBean g;
    /* access modifiers changed from: private */
    public RecyclerView h;
    /* access modifiers changed from: private */
    public MessageListAdapter i;
    private String j;
    /* access modifiers changed from: private */
    public int k = 1;
    /* access modifiers changed from: private */
    public EditText l;
    private Button m;

    public static MessageListFragment a(MessageBean messageBean) {
        MessageListFragment messageListFragment = new MessageListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", messageBean);
        messageListFragment.setArguments(bundle);
        return messageListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.g = (MessageBean) getArguments().getSerializable("bean");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f137generateorder, viewGroup, false);
        if (this.g.getIscom().equals("1")) {
            this.j = this.g.getSend_from_uid();
        } else {
            this.j = this.g.getSend_to_uid();
        }
        b(inflate);
        return inflate;
    }

    private void b(View view) {
        a.b(this.c, 0, null);
        a.a((Activity) this.c);
        this.d = (Toolbar) view.findViewById(R.id.toolbar);
        this.e = (TextView) this.d.findViewById(R.id.toolbar_center_title);
        this.f = (ImageView) this.d.findViewById(R.id.toolbar_left_back);
        this.f.setVisibility(0);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MessageListFragment.this.c.onBackPressed();
            }
        });
        this.e.setVisibility(0);
        if (this.g.getIscom().equals("1")) {
            this.e.setText(this.g.getSend_from_id());
        } else {
            this.e.setText(this.g.getSend_to_id());
        }
        this.h = (RecyclerView) view.findViewById(R.id.message_list_rv);
        this.l = (EditText) view.findViewById(R.id.et_sendmessage);
        this.m = (Button) view.findViewById(R.id.btn_send);
        this.m.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!MessageListFragment.this.l.getText().toString().trim().equals("")) {
                    MessageListFragment.this.j();
                }
            }
        });
        this.i = new MessageListAdapter(this.c, null, this.g);
        this.h.setLayoutManager(new LinearLayoutManager(this.c));
        this.h.setAdapter(this.i);
        this.i.setStartUpFetchPosition(2);
        this.i.setUpFetchListener(new UpFetchListener() {
            public void onUpFetch() {
                MessageListFragment.this.k = MessageListFragment.this.k + 1;
                MessageListFragment.this.i();
            }
        });
        i();
        a(this.g.getMessageid());
    }

    /* access modifiers changed from: private */
    public void i() {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=message&a=ftMessage")).params(b.a(null)).addParams("touid", this.j).addParams("page", this.k + "").build().execute(new com.e23.ajn.c.a<MessageListResponseModel>() {
            public void onBefore(Request request, int i) {
                MessageListFragment.this.i.setUpFetching(true);
                MessageListFragment.this.i.setUpFetchEnable(false);
            }

            public void onError(Call call, Exception exc, int i) {
                MessageListFragment.this.i.setUpFetching(false);
                MessageListFragment.this.i.setUpFetchEnable(false);
            }

            /* renamed from: a */
            public void onResponse(MessageListResponseModel messageListResponseModel, int i) {
                if (messageListResponseModel == null || messageListResponseModel.getCode() != 200) {
                    MessageListFragment.this.i.setUpFetching(false);
                    MessageListFragment.this.i.setUpFetchEnable(false);
                } else if (e.b(messageListResponseModel.getData())) {
                    MessageListFragment.this.i.addData(0, (Collection<? extends T>) messageListResponseModel.getData());
                    MessageListFragment.this.i.notifyDataSetChanged();
                    MessageListFragment.this.i.setUpFetching(false);
                    if (messageListResponseModel.getData().size() == 20) {
                        MessageListFragment.this.i.setUpFetchEnable(true);
                    } else {
                        MessageListFragment.this.i.setUpFetchEnable(false);
                    }
                    if (MessageListFragment.this.k == 1) {
                        MessageListFragment.this.h.a(MessageListFragment.this.i.getItemCount() - 1);
                    }
                } else {
                    MessageListFragment.this.i.setUpFetchEnable(false);
                    MessageListFragment.this.i.setUpFetching(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void j() {
        String send_to_id;
        String send_to_uid;
        if (this.g.getIscom().equals("1")) {
            send_to_id = this.g.getSend_from_id();
            send_to_uid = this.g.getSend_from_uid();
        } else {
            send_to_id = this.g.getSend_to_id();
            send_to_uid = this.g.getSend_to_uid();
        }
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=message&a=sendMessage")).params(b.a(null)).addParams("send_to_uname", send_to_id).addParams("send_to_uid", send_to_uid).addParams("content", f.a(this.l.getText().toString().trim())).addParams("replyid", "0").build().execute(new com.e23.ajn.c.a<MessageSendResponse>() {
            public void onError(Call call, Exception exc, int i) {
                System.out.println("error");
            }

            /* renamed from: a */
            public void onResponse(MessageSendResponse messageSendResponse, int i) {
                if (messageSendResponse == null || messageSendResponse.getCode() != 200) {
                    w.b(messageSendResponse.getMsg());
                    return;
                }
                MessageListFragment.this.i.getData().add(messageSendResponse.getData());
                MessageListFragment.this.i.notifyDataSetChanged();
                MessageListFragment.this.l.setText("");
                MessageListFragment.this.l.clearFocus();
                MessageListFragment.this.h();
                w.b(messageSendResponse.getMsg());
                MessageListFragment.this.h.a(MessageListFragment.this.i.getItemCount() - 1);
            }
        });
    }

    public void h() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.c.getSystemService("input_method");
        if (inputMethodManager.isActive() && this.c.getCurrentFocus() != null && this.c.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(this.c.getCurrentFocus().getWindowToken(), 2);
        }
    }

    private void a(String str) {
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=message&a=setMessageStatus")).params(b.a(null)).addParams("messageid", str).build().execute(new com.e23.ajn.c.a<BaseResponse>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(BaseResponse baseResponse, int i) {
            }
        });
    }
}
