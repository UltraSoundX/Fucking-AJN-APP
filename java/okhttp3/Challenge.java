package okhttp3;

import net.sf.json.util.JSONUtils;
import okhttp3.internal.Util;

public final class Challenge {
    private final String realm;
    private final String scheme;

    public Challenge(String str, String str2) {
        this.scheme = str;
        this.realm = str2;
    }

    public String scheme() {
        return this.scheme;
    }

    public String realm() {
        return this.realm;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Challenge) && Util.equal(this.scheme, ((Challenge) obj).scheme) && Util.equal(this.realm, ((Challenge) obj).realm);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.realm != null) {
            i = this.realm.hashCode();
        } else {
            i = 0;
        }
        int i3 = (i + 899) * 31;
        if (this.scheme != null) {
            i2 = this.scheme.hashCode();
        }
        return i3 + i2;
    }

    public String toString() {
        return this.scheme + " realm=\"" + this.realm + JSONUtils.DOUBLE_QUOTE;
    }
}
