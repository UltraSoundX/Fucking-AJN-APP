package com.tencent.mid.core;

import android.content.Context;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.sotrage.CheckEntity;
import com.tencent.mid.sotrage.UnifiedStorage;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.SettingsHelper;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import java.util.Arrays;

public class ServiceRunnable implements Runnable {
    public static final int CHECK_TYPE = 2;
    public static final int REQUEST_NEW_TYPE = 3;
    public static final int REQUEST_TYPE = 1;
    private MidCallback callback = null;
    private Context context = null;
    /* access modifiers changed from: private */
    public Logger logger = null;
    private int type = 0;

    public ServiceRunnable(Context context2, int i, MidCallback midCallback) {
        this.context = context2;
        this.type = i;
        this.callback = midCallback;
        this.logger = Util.getLogger();
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
            java.lang.Class<com.tencent.mid.core.ServiceRunnable> r1 = com.tencent.mid.core.ServiceRunnable.class
            monitor-enter(r1)
            com.tencent.mid.util.Logger r0 = r5.logger     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r2.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r3 = "ServiceRunnable begin, type:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x006b }
            int r3 = r5.type     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x006b }
            java.lang.String r3 = ",ver:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x006b }
            r3 = 1082277233(0x40823d71, float:4.07)
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x006b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x006b }
            r0.w(r2)     // Catch:{ all -> 0x006b }
            int r0 = r5.type     // Catch:{ Throwable -> 0x0064 }
            switch(r0) {
                case 1: goto L_0x0052;
                case 2: goto L_0x0094;
                default: goto L_0x002f;
            }     // Catch:{ Throwable -> 0x0064 }
        L_0x002f:
            com.tencent.mid.util.Logger r0 = r5.logger     // Catch:{ Throwable -> 0x0064 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0064 }
            r2.<init>()     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r3 = "wrong type:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0064 }
            int r3 = r5.type     // Catch:{ Throwable -> 0x0064 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0064 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0064 }
            r0.w(r2)     // Catch:{ Throwable -> 0x0064 }
        L_0x0049:
            com.tencent.mid.util.Logger r0 = r5.logger     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "ServiceRunnable end"
            r0.w(r2)     // Catch:{ all -> 0x006b }
            monitor-exit(r1)     // Catch:{ all -> 0x006b }
            return
        L_0x0052:
            android.content.Context r0 = r5.context     // Catch:{ Throwable -> 0x0064 }
            com.tencent.mid.api.MidEntity r0 = com.tencent.mid.core.ServiceIMPL.getLocalMidEntity(r0)     // Catch:{ Throwable -> 0x0064 }
            boolean r2 = com.tencent.mid.util.Util.isMidValid(r0)     // Catch:{ Throwable -> 0x0064 }
            if (r2 == 0) goto L_0x006e
            com.tencent.mid.api.MidCallback r2 = r5.callback     // Catch:{ Throwable -> 0x0064 }
            r2.onSuccess(r0)     // Catch:{ Throwable -> 0x0064 }
            goto L_0x0049
        L_0x0064:
            r0 = move-exception
            com.tencent.mid.util.Logger r2 = r5.logger     // Catch:{ all -> 0x006b }
            r2.e(r0)     // Catch:{ all -> 0x006b }
            goto L_0x0049
        L_0x006b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x006b }
            throw r0
        L_0x006e:
            android.content.Context r0 = r5.context     // Catch:{ Throwable -> 0x0064 }
            boolean r0 = com.tencent.mid.util.Util.isNetworkAvailable(r0)     // Catch:{ Throwable -> 0x0064 }
            if (r0 == 0) goto L_0x008a
            android.content.Context r0 = r5.context     // Catch:{ Throwable -> 0x0064 }
            com.tencent.mid.core.HttpManager r0 = com.tencent.mid.core.HttpManager.getInstance(r0)     // Catch:{ Throwable -> 0x0064 }
            r2 = 1
            com.tencent.mid.core.RequestPacket r3 = new com.tencent.mid.core.RequestPacket     // Catch:{ Throwable -> 0x0064 }
            android.content.Context r4 = r5.context     // Catch:{ Throwable -> 0x0064 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0064 }
            com.tencent.mid.api.MidCallback r4 = r5.callback     // Catch:{ Throwable -> 0x0064 }
            r0.send(r2, r3, r4)     // Catch:{ Throwable -> 0x0064 }
            goto L_0x0049
        L_0x008a:
            com.tencent.mid.api.MidCallback r0 = r5.callback     // Catch:{ Throwable -> 0x0064 }
            r2 = -10010(0xffffffffffffd8e6, float:NaN)
            java.lang.String r3 = "network not available."
            r0.onFail(r2, r3)     // Catch:{ Throwable -> 0x0064 }
            goto L_0x0049
        L_0x0094:
            r5.autoCheck()     // Catch:{ Throwable -> 0x0064 }
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.core.ServiceRunnable.run():void");
    }

    private void checkLocalConsistency() {
        MidEntity readMidEntity = UnifiedStorage.getInstance(this.context).readMidEntity(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2)})));
        MidEntity readMidEntity2 = UnifiedStorage.getInstance(this.context).readMidEntity(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4)})));
        if (Util.equal(readMidEntity2, readMidEntity)) {
            this.logger.w("local mid check passed.");
            return;
        }
        MidEntity newerMidEntity = Util.getNewerMidEntity(readMidEntity2, readMidEntity);
        this.logger.w("local mid check failed, redress with mid:" + newerMidEntity.toString());
        if (SettingsHelper.getInstance(this.context).getInt("ten.mid.allowCheckAndRewriteLocal.bool", 0) == 1) {
            UnifiedStorage.getInstance(this.context).writeMidEntity(newerMidEntity);
        }
    }

    private void autoCheck() {
        CheckEntity readCheckEntity = UnifiedStorage.getInstance(this.context).readCheckEntity();
        if (readCheckEntity == null) {
            this.logger.w("CheckEntity is null");
            return;
        }
        int lastCheckTimes = readCheckEntity.getLastCheckTimes() + 1;
        long currentTimeMillis = System.currentTimeMillis() - readCheckEntity.getLastCheckTimestamps();
        if (currentTimeMillis < 0) {
            currentTimeMillis = -currentTimeMillis;
        }
        this.logger.i("check entity: " + readCheckEntity.toString() + ",duration:" + currentTimeMillis);
        if ((lastCheckTimes > readCheckEntity.getMaxFreq() && currentTimeMillis > Constants.DAY_TIMESTAMPS) || currentTimeMillis > ((long) readCheckEntity.getMaxDays()) * Constants.DAY_TIMESTAMPS) {
            checkLocalConsistency();
            checkServer();
            readCheckEntity.setLastCheckTimes(lastCheckTimes);
            readCheckEntity.setLastCheckTimestamps(System.currentTimeMillis());
            UnifiedStorage.getInstance(this.context).writeCheckEntity(readCheckEntity);
        }
        MidEntity readNewVersionMidEntity = UnifiedStorage.getInstance(this.context).readNewVersionMidEntity();
        this.logger.i("midNewEntity:" + readNewVersionMidEntity);
        if (!Util.isMidValid(readNewVersionMidEntity)) {
            this.logger.i("request mid_new ");
            HttpManager.getInstance(this.context).send(3, new RequestPacket(this.context), new MidCallback() {
                public void onSuccess(Object obj) {
                    ServiceRunnable.this.logger.i("request new mid success:" + obj);
                }

                public void onFail(int i, String str) {
                    ServiceRunnable.this.logger.i("request new mid failed, errCode:" + i + ",msg:" + str);
                }
            });
        }
    }

    private void checkServer() {
        this.logger.i("checkServer");
        HttpManager.getInstance(this.context).send(2, new RequestPacket(this.context), new MidCallback() {
            public void onSuccess(Object obj) {
                ServiceRunnable.this.logger.i("checkServer success:" + obj);
            }

            public void onFail(int i, String str) {
                ServiceRunnable.this.logger.i("checkServer failed, errCode:" + i + ",msg:" + str);
            }
        });
    }
}
