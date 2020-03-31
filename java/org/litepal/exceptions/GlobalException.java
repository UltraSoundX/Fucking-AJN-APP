package org.litepal.exceptions;

public class GlobalException extends RuntimeException {
    public static final String APPLICATION_CONTEXT_IS_NULL = "Application context is null. Maybe you neither configured your application name with \"org.litepal.LitePalApplication\" in your AndroidManifest.xml, nor called LitePal.initialize(Context) method.";
    private static final long serialVersionUID = 1;

    public GlobalException(String str) {
        super(str);
    }
}
