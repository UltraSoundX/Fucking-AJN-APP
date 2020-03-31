package com.tencent.smtt.export.external.extension.proxy;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;

public abstract class ProxyWebViewClientExtension implements IX5WebViewClientExtension {
    private static boolean sCompatibleOnMetricsSavedCountReceived = true;
    private static boolean sCompatibleOnPageLoadingStartedAndFinished = true;
    protected IX5WebViewClientExtension mWebViewClientExt;

    public void onPreReadFinished() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onPreReadFinished();
        }
    }

    public void onPromptScaleSaved() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onPromptScaleSaved();
        }
    }

    public void onUrlChange(String str, String str2) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onUrlChange(str, str2);
        }
    }

    public void onHideListBox() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onHideListBox();
        }
    }

    public void onShowListBox(String[] strArr, int[] iArr, int[] iArr2, int i) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onShowListBox(strArr, iArr, iArr2, i);
        }
    }

    public void onShowMutilListBox(String[] strArr, int[] iArr, int[] iArr2, int[] iArr3) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onShowMutilListBox(strArr, iArr, iArr2, iArr3);
        }
    }

    public void onInputBoxTextChanged(IX5WebViewExtension iX5WebViewExtension, String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onInputBoxTextChanged(iX5WebViewExtension, str);
        }
    }

    public void onTransitionToCommitted() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onTransitionToCommitted();
        }
    }

    public void showTranslateBubble(int i, String str, String str2, int i2) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.showTranslateBubble(i, str, str2, i2);
        }
    }

    public void onUploadProgressStart(int i) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onUploadProgressStart(i);
        }
    }

    public void onUploadProgressChange(int i, int i2, String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onUploadProgressChange(i, i2, str);
        }
    }

    public void onFlingScrollBegin(int i, int i2, int i3) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onFlingScrollBegin(i, i2, i3);
        }
    }

    public void onFlingScrollEnd() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onFlingScrollEnd();
        }
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onScrollChanged(i, i2, i3, i4);
        }
    }

    public void onSoftKeyBoardShow() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onSoftKeyBoardShow();
        }
    }

    public void onSoftKeyBoardHide(int i) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onSoftKeyBoardHide(i);
        }
    }

    public void onSetButtonStatus(boolean z, boolean z2) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onSetButtonStatus(z, z2);
        }
    }

    public void onHistoryItemChanged() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onHistoryItemChanged();
        }
    }

    public void hideAddressBar() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.hideAddressBar();
        }
    }

    public void handlePluginTag(String str, String str2, boolean z, String str3) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.handlePluginTag(str, str2, z, str3);
        }
    }

    public void onDoubleTapStart() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onDoubleTapStart();
        }
    }

    public void onPinchToZoomStart() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onPinchToZoomStart();
        }
    }

    public void onSlidingTitleOffScreen(int i, int i2) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onSlidingTitleOffScreen(i, i2);
        }
    }

    public boolean preShouldOverrideUrlLoading(IX5WebViewExtension iX5WebViewExtension, String str) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.preShouldOverrideUrlLoading(iX5WebViewExtension, str);
        }
        return false;
    }

    public void onMissingPluginClicked(String str, String str2, String str3, int i) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onMissingPluginClicked(str, str2, str3, i);
        }
    }

    public void onReportAdFilterInfo(int i, int i2, String str, boolean z) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onReportAdFilterInfo(i, i2, str, z);
        }
    }

    public void onReportHtmlInfo(int i, String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onReportHtmlInfo(i, str);
        }
    }

    public void onNativeCrashReport(int i, String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onNativeCrashReport(i, str);
        }
    }

    public Object onMiscCallBack(String str, Bundle bundle) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.onMiscCallBack(str, bundle);
        }
        return null;
    }

    public Object onMiscCallBack(String str, Bundle bundle, Object obj, Object obj2, Object obj3, Object obj4) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.onMiscCallBack(str, bundle, obj, obj2, obj3, obj4);
        }
        return null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent, View view) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.onInterceptTouchEvent(motionEvent, view);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent, View view) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.onTouchEvent(motionEvent, view);
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent, View view) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.dispatchTouchEvent(motionEvent, view);
        }
        return false;
    }

    public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, View view) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z, view);
        }
        return false;
    }

    public void onScrollChanged(int i, int i2, int i3, int i4, View view) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onScrollChanged(i, i2, i3, i4, view);
        }
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2, View view) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onOverScrolled(i, i2, z, z2, view);
        }
    }

    public void computeScroll(View view) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.computeScroll(view);
        }
    }

    public void onMetricsSavedCountReceived(String str, boolean z, long j, String str2, int i) {
        if (this.mWebViewClientExt != null && sCompatibleOnMetricsSavedCountReceived) {
            try {
                this.mWebViewClientExt.onMetricsSavedCountReceived(str, z, j, str2, i);
            } catch (NoSuchMethodError e) {
                if (e.getMessage() == null || !e.getMessage().contains("onMetricsSavedCountReceived")) {
                    throw e;
                }
                Log.d("incompatible-oldcore", "IX5WebViewClientExtension.onMetricsSavedCountReceived");
                sCompatibleOnMetricsSavedCountReceived = false;
            }
        }
    }

    public boolean notifyAutoAudioPlay(String str, JsResult jsResult) {
        if (this.mWebViewClientExt != null) {
            try {
                return this.mWebViewClientExt.notifyAutoAudioPlay(str, jsResult);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean allowJavaScriptOpenWindowAutomatically(String str, String str2) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.allowJavaScriptOpenWindowAutomatically(str, str2);
        }
        return false;
    }

    public boolean notifyJavaScriptOpenWindowsBlocked(String str, String[] strArr, ValueCallback<Boolean> valueCallback, boolean z) {
        if (this.mWebViewClientExt != null) {
            return this.mWebViewClientExt.notifyJavaScriptOpenWindowsBlocked(str, strArr, valueCallback, z);
        }
        return false;
    }

    public boolean onShowLongClickPopupMenu() {
        if (this.mWebViewClientExt != null) {
            try {
                return this.mWebViewClientExt.onShowLongClickPopupMenu();
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void onResponseReceived(WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse, int i) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onResponseReceived(webResourceRequest, webResourceResponse, i);
        }
    }

    public void didFirstVisuallyNonEmptyPaint() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.didFirstVisuallyNonEmptyPaint();
        }
    }

    public void documentAvailableInMainFrame() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.documentAvailableInMainFrame();
        }
    }

    public void onReceivedViewSource(String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onReceivedViewSource(str);
        }
    }

    public void onPrefetchResourceHit(boolean z) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onPrefetchResourceHit(z);
        }
    }

    public void onReceivedSslErrorCancel() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onReceivedSslErrorCancel();
        }
    }

    public void invalidate() {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.invalidate();
        }
    }

    public void onPreloadCallback(int i, String str) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onPreloadCallback(i, str);
        }
    }

    public void onFakeLoginRecognised(Bundle bundle) {
        if (this.mWebViewClientExt != null) {
            this.mWebViewClientExt.onFakeLoginRecognised(bundle);
        }
    }
}
