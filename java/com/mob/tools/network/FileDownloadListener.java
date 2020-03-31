package com.mob.tools.network;

public abstract class FileDownloadListener {
    private boolean isCanceled = false;

    /* access modifiers changed from: protected */
    public abstract void onProgress(int i, long j, long j2);

    public void cancel() {
        this.isCanceled = true;
    }

    public boolean isCanceled() {
        return this.isCanceled;
    }
}
