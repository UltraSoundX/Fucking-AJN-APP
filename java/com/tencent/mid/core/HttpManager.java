package com.tencent.mid.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.LocalServerSocket;
import com.stub.StubApp;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.MidPreference;
import com.tencent.mid.sotrage.UnifiedStorage;
import com.tencent.mid.util.AESHelper;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.Util;
import java.io.IOException;

public class HttpManager {
    private static final String LOCAL_SERVER_SOCKET_NAME_SUFFIX = "com.tencent.teg.mid.sock.lock";
    private static final long MAX_DURATION_FAILED_TIME = 1800000;
    private static final int MAX_FAILED_COUNT = 3;
    private static Context applicationContext = null;
    private static HttpManager instance = null;
    private static Logger logger = Util.getLogger();
    private AESHelper aesHelper = null;
    private AESHelper defaultAesHelper = null;
    private int httpFailedCount = 0;
    private long lastHttpFailedTime = 0;
    int limit = -1;
    LocalServerSocket mLocalServerSocket = null;
    private int seq = -1;

    private void resetFailedInfo() {
        this.lastHttpFailedTime = 0;
        this.httpFailedCount = 0;
    }

    private void incFailedCount() {
        this.httpFailedCount++;
        this.lastHttpFailedTime = System.currentTimeMillis();
    }

    private boolean checkFailedCountLimited() {
        if (this.httpFailedCount > 3) {
            if (System.currentTimeMillis() - this.lastHttpFailedTime < MAX_DURATION_FAILED_TIME) {
                return true;
            }
            resetFailedInfo();
        }
        return false;
    }

    private HttpManager(Context context) {
        try {
            applicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        } catch (Throwable th) {
            logger.e((Object) th);
        }
    }

    /* access modifiers changed from: 0000 */
    public AESHelper getAesHelper(int i) {
        if (i == 1) {
            if (this.aesHelper == null) {
                this.aesHelper = new AESHelper();
                this.aesHelper.initKeyAndIV();
            }
            return this.aesHelper;
        }
        if (this.defaultAesHelper == null) {
            this.defaultAesHelper = new AESHelper();
            this.defaultAesHelper.setKeyAndIV("key-/.*$!xx", "vec-;*5@)&%(");
        }
        return this.defaultAesHelper;
    }

    public static synchronized HttpManager getInstance(Context context) {
        HttpManager httpManager;
        synchronized (HttpManager.class) {
            if (instance == null) {
                instance = new HttpManager(context);
            }
            httpManager = instance;
        }
        return httpManager;
    }

    static Context getApplicationContext() {
        return applicationContext;
    }

    /* access modifiers changed from: 0000 */
    public boolean checkSendLimit() {
        int i = this.limit;
        this.limit = i + 1;
        if (i > 1000) {
            logger.e((Object) "send count limit " + this.limit);
            return false;
        }
        SharedPreferences sharedPreferences = MidPreference.getInstance(applicationContext).getSharedPreferences();
        if (sharedPreferences != null) {
            String str = "SEND_LIMIT_" + Util.getDateString(0);
            if (this.limit == 0) {
                this.limit = sharedPreferences.getInt(str, 0);
            }
            sharedPreferences.edit().putInt(str, this.limit);
        }
        return true;
    }

    private boolean getSocket() {
        String str = LOCAL_SERVER_SOCKET_NAME_SUFFIX;
        try {
            this.mLocalServerSocket = new LocalServerSocket(str);
            logger.d("open socket mLocalServerSocket:" + this.mLocalServerSocket);
            return true;
        } catch (IOException e) {
            logger.w("socket Name:" + str + " is in use.");
            return false;
        } catch (Throwable th) {
            logger.w("something wrong while create LocalServerSocket.");
            return false;
        }
    }

