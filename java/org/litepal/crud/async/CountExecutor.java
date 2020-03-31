package org.litepal.crud.async;

import org.litepal.crud.callback.CountCallback;

public class CountExecutor extends AsyncExecutor {
    private CountCallback cb;

    public void listen(CountCallback countCallback) {
        this.cb = countCallback;
        execute();
    }

    public CountCallback getListener() {
        return this.cb;
    }
}
