package com.mob.wrappers;

import android.os.Handler.Callback;
import android.os.Message;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.SMSSDK.InitFlag;
import cn.smssdk.gui.RegisterPage;
import com.mob.MobSDK;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class SMSSDKWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    public interface BeforeSendMessage {
        boolean beforeSendMessage(String str, String str2);
    }

    public static abstract class CallbackWrapper implements PublicMemberKeeper {
        private static final HashSet<CallbackWrapper> CALLBACKS = new HashSet<>();
        /* access modifiers changed from: private */
        public Object handler;

        /* access modifiers changed from: protected */
        public abstract void afterEvent(int i, int i2, Object obj);

        public CallbackWrapper() {
            if (SMSSDKWrapper.isAvailable()) {
                this.handler = new EventHandler() {
                    public void afterEvent(final int i, final int i2, final Object obj) {
                        UIHandler.sendEmptyMessage(0, new Callback() {
                            public boolean handleMessage(Message message) {
                                CallbackWrapper.this.afterEvent(i, i2, obj);
                                return false;
                            }
                        });
                    }
                };
            }
        }

        /* access modifiers changed from: private */
        public void registerEventHandler() {
            if (SMSSDKWrapper.isAvailable()) {
                SMSSDK.registerEventHandler((EventHandler) this.handler);
                return;
            }
            synchronized (CALLBACKS) {
                CALLBACKS.add(this);
            }
        }

        /* access modifiers changed from: private */
        public void unregisterEventHandler() {
            if (SMSSDKWrapper.isAvailable()) {
                SMSSDK.unregisterEventHandler((EventHandler) this.handler);
                return;
            }
            synchronized (CALLBACKS) {
                CALLBACKS.remove(this);
            }
        }

        /* access modifiers changed from: private */
        public static void broadcastNotAvailable(int i) {
            Throwable th = new Throwable("SMSSDK is not available");
            synchronized (CALLBACKS) {
                Iterator it = CALLBACKS.iterator();
                while (it.hasNext()) {
                    ((CallbackWrapper) it.next()).afterEvent(i, 0, th);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (SMSSDKWrapper.class) {
            if (state == 0) {
                state = isAvailable("SMSSDK");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void setAskPermisionOnReadContact(boolean z) {
        synchronized (SMSSDKWrapper.class) {
            if (isAvailable()) {
                SMSSDK.setInitFlag(z ? InitFlag.WARNNING_READCONTACT_DIALOG_MODE : InitFlag.DEFAULT);
            }
        }
    }

    public static void registerEventHandler(CallbackWrapper callbackWrapper) {
        callbackWrapper.registerEventHandler();
    }

    public static void unregisterEventHandler(CallbackWrapper callbackWrapper) {
        callbackWrapper.unregisterEventHandler();
    }

    public static synchronized HashMap<Character, ArrayList<String[]>> getGroupedCountryList() {
        HashMap<Character, ArrayList<String[]>> hashMap;
        synchronized (SMSSDKWrapper.class) {
            if (isAvailable()) {
                hashMap = SMSSDK.getGroupedCountryList();
            } else {
                hashMap = new HashMap<>();
            }
        }
        return hashMap;
    }

    public static void getSupportedCountries() {
        if (isAvailable()) {
            SMSSDK.getSupportedCountries();
        } else {
            CallbackWrapper.broadcastNotAvailable(1);
        }
    }

    public static void getVerificationCode(String str, String str2) {
        getVerificationCode(str, str2, null);
    }

    public static void getVerificationCode(String str, String str2, final BeforeSendMessage beforeSendMessage) {
        if (isAvailable()) {
            AnonymousClass1 r0 = null;
            if (beforeSendMessage != null) {
                r0 = new OnSendMessageHandler() {
                    public boolean onSendMessage(String str, String str2) {
                        return beforeSendMessage.beforeSendMessage(str, str2);
                    }
                };
            }
            SMSSDK.getVerificationCode(str, str2, r0);
            return;
        }
        CallbackWrapper.broadcastNotAvailable(2);
    }

    public static synchronized void submitVerificationCode(String str, String str2, String str3) {
        synchronized (SMSSDKWrapper.class) {
            if (isAvailable()) {
                SMSSDK.submitVerificationCode(str, str2, str3);
            } else {
                CallbackWrapper.broadcastNotAvailable(3);
            }
        }
    }

    public static synchronized void getVoiceVerifyCode(String str, String str2) {
        synchronized (SMSSDKWrapper.class) {
            if (isAvailable()) {
                SMSSDK.getVoiceVerifyCode(str, str2);
            } else {
                CallbackWrapper.broadcastNotAvailable(8);
            }
        }
    }

    public static synchronized void showVerificationPage(final CallbackWrapper callbackWrapper) {
        synchronized (SMSSDKWrapper.class) {
            try {
                RegisterPage registerPage = new RegisterPage();
                if (callbackWrapper != null) {
                    registerPage.setRegisterCallback((EventHandler) callbackWrapper.handler);
                }
                registerPage.show(MobSDK.getContext());
            } catch (Throwable th) {
                if (callbackWrapper != null) {
                    UIHandler.sendEmptyMessage(0, new Callback() {
                        public boolean handleMessage(Message message) {
                            if (callbackWrapper != null) {
                                callbackWrapper.afterEvent(0, 0, th);
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }
}
