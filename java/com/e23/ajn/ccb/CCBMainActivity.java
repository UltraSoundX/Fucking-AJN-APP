package com.e23.ajn.ccb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.ccb.crypto.tp.tool.d;
import com.e23.ajn.ccb.d.b;
import com.e23.ajn.ccb.d.c;
import com.e23.ajn.ccb.d.e;
import com.e23.ajn.ccb.d.f;
import com.e23.ajn.ccb.d.h;
import com.e23.ajn.ccb.d.i;
import com.e23.ajn.ccb.entity.BaseReq;
import com.e23.ajn.ccb.entity.FileUploadEntity;
import com.e23.ajn.ccb.entity.IDCardEntity;
import com.e23.ajn.ccb.entity.SecurityReq;
import com.e23.ajn.ccb.entity.SecurityReqBody;
import com.stub.StubApp;
import com.tendyron.liveness.impl.LivenessInterface;
import java.io.File;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONException;
import org.json.JSONObject;

public class CCBMainActivity extends Activity implements OnClickListener {
    public static final int REQUEST_CODE_IDCARD_SIDE_BACK = 2;
    public static final int REQUEST_CODE_IDCARD_SIDE_FRONT = 1;
    public static final int REQUEST_CODE_SCAN_FACE = 8;
    public static final int WEBVIEW_ACTIVITY_CODE = 4;
    Context a;
    com.ccb.a.a b;
    LivenessInterface c;
    String d = "";
    SecurityReq e;
    SecurityReqBody f;
    String g = "";
    byte[] h;
    byte[] i;
    String j = "";
    JSONObject k = new JSONObject();
    FileUploadEntity l;
    String m = "";
    CookieStore n = new BasicCookieStore();
    d o;
    String p;

    /* renamed from: q reason: collision with root package name */
    File f399q;
    Uri r;
    com.e23.ajn.ccb.a.a s = new com.e23.ajn.ccb.a.a() {
        public void a(int i) {
            switch (i) {
                case 1:
                    CCBMainActivity.this.a(1);
                    return;
                case 2:
                    CCBMainActivity.this.a(2);
                    return;
                case 7:
                    CCBMainActivity.this.startfaceScan();
                    return;
                default:
                    return;
            }
        }

        public void b(int i) {
        }

        public void a(int i, String[] strArr, int[] iArr) {
        }
    };
    /* access modifiers changed from: private */
    public String t = "CCBMainActivity";
    /* access modifiers changed from: private */
    public WebView u = null;
    private boolean v = false;
    /* access modifiers changed from: private */
    public IDCardEntity w;

    public class a extends com.ccb.a.a {
        a(Context context, LinearLayout linearLayout, WebView webView) {
            super(context, com.e23.ajn.ccb.b.a.g, linearLayout, webView);
        }

        @JavascriptInterface
        public void showToast(String str) {
            Toast.makeText(CCBMainActivity.this.a, str, 1).show();
        }

        @JavascriptInterface
        public void createESafe(String str) {
            CCBMainActivity.this.initESafe();
        }

        @JavascriptInterface
        public void scanFace(String... strArr) {
            CCBMainActivity.this.initPermissiont("android.permission.CAMERA", 7);
        }

        @JavascriptInterface
        public void requestFaceInfo(String str) {
            CCBMainActivity.this.a(str);
            CCBMainActivity.this.b();
        }

        @JavascriptInterface
        public void requestFaceInfoZX02(String str) {
            CCBMainActivity.this.a(str, "02");
            CCBMainActivity.this.b();
        }

