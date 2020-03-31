package com.e23.ajn.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.e23.ajn.R;
import com.e23.ajn.d.q;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: EditNickname */
public class h extends Dialog {
    Context a;
    private View b;
    /* access modifiers changed from: private */
    public EditText c = ((EditText) this.b.findViewById(R.id.fragment_mine_editme_et_nickname));
    private Timer d;
    /* access modifiers changed from: private */
    public InputMethodManager e;

    public h(Context context, int i, String str) {
        super(context, i);
        this.a = context;
        this.b = View.inflate(context, R.layout.f142hdcontent, null);
        this.c.setText(str);
        this.c.setSelection(str.length());
        this.e = (InputMethodManager) this.c.getContext().getSystemService("input_method");
        a();
    }

    private void a() {
        setContentView(this.b, new LayoutParams(q.b((Activity) this.a), -2));
        getWindow().setGravity(80);
        this.c.requestFocus();
    }

    public void show() {
        super.show();
        if (this.d == null) {
            this.d = new Timer();
        }
        this.d.schedule(new TimerTask() {
            public void run() {
                h.this.e.showSoftInput(h.this.c, 0);
            }
        }, 200);
    }

    public void dismiss() {
        this.e.hideSoftInputFromWindow(this.c.getWindowToken(), 0);
        if (this.d != null) {
            this.d = null;
        }
        super.dismiss();
    }
}
