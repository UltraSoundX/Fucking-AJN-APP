package com.e23.ajn.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.e23.ajn.R;

/* compiled from: BottomBarTab */
public class d extends FrameLayout {
    private ImageView a;
    private TextView b;
    private Context c;
    private int d;
    private TextView e;
    private int f;
    private int g;

    public d(Context context, int i, int i2, CharSequence charSequence) {
        this(context, null, i, i2, charSequence);
    }

    public d(Context context, AttributeSet attributeSet, int i, int i2, CharSequence charSequence) {
        this(context, attributeSet, 0, i, i2, charSequence);
    }

    public d(Context context, AttributeSet attributeSet, int i, int i2, int i3, CharSequence charSequence) {
        super(context, attributeSet, i);
        this.d = -1;
        a(context, i2, i3, charSequence);
    }

    private void a(Context context, int i, int i2, CharSequence charSequence) {
        this.c = context;
        this.f = i;
        this.g = i2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.activeColor});
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        linearLayout.setLayoutParams(layoutParams);
        this.a = new ImageView(context);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_18);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        this.a.setImageResource(i);
        this.a.setLayoutParams(layoutParams2);
        linearLayout.addView(this.a);
        this.b = new TextView(context);
        this.b.setText(charSequence);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        this.b.setTextSize(11.0f);
        this.b.setLayoutParams(layoutParams3);
        linearLayout.addView(this.b);
        addView(linearLayout);
        int a2 = a(context, 10.0f);
        int a3 = a(context, 5.0f);
        this.e = new TextView(context);
        this.e.setBackgroundResource(R.drawable.f73color_radiobutton);
        this.e.setMinWidth(a2);
        this.e.setTextColor(-1);
        this.e.setPadding(a3, 0, a3, 0);
        this.e.setGravity(17);
        LayoutParams layoutParams4 = new LayoutParams(-2, a2);
        layoutParams4.gravity = 17;
        layoutParams4.leftMargin = a(context, 17.0f);
        layoutParams4.bottomMargin = a(context, 14.0f);
        this.e.setLayoutParams(layoutParams4);
        this.e.setVisibility(8);
        addView(this.e);
    }

    public void setShowOrHideCount(int i) {
        this.e.setVisibility(i);
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        if (z) {
            this.a.setImageResource(this.g);
            this.b.setTextColor(ContextCompat.getColor(this.c, R.color.colorBlack0));
            return;
        }
        this.a.setImageResource(this.f);
        this.b.setTextColor(ContextCompat.getColor(this.c, R.color.color_tab_un_select));
    }

    public void setTabPosition(int i) {
        this.d = i;
        if (i == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return this.d;
    }

    private int a(Context context, float f2) {
        return (int) TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }
}
