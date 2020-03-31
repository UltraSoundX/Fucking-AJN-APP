package org.litepal.tablemanager.typechange;

public class BlobOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str == null || !str.equals("[B")) {
            return null;
        }
        return "blob";
    }
}
