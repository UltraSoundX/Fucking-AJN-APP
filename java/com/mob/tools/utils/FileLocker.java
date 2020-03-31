package com.mob.tools.utils;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public class FileLocker {
    private FileOutputStream fos;
    private FileLock lock;

    public synchronized void setLockFile(String str) {
        try {
            this.fos = new FileOutputStream(str);
        } catch (Throwable th) {
        }
        this.fos = null;
    }

    public synchronized boolean lock(boolean z) {
        return lock(z, z ? 1000 : 500, 16);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0037, code lost:
        if (r2 > 0) goto L_0x0006;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0049 A[SYNTHETIC, Splitter:B:35:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean lock(boolean r11, long r12, long r14) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.io.FileOutputStream r0 = r10.fos     // Catch:{ all -> 0x007e }
            if (r0 != 0) goto L_0x0008
            r0 = 0
        L_0x0006:
            monitor-exit(r10)
            return r0
        L_0x0008:
            boolean r0 = r10.getLock(r11)     // Catch:{ Throwable -> 0x000d }
            goto L_0x0006
        L_0x000d:
            r5 = move-exception
            r0 = 0
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0076
            boolean r0 = r5 instanceof java.nio.channels.OverlappingFileLockException     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x0076
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x007e }
            long r6 = r12 + r0
            r4 = 0
            r2 = r12
        L_0x0020:
            r0 = 0
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0087
            java.lang.Thread.sleep(r14)     // Catch:{ Throwable -> 0x0081 }
        L_0x0029:
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Throwable -> 0x0053 }
            long r2 = r6 - r0
            boolean r0 = r10.getLock(r11)     // Catch:{ Throwable -> 0x0053 }
        L_0x0033:
            r4 = 0
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 > 0) goto L_0x0006
        L_0x0039:
            java.nio.channels.FileLock r0 = r10.lock     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x0045
            java.nio.channels.FileLock r0 = r10.lock     // Catch:{ Throwable -> 0x0085 }
            r0.release()     // Catch:{ Throwable -> 0x0085 }
        L_0x0042:
            r0 = 0
            r10.lock = r0     // Catch:{ all -> 0x007e }
        L_0x0045:
            java.io.FileOutputStream r0 = r10.fos     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x0051
            java.io.FileOutputStream r0 = r10.fos     // Catch:{ Throwable -> 0x0083 }
            r0.close()     // Catch:{ Throwable -> 0x0083 }
        L_0x004e:
            r0 = 0
            r10.fos = r0     // Catch:{ all -> 0x007e }
        L_0x0051:
            r0 = 0
            goto L_0x0006
        L_0x0053:
            r0 = move-exception
            r8 = r0
            r0 = r2
            r2 = r8
            boolean r2 = r2 instanceof java.nio.channels.OverlappingFileLockException     // Catch:{ all -> 0x007e }
            if (r2 == 0) goto L_0x006c
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x006a
            com.mob.tools.log.NLog r2 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007e }
            java.lang.String r3 = "OverlappingFileLockException and timeout"
            r2.w(r3)     // Catch:{ all -> 0x007e }
        L_0x006a:
            r2 = r0
            goto L_0x0020
        L_0x006c:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007e }
            r0.w(r5)     // Catch:{ all -> 0x007e }
            r0 = -1
            goto L_0x006a
        L_0x0076:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x007e }
            r0.w(r5)     // Catch:{ all -> 0x007e }
            goto L_0x0039
        L_0x007e:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x0081:
            r0 = move-exception
            goto L_0x0029
        L_0x0083:
            r0 = move-exception
            goto L_0x004e
        L_0x0085:
            r0 = move-exception
            goto L_0x0042
        L_0x0087:
            r0 = r4
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.FileLocker.lock(boolean, long, long):boolean");
    }

    private boolean getLock(boolean z) throws Throwable {
        if (z) {
            this.lock = this.fos.getChannel().lock();
        } else {
            this.lock = this.fos.getChannel().tryLock();
        }
        return this.lock != null;
    }

    public synchronized void lock(Runnable runnable, boolean z) {
        if (lock(z) && runnable != null) {
            runnable.run();
        }
    }

    public synchronized void unlock() {
        if (this.lock != null) {
            try {
                this.lock.release();
            } catch (Throwable th) {
            }
            this.lock = null;
        }
    }

    public synchronized void release() {
        if (this.fos != null) {
            unlock();
            try {
                this.fos.close();
            } catch (Throwable th) {
            }
            this.fos = null;
        }
    }
}
