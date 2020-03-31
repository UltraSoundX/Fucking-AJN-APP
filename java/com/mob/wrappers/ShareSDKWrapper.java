package com.mob.wrappers;

import android.os.Handler.Callback;
import android.os.Message;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.mob.MobSDK;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import java.lang.reflect.Field;
import java.util.HashMap;

public class ShareSDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    public interface CallbackWrapper {
        void onCancel(String str, int i);

        void onComplete(String str, int i, HashMap<String, Object> hashMap);

        void onError(String str, int i, Throwable th);
    }

    private static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (ShareSDKWrapper.class) {
            if (state == 0) {
                state = isAvailable(ShareSDK.SDK_TAG);
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static String[] getPlatformList() {
        if (!isAvailable()) {
            return null;
        }
        Platform[] platformList = ShareSDK.getPlatformList();
        if (platformList == null) {
            return null;
        }
        String[] strArr = new String[platformList.length];
        for (int i = 0; i < platformList.length; i++) {
            strArr[i] = platformList[i].getName();
        }
        return strArr;
    }

    public static void login(final String str, final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            Platform platform = ShareSDK.getPlatform(str);
            if (platform == null) {
                UIHandler.sendEmptyMessage(0, new Callback() {
                    public boolean handleMessage(Message message) {
                        callbackWrapper.onError(str, 8, new Throwable("platform " + str + " is not exist or disable"));
                        return false;
                    }
                });
                return;
            }
            platform.showUser(null);
            if (callbackWrapper != null) {
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
        } else if (callbackWrapper != null) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                public boolean handleMessage(Message message) {
                    callbackWrapper.onError(str, 8, new Throwable("platform " + str + " is not exist or disable"));
                    return false;
                }
            });
        }
    }

    public static void logout(String str) {
        if (isAvailable()) {
            Platform platform = ShareSDK.getPlatform(str);
            if (platform != null) {
                platform.removeAccount(true);
            }
        }
    }

    public static int getPlatformToId(String str) {
        if (isAvailable()) {
            return ShareSDK.platformNameToId(str);
        }
        return 0;
    }

    public static String getDbData(String str) {
        if (!isAvailable()) {
            return null;
        }
        Platform platform = ShareSDK.getPlatform(str);
        if (platform != null) {
            return platform.getDb().exportData();
        }
        return null;
    }

    public static String getToken(String str) {
        if (!isAvailable()) {
            return null;
        }
        Platform platform = ShareSDK.getPlatform(str);
        if (platform != null) {
            return platform.getDb().getToken();
        }
        return null;
    }

    public static String getUserID(String str) {
        if (!isAvailable()) {
            return null;
        }
        Platform platform = ShareSDK.getPlatform(str);
        if (platform != null) {
            return platform.getDb().getUserId();
        }
        return null;
    }

    public static String getUserName(String str) {
        if (!isAvailable()) {
            return null;
        }
        Platform platform = ShareSDK.getPlatform(str);
        if (platform != null) {
            return platform.getDb().getUserName();
        }
        return null;
    }

    public static boolean isLogin(String str) {
        if (!isAvailable()) {
            return false;
        }
        Platform platform = ShareSDK.getPlatform(str);
        if (platform != null) {
            return platform.isAuthValid();
        }
        return false;
    }

    public static void share(final String str, HashMap<String, Object> hashMap, final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            Platform platform = ShareSDK.getPlatform(str);
            if (platform == null) {
                UIHandler.sendEmptyMessage(0, new Callback() {
                    public boolean handleMessage(Message message) {
                        callbackWrapper.onError(str, 9, new Throwable("platform " + str + " is not exist or disable"));
                        return false;
                    }
                });
                return;
            }
            ShareParams shareParams = new ShareParams(hashMap);
            if (callbackWrapper != null) {
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
            platform.share(shareParams);
        } else if (callbackWrapper != null) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                public boolean handleMessage(Message message) {
                    callbackWrapper.onError(str, 9, new Throwable("platform " + str + " is not exist or disable"));
                    return false;
                }
            });
        }
    }

    private static boolean isOneKeyShareAvailable() {
        try {
            if (new OnekeyShare() != null) {
                return true;
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static void oneKeyShare(String str, HashMap<String, Object> hashMap, final CallbackWrapper callbackWrapper) {
        if (isOneKeyShareAvailable()) {
            OnekeyShare onekeyShare = new OnekeyShare();
            if (str != null) {
                onekeyShare.setPlatform(str);
            }
            if (callbackWrapper != null) {
                onekeyShare.setCallback(new PlatformActionListener() {
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        callbackWrapper.onComplete(platform.getName(), i, hashMap);
                    }

                    public void onError(Platform platform, int i, Throwable th) {
                        callbackWrapper.onError(platform.getName(), i, th);
                    }

                    public void onCancel(Platform platform, int i) {
                        callbackWrapper.onCancel(platform.getName(), i);
                    }
                });
            }
            Field[] declaredFields = onekeyShare.getClass().getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                if (HashMap.class.isAssignableFrom(field.getType())) {
                    try {
                        field.setAccessible(true);
                        ((HashMap) field.get(onekeyShare)).putAll(hashMap);
                        break;
                    } catch (Throwable th) {
                    }
                } else {
                    i++;
                }
            }
            onekeyShare.show(MobSDK.getContext());
        }
    }

    public static void oneKeyShare(HashMap<String, Object> hashMap, CallbackWrapper callbackWrapper) {
        oneKeyShare(null, hashMap, callbackWrapper);
    }
}
