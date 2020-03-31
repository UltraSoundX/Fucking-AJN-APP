package android.support.design.f;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.v4.graphics.ColorUtils;
import android.util.StateSet;

/* compiled from: RippleUtils */
public class a {
    public static final boolean a = (VERSION.SDK_INT >= 21);
    private static final int[] b = {16842919};
    private static final int[] c = {16843623, 16842908};
    private static final int[] d = {16842908};
    private static final int[] e = {16843623};
    private static final int[] f = {16842913, 16842919};
    private static final int[] g = {16842913, 16843623, 16842908};
    private static final int[] h = {16842913, 16842908};
    private static final int[] i = {16842913, 16843623};
    private static final int[] j = {16842913};

    public static ColorStateList a(ColorStateList colorStateList) {
        if (a) {
            return new ColorStateList(new int[][]{j, StateSet.NOTHING}, new int[]{a(colorStateList, f), a(colorStateList, b)});
        }
        return new ColorStateList(new int[][]{f, g, h, i, j, b, c, d, e, StateSet.NOTHING}, new int[]{a(colorStateList, f), a(colorStateList, g), a(colorStateList, h), a(colorStateList, i), 0, a(colorStateList, b), a(colorStateList, c), a(colorStateList, d), a(colorStateList, e), 0});
    }

    private static int a(ColorStateList colorStateList, int[] iArr) {
        int i2;
        if (colorStateList != null) {
            i2 = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        } else {
            i2 = 0;
        }
        return a ? a(i2) : i2;
    }

    @TargetApi(21)
    private static int a(int i2) {
        return ColorUtils.setAlphaComponent(i2, Math.min(Color.alpha(i2) * 2, 255));
    }
}
