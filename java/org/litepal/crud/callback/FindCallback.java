package org.litepal.crud.callback;

public interface FindCallback {
    <T> void onFinish(T t);
}
