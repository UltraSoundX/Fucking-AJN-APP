package com.mob.commons;

import cn.sharesdk.framework.ShareSDK;

public class SHARESDK implements MobProduct {
    public String getProductTag() {
        return ShareSDK.SDK_TAG;
    }

    public int getSdkver() {
        return ShareSDK.SDK_VERSION_CODE;
    }
}
