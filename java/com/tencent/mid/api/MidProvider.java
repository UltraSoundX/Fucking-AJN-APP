package com.tencent.mid.api;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.stub.StubApp;
import com.tencent.mid.core.Constants.ERROR;
import com.tencent.mid.sotrage.UnifiedStorage;
import com.tencent.mid.util.Util;

public class MidProvider extends ContentProvider {
    public static final int CMD_GET_PRIVATE_MID = 1;
    public static final int CMD_GET_PRIVATE_MID_ENTITY = 2;
    public static final int CMD_GET_PRIVATE_NEW_VERSION_MID_ENTITY = 3;
    public static final int CMD_INSERT_NEW_VERSION_MID_ENTITY = 10;
    public static final int CMD_INSERT_NEW_VERSION_MID_OLD_ENTITY = 11;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        Context origApplicationContext = StubApp.getOrigApplicationContext(getContext().getApplicationContext());
        origApplicationContext.getPackageName();
        if (Util.isEmpty(lastPathSegment)) {
            return ERROR.CMD_FORMAT_ERROR;
        }
        try {
            switch (Integer.parseInt(lastPathSegment)) {
                case 1:
                    MidEntity readPrivateMidEntity = UnifiedStorage.getInstance(origApplicationContext).readPrivateMidEntity();
                    if (readPrivateMidEntity != null) {
                        return readPrivateMidEntity.getMid();
                    }
                    return null;
                case 2:
                    MidEntity readPrivateMidEntity2 = UnifiedStorage.getInstance(origApplicationContext).readPrivateMidEntity();
                    if (readPrivateMidEntity2 == null) {
                        return null;
                    }
                    readPrivateMidEntity2.setImei("");
                    readPrivateMidEntity2.setImsi("");
                    readPrivateMidEntity2.setMac("");
                    return readPrivateMidEntity2.toString();
                case 3:
                    MidEntity readPrivateNewVersionMidEntity = UnifiedStorage.getInstance(origApplicationContext).readPrivateNewVersionMidEntity();
                    if (readPrivateNewVersionMidEntity == null) {
                        return null;
                    }
                    readPrivateNewVersionMidEntity.setImei("");
                    readPrivateNewVersionMidEntity.setImsi("");
                    readPrivateNewVersionMidEntity.setMac("");
                    return readPrivateNewVersionMidEntity.toString();
                default:
                    return "";
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return ERROR.CMD_NO_CMD;
        }
        th.printStackTrace();
        return ERROR.CMD_NO_CMD;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        String lastPathSegment = uri.getLastPathSegment();
        Context origApplicationContext = StubApp.getOrigApplicationContext(getContext().getApplicationContext());
        if (origApplicationContext != null) {
            origApplicationContext.getPackageName();
            if (!Util.isEmpty(lastPathSegment)) {
                try {
                    switch (Integer.parseInt(lastPathSegment)) {
                        case 10:
                            try {
                                String asString = contentValues.getAsString("mid");
                                if (!Util.isMidValid(MidService.getLocalMidOnly(StubApp.getOrigApplicationContext(getContext().getApplicationContext())))) {
                                    UnifiedStorage.getInstance(origApplicationContext).writeMidEntityWithProvide(MidEntity.parse(asString), false);
                                    break;
                                }
                            } catch (Throwable th) {
                                break;
                            }
                            break;
                        case 11:
                            try {
                                String asString2 = contentValues.getAsString("mid");
                                if (!Util.isMidValid(MidService.getLocalMidOnly(StubApp.getOrigApplicationContext(getContext().getApplicationContext())))) {
                                    UnifiedStorage.getInstance(origApplicationContext).writeNewVersionMidEntityWithProvider(MidEntity.parse(asString2), false);
                                    break;
                                }
                            } catch (Throwable th2) {
                                break;
                            }
                            break;
                    }
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
