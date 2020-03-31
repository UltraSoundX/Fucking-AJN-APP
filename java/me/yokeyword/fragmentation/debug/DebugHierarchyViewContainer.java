package me.yokeyword.fragmentation.debug;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import me.yokeyword.fragmentation.R;

public class DebugHierarchyViewContainer extends ScrollView {
    /* access modifiers changed from: private */
    public Context a;
    private LinearLayout b;
    private LinearLayout c;
    private int d;
    private int e;

    public DebugHierarchyViewContainer(Context context) {
        super(context);
        a(context);
    }

    public DebugHierarchyViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DebugHierarchyViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        this.b = new LinearLayout(context);
        this.b.setOrientation(1);
        horizontalScrollView.addView(this.b);
        addView(horizontalScrollView);
        this.d = a(50.0f);
        this.e = a(16.0f);
    }

    private int a(float f) {
        return (int) ((this.a.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public void a(List<a> list) {
        this.b.removeAllViews();
        this.b.addView(getTitleLayout());
        if (list != null) {
            a(list, 0, null);
        }
    }

    private LinearLayout getTitleLayout() {
        if (this.c != null) {
            return this.c;
        }
        this.c = new LinearLayout(this.a);
        this.c.setPadding(a(24.0f), a(24.0f), 0, a(8.0f));
        this.c.setOrientation(0);
        this.c.setLayoutParams(new LayoutParams(-1, -2));
        TextView textView = new TextView(this.a);
        textView.setText("栈视图(Stack)");
        textView.setTextSize(20.0f);
        textView.setTextColor(-16777216);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        this.c.addView(textView);
        ImageView imageView = new ImageView(this.a);
        imageView.setImageResource(R.drawable.fragmentation_help);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = a(16.0f);
        layoutParams2.gravity = 16;
        imageView.setLayoutParams(layoutParams2);
        this.c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(DebugHierarchyViewContainer.this.a, "* means not in backBack.", 0).show();
            }
        });
        this.c.addView(imageView);
        return this.c;
    }

    private void a(List<a> list, int i, TextView textView) {
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            final TextView a2 = a(aVar, i);
            a2.setTag(R.id.hierarchy, Integer.valueOf(i));
            final List<a> list2 = aVar.b;
            if (list2 == null || list2.size() <= 0) {
                a2.setPadding(a2.getPaddingLeft() + this.e, 0, this.e, 0);
            } else {
                final int i2 = i + 1;
                a2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fragmentation_ic_right, 0, 0, 0);
                a2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        boolean z;
                        if (view.getTag(R.id.isexpand) != null) {
                            boolean booleanValue = ((Boolean) view.getTag(R.id.isexpand)).booleanValue();
                            if (booleanValue) {
                                a2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fragmentation_ic_right, 0, 0, 0);
                                DebugHierarchyViewContainer.this.a(i2);
                            } else {
                                DebugHierarchyViewContainer.this.b(list2, i2, a2);
                            }
                            int i = R.id.isexpand;
                            if (!booleanValue) {
                                z = true;
                            } else {
                                z = false;
                            }
                            view.setTag(i, Boolean.valueOf(z));
                            return;
                        }
                        a2.setTag(R.id.isexpand, Boolean.valueOf(true));
                        DebugHierarchyViewContainer.this.b(list2, i2, a2);
                    }
                });
            }
            if (textView == null) {
                this.b.addView(a2);
            } else {
                this.b.addView(a2, this.b.indexOfChild(textView) + 1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(List<a> list, int i, TextView textView) {
        a(list, i, textView);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fragmentation_ic_expandable, 0, 0, 0);
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        for (int childCount = this.b.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.b.getChildAt(childCount);
            if (childAt.getTag(R.id.hierarchy) != null && ((Integer) childAt.getTag(R.id.hierarchy)).intValue() >= i) {
                this.b.removeView(childAt);
            }
        }
    }

    private TextView a(a aVar, int i) {
        TextView textView = new TextView(this.a);
        textView.setLayoutParams(new LayoutParams(-1, this.d));
        if (i == 0) {
            textView.setTextColor(Color.parseColor("#333333"));
            textView.setTextSize(16.0f);
        }
        textView.setGravity(16);
        textView.setPadding((int) (((double) this.e) + (((double) (this.e * i)) * 1.5d)), 0, this.e, 0);
        textView.setCompoundDrawablePadding(this.e / 2);
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(new int[]{16843534});
        textView.setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        obtainStyledAttributes.recycle();
        textView.setText(aVar.a);
        return textView;
    }
}
