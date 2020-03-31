package org.litepal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.File;
import org.litepal.parser.LitePalAttr;
import org.litepal.parser.LitePalParser;
import org.litepal.tablemanager.Connector;
import org.litepal.util.BaseUtility;
import org.litepal.util.Const.Config;
import org.litepal.util.SharedUtil;

public class LitePal {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void initialize(Context context) {
        LitePalApplication.sContext = context;
    }

    public static SQLiteDatabase getDatabase() {
        return Connector.getDatabase();
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void use(LitePalDB litePalDB) {
        LitePalAttr instance = LitePalAttr.getInstance();
        instance.setDbName(litePalDB.getDbName());
        instance.setVersion(litePalDB.getVersion());
        instance.setStorage(litePalDB.isExternalStorage() ? "external" : "internal");
        instance.setClassNames(litePalDB.getClassNames());
        if (!isDefaultDatabase(litePalDB.getDbName())) {
            instance.setExtraKeyName(litePalDB.getDbName());
            instance.setCases(Config.CASES_LOWER);
        }
        Connector.clearLitePalOpenHelperInstance();
    }

    public static void useDefault() {
        LitePalAttr.clearInstance();
        Connector.clearLitePalOpenHelperInstance();
    }

    public static boolean deleteDatabase(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.endsWith(Config.DB_NAME_SUFFIX)) {
            str = str + Config.DB_NAME_SUFFIX;
        }
        File databasePath = LitePalApplication.getContext().getDatabasePath(str);
        if (databasePath.exists()) {
            boolean delete = databasePath.delete();
            if (!delete) {
                return delete;
            }
            removeVersionInSharedPreferences(str);
            Connector.clearLitePalOpenHelperInstance();
            return delete;
        }
        boolean delete2 = new File((LitePalApplication.getContext().getExternalFilesDir("") + "/databases/") + str).delete();
        if (!delete2) {
            return delete2;
        }
        removeVersionInSharedPreferences(str);
        Connector.clearLitePalOpenHelperInstance();
        return delete2;
    }

    private static void removeVersionInSharedPreferences(String str) {
        if (isDefaultDatabase(str)) {
            SharedUtil.removeVersion(null);
        } else {
            SharedUtil.removeVersion(str);
        }
    }

    private static boolean isDefaultDatabase(String str) {
        if (!BaseUtility.isLitePalXMLExists()) {
            return false;
        }
        if (!str.endsWith(Config.DB_NAME_SUFFIX)) {
            str = str + Config.DB_NAME_SUFFIX;
        }
        String dbName = LitePalParser.parseLitePalConfiguration().getDbName();
        if (!dbName.endsWith(Config.DB_NAME_SUFFIX)) {
            dbName = dbName + Config.DB_NAME_SUFFIX;
        }
        return str.equalsIgnoreCase(dbName);
    }
}
