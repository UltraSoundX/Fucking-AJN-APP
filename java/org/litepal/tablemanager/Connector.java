package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import org.litepal.LitePalApplication;
import org.litepal.parser.LitePalAttr;

public class Connector {
    private static LitePalOpenHelper mLitePalHelper;

    public static synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Connector.class) {
            writableDatabase = buildConnection().getWritableDatabase();
        }
        return writableDatabase;
    }

    @Deprecated
    public static synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase readableDatabase;
        synchronized (Connector.class) {
            readableDatabase = buildConnection().getReadableDatabase();
        }
        return readableDatabase;
    }

    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    private static LitePalOpenHelper buildConnection() {
        LitePalAttr instance = LitePalAttr.getInstance();
        instance.checkSelfValid();
        if (mLitePalHelper == null) {
            String dbName = instance.getDbName();
            if ("external".equalsIgnoreCase(instance.getStorage())) {
                dbName = LitePalApplication.getContext().getExternalFilesDir("") + "/databases/" + dbName;
            }
            mLitePalHelper = new LitePalOpenHelper(dbName, instance.getVersion());
        }
        return mLitePalHelper;
    }

    public static void clearLitePalOpenHelperInstance() {
        if (mLitePalHelper != null) {
            mLitePalHelper.getWritableDatabase().close();
            mLitePalHelper = null;
        }
    }
}
