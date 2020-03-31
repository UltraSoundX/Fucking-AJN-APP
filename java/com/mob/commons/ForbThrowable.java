package com.mob.commons;

public class ForbThrowable extends Throwable {
    public ForbThrowable() {
        super("Service is forbidden currently");
    }

    public ForbThrowable(String str) {
        super(str);
    }
}
