package com.e23.ajn.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.e23.ajn.R;
import com.e23.ajn.b.e;
import com.e23.ajn.b.f;
import com.e23.ajn.c.a;
import com.e23.ajn.d.p;
import com.e23.ajn.d.w;
import com.e23.ajn.model.BaseResponse;
import com.tencent.android.tpush.common.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;

/* compiled from: DialogInputYqm */
public class g extends Dialog implements OnClickListener {
    public EditText a;
    public Button b;
    public Button c;
    private View d;
    /* access modifiers changed from: private */
    public Context e;
    /* access modifiers changed from: private */
    public g f = this;
    /* access modifiers changed from: private */
    public InputMethodManager g;
    private Timer h;

    public g(Context context) {
        super(context, R.style.NewsplDialog);
        this.e = context;
        setCanceledOnTouchOutside(false);
        getWindow().setGravity(17);
        this.d = LayoutInflater.from(context).inflate(R.layout.design_navigation_menu, null);
        this.a = (EditText) this.d.findViewById(R.id.dialog_loginname);
        this.b = (Button) this.d.findViewById(R.id.confirm);
        this.b.setOnClickListener(this);
        this.c = (Button) this.d.findViewById(R.id.cancel);
        this.c.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.d);
        getWindow().setBackgroundDrawableResource(17170445);
        this.g = (InputMethodManager) this.a.getContext().getSystemService("input_method");
    }

    public void show() {
        super.show();
        if (this.h == null) {
            this.h = new Timer();
        }
        this.h.schedule(new TimerTask() {
            public void run() {
                g.this.g.showSoftInput(g.this.a, 0);
            }
        }, 200);
    }

    public void dismiss() {
        this.g.hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        if (this.h != null) {
            this.h = null;
        }
        super.dismiss();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel /*2131820840*/:
                this.f.dismiss();
                return;
            case R.id.confirm /*2131820841*/:
                a();
                return;
            default:
                return;
        }
    }

    private void a() {
        if (this.a.getText().toString().equals("")) {
            Toast.makeText(this.e, R.string.dialog_inputyqm, 0).show();
            return;
        }
        k.b(this.e, this.e.getString(R.string.waiting));
        ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=user&a=fixPromocode")).addParams(Constants.FLAG_TOKEN, p.b(Constants.FLAG_TOKEN, "")).addParams("promocode", this.a.getText().toString()).build().execute(new a<BaseResponse>() {
            public void onError(Call call, Exception exc, int i) {
                k.b();
                w.b(g.this.e.getString(R.string.fragment_suggestion_yzjy_submit_fail));
            }

            /* renamed from: a */
            public void onResponse(BaseResponse baseResponse, int i) {
                k.b();
                if (baseResponse.getCode() != 200) {
                    w.b(baseResponse.getMsg());
                    return;
                }
                w.b(baseResponse.getMsg());
                g.this.f.dismiss();
                e.a((Activity) g.this.e).c(new f());
            }
        });
    }
}
