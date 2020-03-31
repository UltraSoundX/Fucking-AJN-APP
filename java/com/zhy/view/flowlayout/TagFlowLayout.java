package com.zhy.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TagFlowLayout extends FlowLayout implements C0084a {
    private a d;
    private int e;
    private Set<Integer> f;
    private a g;
    /* access modifiers changed from: private */
    public b h;

    public interface a {
        void a(Set<Integer> set);
    }

    public interface b {
        boolean a(View view, int i, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -1;
        this.f = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.e = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        obtainStyledAttributes.recycle();
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            b bVar = (b) getChildAt(i3);
            if (bVar.getVisibility() != 8 && bVar.getTagView().getVisibility() == 8) {
                bVar.setVisibility(8);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setOnSelectListener(a aVar) {
        this.g = aVar;
    }

    public void setOnTagClickListener(b bVar) {
        this.h = bVar;
    }

    public void setAdapter(a aVar) {
        this.d = aVar;
        this.d.a((C0084a) this);
        this.f.clear();
        a();
    }

    private void a() {
        removeAllViews();
        a aVar = this.d;
        HashSet a2 = this.d.a();
        for (final int i = 0; i < aVar.b(); i++) {
            View a3 = aVar.a(this, i, aVar.a(i));
            final b bVar = new b(getContext());
            a3.setDuplicateParentStateEnabled(true);
            if (a3.getLayoutParams() != null) {
                bVar.setLayoutParams(a3.getLayoutParams());
            } else {
                MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f));
                bVar.setLayoutParams(marginLayoutParams);
            }
            a3.setLayoutParams(new LayoutParams(-1, -1));
            bVar.addView(a3);
            addView(bVar);
            if (a2.contains(Integer.valueOf(i))) {
                a(i, bVar);
            }
            if (this.d.a(i, aVar.a(i))) {
                a(i, bVar);
            }
            a3.setClickable(false);
            bVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TagFlowLayout.this.a(bVar, i);
                    if (TagFlowLayout.this.h != null) {
                        TagFlowLayout.this.h.a(bVar, i, TagFlowLayout.this);
                    }
                }
            });
        }
        this.f.addAll(a2);
    }

    public void setMaxSelectCount(int i) {
        if (this.f.size() > i) {
            Log.w("TagFlowLayout", "you has already select more than " + i + " views , so it will be clear .");
            this.f.clear();
        }
        this.e = i;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.f);
    }

    private void a(int i, b bVar) {
        bVar.setChecked(true);
        this.d.a(i, bVar.getTagView());
    }

    private void b(int i, b bVar) {
        bVar.setChecked(false);
        this.d.b(i, bVar.getTagView());
    }

    /* access modifiers changed from: private */
    public void a(b bVar, int i) {
        if (bVar.isChecked()) {
            b(i, bVar);
            this.f.remove(Integer.valueOf(i));
        } else if (this.e == 1 && this.f.size() == 1) {
            Integer num = (Integer) this.f.iterator().next();
            b(num.intValue(), (b) getChildAt(num.intValue()));
            a(i, bVar);
            this.f.remove(num);
            this.f.add(Integer.valueOf(i));
        } else if (this.e <= 0 || this.f.size() < this.e) {
            a(i, bVar);
            this.f.add(Integer.valueOf(i));
        } else {
            return;
        }
        if (this.g != null) {
            this.g.a(new HashSet(this.f));
        }
    }

    public a getAdapter() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        String str;
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_default", super.onSaveInstanceState());
        String str2 = "";
        if (this.f.size() > 0) {
            Iterator it = this.f.iterator();
            while (true) {
                str = str2;
                if (!it.hasNext()) {
                    break;
                }
                str2 = str + ((Integer) it.next()).intValue() + "|";
            }
            str2 = str.substring(0, str.length() - 1);
        }
        bundle.putString("key_choose_pos", str2);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString("key_choose_pos");
            if (!TextUtils.isEmpty(string)) {
                for (String parseInt : string.split("\\|")) {
                    int parseInt2 = Integer.parseInt(parseInt);
                    this.f.add(Integer.valueOf(parseInt2));
                    b bVar = (b) getChildAt(parseInt2);
                    if (bVar != null) {
                        a(parseInt2, bVar);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable("key_default"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public static int a(Context context, float f2) {
        return (int) ((context.getResources().getDisplayMetrics().density * f2) + 0.5f);
    }
}
