package com.ccb.b.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import java.util.HashMap;

public class m {
    private static final m a = new m();
    private static HashMap<String, Bitmap> b = new HashMap<>();

    public static Drawable a(String str) {
        Bitmap bitmap = (Bitmap) b.get(str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeStream(a.getClass().getResourceAsStream("/assets/" + str));
            if (bitmap == null) {
                return null;
            }
            b.put(str, bitmap);
        }
        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        if (ninePatchChunk == null || !NinePatch.isNinePatchChunk(ninePatchChunk)) {
            return new BitmapDrawable(bitmap);
        }
        return new NinePatchDrawable(bitmap, ninePatchChunk, new Rect(), str);
    }
}
