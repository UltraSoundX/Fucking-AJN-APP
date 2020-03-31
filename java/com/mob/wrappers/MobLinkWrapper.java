package com.mob.wrappers;

import com.mob.moblink.ActionListener;
import com.mob.moblink.MobLink;
import com.mob.moblink.Scene;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashMap;
import java.util.Map;

public class MobLinkWrapper extends SDKWrapper implements PublicMemberKeeper {
    private static int state;

    public interface MobIdCallback {
        void onError(Throwable th);

        void onResult(String str);
    }

    private static synchronized boolean isAvailable() {
        boolean z = true;
        synchronized (MobLinkWrapper.class) {
            if (state == 0) {
                state = isAvailable("MOBLINK");
            }
            if (state != 1) {
                z = false;
            }
        }
        return z;
    }

    public static boolean getMobId(String str, String str2, Map<String, Object> map, final MobIdCallback mobIdCallback) {
        if (!isAvailable()) {
            return false;
        }
        Scene scene = new Scene();
        scene.path = str;
        scene.source = str2;
        scene.params = new HashMap(map);
        MobLink.getMobID(scene, new ActionListener<String>() {
            public void onResult(String str) {
                mobIdCallback.onResult(str);
            }

            public void onError(Throwable th) {
                mobIdCallback.onError(th);
            }
        });
        return true;
    }
}
