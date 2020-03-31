package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.e23.ajn.R;

/* compiled from: ContentDialog */
public class f extends Dialog implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private View d;
    private String e;
    private a f;
    private Context g;

    /* compiled from: ContentDialog */
    public interface a {
        void a();
    }

    public f(Context context, String str, a aVar) {
        super(context, R.style.dialog_style);
        this.g = context;
        this.e = str;
        this.f = aVar;
        this.d = View.inflate(context, R.layout.design_navigation_item_subheader, null);
        a();
    }

    private void a() {
        setContentView(this.d, new LayoutParams(this.g.getResources().getDimensionPixelSize(R.dimen.dp_280), -2));
        setCancelable(false);
        this.a = (TextView) findViewById(R.id.tv_content);
        this.b = (TextView) findViewById(R.id.tv_confirm);
        this.c = (TextView) findViewById(R.id.tv_cancel);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.a.setText(this.e);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel /*2131820836*/:
                dismiss();
                return;
            case R.id.tv_confirm /*2131820837*/:
                if (this.f != null) {
                    this.f.a();
                }
                dismiss();
                return;
            default:
                return;
        }
    }
}
