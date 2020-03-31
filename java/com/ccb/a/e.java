package com.ccb.a;

import android.text.Editable;
import android.text.TextWatcher;

class e implements TextWatcher {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String str = "";
        for (int i = 0; i < editable.length(); i++) {
            str = str + "*";
        }
        this.a.d.e.loadUrl("javascript:setInputCallBack('" + this.a.a + "','" + str + "')");
    }
}
