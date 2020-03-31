package com.mob.commons.filesys;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.commons.ForbThrowable;
import com.mob.commons.MobProduct;
import com.mob.commons.a;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.h;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.tencent.android.tpush.SettingsContentProvider;
import com.tencent.android.tpush.common.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUploader implements PublicMemberKeeper {
    private static String a = h.b("http://up.sdk.mob.com");

    public static class UploadedAudio extends UploadedFile {
        public final int durationUSec;

        public UploadedAudio(HashMap<String, Object> hashMap) {
            super(hashMap);
            int i = -1;
            try {
                i = Integer.parseInt(String.valueOf(hashMap.get("time")));
            } catch (Throwable th) {
            }
            this.durationUSec = i;
        }
    }

    public static class UploadedAvatar extends UploadedImage {
        public UploadedAvatar(HashMap<String, Object> hashMap) {
            super(hashMap);
        }
    }

    public static class UploadedFile implements PublicMemberKeeper {
        public final String id;
        public final String url;

        public UploadedFile(HashMap<String, Object> hashMap) {
            if (hashMap.containsKey("src")) {
                this.url = (String) hashMap.get("src");
            } else {
                this.url = null;
            }
            if (hashMap.containsKey(Config.FEED_LIST_ITEM_CUSTOM_ID)) {
                this.id = (String) hashMap.get(Config.FEED_LIST_ITEM_CUSTOM_ID);
            } else {
                this.id = null;
            }
        }
    }

    public static class UploadedImage extends UploadedFile {
        public final HashMap<String, String> zoomList;

        public UploadedImage(HashMap<String, Object> hashMap) {
            HashMap<String, String> hashMap2;
            super(hashMap);
            try {
                hashMap2 = (HashMap) hashMap.get("list");
            } catch (Throwable th) {
                hashMap2 = null;
            }
            this.zoomList = hashMap2;
        }
    }

    public static class UploadedVideo extends UploadedFile {
        public final int durationUSec;
        public final int height;
        public final int width;

        public UploadedVideo(HashMap<String, Object> hashMap) {
            int i;
            int i2;
            int i3 = -1;
            super(hashMap);
            try {
                i2 = Integer.parseInt(String.valueOf(hashMap.get("time")));
                try {
                    i = Integer.parseInt(String.valueOf(hashMap.get("height")));
                    try {
                        i3 = Integer.parseInt(String.valueOf(hashMap.get("width")));
                    } catch (Throwable th) {
                    }
                } catch (Throwable th2) {
                    i = i3;
                }
            } catch (Throwable th3) {
                i = i3;
                i2 = i3;
            }
            this.durationUSec = i2;
            this.width = i3;
            this.height = i;
        }
    }

    public static void setUploadServer(String str) {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        a = h.b(str);
    }

    public static UploadedImage uploadImage(MobProduct mobProduct, String str) throws Throwable {
        return uploadImage(mobProduct, str, false, new int[0]);
    }

    private static String a() {
        return a + "/image";
    }

    public static UploadedImage uploadImage(MobProduct mobProduct, String str, boolean z, int... iArr) throws Throwable {
        String str2 = null;
        if (iArr != null && iArr.length > 0) {
            String str3 = "";
            int length = iArr.length;
            for (int i = 0; i < length; i++) {
                str3 = str3 + StorageInterface.KEY_SPLITER + iArr[i];
            }
            if (str3.length() > 0) {
                str2 = str3.substring(1);
            }
        }
        return new UploadedImage(a(mobProduct, str, z, str2, a()));
    }

    public static UploadedAvatar uploadAvatar(MobProduct mobProduct, String str) throws Throwable {
        return uploadAvatar(mobProduct, str, false, new int[0]);
    }

    private static String b() {
        return a + "/avatar";
    }

    public static UploadedAvatar uploadAvatar(MobProduct mobProduct, String str, boolean z, int... iArr) throws Throwable {
        String str2 = null;
        if (iArr != null && iArr.length > 0) {
            String str3 = "";
            int length = iArr.length;
            for (int i = 0; i < length; i++) {
                str3 = str3 + StorageInterface.KEY_SPLITER + iArr[i];
            }
            if (str3.length() > 0) {
                str2 = str3.substring(1);
            }
        }
        return new UploadedAvatar(a(mobProduct, str, z, str2, b()));
    }

    private static String c() {
        return a + "/video";
    }

    public static UploadedVideo uploadVideo(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedVideo(a(mobProduct, str, hashMap, c(), 209715200));
    }

    private static String d() {
        return a + "/audio";
    }

    public static UploadedAudio uploadAudio(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedAudio(a(mobProduct, str, hashMap, d(), 209715200));
    }

    private static String e() {
        return a + "/file";
    }

    public static UploadedFile uploadFile(MobProduct mobProduct, String str, boolean z) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        return new UploadedFile(a(mobProduct, str, hashMap, e(), (long) Config.RAVEN_LOG_LIMIT));
    }

    private static Throwable a(long j, long j2) {
        return new Throwable("{\"status\": ,\"error\":\"max size: " + j2 + ", file size: " + j + "\"}");
    }

    private static HashMap<String, Object> a(MobProduct mobProduct, String str, boolean z, String str2, String str3) throws Throwable {
        HashMap hashMap = new HashMap();
        hashMap.put("sm", Integer.valueOf(z ? 2 : 1));
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("zoomScaleWidths", str2);
        }
        return a(mobProduct, str, hashMap, str3, (long) Config.FULL_TRACE_LOG_LIMIT);
    }

    private static HashMap<String, Object> a(MobProduct mobProduct, String str, HashMap<String, Object> hashMap, String str2, long j) throws Throwable {
        if (a.ad()) {
            throw new ForbThrowable();
        }
        File file = new File(str);
        if (file.length() > j) {
            throw a(file.length(), j);
        }
        NetworkHelper networkHelper = new NetworkHelper();
        ArrayList a2 = a(mobProduct, a(mobProduct));
        ArrayList arrayList = new ArrayList();
        for (String str3 : hashMap.keySet()) {
            arrayList.add(new KVPair(str3, String.valueOf(hashMap.get(str3))));
        }
        KVPair kVPair = new KVPair("file", str);
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = Config.SESSION_PERIOD;
        networkTimeOut.connectionTimeout = ReaderCallback.GET_BAR_ANIMATING;
        String httpPost = networkHelper.httpPost(str2, arrayList, kVPair, a2, networkTimeOut);
        HashMap fromJson = new Hashon().fromJson(httpPost);
        if (fromJson != null && "200".equals(String.valueOf(fromJson.get(NotificationCompat.CATEGORY_STATUS)))) {
            return (HashMap) fromJson.get("res");
        }
        throw new Throwable(httpPost);
    }

    private static String f() {
        return a + "/getToken";
    }

    private static String a(MobProduct mobProduct) throws Throwable {
        NetworkHelper networkHelper = new NetworkHelper();
        ArrayList a2 = a(mobProduct, "1234567890abcdeffedcba0987654321");
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = Config.SESSION_PERIOD;
        networkTimeOut.connectionTimeout = ReaderCallback.GET_BAR_ANIMATING;
        String httpPost = networkHelper.httpPost(f(), null, null, a2, networkTimeOut);
        HashMap fromJson = new Hashon().fromJson(httpPost);
        if (fromJson == null || !"200".equals(String.valueOf(fromJson.get(NotificationCompat.CATEGORY_STATUS)))) {
            throw new Throwable(httpPost);
        }
        try {
            return (String) ((HashMap) fromJson.get("res")).get(Constants.FLAG_TOKEN);
        } catch (Throwable th) {
            throw new Throwable(httpPost, th);
        }
    }

    private static ArrayList<KVPair<String>> a(MobProduct mobProduct, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair("sign", Data.MD5(str + MobSDK.getAppSecret())));
        arrayList.add(new KVPair(SettingsContentProvider.KEY, MobSDK.getAppkey()));
        arrayList.add(new KVPair(Constants.FLAG_TOKEN, str));
        arrayList.add(new KVPair("product", mobProduct.getProductTag()));
        arrayList.add(new KVPair("cliid", DeviceAuthorizer.authorize(mobProduct)));
        return arrayList;
    }
}
