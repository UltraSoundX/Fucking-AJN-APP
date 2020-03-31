package com.ccb.b;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

class h implements OnKeyListener {
    final /* synthetic */ a a;

    h(a aVar) {
        this.a = aVar;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4 || this.a.p == null || !this.a.p.isShowing()) {
            return false;
        }
        this.a.h((EditText) view);
        return true;
    }
}
