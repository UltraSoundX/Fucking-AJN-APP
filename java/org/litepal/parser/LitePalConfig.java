package org.litepal.parser;

import java.util.ArrayList;
import java.util.List;

public class LitePalConfig {
    private String cases;
    private List<String> classNames;
    private String dbName;
    private String storage;
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String str) {
        this.dbName = str;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String str) {
        this.storage = str;
    }

    public List<String> getClassNames() {
        if (this.classNames == null) {
            this.classNames = new ArrayList();
            this.classNames.add("org.litepal.model.Table_Schema");
        } else if (this.classNames.isEmpty()) {
            this.classNames.add("org.litepal.model.Table_Schema");
        }
        return this.classNames;
    }

    public void addClassName(String str) {
        getClassNames().add(str);
    }

    public void setClassNames(List<String> list) {
        this.classNames = list;
    }

    public String getCases() {
        return this.cases;
    }

    public void setCases(String str) {
        this.cases = str;
    }
}
