package cn.sharesdk.framework.utils;

/* compiled from: Preconditions */
public final class c {
    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
