package com.mcxtzhang.indexlib.IndexBar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.mcxtzhang.indexlib.IndexBar.a.b;
import com.mcxtzhang.indexlib.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexBar extends View {
    public static String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private boolean b;
    private List<String> c;
    private int d;
    private int e;
    private int f;
    private Paint g;
    private int h;
    private com.mcxtzhang.indexlib.IndexBar.b.a i;
    /* access modifiers changed from: private */
    public TextView j;
    private List<? extends b> k;
    /* access modifiers changed from: private */
    public LinearLayoutManager l;
    private int m;
    private a n;

    public interface a {
        void a();

        void a(int i, String str);
    }

    public IndexBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IndexBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m = 0;
        a(context, attributeSet, i2);
    }

    public int getHeaderViewCount() {
        return this.m;
    }

    public com.mcxtzhang.indexlib.IndexBar.b.a getDataHelper() {
        return this.i;
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        int applyDimension = (int) TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics());
        this.h = -16777216;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.IndexBar, i2, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = applyDimension;
        for (int i4 = 0; i4 < indexCount; i4++) {
            int index = obtainStyledAttributes.getIndex(i4);
            if (index == R.styleable.IndexBar_indexBarTextSize) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
            } else if (index == R.styleable.IndexBar_indexBarPressBackground) {
                this.h = obtainStyledAttributes.getColor(index, this.h);
            }
        }
        obtainStyledAttributes.recycle();
        a();
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.g.setTextSize((float) i3);
        this.g.setColor(-16777216);
        setmOnIndexPressedListener(new a() {
            public void a(int i, String str) {
                if (IndexBar.this.j != null) {
                    IndexBar.this.j.setVisibility(0);
                    IndexBar.this.j.setText(str);
                }
                if (IndexBar.this.l != null) {
                    int a2 = IndexBar.this.a(str);
                    if (a2 != -1) {
                        IndexBar.this.l.b(a2, 0);
                    }
                }
            }

            public void a() {
                if (IndexBar.this.j != null) {
                    IndexBar.this.j.setVisibility(8);
                }
            }
        });
        this.i = new com.mcxtzhang.indexlib.IndexBar.b.b();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        Rect rect = new Rect();
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.c.size(); i6++) {
            String str = (String) this.c.get(i6);
            this.g.getTextBounds(str, 0, str.length(), rect);
            i5 = Math.max(rect.width(), i5);
            i4 = Math.max(rect.height(), i4);
        }
        int size3 = this.c.size() * i4;
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                i5 = Math.min(i5, size);
                break;
            case 1073741824:
                i5 = size;
                break;
        }
        switch (mode2) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                size3 = Math.min(size3, size2);
                break;
            case 1073741824:
                size3 = size2;
                break;
        }
        setMeasuredDimension(i5, size3);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int paddingTop = getPaddingTop();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.c.size()) {
                String str = (String) this.c.get(i3);
                FontMetrics fontMetrics = this.g.getFontMetrics();
                canvas.drawText(str, ((float) (this.d / 2)) - (this.g.measureText(str) / 2.0f), (float) (((int) (((((float) this.f) - fontMetrics.bottom) - fontMetrics.top) / 2.0f)) + (this.f * i3) + paddingTop), this.g);
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                setBackgroundColor(this.h);
                break;
            case 2:
                break;
            default:
                setBackgroundResource(17170445);
                if (this.n != null) {
                    this.n.a();
                    break;
                }
                break;
        }
        int y = (int) ((motionEvent.getY() - ((float) getPaddingTop())) / ((float) this.f));
        int i2 = y < 0 ? 0 : y >= this.c.size() ? this.c.size() - 1 : y;
        if (this.n != null && i2 > -1 && i2 < this.c.size()) {
            this.n.a(i2, (String) this.c.get(i2));
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.d = i2;
        this.e = i3;
        if (this.c != null && !this.c.isEmpty()) {
            b();
        }
    }

    public a getmOnIndexPressedListener() {
        return this.n;
    }

    public void setmOnIndexPressedListener(a aVar) {
        this.n = aVar;
    }

    private void a() {
        if (this.b) {
            this.c = new ArrayList();
        } else {
            this.c = Arrays.asList(a);
        }
    }

    private void b() {
        this.f = ((this.e - getPaddingTop()) - getPaddingBottom()) / this.c.size();
    }

    /* access modifiers changed from: private */
    public int a(String str) {
        if (this.k == null || this.k.isEmpty()) {
            return -1;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.k.size()) {
                return -1;
            }
            if (str.equals(((b) this.k.get(i3)).a())) {
                return getHeaderViewCount() + i3;
            }
            i2 = i3 + 1;
        }
    }
}
