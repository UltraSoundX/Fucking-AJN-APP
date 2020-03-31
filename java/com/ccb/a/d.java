package com.ccb.a;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.ccb.b.a;
import com.ccb.b.k;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ a d;

    d(a aVar, String str, int i, int i2) {
        this.d = aVar;
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public void run() {
        a aVar = (a) this.d.f.get(this.a);
        if (aVar == null) {
        }
        if (aVar == null) {
            aVar = (a) k.a(this.d.c, this.d.b);
            EditText editText = new EditText(this.d.c);
            LinearLayout linearLayout = new LinearLayout(this.d.c);
            LayoutParams layoutParams = new LayoutParams(1, 1);
            linearLayout.setOrientation(1);
            linearLayout.setVisibility(4);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.addView(editText);
            this.d.d.addView(linearLayout, 0);
            aVar.a(true);
            aVar.a(editText);
            editText.addTextChangedListener(new e(this));
            this.d.f.put(this.a, aVar);
            this.d.g.put(this.a, editText);
        }
        a aVar2 = aVar;
        EditText editText2 = (EditText) this.d.g.get(this.a);
        if (editText2 != null) {
            aVar2.a(this.b);
            aVar2.b(this.c);
            editText2.setText("");
            editText2.bringToFront();
            editText2.setFocusable(true);
            editText2.requestFocus();
        }
    }
}
