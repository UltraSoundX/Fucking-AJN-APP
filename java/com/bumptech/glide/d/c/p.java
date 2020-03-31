package com.bumptech.glide.d.c;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.d.a.c;
import java.io.File;

/* compiled from: StringLoader */
public class p<T> implements l<String, T> {
    private final l<Uri, T> a;

    public p(l<Uri, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(String str, int i, int i2) {
        Uri parse;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("/")) {
            parse = a(str);
        } else {
            parse = Uri.parse(str);
            if (parse.getScheme() == null) {
                parse = a(str);
            }
        }
        return this.a.a(parse, i, i2);
    }

    private static Uri a(String str) {
        return Uri.fromFile(new File(str));
    }
}
