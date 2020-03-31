package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.e23.ajn.R;

/* compiled from: TipProgress */
public class p extends Dialog {
    private String a;

    public p(Context context, int i, String str) {
        super(context, i);
        this.a = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.view_dialog_tip);
        ((TextView) findViewById(R.id.view_dialog_tv_tip)).setText(this.a);
    }
}
