package com.tencent.android.tpush;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import com.stub.StubApp;
import com.tencent.android.tpush.b.a;
import com.tencent.android.tpush.c.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.data.RegisterEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.stat.b.d;
import com.tencent.android.tpush.stat.b.i;
import java.net.URISyntaxException;

/* compiled from: ProGuard */
public class XGPushProvider extends ContentProvider {
    public static final String AUTH_PRIX = ".AUTH_XGPUSH";
    public static final String TAG = XGPushProvider.class.getSimpleName();
    private Context a = null;
    private String b = null;
    private UriMatcher c = new UriMatcher(-1);

    private void a() {
        this.a = StubApp.getOrigApplicationContext(getContext().getApplicationContext());
        l.a(this.a);
        this.b = this.a.getPackageName();
        String str = this.b + AUTH_PRIX;
        this.c.addURI(str, TypeStr.config.getStr(), TypeStr.config.getType());
        this.c.addURI(str, TypeStr.config_all.getStr(), TypeStr.config_all.getType());
        this.c.addURI(str, TypeStr.msg.getStr(), TypeStr.msg.getType());
        this.c.addURI(str, TypeStr.msg_all.getStr(), TypeStr.msg_all.getType());
        this.c.addURI(str, TypeStr.hearbeat.getStr(), TypeStr.hearbeat.getType());
        this.c.addURI(str, TypeStr.hearbeat_all.getStr(), TypeStr.hearbeat_all.getType());
        this.c.addURI(str, TypeStr.feedback.getStr(), TypeStr.feedback.getType());
        this.c.addURI(str, TypeStr.feedback_all.getStr(), TypeStr.feedback_all.getType());
        this.c.addURI(str, TypeStr.token.getStr(), TypeStr.token.getType());
        this.c.addURI(str, TypeStr.register.getStr(), TypeStr.register.getType());
        this.c.addURI(str, TypeStr.insert_mid_new.getStr(), TypeStr.insert_mid_new.getType());
        this.c.addURI(str, TypeStr.insert_mid_old.getStr(), TypeStr.insert_mid_old.getType());
        this.c.addURI(str, TypeStr.pullupxg.getStr(), TypeStr.pullupxg.getType());
    }

    public boolean onCreate() {
        a();
        a.e(Constants.LogTag, "XGPushProvider onCreate");
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        a.e(Constants.LogTag, "query uri:" + uri + ",projection:" + strArr + ",selection:" + str + ",selectionArgs:" + strArr2 + ",sortOrder:" + str2);
        return null;
    }

    public String getType(Uri uri) {
        int match = this.c.match(uri);
        a.e(Constants.LogTag, "getType uri:" + uri + ",match:" + match);
        TypeStr typeStr = TypeStr.getTypeStr(match);
        if (typeStr == null) {
            return null;
        }
        switch (typeStr) {
            case config:
                return "CONFIG";
            case config_all:
                return "CONFIG_ALL";
            case hearbeat:
                f.a(this.a).a(false);
                return null;
            case pullupxg:
                a.e(Constants.LogTag, "Start XGService by provider");
                b.a(this.a);
                return null;
            case hearbeat_all:
                return "HEARTBEAT_ALL";
            case token:
                return Rijndael.encrypt(i.a(this.a).c());
            case register:
                RegisterEntity currentAppRegisterEntity = CacheManager.getCurrentAppRegisterEntity(this.a);
                a.e(Constants.LogTag, "get RegisterEntity:" + currentAppRegisterEntity);
                return Rijndael.encrypt(RegisterEntity.encode(currentAppRegisterEntity));
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = this.c.match(uri);
        TypeStr typeStr = TypeStr.getTypeStr(match);
        if (typeStr != null) {
            a.e(Constants.LogTag, "insert uri:" + uri + ",match:" + match + ",values:" + contentValues);
            switch (typeStr) {
                case msg:
                    try {
                        Intent parseUri = Intent.parseUri(contentValues.getAsString(SettingsContentProvider.KEY), 0);
                        parseUri.addCategory("android.intent.category.BROWSABLE");
                        parseUri.setComponent(null);
                        if (VERSION.SDK_INT >= 15) {
                            try {
                                parseUri.getClass().getMethod("setSelector", new Class[]{Intent.class}).invoke(parseUri, new Object[]{null});
                            } catch (Exception e) {
                                a.c(Constants.LogTag, "invoke intent.setComponent error.", e);
                            }
                        }
                        f.a(this.a).b(parseUri);
                        break;
                    } catch (URISyntaxException e2) {
                        e2.printStackTrace();
                        break;
                    }
                case insert_mid_new:
                    try {
                        i.a(this.a).a(d.a(contentValues.getAsString("mid")), false);
                        break;
                    } catch (Throwable th) {
                        break;
                    }
                case insert_mid_old:
                    try {
                        i.a(this.a).b(d.a(contentValues.getAsString("mid")), false);
                        break;
                    } catch (Throwable th2) {
                        break;
                    }
            }
        }
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        a.g(TAG, "delete uri:" + uri + ",selection:" + str + ",selectionArgs:" + strArr);
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = this.c.match(uri);
        TypeStr typeStr = TypeStr.getTypeStr(match);
        if (typeStr != null) {
            a.e(Constants.LogTag, "update uri:" + uri + ",values:" + contentValues + ",selection:" + str + ",selectionArgs:" + strArr + ",match:" + match);
            switch (typeStr) {
                case feedback:
                    a.e(Constants.LogTag, "feeback: " + Rijndael.decrypt(contentValues.getAsString("feedback")));
                    break;
            }
        }
        return 0;
    }
}
