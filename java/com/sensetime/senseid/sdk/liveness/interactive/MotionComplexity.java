package com.sensetime.senseid.sdk.liveness.interactive;

import android.support.annotation.Keep;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Keep
@Documented
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface MotionComplexity {
    public static final int EASY = 1;
    public static final int HARD = 3;
    public static final int HELL = 4;
    public static final int NORMAL = 2;
}
