package org.litepal.tablemanager.typechange;

public class DecimalOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str != null) {
            if (str.equals("float") || str.equals("java.lang.Float")) {
                return "real";
            }
            if (str.equals("double") || str.equals("java.lang.Double")) {
                return "real";
            }
        }
        return null;
    }
}
