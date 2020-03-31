package com.ccb.b;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

class g implements OnTouchListener {
    final /* synthetic */ a a;

    g(a aVar) {
        this.a = aVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        EditText editText = (EditText) view;
        this.a.g(editText);
        editText.setCursorVisible(true);
        editText.requestFocus();
        int length = editText.getText().length();
        if (length >= 0) {
            editText.setSelection(length);
        }
        if (this.a.p == null) {
            this.a.p = this.a.d();
        }
        if (editText.isFocused() && !this.a.p.isShowing()) {
            this.a.d(editText);
        }
        return true;
    }
}
