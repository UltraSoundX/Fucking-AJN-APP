package com.mob.tools.java8;

import com.mob.tools.java8.Closure.IClosureV;

public class Optional<R> {
    private R value;

    public Optional(R r) {
        this.value = r;
    }

    public R get() {
        return this.value;
    }

    public Optional<R> ifPresent(Consumer<R> consumer) {
        if (this.value != null) {
            consumer.test(this.value);
        }
        return this;
    }

    public R orElse(R r) {
        return this.value == null ? r : this.value;
    }

    public void orElse(IClosureV iClosureV) {
        if (this.value == null) {
            Closure.uncheck(iClosureV);
        }
    }
}
