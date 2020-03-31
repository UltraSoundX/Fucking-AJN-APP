package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.b;
import com.tencent.open.a.f;
import com.tencent.open.d.g;
import com.tencent.open.d.j;
import com.tencent.smtt.sdk.TbsConfig;

/* compiled from: ProGuard */
public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            f.d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            finish();
            return;
        }
        Uri uri = null;
        try {
            uri = getIntent().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.a("openSDK_LOG.AuthActivity", "-->onCreate, uri: " + uri);
        try {
            a(uri);
        } catch (Exception e2) {
            e2.printStackTrace();
            finish();
        }
    }

    private void a(Uri uri) {
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (uri == null || uri.toString() == null || uri.toString().equals("")) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
            finish();
            return;
        }
        String uri2 = uri.toString();
        Bundle a2 = j.a(uri2.substring(uri2.indexOf("#") + 1));
        if (a2 == null) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
            finish();
            return;
        }
        String string = a2.getString("action");
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, action: " + string);
        if (string == null) {
            finish();
        } else if (string.equals("shareToQQ") || string.equals("shareToQzone") || string.equals("sendToMyComputer") || string.equals("shareToTroopBar")) {
            if (string.equals("shareToQzone") && g.a((Context) this, TbsConfig.APP_QQ) != null && g.b(this, "5.2.0") < 0) {
                a++;
                if (a == 2) {
                    a = 0;
                    finish();
                    return;
                }
            }
            f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
            Intent intent = new Intent(this, AssistActivity.class);
            intent.putExtras(a2);
            intent.setFlags(603979776);
            startActivity(intent);
            finish();
        } else if (string.equals("addToQQFavorites")) {
            Intent intent2 = getIntent();
            intent2.putExtras(a2);
            intent2.putExtra("key_action", "action_share");
            b a3 = b.a().a(string);
            if (a3 != null) {
                b.a().a(intent2, a3);
            }
            finish();
        } else if (string.equals(ACTION_SHARE_PRIZE)) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            String string2 = a2.getString("response");
            String str = "";
            try {
                str = j.c(string2).getString("activityid");
            } catch (Exception e) {
                f.a("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", e);
            }
            if (!TextUtils.isEmpty(str)) {
                launchIntentForPackage.putExtra(ACTION_SHARE_PRIZE, true);
                Bundle bundle = new Bundle();
                bundle.putString("activityid", str);
                launchIntentForPackage.putExtras(bundle);
            }
            startActivity(launchIntentForPackage);
            finish();
        } else if (string.equals("sdkSetAvatar")) {
            boolean booleanExtra = getIntent().getBooleanExtra("stay_back_stack", false);
            Intent intent3 = new Intent(this, AssistActivity.class);
            intent3.putExtra("key_request_code", 10108);
            intent3.putExtra("stay_back_stack", booleanExtra);
            intent3.putExtras(a2);
            intent3.setFlags(603979776);
            startActivity(intent3);
            finish();
        } else if ("sdkSetDynamicAvatar".equals(string)) {
            boolean booleanExtra2 = getIntent().getBooleanExtra("stay_back_stack", false);
            Intent intent4 = new Intent(this, AssistActivity.class);
            intent4.putExtra("key_request_code", 10110);
            intent4.putExtra("stay_back_stack", booleanExtra2);
            intent4.putExtras(a2);
            intent4.setFlags(603979776);
            startActivity(intent4);
            finish();
        } else if (string.equals("sdkSetEmotion")) {
            boolean booleanExtra3 = getIntent().getBooleanExtra("stay_back_stack", false);
            Intent intent5 = new Intent(this, AssistActivity.class);
            intent5.putExtra("key_request_code", 10109);
            intent5.putExtra("stay_back_stack", booleanExtra3);
            intent5.putExtras(a2);
            intent5.setFlags(603979776);
            startActivity(intent5);
            finish();
        } else if (string.equals("bindGroup")) {
            f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--bind group callback.");
            boolean booleanExtra4 = getIntent().getBooleanExtra("stay_back_stack", false);
            Intent intent6 = new Intent(this, AssistActivity.class);
            intent6.putExtra("key_request_code", 10112);
            intent6.putExtra("stay_back_stack", booleanExtra4);
            intent6.putExtras(a2);
            intent6.setFlags(603979776);
            startActivity(intent6);
            finish();
        } else if (string.equals("joinGroup")) {
            f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--join group callback. ");
            boolean booleanExtra5 = getIntent().getBooleanExtra("stay_back_stack", false);
            Intent intent7 = new Intent(this, AssistActivity.class);
            intent7.putExtra("key_request_code", 10111);
            intent7.putExtra("stay_back_stack", booleanExtra5);
            intent7.putExtras(a2);
            intent7.setFlags(603979776);
            startActivity(intent7);
            finish();
        } else {
            finish();
        }
    }
}
