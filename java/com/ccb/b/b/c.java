package com.ccb.b.b;

import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onClick(View view) {
        if (this.a.i != null) {
            this.a.i.onClick(view);
        }
        b.d.a(this.a, this.a.a, this.a.e);
    }
}
