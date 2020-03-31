package com.e23.ajn.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.e23.ajn.model.PaperMonthBzResponse.DataBean;
import java.util.List;

public class CalendarCard extends View {
    private static a g;
    /* access modifiers changed from: private */
    public Paint a;
    /* access modifiers changed from: private */
    public Paint b;
    private int c;
    private int d;
    /* access modifiers changed from: private */
    public int e;
    private c[] f = new c[6];
    private b h;
    private int i;
    private boolean j;
    private a k;
    private float l;
    private float m;
    private String n;
    private List<DataBean> o;

    class a {
        public a a;
        public d b;
        public int c;
        public int d;

        public a(a aVar, d dVar, int i, int i2) {
            this.a = aVar;
            this.b = dVar;
            this.c = i;
            this.d = i2;
        }

        public void a(Canvas canvas) {
            switch (this.b) {
                case TODAY:
                    CalendarCard.this.b.setColor(Color.parseColor("#fffffe"));
                    canvas.drawCircle((float) (((double) CalendarCard.this.e) * (((double) this.c) + 0.5d)), (float) ((((double) this.d) + 0.5d) * ((double) CalendarCard.this.e)), (float) (CalendarCard.this.e / 3), CalendarCard.this.a);
                    break;
                case CURRENT_MONTH_DAY:
                    CalendarCard.this.b.setColor(-16777216);
                    break;
                case PAST_MONTH_DAY:
                case NEXT_MONTH_DAY:
                    CalendarCard.this.b.setColor(Color.parseColor("#fffffe"));
                    break;
                case UNREACH_DAY:
                    CalendarCard.this.b.setColor(-7829368);
                    break;
            }
            String str = this.a.c + "";
            canvas.drawText(str, (float) (((((double) this.c) + 0.5d) * ((double) CalendarCard.this.e)) - ((double) (CalendarCard.this.b.measureText(str) / 2.0f))), (float) (((((double) this.d) + 0.7d) * ((double) CalendarCard.this.e)) - ((double) (CalendarCard.this.b.measureText(str, 0, 1) / 2.0f))), CalendarCard.this.b);
        }
    }

    public interface b {
        void a(a aVar);

        void b(a aVar);
    }

    class c {
        public int a;
        public a[] b = new a[7];

        c(int i) {
            this.a = i;
        }

        public void a(Canvas canvas) {
            for (int i = 0; i < this.b.length; i++) {
                if (this.b[i] != null) {
                    this.b[i].a(canvas);
                }
            }
        }
    }

    enum d {
        TODAY,
        CURRENT_MONTH_DAY,
        PAST_MONTH_DAY,
        NEXT_MONTH_DAY,
        UNREACH_DAY
    }

    public CalendarCard(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    public CalendarCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CalendarCard(Context context, b bVar, String str, List<DataBean> list) {
        super(context);
        this.h = bVar;
        this.n = str;
        this.o = list;
        a(context);
    }

    private void a(Context context) {
        this.b = new Paint(1);
        this.a = new Paint(1);
        this.a.setStyle(Style.FILL);
        this.a.setColor(Color.parseColor("#F24949"));
        this.i = ViewConfiguration.get(context).getScaledTouchSlop();
        b();
    }

    private void b() {
        String[] split = this.n.split("-");
        g = new a(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        c();
    }

    private void c() {
        boolean z;
        int c2 = b.c();
        b.a(g.a, g.b - 1);
        int a2 = b.a(g.a, g.b);
        int b2 = b.b(g.a, g.b);
        if (b.a(g)) {
            z = true;
        } else {
            z = false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            this.f[i3] = new c(i3);
            for (int i4 = 0; i4 < 7; i4++) {
                int i5 = (i3 * 7) + i4;
                if (i5 >= b2 && i5 < b2 + a2) {
                    int i6 = i2 + 1;
                    this.f[i3].b[i4] = new a(a.a(g, i6), d.CURRENT_MONTH_DAY, i4, i3);
                    if (z && i6 == c2) {
                        this.f[i3].b[i4] = new a(a.a(g, i6), d.TODAY, i4, i3);
                    }
                    if (z && i6 > c2) {
                        this.f[i3].b[i4] = new a(a.a(g, i6), d.UNREACH_DAY, i4, i3);
                    }
                    if (!a(i6)) {
                        this.f[i3].b[i4] = new a(a.a(g, i6), d.UNREACH_DAY, i4, i3);
                    }
                    i2 = i6;
                }
            }
        }
        this.h.b(g);
    }

    private boolean a(int i2) {
        String str;
        String str2 = i2 + "";
        if (str2.length() == 1) {
            str = "0" + str2;
        } else {
            str = str2;
        }
        for (int i3 = 0; i3 < this.o.size(); i3++) {
            if (((DataBean) this.o.get(i3)).getPublishdate().substring(8, 10).equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i2 = 0; i2 < 6; i2++) {
            if (this.f[i2] != null) {
                this.f[i2].a(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.c = i2;
        this.d = i3;
        this.e = Math.min(this.d / 6, this.c / 7);
        if (!this.j) {
            this.j = true;
        }
        this.b.setTextSize((float) (this.e / 3));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.l = motionEvent.getX();
                this.m = motionEvent.getY();
                break;
            case 1:
                float y = motionEvent.getY() - this.m;
                if (Math.abs(motionEvent.getX() - this.l) < ((float) this.i) && Math.abs(y) < ((float) this.i)) {
                    a((int) (this.l / ((float) this.e)), (int) (this.m / ((float) this.e)));
                    break;
                }
        }
        return true;
    }

    private void a(int i2, int i3) {
        if (i2 < 7 && i3 < 6) {
            if (this.k != null) {
                this.f[this.k.d].b[this.k.c] = this.k;
            }
            if (this.f[i3] != null) {
                this.k = new a(this.f[i3].b[i2].a, this.f[i3].b[i2].b, this.f[i3].b[i2].c, this.f[i3].b[i2].d);
                a aVar = this.f[i3].b[i2].a;
                aVar.d = i2;
                this.h.a(aVar);
                a();
            }
        }
    }

    public void a() {
        c();
        invalidate();
    }
}
