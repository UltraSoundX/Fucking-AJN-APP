package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.e23.ajn.R;

/* compiled from: LoadingProgress */
public class i extends Dialog {
    public i(Context context, int i) {
        super(context, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.view_dialog_loading);
    }
}
