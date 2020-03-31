package android.support.a.a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

/* compiled from: DragAndDropPermissionsCompat */
public final class a {
    private Object a;

    private a(Object obj) {
        this.a = obj;
    }

    public static a a(Activity activity, DragEvent dragEvent) {
        if (VERSION.SDK_INT >= 24) {
            DragAndDropPermissions requestDragAndDropPermissions = activity.requestDragAndDropPermissions(dragEvent);
            if (requestDragAndDropPermissions != null) {
                return new a(requestDragAndDropPermissions);
            }
        }
        return null;
    }
}
