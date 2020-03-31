package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduStatJSInterface {

    public static class CustomWebChromeViewClient extends WebChromeClient {
        private WeakReference<Context> a;
        private WebChromeClient b;
        private ArrayList<IWebviewPageLoadCallback> c;
        private aq d;
        private int e = 0;

        public CustomWebChromeViewClient(Context context, WebChromeClient webChromeClient, ArrayList<IWebviewPageLoadCallback> arrayList, aq aqVar) {
            this.a = new WeakReference<>(context);
            this.b = webChromeClient;
            this.c = arrayList;
            this.d = aqVar;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (this.c != null) {
                if (this.e == 0) {
                    Iterator it = this.c.iterator();
                    while (it.hasNext()) {
                        IWebviewPageLoadCallback iWebviewPageLoadCallback = (IWebviewPageLoadCallback) it.next();
                        if (iWebviewPageLoadCallback != null) {
                            iWebviewPageLoadCallback.onPageStarted(webView, webView.getUrl(), this.d);
                        }
                    }
                }
                this.e = i;
                if (i == 100) {
                    Iterator it2 = this.c.iterator();
                    while (it2.hasNext()) {
                        IWebviewPageLoadCallback iWebviewPageLoadCallback2 = (IWebviewPageLoadCallback) it2.next();
                        if (iWebviewPageLoadCallback2 != null) {
                            iWebviewPageLoadCallback2.onPageFinished(webView, webView.getUrl(), this.d);
                        }
                    }
                }
            }
            if (this.b != null) {
                this.b.onProgressChanged(webView, i);
            }
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            if (this.b != null) {
                return this.b.onCreateWindow(webView, z, z2, message);
            }
            return super.onCreateWindow(webView, z, z2, message);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.b != null) {
                return this.b.onJsAlert(webView, str, str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (this.b != null) {
                return this.b.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        @Deprecated
        public void onConsoleMessage(String str, int i, String str2) {
            if (this.b != null) {
                this.b.onConsoleMessage(str, i, str2);
            }
        }

        public void onCloseWindow(WebView webView) {
            if (this.b != null) {
                this.b.onCloseWindow(webView);
            }
        }

        @Deprecated
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
            if (this.b != null) {
                this.b.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            }
        }

        public void onGeolocationPermissionsHidePrompt() {
            if (this.b != null) {
                this.b.onGeolocationPermissionsHidePrompt();
            }
        }

        public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
            if (this.b != null) {
                this.b.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        public void onHideCustomView() {
            if (this.b != null) {
                this.b.onHideCustomView();
            }
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.b != null) {
                return this.b.onJsBeforeUnload(webView, str, str2, jsResult);
            }
            return super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.b != null) {
                return this.b.onJsConfirm(webView, str, str2, jsResult);
            }
            return super.onJsConfirm(webView, str, str2, jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if (this.b != null) {
                return this.b.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        @Deprecated
        public boolean onJsTimeout() {
            if (this.b != null) {
                return this.b.onJsTimeout();
            }
            return super.onJsTimeout();
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (this.b != null) {
                this.b.onPermissionRequest(permissionRequest);
            }
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
            if (this.b != null) {
                this.b.onPermissionRequestCanceled(permissionRequest);
            }
        }

        @Deprecated
        public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
            if (this.b != null) {
                this.b.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            if (this.b != null) {
                this.b.onReceivedIcon(webView, bitmap);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (this.b != null) {
                this.b.onReceivedTitle(webView, str);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            if (this.b != null) {
                this.b.onReceivedTouchIconUrl(webView, str, z);
            }
        }

        public void onRequestFocus(WebView webView) {
            if (this.b != null) {
                this.b.onRequestFocus(webView);
            }
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            if (this.b != null) {
                this.b.onShowCustomView(view, customViewCallback);
            }
        }

        @SuppressLint({"NewApi"})
        @Deprecated
        public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
            if (this.b != null) {
                this.b.onShowCustomView(view, i, customViewCallback);
            }
        }

        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            if (this.b != null) {
                return this.b.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    }

    public static class CustomWebViewClient extends WebViewClient {
        private WeakReference<Context> a;
        private WebViewClient b;
        private IWebviewPageLoadCallback c;
        private aq d;

        public CustomWebViewClient(Context context, WebViewClient webViewClient, IWebviewPageLoadCallback iWebviewPageLoadCallback, aq aqVar) {
            this.a = new WeakReference<>(context);
            this.b = webViewClient;
            this.c = iWebviewPageLoadCallback;
            this.d = aqVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String str2;
            try {
                str2 = URLDecoder.decode(str, "UTF-8");
                try {
                    if (!TextUtils.isEmpty(str2) && str2.startsWith("bmtj:")) {
                        a(str2.substring(5));
                        return true;
                    }
                } catch (UnsupportedEncodingException | JSONException e) {
                }
            } catch (UnsupportedEncodingException e2) {
                str2 = str;
            } catch (JSONException e3) {
                str2 = str;
            }
            if (this.b != null) {
                return this.b.shouldOverrideUrlLoading(webView, str2);
            }
            return super.shouldOverrideUrlLoading(webView, str2);
        }

        @SuppressLint({"NewApi"})
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            if (this.b != null) {
                return this.b.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        private void a(String str) throws JSONException {
            JSONObject jSONObject;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            JSONObject jSONObject4 = new JSONObject(str);
            String string = jSONObject4.getString("action");
            JSONObject jSONObject5 = jSONObject4.getJSONObject("obj");
            Context context = (Context) this.a.get();
            if (context != null) {
                if ("onPageStart".equals(string)) {
                    String string2 = jSONObject5.getString("page");
                    if (!TextUtils.isEmpty(string2)) {
                        BDStatCore.instance().onPageStart(context, string2);
                    }
                } else if ("onPageEnd".equals(string)) {
                    String string3 = jSONObject5.getString("page");
                    if (!TextUtils.isEmpty(string3)) {
                        BDStatCore.instance().onPageEnd(context, string3, null, true);
                    }
                } else if ("onEvent".equals(string)) {
                    String string4 = jSONObject5.getString("event_id");
                    String string5 = jSONObject5.getString("label");
                    int i = jSONObject5.getInt("acc");
                    if (!TextUtils.isEmpty(string4)) {
                        try {
                            jSONObject3 = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception e) {
                            jSONObject3 = null;
                        }
                        BDStatCore.instance().onEvent(context, string4, string5, i, (ExtraInfo) null, (Map<String, String>) a(jSONObject3), false, true);
                    }
                } else if ("onEventStart".equals(string)) {
                    String string6 = jSONObject5.getString("event_id");
                    String string7 = jSONObject5.getString("label");
                    if (!TextUtils.isEmpty(string6)) {
                        BDStatCore.instance().onEventStart(context, string6, string7, false);
                    }
                } else if ("onEventEnd".equals(string)) {
                    String string8 = jSONObject5.getString("event_id");
                    String string9 = jSONObject5.getString("label");
                    if (!TextUtils.isEmpty(string8)) {
                        try {
                            jSONObject2 = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception e2) {
                            jSONObject2 = null;
                        }
                        BDStatCore.instance().onEventEnd(context, string8, string9, null, a(jSONObject2), true);
                    }
                } else if ("onEventDuration".equals(string)) {
                    String string10 = jSONObject5.getString("event_id");
                    String string11 = jSONObject5.getString("label");
                    long j = jSONObject5.getLong("duration");
                    if (!TextUtils.isEmpty(string10)) {
                        try {
                            jSONObject = (JSONObject) jSONObject5.get("attributes");
                        } catch (Exception e3) {
                            jSONObject = null;
                        }
                        BDStatCore.instance().onEventDuration(context, string10, string11, j, null, a(jSONObject), false, true);
                    }
                }
            }
        }

        private HashMap<String, String> a(JSONObject jSONObject) {
            HashMap hashMap;
            if (jSONObject == null) {
                return null;
            }
            if (jSONObject.length() != 0) {
                hashMap = new HashMap();
            } else {
                hashMap = null;
            }
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                try {
                    String str = (String) keys.next();
                    String string = jSONObject.getString(str);
                    if (hashMap != null) {
                        hashMap.put(str, string);
                    }
                } catch (Exception e) {
                }
            }
            return hashMap;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (this.b != null) {
                this.b.onPageStarted(webView, str, bitmap);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (this.b != null) {
                this.b.onPageFinished(webView, str);
            }
        }

        public void onLoadResource(WebView webView, String str) {
            if (this.b != null) {
                this.b.onLoadResource(webView, str);
            }
        }

        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            if (this.b != null) {
                this.b.onTooManyRedirects(webView, message, message2);
            }
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            if (this.b != null) {
                this.b.onFormResubmission(webView, message, message2);
            }
        }

        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (this.b != null) {
                this.b.doUpdateVisitedHistory(webView, str, z);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (this.b != null) {
                this.b.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            if (this.b != null) {
                this.b.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.b != null) {
                return this.b.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.b != null) {
                this.b.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            if (this.b != null) {
                this.b.onScaleChanged(webView, f, f2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (this.b != null) {
                this.b.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            if (this.b != null) {
                this.b.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (this.b != null) {
                return this.b.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (this.b != null) {
                return this.b.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @SuppressLint({"NewApi"})
        public void onPageCommitVisible(WebView webView, String str) {
            if (this.b != null) {
                this.b.onPageCommitVisible(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.b != null) {
                this.b.onReceivedError(webView, i, str, str2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (this.b != null) {
                this.b.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (this.b != null) {
                this.b.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @SuppressLint({"NewApi"})
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            if (this.b != null) {
                return this.b.onRenderProcessGone(webView, renderProcessGoneDetail);
            }
            return super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }
    }

    public interface IWebviewPageLoadCallback {
        void onPageFinished(WebView webView, String str, aq aqVar);

        void onPageStarted(WebView webView, String str, aq aqVar);
    }
}
