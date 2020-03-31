package org.litepal.tablemanager.typechange;

public class DateOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str == null || !str.equals("java.util.Date")) {
            return null;
        }
        return "integer";
    }
}
