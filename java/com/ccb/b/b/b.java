package com.ccb.b.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

public abstract class b extends Button {
    public static int c = 18;
    /* access modifiers changed from: private */
    public static l d;
    protected int a;
    protected String b;
    /* access modifiers changed from: private */
    public String e;
    private boolean f = true;
    private Drawable g;
    private Drawable h;
    /* access modifiers changed from: private */
    public OnClickListener i;

    /* access modifiers changed from: protected */
    public void setShowFloat(boolean z) {
        this.f = z;
    }

    protected b(Context context, String str, String str2, int i2, float f2, Drawable drawable, Drawable drawable2) {
        super(context);
        setTextSize((float) c);
        this.g = drawable;
        this.h = drawable2;
        this.e = str2;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        int i3 = (int) ((com.ccb.b.b.b.density * 5.0f) / 3.0f);
        layoutParams.setMargins(i3, i3, i3, i3);
        setPadding(0, 0, 0, 0);
        layoutParams.weight = f2;
        setLayoutParams(layoutParams);
        this.b = str;
        this.a = i2;
        setText(this.b);
        setAllCaps(false);
        getPaint().setFakeBoldText(true);
        setOnClickListener(new c(this));
        setOnTouchListener(new d(this));
        setLongClickable(false);
        a(this.h, this.g);
    }

    public b(Context context, String str, int i2, int i3) {
        super(context);
        this.b = str;
        this.a = i2;
        setTextSize((float) c);
        getPaint().setFakeBoldText(true);
        setText(this.b);
        setBackgroundDrawable(null);
        setAllCaps(false);
        setTextColor(i3);
        setLongClickable(false);
        setOnClickListener(new e(this));
        setOnTouchListener(new f(this));
    }

    public void setPrivateOnClickListener(OnClickListener onClickListener) {
        this.i = onClickListener;
    }

    public void a(String str, String str2) {
        this.g = m.a(str);
        this.h = m.a(str2);
        a(this.h, this.g);
    }

    public void b(String str, String str2) {
        setText(str);
        this.e = str2;
    }

    public String getValue() {
        return this.e;
    }

    public static void setOnKeysListener(l lVar) {
        d = lVar;
    }

    /* access modifiers changed from: protected */
    public void a(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (drawable != null) {
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, drawable);
        }
        if (drawable2 != null) {
            stateListDrawable.addState(View.EMPTY_STATE_SET, drawable2);
        }
        setBackgroundDrawable(stateListDrawable);
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3) {
        setTextColor(new ColorStateList(new int[][]{View.PRESSED_ENABLED_STATE_SET, View.EMPTY_STATE_SET}, new int[]{i2, i3}));
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3, String str) {
        if (i2 != 0) {
            if (i2 == 1) {
            }
        } else if (str == null) {
            switch (i3) {
            }
        }
    }
}
