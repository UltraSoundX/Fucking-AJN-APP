package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class VisibilityAwareImageButton extends ImageButton {
    private int a;

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = getVisibility();
    }

    public void setVisibility(int i) {
        a(i, true);
    }

    public final void a(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.a = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.a;
    }
}
