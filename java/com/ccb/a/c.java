package com.ccb.a;

import android.text.Editable;
import android.text.TextWatcher;

class c implements TextWatcher {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.d.e.loadUrl("javascript:setInputCallBack('" + this.a.a + "','" + editable.toString() + "')");
    }
}
