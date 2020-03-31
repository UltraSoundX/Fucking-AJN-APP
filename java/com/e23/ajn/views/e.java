package com.e23.ajn.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import com.e23.ajn.R;
import com.e23.ajn.activity.OutUrlActivity;
import com.e23.ajn.c.b;
import com.e23.ajn.d.f;
import com.e23.ajn.d.p;
import com.e23.ajn.d.q;
import com.e23.ajn.model.CommentBean;
import com.e23.ajn.model.PostCommentResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import okhttp3.Request;

/* compiled from: CommentDialog */
public class e extends Dialog implements OnClickListener {
    /* access modifiers changed from: private */
    public Context a;
    /* access modifiers changed from: private */
    public SmiliesEditText b;
    private Button c;
    private Button d;
    private ImageView e;
    private GridView f;
    private Timer g;
    private View h;
    /* access modifiers changed from: private */
    public InputMethodManager i;
    private String[] j;
    /* access modifiers changed from: private */
    public a k;
    private OnItemClickListener l = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            e.this.b.a(f.a[i] + "", f.b[i]);
        }
    };

    /* compiled from: CommentDialog */
    public interface a {
        void a(CommentBean commentBean);
    }

    public e(Context context, String[] strArr, a aVar) {
        super(context, R.style.dm_alert_dialog);
        this.a = context;
        this.k = aVar;
        this.j = strArr;
        this.h = View.inflate(context, R.layout.f124cube_mints_base_content_frame_with_title_header, null);
        a();
    }

    public void a(String[] strArr) {
        this.j = strArr;
        if (!strArr[8].equals("")) {
        }
    }

    private void a() {
        setContentView(this.h, new LayoutParams(q.b((Activity) this.a), -2));
        getWindow().setGravity(80);
        this.b = (SmiliesEditText) findViewById(R.id.comment_dialog_content);
        if (!this.j[8].equals("")) {
        }
        this.c = (Button) findViewById(R.id.comment_dialog_btn_left);
        this.d = (Button) findViewById(R.id.comment_dialog_btn_right);
        this.e = (ImageView) findViewById(R.id.comment_dialog_em);
        this.e.setOnClickListener(this);
        this.f = (GridView) findViewById(R.id.comment_dialog_gv_em);
        this.f.setAdapter(new com.e23.ajn.adapter.a(this.a));
        this.f.setOnItemClickListener(this.l);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.i = (InputMethodManager) this.b.getContext().getSystemService("input_method");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_dialog_btn_left /*2131820805*/:
                this.i.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
                dismiss();
                return;
            case R.id.comment_dialog_btn_right /*2131820807*/:
                if (TextUtils.isEmpty(this.b.getText().toString())) {
                    k.a(this.a, "请输入评论内容！");
                    return;
                }
                a(this.b.getText().toString());
                dismiss();
                return;
            case R.id.comment_dialog_em /*2131820809*/:
                if (this.f.getVisibility() == 8) {
                    this.f.setVisibility(0);
                    this.i.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
                    return;
                }
                this.f.setVisibility(8);
                this.i.showSoftInput(this.b, 0);
                return;
            default:
                return;
        }
    }

    public void show() {
        super.show();
        if (this.g == null) {
            this.g = new Timer();
        }
        this.g.schedule(new TimerTask() {
            public void run() {
                e.this.i.showSoftInput(e.this.b, 0);
            }
        }, 200);
    }

    public void dismiss() {
        this.i.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        if (this.g != null) {
            this.g = null;
        }
        super.dismiss();
    }

    private void a(final String str) {
        ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=comment&a=commentPost_pub")).params(b.a(null)).addParams("newsid", this.j[0]).addParams(OutUrlActivity.ARG_THUMB, this.j[1]).addParams("title", this.j[2]).addParams("wzposterid", this.j[3]).addParams("newstype", this.j[4]).addParams("replid", this.j[5]).addParams("reuserid", this.j[6]).addParams("reusername", this.j[7]).addParams("content", f.a(str)).addParams("qlimg", "").tag(this)).build().execute(new com.e23.ajn.c.a<PostCommentResponse>() {
            public void onError(Call call, Exception exc, int i) {
            }

            /* renamed from: a */
            public void onResponse(PostCommentResponse postCommentResponse, int i) {
                if (postCommentResponse != null && postCommentResponse.getCode() == 200) {
                    e.this.i.hideSoftInputFromWindow(e.this.b.getWindowToken(), 0);
                    e.this.b.setText("");
                    if (e.this.k != null) {
                        if (f.a(str).length() >= 5) {
                            com.e23.ajn.d.a.a("ctmorefive", e.this.a);
                        } else {
                            com.e23.ajn.d.a.a("ctlessfive", e.this.a);
                        }
                        postCommentResponse.getData().setAvatar(p.b("user_avatar", ""));
                        e.this.k.a(postCommentResponse.getData());
                        e.this.dismiss();
                    }
                }
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }

            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
                k.a(e.this.a);
            }
        });
    }
}