    private void closeSocket() {
        if (this.mLocalServerSocket != null) {
            try {
                this.mLocalServerSocket.close();
                logger.i("close socket  mLocalServerSocket:" + this.mLocalServerSocket);
                this.mLocalServerSocket = null;
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void send(int i, PacketInterface packetInterface, MidCallback midCallback) {
        if (packetInterface == null || midCallback == null) {
            if (midCallback != null) {
                midCallback.onFail(MidConstants.ERROR_ARGUMENT, "packet == null || handler == null");
            }
            logger.e((Object) "packet == null || handler == null || cb == null");
        } else if (!Util.isNetworkAvailable(applicationContext)) {
            midCallback.onFail(MidConstants.ERROR_NETWORK, "network not available.");
        } else {
            int i2 = 0;
            while (!getSocket()) {
                int i3 = i2 + 1;
                if (i2 >= 10) {
                    break;
                }
                try {
                    Thread.sleep(500);
                    i2 = i3;
                } catch (InterruptedException e) {
                    i2 = i3;
                }
            }
            if (i == 1) {
                MidEntity localMidEntity = ServiceIMPL.getLocalMidEntity(applicationContext);
                if (Util.isMidValid(localMidEntity)) {
                    midCallback.onSuccess(localMidEntity);
                    closeSocket();
                    return;
                }
            }
            if (i == 3) {
                MidEntity readNewVersionMidEntity = UnifiedStorage.getInstance(applicationContext).readNewVersionMidEntity();
                if (Util.isMidValid(readNewVersionMidEntity)) {
                    midCallback.onSuccess(readNewVersionMidEntity);
                    closeSocket();
                    return;
                }
            }
            if (!checkSendLimit()) {
                closeSocket();
                return;
            }
            request(i, packetInterface, midCallback);
            closeSocket();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x0250 A[SYNTHETIC, Splitter:B:70:0x0250] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x025f A[SYNTHETIC, Splitter:B:77:0x025f] */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void request(int r11, com.tencent.mid.core.PacketInterface r12, com.tencent.mid.api.MidCallback r13) {
        /*
            r10 = this;
            r1 = 0
            r9 = -1
            r8 = 1
            com.tencent.mid.util.Logger r0 = logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = " enter http request, type:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.i(r2)
            r0 = 0
            java.lang.String r2 = ""
            boolean r2 = r10.checkFailedCountLimited()     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            if (r2 == 0) goto L_0x003d
            java.lang.String r2 = "Http request failed too much, please check the network."
            com.tencent.mid.util.Logger r3 = logger     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            r3.e(r2)     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            if (r13 == 0) goto L_0x0032
            r3 = -10050(0xffffffffffffd8be, float:NaN)
            r13.onFail(r3, r2)     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
        L_0x0032:
            if (r1 == 0) goto L_0x0037
            r0.shutdown()     // Catch:{ Throwable -> 0x0038 }
        L_0x0037:
            return
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x003d:
            android.content.Context r0 = applicationContext     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            com.tencent.mid.util.ConstantUtil r3 = com.tencent.mid.util.ConstantUtil.getInstance(r0)     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            com.tencent.mid.core.HttpConnectClient r2 = new com.tencent.mid.core.HttpConnectClient     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            android.content.Context r0 = applicationContext     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            java.lang.String r0 = com.tencent.mid.util.Util.getHttpAddr(r0)     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            r4 = 0
            r2.<init>(r0, r4)     // Catch:{ Throwable -> 0x0232, all -> 0x025b }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r0.<init>()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r12.encode(r0)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r1 = "rty"
            r0.put(r1, r11)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r1 = r10.seq     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 <= 0) goto L_0x0067
            java.lang.String r1 = "seq"
            int r4 = r10.seq     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r0.put(r1, r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x0067:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.<init>()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r4 = "android"
            r1.put(r4, r0)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r0 = "mid_list"
            android.content.Context r4 = applicationContext     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r5 = 1
            org.json.JSONArray r4 = com.tencent.mid.util.Util.queryMids(r4, r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.put(r0, r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r0 = "mid_list_new"
            android.content.Context r4 = applicationContext     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r5 = 2
            org.json.JSONArray r4 = com.tencent.mid.util.Util.queryMids(r4, r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.put(r0, r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r0 = r1.toString()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.Logger r1 = logger     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.<init>()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r5 = "jsonBodyStr:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.i(r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r4 = r0.length()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r5 = "UTF-8"
            byte[] r0 = r0.getBytes(r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.write(r0)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.close()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.flush()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.AESHelper r5 = r10.getAesHelper(r11)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.reset()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.io.DataOutputStream r6 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.<init>(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r0 = r3.getVerifyMidUrl()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r11 == r8) goto L_0x00db
            r7 = 3
            if (r11 != r7) goto L_0x0116
        L_0x00db:
            if (r11 != r8) goto L_0x0165
            java.lang.String r0 = r3.getRequestMidUrl()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x00e1:
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r8 = 64
            r7.<init>(r8)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r8 = r5.getKeyBytes()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r7.write(r8)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r8 = r5.getIvBytes()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r7.write(r8)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r7.close()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r7 = r7.toByteArray()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r8 = r3.getRSAPK()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.RSAHelper.createPublicKey(r8)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r7 = com.tencent.mid.util.RSAHelper.encrypt(r7)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r3 = r3.getRSAKeyVersion()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.writeShort(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r3 = r7.length     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.writeShort(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.write(r7)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x0116:
            byte[] r3 = r5.encrypt(r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.write(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r6.close()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.close()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            byte[] r1 = r1.toByteArray()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "gzip"
            com.tencent.mid.core.HttpResponseResult r0 = r2.httpPost(r0, r1, r3, r11)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r1 = r0.getCode()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r1 == r3) goto L_0x016b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r1.<init>()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "response code invalid:"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r3 = r0.getCode()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.Logger r3 = logger     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r3.w(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            int r0 = r0.getCode()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r13.onFail(r0, r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r2 == 0) goto L_0x0037
            r2.shutdown()     // Catch:{ Throwable -> 0x015f }
            goto L_0x0037
        L_0x015f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x0165:
            java.lang.String r0 = r3.getRequestMidNewUrl()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            goto L_0x00e1
        L_0x016b:
            java.lang.String r1 = ""
            org.json.JSONObject r0 = r0.getData()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r1 = "ret_code"
            boolean r1 = r0.has(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 != 0) goto L_0x0181
            java.lang.String r1 = "ret_msg"
            boolean r1 = r0.has(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 == 0) goto L_0x01c1
        L_0x0181:
            java.lang.String r1 = "ret_code"
            int r1 = r0.getInt(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "ret_msg"
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.<init>()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r5 = "response code:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r5 = ",msg:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.Logger r4 = logger     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r4.w(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 == 0) goto L_0x01c1
            r13.onFail(r1, r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r2 == 0) goto L_0x0037
            r2.shutdown()     // Catch:{ Throwable -> 0x01bb }
            goto L_0x0037
        L_0x01bb:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x01c1:
            java.lang.String r1 = "seq"
            boolean r1 = r0.isNull(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 != 0) goto L_0x01d1
            java.lang.String r1 = "seq"
            int r1 = r0.getInt(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            r10.seq = r1     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x01d1:
            java.lang.String r1 = "mid"
            boolean r1 = r0.isNull(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 != 0) goto L_0x01f9
            java.lang.String r1 = "mid"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "guid"
            boolean r3 = r0.has(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r3 == 0) goto L_0x01f9
            java.lang.String r3 = "guid"
            r4 = 0
            long r4 = r0.optLong(r3, r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "reset"
            r6 = 0
            int r3 = r0.optInt(r3, r6)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            updateMidEntity(r1, r4, r3, r13)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x01f9:
            java.lang.String r1 = "locW"
            r3 = -1
            int r1 = r0.optInt(r1, r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r1 <= r9) goto L_0x020d
            android.content.Context r3 = applicationContext     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            com.tencent.mid.util.SettingsHelper r3 = com.tencent.mid.util.SettingsHelper.getInstance(r3)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r4 = "ten.mid.allowCheckAndRewriteLocal.bool"
            r3.putInt(r4, r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
        L_0x020d:
            java.lang.String r1 = "mid_new"
            java.lang.String r1 = r0.optString(r1)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "guid"
            r4 = 0
            long r4 = r0.optLong(r3, r4)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = "reset_new"
            r6 = 0
            int r0 = r0.optInt(r3, r6)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            updateNewVersionMidEntity(r1, r4, r0)     // Catch:{ Throwable -> 0x026d, all -> 0x0268 }
            if (r2 == 0) goto L_0x0037
            r2.shutdown()     // Catch:{ Throwable -> 0x022c }
            goto L_0x0037
        L_0x022c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x0232:
            r0 = move-exception
        L_0x0233:
            r10.incFailedCount()     // Catch:{ all -> 0x026a }
            r0.printStackTrace()     // Catch:{ all -> 0x026a }
            r2 = -10030(0xffffffffffffd8d2, float:NaN)
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x026a }
            r13.onFail(r2, r3)     // Catch:{ all -> 0x026a }
            com.tencent.mid.util.Logger r2 = logger     // Catch:{ all -> 0x026a }
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x026a }
            r2.w(r3)     // Catch:{ all -> 0x026a }
            r0.printStackTrace()     // Catch:{ all -> 0x026a }
            if (r1 == 0) goto L_0x0037
            r1.shutdown()     // Catch:{ Throwable -> 0x0255 }
            goto L_0x0037
        L_0x0255:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0037
        L_0x025b:
            r0 = move-exception
            r2 = r1
        L_0x025d:
            if (r2 == 0) goto L_0x0262
            r2.shutdown()     // Catch:{ Throwable -> 0x0263 }
        L_0x0262:
            throw r0
        L_0x0263:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0262
        L_0x0268:
            r0 = move-exception
            goto L_0x025d
        L_0x026a:
            r0 = move-exception
            r2 = r1
            goto L_0x025d
        L_0x026d:
            r0 = move-exception
            r1 = r2
            goto L_0x0233
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mid.core.HttpManager.request(int, com.tencent.mid.core.PacketInterface, com.tencent.mid.api.MidCallback):void");
    }

    private static void updateNewVersionMidEntity(String str, long j, int i) {
        if (Util.isMidValid(str)) {
            if (!Util.isMidValid(UnifiedStorage.getInstance(applicationContext).readNewVersionMidStr())) {
                i = 3;
            }
            logger.i("updateNewVersionMidEntity reset:" + i);
            if (i > 0) {
                MidEntity midEntity = new MidEntity();
                midEntity.setMid(str);
                midEntity.setGuid(j);
                midEntity.setMac(CustomDeviceInfos.getMacAddress(applicationContext));
                midEntity.setImei(CustomDeviceInfos.getDeviceId(applicationContext));
                midEntity.setImsi(CustomDeviceInfos.getImsi(applicationContext));
                midEntity.setTimestamps(System.currentTimeMillis());
                midEntity.setVersion(3);
                logger.i("server return new version mid midEntity:" + midEntity.toString());
                switch (i) {
                    case 1:
                        UnifiedStorage.getInstance(applicationContext).writePrivateNewVersionMidEntity(midEntity);
                        break;
                    case 2:
                        UnifiedStorage.getInstance(applicationContext).writePublicNewVersionMidEntity(midEntity);
                        break;
                    case 3:
                        UnifiedStorage.getInstance(applicationContext).writeNewVersionMidEntity(midEntity);
                        break;
                    case 4:
                        UnifiedStorage.getInstance(applicationContext).writeMidEntity(midEntity);
                        UnifiedStorage.getInstance(applicationContext).writeNewVersionMidEntity(midEntity);
                        break;
                    case 8:
                        UnifiedStorage.getInstance(applicationContext).writeMidEntity(midEntity);
                        UnifiedStorage.getInstance(applicationContext).writeNewVersionMidEntity(midEntity);
                        UnifiedStorage.getInstance(applicationContext).writeOldMidEntity(midEntity);
                        break;
                }
                UnifiedStorage.getInstance(applicationContext).resetCheckEntity(-1, -1);
            }
        }
    }

    private static void updateMidEntity(String str, long j, int i, MidCallback midCallback) {
        if (Util.isMidValid(str)) {
            if (!Util.isMidValid(ServiceIMPL.getLocalMid(applicationContext))) {
                i = 4;
            }
            logger.i("updateMidEntity reset:" + i);
            if (i > 0) {
                MidEntity midEntity = new MidEntity();
                midEntity.setMid(str);
                midEntity.setGuid(j);
                midEntity.setMac(CustomDeviceInfos.getMacAddress(applicationContext));
                midEntity.setImei(CustomDeviceInfos.getDeviceId(applicationContext));
                midEntity.setImsi(CustomDeviceInfos.getImsi(applicationContext));
                midEntity.setTimestamps(System.currentTimeMillis());
                midEntity.setVersion(3);
                logger.i("server return new mid midEntity:" + midEntity.toString());
                midCallback.onSuccess(midEntity.toString());
                switch (i) {
                    case 1:
                        UnifiedStorage.getInstance(applicationContext).writePrivateMidEntity(midEntity);
                        break;
                    case 2:
                        UnifiedStorage.getInstance(applicationContext).writePublicMidEntity(midEntity);
                        break;
                    case 3:
                        UnifiedStorage.getInstance(applicationContext).writeMidEntity(midEntity);
                        break;
                    case 4:
                        UnifiedStorage.getInstance(applicationContext).writeMidEntity(midEntity);
                        UnifiedStorage.getInstance(applicationContext).writeOldMidEntity(midEntity);
                        break;
                }
                UnifiedStorage.getInstance(applicationContext).resetCheckEntity(-1, -1);
            }
        }
    }
}
