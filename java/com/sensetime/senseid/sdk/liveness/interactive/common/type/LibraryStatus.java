package com.sensetime.senseid.sdk.liveness.interactive.common.type;

public final class LibraryStatus {
    public static final int ERROR = -1;
    public static final int HANDLE_CREATED = 2;
    public static final int IDLE = 0;
    public static final int LICENSE_LOADED = 1;
    public static final int STARTED = 3;
    public static final int STOPPED = 4;

    private LibraryStatus() {
    }
}
