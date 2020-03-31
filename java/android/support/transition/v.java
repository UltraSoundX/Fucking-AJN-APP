package android.support.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: TransitionValues */
public class v {
    public final Map<String, Object> a = new HashMap();
    public View b;
    final ArrayList<Transition> c = new ArrayList<>();

    public boolean equals(Object obj) {
        if (!(obj instanceof v) || this.b != ((v) obj).b || !this.a.equals(((v) obj).a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.b + "\n") + "    values:";
        Iterator it = this.a.keySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2;
            }
            String str3 = (String) it.next();
            str = str2 + "    " + str3 + ": " + this.a.get(str3) + "\n";
        }
    }
}
