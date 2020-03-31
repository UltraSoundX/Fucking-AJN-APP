package com.tencent.android.tpush;

import android.support.v4.app.NotificationCompat;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ProGuard */
public enum TypeStr {
    config(10, "config"),
    config_all(11, "config/#"),
    msg(20, NotificationCompat.CATEGORY_MESSAGE),
    msg_all(21, "msg/#"),
    hearbeat(30, "heart"),
    hearbeat_all(31, "heart/#"),
    feedback(40, "feedback"),
    feedback_all(41, "feedback/#"),
    token(50, Constants.FLAG_TOKEN),
    register(60, "register"),
    pullupxg(70, "pullupxg"),
    insert_mid_new(80, "insert_mid_new"),
    insert_mid_old(81, "insert_mid_old");
    
    private String str;
    private int type;

    public static TypeStr getTypeStr(int i) {
        switch (i) {
            case 10:
                return config;
            case 11:
                return config_all;
            case 20:
                return msg;
            case 21:
                return msg_all;
            case 30:
                return hearbeat;
            case 31:
                return hearbeat_all;
            case 40:
                return feedback;
            case 41:
                return feedback_all;
            case 50:
                return token;
            case 60:
                return register;
            case 70:
                return pullupxg;
            case 80:
                return insert_mid_new;
            case 81:
                return insert_mid_old;
            default:
                return null;
        }
    }

    private TypeStr(int i, String str2) {
        this.type = i;
        this.str = str2;
    }

    public int getType() {
        return this.type;
    }

    public String getStr() {
        return this.str;
    }
}
