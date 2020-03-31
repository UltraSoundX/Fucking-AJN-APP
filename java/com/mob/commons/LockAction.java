package com.mob.commons;

import com.mob.tools.utils.FileLocker;

public interface LockAction {
    boolean run(FileLocker fileLocker);
}
