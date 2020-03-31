package com.mob;

import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.commons.MobProductCollector;
import com.mob.commons.a;
import com.mob.tools.MobLog;
import com.mob.tools.RxMob;
import com.mob.tools.RxMob.QuickSubscribe;
import com.mob.tools.RxMob.Subscriber;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.network.StringPart;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.MobRSA;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public final class MobCommunicator implements PublicMemberKeeper {
    private Random a = new Random();
    private BigInteger b;
    private BigInteger c;
    private MobRSA d;
    /* access modifiers changed from: private */
    public Hashon e;
    private NetworkHelper f;
    private NetworkTimeOut g;

    public static class Callback<T> implements PublicMemberKeeper {
        public void onResultOk(T t) {
        }

        public void onResultError(Throwable th) {
        }
    }

    public static class NetworkError extends Exception implements PublicMemberKeeper {
        private static final long serialVersionUID = -8447657431687664787L;

        public NetworkError(String str) {
            super(str);
        }
    }

    public MobCommunicator(int i, String str, String str2) {
        this.d = new MobRSA(i);
        this.b = new BigInteger(str, 16);
        this.c = new BigInteger(str2, 16);
        this.e = new Hashon();
        this.f = new NetworkHelper();
        this.g = new NetworkTimeOut();
        this.g.readTimout = Config.SESSION_PERIOD;
        this.g.connectionTimeout = ReaderCallback.GET_BAR_ANIMATING;
    }

    public <T> void request(final HashMap<String, Object> hashMap, final String str, final boolean z, final Callback<T> callback) {
        RxMob.create(new QuickSubscribe<T>() {
            /* access modifiers changed from: protected */
            public void doNext(Subscriber<T> subscriber) throws Throwable {
                Object obj = null;
                if (!a.ad()) {
                    obj = MobCommunicator.this.requestSynchronized(hashMap, str, z);
                }
                subscriber.onNext(obj);
            }
        }).subscribeOnNewThreadAndObserveOnUIThread(new Subscriber<T>() {
            public void onNext(T t) {
                callback.onResultOk(t);
            }

            public void onError(Throwable th) {
                callback.onResultError(th);
            }
        });
    }

    public <T> T requestSynchronized(HashMap<String, Object> hashMap, String str, boolean z) throws Throwable {
        String fromHashMap;
        if (hashMap == null) {
            fromHashMap = "{}";
        } else {
            fromHashMap = this.e.fromHashMap(hashMap);
            if (fromHashMap.length() == 0) {
                fromHashMap = "{}";
            }
        }
        return requestSynchronized(fromHashMap, str, z);
    }

    public <T> T requestSynchronized(String str, String str2, boolean z) throws Throwable {
        byte[] a2 = a();
        String a3 = a(a2, str, z);
        ArrayList a4 = a(str, a3.getBytes("utf-8").length);
        String[] strArr = new String[1];
        HttpResponseCallback a5 = a(a2, strArr);
        StringPart stringPart = new StringPart();
        stringPart.append(a3);
        MobLog.getInstance().d(">>>  request: " + str + "\nurl = " + str2 + "\nheader = " + a4.toString(), new Object[0]);
        this.f.rawPost(str2, a4, stringPart, -1, a5, this.g);
        if (strArr[0] == null) {
            return null;
        }
        MobLog.getInstance().d(">>> response: " + strArr[0], new Object[0]);
        return a(strArr[0]);
    }

    private byte[] a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(byteArrayOutputStream));
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] a() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.a.setSeed(System.currentTimeMillis());
        dataOutputStream.writeLong(this.a.nextLong());
        dataOutputStream.writeLong(this.a.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private String a(byte[] bArr, String str, boolean z) throws Throwable {
        byte[] bytes = str.getBytes("utf-8");
        if (z) {
            bytes = a(bytes);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] encode = this.d.encode(bArr, this.b, this.c);
        dataOutputStream.writeInt(encode.length);
        dataOutputStream.write(encode);
        byte[] AES128Encode = Data.AES128Encode(bArr, bytes);
        dataOutputStream.writeInt(AES128Encode.length);
        dataOutputStream.write(AES128Encode);
        dataOutputStream.flush();
        dataOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    private ArrayList<KVPair<String>> a(String str, int i) throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair("sign", Data.MD5(str + MobSDK.getAppSecret())));
        arrayList.add(new KVPair(SettingsContentProvider.KEY, MobSDK.getAppkey()));
        arrayList.add(new KVPair("Content-Length", String.valueOf(i)));
        arrayList.add(new KVPair("User-Identity", MobProductCollector.getUserIdentity()));
        return arrayList;
    }

    private HttpResponseCallback a(final byte[] bArr, final String[] strArr) {
        return new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                InputStream errorStream = responseCode == 200 ? httpConnection.getInputStream() : httpConnection.getErrorStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                for (int read = errorStream.read(bArr); read != -1; read = errorStream.read(bArr)) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                errorStream.close();
                byteArrayOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (responseCode != 200) {
                    HashMap fromJson = MobCommunicator.this.e.fromJson(new String(byteArray, "utf-8"));
                    fromJson.put("httpStatus", Integer.valueOf(responseCode));
                    throw new NetworkError(MobCommunicator.this.e.fromHashMap(fromJson));
                }
                long a2 = MobCommunicator.this.a(httpConnection);
                if (a2 == -1 || a2 != ((long) byteArray.length)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("httpStatus", Integer.valueOf(responseCode));
                    hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-2));
                    hashMap.put("error", "Illegal content length");
                    throw new NetworkError(MobCommunicator.this.e.fromHashMap(hashMap));
                }
                strArr[0] = MobCommunicator.this.a(bArr, byteArray);
            }
        };
    }

    /* access modifiers changed from: private */
    public long a(HttpConnection httpConnection) throws Throwable {
        List a2 = a(httpConnection, "Content-Length");
        if (a2 == null || a2.size() <= 0) {
            return -1;
        }
        return Long.parseLong((String) a2.get(0));
    }

    private List<String> a(HttpConnection httpConnection, String str) throws Throwable {
        Map headerFields = httpConnection.getHeaderFields();
        if (headerFields != null && !headerFields.isEmpty()) {
            for (String str2 : headerFields.keySet()) {
                if (str2 != null && str2.equals(str)) {
                    return (List) headerFields.get(str2);
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public String a(byte[] bArr, byte[] bArr2) throws Throwable {
        return new String(Data.AES128Decode(bArr, Base64.decode(bArr2, 2)), "utf-8");
    }

    private Object a(String str) throws Throwable {
        if (str == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-1));
            hashMap.put("error", "response is empty");
            throw new NetworkError(this.e.fromHashMap(hashMap));
        }
        HashMap fromJson = this.e.fromJson(str.trim());
        if (!fromJson.isEmpty()) {
            return fromJson.get("res");
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-1));
        hashMap2.put("error", "response is empty");
        throw new NetworkError(this.e.fromHashMap(hashMap2));
    }
}
