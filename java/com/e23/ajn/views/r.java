package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;

/* compiled from: UserPrivacyDialog */
public class r extends Dialog implements OnClickListener {
    private Context a;
    private TextView b = ((TextView) findViewById(R.id.user_privacy_dialog_layout_title));
    private TextView c = ((TextView) findViewById(R.id.user_privacy_dialog_layout_content));
    private Button d;

    public r(Context context) {
        super(context, R.style.dm_alert_dialog);
        this.a = context;
        setContentView(R.layout.user_privacy_dialog_layout);
        this.c.setText(Html.fromHtml("<font color='#000000'>请充分阅读并理解</font><font color='#2882E9'>《用户协议和隐私政策》</font>"));
        this.c.setOnClickListener(this);
        this.d = (Button) findViewById(R.id.user_privacy_dialog_btn_know);
        this.d.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_privacy_dialog_btn_know /*2131821207*/:
                cancel();
                return;
            case R.id.user_privacy_dialog_layout_content /*2131821209*/:
                Intent intent = new Intent(this.a, SwipeBackCommonActivity.class);
                intent.putExtra(SwipeBackCommonActivity.TAG, 1);
                intent.putExtra(SwipeBackCommonActivity.URL, "http://appc.e23.cn/secret/");
                this.a.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
