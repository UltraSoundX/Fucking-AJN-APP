package android.support.design.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.R;
import android.support.design.internal.c;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class MaterialCardView extends CardView {
    private final a e;

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray a = c.a(context, attributeSet, R.styleable.MaterialCardView, i, R.style.Widget_MaterialComponents_CardView, new int[0]);
        this.e = new a(this);
        this.e.a(a);
        a.recycle();
    }

    public void setStrokeColor(int i) {
        this.e.a(i);
    }

    public int getStrokeColor() {
        return this.e.a();
    }

    public void setStrokeWidth(int i) {
        this.e.b(i);
    }

    public int getStrokeWidth() {
        return this.e.b();
    }

    public void setRadius(float f) {
        super.setRadius(f);
        this.e.c();
    }
}
