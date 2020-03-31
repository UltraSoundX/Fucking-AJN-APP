package uk.co.senab.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import uk.co.senab.photoview.a;

@TargetApi(5)
/* compiled from: EclairGestureDetector */
public class b extends a {
    private int f = -1;
    private int g = 0;

    public b(Context context) {
        super(context);
    }

    /* access modifiers changed from: 0000 */
    public float a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.g);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    /* access modifiers changed from: 0000 */
    public float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.g);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean c(MotionEvent motionEvent) {
        int i;
        int i2 = 0;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.f = -1;
                break;
            case 6:
                int a = a.a(motionEvent.getAction());
                if (motionEvent.getPointerId(a) == this.f) {
                    if (a == 0) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    this.f = motionEvent.getPointerId(i);
                    this.b = motionEvent.getX(i);
                    this.c = motionEvent.getY(i);
                    break;
                }
                break;
        }
        if (this.f != -1) {
            i2 = this.f;
        }
        this.g = motionEvent.findPointerIndex(i2);
        return super.c(motionEvent);
    }
}
