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
public @interface OcclusionState {
    public static final int NORMAL = 1;
    public static final int OCCLUSION = 2;
    public static final int UNKNOWN = 0;
}
