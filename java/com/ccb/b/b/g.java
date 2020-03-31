package com.ccb.b.b;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.LinearLayout;
import com.ccb.b.b;
import java.util.ArrayList;
import java.util.Random;

public abstract class g extends LinearLayout {
    protected OnKeyListener a;
    private ArrayList<b> b = new ArrayList<>();
    private ArrayList<b> c = new ArrayList<>();

    public g(Context context) {
        super(context);
        if (VERSION.SDK_INT > 10) {
            setMotionEventSplittingEnabled(false);
        }
        setBackgroundColor(Color.parseColor("#0f517b"));
        int i = (int) (b.b.density * 3.0f);
        setPadding(i, i, i, i);
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        this.a = onKeyListener;
    }

    /* access modifiers changed from: protected */
    public b a(b bVar) {
        this.b.add(bVar);
        return bVar;
    }

    /* access modifiers changed from: protected */
    public b b(b bVar) {
        this.c.add(bVar);
        return bVar;
    }

    /* access modifiers changed from: protected */
    public ArrayList<b> getCacheKeys() {
        return this.b;
    }

    public void a() {
        Random random = new Random();
        for (int i = 0; i < this.c.size(); i++) {
            int nextInt = random.nextInt(this.c.size());
            b bVar = (b) this.c.get(i);
            String charSequence = bVar.getText().toString();
            String str = bVar.getValue().toString();
            b bVar2 = (b) this.c.get(nextInt);
            bVar.b(bVar2.getText().toString(), bVar2.getValue().toString());
            bVar2.b(charSequence, str);
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            int nextInt2 = random.nextInt(this.b.size());
            b bVar3 = (b) this.b.get(i2);
            String charSequence2 = bVar3.getText().toString();
            String str2 = bVar3.getValue().toString();
            b bVar4 = (b) this.b.get(nextInt2);
            bVar3.b(bVar4.getText().toString(), bVar4.getValue().toString());
            bVar4.b(charSequence2, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
    }
}
