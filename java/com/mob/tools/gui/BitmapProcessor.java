package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.network.RawNetworkCallback;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class BitmapProcessor {
    private static final int CAPACITY = 3;
    private static final int MAX_CACHE_SIZE = 50;
    private static final int MAX_CACHE_TIME = 60000;
    private static final int MAX_REQ_TIME = 20000;
    private static final int MAX_SIZE = 100;
    private static final int OVERFLOW_SIZE = 120;
    private static final int SCAN_INTERVAL = 20000;
    /* access modifiers changed from: private */
    public static File cacheDir;
    /* access modifiers changed from: private */
    public static CachePool<String, SoftReference<Bitmap>> cachePool = new CachePool<>(50);
    private static ManagerThread manager;
    /* access modifiers changed from: private */
    public static ArrayList<ImageReq> netReqTPS = new ArrayList<>();
    /* access modifiers changed from: private */
    public static ArrayList<ImageReq> reqList = new ArrayList<>();
    /* access modifiers changed from: private */
    public static NetworkTimeOut timeout = new NetworkTimeOut();
    /* access modifiers changed from: private */
    public static boolean work;
    /* access modifiers changed from: private */
    public static WorkerThread[] workerList = new WorkerThread[3];

    public interface BitmapCallback {
        void onImageGot(String str, Bitmap bitmap);
    }

    public static class BitmapDesiredOptions {
        public int desiredHeight = 0;
        public int desiredWidth = 0;
        public long maxBytes = 0;
        public int quality = 0;

        public boolean equals(Object obj) {
            return super.equals(obj) || (obj != null && obj.toString().equals(toString()));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.desiredWidth > 0) {
                sb.append(this.desiredWidth);
            }
            if (this.desiredHeight > 0) {
                sb.append(this.desiredHeight);
            }
            if (this.maxBytes > 0) {
                sb.append(this.maxBytes);
            }
            if (this.quality > 0) {
                sb.append(this.quality);
            }
            return sb.toString();
        }
    }

    public static class ImageReq {
        /* access modifiers changed from: private */
        public BitmapDesiredOptions bitmapDesiredOptions;
        /* access modifiers changed from: private */
        public ArrayList<BitmapCallback> callbacks = new ArrayList<>();
        /* access modifiers changed from: private */
        public long diskCacheTime = 0;
        private long reqTime = System.currentTimeMillis();
        /* access modifiers changed from: private */
        public String url;
        /* access modifiers changed from: private */
        public boolean useDiskCache = true;
        /* access modifiers changed from: private */
        public boolean useRamCache = true;
        /* access modifiers changed from: private */
        public WorkerThread worker;

        /* access modifiers changed from: private */
        public void throwComplete(Bitmap bitmap) {
            Iterator it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((BitmapCallback) it.next()).onImageGot(this.url, bitmap);
            }
            this.callbacks.clear();
        }

        /* access modifiers changed from: private */
        public void throwError() {
            Iterator it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((BitmapCallback) it.next()).onImageGot(this.url, null);
            }
            this.callbacks.clear();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("url=").append(this.url);
            sb.append("time=").append(this.reqTime);
            sb.append("worker=").append(this.worker.getName()).append(" (").append(this.worker.getId()).append("");
            return sb.toString();
        }
    }

    private static class ManagerThread implements Callback {
        private Handler handler = MobHandlerThread.newHandler((Runnable) new Runnable() {
            public void run() {
                boolean z;
                for (int i = 0; i < BitmapProcessor.workerList.length; i++) {
                    if (BitmapProcessor.workerList[i] == null) {
                        BitmapProcessor.workerList[i] = new WorkerThread();
                        BitmapProcessor.workerList[i].setName("worker " + i);
                        WorkerThread workerThread = BitmapProcessor.workerList[i];
                        if (i == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        workerThread.localType = z;
                        BitmapProcessor.workerList[i].start();
                    }
                }
            }
        }, (Callback) this);

        public ManagerThread() {
            this.handler.sendEmptyMessageDelayed(1, 20000);
        }

        public boolean handleMessage(Message message) {
            if (BitmapProcessor.cachePool != null) {
                BitmapProcessor.cachePool.trimBeforeTime(System.currentTimeMillis() - 60000);
            }
            MobLog.getInstance().d(">>>> BitmapProcessor.cachePool: " + (BitmapProcessor.cachePool == null ? 0 : BitmapProcessor.cachePool.size()), new Object[0]);
            MobLog.getInstance().d(">>>> BitmapProcessor.reqList: " + (BitmapProcessor.reqList == null ? 0 : BitmapProcessor.reqList.size()), new Object[0]);
            if (BitmapProcessor.work) {
                this.handler.sendEmptyMessageDelayed(1, 20000);
            }
            return false;
        }

        public void quit() {
            this.handler.removeMessages(1);
            this.handler.getLooper().quit();
            for (int i = 0; i < BitmapProcessor.workerList.length; i++) {
                if (BitmapProcessor.workerList[i] != null) {
                    BitmapProcessor.workerList[i].interrupt();
                    BitmapProcessor.workerList[i] = null;
                }
            }
        }
    }

    private static class PatchInputStream extends FilterInputStream {
        InputStream in;

        protected PatchInputStream(InputStream inputStream) {
            super(inputStream);
            this.in = inputStream;
        }

        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    break;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    private static class WorkerThread extends Thread {
        /* access modifiers changed from: private */
        public ImageReq curReq;
        /* access modifiers changed from: private */
        public boolean localType;

        private WorkerThread() {
        }

        public void run() {
            while (BitmapProcessor.work) {
                try {
                    if (this.localType) {
                        doLocalTask();
                    } else {
                        doNetworkTask();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().w(th);
                }
            }
        }

        private void doLocalTask() throws Throwable {
            ImageReq imageReq;
            Bitmap bitmap;
            synchronized (BitmapProcessor.reqList) {
                if (BitmapProcessor.reqList.size() > 0) {
                    imageReq = (ImageReq) BitmapProcessor.reqList.remove(0);
                } else {
                    imageReq = null;
                }
            }
            if (imageReq != null) {
                if (imageReq.useRamCache) {
                    SoftReference softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions));
                    bitmap = softReference == null ? null : (Bitmap) softReference.get();
                } else {
                    bitmap = null;
                }
                if (bitmap != null) {
                    this.curReq = imageReq;
                    this.curReq.worker = this;
                    imageReq.throwComplete(bitmap);
                } else if (!imageReq.useDiskCache || BitmapProcessor.cacheDir == null || !new File(BitmapProcessor.cacheDir, Data.MD5(imageReq.url)).exists()) {
                    synchronized (BitmapProcessor.reqList) {
                        if (BitmapProcessor.netReqTPS.size() > 100) {
                            synchronized (BitmapProcessor.reqList) {
                                while (BitmapProcessor.reqList.size() > 0) {
                                    BitmapProcessor.reqList.remove(0);
                                }
                            }
                            BitmapProcessor.netReqTPS.remove(0);
                        }
                    }
                    BitmapProcessor.netReqTPS.add(imageReq);
                } else {
                    doTask(imageReq);
                }
            } else {
                try {
                    Thread.sleep(30);
                } catch (Throwable th) {
                }
            }
        }

        private void doNetworkTask() throws Throwable {
            ImageReq imageReq;
            ImageReq imageReq2;
            Bitmap bitmap;
            synchronized (BitmapProcessor.netReqTPS) {
                if (BitmapProcessor.netReqTPS.size() > 0) {
                    imageReq = (ImageReq) BitmapProcessor.netReqTPS.remove(0);
                } else {
                    imageReq = null;
                }
            }
            if (imageReq == null) {
                synchronized (BitmapProcessor.reqList) {
                    if (BitmapProcessor.reqList.size() > 0) {
                        imageReq = (ImageReq) BitmapProcessor.reqList.remove(0);
                    }
                }
                imageReq2 = imageReq;
            } else {
                imageReq2 = imageReq;
            }
            if (imageReq2 != null) {
                if (imageReq2.useRamCache) {
                    SoftReference softReference = (SoftReference) BitmapProcessor.cachePool.get(BitmapProcessor.getCacheKey(imageReq2.url, imageReq2.bitmapDesiredOptions));
                    bitmap = softReference == null ? null : (Bitmap) softReference.get();
                } else {
                    bitmap = null;
                }
                if (bitmap != null) {
                    this.curReq = imageReq2;
                    this.curReq.worker = this;
                    imageReq2.throwComplete(bitmap);
                    return;
                }
                doTask(imageReq2);
                return;
            }
            try {
                Thread.sleep(30);
            } catch (Throwable th) {
            }
        }

        private void doTask(final ImageReq imageReq) throws Throwable {
            Bitmap bitmap;
            try {
                this.curReq = imageReq;
                this.curReq.worker = this;
                final String MD5 = Data.MD5(imageReq.url);
                File file = new File(BitmapProcessor.cacheDir, MD5);
                if (imageReq.useDiskCache && imageReq.diskCacheTime > 0 && file.exists()) {
                    long lastModified = file.lastModified();
                    if (lastModified + imageReq.diskCacheTime < System.currentTimeMillis()) {
                        file.delete();
                    }
                }
                if (!imageReq.useDiskCache || BitmapProcessor.cacheDir == null || !file.exists()) {
                    new NetworkHelper().rawGet(imageReq.url, (RawNetworkCallback) new RawNetworkCallback() {
                        public void onResponse(InputStream inputStream) throws Throwable {
                            Bitmap bitmap;
                            PatchInputStream patchInputStream = new PatchInputStream(inputStream);
                            if (BitmapProcessor.cacheDir != null) {
                                File file = new File(BitmapProcessor.cacheDir, MD5);
                                WorkerThread.this.saveFile(patchInputStream, file);
                                if (imageReq.bitmapDesiredOptions == null || imageReq.bitmapDesiredOptions.equals("")) {
                                    bitmap = BitmapHelper.getBitmap(file, 1);
                                } else {
                                    bitmap = BitmapHelper.getBitmapByCompressQuality(file.getAbsolutePath(), imageReq.bitmapDesiredOptions.desiredWidth, imageReq.bitmapDesiredOptions.desiredHeight, imageReq.bitmapDesiredOptions.quality, imageReq.bitmapDesiredOptions.maxBytes);
                                }
                                if (!imageReq.useDiskCache) {
                                    file.delete();
                                }
                            } else {
                                bitmap = BitmapHelper.getBitmap((InputStream) patchInputStream, 1);
                            }
                            if (bitmap == null || bitmap.isRecycled()) {
                                imageReq.throwError();
                            } else {
                                if (imageReq.useRamCache) {
                                    BitmapProcessor.cachePool.put(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions), new SoftReference(bitmap));
                                }
                                imageReq.throwComplete(bitmap);
                            }
                            WorkerThread.this.curReq = null;
                        }
                    }, BitmapProcessor.timeout);
                    return;
                }
                if (imageReq.bitmapDesiredOptions == null || imageReq.bitmapDesiredOptions.equals("")) {
                    bitmap = BitmapHelper.getBitmap(file.getAbsolutePath());
                } else {
                    bitmap = BitmapHelper.getBitmapByCompressQuality(new File(BitmapProcessor.cacheDir, MD5).getAbsolutePath(), imageReq.bitmapDesiredOptions.desiredWidth, imageReq.bitmapDesiredOptions.desiredHeight, imageReq.bitmapDesiredOptions.quality, imageReq.bitmapDesiredOptions.maxBytes);
                }
                if (bitmap != null) {
                    if (imageReq.useRamCache) {
                        BitmapProcessor.cachePool.put(BitmapProcessor.getCacheKey(imageReq.url, imageReq.bitmapDesiredOptions), new SoftReference(bitmap));
                    }
                    imageReq.throwComplete(bitmap);
                } else {
                    imageReq.throwError();
                }
                this.curReq = null;
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
                imageReq.throwError();
                this.curReq = null;
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0047 A[Catch:{ all -> 0x0062 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void saveFile(java.io.InputStream r6, java.io.File r7) {
            /*
                r5 = this;
                r0 = 0
                boolean r1 = r7.exists()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                if (r1 == 0) goto L_0x000a
                r7.delete()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
            L_0x000a:
                java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                if (r1 != 0) goto L_0x001b
                java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                r1.mkdirs()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
            L_0x001b:
                r7.createNewFile()     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                r1.<init>(r7)     // Catch:{ Throwable -> 0x0040, all -> 0x0053 }
                r0 = 256(0x100, float:3.59E-43)
                byte[] r2 = new byte[r0]     // Catch:{ Throwable -> 0x0067, all -> 0x0060 }
                int r0 = r6.read(r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0060 }
            L_0x002b:
                if (r0 <= 0) goto L_0x0036
                r3 = 0
                r1.write(r2, r3, r0)     // Catch:{ Throwable -> 0x0067, all -> 0x0060 }
                int r0 = r6.read(r2)     // Catch:{ Throwable -> 0x0067, all -> 0x0060 }
                goto L_0x002b
            L_0x0036:
                r1.flush()     // Catch:{ Throwable -> 0x0067, all -> 0x0060 }
                r1.close()     // Catch:{ Throwable -> 0x006a }
                r6.close()     // Catch:{ Throwable -> 0x006a }
            L_0x003f:
                return
            L_0x0040:
                r1 = move-exception
            L_0x0041:
                boolean r1 = r7.exists()     // Catch:{ all -> 0x0062 }
                if (r1 == 0) goto L_0x004a
                r7.delete()     // Catch:{ all -> 0x0062 }
            L_0x004a:
                r0.close()     // Catch:{ Throwable -> 0x0051 }
                r6.close()     // Catch:{ Throwable -> 0x0051 }
                goto L_0x003f
            L_0x0051:
                r0 = move-exception
                goto L_0x003f
            L_0x0053:
                r1 = move-exception
                r4 = r1
                r1 = r0
                r0 = r4
            L_0x0057:
                r1.close()     // Catch:{ Throwable -> 0x005e }
                r6.close()     // Catch:{ Throwable -> 0x005e }
            L_0x005d:
                throw r0
            L_0x005e:
                r1 = move-exception
                goto L_0x005d
            L_0x0060:
                r0 = move-exception
                goto L_0x0057
            L_0x0062:
                r1 = move-exception
                r4 = r1
                r1 = r0
                r0 = r4
                goto L_0x0057
            L_0x0067:
                r0 = move-exception
                r0 = r1
                goto L_0x0041
            L_0x006a:
                r0 = move-exception
                goto L_0x003f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.WorkerThread.saveFile(java.io.InputStream, java.io.File):void");
        }

        public void interrupt() {
            try {
                super.interrupt();
            } catch (Throwable th) {
            }
        }
    }

    static {
        timeout.connectionTimeout = ReaderCallback.GET_BAR_ANIMATING;
        timeout.readTimout = 20000 - timeout.connectionTimeout;
    }

    public static synchronized void prepare(Context context) {
        synchronized (BitmapProcessor.class) {
            cacheDir = new File(ResHelper.getImageCachePath(context));
        }
    }

    public static synchronized void start() {
        synchronized (BitmapProcessor.class) {
            if (!work) {
                work = true;
                manager = new ManagerThread();
            }
        }
    }

    public static synchronized void stop() {
        synchronized (BitmapProcessor.class) {
            if (work) {
                work = false;
                synchronized (reqList) {
                    reqList.clear();
                    cachePool.clear();
                }
                manager.quit();
            }
        }
    }

    public static synchronized void process(String str, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, null, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, true, bitmapCallback);
        }
    }

    public static synchronized void process(String str, BitmapDesiredOptions bitmapDesiredOptions, boolean z, boolean z2, BitmapCallback bitmapCallback) {
        synchronized (BitmapProcessor.class) {
            process(str, bitmapDesiredOptions, z, z2, 0, bitmapCallback);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r0 = new com.mob.tools.gui.BitmapProcessor.ImageReq();
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$002(r0, r8);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$102(r0, r9);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$302(r0, r10);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$402(r0, r12);
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$502(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007b, code lost:
        if (r14 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007d, code lost:
        com.mob.tools.gui.BitmapProcessor.ImageReq.access$200(r0).add(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0084, code lost:
        r1 = reqList;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0086, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        reqList.add(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0094, code lost:
        if (reqList.size() <= 120) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009e, code lost:
        if (reqList.size() <= 100) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a0, code lost:
        reqList.remove(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        start();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void process(java.lang.String r8, com.mob.tools.gui.BitmapProcessor.BitmapDesiredOptions r9, boolean r10, boolean r11, long r12, com.mob.tools.gui.BitmapProcessor.BitmapCallback r14) {
        /*
            r2 = 0
            java.lang.Class<com.mob.tools.gui.BitmapProcessor> r4 = com.mob.tools.gui.BitmapProcessor.class
            monitor-enter(r4)
            if (r8 != 0) goto L_0x0008
        L_0x0006:
            monitor-exit(r4)
            return
        L_0x0008:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r5 = reqList     // Catch:{ all -> 0x005d }
            monitor-enter(r5)     // Catch:{ all -> 0x005d }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r0 = reqList     // Catch:{ all -> 0x005a }
            int r6 = r0.size()     // Catch:{ all -> 0x005a }
            r3 = r2
        L_0x0012:
            if (r3 >= r6) goto L_0x0066
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r0 = reqList     // Catch:{ all -> 0x005a }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x005a }
            com.mob.tools.gui.BitmapProcessor$ImageReq r0 = (com.mob.tools.gui.BitmapProcessor.ImageReq) r0     // Catch:{ all -> 0x005a }
            java.lang.String r1 = r0.url     // Catch:{ all -> 0x005a }
            boolean r7 = r1.equals(r8)     // Catch:{ all -> 0x005a }
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r0.bitmapDesiredOptions     // Catch:{ all -> 0x005a }
            if (r1 != 0) goto L_0x002c
            if (r9 == 0) goto L_0x003c
        L_0x002c:
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r0.bitmapDesiredOptions     // Catch:{ all -> 0x005a }
            if (r1 == 0) goto L_0x0060
            com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions r1 = r0.bitmapDesiredOptions     // Catch:{ all -> 0x005a }
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x005a }
            if (r1 == 0) goto L_0x0060
        L_0x003c:
            r1 = 1
        L_0x003d:
            if (r7 == 0) goto L_0x0062
            if (r1 == 0) goto L_0x0062
            if (r14 == 0) goto L_0x0055
            java.util.ArrayList r1 = r0.callbacks     // Catch:{ all -> 0x005a }
            int r1 = r1.indexOf(r14)     // Catch:{ all -> 0x005a }
            r2 = -1
            if (r1 != r2) goto L_0x0055
            java.util.ArrayList r0 = r0.callbacks     // Catch:{ all -> 0x005a }
            r0.add(r14)     // Catch:{ all -> 0x005a }
        L_0x0055:
            start()     // Catch:{ all -> 0x005a }
            monitor-exit(r5)     // Catch:{ all -> 0x005a }
            goto L_0x0006
        L_0x005a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x005a }
            throw r0     // Catch:{ all -> 0x005d }
        L_0x005d:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0060:
            r1 = r2
            goto L_0x003d
        L_0x0062:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x0012
        L_0x0066:
            monitor-exit(r5)     // Catch:{ all -> 0x005a }
            com.mob.tools.gui.BitmapProcessor$ImageReq r0 = new com.mob.tools.gui.BitmapProcessor$ImageReq     // Catch:{ all -> 0x005d }
            r0.<init>()     // Catch:{ all -> 0x005d }
            r0.url = r8     // Catch:{ all -> 0x005d }
            r0.bitmapDesiredOptions = r9     // Catch:{ all -> 0x005d }
            r0.useRamCache = r10     // Catch:{ all -> 0x005d }
            r0.diskCacheTime = r12     // Catch:{ all -> 0x005d }
            r0.useDiskCache = r11     // Catch:{ all -> 0x005d }
            if (r14 == 0) goto L_0x0084
            java.util.ArrayList r1 = r0.callbacks     // Catch:{ all -> 0x005d }
            r1.add(r14)     // Catch:{ all -> 0x005d }
        L_0x0084:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r1 = reqList     // Catch:{ all -> 0x005d }
            monitor-enter(r1)     // Catch:{ all -> 0x005d }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r2 = reqList     // Catch:{ all -> 0x00a7 }
            r2.add(r0)     // Catch:{ all -> 0x00a7 }
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r0 = reqList     // Catch:{ all -> 0x00a7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00a7 }
            r2 = 120(0x78, float:1.68E-43)
            if (r0 <= r2) goto L_0x00aa
        L_0x0096:
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r0 = reqList     // Catch:{ all -> 0x00a7 }
            int r0 = r0.size()     // Catch:{ all -> 0x00a7 }
            r2 = 100
            if (r0 <= r2) goto L_0x00aa
            java.util.ArrayList<com.mob.tools.gui.BitmapProcessor$ImageReq> r0 = reqList     // Catch:{ all -> 0x00a7 }
            r2 = 0
            r0.remove(r2)     // Catch:{ all -> 0x00a7 }
            goto L_0x0096
        L_0x00a7:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            throw r0     // Catch:{ all -> 0x005d }
        L_0x00aa:
            monitor-exit(r1)     // Catch:{ all -> 0x00a7 }
            start()     // Catch:{ all -> 0x005d }
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.BitmapProcessor.process(java.lang.String, com.mob.tools.gui.BitmapProcessor$BitmapDesiredOptions, boolean, boolean, long, com.mob.tools.gui.BitmapProcessor$BitmapCallback):void");
    }

    /* access modifiers changed from: private */
    public static String getCacheKey(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        return bitmapDesiredOptions == null ? str : str + bitmapDesiredOptions.toString();
    }

    public static Bitmap getBitmapFromCache(String str) {
        return getBitmapFromCache(str, null);
    }

    public static Bitmap getBitmapFromCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        if (cachePool == null || str == null || cachePool.get(getCacheKey(str, bitmapDesiredOptions)) == null) {
            return null;
        }
        return (Bitmap) ((SoftReference) cachePool.get(getCacheKey(str, bitmapDesiredOptions))).get();
    }

    public static void removeBitmapFromRamCache(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        if (cachePool != null) {
            cachePool.put(getCacheKey(str, bitmapDesiredOptions), null);
        }
    }

    public static void deleteCachedFile(String str, BitmapDesiredOptions bitmapDesiredOptions) {
        removeBitmapFromRamCache(str, bitmapDesiredOptions);
        try {
            new File(cacheDir, Data.MD5(str)).delete();
        } catch (Throwable th) {
        }
    }

    public static void deleteCacheDir(boolean z) {
        if (z) {
            deleteCacheDir();
        } else {
            new Thread() {
                public void run() {
                    BitmapProcessor.deleteCacheDir();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void deleteCacheDir() {
        synchronized (BitmapProcessor.class) {
            File file = new File(cacheDir.getPath());
            if (file.isDirectory()) {
                String[] list = file.list();
                for (String file2 : list) {
                    new File(file, file2).delete();
                }
            }
        }
    }

    public static long getCacheSizeInByte() {
        long j = 0;
        File[] listFiles = new File(cacheDir.getPath()).listFiles();
        for (File length : listFiles) {
            j += length.length();
        }
        return j;
    }

    public static String getCacheSizeText() {
        float cacheSizeInByte = (float) getCacheSizeInByte();
        if (cacheSizeInByte < 1024.0f) {
            return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf(cacheSizeInByte)}) + " B";
        } else if (cacheSizeInByte / 1024.0f < 1000.0f) {
            return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf(cacheSizeInByte / 1024.0f)}) + " KB";
        } else {
            return String.format(Locale.CHINA, "%.02f", new Object[]{Float.valueOf((cacheSizeInByte / 1024.0f) / 1204.0f)}) + " MB";
        }
    }
}
