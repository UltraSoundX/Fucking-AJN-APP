package com.ccb.b.c;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

class b implements OnClickListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        String lowerCase;
        String lowerCase2;
        Log.i("keyboard-testing", "mIsUpper: " + this.a.f);
        this.a.f = !this.a.f;
        Iterator it = this.a.getCacheKeys().iterator();
        while (it.hasNext()) {
            com.ccb.b.b.b bVar = (com.ccb.b.b.b) it.next();
            String charSequence = bVar.getText().toString();
            String str = bVar.getValue().toString();
            if (this.a.f) {
                lowerCase = charSequence.toUpperCase();
            } else {
                lowerCase = charSequence.toLowerCase();
            }
            if (this.a.f) {
                lowerCase2 = str.toUpperCase();
            } else {
                lowerCase2 = str.toLowerCase();
            }
            bVar.b(lowerCase, lowerCase2);
        }
        ((com.ccb.b.b.b) view).a(this.a.f ? "up_case_press.png" : "up_case.png", this.a.f ? "up_case.png" : "up_case_press.png");
    }
}
