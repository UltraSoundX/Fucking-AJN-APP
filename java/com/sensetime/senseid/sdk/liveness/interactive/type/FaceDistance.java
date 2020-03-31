package com.sensetime.senseid.sdk.liveness.interactive.type;

import android.support.annotation.Keep;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Keep
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.CLASS)
public @interface FaceDistance {
    public static final int NORMAL = 0;
    public static final int TOO_CLOSE = -1;
    public static final int TOO_FAR = 1;
}
