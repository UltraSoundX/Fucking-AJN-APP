package com.tencent.smtt.sdk;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.webkit.WebIconDatabase.IconListener;

@Deprecated
public class WebIconDatabase {
    private static WebIconDatabase a;

    @Deprecated
    public interface a {
        void a(String str, Bitmap bitmap);
    }

    public void open(String str) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().open(str);
        } else {
            a2.c().b(str);
        }
    }

    public void close() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().close();
        } else {
            a2.c().m();
        }
    }

    public void removeAllIcons() {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().removeAllIcons();
        } else {
            a2.c().l();
        }
    }

    public void requestIconForPageUrl(String str, final a aVar) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().requestIconForPageUrl(str, new IconListener() {
                public void onReceivedIcon(String str, Bitmap bitmap) {
                    aVar.a(str, bitmap);
                }
            });
        } else {
            a2.c().a(str, (com.tencent.smtt.export.external.interfaces.IconListener) new com.tencent.smtt.export.external.interfaces.IconListener() {
                public void onReceivedIcon(String str, Bitmap bitmap) {
                    aVar.a(str, bitmap);
                }
            });
        }
    }

    public void bulkRequestIconForPageUrl(ContentResolver contentResolver, String str, a aVar) {
    }

    public void retainIconForPageUrl(String str) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().retainIconForPageUrl(str);
        } else {
            a2.c().c(str);
        }
    }

    public void releaseIconForPageUrl(String str) {
        t a2 = t.a();
        if (a2 == null || !a2.b()) {
            android.webkit.WebIconDatabase.getInstance().releaseIconForPageUrl(str);
        } else {
            a2.c().d(str);
        }
    }

    public static WebIconDatabase getInstance() {
        return a();
    }

    private static synchronized WebIconDatabase a() {
        WebIconDatabase webIconDatabase;
        synchronized (WebIconDatabase.class) {
            if (a == null) {
                a = new WebIconDatabase();
            }
            webIconDatabase = a;
        }
        return webIconDatabase;
    }

    private WebIconDatabase() {
    }
}
