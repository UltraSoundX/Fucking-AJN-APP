package org.litepal.tablemanager.typechange;

import com.tencent.android.tpush.SettingsContentProvider;

public class NumericOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str != null) {
            if (str.equals("int") || str.equals("java.lang.Integer")) {
                return "integer";
            }
            if (str.equals(SettingsContentProvider.LONG_TYPE) || str.equals("java.lang.Long")) {
                return "integer";
            }
            if (str.equals("short") || str.equals("java.lang.Short")) {
                return "integer";
            }
        }
        return null;
    }
}
