package com.tencent.mid.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.tencent.bigdata.dataacquisition.CustomDeviceInfos;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.MidProvider;
import com.tencent.mid.core.Constants;
import com.tencent.mid.sotrage.StorageInterface;
import com.tencent.mid.sotrage.UnifiedStorage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static final String AUTHORTY_POSTFIX = ".AUTH_XGPUSH";
    private static final String GET_TOKEN_CMD = "tokenByMid";
    public static int errorCount = 0;
    public static long lastGetOtherMidMapTime = 0;
    public static Map<String, MidEntity> lastOtherMidMap = null;
    private static Logger logger = null;
    private static Random random = null;

    static class MidResolver implements Runnable {
        private Map<String, MidEntity> appPrivateMidMap;
        private Context context;
        private int providerCmd;

        public MidResolver(Context context2, int i) {
            this.providerCmd = i;
            this.context = context2;
        }

        public void run() {
            try {
                set(Util.getMidsByAppsByThread(this.context, this.providerCmd));
            } catch (Exception e) {
            }
        }

        public synchronized void set(Map<String, MidEntity> map) {
            this.appPrivateMidMap = map;
        }

        public synchronized Map<String, MidEntity> get() {
            return this.appPrivateMidMap;
        }
    }

    public static synchronized Logger getLogger() {
        Logger logger2;
        synchronized (Util.class) {
            if (logger == null) {
                logger = new Logger(Constants.LOG_TAG);
            }
            logger2 = logger;
        }
        return logger2;
    }

    public static boolean isStringValid(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isMidValid(MidEntity midEntity) {
        if (midEntity == null || !isMidValid(midEntity.getMid())) {
            return false;
        }
        return true;
    }

    public static boolean isMidValid(String str) {
        if (str == null || str.trim().length() < 40) {
            return false;
        }
        return true;
    }

    public static MidEntity getNewerMidEntity(MidEntity midEntity, MidEntity midEntity2) {
        if (midEntity == null || midEntity2 == null) {
            if (midEntity != null) {
                return midEntity;
            }
            if (midEntity2 != null) {
                return midEntity2;
            }
            return null;
        } else if (midEntity.compairTo(midEntity2) >= 0) {
            return midEntity;
        } else {
            return midEntity2;
        }
    }

    public static boolean equal(MidEntity midEntity, MidEntity midEntity2) {
        if (midEntity == null || midEntity2 == null) {
            if (midEntity == null && midEntity2 == null) {
                return true;
            }
            return false;
        } else if (midEntity.compairTo(midEntity2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e(Constants.LOG_TAG, "checkPermission error", th);
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (!checkPermission(context, Constants.PERMISSION_INTERNET)) {
                return false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.isConnectedOrConnecting()) {
                        return true;
                    }
                    Log.w(Constants.LOG_TAG, "Network error is not exist");
                    return false;
                }
            }
            errorCount++;
            if (errorCount <= 5) {
                return true;
            }
            if (errorCount < 10) {
                return false;
            }
            if (errorCount >= 10) {
                errorCount = 0;
            }
            return false;
        } catch (Throwable th) {
            Log.e(Constants.LOG_TAG, "isNetworkAvailable error", th);
        }
    }

    public static String md5sum(String str) {
        return md5(str);
    }

    public static String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte append : digest) {
                stringBuffer.append(append);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void jsonPut(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (isStringValid(str2)) {
            jSONObject.put(str, str2);
        }
    }

    public static String decode(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(RC4.decrypt(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            Log.e(Constants.LOG_TAG, "decode error", th);
            return str;
        }
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(RC4.encrypt(str.getBytes("UTF-8")), 0), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            Log.e(Constants.LOG_TAG, "encode error", th);
            return str;
        }
    }

    public static HttpHost getHttpProxy() {
        if (Proxy.getDefaultHost() != null) {
            return new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort());
        }
        return null;
    }

    public static HttpHost getHttpProxy(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission(Constants.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            return null;
        } catch (Throwable th) {
            logger.e((Object) th);
        }
    }

    public static byte[] deocdeGZipContent(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String getHttpAddr(Context context) {
        return "http://" + ConstantUtil.getInstance(context).getHttpService();
    }

    public static byte[] getHMAC(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "hmacmd5");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            instance.update(str2.getBytes());
            return instance.doFinal();
        } catch (Exception e) {
            logger.e(e);
            return null;
        }
    }

    private static synchronized Random getRandom() {
        Random random2;
        synchronized (Util.class) {
            if (random == null) {
                random = new Random();
            }
            random2 = random;
        }
        return random2;
    }

    public static int getRandInt() {
        return getRandom().nextInt(Integer.MAX_VALUE);
    }

    public static String bytesToStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(bArr[i] + "");
        }
        return stringBuffer.toString();
    }

    public static byte[] strToBytes(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static JSONArray queryMids(Context context, int i) {
        int i2;
        MidEntity readSettingMidEntity;
        MidEntity readSdCarkMidEntity;
        logger.d("queryMids, midType=" + i);
        JSONArray jSONArray = new JSONArray();
        if (i == 2) {
            i2 = 3;
        } else {
            i2 = 2;
        }
        Map midsByApps = getMidsByApps(context, i2);
        if (midsByApps != null && midsByApps.size() > 0) {
            for (Entry entry : midsByApps.entrySet()) {
                String str = (String) entry.getKey();
                MidEntity midEntity = (MidEntity) entry.getValue();
                if (midEntity != null && midEntity.isMidValid()) {
                    try {
                        JSONObject convert2MidEntityJSON = convert2MidEntityJSON(midEntity);
                        convert2MidEntityJSON.put("loc", "priv");
                        if (str.equals(context.getPackageName())) {
                            convert2MidEntityJSON.put("app", 1);
                        }
                        convert2MidEntityJSON.put(Config.INPUT_DEF_PKG, str);
                        jSONArray.put(convert2MidEntityJSON);
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (i == 2) {
            readSettingMidEntity = UnifiedStorage.getInstance(context).readSettingNewVersionMidEntity();
        } else {
            readSettingMidEntity = UnifiedStorage.getInstance(context).readSettingMidEntity();
        }
        logger.i("settingEntity:" + readSettingMidEntity);
        if (readSettingMidEntity != null && readSettingMidEntity.isMidValid()) {
            try {
                JSONObject convert2MidEntityJSON2 = convert2MidEntityJSON(readSettingMidEntity);
                convert2MidEntityJSON2.put("loc", "pub");
                convert2MidEntityJSON2.put("lc", "set");
                jSONArray.put(convert2MidEntityJSON2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (i == 2) {
            readSdCarkMidEntity = UnifiedStorage.getInstance(context).readSdCarkNewVersionMidEntity();
        } else {
            readSdCarkMidEntity = UnifiedStorage.getInstance(context).readSdCarkMidEntity();
        }
        logger.i("sdCardEntity:" + readSdCarkMidEntity);
        if (readSdCarkMidEntity != null && readSdCarkMidEntity.isMidValid()) {
            try {
                JSONObject convert2MidEntityJSON3 = convert2MidEntityJSON(readSdCarkMidEntity);
                convert2MidEntityJSON3.put("loc", "pub");
                convert2MidEntityJSON3.put("lc", Config.FEED_LIST_MAPPING);
                jSONArray.put(convert2MidEntityJSON3);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return jSONArray;
    }

    private static JSONObject convert2MidEntityJSON(MidEntity midEntity) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", midEntity.getMid());
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, midEntity.getTimestamps() / 1000);
        return jSONObject;
    }

    public static String getPackageAuth(String str) {
        return "content://" + getPackageAuthName(str);
    }

    public static String getPackageAuthName(String str) {
        return str + MidConstants.PROVIDER_AUTH_SUFFIX;
    }

    public static Map<String, MidEntity> getMidsByApps(Context context, int i) {
        MidResolver midResolver = new MidResolver(context, i);
        Thread thread = new Thread(midResolver);
        thread.start();
        try {
            thread.join(3500);
        } catch (Throwable th) {
            logger.w(th.toString());
        }
        return midResolver.get();
    }

    /* access modifiers changed from: private */
    public static Map<String, MidEntity> getMidsByAppsByThread(Context context, int i) {
        HashMap hashMap = new HashMap(4);
        Map queryMatchContentProviders = queryMatchContentProviders(context);
        Log.i(Constants.LOG_TAG, ">>>   queryMatchContentProviders size:" + (queryMatchContentProviders != null ? queryMatchContentProviders.size() : 0));
        MidEntity midEntity = null;
        if (i == 2) {
            midEntity = UnifiedStorage.getInstance(context).readPrivateMidEntity();
        } else if (i == 3) {
            midEntity = UnifiedStorage.getInstance(context).readPrivateNewVersionMidEntity();
        }
        if (isMidValid(midEntity)) {
            hashMap.put(context.getPackageName(), midEntity);
        }
        if (queryMatchContentProviders == null || queryMatchContentProviders.size() == 0) {
            return hashMap;
        }
        if (lastOtherMidMap == null || lastOtherMidMap.isEmpty() || Math.abs(System.currentTimeMillis() - lastGetOtherMidMapTime) >= 1000) {
            for (String str : queryMatchContentProviders.keySet()) {
                try {
                    if (!str.equals(context.getPackageName())) {
                        String str2 = getPackageAuth(str) + "/" + i;
                        Log.d(Constants.LOG_TAG, ">>>   read mid from other providrt cmd:" + str2);
                        String type = context.getContentResolver().getType(Uri.parse(str2));
                        Log.d(Constants.LOG_TAG, ">>>   mid cmd:" + str2 + ", return:" + type);
                        if (!isEmpty(type)) {
                            MidEntity parse = MidEntity.parse(type);
                            if (parse != null && parse.isMidValid()) {
                                hashMap.put(str, parse);
                            }
                        }
                    }
                } catch (Throwable th) {
                    logger.e((Object) th);
                }
            }
            lastOtherMidMap = hashMap;
            lastGetOtherMidMapTime = System.currentTimeMillis();
            Log.d(Constants.LOG_TAG, ">>>   appPrivateMidMap size:" + hashMap.size() + ",content:");
            for (Entry entry : hashMap.entrySet()) {
                Log.w(Constants.LOG_TAG, ">>>   pkg:" + ((String) entry.getKey()) + ",midEntity:" + ((MidEntity) entry.getValue()).toString());
            }
            return hashMap;
        }
        Log.d(Constants.LOG_TAG, ">>> use lastOtherMidMap size:" + lastOtherMidMap.size() + ",content:");
        return lastOtherMidMap;
    }

    public static void insertMid2Provider(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            context.getContentResolver().insert(Uri.parse(getPackageAuth(str) + "/" + 10), contentValues);
        } catch (Throwable th) {
        }
    }

    public static void insertMid2OldProvider(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            context.getContentResolver().insert(Uri.parse(getPackageAuth(str) + "/" + 11), contentValues);
        } catch (Throwable th) {
        }
    }

    public static Map<String, ProviderInfo> queryMatchContentProviders(Context context) {
        HashMap hashMap = new HashMap();
        for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
            if (providerInfo.name.equals(MidProvider.class.getName()) && providerInfo.authority.equals(getPackageAuthName(providerInfo.packageName))) {
                hashMap.put(providerInfo.packageName, providerInfo);
            }
        }
        return hashMap;
    }

    public static void updateIfLocalInvalid(Context context, String str) {
        if (isMidValid(str)) {
            MidEntity midEntity = new MidEntity();
            midEntity.setImei(CustomDeviceInfos.getDeviceId(context));
            midEntity.setMac(CustomDeviceInfos.getMacAddress(context));
            midEntity.setMid(str);
            midEntity.setTimestamps(System.currentTimeMillis());
            UnifiedStorage.getInstance(context).writeMidEntity(midEntity);
        }
    }

    public static String getDateString(int i) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.roll(6, i);
            return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
        } catch (Throwable th) {
            return "00";
        }
    }

    public static Map<String, ProviderInfo> getLocalXGAppList(Context context) {
        HashMap hashMap = new HashMap();
        for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
            if (providerInfo.name.equals("com.tencent.android.tpush.XGPushProvider") && providerInfo.authority.equals(getProviderAuth(providerInfo.packageName))) {
                hashMap.put(providerInfo.packageName, providerInfo);
                Log.d("MID.XG", providerInfo.authority + StorageInterface.KEY_SPLITER + providerInfo.packageName + StorageInterface.KEY_SPLITER + providerInfo.name);
            }
        }
        return hashMap;
    }

    public static String getProviderAuth(String str) {
        return str + ".AUTH_XGPUSH";
    }

    public static String getToken(Context context, String str) {
        String str2;
        String type = context.getContentResolver().getType(Uri.parse("content://" + str + ".AUTH_XGPUSH" + "/" + GET_TOKEN_CMD));
        if (type != null) {
            try {
                str2 = new String(Base64.decode(type.getBytes(), 0), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("MID.XG", "get token from pkg:" + str + ", token:" + str2);
            if (str2 == null && str2.trim().length() == 40) {
                return str2;
            }
            return null;
        }
        str2 = type;
        Log.i("MID.XG", "get token from pkg:" + str + ", token:" + str2);
        if (str2 == null) {
        }
        return null;
    }

    public static Map<String, Integer> queryAllToken(Context context) {
        Map localXGAppList = getLocalXGAppList(context);
        HashMap hashMap = new HashMap();
        if (localXGAppList == null || localXGAppList.size() == 0) {
            return hashMap;
        }
        for (String token : localXGAppList.keySet()) {
            String token2 = getToken(context, token);
            if (isMidValid(token2)) {
                Integer num = (Integer) hashMap.get(token2);
                if (num == null) {
                    hashMap.put(token2, Integer.valueOf(1));
                } else {
                    hashMap.put(token2, Integer.valueOf(num.intValue() + 1));
                }
            }
        }
        return hashMap;
    }

    public static String selectMaxCountXgAppToken(Context context) {
        String str;
        int i;
        String str2 = null;
        Map queryAllToken = queryAllToken(context);
        if (queryAllToken != null && queryAllToken.size() > 0) {
            int i2 = 0;
            for (Entry entry : queryAllToken.entrySet()) {
                if (((Integer) entry.getValue()).intValue() > i2) {
                    i = ((Integer) entry.getValue()).intValue();
                    str = (String) entry.getKey();
                } else {
                    str = str2;
                    i = i2;
                }
                str2 = str;
                i2 = i;
            }
        }
        return str2;
    }
}
