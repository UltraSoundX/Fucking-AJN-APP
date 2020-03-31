package com.tencent.smtt.export.external.proxy;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.FileChooserParams;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.QuotaUpdater;

public class ProxyWebChromeClient implements IX5WebChromeClient {
    protected IX5WebChromeClient mWebChromeClient;

    public IX5WebChromeClient getmWebChromeClient() {
        return this.mWebChromeClient;
    }

    public void setWebChromeClient(IX5WebChromeClient iX5WebChromeClient) {
        this.mWebChromeClient = iX5WebChromeClient;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onConsoleMessage(consoleMessage);
        }
        return false;
    }

    public boolean onCreateWindow(IX5WebViewBase iX5WebViewBase, boolean z, boolean z2, Message message) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onCreateWindow(iX5WebViewBase, z, z2, message);
        }
        return false;
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationPermissionsShowPrompt(str, geolocationPermissionsCallback);
        }
    }

    public void onHideCustomView() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onHideCustomView();
        }
    }

    public boolean onJsAlert(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsAlert(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public boolean onJsPrompt(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsPrompt(iX5WebViewBase, str, str2, str3, jsPromptResult);
        }
        return false;
    }

    public boolean onJsBeforeUnload(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsBeforeUnload(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public boolean onJsTimeout() {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsTimeout();
        }
        return false;
    }

    public void onProgressChanged(IX5WebViewBase iX5WebViewBase, int i) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onProgressChanged(iX5WebViewBase, i);
        }
    }

    public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }
    }

    public void onReceivedIcon(IX5WebViewBase iX5WebViewBase, Bitmap bitmap) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedIcon(iX5WebViewBase, bitmap);
        }
    }

    public void onReceivedTouchIconUrl(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedTouchIconUrl(iX5WebViewBase, str, z);
        }
    }

    public void onReceivedTitle(IX5WebViewBase iX5WebViewBase, String str) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedTitle(iX5WebViewBase, str);
        }
    }

    public void onRequestFocus(IX5WebViewBase iX5WebViewBase) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onRequestFocus(iX5WebViewBase);
        }
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        }
    }

    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    public void onConsoleMessage(String str, int i, String str2) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onConsoleMessage(str, i, str2);
        }
    }

    public void onGeolocationPermissionsHidePrompt() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    public boolean onJsConfirm(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsConfirm(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public void onCloseWindow(IX5WebViewBase iX5WebViewBase) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onCloseWindow(iX5WebViewBase);
        }
    }

    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.getVisitedHistory(valueCallback);
        }
    }

    public void openFileChooser(ValueCallback<Uri[]> valueCallback, String str, String str2, boolean z) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.openFileChooser(valueCallback, str, str2, z);
        }
    }

    public void onGeolocationStartUpdating(ValueCallback<Location> valueCallback, ValueCallback<Bundle> valueCallback2) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationStartUpdating(valueCallback, valueCallback2);
        }
    }

    public void onGeolocationStopUpdating() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationStopUpdating();
        }
    }

    public boolean onShowFileChooser(IX5WebViewBase iX5WebViewBase, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        return false;
    }
}
