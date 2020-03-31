package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IX5WebHistoryItem;

public class WebHistoryItem {
    private IX5WebHistoryItem a = null;
    private android.webkit.WebHistoryItem b = null;

    private WebHistoryItem() {
    }

    static WebHistoryItem a(IX5WebHistoryItem iX5WebHistoryItem) {
        if (iX5WebHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem = new WebHistoryItem();
        webHistoryItem.a = iX5WebHistoryItem;
        return webHistoryItem;
    }

    static WebHistoryItem a(android.webkit.WebHistoryItem webHistoryItem) {
        if (webHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem2 = new WebHistoryItem();
        webHistoryItem2.b = webHistoryItem;
        return webHistoryItem2;
    }

    public String getUrl() {
        if (this.a != null) {
            return this.a.getUrl();
        }
        return this.b.getUrl();
    }

    public String getOriginalUrl() {
        if (this.a != null) {
            return this.a.getOriginalUrl();
        }
        return this.b.getOriginalUrl();
    }

    public String getTitle() {
        if (this.a != null) {
            return this.a.getTitle();
        }
        return this.b.getTitle();
    }

    public Bitmap getFavicon() {
        if (this.a != null) {
            return this.a.getFavicon();
        }
        return this.b.getFavicon();
    }
}
