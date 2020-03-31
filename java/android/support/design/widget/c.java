package android.support.design.widget;

import android.graphics.Outline;

/* compiled from: CircularBorderDrawableLollipop */
public class c extends b {
    public void getOutline(Outline outline) {
        copyBounds(this.b);
        outline.setOval(this.b);
    }
}
