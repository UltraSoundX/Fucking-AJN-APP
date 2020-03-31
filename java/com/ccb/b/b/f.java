package com.ccb.b.b;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class f implements OnTouchListener {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a(motionEvent.getAction(), this.a.a, null);
        return false;
    }
}
