package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v7.widget.at;
import android.util.AttributeSet;
import android.view.View;

public class TabItem extends View {
    public final CharSequence a;
    public final Drawable b;
    public final int c;

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        at a2 = at.a(context, attributeSet, R.styleable.TabItem);
        this.a = a2.c(R.styleable.TabItem_android_text);
        this.b = a2.a(R.styleable.TabItem_android_icon);
        this.c = a2.g(R.styleable.TabItem_android_layout, 0);
        a2.a();
    }
}
