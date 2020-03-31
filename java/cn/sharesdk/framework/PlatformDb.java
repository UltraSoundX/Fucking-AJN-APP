package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.SharePrefrenceHelper;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import java.util.HashMap;

public class PlatformDb {
    private static final String DB_NAME = "cn_sharesdk_weibodb";
    private String platformNname;
    private int platformVersion;
    private SharePrefrenceHelper sp = new SharePrefrenceHelper(MobSDK.getContext());

    public PlatformDb(String str, int i) {
        this.sp.open("cn_sharesdk_weibodb_" + str, i);
        this.platformNname = str;
        this.platformVersion = i;
    }

    public void put(String str, String str2) {
        this.sp.putString(str, str2);
    }

    public String get(String str) {
        return this.sp.getString(str);
    }

    public String getToken() {
        return this.sp.getString(Constants.FLAG_TOKEN);
    }

    public void putToken(String str) {
        this.sp.putString(Constants.FLAG_TOKEN, str);
    }

    public String getTokenSecret() {
        return this.sp.getString("secret");
    }

    public void putTokenSecret(String str) {
        this.sp.putString("secret", str);
    }

    public long getExpiresIn() {
        try {
            return this.sp.getLong("expiresIn");
        } catch (Throwable th) {
            return 0;
        }
    }

    public void putExpiresIn(long j) {
        this.sp.putLong("expiresIn", Long.valueOf(j));
        this.sp.putLong("expiresTime", Long.valueOf(System.currentTimeMillis()));
    }

    public long getExpiresTime() {
        return this.sp.getLong("expiresTime") + (getExpiresIn() * 1000);
    }

    public int getPlatformVersion() {
        return this.platformVersion;
    }

    public String getPlatformNname() {
        return this.platformNname;
    }

    public void putUserId(String str) {
        this.sp.putString("userID", str);
    }

    public String getUserId() {
        String string = this.sp.getString("userID");
        if (TextUtils.isEmpty(string)) {
            return this.sp.getString("weibo");
        }
        return string;
    }

    public String getUserName() {
        return this.sp.getString("nickname");
    }

    public String getUserIcon() {
        return this.sp.getString(MessageKey.MSG_ICON);
    }

    public void removeAccount() {
        this.sp.clear();
    }

    public String exportData() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.putAll(this.sp.getAll());
            return new Hashon().fromHashMap(hashMap);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    public void importData(String str) {
        try {
            HashMap fromJson = new Hashon().fromJson(str);
            if (fromJson != null) {
                this.sp.putAll(fromJson);
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
    }

    public boolean isValid() {
        String token = getToken();
        if (token == null || token.length() <= 0) {
            return false;
        }
        if (getExpiresIn() == 0 || getExpiresTime() > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public String getUserGender() {
        String string = this.sp.getString("gender");
        if ("0".equals(string)) {
            return Config.MODEL;
        }
        if ("1".equals(string)) {
            return "f";
        }
        return null;
    }
}
