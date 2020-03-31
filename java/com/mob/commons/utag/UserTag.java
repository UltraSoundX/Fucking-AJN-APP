package com.mob.commons.utag;

import com.mob.tools.proguard.PublicMemberKeeper;

public class UserTag implements PublicMemberKeeper {
    private UserTag() {
    }

    public static UserTager tagUser() {
        return new UserTager();
    }

    public static TagRequester getUserTags() {
        return new TagRequester();
    }
}
