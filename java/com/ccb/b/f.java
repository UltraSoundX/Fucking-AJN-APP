package com.ccb.b;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

class f implements OnFocusChangeListener {
    final /* synthetic */ OnFocusChangeListener a;
    final /* synthetic */ a b;

    f(a aVar, OnFocusChangeListener onFocusChangeListener) {
        this.b = aVar;
        this.a = onFocusChangeListener;
    }

    public void onFocusChange(View view, boolean z) {
        if (this.a != null) {
            this.a.onFocusChange(view, z);
        }
        if (!z) {
            if (!this.b.w) {
            }
            this.b.h((EditText) view);
            return;
        }
        this.b.d((EditText) view);
    }
}
