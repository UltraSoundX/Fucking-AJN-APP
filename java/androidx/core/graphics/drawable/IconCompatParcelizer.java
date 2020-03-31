package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = aVar.b(iconCompat.mType, 1);
        iconCompat.mData = aVar.b(iconCompat.mData, 2);
        iconCompat.mParcelable = aVar.b(iconCompat.mParcelable, 3);
        iconCompat.mInt1 = aVar.b(iconCompat.mInt1, 4);
        iconCompat.mInt2 = aVar.b(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) aVar.b(iconCompat.mTintList, 6);
        iconCompat.mTintModeStr = aVar.b(iconCompat.mTintModeStr, 7);
        iconCompat.onPostParceling();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.a(true, true);
        iconCompat.onPreParceling(aVar.a());
        aVar.a(iconCompat.mType, 1);
        aVar.a(iconCompat.mData, 2);
        aVar.a(iconCompat.mParcelable, 3);
        aVar.a(iconCompat.mInt1, 4);
        aVar.a(iconCompat.mInt2, 5);
        aVar.a((Parcelable) iconCompat.mTintList, 6);
        aVar.a(iconCompat.mTintModeStr, 7);
    }
}
