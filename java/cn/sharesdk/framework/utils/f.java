package cn.sharesdk.framework.utils;

import com.mob.commons.InternationalDomain;
import java.util.HashMap;

/* compiled from: ShareSDKDomain */
public class f {
    private HashMap<String, String> a = new HashMap<>();

    public f() {
        this.a.put("jp", "Japan");
        this.a.put("us", "United States of America");
    }

    public boolean a(InternationalDomain internationalDomain) {
        if (this.a.containsKey(internationalDomain.getDomain())) {
            return true;
        }
        return false;
    }
}
