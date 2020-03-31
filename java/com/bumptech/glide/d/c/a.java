package com.bumptech.glide.d.c;

import android.net.Uri;

/* compiled from: AssetUriParser */
final class a {
    private static final int a = "file:///android_asset/".length();

    public static boolean a(Uri uri) {
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }

    public static String b(Uri uri) {
        return uri.toString().substring(a);
    }
}
