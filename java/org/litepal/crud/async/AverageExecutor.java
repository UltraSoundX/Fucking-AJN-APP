package org.litepal.crud.async;

import org.litepal.crud.callback.AverageCallback;

public class AverageExecutor extends AsyncExecutor {
    private AverageCallback cb;

    public void listen(AverageCallback averageCallback) {
        this.cb = averageCallback;
        execute();
    }

    public AverageCallback getListener() {
        return this.cb;
    }
}