        @JavascriptInterface
        public void sendParams(final String str) {
            CCBMainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    CCBMainActivity.this.u.loadUrl("javascript:scanFaceResult('" + str + " success')");
                }
            });
        }

        @JavascriptInterface
        public void scanIdCardFront() {
            CCBMainActivity.this.initPermissiont("android.permission.CAMERA", 1);
        }

        @JavascriptInterface
        public void scanIdCardBack() {
            CCBMainActivity.this.initPermissiont("android.permission.CAMERA", 2);
        }

        @JavascriptInterface
        public void scanBankCard() {
        }

        @JavascriptInterface
        public void createPicture(String str) {
            CCBMainActivity.this.saveIdCardPic2Cache(str);
        }

        @JavascriptInterface
        public void openWebView(String str) {
            Intent intent = new Intent(CCBMainActivity.this.a, CcbWebViewActivity.class);
            intent.putExtra("url", str);
            CCBMainActivity.this.startActivityForResult(intent, 4);
        }

        @JavascriptInterface
        public void copyToClipboard(String str) {
            ((ClipboardManager) CCBMainActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", str));
        }

        @JavascriptInterface
        public void forBidScreenCap(String str) {
            ((Activity) CCBMainActivity.this.a).runOnUiThread(new Runnable() {
                public void run() {
                    ((Activity) CCBMainActivity.this.a).getWindow().setFlags(8192, 8192);
                }
            });
        }

        @JavascriptInterface
        public void closeMainWebview(String str) {
            ((Activity) CCBMainActivity.this.a).runOnUiThread(new Runnable() {
                public void run() {
                    Log.i("closeMainWebview", "closeMainWebview with data");
                    CCBMainActivity.this.finish();
                }
            });
        }

        @JavascriptInterface
        public void closeMainWebview() {
            Log.i("closeMainWebview", "closeMainWebview with no data");
            CCBMainActivity.this.finish();
        }
    }

    static {
        StubApp.interface11(3631);
    }

    public native void onCreate(Bundle bundle);

    private void a(String[] strArr, int i2) {
        ActivityCompat.requestPermissions(this, strArr, i2);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    private void a() {
        WebSettings settings = this.u.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(2);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setDefaultFontSize(18);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        this.u.setHorizontalScrollBarEnabled(false);
        this.u.setVerticalScrollBarEnabled(true);
        this.u.setWebViewClient(new WebViewClient() {
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                Log.i(CCBMainActivity.this.t, "polling:shouldInterceptRequest request url:" + webResourceRequest.getUrl().toString());
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }

            public void onPageFinished(WebView webView, String str) {
                Log.i(CCBMainActivity.this.t, "polling:onPageFinished request url:" + str);
                super.onPageFinished(webView, str);
                String cookie = CookieManager.getInstance().getCookie(str);
                if (cookie != null) {
                    Log.i(CCBMainActivity.this.t + "polling ", cookie);
                    for (String split : cookie.split(";")) {
                        String[] split2 = split.split("=");
                        if (split2 != null && split2.length > 1) {
                            CCBMainActivity.this.n.addCookie(new BasicClientCookie(split2[0], split2[1]));
                        }
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.i(CCBMainActivity.this.t, "polling:shouldOverrideUrlLoading request url:" + str);
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                Log.i(CCBMainActivity.this.t, "polling:shouldOverrideUrlLoading request url:" + webResourceRequest.getUrl().toString());
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                Log.i(CCBMainActivity.this.t, "polling:onReceivedSslError");
                sslErrorHandler.proceed();
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Log.i(CCBMainActivity.this.t, "polling:onReceivedError");
                super.onReceivedError(webView, i, str, str2);
            }
        });
        this.u.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        this.u.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.u, true);
        }
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.u.addJavascriptInterface(this.b, com.ccb.a.a.CCB_JS_OBJECT);
        if (this.v) {
            this.u.loadUrl("file:///android_asset/www/index.html");
        } else {
            this.u.loadUrl(getIntent().getStringExtra("posturl"));
        }
        this.u.removeJavascriptInterface("searchBoxJavaBridge_");
        this.u.removeJavascriptInterface("accessibility");
        this.u.removeJavascriptInterface("accessibilityTraversal");
    }

    public void initPermissiont(String str, int i2) {
        if (ContextCompat.checkSelfPermission(this, str) != 0) {
            if (this.s != null) {
                this.s.b(i2);
            }
            ActivityCompat.requestPermissions(this, new String[]{str}, i2);
        } else if (this.s != null) {
            this.s.a(i2);
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !this.u.canGoBack()) {
            return super.onKeyDown(i2, keyEvent);
        }
        this.u.goBack();
        return true;
    }

    public void onClick(View view) {
        if (2131820742 == view.getId()) {
            finish();
        } else if (2131820766 == view.getId()) {
            initPermissiont("android.permission.CAMERA", 1);
        }
    }

    public void initESafe() {
        this.o = e.a(this.a);
        this.e = new SecurityReq();
        this.e.SYS_CODE = this.o.c();
        this.e.APP_NAME = this.o.e();
        this.e.MP_CODE = this.o.d();
        this.e.SEC_VERSION = this.o.a();
        this.e.APP_IMEI = TextUtils.isEmpty(this.o.g()) ? "" : this.o.g();
        this.e.GPS_INFO = TextUtils.isEmpty(this.o.i()) ? "" : this.o.i();
        final String a2 = h.a(this.e);
        runOnUiThread(new Runnable() {
            public void run() {
                CCBMainActivity.this.u.loadUrl("javascript:createESafeResult('" + a2 + "')");
            }
        });
    }

    @SuppressLint({"Override"})
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (iArr.length <= 0 || iArr[0] != 0) {
            this.s.b(i2);
        } else {
            this.s.a(i2);
        }
        this.s.a(i2, strArr, iArr);
    }

    public void startfaceScan() {
        try {
            this.c.startLivenessActivityForResult(this, 8, 2, true, new int[]{0});
        } catch (Exception e2) {
            Log.i("MainActivity", "Exception:" + e2.toString());
        }
    }

    private void a(final int i2, List<byte[]> list) {
        if (i2 == -1) {
            this.d = Base64.encodeToString((byte[]) list.get(0), 0).replaceAll("\r|\n", "");
            String str = "{\"picture\":\"\"}";
            runOnUiThread(new Runnable() {
                public void run() {
                    CCBMainActivity.this.u.loadUrl("javascript:scanFaceResult('{\"picture\":\"\"}')");
                }
            });
            return;
        }
        new Runnable() {
            public void run() {
                CCBMainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CCBMainActivity.this.u.loadUrl("javascript:errorHandle(" + CCBMainActivity.this.c.getLivenessErrorMessage(i2) + ",'用户取消操作。')");
                    }
                });
            }
        }.run();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        a(str, (String) null);
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        Log.i(this.t, "Security json from page:");
        b(str);
        if (this.e == null) {
            initESafe();
        }
        this.e.BRANCHID = h.a(str, "BRANCHID", "");
        int a2 = h.a(str, "FaceType", Integer.valueOf(2));
        if (str2 != null) {
            this.e.TXCODE = com.e23.ajn.ccb.b.a.d;
        } else {
            this.e.TXCODE = com.e23.ajn.ccb.b.a.e;
        }
        if (a2 != 1 || this.w == null) {
            this.f.base64_Ecrp_Txn_Inf = this.d;
            return;
        }
        this.j = h.a(str, "CardFace", "");
        Log.i(this.t, "get the cardFace image from server.");
        this.f.base64_Ecrp_Txn_Inf = this.j;
    }

    private void b(String str) {
        this.f = (SecurityReqBody) h.a(str, SecurityReqBody.class);
        this.f.SYSTEM_TIME = b.a(b.b);
        this.f.HARDWARESN = c.a(this.a);
    }

    /* access modifiers changed from: private */
    public void b() {
        com.e23.ajn.ccb.c.a.a().a(this.a, (BaseReq) this.e, this.f, (com.c.a.c.a.d) new com.c.a.c.a.d<String>() {
            public void a(com.c.a.b.b bVar, String str) {
                Log.i("Polling", "send security failure responString" + bVar.toString() + str);
                i.a().b();
                new Runnable() {
                    public void run() {
                        CCBMainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CCBMainActivity.this.u.loadUrl("javascript:errorHandle(" + null + ",'网络异常，请稍后尝试。')");
                            }
                        });
                    }
                }.run();
            }

            public void a(com.c.a.c.d<String> dVar) {
                Log.i("Polling", "xutils post security success." + dVar.toString());
                i.a().b();
                if (dVar == null || TextUtils.isEmpty((CharSequence) dVar.a)) {
                    new Runnable() {
                        public void run() {
                            CCBMainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    CCBMainActivity.this.u.loadUrl("javascript:errorHandle(" + null + ",'网络异常，请稍后尝试。')");
                                }
                            });
                        }
                    }.run();
                    return;
                }
                CCBMainActivity.this.g = ((String) dVar.a).toString();
                Log.i("解密前Polling", "responString.result ：" + CCBMainActivity.this.g);
                try {
                    JSONObject jSONObject = new JSONObject((String) dVar.a);
                    if ("000000".equals(jSONObject.getString("Res_Rtn_Code"))) {
                        CCBMainActivity.this.g = CCBMainActivity.this.o.d(jSONObject.getString("Ret_Enc_Inf"));
                        CCBMainActivity.this.g = TextUtils.isEmpty(CCBMainActivity.this.g) ? "" : CCBMainActivity.this.g;
                        Log.i("解密后Polling", "responString.result ：" + CCBMainActivity.this.g);
                        CCBMainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CCBMainActivity.this.u.loadUrl("javascript:requestFaceInfoResult('" + CCBMainActivity.this.g + "')");
                            }
                        });
                        return;
                    }
                    CCBMainActivity.this.g = jSONObject.getString("Res_Rtn_Msg");
                    final String string = jSONObject.getString("Res_Rtn_Code");
                    new Runnable() {
                        public void run() {
                            CCBMainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    CCBMainActivity.this.u.loadUrl("javascript:errorHandle('" + string + "','" + CCBMainActivity.this.g + "')");
                                }
                            });
                        }
                    }.run();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void uploadFiles(final int i2) {
        String str = "";
        if (i2 == 1) {
            try {
                str = this.k.getString("FrontPicPath");
                this.l.File_Nm = this.m + "_ZM.jpg";
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
        } else if (i2 == 2) {
            str = this.k.getString("BackPicPath");
            this.l.File_Nm = this.m + "_FM.jpg";
        }
        if (!TextUtils.isEmpty(this.k.getString("FrontPicPath"))) {
            File file = new File(str);
            i.a().a((Context) this);
            com.e23.ajn.ccb.c.a.a().a(this.n, file, (BaseReq) this.l, (com.c.a.c.a.d) new com.c.a.c.a.d<String>() {
                public void a(com.c.a.c.d dVar) {
                    boolean z = false;
                    Log.i("Polling", "upload file success responString" + dVar.a.toString());
                    i.a().b();
                    if (dVar.a != null) {
                        z = h.a(dVar.a.toString(), "SUCCESS", Boolean.valueOf(false));
                    }
                    if (z && 1 == i2) {
                        CCBMainActivity.this.uploadFiles(2);
                        try {
                            f.a(CCBMainActivity.this.k.getString("FrontPicPath"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (!z || 2 != i2) {
                        new Runnable() {
                            public void run() {
                                CCBMainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CCBMainActivity.this.u.loadUrl("javascript:errorHandle(" + null + ",'上传图片失败，请稍后尝试。')");
                                    }
                                });
                            }
                        }.run();
                    } else {
                        try {
                            f.a(CCBMainActivity.this.k.getString("BackPicPath"));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        new Runnable() {
                            public void run() {
                                CCBMainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        CCBMainActivity.this.u.loadUrl("javascript:createPictureResult()");
                                    }
                                });
                            }
                        }.run();
                    }
                }

                public void a(com.c.a.b.b bVar, String str) {
                    Log.i("Polling", "upload file failure responString" + bVar.toString() + str);
                    i.a().b();
                    new Runnable() {
                        public void run() {
                            CCBMainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    CCBMainActivity.this.u.loadUrl("javascript:errorHandle(" + null + ",'网络异常，请稍后尝试。')");
                                }
                            });
                        }
                    }.run();
                }

                public void a() {
                    super.a();
                    Log.i(CCBMainActivity.this.t, "--------upload file start------");
                }

                public void a(long j, long j2, boolean z) {
                    super.a(j, j2, z);
                    Log.i(CCBMainActivity.this.t, "--------onLoading file ,total is" + j + ";current is " + j2 + "------");
                }
            });
        }
    }

    private void c(String str) {
        this.l = new FileUploadEntity();
        this.l.USERID = h.a(str, "USERID", "");
        this.l.BRANCHID = h.a(str, "BRANCHID", "");
        this.l.TXCODE = h.a(str, "TXCODE", "");
        this.l.File_Date = h.a(str, "File_Date", "");
        this.l.CCB_IBSVersion = h.a(str, "CCB_IBSVersion", "");
        this.l.ACTION = h.a(str, "ACTION", "");
        this.m = h.a(str, "File_Nm", "");
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        b(i2);
    }

    private void b(int i2) {
        if (f.a()) {
            File file = new File(Environment.getExternalStorageDirectory() + "/ccb");
            if (!file.exists()) {
                file.mkdir();
            }
            this.p = Environment.getExternalStorageDirectory() + "/ccb/temp.jpg";
            this.f399q = new File(this.p);
            this.r = com.e23.ajn.ccb.d.a.a(this, this.f399q);
            com.e23.ajn.ccb.d.a.a(this, i2, this.r);
            return;
        }
        Toast.makeText(this, "您的手机不存在sd卡，无法保存相片", 0).show();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (1 == i2) {
            if (this.w == null) {
                this.w = new IDCardEntity();
            }
            if (i3 == -1) {
                Log.i(this.t, "BitmapFactory.decodeFile");
                Bitmap c2 = c();
                if (c2 == null) {
                    Toast.makeText(this, "相机拍照失败，未获取到照片。", 0).show();
                    return;
                }
                Log.i(this.t, "FileUtils.compressBitmap");
                this.h = f.a(c2, 350);
                Log.i(this.t, "Base64.encodeToString");
                this.w.cardImage = Base64.encodeToString(this.h, 0).replaceAll("\r|\n", "");
                this.w.scanType = "TakePicture";
                new Runnable() {
                    public void run() {
                        final String a2 = h.a(CCBMainActivity.this.w);
                        CCBMainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CCBMainActivity.this.u.loadUrl("javascript:scanIdCardFrontResult('" + a2 + "')");
                            }
                        });
                    }
                }.run();
                return;
            }
            Toast.makeText(this, "相机拍照失败，未获取到照片。", 0).show();
        } else if (2 == i2) {
            if (this.w == null) {
                this.w = new IDCardEntity();
            }
            if (i3 == -1) {
                Bitmap c3 = c();
                if (c3 == null) {
                    Toast.makeText(this, "相机拍照失败，未获取到照片。", 0).show();
                    return;
                }
                Log.i(this.t, "FileUtils.compressBitmap");
                this.i = f.a(c3, 350);
                Log.i(this.t, "Base64.encodeToString");
                this.w.cardImage = Base64.encodeToString(this.i, 0).replaceAll("\r|\n", "");
                this.w.scanType = "TakePicture";
                new Runnable() {
                    public void run() {
                        final String a2 = h.a(CCBMainActivity.this.w);
                        CCBMainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CCBMainActivity.this.u.loadUrl("javascript:scanIdCardBackResult('" + a2 + "')");
                            }
                        });
                    }
                }.run();
                return;
            }
            Toast.makeText(this, "相机拍照失败，未获取到照片。", 0).show();
        } else if (i2 == 4) {
            final String stringExtra = intent.getStringExtra("PARAMS");
            runOnUiThread(new Runnable() {
                public void run() {
                    CCBMainActivity.this.u.loadUrl("javascript:closeWebViewResult('" + stringExtra + "')");
                }
            });
        } else if (i2 == 8) {
            a(i3, this.c.getLivenessResultImages(intent, 50));
        }
    }

    public void saveIdCardPic2Cache(String str) {
        String a2;
        String str2 = "";
        String str3 = "";
        String str4 = "";
        try {
            if (this.v) {
                a2 = str;
            } else {
                a2 = h.a(str, "File_Nm", "");
            }
            if (this.h != null) {
                str3 = f.a(this.a, this.h, a2 + "_ZM");
            }
            if (this.i != null) {
                str2 = f.a(this.a, this.i, a2 + "_FM");
            }
            this.k.put("FrontPicPath", str3);
            this.k.put("BackPicPath", str2);
            c(str);
            uploadFiles(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private Bitmap c() {
        if (this.p == null) {
            this.p = Environment.getExternalStorageDirectory() + "/ccb/temp.jpg";
        }
        int b2 = f.b(this.p);
        Bitmap decodeFile = BitmapFactory.decodeFile(this.p);
        if (b2 != 0) {
            return f.a(b2, decodeFile);
        }
        return decodeFile;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.u != null) {
            ViewGroup viewGroup = (ViewGroup) this.u.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.u);
            }
            this.u.removeAllViews();
            this.u.destroy();
        }
        super.onDestroy();
    }
}
