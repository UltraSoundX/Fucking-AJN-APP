package cn.sharesdk.framework.loopshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.loopshare.MobLink;
import cn.sharesdk.loopshare.Scene;
import cn.sharesdk.loopshare.SceneRestorable;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public class RestoreTempActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            MobLink.setActivityDelegate(this, new SceneRestorable() {
                public void onReturnSceneData(Scene scene) {
                    if (scene != null) {
                        scene.getPath();
                        HashMap params = scene.getParams();
                        String str = (String) params.get("targetAction_And");
                        params.remove("targetAction_And");
                        params.remove("targetAction_iOS");
                        try {
                            String fromHashMap = new Hashon().fromHashMap(params);
                            if (!TextUtils.isEmpty(fromHashMap)) {
                                new a(MobSDK.getContext(), "sharesdk_moblink_sp").a("share_restore_extra", fromHashMap);
                                SSDKLog.b().d("LoopShare RestoreTempActivity save json is okd " + fromHashMap, new Object[0]);
                            }
                            if (MobLinkAPI.b() != null) {
                                MobLinkAPI.b().onResult(params);
                                SSDKLog.b().d("LoopShare RestoreTempActivity onResult is OK", new Object[0]);
                            }
                            RestoreTempActivity.this.finish();
                        } catch (Throwable th) {
                            SSDKLog.b().d("LoopShare RestoreTempActivity onReturnSceneData catch " + th, new Object[0]);
                            if (MobLinkAPI.b() != null) {
                                MobLinkAPI.b().onError(th);
                            }
                            RestoreTempActivity.this.finish();
                        }
                    }
                }
            });
        } catch (Throwable th) {
            SSDKLog.b().d("LoopShare RestoreTempActivity onCreate catch " + th, new Object[0]);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            MobLink.updateNewIntent(intent, this);
            SSDKLog.b().d("LoopShare RestoreTempActivity onNewIntent ", new Object[0]);
        } catch (Throwable th) {
            SSDKLog.b().d("LoopShare RestoreTempActivity onNewIntent catch " + th, new Object[0]);
        }
    }
}
