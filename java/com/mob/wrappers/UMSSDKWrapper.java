package com.mob.wrappers;

import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import com.mob.ums.OperationCallback;
import com.mob.ums.UMSSDK;
import com.mob.ums.gui.UMSGUI;

public class UMSSDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;
    private static int stateGUI;

    public interface CallbackWrapper {
        void onError(Throwable th);

        void onSuccess(User user);
    }

    public static class User implements PublicMemberKeeper {
        private com.mob.ums.User user;

        private User(com.mob.ums.User user2) {
            this.user = user2;
        }

        public String getUserId() {
            if (this.user == null) {
                return null;
            }
            return (String) this.user.id.get();
        }

        public String getNickname() {
            if (this.user == null) {
                return null;
            }
            return (String) this.user.nickname.get();
        }

        public String getAvatar() {
            if (this.user != null && !this.user.avatar.isNull()) {
                return ((String[]) this.user.avatar.get())[0];
            }
            return null;
        }
    }

    private static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (UMSSDKWrapper.class) {
            if (state == 0) {
                state = isAvailable("UMSSDK");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static String getLoginUserToken() {
        if (isAvailable()) {
            return UMSSDK.getLoginUserToken();
        }
        return null;
    }

    public static String getLoginUserId() {
        if (isAvailable()) {
            return UMSSDK.getLoginUserId();
        }
        return null;
    }

    public static boolean hasLogin() {
        return !TextUtils.isEmpty(getLoginUserToken());
    }

    public static void logout() {
        if (isAvailable()) {
            UMSSDK.logout(null);
        }
    }

    public static void getLoginUser(final CallbackWrapper callbackWrapper) {
        if (isAvailable()) {
            try {
                UMSSDK.getLoginUser(new OperationCallback<com.mob.ums.User>() {
                    public void onSuccess(com.mob.ums.User user) {
                        if (callbackWrapper != null) {
                            callbackWrapper.onSuccess(new User(user));
                        }
                    }

                    public void onFailed(Throwable th) {
                        if (callbackWrapper != null) {
                            callbackWrapper.onError(th);
                        }
                    }
                });
            } catch (Throwable th) {
                UIHandler.sendEmptyMessage(0, new Callback() {
                    public boolean handleMessage(Message message) {
                        if (callbackWrapper != null) {
                            callbackWrapper.onError(th);
                        }
                        return false;
                    }
                });
            }
        } else {
            unAvailable(callbackWrapper, 1);
        }
    }

    private static boolean isAvailableGUI() {
        if (stateGUI == 0) {
            stateGUI = -1;
            try {
                if (new UMSGUI() != null) {
                    stateGUI = 1;
                }
            } catch (Throwable th) {
            }
        }
        if (stateGUI == 1) {
            return true;
        }
        return false;
    }

    public static void showLogin(final CallbackWrapper callbackWrapper) {
        if (isAvailableGUI()) {
            UMSGUI.showLogin(new OperationCallback<com.mob.ums.User>() {
                public void onSuccess(com.mob.ums.User user) {
                    if (callbackWrapper != null) {
                        callbackWrapper.onSuccess(new User(user));
                    }
                }

                public void onFailed(Throwable th) {
                    if (callbackWrapper != null) {
                        callbackWrapper.onError(th);
                    }
                }
            });
        } else {
            unAvailable(callbackWrapper, 2);
        }
    }

    private static void unAvailable(final CallbackWrapper callbackWrapper, final int i) {
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                Throwable th;
                if (callbackWrapper != null) {
                    if (i == 2) {
                        th = new Throwable("UMSSDKGUI is not available");
                    } else {
                        th = new Throwable("UMSSDK is not available");
                    }
                    callbackWrapper.onError(th);
                }
                return false;
            }
        });
    }
}
