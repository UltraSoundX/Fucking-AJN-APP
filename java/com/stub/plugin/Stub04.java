package com.stub.plugin;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Stub04 extends ContentProvider {
    private Map<String, BusiItem> delegates = new HashMap();

    public boolean onCreate() {
        try {
            BusiItem busiItem = new BusiItem();
            busiItem.setDelegateClz(Class.forName("com.stub.stub02.ImplProvider"));
            if (busiItem.getDelegateClz() != null) {
                try {
                    busiItem.setDelegateImpl(busiItem.getDelegateClz().newInstance());
                    this.delegates.put("pull", busiItem);
                    if (!(busiItem.getDelegateImpl() == null || busiItem.getDelegateClz() == null)) {
                        Method method = ReflectionUtil.getMethod(busiItem.getDelegateClz(), "onCreate", ContentProvider.class);
                        Object invoke = ReflectionUtil.invoke(busiItem.getDelegateImpl(), method, this);
                        if (invoke != null) {
                            return ((Boolean) invoke).booleanValue();
                        }
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public String getType(Uri uri) {
        try {
            for (BusiItem busiItem : this.delegates.values()) {
                Method method = ReflectionUtil.getMethod(busiItem.getDelegateClz(), "getType", Uri.class);
                ReflectionUtil.invoke(busiItem.getDelegateImpl(), method, uri);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            for (BusiItem busiItem : this.delegates.values()) {
                Method method = ReflectionUtil.getMethod(busiItem.getDelegateClz(), "insert", Uri.class, ContentValues.class);
                ReflectionUtil.invoke(busiItem.getDelegateImpl(), method, uri, contentValues);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
