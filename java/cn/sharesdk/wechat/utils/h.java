package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;

/* compiled from: WXLaunchMiniProgram */
public class h {

    /* compiled from: WXLaunchMiniProgram */
    public static final class a extends l {
        public String a;
        public String b = "";
        public int c = 0;

        public final int a() {
            return 19;
        }

        public final boolean a(boolean z) {
            if (this.a == null || this.a.length() == 0 || this.a.length() > 10240) {
                SSDKLog.b().d("checkArgs fail, userName is invalid", new Object[0]);
                return false;
            } else if (this.c >= 0 && this.c <= 2) {
                return true;
            } else {
                SSDKLog.b().d("checkArgs fail", "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW");
                return false;
            }
        }

        public final void a(Bundle bundle) {
            super.a(bundle);
            bundle.putString("_launch_wxminiprogram_username", this.a);
            bundle.putString("_launch_wxminiprogram_path", this.b);
            bundle.putInt("_launch_wxminiprogram_type", this.c);
        }
    }
}
