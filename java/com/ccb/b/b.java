package com.ccb.b;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.ccb.b.b.g;
import com.ccb.b.b.l;
import com.ccb.b.b.m;
import com.ccb.b.b.n;
import com.ccb.b.c.a;
import com.ccb.b.c.c;
import com.ccb.b.c.d;
import java.util.HashMap;

public class b extends LinearLayout implements l {
    public static DisplayMetrics b;
    HashMap<Integer, g> a;
    private LayoutParams c;
    private l d;
    private int e = 1;
    private TextView f = null;
    private d g = null;
    private c h = null;
    private a i = null;
    private HashMap<Integer, TextView> j;
    private final TextView k;
    private final TextView l;
    private final TextView m;
    private final float n = 16.0f;
    private Context o;
    private boolean p = false;

    /* renamed from: q reason: collision with root package name */
    private boolean f396q = false;

    public b(Context context, DisplayMetrics displayMetrics) {
        super(context);
        com.ccb.b.b.b.setOnKeysListener(this);
        this.o = context;
        setOnKeysListener(new m());
        this.a = new HashMap<>();
        this.j = new HashMap<>();
        b = displayMetrics;
        setOrientation(1);
        int i2 = (int) (b.density * 5.0f);
        if (b.density >= 2.0f) {
            int i3 = (int) (b.density * 7.0f);
        } else {
            int i4 = (int) (b.density * 5.0f);
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        if (VERSION.SDK_INT > 10) {
            setMotionEventSplittingEnabled(false);
        }
        int i5 = ((b.heightPixels * 2) / 25) - i2;
        linearLayout.setBackgroundDrawable(m.a("titlebar.png"));
        addView(linearLayout, new LayoutParams(-1, i5));
        LayoutParams layoutParams = new LayoutParams(0, -2, 0.8f);
        this.k = new n(context, "符");
        this.l = new n(context, "Abc");
        this.m = new n(context, "123");
        this.k.setOnClickListener(new c(this));
        linearLayout.addView(this.k, layoutParams);
        this.m.setOnClickListener(new d(this));
        linearLayout.addView(this.m, layoutParams);
        this.l.setOnClickListener(new e(this));
        linearLayout.addView(this.l, layoutParams);
        this.j.put(Integer.valueOf(78), this.m);
        this.j.put(Integer.valueOf(180), this.l);
        this.j.put(Integer.valueOf(120), this.k);
        LayoutParams layoutParams2 = new LayoutParams(0, -2, 3.0f);
        this.f = new TextView(context);
        this.f.setText("中国建设银行安全键盘");
        this.f.getPaint().setFakeBoldText(false);
        this.f.setTextSize(16.0f);
        this.f.setTextColor(-1);
        this.f.setGravity(17);
        linearLayout.addView(this.f, layoutParams2);
        LayoutParams layoutParams3 = new LayoutParams(0, -2, 1.0f);
        com.ccb.b.b.a aVar = new com.ccb.b.b.a(context, "完成", 66, Color.parseColor("#09b6f2"));
        aVar.setTextSize(16.0f);
        linearLayout.addView(aVar, layoutParams3);
        this.c = new LayoutParams(-1, -1);
        this.i = new a(context);
        addView(this.i, this.c);
        this.a.put(Integer.valueOf(180), this.i);
        this.h = new c(context);
        addView(this.h, this.c);
        this.a.put(Integer.valueOf(78), this.h);
        this.g = new d(context);
        addView(this.g, this.c);
        this.a.put(Integer.valueOf(120), this.g);
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        if (this.e == 0) {
            this.i.setVisibility(0);
        } else if (this.e == 1) {
            this.h.setVisibility(0);
        } else if (this.e == 2) {
            this.g.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        if (!this.f396q) {
            for (Integer intValue : this.a.keySet()) {
                ((g) this.a.get(Integer.valueOf(intValue.intValue()))).a();
            }
        }
        super.onAttachedToWindow();
    }

    public void setOnKeysListener(l lVar) {
        this.d = lVar;
    }

    @Deprecated
    public void setShift(boolean z) {
    }

    public void setNormalArrange(boolean z) {
        this.f396q = z;
    }

    public void setKeyboardLocked(boolean z) {
        this.p = z;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
    }

    public void setKeybordType(int i2) {
        this.e = i2;
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        if (i2 == 0) {
            this.i.setVisibility(0);
            this.k.setVisibility(0);
            this.m.setVisibility(0);
        } else if (i2 == 1) {
            this.h.setVisibility(0);
            this.k.setVisibility(0);
            this.l.setVisibility(0);
        } else if (i2 == 2) {
            this.g.setVisibility(0);
            this.l.setVisibility(0);
            this.m.setVisibility(0);
        }
    }

    private void b(int i2) {
        switch (i2) {
            case 78:
            case 120:
            case 180:
                for (Integer intValue : this.a.keySet()) {
                    int intValue2 = intValue.intValue();
                    ((g) this.a.get(Integer.valueOf(intValue2))).setVisibility(intValue2 == i2 ? 0 : 8);
                }
                for (Integer intValue3 : this.j.keySet()) {
                    int intValue4 = intValue3.intValue();
                    ((TextView) this.j.get(Integer.valueOf(intValue4))).setVisibility(intValue4 == i2 ? 8 : 0);
                }
                return;
            default:
                return;
        }
    }

    public boolean a(com.ccb.b.b.b bVar, int i2, String str) {
        this.d.a(bVar, i2, str);
        return false;
    }

    public void a(int i2) {
        if (!this.p) {
            b(i2);
        } else {
            Toast.makeText(this.o, "请使用当前键盘", 0).show();
        }
    }
}
