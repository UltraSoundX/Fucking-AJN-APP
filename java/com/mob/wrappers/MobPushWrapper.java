package com.mob.wrappers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.pushsdk.MobPush;
import com.mob.pushsdk.MobPushCallback;
import com.mob.pushsdk.MobPushCustomMessage;
import com.mob.pushsdk.MobPushCustomNotification;
import com.mob.pushsdk.MobPushLocalNotification;
import com.mob.pushsdk.MobPushNotifyMessage;
import com.mob.pushsdk.MobPushReceiver;
import com.mob.tools.proguard.ClassKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.UIHandler;
import java.io.Serializable;
import java.util.HashMap;

public class MobPushWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static HashMap<MobPushReceiverWrapper, Object> receiverWrapperMap = null;
    private static int state;

    public interface MobPushCallbackWrapper<T> {
        void onSuccess(T t);
    }

    public static class MobPushCustomMessageWrapper implements ClassKeeper, Serializable {
        private String content;
        private HashMap<String, String> extrasMap;
        private String messageId;
        private long timestamp;

        public MobPushCustomMessageWrapper(String str, HashMap<String, String> hashMap, String str2, long j) {
            this.content = str;
            this.extrasMap = hashMap;
            this.messageId = str2;
            this.timestamp = j;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setExtrasMap(HashMap<String, String> hashMap) {
            this.extrasMap = hashMap;
        }

        public void setMessageId(String str) {
            this.messageId = str;
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public String getContent() {
            return this.content;
        }

        public HashMap<String, String> getExtrasMap() {
            return this.extrasMap;
        }

        public String getMessageId() {
            return this.messageId;
        }

        public long getTimestamp() {
            return this.timestamp;
        }
    }

    public interface MobPushCustomNotificationWrapper {
        Notification getNotification(Context context, NotificationManager notificationManager, long j, String str, String str2, String str3, int i, int i2, String str4, String[] strArr, boolean z, boolean z2, boolean z3);
    }

    class MobPushLocalNotificationWrapper extends MobPushNotifyMessageWrapper {
        private int notificationId;

        public MobPushLocalNotificationWrapper() {
        }

        public MobPushLocalNotificationWrapper(int i, String str, String str2, String str3, String[] strArr, HashMap<String, String> hashMap, String str4, long j, boolean z, boolean z2, boolean z3) {
            super(i, str, str2, str3, strArr, hashMap, str4, j, z, z2, z3);
        }

        public void setNotificationId(int i) {
            this.notificationId = i;
        }

        public int getNotificationId() {
            return this.notificationId;
        }
    }

    public static class MobPushNotifyMessageWrapper implements ClassKeeper, Serializable {
        private String content;
        private HashMap<String, String> extrasMap;
        private String[] inboxStyleContent;
        private boolean light = true;
        private String messageId;
        private boolean shake = true;
        private int style;
        private String styleContent;
        private long timestamp;
        private String title;
        private boolean voice = true;

        public MobPushNotifyMessageWrapper() {
        }

        public MobPushNotifyMessageWrapper(int i, String str, String str2, String str3, String[] strArr, HashMap<String, String> hashMap, String str4, long j, boolean z, boolean z2, boolean z3) {
            this.style = i;
            this.title = str;
            this.content = str2;
            this.styleContent = str3;
            this.inboxStyleContent = strArr;
            this.extrasMap = hashMap;
            this.messageId = str4;
            this.timestamp = j;
            this.voice = z;
            this.shake = z2;
            this.light = z3;
        }

        public void setMessageId(String str) {
            this.messageId = str;
        }

        public void setVoice(boolean z) {
            this.voice = z;
        }

        public void setLight(boolean z) {
            this.light = z;
        }

        public void setShake(boolean z) {
            this.shake = z;
        }

        public void setStyle(int i) {
            this.style = i;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setStyleContent(String str) {
            this.styleContent = str;
        }

        public void setInboxStyleContent(String[] strArr) {
            this.inboxStyleContent = strArr;
        }

        public void setExtrasMap(HashMap<String, String> hashMap) {
            this.extrasMap = hashMap;
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public int getStyle() {
            return this.style;
        }

        public boolean isVoice() {
            return this.voice;
        }

        public boolean isShake() {
            return this.shake;
        }

        public boolean isLight() {
            return this.light;
        }

        public String getTitle() {
            return this.title;
        }

        public String getContent() {
            return this.content;
        }

        public HashMap<String, String> getExtrasMap() {
            return this.extrasMap;
        }

        public String getMessageId() {
            return this.messageId;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public String getStyleContent() {
            return this.styleContent;
        }

        public String[] getInboxStyleContent() {
            return this.inboxStyleContent;
        }
    }

    public interface MobPushReceiverWrapper {
        void onAliasCallback(Context context, String str, int i, int i2);

        void onCustomMessageReceive(Context context, MobPushCustomMessageWrapper mobPushCustomMessageWrapper);

        void onNotifyMessageOpenedReceive(Context context, MobPushNotifyMessageWrapper mobPushNotifyMessageWrapper);

        void onNotifyMessageReceive(Context context, MobPushNotifyMessageWrapper mobPushNotifyMessageWrapper);

        void onTagsCallback(Context context, String[] strArr, int i, int i2);
    }

    private static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (MobPushWrapper.class) {
            if (state == 0) {
                state = isAvailable("MOBPUSH");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static void getRegistrationId(final MobPushCallbackWrapper<String> mobPushCallbackWrapper) {
        if (isAvailable()) {
            try {
                MobPush.getRegistrationId(new MobPushCallback<String>() {
                    public void onCallback(String str) {
                        if (mobPushCallbackWrapper != null) {
                            mobPushCallbackWrapper.onSuccess(str);
                        }
                    }
                });
            } catch (Throwable th) {
                if (mobPushCallbackWrapper != null) {
                    UIHandler.sendEmptyMessage(0, new Callback() {
                        public boolean handleMessage(Message message) {
                            mobPushCallbackWrapper.onSuccess(null);
                            return false;
                        }
                    });
                }
            }
        } else if (mobPushCallbackWrapper != null) {
            UIHandler.sendEmptyMessage(0, new Callback() {
                public boolean handleMessage(Message message) {
                    mobPushCallbackWrapper.onSuccess(null);
                    return false;
                }
            });
        }
    }

    public static void addPushReceiver(final MobPushReceiverWrapper mobPushReceiverWrapper) {
        if (mobPushReceiverWrapper != null && isAvailable()) {
            MobPushReceiver mobPushReceiver = null;
            try {
                if (receiverWrapperMap == null) {
                    receiverWrapperMap = new HashMap<>();
                } else {
                    mobPushReceiver = (MobPushReceiver) receiverWrapperMap.get(mobPushReceiverWrapper);
                }
                if (mobPushReceiver == null) {
                    AnonymousClass4 r0 = new MobPushReceiver() {
                        public void onCustomMessageReceive(Context context, MobPushCustomMessage mobPushCustomMessage) {
                            MobPushCustomMessageWrapper mobPushCustomMessageWrapper = null;
                            if (mobPushCustomMessage != null) {
                                mobPushCustomMessageWrapper = new MobPushCustomMessageWrapper(mobPushCustomMessage.getContent(), mobPushCustomMessage.getExtrasMap(), mobPushCustomMessage.getMessageId(), mobPushCustomMessage.getTimestamp());
                            }
                            mobPushReceiverWrapper.onCustomMessageReceive(context, mobPushCustomMessageWrapper);
                        }

                        public void onNotifyMessageReceive(Context context, MobPushNotifyMessage mobPushNotifyMessage) {
                            MobPushNotifyMessageWrapper mobPushNotifyMessageWrapper = null;
                            if (mobPushNotifyMessage != null) {
                                mobPushNotifyMessageWrapper = new MobPushNotifyMessageWrapper(mobPushNotifyMessage.getStyle(), mobPushNotifyMessage.getTitle(), mobPushNotifyMessage.getContent(), mobPushNotifyMessage.getStyleContent(), mobPushNotifyMessage.getInboxStyleContent(), mobPushNotifyMessage.getExtrasMap(), mobPushNotifyMessage.getMessageId(), mobPushNotifyMessage.getTimestamp(), mobPushNotifyMessage.isVoice(), mobPushNotifyMessage.isShake(), mobPushNotifyMessage.isLight());
                            }
                            mobPushReceiverWrapper.onNotifyMessageReceive(context, mobPushNotifyMessageWrapper);
                        }

                        public void onNotifyMessageOpenedReceive(Context context, MobPushNotifyMessage mobPushNotifyMessage) {
                            MobPushNotifyMessageWrapper mobPushNotifyMessageWrapper = null;
                            if (mobPushNotifyMessage != null) {
                                mobPushNotifyMessageWrapper = new MobPushNotifyMessageWrapper(mobPushNotifyMessage.getStyle(), mobPushNotifyMessage.getTitle(), mobPushNotifyMessage.getContent(), mobPushNotifyMessage.getStyleContent(), mobPushNotifyMessage.getInboxStyleContent(), mobPushNotifyMessage.getExtrasMap(), mobPushNotifyMessage.getMessageId(), mobPushNotifyMessage.getTimestamp(), mobPushNotifyMessage.isVoice(), mobPushNotifyMessage.isShake(), mobPushNotifyMessage.isLight());
                            }
                            mobPushReceiverWrapper.onNotifyMessageOpenedReceive(context, mobPushNotifyMessageWrapper);
                        }

                        public void onTagsCallback(Context context, String[] strArr, int i, int i2) {
                            mobPushReceiverWrapper.onTagsCallback(context, strArr, i, i2);
                        }

                        public void onAliasCallback(Context context, String str, int i, int i2) {
                            mobPushReceiverWrapper.onAliasCallback(context, str, i, i2);
                        }
                    };
                    receiverWrapperMap.put(mobPushReceiverWrapper, r0);
                    MobPush.addPushReceiver(r0);
                }
            } catch (Throwable th) {
            }
        }
    }

    public static void removePushReceiver(MobPushReceiverWrapper mobPushReceiverWrapper) {
        if (mobPushReceiverWrapper != null && isAvailable()) {
            try {
                if (receiverWrapperMap != null) {
                    Object obj = receiverWrapperMap.get(mobPushReceiverWrapper);
                    if (obj != null) {
                        MobPush.removePushReceiver((MobPushReceiver) obj);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    public static void stopPush() {
        if (isAvailable()) {
            try {
                MobPush.stopPush();
            } catch (Throwable th) {
            }
        }
    }

    public static void restartPush() {
        if (isAvailable()) {
            try {
                MobPush.restartPush();
            } catch (Throwable th) {
            }
        }
    }

    public static boolean isPushStopped() {
        if (isAvailable()) {
            try {
                return MobPush.isPushStopped();
            } catch (Throwable th) {
            }
        }
        return false;
    }

    public static void setAlias(String str) {
        if (isAvailable()) {
            try {
                MobPush.setAlias(str);
            } catch (Throwable th) {
            }
        }
    }

    public static void getAlias() {
        if (isAvailable()) {
            try {
                MobPush.getAlias();
            } catch (Throwable th) {
            }
        }
    }

    public static void deleteAlias() {
        if (isAvailable()) {
            try {
                MobPush.deleteAlias();
            } catch (Throwable th) {
            }
        }
    }

    public static void addTags(String[] strArr) {
        if (isAvailable()) {
            try {
                MobPush.addTags(strArr);
            } catch (Throwable th) {
            }
        }
    }

    public static void getTags() {
        if (isAvailable()) {
            try {
                MobPush.getTags();
            } catch (Throwable th) {
            }
        }
    }

    public static void deleteTags(String[] strArr) {
        if (isAvailable()) {
            try {
                MobPush.deleteTags(strArr);
            } catch (Throwable th) {
            }
        }
    }

    public static void cleanTags() {
        if (isAvailable()) {
            try {
                MobPush.cleanTags();
            } catch (Throwable th) {
            }
        }
    }

    public static void setSilenceTime(int i, int i2, int i3, int i4) {
        if (isAvailable()) {
            try {
                MobPush.setSilenceTime(i, i2, i3, i4);
            } catch (Throwable th) {
            }
        }
    }

    public static void setCustomNotification(final MobPushCustomNotificationWrapper mobPushCustomNotificationWrapper) {
        if (!isAvailable()) {
            return;
        }
        if (mobPushCustomNotificationWrapper == null) {
            try {
                MobPush.setCustomNotification(null);
            } catch (Throwable th) {
            }
        } else {
            MobPush.setCustomNotification(new MobPushCustomNotification() {
                public Notification getNotification(Context context, NotificationManager notificationManager, long j, String str, String str2, String str3, int i, int i2, String str4, String[] strArr, boolean z, boolean z2, boolean z3) {
                    try {
                        return mobPushCustomNotificationWrapper.getNotification(context, notificationManager, j, str, str2, str3, i, i2, str4, strArr, z, z2, z3);
                    } catch (Throwable th) {
                        return new Notification();
                    }
                }
            });
        }
    }

    public static boolean addLocalNotification(MobPushLocalNotificationWrapper mobPushLocalNotificationWrapper) {
        if (!isAvailable() || mobPushLocalNotificationWrapper == null) {
            return false;
        }
        MobPushLocalNotification mobPushLocalNotification = new MobPushLocalNotification(mobPushLocalNotificationWrapper.getStyle(), mobPushLocalNotificationWrapper.getTitle(), mobPushLocalNotificationWrapper.getContent(), mobPushLocalNotificationWrapper.getStyleContent(), mobPushLocalNotificationWrapper.getInboxStyleContent(), mobPushLocalNotificationWrapper.getExtrasMap(), mobPushLocalNotificationWrapper.getMessageId(), mobPushLocalNotificationWrapper.getTimestamp(), mobPushLocalNotificationWrapper.isVoice(), mobPushLocalNotificationWrapper.isShake(), mobPushLocalNotificationWrapper.isLight());
        mobPushLocalNotification.setNotificationId(mobPushLocalNotificationWrapper.getNotificationId());
        return MobPush.addLocalNotification(mobPushLocalNotification);
    }

    public static boolean removeLocalNotification(int i) {
        if (isAvailable()) {
            return MobPush.removeLocalNotification(i);
        }
        return false;
    }

    public static boolean clearLocalNotifications() {
        if (isAvailable()) {
            return MobPush.clearLocalNotifications();
        }
        return false;
    }
}
