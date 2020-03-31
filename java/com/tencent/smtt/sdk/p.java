package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* compiled from: TbsVideoUtilsWizard */
class p {
    private DexLoader a = null;

    public p(DexLoader dexLoader) {
        this.a = dexLoader;
    }

    public void a(Context context, String str) {
        if (this.a != null) {
            Object newInstance = this.a.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0]);
            if (newInstance != null) {
                this.a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "deleteVideoCache", new Class[]{Context.class, String.class}, context, str);
            }
        }
    }

    public String a(Context context) {
        if (this.a != null) {
            Object newInstance = this.a.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0]);
            if (newInstance != null) {
                Object invokeMethod = this.a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "getCurWDPDecodeType", new Class[]{Context.class}, context);
                if (invokeMethod != null) {
                    return String.valueOf(invokeMethod);
                }
            }
        }
        return "";
    }
}
