package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.d.j;
import com.tencent.tauth.b;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected boolean a = false;
    protected Handler b = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (!AssistActivity.this.isFinishing()) {
                        f.d("openSDK_LOG.AssistActivity", "-->finish by timeout");
                        AssistActivity.this.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private boolean c = false;
    private String d;

    public static Intent getAssistActivityIntent(Context context) {
        return new Intent(context, AssistActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String stringExtra;
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(3);
        f.b("openSDK_LOG.AssistActivity", "--onCreate--");
        if (getIntent() == null) {
            f.e("openSDK_LOG.AssistActivity", "-->onCreate--getIntent() returns null");
            finish();
        }
        Intent intent = (Intent) getIntent().getParcelableExtra(EXTRA_INTENT);
        int intExtra = intent == null ? 0 : intent.getIntExtra("key_request_code", 0);
        if (intent == null) {
            stringExtra = "";
        } else {
            stringExtra = intent.getStringExtra("appid");
        }
        this.d = stringExtra;
        Bundle bundleExtra = getIntent().getBundleExtra("h5_share_data");
        if (bundle != null) {
            this.c = bundle.getBoolean("RESTART_FLAG");
            this.a = bundle.getBoolean("RESUME_FLAG", false);
        }
        if (this.c) {
            f.b("openSDK_LOG.AssistActivity", "is restart");
        } else if (bundleExtra != null) {
            f.d("openSDK_LOG.AssistActivity", "--onCreate--h5 bundle not null, will open browser");
            a(bundleExtra);
        } else if (intent != null) {
            f.c("openSDK_LOG.AssistActivity", "--onCreate--activityIntent not null, will start activity, reqcode = " + intExtra);
            startActivityForResult(intent, intExtra);
        } else {
            f.e("openSDK_LOG.AssistActivity", "--onCreate--activityIntent is null");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        f.b("openSDK_LOG.AssistActivity", "-->onStart");
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        f.b("openSDK_LOG.AssistActivity", "-->onResume");
        super.onResume();
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("is_login", false)) {
            if (!intent.getBooleanExtra("is_qq_mobile_share", false) && this.c && !isFinishing()) {
                finish();
            }
            if (this.a) {
                this.b.sendMessage(this.b.obtainMessage(0));
                return;
            }
            this.a = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        f.b("openSDK_LOG.AssistActivity", "-->onPause");
        this.b.removeMessages(0);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        f.b("openSDK_LOG.AssistActivity", "-->onStop");
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        f.b("openSDK_LOG.AssistActivity", "-->onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        f.c("openSDK_LOG.AssistActivity", "--onNewIntent");
        super.onNewIntent(intent);
        int intExtra = intent.getIntExtra("key_request_code", -1);
        if (intExtra == 10108) {
            intent.putExtra("key_action", "action_request_avatar");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (!isFinishing()) {
                finish();
            }
        } else if (intExtra == 10109) {
            intent.putExtra("key_action", "action_request_set_emotion");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (!isFinishing()) {
                finish();
            }
        } else if (intExtra == 10110) {
            intent.putExtra("key_action", "action_request_dynamic_avatar");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (!isFinishing()) {
                finish();
            }
        } else if (intExtra == 10111) {
            intent.putExtra("key_action", "joinGroup");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (!isFinishing()) {
                finish();
            }
        } else if (intExtra == 10112) {
            intent.putExtra("key_action", "bindGroup");
            if (intent.getBooleanExtra("stay_back_stack", false)) {
                moveTaskToBack(true);
            }
            setResult(-1, intent);
            if (!isFinishing()) {
                finish();
            }
        } else {
            intent.putExtra("key_action", "action_share");
            setResult(-1, intent);
            if (!isFinishing()) {
                f.c("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        f.b("openSDK_LOG.AssistActivity", "--onSaveInstanceState--");
        bundle.putBoolean("RESTART_FLAG", true);
        bundle.putBoolean("RESUME_FLAG", this.a);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        f.c("openSDK_LOG.AssistActivity", "--onActivityResult--requestCode: " + i + " | resultCode: " + i2 + "data = null ? " + (intent == null));
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (intent != null) {
                intent.putExtra("key_action", "action_login");
            }
            setResultData(i, intent);
            finish();
        }
    }

    public void setResultData(int i, Intent intent) {
        if (intent == null) {
            f.d("openSDK_LOG.AssistActivity", "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            setResult(0);
            if (i == 11101) {
                d.a().a("", this.d, "2", "1", "7", "2");
                return;
            }
            return;
        }
        try {
            String stringExtra = intent.getStringExtra("key_response");
            f.b("openSDK_LOG.AssistActivity", "--setResultDataForLogin-- " + stringExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                JSONObject jSONObject = new JSONObject(stringExtra);
                String optString = jSONObject.optString("openid");
                String optString2 = jSONObject.optString("access_token");
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    f.d("openSDK_LOG.AssistActivity", "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                    setResult(0, intent);
                    d.a().a("", this.d, "2", "1", "7", "1");
                    return;
                }
                f.c("openSDK_LOG.AssistActivity", "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
                setResult(-1, intent);
                d.a().a(optString, this.d, "2", "1", "7", "0");
                return;
            }
            f.d("openSDK_LOG.AssistActivity", "--setResultData--response is empty, setResult ACTIVITY_OK");
            setResult(-1, intent);
        } catch (Exception e) {
            f.e("openSDK_LOG.AssistActivity", "--setResultData--parse response failed");
            e.printStackTrace();
        }
    }

    private void a(Bundle bundle) {
        String string = bundle.getString("viaShareType");
        String string2 = bundle.getString("callbackAction");
        String string3 = bundle.getString("url");
        String string4 = bundle.getString("openId");
        String string5 = bundle.getString("appId");
        String str = "";
        String str2 = "";
        if ("shareToQQ".equals(string2)) {
            str = "ANDROIDQQ.SHARETOQQ.XX";
            str2 = "10";
        } else if ("shareToQzone".equals(string2)) {
            str = "ANDROIDQQ.SHARETOQZ.XX";
            str2 = "11";
        }
        if (!j.a((Context) this, string3)) {
            b a2 = b.a().a(string2);
            if (a2 != null) {
                a2.onError(new com.tencent.tauth.d(-6, "打开浏览器失败!", null));
            }
            d.a().a(string4, string5, str, str2, "3", "1", string, "0", "2", "0");
            finish();
        } else {
            d.a().a(string4, string5, str, str2, "3", "0", string, "0", "2", "0");
        }
        getIntent().removeExtra("shareH5");
    }
}
