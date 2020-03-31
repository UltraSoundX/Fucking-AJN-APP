package cn.sharesdk.framework.loopshare;

import android.app.Activity;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.loopshare.ActionListener;
import cn.sharesdk.loopshare.MobLink;
import cn.sharesdk.loopshare.RestoreSceneListener;
import cn.sharesdk.loopshare.Scene;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public class MobLinkAPI {
    /* access modifiers changed from: private */
    public static MoblinkActionListener a;
    private static LoopShareResultListener b;
    private static volatile MobLinkAPI c = null;

    private static class SceneListener implements RestoreSceneListener {
        private SceneListener() {
        }

        public Class<? extends Activity> willRestoreScene(Scene scene) {
            SSDKLog.b().d("LoopShare MobLinkAPI willRestoreScene ==> RestoreTempActivity", new Object[0]);
            return RestoreTempActivity.class;
        }

        public void notFoundScene(Scene scene) {
        }

        public void completeRestore(Scene scene) {
        }
    }

    public static MobLinkAPI a() {
        synchronized (MobLinkAPI.class) {
            if (c == null) {
                synchronized (MobLinkAPI.class) {
                    if (c == null) {
                        c = new MobLinkAPI();
                    }
                }
            }
        }
        return c;
    }

    public static LoopShareResultListener b() {
        return b;
    }

    public static void a(LoopShareResultListener loopShareResultListener) {
        b = loopShareResultListener;
    }

    public static void a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        if (moblinkActionListener != null) {
            try {
                a = moblinkActionListener;
                Scene scene = new Scene();
                scene.setPath(String.valueOf(hashMap.get(Config.FEED_LIST_ITEM_PATH)));
                if (hashMap.get("params") instanceof HashMap) {
                    scene.setParams((HashMap) hashMap.get("params"));
                } else if (a != null) {
                    a.onError(new Throwable("setLoopshareCustomParams方法中 params 为key的时候，value需要为HashMap类型"));
                    return;
                }
                MobLink.getMobID(scene, new ActionListener<String>() {
                    public void onResult(String str) {
                        if (MobLinkAPI.a != null) {
                            MobLinkAPI.a.onResult(str);
                            MobLinkAPI.a = null;
                        }
                    }

                    public void onError(Throwable th) {
                        if (MobLinkAPI.a != null) {
                            MobLinkAPI.a.onError(th);
                            MobLinkAPI.a = null;
                        }
                    }
                });
            } catch (Throwable th) {
                SSDKLog.b().e("LoopShare MobLinkAPI mobLinkGetMobID" + th, new Object[0]);
            }
        }
    }

    public static void b(LoopShareResultListener loopShareResultListener) {
        if (loopShareResultListener != null) {
            try {
                a(loopShareResultListener);
            } catch (Throwable th) {
                SSDKLog.b().e("LoopShare MobLinkAPI prepareLoopShare " + th, new Object[0]);
                return;
            }
        }
        MobLink.registerSpecifiedSchemeListener("sdfwe435fdsr34656uthfwer32ufeh439==", new SceneListener());
        SSDKLog.b().d("LoopShare MobLinkAPI prepareLoopShare is OK", new Object[0]);
    }

    public static HashMap<String, Object> c() {
        try {
            String trim = new a(MobSDK.getContext(), "sharesdk_moblink_sp").b("share_restore_extra", null).toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                return new Hashon().fromJson(trim);
            }
        } catch (Throwable th) {
            SSDKLog.b().e("LoopShare MobLinkAPI getCustomDataFromLoopShare " + th, new Object[0]);
        }
        return new HashMap<>();
    }
}
