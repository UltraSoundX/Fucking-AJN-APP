package q.rorbin.badgeview;

import android.graphics.PointF;
import java.util.List;

/* compiled from: MathUtil */
public class d {
    public static double a(double d, int i) {
        if (d < 0.0d) {
            d += 1.5707963267948966d;
        }
        return (((double) (i - 1)) * 1.5707963267948966d) + d;
    }

    public static double a(double d) {
        return 360.0d * (d / 6.283185307179586d);
    }

    public static int a(PointF pointF, PointF pointF2) {
        if (pointF.x > pointF2.x) {
            if (pointF.y > pointF2.y) {
                return 4;
            }
            if (pointF.y < pointF2.y) {
                return 1;
            }
        } else if (pointF.x < pointF2.x) {
            if (pointF.y > pointF2.y) {
                return 3;
            }
            if (pointF.y < pointF2.y) {
                return 2;
            }
        }
        return -1;
    }

    public static float b(PointF pointF, PointF pointF2) {
        return (float) Math.sqrt(Math.pow((double) (pointF.x - pointF2.x), 2.0d) + Math.pow((double) (pointF.y - pointF2.y), 2.0d));
    }

    public static void a(PointF pointF, float f, Double d, List<PointF> list) {
        float f2;
        if (d != null) {
            float atan = (float) Math.atan(d.doubleValue());
            float cos = (float) (Math.cos((double) atan) * ((double) f));
            f2 = (float) (Math.sin((double) atan) * ((double) f));
            f = cos;
        } else {
            f2 = 0.0f;
        }
        list.add(new PointF(pointF.x + f, pointF.y + f2));
        list.add(new PointF(pointF.x - f, pointF.y - f2));
    }
}
