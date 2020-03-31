package org.litepal.crud.model;

import java.lang.reflect.Field;

public class AssociationsInfo {
    private Field associateOtherModelFromSelf;
    private Field associateSelfFromOtherModel;
    private String associatedClassName;
    private int associationType;
    private String classHoldsForeignKey;
    private String selfClassName;

    public String getSelfClassName() {
        return this.selfClassName;
    }

    public void setSelfClassName(String str) {
        this.selfClassName = str;
    }

    public String getAssociatedClassName() {
        return this.associatedClassName;
    }

    public void setAssociatedClassName(String str) {
        this.associatedClassName = str;
    }

    public String getClassHoldsForeignKey() {
        return this.classHoldsForeignKey;
    }

    public void setClassHoldsForeignKey(String str) {
        this.classHoldsForeignKey = str;
    }

    public Field getAssociateOtherModelFromSelf() {
        return this.associateOtherModelFromSelf;
    }

    public void setAssociateOtherModelFromSelf(Field field) {
        this.associateOtherModelFromSelf = field;
    }

    public Field getAssociateSelfFromOtherModel() {
        return this.associateSelfFromOtherModel;
    }

    public void setAssociateSelfFromOtherModel(Field field) {
        this.associateSelfFromOtherModel = field;
    }

    public int getAssociationType() {
        return this.associationType;
    }

    public void setAssociationType(int i) {
        this.associationType = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AssociationsInfo) {
            AssociationsInfo associationsInfo = (AssociationsInfo) obj;
            if (obj != null && associationsInfo != null && associationsInfo.getAssociationType() == this.associationType && associationsInfo.getClassHoldsForeignKey().equals(this.classHoldsForeignKey)) {
                if (associationsInfo.getSelfClassName().equals(this.selfClassName) && associationsInfo.getAssociatedClassName().equals(this.associatedClassName)) {
                    return true;
                }
                if (associationsInfo.getSelfClassName().equals(this.associatedClassName) && associationsInfo.getAssociatedClassName().equals(this.selfClassName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
