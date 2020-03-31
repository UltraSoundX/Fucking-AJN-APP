package org.litepal.util;

import android.text.TextUtils;
import com.tencent.android.tpush.SettingsContentProvider;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import org.litepal.LitePalApplication;
import org.litepal.exceptions.DataSupportException;
import org.litepal.parser.LitePalAttr;
import org.litepal.util.Const.Config;

public class BaseUtility {
    private BaseUtility() {
    }

    public static String changeCase(String str) {
        if (str == null) {
            return null;
        }
        String cases = LitePalAttr.getInstance().getCases();
        if (Config.CASES_KEEP.equals(cases)) {
            return str;
        }
        if (Config.CASES_UPPER.equals(cases)) {
            return str.toUpperCase(Locale.US);
        }
        return str.toLowerCase(Locale.US);
    }

    public static boolean containsIgnoreCases(Collection<String> collection, String str) {
        boolean z;
        if (collection == null) {
            return false;
        }
        if (str == null) {
            return collection.contains(null);
        }
        Iterator it = collection.iterator();
        while (true) {
            if (it.hasNext()) {
                if (str.equalsIgnoreCase((String) it.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    public static String capitalize(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.substring(0, 1).toUpperCase(Locale.US) + str.substring(1);
        }
        if (str == null) {
            return null;
        }
        return "";
    }

    public static int count(String str, String str2) {
        int i = 0;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int indexOf = str.indexOf(str2);
            while (indexOf != -1) {
                int i2 = i + 1;
                str = str.substring(str2.length() + indexOf);
                indexOf = str.indexOf(str2);
                i = i2;
            }
        }
        return i;
    }

    public static void checkConditionsCorrect(String... strArr) {
        if (strArr != null) {
            int length = strArr.length;
            if (length > 0 && length != count(strArr[0], "?") + 1) {
                throw new DataSupportException(DataSupportException.UPDATE_CONDITIONS_EXCEPTION);
            }
        }
    }

    public static boolean isFieldTypeSupported(String str) {
        if ("boolean".equals(str) || "java.lang.Boolean".equals(str) || "float".equals(str) || "java.lang.Float".equals(str) || "double".equals(str) || "java.lang.Double".equals(str) || "int".equals(str) || "java.lang.Integer".equals(str) || SettingsContentProvider.LONG_TYPE.equals(str) || "java.lang.Long".equals(str) || "short".equals(str) || "java.lang.Short".equals(str) || "char".equals(str) || "java.lang.Character".equals(str) || "[B".equals(str) || "[Ljava.lang.Byte;".equals(str) || "java.lang.String".equals(str) || "java.util.Date".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isGenericTypeSupported(String str) {
        if (!"java.lang.String".equals(str) && !"java.lang.Integer".equals(str) && !"java.lang.Float".equals(str) && !"java.lang.Double".equals(str) && !"java.lang.Long".equals(str) && !"java.lang.Short".equals(str) && !"java.lang.Boolean".equals(str) && !"java.lang.Character".equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isLitePalXMLExists() {
        try {
            String[] list = LitePalApplication.getContext().getAssets().list("");
            if (list == null || list.length <= 0) {
                return false;
            }
            for (String equalsIgnoreCase : list) {
                if (Config.CONFIGURATION_FILE_NAME.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
