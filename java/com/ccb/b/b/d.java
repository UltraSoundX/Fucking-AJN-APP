package com.ccb.b.b;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class d implements OnTouchListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a(motionEvent.getAction(), this.a.a, this.a.e);
        return false;
    }
}
