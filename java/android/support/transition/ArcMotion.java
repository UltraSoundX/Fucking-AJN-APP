package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

public class ArcMotion extends PathMotion {
    private static final float a = ((float) Math.tan(Math.toRadians(35.0d)));
    private float b = 0.0f;
    private float c = 0.0f;
    private float d = 70.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = a;

    public ArcMotion() {
    }

    public ArcMotion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        b(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "minimumVerticalAngle", 1, 0.0f));
        a(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "minimumHorizontalAngle", 0, 0.0f));
        c(TypedArrayUtils.getNamedFloat(obtainStyledAttributes, xmlPullParser, "maximumAngle", 2, 70.0f));
        obtainStyledAttributes.recycle();
    }

    public void a(float f2) {
        this.b = f2;
        this.e = d(f2);
    }

    public void b(float f2) {
        this.c = f2;
        this.f = d(f2);
    }

    public void c(float f2) {
        this.d = f2;
        this.g = d(f2);
    }

    private static float d(float f2) {
        if (f2 >= 0.0f && f2 <= 90.0f) {
            return (float) Math.tan(Math.toRadians((double) (f2 / 2.0f)));
        }
        throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
    }

    public Path a(float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        Path path = new Path();
        path.moveTo(f2, f3);
        float f14 = f4 - f2;
        float f15 = f5 - f3;
        float f16 = (f15 * f15) + (f14 * f14);
        float f17 = (f2 + f4) / 2.0f;
        float f18 = (f3 + f5) / 2.0f;
        float f19 = f16 * 0.25f;
        boolean z = f3 > f5;
        if (Math.abs(f14) < Math.abs(f15)) {
            float abs = Math.abs(f16 / (2.0f * f15));
            if (z) {
                f12 = f5 + abs;
                f13 = f4;
            } else {
                f12 = f3 + abs;
                f13 = f2;
            }
            f8 = f13;
            f9 = f12;
            f10 = this.f * f19 * this.f;
        } else {
            float f20 = f16 / (f14 * 2.0f);
            if (z) {
                f6 = f2 + f20;
                f7 = f3;
            } else {
                f6 = f4 - f20;
                f7 = f5;
            }
            f8 = f6;
            f9 = f7;
            f10 = this.e * f19 * this.e;
        }
        float f21 = f17 - f8;
        float f22 = f18 - f9;
        float f23 = (f22 * f22) + (f21 * f21);
        float f24 = this.g * f19 * this.g;
        if (f23 >= f10) {
            if (f23 > f24) {
                f10 = f24;
            } else {
                f10 = 0.0f;
            }
        }
        if (f10 != 0.0f) {
            float sqrt = (float) Math.sqrt((double) (f10 / f23));
            f8 = ((f8 - f17) * sqrt) + f17;
            f11 = f18 + (sqrt * (f9 - f18));
        } else {
            f11 = f9;
        }
        path.cubicTo((f2 + f8) / 2.0f, (f3 + f11) / 2.0f, (f8 + f4) / 2.0f, (f11 + f5) / 2.0f, f4, f5);
        return path;
    }
}
