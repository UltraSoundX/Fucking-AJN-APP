package com.tencent.android.tpush;

/* compiled from: ProGuard */
public enum NotificationAction {
    clicked(0),
    activity(1),
    url(2),
    intent(3),
    action_package(4),
    intent_with_action(5),
    delete(2),
    open(3),
    open_cancel(4),
    download(5),
    download_cancel(6);
    
    private int type;

    private NotificationAction(int i) {
        this.type = i;
    }

    public static NotificationAction getNotificationAction(int i) {
        switch (i) {
            case 0:
                return clicked;
            case 1:
                return activity;
            case 2:
                return url;
            case 3:
                return intent;
            case 4:
                return action_package;
            case 5:
                return intent_with_action;
            default:
                return null;
        }
    }

    public int getType() {
        return this.type;
    }
}
