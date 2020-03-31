package uk.co.senab.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;

@TargetApi(14)
/* compiled from: IcsScroller */
public class b extends a {
    public b(Context context) {
        super(context);
    }

    public boolean a() {
        return this.a.computeScrollOffset();
    }
}
