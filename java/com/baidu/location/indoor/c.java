package com.baidu.location.indoor;

import java.util.ArrayList;

public class c<T> extends ArrayList<T> {
    private int a = 0;

    public c(int i) {
        this.a = i;
    }

    public boolean add(T t) {
        synchronized (this) {
            if (size() == this.a) {
                remove(0);
            }
            add(size(), t);
        }
        return true;
    }

    public void clear() {
        synchronized (this) {
            if (size() > 3) {
                int size = size() / 2;
                while (true) {
                    int i = size - 1;
                    if (size > 0) {
                        remove(0);
                        size = i;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
