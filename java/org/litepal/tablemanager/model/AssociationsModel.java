package org.litepal.tablemanager.model;

public class AssociationsModel {
    private String associatedTableName;
    private int associationType;
    private String tableHoldsForeignKey;
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String getAssociatedTableName() {
        return this.associatedTableName;
    }

    public void setAssociatedTableName(String str) {
        this.associatedTableName = str;
    }

    public String getTableHoldsForeignKey() {
        return this.tableHoldsForeignKey;
    }

    public void setTableHoldsForeignKey(String str) {
        this.tableHoldsForeignKey = str;
    }

    public int getAssociationType() {
        return this.associationType;
    }

    public void setAssociationType(int i) {
        this.associationType = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AssociationsModel) {
            AssociationsModel associationsModel = (AssociationsModel) obj;
            if (associationsModel.getTableName() != null && associationsModel.getAssociatedTableName() != null && associationsModel.getAssociationType() == this.associationType && associationsModel.getTableHoldsForeignKey().equals(this.tableHoldsForeignKey)) {
                if (associationsModel.getTableName().equals(this.tableName) && associationsModel.getAssociatedTableName().equals(this.associatedTableName) && associationsModel.getTableHoldsForeignKey().equals(this.tableHoldsForeignKey)) {
                    return true;
                }
                if (associationsModel.getTableName().equals(this.associatedTableName) && associationsModel.getAssociatedTableName().equals(this.tableName) && associationsModel.getTableHoldsForeignKey().equals(this.tableHoldsForeignKey)) {
                    return true;
                }
            }
        }
        return false;
    }
}
