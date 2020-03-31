package cn.sharesdk.framework;

import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.b.b.f.a;
import java.util.HashMap;

public abstract class CustomPlatform extends Platform {
    /* access modifiers changed from: protected */
    public abstract boolean checkAuthorize(int i, Object obj);

    public abstract String getName();

    /* access modifiers changed from: protected */
    public final void initDevInfo(String str) {
    }

    public int getVersion() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final int getPlatformId() {
        return -Math.abs(getCustomPlatformId());
    }

    /* access modifiers changed from: protected */
    public int getCustomPlatformId() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public final void setNetworkDevinfo() {
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
    }

    /* access modifiers changed from: protected */
    public void doShare(ShareParams shareParams) {
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
    }

    /* access modifiers changed from: protected */
    public void userInfor(String str) {
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
    }

    /* access modifiers changed from: protected */
    public final a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return false;
    }
}
