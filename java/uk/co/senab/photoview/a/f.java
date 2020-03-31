package uk.co.senab.photoview.a;

import android.content.Context;
import android.os.Build.VERSION;

/* compiled from: VersionedGestureDetector */
public final class f {
    public static d a(Context context, e eVar) {
        d cVar;
        int i = VERSION.SDK_INT;
        if (i < 5) {
            cVar = new a(context);
        } else if (i < 8) {
            cVar = new b(context);
        } else {
            cVar = new c(context);
        }
        cVar.a(eVar);
        return cVar;
    }
}
