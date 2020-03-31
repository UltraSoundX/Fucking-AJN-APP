package com.tencent.android.tpush.data;

import java.io.Serializable;

/* compiled from: ProGuard */
public class CachedMessageIntent implements Serializable {
    private static final long serialVersionUID = 1724218633838690967L;
    public String intent = "";
    public long msgId;
    public String pkgName = "";

    public boolean equals(Object obj) {
        if (!(obj instanceof CachedMessageIntent)) {
            return super.equals(obj);
        }
        CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) obj;
        return cachedMessageIntent.pkgName.equals(this.pkgName) && cachedMessageIntent.msgId == this.msgId;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "CachedMessageIntent [pkgName=" + this.pkgName + ", msgId=" + this.msgId + "]";
    }
}
