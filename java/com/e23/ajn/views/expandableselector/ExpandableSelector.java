package com.e23.ajn.views.expandableselector;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.e23.ajn.R;
import com.e23.ajn.views.expandableselector.a.a;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpandableSelector extends FrameLayout {
    private List<Object> a;
    private List<View> b;
    private a c;
    private a d;
    private b e;
    private boolean f;
    private Drawable g;

    public ExpandableSelector(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandableSelector(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = Collections.EMPTY_LIST;
        this.b = new ArrayList();
        a(attributeSet);
    }

    public boolean a() {
        return this.c.b();
    }

    public void setExpandableSelectorListener(a aVar) {
        this.d = aVar;
    }

    public void setOnExpandableItemClickListener(b bVar) {
        this.e = bVar;
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.expandable_selector);
        b(obtainStyledAttributes);
        a(obtainStyledAttributes);
        c(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void a(TypedArray typedArray) {
        this.f = typedArray.getBoolean(0, false);
        this.g = getBackground();
        b();
    }

    private void b(TypedArray typedArray) {
        this.c = new a(this, typedArray.getInteger(1, ErrorCode.ERROR_CODE_LOAD_BASE), typedArray.getResourceId(3, 17432581), typedArray.getResourceId(4, 17432582), typedArray.getResourceId(5, 17432582));
    }

    private void c(TypedArray typedArray) {
        this.c.a(typedArray.getBoolean(2, false));
    }

    private void b() {
        if (this.f) {
            if (a()) {
                setBackgroundDrawable(this.g);
            } else {
                setBackgroundResource(17170445);
            }
        }
    }

    private void setExpandableItems(List<Object> list) {
        this.a = new ArrayList(list);
    }
}
