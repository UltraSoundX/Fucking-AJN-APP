package com.ccb.b.b;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class o implements OnTouchListener {
    final /* synthetic */ String a;
    final /* synthetic */ n b;

    o(n nVar, String str) {
        this.b = nVar;
        this.a = str;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            char c = 65535;
            if (this.a.equals("符")) {
                c = 0;
            }
            if (this.a.equals("Abc")) {
                c = 1;
            }
            if (this.a.equals("123")) {
                c = 2;
            }
            switch (c) {
            }
        } else if (motionEvent.getAction() == 1) {
        }
        return false;
    }
}
