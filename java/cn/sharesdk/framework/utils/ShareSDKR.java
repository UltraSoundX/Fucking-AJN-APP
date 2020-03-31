package cn.sharesdk.framework.utils;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.JSONTypes;

public class ShareSDKR {
    public static int getResId(Context context, String str, String str2) {
        int i = 0;
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String packageName = context.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                i = context.getResources().getIdentifier(str2, str, packageName);
                if (i <= 0) {
                    i = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
                }
                if (i <= 0) {
                    i = context.getResources().getIdentifier("ssdk_" + str2, str, packageName);
                    if (i <= 0) {
                        i = context.getResources().getIdentifier("ssdk_" + str2.toLowerCase(), str, packageName);
                    }
                }
                if (i <= 0) {
                    i = context.getResources().getIdentifier("ssdk_oks_" + str2, str, packageName);
                    if (i <= 0) {
                        i = context.getResources().getIdentifier("ssdk_oks_" + str2.toLowerCase(), str, packageName);
                    }
                }
                if (i <= 0) {
                    SSDKLog.b().w("failed to parse " + str + " resource \"" + str2 + JSONUtils.DOUBLE_QUOTE);
                }
            }
        }
        return i;
    }

    public static int getBitmapRes(Context context, String str) {
        return getResId(context, "drawable", str);
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, JSONTypes.ARRAY, str);
    }

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, "style", str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, Config.FEED_LIST_ITEM_CUSTOM_ID, str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }
}
