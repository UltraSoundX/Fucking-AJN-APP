package org.litepal.crud.callback;

import java.util.List;

public interface FindMultiCallback {
    <T> void onFinish(List<T> list);
}
