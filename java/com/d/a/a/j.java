package com.d.a.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import com.d.a.a;
import com.mob.tools.gui.ViewPagerAdapter;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;

/* compiled from: PlatformPageAdapter */
public abstract class j extends ViewPagerAdapter implements OnClickListener {
    protected Object[][] a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    private i i;
    private f j;
    private long k;

    /* access modifiers changed from: protected */
    public abstract void a(Context context, ArrayList<Object> arrayList);

    /* access modifiers changed from: protected */
    public abstract void a(ArrayList<Object> arrayList);

    public j(i iVar, ArrayList<Object> arrayList) {
        this.i = iVar;
        if (arrayList != null && !arrayList.isEmpty()) {
            a(iVar.getContext(), arrayList);
            a(arrayList);
        }
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.length;
    }

    public void a(f fVar) {
        this.j = fVar;
    }

    public void onScreenChange(int i2, int i3) {
        if (this.j != null) {
            this.j.setScreenCount(getCount());
            this.j.a(i2, i3);
        }
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(viewGroup.getContext());
        }
        a((LinearLayout[]) ResHelper.forceCast(((LinearLayout) ResHelper.forceCast(view)).getTag()), this.a[i2]);
        return view;
    }

    private View a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-855310);
        int i2 = this.c / this.d;
        LinearLayout[] linearLayoutArr = new LinearLayout[(this.e * i2)];
        linearLayout.setTag(linearLayoutArr);
        int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_oks_classic_platform_cell_back");
        for (int i3 = 0; i3 < i2; i3++) {
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout.addView(linearLayout2, new LayoutParams(-1, this.d));
            for (int i4 = 0; i4 < this.e; i4++) {
                linearLayoutArr[(this.e * i3) + i4] = new LinearLayout(context);
                linearLayoutArr[(this.e * i3) + i4].setBackgroundResource(bitmapRes);
                linearLayoutArr[(this.e * i3) + i4].setOrientation(1);
                LayoutParams layoutParams = new LayoutParams(-1, this.d);
                layoutParams.weight = 1.0f;
                linearLayout2.addView(linearLayoutArr[(this.e * i3) + i4], layoutParams);
                if (i4 < this.e - 1) {
                    linearLayout2.addView(new View(context), new LayoutParams(this.f, -1));
                }
            }
            linearLayout.addView(new View(context), new LayoutParams(-1, this.f));
        }
        for (LinearLayout linearLayout3 : linearLayoutArr) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            LayoutParams layoutParams2 = new LayoutParams(-1, this.h);
            layoutParams2.topMargin = this.g;
            linearLayout3.addView(imageView, layoutParams2);
            TextView textView = new TextView(context);
            textView.setTextColor(-10197916);
            textView.setTextSize(2, 14.0f);
            textView.setGravity(17);
            LayoutParams layoutParams3 = new LayoutParams(-1, -2);
            layoutParams3.weight = 1.0f;
            linearLayout3.addView(textView, layoutParams3);
        }
        return linearLayout;
    }

    private void a(LinearLayout[] linearLayoutArr, Object[] objArr) {
        int bitmapRes = ResHelper.getBitmapRes(this.i.getContext(), "ssdk_oks_classic_platform_cell_back");
        int bitmapRes2 = ResHelper.getBitmapRes(this.i.getContext(), "ssdk_oks_classic_platfrom_cell_back_nor");
        for (int i2 = 0; i2 < objArr.length; i2++) {
            ImageView imageView = (ImageView) ResHelper.forceCast(linearLayoutArr[i2].getChildAt(0));
            TextView textView = (TextView) ResHelper.forceCast(linearLayoutArr[i2].getChildAt(1));
            if (objArr[i2] == null) {
                imageView.setVisibility(4);
                textView.setVisibility(4);
                linearLayoutArr[i2].setBackgroundResource(bitmapRes2);
                linearLayoutArr[i2].setOnClickListener(null);
            } else {
                imageView.setVisibility(0);
                textView.setVisibility(0);
                imageView.requestLayout();
                textView.requestLayout();
                linearLayoutArr[i2].setBackgroundResource(bitmapRes);
                linearLayoutArr[i2].setOnClickListener(this);
                linearLayoutArr[i2].setTag(objArr[i2]);
                if (objArr[i2] instanceof a) {
                    a aVar = (a) ResHelper.forceCast(objArr[i2]);
                    if (aVar.b != null) {
                        imageView.setImageBitmap(aVar.b);
                    } else {
                        imageView.setImageBitmap(null);
                    }
                    if (aVar.a != null) {
                        textView.setText(aVar.a);
                    } else {
                        textView.setText("");
                    }
                } else {
                    String lowerCase = ((Platform) ResHelper.forceCast(objArr[i2])).getName().toLowerCase();
                    int bitmapRes3 = ResHelper.getBitmapRes(imageView.getContext(), "ssdk_oks_classic_" + lowerCase);
                    if (bitmapRes3 > 0) {
                        imageView.setImageResource(bitmapRes3);
                    } else {
                        imageView.setImageBitmap(null);
                    }
                    int stringRes = ResHelper.getStringRes(textView.getContext(), "ssdk_" + lowerCase);
                    if (stringRes > 0) {
                        textView.setText(stringRes);
                    } else {
                        textView.setText("");
                    }
                }
            }
        }
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.k >= 1000) {
            this.k = currentTimeMillis;
            if (view.getTag() instanceof a) {
                this.i.a(view, (a) ResHelper.forceCast(view.getTag()));
                return;
            }
            this.i.d((Platform) ResHelper.forceCast(view.getTag()));
        }
    }
}
