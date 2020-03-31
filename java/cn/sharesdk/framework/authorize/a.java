package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;

/* compiled from: AbstractAuthorizeActivity */
public class a extends FakeActivity {
    protected AuthorizeHelper a;

    public void a(AuthorizeHelper authorizeHelper) {
        this.a = authorizeHelper;
        super.show(MobSDK.getContext(), null);
    }

    public void show(Context context, Intent intent) {
        throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
    }

    public AuthorizeHelper a() {
        return this.a;
    }
}
