package com.stub.plugin;

public class BusiItem {
    private Class<?> delegateClz = null;
    private Object delegateImpl = null;

    public Class<?> getDelegateClz() {
        return this.delegateClz;
    }

    public void setDelegateClz(Class<?> cls) {
        this.delegateClz = cls;
    }

    public Object getDelegateImpl() {
        return this.delegateImpl;
    }

    public void setDelegateImpl(Object obj) {
        this.delegateImpl = obj;
    }
}
