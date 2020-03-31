package android.support.design.card;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.R;

/* compiled from: MaterialCardViewHelper */
class a {
    private final MaterialCardView a;
    private int b;
    private int c;

    public a(MaterialCardView materialCardView) {
        this.a = materialCardView;
    }

    public void a(TypedArray typedArray) {
        this.b = typedArray.getColor(R.styleable.MaterialCardView_strokeColor, -1);
        this.c = typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        c();
        e();
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        this.b = i;
        c();
    }

    /* access modifiers changed from: 0000 */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public void b(int i) {
        this.c = i;
        c();
        e();
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.a.setForeground(d());
    }

    private Drawable d() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.a.getRadius());
        if (this.b != -1) {
            gradientDrawable.setStroke(this.c, this.b);
        }
        return gradientDrawable;
    }

    private void e() {
        this.a.a(this.a.getContentPaddingLeft() + this.c, this.a.getContentPaddingTop() + this.c, this.a.getContentPaddingRight() + this.c, this.a.getContentPaddingBottom() + this.c);
    }
}
