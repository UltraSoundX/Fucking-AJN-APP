package org.litepal.tablemanager.typechange;

public class TextOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str != null) {
            if (str.equals("char") || str.equals("java.lang.Character")) {
                return "text";
            }
            if (str.equals("java.lang.String")) {
                return "text";
            }
        }
        return null;
    }
}
